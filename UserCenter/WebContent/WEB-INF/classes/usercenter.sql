/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : usercenter

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-04-02 14:18:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `uc_accountapp`
-- ----------------------------
DROP TABLE IF EXISTS `uc_accountapp`;
CREATE TABLE `uc_accountapp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` bigint(32) NOT NULL COMMENT '账号',
  `appkey` varchar(10) NOT NULL COMMENT 'appkey',
  `status` int(4) NOT NULL COMMENT '状态',
  `nickname` varchar(100) NOT NULL COMMENT '昵称',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `lastmodifytime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `uc_accountdeveloper`
-- ----------------------------
DROP TABLE IF EXISTS `uc_accountdeveloper`;
CREATE TABLE `uc_accountdeveloper` (
  `account` bigint(32) NOT NULL COMMENT '账号',
  `authgrade` int(4) NOT NULL COMMENT '权限等级',
  `devstatus` int(4) NOT NULL COMMENT '状态',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `lastmodifytime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_accountdeveloper
-- ----------------------------

-- ----------------------------
-- Table structure for `uc_accountinfo`
-- ----------------------------
DROP TABLE IF EXISTS `uc_accountinfo`;
CREATE TABLE `uc_accountinfo` (
  `account` bigint(32) NOT NULL COMMENT '账号',
  `accountname` varchar(200) NOT NULL COMMENT '账户',
  `nickname` varchar(100) NOT NULL COMMENT '昵称',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `pinpassword` varchar(32) NOT NULL COMMENT 'pin密码',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phoneauth` varchar(4) DEFAULT NULL COMMENT '手机验证',
  `emailauth` varchar(4) DEFAULT NULL COMMENT '邮箱验证',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `lastmodifytime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
