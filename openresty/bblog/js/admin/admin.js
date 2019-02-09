layui.config({
	base: '../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['element', 'common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		element = layui.element;
	var index = {
		myNav: null,
		init: function() {
			index.showUserInfo();
			index.changeSide(null, false, $(window).width());
			index.initNav();
			index.initMenuBar();
			index.bindEvent();
		},
		//初始化导航栏
		initNav: function() {
			index.myNav = new IScroll('#wrapper', {
				mouseWheel: true,
				bounce: true,
				scrollX: true,
				tap: true,
				click: true
			});
		},
		bindEvent: function() {
			//伸缩按钮事件
			$('#LAY_app_flexible').parent('a').click(function() {
				var that = $('#LAY_app_flexible');
				if($(that).hasClass('layui-icon-shrink-right')) {
					index.changeSide(0, true);
					$('.layadmin-body-shade').hide();
				} else {
					index.changeSide(200, false);
					if($(window).width() <= 768) {
						$('.layadmin-body-shade').show();
					}
				}
			});
			//点击遮罩事件
			$('.layadmin-body-shade').click(function() {
				index.changeSide(0, true);
				$(this).hide();
			});
			//窗口改变事件
			$(window).resize(function() {
				index.changeSide(null, false, $(window).width());
			});
			$('#user_info dl dd a').click(function() {
				var idx = $("#user_info dl dd a").index(this);
				switch(idx) {
					case 0:
						$('#update_password').trigger('click')
						break;
					case 1:
						layer.confirm("您要退出吗？", {
							icon: 3
						}, function(idx) {
							layer.close(idx);
							index.loginOut();
						});
						break;
				}
			});
			$(".layui-nav-tree").on('click', '>li >a', function() {
				$(".layui-nav-tree >li").not($(this).parent("li")).removeClass("layui-nav-itemed");
			});
		},
		isSlide: true,
		changeSide: function(sideWidth, flag, windowWidth) {
			windowWidth ? (windowWidth <= 768 ? sideWidth = 0 : sideWidth = 200) : '';
			$('#LAY_app_flexible').removeClass('layui-icon-shrink-right layui-icon-spread-left');
			if(flag || windowWidth <= 768) {
				$('#LAY_app_flexible').addClass('layui-icon-spread-left');
			} else {
				$('#LAY_app_flexible').addClass('layui-icon-shrink-right');
			}
			if(null != sideWidth && index.isSlide) {
				$('.layui-layout-admin .layui-side').css({
					left: -(200 - sideWidth) + "px"
				});
				$('.layui-layout-admin .layui-body').css({
					left: sideWidth + "px"
				});
			}
			setTimeout(function() {
				index.Naviscroll();
				console.log("刷新滑块")
			}, 350);
		},
		Naviscroll: function() {
			//改变.layui-layout-admin .layui-body .layui-tab .layui-tab-title的宽度
			var list = $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title li');
			var width = 0;
			var tabtitle = $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title');
			$.each(list, function(idx, obj) {
				width += parseInt($(this).outerWidth(true));
			});
			$(tabtitle).width(width + 3);
			index.myNav.refresh(); //刷新滑块
		},
		initMenuBar: function() {
			var items = $(".layui-side .layui-nav-tree a[data-url]");
			$.each(items, function(idx, obj) {
				$(this).attr("lay-id", "item" + idx);
			});
			items.parent("dd").on("click", function() {
				var flag = true;
				var tabs = $('#layui-tabs li');
				var url = $(this).children("a").data('url');
				var title = $(this).children("a").data('title') || $(this).children("a").text().trim();
				var id = $(this).children("a").attr('lay-id');
				if(url && title && id) {
					tabs.each(function(idx, obj) {
						//判断是否在右侧菜单栏是否打开
						if(id == $(obj).attr('lay-id')) {
							flag = false;
							element.tabChange('layui-tab', id); //如果存在，则打开它
						}
					});
					if(flag) {
						var close = $('<i class="layui-icon layui-unselect layui-tab-close" data-id="' + id + '">ဆ</i>');
						$(close).click(function() {
							element.tabDelete('layui-tab', $(this).data('id'));
							element.tabChange('layui-tab', $('#layui-tabs li:last').attr('lay-id'));
							index.Naviscroll(); //刷新滑块
						})
						element.tabAdd('layui-tab', {
							title: title,
							content: '<iframe scrolling="auto" frameborder="0" data-src="' + url + '"  src="' + url + '" style="width:100%;height:100%;"></iframe>',
							id: id
						});
						element.tabChange('layui-tab', id)
						$('#layui-tabs .layui-this').append(close);
						index.Naviscroll(); //刷新滑块
						index.refreshcontextMenu();
					}
				}
				//关闭遮罩
				$('.layadmin-body-shade').hide();
				if($(window).width() <= 768) {
					index.changeSide(0, true);
				}
			});
		},
		refreshcontextMenu: function() {
			$('#wrapper ul li').not('.layui-tab-index').contextMenu('myMenu1', {
				bindings: {
					'deletemyself': function(t) {
						element.tabDelete('layui-tab', $(t).attr('lay-id'));
						element.tabChange('layui-tab', $('#layui-tabs li:last').attr('lay-id'));
						index.Naviscroll();
					},
					'deleteother': function(t) {
						$.each($('#layui-tabs li'), function(idx, obj) {
							if($(t).attr('lay-id') != $(obj).attr('lay-id')) {
								if($(obj).attr('lay-id') != "index") {
									element.tabDelete('layui-tab', $(obj).attr('lay-id'));
								}
							}
							element.tabChange('layui-tab', $(t).attr('lay-id'));
						});
						index.Naviscroll();
					},
					'deleteall': function(t) {
						$.each($('#layui-tabs li'), function(idx, obj) {
							if($(obj).attr('lay-id') != "index") {
								element.tabDelete('layui-tab', $(obj).attr('lay-id'));
							}
							element.tabChange('layui-tab', "index");
						});
						index.Naviscroll();
					},
					'reload': function(t) {
						element.tabChange('layui-tab', $(t).attr('lay-id'));
						var iframe = $(".layui-tab-content .layui-show iframe");
						var src = iframe.data("src");
						iframe.attr("src", src + "?r=" + Math.random());
					}
				}
			});
		},
		showUserInfo: function() {
			common.ajaxForm(common.IP + '/api/blog-oauth2/currentUser', {}, function(result, status, xhr) {
				var data = result;
				$('#nickname').html(data.nickname)
				sessionStorage.setItem("username", data.username)
				sessionStorage.setItem("nickname", data.nickname)
				$('#headimgurl').attr('src', data.headimgurl)
				index.showCurrentMenus(result);
			}, null, false);
		},
		showCurrentMenus: function(result) {
			var menus = common.toTree(common.bubbleSort(result.menus));
			var menuHtml = "";
			$.each(menus, function(idx, obj) {
				var p = '<li class="layui-nav-item">' +
					'<a href="javascript:;" lay-tips="文章">' +
					'<i class="' + obj.ico + '"></i>' +
					'<cite>' + obj.name + '</cite>' +
					'<span class="layui-icon layui-icon-right"></span></a>' +
					'<dl class="layui-nav-child">';
				$.each(obj.children, function(idx1, child) {
					p += "<dd>" +
						"<a data-url='" + child.href + "' data-title='" + child.name + "'>" + child.name + "</a>" +
						"</dd>";
				});
				p += "</dl></li>"
				menuHtml += p;
			});
			$('.layui-nav-myitem').html(menuHtml);
			element.render('nav');
		},
		loginOut: function() {
			var access_token = localStorage.getItem("access_token");
			common.ajaxForm('/api/blog-oauth2/oauth2/loginout?access_token=' + access_token, {}, function() {
				localStorage.clear();
				sessionStorage.clear();
				window.parent.location.href = common.IP + '/admin/login.html';
			}, null, false);
		}
	};
	$(function() {
		index.init();
	});
});