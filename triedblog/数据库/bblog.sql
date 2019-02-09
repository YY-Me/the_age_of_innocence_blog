/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-02-09 21:28:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` varchar(20) NOT NULL DEFAULT '' COMMENT '文章id',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `summary` varchar(255) NOT NULL COMMENT '文章摘要（简介）',
  `summaryImg` varchar(255) DEFAULT NULL COMMENT '摘要图片，封面',
  `content` longtext NOT NULL COMMENT '文章内容',
  `publisher` varchar(255) NOT NULL COMMENT '发布人id',
  `status` varchar(1) NOT NULL COMMENT '状态，具体到字典数据库看',
  `isTop` varchar(1) NOT NULL DEFAULT '0' COMMENT '1:顶置，0:默认不顶置',
  `isVisible` varchar(1) NOT NULL DEFAULT '0' COMMENT '1:显示，0:默认不显示，需要手动设置显示',
  `pv` bigint(255) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- ----------------------------
-- Table structure for blog_article_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_classify`;
CREATE TABLE `blog_article_classify` (
  `articleid` varchar(255) NOT NULL COMMENT '文章id',
  `classifyid` int(11) NOT NULL COMMENT '分类id，也就是字典库里面的id',
  PRIMARY KEY (`articleid`,`classifyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章-分类表';

-- ----------------------------
-- Table structure for blog_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_comment`;
CREATE TABLE `blog_article_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `article_id` varchar(20) NOT NULL COMMENT '文章id',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '0:默认为文章评论',
  `from_id` varchar(255) NOT NULL COMMENT '回复用户id',
  `from_name` varchar(255) NOT NULL,
  `from_head_img` varchar(255) DEFAULT NULL,
  `to_id` varchar(255) DEFAULT NULL COMMENT '目标用户id(为空表示一级评论)',
  `to_name` varchar(255) DEFAULT NULL,
  `content` text NOT NULL COMMENT '评论内容',
  `like_num` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `area_ip` varchar(255) NOT NULL COMMENT 'ip地址',
  `area` varchar(255) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_article_id` (`article_id`),
  CONSTRAINT `fk_comment_article_id` FOREIGN KEY (`article_id`) REFERENCES `blog_article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='文章评论表';

-- ----------------------------
-- Table structure for blog_article_lable
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_lable`;
CREATE TABLE `blog_article_lable` (
  `articleid` varchar(255) NOT NULL COMMENT '文字id',
  `lableid` int(11) NOT NULL COMMENT '标签id，也就是字典里面的',
  PRIMARY KEY (`articleid`,`lableid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章-标签表';

-- ----------------------------
-- Table structure for blog_label_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_label_classify`;
CREATE TABLE `blog_label_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '分类、标签名称',
  `type` varchar(1) NOT NULL COMMENT '0：标签，1：分类',
  `color` varchar(10) DEFAULT '#3385FF' COMMENT '背景',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COMMENT='标签、分类表';

-- ----------------------------
-- Table structure for blog_leave_msg
-- ----------------------------
DROP TABLE IF EXISTS `blog_leave_msg`;
CREATE TABLE `blog_leave_msg` (
  `leave_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '0:默认为一级留言',
  `from_id` varchar(255) NOT NULL COMMENT '回复用户id',
  `from_name` varchar(255) NOT NULL COMMENT '留言者昵称',
  `from_head_img` varchar(255) DEFAULT NULL COMMENT '留言者头像',
  `to_id` varchar(255) DEFAULT NULL COMMENT '目标用户id(为空表示一级评论)',
  `to_name` varchar(255) DEFAULT NULL COMMENT '目标用户昵称',
  `content` text NOT NULL COMMENT '评论内容',
  `like_num` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `area_ip` varchar(255) NOT NULL COMMENT 'ip地址',
  `area` varchar(255) NOT NULL DEFAULT '' COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '留言时间',
  PRIMARY KEY (`leave_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='博客留言表';

-- ----------------------------
-- Table structure for blog_qq_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_qq_user`;
CREATE TABLE `blog_qq_user` (
  `openid` varchar(255) NOT NULL COMMENT 'qq用户唯一标识',
  `nickname` varchar(255) NOT NULL COMMENT '用户在QQ空间的昵称。',
  `area` varchar(255) DEFAULT NULL COMMENT '地区',
  `figureurl_qq_1` varchar(255) NOT NULL COMMENT '大小为40×40像素的QQ头像URL。',
  `gender` varchar(255) NOT NULL COMMENT '性别',
  `status` varchar(2) NOT NULL COMMENT '0:禁用，1:正常',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='QQ用户基本信息表';

-- ----------------------------
-- Table structure for cust_info
-- ----------------------------
DROP TABLE IF EXISTS `cust_info`;
CREATE TABLE `cust_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `cust_no` varchar(20) DEFAULT NULL COMMENT '用户编号',
  `cust_type` char(1) DEFAULT 'E' COMMENT 'P：个人，E：企业',
  `credit_no` varchar(32) DEFAULT NULL COMMENT '企业：统一社会信用编码/个人：身份证号码',
  `cust_nm` varchar(200) DEFAULT NULL COMMENT '名称',
  `available` char(1) DEFAULT NULL COMMENT '是否可用',
  `monitorid` varchar(200) DEFAULT NULL COMMENT '监控ID（由汇法生成）',
  `import_date` datetime DEFAULT NULL COMMENT '导入日期',
  PRIMARY KEY (`id`),
  KEY `index_creteno` (`credit_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18639 DEFAULT CHARSET=utf8 COMMENT='客户表';

-- ----------------------------
-- Table structure for day_area_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_area_times_stat`;
CREATE TABLE `day_area_times_stat` (
  `day` varchar(8) NOT NULL,
  `area` varchar(10) NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`,`area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for day_browser_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_browser_times_stat`;
CREATE TABLE `day_browser_times_stat` (
  `day` varchar(8) NOT NULL,
  `browser` varchar(20) NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`,`browser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for day_ip_area_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_ip_area_times_stat`;
CREATE TABLE `day_ip_area_times_stat` (
  `day` varchar(8) NOT NULL,
  `ip` varchar(15) NOT NULL,
  `area` varchar(20) NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`,`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for day_os_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_os_times_stat`;
CREATE TABLE `day_os_times_stat` (
  `day` varchar(8) NOT NULL,
  `os` varchar(20) NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`,`os`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `k` varchar(255) NOT NULL,
  `v` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='后台字典表';

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `flag` int(1) NOT NULL,
  `createTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remart` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后台日志表';

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentid` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `ico` varchar(30) DEFAULT NULL COMMENT '父类菜单的ico图标',
  `href` varchar(255) DEFAULT NULL COMMENT '需要跳转的url',
  `type` int(1) NOT NULL COMMENT '菜单类型，1：菜单是个url，2:是一个按钮',
  `permission` varchar(50) DEFAULT NULL COMMENT '菜单权限的唯一标识',
  `sort` int(11) NOT NULL COMMENT '菜单的排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COMMENT='权限（菜单）表';

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='后台用户角色表';

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu` (
  `roleid` bigint(20) NOT NULL,
  `menuid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户（后台）角色-权限表';

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `uid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sex` int(1) DEFAULT NULL,
  `headimgurl` varchar(512) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统（后台）用户表';

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `userid` varchar(255) DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  KEY `fk_system_user_user_role_by_userid` (`userid`),
  KEY `fk_system_role_user_role_by_roleid` (`roleid`),
  CONSTRAINT `fk_system_role_user_role_by_roleid` FOREIGN KEY (`roleid`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_system_user_user_role_by_userid` FOREIGN KEY (`userid`) REFERENCES `system_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Procedure structure for myproc
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `myproc`()
BEGIN
	DECLARE num INT;
	set num=1;
	WHILE num<10000000 DO
	INSERT INTO test(username,gender,password) VALUE(num,'保密',PASSWORD(num));
	set num=num+1;
	end WHILE;
END
;;
DELIMITER ;
