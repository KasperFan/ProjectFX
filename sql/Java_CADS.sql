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

 Date: 15/12/2023 04:44:43
*/

CREATE DATABASE IF NOT EXISTS Java_CADS;

USE Java_CADS;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_list
-- ----------------------------
DROP TABLE IF EXISTS `user_list`;
CREATE TABLE `user_list`
(
    `uid`       int unsigned                                                  NOT NULL COMMENT '用户ID',
    `name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
    `pswd_sha` varchar(255)                                                  NOT NULL COMMENT '密码的SHA-256加密值',
    PRIMARY KEY (`uid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 51
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_list
-- ----------------------------
BEGIN;
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (2, 'Xiaoming', '30225d343aeb3105ff77b519edff77ba73553012a08c3f18b70890ba79be0ba6');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (3, 'Bruce', 'b18978c14b8024dc00a7fe6fdb7d03ec538263570d810811d16042d94b33b828');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (4, 'Yunxi', 'eadd6f83d16eab0ff0f1f2417e5836127d172efb0adfa659653f47da72b7bd55');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (5, 'Raymond', 'd102e9641da0f59932fa2bcb615dc4d89910ae9b38b661b1c4f95cc3f0343695');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (6, 'Tsubasa', '56b48d44004de47d38a1752f1e394bb8423cf24f0f01235bb6aa70d583e029f2');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (7, 'Sakura', '5ab21e4cf7d1066165ce7febcf66046e3c187caaae706d5c06fde7fe3d99e59e');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (8, 'Travis', 'f2dc9d4d40345059e8219494c9a6b901ff639c2cb4b33d7c60224b6c8ae3dc00');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (9, 'Ikki', '1288ba27636715c73cc12d2fda35a1f76066385990e211ef14db23ad7b845100');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (10, 'Bobby', 'f6d674ba4cb07d6ff6375b4b561a434b475f57b46ef6cf9658f99161fadf09bf');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (11, 'Zitao', 'fc47151775c202e50538bc6a9f4dc5fcb30fff5ed71d5fc3a1c4f2f1dc3f26f5');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (12, 'Shihan', '3e98381ea240ae6caf749941d1d3cffd532c1d848fb16fc45cd0ead28127ab76');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (13, 'Momoka', '8c4031fcecb9cd4f6115aa3655443baf9a01d5f5d10c61c2277017f37c833690');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (14, 'Bruce', 'b18978c14b8024dc00a7fe6fdb7d03ec538263570d810811d16042d94b33b828');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (15, 'Zitao', 'fc47151775c202e50538bc6a9f4dc5fcb30fff5ed71d5fc3a1c4f2f1dc3f26f5');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (16, 'Mark', '151e68811023d9ac9be511f7de11094203ad915da7b24c7817a036ab4f5b4194');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (17, 'Denise', 'ffcd9bebaa792c7b19be1496c945fd36df5f148582e0e36e725fc5ffc4426e6e');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (18, 'Airi', '92b35c59bdfc6a68c70cb651a222ea0de4f5cee29cb19a9da53487ce1117f77a');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (19, 'Nanami', '3339ccfad84d83d4d389eccd8dbf8a442e096273f00d1de71703ef3bb80d9f01');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (20, 'Sakura', '5ab21e4cf7d1066165ce7febcf66046e3c187caaae706d5c06fde7fe3d99e59e');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (21, 'Kathleen', 'edce3b818697256b66bfab6bfb26d2712ce1144fc94433ea50411ff2df5d1c4e');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (22, 'Momoka', '8c4031fcecb9cd4f6115aa3655443baf9a01d5f5d10c61c2277017f37c833690');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (23, 'Rin', 'ef24727ede388680f0aa5925cf0ecdd568b4547f2c6e421bf39c991d27be0fe4');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (24, 'Lu', '31750dacc4aad4fa802c0d0dcad51f3844d884aeca82039d94755206dccac423');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (25, 'Alan', '8d5d6694bdd31cc267fd4d1cac0147133cab22b0705eae5e1207d071aa6c062d');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (26, 'Catherine', 'de3b0e2ad6d2fc0f194dc84df2bc46fc4f112a7899f55983489a17bda1bbb465');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (27, 'Raymond', 'd102e9641da0f59932fa2bcb615dc4d89910ae9b38b661b1c4f95cc3f0343695');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (28, 'Eita', '04d31a7bdf3dc4e52ff1ccada6c9831489df21e620b5c98be7b10254fab9403d');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (29, 'Aoi', '89c9ef9e3e866daca12379ca31e84fae525527aae437337163683d533d7c66a9');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (30, 'Zhiyuan', '783beb8b3dd38e801f08b712538d7517226a15523899827cbac71358641c667b');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (31, 'Yota', 'ce2eea212e31fb86daf6dfb9517d5cc3a6c6ea95f1a3665ef019f3773ae7760c');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (32, 'Leroy', 'bb7d8826b54c57dd5f90d3b80d9bd07f8cb1ece279cb67e504f54fe6ea272b49');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (33, 'Ziyi', '60be9ed1114bf4a958df91ffdcc69e20a4470a2fe91da7dab05d694e508c8940');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (34, 'Kathleen', 'edce3b818697256b66bfab6bfb26d2712ce1144fc94433ea50411ff2df5d1c4e');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (35, 'Diane', 'dd3777ae4f4f8a11d3c838bec01f78efaacde7f9a9c2c1c6719189365b5f64c7');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (36, 'Xiaoming', '30225d343aeb3105ff77b519edff77ba73553012a08c3f18b70890ba79be0ba6');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (37, 'Jiehong', '18b09a2378006df1fe6368e7d6fdc0fa7736d131f6174f11eab39c8f9efb72d8');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (38, 'Sara', '3a642c44f275ff48ae5096b654e1aa6e42392b1c98b398f07eae97165fe978ed');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (39, 'Hikaru', '4d241930e81155374a0bbc65b406cdb35ba0925ba3fd5a8c69dfff2609a5e337');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (40, 'Takuya', '4b68d9f403c7eea394de3aa4629eb7483d8a9ea9a56dde4619d1a15fca32b124');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (41, 'Jiehong', '18b09a2378006df1fe6368e7d6fdc0fa7736d131f6174f11eab39c8f9efb72d8');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (42, 'Christina', '9eaa81817b5c5856ec377bc73d387cbfa107138f0ff4a8c483b22fe19b5ff316');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (43, 'Kazuma', '94d7400cdcf56aa22ef6da1f959f56b7bfed98c0f964efde442f1b3dbe27d364');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (44, 'Charles', '4744192ae226a8df3656eae5d83a3f886d0a072f2f10035087c71e5f97d5c6dd');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (45, 'Miu', '33a86976936947026afa95c631b0229a9c5c41af39a27ec04c06867f93798bb6');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (46, 'Kenneth', 'b373265f7acb222b5a61c55184049a80e6544644362032d81cd1bd782fa0fd07');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (47, 'Patricia', 'fa5095c98133479de37c3c0d2eb0f2d231c4ee54f28de2fa8c6ecaf133865123');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (48, 'Miu', '33a86976936947026afa95c631b0229a9c5c41af39a27ec04c06867f93798bb6');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (49, 'Michael', '4adf31ca4bde3a3d3cccfcf599098d5cba7e7405111696ef017a0dfe72f3ec66');
INSERT INTO `user_list` (`uid`, `name`, `pswd_sha`)
VALUES (50, 'Zitao', 'fc47151775c202e50538bc6a9f4dc5fcb30fff5ed71d5fc3a1c4f2f1dc3f26f5');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
