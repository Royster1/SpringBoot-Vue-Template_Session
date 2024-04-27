/*
 Navicat Premium Data Transfer

 Source Server         : JavaEE
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : baima_jwt_forum

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 27/04/2024 15:16:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_account
-- ----------------------------
DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unique_name`(`username`) USING BTREE,
  INDEX `unique_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of db_account
-- ----------------------------
INSERT INTO `db_account` VALUES (7, 'root', '$2a$10$JFiEE7NtcoZTaFr8ICPmIew.T2.SzxWmvUMkbF8QwNl0iIWneNQO2', '');
INSERT INTO `db_account` VALUES (10, 'man', '$2a$10$UqkG7bm4QCZH4kVjDCmdKerG8HDE20q3xN7gJZdw5FF2973M3jtXa', '2318448707@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
