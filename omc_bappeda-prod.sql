/*
Navicat MySQL Data Transfer

Source Server         : Bappeda
Source Server Version : 50173
Source Host           : 182.253.195.115:3306
Source Database       : omc_bappeda

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-03-09 23:34:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `OMC_access`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_access`;
CREATE TABLE `OMC_access` (
  `id` varchar(40) NOT NULL,
  `directory_id` varchar(40) NOT NULL,
  `role_code` varchar(40) NOT NULL,
  `is_active` varchar(40) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `directory_id` (`directory_id`),
  KEY `role_code` (`role_code`),
  CONSTRAINT `OMC_access_ibfk_1` FOREIGN KEY (`directory_id`) REFERENCES `OMC_directories` (`id`),
  CONSTRAINT `OMC_access_ibfk_2` FOREIGN KEY (`role_code`) REFERENCES `OMC_roles` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_access
-- ----------------------------

-- ----------------------------
-- Table structure for `OMC_buckets`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_buckets`;
CREATE TABLE `OMC_buckets` (
  `id` varchar(45) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` date NOT NULL,
  `file_id` varchar(45) NOT NULL,
  `directory_id` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `directory_id` (`directory_id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `OMC_buckets_ibfk_1` FOREIGN KEY (`directory_id`) REFERENCES `OMC_directories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `OMC_buckets_ibfk_2` FOREIGN KEY (`file_id`) REFERENCES `OMC_files` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_buckets
-- ----------------------------

-- ----------------------------
-- Table structure for `OMC_departements`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_departements`;
CREATE TABLE `OMC_departements` (
  `code` varchar(40) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `created_by` varchar(40) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_departements
-- ----------------------------
INSERT INTO OMC_departements VALUES ('BIDBANG', 'BIDANG PENGENDALIAN DAN EVALUASI PEMBANGUNAN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:38:01', 'Y');
INSERT INTO OMC_departements VALUES ('BIDKO', 'BIDANG PEREKONOMIAN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:36:21', 'Y');
INSERT INTO OMC_departements VALUES ('BIDSAR', 'BIDANG SARANA PRASARANA', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:36:59', 'Y');
INSERT INTO OMC_departements VALUES ('BIDSOS', 'BIDANG SOSIAL BUDAYA', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:37:17', 'Y');
INSERT INTO OMC_departements VALUES ('BINFO', 'BIDANG PROGRAM, DATA DAN INFORMASI', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:38:30', 'Y');
INSERT INTO OMC_departements VALUES ('KABAN', 'KEPALA BADAN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:35:18', 'Y');
INSERT INTO OMC_departements VALUES ('SEKRE', 'SEKRETARIAT', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:35:47', 'Y');
INSERT INTO OMC_departements VALUES ('UPTB', 'UPTB PENATAAN RUANG', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:38:53', 'Y');

-- ----------------------------
-- Table structure for `OMC_directories`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_directories`;
CREATE TABLE `OMC_directories` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` date NOT NULL,
  `is_active` varchar(1) NOT NULL,
  `parent` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_directories
-- ----------------------------
INSERT INTO OMC_directories VALUES ('91772540-975c-44ae-8dc7-ed49ac945744', 'testing', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-08', 'Y', 'root');

-- ----------------------------
-- Table structure for `OMC_files`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_files`;
CREATE TABLE `OMC_files` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `path` text NOT NULL,
  `extension` varchar(45) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` date NOT NULL,
  `is_active` varchar(1) NOT NULL,
  `directory_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `directory_id_file_idx` (`directory_id`),
  CONSTRAINT `directory_id_file` FOREIGN KEY (`directory_id`) REFERENCES `OMC_directories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_files
-- ----------------------------

-- ----------------------------
-- Table structure for `OMC_histories`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_histories`;
CREATE TABLE `OMC_histories` (
  `id` varchar(45) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  `user_agent` varchar(255) NOT NULL,
  `activity` text NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` varchar(45) NOT NULL,
  `bucket_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `User_history_id` (`created_by`),
  CONSTRAINT `User_history_id` FOREIGN KEY (`created_by`) REFERENCES `OMC_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_histories
-- ----------------------------
INSERT INTO OMC_histories VALUES ('1a776721-70cf-4aa3-855a-807dbb709576', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 00:51:06', null);
INSERT INTO OMC_histories VALUES ('3d952cf9-ca0a-48a1-a618-faca72ac2357', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 00:02:54', null);
INSERT INTO OMC_histories VALUES ('54f32a71-9589-40ad-82d6-437c6d9f6a70', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 21:56:13', null);
INSERT INTO OMC_histories VALUES ('597bb3f1-027b-41f4-815d-ec0b4010171c', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 22:55:16', null);
INSERT INTO OMC_histories VALUES ('837ccabd-1483-4ee4-8673-d1ecdc11736e', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 00:24:19', null);
INSERT INTO OMC_histories VALUES ('8c2251aa-4f2a-4bf0-8a90-5a7aca08ea01', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 00:55:12', null);
INSERT INTO OMC_histories VALUES ('8e0fefe6-70ca-49e1-8c2f-106710dcf986', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 23:04:04', null);
INSERT INTO OMC_histories VALUES ('91250aa1-b80c-41ef-b91d-4cb3d9505d22', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 22:25:44', null);
INSERT INTO OMC_histories VALUES ('a9612aea-9bd3-4898-8af7-05137a3d6b37', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 21:10:46', null);
INSERT INTO OMC_histories VALUES ('caba8f62-64c4-4a1d-9fbf-4bc173b248e1', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 01:12:01', null);
INSERT INTO OMC_histories VALUES ('d8e7f30f-37e0-4608-88a9-c9feba4decf5', '182.30.125.19', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36', 'LOGIN', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2017-03-09 22:15:51', null);

-- ----------------------------
-- Table structure for `OMC_roles`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_roles`;
CREATE TABLE `OMC_roles` (
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` date NOT NULL,
  `is_active` varchar(1) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code_UNIQUE` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_roles
-- ----------------------------
INSERT INTO OMC_roles VALUES ('SUPER', 'SUPER ADMIN', '', '2016-07-28', 'Y');
INSERT INTO OMC_roles VALUES ('USER', 'User', '', '2016-07-30', 'Y');

-- ----------------------------
-- Table structure for `OMC_users`
-- ----------------------------
DROP TABLE IF EXISTS `OMC_users`;
CREATE TABLE `OMC_users` (
  `id` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dept_code` varchar(45) DEFAULT NULL,
  `role_code` varchar(45) DEFAULT NULL,
  `approval` varchar(20) DEFAULT NULL,
  `approve_by` varchar(45) DEFAULT NULL,
  `approve_date` datetime DEFAULT NULL,
  `is_admin` varchar(1) NOT NULL,
  `is_active` varchar(1) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `jabatan` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `no_hp` varchar(45) DEFAULT NULL,
  `jenis_kelamin` varchar(1) NOT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_name`),
  KEY `dept_id_user_idx` (`dept_code`),
  KEY `role_id_user_idx` (`role_code`),
  CONSTRAINT `dept_code_user` FOREIGN KEY (`dept_code`) REFERENCES `OMC_departements` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_code_user` FOREIGN KEY (`role_code`) REFERENCES `OMC_roles` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OMC_users
-- ----------------------------
INSERT INTO OMC_users VALUES ('fcd125f1-fb2a-4434-90ea-849aa560849d', 'admin', '1a247bfc6f555602ebdfb654fd5def2c', 'BINFO', 'USER', 'Approved', 'fcd125f1-fb2a-4434-90ea-849aa560849d', '2016-08-04 22:46:58', 'Y', 'Y', 'Super Admin', 'Super Admin', 'super@admin.co', '08132222222', 'L', '2016-08-04 22:46:34');
