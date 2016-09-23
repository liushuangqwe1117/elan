/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50537
Source Host           : 127.0.0.1:3306
Source Database       : elan

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-09-23 15:47:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for busi_accessory
-- ----------------------------
DROP TABLE IF EXISTS `busi_accessory`;
CREATE TABLE `busi_accessory` (
  `ID` char(32) NOT NULL COMMENT '主键',
  `CODE` varchar(64) DEFAULT NULL COMMENT '编号',
  `CATEGORY` char(32) DEFAULT NULL COMMENT '种类',
  `STYLE` char(32) DEFAULT NULL COMMENT '款式',
  `MATERIAL` char(32) DEFAULT NULL COMMENT '材质',
  `NSIZE` varchar(32) DEFAULT NULL COMMENT '尺寸',
  `WEIGHT` char(32) DEFAULT NULL COMMENT '重量',
  `MARKET_PRICE` decimal(18,0) DEFAULT NULL COMMENT '市场定价',
  `DISCOUNT` varchar(16) DEFAULT NULL COMMENT '代理折扣',
  `PROXY_PRICE` decimal(18,0) DEFAULT NULL COMMENT '代理价格',
  `MIN_PIC` varchar(32) DEFAULT NULL COMMENT '缩略图',
  `MAX_PIC` varchar(32) DEFAULT NULL COMMENT '大图片',
  `INVENTORY` decimal(18,0) DEFAULT NULL COMMENT '库存',
  `REMARK` varchar(1024) DEFAULT NULL COMMENT '备注',
  `NORDER` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busi_accessory
-- ----------------------------
INSERT INTO `busi_accessory` VALUES ('2b611384b330494885a4cb41078b7a8e', 'A0002', 'bcb1fabdc2ac4f27a87ec4bd21b082b5', '87907de947144a079244b711afa52033', 'c584051bc18144c5ab1dbc7e1dcf7db3', '23', 'f5300b76deb346768fc3cd832993c77e', '234', null, null, null, '3f196e3a859641f58ff9941a90aa875e', '23', '', null, '2016-09-21 22:51:06');
INSERT INTO `busi_accessory` VALUES ('6cc4ae89f1e34c7ebf63ad50417f8291', 'a0003', '5da6aac503594fd5ad1161407019d200', '87907de947144a079244b711afa52033', 'c584051bc18144c5ab1dbc7e1dcf7db3', '23', 'f5300b76deb346768fc3cd832993c77e', '2323', null, null, null, '802e0656c54340daa896557861dcd253', '23', '', '3', '2016-09-21 22:58:45');
INSERT INTO `busi_accessory` VALUES ('af5483f8683241ca8f425b236e0a65c8', 'A0001', '5da6aac503594fd5ad1161407019d200', '87907de947144a079244b711afa52033', 'c584051bc18144c5ab1dbc7e1dcf7db3', '12', 'f5300b76deb346768fc3cd832993c77e', '232', null, null, null, 'd610dd61cadd42a496e0a39d63ed4374', '23', '', '1', '2016-09-18 23:26:51');

-- ----------------------------
-- Table structure for busi_file
-- ----------------------------
DROP TABLE IF EXISTS `busi_file`;
CREATE TABLE `busi_file` (
  `ID` char(32) NOT NULL COMMENT '主键',
  `FILE_NAME` varchar(128) NOT NULL COMMENT '文件名称',
  `FILE_SIZE` decimal(18,0) DEFAULT NULL COMMENT '文件大小',
  `FILE_LOCATION` varchar(128) DEFAULT NULL COMMENT '文件路径',
  `FILE_TYPE` varchar(32) DEFAULT NULL COMMENT '文件类型',
  `FILE_EXT` varchar(32) DEFAULT NULL COMMENT '文件扩展名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busi_file
-- ----------------------------
INSERT INTO `busi_file` VALUES ('3f196e3a859641f58ff9941a90aa875e', '2e7b0a93-2da2-437e-adde-ffba0cfbe218.jpg', '8791', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('6c23c00eb54a407d90c1170b35fe8b4a', '2cf2f1f9-df47-47bc-be52-c5d70af53778.jpg', '8250', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('802e0656c54340daa896557861dcd253', '2e7b0a93-2da2-437e-adde-ffba0cfbe218.jpg', '8791', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('8bdd87eb9085474da74382f65d91fa4c', '1c9cc5cf-4ef5-4474-b4ae-7b2f1efa88f0.jpg', '15895', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('ab58db1b63984a77bcae73303312b8ca', 'e71099af-8a08-45f5-af16-4e8edf2f1bff.jpg', '19527', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('b03a1a11816347388fe5478b50b2b561', '01d5dc75-9e5d-405f-bf93-5793fec72e24.jpg', '9191', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('d0e28086e6414ddd9151807e357c663f', '2e7b0a93-2da2-437e-adde-ffba0cfbe218.jpg', '8791', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('d4fe7f8599164345b91d3857f906be9f', '04ef50d9-97f0-4fea-8359-ee21376df392.jpg', '20663', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('d610dd61cadd42a496e0a39d63ed4374', '0da8eb94-0159-4700-a6a5-bc007da5a853.jpg', '241753', '', 'image/jpeg', '.jpg');
INSERT INTO `busi_file` VALUES ('e03059f6b6fb4b4a9b2cc7da61de0839', '04ef50d9-97f0-4fea-8359-ee21376df392.jpg', '20663', '', 'image/jpeg', '.jpg');

-- ----------------------------
-- Table structure for busi_goods
-- ----------------------------
DROP TABLE IF EXISTS `busi_goods`;
CREATE TABLE `busi_goods` (
  `ID` char(32) NOT NULL COMMENT '主键',
  `CODE` varchar(64) NOT NULL COMMENT '编号',
  `CATEGORY` char(32) DEFAULT NULL COMMENT '种类',
  `STYLE` char(32) DEFAULT NULL COMMENT '款式',
  `MARKET_PRICE` decimal(18,0) DEFAULT NULL COMMENT '市场定价',
  `DISCOUNT` varchar(16) DEFAULT NULL COMMENT '代理折扣',
  `PROXY_PRICE` decimal(18,0) DEFAULT NULL COMMENT '代理价格',
  `MIN_PIC` varchar(32) DEFAULT NULL COMMENT '缩略图',
  `MAX_PIC` varchar(32) DEFAULT NULL COMMENT '大图片',
  `INVENTORY` decimal(18,0) DEFAULT NULL COMMENT '库存',
  `REMARK` varchar(1024) DEFAULT NULL COMMENT '备注',
  `NORDER` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `SPECIAL` decimal(1,0) DEFAULT NULL COMMENT '是否特价品:0-非特价,1-特价',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busi_goods
-- ----------------------------
INSERT INTO `busi_goods` VALUES ('19b28027115a4863963384086a10a650', 'G0001', 'd0c9caa03b5443b6bcf78bfb41ad1c5a', 'c7e8fd4c90684499bba8abab30c71c2d', '2314', null, null, null, 'd4fe7f8599164345b91d3857f906be9f', '12', '', '1', null, '2016-09-17 17:26:51');

-- ----------------------------
-- Table structure for busi_pearl
-- ----------------------------
DROP TABLE IF EXISTS `busi_pearl`;
CREATE TABLE `busi_pearl` (
  `ID` char(32) NOT NULL COMMENT '主键',
  `CODE` varchar(64) DEFAULT NULL COMMENT '编号',
  `NAME` char(32) DEFAULT NULL COMMENT '名称',
  `NLEVEL` char(32) DEFAULT NULL COMMENT '级别',
  `POINT_ONE` char(32) DEFAULT NULL COMMENT '点位',
  `POINT_TWO` char(32) DEFAULT NULL,
  `CIRCLE` char(32) DEFAULT NULL COMMENT '圆度',
  `QUALITY` char(32) DEFAULT NULL COMMENT '质量',
  `LUMINOSITY` char(32) DEFAULT NULL COMMENT '光度',
  `CATEGORY` char(32) DEFAULT NULL COMMENT '种类',
  `PROD_PLACE` char(32) DEFAULT NULL COMMENT '产地',
  `MARKET_PRICE` decimal(18,0) DEFAULT NULL COMMENT '市场定价',
  `DISCOUNT` varchar(16) DEFAULT NULL COMMENT '代理折扣',
  `PROXY_PRICE` decimal(18,0) DEFAULT NULL COMMENT '代理价格',
  `MIN_PIC` varchar(32) DEFAULT NULL COMMENT '缩略图',
  `MAX_PIC` varchar(32) DEFAULT NULL COMMENT '大图片',
  `INVENTORY` decimal(18,0) DEFAULT NULL COMMENT '库存',
  `REMARK` varchar(1024) DEFAULT NULL COMMENT '备注',
  `NORDER` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busi_pearl
-- ----------------------------
INSERT INTO `busi_pearl` VALUES ('8a2a938e0da64c5e8d1d623b6bcf561c', 'P0004', null, 'd2fcb2b232b54caa9dcb3692b0af4b01', 'cf8b5c7b6a084c6c88939f89109b30a8', '060030b50181486f936f1f4a207d6d89', '5c9b28e69180412ebda04ae1efd19483', '7486c25f0ac84577b156bd7e0f1808b6', '2c215b7b960b44f78596994d2bbdb447', 'ee715b71bcb645de93956be055a2434a', '9fc533c94a504fe19ef03b5e0a73fd5b', '1250', null, null, null, '8bdd87eb9085474da74382f65d91fa4c', '100', '', '1', '2016-09-15 19:05:07');
INSERT INTO `busi_pearl` VALUES ('a5888117bc3344248958d995a169f574', 'P0002', null, 'd2fcb2b232b54caa9dcb3692b0af4b01', 'cf8b5c7b6a084c6c88939f89109b30a8', '060030b50181486f936f1f4a207d6d89', '5c9b28e69180412ebda04ae1efd19483', '7486c25f0ac84577b156bd7e0f1808b6', '2c215b7b960b44f78596994d2bbdb447', '38c9d7c50a134bf98dd0d064aa6b1229', '9fc533c94a504fe19ef03b5e0a73fd5b', '2312', null, null, null, 'b03a1a11816347388fe5478b50b2b561', '100', '', '1', '2016-09-15 19:04:07');
INSERT INTO `busi_pearl` VALUES ('d4f247e2b7e2489ab8bf19f4b192069c', 'P0001', null, 'd2fcb2b232b54caa9dcb3692b0af4b01', 'cf8b5c7b6a084c6c88939f89109b30a8', '060030b50181486f936f1f4a207d6d89', '5c9b28e69180412ebda04ae1efd19483', '7486c25f0ac84577b156bd7e0f1808b6', '2c215b7b960b44f78596994d2bbdb447', '38c9d7c50a134bf98dd0d064aa6b1229', '9fc533c94a504fe19ef03b5e0a73fd5b', '1250', null, null, null, 'ab58db1b63984a77bcae73303312b8ca', '100', '测试', '1', '2016-09-15 18:17:24');
INSERT INTO `busi_pearl` VALUES ('d63befb185eb4e0c804b72362521cb50', 'P0005', null, 'd2fcb2b232b54caa9dcb3692b0af4b01', 'cf8b5c7b6a084c6c88939f89109b30a8', 'c92fe00581de42c7838d0314951791dd', '5c9b28e69180412ebda04ae1efd19483', '7486c25f0ac84577b156bd7e0f1808b6', '2c215b7b960b44f78596994d2bbdb447', 'ee715b71bcb645de93956be055a2434a', '9fc533c94a504fe19ef03b5e0a73fd5b', '233', null, null, null, 'e03059f6b6fb4b4a9b2cc7da61de0839', '23', '2', '1', '2016-09-18 09:39:26');
INSERT INTO `busi_pearl` VALUES ('dd3878e24a1a449cae022b6fc86df3d6', 'P0003', null, 'd2fcb2b232b54caa9dcb3692b0af4b01', 'cf8b5c7b6a084c6c88939f89109b30a8', '060030b50181486f936f1f4a207d6d89', '5c9b28e69180412ebda04ae1efd19483', '7486c25f0ac84577b156bd7e0f1808b6', '2c215b7b960b44f78596994d2bbdb447', '38c9d7c50a134bf98dd0d064aa6b1229', '9fc533c94a504fe19ef03b5e0a73fd5b', '1250', null, null, null, 'd0e28086e6414ddd9151807e357c663f', '100', '', '1', '2016-09-15 19:04:47');

-- ----------------------------
-- Table structure for busi_product_property
-- ----------------------------
DROP TABLE IF EXISTS `busi_product_property`;
CREATE TABLE `busi_product_property` (
  `ID` char(32) NOT NULL COMMENT '主键',
  `TYPE` varchar(32) DEFAULT NULL COMMENT '类型',
  `NAME` varchar(128) DEFAULT NULL COMMENT '名称',
  `SHOW` decimal(1,0) unsigned zerofill DEFAULT NULL COMMENT '是否显示：0-显示，1-不显示',
  `NORD` decimal(6,0) unsigned zerofill DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busi_product_property
-- ----------------------------
INSERT INTO `busi_product_property` VALUES ('060030b50181486f936f1f4a207d6d89', 'PEARL_POINT2', '4', null, '000001');
INSERT INTO `busi_product_property` VALUES ('0d3002c8d69841b7aefca042d4e09a70', 'ACCESSORY_CATEGORY', '花链', null, '000007');
INSERT INTO `busi_product_property` VALUES ('2c215b7b960b44f78596994d2bbdb447', 'PEARL_NUMINOSITY', '强光', null, '000001');
INSERT INTO `busi_product_property` VALUES ('38c9d7c50a134bf98dd0d064aa6b1229', 'PEARL_CATEGORY', '耳环', null, '000003');
INSERT INTO `busi_product_property` VALUES ('39ec165ea66b42b4a4f672530b609c68', 'ACCESSORY_CATEGORY', '戒指', null, '000006');
INSERT INTO `busi_product_property` VALUES ('5c9b28e69180412ebda04ae1efd19483', 'PEARL_CIRCLE', '椭圆', null, '000001');
INSERT INTO `busi_product_property` VALUES ('5da6aac503594fd5ad1161407019d200', 'ACCESSORY_CATEGORY', '项链', null, '000001');
INSERT INTO `busi_product_property` VALUES ('7486c25f0ac84577b156bd7e0f1808b6', 'PEARL_QUALITY', '无暇', null, '000001');
INSERT INTO `busi_product_property` VALUES ('87907de947144a079244b711afa52033', 'ACCESSORY_STYLE', '迷你款', null, '000001');
INSERT INTO `busi_product_property` VALUES ('9fc533c94a504fe19ef03b5e0a73fd5b', 'PEARL_PROD_PLACE', '澳洲', null, '000001');
INSERT INTO `busi_product_property` VALUES ('b2255e665b744d26b4633453caf5a61b', 'ACCESSORY_CATEGORY', '手链', null, '000004');
INSERT INTO `busi_product_property` VALUES ('b3811621c54f4ba8bd28a1bba7f2c842', 'ACCESSORY_CATEGORY', '头饰', null, '000008');
INSERT INTO `busi_product_property` VALUES ('bcb1fabdc2ac4f27a87ec4bd21b082b5', 'ACCESSORY_CATEGORY', '耳环', null, '000003');
INSERT INTO `busi_product_property` VALUES ('c584051bc18144c5ab1dbc7e1dcf7db3', 'ACCESSORY_METERIAL', '电镀白', null, '000001');
INSERT INTO `busi_product_property` VALUES ('c7e8fd4c90684499bba8abab30c71c2d', 'GOODS_STYLE', '手链爆款', null, '000001');
INSERT INTO `busi_product_property` VALUES ('c92fe00581de42c7838d0314951791dd', 'PEARL_POINT2', '3.5', null, '000001');
INSERT INTO `busi_product_property` VALUES ('cf8b5c7b6a084c6c88939f89109b30a8', 'PEARL_POINT1', '3', null, '000001');
INSERT INTO `busi_product_property` VALUES ('d0c9caa03b5443b6bcf78bfb41ad1c5a', 'GOODS_CATEGORY', '大溪地珠', null, '000001');
INSERT INTO `busi_product_property` VALUES ('d2fcb2b232b54caa9dcb3692b0af4b01', 'PEARL_NLEVEL', '粉色', null, '000001');
INSERT INTO `busi_product_property` VALUES ('e05e160e329247d1875ecf3572941cc0', 'ACCESSORY_CATEGORY', '吊坠', null, '000002');
INSERT INTO `busi_product_property` VALUES ('eb471817c2d94ad28937d7a56b450c0d', 'PEARL_POINT1', '3.5', null, '000002');
INSERT INTO `busi_product_property` VALUES ('ee715b71bcb645de93956be055a2434a', 'PEARL_CATEGORY', '海水珠', null, '000001');
INSERT INTO `busi_product_property` VALUES ('f5300b76deb346768fc3cd832993c77e', 'ACCESSORY_WEIGHT', '镶嵌锆石', null, '000001');
INSERT INTO `busi_product_property` VALUES ('f8491c66864741a2b61464c58dc06129', 'ACCESSORY_CATEGORY', '胸花', null, '000005');

-- ----------------------------
-- Table structure for sec_perm
-- ----------------------------
DROP TABLE IF EXISTS `sec_perm`;
CREATE TABLE `sec_perm` (
  `ID` varchar(64) NOT NULL COMMENT '权限主键',
  `NAME` varchar(64) NOT NULL COMMENT '权限名称',
  `NLEVEL` varchar(64) DEFAULT NULL COMMENT '权限级别',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_perm
-- ----------------------------
INSERT INTO `sec_perm` VALUES ('ACCESSORY', '配件', null);
INSERT INTO `sec_perm` VALUES ('ACCESSORY_DELETE', '配件删除', null);
INSERT INTO `sec_perm` VALUES ('ACCESSORY_EDIT', '配件编辑', null);
INSERT INTO `sec_perm` VALUES ('BASE_INFO', '基础信息', null);
INSERT INTO `sec_perm` VALUES ('GOODS', '成品', null);
INSERT INTO `sec_perm` VALUES ('GOODS_DELETE', '成品删除', null);
INSERT INTO `sec_perm` VALUES ('GOODS_EDIT', '成品编辑', null);
INSERT INTO `sec_perm` VALUES ('PEARL', '珍珠', null);
INSERT INTO `sec_perm` VALUES ('PEARL_DELETE', '珍珠删除', null);
INSERT INTO `sec_perm` VALUES ('PEARL_EDIT', '珍珠编辑', null);
INSERT INTO `sec_perm` VALUES ('PRODUCT', '产品', null);
INSERT INTO `sec_perm` VALUES ('USER_LIST', '用户列表', null);

-- ----------------------------
-- Table structure for sec_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_perm`;
CREATE TABLE `sec_role_perm` (
  `ROLE_ID` varchar(32) NOT NULL,
  `PERM_ID` varchar(64) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`PERM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_role_perm
-- ----------------------------
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'ACCESSORY');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'ACCESSORY_DELETE');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'ACCESSORY_EDIT');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'BASE_INFO');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'GOODS');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'GOODS_DELETE');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'GOODS_EDIT');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'PEARL');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'PEARL_DELETE');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'PEARL_EDIT');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'PRODUCT');
INSERT INTO `sec_role_perm` VALUES ('ADMIN', 'USER_LIST');
INSERT INTO `sec_role_perm` VALUES ('NORMAL', 'ACCESSORY');
INSERT INTO `sec_role_perm` VALUES ('NORMAL', 'GOODS');
INSERT INTO `sec_role_perm` VALUES ('NORMAL', 'PEARL');
INSERT INTO `sec_role_perm` VALUES ('NORMAL', 'PRODUCT');
INSERT INTO `sec_role_perm` VALUES ('PROXY', 'ACCESSORY');
INSERT INTO `sec_role_perm` VALUES ('PROXY', 'GOODS');
INSERT INTO `sec_role_perm` VALUES ('PROXY', 'PEARL');
INSERT INTO `sec_role_perm` VALUES ('PROXY', 'PRODUCT');

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `ID` char(32) NOT NULL COMMENT '主键',
  `LOGIN_NAME` varchar(32) NOT NULL COMMENT '用户账号',
  `PASSWORD` varchar(64) NOT NULL COMMENT '用户密码',
  `REAL_NAME` varchar(64) NOT NULL COMMENT '真实姓名',
  `MOBILE` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `PHONE` varchar(16) DEFAULT NULL COMMENT '座机',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `ROLE_TYPE` varchar(64) NOT NULL COMMENT '角色类型：ADMIN-管理员，PROXY-代理商，NORMAL-普通用户',
  `STATUS` varchar(16) NOT NULL COMMENT '状态：WAIT_ACTIVE-待激活，EFFECTIVE-有效,DISABLED-停用,DELETE-注销',
  `LAST_UPDATE_PASSWORD_TIME` datetime DEFAULT NULL COMMENT '最后一次更新密码时间',
  `LOGIN_ERROR_TIMES` decimal(10,0) DEFAULT NULL COMMENT '登录错误次数',
  `LAST_LOGIN_LOCK_TIME` datetime DEFAULT NULL COMMENT '最后一次登录失败锁定时间',
  `DESCRIPTION` varchar(1024) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_user
-- ----------------------------
INSERT INTO `sec_user` VALUES ('1d1d5a37055f4e6ba45b93ccef0f238d', 'admin', '2f8fbbba55a24599d4bedc00dc3f418a', '管理员', null, null, null, 'ADMIN', 'EFFECTIVE', null, '0', null, null, '2016-08-27 22:13:17');
