/*
 Navicat Premium Data Transfer

 Source Server         : 47.105.136.203
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : 47.105.136.203:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 30/11/2019 10:05:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam_examination
-- ----------------------------
DROP TABLE IF EXISTS `exam_examination`;
CREATE TABLE `exam_examination`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `exam_paper_id` int(11) NULL DEFAULT NULL COMMENT '试卷代码',
  `train_course_id` int(11) NULL DEFAULT NULL COMMENT '课程代码',
  `traincourse_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷名称',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '考试类型(1-模拟考试；2-正式考试)',
  `enable_control_time` tinyint(4) NULL DEFAULT NULL COMMENT '是否控制开始结束时间（0-不控制,1-控制）',
  `start_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `time_length` int(11) NULL DEFAULT NULL COMMENT '考试时长（分钟）',
  `exam_number` int(6) NULL DEFAULT NULL COMMENT '考试次数',
  `pass_mark` int(6) NULL DEFAULT NULL COMMENT '及格分数',
  `question_disorder` tinyint(4) NULL DEFAULT NULL COMMENT '题目乱序（1-不打乱顺序，2-打乱顺序）',
  `finished_paper` tinyint(4) NULL DEFAULT NULL COMMENT '交卷后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）',
  `exam_end` tinyint(4) NULL DEFAULT NULL COMMENT '考试结束后（0-全部不可见，1-分数可见，2-对错可见，3-答案可见）',
  `examination_user_limit` tinyint(4) NULL DEFAULT NULL COMMENT '考试对象（1-所有人，2-限定对象）',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_examination
-- ----------------------------
INSERT INTO `exam_examination` VALUES (1, NULL, 1, 1, NULL, '10以内加减法', 1, 0, '2019-11-28 09:47:54', '2019-11-28 09:47:54', 10, 1, 60, 2, 1, 2, NULL, 'admin', '2019-11-28 09:47:54', 'admin', '2019-11-28 18:10:27', '1111', 0);
INSERT INTO `exam_examination` VALUES (2, NULL, 2, 1, NULL, '10以内加减法', 2, 1, '2019-11-28 12:00:00', '2019-12-31 12:00:00', 10, 1, 60, 2, 1, 1, NULL, 'admin', '2019-11-28 09:49:03', 'admin', '2019-11-28 17:51:19', '', 0);

-- ----------------------------
-- Table structure for exam_examination_user
-- ----------------------------
DROP TABLE IF EXISTS `exam_examination_user`;
CREATE TABLE `exam_examination_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exam_examination_id` int(11) NULL DEFAULT NULL COMMENT '考试代码',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试对象表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_examination_user
-- ----------------------------
INSERT INTO `exam_examination_user` VALUES (1, 2, 1, 'admin', '2019-11-28 15:02:40', NULL, '2019-11-28 15:02:39', NULL, 0);
INSERT INTO `exam_examination_user` VALUES (2, 2, 102, 'dev', '2019-11-29 14:07:06', NULL, '2019-11-29 14:07:06', NULL, 0);

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，试卷ID',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `exam_paper_category_id` int(11) NULL DEFAULT NULL COMMENT '试卷分类',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '试卷类型（1-固定试卷；2-随机试卷）',
  `score` int(6) NULL DEFAULT NULL COMMENT '分数',
  `question_number` int(6) NULL DEFAULT NULL COMMENT '试题数量',
  `order_num` int(6) NULL DEFAULT NULL COMMENT '显示顺序',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_paper
-- ----------------------------
INSERT INTO `exam_paper` VALUES (1, NULL, 2, 'A卷', 1, 100, 10, NULL, 'admin', '2019-11-27 18:02:48', 'dev', '2019-11-29 16:55:46', '固定试卷类型', 0);
INSERT INTO `exam_paper` VALUES (2, NULL, 3, 'B卷', 2, 155, 25, NULL, 'admin', '2019-11-27 18:07:27', 'admin', '2019-11-29 10:40:32', '非固定试卷类型', 0);

-- ----------------------------
-- Table structure for exam_paper_category
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_category`;
CREATE TABLE `exam_paper_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父课程分类ID',
  `parent_ids` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '祖级列表',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试卷类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_paper_category
-- ----------------------------
INSERT INTO `exam_paper_category` VALUES (1, NULL, 0, '0', '全部试卷', 1, NULL, '2019-11-27 17:56:36', NULL, '2019-11-27 17:56:36', NULL, 0);
INSERT INTO `exam_paper_category` VALUES (2, NULL, 1, '0,1', '固定试卷类型', 1, 'admin', '2019-11-27 17:56:59', 'admin', '2019-11-27 18:05:39', '固定试卷类型', 0);
INSERT INTO `exam_paper_category` VALUES (3, NULL, 1, '0,1', '非固定试卷类型', 2, 'admin', '2019-11-27 17:57:11', 'admin', '2019-11-27 18:05:49', '非固定试卷类型', 0);

-- ----------------------------
-- Table structure for exam_paper_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_question`;
CREATE TABLE `exam_paper_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，试卷题目ID',
  `exam_paper_id` int(11) NULL DEFAULT NULL COMMENT '试卷代码',
  `exam_question_id` int(11) NULL DEFAULT NULL COMMENT '问题代码',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  `score` int(6) NULL DEFAULT NULL COMMENT '总分数',
  `order_num` int(6) NULL DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 231 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试卷题目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_paper_question
-- ----------------------------
INSERT INTO `exam_paper_question` VALUES (167, 2, 4, 'admin', '2019-11-29 10:40:29', 'admin', '2019-11-29 10:40:29', NULL, 0, 15, 1);
INSERT INTO `exam_paper_question` VALUES (168, 2, 6, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 2);
INSERT INTO `exam_paper_question` VALUES (169, 2, 9, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 3);
INSERT INTO `exam_paper_question` VALUES (170, 2, 7, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 4);
INSERT INTO `exam_paper_question` VALUES (171, 2, 8, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 5);
INSERT INTO `exam_paper_question` VALUES (172, 2, 10, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 6);
INSERT INTO `exam_paper_question` VALUES (173, 2, 11, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 7);
INSERT INTO `exam_paper_question` VALUES (174, 2, 12, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 8);
INSERT INTO `exam_paper_question` VALUES (175, 2, 13, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 5, 9);
INSERT INTO `exam_paper_question` VALUES (176, 2, 14, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 15, 10);
INSERT INTO `exam_paper_question` VALUES (177, 2, 15, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 15, 11);
INSERT INTO `exam_paper_question` VALUES (178, 2, 16, 'admin', '2019-11-29 10:40:30', 'admin', '2019-11-29 10:40:30', NULL, 0, 15, 12);
INSERT INTO `exam_paper_question` VALUES (179, 2, 17, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 15, 13);
INSERT INTO `exam_paper_question` VALUES (180, 2, 18, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 15, 14);
INSERT INTO `exam_paper_question` VALUES (181, 2, 19, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 15, 15);
INSERT INTO `exam_paper_question` VALUES (182, 2, 26, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 10, 16);
INSERT INTO `exam_paper_question` VALUES (183, 2, 27, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 10, 17);
INSERT INTO `exam_paper_question` VALUES (184, 2, 28, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 10, 18);
INSERT INTO `exam_paper_question` VALUES (185, 2, 28, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (186, 2, 29, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 10, 19);
INSERT INTO `exam_paper_question` VALUES (187, 2, 29, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (188, 2, 30, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 10, 20);
INSERT INTO `exam_paper_question` VALUES (189, 2, 30, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (190, 2, 31, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 10, 21);
INSERT INTO `exam_paper_question` VALUES (191, 2, 31, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (192, 2, 27, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 22);
INSERT INTO `exam_paper_question` VALUES (193, 2, 28, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (194, 2, 28, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 23);
INSERT INTO `exam_paper_question` VALUES (195, 2, 29, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (196, 2, 29, 'admin', '2019-11-29 10:40:31', 'admin', '2019-11-29 10:40:31', NULL, 0, 0, 24);
INSERT INTO `exam_paper_question` VALUES (197, 2, 30, 'admin', '2019-11-29 10:40:32', 'admin', '2019-11-29 10:40:32', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (198, 2, 30, 'admin', '2019-11-29 10:40:32', 'admin', '2019-11-29 10:40:32', NULL, 0, 0, 25);
INSERT INTO `exam_paper_question` VALUES (199, 2, 31, 'admin', '2019-11-29 10:40:32', 'admin', '2019-11-29 10:40:32', NULL, 0, 0, 9999);
INSERT INTO `exam_paper_question` VALUES (200, 2, 31, 'admin', '2019-11-29 10:40:32', 'admin', '2019-11-29 10:40:32', NULL, 0, 0, 26);
INSERT INTO `exam_paper_question` VALUES (221, 1, 6, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 5, 1);
INSERT INTO `exam_paper_question` VALUES (222, 1, 8, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 5, 2);
INSERT INTO `exam_paper_question` VALUES (223, 1, 10, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 5, 3);
INSERT INTO `exam_paper_question` VALUES (224, 1, 11, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 5, 4);
INSERT INTO `exam_paper_question` VALUES (225, 1, 15, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 15, 5);
INSERT INTO `exam_paper_question` VALUES (226, 1, 16, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 15, 6);
INSERT INTO `exam_paper_question` VALUES (227, 1, 18, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 15, 7);
INSERT INTO `exam_paper_question` VALUES (228, 1, 19, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 15, 8);
INSERT INTO `exam_paper_question` VALUES (229, 1, 26, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 10, 9);
INSERT INTO `exam_paper_question` VALUES (230, 1, 27, 'dev', '2019-11-29 16:55:45', 'dev', '2019-11-29 16:55:45', NULL, 0, 10, 10);

-- ----------------------------
-- Table structure for exam_paper_type_number
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_type_number`;
CREATE TABLE `exam_paper_type_number`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，试卷题型数量ID',
  `exam_paper_id` int(11) NULL DEFAULT NULL COMMENT '试卷代码',
  `exam_question_type` int(11) NULL DEFAULT NULL COMMENT '问题类型（来自字典表）',
  `number` int(11) NULL DEFAULT NULL COMMENT '题型数量',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试卷题型数量' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_paper_type_number
-- ----------------------------
INSERT INTO `exam_paper_type_number` VALUES (4, 2, 1, 4, 'admin', '2019-11-27 18:07:27', 'admin', '2019-11-27 18:07:27', NULL, 0);
INSERT INTO `exam_paper_type_number` VALUES (5, 2, 2, 4, 'admin', '2019-11-27 18:07:27', 'admin', '2019-11-27 18:07:27', NULL, 0);
INSERT INTO `exam_paper_type_number` VALUES (6, 2, 3, 2, 'admin', '2019-11-27 18:07:27', 'admin', '2019-11-27 18:07:27', NULL, 0);
INSERT INTO `exam_paper_type_number` VALUES (7, 1, 1, 0, 'admin', '2019-11-27 18:07:45', 'admin', '2019-11-27 18:07:45', NULL, 0);
INSERT INTO `exam_paper_type_number` VALUES (8, 1, 2, 0, 'admin', '2019-11-27 18:07:45', 'admin', '2019-11-27 18:07:45', NULL, 0);
INSERT INTO `exam_paper_type_number` VALUES (9, 1, 3, 0, 'admin', '2019-11-27 18:07:45', 'admin', '2019-11-27 18:07:45', NULL, 0);

-- ----------------------------
-- Table structure for exam_practice
-- ----------------------------
DROP TABLE IF EXISTS `exam_practice`;
CREATE TABLE `exam_practice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,练习ID',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门代码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '练习名称',
  `train_course_id` int(11) NULL DEFAULT NULL COMMENT '课程代码',
  `enable_control_time` tinyint(4) NULL DEFAULT NULL COMMENT '是否控制开始结束时间（0-不控制,1-控制）',
  `start_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `practice_user_limit` tinyint(4) NULL DEFAULT NULL COMMENT '练习对象（1-所有人，2-限定对象）',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '练习表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_practice
-- ----------------------------
INSERT INTO `exam_practice` VALUES (1, NULL, '10以内加减法(无时间限制)', 1, 0, '2019-11-27 06:28:36', '2019-11-27 06:26:49', 1, 'admin', '2019-11-27 18:26:50', 'admin', '2019-11-28 17:03:26', '10以内加减法，不控制时间，不限定练习对象1', 0);
INSERT INTO `exam_practice` VALUES (2, NULL, '10以内加减法', 1, 1, '2019-11-28 01:30:58', '2019-11-30 11:59:59', 1, 'admin', '2019-11-27 18:27:37', 'admin', '2019-11-28 13:33:50', '10以内加减法，控制时间，限定练习对象', 0);

-- ----------------------------
-- Table structure for exam_practice_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_practice_question`;
CREATE TABLE `exam_practice_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,试卷题目ID',
  `exam_practice_id` int(11) NULL DEFAULT NULL COMMENT '试卷代码',
  `exam_question_id` int(11) NULL DEFAULT NULL COMMENT '题目代码',
  `score` int(6) NULL DEFAULT NULL COMMENT '分数',
  `order_num` int(6) NULL DEFAULT NULL COMMENT '排序号',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  `name` int(6) NULL DEFAULT NULL COMMENT '题目名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '题目类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试卷题目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_practice_question
-- ----------------------------
INSERT INTO `exam_practice_question` VALUES (43, 1, 4, 0, 1, 'admin', '2019-11-27 18:28:35', 'admin', '2019-11-27 18:28:35', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (44, 1, 6, 0, 2, 'admin', '2019-11-27 18:28:35', 'admin', '2019-11-27 18:28:35', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (45, 1, 7, 0, 3, 'admin', '2019-11-27 18:28:35', 'admin', '2019-11-27 18:28:35', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (46, 1, 8, 0, 4, 'admin', '2019-11-27 18:28:35', 'admin', '2019-11-27 18:28:35', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (47, 1, 9, 0, 5, 'admin', '2019-11-27 18:28:35', 'admin', '2019-11-27 18:28:35', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (48, 1, 10, 0, 6, 'admin', '2019-11-27 18:28:35', 'admin', '2019-11-27 18:28:35', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (49, 1, 11, 0, 7, 'admin', '2019-11-27 18:28:35', 'admin', '2019-11-27 18:28:35', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (50, 1, 12, 0, 8, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (51, 1, 13, 0, 9, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (52, 1, 14, 0, 10, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (53, 1, 15, 0, 11, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (54, 1, 16, 0, 12, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (55, 1, 17, 0, 13, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (56, 1, 18, 0, 14, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (57, 1, 19, 0, 15, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (58, 1, 26, 0, 16, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (59, 1, 27, 0, 17, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (60, 1, 28, 0, 18, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (61, 1, 29, 0, 19, 'admin', '2019-11-27 18:28:36', 'admin', '2019-11-27 18:28:36', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (62, 1, 30, 0, 20, 'admin', '2019-11-27 18:28:37', 'admin', '2019-11-27 18:28:37', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (63, 1, 31, 0, 21, 'admin', '2019-11-27 18:28:37', 'admin', '2019-11-27 18:28:37', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (137, 2, 4, 0, 1, 'admin', '2019-11-28 13:30:56', 'admin', '2019-11-28 13:30:56', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (138, 2, 6, 0, 2, 'admin', '2019-11-28 13:30:56', 'admin', '2019-11-28 13:30:56', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (139, 2, 7, 0, 3, 'admin', '2019-11-28 13:30:56', 'admin', '2019-11-28 13:30:56', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (140, 2, 8, 0, 4, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (141, 2, 9, 0, 5, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (142, 2, 11, 0, 6, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (143, 2, 12, 0, 7, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (144, 2, 13, 0, 8, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (145, 2, 14, 0, 9, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (146, 2, 10, 0, 10, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (147, 2, 15, 0, 11, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (148, 2, 16, 0, 12, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (149, 2, 17, 0, 13, 'admin', '2019-11-28 13:30:57', 'admin', '2019-11-28 13:30:57', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (150, 2, 18, 0, 14, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (151, 2, 19, 0, 15, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (152, 2, 26, 0, 16, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (153, 2, 27, 0, 17, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (154, 2, 28, 0, 18, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (155, 2, 29, 0, 19, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (156, 2, 30, 0, 20, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);
INSERT INTO `exam_practice_question` VALUES (157, 2, 31, 0, 21, 'admin', '2019-11-28 13:30:58', 'admin', '2019-11-28 13:30:58', NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for exam_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_question`;
CREATE TABLE `exam_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，问题表id',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题标题',
  `answer` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题答案',
  `type` int(11) NULL DEFAULT NULL COMMENT '问题类型',
  `label` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_question
-- ----------------------------
INSERT INTO `exam_question` VALUES (4, '1+2=', 'C,D,E', 2, NULL, 4, 'admin', '2019-11-27 17:11:54', 'admin', '2019-11-29 14:25:35', '1+2=3', 0);
INSERT INTO `exam_question` VALUES (7, '1+3=', 'B', 1, NULL, 3, 'admin', '2019-11-27 17:32:03', NULL, '2019-11-27 17:32:02', NULL, 0);
INSERT INTO `exam_question` VALUES (8, '1+4=', 'B', 1, NULL, 3, 'admin', '2019-11-27 17:32:04', NULL, '2019-11-27 17:32:03', NULL, 0);
INSERT INTO `exam_question` VALUES (9, '1+5=', 'B', 1, NULL, 3, 'admin', '2019-11-27 17:32:04', NULL, '2019-11-27 17:32:03', NULL, 0);
INSERT INTO `exam_question` VALUES (10, '1+6=', 'B', 1, NULL, 3, 'admin', '2019-11-27 17:32:04', NULL, '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question` VALUES (11, '1+7=', 'B', 1, NULL, 3, 'admin', '2019-11-27 17:32:05', NULL, '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question` VALUES (12, '1+8=', 'C', 1, NULL, 3, 'admin', '2019-11-27 17:33:33', NULL, '2019-11-27 17:33:33', '1+8=9', 0);
INSERT INTO `exam_question` VALUES (13, '1+9', 'D', 1, NULL, 3, 'admin', '2019-11-27 17:37:38', 'admin', '2019-11-27 17:45:20', '1+9=10', 0);
INSERT INTO `exam_question` VALUES (14, '1+2=', 'B,C', 2, NULL, 4, 'admin', '2019-11-27 17:51:55', NULL, '2019-11-27 17:51:56', '1+1=2', 0);
INSERT INTO `exam_question` VALUES (15, '1+3=', 'B,C', 2, NULL, 4, 'admin', '2019-11-27 17:51:58', NULL, '2019-11-27 17:51:57', '1+1=2', 0);
INSERT INTO `exam_question` VALUES (16, '1+4=', 'B,C', 2, NULL, 4, 'admin', '2019-11-27 17:51:58', NULL, '2019-11-27 17:51:57', '1+1=2', 0);
INSERT INTO `exam_question` VALUES (17, '1+5=', 'B,C', 2, NULL, 4, 'admin', '2019-11-27 17:51:59', NULL, '2019-11-27 17:51:58', '1+1=2', 0);
INSERT INTO `exam_question` VALUES (18, '1+6=', 'B,C', 2, NULL, 4, 'admin', '2019-11-27 17:52:00', NULL, '2019-11-27 17:51:59', '1+1=2', 0);
INSERT INTO `exam_question` VALUES (19, '1+7=', 'B,C', 2, NULL, 4, 'admin', '2019-11-27 17:52:00', NULL, '2019-11-27 17:51:59', '1+1=2', 0);
INSERT INTO `exam_question` VALUES (26, '1+2=2', 'B', 3, NULL, 5, 'admin', '2019-11-27 17:54:32', NULL, '2019-11-27 17:54:31', NULL, 0);
INSERT INTO `exam_question` VALUES (27, '1+3=4', 'A', 3, NULL, 5, 'admin', '2019-11-27 17:54:32', NULL, '2019-11-27 17:54:31', NULL, 0);
INSERT INTO `exam_question` VALUES (28, '1+4=5', 'A', 3, NULL, 5, 'admin', '2019-11-27 17:54:33', NULL, '2019-11-27 17:54:32', NULL, 0);
INSERT INTO `exam_question` VALUES (29, '1+5=6', 'A', 3, NULL, 5, 'admin', '2019-11-27 17:54:33', NULL, '2019-11-27 17:54:32', NULL, 0);
INSERT INTO `exam_question` VALUES (30, '1+6=3', 'B', 3, NULL, 5, 'admin', '2019-11-27 17:54:33', NULL, '2019-11-27 17:54:32', NULL, 0);
INSERT INTO `exam_question` VALUES (31, '1+7=1', 'B', 3, NULL, 5, 'admin', '2019-11-27 17:54:33', NULL, '2019-11-27 17:54:32', NULL, 0);

-- ----------------------------
-- Table structure for exam_question_category
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_category`;
CREATE TABLE `exam_question_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，试题分类id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父课程分类ID',
  `parent_ids` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '祖级列表',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  `order_num` int(6) NULL DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试题分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_question_category
-- ----------------------------
INSERT INTO `exam_question_category` VALUES (1, '所有分类', 0, '0', NULL, '2019-11-27 17:00:39', NULL, '2019-11-27 17:00:39', NULL, 0, NULL);
INSERT INTO `exam_question_category` VALUES (2, '选择题', 1, '0,1', 'admin', '2019-11-27 17:01:13', NULL, '2019-11-27 17:01:12', '选择题分单选，多选', 0, NULL);
INSERT INTO `exam_question_category` VALUES (3, '单选题', 2, '0,1,2', 'admin', '2019-11-27 17:01:26', NULL, '2019-11-27 17:01:25', '单选题', 0, NULL);
INSERT INTO `exam_question_category` VALUES (4, '多选题', 2, '0,1,2', 'admin', '2019-11-27 17:01:39', NULL, '2019-11-27 17:01:38', '多选题', 0, NULL);
INSERT INTO `exam_question_category` VALUES (5, '判断题', 1, '0,1', 'admin', '2019-11-27 17:52:37', NULL, '2019-11-27 17:52:36', '判断题', 0, NULL);

-- ----------------------------
-- Table structure for exam_question_comment
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_comment`;
CREATE TABLE `exam_question_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，问题点评表id',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点评内容',
  `exam_question_id` int(11) NULL DEFAULT NULL COMMENT '问题id',
  `praise_count` int(11) NULL DEFAULT NULL COMMENT '点赞数量',
  `comment_type` tinyint(4) NULL DEFAULT NULL COMMENT '类型（0 学生点评 1 老师点评）',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题点评表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for exam_question_item
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_item`;
CREATE TABLE `exam_question_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，问题选项表id',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 选项内容',
  `number` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项',
  `exam_question_id` int(11) NULL DEFAULT NULL COMMENT '题目id',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题选项表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_question_item
-- ----------------------------
INSERT INTO `exam_question_item` VALUES (25, '3', 'A', 7, 'admin', '2019-11-27 17:32:03', 'admin', '2019-11-27 17:32:03', NULL, 0);
INSERT INTO `exam_question_item` VALUES (26, '4', 'B', 7, 'admin', '2019-11-27 17:32:03', 'admin', '2019-11-27 17:32:03', NULL, 0);
INSERT INTO `exam_question_item` VALUES (27, '5', 'C', 7, 'admin', '2019-11-27 17:32:03', 'admin', '2019-11-27 17:32:03', NULL, 0);
INSERT INTO `exam_question_item` VALUES (28, '6', 'D', 7, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (29, '4', 'A', 8, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (30, '5', 'B', 8, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (31, '6', 'C', 8, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (32, '7', 'D', 8, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (33, '5', 'A', 9, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (34, '6', 'B', 9, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (35, '7', 'C', 9, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (36, '8', 'D', 9, 'admin', '2019-11-27 17:32:04', 'admin', '2019-11-27 17:32:04', NULL, 0);
INSERT INTO `exam_question_item` VALUES (37, '6', 'A', 10, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (38, '7', 'B', 10, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (39, '8', 'C', 10, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (40, '9', 'D', 10, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (41, '7', 'A', 11, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (42, '8', 'B', 11, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (43, '9', 'C', 11, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (44, '10', 'D', 11, 'admin', '2019-11-27 17:32:05', 'admin', '2019-11-27 17:32:05', NULL, 0);
INSERT INTO `exam_question_item` VALUES (45, '7', 'A', 12, 'admin', '2019-11-27 17:33:33', NULL, '2019-11-27 17:33:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (46, '8', 'B', 12, 'admin', '2019-11-27 17:33:33', NULL, '2019-11-27 17:33:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (47, '9', 'C', 12, 'admin', '2019-11-27 17:33:33', NULL, '2019-11-27 17:33:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (48, '10', 'D', 12, 'admin', '2019-11-27 17:33:33', NULL, '2019-11-27 17:33:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (61, '7', 'A', 13, 'admin', '2019-11-27 17:45:20', NULL, '2019-11-27 17:46:02', '1+9=10', 0);
INSERT INTO `exam_question_item` VALUES (62, '8', 'B', 13, 'admin', '2019-11-27 17:45:20', NULL, '2019-11-27 17:46:05', '1+9=10', 0);
INSERT INTO `exam_question_item` VALUES (63, '9', 'C', 13, 'admin', '2019-11-27 17:45:20', NULL, '2019-11-27 17:46:05', '1+9=10', 0);
INSERT INTO `exam_question_item` VALUES (64, '10', 'D', 13, 'admin', '2019-11-27 17:45:20', NULL, '2019-11-27 17:46:05', '1+9=10', 0);
INSERT INTO `exam_question_item` VALUES (65, '2', 'A', 14, 'admin', '2019-11-27 17:51:57', 'admin', '2019-11-27 17:51:57', NULL, 0);
INSERT INTO `exam_question_item` VALUES (66, '3', 'B', 14, 'admin', '2019-11-27 17:51:57', 'admin', '2019-11-27 17:51:57', NULL, 0);
INSERT INTO `exam_question_item` VALUES (67, '3', 'C', 14, 'admin', '2019-11-27 17:51:57', 'admin', '2019-11-27 17:51:57', NULL, 0);
INSERT INTO `exam_question_item` VALUES (68, '5', 'D', 14, 'admin', '2019-11-27 17:51:57', 'admin', '2019-11-27 17:51:57', NULL, 0);
INSERT INTO `exam_question_item` VALUES (69, '6', 'E', 14, 'admin', '2019-11-27 17:51:57', 'admin', '2019-11-27 17:51:57', NULL, 0);
INSERT INTO `exam_question_item` VALUES (70, '7', 'F', 14, 'admin', '2019-11-27 17:51:57', 'admin', '2019-11-27 17:51:57', NULL, 0);
INSERT INTO `exam_question_item` VALUES (71, '3', 'A', 15, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (72, '4', 'B', 15, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (73, '3', 'C', 15, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (74, '6', 'D', 15, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (75, '7', 'E', 15, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (76, '8', 'F', 15, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (77, '4', 'A', 16, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (78, '5', 'B', 16, 'admin', '2019-11-27 17:51:58', 'admin', '2019-11-27 17:51:58', NULL, 0);
INSERT INTO `exam_question_item` VALUES (79, '3', 'C', 16, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (80, '7', 'D', 16, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (81, '8', 'E', 16, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (82, '9', 'F', 16, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (83, '5', 'A', 17, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (84, '6', 'B', 17, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (85, '3', 'C', 17, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (86, '8', 'D', 17, 'admin', '2019-11-27 17:51:59', 'admin', '2019-11-27 17:51:59', NULL, 0);
INSERT INTO `exam_question_item` VALUES (87, '9', 'E', 17, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (88, '10', 'F', 17, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (89, '6', 'A', 18, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (90, '7', 'B', 18, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (91, '3', 'C', 18, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (92, '9', 'D', 18, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (93, '10', 'E', 18, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (94, '11', 'F', 18, 'admin', '2019-11-27 17:52:00', 'admin', '2019-11-27 17:52:00', NULL, 0);
INSERT INTO `exam_question_item` VALUES (95, '7', 'A', 19, 'admin', '2019-11-27 17:52:01', 'admin', '2019-11-27 17:52:01', NULL, 0);
INSERT INTO `exam_question_item` VALUES (96, '8', 'B', 19, 'admin', '2019-11-27 17:52:01', 'admin', '2019-11-27 17:52:01', NULL, 0);
INSERT INTO `exam_question_item` VALUES (97, '3', 'C', 19, 'admin', '2019-11-27 17:52:01', 'admin', '2019-11-27 17:52:01', NULL, 0);
INSERT INTO `exam_question_item` VALUES (98, '10', 'D', 19, 'admin', '2019-11-27 17:52:01', 'admin', '2019-11-27 17:52:01', NULL, 0);
INSERT INTO `exam_question_item` VALUES (99, '11', 'E', 19, 'admin', '2019-11-27 17:52:01', 'admin', '2019-11-27 17:52:01', NULL, 0);
INSERT INTO `exam_question_item` VALUES (100, '12', 'F', 19, 'admin', '2019-11-27 17:52:01', 'admin', '2019-11-27 17:52:01', NULL, 0);
INSERT INTO `exam_question_item` VALUES (115, '正确', 'A', 26, 'admin', '2019-11-27 17:54:32', 'admin', '2019-11-27 17:54:32', NULL, 0);
INSERT INTO `exam_question_item` VALUES (116, '错误', 'B', 26, 'admin', '2019-11-27 17:54:32', 'admin', '2019-11-27 17:54:32', NULL, 0);
INSERT INTO `exam_question_item` VALUES (117, '正确', 'A', 27, 'admin', '2019-11-27 17:54:32', 'admin', '2019-11-27 17:54:32', NULL, 0);
INSERT INTO `exam_question_item` VALUES (118, '错误', 'B', 27, 'admin', '2019-11-27 17:54:32', 'admin', '2019-11-27 17:54:32', NULL, 0);
INSERT INTO `exam_question_item` VALUES (119, '正确', 'A', 28, 'admin', '2019-11-27 17:54:33', 'admin', '2019-11-27 17:54:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (120, '错误', 'B', 28, 'admin', '2019-11-27 17:54:33', 'admin', '2019-11-27 17:54:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (121, '正确', 'A', 29, 'admin', '2019-11-27 17:54:33', 'admin', '2019-11-27 17:54:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (122, '错误', 'B', 29, 'admin', '2019-11-27 17:54:33', 'admin', '2019-11-27 17:54:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (123, '正确', 'A', 30, 'admin', '2019-11-27 17:54:33', 'admin', '2019-11-27 17:54:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (124, '错误', 'B', 30, 'admin', '2019-11-27 17:54:33', 'admin', '2019-11-27 17:54:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (125, '正确', 'A', 31, 'admin', '2019-11-27 17:54:33', 'admin', '2019-11-27 17:54:33', NULL, 0);
INSERT INTO `exam_question_item` VALUES (126, '错误', 'B', 31, 'admin', '2019-11-27 17:54:34', 'admin', '2019-11-27 17:54:34', NULL, 0);
INSERT INTO `exam_question_item` VALUES (132, '1', 'A', 4, 'admin', '2019-11-29 14:25:35', NULL, '2019-11-29 14:25:36', '1+2=3', 0);
INSERT INTO `exam_question_item` VALUES (133, '2', 'B', 4, 'admin', '2019-11-29 14:25:35', NULL, '2019-11-29 14:25:36', '1+2=3', 0);
INSERT INTO `exam_question_item` VALUES (134, '3', 'C', 4, 'admin', '2019-11-29 14:25:35', NULL, '2019-11-29 14:25:36', '1+2=3', 0);
INSERT INTO `exam_question_item` VALUES (135, '3', 'D', 4, 'admin', '2019-11-29 14:25:35', NULL, '2019-11-29 14:25:36', '1+2=3', 0);
INSERT INTO `exam_question_item` VALUES (136, '5', 'E', 4, 'admin', '2019-11-29 14:25:35', NULL, '2019-11-29 14:25:36', '1+2=3', 0);

-- ----------------------------
-- Table structure for exam_user_collection_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_collection_question`;
CREATE TABLE `exam_user_collection_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，我的收藏表id',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `exam_question_id` int(11) NULL DEFAULT NULL COMMENT '试题编码',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我的收藏表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_user_collection_question
-- ----------------------------
INSERT INTO `exam_user_collection_question` VALUES (1, 1, 4, 'admin', '2019-11-28 11:47:49', 'admin', '2019-11-28 11:47:49', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (2, 1, 6, 'admin', '2019-11-29 10:11:40', 'admin', '2019-11-29 10:11:40', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (3, 1, 7, 'admin', '2019-11-29 10:11:43', 'admin', '2019-11-29 10:11:43', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (4, 1, 8, 'admin', '2019-11-29 10:11:45', 'admin', '2019-11-29 10:11:45', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (5, 1, 9, 'admin', '2019-11-29 10:11:46', 'admin', '2019-11-29 10:11:46', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (6, 1, 10, 'admin', '2019-11-29 10:11:47', 'admin', '2019-11-29 10:11:47', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (7, 1, 11, 'admin', '2019-11-29 10:11:48', 'admin', '2019-11-29 10:11:48', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (8, 1, 12, 'admin', '2019-11-29 10:11:49', 'admin', '2019-11-29 10:11:49', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (9, 1, 13, 'admin', '2019-11-29 10:11:51', 'admin', '2019-11-29 10:11:51', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (10, 1, 15, 'admin', '2019-11-29 10:11:52', 'admin', '2019-11-29 10:11:52', NULL, 0);
INSERT INTO `exam_user_collection_question` VALUES (11, 1, 30, 'admin', '2019-11-29 10:12:00', 'admin', '2019-11-29 10:12:00', NULL, 0);

-- ----------------------------
-- Table structure for exam_user_error_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_error_question`;
CREATE TABLE `exam_user_error_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，我的错题表id',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `exam_question_id` int(11) NULL DEFAULT NULL COMMENT '练习题代码',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我的错题表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_user_error_question
-- ----------------------------
INSERT INTO `exam_user_error_question` VALUES (5, 1, 4, 'admin', '2019-11-29 09:56:55', 'admin', '2019-11-29 09:56:55', NULL, 0);
INSERT INTO `exam_user_error_question` VALUES (6, 1, 30, 'admin', '2019-11-29 10:11:57', 'admin', '2019-11-29 10:11:57', NULL, 0);

-- ----------------------------
-- Table structure for exam_user_examination
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_examination`;
CREATE TABLE `exam_user_examination`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，我的考试记录表id',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `exam_examination_id` int(11) NULL DEFAULT NULL COMMENT '试题编码',
  `exam_paper_id` int(11) NULL DEFAULT NULL COMMENT '试卷编码',
  `score` int(11) NULL DEFAULT NULL COMMENT '考试得分',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我的考试记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_user_examination
-- ----------------------------
INSERT INTO `exam_user_examination` VALUES (6, 1, 2, 2, 30, 'admin', '2019-11-29 10:12:18', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination` VALUES (7, 102, 2, 2, 0, 'dev', '2019-11-29 14:07:09', NULL, '2019-11-29 14:07:13', NULL, 0);

-- ----------------------------
-- Table structure for exam_user_examination_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_examination_question`;
CREATE TABLE `exam_user_examination_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键， 我参与过的考试试题表id',
  `exam_user_examination_id` int(11) NULL DEFAULT NULL COMMENT '考试记录编码',
  `user_answer` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答答案',
  `exam_question_id` int(11) NULL DEFAULT NULL COMMENT '试题编码',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我参与过的考试试题表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam_user_examination_question
-- ----------------------------
INSERT INTO `exam_user_examination_question` VALUES (1, 3, '', 19, 'admin', '2019-11-28 16:56:07', NULL, '2019-11-28 16:56:06', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (2, 3, '', 12, 'admin', '2019-11-28 16:56:07', NULL, '2019-11-28 16:56:07', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (3, 3, '', 11, 'admin', '2019-11-28 16:56:07', NULL, '2019-11-28 16:56:07', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (4, 3, '', 7, 'admin', '2019-11-28 16:56:07', NULL, '2019-11-28 16:56:07', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (5, 3, '', 8, 'admin', '2019-11-28 16:56:07', NULL, '2019-11-28 16:56:07', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (6, 3, '', 16, 'admin', '2019-11-28 16:56:08', NULL, '2019-11-28 16:56:07', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (7, 3, '', 29, 'admin', '2019-11-28 16:56:08', NULL, '2019-11-28 16:56:07', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (8, 3, '', 18, 'admin', '2019-11-28 16:56:08', NULL, '2019-11-28 16:56:08', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (9, 3, '', 26, 'admin', '2019-11-28 16:56:08', NULL, '2019-11-28 16:56:08', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (10, 3, '', 17, 'admin', '2019-11-28 16:56:08', NULL, '2019-11-28 16:56:08', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (11, 4, '', 18, 'admin', '2019-11-28 17:01:01', NULL, '2019-11-28 17:01:01', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (12, 4, '', 11, 'admin', '2019-11-28 17:01:02', NULL, '2019-11-28 17:01:01', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (13, 4, '', 6, 'admin', '2019-11-28 17:01:02', NULL, '2019-11-28 17:01:01', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (14, 4, '', 10, 'admin', '2019-11-28 17:01:02', NULL, '2019-11-28 17:01:02', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (15, 4, '', 8, 'admin', '2019-11-28 17:01:02', NULL, '2019-11-28 17:01:02', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (16, 4, '', 26, 'admin', '2019-11-28 17:01:02', NULL, '2019-11-28 17:01:02', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (17, 4, '', 19, 'admin', '2019-11-28 17:01:02', NULL, '2019-11-28 17:01:02', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (18, 4, '', 16, 'admin', '2019-11-28 17:01:02', NULL, '2019-11-28 17:01:02', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (19, 4, '', 15, 'admin', '2019-11-28 17:01:03', NULL, '2019-11-28 17:01:02', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (20, 4, '', 27, 'admin', '2019-11-28 17:01:03', NULL, '2019-11-28 17:01:02', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (21, 5, '', 27, 'admin', '2019-11-28 17:02:39', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (22, 5, '', 11, 'admin', '2019-11-28 17:02:39', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (23, 5, '', 16, 'admin', '2019-11-28 17:02:39', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (24, 5, '', 19, 'admin', '2019-11-28 17:02:39', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (25, 5, '', 8, 'admin', '2019-11-28 17:02:39', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (26, 5, '', 6, 'admin', '2019-11-28 17:02:40', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (27, 5, '', 15, 'admin', '2019-11-28 17:02:40', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (28, 5, '', 18, 'admin', '2019-11-28 17:02:40', NULL, '2019-11-28 17:02:39', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (29, 5, '', 10, 'admin', '2019-11-28 17:02:40', NULL, '2019-11-28 17:02:40', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (30, 5, '', 26, 'admin', '2019-11-28 17:02:40', NULL, '2019-11-28 17:02:40', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (31, 6, 'B,E,F', 16, 'admin', '2019-11-29 10:12:56', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (32, 6, 'B', 31, 'admin', '2019-11-29 10:12:56', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (33, 6, 'B', 6, 'admin', '2019-11-29 10:12:56', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (34, 6, 'B', 26, 'admin', '2019-11-29 10:12:56', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (35, 6, 'B', 15, 'admin', '2019-11-29 10:12:56', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (36, 6, 'C', 10, 'admin', '2019-11-29 10:12:57', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (37, 6, 'D', 12, 'admin', '2019-11-29 10:12:57', NULL, '2019-11-29 10:12:57', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (38, 6, 'A,B,C', 4, 'admin', '2019-11-29 10:12:57', NULL, '2019-11-29 10:12:58', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (39, 6, 'B', 8, 'admin', '2019-11-29 10:12:57', NULL, '2019-11-29 10:12:58', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (40, 6, 'A', 17, 'admin', '2019-11-29 10:12:57', NULL, '2019-11-29 10:12:58', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (41, 7, '', 15, 'dev', '2019-11-29 14:07:11', NULL, '2019-11-29 14:07:12', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (42, 7, '', 18, 'dev', '2019-11-29 14:07:11', NULL, '2019-11-29 14:07:12', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (43, 7, '', 10, 'dev', '2019-11-29 14:07:12', NULL, '2019-11-29 14:07:12', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (44, 7, '', 7, 'dev', '2019-11-29 14:07:12', NULL, '2019-11-29 14:07:12', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (45, 7, '', 4, 'dev', '2019-11-29 14:07:12', NULL, '2019-11-29 14:07:12', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (46, 7, '', 11, 'dev', '2019-11-29 14:07:12', NULL, '2019-11-29 14:07:12', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (47, 7, '', 14, 'dev', '2019-11-29 14:07:12', NULL, '2019-11-29 14:07:12', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (48, 7, '', 26, 'dev', '2019-11-29 14:07:12', NULL, '2019-11-29 14:07:13', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (49, 7, '', 30, 'dev', '2019-11-29 14:07:13', NULL, '2019-11-29 14:07:13', NULL, 0);
INSERT INTO `exam_user_examination_question` VALUES (50, 7, '', 13, 'dev', '2019-11-29 14:07:13', NULL, '2019-11-29 14:07:13', NULL, 0);

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', '系统默认（无参）', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', '系统默认（有参）', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', '系统默认（无参）', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707074000E302F3130202A202A202A202A203F70740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672795461736B74000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', '系统默认（有参）', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707074000E302F3230202A202A202A202A203F70740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000672795461736B74000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'XB-20191125AMWR1574834324439', 1574834964510, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(13) NULL DEFAULT NULL,
  `prev_fire_time` bigint(13) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(2) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1574834330000, -1, 5, 'PAUSED', 'CRON', 1574834324000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', '系统默认（无参）', 'TASK_CLASS_NAME1', '系统默认（无参）', NULL, 1574834230000, -1, 5, 'PAUSED', 'CRON', 1574834228000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 1574834325000, -1, 5, 'PAUSED', 'CRON', 1574834325000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', '系统默认（有参）', 'TASK_CLASS_NAME2', '系统默认（有参）', NULL, 1574834240000, -1, 5, 'PAUSED', 'CRON', 1574834229000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 1574834340000, -1, 5, 'PAUSED', 'CRON', 1574834327000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-default', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认 skin-default、蓝色 skin-blue、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (100, '云存储配置KEY', 'sys.oss.cloudStorage', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"ecFOYJi5oQ5NwDpRyF_f84F8v5ZxppViO78-aiBS\",\"qiniuBucketName\":\"xa-newretail\",\"qiniuDomain\":\"https://nrqiniu.tairanmall.com\",\"qiniuPrefix\":\"/\",\"qiniuSecretKey\":\"pmrAyOSstMPYkTyUaMTSAYsR5mVrnaedGCwgqS4k\",\"type\":1}', 'Y', '', NULL, '', NULL, 'qiniuDomain ');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '陕西伟业医药有限公司', 0, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '西安总公司', 1, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '咸阳分公司', 2, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '信息中心', 1, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '陕西伟业医药有限公司', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (11, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (12, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (13, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (14, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (15, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (16, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (17, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (18, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (19, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (20, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (21, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (22, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (23, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (24, 8, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (25, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (26, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 1, '公开', '1', 'train_open_state', '', '', 'Y', '0', '', '2019-11-27 16:24:10', '', '2019-11-27 16:24:04', '课程公开状态');
INSERT INTO `sys_dict_data` VALUES (101, 2, '不公开', '2', 'train_open_state', '', '', 'N', '0', '', '2019-11-27 16:24:12', '', '2019-11-27 16:24:07', '课程公开状态');
INSERT INTO `sys_dict_data` VALUES (102, 1, '视频', '1', 'train_course_section_type', '', '', 'N', '0', '', NULL, '', NULL, '课件类型');
INSERT INTO `sys_dict_data` VALUES (103, 2, '附件', '2', 'train_course_section_type ', '', '', 'N', '1', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (104, 1, '单选题', '1', 'exam_question_type', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (105, 2, '多选题', '2', 'exam_question_type', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (106, 3, '判断题', '3', 'exam_question_type', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (107, 1, '固定试卷', '1', 'exam_paper_type', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (108, 2, '随机试卷', '2', 'exam_paper_type', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (110, 0, '不控制', '0', 'exam_ination_enableControlTime', '', '', 'Y', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (111, 1, '控制', '1', 'exam_ination_enableControlTime', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (112, 1, '所有人', '1', 'exam_ination_examinationUserLimit', '', '', 'Y', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (113, 2, '限定对象', '2', 'exam_ination_examinationUserLimit', '', '', 'N', '1', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (114, 1, '模拟考试', '1', 'exam_ination_type', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (115, 2, '正式考试', '2', 'exam_ination_type', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (116, 1, '不打乱顺序', '1', 'exam_ination_questionDisorder', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (117, 2, '打乱顺序', '2', 'exam_ination_questionDisorder', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (118, 0, '全部不可见', '0', 'exam_ination_finishedPaper', '', '', 'Y', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (119, 1, '分数可见', '1', 'exam_ination_finishedPaper', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (120, 0, '全部不可见', '0', 'exam_ination_examEnd', '', '', 'Y', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (121, 1, '分数可见', '1', 'exam_ination_examEnd', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (122, 2, '对错可见', '2', 'exam_ination_finishedPaper', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (123, 3, '答案可见', '3', 'exam_ination_finishedPaper', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (124, 2, '对错可见', '2', 'exam_ination_examEnd', '', '', 'N', '0', '', NULL, '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (125, 3, '答案可见', '3', 'exam_ination_examEnd', '', '', 'N', '0', '', NULL, '', NULL, '');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (6, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (8, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (9, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法参数',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, 'ryTask', '系统默认（无参）', 'ryNoParams', '', '0/10 * * * * ?', '3', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (2, 'ryTask', '系统默认（有参）', 'ryParams', 'ry', '0/20 * * * * ?', '3', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务方法',
  `method_params` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法参数',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2060 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 4, '#', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-29 09:41:58', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 5, '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-29 09:41:32', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 6, '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-29 09:41:41', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 3, '/monitor/server', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '表单构建', 3, 1, '/tool/build', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES (114, '代码生成', 3, 2, '/tool/gen', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, '/tool/swagger', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1005, '重置密码', 100, 6, '#', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1006, '角色查询', 101, 1, '#', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '角色新增', 101, 2, '#', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色修改', 101, 3, '#', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色删除', 101, 4, '#', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色导出', 101, 5, '#', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '菜单查询', 102, 1, '#', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1012, '菜单新增', 102, 2, '#', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单修改', 102, 3, '#', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单删除', 102, 4, '#', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '部门查询', 103, 1, '#', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '部门新增', 103, 2, '#', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门修改', 103, 3, '#', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门删除', 103, 4, '#', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1019, '岗位查询', 104, 1, '#', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1020, '岗位新增', 104, 2, '#', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位修改', 104, 3, '#', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位删除', 104, 4, '#', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位导出', 104, 5, '#', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '字典查询', 105, 1, '#', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1025, '字典新增', 105, 2, '#', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典修改', 105, 3, '#', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典删除', 105, 4, '#', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典导出', 105, 5, '#', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1029, '参数查询', 106, 1, '#', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1030, '参数新增', 106, 2, '#', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1031, '参数修改', 106, 3, '#', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1032, '参数删除', 106, 4, '#', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1033, '参数导出', 106, 5, '#', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1034, '公告查询', 107, 1, '#', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1035, '公告新增', 107, 2, '#', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1036, '公告修改', 107, 3, '#', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1037, '公告删除', 107, 4, '#', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1038, '操作查询', 500, 1, '#', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1039, '操作删除', 500, 2, '#', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1040, '详细信息', 500, 3, '#', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 4, '#', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 3, '#', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1045, '在线查询', 109, 1, '#', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1046, '批量强退', 109, 2, '#', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1047, '单条强退', 109, 3, '#', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1048, '任务查询', 110, 1, '#', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1049, '任务新增', 110, 2, '#', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1050, '任务修改', 110, 3, '#', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1051, '任务删除', 110, 4, '#', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1052, '状态修改', 110, 5, '#', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1053, '任务详细', 110, 6, '#', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 114, 1, '#', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1056, '生成代码', 114, 2, '#', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (2000, '培训管理', 0, 1, '#', 'M', '0', '', 'fa fa-group', 'admin', '2019-11-29 09:28:53', 'admin', '2019-11-29 09:29:59', '');
INSERT INTO `sys_menu` VALUES (2001, '考试管理', 0, 2, '#', 'M', '0', '', 'fa fa-pencil-square', 'admin', '2019-11-29 09:29:22', 'admin', '2019-11-29 09:42:06', '');
INSERT INTO `sys_menu` VALUES (2002, '个人信息管理', 0, 3, '#', 'M', '0', '', 'fa fa-address-book', 'admin', '2019-11-29 09:30:52', 'admin', '2019-11-29 09:42:15', '');
INSERT INTO `sys_menu` VALUES (2004, '课程分类', 2000, 1, '/train/course/category', 'C', '0', 'train:course:category:view', '#', 'admin', '2019-11-29 09:33:39', 'admin', '2019-11-29 10:43:28', '');
INSERT INTO `sys_menu` VALUES (2005, '课程管理', 2000, 2, '/train/trainCourse', 'C', '0', 'train:trainCourse:view,train:trainCourseSection:view', '#', 'admin', '2019-11-29 09:34:32', 'admin', '2019-11-29 11:22:17', '');
INSERT INTO `sys_menu` VALUES (2006, '个人信息', 2002, 1, '/system/user/profile', 'C', '0', '', '#', 'admin', '2019-11-29 09:35:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '题库维护', 2001, 1, '/exam/examQuestion', 'C', '0', 'exam:examQuestion:view', '#', 'admin', '2019-11-29 09:38:47', 'admin', '2019-11-29 10:19:46', '');
INSERT INTO `sys_menu` VALUES (2008, '试卷维护', 2001, 2, '/exam/examPaper', 'C', '0', 'exam:examPaper:view', '#', 'admin', '2019-11-29 09:39:45', 'admin', '2019-11-29 10:20:06', '');
INSERT INTO `sys_menu` VALUES (2009, '练习管理', 2001, 2, '/exam/examPractice', 'C', '0', 'exam:examPractice:view', '#', 'admin', '2019-11-29 09:40:19', 'admin', '2019-11-29 10:20:23', '');
INSERT INTO `sys_menu` VALUES (2010, '考试管理', 2001, 3, '/exam/examExamination', 'C', '0', 'exam:examExamination:view', '#', 'admin', '2019-11-29 09:40:44', 'admin', '2019-11-29 10:20:39', '');
INSERT INTO `sys_menu` VALUES (2011, '新增', 2004, 1, '#', 'F', '0', 'train:course:category:add', '#', 'admin', '2019-11-29 10:36:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '修改', 2004, 2, '#', 'F', '0', 'train:course:category:edit', '#', 'admin', '2019-11-29 10:37:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '删除', 2004, 3, '#', 'F', '0', 'train:course:category:remove', '#', 'admin', '2019-11-29 10:37:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2014, '搜索', 2004, 4, '#', 'F', '0', 'train:course:category:list', '#', 'admin', '2019-11-29 10:38:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2015, '搜索（课程）', 2005, 1, '#', 'F', '0', 'train:trainCourse:list', '#', 'admin', '2019-11-29 10:44:15', 'admin', '2019-11-29 16:49:05', '');
INSERT INTO `sys_menu` VALUES (2016, '添加（课程）', 2005, 2, '#', 'F', '0', 'train:trainCourse:add', '#', 'admin', '2019-11-29 10:45:00', 'admin', '2019-11-29 10:46:04', '');
INSERT INTO `sys_menu` VALUES (2017, '修改（课程）', 2005, 3, '#', 'F', '0', 'train:trainCourse:edit', '#', 'admin', '2019-11-29 10:46:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2018, '删除（课程）', 2005, 4, '#', 'F', '0', 'train:trainCourse:remove', '#', 'admin', '2019-11-29 10:46:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2020, '搜索（章节）', 2005, 1, '#', 'F', '0', 'train:trainCourseSection:list', '#', 'admin', '2019-11-29 10:50:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2021, '添加（章节）', 2005, 2, '#', 'F', '0', 'train:trainCourseSection:add', '#', 'admin', '2019-11-29 10:51:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '修改（章节）', 2005, 3, '#', 'F', '0', 'train:trainCourseSection:edit', '#', 'admin', '2019-11-29 10:51:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '删除（章节）', 2005, 4, '#', 'F', '0', 'train:trainCourseSection:remove', '#', 'admin', '2019-11-29 10:51:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2024, '试题分类管理', 2007, 1, '/exam/examQuestionCategory', 'C', '0', 'exam:examQuestionCategory:view', '#', 'admin', '2019-11-29 10:57:56', 'admin', '2019-11-29 10:58:09', '');
INSERT INTO `sys_menu` VALUES (2025, '搜索', 2024, 1, '#', 'F', '0', 'exam:examQuestionCategory:list', '#', 'admin', '2019-11-29 10:58:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2026, '添加', 2024, 2, '#', 'F', '0', 'exam:examQuestionCategory:add', '#', 'admin', '2019-11-29 10:59:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2027, '编辑', 2024, 3, '#', 'F', '0', 'exam:examQuestionCategory:edit', '#', 'admin', '2019-11-29 10:59:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2028, '删除', 2024, 4, '#', 'F', '0', 'exam:examQuestionCategory:remove', '#', 'admin', '2019-11-29 11:00:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, '试题管理', 2007, 2, '/exam/examQuestion', 'C', '0', 'exam:examQuestion:view', '#', 'admin', '2019-11-29 11:01:36', 'admin', '2019-11-29 11:01:51', '');
INSERT INTO `sys_menu` VALUES (2032, '搜索', 2029, 1, '#', 'F', '0', 'exam:examQuestion:list', '#', 'admin', '2019-11-29 16:10:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2033, '新增', 2029, 2, '#', 'F', '0', 'exam:examQuestion:add', '#', 'admin', '2019-11-29 16:11:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2034, '修改', 2029, 3, '#', 'F', '0', 'exam:examQuestion:edit', '#', 'admin', '2019-11-29 16:11:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2035, '删除', 2029, 4, '#', 'F', '0', 'exam:examQuestion:remove', '#', 'admin', '2019-11-29 16:12:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '导入', 2029, 5, '#', 'F', '0', 'exam:examQuestion:import', '#', 'admin', '2019-11-29 16:13:42', 'admin', '2019-11-29 16:14:33', '');
INSERT INTO `sys_menu` VALUES (2037, '导出', 2029, 6, '#', 'F', '0', 'exam:examQuestion:export', '#', 'admin', '2019-11-29 16:14:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2038, '试卷分类管理', 2008, 1, '/exam/examPaperCategory', 'C', '0', 'exam:examPaperCategory:view', '#', 'admin', '2019-11-29 16:19:14', 'admin', '2019-11-29 16:33:23', '');
INSERT INTO `sys_menu` VALUES (2039, '试卷管理', 2008, 2, '/exam/examPaper', 'C', '0', 'exam:examPaper:view', '#', 'admin', '2019-11-29 16:19:49', 'admin', '2019-11-29 16:41:23', '');
INSERT INTO `sys_menu` VALUES (2040, '搜索', 2038, 1, '#', 'F', '0', 'exam:examPaperCategory:list', '#', 'admin', '2019-11-29 16:34:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2041, '添加', 2038, 2, '#', 'F', '0', 'exam:examPaperCategory:add', '#', 'admin', '2019-11-29 16:34:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2042, '编辑', 2038, 3, '#', 'F', '0', 'exam:examPaperCategory:edit', '#', 'admin', '2019-11-29 16:35:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2043, '删除', 2038, 4, '#', 'F', '0', 'exam:examPaperCategory:remove', '#', 'admin', '2019-11-29 16:35:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2044, '搜索', 2039, 1, '#', 'F', '0', 'exam:examPaper:list', '#', 'admin', '2019-11-29 16:42:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2045, '添加', 2039, 2, '#', 'F', '0', 'exam:examPaper:add', '#', 'admin', '2019-11-29 16:43:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2046, '修改', 2039, 3, '#', 'F', '0', 'exam:examPaper:edit', '#', 'admin', '2019-11-29 16:44:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2047, '删除', 2039, 4, '#', 'F', '0', 'exam:examPaperCategory:remove', '#', 'admin', '2019-11-29 16:47:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2048, '导出', 2039, 5, '#', 'F', '0', 'exam:examPaperCategory:export', '#', 'admin', '2019-11-29 16:48:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2049, '保存试题', 2039, 6, '#', 'F', '0', 'exam:examPaper:add', '#', 'admin', '2019-11-29 16:54:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2050, '搜索', 2009, 1, '#', 'F', '0', 'exam:examPractice:list', '#', 'admin', '2019-11-29 16:42:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2051, '添加', 2009, 2, '#', 'F', '0', 'exam:examPractice:add', '#', 'admin', '2019-11-29 16:43:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2052, '修改', 2009, 3, '#', 'F', '0', 'exam:examPractice:edit', '#', 'admin', '2019-11-29 16:44:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2053, '删除', 2009, 4, '#', 'F', '0', 'exam:examPractice:remove', '#', 'admin', '2019-11-29 16:47:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2054, '导出', 2009, 5, '#', 'F', '0', 'exam:examPractice:export', '#', 'admin', '2019-11-29 16:48:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2055, '保存试题', 2009, 6, '#', 'F', '0', 'exam:examPractice:add', '#', 'admin', '2019-11-29 16:54:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2056, '搜索', 2010, 1, '#', 'F', '0', 'exam:examExamination:list', '#', 'admin', '2019-11-29 16:42:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2057, '添加', 2010, 2, '#', 'F', '0', 'exam:examExamination:add', '#', 'admin', '2019-11-29 16:43:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2058, '修改', 2010, 3, '#', 'F', '0', 'exam:examExamination:edit', '#', 'admin', '2019-11-29 16:44:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2059, '删除', 2010, 4, '#', 'F', '0', 'exam:examExamination:remove', '#', 'admin', '2019-11-29 16:47:39', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 骏聪信息科技新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-28 11:07:02', '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 骏聪信息科技系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-28 11:07:08', '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 356 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (100, '课程分类管理', 1, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.addSave()', 1, 'admin', '研发部门', '/train/course/category/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"100\" ],\r\n  \"name\" : [ \"语文\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Incorrect integer value: \'null,100\' for column \'parent_ids\' at row 1\r\n### The error may involve com.ruoyi.train.course.mapper.TrainCourseCategoryMapper.insertCategory-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into train_course_category(            parent_id,       name,       parent_ids,       order_num,        create_by,      create_time    )values(            ?,       ?,       ?,       ?,        ?,      sysdate()    )\r\n### Cause: java.sql.SQLException: Incorrect integer value: \'null,100\' for column \'parent_ids\' at row 1\n; uncategorized SQLException; SQL state [HY000]; error code [1366]; Incorrect integer value: \'null,100\' for column \'parent_ids\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'null,100\' for column \'parent_ids\' at row 1', '2019-11-27 14:59:21');
INSERT INTO `sys_oper_log` VALUES (101, '课程分类管理', 1, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.addSave()', 1, 'admin', '研发部门', '/train/course/category/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"100\" ],\r\n  \"name\" : [ \"语文\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:05:09');
INSERT INTO `sys_oper_log` VALUES (102, '课程分类管理', 1, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.addSave()', 1, 'admin', '研发部门', '/train/course/category/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"100\" ],\r\n  \"name\" : [ \"数学\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:15:42');
INSERT INTO `sys_oper_log` VALUES (103, '课程分类管理', 1, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.addSave()', 1, 'admin', '研发部门', '/train/course/category/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"100\" ],\r\n  \"name\" : [ \"英语\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:17:05');
INSERT INTO `sys_oper_log` VALUES (104, '课程分类管理', 1, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.addSave()', 1, 'admin', '研发部门', '/train/course/category/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"101\" ],\r\n  \"name\" : [ \"拼音\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:17:44');
INSERT INTO `sys_oper_log` VALUES (105, '课程分类管理', 1, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.addSave()', 1, 'admin', '研发部门', '/train/course/category/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"101\" ],\r\n  \"name\" : [ \"错别字\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:18:07');
INSERT INTO `sys_oper_log` VALUES (106, '课程分类管理', 2, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.editSave()', 1, 'admin', '研发部门', '/train/course/category/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"104\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"语文\" ],\r\n  \"name\" : [ \"选择\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:19:15');
INSERT INTO `sys_oper_log` VALUES (107, '课程分类管理', 2, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.editSave()', 1, 'admin', '研发部门', '/train/course/category/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"104\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"语文\" ],\r\n  \"name\" : [ \"单选题\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:19:27');
INSERT INTO `sys_oper_log` VALUES (108, '课程分类管理', 2, 'com.ruoyi.train.course.controller.TrainCourseCategoryController.editSave()', 1, 'admin', '研发部门', '/train/course/category/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"105\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"语文\" ],\r\n  \"name\" : [ \"多选题\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"delFlag\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-27 15:19:33');
INSERT INTO `sys_oper_log` VALUES (109, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'syntax error, expect {, actual EOF, pos 0', '2019-11-27 15:22:49');
INSERT INTO `sys_oper_log` VALUES (110, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'syntax error, expect {, actual EOF, pos 0', '2019-11-27 15:27:21');
INSERT INTO `sys_oper_log` VALUES (111, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'syntax error, expect {, actual int, pos 0', '2019-11-27 15:27:44');
INSERT INTO `sys_oper_log` VALUES (112, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'syntax error, expect {, actual int, pos 0', '2019-11-27 15:33:24');
INSERT INTO `sys_oper_log` VALUES (113, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'syntax error, expect {, actual error, pos 0', '2019-11-27 15:34:10');
INSERT INTO `sys_oper_log` VALUES (114, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'syntax error, expect {, actual error, pos 0', '2019-11-27 15:47:17');
INSERT INTO `sys_oper_log` VALUES (115, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'error parse true', '2019-11-27 15:48:22');
INSERT INTO `sys_oper_log` VALUES (116, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, 'error parse true', '2019-11-27 15:48:36');
INSERT INTO `sys_oper_log` VALUES (117, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, '上传文件失败，请核对七牛配置信息', '2019-11-27 15:58:03');
INSERT INTO `sys_oper_log` VALUES (118, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'exam.sys_oss\' doesn\'t exist\r\n### The error may involve com.ruoyi.system.mapper.SysOssMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_oss  ( file_name,file_suffix,url,create_time,create_by,service ) VALUES( ?,?,?,?,?,? )\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'exam.sys_oss\' doesn\'t exist\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table \'exam.sys_oss\' doesn\'t exist', '2019-11-27 16:03:23');
INSERT INTO `sys_oper_log` VALUES (119, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 0, NULL, '2019-11-27 16:12:46');
INSERT INTO `sys_oper_log` VALUES (120, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 0, NULL, '2019-11-27 16:19:40');
INSERT INTO `sys_oper_log` VALUES (121, '课程', 1, 'com.ruoyi.train.course.controller.TrainCourseController.addSave()', 1, 'admin', '研发部门', '/train/trainCourse/add', '127.0.0.1', '内网IP', '{\r\n  \"trainCourseCategoryId\" : [ \"102\" ],\r\n  \"treeName\" : [ \"数学\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"price\" : [ \"0\" ],\r\n  \"cover\" : [ \"https://nrqiniu.tairanmall.com///20191127/bd30a2544d0d4036a3bd4e8e40dcc903.jpg\" ],\r\n  \"description\" ', 0, NULL, '2019-11-27 16:19:52');
INSERT INTO `sys_oper_log` VALUES (122, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 0, NULL, '2019-11-27 16:26:21');
INSERT INTO `sys_oper_log` VALUES (123, '课程', 1, 'com.ruoyi.train.course.controller.TrainCourseController.addSave()', 1, 'admin', '研发部门', '/train/trainCourse/add', '127.0.0.1', '内网IP', '{\r\n  \"trainCourseCategoryId\" : [ \"102\" ],\r\n  \"treeName\" : [ \"数学\" ],\r\n  \"name\" : [ \"10以内乘除法\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"price\" : [ \"0\" ],\r\n  \"cover\" : [ \"https://nrqiniu.tairanmall.com///20191127/68794a3b86ca452e80df8d0057e98b3f.jpg\" ],\r\n  \"description\" ', 0, NULL, '2019-11-27 16:26:24');
INSERT INTO `sys_oper_log` VALUES (124, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-27 16:34:05');
INSERT INTO `sys_oper_log` VALUES (125, '课程章节', 1, 'com.ruoyi.train.course.controller.TrainCourseSectionController.addSave()', 1, 'admin', '研发部门', '/train/trainCourseSection/add', '127.0.0.1', '内网IP', '{\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加法\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"courseware\" : [ \"https://nrqiniu.tairanmall.com///20191127/2f97ecafaeda4adda6edec2d806c8a4e.docx\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"remark\" : [ \"10以内加法\" ]\r\n}', 0, NULL, '2019-11-27 16:34:12');
INSERT INTO `sys_oper_log` VALUES (126, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-27 16:38:14');
INSERT INTO `sys_oper_log` VALUES (127, '课程章节', 1, 'com.ruoyi.train.course.controller.TrainCourseSectionController.addSave()', 1, 'admin', '研发部门', '/train/trainCourseSection/add', '127.0.0.1', '内网IP', '{\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内减法\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"courseware\" : [ \"https://nrqiniu.tairanmall.com///20191127/7e7077a8ce3b40c7948e48c6df82a56e.mp4\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"remark\" : [ \"10以内减法\" ]\r\n}', 0, NULL, '2019-11-27 16:38:15');
INSERT INTO `sys_oper_log` VALUES (128, '课程章节', 1, 'com.ruoyi.train.course.controller.TrainCourseSectionController.addSave()', 1, 'admin', '研发部门', '/train/trainCourseSection/add', '127.0.0.1', '内网IP', '{\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内减法\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"courseware\" : [ \"https://nrqiniu.tairanmall.com///20191127/7e7077a8ce3b40c7948e48c6df82a56e.mp4\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"remark\" : [ \"10以内减法\" ]\r\n}', 0, NULL, '2019-11-27 16:38:15');
INSERT INTO `sys_oper_log` VALUES (129, '课程章节', 3, 'com.ruoyi.train.course.controller.TrainCourseSectionController.remove()', 1, 'admin', '研发部门', '/train/trainCourseSection/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', 0, NULL, '2019-11-27 16:38:22');
INSERT INTO `sys_oper_log` VALUES (130, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-27 16:45:32');
INSERT INTO `sys_oper_log` VALUES (131, '课程章节', 1, 'com.ruoyi.train.course.controller.TrainCourseSectionController.addSave()', 1, 'admin', '研发部门', '/train/trainCourseSection/add', '127.0.0.1', '内网IP', '{\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加法\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"courseware\" : [ \"https://nrqiniu.tairanmall.com///20191127/f9bd9fc2722c486e8f6c864b0c0b18c4.pptx\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-27 16:45:36');
INSERT INTO `sys_oper_log` VALUES (132, '课程章节', 2, 'com.ruoyi.train.course.controller.TrainCourseSectionController.editSave()', 1, 'admin', '研发部门', '/train/trainCourseSection/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内减法\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"courseware\" : [ \"https://nrqiniu.tairanmall.com///20191127/7e7077a8ce3b40c7948e48c6df82a56e.mp4\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"remark\" : [ \"10以内减法\" ]\r\n', 0, NULL, '2019-11-27 16:45:45');
INSERT INTO `sys_oper_log` VALUES (133, '试题分类', 1, 'com.ruoyi.exam.controller.ExamQuestionCategoryController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestionCategory/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"name\" : [ \"选择题\" ],\r\n  \"remarks\" : [ \"选择题分单选，多选\" ]\r\n}', 0, NULL, '2019-11-27 17:01:12');
INSERT INTO `sys_oper_log` VALUES (134, '试题分类', 1, 'com.ruoyi.exam.controller.ExamQuestionCategoryController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestionCategory/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2\" ],\r\n  \"name\" : [ \"单选题\" ],\r\n  \"remarks\" : [ \"单选题\" ]\r\n}', 0, NULL, '2019-11-27 17:01:25');
INSERT INTO `sys_oper_log` VALUES (135, '试题分类', 1, 'com.ruoyi.exam.controller.ExamQuestionCategoryController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestionCategory/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2\" ],\r\n  \"name\" : [ \"多选题\" ],\r\n  \"remarks\" : [ \"多选题\" ]\r\n}', 0, NULL, '2019-11-27 17:01:38');
INSERT INTO `sys_oper_log` VALUES (136, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestion/add', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"1+1=\" ],\r\n  \"number\" : [ \"A\", \"B\", \"C\", \"D\" ],\r\n  \"content\" : [ \"1\", \"2\", \"3\", \"4\" ],\r\n  \"answer\" : [ \"B\" ],\r\n  \"remarks\" : [ \"1+1=2\" ]\r\n}', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Incorrect integer value: \'A\' for column \'number\' at row 1\r\n### The error may involve com.ruoyi.exam.mapper.ExamQuestionItemMapper.insertExamQuestionItem-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into exam_question_item    ( content,    number,    exam_question_id,    create_by,    create_date )           values ( ?,    ?,    ?,    ?,    ? )\r\n### Cause: java.sql.SQLException: Incorrect integer value: \'A\' for column \'number\' at row 1\n; uncategorized SQLException; SQL state [HY000]; error code [1366]; Incorrect integer value: \'A\' for column \'number\' at row 1; nested exception is java.sql.SQLException: Incorrect integer value: \'A\' for column \'number\' at row 1', '2019-11-27 17:04:28');
INSERT INTO `sys_oper_log` VALUES (137, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestion/add', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"1+1=\" ],\r\n  \"number\" : [ \"A\", \"B\", \"C\", \"D\" ],\r\n  \"content\" : [ \"1\", \"2\", \"3\", \"4\" ],\r\n  \"answer\" : [ \"B\" ],\r\n  \"remarks\" : [ \"1+1=2\" ]\r\n}', 0, NULL, '2019-11-27 17:06:49');
INSERT INTO `sys_oper_log` VALUES (138, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestion/add', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"1+2=\" ],\r\n  \"number\" : [ \"A\", \"B\", \"C\", \"D\" ],\r\n  \"content\" : [ \"1\", \"2\", \"3\", \"4\" ],\r\n  \"answer\" : [ \"C\" ],\r\n  \"remarks\" : [ \"1+2=3\" ]\r\n}', 0, NULL, '2019-11-27 17:07:12');
INSERT INTO `sys_oper_log` VALUES (139, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ]\r\n}', 1, '', '2019-11-27 17:07:24');
INSERT INTO `sys_oper_log` VALUES (140, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestion/add', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"4\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"title\" : [ \"1+2=\" ],\r\n  \"number\" : [ \"A\", \"B\", \"C\", \"D\" ],\r\n  \"content\" : [ \"1\", \"2\", \"3\", \"3\" ],\r\n  \"answer\" : [ \"C\", \"D\" ],\r\n  \"remarks\" : [ \"1+2=3\" ]\r\n}', 0, NULL, '2019-11-27 17:11:53');
INSERT INTO `sys_oper_log` VALUES (141, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ]\r\n}', 1, '', '2019-11-27 17:13:22');
INSERT INTO `sys_oper_log` VALUES (142, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ]\r\n}', 1, '', '2019-11-27 17:14:33');
INSERT INTO `sys_oper_log` VALUES (143, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ]\r\n}', 1, '', '2019-11-27 17:16:22');
INSERT INTO `sys_oper_log` VALUES (144, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ]\r\n}', 1, '', '2019-11-27 17:25:04');
INSERT INTO `sys_oper_log` VALUES (145, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ]\r\n}', 0, NULL, '2019-11-27 17:25:33');
INSERT INTO `sys_oper_log` VALUES (146, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.update()', 1, 'admin', '研发部门', '/exam/examQuestion/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"questionAnswer\" : [ \"B\" ],\r\n  \"item\" : [ \"[{\\\"examQuestionId\\\":\\\"2\\\",\\\"number\\\":\\\"A\\\",\\\"createBy\\\":\\\"admin\\\",\\\"updateDate\\\":1574845609000,\\\"id\\\":\\\"1\\\",\\\"delFlag\\\":\\\"0\\\",\\\"content\\\":\\', 0, NULL, '2019-11-27 17:27:15');
INSERT INTO `sys_oper_log` VALUES (147, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.remove()', 1, 'admin', '研发部门', '/exam/examQuestion/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2,3,5\" ]\r\n}', 0, NULL, '2019-11-27 17:31:27');
INSERT INTO `sys_oper_log` VALUES (148, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ]\r\n}', 0, NULL, '2019-11-27 17:32:04');
INSERT INTO `sys_oper_log` VALUES (149, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestion/add', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"1+8=\" ],\r\n  \"number\" : [ \"A\", \"B\", \"C\", \"D\" ],\r\n  \"content\" : [ \"7\", \"8\", \"9\", \"10\" ],\r\n  \"answer\" : [ \"C\" ],\r\n  \"remarks\" : [ \"1+8=9\" ]\r\n}', 0, NULL, '2019-11-27 17:33:33');
INSERT INTO `sys_oper_log` VALUES (150, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestion/add', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"title\" : [ \"1+9\" ],\r\n  \"number\" : [ \"A\", \"B\", \"C\", \"D\" ],\r\n  \"content\" : [ \"7\", \"8\", \"9\", \"10\" ],\r\n  \"answer\" : [ \"D\" ],\r\n  \"remarks\" : [ \"1+9 =10\" ]\r\n}', 0, NULL, '2019-11-27 17:37:38');
INSERT INTO `sys_oper_log` VALUES (151, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.update()', 1, 'admin', '研发部门', '/exam/examQuestion/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"13\" ],\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"questionAnswer\" : [ \"D\" ],\r\n  \"item\" : [ \"[{\\\"examQuestionId\\\":\\\"13\\\",\\\"number\\\":\\\"A\\\",\\\"createBy\\\":\\\"admin\\\",\\\"updateDate\\\":1574847458000,\\\"id\\\":\\\"49\\\",\\\"delFlag\\\":\\\"0\\\",\\\"content\\', 0, NULL, '2019-11-27 17:41:44');
INSERT INTO `sys_oper_log` VALUES (152, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.update()', 1, 'admin', '研发部门', '/exam/examQuestion/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"13\" ],\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"questionAnswer\" : [ \"D\" ],\r\n  \"item\" : [ \"[{\\\"examQuestionId\\\":\\\"13\\\",\\\"number\\\":\\\"A\\\",\\\"createBy\\\":\\\"admin\\\",\\\"updateDate\\\":1574847703000,\\\"id\\\":\\\"53\\\",\\\"delFlag\\\":\\\"0\\\",\\\"content\\', 0, NULL, '2019-11-27 17:44:33');
INSERT INTO `sys_oper_log` VALUES (153, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.update()', 1, 'admin', '研发部门', '/exam/examQuestion/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"13\" ],\r\n  \"categoryId\" : [ \"3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"questionAnswer\" : [ \"D\" ],\r\n  \"item\" : [ \"[{\\\"examQuestionId\\\":\\\"13\\\",\\\"number\\\":\\\"A\\\",\\\"createBy\\\":\\\"admin\\\",\\\"updateDate\\\":1574847873000,\\\"id\\\":\\\"57\\\",\\\"delFlag\\\":\\\"0\\\",\\\"content\\', 0, NULL, '2019-11-27 17:46:06');
INSERT INTO `sys_oper_log` VALUES (154, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"4\" ]\r\n}', 0, NULL, '2019-11-27 17:52:00');
INSERT INTO `sys_oper_log` VALUES (155, '试题分类', 1, 'com.ruoyi.exam.controller.ExamQuestionCategoryController.addSave()', 1, 'admin', '研发部门', '/exam/examQuestionCategory/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"name\" : [ \"判断题\" ],\r\n  \"remarks\" : [ \"判断题\" ]\r\n}', 0, NULL, '2019-11-27 17:52:36');
INSERT INTO `sys_oper_log` VALUES (156, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"5\" ]\r\n}', 0, NULL, '2019-11-27 17:52:55');
INSERT INTO `sys_oper_log` VALUES (157, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.update()', 1, 'admin', '研发部门', '/exam/examQuestion/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"20\" ],\r\n  \"categoryId\" : [ \"5\" ],\r\n  \"type\" : [ \"3\" ],\r\n  \"questionAnswer\" : [ \"错误\" ],\r\n  \"item\" : [ \"[{\\\"examQuestionId\\\":\\\"20\\\",\\\"number\\\":\\\"A\\\",\\\"createBy\\\":\\\"admin\\\",\\\"updateDate\\\":1574848375000,\\\"updateBy\\\":\\\"admin\\\",\\\"id\\\":\\\"101\\\",\\\"d', 0, NULL, '2019-11-27 17:53:42');
INSERT INTO `sys_oper_log` VALUES (158, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.remove()', 1, 'admin', '研发部门', '/exam/examQuestion/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"20,21,22,23,24,25\" ]\r\n}', 0, NULL, '2019-11-27 17:54:27');
INSERT INTO `sys_oper_log` VALUES (159, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.importfile()', 1, 'admin', '研发部门', '/exam/examQuestion/importfile', '127.0.0.1', '内网IP', '{\r\n  \"categoryId\" : [ \"5\" ]\r\n}', 0, NULL, '2019-11-27 17:54:33');
INSERT INTO `sys_oper_log` VALUES (160, '试卷分类', 1, 'com.ruoyi.exam.controller.ExamPaperCategoryController.addSave()', 1, 'admin', '研发部门', '/exam/examPaperCategory/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"name\" : [ \"A卷\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"remarks\" : [ \"A卷\" ]\r\n}', 0, NULL, '2019-11-27 17:56:58');
INSERT INTO `sys_oper_log` VALUES (161, '试卷分类', 1, 'com.ruoyi.exam.controller.ExamPaperCategoryController.addSave()', 1, 'admin', '研发部门', '/exam/examPaperCategory/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"name\" : [ \"B卷\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"remarks\" : [ \"B卷\" ]\r\n}', 0, NULL, '2019-11-27 17:57:10');
INSERT INTO `sys_oper_log` VALUES (162, '试卷分类', 1, 'com.ruoyi.exam.controller.ExamPaperCategoryController.addSave()', 1, 'admin', '研发部门', '/exam/examPaperCategory/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"name\" : [ \"C卷\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"remarks\" : [ \"C卷\" ]\r\n}', 0, NULL, '2019-11-27 17:57:19');
INSERT INTO `sys_oper_log` VALUES (163, '试卷', 1, 'com.ruoyi.exam.controller.ExamPaperController.addSave()', 1, 'admin', '研发部门', '/exam/examPaper/add', '127.0.0.1', '内网IP', '{\r\n  \"examPaperCategoryId\" : [ \"2\" ],\r\n  \"name\" : [ \"A卷\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"choiceNumber\" : [ \"0\" ],\r\n  \"moreChoiceNumber\" : [ \"0\" ],\r\n  \"judgeNumber\" : [ \"0\" ],\r\n  \"remarks\" : [ \"A卷\" ]\r\n}', 0, NULL, '2019-11-27 18:02:48');
INSERT INTO `sys_oper_log` VALUES (164, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPaper/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"6\", \"8\", \"10\", \"11\" ],\r\n  \"paperId\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-27 18:03:51');
INSERT INTO `sys_oper_log` VALUES (165, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPaper/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"6\", \"8\", \"10\", \"11\", \"15\", \"16\", \"18\", \"19\" ],\r\n  \"paperId\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-27 18:04:37');
INSERT INTO `sys_oper_log` VALUES (166, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPaper/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"6\", \"8\", \"10\", \"11\", \"15\", \"16\", \"18\", \"19\", \"26\", \"27\" ],\r\n  \"paperId\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-27 18:04:48');
INSERT INTO `sys_oper_log` VALUES (167, '部门管理', 3, 'com.ruoyi.exam.controller.ExamPaperCategoryController.remove()', 1, 'admin', '研发部门', '/exam/examPaperCategory/remove/4', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-11-27 18:05:24');
INSERT INTO `sys_oper_log` VALUES (168, '试卷分类', 2, 'com.ruoyi.exam.controller.ExamPaperCategoryController.editSave()', 1, 'admin', '研发部门', '/exam/examPaperCategory/edit', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"id\" : [ \"2\" ],\r\n  \"name\" : [ \"固定试卷类型\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"remarks\" : [ \"固定试卷类型\" ]\r\n}', 0, NULL, '2019-11-27 18:05:38');
INSERT INTO `sys_oper_log` VALUES (169, '试卷分类', 2, 'com.ruoyi.exam.controller.ExamPaperCategoryController.editSave()', 1, 'admin', '研发部门', '/exam/examPaperCategory/edit', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"id\" : [ \"3\" ],\r\n  \"name\" : [ \"非固定试卷类型\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"remarks\" : [ \"非固定试卷类型\" ]\r\n}', 0, NULL, '2019-11-27 18:05:48');
INSERT INTO `sys_oper_log` VALUES (170, '试卷', 1, 'com.ruoyi.exam.controller.ExamPaperController.addSave()', 1, 'admin', '研发部门', '/exam/examPaper/add', '127.0.0.1', '内网IP', '{\r\n  \"examPaperCategoryId\" : [ \"3\" ],\r\n  \"name\" : [ \"B卷\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"choiceNumber\" : [ \"4\" ],\r\n  \"moreChoiceNumber\" : [ \"4\" ],\r\n  \"judgeNumber\" : [ \"2\" ],\r\n  \"remarks\" : [ \"非固定试卷类型\" ]\r\n}', 0, NULL, '2019-11-27 18:07:27');
INSERT INTO `sys_oper_log` VALUES (171, '试卷', 2, 'com.ruoyi.exam.controller.ExamPaperController.editSave()', 1, 'admin', '研发部门', '/exam/examPaper/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"name\" : [ \"A卷\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"choiceNumber\" : [ \"0\" ],\r\n  \"moreChoiceNumber\" : [ \"0\" ],\r\n  \"judgeNumber\" : [ \"0\" ],\r\n  \"remarks\" : [ \"固定试卷类型\" ]\r\n}', 0, NULL, '2019-11-27 18:07:45');
INSERT INTO `sys_oper_log` VALUES (172, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPaper/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\" ],\r\n  \"paperId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-27 18:07:55');
INSERT INTO `sys_oper_log` VALUES (173, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPaper/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\" ],\r\n  \"paperId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-27 18:08:27');
INSERT INTO `sys_oper_log` VALUES (174, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPaper/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"26\", \"27\", \"28\", \"29\", \"30\", \"31\" ],\r\n  \"paperId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-27 18:08:35');
INSERT INTO `sys_oper_log` VALUES (175, '练习', 1, 'com.ruoyi.exam.controller.ExamPracticeController.addSave()', 1, 'admin', '研发部门', '/exam/examPractice/add', '127.0.0.1', '内网IP', '{\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"trainCateGoryName\" : [ \"数学\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"0\" ],\r\n  \"startTime\" : [ \"\" ],\r\n  \"endTime\" : [ \"\" ],\r\n  \"practiceUserLimit\" : [ \"1\" ],\r\n  \"remarks\" : [ \"10以内加减法，不控制时间，不限定练习对象\" ]\r\n}', 0, NULL, '2019-11-27 18:26:49');
INSERT INTO `sys_oper_log` VALUES (176, '练习', 1, 'com.ruoyi.exam.controller.ExamPracticeController.addSave()', 1, 'admin', '研发部门', '/exam/examPractice/add', '127.0.0.1', '内网IP', '{\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"trainCateGoryName\" : [ \"数学\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"1\" ],\r\n  \"startTime\" : [ \"2019-11-27 00:00:00\" ],\r\n  \"endTime\" : [ \"2019-11-30 23:59:59\" ],\r\n  \"practiceUserLimit\" : [ \"2\" ],\r\n  \"rem', 0, NULL, '2019-11-27 18:27:36');
INSERT INTO `sys_oper_log` VALUES (177, '练习', 2, 'com.ruoyi.exam.controller.ExamPracticeController.editSave()', 1, 'admin', '研发部门', '/exam/examPractice/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"1\" ],\r\n  \"startTime\" : [ \"2019-11-27 12:00:00\" ],\r\n  \"endTime\" : [ \"2019-11-30 11:59:59\"', 0, NULL, '2019-11-27 18:28:01');
INSERT INTO `sys_oper_log` VALUES (178, '试卷', 3, 'com.ruoyi.exam.controller.ExamPracticeController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPractice/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"26\", \"27\", \"28\", \"29\", \"30\", \"31\" ],\r\n  \"practiceId\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-27 18:28:24');
INSERT INTO `sys_oper_log` VALUES (179, '试卷', 3, 'com.ruoyi.exam.controller.ExamPracticeController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPractice/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\" ],\r\n  \"practiceId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-27 18:28:49');
INSERT INTO `sys_oper_log` VALUES (180, '试卷', 3, 'com.ruoyi.exam.controller.ExamPracticeController.addQuestionForModel()', 1, 'admin', '研发部门', '/exam/examPractice/addQuestionForModel', '127.0.0.1', '内网IP', '{\r\n  \"questionId[]\" : [ \"11\", \"12\", \"13\", \"14\", \"4\", \"6\", \"7\", \"8\", \"9\", \"10\", \"15\", \"16\", \"17\", \"18\", \"19\", \"26\", \"27\", \"28\", \"29\", \"30\", \"31\" ],\r\n  \"practiceId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-27 18:29:02');
INSERT INTO `sys_oper_log` VALUES (181, '练习', 2, 'com.ruoyi.exam.controller.ExamPracticeController.editSave()', 1, 'admin', '研发部门', '/exam/examPractice/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"1\" ],\r\n  \"startTime\" : [ \"2019-11-27 06:29:26\" ],\r\n  \"endTime\" : [ \"2019-11-30 11:59:59\"', 0, NULL, '2019-11-27 18:29:33');
INSERT INTO `sys_oper_log` VALUES (182, '课程章节', 3, 'com.ruoyi.train.course.controller.TrainCourseSectionController.remove()', 1, 'admin', '研发部门', '/train/trainCourseSection/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-28 09:34:51');
INSERT INTO `sys_oper_log` VALUES (183, '课程章节', 3, 'com.ruoyi.train.course.controller.TrainCourseSectionController.remove()', 1, 'admin', '研发部门', '/train/trainCourseSection/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', 0, NULL, '2019-11-28 09:34:54');
INSERT INTO `sys_oper_log` VALUES (184, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:10');
INSERT INTO `sys_oper_log` VALUES (185, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:10');
INSERT INTO `sys_oper_log` VALUES (186, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:10');
INSERT INTO `sys_oper_log` VALUES (187, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:10');
INSERT INTO `sys_oper_log` VALUES (188, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:10');
INSERT INTO `sys_oper_log` VALUES (189, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:11');
INSERT INTO `sys_oper_log` VALUES (190, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:29');
INSERT INTO `sys_oper_log` VALUES (191, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:33');
INSERT INTO `sys_oper_log` VALUES (192, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:35');
INSERT INTO `sys_oper_log` VALUES (193, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '研发部门', '/upload/oss', '127.0.0.1', '内网IP', '{\r\n  \"module\" : [ \"train/courseware\" ]\r\n}', 0, NULL, '2019-11-28 09:45:36');
INSERT INTO `sys_oper_log` VALUES (194, '课程章节', 1, 'com.ruoyi.train.course.controller.TrainCourseSectionController.addSave()', 1, 'admin', '研发部门', '/train/trainCourseSection/add', '127.0.0.1', '内网IP', '{\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"courseware\" : [ \"https://nrqiniu.tairanmall.com///20191128/9eb470eab7e84527a05c802c97b032a0.jpg,https://nrqiniu.tairanmall.com///20191128/ab6c87f0acaa4858a9163da0218a7cd2.', 0, NULL, '2019-11-28 09:45:40');
INSERT INTO `sys_oper_log` VALUES (195, '课程章节', 3, 'com.ruoyi.train.course.controller.TrainCourseSectionController.remove()', 1, 'admin', '研发部门', '/train/trainCourseSection/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', 0, NULL, '2019-11-28 09:46:23');
INSERT INTO `sys_oper_log` VALUES (196, '课程', 3, 'com.ruoyi.train.course.controller.TrainCourseController.remove()', 1, 'admin', '研发部门', '/train/trainCourse/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-28 09:46:32');
INSERT INTO `sys_oper_log` VALUES (197, '考试', 1, 'com.ruoyi.exam.controller.ExamExaminationController.addSave()', 1, 'admin', '研发部门', '/exam/examExamination/add', '127.0.0.1', '内网IP', '{\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"固定试卷类型\" ],\r\n  \"examPaperId\" : [ \"1\" ],\r\n  \"trainCateGoryName\" : [ \"数学\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"0\" ],\r\n  \"startTime\" : [ \"\" ],\r\n  \"endTime\" : [ \"\" ],\r', 0, NULL, '2019-11-28 09:47:54');
INSERT INTO `sys_oper_log` VALUES (198, '考试', 1, 'com.ruoyi.exam.controller.ExamExaminationController.addSave()', 1, 'admin', '研发部门', '/exam/examExamination/add', '127.0.0.1', '内网IP', '{\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"非固定试卷类型\" ],\r\n  \"examPaperId\" : [ \"2\" ],\r\n  \"trainCateGoryName\" : [ \"数学\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"enableControlTime\" : [ \"1\" ],\r\n  \"startTime\" : [ \"2019-11-28 00:00:00\" ],\r\n  ', 0, NULL, '2019-11-28 09:49:04');
INSERT INTO `sys_oper_log` VALUES (199, '课程', 2, 'com.ruoyi.train.course.controller.TrainCourseController.editSave()', 1, 'admin', '研发部门', '/train/trainCourse/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"trainCourseCategoryId\" : [ \"102\" ],\r\n  \"treeName\" : [ \"数学\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"price\" : [ \"0.00\" ],\r\n  \"cover\" : [ \"https://nrqiniu.tairanmall.com///20191127/bd30a2544d0d4036a3bd4e8e40dcc903.jpg', 0, NULL, '2019-11-28 10:04:17');
INSERT INTO `sys_oper_log` VALUES (200, '通知公告', 2, 'com.ruoyi.web.controller.system.SysNoticeController.editSave()', 1, 'admin', '信息中心', '/system/notice/edit', '127.0.0.1', '内网IP', '{\r\n  \"noticeId\" : [ \"1\" ],\r\n  \"noticeTitle\" : [ \"温馨提醒：2018-07-01 骏聪信息科技新版本发布啦\" ],\r\n  \"noticeType\" : [ \"2\" ],\r\n  \"noticeContent\" : [ \"新版本内容\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-28 11:07:02');
INSERT INTO `sys_oper_log` VALUES (201, '通知公告', 2, 'com.ruoyi.web.controller.system.SysNoticeController.editSave()', 1, 'admin', '信息中心', '/system/notice/edit', '127.0.0.1', '内网IP', '{\r\n  \"noticeId\" : [ \"2\" ],\r\n  \"noticeTitle\" : [ \"维护通知：2018-07-01 骏聪信息科技系统凌晨维护\" ],\r\n  \"noticeType\" : [ \"1\" ],\r\n  \"noticeContent\" : [ \"维护内容\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-28 11:07:08');
INSERT INTO `sys_oper_log` VALUES (202, '练习', 2, 'com.ruoyi.exam.controller.ExamPracticeController.editSave()', 1, 'admin', '信息中心', '/exam/examPractice/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法(无时间限制)\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"0\" ],\r\n  \"startTime\" : [ \"2019-11-27 06:28:36\" ],\r\n  \"endTime\" : [ \"2019-11-27 06', 0, NULL, '2019-11-28 13:23:18');
INSERT INTO `sys_oper_log` VALUES (203, '用户管理', 1, 'com.ruoyi.web.controller.system.SysUserController.addSave()', 1, 'admin', '信息中心', '/system/user/add', '192.168.0.163', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"loginName\" : [ \"java开发\" ],\r\n  \"userName\" : [ \"java开发\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"email\" : [ \"123@163.com\" ],\r\n  \"phonenumber\" : [ \"18798765432\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r', 0, NULL, '2019-11-28 13:29:05');
INSERT INTO `sys_oper_log` VALUES (204, '考试', 2, 'com.ruoyi.exam.controller.ExamExaminationController.editSave()', 1, 'admin', '信息中心', '/exam/examExamination/edit', '192.168.0.163', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"paperId\" : [ \"2\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"试卷分类\" ],\r\n  \"examPaperId\" : [ \"2\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"enableContr', 0, NULL, '2019-11-28 13:32:06');
INSERT INTO `sys_oper_log` VALUES (205, '课程', 2, 'com.ruoyi.train.course.controller.TrainCourseController.editSave()', 1, 'admin', '信息中心', '/train/trainCourse/edit', '192.168.0.163', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"trainCourseCategoryId\" : [ \"102\" ],\r\n  \"treeName\" : [ \"数学\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"state\" : [ \"2\" ],\r\n  \"price\" : [ \"0.00\" ],\r\n  \"cover\" : [ \"https://nrqiniu.tairanmall.com///20191127/bd30a2544d0d4036a3bd4e8e40dcc903.jpg', 0, NULL, '2019-11-28 13:33:34');
INSERT INTO `sys_oper_log` VALUES (206, '练习', 2, 'com.ruoyi.exam.controller.ExamPracticeController.editSave()', 1, 'admin', '信息中心', '/exam/examPractice/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"1\" ],\r\n  \"startTime\" : [ \"2019-11-28 01:30:58\" ],\r\n  \"endTime\" : [ \"2019-11-30 11:59:59\"', 0, NULL, '2019-11-28 13:33:49');
INSERT INTO `sys_oper_log` VALUES (207, '个人信息修改', 2, 'com.ruoyi.cms.controller.CmsUserController.update()', 1, 'admin', '信息中心', '/web/user/update', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"remark\" : [ \"管理员1\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ]\r\n}', 0, NULL, '2019-11-28 17:01:18');
INSERT INTO `sys_oper_log` VALUES (208, '个人信息修改', 2, 'com.ruoyi.cms.controller.CmsUserController.update()', 1, NULL, NULL, '/web/user/update', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"remark\" : [ \"管理员1\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ]\r\n}', 1, '', '2019-11-28 17:02:04');
INSERT INTO `sys_oper_log` VALUES (209, '个人信息修改', 2, 'com.ruoyi.cms.controller.CmsUserController.update()', 1, 'admin', '信息中心', '/web/user/update', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"remark\" : [ \"管理员1\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ]\r\n}', 0, NULL, '2019-11-28 17:02:17');
INSERT INTO `sys_oper_log` VALUES (210, '练习', 2, 'com.ruoyi.exam.controller.ExamPracticeController.editSave()', 1, 'admin', '信息中心', '/exam/examPractice/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法(无时间限制)\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"enableControlTime\" : [ \"0\" ],\r\n  \"startTime\" : [ \"2019-11-27 06:28:36\" ],\r\n  \"endTime\" : [ \"2019-11-27 06', 0, NULL, '2019-11-28 17:03:26');
INSERT INTO `sys_oper_log` VALUES (211, '考试', 2, 'com.ruoyi.exam.controller.ExamExaminationController.editSave()', 1, 'admin', '信息中心', '/exam/examExamination/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"paperId\" : [ \"1\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"试卷分类\" ],\r\n  \"examPaperId\" : [ \"1\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"enableContr', 0, NULL, '2019-11-28 17:16:06');
INSERT INTO `sys_oper_log` VALUES (212, '考试', 2, 'com.ruoyi.exam.controller.ExamExaminationController.editSave()', 1, 'admin', '信息中心', '/exam/examExamination/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"2\" ],\r\n  \"paperId\" : [ \"2\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"试卷分类\" ],\r\n  \"examPaperId\" : [ \"2\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"enableContr', 0, NULL, '2019-11-28 17:51:18');
INSERT INTO `sys_oper_log` VALUES (213, '考试', 2, 'com.ruoyi.exam.controller.ExamExaminationController.editSave()', 1, 'admin', '信息中心', '/exam/examExamination/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"paperId\" : [ \"1\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"试卷分类\" ],\r\n  \"examPaperId\" : [ \"1\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"enableContr', 0, NULL, '2019-11-28 17:58:01');
INSERT INTO `sys_oper_log` VALUES (214, '考试', 2, 'com.ruoyi.exam.controller.ExamExaminationController.editSave()', 1, 'admin', '信息中心', '/exam/examExamination/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"paperId\" : [ \"1\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"试卷分类\" ],\r\n  \"examPaperId\" : [ \"1\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"enableContr', 0, NULL, '2019-11-28 18:05:49');
INSERT INTO `sys_oper_log` VALUES (215, '考试', 2, 'com.ruoyi.exam.controller.ExamExaminationController.editSave()', 1, 'admin', '信息中心', '/exam/examExamination/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"1\" ],\r\n  \"paperId\" : [ \"1\" ],\r\n  \"trainId\" : [ \"1\" ],\r\n  \"name\" : [ \"10以内加减法\" ],\r\n  \"treeName\" : [ \"试卷分类\" ],\r\n  \"examPaperId\" : [ \"1\" ],\r\n  \"trainCateGoryName\" : [ \"课程分类\" ],\r\n  \"trainCourseId\" : [ \"1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"enableContr', 0, NULL, '2019-11-28 18:10:26');
INSERT INTO `sys_oper_log` VALUES (216, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"培训管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:28:53');
INSERT INTO `sys_oper_log` VALUES (217, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"考试管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-pencil-square\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:29:22');
INSERT INTO `sys_oper_log` VALUES (218, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2000\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"培训管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-group\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:29:59');
INSERT INTO `sys_oper_log` VALUES (219, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"个人信息管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-address-book\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:30:52');
INSERT INTO `sys_oper_log` VALUES (220, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程分类\" ],\r\n  \"url\" : [ \"/train/course/category\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:32:20');
INSERT INTO `sys_oper_log` VALUES (221, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程分类\" ],\r\n  \"url\" : [ \"train/course/category\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:33:39');
INSERT INTO `sys_oper_log` VALUES (222, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程维护\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:34:32');
INSERT INTO `sys_oper_log` VALUES (223, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 1, 'admin', '信息中心', '/system/menu/remove/2003', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-11-29 09:34:48');
INSERT INTO `sys_oper_log` VALUES (224, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2002\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"个人信息\" ],\r\n  \"url\" : [ \"/system/user/profile\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:35:43');
INSERT INTO `sys_oper_log` VALUES (225, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"考试管理\" ],\r\n  \"url\" : [ \"/exam/examQuestion\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:38:47');
INSERT INTO `sys_oper_log` VALUES (226, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2007\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"题库维护\" ],\r\n  \"url\" : [ \"/exam/examQuestion\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:39:02');
INSERT INTO `sys_oper_log` VALUES (227, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试卷维护\" ],\r\n  \"url\" : [ \"/exam/examPaper\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:39:45');
INSERT INTO `sys_oper_log` VALUES (228, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"练习管理\" ],\r\n  \"url\" : [ \"/exam/examPractice\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:40:19');
INSERT INTO `sys_oper_log` VALUES (229, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"考试管理\" ],\r\n  \"url\" : [ \"/exam/examExamination\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:40:44');
INSERT INTO `sys_oper_log` VALUES (230, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统监控\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-video-camera\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:41:32');
INSERT INTO `sys_oper_log` VALUES (231, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"3\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统工具\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-bars\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:41:41');
INSERT INTO `sys_oper_log` VALUES (232, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"1\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-gear\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:41:58');
INSERT INTO `sys_oper_log` VALUES (233, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2001\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"考试管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-pencil-square\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:42:06');
INSERT INTO `sys_oper_log` VALUES (234, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2002\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"个人信息管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-address-book\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 09:42:15');
INSERT INTO `sys_oper_log` VALUES (235, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-29 09:44:44');
INSERT INTO `sys_oper_log` VALUES (236, '用户管理', 1, 'com.ruoyi.web.controller.system.SysUserController.addSave()', 1, 'admin', '信息中心', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"loginName\" : [ \"dev\" ],\r\n  \"userName\" : [ \"dev\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"email\" : [ \"471900500@qq.com\" ],\r\n  \"phonenumber\" : [ \"13000000000\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n', 0, NULL, '2019-11-29 09:46:00');
INSERT INTO `sys_oper_log` VALUES (237, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通角色\" ],\r\n  \"menuIds\" : [ \"2000,2004,2005,2001,2007,2008,2009,2010,2002,2006\" ]\r\n}', 0, NULL, '2019-11-29 09:47:12');
INSERT INTO `sys_oper_log` VALUES (238, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,1012,1013,', 0, NULL, '2019-11-29 09:47:32');
INSERT INTO `sys_oper_log` VALUES (239, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"dataScope\" : [ \"2\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 09:47:47');
INSERT INTO `sys_oper_log` VALUES (240, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '信息中心', '/upload/oss', '192.168.0.6', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 0, NULL, '2019-11-29 09:55:16');
INSERT INTO `sys_oper_log` VALUES (241, '个人信息修改', 2, 'com.ruoyi.cms.controller.CmsUserController.update()', 1, 'admin', '信息中心', '/web/user/update', '192.168.0.6', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"avatar\" : [ \"https://nrqiniu.tairanmall.com///20191129/43ff1201795d4bf097c98f3ec504aaa0.jpg\" ]\r\n}', 0, NULL, '2019-11-29 09:55:16');
INSERT INTO `sys_oper_log` VALUES (242, 'OSS上传文件', 1, 'com.ruoyi.web.controller.tool.UploadFileController.upload()', 1, 'admin', '信息中心', '/upload/oss', '192.168.0.6', '内网IP', '{\r\n  \"module\" : [ \"train/course\" ]\r\n}', 0, NULL, '2019-11-29 09:55:37');
INSERT INTO `sys_oper_log` VALUES (243, '个人信息修改', 2, 'com.ruoyi.cms.controller.CmsUserController.update()', 1, 'admin', '信息中心', '/web/user/update', '192.168.0.6', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"avatar\" : [ \"https://nrqiniu.tairanmall.com///20191129/ea802cc708e74bc5923d3bb0a9148007.jpg\" ]\r\n}', 0, NULL, '2019-11-29 09:55:37');
INSERT INTO `sys_oper_log` VALUES (244, '个人信息修改', 2, 'com.ruoyi.cms.controller.CmsUserController.update()', 1, 'admin', '信息中心', '/web/user/update', '192.168.0.6', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"remark\" : [ \"管理员1\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ]\r\n}', 0, NULL, '2019-11-29 09:55:50');
INSERT INTO `sys_oper_log` VALUES (245, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,1012,1013,', 0, NULL, '2019-11-29 10:09:01');
INSERT INTO `sys_oper_log` VALUES (246, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.update()', 1, 'admin', '信息中心', '/exam/examQuestion/update', '192.168.0.6', '内网IP', '{\r\n  \"id\" : [ \"4\" ],\r\n  \"categoryId\" : [ \"4\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"questionAnswer\" : [ \"C,D\" ],\r\n  \"item\" : [ \"[{\\\"examQuestionId\\\":\\\"4\\\",\\\"number\\\":\\\"A\\\",\\\"createBy\\\":\\\"admin\\\",\\\"updateDate\\\":1574845913000,\\\"id\\\":\\\"9\\\",\\\"delFlag\\\":\\\"0\\\",\\\"content\\\"', 0, NULL, '2019-11-29 10:16:33');
INSERT INTO `sys_oper_log` VALUES (247, '个人信息', 2, 'com.ruoyi.web.controller.system.SysProfileController.update()', 1, 'admin', '信息中心', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"dept.deptName\" : [ \"信息中心\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"sex\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:17:03');
INSERT INTO `sys_oper_log` VALUES (248, '个人信息', 2, 'com.ruoyi.web.controller.system.SysProfileController.update()', 1, 'admin', '信息中心', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"loginName\" : [ \"admin\" ],\r\n  \"dept.deptName\" : [ \"信息中心\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"sex\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-29 10:17:15');
INSERT INTO `sys_oper_log` VALUES (249, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2004\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程分类\" ],\r\n  \"url\" : [ \"train/course/category\" ],\r\n  \"perms\" : [ \"train:course:category:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" ', 0, NULL, '2019-11-29 10:18:55');
INSERT INTO `sys_oper_log` VALUES (250, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程维护\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\"', 0, NULL, '2019-11-29 10:19:18');
INSERT INTO `sys_oper_log` VALUES (251, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2007\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"题库维护\" ],\r\n  \"url\" : [ \"/exam/examQuestion\" ],\r\n  \"perms\" : [ \"exam:examQuestion:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\"', 0, NULL, '2019-11-29 10:19:47');
INSERT INTO `sys_oper_log` VALUES (252, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2008\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试卷维护\" ],\r\n  \"url\" : [ \"/exam/examPaper\" ],\r\n  \"perms\" : [ \"exam:examPaper:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:20:07');
INSERT INTO `sys_oper_log` VALUES (253, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2009\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"练习管理\" ],\r\n  \"url\" : [ \"/exam/examPractice\" ],\r\n  \"perms\" : [ \"exam:examPractice:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\"', 0, NULL, '2019-11-29 10:20:23');
INSERT INTO `sys_oper_log` VALUES (254, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2010\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"考试管理\" ],\r\n  \"url\" : [ \"/exam/examExamination\" ],\r\n  \"perms\" : [ \"exam:examExamination:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" :', 0, NULL, '2019-11-29 10:20:39');
INSERT INTO `sys_oper_log` VALUES (255, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:21:51');
INSERT INTO `sys_oper_log` VALUES (256, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:22:34');
INSERT INTO `sys_oper_log` VALUES (257, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"102\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"dev\" ],\r\n  \"email\" : [ \"471900500@qq.com\" ],\r\n  \"phonenumber\" : [ \"13000000000\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', 0, NULL, '2019-11-29 10:22:52');
INSERT INTO `sys_oper_log` VALUES (258, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"102\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"dev\" ],\r\n  \"email\" : [ \"471900500@qq.com\" ],\r\n  \"phonenumber\" : [ \"13000000000\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', 0, NULL, '2019-11-29 10:23:19');
INSERT INTO `sys_oper_log` VALUES (259, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:23:38');
INSERT INTO `sys_oper_log` VALUES (260, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:24:45');
INSERT INTO `sys_oper_log` VALUES (261, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:27:42');
INSERT INTO `sys_oper_log` VALUES (262, '角色管理', 3, 'com.ruoyi.web.controller.system.SysRoleController.remove()', 1, 'admin', '信息中心', '/system/role/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 10:28:57');
INSERT INTO `sys_oper_log` VALUES (263, '角色管理', 1, 'com.ruoyi.web.controller.system.SysRoleController.addSave()', 1, 'admin', '信息中心', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"普通管理员\" ],\r\n  \"roleKey\" : [ \"1\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2005,2001,2007,2008,2009,2010,2002,2006\" ]\r\n}', 0, NULL, '2019-11-29 10:29:25');
INSERT INTO `sys_oper_log` VALUES (264, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,1012,101', 0, NULL, '2019-11-29 10:29:37');
INSERT INTO `sys_oper_log` VALUES (265, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin1\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2005,2001,2007,2008,2009,2010,2002,2006\" ]\r\n}', 0, NULL, '2019-11-29 10:29:59');
INSERT INTO `sys_oper_log` VALUES (266, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"sysadmin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"系统管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,101', 0, NULL, '2019-11-29 10:30:11');
INSERT INTO `sys_oper_log` VALUES (267, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2005,2001,2007,2008,2009,2010,2002,2006\" ]\r\n}', 0, NULL, '2019-11-29 10:30:18');
INSERT INTO `sys_oper_log` VALUES (268, '角色管理', 3, 'com.ruoyi.web.controller.system.SysRoleController.remove()', 1, 'admin', '信息中心', '/system/role/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 10:30:23');
INSERT INTO `sys_oper_log` VALUES (269, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"102\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"dev\" ],\r\n  \"email\" : [ \"471900500@qq.com\" ],\r\n  \"phonenumber\" : [ \"13000000000\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"100\" ],\r\n  \"postIds\" : [ \"3\" ]\r\n}', 0, NULL, '2019-11-29 10:30:56');
INSERT INTO `sys_oper_log` VALUES (270, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"phonenumber\" : [ \"15666666666\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"100\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 10:31:04');
INSERT INTO `sys_oper_log` VALUES (271, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"1\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"陕西伟业医药有限公司\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"1\" ]\r\n}', 0, NULL, '2019-11-29 10:31:10');
INSERT INTO `sys_oper_log` VALUES (272, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"101\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"java开发\" ],\r\n  \"email\" : [ \"123@163.com\" ],\r\n  \"phonenumber\" : [ \"18798765432\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"100\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', 0, NULL, '2019-11-29 10:31:19');
INSERT INTO `sys_oper_log` VALUES (273, '角色管理', 3, 'com.ruoyi.web.controller.system.SysRoleController.remove()', 1, 'admin', '信息中心', '/system/role/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 10:31:26');
INSERT INTO `sys_oper_log` VALUES (274, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:32:20');
INSERT INTO `sys_oper_log` VALUES (275, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"sysadmin\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:32:25');
INSERT INTO `sys_oper_log` VALUES (276, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2004\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程分类\" ],\r\n  \"url\" : [ \"/train/course/category\" ],\r\n  \"perms\" : [ \"train:course:category:view;train:course:category:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"ic', 0, NULL, '2019-11-29 10:35:00');
INSERT INTO `sys_oper_log` VALUES (277, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2004\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程分类\" ],\r\n  \"url\" : [ \"/train/course/category\" ],\r\n  \"perms\" : [ \"train:course:category:view,train:course:category:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"ic', 0, NULL, '2019-11-29 10:36:21');
INSERT INTO `sys_oper_log` VALUES (278, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2004\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:course:category:add\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:36:58');
INSERT INTO `sys_oper_log` VALUES (279, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2004\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:course:category:edit\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:37:24');
INSERT INTO `sys_oper_log` VALUES (280, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2004\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:course:category:remove\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:37:57');
INSERT INTO `sys_oper_log` VALUES (281, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2004\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:course:category:list\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:38:50');
INSERT INTO `sys_oper_log` VALUES (282, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '信息中心', '/exam/examPaper/addQuestionForModel', '192.168.0.6', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"9\", \"7\", \"8\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"26\", \"27\", \"28\", \"29\", \"30\", \"31\" ],\r\n  \"paperId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 10:40:18');
INSERT INTO `sys_oper_log` VALUES (283, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '信息中心', '/exam/examPaper/addQuestionForModel', '192.168.0.6', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"9\", \"7\", \"8\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"26\", \"27\", \"28\", \"29\", \"30\", \"31\" ],\r\n  \"paperId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 10:40:24');
INSERT INTO `sys_oper_log` VALUES (284, '试卷', 3, 'com.ruoyi.exam.controller.ExamPaperController.addQuestionForModel()', 1, 'admin', '信息中心', '/exam/examPaper/addQuestionForModel', '192.168.0.6', '内网IP', '{\r\n  \"questionId[]\" : [ \"4\", \"6\", \"9\", \"7\", \"8\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"26\", \"27\", \"28\", \"29\", \"30\", \"31\", \"27\", \"28\", \"29\", \"30\", \"31\" ],\r\n  \"paperId\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 10:40:32');
INSERT INTO `sys_oper_log` VALUES (285, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]', 0, NULL, '2019-11-29 10:42:06');
INSERT INTO `sys_oper_log` VALUES (286, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程维护\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:list,train:trainCourse:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ', 0, NULL, '2019-11-29 10:43:09');
INSERT INTO `sys_oper_log` VALUES (287, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2004\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程分类\" ],\r\n  \"url\" : [ \"/train/course/category\" ],\r\n  \"perms\" : [ \"train:course:category:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\"', 0, NULL, '2019-11-29 10:43:28');
INSERT INTO `sys_oper_log` VALUES (288, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程维护\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\"', 0, NULL, '2019-11-29 10:43:47');
INSERT INTO `sys_oper_log` VALUES (289, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:trainCourse:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:44:15');
INSERT INTO `sys_oper_log` VALUES (290, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"添加\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:45:00');
INSERT INTO `sys_oper_log` VALUES (291, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2016\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"添加\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"train:trainCourse:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:46:04');
INSERT INTO `sys_oper_log` VALUES (292, '操作日志', 5, 'com.ruoyi.web.controller.monitor.SysOperlogController.export()', 1, 'admin', '信息中心', '/monitor/operlog/export', '192.168.0.6', '内网IP', '{\r\n  \"title\" : [ \"\" ],\r\n  \"operName\" : [ \"\" ],\r\n  \"businessType\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 10:46:04');
INSERT INTO `sys_oper_log` VALUES (293, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:trainCourse:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:46:36');
INSERT INTO `sys_oper_log` VALUES (294, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:trainCourse:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:46:54');
INSERT INTO `sys_oper_log` VALUES (295, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程章节管理\" ],\r\n  \"url\" : [ \"/train/course/trainCourseSection\" ],\r\n  \"perms\" : [ \"train:trainCourseSection:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n', 0, NULL, '2019-11-29 10:49:31');
INSERT INTO `sys_oper_log` VALUES (296, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程维护\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\"', 0, NULL, '2019-11-29 10:49:39');
INSERT INTO `sys_oper_log` VALUES (297, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2019\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:trainCourseSection:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:50:36');
INSERT INTO `sys_oper_log` VALUES (298, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2019\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"添加\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:trainCourseSection:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:51:02');
INSERT INTO `sys_oper_log` VALUES (299, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2019\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:trainCourseSection:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:51:29');
INSERT INTO `sys_oper_log` VALUES (300, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2019\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"train:trainCourseSection:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:51:46');
INSERT INTO `sys_oper_log` VALUES (301, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2007\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试题分类管理\" ],\r\n  \"url\" : [ \"/exam/examQuestionCategory\" ],\r\n  \"perms\" : [ \"exam:examQuestionCategory:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:57:56');
INSERT INTO `sys_oper_log` VALUES (302, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2024\" ],\r\n  \"parentId\" : [ \"2007\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试题分类管理\" ],\r\n  \"url\" : [ \"/exam/examQuestionCategory\" ],\r\n  \"perms\" : [ \"exam:examQuestionCategory:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n ', 0, NULL, '2019-11-29 10:58:09');
INSERT INTO `sys_oper_log` VALUES (303, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2024\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestionCategory:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:58:32');
INSERT INTO `sys_oper_log` VALUES (304, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2024\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"添加\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestionCategory:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:59:08');
INSERT INTO `sys_oper_log` VALUES (305, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2024\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"编辑\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestionCategory:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 10:59:55');
INSERT INTO `sys_oper_log` VALUES (306, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2024\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestionCategory:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 11:00:28');
INSERT INTO `sys_oper_log` VALUES (307, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2007\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试题管理\" ],\r\n  \"url\" : [ \"/exam/examQuestion\" ],\r\n  \"perms\" : [ \"exam:examQuestion:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 11:01:36');
INSERT INTO `sys_oper_log` VALUES (308, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2029\" ],\r\n  \"parentId\" : [ \"2007\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试题管理\" ],\r\n  \"url\" : [ \"/exam/examQuestion\" ],\r\n  \"perms\" : [ \"exam:examQuestion:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\"', 0, NULL, '2019-11-29 11:01:51');
INSERT INTO `sys_oper_log` VALUES (309, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 1, 'admin', '信息中心', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"102\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"dev\" ],\r\n  \"email\" : [ \"471900500@qq.com\" ],\r\n  \"phonenumber\" : [ \"13000000000\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"100\" ],\r\n  \"postIds\" : [ \"3\" ]\r\n}', 0, NULL, '2019-11-29 11:02:05');
INSERT INTO `sys_oper_log` VALUES (310, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2011,2012,2013,2014,2005,2015,2016,2017,2018,2019,2020,2021,2022,2023,', 0, NULL, '2019-11-29 11:02:22');
INSERT INTO `sys_oper_log` VALUES (311, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程内容管理\" ],\r\n  \"url\" : [ \"/train/course/trainCourseSection\" ],\r\n  \"perms\" : [ \"train:trainCourseSection:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n', 0, NULL, '2019-11-29 11:06:51');
INSERT INTO `sys_oper_log` VALUES (312, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程管理\" ],\r\n  \"url\" : [ \"/train/course/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 11:07:53');
INSERT INTO `sys_oper_log` VALUES (313, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.genCode()', 1, 'admin', '信息中心', '/tool/gen/genCode/sys_config', '192.168.0.6', '内网IP', '{ }', 0, NULL, '2019-11-29 11:08:52');
INSERT INTO `sys_oper_log` VALUES (314, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 1, 'admin', '信息中心', '/system/menu/remove/2019', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-11-29 11:13:03');
INSERT INTO `sys_oper_log` VALUES (315, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程维护\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\"', 0, NULL, '2019-11-29 11:13:17');
INSERT INTO `sys_oper_log` VALUES (316, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 1, 'admin', '信息中心', '/system/menu/remove/2019', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-11-29 11:13:40');
INSERT INTO `sys_oper_log` VALUES (317, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"sysadmin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"系统管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,101', 0, NULL, '2019-11-29 11:17:49');
INSERT INTO `sys_oper_log` VALUES (318, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2011,2012,2013,2014,2005,2030,2020,2021,2022,2023,2031,2015,2016,2017,', 0, NULL, '2019-11-29 11:17:57');
INSERT INTO `sys_oper_log` VALUES (319, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2030\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程内容管理\" ],\r\n  \"url\" : [ \"/train/course/trainCourseSection\" ],\r\n  \"perms\" : [ \"train:trainCourseSection:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ', 0, NULL, '2019-11-29 11:21:56');
INSERT INTO `sys_oper_log` VALUES (320, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2005\" ],\r\n  \"parentId\" : [ \"2000\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"课程管理\" ],\r\n  \"url\" : [ \"/train/trainCourse\" ],\r\n  \"perms\" : [ \"train:trainCourse:view,train:trainCourseSection:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" :', 0, NULL, '2019-11-29 11:22:17');
INSERT INTO `sys_oper_log` VALUES (321, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 1, 'admin', '信息中心', '/system/menu/remove/2030', '127.0.0.1', '内网IP', '{ }', 0, NULL, '2019-11-29 11:22:27');
INSERT INTO `sys_oper_log` VALUES (322, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2011,2012,2013,2014,2005,2015,2016,2017,2018,2020,2021,2022,2023,2001,', 0, NULL, '2019-11-29 11:24:52');
INSERT INTO `sys_oper_log` VALUES (323, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', '信息中心', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"sysadmin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"系统管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,101', 0, NULL, '2019-11-29 11:24:56');
INSERT INTO `sys_oper_log` VALUES (324, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.ruleSave()', 1, 'admin', '信息中心', '/system/role/rule', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 13:33:22');
INSERT INTO `sys_oper_log` VALUES (325, '用户管理', 3, 'com.ruoyi.web.controller.system.SysUserController.remove()', 1, 'admin', '信息中心', '/system/user/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"101\" ]\r\n}', 0, NULL, '2019-11-29 13:43:17');
INSERT INTO `sys_oper_log` VALUES (326, '用户管理', 3, 'com.ruoyi.web.controller.system.SysUserController.remove()', 1, 'admin', '信息中心', '/system/user/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', 0, NULL, '2019-11-29 13:43:21');
INSERT INTO `sys_oper_log` VALUES (327, '问题', 1, 'com.ruoyi.exam.controller.ExamQuestionController.update()', 1, 'admin', '信息中心', '/exam/examQuestion/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"4\" ],\r\n  \"categoryId\" : [ \"4\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"questionAnswer\" : [ \"C,D,E\" ],\r\n  \"item\" : [ \"[{\\\"examQuestionId\\\":\\\"4\\\",\\\"number\\\":\\\"A\\\",\\\"createBy\\\":\\\"admin\\\",\\\"updateDate\\\":1574993792000,\\\"id\\\":\\\"127\\\",\\\"delFlag\\\":\\\"0\\\",\\\"conte', 0, NULL, '2019-11-29 14:25:36');
INSERT INTO `sys_oper_log` VALUES (328, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2029\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestion:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:10:40');
INSERT INTO `sys_oper_log` VALUES (329, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2029\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"新增\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestion:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:11:19');
INSERT INTO `sys_oper_log` VALUES (330, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2029\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestion:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:11:43');
INSERT INTO `sys_oper_log` VALUES (331, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2029\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestion:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:12:30');
INSERT INTO `sys_oper_log` VALUES (332, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2029\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导入\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestion:edit\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:13:42');
INSERT INTO `sys_oper_log` VALUES (333, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', '信息中心', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2029\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examQuestion:export\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:14:17');
INSERT INTO `sys_oper_log` VALUES (334, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', '信息中心', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2036\" ],\r\n  \"parentId\" : [ \"2029\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导入\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"exam:examQuestion:import\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:14:33');
INSERT INTO `sys_oper_log` VALUES (335, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2008\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试卷分类管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:19:14');
INSERT INTO `sys_oper_log` VALUES (336, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2008\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试卷管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:19:49');
INSERT INTO `sys_oper_log` VALUES (337, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2038\" ],\r\n  \"parentId\" : [ \"2008\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试卷分类管理\" ],\r\n  \"url\" : [ \"/exam/examPaperCategory\" ],\r\n  \"perms\" : [ \"exam:examPaperCategory:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visi', 0, NULL, '2019-11-29 16:33:23');
INSERT INTO `sys_oper_log` VALUES (338, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2038\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaperCategory:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:34:04');
INSERT INTO `sys_oper_log` VALUES (339, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2038\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"添加\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaperCategory:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:34:32');
INSERT INTO `sys_oper_log` VALUES (340, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2038\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"编辑\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaperCategory:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:35:09');
INSERT INTO `sys_oper_log` VALUES (341, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2038\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaperCategory:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:35:32');
INSERT INTO `sys_oper_log` VALUES (342, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2039\" ],\r\n  \"parentId\" : [ \"2008\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"试卷管理\" ],\r\n  \"url\" : [ \"/exam/examPaper\" ],\r\n  \"perms\" : [ \"exam:examPaper:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:41:23');
INSERT INTO `sys_oper_log` VALUES (343, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2039\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaper:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:42:37');
INSERT INTO `sys_oper_log` VALUES (344, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2039\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"添加\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaper:add\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:43:33');
INSERT INTO `sys_oper_log` VALUES (345, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2039\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaper:edit\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:44:44');
INSERT INTO `sys_oper_log` VALUES (346, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2039\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaperCategory:remove\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:47:39');
INSERT INTO `sys_oper_log` VALUES (347, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2039\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaperCategory:export\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:48:27');
INSERT INTO `sys_oper_log` VALUES (348, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 1, 'admin', NULL, '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2015\" ],\r\n  \"parentId\" : [ \"2005\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"搜索（课程）\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"train:trainCourse:list\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:49:05');
INSERT INTO `sys_oper_log` VALUES (349, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 1, 'admin', NULL, '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2039\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"保存试题\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"exam:examPaper:add\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', 0, NULL, '2019-11-29 16:54:24');
INSERT INTO `sys_oper_log` VALUES (350, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', NULL, '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"sysadmin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"系统管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,101', 0, NULL, '2019-11-29 16:54:56');
INSERT INTO `sys_oper_log` VALUES (351, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', NULL, '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2011,2012,2013,2014,2005,2015,2016,2017,2018,2020,2021,2022,2023,2001,', 0, NULL, '2019-11-29 16:55:03');
INSERT INTO `sys_oper_log` VALUES (352, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', NULL, '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"系统管理员\" ],\r\n  \"roleKey\" : [ \"sysadmin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"系统管理员\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,101', 0, NULL, '2019-11-29 17:38:11');
INSERT INTO `sys_oper_log` VALUES (353, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 1, 'admin', NULL, '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通管理员\" ],\r\n  \"menuIds\" : [ \"2000,2004,2011,2012,2013,2014,2005,2015,2016,2017,2018,2020,2021,2022,2023,2001,', 0, NULL, '2019-11-29 17:38:21');
INSERT INTO `sys_oper_log` VALUES (354, '用户管理', 5, 'com.ruoyi.web.controller.system.SysUserController.export()', 1, 'admin', '信息中心', '/system/user/export', '192.168.0.9', '内网IP', '{\r\n  \"deptId\" : [ \"105\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"userType\" : [ \"00\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', 0, NULL, '2019-11-29 18:15:10');
INSERT INTO `sys_oper_log` VALUES (355, '问题', 3, 'com.ruoyi.exam.controller.ExamQuestionController.remove()', 1, 'admin', '信息中心', '/exam/examQuestion/remove', '192.168.0.9', '内网IP', '{\r\n  \"ids\" : [ \"6\" ]\r\n}', 0, NULL, '2019-11-29 18:15:33');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_suffix` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `service` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '服务商 1：七牛 2：阿里云 3：腾讯云',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `del_flag` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0正常 1删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统图片存储表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------
INSERT INTO `sys_oss` VALUES (1, 'timg.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191127/f8dbf1a6894b4736a2d24b5abba6f6e0.jpg', 0, '2019-11-27 16:12:47', 'admin');
INSERT INTO `sys_oss` VALUES (2, 'timg.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191127/bd30a2544d0d4036a3bd4e8e40dcc903.jpg', 0, '2019-11-27 16:19:41', 'admin');
INSERT INTO `sys_oss` VALUES (3, 'timg.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191127/68794a3b86ca452e80df8d0057e98b3f.jpg', 0, '2019-11-27 16:26:22', 'admin');
INSERT INTO `sys_oss` VALUES (4, '10以内加法.docx', '.docx', 1, 'https://nrqiniu.tairanmall.com///20191127/2f97ecafaeda4adda6edec2d806c8a4e.docx', 0, '2019-11-27 16:34:06', 'admin');
INSERT INTO `sys_oss` VALUES (5, 'fd6363c118c3061f655a250d24ed25c1.mp4', '.mp4', 1, 'https://nrqiniu.tairanmall.com///20191127/7e7077a8ce3b40c7948e48c6df82a56e.mp4', 0, '2019-11-27 16:38:15', 'admin');
INSERT INTO `sys_oss` VALUES (6, '111.pptx', '.pptx', 1, 'https://nrqiniu.tairanmall.com///20191127/f9bd9fc2722c486e8f6c864b0c0b18c4.pptx', 0, '2019-11-27 16:45:33', 'admin');
INSERT INTO `sys_oss` VALUES (7, 'u=2816592486,2755829337&fm=26&gp=0.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/2732c38d8f0447b89bb6bfb3a6f8f940.jpg', 0, '2019-11-28 09:45:09', 'admin');
INSERT INTO `sys_oss` VALUES (8, '下载.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/17dc879b5d21448ba609a250342f8fce.jpg', 0, '2019-11-28 09:45:09', 'admin');
INSERT INTO `sys_oss` VALUES (9, '下载.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/0684adc5aee04feba4b1b468b910d792.jpg', 0, '2019-11-28 09:45:09', 'admin');
INSERT INTO `sys_oss` VALUES (10, 'timg.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/da293a88e1f043988604a86764797b81.jpg', 0, '2019-11-28 09:45:09', 'admin');
INSERT INTO `sys_oss` VALUES (11, 'timg.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/5c8948af72eb45afaa19d78aad332376.jpg', 0, '2019-11-28 09:45:10', 'admin');
INSERT INTO `sys_oss` VALUES (12, 'timg.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/9c8153cd57604a97959e3a191165ad6e.jpg', 0, '2019-11-28 09:45:10', 'admin');
INSERT INTO `sys_oss` VALUES (13, 'timg.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/9eb470eab7e84527a05c802c97b032a0.jpg', 0, '2019-11-28 09:45:28', 'admin');
INSERT INTO `sys_oss` VALUES (14, 'u=2816592486,2755829337&fm=26&gp=0.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/ab6c87f0acaa4858a9163da0218a7cd2.jpg', 0, '2019-11-28 09:45:32', 'admin');
INSERT INTO `sys_oss` VALUES (15, '下载 (1).jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/dc64037364f04a62ae6ece7e054bb82d.jpg', 0, '2019-11-28 09:45:34', 'admin');
INSERT INTO `sys_oss` VALUES (16, '下载.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191128/06a581d294214d78ad7be17fd40c4e82.jpg', 0, '2019-11-28 09:45:36', 'admin');
INSERT INTO `sys_oss` VALUES (17, '1.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191129/43ff1201795d4bf097c98f3ec504aaa0.jpg', 0, '2019-11-29 09:55:15', 'admin');
INSERT INTO `sys_oss` VALUES (18, '2.jpg', '.jpg', 1, 'https://nrqiniu.tairanmall.com///20191129/ea802cc708e74bc5923d3bb0a9148007.jpg', 0, '2019-11-29 09:55:36', 'admin');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'sysadmin', 1, '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-29 17:38:11', '系统管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '1', '0', '2', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-29 10:27:42', '普通角色');
INSERT INTO `sys_role` VALUES (100, '管理员', 'admin', 2, '1', '0', '0', 'admin', '2019-11-29 10:29:25', 'admin', '2019-11-29 17:38:20', '普通管理员');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 105);
INSERT INTO `sys_role_menu` VALUES (1, 106);
INSERT INTO `sys_role_menu` VALUES (1, 107);
INSERT INTO `sys_role_menu` VALUES (1, 108);
INSERT INTO `sys_role_menu` VALUES (1, 109);
INSERT INTO `sys_role_menu` VALUES (1, 110);
INSERT INTO `sys_role_menu` VALUES (1, 111);
INSERT INTO `sys_role_menu` VALUES (1, 112);
INSERT INTO `sys_role_menu` VALUES (1, 113);
INSERT INTO `sys_role_menu` VALUES (1, 114);
INSERT INTO `sys_role_menu` VALUES (1, 115);
INSERT INTO `sys_role_menu` VALUES (1, 500);
INSERT INTO `sys_role_menu` VALUES (1, 501);
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 1007);
INSERT INTO `sys_role_menu` VALUES (1, 1008);
INSERT INTO `sys_role_menu` VALUES (1, 1009);
INSERT INTO `sys_role_menu` VALUES (1, 1010);
INSERT INTO `sys_role_menu` VALUES (1, 1011);
INSERT INTO `sys_role_menu` VALUES (1, 1012);
INSERT INTO `sys_role_menu` VALUES (1, 1013);
INSERT INTO `sys_role_menu` VALUES (1, 1014);
INSERT INTO `sys_role_menu` VALUES (1, 1015);
INSERT INTO `sys_role_menu` VALUES (1, 1016);
INSERT INTO `sys_role_menu` VALUES (1, 1017);
INSERT INTO `sys_role_menu` VALUES (1, 1018);
INSERT INTO `sys_role_menu` VALUES (1, 1019);
INSERT INTO `sys_role_menu` VALUES (1, 1020);
INSERT INTO `sys_role_menu` VALUES (1, 1021);
INSERT INTO `sys_role_menu` VALUES (1, 1022);
INSERT INTO `sys_role_menu` VALUES (1, 1023);
INSERT INTO `sys_role_menu` VALUES (1, 1024);
INSERT INTO `sys_role_menu` VALUES (1, 1025);
INSERT INTO `sys_role_menu` VALUES (1, 1026);
INSERT INTO `sys_role_menu` VALUES (1, 1027);
INSERT INTO `sys_role_menu` VALUES (1, 1028);
INSERT INTO `sys_role_menu` VALUES (1, 1029);
INSERT INTO `sys_role_menu` VALUES (1, 1030);
INSERT INTO `sys_role_menu` VALUES (1, 1031);
INSERT INTO `sys_role_menu` VALUES (1, 1032);
INSERT INTO `sys_role_menu` VALUES (1, 1033);
INSERT INTO `sys_role_menu` VALUES (1, 1034);
INSERT INTO `sys_role_menu` VALUES (1, 1035);
INSERT INTO `sys_role_menu` VALUES (1, 1036);
INSERT INTO `sys_role_menu` VALUES (1, 1037);
INSERT INTO `sys_role_menu` VALUES (1, 1038);
INSERT INTO `sys_role_menu` VALUES (1, 1039);
INSERT INTO `sys_role_menu` VALUES (1, 1040);
INSERT INTO `sys_role_menu` VALUES (1, 1041);
INSERT INTO `sys_role_menu` VALUES (1, 1042);
INSERT INTO `sys_role_menu` VALUES (1, 1043);
INSERT INTO `sys_role_menu` VALUES (1, 1044);
INSERT INTO `sys_role_menu` VALUES (1, 1045);
INSERT INTO `sys_role_menu` VALUES (1, 1046);
INSERT INTO `sys_role_menu` VALUES (1, 1047);
INSERT INTO `sys_role_menu` VALUES (1, 1048);
INSERT INTO `sys_role_menu` VALUES (1, 1049);
INSERT INTO `sys_role_menu` VALUES (1, 1050);
INSERT INTO `sys_role_menu` VALUES (1, 1051);
INSERT INTO `sys_role_menu` VALUES (1, 1052);
INSERT INTO `sys_role_menu` VALUES (1, 1053);
INSERT INTO `sys_role_menu` VALUES (1, 1054);
INSERT INTO `sys_role_menu` VALUES (1, 1055);
INSERT INTO `sys_role_menu` VALUES (1, 1056);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2001);
INSERT INTO `sys_role_menu` VALUES (1, 2002);
INSERT INTO `sys_role_menu` VALUES (1, 2004);
INSERT INTO `sys_role_menu` VALUES (1, 2005);
INSERT INTO `sys_role_menu` VALUES (1, 2006);
INSERT INTO `sys_role_menu` VALUES (1, 2007);
INSERT INTO `sys_role_menu` VALUES (1, 2008);
INSERT INTO `sys_role_menu` VALUES (1, 2009);
INSERT INTO `sys_role_menu` VALUES (1, 2010);
INSERT INTO `sys_role_menu` VALUES (1, 2011);
INSERT INTO `sys_role_menu` VALUES (1, 2012);
INSERT INTO `sys_role_menu` VALUES (1, 2013);
INSERT INTO `sys_role_menu` VALUES (1, 2014);
INSERT INTO `sys_role_menu` VALUES (1, 2015);
INSERT INTO `sys_role_menu` VALUES (1, 2016);
INSERT INTO `sys_role_menu` VALUES (1, 2017);
INSERT INTO `sys_role_menu` VALUES (1, 2018);
INSERT INTO `sys_role_menu` VALUES (1, 2020);
INSERT INTO `sys_role_menu` VALUES (1, 2021);
INSERT INTO `sys_role_menu` VALUES (1, 2022);
INSERT INTO `sys_role_menu` VALUES (1, 2023);
INSERT INTO `sys_role_menu` VALUES (1, 2024);
INSERT INTO `sys_role_menu` VALUES (1, 2025);
INSERT INTO `sys_role_menu` VALUES (1, 2026);
INSERT INTO `sys_role_menu` VALUES (1, 2027);
INSERT INTO `sys_role_menu` VALUES (1, 2028);
INSERT INTO `sys_role_menu` VALUES (1, 2029);
INSERT INTO `sys_role_menu` VALUES (1, 2032);
INSERT INTO `sys_role_menu` VALUES (1, 2033);
INSERT INTO `sys_role_menu` VALUES (1, 2034);
INSERT INTO `sys_role_menu` VALUES (1, 2035);
INSERT INTO `sys_role_menu` VALUES (1, 2036);
INSERT INTO `sys_role_menu` VALUES (1, 2037);
INSERT INTO `sys_role_menu` VALUES (1, 2038);
INSERT INTO `sys_role_menu` VALUES (1, 2039);
INSERT INTO `sys_role_menu` VALUES (1, 2040);
INSERT INTO `sys_role_menu` VALUES (1, 2041);
INSERT INTO `sys_role_menu` VALUES (1, 2042);
INSERT INTO `sys_role_menu` VALUES (1, 2043);
INSERT INTO `sys_role_menu` VALUES (1, 2044);
INSERT INTO `sys_role_menu` VALUES (1, 2045);
INSERT INTO `sys_role_menu` VALUES (1, 2046);
INSERT INTO `sys_role_menu` VALUES (1, 2047);
INSERT INTO `sys_role_menu` VALUES (1, 2048);
INSERT INTO `sys_role_menu` VALUES (1, 2049);
INSERT INTO `sys_role_menu` VALUES (1, 2050);
INSERT INTO `sys_role_menu` VALUES (1, 2051);
INSERT INTO `sys_role_menu` VALUES (1, 2052);
INSERT INTO `sys_role_menu` VALUES (1, 2053);
INSERT INTO `sys_role_menu` VALUES (1, 2054);
INSERT INTO `sys_role_menu` VALUES (1, 2055);
INSERT INTO `sys_role_menu` VALUES (1, 2056);
INSERT INTO `sys_role_menu` VALUES (1, 2057);
INSERT INTO `sys_role_menu` VALUES (1, 2058);
INSERT INTO `sys_role_menu` VALUES (1, 2059);
INSERT INTO `sys_role_menu` VALUES (2, 2000);
INSERT INTO `sys_role_menu` VALUES (2, 2001);
INSERT INTO `sys_role_menu` VALUES (2, 2002);
INSERT INTO `sys_role_menu` VALUES (2, 2004);
INSERT INTO `sys_role_menu` VALUES (2, 2005);
INSERT INTO `sys_role_menu` VALUES (2, 2006);
INSERT INTO `sys_role_menu` VALUES (2, 2007);
INSERT INTO `sys_role_menu` VALUES (2, 2008);
INSERT INTO `sys_role_menu` VALUES (2, 2009);
INSERT INTO `sys_role_menu` VALUES (2, 2010);
INSERT INTO `sys_role_menu` VALUES (100, 2000);
INSERT INTO `sys_role_menu` VALUES (100, 2001);
INSERT INTO `sys_role_menu` VALUES (100, 2002);
INSERT INTO `sys_role_menu` VALUES (100, 2004);
INSERT INTO `sys_role_menu` VALUES (100, 2005);
INSERT INTO `sys_role_menu` VALUES (100, 2006);
INSERT INTO `sys_role_menu` VALUES (100, 2007);
INSERT INTO `sys_role_menu` VALUES (100, 2008);
INSERT INTO `sys_role_menu` VALUES (100, 2009);
INSERT INTO `sys_role_menu` VALUES (100, 2010);
INSERT INTO `sys_role_menu` VALUES (100, 2011);
INSERT INTO `sys_role_menu` VALUES (100, 2012);
INSERT INTO `sys_role_menu` VALUES (100, 2013);
INSERT INTO `sys_role_menu` VALUES (100, 2014);
INSERT INTO `sys_role_menu` VALUES (100, 2015);
INSERT INTO `sys_role_menu` VALUES (100, 2016);
INSERT INTO `sys_role_menu` VALUES (100, 2017);
INSERT INTO `sys_role_menu` VALUES (100, 2018);
INSERT INTO `sys_role_menu` VALUES (100, 2020);
INSERT INTO `sys_role_menu` VALUES (100, 2021);
INSERT INTO `sys_role_menu` VALUES (100, 2022);
INSERT INTO `sys_role_menu` VALUES (100, 2023);
INSERT INTO `sys_role_menu` VALUES (100, 2024);
INSERT INTO `sys_role_menu` VALUES (100, 2025);
INSERT INTO `sys_role_menu` VALUES (100, 2026);
INSERT INTO `sys_role_menu` VALUES (100, 2027);
INSERT INTO `sys_role_menu` VALUES (100, 2028);
INSERT INTO `sys_role_menu` VALUES (100, 2029);
INSERT INTO `sys_role_menu` VALUES (100, 2032);
INSERT INTO `sys_role_menu` VALUES (100, 2033);
INSERT INTO `sys_role_menu` VALUES (100, 2034);
INSERT INTO `sys_role_menu` VALUES (100, 2035);
INSERT INTO `sys_role_menu` VALUES (100, 2036);
INSERT INTO `sys_role_menu` VALUES (100, 2037);
INSERT INTO `sys_role_menu` VALUES (100, 2038);
INSERT INTO `sys_role_menu` VALUES (100, 2039);
INSERT INTO `sys_role_menu` VALUES (100, 2040);
INSERT INTO `sys_role_menu` VALUES (100, 2041);
INSERT INTO `sys_role_menu` VALUES (100, 2042);
INSERT INTO `sys_role_menu` VALUES (100, 2043);
INSERT INTO `sys_role_menu` VALUES (100, 2044);
INSERT INTO `sys_role_menu` VALUES (100, 2045);
INSERT INTO `sys_role_menu` VALUES (100, 2046);
INSERT INTO `sys_role_menu` VALUES (100, 2047);
INSERT INTO `sys_role_menu` VALUES (100, 2048);
INSERT INTO `sys_role_menu` VALUES (100, 2049);
INSERT INTO `sys_role_menu` VALUES (100, 2050);
INSERT INTO `sys_role_menu` VALUES (100, 2051);
INSERT INTO `sys_role_menu` VALUES (100, 2052);
INSERT INTO `sys_role_menu` VALUES (100, 2053);
INSERT INTO `sys_role_menu` VALUES (100, 2054);
INSERT INTO `sys_role_menu` VALUES (100, 2055);
INSERT INTO `sys_role_menu` VALUES (100, 2056);
INSERT INTO `sys_role_menu` VALUES (100, 2057);
INSERT INTO `sys_role_menu` VALUES (100, 2058);
INSERT INTO `sys_role_menu` VALUES (100, 2059);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户，10一般用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '陕西伟业医药有限公司', '00', 'ry@163.com', '15888888888', '1', 'https://nrqiniu.tairanmall.com///20191129/ea802cc708e74bc5923d3bb0a9148007.jpg', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2019-11-30 09:40:52', 'admin', '2018-03-16 11:33:00', 'ry', '2019-11-30 09:40:53', '管理员1');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '陕西伟业医药有限公司', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '2', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-29 10:31:04', '测试员');
INSERT INTO `sys_user` VALUES (100, NULL, 'zhaoyan', 'zhaoyan', '10', '', '18712345678', '0', '', '2cc4a87fc1ab7197df062991cccb8040', '898e59', '0', '0', '192.168.0.163', '2019-11-28 10:57:36', '', '2019-11-27 17:11:10', '', '2019-11-28 10:57:36', '');
INSERT INTO `sys_user` VALUES (101, 103, 'java开发', 'java开发', '00', '123@163.com', '18798765432', '0', '', '4610fe94c1575d19cd0beb03a29a0da1', '89a4b6', '0', '2', '', NULL, 'admin', '2019-11-28 13:29:05', 'admin', '2019-11-29 10:31:19', '');
INSERT INTO `sys_user` VALUES (102, 103, 'dev', 'dev', '00', '471900500@qq.com', '13000000000', '0', '', '91d6935519261d7118aebb697eff6ca0', '4deccb', '0', '0', '127.0.0.1', '2019-11-29 17:38:29', 'admin', '2019-11-29 09:46:00', 'admin', '2019-11-29 17:38:29', '');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('3b62ce1e-828d-4040-8bd6-5ad46e959392', 'admin', '信息中心', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', 'on_line', '2019-11-30 09:40:51', '2019-11-30 09:41:57', 1800000);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `post_id` int(11) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);
INSERT INTO `sys_user_post` VALUES (101, 4);
INSERT INTO `sys_user_post` VALUES (102, 3);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 100);
INSERT INTO `sys_user_role` VALUES (101, 100);
INSERT INTO `sys_user_role` VALUES (102, 100);

-- ----------------------------
-- Table structure for train_course
-- ----------------------------
DROP TABLE IF EXISTS `train_course`;
CREATE TABLE `train_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，课程表id',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `train_course_category_id` int(11) NULL DEFAULT NULL COMMENT '课程分类',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '价格',
  `cover` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程封面',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '是否公开（默认 1-公开，2-不公开）',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of train_course
-- ----------------------------
INSERT INTO `train_course` VALUES (1, NULL, 102, '10以内加减法', 0.00, 'https://nrqiniu.tairanmall.com///20191127/bd30a2544d0d4036a3bd4e8e40dcc903.jpg', '这里是10以内加减法的课程简介，14564564654560148按时是的范德萨发展的发生大幅度的说法是的范德萨......', 2, 'admin', '2019-11-27 16:19:53', 'admin', '2019-11-28 13:33:34', NULL, NULL);

-- ----------------------------
-- Table structure for train_course_category
-- ----------------------------
DROP TABLE IF EXISTS `train_course_category`;
CREATE TABLE `train_course_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，课程分类表id',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '课程分类',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父课程分类ID',
  `parent_ids` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '祖级列表',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程分类名称',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `parent_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父课程名称',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of train_course_category
-- ----------------------------
INSERT INTO `train_course_category` VALUES (100, 11, 0, '0', '全部课程', 1, NULL, NULL, '2019-11-27 14:56:32', NULL, '2019-11-27 15:16:24', NULL, 0);
INSERT INTO `train_course_category` VALUES (101, NULL, 100, '0,100', '语文', 1, NULL, 'admin', '2019-11-27 15:05:09', NULL, '2019-11-27 15:08:52', NULL, 0);
INSERT INTO `train_course_category` VALUES (102, NULL, 100, '0,100', '数学', 2, NULL, 'admin', '2019-11-27 15:15:38', NULL, '2019-11-27 15:15:38', NULL, 0);
INSERT INTO `train_course_category` VALUES (103, NULL, 100, '0,100', '英语', 3, NULL, 'admin', '2019-11-27 15:17:05', NULL, '2019-11-27 15:17:05', NULL, 0);
INSERT INTO `train_course_category` VALUES (104, NULL, 101, '0,100,101', '单选题', 1, NULL, 'admin', '2019-11-27 15:17:44', 'admin', '2019-11-27 15:19:27', NULL, 0);
INSERT INTO `train_course_category` VALUES (105, NULL, 101, '0,100,101', '多选题', 2, NULL, 'admin', '2019-11-27 15:18:07', 'admin', '2019-11-27 15:19:33', NULL, 0);

-- ----------------------------
-- Table structure for train_course_section
-- ----------------------------
DROP TABLE IF EXISTS `train_course_section`;
CREATE TABLE `train_course_section`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，课程章节表id',
  `train_course_id` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '课件类型',
  `courseware` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课件',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程章节表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of train_course_section
-- ----------------------------
INSERT INTO `train_course_section` VALUES (2, 1, '10以内减法', 1, 'https://nrqiniu.tairanmall.com///20191127/7e7077a8ce3b40c7948e48c6df82a56e.mp4', 2, 'admin', '2019-11-27 16:38:16', 'admin', '2019-11-27 16:45:46', '10以内减法', 0);

-- ----------------------------
-- Table structure for train_course_section_courseware
-- ----------------------------
DROP TABLE IF EXISTS `train_course_section_courseware`;
CREATE TABLE `train_course_section_courseware`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，章节课件表id',
  `train_course_section_id` int(11) NULL DEFAULT NULL COMMENT '课程章节ID',
  `train_courseware_id` int(11) NULL DEFAULT NULL COMMENT '课件ID',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '章节课件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for train_course_user
-- ----------------------------
DROP TABLE IF EXISTS `train_course_user`;
CREATE TABLE `train_course_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，课程使用对象表id',
  `train_course_id` int(11) NULL DEFAULT NULL COMMENT '考试代码',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程使用对象表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for vip_user_certificate
-- ----------------------------
DROP TABLE IF EXISTS `vip_user_certificate`;
CREATE TABLE `vip_user_certificate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id，会员证书表id',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书名称',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书照片',
  `start_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '生效日期',
  `end_date` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '截止日期',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员证书表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for vip_user_course_section
-- ----------------------------
DROP TABLE IF EXISTS `vip_user_course_section`;
CREATE TABLE `vip_user_course_section`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，我的课程学习表id',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `train_course_id` int(11) NULL DEFAULT NULL COMMENT '课程ID',
  `train_course_section_id` int(11) NULL DEFAULT NULL COMMENT '章节id',
  `duration` int(11) NULL DEFAULT NULL COMMENT '学习时间长度（分钟）',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我的课程学习表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vip_user_course_section
-- ----------------------------
INSERT INTO `vip_user_course_section` VALUES (1, 1, 1, 2, 2, 'admin', '2019-11-27 16:39:18', 'admin', '2019-11-29 10:00:36', NULL, 0);

-- ----------------------------
-- Table structure for vip_user_orders
-- ----------------------------
DROP TABLE IF EXISTS `vip_user_orders`;
CREATE TABLE `vip_user_orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,我的订单表id',
  `vip_user_id` int(11) NULL DEFAULT NULL COMMENT '会员代码',
  `train_course_id` int(11) NULL DEFAULT NULL COMMENT '练习题代码',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我的订单表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
