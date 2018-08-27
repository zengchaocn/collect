/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : collect

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-08-26 21:57:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `food_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `shopId` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `authentic_id` varchar(64) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `opening_hours` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL COMMENT '评分',
  `rating_count` int(10) unsigned DEFAULT NULL COMMENT '评分次数',
  `recent_order_num` int(10) unsigned DEFAULT NULL COMMENT '订单数',
  `supports` varchar(255) DEFAULT NULL COMMENT '支持项',
  `order_lead_time` int(11) DEFAULT NULL COMMENT '订单平均时间',
  `is_new` double(255,0) DEFAULT NULL COMMENT '新店',
  `is_premium` double DEFAULT NULL COMMENT '保险',
  `is_invoice` double(255,0) DEFAULT NULL COMMENT '发票',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `flavors` varchar(255) DEFAULT NULL COMMENT '特色',
  `recommend_reasons` varchar(255) DEFAULT NULL COMMENT '推荐理由',
  `float_delivery_fee` double DEFAULT NULL COMMENT '配送费',
  `float_minimum_order_amount` double(255,2) DEFAULT NULL COMMENT '起送价',
  `data_source` char(1) DEFAULT NULL COMMENT 'E:饿了么',
  `data_source_t` varchar(12) DEFAULT NULL COMMENT 'web android iphone',
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------

-- ----------------------------
-- Table structure for shop_history
-- ----------------------------
DROP TABLE IF EXISTS `shop_history`;
CREATE TABLE `shop_history` (
  `shopId` bigint(20) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `authentic_id` varchar(64) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `opening_hours` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL COMMENT '评分',
  `rating_count` int(10) unsigned DEFAULT NULL COMMENT '评分次数',
  `recent_order_num` int(10) unsigned DEFAULT NULL COMMENT '订单数',
  `supports` varchar(255) DEFAULT NULL COMMENT '支持项',
  `order_lead_time` int(11) DEFAULT NULL COMMENT '订单平均时间',
  `is_new` double(255,0) DEFAULT NULL COMMENT '新店',
  `is_premium` double DEFAULT NULL COMMENT '保险',
  `is_invoice` double(255,0) DEFAULT NULL COMMENT '发票',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `flavors` varchar(255) DEFAULT NULL COMMENT '特色',
  `recommend_reasons` varchar(255) DEFAULT NULL COMMENT '推荐理由',
  `float_delivery_fee` double DEFAULT NULL COMMENT '配送费',
  `float_minimum_order_amount` double(255,2) DEFAULT NULL COMMENT '起送价',
  `data_source` char(1) DEFAULT NULL COMMENT 'E:饿了么',
  `data_source_t` varchar(12) DEFAULT NULL COMMENT 'web android iphone',
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_history
-- ----------------------------

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `taskId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `taskName` varchar(255) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `lastRuntime` datetime DEFAULT NULL,
  `state` char(1) NOT NULL,
  `cron` varchar(255) NOT NULL,
  `runIp` varchar(32) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `district` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
