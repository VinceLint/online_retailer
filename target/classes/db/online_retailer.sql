/*
 Navicat Premium Data Transfer

 Source Server         : Mysql_VinceLin
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : online_retailer

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 22/07/2019 10:58:46
*/

CREATE DATABASE online_retailer;
USE online_retailer;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand_com_id` int(11) NULL DEFAULT NULL,
  `brand_mer_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`brand_id`) USING BTREE,
  INDEX `brand_com_id`(`brand_com_id`) USING BTREE,
  INDEX `brand_mer_id`(`brand_mer_id`) USING BTREE,
  CONSTRAINT `brand_ibfk_1` FOREIGN KEY (`brand_com_id`) REFERENCES `company` (`company_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `brand_ibfk_2` FOREIGN KEY (`brand_mer_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_location` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_contact` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `dic_id` int(11) NOT NULL,
  `dic_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dic_discribe` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dic_code` int(11) NULL DEFAULT NULL,
  `dic_value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL,
  `goods_title` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_pic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_price` float(10, 2) NULL DEFAULT NULL,
  `brand_id` int(11) NULL DEFAULT NULL,
  `goods_amount` int(255) NULL DEFAULT NULL,
  `goods_describe` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `goods_class` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `brand_id`(`brand_id`) USING BTREE,
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods_size
-- ----------------------------
DROP TABLE IF EXISTS `goods_size`;
CREATE TABLE `goods_size`  (
  `goods_id` int(11) NOT NULL,
  `lenght` float(255, 0) NULL DEFAULT NULL,
  `width` float NULL DEFAULT NULL,
  `height` float NULL DEFAULT NULL,
  `weight` float NULL DEFAULT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE,
  CONSTRAINT `goods_size_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for loan_sel_relationship
-- ----------------------------
DROP TABLE IF EXISTS `loan_sel_relationship`;
CREATE TABLE `loan_sel_relationship`  (
  `loan_sel_relationship_id` int(11) NOT NULL,
  `bvo_id` int(11) NULL DEFAULT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  `goods_amount` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`loan_sel_relationship_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `bvo_id`(`bvo_id`) USING BTREE,
  CONSTRAINT `loan_sel_relationship_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `loan_sel_relationship_ibfk_2` FOREIGN KEY (`bvo_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logistics
-- ----------------------------
DROP TABLE IF EXISTS `logistics`;
CREATE TABLE `logistics`  (
  `log_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `log_com_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_cou_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_cou_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logistics_node
-- ----------------------------
DROP TABLE IF EXISTS `logistics_node`;
CREATE TABLE `logistics_node`  (
  `log_node_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `log_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_node_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_arr_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`log_node_id`) USING BTREE,
  INDEX `log_id`(`log_id`) USING BTREE,
  CONSTRAINT `logistics_node_ibfk_1` FOREIGN KEY (`log_id`) REFERENCES `logistics` (`log_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_privilege` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(11) NOT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  `order_amount` int(255) NULL DEFAULT NULL,
  `order_status` int(255) NULL DEFAULT NULL,
  `order_cre_time` datetime(0) NULL DEFAULT NULL,
  `order_pay_time` datetime(0) NULL DEFAULT NULL,
  `order_send_time` datetime(0) NULL DEFAULT NULL,
  `order_cancel_time` datetime(0) NULL DEFAULT NULL,
  `order_complete_time` datetime(0) NULL DEFAULT NULL,
  `order_log_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliver_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliver_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bs_id` int(11) NULL DEFAULT NULL,
  `receiver_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trans_fare` float(255, 0) NULL DEFAULT NULL,
  `trans_way` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `bs_id`(`bs_id`) USING BTREE,
  INDEX `order_log_id`(`order_log_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`bs_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`order_log_id`) REFERENCES `logistics` (`log_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter`  (
  `par_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `par_value` int(255) NULL DEFAULT NULL,
  `par_describe` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`par_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int(11) NOT NULL,
  `store_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_type` int(255) NULL DEFAULT NULL,
  `store_own` int(255) NULL DEFAULT NULL,
  `store_score` float(255, 0) NULL DEFAULT NULL,
  `store_sell` int(255) NULL DEFAULT NULL,
  `store_url` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`store_id`) USING BTREE,
  INDEX `store_own`(`store_own`) USING BTREE,
  CONSTRAINT `store_ibfk_1` FOREIGN KEY (`store_own`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `transaction_record`;
CREATE TABLE `transaction_record`  (
  `tra_rec_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tra_rec_date` datetime(0) NULL DEFAULT NULL,
  `tra_rec_type` int(255) NULL DEFAULT NULL,
  `tra_rec_sum` float(255, 0) NULL DEFAULT NULL,
  `tra_rec_status` int(255) NULL DEFAULT NULL,
  `tra_rec_wal_id` int(11) NULL DEFAULT NULL,
  `tra_rec_balance` float(255, 0) NULL DEFAULT NULL,
  `tra_rec_exchangeMemo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tra_rec_id`) USING BTREE,
  INDEX `tra_rec_wal_id`(`tra_rec_wal_id`) USING BTREE,
  CONSTRAINT `transaction_record_ibfk_1` FOREIGN KEY (`tra_rec_wal_id`) REFERENCES `wallet` (`wal_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_wal_id` int(11) NULL DEFAULT NULL,
  `user_privilege` int(255) NULL DEFAULT NULL,
  `mvo_type` int(255) NULL DEFAULT NULL,
  `mvo_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_wal_id`(`user_wal_id`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_wal_id`) REFERENCES `wallet` (`wal_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_wishlist
-- ----------------------------
DROP TABLE IF EXISTS `user_wishlist`;
CREATE TABLE `user_wishlist`  (
  `wishlist_id` int(11) NOT NULL,
  `bvo_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`wishlist_id`) USING BTREE,
  INDEX `bvo_id`(`bvo_id`) USING BTREE,
  CONSTRAINT `user_wishlist_ibfk_1` FOREIGN KEY (`bvo_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `wal_id` int(11) NOT NULL,
  `wal_type` int(255) NULL DEFAULT NULL,
  `wal_password` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wal_balance` float(255, 0) NULL DEFAULT NULL,
  `wal_pay_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wal_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`wal_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wish_detail
-- ----------------------------
DROP TABLE IF EXISTS `wish_detail`;
CREATE TABLE `wish_detail`  (
  `wish_detail_id` int(11) NOT NULL,
  `wish_list_id` int(11) NULL DEFAULT NULL,
  `wish_goods_id` int(11) NULL DEFAULT NULL,
  `wish_goods_amount` int(255) NULL DEFAULT NULL,
  `wish_goods_status` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`wish_detail_id`) USING BTREE,
  INDEX `wish_goods_id`(`wish_goods_id`) USING BTREE,
  INDEX `wish_list_id`(`wish_list_id`) USING BTREE,
  CONSTRAINT `wish_detail_ibfk_1` FOREIGN KEY (`wish_goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `wish_detail_ibfk_2` FOREIGN KEY (`wish_list_id`) REFERENCES `user_wishlist` (`wishlist_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
