/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : Java_CADS

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 24/12/2023 03:17:30
*/
CREATE DATABASE IF NOT EXISTS Java_CADS;
USE Java_CADS;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for astronaut
-- ----------------------------
DROP TABLE IF EXISTS `astronaut`;
CREATE TABLE `astronaut` (
  `aid` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '宇航员ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '宇航员姓名',
  `age` int DEFAULT NULL COMMENT '宇航员年龄',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '宇航员性别',
  PRIMARY KEY (`aid`,`name`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宇航员信息表';

-- ----------------------------
-- Records of astronaut
-- ----------------------------
BEGIN;
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0001, '杨利伟', 38, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0002, '费俊龙', 40, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0003, '聂海胜', 41, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0004, '翟志刚', 42, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0005, '刘伯明', 42, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0006, '景海鹏', 43, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0007, '刘旺', 46, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0008, '刘洋', 34, '女');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0009, '张晓光', 47, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0010, '王亚平', 33, '女');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0011, '陈冬', 38, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0012, '唐洪波', 42, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0013, '叶光富', 40, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0014, '蔡旭哲', 35, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0015, '邓清明', 37, '男');
INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (0016, '张陆', 36, '男');
COMMIT;

-- ----------------------------
-- Table structure for ev_ast
-- ----------------------------
DROP TABLE IF EXISTS `ev_ast`;
CREATE TABLE `ev_ast` (
  `eid` int(4) unsigned zerofill NOT NULL COMMENT '事件ID',
  `aid` int(4) unsigned zerofill NOT NULL COMMENT '宇航员ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件—宇航员关系表';

-- ----------------------------
-- Records of ev_ast
-- ----------------------------
BEGIN;
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0004, 0001);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0005, 0002);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0005, 0003);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0007, 0004);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0007, 0005);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0007, 0006);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0010, 0006);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0010, 0007);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0010, 0008);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0012, 0006);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0012, 0011);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0005);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0012);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0013);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0004);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0010);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0014);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0015);
INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (0018, 0016);
COMMIT;

-- ----------------------------
-- Table structure for ev_roc
-- ----------------------------
DROP TABLE IF EXISTS `ev_roc`;
CREATE TABLE `ev_roc` (
  `eid` int(4) unsigned zerofill NOT NULL COMMENT '事件ID',
  `rid` int(4) unsigned zerofill NOT NULL COMMENT '火箭ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件—火箭关系表';

-- ----------------------------
-- Records of ev_roc
-- ----------------------------
BEGIN;
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0002, 0001);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0003, 0002);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0003, 0003);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0004, 0002);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0005, 0002);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0006, 0003);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0007, 0002);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0008, 0003);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0009, 0002);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0010, 0002);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0011, 0003);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0012, 0002);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0013, 0007);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0014, 0003);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0015, 0005);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0016, 0005);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0017, 0005);
INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (0018, 0002);
COMMIT;

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `eid` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '事件id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '事件名',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '事件发生时间',
  `mean` varchar(255) DEFAULT NULL COMMENT '事件的意义',
  PRIMARY KEY (`eid`),
  UNIQUE KEY `name` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件表';

-- ----------------------------
-- Records of event
-- ----------------------------
BEGIN;
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0001, '第一个火箭导弹研究机构———国防部第五研究院成立', '1956.10.08', '开启了我国航天事业的发展历程');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0002, '首枚人造卫星“东方红一号”发射成功', '1970.04.24', '我国成为世界上第三个独立研制和发射卫星的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0003, '尝试进入世界航天市场，承揽国内外用户卫星发射', '1985.10.26', '我国成为全球第三个步入国际商业发射市场的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0004, '首次载人航天飞行任务成功，杨利伟成为我国首位航天员', '2003.10.15', '我国成为世界上第三个独立掌握载人航天技术的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0005, '实现“多人多天”在轨飞行，航天员费俊龙、聂海胜在太空生活5天', '2005.10.12', '我国掌握了环境控制与生命保障、飞行器控制、航天医学保障等关键技术');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0006, '探月卫星“嫦娥一号”发射成功，开启了我国探月工程的序幕', '2007.10.24', '我国成为世界上第五个探月的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0007, '航天员太空行走，翟志刚在太空进行了20分钟的出舱活动', '2008.09.25', '我国突破和掌握了空间出舱活动技术');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0008, '探月着陆器“嫦娥二号”发射成功，对月球进行了绕月探测', '2010.10.01', '我国成为世界上第三个实现月球绕月探测的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0009, '空间站交会对接，神舟八号飞船与天宫一号目标飞行器成功对接', '2011.11.03', '我国空间交会对接技术取得重大突破');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0010, '首位女航天员刘洋随神舟九号飞船进入太空，实现了航天员手控交会对接', '2012.06.16', '我国突破和掌握了航天员手控交会对接技术');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0011, '月球软着陆和巡视探测，嫦娥三号探测器和玉兔号月球车成功登陆月球', '2013.12.14', '我国成为世界上第三个实现月球软着陆的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0012, '航天员中期在轨驻留，神舟十一号飞船与天宫二号空间实验室成功对接', '2016.10.19', '我国首次开展了真正意义上有人参与的空间科学实验');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0013, '推进剂在轨补加，天舟一号货运飞船与天宫二号空间实验室成功对接', '2017.04.22', '我国突破和验证了空间站货物运输、推进剂在轨补加等关键技术');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0014, '月球背面软着陆和巡视探测，嫦娥四号探测器和玉兔二号月球车成功登陆月球', '2019.01.03', '我国成为世界上第一个在月球背面着陆的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0015, '实现月球样品返回，嫦娥五号探测器成功取回月壤', '2020.12.17', '我国成为世界上第三个实现月球样品返回的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0016, '实现火星探测，天问一号探测器成功进入火星轨道，祝融号火星车成功登陆火星', '2021.2.10 5.15', '我国成为世界上第二个实现火星探测的国家');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0017, '空间站核心舱发射，天和核心舱成功进入预定轨道', '2021.04.29', '我国空间站建设从关键技术验证阶段转入在轨建造阶段');
INSERT INTO `event` (`eid`, `title`, `time`, `mean`) VALUES (0018, '实现空间站长期有人驻留，神舟十二号、神舟十三号、神舟十四号、神舟十五号飞船与天和核心舱、问天实验舱成功对接', '2021.06.17 10.16 11.29 12.2', '我国实现了6人在轨、太空会师的历史性突破，中国空间站正式开启长期有人驻留模式');
COMMIT;

-- ----------------------------
-- Table structure for rocket
-- ----------------------------
DROP TABLE IF EXISTS `rocket`;
CREATE TABLE `rocket` (
  `rocketID` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '火箭ID',
  `rocketName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '火箭名称',
  `launchDate` varchar(255) DEFAULT NULL COMMENT '发射时间',
  `in_orbitTime` int DEFAULT NULL COMMENT '在轨时间',
  PRIMARY KEY (`rocketID`),
  UNIQUE KEY `rocketName` (`rocketName`) USING BTREE COMMENT '火箭名称唯一'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='火箭信息表';

-- ----------------------------
-- Records of rocket
-- ----------------------------
BEGIN;
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0001, '长征一号', '1970.04.24', 28);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0002, '长征二号', '1971.11.03', NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0003, '长征三号', '1984.04.08', NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0004, '长征四号', '1988.09.06', NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0005, '长征五号', '2016.11.03', NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0006, '长征六号', '2015.09.19', NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0007, '长征七号', '2016.06.25', NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0008, '长征八号', '2020.12.22', NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0009, '长征九号', NULL, NULL);
INSERT INTO `rocket` (`rocketID`, `rocketName`, `launchDate`, `in_orbitTime`) VALUES (0010, '长征十一号', '2015.9.25', NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `pswd_sha` varchar(255) NOT NULL COMMENT '密码的SHA-256加密值',
  `is_admin` int NOT NULL COMMENT '用户的类型',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`uid`, `name`, `pswd_sha`, `is_admin`) VALUES (0001, 'root', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1);
INSERT INTO `user` (`uid`, `name`, `pswd_sha`, `is_admin`) VALUES (0002, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
