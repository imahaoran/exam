/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 01/12/2019 15:13:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `eid` int(10) NOT NULL AUTO_INCREMENT COMMENT '考试id',
  `ename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试名字',
  `etime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试时间',
  `eautostart` tinyint(1) NULL DEFAULT 0 COMMENT '是否自动开始',
  `eactive` tinyint(1) NULL DEFAULT 0 COMMENT '是否正在进行',
  `efinish` tinyint(1) NULL DEFAULT 0 COMMENT '是否结束',
  `earchive` tinyint(1) NULL DEFAULT 0 COMMENT '是否归档',
  `ecleared` tinyint(1) NULL DEFAULT 0 COMMENT '是否清除',
  `tid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建教师',
  `epaper` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷路径',
  `etype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试类型',
  PRIMARY KEY (`eid`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE,
  CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`  (
  `rid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `eid` int(10) NOT NULL COMMENT '考试id',
  `sid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `submitfile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '提交文件名',
  `grade` double(20, 0) NULL DEFAULT -1 COMMENT '成绩',
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  INDEX `grade_ibfk_2`(`sid`) USING BTREE,
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `exam` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `spwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`sid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1710121001', '李寻欢', '1710121001');
INSERT INTO `student` VALUES ('1710121002', '楚留香', '1710121002');
INSERT INTO `student` VALUES ('1710121003', '陆小凤', '1710121003');
INSERT INTO `student` VALUES ('1710121004', '叶孤城', '1710121004');
INSERT INTO `student` VALUES ('1710121005', '花满楼', '1710121005');
INSERT INTO `student` VALUES ('1710121006', '江别鹤', '1710121006');
INSERT INTO `student` VALUES ('1710121007', '荆无命', '1710121007');
INSERT INTO `student` VALUES ('1710121008', '燕十三', '1710121008');
INSERT INTO `student` VALUES ('1710121009', '司空摘星', '1710121009');
INSERT INTO `student` VALUES ('1710121010', '西门吹雪', '1710121010');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师号',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师真实姓名',
  `tpwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师登录密码',
  `tadmin` tinyint(1) NULL DEFAULT 0 COMMENT '是否是管理员',
  PRIMARY KEY (`tid`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1710121000', 'admin', '1710121000', 1);

SET FOREIGN_KEY_CHECKS = 1;
