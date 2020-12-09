/*
Navicat MySQL Data Transfer

Source Server         : DESKTOP-V3E28FC
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-12-09 11:05:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `roleid` int DEFAULT NULL,
  `permissionid` int DEFAULT NULL,
  KEY `roleid` (`roleid`),
  KEY `permissionid` (`permissionid`),
  CONSTRAINT `s_role_permission_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `s_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_role_permission_ibfk_2` FOREIGN KEY (`permissionid`) REFERENCES `s_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('1', '1');
INSERT INTO `s_role_permission` VALUES ('1', '2');
INSERT INTO `s_role_permission` VALUES ('1', '3');
INSERT INTO `s_role_permission` VALUES ('1', '4');
INSERT INTO `s_role_permission` VALUES ('2', '5');
INSERT INTO `s_role_permission` VALUES ('2', '6');
INSERT INTO `s_role_permission` VALUES ('2', '7');
INSERT INTO `s_role_permission` VALUES ('2', '8');
INSERT INTO `s_role_permission` VALUES ('3', '9');
INSERT INTO `s_role_permission` VALUES ('1', '9');
INSERT INTO `s_role_permission` VALUES ('2', '9');
INSERT INTO `s_role_permission` VALUES ('1', '5');
INSERT INTO `s_role_permission` VALUES ('1', '6');
INSERT INTO `s_role_permission` VALUES ('1', '7');
INSERT INTO `s_role_permission` VALUES ('1', '8');
