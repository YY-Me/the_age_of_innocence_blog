@rem = '--*-Perl-*--
@echo off
if "%OS%" == "Windows_NT" goto WinNT
perl -x -S "%0" %1 %2 %3 %4 %5 %6 %7 %8 %9
goto endofperl
:WinNT
perl -x -S %0 %*
if NOT "%COMSPEC%" == "%SystemRoot%\system32\cmd.exe" goto endofperl
if %errorlevel% == 9009 echo You do not have Perl in your PATH.
if errorlevel 1 goto script_failed_so_exit_with_non_zero_val 2>nul
goto endofperl
@rem ';
#!/usr/bin/env perl
#line 15

use strict;
use warnings;

our $VERSION = '0.21';

use Getopt::Std qw( getopts );
use File::Spec ();
use FindBin ();
use Cwd qw( realpath );
use File::Temp qw( tempfile );

my %opts;
getopts("r:vhs:", \%opts)
    or usage(1);

if ($opts{h}) {
    usage(0);
}

if ($opts{v}) {
    print "restydoc $VERSION\n";
    exit;
}

my $section_pat = $opts{s};
my $module_pat = shift;

if (!$section_pat && !$module_pat) {
    print STDERR "ERROR: you need to specify a module name or ",
                 "a section name.\n\n";
    usage(1);
}

my $interactive = (-t STDOUT);
my $pager = $ENV{RESTYDOC_PAGER};
my $on_win32 = ($^O eq 'msys' || $^O eq 'MSWin32');
if (!defined $pager) {
    if ($^O eq 'MSWin32') {
        $pager = 'more';
    } else {
        $pager = 'less -nR';
    }
}

my $root_dir = $opts{r};
if (!$root_dir) {
    $root_dir = "$FindBin::RealBin/..";
}

my $index_file = File::Spec->catfile($root_dir, "resty.index");
if (!-f $index_file) {
    if ($opts{r}) {
        die "cannot file resty.index under the root directory $root_dir/.\n";
    }

    # for Win32 installation tree layout:

    my $f = File::Spec->catfile($FindBin::RealBin, "resty.index");
    if (-f $f) {
        $root_dir = $FindBin::RealBin;
        $index_file = $f;
    }
}

$index_file = realpath($index_file);
open my $in, $index_file
    or die "cannot open $index_file for reading: $!\n";

my ($dist, @dists, $module, %modules, @modules, %module2dist);
while (<$in>) {
    next if / ^ \s* (?: \# .* )? $ /x;

    if (/ ^ module \s+ (\S+) \s* $/x) {
        my $name = $1;
        $module = {
            name => $name,
            priority => $.,  # smaller priority means higher priority.
            index => scalar @modules,
        };

        my $old_module = $modules{$name};
        if (defined $old_module) {
            undef $modules[$old_module->{index}];
        }

        undef $dist;

        $modules{$name} = $module;
        push @modules, $module;
        next;
    }

    if (/ ^ \s* section \s+ (\d+) \s+ (\d+) \s+ (\S.*) $ /xm) {
        my ($from, $to, $title) = ($1, $2, $3);
        $title =~ s/\s+$//;

        if ($from > $to) {
            die "ERROR: $index_file: line $.: invalid \"from\" and ",
                "\"to\" fields.\n";
        }

        undef $dist;

        if (!defined $module) {
            die "ERROR: $index_file: line $.: unepxected section line.\n";
        }

        my $sections = $module->{sections};
        if (!defined $sections) {
            $sections = [];
            $module->{sections} = $sections;
        }

        push @$sections, {
            title => $title,
            from => $from,
            to => $to,
        };

        next;
    }

    if (/ ^ dist \s+ (\S+) \s* $/x) {
        undef $module;
        $dist = {
            dist => $1,
        };
        push @dists, $dist;
        next;
    }

    if (/ ^ \s+ version \s+ (\S+) \s* $/x) {
        my $ver = $1;
        if (!defined $dist) {
            die "ERROR: $index_file: line $.: unexpected version line.\n";
        }
        $dist->{version} = $ver;
        next;
    }

    if (/ ^ \s+ aliases \s+ (\S.*) $/x) {
        (my $s = $1) =~ s/\s+$//;
        my @elems = split /\s+/, $s;
        if (!defined $dist && !defined $module) {
            die "ERROR: $index_file: line $.: unexpected aliases line.\n";
        }
        if (@elems) {
            if (defined $dist) {
                my %aliases = map { ($_ => 1) } @elems;
                $dist->{aliases} = \%aliases;
            } else {
                for my $alias (@elems) {
                    if (!defined $modules{$alias}) {
                        #warn "add module alias $alias for $module->{name}";
                        $modules{$alias} = $module;

                        my $dist = $module2dist{$module->{name}};
                        $dist->{modules}{$alias} = $module;
                    }
                }
            }
        }
        next;
    }

    if (/ ^ \s+ modules \s+ (\S.*) $/x) {
        (my $s = $1) =~ s/\s+$//;
        if (!defined $dist) {
            die "ERROR: $index_file: line $.: unexpected modules line.\n";
        }
        my @elems = split /\s+/, $s;
        if (@elems) {
            my %modules = map { ($_ => 1) } @elems;
            $dist->{modules} = \%modules;
            for my $elem (@elems) {
                $module2dist{$elem} = $dist;
            }
        }
        next;
    }

    die "ERROR: $index_file: line $.: syntax error: $_";
}

close $in;

if (!@dists) {
    die "ERROR: $index_file is empty.\n";
}

my $poddir = File::Spec->catfile($root_dir, "pod");

if ($section_pat) {
    $section_pat = lc $section_pat;

    for my $module (values %modules) {
        my $sections = $module->{sections};
        next if !defined $sections;
        my %dict;
        for my $sec (@$sections) {
            my $title = $sec->{title};
            next if exists $dict{$title};
            $dict{$title} = $sec;
        }
        $module->{section_dict} = \%dict;
    }
}

if ($module_pat) {
    if ($module_pat =~ m{/|\.\.}) {
        die "Invalid module pattern \"$module_pat\".\n";
    }

    $module_pat = lc $module_pat;

    my ($hit_dist, $hit_module_name) = find_module($module_pat);

    if (defined $hit_dist && !$section_pat) {
        process_module_hit($hit_dist, $hit_module_name);
        exit;
    }

    if (!defined $section_pat) {
        die "Documentation for module pattern \"$module_pat\" not found.\n";
    }

    # look for the section in the module

    my $module = $modules{$hit_module_name};
    if (!defined $module) {
        die "No \"module\" record found for $hit_module_name.\n";
    }

    # 1. attempt an exact match.

    my $section_dict = $module->{section_dict};
    #require Data::Dumper;
    #warn Data::Dumper::Dumper($section_dict);

    my $hit_section = $section_dict->{$section_pat};
    if (defined $hit_section) {
        process_section_hit($hit_dist, $hit_section, $hit_module_name);
        exit;
    }

    # 2. attemp a prefix match.

    my $sections = $module->{sections};
    for my $sec (@$sections) {
        if ($sec->{title} =~ / ^ \Q$section_pat\E /x) {
            process_section_hit($hit_dist, $sec, $hit_module_name);
            exit;
        }
    }

    # 3. attemp a contains match.

    for my $sec (@$sections) {
        if ($sec->{title} =~ / \Q$section_pat\E /x) {
            process_section_hit($hit_dist, $sec, $hit_module_name);
            exit;
        }
    }

    die "Documentation for section pattern \"$section_pat\" in ",
        "module $hit_module_name not found.\n";
}

# when only the section pattern is specified.

for my $module (@modules) {
    next if !defined $module;

    my $module_name = $module->{name};
    my $dist = $module2dist{$module_name};
    if (!defined $dist) {
        die "ERROR: $index_file: module $module_name does not ",
            "belong to any distributions.\n";
    }
}

# 1. attempt an exact match.

for my $module (@modules) {
    next if !defined $module;

    my $module_name = $module->{name};
    my $dist = $module2dist{$module_name};

    my $section_dict = $module->{section_dict};
    #require Data::Dumper;
    #warn Data::Dumper::Dumper($section_dict);

    my $hit_section = $section_dict->{$section_pat};
    if (defined $hit_section) {
        process_section_hit($dist, $hit_section, $module_name);
        exit;
    }
}

# 2. attemp a prefix match.

for my $module (@modules) {
    next if !defined $module;

    my $module_name = $module->{name};
    my $dist = $module2dist{$module_name};

    my $sections = $module->{sections};
    for my $sec (@$sections) {
        if ($sec->{title} =~ / ^ \Q$section_pat\E /x) {
            process_section_hit($dist, $sec, $module_name);
            exit;
        }
    }
}

# 3. attemp a contains match.

for my $module (@modules) {
    next if !defined $module;

    my $module_name = $module->{name};
    my $dist = $module2dist{$module_name};
    my $sections = $module->{sections};

    for my $sec (@$sections) {
        if ($sec->{title} =~ / \Q$section_pat\E /x) {
            process_section_hit($dist, $sec, $module_name);
            exit;
        }
    }
}

die "Documentation for section pattern \"$section_pat\" not found.\n";

sub find_module {
    my $pat = shift;

    my ($hit, $name);

    # 1. attempt an exact match

    for my $r (@dists) {
        if ($r->{dist} eq $pat) {
            #warn "Hit dist!";
            $hit = $r;
            $name = $pat;
            last;
        }

        if (defined $r->{modules} && $r->{modules}{$pat}) {
            #warn "Hit module!";
            $hit = $r;
            my $module = $modules{$pat};
            $name = $module->{name};
            last;
        }

        if (defined $r->{aliases} && $r->{aliases}{$pat}) {
            #warn "HIT dist alias!";
            $hit = $r;
            $name = $r->{dist};
            last;
        }
    }

    if (defined $hit) {
        if (!defined $hit->{modules} || !$hit->{modules}{$name}) {
            #warn "HERE";
            die "No documentation found for module $pat.\n";
        }

        return $hit, $name;
    }

    # 2. attempt a prefix match

    for my $r (@dists) {
        if ($r->{dist} =~ / ^ \Q$pat\E/x) {
            #warn "Hit dist prefix!";
            $hit = $r;
            $name = $r->{dist};
            last;
        }

        my $modules = $r->{modules};
        if (defined $modules) {
            for my $module (keys %$modules) {
                if ($module =~ / ^ \Q$pat\E /x) {
                    $hit = $r;
                    $name = $module;
                    last;
                }
            }

            if (defined $hit) {
                last;
            }
        }

        my $aliases = $r->{aliases};
        if (defined $aliases) {
            for my $alias (keys %$aliases) {
                if ($alias =~ / ^ \Q$pat\E /x) {
                    $hit = $r;
                    $name = $r->{dist};
                    last;
                }
            }

            if (defined $hit) {
                last;
            }
        }
    }

    if (defined $hit) {
        return $hit, $name;
    }

    # 3. attempt a contains search

    for my $r (@dists) {
        if ($r->{dist} =~ / \Q$pat\E/x) {
            $hit = $r;
            $name = $r->{dist};
            last;
        }

        my $modules = $r->{modules};
        if (defined $modules) {
            for my $module (keys %$modules) {
                if ($module =~ / \Q$pat\E /x) {
                    $hit = $r;
                    $name = $r->{dist};
                    last;
                }
            }

            if (defined $hit) {
                last;
            }
        }

        my $aliases = $r->{aliases};
        if (defined $aliases) {
            for my $alias (keys %$aliases) {
                if ($alias =~ / \Q$pat\E /x) {
                    $hit = $r;
                    $name = $r->{dist};
                    last;
                }
            }

            if (defined $hit) {
                last;
            }
        }
    }

    if (defined $hit) {
        return $hit, $name;
    }

    die "No documentation found for module pattern $pat.\n";
}

sub process_section_hit {
    my ($dist, $section, $module_name) = @_;

    my $dist_name = $dist->{dist};
    my $dist_ver = $dist->{version};
    my $full_dist_name;
    if (defined $dist_ver) {
        $full_dist_name = "$dist_name $dist_ver";
    } else {
        $full_dist_name = $dist_name;
    }

    my $podfile = File::Spec->catfile($poddir, $dist_name, "$module_name.pod");
    open my $in, $podfile
        or die "Cannot open $podfile for reading: $!\n";

    my $from = $section->{from};
    if (!defined $from) {
        die "No from defined in section $section->{title} in module $module_name.\n";
    }

    seek $in, $from, 0
        or die "Cannot seek to $section->{from} in file $podfile: $!\n";

    my $pod;
    my $to = $section->{to};
    read $in, $pod, $to - $from
        or die "Cannot read from file $podfile starting from $from through $to";

    close $in;

    if (!$pod) {
        die "ERROR: section \"$section->{title}\" is empty.\n";
    }

    my ($tmp, $tmpfile) = tempfile( CLEANUP => 1, SUFFIX => '.pod' );

    print $tmp "=encoding utf8\n\n";

    if ($pod =~ /\A\s*=item/s) {
        print $tmp "=over\n\n";
    }

    print $tmp $pod;

    if ($pod =~ /\A\s*=item/s && $pod !~ /=back\s*\z/s) {
        print $tmp "\n\n=back\n";
    }

    close $tmp;

    if (!$interactive) {
        exec("pod2text $tmpfile");
    }

    if ($on_win32) {
        exec("pod2text $tmpfile | $pager");
    }

    my $groff_cmd = get_groff_cmd();
    my $pod2man_cmd = get_pod2man_cmd();

    my $cmd = "$pod2man_cmd -c '$full_dist_name' "
          . " -r OpenResty -s 7 -n '$module_name' "
          . "$tmpfile | $groff_cmd | $pager";
    #warn $cmd;
    exec $cmd;
}

sub process_module_hit {
    my ($hit, $name) = @_;

    #warn "Found $name";
    my $dist_name = $hit->{dist};
    my $dist_ver = $hit->{version};
    my $full_dist_name;
    if (defined $dist_ver) {
        $full_dist_name = "$dist_name $dist_ver";

    } else {
        $full_dist_name = $dist_name;
    }

    my $podfile = File::Spec->catfile($poddir, $dist_name, "$name.pod");
    if (!-f $podfile) {
        die "POD file $podfile not found.\n";
    }

    if (!$interactive) {
        exec("pod2text $podfile");
    }

    if ($on_win32) {
        exec("pod2text $podfile | $pager");
    }

    my $groff_cmd = get_groff_cmd();
    my $pod2man_cmd = get_pod2man_cmd();

    exec("$pod2man_cmd -c '$full_dist_name' "
          . "-r OpenResty -s 7 -n '$name' "
          . "$podfile | $groff_cmd | $pager");
}

sub get_groff_cmd {
    my $cmd = "groff -Kutf8 -Tutf8 -mandoc -Wbreak";
    if (system("echo '=head1 NAME' | pod2man --errors none | $cmd > /dev/null 2>&1") == 0) {
        return $cmd;
    }
    return "groff -Tascii -mandoc -Wbreak";
}

sub get_pod2man_cmd {
    my $help = `pod2man --help`;
    if ($help =~ /^ \s+ -u \b/xm) {
        return "pod2man -u";
    }
    return "pod2man";
}

sub shell {
    my $cmd = shift;

    #warn $cmd;
    system($cmd) == 0
        or die "failed to run command \"$cmd\": $!\n";
}

sub usage {
    my $code = shift;
    my $msg = <<_EOC_;
Usage:
    $0 [options] [module]

Options:
    -h              Print this help.

    -r DIR          Specify the root directory of docs. Default
                    to the OpenResty installation tree containing
                    the current restydoc tool.

    -s SECTION      Specify the section name to be searched

For bug reporting instructions, please see:

    <https://openresty.org/en/community.html>

Copyright (C) Yichun Zhang (agentzh). All rights reserved.
_EOC_

    if ($code == 0) {
        print $msg;
        exit 0;
    }

    print STDERR $msg;
    exit $code;
}

__END__
:endofperl
