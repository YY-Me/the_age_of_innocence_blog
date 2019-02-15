/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 05/02/2019 22:04:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章摘要（简介）',
  `summaryImg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要图片，封面',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布人id',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态，具体到字典数据库看',
  `isTop` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '1:顶置，0:默认不顶置',
  `isVisible` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '1:显示，0:默认不显示，需要手动设置显示',
  `pv` bigint(255) NOT NULL DEFAULT 0 COMMENT '浏览量',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_article_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_classify`;
CREATE TABLE `blog_article_classify`  (
  `articleid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章id',
  `classifyid` int(11) NOT NULL COMMENT '分类id，也就是字典库里面的id',
  PRIMARY KEY (`articleid`, `classifyid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章-分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_comment`;
CREATE TABLE `blog_article_comment`  (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `article_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章id',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '0:默认为文章评论',
  `from_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回复用户id',
  `from_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `from_head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `to_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标用户id(为空表示一级评论)',
  `to_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `like_num` bigint(20) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `area_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ip地址',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `create_time` datetime(0) NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `fk_comment_article_id`(`article_id`) USING BTREE,
  CONSTRAINT `fk_comment_article_id` FOREIGN KEY (`article_id`) REFERENCES `blog_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_article_lable
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_lable`;
CREATE TABLE `blog_article_lable`  (
  `articleid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文字id',
  `lableid` int(11) NOT NULL COMMENT '标签id，也就是字典里面的',
  PRIMARY KEY (`articleid`, `lableid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章-标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_label_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_label_classify`;
CREATE TABLE `blog_label_classify`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类、标签名称',
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '0：标签，1：分类',
  `color` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#3385FF' COMMENT '背景',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签、分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_leave_msg
-- ----------------------------
DROP TABLE IF EXISTS `blog_leave_msg`;
CREATE TABLE `blog_leave_msg`  (
  `leave_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '0:默认为一级留言',
  `from_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回复用户id',
  `from_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '留言者昵称',
  `from_head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '留言者头像',
  `to_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标用户id(为空表示一级评论)',
  `to_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标用户昵称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `like_num` bigint(20) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `area_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ip地址',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '类型',
  `create_time` datetime(0) NOT NULL COMMENT '留言时间',
  PRIMARY KEY (`leave_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客留言表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_qq_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_qq_user`;
CREATE TABLE `blog_qq_user`  (
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qq用户唯一标识',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户在QQ空间的昵称。',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区',
  `figureurl_qq_1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '大小为40×40像素的QQ头像URL。',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '0:禁用，1:正常',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近更新时间',
  PRIMARY KEY (`openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'QQ用户基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cust_info
-- ----------------------------
DROP TABLE IF EXISTS `cust_info`;
CREATE TABLE `cust_info`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `cust_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `cust_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'E' COMMENT 'P：个人，E：企业',
  `credit_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业：统一社会信用编码/个人：身份证号码',
  `cust_nm` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `available` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否可用',
  `monitorid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监控ID（由汇法生成）',
  `import_date` datetime(0) NULL DEFAULT NULL COMMENT '导入日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_creteno`(`credit_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18639 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for day_area_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_area_times_stat`;
CREATE TABLE `day_area_times_stat`  (
  `day` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`, `area`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of day_area_times_stat
-- ----------------------------
INSERT INTO `day_area_times_stat` VALUES ('20180914', '重庆市', 1);
-- ----------------------------
-- Table structure for day_browser_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_browser_times_stat`;
CREATE TABLE `day_browser_times_stat`  (
  `day` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `browser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`, `browser`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of day_browser_times_stat
-- ----------------------------
INSERT INTO `day_browser_times_stat` VALUES ('20180914', 'Chrome', 1);

-- ----------------------------
-- Table structure for day_ip_area_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_ip_area_times_stat`;
CREATE TABLE `day_ip_area_times_stat`  (
  `day` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`, `ip`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of day_ip_area_times_stat
-- ----------------------------
INSERT INTO `day_ip_area_times_stat` VALUES ('20180914', '123.147.250.68', '重庆市', 2);

-- ----------------------------
-- Table structure for day_os_times_stat
-- ----------------------------
DROP TABLE IF EXISTS `day_os_times_stat`;
CREATE TABLE `day_os_times_stat`  (
  `day` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `os` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `times` bigint(20) NOT NULL,
  PRIMARY KEY (`day`, `os`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of day_os_times_stat
-- ----------------------------
INSERT INTO `day_os_times_stat` VALUES ('20180914', 'Windows 10', 1);

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('blog', NULL, '$2a$10$e.7mNGBYtt.2QuqjICDx8e3jvHv8XGZfYrwOyfgD/eQmBicmOsyfm', 'app', 'authorization_code,password,refresh_token', 'http://www.baidu.com', NULL, 72000, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('system', NULL, '$2a$10$e.7mNGBYtt.2QuqjICDx8e3jvHv8XGZfYrwOyfgD/eQmBicmOsyfm', 'app', 'authorization_code,password,refresh_token', 'http://www.baidu.com', NULL, 72000, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `k` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `v` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dict
-- ----------------------------
INSERT INTO `system_dict` VALUES (1, 'userStatus', '1', '正常', '2018-05-06 17:06:21', '2018-05-06 22:52:47');
INSERT INTO `system_dict` VALUES (2, 'userStatus', '0', '已作废', '2018-05-06 17:06:43', '2018-05-06 22:53:00');
INSERT INTO `system_dict` VALUES (3, 'sex', '0', '女', '2018-05-06 22:08:08', '2018-05-06 22:08:10');
INSERT INTO `system_dict` VALUES (4, 'sex', '1', '男', '2018-05-06 22:08:25', '2018-05-06 22:08:27');
INSERT INTO `system_dict` VALUES (5, 'userStatus', '2', '锁定', '2018-05-06 22:53:49', '2018-05-06 22:53:51');

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `flag` int(1) NOT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `remart` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentid` bigint(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `ico` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父类菜单的ico图标',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '需要跳转的url',
  `type` int(1) NOT NULL COMMENT '菜单类型，1：菜单是个url，2:是一个按钮',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单权限的唯一标识',
  `sort` int(11) NOT NULL COMMENT '菜单的排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限（菜单）表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (1, 0, '用户中心', 'layui-icon layui-icon-user', '', 1, '', 20);
INSERT INTO `system_menu` VALUES (2, 1, '用户管理', NULL, 'users/web/qquser_list.html', 1, '', 21);
INSERT INTO `system_menu` VALUES (3, 9, '添加管理员', NULL, '', 2, 'sys:admin:add', 23);
INSERT INTO `system_menu` VALUES (4, 0, '文章', 'layui-icon layui-icon-read', '', 1, '', 10);
INSERT INTO `system_menu` VALUES (5, 4, '发布文章', NULL, 'article/article_add.html', 1, '', 11);
INSERT INTO `system_menu` VALUES (6, 4, '文章管理', NULL, 'article/article_list.html', 1, '', 12);
INSERT INTO `system_menu` VALUES (9, 1, '管理员列表', NULL, 'users/admin/admin_list.html', 1, '', 22);
INSERT INTO `system_menu` VALUES (10, 9, '单、批量删除管理员', NULL, '', 2, 'sys:admin:del', 24);
INSERT INTO `system_menu` VALUES (11, 9, '查询', NULL, '', 2, 'sys:admin:query', 25);
INSERT INTO `system_menu` VALUES (12, 9, '修改信息', NULL, '', 2, 'sys:admin:update', 26);
INSERT INTO `system_menu` VALUES (13, 0, '权限中心', 'layui-icon layui-icon-auz', '', 1, '', 30);
INSERT INTO `system_menu` VALUES (14, 13, '菜单管理', NULL, 'permission/menu/menu_list.html', 1, '', 100);
INSERT INTO `system_menu` VALUES (15, 13, '角色管理', NULL, 'permission/role/role_list.html', 1, '', 100);
INSERT INTO `system_menu` VALUES (16, 15, '添加角色', NULL, '', 2, 'sys:role:add', 100);
INSERT INTO `system_menu` VALUES (17, 15, '删除角色', NULL, '', 2, 'sys:role:del', 100);
INSERT INTO `system_menu` VALUES (18, 15, '修改角色', NULL, '', 2, 'sys:role:update', 100);
INSERT INTO `system_menu` VALUES (19, 15, '查询角色信息', NULL, '', 2, 'sys:role:query', 100);
INSERT INTO `system_menu` VALUES (20, 14, '添加菜单', NULL, '', 2, 'sys:menu:add', 100);
INSERT INTO `system_menu` VALUES (21, 14, '删除菜单', NULL, '', 2, 'sys:menu:del', 100);
INSERT INTO `system_menu` VALUES (22, 14, '修改菜单信息', NULL, '', 2, 'sys:menu:update', 100);
INSERT INTO `system_menu` VALUES (23, 14, '查询菜单信息', NULL, '', 2, 'sys:menu:query', 100);
INSERT INTO `system_menu` VALUES (24, 0, '系统管理', 'layui-icon layui-icon-set', '', 1, '', 50);
INSERT INTO `system_menu` VALUES (25, 24, '字典管理', NULL, 'system/dictionary/dictionary_list.html', 1, '', 52);
INSERT INTO `system_menu` VALUES (26, 25, '添加字典', NULL, '', 2, 'sys:dictionary:add', 100);
INSERT INTO `system_menu` VALUES (27, 25, '删除字典', NULL, '', 2, 'sys:dictionary:del', 100);
INSERT INTO `system_menu` VALUES (28, 25, '修改字典', NULL, '', 2, 'sys:dictionary:update', 100);
INSERT INTO `system_menu` VALUES (29, 25, '查询字典信息', NULL, '', 2, 'sys:dictionary:query', 100);
INSERT INTO `system_menu` VALUES (31, 24, '文件管理', NULL, 'system/file/file_list.html', 1, '', 51);
INSERT INTO `system_menu` VALUES (32, 31, '文件上传', NULL, '', 2, 'file:upload', 100);
INSERT INTO `system_menu` VALUES (33, 31, '删除文件', NULL, '', 2, 'file:del', 100);
INSERT INTO `system_menu` VALUES (34, 31, '查看文件', NULL, '', 2, 'file:query', 100);
INSERT INTO `system_menu` VALUES (35, 4, '分类、标签', NULL, 'article/article_classify_label.html', 1, '', 13);
INSERT INTO `system_menu` VALUES (36, 24, '网站设置', NULL, 'system/web_setting.html', 1, '', 53);
INSERT INTO `system_menu` VALUES (37, 24, '数据源', NULL, '', 1, '', 54);
INSERT INTO `system_menu` VALUES (38, 6, '添加文章', NULL, '', 2, 'sys:article:add', 100);
INSERT INTO `system_menu` VALUES (39, 6, '删除文章', NULL, '', 2, 'sys:article:del', 100);
INSERT INTO `system_menu` VALUES (40, 6, '修改文章', NULL, '', 2, 'sys:article:update', 100);
INSERT INTO `system_menu` VALUES (41, 6, '查询浏览', NULL, '', 2, 'sys:article:query', 100);
INSERT INTO `system_menu` VALUES (42, 35, '添加', NULL, '', 2, 'sys:classifyLabel:addUp', 100);
INSERT INTO `system_menu` VALUES (43, 35, '删除', NULL, '', 2, 'sys:classifyLabel:del', 100);
INSERT INTO `system_menu` VALUES (44, 35, '修改信息', NULL, '', 2, 'sys:classifyLabel:addUp', 100);
INSERT INTO `system_menu` VALUES (45, 35, '查询浏览', NULL, '', 2, 'sys:classifyLabel:query', 100);
INSERT INTO `system_menu` VALUES (46, 0, '我的设置', 'layui-icon layui-icon-friends', '', 1, '', 40);
INSERT INTO `system_menu` VALUES (47, 46, '密码修改', NULL, 'users/update_my_password.html', 1, '', 41);
INSERT INTO `system_menu` VALUES (48, 46, '个人信息修改', NULL, 'users/update_my_base_info.html?v=1.0', 1, '', 42);
INSERT INTO `system_menu` VALUES (49, 2, '删除用户', NULL, '', 2, 'web:user:del', 100);
INSERT INTO `system_menu` VALUES (50, 2, '修改状态', NULL, '', 2, 'web:user:upstatus', 100);
INSERT INTO `system_menu` VALUES (51, 2, '查询信息', NULL, '', 2, 'web:user:query', 100);
INSERT INTO `system_menu` VALUES (52, 36, '查询信息', NULL, '', 2, 'sys:website:query', 100);
INSERT INTO `system_menu` VALUES (53, 36, '更新网站信息', NULL, '', 2, 'sys:website:update', 100);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NOT NULL,
  `updatetime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, 'ADMIN', '一人之下，万人之上，一不小心就被自己玩死(✿◕‿◕✿)', '2018-05-04 17:43:59', '2018-12-23 15:10:28');
INSERT INTO `system_role` VALUES (2, 'GUEST', '游客', '2018-05-06 22:54:40', '2018-09-21 18:08:47');
INSERT INTO `system_role` VALUES (3, 'test1', 'test1', '2018-10-29 11:54:07', '2018-10-29 11:56:21');

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu`  (
  `roleid` bigint(20) NOT NULL,
  `menuid` bigint(20) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户（后台）角色-权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES (2, 4);
INSERT INTO `system_role_menu` VALUES (2, 5);
INSERT INTO `system_role_menu` VALUES (2, 6);
INSERT INTO `system_role_menu` VALUES (2, 41);
INSERT INTO `system_role_menu` VALUES (2, 35);
INSERT INTO `system_role_menu` VALUES (2, 45);
INSERT INTO `system_role_menu` VALUES (2, 1);
INSERT INTO `system_role_menu` VALUES (2, 2);
INSERT INTO `system_role_menu` VALUES (2, 51);
INSERT INTO `system_role_menu` VALUES (2, 9);
INSERT INTO `system_role_menu` VALUES (2, 11);
INSERT INTO `system_role_menu` VALUES (2, 13);
INSERT INTO `system_role_menu` VALUES (2, 14);
INSERT INTO `system_role_menu` VALUES (2, 23);
INSERT INTO `system_role_menu` VALUES (2, 15);
INSERT INTO `system_role_menu` VALUES (2, 19);
INSERT INTO `system_role_menu` VALUES (2, 46);
INSERT INTO `system_role_menu` VALUES (2, 47);
INSERT INTO `system_role_menu` VALUES (2, 48);
INSERT INTO `system_role_menu` VALUES (2, 24);
INSERT INTO `system_role_menu` VALUES (2, 31);
INSERT INTO `system_role_menu` VALUES (2, 34);
INSERT INTO `system_role_menu` VALUES (2, 25);
INSERT INTO `system_role_menu` VALUES (2, 29);
INSERT INTO `system_role_menu` VALUES (2, 36);
INSERT INTO `system_role_menu` VALUES (2, 52);
INSERT INTO `system_role_menu` VALUES (1, 4);
INSERT INTO `system_role_menu` VALUES (1, 5);
INSERT INTO `system_role_menu` VALUES (1, 6);
INSERT INTO `system_role_menu` VALUES (1, 38);
INSERT INTO `system_role_menu` VALUES (1, 39);
INSERT INTO `system_role_menu` VALUES (1, 40);
INSERT INTO `system_role_menu` VALUES (1, 41);
INSERT INTO `system_role_menu` VALUES (1, 35);
INSERT INTO `system_role_menu` VALUES (1, 42);
INSERT INTO `system_role_menu` VALUES (1, 43);
INSERT INTO `system_role_menu` VALUES (1, 44);
INSERT INTO `system_role_menu` VALUES (1, 45);
INSERT INTO `system_role_menu` VALUES (1, 1);
INSERT INTO `system_role_menu` VALUES (1, 2);
INSERT INTO `system_role_menu` VALUES (1, 49);
INSERT INTO `system_role_menu` VALUES (1, 50);
INSERT INTO `system_role_menu` VALUES (1, 51);
INSERT INTO `system_role_menu` VALUES (1, 9);
INSERT INTO `system_role_menu` VALUES (1, 3);
INSERT INTO `system_role_menu` VALUES (1, 10);
INSERT INTO `system_role_menu` VALUES (1, 11);
INSERT INTO `system_role_menu` VALUES (1, 12);
INSERT INTO `system_role_menu` VALUES (1, 13);
INSERT INTO `system_role_menu` VALUES (1, 14);
INSERT INTO `system_role_menu` VALUES (1, 20);
INSERT INTO `system_role_menu` VALUES (1, 21);
INSERT INTO `system_role_menu` VALUES (1, 22);
INSERT INTO `system_role_menu` VALUES (1, 23);
INSERT INTO `system_role_menu` VALUES (1, 15);
INSERT INTO `system_role_menu` VALUES (1, 16);
INSERT INTO `system_role_menu` VALUES (1, 17);
INSERT INTO `system_role_menu` VALUES (1, 18);
INSERT INTO `system_role_menu` VALUES (1, 19);
INSERT INTO `system_role_menu` VALUES (1, 46);
INSERT INTO `system_role_menu` VALUES (1, 47);
INSERT INTO `system_role_menu` VALUES (1, 48);
INSERT INTO `system_role_menu` VALUES (1, 24);
INSERT INTO `system_role_menu` VALUES (1, 31);
INSERT INTO `system_role_menu` VALUES (1, 32);
INSERT INTO `system_role_menu` VALUES (1, 33);
INSERT INTO `system_role_menu` VALUES (1, 34);
INSERT INTO `system_role_menu` VALUES (1, 25);
INSERT INTO `system_role_menu` VALUES (1, 26);
INSERT INTO `system_role_menu` VALUES (1, 27);
INSERT INTO `system_role_menu` VALUES (1, 28);
INSERT INTO `system_role_menu` VALUES (1, 29);
INSERT INTO `system_role_menu` VALUES (1, 36);
INSERT INTO `system_role_menu` VALUES (1, 52);
INSERT INTO `system_role_menu` VALUES (1, 53);
INSERT INTO `system_role_menu` VALUES (1, 37);

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `sex` int(1) NULL DEFAULT NULL,
  `headimgurl` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT 1,
  `createtime` datetime(0) NOT NULL,
  `updatetime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统（后台）用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('a0fef307-f60a-4ce5-abb0-040c0db450af', 'admin', '$2a$10$e.7mNGBYtt.2QuqjICDx8e3jvHv8XGZfYrwOyfgD/eQmBicmOsyfm', '纯真年代', '18896035059', '1396513066@qq.com', '2019-02-15 22:04:09', 1, 'https://triedblog.oss-cn-beijing.aliyuncs.com/my/login.jpg', 1, '2018-05-04 17:16:59', '2019-02-15 22:04:09');
INSERT INTO `system_user` VALUES ('bb82aec9-72a7-4e0a-8564-83a941e3fac2', 'guest', '$2a$10$e.7mNGBYtt.2QuqjICDx8e3jvHv8XGZfYrwOyfgD/eQmBicmOsyfm', '草丛娇喘', '', '', '2019-02-15 22:04:12', 0, 'https://triedblog.oss-cn-beijing.aliyuncs.com/headimgs/headimg5.jpeg', 1, '2018-09-21 18:15:23', '2019-02-15 22:04:12');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role`  (
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `roleid` bigint(20) NULL DEFAULT NULL,
  INDEX `fk_system_user_user_role_by_userid`(`userid`) USING BTREE,
  INDEX `fk_system_role_user_role_by_roleid`(`roleid`) USING BTREE,
  CONSTRAINT `fk_system_role_user_role_by_roleid` FOREIGN KEY (`roleid`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_system_user_user_role_by_userid` FOREIGN KEY (`userid`) REFERENCES `system_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('a0fef307-f60a-4ce5-abb0-040c0db450af', 2);
INSERT INTO `system_user_role` VALUES ('a0fef307-f60a-4ce5-abb0-040c0db450af', 1);
INSERT INTO `system_user_role` VALUES ('bb82aec9-72a7-4e0a-8564-83a941e3fac2', 2);

-- ----------------------------
-- Procedure structure for myproc
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc`;
delimiter ;;
CREATE PROCEDURE `myproc`()
BEGIN
	DECLARE num INT;
	set num=1;
	WHILE num<10000000 DO
	INSERT INTO test(username,gender,password) VALUE(num,'保密',PASSWORD(num));
	set num=num+1;
	end WHILE;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
