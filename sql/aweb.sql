/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : aweb

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-08-02 17:33:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aweb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `aweb_dictionary`;
CREATE TABLE `aweb_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `data` varchar(256) NOT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `sorting` int(10) DEFAULT '1',
  `parent_id` bigint(20) DEFAULT '0',
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for aweb_menu
-- ----------------------------
DROP TABLE IF EXISTS `aweb_menu`;
CREATE TABLE `aweb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `lvl` int(2) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  `sorting` int(10) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_menu
-- ----------------------------
INSERT INTO `aweb_menu` VALUES ('1', '系统管理', 'menu', null, '0', '0/', '1', '1', '10');
INSERT INTO `aweb_menu` VALUES ('2', '用户管理', 'page', '/user', '1', '0/1/', '2', '1', '10');
INSERT INTO `aweb_menu` VALUES ('3', '资源管理', 'page', '/resource', '1', '0/1/', '2', '1', '8');
INSERT INTO `aweb_menu` VALUES ('4', '菜单管理', 'page', '/menu', '1', '0/1/', '2', '1', '2');
INSERT INTO `aweb_menu` VALUES ('5', '权限管理', 'page', '/permission', '1', '0/1/', '2', '1', '2');
INSERT INTO `aweb_menu` VALUES ('6', '角色管理', 'page', '/role', '1', '0/1/', '2', '1', '9');
INSERT INTO `aweb_menu` VALUES ('2144', '缓存管理', 'page', '/redis', '1', '0/1/', '2', '1', '1');
INSERT INTO `aweb_menu` VALUES ('2145', '组织管理', 'page', '/organization', '1', '0/1/', '2', '1', '1');
INSERT INTO `aweb_menu` VALUES ('2146', '数据字典', 'page', '/dictionary', '1', '0/1/', '2', '1', '1');

-- ----------------------------
-- Table structure for aweb_organization
-- ----------------------------
DROP TABLE IF EXISTS `aweb_organization`;
CREATE TABLE `aweb_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  `data` varchar(255) DEFAULT NULL,
  `sorting` int(10) DEFAULT '1',
  `remark` varchar(255) DEFAULT NULL,
  `lvl` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_organization
-- ----------------------------
INSERT INTO `aweb_organization` VALUES ('1', '中国', null, '', '1', '1', '1', null, '1');
INSERT INTO `aweb_organization` VALUES ('2', '北京', '1', '1/', '1', '2', '999', null, '2');
INSERT INTO `aweb_organization` VALUES ('3', '上海', '1', '1/', '1', '3', '1', null, '2');
INSERT INTO `aweb_organization` VALUES ('4', '浙江', '1', '1/', '1', '4', '1', null, '2');
INSERT INTO `aweb_organization` VALUES ('5', '杭州', '4', '1/4/', '1', '5', '2', null, '3');
INSERT INTO `aweb_organization` VALUES ('6', '宁波', '4', '1/4/', '1', '6', '1', null, '3');

-- ----------------------------
-- Table structure for aweb_permission
-- ----------------------------
DROP TABLE IF EXISTS `aweb_permission`;
CREATE TABLE `aweb_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_permission
-- ----------------------------
INSERT INTO `aweb_permission` VALUES ('1', '所有权限', null, null, null, '*', '1');
INSERT INTO `aweb_permission` VALUES ('2', '系统管理', null, '1', '1/', 'system:*', '1');
INSERT INTO `aweb_permission` VALUES ('3', '菜单管理', null, '2', '1/2/', 'system:menu:*', '1');
INSERT INTO `aweb_permission` VALUES ('4', '权限管理', null, '2', '1/2/', 'system:permission:*', '1');
INSERT INTO `aweb_permission` VALUES ('5', '资源管理', null, '2', '1/2/', 'system:resource:*', '1');
INSERT INTO `aweb_permission` VALUES ('6', '角色管理', null, '2', '1/2/', 'system:role:*', '1');
INSERT INTO `aweb_permission` VALUES ('7', '用户管理', null, '2', '1/2/', 'system:user:*', '1');
INSERT INTO `aweb_permission` VALUES ('8', '组织管理', null, '2', '1/2/', 'system:organization:*', '1');

-- ----------------------------
-- Table structure for aweb_resource
-- ----------------------------
DROP TABLE IF EXISTS `aweb_resource`;
CREATE TABLE `aweb_resource` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `alls` tinyint(1) DEFAULT '0' COMMENT '所有用户都有该资源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_resource
-- ----------------------------
INSERT INTO `aweb_resource` VALUES ('1', '系统管理', 'menu', '1', '0');
INSERT INTO `aweb_resource` VALUES ('2', '用户管理', 'menu', '2', '0');
INSERT INTO `aweb_resource` VALUES ('3', '资源管理', 'menu', '3', '0');
INSERT INTO `aweb_resource` VALUES ('4', '菜单管理', 'menu', '4', '0');
INSERT INTO `aweb_resource` VALUES ('5', '权限管理', 'menu', '5', '0');
INSERT INTO `aweb_resource` VALUES ('6', '角色管理', 'menu', '6', '0');
INSERT INTO `aweb_resource` VALUES ('15', '所有权限', 'permission', '1', '0');
INSERT INTO `aweb_resource` VALUES ('16', '系统管理', 'permission', '2', '0');
INSERT INTO `aweb_resource` VALUES ('17', '菜单管理', 'permission', '3', '0');
INSERT INTO `aweb_resource` VALUES ('18', '权限管理', 'permission', '4', '0');
INSERT INTO `aweb_resource` VALUES ('19', '资源管理', 'permission', '5', '0');
INSERT INTO `aweb_resource` VALUES ('20', '角色管理', 'permission', '6', '0');
INSERT INTO `aweb_resource` VALUES ('21', '用户管理', 'permission', '7', '0');
INSERT INTO `aweb_resource` VALUES ('2145', '缓存管理', 'menu', '2144', '0');
INSERT INTO `aweb_resource` VALUES ('2146', '组织管理', 'menu', '2145', '0');
INSERT INTO `aweb_resource` VALUES ('2147', '组织管理', 'permission', '8', '0');
INSERT INTO `aweb_resource` VALUES ('2148', '数据字典', 'menu', '2146', '0');

-- ----------------------------
-- Table structure for aweb_role
-- ----------------------------
DROP TABLE IF EXISTS `aweb_role`;
CREATE TABLE `aweb_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_role
-- ----------------------------
INSERT INTO `aweb_role` VALUES ('1', 'admin', '超级管理员', '1');
INSERT INTO `aweb_role` VALUES ('2', 'test', '测试管理员', '1');

-- ----------------------------
-- Table structure for aweb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `aweb_role_resource`;
CREATE TABLE `aweb_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_role_resource
-- ----------------------------
INSERT INTO `aweb_role_resource` VALUES ('1', '1', '15');
INSERT INTO `aweb_role_resource` VALUES ('4', '2', '3');
INSERT INTO `aweb_role_resource` VALUES ('5', '2', '16');
INSERT INTO `aweb_role_resource` VALUES ('6', '2', '1');
INSERT INTO `aweb_role_resource` VALUES ('10', '2', '6');
INSERT INTO `aweb_role_resource` VALUES ('11', '2', '2145');
INSERT INTO `aweb_role_resource` VALUES ('13', '2', '2');
INSERT INTO `aweb_role_resource` VALUES ('14', '2', '4');
INSERT INTO `aweb_role_resource` VALUES ('15', '2', '5');

-- ----------------------------
-- Table structure for aweb_user
-- ----------------------------
DROP TABLE IF EXISTS `aweb_user`;
CREATE TABLE `aweb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `salt` varchar(200) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=359 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_user
-- ----------------------------
INSERT INTO `aweb_user` VALUES ('357', 'admin', 'dfa4adbf1e431bd2ff8781c98f5923a2', 'de943503b2fcf5096dcab7f431661854', '0');
INSERT INTO `aweb_user` VALUES ('358', 'test', '83f2a9c3a6a39c5bd833c01fdc4e7e0b', '955a6b1168523048735c84845d3d10a4', '1');

-- ----------------------------
-- Table structure for aweb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `aweb_user_role`;
CREATE TABLE `aweb_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=362 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aweb_user_role
-- ----------------------------
INSERT INTO `aweb_user_role` VALUES ('358', '357', '1');
INSERT INTO `aweb_user_role` VALUES ('361', '358', '2');
