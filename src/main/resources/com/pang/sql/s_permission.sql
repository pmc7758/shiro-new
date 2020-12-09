/*
Navicat MySQL Data Transfer

Source Server         : DESKTOP-V3E28FC
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-12-09 11:04:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', 'sys:x:add', '增加经理');
INSERT INTO `s_permission` VALUES ('2', 'sys:x:del', '删除经理');
INSERT INTO `s_permission` VALUES ('3', 'sys:x:list', '查询经理');
INSERT INTO `s_permission` VALUES ('4', 'sys:x:upd', '修改经理');
INSERT INTO `s_permission` VALUES ('5', 'sys:y:add', '增加员工');
INSERT INTO `s_permission` VALUES ('6', 'sys:y:del', '删除员工');
INSERT INTO `s_permission` VALUES ('7', 'sys:y:list', '查询员工');
INSERT INTO `s_permission` VALUES ('8', 'sys:y:upd', '修改员工');
INSERT INTO `s_permission` VALUES ('9', 'sys:o:list', '查询自己');
