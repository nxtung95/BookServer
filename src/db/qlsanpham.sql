/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL_HOME
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : qlsanpham

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 30/11/2021 07:16:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES (1, 'Author A', '2021-12-02', 'HBT-HCM', '0123456789');
INSERT INTO `author` VALUES (2, 'Author B', '2021-11-27', 'BK-HN', '033215215');
INSERT INTO `author` VALUES (3, 'AuthorB', NULL, NULL, NULL);
INSERT INTO `author` VALUES (4, 'AuthorC', NULL, NULL, NULL);
INSERT INTO `author` VALUES (9, 'TG2', NULL, NULL, NULL);
INSERT INTO `author` VALUES (10, 'TG1', NULL, NULL, NULL);
INSERT INTO `author` VALUES (11, '', NULL, NULL, NULL);
INSERT INTO `author` VALUES (12, '', NULL, NULL, NULL);
INSERT INTO `author` VALUES (13, '', NULL, NULL, NULL);
INSERT INTO `author` VALUES (14, 'Author H', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for book_author
-- ----------------------------
DROP TABLE IF EXISTS `book_author`;
CREATE TABLE `book_author`  (
  `product_id` int NOT NULL,
  `author_id` int NOT NULL,
  PRIMARY KEY (`product_id`, `author_id`) USING BTREE,
  INDEX `FKbjqhp85wjv8vpr0beygh6jsgo`(`author_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = FIXED;

-- ----------------------------
-- Records of book_author
-- ----------------------------
INSERT INTO `book_author` VALUES (4, 1);
INSERT INTO `book_author` VALUES (5, 1);
INSERT INTO `book_author` VALUES (5, 2);
INSERT INTO `book_author` VALUES (10, 9);
INSERT INTO `book_author` VALUES (10, 10);
INSERT INTO `book_author` VALUES (13, 14);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Sách');
INSERT INTO `category` VALUES (2, 'Viết');
INSERT INTO `category` VALUES (3, 'Tập');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'KH01', '2021-11-29', 'KH 1', 'HN');
INSERT INTO `customer` VALUES (2, 'KH02', '2021-11-02', 'KH2', 'HCM');
INSERT INTO `customer` VALUES (3, 'KH03', '2021-11-30', 'KH3', 'CM');
INSERT INTO `customer` VALUES (4, 'KH04', '2021-11-27', 'CNTLHN', 'CNTLHN');

-- ----------------------------
-- Table structure for detail_sale_invoice
-- ----------------------------
DROP TABLE IF EXISTS `detail_sale_invoice`;
CREATE TABLE `detail_sale_invoice`  (
  `product_id` int NOT NULL,
  `invoice_id` int NOT NULL,
  `quantity` int NULL DEFAULT NULL,
  `total_price` decimal(20, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`, `invoice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detail_sale_invoice
-- ----------------------------
INSERT INTO `detail_sale_invoice` VALUES (1, 1, 5, 50000.00);
INSERT INTO `detail_sale_invoice` VALUES (1, 2, 10, 70000.00);
INSERT INTO `detail_sale_invoice` VALUES (2, 1, 10, 100000.00);
INSERT INTO `detail_sale_invoice` VALUES (3, 2, 15, 130000.00);
INSERT INTO `detail_sale_invoice` VALUES (4, 3, 1000, 5000000.00);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `user_id` int NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `FKlx8n4x9vqj3lqv8cj9ienwrv6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'QuanLy');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `price` decimal(19, 2) NULL DEFAULT NULL,
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `quantity` int NOT NULL,
  `total_page` int NULL DEFAULT NULL,
  `category_id` int NOT NULL,
  `publisher_id` int NULL DEFAULT NULL,
  `supplier_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1mtsbur82frn64de7balymq9s`(`category_id`) USING BTREE,
  INDEX `FKjfg1nl4bf425evnmy1cy8dd9e`(`publisher_id`) USING BTREE,
  INDEX `FK2kxvbr72tmtscjvyp9yqb12by`(`supplier_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'Description', 'C:\\Users\\ADMIN\\Pictures\\Screenshots\\Screenshot (4).png', 'Tap Viet', 5000.00, 'VIET04', 0, 150, 2, NULL, 1);
INSERT INTO `product` VALUES (2, 'Vo', 'src/image/vo1.jpg', 'Vo', 2000.00, 'VO01', 50, 150, 2, NULL, 1);
INSERT INTO `product` VALUES (3, 'Tap', 'src/image/tap1.jpg', 'Tap', 5000.00, 'TAP01', 100, 200, 3, NULL, 1);
INSERT INTO `product` VALUES (4, 'SGK', 'src/image/SGK1.jpg', 'SGK01', 50000.00, 'SGK01', 4, 250, 1, 1, 2);
INSERT INTO `product` VALUES (5, 'SGK', 'src/image/SGK2.jpg', 'SGK02', 50000.00, 'SGK02', 150, 300, 1, 1, 2);
INSERT INTO `product` VALUES (6, '', 'C:\\Users\\ADMIN\\Pictures\\Screenshots\\Screenshot (2).png', 'abc', 4000.00, 'bcvbcv', 0, 245, 1, 3, 3);

-- ----------------------------
-- Table structure for publisher
-- ----------------------------
DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of publisher
-- ----------------------------
INSERT INTO `publisher` VALUES (1, 'HCB', 'NXB KD');
INSERT INTO `publisher` VALUES (2, 'HCA', 'NXB KD2');
INSERT INTO `publisher` VALUES (3, NULL, 'NXB123');
INSERT INTO `publisher` VALUES (4, NULL, 'NXB123');
INSERT INTO `publisher` VALUES (5, NULL, 'NVC12344');
INSERT INTO `publisher` VALUES (6, NULL, 'KD2');
INSERT INTO `publisher` VALUES (7, NULL, 'NXX2');
INSERT INTO `publisher` VALUES (8, NULL, 'NXB234');
INSERT INTO `publisher` VALUES (9, NULL, 'NNB1');
INSERT INTO `publisher` VALUES (10, NULL, 'XXXXX');
INSERT INTO `publisher` VALUES (14, NULL, 'NXB1234');

-- ----------------------------
-- Table structure for sale_invoice
-- ----------------------------
DROP TABLE IF EXISTS `sale_invoice`;
CREATE TABLE `sale_invoice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `staff_id` int NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  `invoice_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_invoice_1`(`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sale_invoice
-- ----------------------------
INSERT INTO `sale_invoice` VALUES (1, 2, 1, 'HD01', '2021-11-29 23:12:09');
INSERT INTO `sale_invoice` VALUES (2, 2, 1, 'HD02', '2021-11-24 23:12:28');
INSERT INTO `sale_invoice` VALUES (3, 3, 2, 'HD03', '2021-11-02 23:12:39');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `user_id` int NOT NULL,
  `staff_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `FKbhogfndgswrqk696i1s2stk2g` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (2, 'NV01');
INSERT INTO `staff` VALUES (3, 'NV02');

-- ----------------------------
-- Table structure for staff_work_time
-- ----------------------------
DROP TABLE IF EXISTS `staff_work_time`;
CREATE TABLE `staff_work_time`  (
  `staff_id` int NOT NULL,
  `work_time_id` int NOT NULL,
  `note` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`staff_id`, `work_time_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of staff_work_time
-- ----------------------------
INSERT INTO `staff_work_time` VALUES (2, 1, 'Ca 1');
INSERT INTO `staff_work_time` VALUES (3, 2, 'Ca 2');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, 'HCM', 'Cua hang sach', '0123456789');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, 'HHH', 'Supplier A');
INSERT INTO `supplier` VALUES (2, 'GGGG', 'Supplier B');
INSERT INTO `supplier` VALUES (3, NULL, 'NCCA');
INSERT INTO `supplier` VALUES (4, NULL, 'NCC1');
INSERT INTO `supplier` VALUES (5, NULL, 'NCC1');
INSERT INTO `supplier` VALUES (6, NULL, 'vbnbvgfd');
INSERT INTO `supplier` VALUES (7, NULL, 'nbbvn');
INSERT INTO `supplier` VALUES (8, NULL, 'bvnb');
INSERT INTO `supplier` VALUES (9, NULL, 'nbvn');
INSERT INTO `supplier` VALUES (10, NULL, 'NCC2');
INSERT INTO `supplier` VALUES (11, NULL, 'NCC H');
INSERT INTO `supplier` VALUES (12, NULL, 'NCC D');
INSERT INTO `supplier` VALUES (13, NULL, 'Supplier A');
INSERT INTO `supplier` VALUES (14, NULL, 'NCC D');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'quanly', '70a991f50746d5c4192ede976c89a205', 'Quan Ly', 'TPHCM');
INSERT INTO `user` VALUES (2, 'nhanvien', '8c5cf5e773cd465216561a9fbe6f3656', 'NV1', 'TPHCM');
INSERT INTO `user` VALUES (3, 'nhanvien2', '8c5cf5e773cd465216561a9fbe6f3656', 'NV2', 'TPHCM');

-- ----------------------------
-- Table structure for work_time
-- ----------------------------
DROP TABLE IF EXISTS `work_time`;
CREATE TABLE `work_time`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_time` time(0) NULL DEFAULT NULL,
  `start_time` time(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of work_time
-- ----------------------------
INSERT INTO `work_time` VALUES (1, '13:20:44', '08:20:36');
INSERT INTO `work_time` VALUES (2, '22:21:09', '13:21:01');

-- ----------------------------
-- Table structure for worktime
-- ----------------------------
DROP TABLE IF EXISTS `worktime`;
CREATE TABLE `worktime`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_time` time(0) NULL DEFAULT NULL,
  `start_time` time(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worktime
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
