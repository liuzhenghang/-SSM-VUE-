/*
 Navicat Premium Data Transfer

 Source Server         : PHPStudy
 Source Server Type    : MySQL
 Source Server Version : 50529
 Source Host           : localhost:3306
 Source Schema         : words

 Target Server Type    : MySQL
 Target Server Version : 50529
 File Encoding         : 65001

 Date: 07/06/2020 18:17:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for _user
-- ----------------------------
DROP TABLE IF EXISTS `_user`;
CREATE TABLE `_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of _user
-- ----------------------------
INSERT INTO `_user` VALUES (1, 'test', 'test');
INSERT INTO `_user` VALUES (2, '11223', '134234');
INSERT INTO `_user` VALUES (4, 'qwer', 'lll');

-- ----------------------------
-- Table structure for _user_word
-- ----------------------------
DROP TABLE IF EXISTS `_user_word`;
CREATE TABLE `_user_word`  (
  `userid` int(11) NOT NULL,
  `wordid` int(11) NOT NULL,
  `collection` tinyint(3) UNSIGNED ZEROFILL NOT NULL,
  `history` tinyint(3) UNSIGNED ZEROFILL NOT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of _user_word
-- ----------------------------
INSERT INTO `_user_word` VALUES (2, 2, 000, 001);
INSERT INTO `_user_word` VALUES (1, 9, 000, 000);
INSERT INTO `_user_word` VALUES (1, 1, 001, 000);

-- ----------------------------
-- Table structure for _words
-- ----------------------------
DROP TABLE IF EXISTS `_words`;
CREATE TABLE `_words`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `translate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `speak` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of _words
-- ----------------------------
INSERT INTO `_words` VALUES (1, 'qqq', 'qqq', 'qqq', 'qqq');
INSERT INTO `_words` VALUES (2, 'ewew', 'ew', 'ewewe', 'wwww');
INSERT INTO `_words` VALUES (4, 'w', 'w', 'w', 'w');
INSERT INTO `_words` VALUES (5, 'e', 'e', 'e', 'e');
INSERT INTO `_words` VALUES (6, 'e', 'e', 'e', 'e');
INSERT INTO `_words` VALUES (7, 'e', 'e', 'e', 'e');
INSERT INTO `_words` VALUES (8, 'e', 'e', 'e', 'e');
INSERT INTO `_words` VALUES (9, 'ddd', 'dd', 'dd', 'dd');

SET FOREIGN_KEY_CHECKS = 1;
