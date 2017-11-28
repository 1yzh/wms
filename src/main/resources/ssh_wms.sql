/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : localhost
 Source Database       : ssh_wms

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : utf-8

 Date: 08/19/2016 23:15:57 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `brand`
-- ----------------------------
BEGIN;
INSERT INTO `brand` VALUES ('1', '苹果', 'Apple'), ('2', '华为', 'HUAWEI'), ('3', '小米', 'MI'), ('4', '锤子', 'SMARTISAN'), ('5', '360', '360'), ('6', '菲尔可', 'FILCO'), ('7', '西部数据', 'WD'), ('8', '闪迪', 'SANDISK'), ('9', '斯伯丁', 'SPALDING');
COMMIT;

-- ----------------------------
--  Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `client`
-- ----------------------------
BEGIN;
INSERT INTO `client` VALUES ('1', '小码哥Java学院', 'XMG_JAVA', '123'), ('2', '小码哥iOS学院', 'XMG_IOS', '123');
COMMIT;

-- ----------------------------
--  Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `department`
-- ----------------------------
BEGIN;
INSERT INTO `department` VALUES ('1', '总经办', 'General Deparment'), ('2', '人力资源部', 'Human Resources Department'), ('3', '采购部', 'Order Department'), ('4', '仓储部', 'Warehousing Department'), ('5', '财务部', 'Finance Department'), ('6', '技术部', 'Technolog Department '), ('7', '行政部', 'Admin Department ');
COMMIT;

-- ----------------------------
--  Table structure for `depot`
-- ----------------------------
DROP TABLE IF EXISTS `depot`;
CREATE TABLE `depot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `depot`
-- ----------------------------
BEGIN;
INSERT INTO `depot` VALUES ('1', '天河区', '天河区1号仓'), ('2', '白云区', '白云区2号仓');
COMMIT;

-- ----------------------------
--  Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jupc981pvryfs7lbgtpoy6mmh` (`dept_id`),
  CONSTRAINT `FK_jupc981pvryfs7lbgtpoy6mmh` FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `employee`
-- ----------------------------
BEGIN;
INSERT INTO `employee` VALUES ('1', 'admin', 'C4CA4238A0B923820DCC509A6F75849B', 'admin@abc.com', '20', b'1', '7'), ('2', '赵总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', b'0', '1'), ('3', '赵一明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '1'), ('4', '钱总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', b'0', '2'), ('5', '钱二明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '2'), ('6', '孙总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', b'0', '3'), ('7', '孙三明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '3'), ('8', '李总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', b'0', '4'), ('9', '李四明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '4'), ('10', '周总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', b'0', '5'), ('11', '周五明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '5'), ('12', '吴总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', b'0', '6'), ('13', '吴六明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '6'), ('14', '郑总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', b'0', '7'), ('15', '郑七明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '7'), ('16', '孙四明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '3'), ('17', '孙五明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '3'), ('18', '李五明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '4'), ('19', '李六明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', b'0', '4');
COMMIT;

-- ----------------------------
--  Table structure for `employee_roles`
-- ----------------------------
DROP TABLE IF EXISTS `employee_roles`;
CREATE TABLE `employee_roles` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK_3ene23nq9uvnj1cktmk8ydhah` (`role_id`),
  KEY `FK_3f8wqxdumr93k2hevf7cuwqxg` (`employee_id`),
  CONSTRAINT `FK_3ene23nq9uvnj1cktmk8ydhah` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_3f8wqxdumr93k2hevf7cuwqxg` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `orderbill`
-- ----------------------------
DROP TABLE IF EXISTS `orderbill`;
CREATE TABLE `orderbill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `vdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `totalNumber` decimal(19,2) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `inputUser_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c25usoo9h8v8xck8gnb5uw42p` (`inputUser_id`),
  KEY `FK_9sb894e7wg5qdlrdj00m6w64m` (`auditor_id`),
  KEY `FK_i8lhd25r9a5syjwaksxe07hgj` (`supplier_id`),
  CONSTRAINT `FK_i8lhd25r9a5syjwaksxe07hgj` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FK_9sb894e7wg5qdlrdj00m6w64m` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_c25usoo9h8v8xck8gnb5uw42p` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `orderbill`
-- ----------------------------
BEGIN;
INSERT INTO `orderbill` VALUES ('1', '001', '2016-01-01 00:00:00', '1', '646500.00', '600.00', '2016-08-19 21:31:19', '2016-08-19 20:59:07', '7', '1', '1'), ('2', '002', '2016-01-11 00:00:00', '1', '1420800.00', '720.00', '2016-08-19 21:31:23', '2016-08-19 21:01:08', '16', '1', '2'), ('3', '003', '2016-01-21 00:00:00', '1', '896500.00', '650.00', '2016-08-19 21:31:27', '2016-08-19 21:04:14', '17', '1', '3'), ('4', '004', '2016-02-05 00:00:00', '0', '1116500.00', '600.00', null, '2016-08-19 21:07:24', '7', null, '1'), ('5', '005', '2016-02-15 00:00:00', '0', '645000.00', '500.00', null, '2016-08-19 21:09:32', '16', null, '2'), ('6', '006', '2016-02-25 00:00:00', '0', '1001500.00', '550.00', null, '2016-08-19 21:11:58', '7', null, '3');
COMMIT;

-- ----------------------------
--  Table structure for `orderbillitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderbillitem`;
CREATE TABLE `orderbillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `costPrice` decimal(19,2) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1ur8cgf80jawiv49glygo4tix` (`product_id`),
  KEY `FK_i4m0hnuvwqaaw0s83l789adi0` (`bill_id`),
  CONSTRAINT `FK_i4m0hnuvwqaaw0s83l789adi0` FOREIGN KEY (`bill_id`) REFERENCES `orderbill` (`id`),
  CONSTRAINT `FK_1ur8cgf80jawiv49glygo4tix` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `orderbillitem`
-- ----------------------------
BEGIN;
INSERT INTO `orderbillitem` VALUES ('1', '200.00', '50.00', '10000.00', '', '1', '1'), ('2', '300.00', '50.00', '15000.00', '', '2', '1'), ('3', '800.00', '50.00', '40000.00', '', '3', '1'), ('4', '2000.00', '50.00', '100000.00', '', '4', '1'), ('5', '600.00', '50.00', '30000.00', '', '5', '1'), ('6', '600.00', '50.00', '30000.00', '', '6', '1'), ('7', '2000.00', '50.00', '100000.00', '', '7', '1'), ('8', '800.00', '50.00', '40000.00', '', '8', '1'), ('9', '5000.00', '50.00', '250000.00', '', '9', '1'), ('10', '80.00', '50.00', '4000.00', '', '12', '1'), ('11', '350.00', '50.00', '17500.00', '', '14', '1'), ('12', '200.00', '50.00', '10000.00', '', '16', '1'), ('13', '7000.00', '60.00', '420000.00', '', '20', '2'), ('14', '2500.00', '60.00', '150000.00', '', '19', '2'), ('15', '8000.00', '60.00', '480000.00', '', '18', '2'), ('16', '600.00', '60.00', '36000.00', '', '5', '2'), ('17', '200.00', '60.00', '12000.00', '', '1', '2'), ('18', '800.00', '60.00', '48000.00', '', '8', '2'), ('19', '600.00', '60.00', '36000.00', '', '6', '2'), ('20', '80.00', '60.00', '4800.00', '', '12', '2'), ('21', '2000.00', '60.00', '120000.00', '', '4', '2'), ('22', '200.00', '60.00', '12000.00', '', '16', '2'), ('23', '1500.00', '60.00', '90000.00', '', '15', '2'), ('24', '200.00', '60.00', '12000.00', '', '10', '2'), ('25', '200.00', '50.00', '10000.00', '', '10', '3'), ('26', '600.00', '50.00', '30000.00', '', '5', '3'), ('27', '2000.00', '50.00', '100000.00', '', '4', '3'), ('28', '200.00', '50.00', '10000.00', '', '1', '3'), ('29', '800.00', '50.00', '40000.00', '', '3', '3'), ('30', '80.00', '50.00', '4000.00', '', '12', '3'), ('31', '500.00', '50.00', '25000.00', '', '13', '3'), ('32', '1500.00', '50.00', '75000.00', '', '15', '3'), ('33', '200.00', '50.00', '10000.00', '', '16', '3'), ('34', '2500.00', '50.00', '125000.00', '', '19', '3'), ('35', '7000.00', '50.00', '350000.00', '', '20', '3'), ('36', '350.00', '50.00', '17500.00', '', '14', '3'), ('37', '2000.00', '50.00', '100000.00', '', '11', '3'), ('38', '2000.00', '50.00', '100000.00', '', '4', '4'), ('39', '200.00', '50.00', '10000.00', '', '1', '4'), ('40', '600.00', '50.00', '30000.00', '', '6', '4'), ('41', '80.00', '50.00', '4000.00', '', '12', '4'), ('42', '350.00', '50.00', '17500.00', '', '14', '4'), ('43', '7000.00', '50.00', '350000.00', '', '20', '4'), ('44', '2500.00', '50.00', '125000.00', '', '19', '4'), ('45', '6000.00', '50.00', '300000.00', '', '17', '4'), ('46', '200.00', '50.00', '10000.00', '', '10', '4'), ('47', '600.00', '50.00', '30000.00', '', '5', '4'), ('48', '800.00', '50.00', '40000.00', '', '8', '4'), ('49', '2000.00', '50.00', '100000.00', '', '7', '4'), ('50', '200.00', '50.00', '10000.00', '', '1', '5'), ('51', '600.00', '50.00', '30000.00', '', '5', '5'), ('52', '800.00', '50.00', '40000.00', '', '8', '5'), ('53', '5000.00', '50.00', '250000.00', '', '9', '5'), ('54', '500.00', '50.00', '25000.00', '', '13', '5'), ('55', '200.00', '50.00', '10000.00', '', '16', '5'), ('56', '2500.00', '50.00', '125000.00', '', '19', '5'), ('57', '800.00', '50.00', '40000.00', '', '3', '5'), ('58', '2000.00', '50.00', '100000.00', '', '7', '5'), ('59', '300.00', '50.00', '15000.00', '', '2', '5'), ('60', '200.00', '50.00', '10000.00', '', '1', '6'), ('61', '2000.00', '50.00', '100000.00', '', '4', '6'), ('62', '600.00', '50.00', '30000.00', '', '6', '6'), ('63', '5000.00', '50.00', '250000.00', '', '9', '6'), ('64', '200.00', '50.00', '10000.00', '', '10', '6'), ('65', '80.00', '50.00', '4000.00', '', '12', '6'), ('66', '1500.00', '50.00', '75000.00', '', '15', '6'), ('67', '350.00', '50.00', '17500.00', '', '14', '6'), ('68', '2500.00', '50.00', '125000.00', '', '19', '6'), ('69', '7000.00', '50.00', '350000.00', '', '20', '6'), ('70', '600.00', '50.00', '30000.00', '', '5', '6');
COMMIT;

-- ----------------------------
--  Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `expression` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `permission`
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES ('1', '部门删除', 'com._520it.wms.web.action.DepartmentAction:delete'), ('2', '部门编辑', 'com._520it.wms.web.action.DepartmentAction:input'), ('3', '部门列表', 'com._520it.wms.web.action.DepartmentAction:execute'), ('4', '部门保存/更新', 'com._520it.wms.web.action.DepartmentAction:saveOrUpdate'), ('5', '员工删除', 'com._520it.wms.web.action.EmployeeAction:delete'), ('6', '员工编辑', 'com._520it.wms.web.action.EmployeeAction:input'), ('7', '员工列表', 'com._520it.wms.web.action.EmployeeAction:execute'), ('8', '员工批量删除', 'com._520it.wms.web.action.EmployeeAction:batchDelete'), ('9', '员工保存/更新', 'com._520it.wms.web.action.EmployeeAction:saveOrUpdate'), ('10', '权限删除', 'com._520it.wms.web.action.PermissionAction:delete'), ('11', '权限列表', 'com._520it.wms.web.action.PermissionAction:execute'), ('12', '加载权限', 'com._520it.wms.web.action.PermissionAction:reload'), ('13', '角色删除', 'com._520it.wms.web.action.RoleAction:delete'), ('14', '角色编辑', 'com._520it.wms.web.action.RoleAction:input'), ('15', '角色列表', 'com._520it.wms.web.action.RoleAction:execute'), ('16', '角色保存/更新', 'com._520it.wms.web.action.RoleAction:saveOrUpdate'), ('17', '系统菜单删除', 'com._520it.wms.web.action.SystemMenuAction:delete'), ('18', '系统菜单编辑', 'com._520it.wms.web.action.SystemMenuAction:input'), ('19', '系统菜单列表', 'com._520it.wms.web.action.SystemMenuAction:execute'), ('20', '系统菜单保存/更新', 'com._520it.wms.web.action.SystemMenuAction:saveOrUpdate'), ('21', '品牌删除', 'com._520it.wms.web.action.BrandAction:delete'), ('22', '品牌编辑', 'com._520it.wms.web.action.BrandAction:input'), ('23', '品牌列表', 'com._520it.wms.web.action.BrandAction:execute'), ('24', '品牌保存/更新', 'com._520it.wms.web.action.BrandAction:saveOrUpdate'), ('25', '供应商删除', 'com._520it.wms.web.action.SupplierAction:delete'), ('26', '供应商编辑', 'com._520it.wms.web.action.SupplierAction:input'), ('27', '供应商列表', 'com._520it.wms.web.action.SupplierAction:execute'), ('28', '供应商保存/更新', 'com._520it.wms.web.action.SupplierAction:saveOrUpdate'), ('29', '货品删除', 'com._520it.wms.web.action.ProductAction:delete'), ('30', '货品编辑', 'com._520it.wms.web.action.ProductAction:input'), ('31', '货品列表', 'com._520it.wms.web.action.ProductAction:execute'), ('32', '货品选择列表', 'com._520it.wms.web.action.ProductAction:selectProductList'), ('33', '货品保存/更新', 'com._520it.wms.web.action.ProductAction:saveOrUpdate'), ('34', '采购订单删除', 'com._520it.wms.web.action.OrderBillAction:delete'), ('35', '采购订单编辑', 'com._520it.wms.web.action.OrderBillAction:input'), ('36', '采购订单列表', 'com._520it.wms.web.action.OrderBillAction:execute'), ('37', '采购订单审核', 'com._520it.wms.web.action.OrderBillAction:audit'), ('38', '采购订单查看', 'com._520it.wms.web.action.OrderBillAction:show'), ('39', '采购订单保存/更新', 'com._520it.wms.web.action.OrderBillAction:saveOrUpdate'), ('40', '仓库删除', 'com._520it.wms.web.action.DepotAction:delete'), ('41', '仓库编辑', 'com._520it.wms.web.action.DepotAction:input'), ('42', '仓库列表', 'com._520it.wms.web.action.DepotAction:execute'), ('43', '仓库保存/更新', 'com._520it.wms.web.action.DepotAction:saveOrUpdate'), ('44', '到货入库删除', 'com._520it.wms.web.action.StockIncomeBillAction:delete'), ('45', '到货入库编辑', 'com._520it.wms.web.action.StockIncomeBillAction:input'), ('46', '到货入库列表', 'com._520it.wms.web.action.StockIncomeBillAction:execute'), ('47', '到货入库审核', 'com._520it.wms.web.action.StockIncomeBillAction:audit'), ('48', '到货入库查看', 'com._520it.wms.web.action.StockIncomeBillAction:show'), ('49', '到货入库保存/更新', 'com._520it.wms.web.action.StockIncomeBillAction:saveOrUpdate'), ('50', '销售出库删除', 'com._520it.wms.web.action.StockOutcomeBillAction:delete'), ('51', '销售出库编辑', 'com._520it.wms.web.action.StockOutcomeBillAction:input'), ('52', '销售出库列表', 'com._520it.wms.web.action.StockOutcomeBillAction:execute'), ('53', '销售出库审核', 'com._520it.wms.web.action.StockOutcomeBillAction:audit'), ('54', '销售出库查看', 'com._520it.wms.web.action.StockOutcomeBillAction:show'), ('55', '销售出库保存/更新', 'com._520it.wms.web.action.StockOutcomeBillAction:saveOrUpdate'), ('56', '客户删除', 'com._520it.wms.web.action.ClientAction:delete'), ('57', '客户列表', 'com._520it.wms.web.action.ClientAction:execute'), ('58', '客户编辑', 'com._520it.wms.web.action.ClientAction:input'), ('59', '客户保存/更新', 'com._520it.wms.web.action.ClientAction:saveOrUpdate'), ('60', '即时库存报表', 'com._520it.wms.web.action.ProductStockAction:execute'), ('61', '订货报表', 'com._520it.wms.web.action.ChartAction:orderChart'), ('62', '订货报表线形图', 'com._520it.wms.web.action.ChartAction:showOrderChart'), ('63', '销售报表', 'com._520it.wms.web.action.ChartAction:saleChart');
COMMIT;

-- ----------------------------
--  Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `costPrice` decimal(19,2) DEFAULT NULL,
  `salePrice` decimal(19,2) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1td6gorl25rsvufiiive2svlx` (`brand_id`),
  CONSTRAINT `FK_1td6gorl25rsvufiiive2svlx` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `product`
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES ('1', '360儿童手表', '001', '200.00', '400.00', '/upload/1e0fceec-6336-4ee8-9546-254fb7d42ca3.jpg', '360儿童手表 巴迪龙儿童手表5 W563 360儿童卫士 智能彩屏电话手表 静谧蓝', '5'), ('2', '360智能摄像机', '002', '300.00', '500.00', '/upload/eba39186-e82b-4fd0-8adf-4b88b2f4dedf.jpg', '360智能摄像机夜视版 D503 小水滴 家用网络摄像机 无线WiFi摄像头 手机远程监控 夜视版标', '5'), ('3', '360N4S手机', '003', '800.00', '900.00', '/upload/8a0f3bf1-3ee5-431e-9475-8de604933013.jpg', '360n4s手机 流光金 全网通4G(4G RAM+32G ROM)标准版', '5'), ('4', '锤子(smartisan) T2手机', '004', '2000.00', '2800.00', '/upload/70704b20-8661-4fd8-821e-8e2f0605636c.jpg', '锤子(smartisan) T2 4G手机 黑色 全网通(3G RAM+16G ROM)标配', '4'), ('5', '锤子坚果手机', '005', '600.00', '800.00', '/upload/ba5ecc4c-ce9f-4dc0-b141-0dfe2c5f0adc.jpg', '锤子坚果手机4G手机 红色 全网通(2G RAM+32G ROM)标配', '4'), ('6', '斐尔可（FILCO）机械键盘', '006', '600.00', '1000.00', '/upload/10676642-22c0-4be3-9efa-8a25edb7dc67.jpg', '斐尔可（FILCO）「87圣手二代」机械键盘 黑色 茶轴 ', '6'), ('7', '华为荣耀V8手机', '007', '2000.00', '2500.00', '/upload/4f49a852-b70b-4354-b771-6b39e987ff3f.jpg', '华为 荣耀 V8 4GB+32GB 铂光金 移动联通4G手机 双卡双待双通', '2'), ('8', '华为 手环B3 ', '008', '800.00', '1200.00', '/upload/6934febd-f63f-436b-ab7b-3bd83e263174.jpg', '华为 手环B3 (蓝牙耳机与智能手环结合+金属机身+触控屏幕+真皮腕带) 商务版 摩卡棕', '2'), ('9', '华为 MateBook 12英寸平板笔记本电脑', '009', '5000.00', '6500.00', '/upload/e4f6e5da-ca36-4e9d-a353-a84fbf877410.jpg', '华为 MateBook 12英寸平板二合一笔记本电脑 (Intel core m5 4G内存 128G存储', '2'), ('10', '华为盒子', '010', '200.00', '350.00', '/upload/2d0a679b-79ab-48d9-ba6b-cea538b9773f.jpg', '华为 MediaQ M330华为盒子真4K高清网络播放器带蓝牙增强版', '2'), ('11', '华为 P9手机', '011', '2000.00', '3500.00', '/upload/bd7c3020-b543-40da-ae73-7b2223308903.jpg', '华为 P9 全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待', '2'), ('12', '闪迪（SanDisk）至尊高速3.0U盘', '012', '80.00', '150.00', '/upload/2319866c-6023-4c4f-9d5b-1282ac4a80fe.jpg', '闪迪（SanDisk）至尊高速（CZ48） 256GB USB3.0 U盘 读100MBs 写40MBs', '8'), ('13', '斯伯丁NBA篮球', '013', '500.00', '800.00', '/upload/fbca9b1b-63b6-4be3-b793-a805bd2b7ff1.jpg', '斯伯丁 NBA金色经典系列 耐磨防滑 室内外兼用 篮球', '9'), ('14', '西部数据（WD）移动硬盘', '014', '350.00', '500.00', '/upload/774daaf8-86e6-4d0b-b0a1-8044244f0220.jpg', '西部数据（WD）My Passport Ultra 升级版 1TB 2.5英寸 野莓红 移动硬盘', '7'), ('15', '小米(MI) 小米5手机', '015', '1500.00', '2000.00', '/upload/26c924f4-60ac-4d2d-b428-65ecdf9c25cb.jpg', '小米(MI) 小米5 全网通4G手机 双卡双待 黑色 尊享版(4G RAM+128G ROM) 标配', '3'), ('16', '小米（MI）蓝牙音箱', '016', '200.00', '250.00', '/upload/ca5a5d50-3f4a-4343-aabc-b5cbb597341c.jpg', '小米（MI）蓝牙音箱 音响 无线迷你便携音箱 车载音箱 支持SD卡 免提通话（蓝色）', '3'), ('17', '小米（MI）智能4K分体电视', '017', '6000.00', '8000.00', '/upload/e0d1a76d-87dd-4a15-90d1-56af1d3280f6.jpg', '小米（MI）L70M4-AA 70英寸 智能4K分体电视', '3'), ('18', '苹果Apple iMac 21.5英寸一体机(', '018', '8000.00', '11000.00', '/upload/848b9edb-1172-4abb-a6e2-284267475d01.jpg', 'Apple iMac 21.5英寸一体机(四核 Core i5 处理器8GB内存1TB存储Retina 4K屏).jpg', '1'), ('19', '苹果Apple iPad Air 2 平板电脑 9', '019', '2500.00', '3500.00', '/upload/4b76d10e-f286-4048-842a-8f5bd18b75fe.jpg', 'Apple iPad Air 2 平板电脑 9.7英寸（64G WLAN版A8X 芯片Retina显示屏Touch ID技术 MH182CH）金色', '1'), ('20', '苹果Apple MacBook 12英寸笔记本电脑 玫瑰金色', '020', '7000.00', '10000.00', '/upload/2ba88fd9-6f2e-478e-be32-d89294d4bada.jpg', 'Apple MacBook 12英寸笔记本电脑 玫瑰金色 256GB闪存', '1');
COMMIT;

-- ----------------------------
--  Table structure for `productstock`
-- ----------------------------
DROP TABLE IF EXISTS `productstock`;
CREATE TABLE `productstock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `storeNumber` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `incomeDate` datetime DEFAULT NULL,
  `outcomeDate` datetime DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_odeqlqoefmw21daq1smn4c7gy` (`product_id`),
  KEY `FK_q8smstvleeay56k9yew812e9h` (`depot_id`),
  CONSTRAINT `FK_q8smstvleeay56k9yew812e9h` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  CONSTRAINT `FK_odeqlqoefmw21daq1smn4c7gy` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `productstock`
-- ----------------------------
BEGIN;
INSERT INTO `productstock` VALUES ('1', '200.00', '50.00', '10000.00', '2016-08-19 21:27:47', '2016-08-19 21:51:03', '1', '1'), ('2', '300.00', '50.00', '15000.00', '2016-08-19 21:27:47', null, '2', '1'), ('3', '800.00', '110.00', '88000.00', '2016-08-19 21:27:47', null, '3', '1'), ('4', '2000.00', '50.00', '100000.00', '2016-08-19 21:27:47', '2016-08-19 21:51:03', '4', '1'), ('5', '600.00', '110.00', '66000.00', '2016-08-19 21:27:47', null, '5', '1'), ('6', '600.00', '80.00', '48000.00', '2016-08-19 21:27:47', '2016-08-19 21:48:32', '6', '1'), ('7', '2000.00', '80.00', '160000.00', '2016-08-19 21:27:47', '2016-08-19 21:48:32', '7', '1'), ('8', '800.00', '50.00', '40000.00', '2016-08-19 21:27:47', '2016-08-19 21:51:03', '8', '1'), ('9', '200.00', '50.00', '10000.00', '2016-08-19 21:27:47', null, '10', '1'), ('10', '2000.00', '100.00', '200000.00', '2016-08-19 21:27:47', null, '11', '1'), ('11', '80.00', '80.00', '6400.00', '2016-08-19 21:27:47', '2016-08-19 21:48:32', '12', '1'), ('12', '350.00', '100.00', '35000.00', '2016-08-19 21:27:47', '2016-08-19 21:51:03', '14', '1'), ('13', '1500.00', '70.00', '105000.00', '2016-08-19 21:27:47', '2016-08-19 21:51:03', '15', '1'), ('14', '7000.00', '130.00', '910000.00', '2016-08-19 21:27:47', '2016-08-19 21:51:03', '20', '1'), ('15', '200.00', '50.00', '10000.00', '2016-08-19 21:27:47', '2016-08-19 21:51:03', '16', '1'), ('16', '7000.00', '20.00', '140000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '20', '2'), ('17', '2500.00', '50.00', '125000.00', '2016-08-19 21:27:48', null, '19', '2'), ('18', '200.00', '50.00', '10000.00', '2016-08-19 21:27:48', null, '16', '2'), ('19', '350.00', '20.00', '7000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '14', '2'), ('20', '600.00', '50.00', '30000.00', '2016-08-19 21:27:48', null, '6', '2'), ('21', '80.00', '20.00', '1600.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '12', '2'), ('22', '2000.00', '20.00', '40000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '4', '2'), ('23', '800.00', '50.00', '40000.00', '2016-08-19 21:27:48', null, '3', '2'), ('24', '2000.00', '20.00', '40000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '7', '2'), ('25', '200.00', '20.00', '4000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '10', '2'), ('26', '500.00', '20.00', '10000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '13', '2'), ('27', '200.00', '50.00', '10000.00', '2016-08-19 21:27:48', null, '1', '2'), ('28', '300.00', '20.00', '6000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '2', '2'), ('29', '600.00', '50.00', '30000.00', '2016-08-19 21:27:48', null, '5', '2'), ('30', '1500.00', '20.00', '30000.00', '2016-08-19 21:27:48', '2016-08-19 21:43:41', '15', '2'), ('31', '2500.00', '80.00', '200000.00', '2016-08-19 21:48:12', '2016-08-19 21:48:32', '19', '1'), ('32', '500.00', '60.00', '30000.00', '2016-08-19 21:48:12', null, '13', '1'), ('33', '5000.00', '20.00', '100000.00', '2016-08-19 21:50:51', '2016-08-19 21:51:03', '9', '1');
COMMIT;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('1', '人事管理', 'HR MGR'), ('2', '采购管理', 'ORDER MGR'), ('3', '仓储管理', 'WAREHOUSING MGR'), ('4', '行政部管理', 'Admin MGR');
COMMIT;

-- ----------------------------
--  Table structure for `role_menus`
-- ----------------------------
DROP TABLE IF EXISTS `role_menus`;
CREATE TABLE `role_menus` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  KEY `FK_n9rxuwvg9qeaxqfchovifd9hw` (`menu_id`),
  KEY `FK_fiaxn00niduy5wvs9jyk661f1` (`role_id`),
  CONSTRAINT `FK_fiaxn00niduy5wvs9jyk661f1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_n9rxuwvg9qeaxqfchovifd9hw` FOREIGN KEY (`menu_id`) REFERENCES `systemmenu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `role_menus`
-- ----------------------------
BEGIN;
INSERT INTO `role_menus` VALUES ('1', '4'), ('1', '5'), ('4', '4'), ('4', '5'), ('4', '6'), ('4', '7'), ('4', '8'), ('3', '12'), ('3', '13'), ('3', '15'), ('3', '16'), ('3', '17'), ('3', '18'), ('3', '19'), ('2', '9'), ('2', '10'), ('2', '11'), ('2', '14'), ('2', '17'), ('2', '18');
COMMIT;

-- ----------------------------
--  Table structure for `role_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FK_qfkbccnh2c5o4tc7akq5x11wv` (`permission_id`),
  KEY `FK_d4atqq8ege1sij0316vh2mxfu` (`role_id`),
  CONSTRAINT `FK_d4atqq8ege1sij0316vh2mxfu` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_qfkbccnh2c5o4tc7akq5x11wv` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `role_permissions`
-- ----------------------------
BEGIN;
INSERT INTO `role_permissions` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'), ('1', '6'), ('1', '7'), ('1', '8'), ('1', '9'), ('4', '1'), ('4', '2'), ('4', '3'), ('4', '4'), ('4', '5'), ('4', '6'), ('4', '7'), ('4', '8'), ('4', '9'), ('4', '10'), ('4', '11'), ('4', '12'), ('4', '13'), ('4', '14'), ('4', '15'), ('4', '16'), ('4', '17'), ('4', '18'), ('4', '19'), ('4', '20'), ('3', '40'), ('3', '41'), ('3', '42'), ('3', '43'), ('3', '44'), ('3', '45'), ('3', '46'), ('3', '47'), ('3', '48'), ('3', '49'), ('3', '50'), ('3', '51'), ('3', '52'), ('3', '53'), ('3', '54'), ('3', '55'), ('3', '56'), ('3', '57'), ('3', '58'), ('3', '59'), ('3', '60'), ('3', '61'), ('3', '62'), ('3', '63'), ('2', '34'), ('2', '35'), ('2', '36'), ('2', '37'), ('2', '38'), ('2', '39'), ('2', '60'), ('2', '61'), ('2', '62');
COMMIT;

-- ----------------------------
--  Table structure for `saleaccount`
-- ----------------------------
DROP TABLE IF EXISTS `saleaccount`;
CREATE TABLE `saleaccount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vdate` datetime DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `costPrice` decimal(19,2) DEFAULT NULL,
  `costAmount` decimal(19,2) DEFAULT NULL,
  `salePrice` decimal(19,2) DEFAULT NULL,
  `saleAmount` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `saleman_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5ie3vrst6b6favmphi2lo8qgo` (`product_id`),
  KEY `FK_9ghkciknv2w9g3hmec7ks1517` (`saleman_id`),
  KEY `FK_1irbvbygho7ysu47gvd97xhh5` (`client_id`),
  CONSTRAINT `FK_1irbvbygho7ysu47gvd97xhh5` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FK_5ie3vrst6b6favmphi2lo8qgo` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_9ghkciknv2w9g3hmec7ks1517` FOREIGN KEY (`saleman_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `saleaccount`
-- ----------------------------
BEGIN;
INSERT INTO `saleaccount` VALUES ('29', '2016-07-11 00:00:00', '30.00', '300.00', '9000.00', '500.00', '15000.00', '2', '1', '1'), ('30', '2016-07-11 00:00:00', '30.00', '7000.00', '210000.00', '10000.00', '300000.00', '20', '1', '1'), ('31', '2016-07-11 00:00:00', '30.00', '350.00', '10500.00', '500.00', '15000.00', '14', '1', '1'), ('32', '2016-07-11 00:00:00', '30.00', '2000.00', '60000.00', '2800.00', '84000.00', '4', '1', '1'), ('33', '2016-07-11 00:00:00', '30.00', '2000.00', '60000.00', '2500.00', '75000.00', '7', '1', '1'), ('34', '2016-07-11 00:00:00', '30.00', '200.00', '6000.00', '350.00', '10500.00', '10', '1', '1'), ('35', '2016-07-11 00:00:00', '30.00', '500.00', '15000.00', '800.00', '24000.00', '13', '1', '1'), ('36', '2016-07-11 00:00:00', '30.00', '1500.00', '45000.00', '2000.00', '60000.00', '15', '1', '1'), ('37', '2016-07-11 00:00:00', '30.00', '80.00', '2400.00', '150.00', '4500.00', '12', '1', '1'), ('74', '2016-07-01 00:00:00', '30.00', '200.00', '6000.00', '400.00', '12000.00', '1', '1', '1'), ('75', '2016-07-01 00:00:00', '30.00', '2000.00', '60000.00', '2800.00', '84000.00', '4', '1', '1'), ('76', '2016-07-01 00:00:00', '30.00', '600.00', '18000.00', '1000.00', '30000.00', '6', '1', '1'), ('77', '2016-07-01 00:00:00', '30.00', '2000.00', '60000.00', '2500.00', '75000.00', '7', '1', '1'), ('78', '2016-07-01 00:00:00', '30.00', '2500.00', '75000.00', '3500.00', '105000.00', '19', '1', '1'), ('79', '2016-07-01 00:00:00', '30.00', '80.00', '2400.00', '150.00', '4500.00', '12', '1', '1'), ('80', '2016-07-01 00:00:00', '30.00', '800.00', '24000.00', '1200.00', '36000.00', '8', '1', '1'), ('81', '2016-07-01 00:00:00', '30.00', '200.00', '6000.00', '250.00', '7500.00', '16', '1', '1'), ('82', '2016-07-01 00:00:00', '30.00', '350.00', '10500.00', '500.00', '15000.00', '14', '1', '1'), ('83', '2016-07-15 00:00:00', '30.00', '7000.00', '210000.00', '10000.00', '300000.00', '20', '1', '2'), ('84', '2016-07-15 00:00:00', '30.00', '200.00', '6000.00', '400.00', '12000.00', '1', '1', '2'), ('85', '2016-07-15 00:00:00', '30.00', '2000.00', '60000.00', '2800.00', '84000.00', '4', '1', '2'), ('86', '2016-07-15 00:00:00', '30.00', '800.00', '24000.00', '1200.00', '36000.00', '8', '1', '2'), ('87', '2016-07-15 00:00:00', '30.00', '350.00', '10500.00', '500.00', '15000.00', '14', '1', '2'), ('88', '2016-07-15 00:00:00', '30.00', '200.00', '6000.00', '250.00', '7500.00', '16', '1', '2'), ('89', '2016-07-15 00:00:00', '30.00', '1500.00', '45000.00', '2000.00', '60000.00', '15', '1', '2'), ('90', '2016-07-15 00:00:00', '30.00', '5000.00', '150000.00', '6500.00', '195000.00', '9', '1', '2');
COMMIT;

-- ----------------------------
--  Table structure for `stockincomebill`
-- ----------------------------
DROP TABLE IF EXISTS `stockincomebill`;
CREATE TABLE `stockincomebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `vdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `totalNumber` decimal(19,2) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `inputUser_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2r4lvwfhxvf706vnl3kosb4ge` (`inputUser_id`),
  KEY `FK_mt5u1rj5oa43oqgm1i6faut29` (`auditor_id`),
  KEY `FK_rucpa7mjq86jex50rptjti4wj` (`depot_id`),
  CONSTRAINT `FK_rucpa7mjq86jex50rptjti4wj` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  CONSTRAINT `FK_2r4lvwfhxvf706vnl3kosb4ge` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_mt5u1rj5oa43oqgm1i6faut29` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stockincomebill`
-- ----------------------------
BEGIN;
INSERT INTO `stockincomebill` VALUES ('1', '001', '2016-05-05 00:00:00', '1', '931500.00', '750.00', '2016-08-19 21:27:47', '2016-08-19 21:15:48', '9', '1', '1'), ('2', '002', '2016-05-15 00:00:00', '1', '941500.00', '750.00', '2016-08-19 21:27:48', '2016-08-19 21:21:52', '18', '1', '2'), ('3', '003', '2016-06-10 00:00:00', '1', '1057800.00', '780.00', '2016-08-19 21:48:12', '2016-08-19 21:24:34', '19', '1', '1'), ('4', '004', '2016-06-20 00:00:00', '0', '907800.00', '780.00', null, '2016-08-19 21:27:28', '19', null, '2'), ('5', '005', '2016-06-30 00:00:00', '1', '917500.00', '300.00', '2016-08-19 21:50:51', '2016-08-19 21:50:45', '1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `stockincomebillitem`
-- ----------------------------
DROP TABLE IF EXISTS `stockincomebillitem`;
CREATE TABLE `stockincomebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `costPrice` decimal(19,2) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_j9lqbjftlk2lmsgkdjo9hwgqc` (`product_id`),
  KEY `FK_k50mmys429hikidmwv52fo1ph` (`bill_id`),
  CONSTRAINT `FK_k50mmys429hikidmwv52fo1ph` FOREIGN KEY (`bill_id`) REFERENCES `stockincomebill` (`id`),
  CONSTRAINT `FK_j9lqbjftlk2lmsgkdjo9hwgqc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stockincomebillitem`
-- ----------------------------
BEGIN;
INSERT INTO `stockincomebillitem` VALUES ('16', '200.00', '50.00', '10000.00', '', '1', '1'), ('17', '300.00', '50.00', '15000.00', '', '2', '1'), ('18', '800.00', '50.00', '40000.00', '', '3', '1'), ('19', '2000.00', '50.00', '100000.00', '', '4', '1'), ('20', '600.00', '50.00', '30000.00', '', '5', '1'), ('21', '600.00', '50.00', '30000.00', '', '6', '1'), ('22', '2000.00', '50.00', '100000.00', '', '7', '1'), ('23', '800.00', '50.00', '40000.00', '', '8', '1'), ('24', '200.00', '50.00', '10000.00', '', '10', '1'), ('25', '2000.00', '50.00', '100000.00', '', '11', '1'), ('26', '80.00', '50.00', '4000.00', '', '12', '1'), ('27', '350.00', '50.00', '17500.00', '', '14', '1'), ('28', '1500.00', '50.00', '75000.00', '', '15', '1'), ('29', '7000.00', '50.00', '350000.00', '', '20', '1'), ('30', '200.00', '50.00', '10000.00', '', '16', '1'), ('31', '7000.00', '50.00', '350000.00', '', '20', '2'), ('32', '2500.00', '50.00', '125000.00', '', '19', '2'), ('33', '200.00', '50.00', '10000.00', '', '16', '2'), ('34', '350.00', '50.00', '17500.00', '', '14', '2'), ('35', '600.00', '50.00', '30000.00', '', '6', '2'), ('36', '80.00', '50.00', '4000.00', '', '12', '2'), ('37', '2000.00', '50.00', '100000.00', '', '4', '2'), ('38', '800.00', '50.00', '40000.00', '', '3', '2'), ('39', '2000.00', '50.00', '100000.00', '', '7', '2'), ('40', '200.00', '50.00', '10000.00', '', '10', '2'), ('41', '500.00', '50.00', '25000.00', '', '13', '2'), ('42', '200.00', '50.00', '10000.00', '', '1', '2'), ('43', '300.00', '50.00', '15000.00', '', '2', '2'), ('44', '600.00', '50.00', '30000.00', '', '5', '2'), ('45', '1500.00', '50.00', '75000.00', '', '15', '2'), ('46', '2500.00', '60.00', '150000.00', '', '19', '3'), ('47', '7000.00', '60.00', '420000.00', '', '20', '3'), ('48', '2000.00', '60.00', '120000.00', '', '4', '3'), ('49', '200.00', '60.00', '12000.00', '', '1', '3'), ('50', '600.00', '60.00', '36000.00', '', '5', '3'), ('51', '600.00', '60.00', '36000.00', '', '6', '3'), ('52', '2000.00', '60.00', '120000.00', '', '7', '3'), ('53', '800.00', '60.00', '48000.00', '', '8', '3'), ('54', '500.00', '60.00', '30000.00', '', '13', '3'), ('55', '350.00', '60.00', '21000.00', '', '14', '3'), ('56', '200.00', '60.00', '12000.00', '', '16', '3'), ('57', '80.00', '60.00', '4800.00', '', '12', '3'), ('58', '800.00', '60.00', '48000.00', '', '3', '3'), ('59', '200.00', '60.00', '12000.00', '', '1', '4'), ('60', '300.00', '60.00', '18000.00', '', '2', '4'), ('61', '2000.00', '60.00', '120000.00', '', '4', '4'), ('62', '600.00', '60.00', '36000.00', '', '5', '4'), ('63', '600.00', '60.00', '36000.00', '', '6', '4'), ('64', '800.00', '60.00', '48000.00', '', '8', '4'), ('65', '2000.00', '60.00', '120000.00', '', '11', '4'), ('66', '350.00', '60.00', '21000.00', '', '14', '4'), ('67', '5000.00', '60.00', '300000.00', '', '9', '4'), ('68', '2500.00', '60.00', '150000.00', '', '19', '4'), ('69', '200.00', '60.00', '12000.00', '', '16', '4'), ('70', '500.00', '60.00', '30000.00', '', '13', '4'), ('71', '80.00', '60.00', '4800.00', '', '12', '4'), ('72', '7000.00', '50.00', '350000.00', '', '20', '5'), ('73', '2500.00', '50.00', '125000.00', '', '19', '5'), ('74', '1500.00', '50.00', '75000.00', '', '15', '5'), ('75', '350.00', '50.00', '17500.00', '', '14', '5'), ('76', '5000.00', '50.00', '250000.00', '', '9', '5'), ('77', '2000.00', '50.00', '100000.00', '', '11', '5');
COMMIT;

-- ----------------------------
--  Table structure for `stockoutcomebill`
-- ----------------------------
DROP TABLE IF EXISTS `stockoutcomebill`;
CREATE TABLE `stockoutcomebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `vdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `totalNumber` decimal(19,2) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `inputTime` datetime DEFAULT NULL,
  `inputUser_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3mqhrl0cptc2ogteu3gfka6b7` (`inputUser_id`),
  KEY `FK_krhcbh1hatkrdh9wvvbfis2eb` (`auditor_id`),
  KEY `FK_dj18me4bk3ajp4aj4f6ky8yp6` (`depot_id`),
  KEY `FK_kdyag6t1r63gqjkpclp0n6tmb` (`client_id`),
  CONSTRAINT `FK_kdyag6t1r63gqjkpclp0n6tmb` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FK_3mqhrl0cptc2ogteu3gfka6b7` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_dj18me4bk3ajp4aj4f6ky8yp6` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
  CONSTRAINT `FK_krhcbh1hatkrdh9wvvbfis2eb` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stockoutcomebill`
-- ----------------------------
BEGIN;
INSERT INTO `stockoutcomebill` VALUES ('1', '001', '2016-07-01 00:00:00', '1', '369000.00', '270.00', '2016-08-19 21:48:32', '2016-08-19 21:33:55', '1', '1', '1', '1'), ('2', '002', '2016-07-11 00:00:00', '1', '588000.00', '270.00', '2016-08-19 21:43:41', '2016-08-19 21:36:20', '1', '1', '2', '1'), ('3', '003', '2016-07-25 00:00:00', '0', '288000.00', '180.00', null, '2016-08-19 21:38:26', '9', null, '1', '1'), ('4', '004', '2016-07-15 00:00:00', '1', '709500.00', '240.00', '2016-08-19 21:51:03', '2016-08-19 21:46:19', '1', '1', '1', '2'), ('5', '005', '2016-08-05 00:00:00', '0', '312000.00', '120.00', null, '2016-08-19 21:52:40', '18', null, '2', '2');
COMMIT;

-- ----------------------------
--  Table structure for `stockoutcomebillitem`
-- ----------------------------
DROP TABLE IF EXISTS `stockoutcomebillitem`;
CREATE TABLE `stockoutcomebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `salePrice` decimal(19,2) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cr4n4jpf9y0xl98ijheutb4fn` (`product_id`),
  KEY `FK_cc8ixopcgfe0he98ae11erxlb` (`bill_id`),
  CONSTRAINT `FK_cc8ixopcgfe0he98ae11erxlb` FOREIGN KEY (`bill_id`) REFERENCES `stockoutcomebill` (`id`),
  CONSTRAINT `FK_cr4n4jpf9y0xl98ijheutb4fn` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stockoutcomebillitem`
-- ----------------------------
BEGIN;
INSERT INTO `stockoutcomebillitem` VALUES ('1', '400.00', '30.00', '12000.00', '', '1', '1'), ('2', '2800.00', '30.00', '84000.00', '', '4', '1'), ('3', '1000.00', '30.00', '30000.00', '', '6', '1'), ('4', '2500.00', '30.00', '75000.00', '', '7', '1'), ('5', '3500.00', '30.00', '105000.00', '', '19', '1'), ('6', '150.00', '30.00', '4500.00', '', '12', '1'), ('7', '1200.00', '30.00', '36000.00', '', '8', '1'), ('8', '250.00', '30.00', '7500.00', '', '16', '1'), ('9', '500.00', '30.00', '15000.00', '', '14', '1'), ('10', '500.00', '30.00', '15000.00', '', '2', '2'), ('11', '10000.00', '30.00', '300000.00', '', '20', '2'), ('12', '500.00', '30.00', '15000.00', '', '14', '2'), ('13', '2800.00', '30.00', '84000.00', '', '4', '2'), ('14', '2500.00', '30.00', '75000.00', '', '7', '2'), ('15', '350.00', '30.00', '10500.00', '', '10', '2'), ('16', '800.00', '30.00', '24000.00', '', '13', '2'), ('17', '2000.00', '30.00', '60000.00', '', '15', '2'), ('18', '150.00', '30.00', '4500.00', '', '12', '2'), ('19', '3500.00', '20.00', '70000.00', '', '11', '3'), ('20', '250.00', '20.00', '5000.00', '', '16', '3'), ('21', '400.00', '20.00', '8000.00', '', '1', '3'), ('22', '900.00', '20.00', '18000.00', '', '3', '3'), ('23', '800.00', '30.00', '24000.00', '', '5', '3'), ('24', '1000.00', '30.00', '30000.00', '', '6', '3'), ('25', '150.00', '20.00', '3000.00', '', '12', '3'), ('26', '6500.00', '20.00', '130000.00', '', '9', '3'), ('27', '10000.00', '30.00', '300000.00', '', '20', '4'), ('28', '400.00', '30.00', '12000.00', '', '1', '4'), ('29', '2800.00', '30.00', '84000.00', '', '4', '4'), ('30', '1200.00', '30.00', '36000.00', '', '8', '4'), ('31', '500.00', '30.00', '15000.00', '', '14', '4'), ('32', '250.00', '30.00', '7500.00', '', '16', '4'), ('33', '2000.00', '30.00', '60000.00', '', '15', '4'), ('34', '6500.00', '30.00', '195000.00', '', '9', '4'), ('35', '10000.00', '20.00', '200000.00', '', '20', '5'), ('36', '3500.00', '20.00', '70000.00', '', '19', '5'), ('37', '150.00', '20.00', '3000.00', '', '12', '5'), ('38', '1200.00', '20.00', '24000.00', '', '8', '5'), ('39', '500.00', '20.00', '10000.00', '', '2', '5'), ('40', '250.00', '20.00', '5000.00', '', '16', '5');
COMMIT;

-- ----------------------------
--  Table structure for `supplier`
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `supplier`
-- ----------------------------
BEGIN;
INSERT INTO `supplier` VALUES ('1', '北京XX公司', '123', '北京'), ('2', '上海XX公司', '123', '上海'), ('3', '广州XX公司', '123', '广州');
COMMIT;

-- ----------------------------
--  Table structure for `systemmenu`
-- ----------------------------
DROP TABLE IF EXISTS `systemmenu`;
CREATE TABLE `systemmenu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_oufx29snoe4anqhc8qhl48t90` (`parent_id`),
  CONSTRAINT `FK_oufx29snoe4anqhc8qhl48t90` FOREIGN KEY (`parent_id`) REFERENCES `systemmenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `systemmenu`
-- ----------------------------
BEGIN;
INSERT INTO `systemmenu` VALUES ('1', '系统模块', '', 'system', null), ('2', '业务模块', '', 'business', null), ('3', '报表模块', '', 'chart', null), ('4', '部门管理', 'department', '', '1'), ('5', '员工管理', 'employee', '', '1'), ('6', '权限管理', 'permission', '', '1'), ('7', '角色管理', 'role', '', '1'), ('8', '系统菜单管理', 'systemMenu', '', '1'), ('9', '品牌管理', 'brand', '', '2'), ('10', '供应商管理', 'supplier', '', '2'), ('11', '商品管理', 'product', '', '2'), ('12', '仓库管理', 'depot', '', '2'), ('13', '客户管理', 'client', '', '2'), ('14', '采购订单管理', 'orderBill', '', '2'), ('15', '采购入库单管理', 'stockIncomeBill', '', '2'), ('16', '销售出库单管理', 'stockOutcomeBill', '', '2'), ('17', '即时库存报表', 'productStock', '', '3'), ('18', '订货报表', 'chart_orderChart', '', '3'), ('19', '销售报表', 'chart_saleChart', '', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
