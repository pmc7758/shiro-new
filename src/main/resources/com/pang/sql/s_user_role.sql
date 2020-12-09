/*
Navicat MySQL Data Transfer

Source Server         : DESKTOP-V3E28FC
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-12-09 11:06:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `userid` int NOT NULL,
  `roleid` int NOT NULL,
  KEY `userid` (`userid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `s_user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `s_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `s_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1');
INSERT INTO `s_user_role` VALUES ('2', '2');
INSERT INTO `s_user_role` VALUES ('3', '3');
INSERT INTO `s_user_role` VALUES ('4', '3');
