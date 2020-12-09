/*
Navicat MySQL Data Transfer

Source Server         : DESKTOP-V3E28FC
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-12-09 11:05:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'xiaopan', '456789');
INSERT INTO `s_user` VALUES ('2', 'xiaohong', '123');
INSERT INTO `s_user` VALUES ('3', 'xiaozi', '258');
INSERT INTO `s_user` VALUES ('4', 'xiaom', '369');
