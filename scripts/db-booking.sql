/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : service_driver_booking

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 10/10/2025 19:59:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_center
-- ----------------------------
DROP TABLE IF EXISTS `test_center`;
CREATE TABLE `test_center` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `code` varchar(32) NOT NULL,
                               `name` varchar(128) NOT NULL,
                               `status` tinyint(1) NOT NULL DEFAULT '0',
                               `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of test_center
-- ----------------------------
BEGIN;
INSERT INTO `test_center` (`id`, `code`, `name`, `status`, `created_at`, `updated_at`) VALUES (1, 'UOW', 'UOW Test Center', 0, '2025-10-08 20:51:52', '2025-10-09 23:25:09');
INSERT INTO `test_center` (`id`, `code`, `name`, `status`, `created_at`, `updated_at`) VALUES (2, 'Corrimal', 'Corrimal Test Center', 0, '2025-10-08 20:51:52', '2025-10-09 23:02:43');
INSERT INTO `test_center` (`id`, `code`, `name`, `status`, `created_at`, `updated_at`) VALUES (3, 'Kiama', 'Kiama Test center', 0, '2025-10-08 20:51:52', '2025-10-09 22:19:18');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;




/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : service_driver_booking

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 10/10/2025 19:59:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_booking
-- ----------------------------
DROP TABLE IF EXISTS `test_booking`;
CREATE TABLE `test_booking` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'book ID',
                                `center_id` bigint NOT NULL,
                                `candidate_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                `birthday` date NOT NULL COMMENT 'YYYY-MM-DD',
                                `plate_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `test_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'DRIVE_TEST',
                                `status` varchar(16) NOT NULL DEFAULT 'CONFIRMED' COMMENT 'PENDING/CONFIRMED/CANCELED/COMPLETED',
                                `payment_status` varchar(16) NOT NULL DEFAULT 'UNPAID' COMMENT 'UNPAID/PAID/REFUNDED',
                                `result` varchar(16) NOT NULL DEFAULT 'INCOMPLETE' COMMENT 'INCOMPLETE/PASS/FAIL',
                                `fee_cents` bigint NOT NULL DEFAULT '6000' COMMENT '6000=¥/＄60.00',
                                `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                KEY `idx_booking_center` (`center_id`),
                                KEY `idx_booking_person` (`candidate_name`,`birthday`),
                                KEY `idx_booking_created` (`created_at`),
                                CONSTRAINT `fk_booking_center` FOREIGN KEY (`center_id`) REFERENCES `test_center` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路考预约';

SET FOREIGN_KEY_CHECKS = 1;

