/*
Navicat MySQL Data Transfer

Source Server         : 172.16.0.42
Source Server Version : 50541
Source Host           : 172.16.0.42:3306
Source Database       : handtour

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2016-07-15 17:06:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for back_user
-- ----------------------------
DROP TABLE IF EXISTS `back_user`;
CREATE TABLE `back_user` (
  `mobile` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  PRIMARY KEY (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records to back_user
-- ----------------------------
INSERT INTO `back_user` VALUES ('13761156786', '蒋礼', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for weixin_user
-- ----------------------------
DROP TABLE IF EXISTS `weixin_user`;
CREATE TABLE `weixin_user` (
  `mobile` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  PRIMARY KEY (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records to weixin_user
-- ----------------------------
INSERT INTO `weixin_user` VALUES ('13761156786', '蒋礼', 'e10adc3949ba59abbe56e057f20f883e');
