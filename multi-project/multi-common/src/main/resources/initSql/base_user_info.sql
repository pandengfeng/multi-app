/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : local

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-19 17:13:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `base_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `base_user_info`;
CREATE TABLE `base_user_info` (
  `user_id` integer NOT NULL COMMENT '基本用户主键',
  `account` varchar(128) NOT NULL COMMENT '基本用户帐号',
  `user_name` varchar(128) NOT NULL COMMENT '基本用户用户名',
  `password` varchar(128) NOT NULL COMMENT '基本用户密码',
  `disable` varchar(128) DEFAULT NULL COMMENT '禁用字段 1禁用  0可用',
  `operand` varchar(128) DEFAULT NULL COMMENT '操作次数 对记录进行操作时 +1',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `last_login_time` date DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`,`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础用户表';
-- ----------------------------
-- Records of base_user_info
-- ----------------------------
alter table base_user_info modify user_id integer auto_increment;