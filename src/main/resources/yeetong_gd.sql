/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : yeetong_gd

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-11-29 20:51:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id   不作为主键',
  `uuid` varchar(32) NOT NULL COMMENT '数据id唯一标识',
  `idNumber` varchar(32) DEFAULT NULL COMMENT '用户身份证号，   全表唯一不重复',
  `userName` varchar(255) DEFAULT NULL COMMENT '人员登陆用户名   全表唯一不重复',
  `phone` varchar(11) NOT NULL COMMENT '手机号               全表唯一不重复',
  `name` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `addTime` bigint(16) DEFAULT NULL COMMENT '用户添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新个人信息时间',
  `password` varchar(50) NOT NULL COMMENT '用户密码md5加密',
  `photo` varchar(18) DEFAULT NULL COMMENT '用户头像文件名',
  `sex` int(1) DEFAULT NULL COMMENT '用户性别  0 女 1 男',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '用户状态 0正常1删除2停用',
  `type` int(5) DEFAULT '1' COMMENT '系统用户1',
  `companyUuid` varchar(32) DEFAULT NULL COMMENT 'admin_company企业',
  `departmentUuid` varchar(32) DEFAULT NULL COMMENT '用户所属部门',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uuidIndex` (`uuid`) USING BTREE COMMENT '主键索引'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='admin的用户信息表';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('3', '56eec8b4219f4fdb8cfb7569e6fc508b', null, 'admin_szx', '18518436862', '司仲夏', '1542342789583', '1542342789583', 'e773c8a4d370949d7c708f5495f27140', null, null, '0', '1', null, null);

-- ----------------------------
-- Table structure for equipment_aftersale
-- ----------------------------
DROP TABLE IF EXISTS `equipment_aftersale`;
CREATE TABLE `equipment_aftersale` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) NOT NULL COMMENT '设备表数据id',
  `personInCharge` varchar(32) DEFAULT NULL COMMENT '设备负责人',
  `personInChargePhone` varchar(16) DEFAULT NULL COMMENT '设备负责人联系电话',
  `installPerson` varchar(32) DEFAULT NULL COMMENT '设备安装人',
  `installPersonPhone` varchar(16) DEFAULT NULL COMMENT '设备安装人联系电话',
  `receipt` varchar(32) DEFAULT NULL COMMENT '设备回执单图片地址',
  `installTime` bigint(16) DEFAULT NULL COMMENT '设备安装时间',
  `host` varchar(64) DEFAULT NULL COMMENT '设备服务器地址',
  `sprayType` int(2) DEFAULT NULL COMMENT '功能，区域，道路喷淋，对应sys_idctionary sprayType字段',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '0正常  1 删除',
  PRIMARY KEY (`id`),
  KEY `uuid设备表数据id` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='设备售后表 记录 安装人负责人等信息';

-- ----------------------------
-- Records of equipment_aftersale
-- ----------------------------
INSERT INTO `equipment_aftersale` VALUES ('100', 'd8f4b07ef12548a9a016595db27c015b', '111', '111', '111', '111', '', '1541520000000', '122.11.11.22', null, '0');
INSERT INTO `equipment_aftersale` VALUES ('101', '5763e749c4c14505931adf0f97a230f1', null, null, null, null, null, '1540914166230', null, null, '0');
INSERT INTO `equipment_aftersale` VALUES ('102', '0dd4db409541439b90ebd196165527d7', '1', '1', '1', '1', '', '1541088000000', '1', null, '0');
INSERT INTO `equipment_aftersale` VALUES ('103', 'bf7df451268846f69fdeadda72d01508', null, null, null, null, null, '1541173752112', null, null, '0');
INSERT INTO `equipment_aftersale` VALUES ('104', '32cfcdb5018c4e6088acc3b342375634', '1', '1', '1', '1', '2MdUeR1zGIj07P3Sty', '1540915200000', '1', null, '0');
INSERT INTO `equipment_aftersale` VALUES ('105', 'a840651036d04ead8d614075f3802661', null, null, null, null, '', '1540915630429', null, null, '0');
INSERT INTO `equipment_aftersale` VALUES ('106', 'e2f6798ac38146bb98563a4f902fbd85', null, null, null, null, '', '1540830359693', null, null, '0');

-- ----------------------------
-- Table structure for equipment_basics
-- ----------------------------
DROP TABLE IF EXISTS `equipment_basics`;
CREATE TABLE `equipment_basics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id排序用',
  `uuid` varchar(32) NOT NULL DEFAULT '' COMMENT '数据id',
  `companyUuid` varchar(32) DEFAULT NULL COMMENT '设备所属单位uuid  代理商等，直属工地，则为空',
  `proUuid` varchar(32) DEFAULT NULL COMMENT '设备所属工地， ',
  `name` varchar(128) NOT NULL COMMENT '设备名称',
  `equipmentNo` varchar(32) NOT NULL COMMENT '设备编号，全表唯一不重复（至少某种设备不重复）',
  `positionx` varchar(10) DEFAULT '0' COMMENT '设备区位图相对坐标x',
  `positiony` varchar(10) DEFAULT '0' COMMENT '设备区位图相对坐标y',
  `type` int(2) NOT NULL COMMENT '设备类型  相见字典表sys_dictionart  equipmentType 类型',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '数据状态0正常  其他删除',
  `addTime` bigint(16) NOT NULL COMMENT '设备添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '设备编辑时间',
  `lastOnLineTime` bigint(16) DEFAULT '0' COMMENT '设备最后在线时间',
  `supplierCompanyUuid` varchar(32) DEFAULT NULL COMMENT '设备供应商   uuid',
  `agentCompanyUuid` varchar(32) DEFAULT NULL COMMENT '设备代理商   uuid',
  `ipaddressPort` varchar(32) DEFAULT '0' COMMENT 'ip及端口',
  `ipportIssueStatus` int(11) DEFAULT '2' COMMENT '0待下发，1服务抓取，2下发成功，3下发失败',
  `workType` varchar(64) DEFAULT '1' COMMENT '电表设备工作类型，人员定位设备1进2出',
  `lng` varchar(32) DEFAULT NULL COMMENT '高德地图设备经度',
  `lat` varchar(32) DEFAULT NULL COMMENT '高德地图设备维度',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uuid` (`uuid`),
  KEY `proUuid` (`proUuid`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='设备统一基础表';

-- ----------------------------
-- Records of equipment_basics
-- ----------------------------
INSERT INTO `equipment_basics` VALUES ('32', '4eb7ccd5250847c29dba6446792f9080', '', '14228d15566544d9a427a600c9ba2283', '扬尘噪音', 'ZKZQ_12', '153', '141', '1', '1', '1532921649213', '1542468011288', '1541940835', 'f7d7e8f78e2e468bb1ec3cf9d06ed03d', '', '114.55.126.208:6107', '2', null, '0', '0');
INSERT INTO `equipment_basics` VALUES ('100', '03b7651e71a647ff94781c93f03c6bf7', null, '14228d15566544d9a427a600c9ba2283', '测试2', '1234', '0', '0', '4', '0', '1542464359873', '1542469445778', '0', null, null, '0', '0', '1', null, null);
INSERT INTO `equipment_basics` VALUES ('101', '9cdaa6b5e9944ca5874cab1424eb59e6', null, '14228d15566544d9a427a600c9ba2283', '测试2', '', '0', '0', '6', '1', '1542464835201', '1542466839432', '0', null, null, '0', '0', '1', '12', '21');
INSERT INTO `equipment_basics` VALUES ('102', 'fa142b48900440c4a2892fd546149284', null, '14228d15566544d9a427a600c9ba2283', '测试2', '', '0', '0', '6', '0', '1542466885206', '1542472034039', '0', null, null, '0', '0', '1', null, null);
INSERT INTO `equipment_basics` VALUES ('103', '1cdd55bd695847b3987030003ecc4755', null, '14228d15566544d9a427a600c9ba2283', '111', '', '0', '0', '6', '0', '1542466910982', '1542468282908', '0', null, null, '0', '0', '1', null, null);
INSERT INTO `equipment_basics` VALUES ('104', 'd8f4b07ef12548a9a016595db27c015b', null, '14228d15566544d9a427a600c9ba2283', '测试2', '2', '0', '0', '1', '0', '1542468138814', '1542468379005', '0', '22a667e553e04d52b8627c1ed45e247b', '77d68ca5bc8b4b249f5fb3b5f192653f', '22', '0', '1', '12', '12');
INSERT INTO `equipment_basics` VALUES ('105', '5763e749c4c14505931adf0f97a230f1', null, '14228d15566544d9a427a600c9ba2283', '111', '111', '0', '0', '1', '0', '1542469370219', '1542469370219', '0', null, null, '0', '0', '1', null, null);
INSERT INTO `equipment_basics` VALUES ('106', '0dd4db409541439b90ebd196165527d7', null, '14228d15566544d9a427a600c9ba2283', '测试2', '12340', '0', '0', '2', '0', '1542469450764', '1542469732062', '0', '22a667e553e04d52b8627c1ed45e247b', '77d68ca5bc8b4b249f5fb3b5f192653f', '1', '0', '1', '1', '1');
INSERT INTO `equipment_basics` VALUES ('107', 'bf7df451268846f69fdeadda72d01508', null, '14228d15566544d9a427a600c9ba2283', '1', '1', '0', '0', '2', '0', '1542469760509', '1542471963223', '0', null, null, '0', '0', '1', null, null);
INSERT INTO `equipment_basics` VALUES ('108', '32cfcdb5018c4e6088acc3b342375634', null, '14228d15566544d9a427a600c9ba2283', '测试2', '12221', '0', '0', '3', '1', '1542470601263', '1542470651144', '0', '22a667e553e04d52b8627c1ed45e247b', '77d68ca5bc8b4b249f5fb3b5f192653f', '0', '0', '1', '1', '1');
INSERT INTO `equipment_basics` VALUES ('109', 'a840651036d04ead8d614075f3802661', null, '14228d15566544d9a427a600c9ba2283', '测试2', '11111', '0', '0', '5', '0', '1542470836692', '1542470836692', '0', '22a667e553e04d52b8627c1ed45e247b', null, '0', '0', '1', null, null);
INSERT INTO `equipment_basics` VALUES ('110', 'e2f6798ac38146bb98563a4f902fbd85', null, '14228d15566544d9a427a600c9ba2283', '1', '12', '0', '0', '3', '0', '1542471966436', '1542471966436', '0', null, null, '0', '0', '1', null, null);

-- ----------------------------
-- Table structure for equipment_discharge_current
-- ----------------------------
DROP TABLE IF EXISTS `equipment_discharge_current`;
CREATE TABLE `equipment_discharge_current` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '设备编号',
  `data_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据时间，也是就是数据包里带的时间',
  `weight` double NOT NULL DEFAULT '0' COMMENT '当前载重',
  `dip_angle_X` double NOT NULL DEFAULT '0' COMMENT '倾角X',
  `dip_angle_Y` double NOT NULL DEFAULT '0' COMMENT '倾角Y',
  `give_alarm` varchar(8) COLLATE utf8_bin NOT NULL DEFAULT '00000000' COMMENT '报警状态',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='卸料实时数据表';

-- ----------------------------
-- Records of equipment_discharge_current
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_discharge_orderissued
-- ----------------------------
DROP TABLE IF EXISTS `equipment_discharge_orderissued`;
CREATE TABLE `equipment_discharge_orderissued` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号',
  `ip_dn` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '域名或ip地址',
  `port` int(11) NOT NULL DEFAULT '0' COMMENT '端口',
  `addr_status` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '地址下发状态：0待下发执行 1服务处理 2设备返回成功 3设备返回失败（数据错误）',
  `addr_uuid_admin_user` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '对应的是admin_user的uuid。表示更改ip的操作人',
  `addr_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更改ip的操作时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '记录最后一次被更新的时间',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='卸料命令下发 主要是指的IP配置帧';

-- ----------------------------
-- Records of equipment_discharge_orderissued
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_discharge_parameter
-- ----------------------------
DROP TABLE IF EXISTS `equipment_discharge_parameter`;
CREATE TABLE `equipment_discharge_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号',
  `data_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据时间，也是就是数据包里带的时间',
  `installation_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '安装时间  设备只传递了年月日',
  `safe_weight` double NOT NULL DEFAULT '0' COMMENT '额定载荷',
  `early_warning_ratio` tinyint(4) NOT NULL DEFAULT '0' COMMENT '预警系数',
  `give_alarm_ratio` tinyint(4) NOT NULL DEFAULT '0' COMMENT '报警系数',
  `empty_load_ad1` double NOT NULL DEFAULT '0' COMMENT '空载AD1',
  `empty_load_ad2` double NOT NULL DEFAULT '0' COMMENT '空载AD2',
  `empty_load_ad3` double NOT NULL DEFAULT '0' COMMENT '空载AD3',
  `empty_load_ad4` double NOT NULL DEFAULT '0' COMMENT '空载AD4',
  `empty_load_ad` double NOT NULL DEFAULT '0' COMMENT '空载AD',
  `standard_weight_ad1` double NOT NULL DEFAULT '0' COMMENT '标准重物AD1',
  `standard_weight_ad2` double NOT NULL DEFAULT '0' COMMENT '标准重物AD2',
  `standard_weight_ad3` double NOT NULL DEFAULT '0' COMMENT '标准重物AD3',
  `standard_weight_ad4` double NOT NULL DEFAULT '0' COMMENT '标准重物AD4',
  `standard_weight_ad` double NOT NULL DEFAULT '0' COMMENT '标准重物AD',
  `standard_weight` double NOT NULL DEFAULT '0' COMMENT '标准重物',
  `dip_angle_early_warning` double NOT NULL DEFAULT '0' COMMENT '倾角预警值',
  `dip_angle_give_alarm` double NOT NULL DEFAULT '0' COMMENT '倾角报警值',
  `soft_version` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '软件版本号',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='卸料参数表';

-- ----------------------------
-- Records of equipment_discharge_parameter
-- ----------------------------
INSERT INTO `equipment_discharge_parameter` VALUES ('1', '32cfcdb5018c4e6088acc3b342375634', '12221', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '1542470601294');
INSERT INTO `equipment_discharge_parameter` VALUES ('2', 'e2f6798ac38146bb98563a4f902fbd85', '12', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '1542471966468');

-- ----------------------------
-- Table structure for equipment_dustnoise_current
-- ----------------------------
DROP TABLE IF EXISTS `equipment_dustnoise_current`;
CREATE TABLE `equipment_dustnoise_current` (
  `uuid` varchar(32) NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(32) NOT NULL COMMENT '设备编号',
  `particulates` double NOT NULL DEFAULT '0' COMMENT '颗粒物',
  `pm2_5` double NOT NULL DEFAULT '0' COMMENT 'PM2.5',
  `pm10` double NOT NULL DEFAULT '0' COMMENT 'PM10',
  `wind_speed` double NOT NULL DEFAULT '0' COMMENT '风速',
  `wind_direction` double NOT NULL DEFAULT '0' COMMENT '风向 0度是正北，90是东， 180是南 ，270是西',
  `temperature` double NOT NULL DEFAULT '0' COMMENT '温度',
  `humidity` double NOT NULL DEFAULT '0' COMMENT '湿度',
  `noise` double NOT NULL DEFAULT '0' COMMENT '噪音',
  `noise_max` double NOT NULL DEFAULT '0' COMMENT '噪音峰值',
  `longitude` double NOT NULL DEFAULT '0' COMMENT '经度',
  `latitude` double NOT NULL DEFAULT '0' COMMENT '纬度',
  `pressure` double NOT NULL DEFAULT '0' COMMENT '气压',
  `addTime` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `updateTime` bigint(16) NOT NULL DEFAULT '0' COMMENT '更新时间戳'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='扬尘噪音实时数据表';

-- ----------------------------
-- Records of equipment_dustnoise_current
-- ----------------------------
INSERT INTO `equipment_dustnoise_current` VALUES ('4eb7ccd5250847c29dba6446792f9080', 'ZKZQ_12', '144', '75', '112', '1.5', '248', '26.7', '69.4', '86', '0', '0', '0', '0', '1541940094', '1541940835');

-- ----------------------------
-- Table structure for equipment_dustnoise_parameter
-- ----------------------------
DROP TABLE IF EXISTS `equipment_dustnoise_parameter`;
CREATE TABLE `equipment_dustnoise_parameter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(32) NOT NULL COMMENT '设备编号',
  `companyUuid` varchar(32) NOT NULL DEFAULT '' COMMENT '设备供应商，举例：中科正奇，威海精讯',
  `pm2_5_Alarm` double NOT NULL DEFAULT '0' COMMENT 'PM2.5报警临界值',
  `pm10_Alarm` double NOT NULL DEFAULT '0' COMMENT 'PM10报警临界值',
  `noise_Alarm` double NOT NULL DEFAULT '0' COMMENT '噪音报警临界值',
  `noise_pattern` int(11) DEFAULT '0' COMMENT '0自动模式,1手动模式',
  `noise_cycle` int(11) DEFAULT '10' COMMENT '上传周期（10-60）s',
  `noise_oc` int(11) DEFAULT '1' COMMENT '继电器开合时间，默认1分钟',
  `noise_status` int(11) DEFAULT '2' COMMENT '0待下发，1被服务器抓取，2下发成功，3下发失败',
  `addTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `updateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='扬尘噪音参数表';

-- ----------------------------
-- Records of equipment_dustnoise_parameter
-- ----------------------------
INSERT INTO `equipment_dustnoise_parameter` VALUES ('1', 'ef11d81c65bb4c01a55bfddfc9862d41', 'ZKZQ_12', '', '150', '150', '100', '0', '10', '1', '2', '1538214040511', '1539585695249');
INSERT INTO `equipment_dustnoise_parameter` VALUES ('100', 'a840651036d04ead8d614075f3802661', '11111', '22a667e553e04d52b8627c1ed45e247b', '0', '0', '0', '0', '0', '0', '0', '1542470836706', '1542470836706');

-- ----------------------------
-- Table structure for equipment_foggun_control
-- ----------------------------
DROP TABLE IF EXISTS `equipment_foggun_control`;
CREATE TABLE `equipment_foggun_control` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '和设备表的UUId一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号，全表唯一不重复（至少某种设备不重复）',
  `open_state` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '雾炮开关状态 0关闭 1开启',
  `respose_state` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '0未下发 1命令被获取 2设备执行反馈成功',
  `identity_Uuid` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '操作用户，和user_identity里的uuid一致',
  `control_time` bigint(16) NOT NULL DEFAULT '0' COMMENT '控制时间',
  `update_time` bigint(16) NOT NULL DEFAULT '0' COMMENT '最后一次更新表时间',
  `create_time` bigint(16) NOT NULL DEFAULT '0' COMMENT '创建表时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='雾炮控制实时表';

-- ----------------------------
-- Records of equipment_foggun_control
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_foggun_control_history
-- ----------------------------
DROP TABLE IF EXISTS `equipment_foggun_control_history`;
CREATE TABLE `equipment_foggun_control_history` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '防重复唯一id',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号，全表唯一不重复（至少某种设备不重复）',
  `open_state` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '雾炮开关状态 0关闭 1开启',
  `respose_state` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '0未下发 1命令被获取 2设备执行反馈成功',
  `identity_Uuid` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '操作用户，和user_identity里的uuid一致',
  `control_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '控制时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后一次更新表时间',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建表时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='雾炮控制历史表';

-- ----------------------------
-- Records of equipment_foggun_control_history
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_foggun_current
-- ----------------------------
DROP TABLE IF EXISTS `equipment_foggun_current`;
CREATE TABLE `equipment_foggun_current` (
  `uuid` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '和设备表的UUId一致',
  `equipmentNo` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '设备编号，全表唯一不重复（至少某种设备不重复）',
  `warninglampstatus` int(11) DEFAULT '1' COMMENT '警报灯状态： 0为关闭，1为打开',
  `foggunstatus` int(11) DEFAULT '1' COMMENT '雾炮喷淋状态： 0为关闭，1为打开',
  `update_time` bigint(16) DEFAULT '1',
  `create_time` bigint(16) DEFAULT '0' COMMENT '0',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='雾炮喷淋的实时状态表';

-- ----------------------------
-- Records of equipment_foggun_current
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_foggun_orderissued
-- ----------------------------
DROP TABLE IF EXISTS `equipment_foggun_orderissued`;
CREATE TABLE `equipment_foggun_orderissued` (
  `uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '对应设备表的uuid',
  `equipmentNo` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '设备号',
  `ip_dn` varchar(16) COLLATE utf8_bin NOT NULL COMMENT 'ip',
  `port` int(11) NOT NULL COMMENT '端口',
  `addr_status` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '地址下发状态：0待下发执行 1服务处理 2设备返回成功 3设备返回失败（数据错误）',
  `addr_uuid_admin_user` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '对应的是admin_user的uuid。表示更改ip的操作人',
  `addr_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更改ip操作的时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '记录最后一次更新的时间',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='雾炮IP配置参数表';

-- ----------------------------
-- Records of equipment_foggun_orderissued
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_foggun_time_parament
-- ----------------------------
DROP TABLE IF EXISTS `equipment_foggun_time_parament`;
CREATE TABLE `equipment_foggun_time_parament` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equipmentNo` varchar(20) DEFAULT NULL,
  `resposestate` int(11) DEFAULT NULL COMMENT '0下发，-1未下发成功，1下发成功',
  `openState` int(11) DEFAULT NULL COMMENT '0不启用 1启用',
  `timingstarttime` varchar(50) DEFAULT NULL COMMENT '定时启动时间',
  `timeout` smallint(6) DEFAULT NULL COMMENT '定时持续时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `enterTime` datetime DEFAULT NULL COMMENT '创建日期',
  `enterPer` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `updatePer` varchar(50) DEFAULT NULL COMMENT '修改人',
  `weeks` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='雾炮定时参数设置表';

-- ----------------------------
-- Records of equipment_foggun_time_parament
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_lift_current
-- ----------------------------
DROP TABLE IF EXISTS `equipment_lift_current`;
CREATE TABLE `equipment_lift_current` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '设备编号',
  `work_Circle` bigint(32) NOT NULL DEFAULT '0' COMMENT '工作循环序列号',
  `cardNo` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '司机信息：可能是卡号，工号，Uid',
  `data_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据时间，也是就是数据包里带的时间',
  `weight` double NOT NULL DEFAULT '0' COMMENT '重量',
  `safe_weight` double NOT NULL DEFAULT '0' COMMENT '安全（额定）起重量',
  `height` double NOT NULL DEFAULT '0' COMMENT '高度',
  `speed` double NOT NULL DEFAULT '0' COMMENT '升降机的速度',
  `floor` double NOT NULL DEFAULT '0' COMMENT '楼层',
  `people_num` double NOT NULL DEFAULT '0' COMMENT '人数',
  `sensor_status` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '0000000000000000' COMMENT '传感器状态',
  `give_alarm` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '0000000000000000' COMMENT '报警',
  `relay_status` varchar(8) COLLATE utf8_bin NOT NULL DEFAULT '00000000' COMMENT '继电器状态',
  `gprs_signal_strength` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'GPRS信号强度',
  `current_page` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前页面',
  `wind_scale` tinyint(4) DEFAULT '0' COMMENT '分级',
  `wind_speed` tinyint(4) DEFAULT '0' COMMENT '风速',
  `dip_angle_X` double NOT NULL DEFAULT '0' COMMENT '倾角X',
  `dip_angle_Y` double NOT NULL DEFAULT '0' COMMENT '倾角Y',
  `boot_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '开机运行时间  需要从运行时间包进行更新',
  `boot_time_sum` bigint(20) NOT NULL DEFAULT '0' COMMENT '总的运行时间 从运行时间包获取',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='升降机实时数据表';

-- ----------------------------
-- Records of equipment_lift_current
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_lift_feature
-- ----------------------------
DROP TABLE IF EXISTS `equipment_lift_feature`;
CREATE TABLE `equipment_lift_feature` (
  `equipmentNo` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '设备编号',
  `userid` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `TotalPack` int(1) DEFAULT '0' COMMENT '总包数',
  `CurrentPack` int(1) DEFAULT '0' COMMENT '当前包数',
  `FeaturePack` text COLLATE utf8_bin COMMENT '特征码的包',
  `SumFeaturePack` text COLLATE utf8_bin COMMENT '总的包，包括针头真尾',
  `isrecv` int(11) DEFAULT '3' COMMENT '3 初始化状态，0失败1成功，2错误',
  `createDate` datetime DEFAULT NULL COMMENT '保存时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='升降机人脸特征库';

-- ----------------------------
-- Records of equipment_lift_feature
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_lift_feature_issued
-- ----------------------------
DROP TABLE IF EXISTS `equipment_lift_feature_issued`;
CREATE TABLE `equipment_lift_feature_issued` (
  `equipmentNo` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '设备编号',
  `userid` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `issued_status` int(11) DEFAULT '2' COMMENT '0待下发1服务器抓取2下发成功3下发失败',
  `createdate` bigint(20) DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='升降机人脸下发标识';

-- ----------------------------
-- Records of equipment_lift_feature_issued
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_lift_orderissued
-- ----------------------------
DROP TABLE IF EXISTS `equipment_lift_orderissued`;
CREATE TABLE `equipment_lift_orderissued` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号',
  `ip_dn` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '域名或ip地址',
  `port` int(11) NOT NULL DEFAULT '0' COMMENT '端口',
  `addr_status` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '地址下发状态：0待下发执行 1服务处理 2设备返回成功 3设备返回失败（数据错误）',
  `addr_uuid_admin_user` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '对应的是admin_user的uuid。表示更改ip的操作人',
  `addr_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更改ip的操作时间',
  `limit_value` tinyint(4) NOT NULL DEFAULT '0' COMMENT '限位值：当前可能的值0解除锁定  1锁定',
  `limit_status` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '限位下发状态：0待下发执行 1服务处理 2设备返回成功 3设备返回失败（数据错误）',
  `limit_uuid_admin_user` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '对应的是admin_user的uuid。表示更改限位的操作人',
  `limit_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更改限位的操作时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '记录最后一次被更新的时间',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='升降机命令下发 主要是指的IP配置帧和限位控制帧';

-- ----------------------------
-- Records of equipment_lift_orderissued
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_lift_parameter
-- ----------------------------
DROP TABLE IF EXISTS `equipment_lift_parameter`;
CREATE TABLE `equipment_lift_parameter` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号',
  `data_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据时间，也是就是数据包里带的时间',
  `function_configuration` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '0000000000000000' COMMENT '功能配置',
  `Identification_method` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '0RFID刷卡（刷一次）1RFID刷卡（持续）2指纹3指纹加卡 4虹膜5虹膜加卡6人脸识别',
  `installation_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '安装时间  设备只传递了年月日',
  `empty_hook_sampling_value1` double NOT NULL DEFAULT '0' COMMENT '空钩采样值1',
  `empty_hook_sampling_value2` double NOT NULL DEFAULT '0' COMMENT '空钩采样值2',
  `empty_hook_sampling_value` double NOT NULL DEFAULT '0' COMMENT '空钩采样值',
  `standard_weight_sampling_value1` double NOT NULL DEFAULT '0' COMMENT '标准重量采样值1',
  `standard_weight_sampling_value2` double NOT NULL DEFAULT '0' COMMENT '标准重量采样值2',
  `standard_weight_sampling_value` double NOT NULL DEFAULT '0' COMMENT '标准重物采样值',
  `standard_weight_weight` double NOT NULL DEFAULT '0' COMMENT '标准重物重量',
  `safe_weight` double NOT NULL DEFAULT '0' COMMENT '额定载荷',
  `early_warning_ratio` tinyint(4) NOT NULL DEFAULT '0' COMMENT '预警系数',
  `give_alarm_ratio` tinyint(4) NOT NULL DEFAULT '0' COMMENT '报警系数',
  `time_limited_start_time1` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载起始时间1  小时',
  `time_limited_end_time1` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载结束时间1 小时',
  `time_limited_start_time2` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载起始时间2  小时',
  `time_limited_end_time2` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载结束时间2 小时',
  `time_limited_start_time3` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载起始时间3  小时',
  `time_limited_end_time3` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载结束时间3 小时',
  `time_limited_start_time4` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载起始时间4  小时',
  `time_limited_end_time4` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分时限载结束时间4 小时',
  `time_limited_safe_weight1` double NOT NULL DEFAULT '0' COMMENT '分时限载额定载荷1     kg',
  `time_limited_safe_weight2` double NOT NULL DEFAULT '0' COMMENT '分时限载额定载荷2     kg',
  `time_limited_safe_weight3` double NOT NULL DEFAULT '0' COMMENT '分时限载额定载荷3     kg',
  `time_limited_safe_weight4` double NOT NULL DEFAULT '0' COMMENT '分时限载额定载荷4     kg',
  `floor_sampling_one` double NOT NULL DEFAULT '0' COMMENT '1楼楼层采样值',
  `floor_sampling_max` double NOT NULL DEFAULT '0' COMMENT '最高楼层采样值',
  `storey_height1` double NOT NULL DEFAULT '0' COMMENT '层高1   分米',
  `storey_height2` double NOT NULL DEFAULT '0' COMMENT '层高2   分米',
  `storey_height3` double NOT NULL DEFAULT '0' COMMENT '层高3   分米',
  `storey_height4` double NOT NULL DEFAULT '0' COMMENT '层高4   分米',
  `floor1` double NOT NULL DEFAULT '0' COMMENT '楼层1',
  `floor2` double NOT NULL DEFAULT '0' COMMENT '楼层2',
  `floor3` double NOT NULL DEFAULT '0' COMMENT '楼层3',
  `floor4` double NOT NULL DEFAULT '0' COMMENT '楼层4',
  `floor_max` double NOT NULL DEFAULT '0' COMMENT '最大楼层',
  `height_max` double NOT NULL DEFAULT '0' COMMENT '最大高度',
  `driver_identity_cycle` double NOT NULL DEFAULT '0' COMMENT '司机身份对比周期',
  `supervision_identity_cycle` double NOT NULL DEFAULT '0' COMMENT '监理身份对比周期',
  `limited_person` tinyint(4) NOT NULL DEFAULT '0' COMMENT '限载人数',
  `latitude` double NOT NULL DEFAULT '0' COMMENT '纬度值',
  `latitude_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '北纬或南纬',
  `longitude` double NOT NULL DEFAULT '0' COMMENT '经度',
  `longitude_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '东经或西经',
  `wind_early_warning` tinyint(4) DEFAULT '0' COMMENT '风速预警',
  `wind_give_alarm` tinyint(4) DEFAULT '0' COMMENT '风速报警',
  `boot_loader_version` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'boot loader 版本号',
  `soft_version` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '软件版本号',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='升降机参数表';

-- ----------------------------
-- Records of equipment_lift_parameter
-- ----------------------------
INSERT INTO `equipment_lift_parameter` VALUES ('1', '5763e749c4c14505931adf0f97a230f1', '111', '0', '0000000000000000', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '', '1542469370278');
INSERT INTO `equipment_lift_parameter` VALUES ('2', '0dd4db409541439b90ebd196165527d7', '12340', '0', '0000000000000000', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '', '1542469450798');
INSERT INTO `equipment_lift_parameter` VALUES ('3', 'bf7df451268846f69fdeadda72d01508', '1', '0', '0000000000000000', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '', '', '1542469760533');

-- ----------------------------
-- Table structure for equipment_link
-- ----------------------------
DROP TABLE IF EXISTS `equipment_link`;
CREATE TABLE `equipment_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `yuuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '扬尘设备表里的uuid ',
  `wuuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '雾炮在设备表里的uuid',
  `linkage_is` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT 'false' COMMENT '是否联动',
  `linkage_overalarm_delay` int(11) NOT NULL DEFAULT '60' COMMENT '联动：报警延时时间 单位秒',
  `linkage_single_time` int(11) NOT NULL DEFAULT '300' COMMENT '联动：单次工作时长',
  `linkage_interval_time` int(11) NOT NULL DEFAULT '300' COMMENT '联动：间隔时长',
  `linkage_alarm_reference_mode` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '联动：报警参考方式 0手动 1天气预报',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='扬尘和雾炮的联动表';

-- ----------------------------
-- Records of equipment_link
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_towercrane_current
-- ----------------------------
DROP TABLE IF EXISTS `equipment_towercrane_current`;
CREATE TABLE `equipment_towercrane_current` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '设备编号',
  `cardNo` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '司机信息：可能是卡号，工号，Uid',
  `data_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据时间，也是就是数据包里带的时间',
  `longitude` double NOT NULL DEFAULT '0' COMMENT '经度',
  `latitude` double NOT NULL DEFAULT '0' COMMENT '纬度',
  `height` double NOT NULL DEFAULT '0' COMMENT '高度',
  `range` double NOT NULL DEFAULT '0' COMMENT '幅度',
  `rotation` double NOT NULL DEFAULT '0' COMMENT '回转.......就是那个旋转角度',
  `weight` double NOT NULL DEFAULT '0' COMMENT '重量',
  `wind_speed` double NOT NULL DEFAULT '0' COMMENT '风速',
  `dip_angle_X` double NOT NULL DEFAULT '0' COMMENT '倾角X',
  `dip_angle_Y` double NOT NULL DEFAULT '0' COMMENT '倾角Y',
  `safe_torque` double NOT NULL DEFAULT '0' COMMENT '额定（安全）力矩',
  `safe_weight` double NOT NULL DEFAULT '0' COMMENT '安全（额定）起重量',
  `rate` tinyint(4) NOT NULL DEFAULT '2' COMMENT '倍率取值2或4',
  `relay_status` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '0000000000000000' COMMENT '控制器状态',
  `sensor_status` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '0000000000000000' COMMENT '传感器状态',
  `early_warning` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '00000000000000000000000000000000' COMMENT '预警',
  `give_alarm` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '00000000000000000000000000000000' COMMENT '报警',
  `work_Circle` bigint(20) NOT NULL DEFAULT '0' COMMENT '工作循环序列号',
  `boot_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '开机运行时间  需要从运行时间包进行更新',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='塔吊实时数据表';

-- ----------------------------
-- Records of equipment_towercrane_current
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_towercrane_orderissued
-- ----------------------------
DROP TABLE IF EXISTS `equipment_towercrane_orderissued`;
CREATE TABLE `equipment_towercrane_orderissued` (
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号',
  `ip_dn` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '域名或ip地址',
  `port` int(11) NOT NULL DEFAULT '0' COMMENT '端口',
  `addr_status` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '地址下发状态：0待下发执行 1服务处理 2设备返回成功 3设备返回失败（数据错误）',
  `addr_uuid_admin_user` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '对应的是admin_user的uuid。表示更改ip的操作人',
  `addr_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更改ip的操作时间',
  `limit_value` int(11) NOT NULL DEFAULT '0' COMMENT '限位值：当前可能的值0未限位  2147483648限位（4字节最高位为1）',
  `limit_status` char(1) COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '限位下发状态：0待下发执行 1服务处理 2设备返回成功 3设备返回失败（数据错误）',
  `limit_uuid_admin_user` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '对应的是admin_user的uuid。表示更改限位的操作人',
  `limit_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更改限位的操作时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '记录最后一次被更新的时间',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='塔吊命令下发 主要是指的IP配置帧和限位控制帧';

-- ----------------------------
-- Records of equipment_towercrane_orderissued
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_towercrane_parameter
-- ----------------------------
DROP TABLE IF EXISTS `equipment_towercrane_parameter`;
CREATE TABLE `equipment_towercrane_parameter` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备表里面的UUid一致',
  `equipmentNo` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '设备编号',
  `data_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据时间，也是就是数据包里带的时间',
  `rate` tinyint(4) NOT NULL DEFAULT '2' COMMENT '倍率取值2或4',
  `min_height_sensor_value` double NOT NULL DEFAULT '0' COMMENT '最小高度时传感器采样值',
  `max_height_sensor_value` double NOT NULL DEFAULT '0' COMMENT '最大高度时传感器采样值',
  `standard_length` double NOT NULL DEFAULT '0' COMMENT '标准尺长度',
  `min_range` double NOT NULL DEFAULT '0' COMMENT '最小幅度',
  `min_range_sensor_value` double NOT NULL DEFAULT '0' COMMENT '最小幅度时传感器采样值',
  `max_range` double NOT NULL DEFAULT '0' COMMENT '最大幅度',
  `max_range_sensor_value` double NOT NULL DEFAULT '0' COMMENT '最大幅度时传感器采样值',
  `empty_hook_AD_value` double NOT NULL DEFAULT '0' COMMENT '空钩时AD采样值',
  `weight_sensor_value` double NOT NULL DEFAULT '0' COMMENT '吊载砝码时传感器采样值',
  `weight_weight` double NOT NULL DEFAULT '0' COMMENT '砝码重量',
  `rotation_type` char(1) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '1电位器回转2绝对值回转',
  `absolute_rotation_direction` char(1) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '1东2南3西4北',
  `absolute_rotation` double NOT NULL DEFAULT '0' COMMENT '绝对值回转值',
  `absolute_rotationpoint_confirm` double NOT NULL DEFAULT '0' COMMENT '绝对值回转点确认后的回转值',
  `potentiometer_rotation_leftlimit_sensor_value` double NOT NULL DEFAULT '0' COMMENT '电位器回转左限位传感器值',
  `potentiometer_rotation_rightlimit_sensor_value` double NOT NULL DEFAULT '0' COMMENT '电位器回转右限位传感器值',
  `potentiometer_rotation_limit_anglesum` double NOT NULL DEFAULT '0' COMMENT '电位器回转左右限位角度和',
  `four_rate_max_weight` double NOT NULL DEFAULT '0' COMMENT '4倍率时最大起重量',
  `four_rate_max_weight_range` double NOT NULL DEFAULT '0' COMMENT '4倍率时最大起重量幅度',
  `four_rate_max_range` double NOT NULL DEFAULT '0' COMMENT '4倍率时最大幅度',
  `four_rate_max_range_weight` double NOT NULL DEFAULT '0' COMMENT '4倍率时最大幅度的起重量',
  `two_rate_max_weight` double NOT NULL DEFAULT '0' COMMENT '2倍率时最大起重量',
  `two_rate_max_weight_range` double NOT NULL DEFAULT '0' COMMENT '2倍率时最大起重量幅度',
  `two_rate_max_range` double NOT NULL DEFAULT '0' COMMENT '2倍率时最大幅度',
  `two_rate_max_range_weight` double NOT NULL DEFAULT '0' COMMENT '2倍率时最大幅度的起重量',
  `zigbee_local_No` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'zigbee 本机编号',
  `zigbee_channel_No` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'zigbee本机频道号',
  `zigbee_group_No` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'zigbee本机组号',
  `anti_collision_local_X` double NOT NULL DEFAULT '0' COMMENT '防碰撞信息本机X',
  `anti_collision_local_Y` double NOT NULL DEFAULT '0' COMMENT '防碰撞信息本机Y',
  `lifting_arm_length` double NOT NULL DEFAULT '0' COMMENT '起重臂长',
  `balance_arm_length` double NOT NULL DEFAULT '0' COMMENT '平衡臂长',
  `tower_body_height` double NOT NULL DEFAULT '0' COMMENT '塔身高',
  `tower_hat_height` double NOT NULL DEFAULT '0' COMMENT '塔帽高',
  `range_decelerate` double NOT NULL DEFAULT '0' COMMENT '幅度减速值  单位 米',
  `range_speedlimit` double NOT NULL DEFAULT '0' COMMENT '幅度限速值   单位  米',
  `height_decelerate` double NOT NULL DEFAULT '0' COMMENT '高度减速值',
  `height_speedlimit` double NOT NULL DEFAULT '0' COMMENT '高度限速值',
  `rotation_decelerate` double NOT NULL DEFAULT '0' COMMENT '回转减速值',
  `rotation_speedlimit` double NOT NULL DEFAULT '0' COMMENT '回转限速值',
  `locality_protection_decelerate` double NOT NULL DEFAULT '0' COMMENT '区域保护减速值',
  `locality_protection_speedlimit` double NOT NULL DEFAULT '0' COMMENT '区域保护限速值',
  `anti_collision_decelerate` double NOT NULL DEFAULT '0' COMMENT '防碰撞减速值',
  `anti_collision_speedlimit` double NOT NULL DEFAULT '0' COMMENT '防碰撞限速值',
  `speed_exchange_torque` double NOT NULL DEFAULT '0' COMMENT '换速力矩',
  `cutting_torque` double NOT NULL DEFAULT '0' COMMENT '切断力矩',
  `speed_exchange_weight` double NOT NULL DEFAULT '0' COMMENT '换速重量',
  `cutting_weight` double NOT NULL DEFAULT '0' COMMENT '切断重量',
  `soft_version` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '软件版本号',
  `creat_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='塔吊参数表';

-- ----------------------------
-- Records of equipment_towercrane_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_video
-- ----------------------------
DROP TABLE IF EXISTS `equipment_video`;
CREATE TABLE `equipment_video` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '数据id',
  `name` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '摄像头名称',
  `deptUuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '所属工地  或者单位',
  `flowAddress` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '视频拉流地址',
  `streamUrl` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '推流地址',
  `playUrl` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '播放地址',
  `deployTime` bigint(16) DEFAULT NULL COMMENT '部署安装时间',
  `brandName` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '品牌名称',
  `type` varchar(1) CHARACTER SET utf8 NOT NULL COMMENT '1：球机  2：枪机',
  `voperation` varchar(8) COLLATE utf8_bin DEFAULT '1' COMMENT '变焦 1  旋转 2   3焦点  4 光圈',
  `isTowerEye` int(2) DEFAULT '0' COMMENT '是否一体机推流方式 (1是 2否)',
  `pushFlowMode` varchar(1) COLLATE utf8_bin DEFAULT '0' COMMENT '推流方式（呼叫0，长期1）',
  `ip` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'ip地址',
  `port` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '端口号',
  `userName` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '0正常   1 删除',
  `lasttime` datetime DEFAULT NULL COMMENT '最后一次刷新的时间 可以判断在线与否',
  `onvifstatus` varchar(8) COLLATE utf8_bin DEFAULT 'no' COMMENT 'no 不在线 yes 判断lasttime 不能超过2分钟',
  `isEyeClient` int(1) DEFAULT '2' COMMENT '是否是塔吊眼 (1是 2否)',
  `equipmentUuid` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备数据uuid 比如塔吊',
  `remoteConnectionString` text COLLATE utf8_bin COMMENT '客户端连接字符串',
  `remoteTime` datetime DEFAULT NULL COMMENT '连接字符串被更新时的时间',
  PRIMARY KEY (`id`),
  KEY `uuid` (`uuid`) USING BTREE,
  KEY `deptUuid` (`deptUuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='视频摄像头表';

-- ----------------------------
-- Records of equipment_video
-- ----------------------------
INSERT INTO `equipment_video` VALUES ('1', '03b7651e71a647ff94781c93f03c6bf7', '测试2', '14228d15566544d9a427a600c9ba2283', null, null, null, '0', null, '1', '1', '1', '0', null, null, null, null, '0', null, 'no', '2', null, null, null);
INSERT INTO `equipment_video` VALUES ('2', '9cdaa6b5e9944ca5874cab1424eb59e6', '测试2', '14228d15566544d9a427a600c9ba2283', 'http:3', 'http:2', 'http:1', '1541692800000', '海康', '1', '1,2,3,4', '2', '0', '1212121', '3333', '13426277685', '1212', '0', null, 'no', '1', '4eb7ccd5250847c29dba6446792f9080', null, null);
INSERT INTO `equipment_video` VALUES ('3', 'fa142b48900440c4a2892fd546149284', '测试2', '14228d15566544d9a427a600c9ba2283', null, null, null, '1541088000000', null, '1', '1', '1', '0', null, null, null, null, '0', null, 'no', '1', 'd8f4b07ef12548a9a016595db27c015b', null, null);
INSERT INTO `equipment_video` VALUES ('4', '1cdd55bd695847b3987030003ecc4755', '111', '14228d15566544d9a427a600c9ba2283', null, null, null, '1542297600000', '海康', '1', '1', '1', '0', null, null, null, null, '0', null, 'no', '1', 'd8f4b07ef12548a9a016595db27c015b', null, null);

-- ----------------------------
-- Table structure for forwardconfig
-- ----------------------------
DROP TABLE IF EXISTS `forwardconfig`;
CREATE TABLE `forwardconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `SendTag` varchar(50) DEFAULT NULL COMMENT '标识码(如果标识码是100用作人员数据转发)',
  `Server` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `Port` varchar(255) DEFAULT NULL COMMENT '端口',
  `SNPrefix` varchar(50) DEFAULT NULL,
  `linktype` int(11) DEFAULT NULL COMMENT '链接类型 1 tcp 2 udp 3 http',
  `AgreementName` varchar(50) DEFAULT NULL COMMENT '协议名称',
  `ServerType` varchar(50) DEFAULT NULL,
  `ClassName` varchar(50) DEFAULT NULL COMMENT '程序的类名',
  `ForwardCount` int(11) DEFAULT '0' COMMENT '转发统计发送次数，默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='第三方平台转发表';

-- ----------------------------
-- Records of forwardconfig
-- ----------------------------

-- ----------------------------
-- Table structure for personnel_activation
-- ----------------------------
DROP TABLE IF EXISTS `personnel_activation`;
CREATE TABLE `personnel_activation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `b_count` int(11) DEFAULT NULL COMMENT '开始人员编号',
  `e_count` int(11) DEFAULT NULL COMMENT '结束人员编号',
  `b_count1` int(11) DEFAULT NULL COMMENT '第一次扩容开始人员编号',
  `e_count1` int(11) DEFAULT NULL COMMENT '第一次扩容结束人员编号',
  `activationid` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '激活码',
  `activationstatus` int(11) DEFAULT '0' COMMENT '激活状态（0：未激活，1：已激活）',
  `client_type` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '0 人员管理  1视频推拉流',
  `winmac` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '激活电脑mac地址',
  `proid` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '工地编号',
  `proName` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '工地名称',
  `datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `activationdate` datetime DEFAULT NULL COMMENT '激活时间',
  `changeCount` int(11) DEFAULT '0',
  `Remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lasttime` datetime DEFAULT NULL COMMENT '最后一次更新的时间 可以判断客户端是否在线',
  `remoteconnectionstring` text COLLATE utf8_bin COMMENT '客户端连接字符串',
  `remotetime` datetime DEFAULT NULL COMMENT '连接字符串被更新时的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='客户端激活码';

-- ----------------------------
-- Records of personnel_activation
-- ----------------------------

-- ----------------------------
-- Table structure for personnel_attendance
-- ----------------------------
DROP TABLE IF EXISTS `personnel_attendance`;
CREATE TABLE `personnel_attendance` (
  `empNo` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '考勤卡卡号',
  `inOrOut` int(2) NOT NULL COMMENT '1 进入   2出去',
  `attendanceTime` bigint(16) NOT NULL COMMENT '考勤时间',
  `proUuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '考勤工地',
  KEY `proUuid` (`proUuid`),
  KEY `empNo` (`empNo`)
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员进出打卡记录表，';

-- ----------------------------
-- Records of personnel_attendance
-- ----------------------------

-- ----------------------------
-- Table structure for personnel_attendance_statistics
-- ----------------------------
DROP TABLE IF EXISTS `personnel_attendance_statistics`;
CREATE TABLE `personnel_attendance_statistics` (
  `empNo` varchar(8) COLLATE utf8_bin NOT NULL COMMENT '人员编号',
  `inTime` bigint(16) NOT NULL COMMENT '入场时间时间戳',
  `outTime` bigint(16) NOT NULL DEFAULT '0' COMMENT '出场时间时间戳',
  `proUuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '考勤工地',
  `residenceTime` bigint(20) DEFAULT NULL COMMENT '滞留时间',
  KEY `proUuid` (`proUuid`)
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员考勤分析表';

-- ----------------------------
-- Records of personnel_attendance_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for personnel_card
-- ----------------------------
DROP TABLE IF EXISTS `personnel_card`;
CREATE TABLE `personnel_card` (
  `cardNo` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '卡号，全表唯一 不重复',
  `type` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '卡类型   ic2白卡 saftid 安全帽',
  `empNo` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '人员编号',
  PRIMARY KEY (`cardNo`),
  KEY `empNo` (`empNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员卡表  人脸表等等';

-- ----------------------------
-- Records of personnel_card
-- ----------------------------

-- ----------------------------
-- Table structure for personnel_identity
-- ----------------------------
DROP TABLE IF EXISTS `personnel_identity`;
CREATE TABLE `personnel_identity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `empNo` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '数据id 也就是人员身份证号',
  `type` int(2) NOT NULL COMMENT '人员类型  sys_dictionry 表  personnelType字段值 1 管理 2  劳务',
  `companyUuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '人员所属企业uuid   unit_company 表的uuid',
  `proUuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '人员所属工地uuid   unit_projectUuid   表的uuid',
  `groupUuid` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '劳务人员所属班组  unit_group 表的uuid',
  `isGroupLeader` int(10) unsigned DEFAULT NULL COMMENT '是否是班组长',
  `workType` int(11) DEFAULT NULL COMMENT '劳务人员工种   unit_workType 表的id',
  `deptUuid` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '管理人员所属部门   unit_departMent  表的 uuid',
  `post` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '管理人员岗位   unit_post 表的uuid',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除0正常   1 数据删除',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新时间时间戳',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员工作记录身份表    可以有多条数据';

-- ----------------------------
-- Records of personnel_identity
-- ----------------------------

-- ----------------------------
-- Table structure for personnel_realname_system
-- ----------------------------
DROP TABLE IF EXISTS `personnel_realname_system`;
CREATE TABLE `personnel_realname_system` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `empNo` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '人员编号',
  `name` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '人员姓名',
  `code` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '身份证号       身份证号最为全表唯一主键',
  `sex` int(2) NOT NULL COMMENT 'sys_dictionary表    userSex 字段值 0 女 1 男',
  `birthday` date NOT NULL COMMENT '生日         ',
  `age` int(11) NOT NULL,
  `birthplace` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '籍贯',
  `photo` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '人员头像',
  `nation` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '民族',
  `homeAddress` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '家庭住址',
  `telephone` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '手机号码',
  `manualInput` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '是否手动输入的人员实名制信息，1否  0是',
  `dateIn` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '入场日期：用字符串的形式存储有可能为空。',
  `addTime` bigint(20) NOT NULL COMMENT '增加时间',
  `updateTime` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `empNo` (`empNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人员实名制表，这个是用来保存人员的实名制信息的';

-- ----------------------------
-- Records of personnel_realname_system
-- ----------------------------

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(50) DEFAULT NULL,
  `pinyin` varchar(100) DEFAULT NULL,
  `jianpin` varchar(100) DEFAULT NULL,
  `pcode` int(10) DEFAULT NULL,
  `code` int(10) DEFAULT NULL,
  `lng` varchar(32) DEFAULT NULL COMMENT '经度',
  `lat` varchar(32) DEFAULT NULL COMMENT '维度',
  PRIMARY KEY (`id`),
  KEY `code索引` (`code`),
  KEY `pcode索引` (`pcode`)
) ENGINE=MyISAM AUTO_INCREMENT=3322 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='城市地区表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('102', '北京市', 'beijingshi', 'bjs', '0', '110000', null, null);
INSERT INTO `sys_area` VALUES ('103', '东城区', 'dongchengqu', 'dcq', '110000', '110101', null, null);
INSERT INTO `sys_area` VALUES ('104', '西城区', 'xichengqu', 'xcq', '110000', '110102', null, null);
INSERT INTO `sys_area` VALUES ('105', '朝阳区', 'chaoyangqu', 'cyq', '110000', '110105', null, null);
INSERT INTO `sys_area` VALUES ('106', '丰台区', 'fengtaiqu', 'ftq', '110000', '110106', null, null);
INSERT INTO `sys_area` VALUES ('107', '石景山区', 'shijingshanqu', 'sjsq', '110000', '110107', null, null);
INSERT INTO `sys_area` VALUES ('108', '海淀区', 'haidianqu', 'hdq', '110000', '110108', null, null);
INSERT INTO `sys_area` VALUES ('109', '门头沟区', 'mentougouqu', 'mtgq', '110000', '110109', null, null);
INSERT INTO `sys_area` VALUES ('110', '房山区', 'fangshanqu', 'fsq', '110000', '110111', null, null);
INSERT INTO `sys_area` VALUES ('111', '通州区', 'tongzhouqu', 'tzq', '110000', '110112', null, null);
INSERT INTO `sys_area` VALUES ('112', '顺义区', 'shunyiqu', 'syq', '110000', '110113', null, null);
INSERT INTO `sys_area` VALUES ('113', '昌平区', 'changpingqu', 'cpq', '110000', '110114', null, null);
INSERT INTO `sys_area` VALUES ('114', '大兴区', 'daxingqu', 'dxq', '110000', '110115', null, null);
INSERT INTO `sys_area` VALUES ('115', '怀柔区', 'huairouqu', 'hrq', '110000', '110116', null, null);
INSERT INTO `sys_area` VALUES ('116', '平谷区', 'pingguqu', 'pgq', '110000', '110117', null, null);
INSERT INTO `sys_area` VALUES ('117', '密云区', 'miyunqu', 'myq', '110000', '110118', null, null);
INSERT INTO `sys_area` VALUES ('118', '延庆区', 'yanqingqu', 'yqq', '110000', '110119', null, null);
INSERT INTO `sys_area` VALUES ('119', '天津市', 'tianjinshi', 'tjs', '0', '120000', null, null);
INSERT INTO `sys_area` VALUES ('120', '和平区', 'hepingqu', 'hpq', '120000', '120101', null, null);
INSERT INTO `sys_area` VALUES ('121', '河东区', 'hedongqu', 'hdq', '120000', '120102', null, null);
INSERT INTO `sys_area` VALUES ('122', '河西区', 'hexiqu', 'hxq', '120000', '120103', null, null);
INSERT INTO `sys_area` VALUES ('123', '南开区', 'nankaiqu', 'nkq', '120000', '120104', null, null);
INSERT INTO `sys_area` VALUES ('124', '河北区', 'hebeiqu', 'hbq', '120000', '120105', null, null);
INSERT INTO `sys_area` VALUES ('125', '红桥区', 'hongqiaoqu', 'hqq', '120000', '120106', null, null);
INSERT INTO `sys_area` VALUES ('126', '东丽区', 'dongliqu', 'dlq', '120000', '120110', null, null);
INSERT INTO `sys_area` VALUES ('127', '西青区', 'xiqingqu', 'xqq', '120000', '120111', null, null);
INSERT INTO `sys_area` VALUES ('128', '津南区', 'jinnanqu', 'jnq', '120000', '120112', null, null);
INSERT INTO `sys_area` VALUES ('129', '北辰区', 'beichenqu', 'bcq', '120000', '120113', null, null);
INSERT INTO `sys_area` VALUES ('130', '武清区', 'wuqingqu', 'wqq', '120000', '120114', null, null);
INSERT INTO `sys_area` VALUES ('131', '宝坻区', 'baozuoqu', 'b坻q', '120000', '120115', null, null);
INSERT INTO `sys_area` VALUES ('132', '滨海新区', 'binhaixinqu', 'bhxq', '120000', '120116', null, null);
INSERT INTO `sys_area` VALUES ('133', '宁河区', 'ninghequ', 'nhq', '120000', '120117', null, null);
INSERT INTO `sys_area` VALUES ('134', '静海区', 'jinghaiqu', 'jhq', '120000', '120118', null, null);
INSERT INTO `sys_area` VALUES ('135', '蓟州区', 'jizhouqu', 'jzq', '120000', '120119', null, null);
INSERT INTO `sys_area` VALUES ('136', '河北省', 'hebeisheng', 'hbs', '0', '130000', null, null);
INSERT INTO `sys_area` VALUES ('137', '石家庄市', 'shijiazhuangshi', 'sjzs', '130000', '130100', null, null);
INSERT INTO `sys_area` VALUES ('138', '长安区', 'changanqu', 'caq', '130100', '130102', null, null);
INSERT INTO `sys_area` VALUES ('139', '桥西区', 'qiaoxiqu', 'qxq', '130100', '130104', null, null);
INSERT INTO `sys_area` VALUES ('140', '新华区', 'xinhuaqu', 'xhq', '130100', '130105', null, null);
INSERT INTO `sys_area` VALUES ('141', '井陉矿区', 'jingzuokuangqu', 'j陉kq', '130100', '130107', null, null);
INSERT INTO `sys_area` VALUES ('142', '裕华区', 'yuhuaqu', 'yhq', '130100', '130108', null, null);
INSERT INTO `sys_area` VALUES ('143', '藁城区', 'zuochengqu', '藁cq', '130100', '130109', null, null);
INSERT INTO `sys_area` VALUES ('144', '鹿泉区', 'luquanqu', 'lqq', '130100', '130110', null, null);
INSERT INTO `sys_area` VALUES ('145', '栾城区', 'zuochengqu', '栾cq', '130100', '130111', null, null);
INSERT INTO `sys_area` VALUES ('146', '井陉县', 'jingzuoxian', 'j陉x', '130100', '130121', null, null);
INSERT INTO `sys_area` VALUES ('147', '正定县', 'zhengdingxian', 'zdx', '130100', '130123', null, null);
INSERT INTO `sys_area` VALUES ('148', '行唐县', 'xingtangxian', 'xtx', '130100', '130125', null, null);
INSERT INTO `sys_area` VALUES ('149', '灵寿县', 'lingshouxian', 'lsx', '130100', '130126', null, null);
INSERT INTO `sys_area` VALUES ('150', '高邑县', 'gaoyixian', 'gyx', '130100', '130127', null, null);
INSERT INTO `sys_area` VALUES ('151', '深泽县', 'shenzexian', 'szx', '130100', '130128', null, null);
INSERT INTO `sys_area` VALUES ('152', '赞皇县', 'zanhuangxian', 'zhx', '130100', '130129', null, null);
INSERT INTO `sys_area` VALUES ('153', '无极县', 'wujixian', 'wjx', '130100', '130130', null, null);
INSERT INTO `sys_area` VALUES ('154', '平山县', 'pingshanxian', 'psx', '130100', '130131', null, null);
INSERT INTO `sys_area` VALUES ('155', '元氏县', 'yuanshixian', 'ysx', '130100', '130132', null, null);
INSERT INTO `sys_area` VALUES ('156', '赵县', 'zhaoxian', 'zx', '130100', '130133', null, null);
INSERT INTO `sys_area` VALUES ('157', '辛集市', 'xinjishi', 'xjs', '130100', '130181', null, null);
INSERT INTO `sys_area` VALUES ('158', '晋州市', 'jinzhoushi', 'jzs', '130100', '130183', null, null);
INSERT INTO `sys_area` VALUES ('159', '新乐市', 'xinleshi', 'xls', '130100', '130184', null, null);
INSERT INTO `sys_area` VALUES ('160', '唐山市', 'tangshanshi', 'tss', '130000', '130200', null, null);
INSERT INTO `sys_area` VALUES ('161', '路南区', 'lunanqu', 'lnq', '130200', '130202', null, null);
INSERT INTO `sys_area` VALUES ('162', '路北区', 'lubeiqu', 'lbq', '130200', '130203', null, null);
INSERT INTO `sys_area` VALUES ('163', '古冶区', 'guyequ', 'gyq', '130200', '130204', null, null);
INSERT INTO `sys_area` VALUES ('164', '开平区', 'kaipingqu', 'kpq', '130200', '130205', null, null);
INSERT INTO `sys_area` VALUES ('165', '丰南区', 'fengnanqu', 'fnq', '130200', '130207', null, null);
INSERT INTO `sys_area` VALUES ('166', '丰润区', 'fengrunqu', 'frq', '130200', '130208', null, null);
INSERT INTO `sys_area` VALUES ('167', '曹妃甸区', 'caozuodianqu', 'c妃dq', '130200', '130209', null, null);
INSERT INTO `sys_area` VALUES ('168', '滦县', 'luanxian', 'lx', '130200', '130223', null, null);
INSERT INTO `sys_area` VALUES ('169', '滦南县', 'luannanxian', 'lnx', '130200', '130224', null, null);
INSERT INTO `sys_area` VALUES ('170', '乐亭县', 'letingxian', 'ltx', '130200', '130225', null, null);
INSERT INTO `sys_area` VALUES ('171', '迁西县', 'qianxixian', 'qxx', '130200', '130227', null, null);
INSERT INTO `sys_area` VALUES ('172', '玉田县', 'yutianxian', 'ytx', '130200', '130229', null, null);
INSERT INTO `sys_area` VALUES ('173', '遵化市', 'zunhuashi', 'zhs', '130200', '130281', null, null);
INSERT INTO `sys_area` VALUES ('174', '迁安市', 'qiananshi', 'qas', '130200', '130283', null, null);
INSERT INTO `sys_area` VALUES ('175', '秦皇岛市', 'qinhuangdaoshi', 'qhds', '130000', '130300', null, null);
INSERT INTO `sys_area` VALUES ('176', '海港区', 'haigangqu', 'hgq', '130300', '130302', null, null);
INSERT INTO `sys_area` VALUES ('177', '山海关区', 'shanhaiguanqu', 'shgq', '130300', '130303', null, null);
INSERT INTO `sys_area` VALUES ('178', '北戴河区', 'beidaihequ', 'bdhq', '130300', '130304', null, null);
INSERT INTO `sys_area` VALUES ('179', '抚宁区', 'funingqu', 'fnq', '130300', '130306', null, null);
INSERT INTO `sys_area` VALUES ('180', '青龙满族自治县', 'qinglongmanzuzizhixian', 'qlmzzzx', '130300', '130321', null, null);
INSERT INTO `sys_area` VALUES ('181', '昌黎县', 'changlixian', 'clx', '130300', '130322', null, null);
INSERT INTO `sys_area` VALUES ('182', '卢龙县', 'lulongxian', 'llx', '130300', '130324', null, null);
INSERT INTO `sys_area` VALUES ('183', '邯郸市', 'handanshi', 'hds', '130000', '130400', null, null);
INSERT INTO `sys_area` VALUES ('184', '邯山区', 'hanshanqu', 'hsq', '130400', '130402', null, null);
INSERT INTO `sys_area` VALUES ('185', '丛台区', 'congtaiqu', 'ctq', '130400', '130403', null, null);
INSERT INTO `sys_area` VALUES ('186', '复兴区', 'fuxingqu', 'fxq', '130400', '130404', null, null);
INSERT INTO `sys_area` VALUES ('187', '峰峰矿区', 'fengfengkuangqu', 'ffkq', '130400', '130406', null, null);
INSERT INTO `sys_area` VALUES ('188', '肥乡区', 'feixiangqu', 'fxq', '130400', '130407', null, null);
INSERT INTO `sys_area` VALUES ('189', '永年区', 'yongnianqu', 'ynq', '130400', '130408', null, null);
INSERT INTO `sys_area` VALUES ('190', '临漳县', 'linzhangxian', 'lzx', '130400', '130423', null, null);
INSERT INTO `sys_area` VALUES ('191', '成安县', 'chenganxian', 'cax', '130400', '130424', null, null);
INSERT INTO `sys_area` VALUES ('192', '大名县', 'damingxian', 'dmx', '130400', '130425', null, null);
INSERT INTO `sys_area` VALUES ('193', '涉县', 'shexian', 'sx', '130400', '130426', null, null);
INSERT INTO `sys_area` VALUES ('194', '磁县', 'cixian', 'cx', '130400', '130427', null, null);
INSERT INTO `sys_area` VALUES ('195', '邱县', 'qiuxian', 'qx', '130400', '130430', null, null);
INSERT INTO `sys_area` VALUES ('196', '鸡泽县', 'jizexian', 'jzx', '130400', '130431', null, null);
INSERT INTO `sys_area` VALUES ('197', '广平县', 'guangpingxian', 'gpx', '130400', '130432', null, null);
INSERT INTO `sys_area` VALUES ('198', '馆陶县', 'guantaoxian', 'gtx', '130400', '130433', null, null);
INSERT INTO `sys_area` VALUES ('199', '魏县', 'weixian', 'wx', '130400', '130434', null, null);
INSERT INTO `sys_area` VALUES ('200', '曲周县', 'quzhouxian', 'qzx', '130400', '130435', null, null);
INSERT INTO `sys_area` VALUES ('201', '武安市', 'wuanshi', 'was', '130400', '130481', null, null);
INSERT INTO `sys_area` VALUES ('202', '邢台市', 'xingtaishi', 'xts', '130000', '130500', null, null);
INSERT INTO `sys_area` VALUES ('203', '桥东区', 'qiaodongqu', 'qdq', '130500', '130502', null, null);
INSERT INTO `sys_area` VALUES ('204', '桥西区', 'qiaoxiqu', 'qxq', '130500', '130503', null, null);
INSERT INTO `sys_area` VALUES ('205', '邢台县', 'xingtaixian', 'xtx', '130500', '130521', null, null);
INSERT INTO `sys_area` VALUES ('206', '临城县', 'linchengxian', 'lcx', '130500', '130522', null, null);
INSERT INTO `sys_area` VALUES ('207', '内丘县', 'neiqiuxian', 'nqx', '130500', '130523', null, null);
INSERT INTO `sys_area` VALUES ('208', '柏乡县', 'baixiangxian', 'bxx', '130500', '130524', null, null);
INSERT INTO `sys_area` VALUES ('209', '隆尧县', 'longyaoxian', 'lyx', '130500', '130525', null, null);
INSERT INTO `sys_area` VALUES ('210', '任县', 'renxian', 'rx', '130500', '130526', null, null);
INSERT INTO `sys_area` VALUES ('211', '南和县', 'nanhexian', 'nhx', '130500', '130527', null, null);
INSERT INTO `sys_area` VALUES ('212', '宁晋县', 'ningjinxian', 'njx', '130500', '130528', null, null);
INSERT INTO `sys_area` VALUES ('213', '巨鹿县', 'juluxian', 'jlx', '130500', '130529', null, null);
INSERT INTO `sys_area` VALUES ('214', '新河县', 'xinhexian', 'xhx', '130500', '130530', null, null);
INSERT INTO `sys_area` VALUES ('215', '广宗县', 'guangzongxian', 'gzx', '130500', '130531', null, null);
INSERT INTO `sys_area` VALUES ('216', '平乡县', 'pingxiangxian', 'pxx', '130500', '130532', null, null);
INSERT INTO `sys_area` VALUES ('217', '威县', 'weixian', 'wx', '130500', '130533', null, null);
INSERT INTO `sys_area` VALUES ('218', '清河县', 'qinghexian', 'qhx', '130500', '130534', null, null);
INSERT INTO `sys_area` VALUES ('219', '临西县', 'linxixian', 'lxx', '130500', '130535', null, null);
INSERT INTO `sys_area` VALUES ('220', '南宫市', 'nangongshi', 'ngs', '130500', '130581', null, null);
INSERT INTO `sys_area` VALUES ('221', '沙河市', 'shaheshi', 'shs', '130500', '130582', null, null);
INSERT INTO `sys_area` VALUES ('222', '保定市', 'baodingshi', 'bds', '130000', '130600', null, null);
INSERT INTO `sys_area` VALUES ('223', '竞秀区', 'jingxiuqu', 'jxq', '130600', '130602', null, null);
INSERT INTO `sys_area` VALUES ('224', '莲池区', 'lianchiqu', 'lcq', '130600', '130606', null, null);
INSERT INTO `sys_area` VALUES ('225', '满城区', 'manchengqu', 'mcq', '130600', '130607', null, null);
INSERT INTO `sys_area` VALUES ('226', '清苑区', 'qingyuanqu', 'qyq', '130600', '130608', null, null);
INSERT INTO `sys_area` VALUES ('227', '徐水区', 'xushuiqu', 'xsq', '130600', '130609', null, null);
INSERT INTO `sys_area` VALUES ('228', '涞水县', 'zuoshuixian', '涞sx', '130600', '130623', null, null);
INSERT INTO `sys_area` VALUES ('229', '阜平县', 'fupingxian', 'fpx', '130600', '130624', null, null);
INSERT INTO `sys_area` VALUES ('230', '定兴县', 'dingxingxian', 'dxx', '130600', '130626', null, null);
INSERT INTO `sys_area` VALUES ('231', '唐县', 'tangxian', 'tx', '130600', '130627', null, null);
INSERT INTO `sys_area` VALUES ('232', '高阳县', 'gaoyangxian', 'gyx', '130600', '130628', null, null);
INSERT INTO `sys_area` VALUES ('233', '容城县', 'rongchengxian', 'rcx', '130600', '130629', null, null);
INSERT INTO `sys_area` VALUES ('234', '涞源县', 'zuoyuanxian', '涞yx', '130600', '130630', null, null);
INSERT INTO `sys_area` VALUES ('235', '望都县', 'wangduxian', 'wdx', '130600', '130631', null, null);
INSERT INTO `sys_area` VALUES ('236', '安新县', 'anxinxian', 'axx', '130600', '130632', null, null);
INSERT INTO `sys_area` VALUES ('237', '易县', 'yixian', 'yx', '130600', '130633', null, null);
INSERT INTO `sys_area` VALUES ('238', '曲阳县', 'quyangxian', 'qyx', '130600', '130634', null, null);
INSERT INTO `sys_area` VALUES ('239', '蠡县', 'zuoxian', '蠡x', '130600', '130635', null, null);
INSERT INTO `sys_area` VALUES ('240', '顺平县', 'shunpingxian', 'spx', '130600', '130636', null, null);
INSERT INTO `sys_area` VALUES ('241', '博野县', 'boyexian', 'byx', '130600', '130637', null, null);
INSERT INTO `sys_area` VALUES ('242', '雄县', 'xiongxian', 'xx', '130600', '130638', null, null);
INSERT INTO `sys_area` VALUES ('243', '涿州市', 'zuozhoushi', '涿zs', '130600', '130681', null, null);
INSERT INTO `sys_area` VALUES ('244', '定州市', 'dingzhoushi', 'dzs', '130600', '130682', null, null);
INSERT INTO `sys_area` VALUES ('245', '安国市', 'anguoshi', 'ags', '130600', '130683', null, null);
INSERT INTO `sys_area` VALUES ('246', '高碑店市', 'gaobeidianshi', 'gbds', '130600', '130684', null, null);
INSERT INTO `sys_area` VALUES ('247', '张家口市', 'zhangjiakoushi', 'zjks', '130000', '130700', null, null);
INSERT INTO `sys_area` VALUES ('248', '桥东区', 'qiaodongqu', 'qdq', '130700', '130702', null, null);
INSERT INTO `sys_area` VALUES ('249', '桥西区', 'qiaoxiqu', 'qxq', '130700', '130703', null, null);
INSERT INTO `sys_area` VALUES ('250', '宣化区', 'xuanhuaqu', 'xhq', '130700', '130705', null, null);
INSERT INTO `sys_area` VALUES ('251', '下花园区', 'xiahuayuanqu', 'xhyq', '130700', '130706', null, null);
INSERT INTO `sys_area` VALUES ('252', '万全区', 'wanquanqu', 'wqq', '130700', '130708', null, null);
INSERT INTO `sys_area` VALUES ('253', '崇礼区', 'chongliqu', 'clq', '130700', '130709', null, null);
INSERT INTO `sys_area` VALUES ('254', '张北县', 'zhangbeixian', 'zbx', '130700', '130722', null, null);
INSERT INTO `sys_area` VALUES ('255', '康保县', 'kangbaoxian', 'kbx', '130700', '130723', null, null);
INSERT INTO `sys_area` VALUES ('256', '沽源县', 'guyuanxian', 'gyx', '130700', '130724', null, null);
INSERT INTO `sys_area` VALUES ('257', '尚义县', 'shangyixian', 'syx', '130700', '130725', null, null);
INSERT INTO `sys_area` VALUES ('258', '蔚县', 'weixian', 'wx', '130700', '130726', null, null);
INSERT INTO `sys_area` VALUES ('259', '阳原县', 'yangyuanxian', 'yyx', '130700', '130727', null, null);
INSERT INTO `sys_area` VALUES ('260', '怀安县', 'huaianxian', 'hax', '130700', '130728', null, null);
INSERT INTO `sys_area` VALUES ('261', '怀来县', 'huailaixian', 'hlx', '130700', '130730', null, null);
INSERT INTO `sys_area` VALUES ('262', '涿鹿县', 'zuoluxian', '涿lx', '130700', '130731', null, null);
INSERT INTO `sys_area` VALUES ('263', '赤城县', 'chichengxian', 'ccx', '130700', '130732', null, null);
INSERT INTO `sys_area` VALUES ('264', '承德市', 'chengdeshi', 'cds', '130000', '130800', null, null);
INSERT INTO `sys_area` VALUES ('265', '双桥区', 'shuangqiaoqu', 'sqq', '130800', '130802', null, null);
INSERT INTO `sys_area` VALUES ('266', '双滦区', 'shuangluanqu', 'slq', '130800', '130803', null, null);
INSERT INTO `sys_area` VALUES ('267', '鹰手营子矿区', 'yingshouyingzikuangqu', 'ysyzkq', '130800', '130804', null, null);
INSERT INTO `sys_area` VALUES ('268', '承德县', 'chengdexian', 'cdx', '130800', '130821', null, null);
INSERT INTO `sys_area` VALUES ('269', '兴隆县', 'xinglongxian', 'xlx', '130800', '130822', null, null);
INSERT INTO `sys_area` VALUES ('270', '滦平县', 'luanpingxian', 'lpx', '130800', '130824', null, null);
INSERT INTO `sys_area` VALUES ('271', '隆化县', 'longhuaxian', 'lhx', '130800', '130825', null, null);
INSERT INTO `sys_area` VALUES ('272', '丰宁满族自治县', 'fengningmanzuzizhixian', 'fnmzzzx', '130800', '130826', null, null);
INSERT INTO `sys_area` VALUES ('273', '宽城满族自治县', 'kuanchengmanzuzizhixian', 'kcmzzzx', '130800', '130827', null, null);
INSERT INTO `sys_area` VALUES ('274', '围场满族蒙古族自治县', 'weichangmanzumengguzuzizhixian', 'wcmzmgzzzx', '130800', '130828', null, null);
INSERT INTO `sys_area` VALUES ('275', '平泉市', 'pingquanshi', 'pqs', '130800', '130881', null, null);
INSERT INTO `sys_area` VALUES ('276', '沧州市', 'cangzhoushi', 'czs', '130000', '130900', null, null);
INSERT INTO `sys_area` VALUES ('277', '新华区', 'xinhuaqu', 'xhq', '130900', '130902', null, null);
INSERT INTO `sys_area` VALUES ('278', '运河区', 'yunhequ', 'yhq', '130900', '130903', null, null);
INSERT INTO `sys_area` VALUES ('279', '沧县', 'cangxian', 'cx', '130900', '130921', null, null);
INSERT INTO `sys_area` VALUES ('280', '青县', 'qingxian', 'qx', '130900', '130922', null, null);
INSERT INTO `sys_area` VALUES ('281', '东光县', 'dongguangxian', 'dgx', '130900', '130923', null, null);
INSERT INTO `sys_area` VALUES ('282', '海兴县', 'haixingxian', 'hxx', '130900', '130924', null, null);
INSERT INTO `sys_area` VALUES ('283', '盐山县', 'yanshanxian', 'ysx', '130900', '130925', null, null);
INSERT INTO `sys_area` VALUES ('284', '肃宁县', 'suningxian', 'snx', '130900', '130926', null, null);
INSERT INTO `sys_area` VALUES ('285', '南皮县', 'nanpixian', 'npx', '130900', '130927', null, null);
INSERT INTO `sys_area` VALUES ('286', '吴桥县', 'wuqiaoxian', 'wqx', '130900', '130928', null, null);
INSERT INTO `sys_area` VALUES ('287', '献县', 'xianxian', 'xx', '130900', '130929', null, null);
INSERT INTO `sys_area` VALUES ('288', '孟村回族自治县', 'mengcunhuizuzizhixian', 'mchzzzx', '130900', '130930', null, null);
INSERT INTO `sys_area` VALUES ('289', '泊头市', 'botoushi', 'bts', '130900', '130981', null, null);
INSERT INTO `sys_area` VALUES ('290', '任丘市', 'renqiushi', 'rqs', '130900', '130982', null, null);
INSERT INTO `sys_area` VALUES ('291', '黄骅市', 'huangzuoshi', 'h骅s', '130900', '130983', null, null);
INSERT INTO `sys_area` VALUES ('292', '河间市', 'hejianshi', 'hjs', '130900', '130984', null, null);
INSERT INTO `sys_area` VALUES ('293', '廊坊市', 'langfangshi', 'lfs', '130000', '131000', null, null);
INSERT INTO `sys_area` VALUES ('294', '安次区', 'anciqu', 'acq', '131000', '131002', null, null);
INSERT INTO `sys_area` VALUES ('295', '广阳区', 'guangyangqu', 'gyq', '131000', '131003', null, null);
INSERT INTO `sys_area` VALUES ('296', '固安县', 'guanxian', 'gax', '131000', '131022', null, null);
INSERT INTO `sys_area` VALUES ('297', '永清县', 'yongqingxian', 'yqx', '131000', '131023', null, null);
INSERT INTO `sys_area` VALUES ('298', '香河县', 'xianghexian', 'xhx', '131000', '131024', null, null);
INSERT INTO `sys_area` VALUES ('299', '大城县', 'dachengxian', 'dcx', '131000', '131025', null, null);
INSERT INTO `sys_area` VALUES ('300', '文安县', 'wenanxian', 'wax', '131000', '131026', null, null);
INSERT INTO `sys_area` VALUES ('301', '大厂回族自治县', 'dachanghuizuzizhixian', 'dchzzzx', '131000', '131028', null, null);
INSERT INTO `sys_area` VALUES ('302', '霸州市', 'bazhoushi', 'bzs', '131000', '131081', null, null);
INSERT INTO `sys_area` VALUES ('303', '三河市', 'sanheshi', 'shs', '131000', '131082', null, null);
INSERT INTO `sys_area` VALUES ('304', '衡水市', 'hengshuishi', 'hss', '130000', '131100', null, null);
INSERT INTO `sys_area` VALUES ('305', '桃城区', 'taochengqu', 'tcq', '131100', '131102', null, null);
INSERT INTO `sys_area` VALUES ('306', '冀州区', 'jizhouqu', 'jzq', '131100', '131103', null, null);
INSERT INTO `sys_area` VALUES ('307', '枣强县', 'zaoqiangxian', 'zqx', '131100', '131121', null, null);
INSERT INTO `sys_area` VALUES ('308', '武邑县', 'wuyixian', 'wyx', '131100', '131122', null, null);
INSERT INTO `sys_area` VALUES ('309', '武强县', 'wuqiangxian', 'wqx', '131100', '131123', null, null);
INSERT INTO `sys_area` VALUES ('310', '饶阳县', 'raoyangxian', 'ryx', '131100', '131124', null, null);
INSERT INTO `sys_area` VALUES ('311', '安平县', 'anpingxian', 'apx', '131100', '131125', null, null);
INSERT INTO `sys_area` VALUES ('312', '故城县', 'guchengxian', 'gcx', '131100', '131126', null, null);
INSERT INTO `sys_area` VALUES ('313', '景县', 'jingxian', 'jx', '131100', '131127', null, null);
INSERT INTO `sys_area` VALUES ('314', '阜城县', 'fuchengxian', 'fcx', '131100', '131128', null, null);
INSERT INTO `sys_area` VALUES ('315', '深州市', 'shenzhoushi', 'szs', '131100', '131182', null, null);
INSERT INTO `sys_area` VALUES ('316', '山西省', 'shanxisheng', 'sxs', '0', '140000', null, null);
INSERT INTO `sys_area` VALUES ('317', '太原市', 'taiyuanshi', 'tys', '140000', '140100', null, null);
INSERT INTO `sys_area` VALUES ('318', '小店区', 'xiaodianqu', 'xdq', '140100', '140105', null, null);
INSERT INTO `sys_area` VALUES ('319', '迎泽区', 'yingzequ', 'yzq', '140100', '140106', null, null);
INSERT INTO `sys_area` VALUES ('320', '杏花岭区', 'xinghualingqu', 'xhlq', '140100', '140107', null, null);
INSERT INTO `sys_area` VALUES ('321', '尖草坪区', 'jiancaopingqu', 'jcpq', '140100', '140108', null, null);
INSERT INTO `sys_area` VALUES ('322', '万柏林区', 'wanbailinqu', 'wblq', '140100', '140109', null, null);
INSERT INTO `sys_area` VALUES ('323', '晋源区', 'jinyuanqu', 'jyq', '140100', '140110', null, null);
INSERT INTO `sys_area` VALUES ('324', '清徐县', 'qingxuxian', 'qxx', '140100', '140121', null, null);
INSERT INTO `sys_area` VALUES ('325', '阳曲县', 'yangquxian', 'yqx', '140100', '140122', null, null);
INSERT INTO `sys_area` VALUES ('326', '娄烦县', 'loufanxian', 'lfx', '140100', '140123', null, null);
INSERT INTO `sys_area` VALUES ('327', '古交市', 'gujiaoshi', 'gjs', '140100', '140181', null, null);
INSERT INTO `sys_area` VALUES ('328', '大同市', 'datongshi', 'dts', '140000', '140200', null, null);
INSERT INTO `sys_area` VALUES ('329', '新荣区', 'xinrongqu', 'xrq', '140200', '140212', null, null);
INSERT INTO `sys_area` VALUES ('330', '平城区', 'pingchengqu', 'pcq', '140200', '140213', null, null);
INSERT INTO `sys_area` VALUES ('331', '云冈区', 'yungangqu', 'ygq', '140200', '140214', null, null);
INSERT INTO `sys_area` VALUES ('332', '云州区', 'yunzhouqu', 'yzq', '140200', '140215', null, null);
INSERT INTO `sys_area` VALUES ('333', '阳高县', 'yanggaoxian', 'ygx', '140200', '140221', null, null);
INSERT INTO `sys_area` VALUES ('334', '天镇县', 'tianzhenxian', 'tzx', '140200', '140222', null, null);
INSERT INTO `sys_area` VALUES ('335', '广灵县', 'guanglingxian', 'glx', '140200', '140223', null, null);
INSERT INTO `sys_area` VALUES ('336', '灵丘县', 'lingqiuxian', 'lqx', '140200', '140224', null, null);
INSERT INTO `sys_area` VALUES ('337', '浑源县', 'hunyuanxian', 'hyx', '140200', '140225', null, null);
INSERT INTO `sys_area` VALUES ('338', '左云县', 'zuoyunxian', 'zyx', '140200', '140226', null, null);
INSERT INTO `sys_area` VALUES ('339', '阳泉市', 'yangquanshi', 'yqs', '140000', '140300', null, null);
INSERT INTO `sys_area` VALUES ('340', '城区', 'chengqu', 'cq', '140300', '140302', null, null);
INSERT INTO `sys_area` VALUES ('341', '矿区', 'kuangqu', 'kq', '140300', '140303', null, null);
INSERT INTO `sys_area` VALUES ('342', '郊区', 'jiaoqu', 'jq', '140300', '140311', null, null);
INSERT INTO `sys_area` VALUES ('343', '平定县', 'pingdingxian', 'pdx', '140300', '140321', null, null);
INSERT INTO `sys_area` VALUES ('344', '盂县', 'yuxian', 'yx', '140300', '140322', null, null);
INSERT INTO `sys_area` VALUES ('345', '长治市', 'changzhishi', 'czs', '140000', '140400', null, null);
INSERT INTO `sys_area` VALUES ('346', '城区', 'chengqu', 'cq', '140400', '140402', null, null);
INSERT INTO `sys_area` VALUES ('347', '郊区', 'jiaoqu', 'jq', '140400', '140411', null, null);
INSERT INTO `sys_area` VALUES ('348', '长治县', 'changzhixian', 'czx', '140400', '140421', null, null);
INSERT INTO `sys_area` VALUES ('349', '襄垣县', 'xiangyuanxian', 'xyx', '140400', '140423', null, null);
INSERT INTO `sys_area` VALUES ('350', '屯留县', 'tunliuxian', 'tlx', '140400', '140424', null, null);
INSERT INTO `sys_area` VALUES ('351', '平顺县', 'pingshunxian', 'psx', '140400', '140425', null, null);
INSERT INTO `sys_area` VALUES ('352', '黎城县', 'lichengxian', 'lcx', '140400', '140426', null, null);
INSERT INTO `sys_area` VALUES ('353', '壶关县', 'huguanxian', 'hgx', '140400', '140427', null, null);
INSERT INTO `sys_area` VALUES ('354', '长子县', 'changzixian', 'czx', '140400', '140428', null, null);
INSERT INTO `sys_area` VALUES ('355', '武乡县', 'wuxiangxian', 'wxx', '140400', '140429', null, null);
INSERT INTO `sys_area` VALUES ('356', '沁县', 'qinxian', 'qx', '140400', '140430', null, null);
INSERT INTO `sys_area` VALUES ('357', '沁源县', 'qinyuanxian', 'qyx', '140400', '140431', null, null);
INSERT INTO `sys_area` VALUES ('358', '潞城市', 'luchengshi', 'lcs', '140400', '140481', null, null);
INSERT INTO `sys_area` VALUES ('359', '晋城市', 'jinchengshi', 'jcs', '140000', '140500', null, null);
INSERT INTO `sys_area` VALUES ('360', '城区', 'chengqu', 'cq', '140500', '140502', null, null);
INSERT INTO `sys_area` VALUES ('361', '沁水县', 'qinshuixian', 'qsx', '140500', '140521', null, null);
INSERT INTO `sys_area` VALUES ('362', '阳城县', 'yangchengxian', 'ycx', '140500', '140522', null, null);
INSERT INTO `sys_area` VALUES ('363', '陵川县', 'lingchuanxian', 'lcx', '140500', '140524', null, null);
INSERT INTO `sys_area` VALUES ('364', '泽州县', 'zezhouxian', 'zzx', '140500', '140525', null, null);
INSERT INTO `sys_area` VALUES ('365', '高平市', 'gaopingshi', 'gps', '140500', '140581', null, null);
INSERT INTO `sys_area` VALUES ('366', '朔州市', 'shuozhoushi', 'szs', '140000', '140600', null, null);
INSERT INTO `sys_area` VALUES ('367', '朔城区', 'shuochengqu', 'scq', '140600', '140602', null, null);
INSERT INTO `sys_area` VALUES ('368', '平鲁区', 'pingluqu', 'plq', '140600', '140603', null, null);
INSERT INTO `sys_area` VALUES ('369', '山阴县', 'shanyinxian', 'syx', '140600', '140621', null, null);
INSERT INTO `sys_area` VALUES ('370', '应县', 'yingxian', 'yx', '140600', '140622', null, null);
INSERT INTO `sys_area` VALUES ('371', '右玉县', 'youyuxian', 'yyx', '140600', '140623', null, null);
INSERT INTO `sys_area` VALUES ('372', '怀仁市', 'huairenshi', 'hrs', '140600', '140681', null, null);
INSERT INTO `sys_area` VALUES ('373', '晋中市', 'jinzhongshi', 'jzs', '140000', '140700', null, null);
INSERT INTO `sys_area` VALUES ('374', '榆次区', 'yuciqu', 'ycq', '140700', '140702', null, null);
INSERT INTO `sys_area` VALUES ('375', '榆社县', 'yushexian', 'ysx', '140700', '140721', null, null);
INSERT INTO `sys_area` VALUES ('376', '左权县', 'zuoquanxian', 'zqx', '140700', '140722', null, null);
INSERT INTO `sys_area` VALUES ('377', '和顺县', 'heshunxian', 'hsx', '140700', '140723', null, null);
INSERT INTO `sys_area` VALUES ('378', '昔阳县', 'xiyangxian', 'xyx', '140700', '140724', null, null);
INSERT INTO `sys_area` VALUES ('379', '寿阳县', 'shouyangxian', 'syx', '140700', '140725', null, null);
INSERT INTO `sys_area` VALUES ('380', '太谷县', 'taiguxian', 'tgx', '140700', '140726', null, null);
INSERT INTO `sys_area` VALUES ('381', '祁县', 'qixian', 'qx', '140700', '140727', null, null);
INSERT INTO `sys_area` VALUES ('382', '平遥县', 'pingyaoxian', 'pyx', '140700', '140728', null, null);
INSERT INTO `sys_area` VALUES ('383', '灵石县', 'lingshixian', 'lsx', '140700', '140729', null, null);
INSERT INTO `sys_area` VALUES ('384', '介休市', 'jiexiushi', 'jxs', '140700', '140781', null, null);
INSERT INTO `sys_area` VALUES ('385', '运城市', 'yunchengshi', 'ycs', '140000', '140800', null, null);
INSERT INTO `sys_area` VALUES ('386', '盐湖区', 'yanhuqu', 'yhq', '140800', '140802', null, null);
INSERT INTO `sys_area` VALUES ('387', '临猗县', 'linzuoxian', 'l猗x', '140800', '140821', null, null);
INSERT INTO `sys_area` VALUES ('388', '万荣县', 'wanrongxian', 'wrx', '140800', '140822', null, null);
INSERT INTO `sys_area` VALUES ('389', '闻喜县', 'wenxixian', 'wxx', '140800', '140823', null, null);
INSERT INTO `sys_area` VALUES ('390', '稷山县', 'zuoshanxian', '稷sx', '140800', '140824', null, null);
INSERT INTO `sys_area` VALUES ('391', '新绛县', 'xinzuoxian', 'x绛x', '140800', '140825', null, null);
INSERT INTO `sys_area` VALUES ('392', '绛县', 'zuoxian', '绛x', '140800', '140826', null, null);
INSERT INTO `sys_area` VALUES ('393', '垣曲县', 'yuanquxian', 'yqx', '140800', '140827', null, null);
INSERT INTO `sys_area` VALUES ('394', '夏县', 'xiaxian', 'xx', '140800', '140828', null, null);
INSERT INTO `sys_area` VALUES ('395', '平陆县', 'pingluxian', 'plx', '140800', '140829', null, null);
INSERT INTO `sys_area` VALUES ('396', '芮城县', 'zuochengxian', '芮cx', '140800', '140830', null, null);
INSERT INTO `sys_area` VALUES ('397', '永济市', 'yongjishi', 'yjs', '140800', '140881', null, null);
INSERT INTO `sys_area` VALUES ('398', '河津市', 'hejinshi', 'hjs', '140800', '140882', null, null);
INSERT INTO `sys_area` VALUES ('399', '忻州市', 'xinzhoushi', 'xzs', '140000', '140900', null, null);
INSERT INTO `sys_area` VALUES ('400', '忻府区', 'xinfuqu', 'xfq', '140900', '140902', null, null);
INSERT INTO `sys_area` VALUES ('401', '定襄县', 'dingxiangxian', 'dxx', '140900', '140921', null, null);
INSERT INTO `sys_area` VALUES ('402', '五台县', 'wutaixian', 'wtx', '140900', '140922', null, null);
INSERT INTO `sys_area` VALUES ('403', '代县', 'daixian', 'dx', '140900', '140923', null, null);
INSERT INTO `sys_area` VALUES ('404', '繁峙县', 'fanzhixian', 'fzx', '140900', '140924', null, null);
INSERT INTO `sys_area` VALUES ('405', '宁武县', 'ningwuxian', 'nwx', '140900', '140925', null, null);
INSERT INTO `sys_area` VALUES ('406', '静乐县', 'jinglexian', 'jlx', '140900', '140926', null, null);
INSERT INTO `sys_area` VALUES ('407', '神池县', 'shenchixian', 'scx', '140900', '140927', null, null);
INSERT INTO `sys_area` VALUES ('408', '五寨县', 'wuzhaixian', 'wzx', '140900', '140928', null, null);
INSERT INTO `sys_area` VALUES ('409', '岢岚县', 'zuozuoxian', '岢岚x', '140900', '140929', null, null);
INSERT INTO `sys_area` VALUES ('410', '河曲县', 'hequxian', 'hqx', '140900', '140930', null, null);
INSERT INTO `sys_area` VALUES ('411', '保德县', 'baodexian', 'bdx', '140900', '140931', null, null);
INSERT INTO `sys_area` VALUES ('412', '偏关县', 'pianguanxian', 'pgx', '140900', '140932', null, null);
INSERT INTO `sys_area` VALUES ('413', '原平市', 'yuanpingshi', 'yps', '140900', '140981', null, null);
INSERT INTO `sys_area` VALUES ('414', '临汾市', 'linfenshi', 'lfs', '140000', '141000', null, null);
INSERT INTO `sys_area` VALUES ('415', '尧都区', 'yaoduqu', 'ydq', '141000', '141002', null, null);
INSERT INTO `sys_area` VALUES ('416', '曲沃县', 'quwoxian', 'qwx', '141000', '141021', null, null);
INSERT INTO `sys_area` VALUES ('417', '翼城县', 'yichengxian', 'ycx', '141000', '141022', null, null);
INSERT INTO `sys_area` VALUES ('418', '襄汾县', 'xiangfenxian', 'xfx', '141000', '141023', null, null);
INSERT INTO `sys_area` VALUES ('419', '洪洞县', 'hongdongxian', 'hdx', '141000', '141024', null, null);
INSERT INTO `sys_area` VALUES ('420', '古县', 'guxian', 'gx', '141000', '141025', null, null);
INSERT INTO `sys_area` VALUES ('421', '安泽县', 'anzexian', 'azx', '141000', '141026', null, null);
INSERT INTO `sys_area` VALUES ('422', '浮山县', 'fushanxian', 'fsx', '141000', '141027', null, null);
INSERT INTO `sys_area` VALUES ('423', '吉县', 'jixian', 'jx', '141000', '141028', null, null);
INSERT INTO `sys_area` VALUES ('424', '乡宁县', 'xiangningxian', 'xnx', '141000', '141029', null, null);
INSERT INTO `sys_area` VALUES ('425', '大宁县', 'daningxian', 'dnx', '141000', '141030', null, null);
INSERT INTO `sys_area` VALUES ('426', '隰县', 'zuoxian', '隰x', '141000', '141031', null, null);
INSERT INTO `sys_area` VALUES ('427', '永和县', 'yonghexian', 'yhx', '141000', '141032', null, null);
INSERT INTO `sys_area` VALUES ('428', '蒲县', 'puxian', 'px', '141000', '141033', null, null);
INSERT INTO `sys_area` VALUES ('429', '汾西县', 'fenxixian', 'fxx', '141000', '141034', null, null);
INSERT INTO `sys_area` VALUES ('430', '侯马市', 'houmashi', 'hms', '141000', '141081', null, null);
INSERT INTO `sys_area` VALUES ('431', '霍州市', 'huozhoushi', 'hzs', '141000', '141082', null, null);
INSERT INTO `sys_area` VALUES ('432', '吕梁市', 'lvliangshi', 'lls', '140000', '141100', null, null);
INSERT INTO `sys_area` VALUES ('433', '离石区', 'lishiqu', 'lsq', '141100', '141102', null, null);
INSERT INTO `sys_area` VALUES ('434', '文水县', 'wenshuixian', 'wsx', '141100', '141121', null, null);
INSERT INTO `sys_area` VALUES ('435', '交城县', 'jiaochengxian', 'jcx', '141100', '141122', null, null);
INSERT INTO `sys_area` VALUES ('436', '兴县', 'xingxian', 'xx', '141100', '141123', null, null);
INSERT INTO `sys_area` VALUES ('437', '临县', 'linxian', 'lx', '141100', '141124', null, null);
INSERT INTO `sys_area` VALUES ('438', '柳林县', 'liulinxian', 'llx', '141100', '141125', null, null);
INSERT INTO `sys_area` VALUES ('439', '石楼县', 'shilouxian', 'slx', '141100', '141126', null, null);
INSERT INTO `sys_area` VALUES ('440', '岚县', 'zuoxian', '岚x', '141100', '141127', null, null);
INSERT INTO `sys_area` VALUES ('441', '方山县', 'fangshanxian', 'fsx', '141100', '141128', null, null);
INSERT INTO `sys_area` VALUES ('442', '中阳县', 'zhongyangxian', 'zyx', '141100', '141129', null, null);
INSERT INTO `sys_area` VALUES ('443', '交口县', 'jiaokouxian', 'jkx', '141100', '141130', null, null);
INSERT INTO `sys_area` VALUES ('444', '孝义市', 'xiaoyishi', 'xys', '141100', '141181', null, null);
INSERT INTO `sys_area` VALUES ('445', '汾阳市', 'fenyangshi', 'fys', '141100', '141182', null, null);
INSERT INTO `sys_area` VALUES ('446', '内蒙古自治区', 'neimengguzizhiqu', 'nmgzzq', '0', '150000', null, null);
INSERT INTO `sys_area` VALUES ('447', '呼和浩特市', 'huhehaoteshi', 'hhhts', '150000', '150100', null, null);
INSERT INTO `sys_area` VALUES ('448', '新城区', 'xinchengqu', 'xcq', '150100', '150102', null, null);
INSERT INTO `sys_area` VALUES ('449', '回民区', 'huiminqu', 'hmq', '150100', '150103', null, null);
INSERT INTO `sys_area` VALUES ('450', '玉泉区', 'yuquanqu', 'yqq', '150100', '150104', null, null);
INSERT INTO `sys_area` VALUES ('451', '赛罕区', 'saihanqu', 'shq', '150100', '150105', null, null);
INSERT INTO `sys_area` VALUES ('452', '土默特左旗', 'tumotezuoqi', 'tmtzq', '150100', '150121', null, null);
INSERT INTO `sys_area` VALUES ('453', '托克托县', 'tuoketuoxian', 'tktx', '150100', '150122', null, null);
INSERT INTO `sys_area` VALUES ('454', '和林格尔县', 'helingeerxian', 'hlgex', '150100', '150123', null, null);
INSERT INTO `sys_area` VALUES ('455', '清水河县', 'qingshuihexian', 'qshx', '150100', '150124', null, null);
INSERT INTO `sys_area` VALUES ('456', '武川县', 'wuchuanxian', 'wcx', '150100', '150125', null, null);
INSERT INTO `sys_area` VALUES ('457', '包头市', 'baotoushi', 'bts', '150000', '150200', null, null);
INSERT INTO `sys_area` VALUES ('458', '东河区', 'donghequ', 'dhq', '150200', '150202', null, null);
INSERT INTO `sys_area` VALUES ('459', '昆都仑区', 'kundulunqu', 'kdlq', '150200', '150203', null, null);
INSERT INTO `sys_area` VALUES ('460', '青山区', 'qingshanqu', 'qsq', '150200', '150204', null, null);
INSERT INTO `sys_area` VALUES ('461', '石拐区', 'shiguaiqu', 'sgq', '150200', '150205', null, null);
INSERT INTO `sys_area` VALUES ('462', '白云鄂博矿区', 'baiyunebokuangqu', 'byebkq', '150200', '150206', null, null);
INSERT INTO `sys_area` VALUES ('463', '九原区', 'jiuyuanqu', 'jyq', '150200', '150207', null, null);
INSERT INTO `sys_area` VALUES ('464', '土默特右旗', 'tumoteyouqi', 'tmtyq', '150200', '150221', null, null);
INSERT INTO `sys_area` VALUES ('465', '固阳县', 'guyangxian', 'gyx', '150200', '150222', null, null);
INSERT INTO `sys_area` VALUES ('466', '达尔罕茂明安联合旗', 'daerhanmaominganlianheqi', 'dehmmalhq', '150200', '150223', null, null);
INSERT INTO `sys_area` VALUES ('467', '乌海市', 'wuhaishi', 'whs', '150000', '150300', null, null);
INSERT INTO `sys_area` VALUES ('468', '海勃湾区', 'haibowanqu', 'hbwq', '150300', '150302', null, null);
INSERT INTO `sys_area` VALUES ('469', '海南区', 'hainanqu', 'hnq', '150300', '150303', null, null);
INSERT INTO `sys_area` VALUES ('470', '乌达区', 'wudaqu', 'wdq', '150300', '150304', null, null);
INSERT INTO `sys_area` VALUES ('471', '赤峰市', 'chifengshi', 'cfs', '150000', '150400', null, null);
INSERT INTO `sys_area` VALUES ('472', '红山区', 'hongshanqu', 'hsq', '150400', '150402', null, null);
INSERT INTO `sys_area` VALUES ('473', '元宝山区', 'yuanbaoshanqu', 'ybsq', '150400', '150403', null, null);
INSERT INTO `sys_area` VALUES ('474', '松山区', 'songshanqu', 'ssq', '150400', '150404', null, null);
INSERT INTO `sys_area` VALUES ('475', '阿鲁科尔沁旗', 'alukeerqinqi', 'alkeqq', '150400', '150421', null, null);
INSERT INTO `sys_area` VALUES ('476', '巴林左旗', 'balinzuoqi', 'blzq', '150400', '150422', null, null);
INSERT INTO `sys_area` VALUES ('477', '巴林右旗', 'balinyouqi', 'blyq', '150400', '150423', null, null);
INSERT INTO `sys_area` VALUES ('478', '林西县', 'linxixian', 'lxx', '150400', '150424', null, null);
INSERT INTO `sys_area` VALUES ('479', '克什克腾旗', 'keshiketengqi', 'ksktq', '150400', '150425', null, null);
INSERT INTO `sys_area` VALUES ('480', '翁牛特旗', 'wengniuteqi', 'wntq', '150400', '150426', null, null);
INSERT INTO `sys_area` VALUES ('481', '喀喇沁旗', 'kalaqinqi', 'klqq', '150400', '150428', null, null);
INSERT INTO `sys_area` VALUES ('482', '宁城县', 'ningchengxian', 'ncx', '150400', '150429', null, null);
INSERT INTO `sys_area` VALUES ('483', '敖汉旗', 'aohanqi', 'ahq', '150400', '150430', null, null);
INSERT INTO `sys_area` VALUES ('484', '通辽市', 'tongliaoshi', 'tls', '150000', '150500', null, null);
INSERT INTO `sys_area` VALUES ('485', '科尔沁区', 'keerqinqu', 'keqq', '150500', '150502', null, null);
INSERT INTO `sys_area` VALUES ('486', '科尔沁左翼中旗', 'keerqinzuoyizhongqi', 'keqzyzq', '150500', '150521', null, null);
INSERT INTO `sys_area` VALUES ('487', '科尔沁左翼后旗', 'keerqinzuoyihouqi', 'keqzyhq', '150500', '150522', null, null);
INSERT INTO `sys_area` VALUES ('488', '开鲁县', 'kailuxian', 'klx', '150500', '150523', null, null);
INSERT INTO `sys_area` VALUES ('489', '库伦旗', 'kulunqi', 'klq', '150500', '150524', null, null);
INSERT INTO `sys_area` VALUES ('490', '奈曼旗', 'naimanqi', 'nmq', '150500', '150525', null, null);
INSERT INTO `sys_area` VALUES ('491', '扎鲁特旗', 'zhaluteqi', 'zltq', '150500', '150526', null, null);
INSERT INTO `sys_area` VALUES ('492', '霍林郭勒市', 'huolinguoleshi', 'hlgls', '150500', '150581', null, null);
INSERT INTO `sys_area` VALUES ('493', '鄂尔多斯市', 'eerduosishi', 'eedss', '150000', '150600', null, null);
INSERT INTO `sys_area` VALUES ('494', '东胜区', 'dongshengqu', 'dsq', '150600', '150602', null, null);
INSERT INTO `sys_area` VALUES ('495', '康巴什区', 'kangbashiqu', 'kbsq', '150600', '150603', null, null);
INSERT INTO `sys_area` VALUES ('496', '达拉特旗', 'dalateqi', 'dltq', '150600', '150621', null, null);
INSERT INTO `sys_area` VALUES ('497', '准格尔旗', 'zhungeerqi', 'zgeq', '150600', '150622', null, null);
INSERT INTO `sys_area` VALUES ('498', '鄂托克前旗', 'etuokeqianqi', 'etkqq', '150600', '150623', null, null);
INSERT INTO `sys_area` VALUES ('499', '鄂托克旗', 'etuokeqi', 'etkq', '150600', '150624', null, null);
INSERT INTO `sys_area` VALUES ('500', '杭锦旗', 'hangjinqi', 'hjq', '150600', '150625', null, null);
INSERT INTO `sys_area` VALUES ('501', '乌审旗', 'wushenqi', 'wsq', '150600', '150626', null, null);
INSERT INTO `sys_area` VALUES ('502', '伊金霍洛旗', 'yijinhuoluoqi', 'yjhlq', '150600', '150627', null, null);
INSERT INTO `sys_area` VALUES ('503', '呼伦贝尔市', 'hulunbeiershi', 'hlbes', '150000', '150700', null, null);
INSERT INTO `sys_area` VALUES ('504', '海拉尔区', 'hailaerqu', 'hleq', '150700', '150702', null, null);
INSERT INTO `sys_area` VALUES ('505', '扎赉诺尔区', 'zhazuonuoerqu', 'z赉neq', '150700', '150703', null, null);
INSERT INTO `sys_area` VALUES ('506', '阿荣旗', 'arongqi', 'arq', '150700', '150721', null, null);
INSERT INTO `sys_area` VALUES ('507', '莫力达瓦达斡尔族自治旗', 'molidawadawoerzuzizhiqi', 'mldwdwezzzq', '150700', '150722', null, null);
INSERT INTO `sys_area` VALUES ('508', '鄂伦春自治旗', 'elunchunzizhiqi', 'elczzq', '150700', '150723', null, null);
INSERT INTO `sys_area` VALUES ('509', '鄂温克族自治旗', 'ewenkezuzizhiqi', 'ewkzzzq', '150700', '150724', null, null);
INSERT INTO `sys_area` VALUES ('510', '陈巴尔虎旗', 'chenbaerhuqi', 'cbehq', '150700', '150725', null, null);
INSERT INTO `sys_area` VALUES ('511', '新巴尔虎左旗', 'xinbaerhuzuoqi', 'xbehzq', '150700', '150726', null, null);
INSERT INTO `sys_area` VALUES ('512', '新巴尔虎右旗', 'xinbaerhuyouqi', 'xbehyq', '150700', '150727', null, null);
INSERT INTO `sys_area` VALUES ('513', '满洲里市', 'manzhoulishi', 'mzls', '150700', '150781', null, null);
INSERT INTO `sys_area` VALUES ('514', '牙克石市', 'yakeshishi', 'ykss', '150700', '150782', null, null);
INSERT INTO `sys_area` VALUES ('515', '扎兰屯市', 'zhalantunshi', 'zlts', '150700', '150783', null, null);
INSERT INTO `sys_area` VALUES ('516', '额尔古纳市', 'eergunashi', 'eegns', '150700', '150784', null, null);
INSERT INTO `sys_area` VALUES ('517', '根河市', 'genheshi', 'ghs', '150700', '150785', null, null);
INSERT INTO `sys_area` VALUES ('518', '巴彦淖尔市', 'bayannaoershi', 'bynes', '150000', '150800', null, null);
INSERT INTO `sys_area` VALUES ('519', '临河区', 'linhequ', 'lhq', '150800', '150802', null, null);
INSERT INTO `sys_area` VALUES ('520', '五原县', 'wuyuanxian', 'wyx', '150800', '150821', null, null);
INSERT INTO `sys_area` VALUES ('521', '磴口县', 'zuokouxian', '磴kx', '150800', '150822', null, null);
INSERT INTO `sys_area` VALUES ('522', '乌拉特前旗', 'wulateqianqi', 'wltqq', '150800', '150823', null, null);
INSERT INTO `sys_area` VALUES ('523', '乌拉特中旗', 'wulatezhongqi', 'wltzq', '150800', '150824', null, null);
INSERT INTO `sys_area` VALUES ('524', '乌拉特后旗', 'wulatehouqi', 'wlthq', '150800', '150825', null, null);
INSERT INTO `sys_area` VALUES ('525', '杭锦后旗', 'hangjinhouqi', 'hjhq', '150800', '150826', null, null);
INSERT INTO `sys_area` VALUES ('526', '乌兰察布市', 'wulanchabushi', 'wlcbs', '150000', '150900', null, null);
INSERT INTO `sys_area` VALUES ('527', '集宁区', 'jiningqu', 'jnq', '150900', '150902', null, null);
INSERT INTO `sys_area` VALUES ('528', '卓资县', 'zhuozixian', 'zzx', '150900', '150921', null, null);
INSERT INTO `sys_area` VALUES ('529', '化德县', 'huadexian', 'hdx', '150900', '150922', null, null);
INSERT INTO `sys_area` VALUES ('530', '商都县', 'shangduxian', 'sdx', '150900', '150923', null, null);
INSERT INTO `sys_area` VALUES ('531', '兴和县', 'xinghexian', 'xhx', '150900', '150924', null, null);
INSERT INTO `sys_area` VALUES ('532', '凉城县', 'liangchengxian', 'lcx', '150900', '150925', null, null);
INSERT INTO `sys_area` VALUES ('533', '察哈尔右翼前旗', 'chahaeryouyiqianqi', 'cheyyqq', '150900', '150926', null, null);
INSERT INTO `sys_area` VALUES ('534', '察哈尔右翼中旗', 'chahaeryouyizhongqi', 'cheyyzq', '150900', '150927', null, null);
INSERT INTO `sys_area` VALUES ('535', '察哈尔右翼后旗', 'chahaeryouyihouqi', 'cheyyhq', '150900', '150928', null, null);
INSERT INTO `sys_area` VALUES ('536', '四子王旗', 'siziwangqi', 'szwq', '150900', '150929', null, null);
INSERT INTO `sys_area` VALUES ('537', '丰镇市', 'fengzhenshi', 'fzs', '150900', '150981', null, null);
INSERT INTO `sys_area` VALUES ('538', '兴安盟', 'xinganmeng', 'xam', '150000', '152200', null, null);
INSERT INTO `sys_area` VALUES ('539', '乌兰浩特市', 'wulanhaoteshi', 'wlhts', '152200', '152201', null, null);
INSERT INTO `sys_area` VALUES ('540', '阿尔山市', 'aershanshi', 'aess', '152200', '152202', null, null);
INSERT INTO `sys_area` VALUES ('541', '科尔沁右翼前旗', 'keerqinyouyiqianqi', 'keqyyqq', '152200', '152221', null, null);
INSERT INTO `sys_area` VALUES ('542', '科尔沁右翼中旗', 'keerqinyouyizhongqi', 'keqyyzq', '152200', '152222', null, null);
INSERT INTO `sys_area` VALUES ('543', '扎赉特旗', 'zhazuoteqi', 'z赉tq', '152200', '152223', null, null);
INSERT INTO `sys_area` VALUES ('544', '突泉县', 'tuquanxian', 'tqx', '152200', '152224', null, null);
INSERT INTO `sys_area` VALUES ('545', '锡林郭勒盟', 'xilinguolemeng', 'xlglm', '150000', '152500', null, null);
INSERT INTO `sys_area` VALUES ('546', '二连浩特市', 'erlianhaoteshi', 'elhts', '152500', '152501', null, null);
INSERT INTO `sys_area` VALUES ('547', '锡林浩特市', 'xilinhaoteshi', 'xlhts', '152500', '152502', null, null);
INSERT INTO `sys_area` VALUES ('548', '阿巴嘎旗', 'abagaqi', 'abgq', '152500', '152522', null, null);
INSERT INTO `sys_area` VALUES ('549', '苏尼特左旗', 'sunitezuoqi', 'sntzq', '152500', '152523', null, null);
INSERT INTO `sys_area` VALUES ('550', '苏尼特右旗', 'suniteyouqi', 'sntyq', '152500', '152524', null, null);
INSERT INTO `sys_area` VALUES ('551', '东乌珠穆沁旗', 'dongwuzhumuqinqi', 'dwzmqq', '152500', '152525', null, null);
INSERT INTO `sys_area` VALUES ('552', '西乌珠穆沁旗', 'xiwuzhumuqinqi', 'xwzmqq', '152500', '152526', null, null);
INSERT INTO `sys_area` VALUES ('553', '太仆寺旗', 'taipusiqi', 'tpsq', '152500', '152527', null, null);
INSERT INTO `sys_area` VALUES ('554', '镶黄旗', 'xianghuangqi', 'xhq', '152500', '152528', null, null);
INSERT INTO `sys_area` VALUES ('555', '正镶白旗', 'zhengxiangbaiqi', 'zxbq', '152500', '152529', null, null);
INSERT INTO `sys_area` VALUES ('556', '正蓝旗', 'zhenglanqi', 'zlq', '152500', '152530', null, null);
INSERT INTO `sys_area` VALUES ('557', '多伦县', 'duolunxian', 'dlx', '152500', '152531', null, null);
INSERT INTO `sys_area` VALUES ('558', '阿拉善盟', 'alashanmeng', 'alsm', '150000', '152900', null, null);
INSERT INTO `sys_area` VALUES ('559', '阿拉善左旗', 'alashanzuoqi', 'alszq', '152900', '152921', null, null);
INSERT INTO `sys_area` VALUES ('560', '阿拉善右旗', 'alashanyouqi', 'alsyq', '152900', '152922', null, null);
INSERT INTO `sys_area` VALUES ('561', '额济纳旗', 'ejinaqi', 'ejnq', '152900', '152923', null, null);
INSERT INTO `sys_area` VALUES ('562', '辽宁省', 'liaoningsheng', 'lns', '0', '210000', null, null);
INSERT INTO `sys_area` VALUES ('563', '沈阳市', 'shenyangshi', 'sys', '210000', '210100', null, null);
INSERT INTO `sys_area` VALUES ('564', '和平区', 'hepingqu', 'hpq', '210100', '210102', null, null);
INSERT INTO `sys_area` VALUES ('565', '沈河区', 'shenhequ', 'shq', '210100', '210103', null, null);
INSERT INTO `sys_area` VALUES ('566', '大东区', 'dadongqu', 'ddq', '210100', '210104', null, null);
INSERT INTO `sys_area` VALUES ('567', '皇姑区', 'huangguqu', 'hgq', '210100', '210105', null, null);
INSERT INTO `sys_area` VALUES ('568', '铁西区', 'tiexiqu', 'txq', '210100', '210106', null, null);
INSERT INTO `sys_area` VALUES ('569', '苏家屯区', 'sujiatunqu', 'sjtq', '210100', '210111', null, null);
INSERT INTO `sys_area` VALUES ('570', '浑南区', 'hunnanqu', 'hnq', '210100', '210112', null, null);
INSERT INTO `sys_area` VALUES ('571', '沈北新区', 'shenbeixinqu', 'sbxq', '210100', '210113', null, null);
INSERT INTO `sys_area` VALUES ('572', '于洪区', 'yuhongqu', 'yhq', '210100', '210114', null, null);
INSERT INTO `sys_area` VALUES ('573', '辽中区', 'liaozhongqu', 'lzq', '210100', '210115', null, null);
INSERT INTO `sys_area` VALUES ('574', '康平县', 'kangpingxian', 'kpx', '210100', '210123', null, null);
INSERT INTO `sys_area` VALUES ('575', '法库县', 'fakuxian', 'fkx', '210100', '210124', null, null);
INSERT INTO `sys_area` VALUES ('576', '新民市', 'xinminshi', 'xms', '210100', '210181', null, null);
INSERT INTO `sys_area` VALUES ('577', '大连市', 'dalianshi', 'dls', '210000', '210200', null, null);
INSERT INTO `sys_area` VALUES ('578', '中山区', 'zhongshanqu', 'zsq', '210200', '210202', null, null);
INSERT INTO `sys_area` VALUES ('579', '西岗区', 'xigangqu', 'xgq', '210200', '210203', null, null);
INSERT INTO `sys_area` VALUES ('580', '沙河口区', 'shahekouqu', 'shkq', '210200', '210204', null, null);
INSERT INTO `sys_area` VALUES ('581', '甘井子区', 'ganjingziqu', 'gjzq', '210200', '210211', null, null);
INSERT INTO `sys_area` VALUES ('582', '旅顺口区', 'lvshunkouqu', 'lskq', '210200', '210212', null, null);
INSERT INTO `sys_area` VALUES ('583', '金州区', 'jinzhouqu', 'jzq', '210200', '210213', null, null);
INSERT INTO `sys_area` VALUES ('584', '普兰店区', 'pulandianqu', 'pldq', '210200', '210214', null, null);
INSERT INTO `sys_area` VALUES ('585', '长海县', 'changhaixian', 'chx', '210200', '210224', null, null);
INSERT INTO `sys_area` VALUES ('586', '瓦房店市', 'wafangdianshi', 'wfds', '210200', '210281', null, null);
INSERT INTO `sys_area` VALUES ('587', '庄河市', 'zhuangheshi', 'zhs', '210200', '210283', null, null);
INSERT INTO `sys_area` VALUES ('588', '鞍山市', 'anshanshi', 'ass', '210000', '210300', null, null);
INSERT INTO `sys_area` VALUES ('589', '铁东区', 'tiedongqu', 'tdq', '210300', '210302', null, null);
INSERT INTO `sys_area` VALUES ('590', '铁西区', 'tiexiqu', 'txq', '210300', '210303', null, null);
INSERT INTO `sys_area` VALUES ('591', '立山区', 'lishanqu', 'lsq', '210300', '210304', null, null);
INSERT INTO `sys_area` VALUES ('592', '千山区', 'qianshanqu', 'qsq', '210300', '210311', null, null);
INSERT INTO `sys_area` VALUES ('593', '台安县', 'taianxian', 'tax', '210300', '210321', null, null);
INSERT INTO `sys_area` VALUES ('594', '岫岩满族自治县', 'zuoyanmanzuzizhixian', '岫ymzzzx', '210300', '210323', null, null);
INSERT INTO `sys_area` VALUES ('595', '海城市', 'haichengshi', 'hcs', '210300', '210381', null, null);
INSERT INTO `sys_area` VALUES ('596', '抚顺市', 'fushunshi', 'fss', '210000', '210400', null, null);
INSERT INTO `sys_area` VALUES ('597', '新抚区', 'xinfuqu', 'xfq', '210400', '210402', null, null);
INSERT INTO `sys_area` VALUES ('598', '东洲区', 'dongzhouqu', 'dzq', '210400', '210403', null, null);
INSERT INTO `sys_area` VALUES ('599', '望花区', 'wanghuaqu', 'whq', '210400', '210404', null, null);
INSERT INTO `sys_area` VALUES ('600', '顺城区', 'shunchengqu', 'scq', '210400', '210411', null, null);
INSERT INTO `sys_area` VALUES ('601', '抚顺县', 'fushunxian', 'fsx', '210400', '210421', null, null);
INSERT INTO `sys_area` VALUES ('602', '新宾满族自治县', 'xinbinmanzuzizhixian', 'xbmzzzx', '210400', '210422', null, null);
INSERT INTO `sys_area` VALUES ('603', '清原满族自治县', 'qingyuanmanzuzizhixian', 'qymzzzx', '210400', '210423', null, null);
INSERT INTO `sys_area` VALUES ('604', '本溪市', 'benxishi', 'bxs', '210000', '210500', null, null);
INSERT INTO `sys_area` VALUES ('605', '平山区', 'pingshanqu', 'psq', '210500', '210502', null, null);
INSERT INTO `sys_area` VALUES ('606', '溪湖区', 'xihuqu', 'xhq', '210500', '210503', null, null);
INSERT INTO `sys_area` VALUES ('607', '明山区', 'mingshanqu', 'msq', '210500', '210504', null, null);
INSERT INTO `sys_area` VALUES ('608', '南芬区', 'nanfenqu', 'nfq', '210500', '210505', null, null);
INSERT INTO `sys_area` VALUES ('609', '本溪满族自治县', 'benximanzuzizhixian', 'bxmzzzx', '210500', '210521', null, null);
INSERT INTO `sys_area` VALUES ('610', '桓仁满族自治县', 'huanrenmanzuzizhixian', 'hrmzzzx', '210500', '210522', null, null);
INSERT INTO `sys_area` VALUES ('611', '丹东市', 'dandongshi', 'dds', '210000', '210600', null, null);
INSERT INTO `sys_area` VALUES ('612', '元宝区', 'yuanbaoqu', 'ybq', '210600', '210602', null, null);
INSERT INTO `sys_area` VALUES ('613', '振兴区', 'zhenxingqu', 'zxq', '210600', '210603', null, null);
INSERT INTO `sys_area` VALUES ('614', '振安区', 'zhenanqu', 'zaq', '210600', '210604', null, null);
INSERT INTO `sys_area` VALUES ('615', '宽甸满族自治县', 'kuandianmanzuzizhixian', 'kdmzzzx', '210600', '210624', null, null);
INSERT INTO `sys_area` VALUES ('616', '东港市', 'donggangshi', 'dgs', '210600', '210681', null, null);
INSERT INTO `sys_area` VALUES ('617', '凤城市', 'fengchengshi', 'fcs', '210600', '210682', null, null);
INSERT INTO `sys_area` VALUES ('618', '锦州市', 'jinzhoushi', 'jzs', '210000', '210700', null, null);
INSERT INTO `sys_area` VALUES ('619', '古塔区', 'gutaqu', 'gtq', '210700', '210702', null, null);
INSERT INTO `sys_area` VALUES ('620', '凌河区', 'linghequ', 'lhq', '210700', '210703', null, null);
INSERT INTO `sys_area` VALUES ('621', '太和区', 'taihequ', 'thq', '210700', '210711', null, null);
INSERT INTO `sys_area` VALUES ('622', '黑山县', 'heishanxian', 'hsx', '210700', '210726', null, null);
INSERT INTO `sys_area` VALUES ('623', '义县', 'yixian', 'yx', '210700', '210727', null, null);
INSERT INTO `sys_area` VALUES ('624', '凌海市', 'linghaishi', 'lhs', '210700', '210781', null, null);
INSERT INTO `sys_area` VALUES ('625', '北镇市', 'beizhenshi', 'bzs', '210700', '210782', null, null);
INSERT INTO `sys_area` VALUES ('626', '营口市', 'yingkoushi', 'yks', '210000', '210800', null, null);
INSERT INTO `sys_area` VALUES ('627', '站前区', 'zhanqianqu', 'zqq', '210800', '210802', null, null);
INSERT INTO `sys_area` VALUES ('628', '西市区', 'xishiqu', 'xsq', '210800', '210803', null, null);
INSERT INTO `sys_area` VALUES ('629', '鲅鱼圈区', 'zuoyuquanqu', '鲅yqq', '210800', '210804', null, null);
INSERT INTO `sys_area` VALUES ('630', '老边区', 'laobianqu', 'lbq', '210800', '210811', null, null);
INSERT INTO `sys_area` VALUES ('631', '盖州市', 'gaizhoushi', 'gzs', '210800', '210881', null, null);
INSERT INTO `sys_area` VALUES ('632', '大石桥市', 'dashiqiaoshi', 'dsqs', '210800', '210882', null, null);
INSERT INTO `sys_area` VALUES ('633', '阜新市', 'fuxinshi', 'fxs', '210000', '210900', null, null);
INSERT INTO `sys_area` VALUES ('634', '海州区', 'haizhouqu', 'hzq', '210900', '210902', null, null);
INSERT INTO `sys_area` VALUES ('635', '新邱区', 'xinqiuqu', 'xqq', '210900', '210903', null, null);
INSERT INTO `sys_area` VALUES ('636', '太平区', 'taipingqu', 'tpq', '210900', '210904', null, null);
INSERT INTO `sys_area` VALUES ('637', '清河门区', 'qinghemenqu', 'qhmq', '210900', '210905', null, null);
INSERT INTO `sys_area` VALUES ('638', '细河区', 'xihequ', 'xhq', '210900', '210911', null, null);
INSERT INTO `sys_area` VALUES ('639', '阜新蒙古族自治县', 'fuxinmengguzuzizhixian', 'fxmgzzzx', '210900', '210921', null, null);
INSERT INTO `sys_area` VALUES ('640', '彰武县', 'zhangwuxian', 'zwx', '210900', '210922', null, null);
INSERT INTO `sys_area` VALUES ('641', '辽阳市', 'liaoyangshi', 'lys', '210000', '211000', null, null);
INSERT INTO `sys_area` VALUES ('642', '白塔区', 'baitaqu', 'btq', '211000', '211002', null, null);
INSERT INTO `sys_area` VALUES ('643', '文圣区', 'wenshengqu', 'wsq', '211000', '211003', null, null);
INSERT INTO `sys_area` VALUES ('644', '宏伟区', 'hongweiqu', 'hwq', '211000', '211004', null, null);
INSERT INTO `sys_area` VALUES ('645', '弓长岭区', 'gongchanglingqu', 'gclq', '211000', '211005', null, null);
INSERT INTO `sys_area` VALUES ('646', '太子河区', 'taizihequ', 'tzhq', '211000', '211011', null, null);
INSERT INTO `sys_area` VALUES ('647', '辽阳县', 'liaoyangxian', 'lyx', '211000', '211021', null, null);
INSERT INTO `sys_area` VALUES ('648', '灯塔市', 'dengtashi', 'dts', '211000', '211081', null, null);
INSERT INTO `sys_area` VALUES ('649', '盘锦市', 'panjinshi', 'pjs', '210000', '211100', null, null);
INSERT INTO `sys_area` VALUES ('650', '双台子区', 'shuangtaiziqu', 'stzq', '211100', '211102', null, null);
INSERT INTO `sys_area` VALUES ('651', '兴隆台区', 'xinglongtaiqu', 'xltq', '211100', '211103', null, null);
INSERT INTO `sys_area` VALUES ('652', '大洼区', 'dawaqu', 'dwq', '211100', '211104', null, null);
INSERT INTO `sys_area` VALUES ('653', '盘山县', 'panshanxian', 'psx', '211100', '211122', null, null);
INSERT INTO `sys_area` VALUES ('654', '铁岭市', 'tielingshi', 'tls', '210000', '211200', null, null);
INSERT INTO `sys_area` VALUES ('655', '银州区', 'yinzhouqu', 'yzq', '211200', '211202', null, null);
INSERT INTO `sys_area` VALUES ('656', '清河区', 'qinghequ', 'qhq', '211200', '211204', null, null);
INSERT INTO `sys_area` VALUES ('657', '铁岭县', 'tielingxian', 'tlx', '211200', '211221', null, null);
INSERT INTO `sys_area` VALUES ('658', '西丰县', 'xifengxian', 'xfx', '211200', '211223', null, null);
INSERT INTO `sys_area` VALUES ('659', '昌图县', 'changtuxian', 'ctx', '211200', '211224', null, null);
INSERT INTO `sys_area` VALUES ('660', '调兵山市', 'diaobingshanshi', 'dbss', '211200', '211281', null, null);
INSERT INTO `sys_area` VALUES ('661', '开原市', 'kaiyuanshi', 'kys', '211200', '211282', null, null);
INSERT INTO `sys_area` VALUES ('662', '朝阳市', 'chaoyangshi', 'cys', '210000', '211300', null, null);
INSERT INTO `sys_area` VALUES ('663', '双塔区', 'shuangtaqu', 'stq', '211300', '211302', null, null);
INSERT INTO `sys_area` VALUES ('664', '龙城区', 'longchengqu', 'lcq', '211300', '211303', null, null);
INSERT INTO `sys_area` VALUES ('665', '朝阳县', 'chaoyangxian', 'cyx', '211300', '211321', null, null);
INSERT INTO `sys_area` VALUES ('666', '建平县', 'jianpingxian', 'jpx', '211300', '211322', null, null);
INSERT INTO `sys_area` VALUES ('667', '喀喇沁左翼蒙古族自治县', 'kalaqinzuoyimengguzuzizhixian', 'klqzymgzzzx', '211300', '211324', null, null);
INSERT INTO `sys_area` VALUES ('668', '北票市', 'beipiaoshi', 'bps', '211300', '211381', null, null);
INSERT INTO `sys_area` VALUES ('669', '凌源市', 'lingyuanshi', 'lys', '211300', '211382', null, null);
INSERT INTO `sys_area` VALUES ('670', '葫芦岛市', 'huludaoshi', 'hlds', '210000', '211400', null, null);
INSERT INTO `sys_area` VALUES ('671', '连山区', 'lianshanqu', 'lsq', '211400', '211402', null, null);
INSERT INTO `sys_area` VALUES ('672', '龙港区', 'longgangqu', 'lgq', '211400', '211403', null, null);
INSERT INTO `sys_area` VALUES ('673', '南票区', 'nanpiaoqu', 'npq', '211400', '211404', null, null);
INSERT INTO `sys_area` VALUES ('674', '绥中县', 'suizhongxian', 'szx', '211400', '211421', null, null);
INSERT INTO `sys_area` VALUES ('675', '建昌县', 'jianchangxian', 'jcx', '211400', '211422', null, null);
INSERT INTO `sys_area` VALUES ('676', '兴城市', 'xingchengshi', 'xcs', '211400', '211481', null, null);
INSERT INTO `sys_area` VALUES ('677', '吉林省', 'jilinsheng', 'jls', '0', '220000', null, null);
INSERT INTO `sys_area` VALUES ('678', '长春市', 'changchunshi', 'ccs', '220000', '220100', null, null);
INSERT INTO `sys_area` VALUES ('679', '南关区', 'nanguanqu', 'ngq', '220100', '220102', null, null);
INSERT INTO `sys_area` VALUES ('680', '宽城区', 'kuanchengqu', 'kcq', '220100', '220103', null, null);
INSERT INTO `sys_area` VALUES ('681', '朝阳区', 'chaoyangqu', 'cyq', '220100', '220104', null, null);
INSERT INTO `sys_area` VALUES ('682', '二道区', 'erdaoqu', 'edq', '220100', '220105', null, null);
INSERT INTO `sys_area` VALUES ('683', '绿园区', 'lvyuanqu', 'lyq', '220100', '220106', null, null);
INSERT INTO `sys_area` VALUES ('684', '双阳区', 'shuangyangqu', 'syq', '220100', '220112', null, null);
INSERT INTO `sys_area` VALUES ('685', '九台区', 'jiutaiqu', 'jtq', '220100', '220113', null, null);
INSERT INTO `sys_area` VALUES ('686', '农安县', 'nonganxian', 'nax', '220100', '220122', null, null);
INSERT INTO `sys_area` VALUES ('687', '榆树市', 'yushushi', 'yss', '220100', '220182', null, null);
INSERT INTO `sys_area` VALUES ('688', '德惠市', 'dehuishi', 'dhs', '220100', '220183', null, null);
INSERT INTO `sys_area` VALUES ('689', '吉林市', 'jilinshi', 'jls', '220000', '220200', null, null);
INSERT INTO `sys_area` VALUES ('690', '昌邑区', 'changyiqu', 'cyq', '220200', '220202', null, null);
INSERT INTO `sys_area` VALUES ('691', '龙潭区', 'longtanqu', 'ltq', '220200', '220203', null, null);
INSERT INTO `sys_area` VALUES ('692', '船营区', 'chuanyingqu', 'cyq', '220200', '220204', null, null);
INSERT INTO `sys_area` VALUES ('693', '丰满区', 'fengmanqu', 'fmq', '220200', '220211', null, null);
INSERT INTO `sys_area` VALUES ('694', '永吉县', 'yongjixian', 'yjx', '220200', '220221', null, null);
INSERT INTO `sys_area` VALUES ('695', '蛟河市', 'zuoheshi', '蛟hs', '220200', '220281', null, null);
INSERT INTO `sys_area` VALUES ('696', '桦甸市', 'zuodianshi', '桦ds', '220200', '220282', null, null);
INSERT INTO `sys_area` VALUES ('697', '舒兰市', 'shulanshi', 'sls', '220200', '220283', null, null);
INSERT INTO `sys_area` VALUES ('698', '磐石市', 'panshishi', 'pss', '220200', '220284', null, null);
INSERT INTO `sys_area` VALUES ('699', '四平市', 'sipingshi', 'sps', '220000', '220300', null, null);
INSERT INTO `sys_area` VALUES ('700', '铁西区', 'tiexiqu', 'txq', '220300', '220302', null, null);
INSERT INTO `sys_area` VALUES ('701', '铁东区', 'tiedongqu', 'tdq', '220300', '220303', null, null);
INSERT INTO `sys_area` VALUES ('702', '梨树县', 'lishuxian', 'lsx', '220300', '220322', null, null);
INSERT INTO `sys_area` VALUES ('703', '伊通满族自治县', 'yitongmanzuzizhixian', 'ytmzzzx', '220300', '220323', null, null);
INSERT INTO `sys_area` VALUES ('704', '公主岭市', 'gongzhulingshi', 'gzls', '220300', '220381', null, null);
INSERT INTO `sys_area` VALUES ('705', '双辽市', 'shuangliaoshi', 'sls', '220300', '220382', null, null);
INSERT INTO `sys_area` VALUES ('706', '辽源市', 'liaoyuanshi', 'lys', '220000', '220400', null, null);
INSERT INTO `sys_area` VALUES ('707', '龙山区', 'longshanqu', 'lsq', '220400', '220402', null, null);
INSERT INTO `sys_area` VALUES ('708', '西安区', 'xianqu', 'xaq', '220400', '220403', null, null);
INSERT INTO `sys_area` VALUES ('709', '东丰县', 'dongfengxian', 'dfx', '220400', '220421', null, null);
INSERT INTO `sys_area` VALUES ('710', '东辽县', 'dongliaoxian', 'dlx', '220400', '220422', null, null);
INSERT INTO `sys_area` VALUES ('711', '通化市', 'tonghuashi', 'ths', '220000', '220500', null, null);
INSERT INTO `sys_area` VALUES ('712', '东昌区', 'dongchangqu', 'dcq', '220500', '220502', null, null);
INSERT INTO `sys_area` VALUES ('713', '二道江区', 'erdaojiangqu', 'edjq', '220500', '220503', null, null);
INSERT INTO `sys_area` VALUES ('714', '通化县', 'tonghuaxian', 'thx', '220500', '220521', null, null);
INSERT INTO `sys_area` VALUES ('715', '辉南县', 'huinanxian', 'hnx', '220500', '220523', null, null);
INSERT INTO `sys_area` VALUES ('716', '柳河县', 'liuhexian', 'lhx', '220500', '220524', null, null);
INSERT INTO `sys_area` VALUES ('717', '梅河口市', 'meihekoushi', 'mhks', '220500', '220581', null, null);
INSERT INTO `sys_area` VALUES ('718', '集安市', 'jianshi', 'jas', '220500', '220582', null, null);
INSERT INTO `sys_area` VALUES ('719', '白山市', 'baishanshi', 'bss', '220000', '220600', null, null);
INSERT INTO `sys_area` VALUES ('720', '浑江区', 'hunjiangqu', 'hjq', '220600', '220602', null, null);
INSERT INTO `sys_area` VALUES ('721', '江源区', 'jiangyuanqu', 'jyq', '220600', '220605', null, null);
INSERT INTO `sys_area` VALUES ('722', '抚松县', 'fusongxian', 'fsx', '220600', '220621', null, null);
INSERT INTO `sys_area` VALUES ('723', '靖宇县', 'jingyuxian', 'jyx', '220600', '220622', null, null);
INSERT INTO `sys_area` VALUES ('724', '长白朝鲜族自治县', 'changbaichaoxianzuzizhixian', 'cbcxzzzx', '220600', '220623', null, null);
INSERT INTO `sys_area` VALUES ('725', '临江市', 'linjiangshi', 'ljs', '220600', '220681', null, null);
INSERT INTO `sys_area` VALUES ('726', '松原市', 'songyuanshi', 'sys', '220000', '220700', null, null);
INSERT INTO `sys_area` VALUES ('727', '宁江区', 'ningjiangqu', 'njq', '220700', '220702', null, null);
INSERT INTO `sys_area` VALUES ('728', '前郭尔罗斯蒙古族自治县', 'qianguoerluosimengguzuzizhixian', 'qgelsmgzzzx', '220700', '220721', null, null);
INSERT INTO `sys_area` VALUES ('729', '长岭县', 'changlingxian', 'clx', '220700', '220722', null, null);
INSERT INTO `sys_area` VALUES ('730', '乾安县', 'qiananxian', 'qax', '220700', '220723', null, null);
INSERT INTO `sys_area` VALUES ('731', '扶余市', 'fuyushi', 'fys', '220700', '220781', null, null);
INSERT INTO `sys_area` VALUES ('732', '白城市', 'baichengshi', 'bcs', '220000', '220800', null, null);
INSERT INTO `sys_area` VALUES ('733', '洮北区', 'zuobeiqu', '洮bq', '220800', '220802', null, null);
INSERT INTO `sys_area` VALUES ('734', '镇赉县', 'zhenzuoxian', 'z赉x', '220800', '220821', null, null);
INSERT INTO `sys_area` VALUES ('735', '通榆县', 'tongyuxian', 'tyx', '220800', '220822', null, null);
INSERT INTO `sys_area` VALUES ('736', '洮南市', 'zuonanshi', '洮ns', '220800', '220881', null, null);
INSERT INTO `sys_area` VALUES ('737', '大安市', 'daanshi', 'das', '220800', '220882', null, null);
INSERT INTO `sys_area` VALUES ('738', '延边朝鲜族自治州', 'yanbianchaoxianzuzizhizhou', 'ybcxzzzz', '220000', '222400', null, null);
INSERT INTO `sys_area` VALUES ('739', '延吉市', 'yanjishi', 'yjs', '222400', '222401', null, null);
INSERT INTO `sys_area` VALUES ('740', '图们市', 'tumenshi', 'tms', '222400', '222402', null, null);
INSERT INTO `sys_area` VALUES ('741', '敦化市', 'dunhuashi', 'dhs', '222400', '222403', null, null);
INSERT INTO `sys_area` VALUES ('742', '珲春市', 'zuochunshi', '珲cs', '222400', '222404', null, null);
INSERT INTO `sys_area` VALUES ('743', '龙井市', 'longjingshi', 'ljs', '222400', '222405', null, null);
INSERT INTO `sys_area` VALUES ('744', '和龙市', 'helongshi', 'hls', '222400', '222406', null, null);
INSERT INTO `sys_area` VALUES ('745', '汪清县', 'wangqingxian', 'wqx', '222400', '222424', null, null);
INSERT INTO `sys_area` VALUES ('746', '安图县', 'antuxian', 'atx', '222400', '222426', null, null);
INSERT INTO `sys_area` VALUES ('747', '黑龙江省', 'heilongjiangsheng', 'hljs', '0', '230000', null, null);
INSERT INTO `sys_area` VALUES ('748', '哈尔滨市', 'haerbinshi', 'hebs', '230000', '230100', null, null);
INSERT INTO `sys_area` VALUES ('749', '道里区', 'daoliqu', 'dlq', '230100', '230102', null, null);
INSERT INTO `sys_area` VALUES ('750', '南岗区', 'nangangqu', 'ngq', '230100', '230103', null, null);
INSERT INTO `sys_area` VALUES ('751', '道外区', 'daowaiqu', 'dwq', '230100', '230104', null, null);
INSERT INTO `sys_area` VALUES ('752', '平房区', 'pingfangqu', 'pfq', '230100', '230108', null, null);
INSERT INTO `sys_area` VALUES ('753', '松北区', 'songbeiqu', 'sbq', '230100', '230109', null, null);
INSERT INTO `sys_area` VALUES ('754', '香坊区', 'xiangfangqu', 'xfq', '230100', '230110', null, null);
INSERT INTO `sys_area` VALUES ('755', '呼兰区', 'hulanqu', 'hlq', '230100', '230111', null, null);
INSERT INTO `sys_area` VALUES ('756', '阿城区', 'achengqu', 'acq', '230100', '230112', null, null);
INSERT INTO `sys_area` VALUES ('757', '双城区', 'shuangchengqu', 'scq', '230100', '230113', null, null);
INSERT INTO `sys_area` VALUES ('758', '依兰县', 'yilanxian', 'ylx', '230100', '230123', null, null);
INSERT INTO `sys_area` VALUES ('759', '方正县', 'fangzhengxian', 'fzx', '230100', '230124', null, null);
INSERT INTO `sys_area` VALUES ('760', '宾县', 'binxian', 'bx', '230100', '230125', null, null);
INSERT INTO `sys_area` VALUES ('761', '巴彦县', 'bayanxian', 'byx', '230100', '230126', null, null);
INSERT INTO `sys_area` VALUES ('762', '木兰县', 'mulanxian', 'mlx', '230100', '230127', null, null);
INSERT INTO `sys_area` VALUES ('763', '通河县', 'tonghexian', 'thx', '230100', '230128', null, null);
INSERT INTO `sys_area` VALUES ('764', '延寿县', 'yanshouxian', 'ysx', '230100', '230129', null, null);
INSERT INTO `sys_area` VALUES ('765', '尚志市', 'shangzhishi', 'szs', '230100', '230183', null, null);
INSERT INTO `sys_area` VALUES ('766', '五常市', 'wuchangshi', 'wcs', '230100', '230184', null, null);
INSERT INTO `sys_area` VALUES ('767', '齐齐哈尔市', 'qiqihaershi', 'qqhes', '230000', '230200', null, null);
INSERT INTO `sys_area` VALUES ('768', '龙沙区', 'longshaqu', 'lsq', '230200', '230202', null, null);
INSERT INTO `sys_area` VALUES ('769', '建华区', 'jianhuaqu', 'jhq', '230200', '230203', null, null);
INSERT INTO `sys_area` VALUES ('770', '铁锋区', 'tiefengqu', 'tfq', '230200', '230204', null, null);
INSERT INTO `sys_area` VALUES ('771', '昂昂溪区', 'angangxiqu', 'aaxq', '230200', '230205', null, null);
INSERT INTO `sys_area` VALUES ('772', '富拉尔基区', 'fulaerjiqu', 'flejq', '230200', '230206', null, null);
INSERT INTO `sys_area` VALUES ('773', '碾子山区', 'nianzishanqu', 'nzsq', '230200', '230207', null, null);
INSERT INTO `sys_area` VALUES ('774', '梅里斯达斡尔族区', 'meilisidawoerzuqu', 'mlsdwezq', '230200', '230208', null, null);
INSERT INTO `sys_area` VALUES ('775', '龙江县', 'longjiangxian', 'ljx', '230200', '230221', null, null);
INSERT INTO `sys_area` VALUES ('776', '依安县', 'yianxian', 'yax', '230200', '230223', null, null);
INSERT INTO `sys_area` VALUES ('777', '泰来县', 'tailaixian', 'tlx', '230200', '230224', null, null);
INSERT INTO `sys_area` VALUES ('778', '甘南县', 'gannanxian', 'gnx', '230200', '230225', null, null);
INSERT INTO `sys_area` VALUES ('779', '富裕县', 'fuyuxian', 'fyx', '230200', '230227', null, null);
INSERT INTO `sys_area` VALUES ('780', '克山县', 'keshanxian', 'ksx', '230200', '230229', null, null);
INSERT INTO `sys_area` VALUES ('781', '克东县', 'kedongxian', 'kdx', '230200', '230230', null, null);
INSERT INTO `sys_area` VALUES ('782', '拜泉县', 'baiquanxian', 'bqx', '230200', '230231', null, null);
INSERT INTO `sys_area` VALUES ('783', '讷河市', 'zuoheshi', '讷hs', '230200', '230281', null, null);
INSERT INTO `sys_area` VALUES ('784', '鸡西市', 'jixishi', 'jxs', '230000', '230300', null, null);
INSERT INTO `sys_area` VALUES ('785', '鸡冠区', 'jiguanqu', 'jgq', '230300', '230302', null, null);
INSERT INTO `sys_area` VALUES ('786', '恒山区', 'hengshanqu', 'hsq', '230300', '230303', null, null);
INSERT INTO `sys_area` VALUES ('787', '滴道区', 'didaoqu', 'ddq', '230300', '230304', null, null);
INSERT INTO `sys_area` VALUES ('788', '梨树区', 'lishuqu', 'lsq', '230300', '230305', null, null);
INSERT INTO `sys_area` VALUES ('789', '城子河区', 'chengzihequ', 'czhq', '230300', '230306', null, null);
INSERT INTO `sys_area` VALUES ('790', '麻山区', 'mashanqu', 'msq', '230300', '230307', null, null);
INSERT INTO `sys_area` VALUES ('791', '鸡东县', 'jidongxian', 'jdx', '230300', '230321', null, null);
INSERT INTO `sys_area` VALUES ('792', '虎林市', 'hulinshi', 'hls', '230300', '230381', null, null);
INSERT INTO `sys_area` VALUES ('793', '密山市', 'mishanshi', 'mss', '230300', '230382', null, null);
INSERT INTO `sys_area` VALUES ('794', '鹤岗市', 'hegangshi', 'hgs', '230000', '230400', null, null);
INSERT INTO `sys_area` VALUES ('795', '向阳区', 'xiangyangqu', 'xyq', '230400', '230402', null, null);
INSERT INTO `sys_area` VALUES ('796', '工农区', 'gongnongqu', 'gnq', '230400', '230403', null, null);
INSERT INTO `sys_area` VALUES ('797', '南山区', 'nanshanqu', 'nsq', '230400', '230404', null, null);
INSERT INTO `sys_area` VALUES ('798', '兴安区', 'xinganqu', 'xaq', '230400', '230405', null, null);
INSERT INTO `sys_area` VALUES ('799', '东山区', 'dongshanqu', 'dsq', '230400', '230406', null, null);
INSERT INTO `sys_area` VALUES ('800', '兴山区', 'xingshanqu', 'xsq', '230400', '230407', null, null);
INSERT INTO `sys_area` VALUES ('801', '萝北县', 'luobeixian', 'lbx', '230400', '230421', null, null);
INSERT INTO `sys_area` VALUES ('802', '绥滨县', 'suibinxian', 'sbx', '230400', '230422', null, null);
INSERT INTO `sys_area` VALUES ('803', '双鸭山市', 'shuangyashanshi', 'syss', '230000', '230500', null, null);
INSERT INTO `sys_area` VALUES ('804', '尖山区', 'jianshanqu', 'jsq', '230500', '230502', null, null);
INSERT INTO `sys_area` VALUES ('805', '岭东区', 'lingdongqu', 'ldq', '230500', '230503', null, null);
INSERT INTO `sys_area` VALUES ('806', '四方台区', 'sifangtaiqu', 'sftq', '230500', '230505', null, null);
INSERT INTO `sys_area` VALUES ('807', '宝山区', 'baoshanqu', 'bsq', '230500', '230506', null, null);
INSERT INTO `sys_area` VALUES ('808', '集贤县', 'jixianxian', 'jxx', '230500', '230521', null, null);
INSERT INTO `sys_area` VALUES ('809', '友谊县', 'youyixian', 'yyx', '230500', '230522', null, null);
INSERT INTO `sys_area` VALUES ('810', '宝清县', 'baoqingxian', 'bqx', '230500', '230523', null, null);
INSERT INTO `sys_area` VALUES ('811', '饶河县', 'raohexian', 'rhx', '230500', '230524', null, null);
INSERT INTO `sys_area` VALUES ('812', '大庆市', 'daqingshi', 'dqs', '230000', '230600', null, null);
INSERT INTO `sys_area` VALUES ('813', '萨尔图区', 'saertuqu', 'setq', '230600', '230602', null, null);
INSERT INTO `sys_area` VALUES ('814', '龙凤区', 'longfengqu', 'lfq', '230600', '230603', null, null);
INSERT INTO `sys_area` VALUES ('815', '让胡路区', 'ranghuluqu', 'rhlq', '230600', '230604', null, null);
INSERT INTO `sys_area` VALUES ('816', '红岗区', 'honggangqu', 'hgq', '230600', '230605', null, null);
INSERT INTO `sys_area` VALUES ('817', '大同区', 'datongqu', 'dtq', '230600', '230606', null, null);
INSERT INTO `sys_area` VALUES ('818', '肇州县', 'zhaozhouxian', 'zzx', '230600', '230621', null, null);
INSERT INTO `sys_area` VALUES ('819', '肇源县', 'zhaoyuanxian', 'zyx', '230600', '230622', null, null);
INSERT INTO `sys_area` VALUES ('820', '林甸县', 'lindianxian', 'ldx', '230600', '230623', null, null);
INSERT INTO `sys_area` VALUES ('821', '杜尔伯特蒙古族自治县', 'duerbotemengguzuzizhixian', 'debtmgzzzx', '230600', '230624', null, null);
INSERT INTO `sys_area` VALUES ('822', '伊春市', 'yichunshi', 'ycs', '230000', '230700', null, null);
INSERT INTO `sys_area` VALUES ('823', '伊春区', 'yichunqu', 'ycq', '230700', '230702', null, null);
INSERT INTO `sys_area` VALUES ('824', '南岔区', 'nanchaqu', 'ncq', '230700', '230703', null, null);
INSERT INTO `sys_area` VALUES ('825', '友好区', 'youhaoqu', 'yhq', '230700', '230704', null, null);
INSERT INTO `sys_area` VALUES ('826', '西林区', 'xilinqu', 'xlq', '230700', '230705', null, null);
INSERT INTO `sys_area` VALUES ('827', '翠峦区', 'cuiluanqu', 'clq', '230700', '230706', null, null);
INSERT INTO `sys_area` VALUES ('828', '新青区', 'xinqingqu', 'xqq', '230700', '230707', null, null);
INSERT INTO `sys_area` VALUES ('829', '美溪区', 'meixiqu', 'mxq', '230700', '230708', null, null);
INSERT INTO `sys_area` VALUES ('830', '金山屯区', 'jinshantunqu', 'jstq', '230700', '230709', null, null);
INSERT INTO `sys_area` VALUES ('831', '五营区', 'wuyingqu', 'wyq', '230700', '230710', null, null);
INSERT INTO `sys_area` VALUES ('832', '乌马河区', 'wumahequ', 'wmhq', '230700', '230711', null, null);
INSERT INTO `sys_area` VALUES ('833', '汤旺河区', 'tangwanghequ', 'twhq', '230700', '230712', null, null);
INSERT INTO `sys_area` VALUES ('834', '带岭区', 'dailingqu', 'dlq', '230700', '230713', null, null);
INSERT INTO `sys_area` VALUES ('835', '乌伊岭区', 'wuyilingqu', 'wylq', '230700', '230714', null, null);
INSERT INTO `sys_area` VALUES ('836', '红星区', 'hongxingqu', 'hxq', '230700', '230715', null, null);
INSERT INTO `sys_area` VALUES ('837', '上甘岭区', 'shangganlingqu', 'sglq', '230700', '230716', null, null);
INSERT INTO `sys_area` VALUES ('838', '嘉荫县', 'jiayinxian', 'jyx', '230700', '230722', null, null);
INSERT INTO `sys_area` VALUES ('839', '铁力市', 'tielishi', 'tls', '230700', '230781', null, null);
INSERT INTO `sys_area` VALUES ('840', '佳木斯市', 'jiamusishi', 'jmss', '230000', '230800', null, null);
INSERT INTO `sys_area` VALUES ('841', '向阳区', 'xiangyangqu', 'xyq', '230800', '230803', null, null);
INSERT INTO `sys_area` VALUES ('842', '前进区', 'qianjinqu', 'qjq', '230800', '230804', null, null);
INSERT INTO `sys_area` VALUES ('843', '东风区', 'dongfengqu', 'dfq', '230800', '230805', null, null);
INSERT INTO `sys_area` VALUES ('844', '郊区', 'jiaoqu', 'jq', '230800', '230811', null, null);
INSERT INTO `sys_area` VALUES ('845', '桦南县', 'zuonanxian', '桦nx', '230800', '230822', null, null);
INSERT INTO `sys_area` VALUES ('846', '桦川县', 'zuochuanxian', '桦cx', '230800', '230826', null, null);
INSERT INTO `sys_area` VALUES ('847', '汤原县', 'tangyuanxian', 'tyx', '230800', '230828', null, null);
INSERT INTO `sys_area` VALUES ('848', '同江市', 'tongjiangshi', 'tjs', '230800', '230881', null, null);
INSERT INTO `sys_area` VALUES ('849', '富锦市', 'fujinshi', 'fjs', '230800', '230882', null, null);
INSERT INTO `sys_area` VALUES ('850', '抚远市', 'fuyuanshi', 'fys', '230800', '230883', null, null);
INSERT INTO `sys_area` VALUES ('851', '七台河市', 'qitaiheshi', 'qths', '230000', '230900', null, null);
INSERT INTO `sys_area` VALUES ('852', '新兴区', 'xinxingqu', 'xxq', '230900', '230902', null, null);
INSERT INTO `sys_area` VALUES ('853', '桃山区', 'taoshanqu', 'tsq', '230900', '230903', null, null);
INSERT INTO `sys_area` VALUES ('854', '茄子河区', 'qiezihequ', 'qzhq', '230900', '230904', null, null);
INSERT INTO `sys_area` VALUES ('855', '勃利县', 'bolixian', 'blx', '230900', '230921', null, null);
INSERT INTO `sys_area` VALUES ('856', '牡丹江市', 'mudanjiangshi', 'mdjs', '230000', '231000', null, null);
INSERT INTO `sys_area` VALUES ('857', '东安区', 'donganqu', 'daq', '231000', '231002', null, null);
INSERT INTO `sys_area` VALUES ('858', '阳明区', 'yangmingqu', 'ymq', '231000', '231003', null, null);
INSERT INTO `sys_area` VALUES ('859', '爱民区', 'aiminqu', 'amq', '231000', '231004', null, null);
INSERT INTO `sys_area` VALUES ('860', '西安区', 'xianqu', 'xaq', '231000', '231005', null, null);
INSERT INTO `sys_area` VALUES ('861', '林口县', 'linkouxian', 'lkx', '231000', '231025', null, null);
INSERT INTO `sys_area` VALUES ('862', '绥芬河市', 'suifenheshi', 'sfhs', '231000', '231081', null, null);
INSERT INTO `sys_area` VALUES ('863', '海林市', 'hailinshi', 'hls', '231000', '231083', null, null);
INSERT INTO `sys_area` VALUES ('864', '宁安市', 'ninganshi', 'nas', '231000', '231084', null, null);
INSERT INTO `sys_area` VALUES ('865', '穆棱市', 'mulengshi', 'mls', '231000', '231085', null, null);
INSERT INTO `sys_area` VALUES ('866', '东宁市', 'dongningshi', 'dns', '231000', '231086', null, null);
INSERT INTO `sys_area` VALUES ('867', '黑河市', 'heiheshi', 'hhs', '230000', '231100', null, null);
INSERT INTO `sys_area` VALUES ('868', '爱辉区', 'aihuiqu', 'ahq', '231100', '231102', null, null);
INSERT INTO `sys_area` VALUES ('869', '嫩江县', 'nenjiangxian', 'njx', '231100', '231121', null, null);
INSERT INTO `sys_area` VALUES ('870', '逊克县', 'xunkexian', 'xkx', '231100', '231123', null, null);
INSERT INTO `sys_area` VALUES ('871', '孙吴县', 'sunwuxian', 'swx', '231100', '231124', null, null);
INSERT INTO `sys_area` VALUES ('872', '北安市', 'beianshi', 'bas', '231100', '231181', null, null);
INSERT INTO `sys_area` VALUES ('873', '五大连池市', 'wudalianchishi', 'wdlcs', '231100', '231182', null, null);
INSERT INTO `sys_area` VALUES ('874', '绥化市', 'suihuashi', 'shs', '230000', '231200', null, null);
INSERT INTO `sys_area` VALUES ('875', '北林区', 'beilinqu', 'blq', '231200', '231202', null, null);
INSERT INTO `sys_area` VALUES ('876', '望奎县', 'wangkuixian', 'wkx', '231200', '231221', null, null);
INSERT INTO `sys_area` VALUES ('877', '兰西县', 'lanxixian', 'lxx', '231200', '231222', null, null);
INSERT INTO `sys_area` VALUES ('878', '青冈县', 'qinggangxian', 'qgx', '231200', '231223', null, null);
INSERT INTO `sys_area` VALUES ('879', '庆安县', 'qinganxian', 'qax', '231200', '231224', null, null);
INSERT INTO `sys_area` VALUES ('880', '明水县', 'mingshuixian', 'msx', '231200', '231225', null, null);
INSERT INTO `sys_area` VALUES ('881', '绥棱县', 'suilengxian', 'slx', '231200', '231226', null, null);
INSERT INTO `sys_area` VALUES ('882', '安达市', 'andashi', 'ads', '231200', '231281', null, null);
INSERT INTO `sys_area` VALUES ('883', '肇东市', 'zhaodongshi', 'zds', '231200', '231282', null, null);
INSERT INTO `sys_area` VALUES ('884', '海伦市', 'hailunshi', 'hls', '231200', '231283', null, null);
INSERT INTO `sys_area` VALUES ('885', '大兴安岭地区', 'daxinganlingdiqu', 'dxaldq', '230000', '232700', null, null);
INSERT INTO `sys_area` VALUES ('886', '漠河市', 'moheshi', 'mhs', '232700', '232701', null, null);
INSERT INTO `sys_area` VALUES ('887', '呼玛县', 'humaxian', 'hmx', '232700', '232721', null, null);
INSERT INTO `sys_area` VALUES ('888', '塔河县', 'tahexian', 'thx', '232700', '232722', null, null);
INSERT INTO `sys_area` VALUES ('889', '上海市', 'shanghaishi', 'shs', '0', '310000', null, null);
INSERT INTO `sys_area` VALUES ('890', '黄浦区', 'huangpuqu', 'hpq', '310000', '310101', null, null);
INSERT INTO `sys_area` VALUES ('891', '徐汇区', 'xuhuiqu', 'xhq', '310000', '310104', null, null);
INSERT INTO `sys_area` VALUES ('892', '长宁区', 'changningqu', 'cnq', '310000', '310105', null, null);
INSERT INTO `sys_area` VALUES ('893', '静安区', 'jinganqu', 'jaq', '310000', '310106', null, null);
INSERT INTO `sys_area` VALUES ('894', '普陀区', 'putuoqu', 'ptq', '310000', '310107', null, null);
INSERT INTO `sys_area` VALUES ('895', '虹口区', 'hongkouqu', 'hkq', '310000', '310109', null, null);
INSERT INTO `sys_area` VALUES ('896', '杨浦区', 'yangpuqu', 'ypq', '310000', '310110', null, null);
INSERT INTO `sys_area` VALUES ('897', '闵行区', 'zuoxingqu', '闵xq', '310000', '310112', null, null);
INSERT INTO `sys_area` VALUES ('898', '宝山区', 'baoshanqu', 'bsq', '310000', '310113', null, null);
INSERT INTO `sys_area` VALUES ('899', '嘉定区', 'jiadingqu', 'jdq', '310000', '310114', null, null);
INSERT INTO `sys_area` VALUES ('900', '浦东新区', 'pudongxinqu', 'pdxq', '310000', '310115', null, null);
INSERT INTO `sys_area` VALUES ('901', '金山区', 'jinshanqu', 'jsq', '310000', '310116', null, null);
INSERT INTO `sys_area` VALUES ('902', '松江区', 'songjiangqu', 'sjq', '310000', '310117', null, null);
INSERT INTO `sys_area` VALUES ('903', '青浦区', 'qingpuqu', 'qpq', '310000', '310118', null, null);
INSERT INTO `sys_area` VALUES ('904', '奉贤区', 'fengxianqu', 'fxq', '310000', '310120', null, null);
INSERT INTO `sys_area` VALUES ('905', '崇明区', 'chongmingqu', 'cmq', '310000', '310151', null, null);
INSERT INTO `sys_area` VALUES ('906', '江苏省', 'jiangsusheng', 'jss', '0', '320000', null, null);
INSERT INTO `sys_area` VALUES ('907', '南京市', 'nanjingshi', 'njs', '320000', '320100', null, null);
INSERT INTO `sys_area` VALUES ('908', '玄武区', 'xuanwuqu', 'xwq', '320100', '320102', null, null);
INSERT INTO `sys_area` VALUES ('909', '秦淮区', 'qinhuaiqu', 'qhq', '320100', '320104', null, null);
INSERT INTO `sys_area` VALUES ('910', '建邺区', 'jianzuoqu', 'j邺q', '320100', '320105', null, null);
INSERT INTO `sys_area` VALUES ('911', '鼓楼区', 'gulouqu', 'glq', '320100', '320106', null, null);
INSERT INTO `sys_area` VALUES ('912', '浦口区', 'pukouqu', 'pkq', '320100', '320111', null, null);
INSERT INTO `sys_area` VALUES ('913', '栖霞区', 'qixiaqu', 'qxq', '320100', '320113', null, null);
INSERT INTO `sys_area` VALUES ('914', '雨花台区', 'yuhuataiqu', 'yhtq', '320100', '320114', null, null);
INSERT INTO `sys_area` VALUES ('915', '江宁区', 'jiangningqu', 'jnq', '320100', '320115', null, null);
INSERT INTO `sys_area` VALUES ('916', '六合区', 'liuhequ', 'lhq', '320100', '320116', null, null);
INSERT INTO `sys_area` VALUES ('917', '溧水区', 'zuoshuiqu', '溧sq', '320100', '320117', null, null);
INSERT INTO `sys_area` VALUES ('918', '高淳区', 'gaochunqu', 'gcq', '320100', '320118', null, null);
INSERT INTO `sys_area` VALUES ('919', '无锡市', 'wuxishi', 'wxs', '320000', '320200', null, null);
INSERT INTO `sys_area` VALUES ('920', '锡山区', 'xishanqu', 'xsq', '320200', '320205', null, null);
INSERT INTO `sys_area` VALUES ('921', '惠山区', 'huishanqu', 'hsq', '320200', '320206', null, null);
INSERT INTO `sys_area` VALUES ('922', '滨湖区', 'binhuqu', 'bhq', '320200', '320211', null, null);
INSERT INTO `sys_area` VALUES ('923', '梁溪区', 'liangxiqu', 'lxq', '320200', '320213', null, null);
INSERT INTO `sys_area` VALUES ('924', '新吴区', 'xinwuqu', 'xwq', '320200', '320214', null, null);
INSERT INTO `sys_area` VALUES ('925', '江阴市', 'jiangyinshi', 'jys', '320200', '320281', null, null);
INSERT INTO `sys_area` VALUES ('926', '宜兴市', 'yixingshi', 'yxs', '320200', '320282', null, null);
INSERT INTO `sys_area` VALUES ('927', '徐州市', 'xuzhoushi', 'xzs', '320000', '320300', null, null);
INSERT INTO `sys_area` VALUES ('928', '鼓楼区', 'gulouqu', 'glq', '320300', '320302', null, null);
INSERT INTO `sys_area` VALUES ('929', '云龙区', 'yunlongqu', 'ylq', '320300', '320303', null, null);
INSERT INTO `sys_area` VALUES ('930', '贾汪区', 'jiawangqu', 'jwq', '320300', '320305', null, null);
INSERT INTO `sys_area` VALUES ('931', '泉山区', 'quanshanqu', 'qsq', '320300', '320311', null, null);
INSERT INTO `sys_area` VALUES ('932', '铜山区', 'tongshanqu', 'tsq', '320300', '320312', null, null);
INSERT INTO `sys_area` VALUES ('933', '丰县', 'fengxian', 'fx', '320300', '320321', null, null);
INSERT INTO `sys_area` VALUES ('934', '沛县', 'peixian', 'px', '320300', '320322', null, null);
INSERT INTO `sys_area` VALUES ('935', '睢宁县', 'zuoningxian', '睢nx', '320300', '320324', null, null);
INSERT INTO `sys_area` VALUES ('936', '新沂市', 'xinyishi', 'xys', '320300', '320381', null, null);
INSERT INTO `sys_area` VALUES ('937', '邳州市', 'zuozhoushi', '邳zs', '320300', '320382', null, null);
INSERT INTO `sys_area` VALUES ('938', '常州市', 'changzhoushi', 'czs', '320000', '320400', null, null);
INSERT INTO `sys_area` VALUES ('939', '天宁区', 'tianningqu', 'tnq', '320400', '320402', null, null);
INSERT INTO `sys_area` VALUES ('940', '钟楼区', 'zhonglouqu', 'zlq', '320400', '320404', null, null);
INSERT INTO `sys_area` VALUES ('941', '新北区', 'xinbeiqu', 'xbq', '320400', '320411', null, null);
INSERT INTO `sys_area` VALUES ('942', '武进区', 'wujinqu', 'wjq', '320400', '320412', null, null);
INSERT INTO `sys_area` VALUES ('943', '金坛区', 'jintanqu', 'jtq', '320400', '320413', null, null);
INSERT INTO `sys_area` VALUES ('944', '溧阳市', 'zuoyangshi', '溧ys', '320400', '320481', null, null);
INSERT INTO `sys_area` VALUES ('945', '苏州市', 'suzhoushi', 'szs', '320000', '320500', null, null);
INSERT INTO `sys_area` VALUES ('946', '虎丘区', 'huqiuqu', 'hqq', '320500', '320505', null, null);
INSERT INTO `sys_area` VALUES ('947', '吴中区', 'wuzhongqu', 'wzq', '320500', '320506', null, null);
INSERT INTO `sys_area` VALUES ('948', '相城区', 'xiangchengqu', 'xcq', '320500', '320507', null, null);
INSERT INTO `sys_area` VALUES ('949', '姑苏区', 'gusuqu', 'gsq', '320500', '320508', null, null);
INSERT INTO `sys_area` VALUES ('950', '吴江区', 'wujiangqu', 'wjq', '320500', '320509', null, null);
INSERT INTO `sys_area` VALUES ('951', '常熟市', 'changshushi', 'css', '320500', '320581', null, null);
INSERT INTO `sys_area` VALUES ('952', '张家港市', 'zhangjiagangshi', 'zjgs', '320500', '320582', null, null);
INSERT INTO `sys_area` VALUES ('953', '昆山市', 'kunshanshi', 'kss', '320500', '320583', null, null);
INSERT INTO `sys_area` VALUES ('954', '太仓市', 'taicangshi', 'tcs', '320500', '320585', null, null);
INSERT INTO `sys_area` VALUES ('955', '南通市', 'nantongshi', 'nts', '320000', '320600', null, null);
INSERT INTO `sys_area` VALUES ('956', '崇川区', 'chongchuanqu', 'ccq', '320600', '320602', null, null);
INSERT INTO `sys_area` VALUES ('957', '港闸区', 'gangzhaqu', 'gzq', '320600', '320611', null, null);
INSERT INTO `sys_area` VALUES ('958', '通州区', 'tongzhouqu', 'tzq', '320600', '320612', null, null);
INSERT INTO `sys_area` VALUES ('959', '如东县', 'rudongxian', 'rdx', '320600', '320623', null, null);
INSERT INTO `sys_area` VALUES ('960', '启东市', 'qidongshi', 'qds', '320600', '320681', null, null);
INSERT INTO `sys_area` VALUES ('961', '如皋市', 'rugaoshi', 'rgs', '320600', '320682', null, null);
INSERT INTO `sys_area` VALUES ('962', '海门市', 'haimenshi', 'hms', '320600', '320684', null, null);
INSERT INTO `sys_area` VALUES ('963', '海安市', 'haianshi', 'has', '320600', '320685', null, null);
INSERT INTO `sys_area` VALUES ('964', '连云港市', 'lianyungangshi', 'lygs', '320000', '320700', null, null);
INSERT INTO `sys_area` VALUES ('965', '连云区', 'lianyunqu', 'lyq', '320700', '320703', null, null);
INSERT INTO `sys_area` VALUES ('966', '海州区', 'haizhouqu', 'hzq', '320700', '320706', null, null);
INSERT INTO `sys_area` VALUES ('967', '赣榆区', 'ganyuqu', 'gyq', '320700', '320707', null, null);
INSERT INTO `sys_area` VALUES ('968', '东海县', 'donghaixian', 'dhx', '320700', '320722', null, null);
INSERT INTO `sys_area` VALUES ('969', '灌云县', 'guanyunxian', 'gyx', '320700', '320723', null, null);
INSERT INTO `sys_area` VALUES ('970', '灌南县', 'guannanxian', 'gnx', '320700', '320724', null, null);
INSERT INTO `sys_area` VALUES ('971', '淮安市', 'huaianshi', 'has', '320000', '320800', null, null);
INSERT INTO `sys_area` VALUES ('972', '淮安区', 'huaianqu', 'haq', '320800', '320803', null, null);
INSERT INTO `sys_area` VALUES ('973', '淮阴区', 'huaiyinqu', 'hyq', '320800', '320804', null, null);
INSERT INTO `sys_area` VALUES ('974', '清江浦区', 'qingjiangpuqu', 'qjpq', '320800', '320812', null, null);
INSERT INTO `sys_area` VALUES ('975', '洪泽区', 'hongzequ', 'hzq', '320800', '320813', null, null);
INSERT INTO `sys_area` VALUES ('976', '涟水县', 'lianshuixian', 'lsx', '320800', '320826', null, null);
INSERT INTO `sys_area` VALUES ('977', '盱眙县', 'zuozuoxian', '盱眙x', '320800', '320830', null, null);
INSERT INTO `sys_area` VALUES ('978', '金湖县', 'jinhuxian', 'jhx', '320800', '320831', null, null);
INSERT INTO `sys_area` VALUES ('979', '盐城市', 'yanchengshi', 'ycs', '320000', '320900', null, null);
INSERT INTO `sys_area` VALUES ('980', '亭湖区', 'tinghuqu', 'thq', '320900', '320902', null, null);
INSERT INTO `sys_area` VALUES ('981', '盐都区', 'yanduqu', 'ydq', '320900', '320903', null, null);
INSERT INTO `sys_area` VALUES ('982', '大丰区', 'dafengqu', 'dfq', '320900', '320904', null, null);
INSERT INTO `sys_area` VALUES ('983', '响水县', 'xiangshuixian', 'xsx', '320900', '320921', null, null);
INSERT INTO `sys_area` VALUES ('984', '滨海县', 'binhaixian', 'bhx', '320900', '320922', null, null);
INSERT INTO `sys_area` VALUES ('985', '阜宁县', 'funingxian', 'fnx', '320900', '320923', null, null);
INSERT INTO `sys_area` VALUES ('986', '射阳县', 'sheyangxian', 'syx', '320900', '320924', null, null);
INSERT INTO `sys_area` VALUES ('987', '建湖县', 'jianhuxian', 'jhx', '320900', '320925', null, null);
INSERT INTO `sys_area` VALUES ('988', '东台市', 'dongtaishi', 'dts', '320900', '320981', null, null);
INSERT INTO `sys_area` VALUES ('989', '扬州市', 'yangzhoushi', 'yzs', '320000', '321000', null, null);
INSERT INTO `sys_area` VALUES ('990', '广陵区', 'guanglingqu', 'glq', '321000', '321002', null, null);
INSERT INTO `sys_area` VALUES ('991', '邗江区', 'zuojiangqu', '邗jq', '321000', '321003', null, null);
INSERT INTO `sys_area` VALUES ('992', '江都区', 'jiangduqu', 'jdq', '321000', '321012', null, null);
INSERT INTO `sys_area` VALUES ('993', '宝应县', 'baoyingxian', 'byx', '321000', '321023', null, null);
INSERT INTO `sys_area` VALUES ('994', '仪征市', 'yizhengshi', 'yzs', '321000', '321081', null, null);
INSERT INTO `sys_area` VALUES ('995', '高邮市', 'gaoyoushi', 'gys', '321000', '321084', null, null);
INSERT INTO `sys_area` VALUES ('996', '镇江市', 'zhenjiangshi', 'zjs', '320000', '321100', null, null);
INSERT INTO `sys_area` VALUES ('997', '京口区', 'jingkouqu', 'jkq', '321100', '321102', null, null);
INSERT INTO `sys_area` VALUES ('998', '润州区', 'runzhouqu', 'rzq', '321100', '321111', null, null);
INSERT INTO `sys_area` VALUES ('999', '丹徒区', 'dantuqu', 'dtq', '321100', '321112', null, null);
INSERT INTO `sys_area` VALUES ('1000', '丹阳市', 'danyangshi', 'dys', '321100', '321181', null, null);
INSERT INTO `sys_area` VALUES ('1001', '扬中市', 'yangzhongshi', 'yzs', '321100', '321182', null, null);
INSERT INTO `sys_area` VALUES ('1002', '句容市', 'jurongshi', 'jrs', '321100', '321183', null, null);
INSERT INTO `sys_area` VALUES ('1003', '泰州市', 'taizhoushi', 'tzs', '320000', '321200', null, null);
INSERT INTO `sys_area` VALUES ('1004', '海陵区', 'hailingqu', 'hlq', '321200', '321202', null, null);
INSERT INTO `sys_area` VALUES ('1005', '高港区', 'gaogangqu', 'ggq', '321200', '321203', null, null);
INSERT INTO `sys_area` VALUES ('1006', '姜堰区', 'jiangyanqu', 'jyq', '321200', '321204', null, null);
INSERT INTO `sys_area` VALUES ('1007', '兴化市', 'xinghuashi', 'xhs', '321200', '321281', null, null);
INSERT INTO `sys_area` VALUES ('1008', '靖江市', 'jingjiangshi', 'jjs', '321200', '321282', null, null);
INSERT INTO `sys_area` VALUES ('1009', '泰兴市', 'taixingshi', 'txs', '321200', '321283', null, null);
INSERT INTO `sys_area` VALUES ('1010', '宿迁市', 'suqianshi', 'sqs', '320000', '321300', null, null);
INSERT INTO `sys_area` VALUES ('1011', '宿城区', 'suchengqu', 'scq', '321300', '321302', null, null);
INSERT INTO `sys_area` VALUES ('1012', '宿豫区', 'suyuqu', 'syq', '321300', '321311', null, null);
INSERT INTO `sys_area` VALUES ('1013', '沭阳县', 'zuoyangxian', '沭yx', '321300', '321322', null, null);
INSERT INTO `sys_area` VALUES ('1014', '泗阳县', 'zuoyangxian', '泗yx', '321300', '321323', null, null);
INSERT INTO `sys_area` VALUES ('1015', '泗洪县', 'zuohongxian', '泗hx', '321300', '321324', null, null);
INSERT INTO `sys_area` VALUES ('1016', '浙江省', 'zhejiangsheng', 'zjs', '0', '330000', null, null);
INSERT INTO `sys_area` VALUES ('1017', '杭州市', 'hangzhoushi', 'hzs', '330000', '330100', null, null);
INSERT INTO `sys_area` VALUES ('1018', '上城区', 'shangchengqu', 'scq', '330100', '330102', null, null);
INSERT INTO `sys_area` VALUES ('1019', '下城区', 'xiachengqu', 'xcq', '330100', '330103', null, null);
INSERT INTO `sys_area` VALUES ('1020', '江干区', 'jiangganqu', 'jgq', '330100', '330104', null, null);
INSERT INTO `sys_area` VALUES ('1021', '拱墅区', 'gongshuqu', 'gsq', '330100', '330105', null, null);
INSERT INTO `sys_area` VALUES ('1022', '西湖区', 'xihuqu', 'xhq', '330100', '330106', null, null);
INSERT INTO `sys_area` VALUES ('1023', '滨江区', 'binjiangqu', 'bjq', '330100', '330108', null, null);
INSERT INTO `sys_area` VALUES ('1024', '萧山区', 'xiaoshanqu', 'xsq', '330100', '330109', null, null);
INSERT INTO `sys_area` VALUES ('1025', '余杭区', 'yuhangqu', 'yhq', '330100', '330110', null, null);
INSERT INTO `sys_area` VALUES ('1026', '富阳区', 'fuyangqu', 'fyq', '330100', '330111', null, null);
INSERT INTO `sys_area` VALUES ('1027', '临安区', 'linanqu', 'laq', '330100', '330112', null, null);
INSERT INTO `sys_area` VALUES ('1028', '桐庐县', 'tongluxian', 'tlx', '330100', '330122', null, null);
INSERT INTO `sys_area` VALUES ('1029', '淳安县', 'chunanxian', 'cax', '330100', '330127', null, null);
INSERT INTO `sys_area` VALUES ('1030', '建德市', 'jiandeshi', 'jds', '330100', '330182', null, null);
INSERT INTO `sys_area` VALUES ('1031', '宁波市', 'ningboshi', 'nbs', '330000', '330200', null, null);
INSERT INTO `sys_area` VALUES ('1032', '海曙区', 'haishuqu', 'hsq', '330200', '330203', null, null);
INSERT INTO `sys_area` VALUES ('1033', '江北区', 'jiangbeiqu', 'jbq', '330200', '330205', null, null);
INSERT INTO `sys_area` VALUES ('1034', '北仑区', 'beilunqu', 'blq', '330200', '330206', null, null);
INSERT INTO `sys_area` VALUES ('1035', '镇海区', 'zhenhaiqu', 'zhq', '330200', '330211', null, null);
INSERT INTO `sys_area` VALUES ('1036', '鄞州区', 'zuozhouqu', '鄞zq', '330200', '330212', null, null);
INSERT INTO `sys_area` VALUES ('1037', '奉化区', 'fenghuaqu', 'fhq', '330200', '330213', null, null);
INSERT INTO `sys_area` VALUES ('1038', '象山县', 'xiangshanxian', 'xsx', '330200', '330225', null, null);
INSERT INTO `sys_area` VALUES ('1039', '宁海县', 'ninghaixian', 'nhx', '330200', '330226', null, null);
INSERT INTO `sys_area` VALUES ('1040', '余姚市', 'yuyaoshi', 'yys', '330200', '330281', null, null);
INSERT INTO `sys_area` VALUES ('1041', '慈溪市', 'cixishi', 'cxs', '330200', '330282', null, null);
INSERT INTO `sys_area` VALUES ('1042', '温州市', 'wenzhoushi', 'wzs', '330000', '330300', null, null);
INSERT INTO `sys_area` VALUES ('1043', '鹿城区', 'luchengqu', 'lcq', '330300', '330302', null, null);
INSERT INTO `sys_area` VALUES ('1044', '龙湾区', 'longwanqu', 'lwq', '330300', '330303', null, null);
INSERT INTO `sys_area` VALUES ('1045', '瓯海区', 'zuohaiqu', '瓯hq', '330300', '330304', null, null);
INSERT INTO `sys_area` VALUES ('1046', '洞头区', 'dongtouqu', 'dtq', '330300', '330305', null, null);
INSERT INTO `sys_area` VALUES ('1047', '永嘉县', 'yongjiaxian', 'yjx', '330300', '330324', null, null);
INSERT INTO `sys_area` VALUES ('1048', '平阳县', 'pingyangxian', 'pyx', '330300', '330326', null, null);
INSERT INTO `sys_area` VALUES ('1049', '苍南县', 'cangnanxian', 'cnx', '330300', '330327', null, null);
INSERT INTO `sys_area` VALUES ('1050', '文成县', 'wenchengxian', 'wcx', '330300', '330328', null, null);
INSERT INTO `sys_area` VALUES ('1051', '泰顺县', 'taishunxian', 'tsx', '330300', '330329', null, null);
INSERT INTO `sys_area` VALUES ('1052', '瑞安市', 'ruianshi', 'ras', '330300', '330381', null, null);
INSERT INTO `sys_area` VALUES ('1053', '乐清市', 'leqingshi', 'lqs', '330300', '330382', null, null);
INSERT INTO `sys_area` VALUES ('1054', '嘉兴市', 'jiaxingshi', 'jxs', '330000', '330400', null, null);
INSERT INTO `sys_area` VALUES ('1055', '南湖区', 'nanhuqu', 'nhq', '330400', '330402', null, null);
INSERT INTO `sys_area` VALUES ('1056', '秀洲区', 'xiuzhouqu', 'xzq', '330400', '330411', null, null);
INSERT INTO `sys_area` VALUES ('1057', '嘉善县', 'jiashanxian', 'jsx', '330400', '330421', null, null);
INSERT INTO `sys_area` VALUES ('1058', '海盐县', 'haiyanxian', 'hyx', '330400', '330424', null, null);
INSERT INTO `sys_area` VALUES ('1059', '海宁市', 'hainingshi', 'hns', '330400', '330481', null, null);
INSERT INTO `sys_area` VALUES ('1060', '平湖市', 'pinghushi', 'phs', '330400', '330482', null, null);
INSERT INTO `sys_area` VALUES ('1061', '桐乡市', 'tongxiangshi', 'txs', '330400', '330483', null, null);
INSERT INTO `sys_area` VALUES ('1062', '湖州市', 'huzhoushi', 'hzs', '330000', '330500', null, null);
INSERT INTO `sys_area` VALUES ('1063', '吴兴区', 'wuxingqu', 'wxq', '330500', '330502', null, null);
INSERT INTO `sys_area` VALUES ('1064', '南浔区', 'nanzuoqu', 'n浔q', '330500', '330503', null, null);
INSERT INTO `sys_area` VALUES ('1065', '德清县', 'deqingxian', 'dqx', '330500', '330521', null, null);
INSERT INTO `sys_area` VALUES ('1066', '长兴县', 'changxingxian', 'cxx', '330500', '330522', null, null);
INSERT INTO `sys_area` VALUES ('1067', '安吉县', 'anjixian', 'ajx', '330500', '330523', null, null);
INSERT INTO `sys_area` VALUES ('1068', '绍兴市', 'shaoxingshi', 'sxs', '330000', '330600', null, null);
INSERT INTO `sys_area` VALUES ('1069', '越城区', 'yuechengqu', 'ycq', '330600', '330602', null, null);
INSERT INTO `sys_area` VALUES ('1070', '柯桥区', 'keqiaoqu', 'kqq', '330600', '330603', null, null);
INSERT INTO `sys_area` VALUES ('1071', '上虞区', 'shangyuqu', 'syq', '330600', '330604', null, null);
INSERT INTO `sys_area` VALUES ('1072', '新昌县', 'xinchangxian', 'xcx', '330600', '330624', null, null);
INSERT INTO `sys_area` VALUES ('1073', '诸暨市', 'zhuzuoshi', 'z暨s', '330600', '330681', null, null);
INSERT INTO `sys_area` VALUES ('1074', '嵊州市', 'zuozhoushi', '嵊zs', '330600', '330683', null, null);
INSERT INTO `sys_area` VALUES ('1075', '金华市', 'jinhuashi', 'jhs', '330000', '330700', null, null);
INSERT INTO `sys_area` VALUES ('1076', '婺城区', 'zuochengqu', '婺cq', '330700', '330702', null, null);
INSERT INTO `sys_area` VALUES ('1077', '金东区', 'jindongqu', 'jdq', '330700', '330703', null, null);
INSERT INTO `sys_area` VALUES ('1078', '武义县', 'wuyixian', 'wyx', '330700', '330723', null, null);
INSERT INTO `sys_area` VALUES ('1079', '浦江县', 'pujiangxian', 'pjx', '330700', '330726', null, null);
INSERT INTO `sys_area` VALUES ('1080', '磐安县', 'pananxian', 'pax', '330700', '330727', null, null);
INSERT INTO `sys_area` VALUES ('1081', '兰溪市', 'lanxishi', 'lxs', '330700', '330781', null, null);
INSERT INTO `sys_area` VALUES ('1082', '义乌市', 'yiwushi', 'yws', '330700', '330782', null, null);
INSERT INTO `sys_area` VALUES ('1083', '东阳市', 'dongyangshi', 'dys', '330700', '330783', null, null);
INSERT INTO `sys_area` VALUES ('1084', '永康市', 'yongkangshi', 'yks', '330700', '330784', null, null);
INSERT INTO `sys_area` VALUES ('1085', '衢州市', 'zuozhoushi', '衢zs', '330000', '330800', null, null);
INSERT INTO `sys_area` VALUES ('1086', '柯城区', 'kechengqu', 'kcq', '330800', '330802', null, null);
INSERT INTO `sys_area` VALUES ('1087', '衢江区', 'zuojiangqu', '衢jq', '330800', '330803', null, null);
INSERT INTO `sys_area` VALUES ('1088', '常山县', 'changshanxian', 'csx', '330800', '330822', null, null);
INSERT INTO `sys_area` VALUES ('1089', '开化县', 'kaihuaxian', 'khx', '330800', '330824', null, null);
INSERT INTO `sys_area` VALUES ('1090', '龙游县', 'longyouxian', 'lyx', '330800', '330825', null, null);
INSERT INTO `sys_area` VALUES ('1091', '江山市', 'jiangshanshi', 'jss', '330800', '330881', null, null);
INSERT INTO `sys_area` VALUES ('1092', '舟山市', 'zhoushanshi', 'zss', '330000', '330900', null, null);
INSERT INTO `sys_area` VALUES ('1093', '定海区', 'dinghaiqu', 'dhq', '330900', '330902', null, null);
INSERT INTO `sys_area` VALUES ('1094', '普陀区', 'putuoqu', 'ptq', '330900', '330903', null, null);
INSERT INTO `sys_area` VALUES ('1095', '岱山县', 'zuoshanxian', '岱sx', '330900', '330921', null, null);
INSERT INTO `sys_area` VALUES ('1096', '嵊泗县', 'zuozuoxian', '嵊泗x', '330900', '330922', null, null);
INSERT INTO `sys_area` VALUES ('1097', '台州市', 'taizhoushi', 'tzs', '330000', '331000', null, null);
INSERT INTO `sys_area` VALUES ('1098', '椒江区', 'jiaojiangqu', 'jjq', '331000', '331002', null, null);
INSERT INTO `sys_area` VALUES ('1099', '黄岩区', 'huangyanqu', 'hyq', '331000', '331003', null, null);
INSERT INTO `sys_area` VALUES ('1100', '路桥区', 'luqiaoqu', 'lqq', '331000', '331004', null, null);
INSERT INTO `sys_area` VALUES ('1101', '三门县', 'sanmenxian', 'smx', '331000', '331022', null, null);
INSERT INTO `sys_area` VALUES ('1102', '天台县', 'tiantaixian', 'ttx', '331000', '331023', null, null);
INSERT INTO `sys_area` VALUES ('1103', '仙居县', 'xianjuxian', 'xjx', '331000', '331024', null, null);
INSERT INTO `sys_area` VALUES ('1104', '温岭市', 'wenlingshi', 'wls', '331000', '331081', null, null);
INSERT INTO `sys_area` VALUES ('1105', '临海市', 'linhaishi', 'lhs', '331000', '331082', null, null);
INSERT INTO `sys_area` VALUES ('1106', '玉环市', 'yuhuanshi', 'yhs', '331000', '331083', null, null);
INSERT INTO `sys_area` VALUES ('1107', '丽水市', 'lishuishi', 'lss', '330000', '331100', null, null);
INSERT INTO `sys_area` VALUES ('1108', '莲都区', 'lianduqu', 'ldq', '331100', '331102', null, null);
INSERT INTO `sys_area` VALUES ('1109', '青田县', 'qingtianxian', 'qtx', '331100', '331121', null, null);
INSERT INTO `sys_area` VALUES ('1110', '缙云县', 'zuoyunxian', '缙yx', '331100', '331122', null, null);
INSERT INTO `sys_area` VALUES ('1111', '遂昌县', 'suichangxian', 'scx', '331100', '331123', null, null);
INSERT INTO `sys_area` VALUES ('1112', '松阳县', 'songyangxian', 'syx', '331100', '331124', null, null);
INSERT INTO `sys_area` VALUES ('1113', '云和县', 'yunhexian', 'yhx', '331100', '331125', null, null);
INSERT INTO `sys_area` VALUES ('1114', '庆元县', 'qingyuanxian', 'qyx', '331100', '331126', null, null);
INSERT INTO `sys_area` VALUES ('1115', '景宁畲族自治县', 'jingningzuozuzizhixian', 'jn畲zzzx', '331100', '331127', null, null);
INSERT INTO `sys_area` VALUES ('1116', '龙泉市', 'longquanshi', 'lqs', '331100', '331181', null, null);
INSERT INTO `sys_area` VALUES ('1117', '安徽省', 'anhuisheng', 'ahs', '0', '340000', null, null);
INSERT INTO `sys_area` VALUES ('1118', '合肥市', 'hefeishi', 'hfs', '340000', '340100', null, null);
INSERT INTO `sys_area` VALUES ('1119', '瑶海区', 'yaohaiqu', 'yhq', '340100', '340102', null, null);
INSERT INTO `sys_area` VALUES ('1120', '庐阳区', 'luyangqu', 'lyq', '340100', '340103', null, null);
INSERT INTO `sys_area` VALUES ('1121', '蜀山区', 'shushanqu', 'ssq', '340100', '340104', null, null);
INSERT INTO `sys_area` VALUES ('1122', '包河区', 'baohequ', 'bhq', '340100', '340111', null, null);
INSERT INTO `sys_area` VALUES ('1123', '长丰县', 'changfengxian', 'cfx', '340100', '340121', null, null);
INSERT INTO `sys_area` VALUES ('1124', '肥东县', 'feidongxian', 'fdx', '340100', '340122', null, null);
INSERT INTO `sys_area` VALUES ('1125', '肥西县', 'feixixian', 'fxx', '340100', '340123', null, null);
INSERT INTO `sys_area` VALUES ('1126', '庐江县', 'lujiangxian', 'ljx', '340100', '340124', null, null);
INSERT INTO `sys_area` VALUES ('1127', '巢湖市', 'chaohushi', 'chs', '340100', '340181', null, null);
INSERT INTO `sys_area` VALUES ('1128', '芜湖市', 'wuhushi', 'whs', '340000', '340200', null, null);
INSERT INTO `sys_area` VALUES ('1129', '镜湖区', 'jinghuqu', 'jhq', '340200', '340202', null, null);
INSERT INTO `sys_area` VALUES ('1130', '弋江区', 'zuojiangqu', '弋jq', '340200', '340203', null, null);
INSERT INTO `sys_area` VALUES ('1131', '鸠江区', 'zuojiangqu', '鸠jq', '340200', '340207', null, null);
INSERT INTO `sys_area` VALUES ('1132', '三山区', 'sanshanqu', 'ssq', '340200', '340208', null, null);
INSERT INTO `sys_area` VALUES ('1133', '芜湖县', 'wuhuxian', 'whx', '340200', '340221', null, null);
INSERT INTO `sys_area` VALUES ('1134', '繁昌县', 'fanchangxian', 'fcx', '340200', '340222', null, null);
INSERT INTO `sys_area` VALUES ('1135', '南陵县', 'nanlingxian', 'nlx', '340200', '340223', null, null);
INSERT INTO `sys_area` VALUES ('1136', '无为县', 'wuweixian', 'wwx', '340200', '340225', null, null);
INSERT INTO `sys_area` VALUES ('1137', '蚌埠市', 'bangbushi', 'bbs', '340000', '340300', null, null);
INSERT INTO `sys_area` VALUES ('1138', '龙子湖区', 'longzihuqu', 'lzhq', '340300', '340302', null, null);
INSERT INTO `sys_area` VALUES ('1139', '蚌山区', 'bangshanqu', 'bsq', '340300', '340303', null, null);
INSERT INTO `sys_area` VALUES ('1140', '禹会区', 'yuhuiqu', 'yhq', '340300', '340304', null, null);
INSERT INTO `sys_area` VALUES ('1141', '淮上区', 'huaishangqu', 'hsq', '340300', '340311', null, null);
INSERT INTO `sys_area` VALUES ('1142', '怀远县', 'huaiyuanxian', 'hyx', '340300', '340321', null, null);
INSERT INTO `sys_area` VALUES ('1143', '五河县', 'wuhexian', 'whx', '340300', '340322', null, null);
INSERT INTO `sys_area` VALUES ('1144', '固镇县', 'guzhenxian', 'gzx', '340300', '340323', null, null);
INSERT INTO `sys_area` VALUES ('1145', '淮南市', 'huainanshi', 'hns', '340000', '340400', null, null);
INSERT INTO `sys_area` VALUES ('1146', '大通区', 'datongqu', 'dtq', '340400', '340402', null, null);
INSERT INTO `sys_area` VALUES ('1147', '田家庵区', 'tianjiazuoqu', 'tj庵q', '340400', '340403', null, null);
INSERT INTO `sys_area` VALUES ('1148', '谢家集区', 'xiejiajiqu', 'xjjq', '340400', '340404', null, null);
INSERT INTO `sys_area` VALUES ('1149', '八公山区', 'bagongshanqu', 'bgsq', '340400', '340405', null, null);
INSERT INTO `sys_area` VALUES ('1150', '潘集区', 'panjiqu', 'pjq', '340400', '340406', null, null);
INSERT INTO `sys_area` VALUES ('1151', '凤台县', 'fengtaixian', 'ftx', '340400', '340421', null, null);
INSERT INTO `sys_area` VALUES ('1152', '寿县', 'shouxian', 'sx', '340400', '340422', null, null);
INSERT INTO `sys_area` VALUES ('1153', '马鞍山市', 'maanshanshi', 'mass', '340000', '340500', null, null);
INSERT INTO `sys_area` VALUES ('1154', '花山区', 'huashanqu', 'hsq', '340500', '340503', null, null);
INSERT INTO `sys_area` VALUES ('1155', '雨山区', 'yushanqu', 'ysq', '340500', '340504', null, null);
INSERT INTO `sys_area` VALUES ('1156', '博望区', 'bowangqu', 'bwq', '340500', '340506', null, null);
INSERT INTO `sys_area` VALUES ('1157', '当涂县', 'dangtuxian', 'dtx', '340500', '340521', null, null);
INSERT INTO `sys_area` VALUES ('1158', '含山县', 'hanshanxian', 'hsx', '340500', '340522', null, null);
INSERT INTO `sys_area` VALUES ('1159', '和县', 'hexian', 'hx', '340500', '340523', null, null);
INSERT INTO `sys_area` VALUES ('1160', '淮北市', 'huaibeishi', 'hbs', '340000', '340600', null, null);
INSERT INTO `sys_area` VALUES ('1161', '杜集区', 'dujiqu', 'djq', '340600', '340602', null, null);
INSERT INTO `sys_area` VALUES ('1162', '相山区', 'xiangshanqu', 'xsq', '340600', '340603', null, null);
INSERT INTO `sys_area` VALUES ('1163', '烈山区', 'lieshanqu', 'lsq', '340600', '340604', null, null);
INSERT INTO `sys_area` VALUES ('1164', '濉溪县', 'zuoxixian', '濉xx', '340600', '340621', null, null);
INSERT INTO `sys_area` VALUES ('1165', '铜陵市', 'tonglingshi', 'tls', '340000', '340700', null, null);
INSERT INTO `sys_area` VALUES ('1166', '铜官区', 'tongguanqu', 'tgq', '340700', '340705', null, null);
INSERT INTO `sys_area` VALUES ('1167', '义安区', 'yianqu', 'yaq', '340700', '340706', null, null);
INSERT INTO `sys_area` VALUES ('1168', '郊区', 'jiaoqu', 'jq', '340700', '340711', null, null);
INSERT INTO `sys_area` VALUES ('1169', '枞阳县', 'zuoyangxian', '枞yx', '340700', '340722', null, null);
INSERT INTO `sys_area` VALUES ('1170', '安庆市', 'anqingshi', 'aqs', '340000', '340800', null, null);
INSERT INTO `sys_area` VALUES ('1171', '迎江区', 'yingjiangqu', 'yjq', '340800', '340802', null, null);
INSERT INTO `sys_area` VALUES ('1172', '大观区', 'daguanqu', 'dgq', '340800', '340803', null, null);
INSERT INTO `sys_area` VALUES ('1173', '宜秀区', 'yixiuqu', 'yxq', '340800', '340811', null, null);
INSERT INTO `sys_area` VALUES ('1174', '怀宁县', 'huainingxian', 'hnx', '340800', '340822', null, null);
INSERT INTO `sys_area` VALUES ('1175', '潜山县', 'qianshanxian', 'qsx', '340800', '340824', null, null);
INSERT INTO `sys_area` VALUES ('1176', '太湖县', 'taihuxian', 'thx', '340800', '340825', null, null);
INSERT INTO `sys_area` VALUES ('1177', '宿松县', 'susongxian', 'ssx', '340800', '340826', null, null);
INSERT INTO `sys_area` VALUES ('1178', '望江县', 'wangjiangxian', 'wjx', '340800', '340827', null, null);
INSERT INTO `sys_area` VALUES ('1179', '岳西县', 'yuexixian', 'yxx', '340800', '340828', null, null);
INSERT INTO `sys_area` VALUES ('1180', '桐城市', 'tongchengshi', 'tcs', '340800', '340881', null, null);
INSERT INTO `sys_area` VALUES ('1181', '黄山市', 'huangshanshi', 'hss', '340000', '341000', null, null);
INSERT INTO `sys_area` VALUES ('1182', '屯溪区', 'tunxiqu', 'txq', '341000', '341002', null, null);
INSERT INTO `sys_area` VALUES ('1183', '黄山区', 'huangshanqu', 'hsq', '341000', '341003', null, null);
INSERT INTO `sys_area` VALUES ('1184', '徽州区', 'huizhouqu', 'hzq', '341000', '341004', null, null);
INSERT INTO `sys_area` VALUES ('1185', '歙县', 'zuoxian', '歙x', '341000', '341021', null, null);
INSERT INTO `sys_area` VALUES ('1186', '休宁县', 'xiuningxian', 'xnx', '341000', '341022', null, null);
INSERT INTO `sys_area` VALUES ('1187', '黟县', 'zuoxian', '黟x', '341000', '341023', null, null);
INSERT INTO `sys_area` VALUES ('1188', '祁门县', 'qimenxian', 'qmx', '341000', '341024', null, null);
INSERT INTO `sys_area` VALUES ('1189', '滁州市', 'chuzhoushi', 'czs', '340000', '341100', null, null);
INSERT INTO `sys_area` VALUES ('1190', '琅琊区', 'langzuoqu', 'l琊q', '341100', '341102', null, null);
INSERT INTO `sys_area` VALUES ('1191', '南谯区', 'nanzuoqu', 'n谯q', '341100', '341103', null, null);
INSERT INTO `sys_area` VALUES ('1192', '来安县', 'laianxian', 'lax', '341100', '341122', null, null);
INSERT INTO `sys_area` VALUES ('1193', '全椒县', 'quanjiaoxian', 'qjx', '341100', '341124', null, null);
INSERT INTO `sys_area` VALUES ('1194', '定远县', 'dingyuanxian', 'dyx', '341100', '341125', null, null);
INSERT INTO `sys_area` VALUES ('1195', '凤阳县', 'fengyangxian', 'fyx', '341100', '341126', null, null);
INSERT INTO `sys_area` VALUES ('1196', '天长市', 'tianchangshi', 'tcs', '341100', '341181', null, null);
INSERT INTO `sys_area` VALUES ('1197', '明光市', 'mingguangshi', 'mgs', '341100', '341182', null, null);
INSERT INTO `sys_area` VALUES ('1198', '阜阳市', 'fuyangshi', 'fys', '340000', '341200', null, null);
INSERT INTO `sys_area` VALUES ('1199', '颍州区', 'zuozhouqu', '颍zq', '341200', '341202', null, null);
INSERT INTO `sys_area` VALUES ('1200', '颍东区', 'zuodongqu', '颍dq', '341200', '341203', null, null);
INSERT INTO `sys_area` VALUES ('1201', '颍泉区', 'zuoquanqu', '颍qq', '341200', '341204', null, null);
INSERT INTO `sys_area` VALUES ('1202', '临泉县', 'linquanxian', 'lqx', '341200', '341221', null, null);
INSERT INTO `sys_area` VALUES ('1203', '太和县', 'taihexian', 'thx', '341200', '341222', null, null);
INSERT INTO `sys_area` VALUES ('1204', '阜南县', 'funanxian', 'fnx', '341200', '341225', null, null);
INSERT INTO `sys_area` VALUES ('1205', '颍上县', 'zuoshangxian', '颍sx', '341200', '341226', null, null);
INSERT INTO `sys_area` VALUES ('1206', '界首市', 'jieshoushi', 'jss', '341200', '341282', null, null);
INSERT INTO `sys_area` VALUES ('1207', '宿州市', 'suzhoushi', 'szs', '340000', '341300', null, null);
INSERT INTO `sys_area` VALUES ('1208', '埇桥区', 'qiaoqu', '埇qq', '341300', '341302', null, null);
INSERT INTO `sys_area` VALUES ('1209', '砀山县', 'zuoshanxian', '砀sx', '341300', '341321', null, null);
INSERT INTO `sys_area` VALUES ('1210', '萧县', 'xiaoxian', 'xx', '341300', '341322', null, null);
INSERT INTO `sys_area` VALUES ('1211', '灵璧县', 'lingzuoxian', 'l璧x', '341300', '341323', null, null);
INSERT INTO `sys_area` VALUES ('1212', '泗县', 'zuoxian', '泗x', '341300', '341324', null, null);
INSERT INTO `sys_area` VALUES ('1213', '六安市', 'liuanshi', 'las', '340000', '341500', null, null);
INSERT INTO `sys_area` VALUES ('1214', '金安区', 'jinanqu', 'jaq', '341500', '341502', null, null);
INSERT INTO `sys_area` VALUES ('1215', '裕安区', 'yuanqu', 'yaq', '341500', '341503', null, null);
INSERT INTO `sys_area` VALUES ('1216', '叶集区', 'yejiqu', 'yjq', '341500', '341504', null, null);
INSERT INTO `sys_area` VALUES ('1217', '霍邱县', 'huoqiuxian', 'hqx', '341500', '341522', null, null);
INSERT INTO `sys_area` VALUES ('1218', '舒城县', 'shuchengxian', 'scx', '341500', '341523', null, null);
INSERT INTO `sys_area` VALUES ('1219', '金寨县', 'jinzhaixian', 'jzx', '341500', '341524', null, null);
INSERT INTO `sys_area` VALUES ('1220', '霍山县', 'huoshanxian', 'hsx', '341500', '341525', null, null);
INSERT INTO `sys_area` VALUES ('1221', '亳州市', 'zuozhoushi', '亳zs', '340000', '341600', null, null);
INSERT INTO `sys_area` VALUES ('1222', '谯城区', 'zuochengqu', '谯cq', '341600', '341602', null, null);
INSERT INTO `sys_area` VALUES ('1223', '涡阳县', 'woyangxian', 'wyx', '341600', '341621', null, null);
INSERT INTO `sys_area` VALUES ('1224', '蒙城县', 'mengchengxian', 'mcx', '341600', '341622', null, null);
INSERT INTO `sys_area` VALUES ('1225', '利辛县', 'lixinxian', 'lxx', '341600', '341623', null, null);
INSERT INTO `sys_area` VALUES ('1226', '池州市', 'chizhoushi', 'czs', '340000', '341700', null, null);
INSERT INTO `sys_area` VALUES ('1227', '贵池区', 'guichiqu', 'gcq', '341700', '341702', null, null);
INSERT INTO `sys_area` VALUES ('1228', '东至县', 'dongzhixian', 'dzx', '341700', '341721', null, null);
INSERT INTO `sys_area` VALUES ('1229', '石台县', 'shitaixian', 'stx', '341700', '341722', null, null);
INSERT INTO `sys_area` VALUES ('1230', '青阳县', 'qingyangxian', 'qyx', '341700', '341723', null, null);
INSERT INTO `sys_area` VALUES ('1231', '宣城市', 'xuanchengshi', 'xcs', '340000', '341800', null, null);
INSERT INTO `sys_area` VALUES ('1232', '宣州区', 'xuanzhouqu', 'xzq', '341800', '341802', null, null);
INSERT INTO `sys_area` VALUES ('1233', '郎溪县', 'langxixian', 'lxx', '341800', '341821', null, null);
INSERT INTO `sys_area` VALUES ('1234', '广德县', 'guangdexian', 'gdx', '341800', '341822', null, null);
INSERT INTO `sys_area` VALUES ('1235', '泾县', 'zuoxian', '泾x', '341800', '341823', null, null);
INSERT INTO `sys_area` VALUES ('1236', '绩溪县', 'jixixian', 'jxx', '341800', '341824', null, null);
INSERT INTO `sys_area` VALUES ('1237', '旌德县', 'zuodexian', '旌dx', '341800', '341825', null, null);
INSERT INTO `sys_area` VALUES ('1238', '宁国市', 'ningguoshi', 'ngs', '341800', '341881', null, null);
INSERT INTO `sys_area` VALUES ('1239', '福建省', 'fujiansheng', 'fjs', '0', '350000', null, null);
INSERT INTO `sys_area` VALUES ('1240', '福州市', 'fuzhoushi', 'fzs', '350000', '350100', null, null);
INSERT INTO `sys_area` VALUES ('1241', '鼓楼区', 'gulouqu', 'glq', '350100', '350102', null, null);
INSERT INTO `sys_area` VALUES ('1242', '台江区', 'taijiangqu', 'tjq', '350100', '350103', null, null);
INSERT INTO `sys_area` VALUES ('1243', '仓山区', 'cangshanqu', 'csq', '350100', '350104', null, null);
INSERT INTO `sys_area` VALUES ('1244', '马尾区', 'maweiqu', 'mwq', '350100', '350105', null, null);
INSERT INTO `sys_area` VALUES ('1245', '晋安区', 'jinanqu', 'jaq', '350100', '350111', null, null);
INSERT INTO `sys_area` VALUES ('1246', '长乐区', 'changlequ', 'clq', '350100', '350112', null, null);
INSERT INTO `sys_area` VALUES ('1247', '闽侯县', 'minhouxian', 'mhx', '350100', '350121', null, null);
INSERT INTO `sys_area` VALUES ('1248', '连江县', 'lianjiangxian', 'ljx', '350100', '350122', null, null);
INSERT INTO `sys_area` VALUES ('1249', '罗源县', 'luoyuanxian', 'lyx', '350100', '350123', null, null);
INSERT INTO `sys_area` VALUES ('1250', '闽清县', 'minqingxian', 'mqx', '350100', '350124', null, null);
INSERT INTO `sys_area` VALUES ('1251', '永泰县', 'yongtaixian', 'ytx', '350100', '350125', null, null);
INSERT INTO `sys_area` VALUES ('1252', '平潭县', 'pingtanxian', 'ptx', '350100', '350128', null, null);
INSERT INTO `sys_area` VALUES ('1253', '福清市', 'fuqingshi', 'fqs', '350100', '350181', null, null);
INSERT INTO `sys_area` VALUES ('1254', '厦门市', 'xiamenshi', 'xms', '350000', '350200', null, null);
INSERT INTO `sys_area` VALUES ('1255', '思明区', 'simingqu', 'smq', '350200', '350203', null, null);
INSERT INTO `sys_area` VALUES ('1256', '海沧区', 'haicangqu', 'hcq', '350200', '350205', null, null);
INSERT INTO `sys_area` VALUES ('1257', '湖里区', 'huliqu', 'hlq', '350200', '350206', null, null);
INSERT INTO `sys_area` VALUES ('1258', '集美区', 'jimeiqu', 'jmq', '350200', '350211', null, null);
INSERT INTO `sys_area` VALUES ('1259', '同安区', 'tonganqu', 'taq', '350200', '350212', null, null);
INSERT INTO `sys_area` VALUES ('1260', '翔安区', 'xianganqu', 'xaq', '350200', '350213', null, null);
INSERT INTO `sys_area` VALUES ('1261', '莆田市', 'putianshi', 'pts', '350000', '350300', null, null);
INSERT INTO `sys_area` VALUES ('1262', '城厢区', 'chengxiangqu', 'cxq', '350300', '350302', null, null);
INSERT INTO `sys_area` VALUES ('1263', '涵江区', 'hanjiangqu', 'hjq', '350300', '350303', null, null);
INSERT INTO `sys_area` VALUES ('1264', '荔城区', 'lichengqu', 'lcq', '350300', '350304', null, null);
INSERT INTO `sys_area` VALUES ('1265', '秀屿区', 'xiuyuqu', 'xyq', '350300', '350305', null, null);
INSERT INTO `sys_area` VALUES ('1266', '仙游县', 'xianyouxian', 'xyx', '350300', '350322', null, null);
INSERT INTO `sys_area` VALUES ('1267', '三明市', 'sanmingshi', 'sms', '350000', '350400', null, null);
INSERT INTO `sys_area` VALUES ('1268', '梅列区', 'meiliequ', 'mlq', '350400', '350402', null, null);
INSERT INTO `sys_area` VALUES ('1269', '三元区', 'sanyuanqu', 'syq', '350400', '350403', null, null);
INSERT INTO `sys_area` VALUES ('1270', '明溪县', 'mingxixian', 'mxx', '350400', '350421', null, null);
INSERT INTO `sys_area` VALUES ('1271', '清流县', 'qingliuxian', 'qlx', '350400', '350423', null, null);
INSERT INTO `sys_area` VALUES ('1272', '宁化县', 'ninghuaxian', 'nhx', '350400', '350424', null, null);
INSERT INTO `sys_area` VALUES ('1273', '大田县', 'datianxian', 'dtx', '350400', '350425', null, null);
INSERT INTO `sys_area` VALUES ('1274', '尤溪县', 'youxixian', 'yxx', '350400', '350426', null, null);
INSERT INTO `sys_area` VALUES ('1275', '沙县', 'shaxian', 'sx', '350400', '350427', null, null);
INSERT INTO `sys_area` VALUES ('1276', '将乐县', 'jianglexian', 'jlx', '350400', '350428', null, null);
INSERT INTO `sys_area` VALUES ('1277', '泰宁县', 'tainingxian', 'tnx', '350400', '350429', null, null);
INSERT INTO `sys_area` VALUES ('1278', '建宁县', 'jianningxian', 'jnx', '350400', '350430', null, null);
INSERT INTO `sys_area` VALUES ('1279', '永安市', 'yonganshi', 'yas', '350400', '350481', null, null);
INSERT INTO `sys_area` VALUES ('1280', '泉州市', 'quanzhoushi', 'qzs', '350000', '350500', null, null);
INSERT INTO `sys_area` VALUES ('1281', '鲤城区', 'lichengqu', 'lcq', '350500', '350502', null, null);
INSERT INTO `sys_area` VALUES ('1282', '丰泽区', 'fengzequ', 'fzq', '350500', '350503', null, null);
INSERT INTO `sys_area` VALUES ('1283', '洛江区', 'luojiangqu', 'ljq', '350500', '350504', null, null);
INSERT INTO `sys_area` VALUES ('1284', '泉港区', 'quangangqu', 'qgq', '350500', '350505', null, null);
INSERT INTO `sys_area` VALUES ('1285', '惠安县', 'huianxian', 'hax', '350500', '350521', null, null);
INSERT INTO `sys_area` VALUES ('1286', '安溪县', 'anxixian', 'axx', '350500', '350524', null, null);
INSERT INTO `sys_area` VALUES ('1287', '永春县', 'yongchunxian', 'ycx', '350500', '350525', null, null);
INSERT INTO `sys_area` VALUES ('1288', '德化县', 'dehuaxian', 'dhx', '350500', '350526', null, null);
INSERT INTO `sys_area` VALUES ('1289', '金门县', 'jinmenxian', 'jmx', '350500', '350527', null, null);
INSERT INTO `sys_area` VALUES ('1290', '石狮市', 'shishishi', 'sss', '350500', '350581', null, null);
INSERT INTO `sys_area` VALUES ('1291', '晋江市', 'jinjiangshi', 'jjs', '350500', '350582', null, null);
INSERT INTO `sys_area` VALUES ('1292', '南安市', 'nananshi', 'nas', '350500', '350583', null, null);
INSERT INTO `sys_area` VALUES ('1293', '漳州市', 'zhangzhoushi', 'zzs', '350000', '350600', null, null);
INSERT INTO `sys_area` VALUES ('1294', '芗城区', 'zuochengqu', '芗cq', '350600', '350602', null, null);
INSERT INTO `sys_area` VALUES ('1295', '龙文区', 'longwenqu', 'lwq', '350600', '350603', null, null);
INSERT INTO `sys_area` VALUES ('1296', '云霄县', 'yunxiaoxian', 'yxx', '350600', '350622', null, null);
INSERT INTO `sys_area` VALUES ('1297', '漳浦县', 'zhangpuxian', 'zpx', '350600', '350623', null, null);
INSERT INTO `sys_area` VALUES ('1298', '诏安县', 'zuoanxian', '诏ax', '350600', '350624', null, null);
INSERT INTO `sys_area` VALUES ('1299', '长泰县', 'changtaixian', 'ctx', '350600', '350625', null, null);
INSERT INTO `sys_area` VALUES ('1300', '东山县', 'dongshanxian', 'dsx', '350600', '350626', null, null);
INSERT INTO `sys_area` VALUES ('1301', '南靖县', 'nanjingxian', 'njx', '350600', '350627', null, null);
INSERT INTO `sys_area` VALUES ('1302', '平和县', 'pinghexian', 'phx', '350600', '350628', null, null);
INSERT INTO `sys_area` VALUES ('1303', '华安县', 'huaanxian', 'hax', '350600', '350629', null, null);
INSERT INTO `sys_area` VALUES ('1304', '龙海市', 'longhaishi', 'lhs', '350600', '350681', null, null);
INSERT INTO `sys_area` VALUES ('1305', '南平市', 'nanpingshi', 'nps', '350000', '350700', null, null);
INSERT INTO `sys_area` VALUES ('1306', '延平区', 'yanpingqu', 'ypq', '350700', '350702', null, null);
INSERT INTO `sys_area` VALUES ('1307', '建阳区', 'jianyangqu', 'jyq', '350700', '350703', null, null);
INSERT INTO `sys_area` VALUES ('1308', '顺昌县', 'shunchangxian', 'scx', '350700', '350721', null, null);
INSERT INTO `sys_area` VALUES ('1309', '浦城县', 'puchengxian', 'pcx', '350700', '350722', null, null);
INSERT INTO `sys_area` VALUES ('1310', '光泽县', 'guangzexian', 'gzx', '350700', '350723', null, null);
INSERT INTO `sys_area` VALUES ('1311', '松溪县', 'songxixian', 'sxx', '350700', '350724', null, null);
INSERT INTO `sys_area` VALUES ('1312', '政和县', 'zhenghexian', 'zhx', '350700', '350725', null, null);
INSERT INTO `sys_area` VALUES ('1313', '邵武市', 'shaowushi', 'sws', '350700', '350781', null, null);
INSERT INTO `sys_area` VALUES ('1314', '武夷山市', 'wuyishanshi', 'wyss', '350700', '350782', null, null);
INSERT INTO `sys_area` VALUES ('1315', '建瓯市', 'jianzuoshi', 'j瓯s', '350700', '350783', null, null);
INSERT INTO `sys_area` VALUES ('1316', '龙岩市', 'longyanshi', 'lys', '350000', '350800', null, null);
INSERT INTO `sys_area` VALUES ('1317', '新罗区', 'xinluoqu', 'xlq', '350800', '350802', null, null);
INSERT INTO `sys_area` VALUES ('1318', '永定区', 'yongdingqu', 'ydq', '350800', '350803', null, null);
INSERT INTO `sys_area` VALUES ('1319', '长汀县', 'changtingxian', 'ctx', '350800', '350821', null, null);
INSERT INTO `sys_area` VALUES ('1320', '上杭县', 'shanghangxian', 'shx', '350800', '350823', null, null);
INSERT INTO `sys_area` VALUES ('1321', '武平县', 'wupingxian', 'wpx', '350800', '350824', null, null);
INSERT INTO `sys_area` VALUES ('1322', '连城县', 'lianchengxian', 'lcx', '350800', '350825', null, null);
INSERT INTO `sys_area` VALUES ('1323', '漳平市', 'zhangpingshi', 'zps', '350800', '350881', null, null);
INSERT INTO `sys_area` VALUES ('1324', '宁德市', 'ningdeshi', 'nds', '350000', '350900', null, null);
INSERT INTO `sys_area` VALUES ('1325', '蕉城区', 'jiaochengqu', 'jcq', '350900', '350902', null, null);
INSERT INTO `sys_area` VALUES ('1326', '霞浦县', 'xiapuxian', 'xpx', '350900', '350921', null, null);
INSERT INTO `sys_area` VALUES ('1327', '古田县', 'gutianxian', 'gtx', '350900', '350922', null, null);
INSERT INTO `sys_area` VALUES ('1328', '屏南县', 'pingnanxian', 'pnx', '350900', '350923', null, null);
INSERT INTO `sys_area` VALUES ('1329', '寿宁县', 'shouningxian', 'snx', '350900', '350924', null, null);
INSERT INTO `sys_area` VALUES ('1330', '周宁县', 'zhouningxian', 'znx', '350900', '350925', null, null);
INSERT INTO `sys_area` VALUES ('1331', '柘荣县', 'zuorongxian', '柘rx', '350900', '350926', null, null);
INSERT INTO `sys_area` VALUES ('1332', '福安市', 'fuanshi', 'fas', '350900', '350981', null, null);
INSERT INTO `sys_area` VALUES ('1333', '福鼎市', 'fudingshi', 'fds', '350900', '350982', null, null);
INSERT INTO `sys_area` VALUES ('1334', '江西省', 'jiangxisheng', 'jxs', '0', '360000', null, null);
INSERT INTO `sys_area` VALUES ('1335', '南昌市', 'nanchangshi', 'ncs', '360000', '360100', null, null);
INSERT INTO `sys_area` VALUES ('1336', '东湖区', 'donghuqu', 'dhq', '360100', '360102', null, null);
INSERT INTO `sys_area` VALUES ('1337', '西湖区', 'xihuqu', 'xhq', '360100', '360103', null, null);
INSERT INTO `sys_area` VALUES ('1338', '青云谱区', 'qingyunpuqu', 'qypq', '360100', '360104', null, null);
INSERT INTO `sys_area` VALUES ('1339', '湾里区', 'wanliqu', 'wlq', '360100', '360105', null, null);
INSERT INTO `sys_area` VALUES ('1340', '青山湖区', 'qingshanhuqu', 'qshq', '360100', '360111', null, null);
INSERT INTO `sys_area` VALUES ('1341', '新建区', 'xinjianqu', 'xjq', '360100', '360112', null, null);
INSERT INTO `sys_area` VALUES ('1342', '南昌县', 'nanchangxian', 'ncx', '360100', '360121', null, null);
INSERT INTO `sys_area` VALUES ('1343', '安义县', 'anyixian', 'ayx', '360100', '360123', null, null);
INSERT INTO `sys_area` VALUES ('1344', '进贤县', 'jinxianxian', 'jxx', '360100', '360124', null, null);
INSERT INTO `sys_area` VALUES ('1345', '景德镇市', 'jingdezhenshi', 'jdzs', '360000', '360200', null, null);
INSERT INTO `sys_area` VALUES ('1346', '昌江区', 'changjiangqu', 'cjq', '360200', '360202', null, null);
INSERT INTO `sys_area` VALUES ('1347', '珠山区', 'zhushanqu', 'zsq', '360200', '360203', null, null);
INSERT INTO `sys_area` VALUES ('1348', '浮梁县', 'fuliangxian', 'flx', '360200', '360222', null, null);
INSERT INTO `sys_area` VALUES ('1349', '乐平市', 'lepingshi', 'lps', '360200', '360281', null, null);
INSERT INTO `sys_area` VALUES ('1350', '萍乡市', 'pingxiangshi', 'pxs', '360000', '360300', null, null);
INSERT INTO `sys_area` VALUES ('1351', '安源区', 'anyuanqu', 'ayq', '360300', '360302', null, null);
INSERT INTO `sys_area` VALUES ('1352', '湘东区', 'xiangdongqu', 'xdq', '360300', '360313', null, null);
INSERT INTO `sys_area` VALUES ('1353', '莲花县', 'lianhuaxian', 'lhx', '360300', '360321', null, null);
INSERT INTO `sys_area` VALUES ('1354', '上栗县', 'shanglixian', 'slx', '360300', '360322', null, null);
INSERT INTO `sys_area` VALUES ('1355', '芦溪县', 'luxixian', 'lxx', '360300', '360323', null, null);
INSERT INTO `sys_area` VALUES ('1356', '九江市', 'jiujiangshi', 'jjs', '360000', '360400', null, null);
INSERT INTO `sys_area` VALUES ('1357', '濂溪区', 'zuoxiqu', '濂xq', '360400', '360402', null, null);
INSERT INTO `sys_area` VALUES ('1358', '浔阳区', 'zuoyangqu', '浔yq', '360400', '360403', null, null);
INSERT INTO `sys_area` VALUES ('1359', '柴桑区', 'chaisangqu', 'csq', '360400', '360404', null, null);
INSERT INTO `sys_area` VALUES ('1360', '武宁县', 'wuningxian', 'wnx', '360400', '360423', null, null);
INSERT INTO `sys_area` VALUES ('1361', '修水县', 'xiushuixian', 'xsx', '360400', '360424', null, null);
INSERT INTO `sys_area` VALUES ('1362', '永修县', 'yongxiuxian', 'yxx', '360400', '360425', null, null);
INSERT INTO `sys_area` VALUES ('1363', '德安县', 'deanxian', 'dax', '360400', '360426', null, null);
INSERT INTO `sys_area` VALUES ('1364', '都昌县', 'duchangxian', 'dcx', '360400', '360428', null, null);
INSERT INTO `sys_area` VALUES ('1365', '湖口县', 'hukouxian', 'hkx', '360400', '360429', null, null);
INSERT INTO `sys_area` VALUES ('1366', '彭泽县', 'pengzexian', 'pzx', '360400', '360430', null, null);
INSERT INTO `sys_area` VALUES ('1367', '瑞昌市', 'ruichangshi', 'rcs', '360400', '360481', null, null);
INSERT INTO `sys_area` VALUES ('1368', '共青城市', 'gongqingchengshi', 'gqcs', '360400', '360482', null, null);
INSERT INTO `sys_area` VALUES ('1369', '庐山市', 'lushanshi', 'lss', '360400', '360483', null, null);
INSERT INTO `sys_area` VALUES ('1370', '新余市', 'xinyushi', 'xys', '360000', '360500', null, null);
INSERT INTO `sys_area` VALUES ('1371', '渝水区', 'yushuiqu', 'ysq', '360500', '360502', null, null);
INSERT INTO `sys_area` VALUES ('1372', '分宜县', 'fenyixian', 'fyx', '360500', '360521', null, null);
INSERT INTO `sys_area` VALUES ('1373', '鹰潭市', 'yingtanshi', 'yts', '360000', '360600', null, null);
INSERT INTO `sys_area` VALUES ('1374', '月湖区', 'yuehuqu', 'yhq', '360600', '360602', null, null);
INSERT INTO `sys_area` VALUES ('1375', '余江区', 'yujiangqu', 'yjq', '360600', '360603', null, null);
INSERT INTO `sys_area` VALUES ('1376', '贵溪市', 'guixishi', 'gxs', '360600', '360681', null, null);
INSERT INTO `sys_area` VALUES ('1377', '赣州市', 'ganzhoushi', 'gzs', '360000', '360700', null, null);
INSERT INTO `sys_area` VALUES ('1378', '章贡区', 'zhanggongqu', 'zgq', '360700', '360702', null, null);
INSERT INTO `sys_area` VALUES ('1379', '南康区', 'nankangqu', 'nkq', '360700', '360703', null, null);
INSERT INTO `sys_area` VALUES ('1380', '赣县区', 'ganxianqu', 'gxq', '360700', '360704', null, null);
INSERT INTO `sys_area` VALUES ('1381', '信丰县', 'xinfengxian', 'xfx', '360700', '360722', null, null);
INSERT INTO `sys_area` VALUES ('1382', '大余县', 'dayuxian', 'dyx', '360700', '360723', null, null);
INSERT INTO `sys_area` VALUES ('1383', '上犹县', 'shangyouxian', 'syx', '360700', '360724', null, null);
INSERT INTO `sys_area` VALUES ('1384', '崇义县', 'chongyixian', 'cyx', '360700', '360725', null, null);
INSERT INTO `sys_area` VALUES ('1385', '安远县', 'anyuanxian', 'ayx', '360700', '360726', null, null);
INSERT INTO `sys_area` VALUES ('1386', '龙南县', 'longnanxian', 'lnx', '360700', '360727', null, null);
INSERT INTO `sys_area` VALUES ('1387', '定南县', 'dingnanxian', 'dnx', '360700', '360728', null, null);
INSERT INTO `sys_area` VALUES ('1388', '全南县', 'quannanxian', 'qnx', '360700', '360729', null, null);
INSERT INTO `sys_area` VALUES ('1389', '宁都县', 'ningduxian', 'ndx', '360700', '360730', null, null);
INSERT INTO `sys_area` VALUES ('1390', '于都县', 'yuduxian', 'ydx', '360700', '360731', null, null);
INSERT INTO `sys_area` VALUES ('1391', '兴国县', 'xingguoxian', 'xgx', '360700', '360732', null, null);
INSERT INTO `sys_area` VALUES ('1392', '会昌县', 'huichangxian', 'hcx', '360700', '360733', null, null);
INSERT INTO `sys_area` VALUES ('1393', '寻乌县', 'xunwuxian', 'xwx', '360700', '360734', null, null);
INSERT INTO `sys_area` VALUES ('1394', '石城县', 'shichengxian', 'scx', '360700', '360735', null, null);
INSERT INTO `sys_area` VALUES ('1395', '瑞金市', 'ruijinshi', 'rjs', '360700', '360781', null, null);
INSERT INTO `sys_area` VALUES ('1396', '吉安市', 'jianshi', 'jas', '360000', '360800', null, null);
INSERT INTO `sys_area` VALUES ('1397', '吉州区', 'jizhouqu', 'jzq', '360800', '360802', null, null);
INSERT INTO `sys_area` VALUES ('1398', '青原区', 'qingyuanqu', 'qyq', '360800', '360803', null, null);
INSERT INTO `sys_area` VALUES ('1399', '吉安县', 'jianxian', 'jax', '360800', '360821', null, null);
INSERT INTO `sys_area` VALUES ('1400', '吉水县', 'jishuixian', 'jsx', '360800', '360822', null, null);
INSERT INTO `sys_area` VALUES ('1401', '峡江县', 'xiajiangxian', 'xjx', '360800', '360823', null, null);
INSERT INTO `sys_area` VALUES ('1402', '新干县', 'xinganxian', 'xgx', '360800', '360824', null, null);
INSERT INTO `sys_area` VALUES ('1403', '永丰县', 'yongfengxian', 'yfx', '360800', '360825', null, null);
INSERT INTO `sys_area` VALUES ('1404', '泰和县', 'taihexian', 'thx', '360800', '360826', null, null);
INSERT INTO `sys_area` VALUES ('1405', '遂川县', 'suichuanxian', 'scx', '360800', '360827', null, null);
INSERT INTO `sys_area` VALUES ('1406', '万安县', 'wananxian', 'wax', '360800', '360828', null, null);
INSERT INTO `sys_area` VALUES ('1407', '安福县', 'anfuxian', 'afx', '360800', '360829', null, null);
INSERT INTO `sys_area` VALUES ('1408', '永新县', 'yongxinxian', 'yxx', '360800', '360830', null, null);
INSERT INTO `sys_area` VALUES ('1409', '井冈山市', 'jinggangshanshi', 'jgss', '360800', '360881', null, null);
INSERT INTO `sys_area` VALUES ('1410', '宜春市', 'yichunshi', 'ycs', '360000', '360900', null, null);
INSERT INTO `sys_area` VALUES ('1411', '袁州区', 'yuanzhouqu', 'yzq', '360900', '360902', null, null);
INSERT INTO `sys_area` VALUES ('1412', '奉新县', 'fengxinxian', 'fxx', '360900', '360921', null, null);
INSERT INTO `sys_area` VALUES ('1413', '万载县', 'wanzaixian', 'wzx', '360900', '360922', null, null);
INSERT INTO `sys_area` VALUES ('1414', '上高县', 'shanggaoxian', 'sgx', '360900', '360923', null, null);
INSERT INTO `sys_area` VALUES ('1415', '宜丰县', 'yifengxian', 'yfx', '360900', '360924', null, null);
INSERT INTO `sys_area` VALUES ('1416', '靖安县', 'jinganxian', 'jax', '360900', '360925', null, null);
INSERT INTO `sys_area` VALUES ('1417', '铜鼓县', 'tongguxian', 'tgx', '360900', '360926', null, null);
INSERT INTO `sys_area` VALUES ('1418', '丰城市', 'fengchengshi', 'fcs', '360900', '360981', null, null);
INSERT INTO `sys_area` VALUES ('1419', '樟树市', 'zhangshushi', 'zss', '360900', '360982', null, null);
INSERT INTO `sys_area` VALUES ('1420', '高安市', 'gaoanshi', 'gas', '360900', '360983', null, null);
INSERT INTO `sys_area` VALUES ('1421', '抚州市', 'fuzhoushi', 'fzs', '360000', '361000', null, null);
INSERT INTO `sys_area` VALUES ('1422', '临川区', 'linchuanqu', 'lcq', '361000', '361002', null, null);
INSERT INTO `sys_area` VALUES ('1423', '东乡区', 'dongxiangqu', 'dxq', '361000', '361003', null, null);
INSERT INTO `sys_area` VALUES ('1424', '南城县', 'nanchengxian', 'ncx', '361000', '361021', null, null);
INSERT INTO `sys_area` VALUES ('1425', '黎川县', 'lichuanxian', 'lcx', '361000', '361022', null, null);
INSERT INTO `sys_area` VALUES ('1426', '南丰县', 'nanfengxian', 'nfx', '361000', '361023', null, null);
INSERT INTO `sys_area` VALUES ('1427', '崇仁县', 'chongrenxian', 'crx', '361000', '361024', null, null);
INSERT INTO `sys_area` VALUES ('1428', '乐安县', 'leanxian', 'lax', '361000', '361025', null, null);
INSERT INTO `sys_area` VALUES ('1429', '宜黄县', 'yihuangxian', 'yhx', '361000', '361026', null, null);
INSERT INTO `sys_area` VALUES ('1430', '金溪县', 'jinxixian', 'jxx', '361000', '361027', null, null);
INSERT INTO `sys_area` VALUES ('1431', '资溪县', 'zixixian', 'zxx', '361000', '361028', null, null);
INSERT INTO `sys_area` VALUES ('1432', '广昌县', 'guangchangxian', 'gcx', '361000', '361030', null, null);
INSERT INTO `sys_area` VALUES ('1433', '上饶市', 'shangraoshi', 'srs', '360000', '361100', null, null);
INSERT INTO `sys_area` VALUES ('1434', '信州区', 'xinzhouqu', 'xzq', '361100', '361102', null, null);
INSERT INTO `sys_area` VALUES ('1435', '广丰区', 'guangfengqu', 'gfq', '361100', '361103', null, null);
INSERT INTO `sys_area` VALUES ('1436', '上饶县', 'shangraoxian', 'srx', '361100', '361121', null, null);
INSERT INTO `sys_area` VALUES ('1437', '玉山县', 'yushanxian', 'ysx', '361100', '361123', null, null);
INSERT INTO `sys_area` VALUES ('1438', '铅山县', 'qianshanxian', 'qsx', '361100', '361124', null, null);
INSERT INTO `sys_area` VALUES ('1439', '横峰县', 'hengfengxian', 'hfx', '361100', '361125', null, null);
INSERT INTO `sys_area` VALUES ('1440', '弋阳县', 'zuoyangxian', '弋yx', '361100', '361126', null, null);
INSERT INTO `sys_area` VALUES ('1441', '余干县', 'yuganxian', 'ygx', '361100', '361127', null, null);
INSERT INTO `sys_area` VALUES ('1442', '鄱阳县', 'zuoyangxian', '鄱yx', '361100', '361128', null, null);
INSERT INTO `sys_area` VALUES ('1443', '万年县', 'wannianxian', 'wnx', '361100', '361129', null, null);
INSERT INTO `sys_area` VALUES ('1444', '婺源县', 'zuoyuanxian', '婺yx', '361100', '361130', null, null);
INSERT INTO `sys_area` VALUES ('1445', '德兴市', 'dexingshi', 'dxs', '361100', '361181', null, null);
INSERT INTO `sys_area` VALUES ('1446', '山东省', 'shandongsheng', 'sds', '0', '370000', null, null);
INSERT INTO `sys_area` VALUES ('1447', '济南市', 'jinanshi', 'jns', '370000', '370100', null, null);
INSERT INTO `sys_area` VALUES ('1448', '历下区', 'lixiaqu', 'lxq', '370100', '370102', null, null);
INSERT INTO `sys_area` VALUES ('1449', '市中区', 'shizhongqu', 'szq', '370100', '370103', null, null);
INSERT INTO `sys_area` VALUES ('1450', '槐荫区', 'huaiyinqu', 'hyq', '370100', '370104', null, null);
INSERT INTO `sys_area` VALUES ('1451', '天桥区', 'tianqiaoqu', 'tqq', '370100', '370105', null, null);
INSERT INTO `sys_area` VALUES ('1452', '历城区', 'lichengqu', 'lcq', '370100', '370112', null, null);
INSERT INTO `sys_area` VALUES ('1453', '长清区', 'changqingqu', 'cqq', '370100', '370113', null, null);
INSERT INTO `sys_area` VALUES ('1454', '章丘区', 'zhangqiuqu', 'zqq', '370100', '370114', null, null);
INSERT INTO `sys_area` VALUES ('1455', '平阴县', 'pingyinxian', 'pyx', '370100', '370124', null, null);
INSERT INTO `sys_area` VALUES ('1456', '济阳县', 'jiyangxian', 'jyx', '370100', '370125', null, null);
INSERT INTO `sys_area` VALUES ('1457', '商河县', 'shanghexian', 'shx', '370100', '370126', null, null);
INSERT INTO `sys_area` VALUES ('1458', '青岛市', 'qingdaoshi', 'qds', '370000', '370200', null, null);
INSERT INTO `sys_area` VALUES ('1459', '市南区', 'shinanqu', 'snq', '370200', '370202', null, null);
INSERT INTO `sys_area` VALUES ('1460', '市北区', 'shibeiqu', 'sbq', '370200', '370203', null, null);
INSERT INTO `sys_area` VALUES ('1461', '黄岛区', 'huangdaoqu', 'hdq', '370200', '370211', null, null);
INSERT INTO `sys_area` VALUES ('1462', '崂山区', 'zuoshanqu', '崂sq', '370200', '370212', null, null);
INSERT INTO `sys_area` VALUES ('1463', '李沧区', 'licangqu', 'lcq', '370200', '370213', null, null);
INSERT INTO `sys_area` VALUES ('1464', '城阳区', 'chengyangqu', 'cyq', '370200', '370214', null, null);
INSERT INTO `sys_area` VALUES ('1465', '即墨区', 'jimoqu', 'jmq', '370200', '370215', null, null);
INSERT INTO `sys_area` VALUES ('1466', '胶州市', 'jiaozhoushi', 'jzs', '370200', '370281', null, null);
INSERT INTO `sys_area` VALUES ('1467', '平度市', 'pingdushi', 'pds', '370200', '370283', null, null);
INSERT INTO `sys_area` VALUES ('1468', '莱西市', 'laixishi', 'lxs', '370200', '370285', null, null);
INSERT INTO `sys_area` VALUES ('1469', '淄博市', 'ziboshi', 'zbs', '370000', '370300', null, null);
INSERT INTO `sys_area` VALUES ('1470', '淄川区', 'zichuanqu', 'zcq', '370300', '370302', null, null);
INSERT INTO `sys_area` VALUES ('1471', '张店区', 'zhangdianqu', 'zdq', '370300', '370303', null, null);
INSERT INTO `sys_area` VALUES ('1472', '博山区', 'boshanqu', 'bsq', '370300', '370304', null, null);
INSERT INTO `sys_area` VALUES ('1473', '临淄区', 'linziqu', 'lzq', '370300', '370305', null, null);
INSERT INTO `sys_area` VALUES ('1474', '周村区', 'zhoucunqu', 'zcq', '370300', '370306', null, null);
INSERT INTO `sys_area` VALUES ('1475', '桓台县', 'huantaixian', 'htx', '370300', '370321', null, null);
INSERT INTO `sys_area` VALUES ('1476', '高青县', 'gaoqingxian', 'gqx', '370300', '370322', null, null);
INSERT INTO `sys_area` VALUES ('1477', '沂源县', 'yiyuanxian', 'yyx', '370300', '370323', null, null);
INSERT INTO `sys_area` VALUES ('1478', '枣庄市', 'zaozhuangshi', 'zzs', '370000', '370400', null, null);
INSERT INTO `sys_area` VALUES ('1479', '市中区', 'shizhongqu', 'szq', '370400', '370402', null, null);
INSERT INTO `sys_area` VALUES ('1480', '薛城区', 'xuechengqu', 'xcq', '370400', '370403', null, null);
INSERT INTO `sys_area` VALUES ('1481', '峄城区', 'zuochengqu', '峄cq', '370400', '370404', null, null);
INSERT INTO `sys_area` VALUES ('1482', '台儿庄区', 'taierzhuangqu', 'tezq', '370400', '370405', null, null);
INSERT INTO `sys_area` VALUES ('1483', '山亭区', 'shantingqu', 'stq', '370400', '370406', null, null);
INSERT INTO `sys_area` VALUES ('1484', '滕州市', 'zuozhoushi', '滕zs', '370400', '370481', null, null);
INSERT INTO `sys_area` VALUES ('1485', '东营市', 'dongyingshi', 'dys', '370000', '370500', null, null);
INSERT INTO `sys_area` VALUES ('1486', '东营区', 'dongyingqu', 'dyq', '370500', '370502', null, null);
INSERT INTO `sys_area` VALUES ('1487', '河口区', 'hekouqu', 'hkq', '370500', '370503', null, null);
INSERT INTO `sys_area` VALUES ('1488', '垦利区', 'kenliqu', 'klq', '370500', '370505', null, null);
INSERT INTO `sys_area` VALUES ('1489', '利津县', 'lijinxian', 'ljx', '370500', '370522', null, null);
INSERT INTO `sys_area` VALUES ('1490', '广饶县', 'guangraoxian', 'grx', '370500', '370523', null, null);
INSERT INTO `sys_area` VALUES ('1491', '烟台市', 'yantaishi', 'yts', '370000', '370600', null, null);
INSERT INTO `sys_area` VALUES ('1492', '芝罘区', 'zhizuoqu', 'z罘q', '370600', '370602', null, null);
INSERT INTO `sys_area` VALUES ('1493', '福山区', 'fushanqu', 'fsq', '370600', '370611', null, null);
INSERT INTO `sys_area` VALUES ('1494', '牟平区', 'moupingqu', 'mpq', '370600', '370612', null, null);
INSERT INTO `sys_area` VALUES ('1495', '莱山区', 'laishanqu', 'lsq', '370600', '370613', null, null);
INSERT INTO `sys_area` VALUES ('1496', '长岛县', 'changdaoxian', 'cdx', '370600', '370634', null, null);
INSERT INTO `sys_area` VALUES ('1497', '龙口市', 'longkoushi', 'lks', '370600', '370681', null, null);
INSERT INTO `sys_area` VALUES ('1498', '莱阳市', 'laiyangshi', 'lys', '370600', '370682', null, null);
INSERT INTO `sys_area` VALUES ('1499', '莱州市', 'laizhoushi', 'lzs', '370600', '370683', null, null);
INSERT INTO `sys_area` VALUES ('1500', '蓬莱市', 'penglaishi', 'pls', '370600', '370684', null, null);
INSERT INTO `sys_area` VALUES ('1501', '招远市', 'zhaoyuanshi', 'zys', '370600', '370685', null, null);
INSERT INTO `sys_area` VALUES ('1502', '栖霞市', 'qixiashi', 'qxs', '370600', '370686', null, null);
INSERT INTO `sys_area` VALUES ('1503', '海阳市', 'haiyangshi', 'hys', '370600', '370687', null, null);
INSERT INTO `sys_area` VALUES ('1504', '潍坊市', 'weifangshi', 'wfs', '370000', '370700', null, null);
INSERT INTO `sys_area` VALUES ('1505', '潍城区', 'weichengqu', 'wcq', '370700', '370702', null, null);
INSERT INTO `sys_area` VALUES ('1506', '寒亭区', 'hantingqu', 'htq', '370700', '370703', null, null);
INSERT INTO `sys_area` VALUES ('1507', '坊子区', 'fangziqu', 'fzq', '370700', '370704', null, null);
INSERT INTO `sys_area` VALUES ('1508', '奎文区', 'kuiwenqu', 'kwq', '370700', '370705', null, null);
INSERT INTO `sys_area` VALUES ('1509', '临朐县', 'linzuoxian', 'l朐x', '370700', '370724', null, null);
INSERT INTO `sys_area` VALUES ('1510', '昌乐县', 'changlexian', 'clx', '370700', '370725', null, null);
INSERT INTO `sys_area` VALUES ('1511', '青州市', 'qingzhoushi', 'qzs', '370700', '370781', null, null);
INSERT INTO `sys_area` VALUES ('1512', '诸城市', 'zhuchengshi', 'zcs', '370700', '370782', null, null);
INSERT INTO `sys_area` VALUES ('1513', '寿光市', 'shouguangshi', 'sgs', '370700', '370783', null, null);
INSERT INTO `sys_area` VALUES ('1514', '安丘市', 'anqiushi', 'aqs', '370700', '370784', null, null);
INSERT INTO `sys_area` VALUES ('1515', '高密市', 'gaomishi', 'gms', '370700', '370785', null, null);
INSERT INTO `sys_area` VALUES ('1516', '昌邑市', 'changyishi', 'cys', '370700', '370786', null, null);
INSERT INTO `sys_area` VALUES ('1517', '济宁市', 'jiningshi', 'jns', '370000', '370800', null, null);
INSERT INTO `sys_area` VALUES ('1518', '任城区', 'renchengqu', 'rcq', '370800', '370811', null, null);
INSERT INTO `sys_area` VALUES ('1519', '兖州区', 'zuozhouqu', '兖zq', '370800', '370812', null, null);
INSERT INTO `sys_area` VALUES ('1520', '微山县', 'weishanxian', 'wsx', '370800', '370826', null, null);
INSERT INTO `sys_area` VALUES ('1521', '鱼台县', 'yutaixian', 'ytx', '370800', '370827', null, null);
INSERT INTO `sys_area` VALUES ('1522', '金乡县', 'jinxiangxian', 'jxx', '370800', '370828', null, null);
INSERT INTO `sys_area` VALUES ('1523', '嘉祥县', 'jiaxiangxian', 'jxx', '370800', '370829', null, null);
INSERT INTO `sys_area` VALUES ('1524', '汶上县', 'zuoshangxian', '汶sx', '370800', '370830', null, null);
INSERT INTO `sys_area` VALUES ('1525', '泗水县', 'zuoshuixian', '泗sx', '370800', '370831', null, null);
INSERT INTO `sys_area` VALUES ('1526', '梁山县', 'liangshanxian', 'lsx', '370800', '370832', null, null);
INSERT INTO `sys_area` VALUES ('1527', '曲阜市', 'qufushi', 'qfs', '370800', '370881', null, null);
INSERT INTO `sys_area` VALUES ('1528', '邹城市', 'zouchengshi', 'zcs', '370800', '370883', null, null);
INSERT INTO `sys_area` VALUES ('1529', '泰安市', 'taianshi', 'tas', '370000', '370900', null, null);
INSERT INTO `sys_area` VALUES ('1530', '泰山区', 'taishanqu', 'tsq', '370900', '370902', null, null);
INSERT INTO `sys_area` VALUES ('1531', '岱岳区', 'zuoyuequ', '岱yq', '370900', '370911', null, null);
INSERT INTO `sys_area` VALUES ('1532', '宁阳县', 'ningyangxian', 'nyx', '370900', '370921', null, null);
INSERT INTO `sys_area` VALUES ('1533', '东平县', 'dongpingxian', 'dpx', '370900', '370923', null, null);
INSERT INTO `sys_area` VALUES ('1534', '新泰市', 'xintaishi', 'xts', '370900', '370982', null, null);
INSERT INTO `sys_area` VALUES ('1535', '肥城市', 'feichengshi', 'fcs', '370900', '370983', null, null);
INSERT INTO `sys_area` VALUES ('1536', '威海市', 'weihaishi', 'whs', '370000', '371000', null, null);
INSERT INTO `sys_area` VALUES ('1537', '环翠区', 'huancuiqu', 'hcq', '371000', '371002', null, null);
INSERT INTO `sys_area` VALUES ('1538', '文登区', 'wendengqu', 'wdq', '371000', '371003', null, null);
INSERT INTO `sys_area` VALUES ('1539', '荣成市', 'rongchengshi', 'rcs', '371000', '371082', null, null);
INSERT INTO `sys_area` VALUES ('1540', '乳山市', 'rushanshi', 'rss', '371000', '371083', null, null);
INSERT INTO `sys_area` VALUES ('1541', '日照市', 'rizhaoshi', 'rzs', '370000', '371100', null, null);
INSERT INTO `sys_area` VALUES ('1542', '东港区', 'donggangqu', 'dgq', '371100', '371102', null, null);
INSERT INTO `sys_area` VALUES ('1543', '岚山区', 'zuoshanqu', '岚sq', '371100', '371103', null, null);
INSERT INTO `sys_area` VALUES ('1544', '五莲县', 'wulianxian', 'wlx', '371100', '371121', null, null);
INSERT INTO `sys_area` VALUES ('1545', '莒县', 'zuoxian', '莒x', '371100', '371122', null, null);
INSERT INTO `sys_area` VALUES ('1546', '莱芜市', 'laiwushi', 'lws', '370000', '371200', null, null);
INSERT INTO `sys_area` VALUES ('1547', '莱城区', 'laichengqu', 'lcq', '371200', '371202', null, null);
INSERT INTO `sys_area` VALUES ('1548', '钢城区', 'gangchengqu', 'gcq', '371200', '371203', null, null);
INSERT INTO `sys_area` VALUES ('1549', '临沂市', 'linyishi', 'lys', '370000', '371300', null, null);
INSERT INTO `sys_area` VALUES ('1550', '兰山区', 'lanshanqu', 'lsq', '371300', '371302', null, null);
INSERT INTO `sys_area` VALUES ('1551', '罗庄区', 'luozhuangqu', 'lzq', '371300', '371311', null, null);
INSERT INTO `sys_area` VALUES ('1552', '河东区', 'hedongqu', 'hdq', '371300', '371312', null, null);
INSERT INTO `sys_area` VALUES ('1553', '沂南县', 'yinanxian', 'ynx', '371300', '371321', null, null);
INSERT INTO `sys_area` VALUES ('1554', '郯城县', 'zuochengxian', '郯cx', '371300', '371322', null, null);
INSERT INTO `sys_area` VALUES ('1555', '沂水县', 'yishuixian', 'ysx', '371300', '371323', null, null);
INSERT INTO `sys_area` VALUES ('1556', '兰陵县', 'lanlingxian', 'llx', '371300', '371324', null, null);
INSERT INTO `sys_area` VALUES ('1557', '费县', 'feixian', 'fx', '371300', '371325', null, null);
INSERT INTO `sys_area` VALUES ('1558', '平邑县', 'pingyixian', 'pyx', '371300', '371326', null, null);
INSERT INTO `sys_area` VALUES ('1559', '莒南县', 'zuonanxian', '莒nx', '371300', '371327', null, null);
INSERT INTO `sys_area` VALUES ('1560', '蒙阴县', 'mengyinxian', 'myx', '371300', '371328', null, null);
INSERT INTO `sys_area` VALUES ('1561', '临沭县', 'linzuoxian', 'l沭x', '371300', '371329', null, null);
INSERT INTO `sys_area` VALUES ('1562', '德州市', 'dezhoushi', 'dzs', '370000', '371400', null, null);
INSERT INTO `sys_area` VALUES ('1563', '德城区', 'dechengqu', 'dcq', '371400', '371402', null, null);
INSERT INTO `sys_area` VALUES ('1564', '陵城区', 'lingchengqu', 'lcq', '371400', '371403', null, null);
INSERT INTO `sys_area` VALUES ('1565', '宁津县', 'ningjinxian', 'njx', '371400', '371422', null, null);
INSERT INTO `sys_area` VALUES ('1566', '庆云县', 'qingyunxian', 'qyx', '371400', '371423', null, null);
INSERT INTO `sys_area` VALUES ('1567', '临邑县', 'linyixian', 'lyx', '371400', '371424', null, null);
INSERT INTO `sys_area` VALUES ('1568', '齐河县', 'qihexian', 'qhx', '371400', '371425', null, null);
INSERT INTO `sys_area` VALUES ('1569', '平原县', 'pingyuanxian', 'pyx', '371400', '371426', null, null);
INSERT INTO `sys_area` VALUES ('1570', '夏津县', 'xiajinxian', 'xjx', '371400', '371427', null, null);
INSERT INTO `sys_area` VALUES ('1571', '武城县', 'wuchengxian', 'wcx', '371400', '371428', null, null);
INSERT INTO `sys_area` VALUES ('1572', '乐陵市', 'lelingshi', 'lls', '371400', '371481', null, null);
INSERT INTO `sys_area` VALUES ('1573', '禹城市', 'yuchengshi', 'ycs', '371400', '371482', null, null);
INSERT INTO `sys_area` VALUES ('1574', '聊城市', 'liaochengshi', 'lcs', '370000', '371500', null, null);
INSERT INTO `sys_area` VALUES ('1575', '东昌府区', 'dongchangfuqu', 'dcfq', '371500', '371502', null, null);
INSERT INTO `sys_area` VALUES ('1576', '阳谷县', 'yangguxian', 'ygx', '371500', '371521', null, null);
INSERT INTO `sys_area` VALUES ('1577', '莘县', 'zuoxian', '莘x', '371500', '371522', null, null);
INSERT INTO `sys_area` VALUES ('1578', '茌平县', 'zuopingxian', '茌px', '371500', '371523', null, null);
INSERT INTO `sys_area` VALUES ('1579', '东阿县', 'dongaxian', 'dax', '371500', '371524', null, null);
INSERT INTO `sys_area` VALUES ('1580', '冠县', 'guanxian', 'gx', '371500', '371525', null, null);
INSERT INTO `sys_area` VALUES ('1581', '高唐县', 'gaotangxian', 'gtx', '371500', '371526', null, null);
INSERT INTO `sys_area` VALUES ('1582', '临清市', 'linqingshi', 'lqs', '371500', '371581', null, null);
INSERT INTO `sys_area` VALUES ('1583', '滨州市', 'binzhoushi', 'bzs', '370000', '371600', null, null);
INSERT INTO `sys_area` VALUES ('1584', '滨城区', 'binchengqu', 'bcq', '371600', '371602', null, null);
INSERT INTO `sys_area` VALUES ('1585', '沾化区', 'zhanhuaqu', 'zhq', '371600', '371603', null, null);
INSERT INTO `sys_area` VALUES ('1586', '惠民县', 'huiminxian', 'hmx', '371600', '371621', null, null);
INSERT INTO `sys_area` VALUES ('1587', '阳信县', 'yangxinxian', 'yxx', '371600', '371622', null, null);
INSERT INTO `sys_area` VALUES ('1588', '无棣县', 'wuzuoxian', 'w棣x', '371600', '371623', null, null);
INSERT INTO `sys_area` VALUES ('1589', '博兴县', 'boxingxian', 'bxx', '371600', '371625', null, null);
INSERT INTO `sys_area` VALUES ('1590', '邹平县', 'zoupingxian', 'zpx', '371600', '371626', null, null);
INSERT INTO `sys_area` VALUES ('1591', '菏泽市', 'hezeshi', 'hzs', '370000', '371700', null, null);
INSERT INTO `sys_area` VALUES ('1592', '牡丹区', 'mudanqu', 'mdq', '371700', '371702', null, null);
INSERT INTO `sys_area` VALUES ('1593', '定陶区', 'dingtaoqu', 'dtq', '371700', '371703', null, null);
INSERT INTO `sys_area` VALUES ('1594', '曹县', 'caoxian', 'cx', '371700', '371721', null, null);
INSERT INTO `sys_area` VALUES ('1595', '单县', 'danxian', 'dx', '371700', '371722', null, null);
INSERT INTO `sys_area` VALUES ('1596', '成武县', 'chengwuxian', 'cwx', '371700', '371723', null, null);
INSERT INTO `sys_area` VALUES ('1597', '巨野县', 'juyexian', 'jyx', '371700', '371724', null, null);
INSERT INTO `sys_area` VALUES ('1598', '郓城县', 'zuochengxian', '郓cx', '371700', '371725', null, null);
INSERT INTO `sys_area` VALUES ('1599', '鄄城县', 'zuochengxian', '鄄cx', '371700', '371726', null, null);
INSERT INTO `sys_area` VALUES ('1600', '东明县', 'dongmingxian', 'dmx', '371700', '371728', null, null);
INSERT INTO `sys_area` VALUES ('1601', '河南省', 'henansheng', 'hns', '0', '410000', null, null);
INSERT INTO `sys_area` VALUES ('1602', '郑州市', 'zhengzhoushi', 'zzs', '410000', '410100', null, null);
INSERT INTO `sys_area` VALUES ('1603', '中原区', 'zhongyuanqu', 'zyq', '410100', '410102', null, null);
INSERT INTO `sys_area` VALUES ('1604', '二七区', 'erqiqu', 'eqq', '410100', '410103', null, null);
INSERT INTO `sys_area` VALUES ('1605', '管城回族区', 'guanchenghuizuqu', 'gchzq', '410100', '410104', null, null);
INSERT INTO `sys_area` VALUES ('1606', '金水区', 'jinshuiqu', 'jsq', '410100', '410105', null, null);
INSERT INTO `sys_area` VALUES ('1607', '上街区', 'shangjiequ', 'sjq', '410100', '410106', null, null);
INSERT INTO `sys_area` VALUES ('1608', '惠济区', 'huijiqu', 'hjq', '410100', '410108', null, null);
INSERT INTO `sys_area` VALUES ('1609', '中牟县', 'zhongmouxian', 'zmx', '410100', '410122', null, null);
INSERT INTO `sys_area` VALUES ('1610', '巩义市', 'gongyishi', 'gys', '410100', '410181', null, null);
INSERT INTO `sys_area` VALUES ('1611', '荥阳市', 'zuoyangshi', '荥ys', '410100', '410182', null, null);
INSERT INTO `sys_area` VALUES ('1612', '新密市', 'xinmishi', 'xms', '410100', '410183', null, null);
INSERT INTO `sys_area` VALUES ('1613', '新郑市', 'xinzhengshi', 'xzs', '410100', '410184', null, null);
INSERT INTO `sys_area` VALUES ('1614', '登封市', 'dengfengshi', 'dfs', '410100', '410185', null, null);
INSERT INTO `sys_area` VALUES ('1615', '开封市', 'kaifengshi', 'kfs', '410000', '410200', null, null);
INSERT INTO `sys_area` VALUES ('1616', '龙亭区', 'longtingqu', 'ltq', '410200', '410202', null, null);
INSERT INTO `sys_area` VALUES ('1617', '顺河回族区', 'shunhehuizuqu', 'shhzq', '410200', '410203', null, null);
INSERT INTO `sys_area` VALUES ('1618', '鼓楼区', 'gulouqu', 'glq', '410200', '410204', null, null);
INSERT INTO `sys_area` VALUES ('1619', '禹王台区', 'yuwangtaiqu', 'ywtq', '410200', '410205', null, null);
INSERT INTO `sys_area` VALUES ('1620', '祥符区', 'xiangfuqu', 'xfq', '410200', '410212', null, null);
INSERT INTO `sys_area` VALUES ('1621', '杞县', 'zuoxian', '杞x', '410200', '410221', null, null);
INSERT INTO `sys_area` VALUES ('1622', '通许县', 'tongxuxian', 'txx', '410200', '410222', null, null);
INSERT INTO `sys_area` VALUES ('1623', '尉氏县', 'weishixian', 'wsx', '410200', '410223', null, null);
INSERT INTO `sys_area` VALUES ('1624', '兰考县', 'lankaoxian', 'lkx', '410200', '410225', null, null);
INSERT INTO `sys_area` VALUES ('1625', '洛阳市', 'luoyangshi', 'lys', '410000', '410300', null, null);
INSERT INTO `sys_area` VALUES ('1626', '老城区', 'laochengqu', 'lcq', '410300', '410302', null, null);
INSERT INTO `sys_area` VALUES ('1627', '西工区', 'xigongqu', 'xgq', '410300', '410303', null, null);
INSERT INTO `sys_area` VALUES ('1628', '瀍河回族区', 'hehuizuqu', '瀍hhzq', '410300', '410304', null, null);
INSERT INTO `sys_area` VALUES ('1629', '涧西区', 'jianxiqu', 'jxq', '410300', '410305', null, null);
INSERT INTO `sys_area` VALUES ('1630', '吉利区', 'jiliqu', 'jlq', '410300', '410306', null, null);
INSERT INTO `sys_area` VALUES ('1631', '洛龙区', 'luolongqu', 'llq', '410300', '410311', null, null);
INSERT INTO `sys_area` VALUES ('1632', '孟津县', 'mengjinxian', 'mjx', '410300', '410322', null, null);
INSERT INTO `sys_area` VALUES ('1633', '新安县', 'xinanxian', 'xax', '410300', '410323', null, null);
INSERT INTO `sys_area` VALUES ('1634', '栾川县', 'zuochuanxian', '栾cx', '410300', '410324', null, null);
INSERT INTO `sys_area` VALUES ('1635', '嵩县', 'zuoxian', '嵩x', '410300', '410325', null, null);
INSERT INTO `sys_area` VALUES ('1636', '汝阳县', 'ruyangxian', 'ryx', '410300', '410326', null, null);
INSERT INTO `sys_area` VALUES ('1637', '宜阳县', 'yiyangxian', 'yyx', '410300', '410327', null, null);
INSERT INTO `sys_area` VALUES ('1638', '洛宁县', 'luoningxian', 'lnx', '410300', '410328', null, null);
INSERT INTO `sys_area` VALUES ('1639', '伊川县', 'yichuanxian', 'ycx', '410300', '410329', null, null);
INSERT INTO `sys_area` VALUES ('1640', '偃师市', 'zuoshishi', '偃ss', '410300', '410381', null, null);
INSERT INTO `sys_area` VALUES ('1641', '平顶山市', 'pingdingshanshi', 'pdss', '410000', '410400', null, null);
INSERT INTO `sys_area` VALUES ('1642', '新华区', 'xinhuaqu', 'xhq', '410400', '410402', null, null);
INSERT INTO `sys_area` VALUES ('1643', '卫东区', 'weidongqu', 'wdq', '410400', '410403', null, null);
INSERT INTO `sys_area` VALUES ('1644', '石龙区', 'shilongqu', 'slq', '410400', '410404', null, null);
INSERT INTO `sys_area` VALUES ('1645', '湛河区', 'zhanhequ', 'zhq', '410400', '410411', null, null);
INSERT INTO `sys_area` VALUES ('1646', '宝丰县', 'baofengxian', 'bfx', '410400', '410421', null, null);
INSERT INTO `sys_area` VALUES ('1647', '叶县', 'yexian', 'yx', '410400', '410422', null, null);
INSERT INTO `sys_area` VALUES ('1648', '鲁山县', 'lushanxian', 'lsx', '410400', '410423', null, null);
INSERT INTO `sys_area` VALUES ('1649', '郏县', 'zuoxian', '郏x', '410400', '410425', null, null);
INSERT INTO `sys_area` VALUES ('1650', '舞钢市', 'wugangshi', 'wgs', '410400', '410481', null, null);
INSERT INTO `sys_area` VALUES ('1651', '汝州市', 'ruzhoushi', 'rzs', '410400', '410482', null, null);
INSERT INTO `sys_area` VALUES ('1652', '安阳市', 'anyangshi', 'ays', '410000', '410500', null, null);
INSERT INTO `sys_area` VALUES ('1653', '文峰区', 'wenfengqu', 'wfq', '410500', '410502', null, null);
INSERT INTO `sys_area` VALUES ('1654', '北关区', 'beiguanqu', 'bgq', '410500', '410503', null, null);
INSERT INTO `sys_area` VALUES ('1655', '殷都区', 'yinduqu', 'ydq', '410500', '410505', null, null);
INSERT INTO `sys_area` VALUES ('1656', '龙安区', 'longanqu', 'laq', '410500', '410506', null, null);
INSERT INTO `sys_area` VALUES ('1657', '安阳县', 'anyangxian', 'ayx', '410500', '410522', null, null);
INSERT INTO `sys_area` VALUES ('1658', '汤阴县', 'tangyinxian', 'tyx', '410500', '410523', null, null);
INSERT INTO `sys_area` VALUES ('1659', '滑县', 'huaxian', 'hx', '410500', '410526', null, null);
INSERT INTO `sys_area` VALUES ('1660', '内黄县', 'neihuangxian', 'nhx', '410500', '410527', null, null);
INSERT INTO `sys_area` VALUES ('1661', '林州市', 'linzhoushi', 'lzs', '410500', '410581', null, null);
INSERT INTO `sys_area` VALUES ('1662', '鹤壁市', 'hebishi', 'hbs', '410000', '410600', null, null);
INSERT INTO `sys_area` VALUES ('1663', '鹤山区', 'heshanqu', 'hsq', '410600', '410602', null, null);
INSERT INTO `sys_area` VALUES ('1664', '山城区', 'shanchengqu', 'scq', '410600', '410603', null, null);
INSERT INTO `sys_area` VALUES ('1665', '淇滨区', 'zuobinqu', '淇bq', '410600', '410611', null, null);
INSERT INTO `sys_area` VALUES ('1666', '浚县', 'junxian', 'jx', '410600', '410621', null, null);
INSERT INTO `sys_area` VALUES ('1667', '淇县', 'zuoxian', '淇x', '410600', '410622', null, null);
INSERT INTO `sys_area` VALUES ('1668', '新乡市', 'xinxiangshi', 'xxs', '410000', '410700', null, null);
INSERT INTO `sys_area` VALUES ('1669', '红旗区', 'hongqiqu', 'hqq', '410700', '410702', null, null);
INSERT INTO `sys_area` VALUES ('1670', '卫滨区', 'weibinqu', 'wbq', '410700', '410703', null, null);
INSERT INTO `sys_area` VALUES ('1671', '凤泉区', 'fengquanqu', 'fqq', '410700', '410704', null, null);
INSERT INTO `sys_area` VALUES ('1672', '牧野区', 'muyequ', 'myq', '410700', '410711', null, null);
INSERT INTO `sys_area` VALUES ('1673', '新乡县', 'xinxiangxian', 'xxx', '410700', '410721', null, null);
INSERT INTO `sys_area` VALUES ('1674', '获嘉县', 'huojiaxian', 'hjx', '410700', '410724', null, null);
INSERT INTO `sys_area` VALUES ('1675', '原阳县', 'yuanyangxian', 'yyx', '410700', '410725', null, null);
INSERT INTO `sys_area` VALUES ('1676', '延津县', 'yanjinxian', 'yjx', '410700', '410726', null, null);
INSERT INTO `sys_area` VALUES ('1677', '封丘县', 'fengqiuxian', 'fqx', '410700', '410727', null, null);
INSERT INTO `sys_area` VALUES ('1678', '长垣县', 'changyuanxian', 'cyx', '410700', '410728', null, null);
INSERT INTO `sys_area` VALUES ('1679', '卫辉市', 'weihuishi', 'whs', '410700', '410781', null, null);
INSERT INTO `sys_area` VALUES ('1680', '辉县市', 'huixianshi', 'hxs', '410700', '410782', null, null);
INSERT INTO `sys_area` VALUES ('1681', '焦作市', 'jiaozuoshi', 'jzs', '410000', '410800', null, null);
INSERT INTO `sys_area` VALUES ('1682', '解放区', 'jiefangqu', 'jfq', '410800', '410802', null, null);
INSERT INTO `sys_area` VALUES ('1683', '中站区', 'zhongzhanqu', 'zzq', '410800', '410803', null, null);
INSERT INTO `sys_area` VALUES ('1684', '马村区', 'macunqu', 'mcq', '410800', '410804', null, null);
INSERT INTO `sys_area` VALUES ('1685', '山阳区', 'shanyangqu', 'syq', '410800', '410811', null, null);
INSERT INTO `sys_area` VALUES ('1686', '修武县', 'xiuwuxian', 'xwx', '410800', '410821', null, null);
INSERT INTO `sys_area` VALUES ('1687', '博爱县', 'boaixian', 'bax', '410800', '410822', null, null);
INSERT INTO `sys_area` VALUES ('1688', '武陟县', 'wuzuoxian', 'w陟x', '410800', '410823', null, null);
INSERT INTO `sys_area` VALUES ('1689', '温县', 'wenxian', 'wx', '410800', '410825', null, null);
INSERT INTO `sys_area` VALUES ('1690', '沁阳市', 'qinyangshi', 'qys', '410800', '410882', null, null);
INSERT INTO `sys_area` VALUES ('1691', '孟州市', 'mengzhoushi', 'mzs', '410800', '410883', null, null);
INSERT INTO `sys_area` VALUES ('1692', '濮阳市', 'zuoyangshi', '濮ys', '410000', '410900', null, null);
INSERT INTO `sys_area` VALUES ('1693', '华龙区', 'hualongqu', 'hlq', '410900', '410902', null, null);
INSERT INTO `sys_area` VALUES ('1694', '清丰县', 'qingfengxian', 'qfx', '410900', '410922', null, null);
INSERT INTO `sys_area` VALUES ('1695', '南乐县', 'nanlexian', 'nlx', '410900', '410923', null, null);
INSERT INTO `sys_area` VALUES ('1696', '范县', 'fanxian', 'fx', '410900', '410926', null, null);
INSERT INTO `sys_area` VALUES ('1697', '台前县', 'taiqianxian', 'tqx', '410900', '410927', null, null);
INSERT INTO `sys_area` VALUES ('1698', '濮阳县', 'zuoyangxian', '濮yx', '410900', '410928', null, null);
INSERT INTO `sys_area` VALUES ('1699', '许昌市', 'xuchangshi', 'xcs', '410000', '411000', null, null);
INSERT INTO `sys_area` VALUES ('1700', '魏都区', 'weiduqu', 'wdq', '411000', '411002', null, null);
INSERT INTO `sys_area` VALUES ('1701', '建安区', 'jiananqu', 'jaq', '411000', '411003', null, null);
INSERT INTO `sys_area` VALUES ('1702', '鄢陵县', 'zuolingxian', '鄢lx', '411000', '411024', null, null);
INSERT INTO `sys_area` VALUES ('1703', '襄城县', 'xiangchengxian', 'xcx', '411000', '411025', null, null);
INSERT INTO `sys_area` VALUES ('1704', '禹州市', 'yuzhoushi', 'yzs', '411000', '411081', null, null);
INSERT INTO `sys_area` VALUES ('1705', '长葛市', 'changgeshi', 'cgs', '411000', '411082', null, null);
INSERT INTO `sys_area` VALUES ('1706', '漯河市', 'zuoheshi', '漯hs', '410000', '411100', null, null);
INSERT INTO `sys_area` VALUES ('1707', '源汇区', 'yuanhuiqu', 'yhq', '411100', '411102', null, null);
INSERT INTO `sys_area` VALUES ('1708', '郾城区', 'zuochengqu', '郾cq', '411100', '411103', null, null);
INSERT INTO `sys_area` VALUES ('1709', '召陵区', 'zhaolingqu', 'zlq', '411100', '411104', null, null);
INSERT INTO `sys_area` VALUES ('1710', '舞阳县', 'wuyangxian', 'wyx', '411100', '411121', null, null);
INSERT INTO `sys_area` VALUES ('1711', '临颍县', 'linzuoxian', 'l颍x', '411100', '411122', null, null);
INSERT INTO `sys_area` VALUES ('1712', '三门峡市', 'sanmenxiashi', 'smxs', '410000', '411200', null, null);
INSERT INTO `sys_area` VALUES ('1713', '湖滨区', 'hubinqu', 'hbq', '411200', '411202', null, null);
INSERT INTO `sys_area` VALUES ('1714', '陕州区', 'shanzhouqu', 'szq', '411200', '411203', null, null);
INSERT INTO `sys_area` VALUES ('1715', '渑池县', 'zuochixian', '渑cx', '411200', '411221', null, null);
INSERT INTO `sys_area` VALUES ('1716', '卢氏县', 'lushixian', 'lsx', '411200', '411224', null, null);
INSERT INTO `sys_area` VALUES ('1717', '义马市', 'yimashi', 'yms', '411200', '411281', null, null);
INSERT INTO `sys_area` VALUES ('1718', '灵宝市', 'lingbaoshi', 'lbs', '411200', '411282', null, null);
INSERT INTO `sys_area` VALUES ('1719', '南阳市', 'nanyangshi', 'nys', '410000', '411300', null, null);
INSERT INTO `sys_area` VALUES ('1720', '宛城区', 'wanchengqu', 'wcq', '411300', '411302', null, null);
INSERT INTO `sys_area` VALUES ('1721', '卧龙区', 'wolongqu', 'wlq', '411300', '411303', null, null);
INSERT INTO `sys_area` VALUES ('1722', '南召县', 'nanzhaoxian', 'nzx', '411300', '411321', null, null);
INSERT INTO `sys_area` VALUES ('1723', '方城县', 'fangchengxian', 'fcx', '411300', '411322', null, null);
INSERT INTO `sys_area` VALUES ('1724', '西峡县', 'xixiaxian', 'xxx', '411300', '411323', null, null);
INSERT INTO `sys_area` VALUES ('1725', '镇平县', 'zhenpingxian', 'zpx', '411300', '411324', null, null);
INSERT INTO `sys_area` VALUES ('1726', '内乡县', 'neixiangxian', 'nxx', '411300', '411325', null, null);
INSERT INTO `sys_area` VALUES ('1727', '淅川县', 'zuochuanxian', '淅cx', '411300', '411326', null, null);
INSERT INTO `sys_area` VALUES ('1728', '社旗县', 'sheqixian', 'sqx', '411300', '411327', null, null);
INSERT INTO `sys_area` VALUES ('1729', '唐河县', 'tanghexian', 'thx', '411300', '411328', null, null);
INSERT INTO `sys_area` VALUES ('1730', '新野县', 'xinyexian', 'xyx', '411300', '411329', null, null);
INSERT INTO `sys_area` VALUES ('1731', '桐柏县', 'tongbaixian', 'tbx', '411300', '411330', null, null);
INSERT INTO `sys_area` VALUES ('1732', '邓州市', 'dengzhoushi', 'dzs', '411300', '411381', null, null);
INSERT INTO `sys_area` VALUES ('1733', '商丘市', 'shangqiushi', 'sqs', '410000', '411400', null, null);
INSERT INTO `sys_area` VALUES ('1734', '梁园区', 'liangyuanqu', 'lyq', '411400', '411402', null, null);
INSERT INTO `sys_area` VALUES ('1735', '睢阳区', 'zuoyangqu', '睢yq', '411400', '411403', null, null);
INSERT INTO `sys_area` VALUES ('1736', '民权县', 'minquanxian', 'mqx', '411400', '411421', null, null);
INSERT INTO `sys_area` VALUES ('1737', '睢县', 'zuoxian', '睢x', '411400', '411422', null, null);
INSERT INTO `sys_area` VALUES ('1738', '宁陵县', 'ninglingxian', 'nlx', '411400', '411423', null, null);
INSERT INTO `sys_area` VALUES ('1739', '柘城县', 'zuochengxian', '柘cx', '411400', '411424', null, null);
INSERT INTO `sys_area` VALUES ('1740', '虞城县', 'yuchengxian', 'ycx', '411400', '411425', null, null);
INSERT INTO `sys_area` VALUES ('1741', '夏邑县', 'xiayixian', 'xyx', '411400', '411426', null, null);
INSERT INTO `sys_area` VALUES ('1742', '永城市', 'yongchengshi', 'ycs', '411400', '411481', null, null);
INSERT INTO `sys_area` VALUES ('1743', '信阳市', 'xinyangshi', 'xys', '410000', '411500', null, null);
INSERT INTO `sys_area` VALUES ('1744', '浉河区', 'hequ', '浉hq', '411500', '411502', null, null);
INSERT INTO `sys_area` VALUES ('1745', '平桥区', 'pingqiaoqu', 'pqq', '411500', '411503', null, null);
INSERT INTO `sys_area` VALUES ('1746', '罗山县', 'luoshanxian', 'lsx', '411500', '411521', null, null);
INSERT INTO `sys_area` VALUES ('1747', '光山县', 'guangshanxian', 'gsx', '411500', '411522', null, null);
INSERT INTO `sys_area` VALUES ('1748', '新县', 'xinxian', 'xx', '411500', '411523', null, null);
INSERT INTO `sys_area` VALUES ('1749', '商城县', 'shangchengxian', 'scx', '411500', '411524', null, null);
INSERT INTO `sys_area` VALUES ('1750', '固始县', 'gushixian', 'gsx', '411500', '411525', null, null);
INSERT INTO `sys_area` VALUES ('1751', '潢川县', 'zuochuanxian', '潢cx', '411500', '411526', null, null);
INSERT INTO `sys_area` VALUES ('1752', '淮滨县', 'huaibinxian', 'hbx', '411500', '411527', null, null);
INSERT INTO `sys_area` VALUES ('1753', '息县', 'xixian', 'xx', '411500', '411528', null, null);
INSERT INTO `sys_area` VALUES ('1754', '周口市', 'zhoukoushi', 'zks', '410000', '411600', null, null);
INSERT INTO `sys_area` VALUES ('1755', '川汇区', 'chuanhuiqu', 'chq', '411600', '411602', null, null);
INSERT INTO `sys_area` VALUES ('1756', '扶沟县', 'fugouxian', 'fgx', '411600', '411621', null, null);
INSERT INTO `sys_area` VALUES ('1757', '西华县', 'xihuaxian', 'xhx', '411600', '411622', null, null);
INSERT INTO `sys_area` VALUES ('1758', '商水县', 'shangshuixian', 'ssx', '411600', '411623', null, null);
INSERT INTO `sys_area` VALUES ('1759', '沈丘县', 'shenqiuxian', 'sqx', '411600', '411624', null, null);
INSERT INTO `sys_area` VALUES ('1760', '郸城县', 'danchengxian', 'dcx', '411600', '411625', null, null);
INSERT INTO `sys_area` VALUES ('1761', '淮阳县', 'huaiyangxian', 'hyx', '411600', '411626', null, null);
INSERT INTO `sys_area` VALUES ('1762', '太康县', 'taikangxian', 'tkx', '411600', '411627', null, null);
INSERT INTO `sys_area` VALUES ('1763', '鹿邑县', 'luyixian', 'lyx', '411600', '411628', null, null);
INSERT INTO `sys_area` VALUES ('1764', '项城市', 'xiangchengshi', 'xcs', '411600', '411681', null, null);
INSERT INTO `sys_area` VALUES ('1765', '驻马店市', 'zhumadianshi', 'zmds', '410000', '411700', null, null);
INSERT INTO `sys_area` VALUES ('1766', '驿城区', 'zuochengqu', '驿cq', '411700', '411702', null, null);
INSERT INTO `sys_area` VALUES ('1767', '西平县', 'xipingxian', 'xpx', '411700', '411721', null, null);
INSERT INTO `sys_area` VALUES ('1768', '上蔡县', 'shangcaixian', 'scx', '411700', '411722', null, null);
INSERT INTO `sys_area` VALUES ('1769', '平舆县', 'pingyuxian', 'pyx', '411700', '411723', null, null);
INSERT INTO `sys_area` VALUES ('1770', '正阳县', 'zhengyangxian', 'zyx', '411700', '411724', null, null);
INSERT INTO `sys_area` VALUES ('1771', '确山县', 'queshanxian', 'qsx', '411700', '411725', null, null);
INSERT INTO `sys_area` VALUES ('1772', '泌阳县', 'miyangxian', 'myx', '411700', '411726', null, null);
INSERT INTO `sys_area` VALUES ('1773', '汝南县', 'runanxian', 'rnx', '411700', '411727', null, null);
INSERT INTO `sys_area` VALUES ('1774', '遂平县', 'suipingxian', 'spx', '411700', '411728', null, null);
INSERT INTO `sys_area` VALUES ('1775', '新蔡县', 'xincaixian', 'xcx', '411700', '411729', null, null);
INSERT INTO `sys_area` VALUES ('1776', '济源市', 'jiyuanshi', 'jys', '410000', '419001', null, null);
INSERT INTO `sys_area` VALUES ('1777', '湖北省', 'hubeisheng', 'hbs', '0', '420000', null, null);
INSERT INTO `sys_area` VALUES ('1778', '武汉市', 'wuhanshi', 'whs', '420000', '420100', null, null);
INSERT INTO `sys_area` VALUES ('1779', '江岸区', 'jianganqu', 'jaq', '420100', '420102', null, null);
INSERT INTO `sys_area` VALUES ('1780', '江汉区', 'jianghanqu', 'jhq', '420100', '420103', null, null);
INSERT INTO `sys_area` VALUES ('1781', '硚口区', 'chukouqu', 'ckq', '420100', '420104', null, null);
INSERT INTO `sys_area` VALUES ('1782', '汉阳区', 'hanyangqu', 'hyq', '420100', '420105', null, null);
INSERT INTO `sys_area` VALUES ('1783', '武昌区', 'wuchangqu', 'wcq', '420100', '420106', null, null);
INSERT INTO `sys_area` VALUES ('1784', '青山区', 'qingshanqu', 'qsq', '420100', '420107', null, null);
INSERT INTO `sys_area` VALUES ('1785', '洪山区', 'hongshanqu', 'hsq', '420100', '420111', null, null);
INSERT INTO `sys_area` VALUES ('1786', '东西湖区', 'dongxihuqu', 'dxhq', '420100', '420112', null, null);
INSERT INTO `sys_area` VALUES ('1787', '汉南区', 'hannanqu', 'hnq', '420100', '420113', null, null);
INSERT INTO `sys_area` VALUES ('1788', '蔡甸区', 'caidianqu', 'cdq', '420100', '420114', null, null);
INSERT INTO `sys_area` VALUES ('1789', '江夏区', 'jiangxiaqu', 'jxq', '420100', '420115', null, null);
INSERT INTO `sys_area` VALUES ('1790', '黄陂区', 'huangzuoqu', 'h陂q', '420100', '420116', null, null);
INSERT INTO `sys_area` VALUES ('1791', '新洲区', 'xinzhouqu', 'xzq', '420100', '420117', null, null);
INSERT INTO `sys_area` VALUES ('1792', '黄石市', 'huangshishi', 'hss', '420000', '420200', null, null);
INSERT INTO `sys_area` VALUES ('1793', '黄石港区', 'huangshigangqu', 'hsgq', '420200', '420202', null, null);
INSERT INTO `sys_area` VALUES ('1794', '西塞山区', 'xisaishanqu', 'xssq', '420200', '420203', null, null);
INSERT INTO `sys_area` VALUES ('1795', '下陆区', 'xialuqu', 'xlq', '420200', '420204', null, null);
INSERT INTO `sys_area` VALUES ('1796', '铁山区', 'tieshanqu', 'tsq', '420200', '420205', null, null);
INSERT INTO `sys_area` VALUES ('1797', '阳新县', 'yangxinxian', 'yxx', '420200', '420222', null, null);
INSERT INTO `sys_area` VALUES ('1798', '大冶市', 'dayeshi', 'dys', '420200', '420281', null, null);
INSERT INTO `sys_area` VALUES ('1799', '十堰市', 'shiyanshi', 'sys', '420000', '420300', null, null);
INSERT INTO `sys_area` VALUES ('1800', '茅箭区', 'maojianqu', 'mjq', '420300', '420302', null, null);
INSERT INTO `sys_area` VALUES ('1801', '张湾区', 'zhangwanqu', 'zwq', '420300', '420303', null, null);
INSERT INTO `sys_area` VALUES ('1802', '郧阳区', 'yunyangqu', 'yyq', '420300', '420304', null, null);
INSERT INTO `sys_area` VALUES ('1803', '郧西县', 'yunxixian', 'yxx', '420300', '420322', null, null);
INSERT INTO `sys_area` VALUES ('1804', '竹山县', 'zhushanxian', 'zsx', '420300', '420323', null, null);
INSERT INTO `sys_area` VALUES ('1805', '竹溪县', 'zhuxixian', 'zxx', '420300', '420324', null, null);
INSERT INTO `sys_area` VALUES ('1806', '房县', 'fangxian', 'fx', '420300', '420325', null, null);
INSERT INTO `sys_area` VALUES ('1807', '丹江口市', 'danjiangkoushi', 'djks', '420300', '420381', null, null);
INSERT INTO `sys_area` VALUES ('1808', '宜昌市', 'yichangshi', 'ycs', '420000', '420500', null, null);
INSERT INTO `sys_area` VALUES ('1809', '西陵区', 'xilingqu', 'xlq', '420500', '420502', null, null);
INSERT INTO `sys_area` VALUES ('1810', '伍家岗区', 'wujiagangqu', 'wjgq', '420500', '420503', null, null);
INSERT INTO `sys_area` VALUES ('1811', '点军区', 'dianjunqu', 'djq', '420500', '420504', null, null);
INSERT INTO `sys_area` VALUES ('1812', '猇亭区', 'tingqu', '猇tq', '420500', '420505', null, null);
INSERT INTO `sys_area` VALUES ('1813', '夷陵区', 'yilingqu', 'ylq', '420500', '420506', null, null);
INSERT INTO `sys_area` VALUES ('1814', '远安县', 'yuananxian', 'yax', '420500', '420525', null, null);
INSERT INTO `sys_area` VALUES ('1815', '兴山县', 'xingshanxian', 'xsx', '420500', '420526', null, null);
INSERT INTO `sys_area` VALUES ('1816', '秭归县', 'zuoguixian', '秭gx', '420500', '420527', null, null);
INSERT INTO `sys_area` VALUES ('1817', '长阳土家族自治县', 'changyangtujiazuzizhixian', 'cytjzzzx', '420500', '420528', null, null);
INSERT INTO `sys_area` VALUES ('1818', '五峰土家族自治县', 'wufengtujiazuzizhixian', 'wftjzzzx', '420500', '420529', null, null);
INSERT INTO `sys_area` VALUES ('1819', '宜都市', 'yidushi', 'yds', '420500', '420581', null, null);
INSERT INTO `sys_area` VALUES ('1820', '当阳市', 'dangyangshi', 'dys', '420500', '420582', null, null);
INSERT INTO `sys_area` VALUES ('1821', '枝江市', 'zhijiangshi', 'zjs', '420500', '420583', null, null);
INSERT INTO `sys_area` VALUES ('1822', '襄阳市', 'xiangyangshi', 'xys', '420000', '420600', null, null);
INSERT INTO `sys_area` VALUES ('1823', '襄城区', 'xiangchengqu', 'xcq', '420600', '420602', null, null);
INSERT INTO `sys_area` VALUES ('1824', '樊城区', 'fanchengqu', 'fcq', '420600', '420606', null, null);
INSERT INTO `sys_area` VALUES ('1825', '襄州区', 'xiangzhouqu', 'xzq', '420600', '420607', null, null);
INSERT INTO `sys_area` VALUES ('1826', '南漳县', 'nanzhangxian', 'nzx', '420600', '420624', null, null);
INSERT INTO `sys_area` VALUES ('1827', '谷城县', 'guchengxian', 'gcx', '420600', '420625', null, null);
INSERT INTO `sys_area` VALUES ('1828', '保康县', 'baokangxian', 'bkx', '420600', '420626', null, null);
INSERT INTO `sys_area` VALUES ('1829', '老河口市', 'laohekoushi', 'lhks', '420600', '420682', null, null);
INSERT INTO `sys_area` VALUES ('1830', '枣阳市', 'zaoyangshi', 'zys', '420600', '420683', null, null);
INSERT INTO `sys_area` VALUES ('1831', '宜城市', 'yichengshi', 'ycs', '420600', '420684', null, null);
INSERT INTO `sys_area` VALUES ('1832', '鄂州市', 'ezhoushi', 'ezs', '420000', '420700', null, null);
INSERT INTO `sys_area` VALUES ('1833', '梁子湖区', 'liangzihuqu', 'lzhq', '420700', '420702', null, null);
INSERT INTO `sys_area` VALUES ('1834', '华容区', 'huarongqu', 'hrq', '420700', '420703', null, null);
INSERT INTO `sys_area` VALUES ('1835', '鄂城区', 'echengqu', 'ecq', '420700', '420704', null, null);
INSERT INTO `sys_area` VALUES ('1836', '荆门市', 'jingmenshi', 'jms', '420000', '420800', null, null);
INSERT INTO `sys_area` VALUES ('1837', '东宝区', 'dongbaoqu', 'dbq', '420800', '420802', null, null);
INSERT INTO `sys_area` VALUES ('1838', '掇刀区', 'duodaoqu', 'ddq', '420800', '420804', null, null);
INSERT INTO `sys_area` VALUES ('1839', '沙洋县', 'shayangxian', 'syx', '420800', '420822', null, null);
INSERT INTO `sys_area` VALUES ('1840', '钟祥市', 'zhongxiangshi', 'zxs', '420800', '420881', null, null);
INSERT INTO `sys_area` VALUES ('1841', '京山市', 'jingshanshi', 'jss', '420800', '420882', null, null);
INSERT INTO `sys_area` VALUES ('1842', '孝感市', 'xiaoganshi', 'xgs', '420000', '420900', null, null);
INSERT INTO `sys_area` VALUES ('1843', '孝南区', 'xiaonanqu', 'xnq', '420900', '420902', null, null);
INSERT INTO `sys_area` VALUES ('1844', '孝昌县', 'xiaochangxian', 'xcx', '420900', '420921', null, null);
INSERT INTO `sys_area` VALUES ('1845', '大悟县', 'dawuxian', 'dwx', '420900', '420922', null, null);
INSERT INTO `sys_area` VALUES ('1846', '云梦县', 'yunmengxian', 'ymx', '420900', '420923', null, null);
INSERT INTO `sys_area` VALUES ('1847', '应城市', 'yingchengshi', 'ycs', '420900', '420981', null, null);
INSERT INTO `sys_area` VALUES ('1848', '安陆市', 'anlushi', 'als', '420900', '420982', null, null);
INSERT INTO `sys_area` VALUES ('1849', '汉川市', 'hanchuanshi', 'hcs', '420900', '420984', null, null);
INSERT INTO `sys_area` VALUES ('1850', '荆州市', 'jingzhoushi', 'jzs', '420000', '421000', null, null);
INSERT INTO `sys_area` VALUES ('1851', '沙市区', 'shashiqu', 'ssq', '421000', '421002', null, null);
INSERT INTO `sys_area` VALUES ('1852', '荆州区', 'jingzhouqu', 'jzq', '421000', '421003', null, null);
INSERT INTO `sys_area` VALUES ('1853', '公安县', 'gonganxian', 'gax', '421000', '421022', null, null);
INSERT INTO `sys_area` VALUES ('1854', '监利县', 'jianlixian', 'jlx', '421000', '421023', null, null);
INSERT INTO `sys_area` VALUES ('1855', '江陵县', 'jianglingxian', 'jlx', '421000', '421024', null, null);
INSERT INTO `sys_area` VALUES ('1856', '石首市', 'shishoushi', 'sss', '421000', '421081', null, null);
INSERT INTO `sys_area` VALUES ('1857', '洪湖市', 'honghushi', 'hhs', '421000', '421083', null, null);
INSERT INTO `sys_area` VALUES ('1858', '松滋市', 'songzishi', 'szs', '421000', '421087', null, null);
INSERT INTO `sys_area` VALUES ('1859', '黄冈市', 'huanggangshi', 'hgs', '420000', '421100', null, null);
INSERT INTO `sys_area` VALUES ('1860', '黄州区', 'huangzhouqu', 'hzq', '421100', '421102', null, null);
INSERT INTO `sys_area` VALUES ('1861', '团风县', 'tuanfengxian', 'tfx', '421100', '421121', null, null);
INSERT INTO `sys_area` VALUES ('1862', '红安县', 'honganxian', 'hax', '421100', '421122', null, null);
INSERT INTO `sys_area` VALUES ('1863', '罗田县', 'luotianxian', 'ltx', '421100', '421123', null, null);
INSERT INTO `sys_area` VALUES ('1864', '英山县', 'yingshanxian', 'ysx', '421100', '421124', null, null);
INSERT INTO `sys_area` VALUES ('1865', '浠水县', 'zuoshuixian', '浠sx', '421100', '421125', null, null);
INSERT INTO `sys_area` VALUES ('1866', '蕲春县', 'zuochunxian', '蕲cx', '421100', '421126', null, null);
INSERT INTO `sys_area` VALUES ('1867', '黄梅县', 'huangmeixian', 'hmx', '421100', '421127', null, null);
INSERT INTO `sys_area` VALUES ('1868', '麻城市', 'machengshi', 'mcs', '421100', '421181', null, null);
INSERT INTO `sys_area` VALUES ('1869', '武穴市', 'wuxueshi', 'wxs', '421100', '421182', null, null);
INSERT INTO `sys_area` VALUES ('1870', '咸宁市', 'xianningshi', 'xns', '420000', '421200', null, null);
INSERT INTO `sys_area` VALUES ('1871', '咸安区', 'xiananqu', 'xaq', '421200', '421202', null, null);
INSERT INTO `sys_area` VALUES ('1872', '嘉鱼县', 'jiayuxian', 'jyx', '421200', '421221', null, null);
INSERT INTO `sys_area` VALUES ('1873', '通城县', 'tongchengxian', 'tcx', '421200', '421222', null, null);
INSERT INTO `sys_area` VALUES ('1874', '崇阳县', 'chongyangxian', 'cyx', '421200', '421223', null, null);
INSERT INTO `sys_area` VALUES ('1875', '通山县', 'tongshanxian', 'tsx', '421200', '421224', null, null);
INSERT INTO `sys_area` VALUES ('1876', '赤壁市', 'chibishi', 'cbs', '421200', '421281', null, null);
INSERT INTO `sys_area` VALUES ('1877', '随州市', 'suizhoushi', 'szs', '420000', '421300', null, null);
INSERT INTO `sys_area` VALUES ('1878', '曾都区', 'zengduqu', 'zdq', '421300', '421303', null, null);
INSERT INTO `sys_area` VALUES ('1879', '随县', 'suixian', 'sx', '421300', '421321', null, null);
INSERT INTO `sys_area` VALUES ('1880', '广水市', 'guangshuishi', 'gss', '421300', '421381', null, null);
INSERT INTO `sys_area` VALUES ('1881', '恩施土家族苗族自治州', 'enshitujiazumiaozuzizhizhou', 'estjzmzzzz', '420000', '422800', null, null);
INSERT INTO `sys_area` VALUES ('1882', '恩施市', 'enshishi', 'ess', '422800', '422801', null, null);
INSERT INTO `sys_area` VALUES ('1883', '利川市', 'lichuanshi', 'lcs', '422800', '422802', null, null);
INSERT INTO `sys_area` VALUES ('1884', '建始县', 'jianshixian', 'jsx', '422800', '422822', null, null);
INSERT INTO `sys_area` VALUES ('1885', '巴东县', 'badongxian', 'bdx', '422800', '422823', null, null);
INSERT INTO `sys_area` VALUES ('1886', '宣恩县', 'xuanenxian', 'xex', '422800', '422825', null, null);
INSERT INTO `sys_area` VALUES ('1887', '咸丰县', 'xianfengxian', 'xfx', '422800', '422826', null, null);
INSERT INTO `sys_area` VALUES ('1888', '来凤县', 'laifengxian', 'lfx', '422800', '422827', null, null);
INSERT INTO `sys_area` VALUES ('1889', '鹤峰县', 'hefengxian', 'hfx', '422800', '422828', null, null);
INSERT INTO `sys_area` VALUES ('1890', '仙桃市', 'xiantaoshi', 'xts', '420000', '429004', null, null);
INSERT INTO `sys_area` VALUES ('1891', '潜江市', 'qianjiangshi', 'qjs', '420000', '429005', null, null);
INSERT INTO `sys_area` VALUES ('1892', '天门市', 'tianmenshi', 'tms', '420000', '429006', null, null);
INSERT INTO `sys_area` VALUES ('1893', '神农架林区', 'shennongjialinqu', 'snjlq', '420000', '429021', null, null);
INSERT INTO `sys_area` VALUES ('1894', '湖南省', 'hunansheng', 'hns', '0', '430000', null, null);
INSERT INTO `sys_area` VALUES ('1895', '长沙市', 'changshashi', 'css', '430000', '430100', null, null);
INSERT INTO `sys_area` VALUES ('1896', '芙蓉区', 'zuorongqu', '芙rq', '430100', '430102', null, null);
INSERT INTO `sys_area` VALUES ('1897', '天心区', 'tianxinqu', 'txq', '430100', '430103', null, null);
INSERT INTO `sys_area` VALUES ('1898', '岳麓区', 'yueluqu', 'ylq', '430100', '430104', null, null);
INSERT INTO `sys_area` VALUES ('1899', '开福区', 'kaifuqu', 'kfq', '430100', '430105', null, null);
INSERT INTO `sys_area` VALUES ('1900', '雨花区', 'yuhuaqu', 'yhq', '430100', '430111', null, null);
INSERT INTO `sys_area` VALUES ('1901', '望城区', 'wangchengqu', 'wcq', '430100', '430112', null, null);
INSERT INTO `sys_area` VALUES ('1902', '长沙县', 'changshaxian', 'csx', '430100', '430121', null, null);
INSERT INTO `sys_area` VALUES ('1903', '浏阳市', 'zuoyangshi', '浏ys', '430100', '430181', null, null);
INSERT INTO `sys_area` VALUES ('1904', '宁乡市', 'ningxiangshi', 'nxs', '430100', '430182', null, null);
INSERT INTO `sys_area` VALUES ('1905', '株洲市', 'zhuzhoushi', 'zzs', '430000', '430200', null, null);
INSERT INTO `sys_area` VALUES ('1906', '荷塘区', 'hetangqu', 'htq', '430200', '430202', null, null);
INSERT INTO `sys_area` VALUES ('1907', '芦淞区', 'luzuoqu', 'l淞q', '430200', '430203', null, null);
INSERT INTO `sys_area` VALUES ('1908', '石峰区', 'shifengqu', 'sfq', '430200', '430204', null, null);
INSERT INTO `sys_area` VALUES ('1909', '天元区', 'tianyuanqu', 'tyq', '430200', '430211', null, null);
INSERT INTO `sys_area` VALUES ('1910', '株洲县', 'zhuzhouxian', 'zzx', '430200', '430221', null, null);
INSERT INTO `sys_area` VALUES ('1911', '攸县', 'zuoxian', '攸x', '430200', '430223', null, null);
INSERT INTO `sys_area` VALUES ('1912', '茶陵县', 'chalingxian', 'clx', '430200', '430224', null, null);
INSERT INTO `sys_area` VALUES ('1913', '炎陵县', 'yanlingxian', 'ylx', '430200', '430225', null, null);
INSERT INTO `sys_area` VALUES ('1914', '醴陵市', 'zuolingshi', '醴ls', '430200', '430281', null, null);
INSERT INTO `sys_area` VALUES ('1915', '湘潭市', 'xiangtanshi', 'xts', '430000', '430300', null, null);
INSERT INTO `sys_area` VALUES ('1916', '雨湖区', 'yuhuqu', 'yhq', '430300', '430302', null, null);
INSERT INTO `sys_area` VALUES ('1917', '岳塘区', 'yuetangqu', 'ytq', '430300', '430304', null, null);
INSERT INTO `sys_area` VALUES ('1918', '湘潭县', 'xiangtanxian', 'xtx', '430300', '430321', null, null);
INSERT INTO `sys_area` VALUES ('1919', '湘乡市', 'xiangxiangshi', 'xxs', '430300', '430381', null, null);
INSERT INTO `sys_area` VALUES ('1920', '韶山市', 'shaoshanshi', 'sss', '430300', '430382', null, null);
INSERT INTO `sys_area` VALUES ('1921', '衡阳市', 'hengyangshi', 'hys', '430000', '430400', null, null);
INSERT INTO `sys_area` VALUES ('1922', '珠晖区', 'zhuzuoqu', 'z晖q', '430400', '430405', null, null);
INSERT INTO `sys_area` VALUES ('1923', '雁峰区', 'yanfengqu', 'yfq', '430400', '430406', null, null);
INSERT INTO `sys_area` VALUES ('1924', '石鼓区', 'shiguqu', 'sgq', '430400', '430407', null, null);
INSERT INTO `sys_area` VALUES ('1925', '蒸湘区', 'zhengxiangqu', 'zxq', '430400', '430408', null, null);
INSERT INTO `sys_area` VALUES ('1926', '南岳区', 'nanyuequ', 'nyq', '430400', '430412', null, null);
INSERT INTO `sys_area` VALUES ('1927', '衡阳县', 'hengyangxian', 'hyx', '430400', '430421', null, null);
INSERT INTO `sys_area` VALUES ('1928', '衡南县', 'hengnanxian', 'hnx', '430400', '430422', null, null);
INSERT INTO `sys_area` VALUES ('1929', '衡山县', 'hengshanxian', 'hsx', '430400', '430423', null, null);
INSERT INTO `sys_area` VALUES ('1930', '衡东县', 'hengdongxian', 'hdx', '430400', '430424', null, null);
INSERT INTO `sys_area` VALUES ('1931', '祁东县', 'qidongxian', 'qdx', '430400', '430426', null, null);
INSERT INTO `sys_area` VALUES ('1932', '耒阳市', 'zuoyangshi', '耒ys', '430400', '430481', null, null);
INSERT INTO `sys_area` VALUES ('1933', '常宁市', 'changningshi', 'cns', '430400', '430482', null, null);
INSERT INTO `sys_area` VALUES ('1934', '邵阳市', 'shaoyangshi', 'sys', '430000', '430500', null, null);
INSERT INTO `sys_area` VALUES ('1935', '双清区', 'shuangqingqu', 'sqq', '430500', '430502', null, null);
INSERT INTO `sys_area` VALUES ('1936', '大祥区', 'daxiangqu', 'dxq', '430500', '430503', null, null);
INSERT INTO `sys_area` VALUES ('1937', '北塔区', 'beitaqu', 'btq', '430500', '430511', null, null);
INSERT INTO `sys_area` VALUES ('1938', '邵东县', 'shaodongxian', 'sdx', '430500', '430521', null, null);
INSERT INTO `sys_area` VALUES ('1939', '新邵县', 'xinshaoxian', 'xsx', '430500', '430522', null, null);
INSERT INTO `sys_area` VALUES ('1940', '邵阳县', 'shaoyangxian', 'syx', '430500', '430523', null, null);
INSERT INTO `sys_area` VALUES ('1941', '隆回县', 'longhuixian', 'lhx', '430500', '430524', null, null);
INSERT INTO `sys_area` VALUES ('1942', '洞口县', 'dongkouxian', 'dkx', '430500', '430525', null, null);
INSERT INTO `sys_area` VALUES ('1943', '绥宁县', 'suiningxian', 'snx', '430500', '430527', null, null);
INSERT INTO `sys_area` VALUES ('1944', '新宁县', 'xinningxian', 'xnx', '430500', '430528', null, null);
INSERT INTO `sys_area` VALUES ('1945', '城步苗族自治县', 'chengbumiaozuzizhixian', 'cbmzzzx', '430500', '430529', null, null);
INSERT INTO `sys_area` VALUES ('1946', '武冈市', 'wugangshi', 'wgs', '430500', '430581', null, null);
INSERT INTO `sys_area` VALUES ('1947', '岳阳市', 'yueyangshi', 'yys', '430000', '430600', null, null);
INSERT INTO `sys_area` VALUES ('1948', '岳阳楼区', 'yueyanglouqu', 'yylq', '430600', '430602', null, null);
INSERT INTO `sys_area` VALUES ('1949', '云溪区', 'yunxiqu', 'yxq', '430600', '430603', null, null);
INSERT INTO `sys_area` VALUES ('1950', '君山区', 'junshanqu', 'jsq', '430600', '430611', null, null);
INSERT INTO `sys_area` VALUES ('1951', '岳阳县', 'yueyangxian', 'yyx', '430600', '430621', null, null);
INSERT INTO `sys_area` VALUES ('1952', '华容县', 'huarongxian', 'hrx', '430600', '430623', null, null);
INSERT INTO `sys_area` VALUES ('1953', '湘阴县', 'xiangyinxian', 'xyx', '430600', '430624', null, null);
INSERT INTO `sys_area` VALUES ('1954', '平江县', 'pingjiangxian', 'pjx', '430600', '430626', null, null);
INSERT INTO `sys_area` VALUES ('1955', '汨罗市', 'zuoluoshi', '汨ls', '430600', '430681', null, null);
INSERT INTO `sys_area` VALUES ('1956', '临湘市', 'linxiangshi', 'lxs', '430600', '430682', null, null);
INSERT INTO `sys_area` VALUES ('1957', '常德市', 'changdeshi', 'cds', '430000', '430700', null, null);
INSERT INTO `sys_area` VALUES ('1958', '武陵区', 'wulingqu', 'wlq', '430700', '430702', null, null);
INSERT INTO `sys_area` VALUES ('1959', '鼎城区', 'dingchengqu', 'dcq', '430700', '430703', null, null);
INSERT INTO `sys_area` VALUES ('1960', '安乡县', 'anxiangxian', 'axx', '430700', '430721', null, null);
INSERT INTO `sys_area` VALUES ('1961', '汉寿县', 'hanshouxian', 'hsx', '430700', '430722', null, null);
INSERT INTO `sys_area` VALUES ('1962', '澧县', 'zuoxian', '澧x', '430700', '430723', null, null);
INSERT INTO `sys_area` VALUES ('1963', '临澧县', 'linzuoxian', 'l澧x', '430700', '430724', null, null);
INSERT INTO `sys_area` VALUES ('1964', '桃源县', 'taoyuanxian', 'tyx', '430700', '430725', null, null);
INSERT INTO `sys_area` VALUES ('1965', '石门县', 'shimenxian', 'smx', '430700', '430726', null, null);
INSERT INTO `sys_area` VALUES ('1966', '津市市', 'jinshishi', 'jss', '430700', '430781', null, null);
INSERT INTO `sys_area` VALUES ('1967', '张家界市', 'zhangjiajieshi', 'zjjs', '430000', '430800', null, null);
INSERT INTO `sys_area` VALUES ('1968', '永定区', 'yongdingqu', 'ydq', '430800', '430802', null, null);
INSERT INTO `sys_area` VALUES ('1969', '武陵源区', 'wulingyuanqu', 'wlyq', '430800', '430811', null, null);
INSERT INTO `sys_area` VALUES ('1970', '慈利县', 'cilixian', 'clx', '430800', '430821', null, null);
INSERT INTO `sys_area` VALUES ('1971', '桑植县', 'sangzhixian', 'szx', '430800', '430822', null, null);
INSERT INTO `sys_area` VALUES ('1972', '益阳市', 'yiyangshi', 'yys', '430000', '430900', null, null);
INSERT INTO `sys_area` VALUES ('1973', '资阳区', 'ziyangqu', 'zyq', '430900', '430902', null, null);
INSERT INTO `sys_area` VALUES ('1974', '赫山区', 'heshanqu', 'hsq', '430900', '430903', null, null);
INSERT INTO `sys_area` VALUES ('1975', '南县', 'nanxian', 'nx', '430900', '430921', null, null);
INSERT INTO `sys_area` VALUES ('1976', '桃江县', 'taojiangxian', 'tjx', '430900', '430922', null, null);
INSERT INTO `sys_area` VALUES ('1977', '安化县', 'anhuaxian', 'ahx', '430900', '430923', null, null);
INSERT INTO `sys_area` VALUES ('1978', '沅江市', 'zuojiangshi', '沅js', '430900', '430981', null, null);
INSERT INTO `sys_area` VALUES ('1979', '郴州市', 'chenzhoushi', 'czs', '430000', '431000', null, null);
INSERT INTO `sys_area` VALUES ('1980', '北湖区', 'beihuqu', 'bhq', '431000', '431002', null, null);
INSERT INTO `sys_area` VALUES ('1981', '苏仙区', 'suxianqu', 'sxq', '431000', '431003', null, null);
INSERT INTO `sys_area` VALUES ('1982', '桂阳县', 'guiyangxian', 'gyx', '431000', '431021', null, null);
INSERT INTO `sys_area` VALUES ('1983', '宜章县', 'yizhangxian', 'yzx', '431000', '431022', null, null);
INSERT INTO `sys_area` VALUES ('1984', '永兴县', 'yongxingxian', 'yxx', '431000', '431023', null, null);
INSERT INTO `sys_area` VALUES ('1985', '嘉禾县', 'jiahexian', 'jhx', '431000', '431024', null, null);
INSERT INTO `sys_area` VALUES ('1986', '临武县', 'linwuxian', 'lwx', '431000', '431025', null, null);
INSERT INTO `sys_area` VALUES ('1987', '汝城县', 'ruchengxian', 'rcx', '431000', '431026', null, null);
INSERT INTO `sys_area` VALUES ('1988', '桂东县', 'guidongxian', 'gdx', '431000', '431027', null, null);
INSERT INTO `sys_area` VALUES ('1989', '安仁县', 'anrenxian', 'arx', '431000', '431028', null, null);
INSERT INTO `sys_area` VALUES ('1990', '资兴市', 'zixingshi', 'zxs', '431000', '431081', null, null);
INSERT INTO `sys_area` VALUES ('1991', '永州市', 'yongzhoushi', 'yzs', '430000', '431100', null, null);
INSERT INTO `sys_area` VALUES ('1992', '零陵区', 'linglingqu', 'llq', '431100', '431102', null, null);
INSERT INTO `sys_area` VALUES ('1993', '冷水滩区', 'lengshuitanqu', 'lstq', '431100', '431103', null, null);
INSERT INTO `sys_area` VALUES ('1994', '祁阳县', 'qiyangxian', 'qyx', '431100', '431121', null, null);
INSERT INTO `sys_area` VALUES ('1995', '东安县', 'donganxian', 'dax', '431100', '431122', null, null);
INSERT INTO `sys_area` VALUES ('1996', '双牌县', 'shuangpaixian', 'spx', '431100', '431123', null, null);
INSERT INTO `sys_area` VALUES ('1997', '道县', 'daoxian', 'dx', '431100', '431124', null, null);
INSERT INTO `sys_area` VALUES ('1998', '江永县', 'jiangyongxian', 'jyx', '431100', '431125', null, null);
INSERT INTO `sys_area` VALUES ('1999', '宁远县', 'ningyuanxian', 'nyx', '431100', '431126', null, null);
INSERT INTO `sys_area` VALUES ('2000', '蓝山县', 'lanshanxian', 'lsx', '431100', '431127', null, null);
INSERT INTO `sys_area` VALUES ('2001', '新田县', 'xintianxian', 'xtx', '431100', '431128', null, null);
INSERT INTO `sys_area` VALUES ('2002', '江华瑶族自治县', 'jianghuayaozuzizhixian', 'jhyzzzx', '431100', '431129', null, null);
INSERT INTO `sys_area` VALUES ('2003', '怀化市', 'huaihuashi', 'hhs', '430000', '431200', null, null);
INSERT INTO `sys_area` VALUES ('2004', '鹤城区', 'hechengqu', 'hcq', '431200', '431202', null, null);
INSERT INTO `sys_area` VALUES ('2005', '中方县', 'zhongfangxian', 'zfx', '431200', '431221', null, null);
INSERT INTO `sys_area` VALUES ('2006', '沅陵县', 'zuolingxian', '沅lx', '431200', '431222', null, null);
INSERT INTO `sys_area` VALUES ('2007', '辰溪县', 'chenxixian', 'cxx', '431200', '431223', null, null);
INSERT INTO `sys_area` VALUES ('2008', '溆浦县', 'zuopuxian', '溆px', '431200', '431224', null, null);
INSERT INTO `sys_area` VALUES ('2009', '会同县', 'huitongxian', 'htx', '431200', '431225', null, null);
INSERT INTO `sys_area` VALUES ('2010', '麻阳苗族自治县', 'mayangmiaozuzizhixian', 'mymzzzx', '431200', '431226', null, null);
INSERT INTO `sys_area` VALUES ('2011', '新晃侗族自治县', 'xinhuangdongzuzizhixian', 'xhdzzzx', '431200', '431227', null, null);
INSERT INTO `sys_area` VALUES ('2012', '芷江侗族自治县', 'zuojiangdongzuzizhixian', '芷jdzzzx', '431200', '431228', null, null);
INSERT INTO `sys_area` VALUES ('2013', '靖州苗族侗族自治县', 'jingzhoumiaozudongzuzizhixian', 'jzmzdzzzx', '431200', '431229', null, null);
INSERT INTO `sys_area` VALUES ('2014', '通道侗族自治县', 'tongdaodongzuzizhixian', 'tddzzzx', '431200', '431230', null, null);
INSERT INTO `sys_area` VALUES ('2015', '洪江市', 'hongjiangshi', 'hjs', '431200', '431281', null, null);
INSERT INTO `sys_area` VALUES ('2016', '娄底市', 'loudishi', 'lds', '430000', '431300', null, null);
INSERT INTO `sys_area` VALUES ('2017', '娄星区', 'louxingqu', 'lxq', '431300', '431302', null, null);
INSERT INTO `sys_area` VALUES ('2018', '双峰县', 'shuangfengxian', 'sfx', '431300', '431321', null, null);
INSERT INTO `sys_area` VALUES ('2019', '新化县', 'xinhuaxian', 'xhx', '431300', '431322', null, null);
INSERT INTO `sys_area` VALUES ('2020', '冷水江市', 'lengshuijiangshi', 'lsjs', '431300', '431381', null, null);
INSERT INTO `sys_area` VALUES ('2021', '涟源市', 'lianyuanshi', 'lys', '431300', '431382', null, null);
INSERT INTO `sys_area` VALUES ('2022', '湘西土家族苗族自治州', 'xiangxitujiazumiaozuzizhizhou', 'xxtjzmzzzz', '430000', '433100', null, null);
INSERT INTO `sys_area` VALUES ('2023', '吉首市', 'jishoushi', 'jss', '433100', '433101', null, null);
INSERT INTO `sys_area` VALUES ('2024', '泸溪县', 'zuoxixian', '泸xx', '433100', '433122', null, null);
INSERT INTO `sys_area` VALUES ('2025', '凤凰县', 'fenghuangxian', 'fhx', '433100', '433123', null, null);
INSERT INTO `sys_area` VALUES ('2026', '花垣县', 'huayuanxian', 'hyx', '433100', '433124', null, null);
INSERT INTO `sys_area` VALUES ('2027', '保靖县', 'baojingxian', 'bjx', '433100', '433125', null, null);
INSERT INTO `sys_area` VALUES ('2028', '古丈县', 'guzhangxian', 'gzx', '433100', '433126', null, null);
INSERT INTO `sys_area` VALUES ('2029', '永顺县', 'yongshunxian', 'ysx', '433100', '433127', null, null);
INSERT INTO `sys_area` VALUES ('2030', '龙山县', 'longshanxian', 'lsx', '433100', '433130', null, null);
INSERT INTO `sys_area` VALUES ('2031', '广东省', 'guangdongsheng', 'gds', '0', '440000', null, null);
INSERT INTO `sys_area` VALUES ('2032', '广州市', 'guangzhoushi', 'gzs', '440000', '440100', null, null);
INSERT INTO `sys_area` VALUES ('2033', '荔湾区', 'liwanqu', 'lwq', '440100', '440103', null, null);
INSERT INTO `sys_area` VALUES ('2034', '越秀区', 'yuexiuqu', 'yxq', '440100', '440104', null, null);
INSERT INTO `sys_area` VALUES ('2035', '海珠区', 'haizhuqu', 'hzq', '440100', '440105', null, null);
INSERT INTO `sys_area` VALUES ('2036', '天河区', 'tianhequ', 'thq', '440100', '440106', null, null);
INSERT INTO `sys_area` VALUES ('2037', '白云区', 'baiyunqu', 'byq', '440100', '440111', null, null);
INSERT INTO `sys_area` VALUES ('2038', '黄埔区', 'huangpuqu', 'hpq', '440100', '440112', null, null);
INSERT INTO `sys_area` VALUES ('2039', '番禺区', 'fanzuoqu', 'f禺q', '440100', '440113', null, null);
INSERT INTO `sys_area` VALUES ('2040', '花都区', 'huaduqu', 'hdq', '440100', '440114', null, null);
INSERT INTO `sys_area` VALUES ('2041', '南沙区', 'nanshaqu', 'nsq', '440100', '440115', null, null);
INSERT INTO `sys_area` VALUES ('2042', '从化区', 'conghuaqu', 'chq', '440100', '440117', null, null);
INSERT INTO `sys_area` VALUES ('2043', '增城区', 'zengchengqu', 'zcq', '440100', '440118', null, null);
INSERT INTO `sys_area` VALUES ('2044', '韶关市', 'shaoguanshi', 'sgs', '440000', '440200', null, null);
INSERT INTO `sys_area` VALUES ('2045', '武江区', 'wujiangqu', 'wjq', '440200', '440203', null, null);
INSERT INTO `sys_area` VALUES ('2046', '浈江区', 'zuojiangqu', '浈jq', '440200', '440204', null, null);
INSERT INTO `sys_area` VALUES ('2047', '曲江区', 'qujiangqu', 'qjq', '440200', '440205', null, null);
INSERT INTO `sys_area` VALUES ('2048', '始兴县', 'shixingxian', 'sxx', '440200', '440222', null, null);
INSERT INTO `sys_area` VALUES ('2049', '仁化县', 'renhuaxian', 'rhx', '440200', '440224', null, null);
INSERT INTO `sys_area` VALUES ('2050', '翁源县', 'wengyuanxian', 'wyx', '440200', '440229', null, null);
INSERT INTO `sys_area` VALUES ('2051', '乳源瑶族自治县', 'ruyuanyaozuzizhixian', 'ryyzzzx', '440200', '440232', null, null);
INSERT INTO `sys_area` VALUES ('2052', '新丰县', 'xinfengxian', 'xfx', '440200', '440233', null, null);
INSERT INTO `sys_area` VALUES ('2053', '乐昌市', 'lechangshi', 'lcs', '440200', '440281', null, null);
INSERT INTO `sys_area` VALUES ('2054', '南雄市', 'nanxiongshi', 'nxs', '440200', '440282', null, null);
INSERT INTO `sys_area` VALUES ('2055', '深圳市', 'shenzuoshi', 's圳s', '440000', '440300', null, null);
INSERT INTO `sys_area` VALUES ('2056', '罗湖区', 'luohuqu', 'lhq', '440300', '440303', null, null);
INSERT INTO `sys_area` VALUES ('2057', '福田区', 'futianqu', 'ftq', '440300', '440304', null, null);
INSERT INTO `sys_area` VALUES ('2058', '南山区', 'nanshanqu', 'nsq', '440300', '440305', null, null);
INSERT INTO `sys_area` VALUES ('2059', '宝安区', 'baoanqu', 'baq', '440300', '440306', null, null);
INSERT INTO `sys_area` VALUES ('2060', '龙岗区', 'longgangqu', 'lgq', '440300', '440307', null, null);
INSERT INTO `sys_area` VALUES ('2061', '盐田区', 'yantianqu', 'ytq', '440300', '440308', null, null);
INSERT INTO `sys_area` VALUES ('2062', '龙华区', 'longhuaqu', 'lhq', '440300', '440309', null, null);
INSERT INTO `sys_area` VALUES ('2063', '坪山区', 'pingshanqu', 'psq', '440300', '440310', null, null);
INSERT INTO `sys_area` VALUES ('2064', '光明区', 'guangmingqu', 'gmq', '440300', '440311', null, null);
INSERT INTO `sys_area` VALUES ('2065', '珠海市', 'zhuhaishi', 'zhs', '440000', '440400', null, null);
INSERT INTO `sys_area` VALUES ('2066', '香洲区', 'xiangzhouqu', 'xzq', '440400', '440402', null, null);
INSERT INTO `sys_area` VALUES ('2067', '斗门区', 'doumenqu', 'dmq', '440400', '440403', null, null);
INSERT INTO `sys_area` VALUES ('2068', '金湾区', 'jinwanqu', 'jwq', '440400', '440404', null, null);
INSERT INTO `sys_area` VALUES ('2069', '汕头市', 'shantoushi', 'sts', '440000', '440500', null, null);
INSERT INTO `sys_area` VALUES ('2070', '龙湖区', 'longhuqu', 'lhq', '440500', '440507', null, null);
INSERT INTO `sys_area` VALUES ('2071', '金平区', 'jinpingqu', 'jpq', '440500', '440511', null, null);
INSERT INTO `sys_area` VALUES ('2072', '濠江区', 'zuojiangqu', '濠jq', '440500', '440512', null, null);
INSERT INTO `sys_area` VALUES ('2073', '潮阳区', 'chaoyangqu', 'cyq', '440500', '440513', null, null);
INSERT INTO `sys_area` VALUES ('2074', '潮南区', 'chaonanqu', 'cnq', '440500', '440514', null, null);
INSERT INTO `sys_area` VALUES ('2075', '澄海区', 'chenghaiqu', 'chq', '440500', '440515', null, null);
INSERT INTO `sys_area` VALUES ('2076', '南澳县', 'nanaoxian', 'nax', '440500', '440523', null, null);
INSERT INTO `sys_area` VALUES ('2077', '佛山市', 'foshanshi', 'fss', '440000', '440600', null, null);
INSERT INTO `sys_area` VALUES ('2078', '禅城区', 'zuochengqu', '禅cq', '440600', '440604', null, null);
INSERT INTO `sys_area` VALUES ('2079', '南海区', 'nanhaiqu', 'nhq', '440600', '440605', null, null);
INSERT INTO `sys_area` VALUES ('2080', '顺德区', 'shundequ', 'sdq', '440600', '440606', null, null);
INSERT INTO `sys_area` VALUES ('2081', '三水区', 'sanshuiqu', 'ssq', '440600', '440607', null, null);
INSERT INTO `sys_area` VALUES ('2082', '高明区', 'gaomingqu', 'gmq', '440600', '440608', null, null);
INSERT INTO `sys_area` VALUES ('2083', '江门市', 'jiangmenshi', 'jms', '440000', '440700', null, null);
INSERT INTO `sys_area` VALUES ('2084', '蓬江区', 'pengjiangqu', 'pjq', '440700', '440703', null, null);
INSERT INTO `sys_area` VALUES ('2085', '江海区', 'jianghaiqu', 'jhq', '440700', '440704', null, null);
INSERT INTO `sys_area` VALUES ('2086', '新会区', 'xinhuiqu', 'xhq', '440700', '440705', null, null);
INSERT INTO `sys_area` VALUES ('2087', '台山市', 'taishanshi', 'tss', '440700', '440781', null, null);
INSERT INTO `sys_area` VALUES ('2088', '开平市', 'kaipingshi', 'kps', '440700', '440783', null, null);
INSERT INTO `sys_area` VALUES ('2089', '鹤山市', 'heshanshi', 'hss', '440700', '440784', null, null);
INSERT INTO `sys_area` VALUES ('2090', '恩平市', 'enpingshi', 'eps', '440700', '440785', null, null);
INSERT INTO `sys_area` VALUES ('2091', '湛江市', 'zhanjiangshi', 'zjs', '440000', '440800', null, null);
INSERT INTO `sys_area` VALUES ('2092', '赤坎区', 'chikanqu', 'ckq', '440800', '440802', null, null);
INSERT INTO `sys_area` VALUES ('2093', '霞山区', 'xiashanqu', 'xsq', '440800', '440803', null, null);
INSERT INTO `sys_area` VALUES ('2094', '坡头区', 'potouqu', 'ptq', '440800', '440804', null, null);
INSERT INTO `sys_area` VALUES ('2095', '麻章区', 'mazhangqu', 'mzq', '440800', '440811', null, null);
INSERT INTO `sys_area` VALUES ('2096', '遂溪县', 'suixixian', 'sxx', '440800', '440823', null, null);
INSERT INTO `sys_area` VALUES ('2097', '徐闻县', 'xuwenxian', 'xwx', '440800', '440825', null, null);
INSERT INTO `sys_area` VALUES ('2098', '廉江市', 'lianjiangshi', 'ljs', '440800', '440881', null, null);
INSERT INTO `sys_area` VALUES ('2099', '雷州市', 'leizhoushi', 'lzs', '440800', '440882', null, null);
INSERT INTO `sys_area` VALUES ('2100', '吴川市', 'wuchuanshi', 'wcs', '440800', '440883', null, null);
INSERT INTO `sys_area` VALUES ('2101', '茂名市', 'maomingshi', 'mms', '440000', '440900', null, null);
INSERT INTO `sys_area` VALUES ('2102', '茂南区', 'maonanqu', 'mnq', '440900', '440902', null, null);
INSERT INTO `sys_area` VALUES ('2103', '电白区', 'dianbaiqu', 'dbq', '440900', '440904', null, null);
INSERT INTO `sys_area` VALUES ('2104', '高州市', 'gaozhoushi', 'gzs', '440900', '440981', null, null);
INSERT INTO `sys_area` VALUES ('2105', '化州市', 'huazhoushi', 'hzs', '440900', '440982', null, null);
INSERT INTO `sys_area` VALUES ('2106', '信宜市', 'xinyishi', 'xys', '440900', '440983', null, null);
INSERT INTO `sys_area` VALUES ('2107', '肇庆市', 'zhaoqingshi', 'zqs', '440000', '441200', null, null);
INSERT INTO `sys_area` VALUES ('2108', '端州区', 'duanzhouqu', 'dzq', '441200', '441202', null, null);
INSERT INTO `sys_area` VALUES ('2109', '鼎湖区', 'dinghuqu', 'dhq', '441200', '441203', null, null);
INSERT INTO `sys_area` VALUES ('2110', '高要区', 'gaoyaoqu', 'gyq', '441200', '441204', null, null);
INSERT INTO `sys_area` VALUES ('2111', '广宁县', 'guangningxian', 'gnx', '441200', '441223', null, null);
INSERT INTO `sys_area` VALUES ('2112', '怀集县', 'huaijixian', 'hjx', '441200', '441224', null, null);
INSERT INTO `sys_area` VALUES ('2113', '封开县', 'fengkaixian', 'fkx', '441200', '441225', null, null);
INSERT INTO `sys_area` VALUES ('2114', '德庆县', 'deqingxian', 'dqx', '441200', '441226', null, null);
INSERT INTO `sys_area` VALUES ('2115', '四会市', 'sihuishi', 'shs', '441200', '441284', null, null);
INSERT INTO `sys_area` VALUES ('2116', '惠州市', 'huizhoushi', 'hzs', '440000', '441300', null, null);
INSERT INTO `sys_area` VALUES ('2117', '惠城区', 'huichengqu', 'hcq', '441300', '441302', null, null);
INSERT INTO `sys_area` VALUES ('2118', '惠阳区', 'huiyangqu', 'hyq', '441300', '441303', null, null);
INSERT INTO `sys_area` VALUES ('2119', '博罗县', 'boluoxian', 'blx', '441300', '441322', null, null);
INSERT INTO `sys_area` VALUES ('2120', '惠东县', 'huidongxian', 'hdx', '441300', '441323', null, null);
INSERT INTO `sys_area` VALUES ('2121', '龙门县', 'longmenxian', 'lmx', '441300', '441324', null, null);
INSERT INTO `sys_area` VALUES ('2122', '梅州市', 'meizhoushi', 'mzs', '440000', '441400', null, null);
INSERT INTO `sys_area` VALUES ('2123', '梅江区', 'meijiangqu', 'mjq', '441400', '441402', null, null);
INSERT INTO `sys_area` VALUES ('2124', '梅县区', 'meixianqu', 'mxq', '441400', '441403', null, null);
INSERT INTO `sys_area` VALUES ('2125', '大埔县', 'dapuxian', 'dpx', '441400', '441422', null, null);
INSERT INTO `sys_area` VALUES ('2126', '丰顺县', 'fengshunxian', 'fsx', '441400', '441423', null, null);
INSERT INTO `sys_area` VALUES ('2127', '五华县', 'wuhuaxian', 'whx', '441400', '441424', null, null);
INSERT INTO `sys_area` VALUES ('2128', '平远县', 'pingyuanxian', 'pyx', '441400', '441426', null, null);
INSERT INTO `sys_area` VALUES ('2129', '蕉岭县', 'jiaolingxian', 'jlx', '441400', '441427', null, null);
INSERT INTO `sys_area` VALUES ('2130', '兴宁市', 'xingningshi', 'xns', '441400', '441481', null, null);
INSERT INTO `sys_area` VALUES ('2131', '汕尾市', 'shanweishi', 'sws', '440000', '441500', null, null);
INSERT INTO `sys_area` VALUES ('2132', '城区', 'chengqu', 'cq', '441500', '441502', null, null);
INSERT INTO `sys_area` VALUES ('2133', '海丰县', 'haifengxian', 'hfx', '441500', '441521', null, null);
INSERT INTO `sys_area` VALUES ('2134', '陆河县', 'luhexian', 'lhx', '441500', '441523', null, null);
INSERT INTO `sys_area` VALUES ('2135', '陆丰市', 'lufengshi', 'lfs', '441500', '441581', null, null);
INSERT INTO `sys_area` VALUES ('2136', '河源市', 'heyuanshi', 'hys', '440000', '441600', null, null);
INSERT INTO `sys_area` VALUES ('2137', '源城区', 'yuanchengqu', 'ycq', '441600', '441602', null, null);
INSERT INTO `sys_area` VALUES ('2138', '紫金县', 'zijinxian', 'zjx', '441600', '441621', null, null);
INSERT INTO `sys_area` VALUES ('2139', '龙川县', 'longchuanxian', 'lcx', '441600', '441622', null, null);
INSERT INTO `sys_area` VALUES ('2140', '连平县', 'lianpingxian', 'lpx', '441600', '441623', null, null);
INSERT INTO `sys_area` VALUES ('2141', '和平县', 'hepingxian', 'hpx', '441600', '441624', null, null);
INSERT INTO `sys_area` VALUES ('2142', '东源县', 'dongyuanxian', 'dyx', '441600', '441625', null, null);
INSERT INTO `sys_area` VALUES ('2143', '阳江市', 'yangjiangshi', 'yjs', '440000', '441700', null, null);
INSERT INTO `sys_area` VALUES ('2144', '江城区', 'jiangchengqu', 'jcq', '441700', '441702', null, null);
INSERT INTO `sys_area` VALUES ('2145', '阳东区', 'yangdongqu', 'ydq', '441700', '441704', null, null);
INSERT INTO `sys_area` VALUES ('2146', '阳西县', 'yangxixian', 'yxx', '441700', '441721', null, null);
INSERT INTO `sys_area` VALUES ('2147', '阳春市', 'yangchunshi', 'ycs', '441700', '441781', null, null);
INSERT INTO `sys_area` VALUES ('2148', '清远市', 'qingyuanshi', 'qys', '440000', '441800', null, null);
INSERT INTO `sys_area` VALUES ('2149', '清城区', 'qingchengqu', 'qcq', '441800', '441802', null, null);
INSERT INTO `sys_area` VALUES ('2150', '清新区', 'qingxinqu', 'qxq', '441800', '441803', null, null);
INSERT INTO `sys_area` VALUES ('2151', '佛冈县', 'fogangxian', 'fgx', '441800', '441821', null, null);
INSERT INTO `sys_area` VALUES ('2152', '阳山县', 'yangshanxian', 'ysx', '441800', '441823', null, null);
INSERT INTO `sys_area` VALUES ('2153', '连山壮族瑶族自治县', 'lianshanzhuangzuyaozuzizhixian', 'lszzyzzzx', '441800', '441825', null, null);
INSERT INTO `sys_area` VALUES ('2154', '连南瑶族自治县', 'liannanyaozuzizhixian', 'lnyzzzx', '441800', '441826', null, null);
INSERT INTO `sys_area` VALUES ('2155', '英德市', 'yingdeshi', 'yds', '441800', '441881', null, null);
INSERT INTO `sys_area` VALUES ('2156', '连州市', 'lianzhoushi', 'lzs', '441800', '441882', null, null);
INSERT INTO `sys_area` VALUES ('2157', '东莞市', 'dongzuoshi', 'd莞s', '440000', '441900', null, null);
INSERT INTO `sys_area` VALUES ('2158', '中山市', 'zhongshanshi', 'zss', '440000', '442000', null, null);
INSERT INTO `sys_area` VALUES ('2159', '潮州市', 'chaozhoushi', 'czs', '440000', '445100', null, null);
INSERT INTO `sys_area` VALUES ('2160', '湘桥区', 'xiangqiaoqu', 'xqq', '445100', '445102', null, null);
INSERT INTO `sys_area` VALUES ('2161', '潮安区', 'chaoanqu', 'caq', '445100', '445103', null, null);
INSERT INTO `sys_area` VALUES ('2162', '饶平县', 'raopingxian', 'rpx', '445100', '445122', null, null);
INSERT INTO `sys_area` VALUES ('2163', '揭阳市', 'jieyangshi', 'jys', '440000', '445200', null, null);
INSERT INTO `sys_area` VALUES ('2164', '榕城区', 'zuochengqu', '榕cq', '445200', '445202', null, null);
INSERT INTO `sys_area` VALUES ('2165', '揭东区', 'jiedongqu', 'jdq', '445200', '445203', null, null);
INSERT INTO `sys_area` VALUES ('2166', '揭西县', 'jiexixian', 'jxx', '445200', '445222', null, null);
INSERT INTO `sys_area` VALUES ('2167', '惠来县', 'huilaixian', 'hlx', '445200', '445224', null, null);
INSERT INTO `sys_area` VALUES ('2168', '普宁市', 'puningshi', 'pns', '445200', '445281', null, null);
INSERT INTO `sys_area` VALUES ('2169', '云浮市', 'yunfushi', 'yfs', '440000', '445300', null, null);
INSERT INTO `sys_area` VALUES ('2170', '云城区', 'yunchengqu', 'ycq', '445300', '445302', null, null);
INSERT INTO `sys_area` VALUES ('2171', '云安区', 'yunanqu', 'yaq', '445300', '445303', null, null);
INSERT INTO `sys_area` VALUES ('2172', '新兴县', 'xinxingxian', 'xxx', '445300', '445321', null, null);
INSERT INTO `sys_area` VALUES ('2173', '郁南县', 'yunanxian', 'ynx', '445300', '445322', null, null);
INSERT INTO `sys_area` VALUES ('2174', '罗定市', 'luodingshi', 'lds', '445300', '445381', null, null);
INSERT INTO `sys_area` VALUES ('2175', '广西壮族自治区', 'guangxizhuangzuzizhiqu', 'gxzzzzq', '0', '450000', null, null);
INSERT INTO `sys_area` VALUES ('2176', '南宁市', 'nanningshi', 'nns', '450000', '450100', null, null);
INSERT INTO `sys_area` VALUES ('2177', '兴宁区', 'xingningqu', 'xnq', '450100', '450102', null, null);
INSERT INTO `sys_area` VALUES ('2178', '青秀区', 'qingxiuqu', 'qxq', '450100', '450103', null, null);
INSERT INTO `sys_area` VALUES ('2179', '江南区', 'jiangnanqu', 'jnq', '450100', '450105', null, null);
INSERT INTO `sys_area` VALUES ('2180', '西乡塘区', 'xixiangtangqu', 'xxtq', '450100', '450107', null, null);
INSERT INTO `sys_area` VALUES ('2181', '良庆区', 'liangqingqu', 'lqq', '450100', '450108', null, null);
INSERT INTO `sys_area` VALUES ('2182', '邕宁区', 'zuoningqu', '邕nq', '450100', '450109', null, null);
INSERT INTO `sys_area` VALUES ('2183', '武鸣区', 'wumingqu', 'wmq', '450100', '450110', null, null);
INSERT INTO `sys_area` VALUES ('2184', '隆安县', 'longanxian', 'lax', '450100', '450123', null, null);
INSERT INTO `sys_area` VALUES ('2185', '马山县', 'mashanxian', 'msx', '450100', '450124', null, null);
INSERT INTO `sys_area` VALUES ('2186', '上林县', 'shanglinxian', 'slx', '450100', '450125', null, null);
INSERT INTO `sys_area` VALUES ('2187', '宾阳县', 'binyangxian', 'byx', '450100', '450126', null, null);
INSERT INTO `sys_area` VALUES ('2188', '横县', 'hengxian', 'hx', '450100', '450127', null, null);
INSERT INTO `sys_area` VALUES ('2189', '柳州市', 'liuzhoushi', 'lzs', '450000', '450200', null, null);
INSERT INTO `sys_area` VALUES ('2190', '城中区', 'chengzhongqu', 'czq', '450200', '450202', null, null);
INSERT INTO `sys_area` VALUES ('2191', '鱼峰区', 'yufengqu', 'yfq', '450200', '450203', null, null);
INSERT INTO `sys_area` VALUES ('2192', '柳南区', 'liunanqu', 'lnq', '450200', '450204', null, null);
INSERT INTO `sys_area` VALUES ('2193', '柳北区', 'liubeiqu', 'lbq', '450200', '450205', null, null);
INSERT INTO `sys_area` VALUES ('2194', '柳江区', 'liujiangqu', 'ljq', '450200', '450206', null, null);
INSERT INTO `sys_area` VALUES ('2195', '柳城县', 'liuchengxian', 'lcx', '450200', '450222', null, null);
INSERT INTO `sys_area` VALUES ('2196', '鹿寨县', 'luzhaixian', 'lzx', '450200', '450223', null, null);
INSERT INTO `sys_area` VALUES ('2197', '融安县', 'ronganxian', 'rax', '450200', '450224', null, null);
INSERT INTO `sys_area` VALUES ('2198', '融水苗族自治县', 'rongshuimiaozuzizhixian', 'rsmzzzx', '450200', '450225', null, null);
INSERT INTO `sys_area` VALUES ('2199', '三江侗族自治县', 'sanjiangdongzuzizhixian', 'sjdzzzx', '450200', '450226', null, null);
INSERT INTO `sys_area` VALUES ('2200', '桂林市', 'guilinshi', 'gls', '450000', '450300', null, null);
INSERT INTO `sys_area` VALUES ('2201', '秀峰区', 'xiufengqu', 'xfq', '450300', '450302', null, null);
INSERT INTO `sys_area` VALUES ('2202', '叠彩区', 'diecaiqu', 'dcq', '450300', '450303', null, null);
INSERT INTO `sys_area` VALUES ('2203', '象山区', 'xiangshanqu', 'xsq', '450300', '450304', null, null);
INSERT INTO `sys_area` VALUES ('2204', '七星区', 'qixingqu', 'qxq', '450300', '450305', null, null);
INSERT INTO `sys_area` VALUES ('2205', '雁山区', 'yanshanqu', 'ysq', '450300', '450311', null, null);
INSERT INTO `sys_area` VALUES ('2206', '临桂区', 'linguiqu', 'lgq', '450300', '450312', null, null);
INSERT INTO `sys_area` VALUES ('2207', '阳朔县', 'yangshuoxian', 'ysx', '450300', '450321', null, null);
INSERT INTO `sys_area` VALUES ('2208', '灵川县', 'lingchuanxian', 'lcx', '450300', '450323', null, null);
INSERT INTO `sys_area` VALUES ('2209', '全州县', 'quanzhouxian', 'qzx', '450300', '450324', null, null);
INSERT INTO `sys_area` VALUES ('2210', '兴安县', 'xinganxian', 'xax', '450300', '450325', null, null);
INSERT INTO `sys_area` VALUES ('2211', '永福县', 'yongfuxian', 'yfx', '450300', '450326', null, null);
INSERT INTO `sys_area` VALUES ('2212', '灌阳县', 'guanyangxian', 'gyx', '450300', '450327', null, null);
INSERT INTO `sys_area` VALUES ('2213', '龙胜各族自治县', 'longshenggezuzizhixian', 'lsgzzzx', '450300', '450328', null, null);
INSERT INTO `sys_area` VALUES ('2214', '资源县', 'ziyuanxian', 'zyx', '450300', '450329', null, null);
INSERT INTO `sys_area` VALUES ('2215', '平乐县', 'pinglexian', 'plx', '450300', '450330', null, null);
INSERT INTO `sys_area` VALUES ('2216', '荔浦县', 'lipuxian', 'lpx', '450300', '450331', null, null);
INSERT INTO `sys_area` VALUES ('2217', '恭城瑶族自治县', 'gongchengyaozuzizhixian', 'gcyzzzx', '450300', '450332', null, null);
INSERT INTO `sys_area` VALUES ('2218', '梧州市', 'wuzhoushi', 'wzs', '450000', '450400', null, null);
INSERT INTO `sys_area` VALUES ('2219', '万秀区', 'wanxiuqu', 'wxq', '450400', '450403', null, null);
INSERT INTO `sys_area` VALUES ('2220', '长洲区', 'changzhouqu', 'czq', '450400', '450405', null, null);
INSERT INTO `sys_area` VALUES ('2221', '龙圩区', 'longzuoqu', 'l圩q', '450400', '450406', null, null);
INSERT INTO `sys_area` VALUES ('2222', '苍梧县', 'cangwuxian', 'cwx', '450400', '450421', null, null);
INSERT INTO `sys_area` VALUES ('2223', '藤县', 'tengxian', 'tx', '450400', '450422', null, null);
INSERT INTO `sys_area` VALUES ('2224', '蒙山县', 'mengshanxian', 'msx', '450400', '450423', null, null);
INSERT INTO `sys_area` VALUES ('2225', '岑溪市', 'zuoxishi', '岑xs', '450400', '450481', null, null);
INSERT INTO `sys_area` VALUES ('2226', '北海市', 'beihaishi', 'bhs', '450000', '450500', null, null);
INSERT INTO `sys_area` VALUES ('2227', '海城区', 'haichengqu', 'hcq', '450500', '450502', null, null);
INSERT INTO `sys_area` VALUES ('2228', '银海区', 'yinhaiqu', 'yhq', '450500', '450503', null, null);
INSERT INTO `sys_area` VALUES ('2229', '铁山港区', 'tieshangangqu', 'tsgq', '450500', '450512', null, null);
INSERT INTO `sys_area` VALUES ('2230', '合浦县', 'hepuxian', 'hpx', '450500', '450521', null, null);
INSERT INTO `sys_area` VALUES ('2231', '防城港市', 'fangchenggangshi', 'fcgs', '450000', '450600', null, null);
INSERT INTO `sys_area` VALUES ('2232', '港口区', 'gangkouqu', 'gkq', '450600', '450602', null, null);
INSERT INTO `sys_area` VALUES ('2233', '防城区', 'fangchengqu', 'fcq', '450600', '450603', null, null);
INSERT INTO `sys_area` VALUES ('2234', '上思县', 'shangsixian', 'ssx', '450600', '450621', null, null);
INSERT INTO `sys_area` VALUES ('2235', '东兴市', 'dongxingshi', 'dxs', '450600', '450681', null, null);
INSERT INTO `sys_area` VALUES ('2236', '钦州市', 'qinzhoushi', 'qzs', '450000', '450700', null, null);
INSERT INTO `sys_area` VALUES ('2237', '钦南区', 'qinnanqu', 'qnq', '450700', '450702', null, null);
INSERT INTO `sys_area` VALUES ('2238', '钦北区', 'qinbeiqu', 'qbq', '450700', '450703', null, null);
INSERT INTO `sys_area` VALUES ('2239', '灵山县', 'lingshanxian', 'lsx', '450700', '450721', null, null);
INSERT INTO `sys_area` VALUES ('2240', '浦北县', 'pubeixian', 'pbx', '450700', '450722', null, null);
INSERT INTO `sys_area` VALUES ('2241', '贵港市', 'guigangshi', 'ggs', '450000', '450800', null, null);
INSERT INTO `sys_area` VALUES ('2242', '港北区', 'gangbeiqu', 'gbq', '450800', '450802', null, null);
INSERT INTO `sys_area` VALUES ('2243', '港南区', 'gangnanqu', 'gnq', '450800', '450803', null, null);
INSERT INTO `sys_area` VALUES ('2244', '覃塘区', 'zuotangqu', '覃tq', '450800', '450804', null, null);
INSERT INTO `sys_area` VALUES ('2245', '平南县', 'pingnanxian', 'pnx', '450800', '450821', null, null);
INSERT INTO `sys_area` VALUES ('2246', '桂平市', 'guipingshi', 'gps', '450800', '450881', null, null);
INSERT INTO `sys_area` VALUES ('2247', '玉林市', 'yulinshi', 'yls', '450000', '450900', null, null);
INSERT INTO `sys_area` VALUES ('2248', '玉州区', 'yuzhouqu', 'yzq', '450900', '450902', null, null);
INSERT INTO `sys_area` VALUES ('2249', '福绵区', 'fumianqu', 'fmq', '450900', '450903', null, null);
INSERT INTO `sys_area` VALUES ('2250', '容县', 'rongxian', 'rx', '450900', '450921', null, null);
INSERT INTO `sys_area` VALUES ('2251', '陆川县', 'luchuanxian', 'lcx', '450900', '450922', null, null);
INSERT INTO `sys_area` VALUES ('2252', '博白县', 'bobaixian', 'bbx', '450900', '450923', null, null);
INSERT INTO `sys_area` VALUES ('2253', '兴业县', 'xingyexian', 'xyx', '450900', '450924', null, null);
INSERT INTO `sys_area` VALUES ('2254', '北流市', 'beiliushi', 'bls', '450900', '450981', null, null);
INSERT INTO `sys_area` VALUES ('2255', '百色市', 'baiseshi', 'bss', '450000', '451000', null, null);
INSERT INTO `sys_area` VALUES ('2256', '右江区', 'youjiangqu', 'yjq', '451000', '451002', null, null);
INSERT INTO `sys_area` VALUES ('2257', '田阳县', 'tianyangxian', 'tyx', '451000', '451021', null, null);
INSERT INTO `sys_area` VALUES ('2258', '田东县', 'tiandongxian', 'tdx', '451000', '451022', null, null);
INSERT INTO `sys_area` VALUES ('2259', '平果县', 'pingguoxian', 'pgx', '451000', '451023', null, null);
INSERT INTO `sys_area` VALUES ('2260', '德保县', 'debaoxian', 'dbx', '451000', '451024', null, null);
INSERT INTO `sys_area` VALUES ('2261', '那坡县', 'napoxian', 'npx', '451000', '451026', null, null);
INSERT INTO `sys_area` VALUES ('2262', '凌云县', 'lingyunxian', 'lyx', '451000', '451027', null, null);
INSERT INTO `sys_area` VALUES ('2263', '乐业县', 'leyexian', 'lyx', '451000', '451028', null, null);
INSERT INTO `sys_area` VALUES ('2264', '田林县', 'tianlinxian', 'tlx', '451000', '451029', null, null);
INSERT INTO `sys_area` VALUES ('2265', '西林县', 'xilinxian', 'xlx', '451000', '451030', null, null);
INSERT INTO `sys_area` VALUES ('2266', '隆林各族自治县', 'longlingezuzizhixian', 'llgzzzx', '451000', '451031', null, null);
INSERT INTO `sys_area` VALUES ('2267', '靖西市', 'jingxishi', 'jxs', '451000', '451081', null, null);
INSERT INTO `sys_area` VALUES ('2268', '贺州市', 'hezhoushi', 'hzs', '450000', '451100', null, null);
INSERT INTO `sys_area` VALUES ('2269', '八步区', 'babuqu', 'bbq', '451100', '451102', null, null);
INSERT INTO `sys_area` VALUES ('2270', '平桂区', 'pingguiqu', 'pgq', '451100', '451103', null, null);
INSERT INTO `sys_area` VALUES ('2271', '昭平县', 'zhaopingxian', 'zpx', '451100', '451121', null, null);
INSERT INTO `sys_area` VALUES ('2272', '钟山县', 'zhongshanxian', 'zsx', '451100', '451122', null, null);
INSERT INTO `sys_area` VALUES ('2273', '富川瑶族自治县', 'fuchuanyaozuzizhixian', 'fcyzzzx', '451100', '451123', null, null);
INSERT INTO `sys_area` VALUES ('2274', '河池市', 'hechishi', 'hcs', '450000', '451200', null, null);
INSERT INTO `sys_area` VALUES ('2275', '金城江区', 'jinchengjiangqu', 'jcjq', '451200', '451202', null, null);
INSERT INTO `sys_area` VALUES ('2276', '宜州区', 'yizhouqu', 'yzq', '451200', '451203', null, null);
INSERT INTO `sys_area` VALUES ('2277', '南丹县', 'nandanxian', 'ndx', '451200', '451221', null, null);
INSERT INTO `sys_area` VALUES ('2278', '天峨县', 'tianexian', 'tex', '451200', '451222', null, null);
INSERT INTO `sys_area` VALUES ('2279', '凤山县', 'fengshanxian', 'fsx', '451200', '451223', null, null);
INSERT INTO `sys_area` VALUES ('2280', '东兰县', 'donglanxian', 'dlx', '451200', '451224', null, null);
INSERT INTO `sys_area` VALUES ('2281', '罗城仫佬族自治县', 'luochengzuolaozuzizhixian', 'lc仫lzzzx', '451200', '451225', null, null);
INSERT INTO `sys_area` VALUES ('2282', '环江毛南族自治县', 'huanjiangmaonanzuzizhixian', 'hjmnzzzx', '451200', '451226', null, null);
INSERT INTO `sys_area` VALUES ('2283', '巴马瑶族自治县', 'bamayaozuzizhixian', 'bmyzzzx', '451200', '451227', null, null);
INSERT INTO `sys_area` VALUES ('2284', '都安瑶族自治县', 'duanyaozuzizhixian', 'dayzzzx', '451200', '451228', null, null);
INSERT INTO `sys_area` VALUES ('2285', '大化瑶族自治县', 'dahuayaozuzizhixian', 'dhyzzzx', '451200', '451229', null, null);
INSERT INTO `sys_area` VALUES ('2286', '来宾市', 'laibinshi', 'lbs', '450000', '451300', null, null);
INSERT INTO `sys_area` VALUES ('2287', '兴宾区', 'xingbinqu', 'xbq', '451300', '451302', null, null);
INSERT INTO `sys_area` VALUES ('2288', '忻城县', 'xinchengxian', 'xcx', '451300', '451321', null, null);
INSERT INTO `sys_area` VALUES ('2289', '象州县', 'xiangzhouxian', 'xzx', '451300', '451322', null, null);
INSERT INTO `sys_area` VALUES ('2290', '武宣县', 'wuxuanxian', 'wxx', '451300', '451323', null, null);
INSERT INTO `sys_area` VALUES ('2291', '金秀瑶族自治县', 'jinxiuyaozuzizhixian', 'jxyzzzx', '451300', '451324', null, null);
INSERT INTO `sys_area` VALUES ('2292', '合山市', 'heshanshi', 'hss', '451300', '451381', null, null);
INSERT INTO `sys_area` VALUES ('2293', '崇左市', 'chongzuoshi', 'czs', '450000', '451400', null, null);
INSERT INTO `sys_area` VALUES ('2294', '江州区', 'jiangzhouqu', 'jzq', '451400', '451402', null, null);
INSERT INTO `sys_area` VALUES ('2295', '扶绥县', 'fusuixian', 'fsx', '451400', '451421', null, null);
INSERT INTO `sys_area` VALUES ('2296', '宁明县', 'ningmingxian', 'nmx', '451400', '451422', null, null);
INSERT INTO `sys_area` VALUES ('2297', '龙州县', 'longzhouxian', 'lzx', '451400', '451423', null, null);
INSERT INTO `sys_area` VALUES ('2298', '大新县', 'daxinxian', 'dxx', '451400', '451424', null, null);
INSERT INTO `sys_area` VALUES ('2299', '天等县', 'tiandengxian', 'tdx', '451400', '451425', null, null);
INSERT INTO `sys_area` VALUES ('2300', '凭祥市', 'pingxiangshi', 'pxs', '451400', '451481', null, null);
INSERT INTO `sys_area` VALUES ('2301', '海南省', 'hainansheng', 'hns', '0', '460000', null, null);
INSERT INTO `sys_area` VALUES ('2302', '海口市', 'haikoushi', 'hks', '460000', '460100', null, null);
INSERT INTO `sys_area` VALUES ('2303', '秀英区', 'xiuyingqu', 'xyq', '460100', '460105', null, null);
INSERT INTO `sys_area` VALUES ('2304', '龙华区', 'longhuaqu', 'lhq', '460100', '460106', null, null);
INSERT INTO `sys_area` VALUES ('2305', '琼山区', 'qiongshanqu', 'qsq', '460100', '460107', null, null);
INSERT INTO `sys_area` VALUES ('2306', '美兰区', 'meilanqu', 'mlq', '460100', '460108', null, null);
INSERT INTO `sys_area` VALUES ('2307', '三亚市', 'sanyashi', 'sys', '460000', '460200', null, null);
INSERT INTO `sys_area` VALUES ('2308', '海棠区', 'haitangqu', 'htq', '460200', '460202', null, null);
INSERT INTO `sys_area` VALUES ('2309', '吉阳区', 'jiyangqu', 'jyq', '460200', '460203', null, null);
INSERT INTO `sys_area` VALUES ('2310', '天涯区', 'tianyaqu', 'tyq', '460200', '460204', null, null);
INSERT INTO `sys_area` VALUES ('2311', '崖州区', 'yazhouqu', 'yzq', '460200', '460205', null, null);
INSERT INTO `sys_area` VALUES ('2312', '三沙市', 'sanshashi', 'sss', '460000', '460300', null, null);
INSERT INTO `sys_area` VALUES ('2313', '儋州市', 'zuozhoushi', '儋zs', '460000', '460400', null, null);
INSERT INTO `sys_area` VALUES ('2314', '五指山市', 'wuzhishanshi', 'wzss', '460000', '469001', null, null);
INSERT INTO `sys_area` VALUES ('2315', '琼海市', 'qionghaishi', 'qhs', '460000', '469002', null, null);
INSERT INTO `sys_area` VALUES ('2316', '文昌市', 'wenchangshi', 'wcs', '460000', '469005', null, null);
INSERT INTO `sys_area` VALUES ('2317', '万宁市', 'wanningshi', 'wns', '460000', '469006', null, null);
INSERT INTO `sys_area` VALUES ('2318', '东方市', 'dongfangshi', 'dfs', '460000', '469007', null, null);
INSERT INTO `sys_area` VALUES ('2319', '定安县', 'dinganxian', 'dax', '460000', '469021', null, null);
INSERT INTO `sys_area` VALUES ('2320', '屯昌县', 'tunchangxian', 'tcx', '460000', '469022', null, null);
INSERT INTO `sys_area` VALUES ('2321', '澄迈县', 'chengmaixian', 'cmx', '460000', '469023', null, null);
INSERT INTO `sys_area` VALUES ('2322', '临高县', 'lingaoxian', 'lgx', '460000', '469024', null, null);
INSERT INTO `sys_area` VALUES ('2323', '白沙黎族自治县', 'baishalizuzizhixian', 'bslzzzx', '460000', '469025', null, null);
INSERT INTO `sys_area` VALUES ('2324', '昌江黎族自治县', 'changjianglizuzizhixian', 'cjlzzzx', '460000', '469026', null, null);
INSERT INTO `sys_area` VALUES ('2325', '乐东黎族自治县', 'ledonglizuzizhixian', 'ldlzzzx', '460000', '469027', null, null);
INSERT INTO `sys_area` VALUES ('2326', '陵水黎族自治县', 'lingshuilizuzizhixian', 'lslzzzx', '460000', '469028', null, null);
INSERT INTO `sys_area` VALUES ('2327', '保亭黎族苗族自治县', 'baotinglizumiaozuzizhixian', 'btlzmzzzx', '460000', '469029', null, null);
INSERT INTO `sys_area` VALUES ('2328', '琼中黎族苗族自治县', 'qiongzhonglizumiaozuzizhixian', 'qzlzmzzzx', '460000', '469030', null, null);
INSERT INTO `sys_area` VALUES ('2329', '重庆市', 'zhongqingshi', 'zqs', '0', '500000', null, null);
INSERT INTO `sys_area` VALUES ('2330', '万州区', 'wanzhouqu', 'wzq', '500000', '500101', null, null);
INSERT INTO `sys_area` VALUES ('2331', '涪陵区', 'fulingqu', 'flq', '500000', '500102', null, null);
INSERT INTO `sys_area` VALUES ('2332', '渝中区', 'yuzhongqu', 'yzq', '500000', '500103', null, null);
INSERT INTO `sys_area` VALUES ('2333', '大渡口区', 'dadukouqu', 'ddkq', '500000', '500104', null, null);
INSERT INTO `sys_area` VALUES ('2334', '江北区', 'jiangbeiqu', 'jbq', '500000', '500105', null, null);
INSERT INTO `sys_area` VALUES ('2335', '沙坪坝区', 'shapingbaqu', 'spbq', '500000', '500106', null, null);
INSERT INTO `sys_area` VALUES ('2336', '九龙坡区', 'jiulongpoqu', 'jlpq', '500000', '500107', null, null);
INSERT INTO `sys_area` VALUES ('2337', '南岸区', 'nananqu', 'naq', '500000', '500108', null, null);
INSERT INTO `sys_area` VALUES ('2338', '北碚区', 'beizuoqu', 'b碚q', '500000', '500109', null, null);
INSERT INTO `sys_area` VALUES ('2339', '綦江区', 'zuojiangqu', '綦jq', '500000', '500110', null, null);
INSERT INTO `sys_area` VALUES ('2340', '大足区', 'dazuqu', 'dzq', '500000', '500111', null, null);
INSERT INTO `sys_area` VALUES ('2341', '渝北区', 'yubeiqu', 'ybq', '500000', '500112', null, null);
INSERT INTO `sys_area` VALUES ('2342', '巴南区', 'bananqu', 'bnq', '500000', '500113', null, null);
INSERT INTO `sys_area` VALUES ('2343', '黔江区', 'qianjiangqu', 'qjq', '500000', '500114', null, null);
INSERT INTO `sys_area` VALUES ('2344', '长寿区', 'changshouqu', 'csq', '500000', '500115', null, null);
INSERT INTO `sys_area` VALUES ('2345', '江津区', 'jiangjinqu', 'jjq', '500000', '500116', null, null);
INSERT INTO `sys_area` VALUES ('2346', '合川区', 'hechuanqu', 'hcq', '500000', '500117', null, null);
INSERT INTO `sys_area` VALUES ('2347', '永川区', 'yongchuanqu', 'ycq', '500000', '500118', null, null);
INSERT INTO `sys_area` VALUES ('2348', '南川区', 'nanchuanqu', 'ncq', '500000', '500119', null, null);
INSERT INTO `sys_area` VALUES ('2349', '璧山区', 'zuoshanqu', '璧sq', '500000', '500120', null, null);
INSERT INTO `sys_area` VALUES ('2350', '铜梁区', 'tongliangqu', 'tlq', '500000', '500151', null, null);
INSERT INTO `sys_area` VALUES ('2351', '潼南区', 'zuonanqu', '潼nq', '500000', '500152', null, null);
INSERT INTO `sys_area` VALUES ('2352', '荣昌区', 'rongchangqu', 'rcq', '500000', '500153', null, null);
INSERT INTO `sys_area` VALUES ('2353', '开州区', 'kaizhouqu', 'kzq', '500000', '500154', null, null);
INSERT INTO `sys_area` VALUES ('2354', '梁平区', 'liangpingqu', 'lpq', '500000', '500155', null, null);
INSERT INTO `sys_area` VALUES ('2355', '武隆区', 'wulongqu', 'wlq', '500000', '500156', null, null);
INSERT INTO `sys_area` VALUES ('2356', '城口县', 'chengkouxian', 'ckx', '500000', '500229', null, null);
INSERT INTO `sys_area` VALUES ('2357', '丰都县', 'fengduxian', 'fdx', '500000', '500230', null, null);
INSERT INTO `sys_area` VALUES ('2358', '垫江县', 'dianjiangxian', 'djx', '500000', '500231', null, null);
INSERT INTO `sys_area` VALUES ('2359', '忠县', 'zhongxian', 'zx', '500000', '500233', null, null);
INSERT INTO `sys_area` VALUES ('2360', '云阳县', 'yunyangxian', 'yyx', '500000', '500235', null, null);
INSERT INTO `sys_area` VALUES ('2361', '奉节县', 'fengjiexian', 'fjx', '500000', '500236', null, null);
INSERT INTO `sys_area` VALUES ('2362', '巫山县', 'wushanxian', 'wsx', '500000', '500237', null, null);
INSERT INTO `sys_area` VALUES ('2363', '巫溪县', 'wuxixian', 'wxx', '500000', '500238', null, null);
INSERT INTO `sys_area` VALUES ('2364', '石柱土家族自治县', 'shizhutujiazuzizhixian', 'sztjzzzx', '500000', '500240', null, null);
INSERT INTO `sys_area` VALUES ('2365', '秀山土家族苗族自治县', 'xiushantujiazumiaozuzizhixian', 'xstjzmzzzx', '500000', '500241', null, null);
INSERT INTO `sys_area` VALUES ('2366', '酉阳土家族苗族自治县', 'youyangtujiazumiaozuzizhixian', 'yytjzmzzzx', '500000', '500242', null, null);
INSERT INTO `sys_area` VALUES ('2367', '彭水苗族土家族自治县', 'pengshuimiaozutujiazuzizhixian', 'psmztjzzzx', '500000', '500243', null, null);
INSERT INTO `sys_area` VALUES ('2368', '四川省', 'sichuansheng', 'scs', '0', '510000', null, null);
INSERT INTO `sys_area` VALUES ('2369', '成都市', 'chengdushi', 'cds', '510000', '510100', null, null);
INSERT INTO `sys_area` VALUES ('2370', '锦江区', 'jinjiangqu', 'jjq', '510100', '510104', null, null);
INSERT INTO `sys_area` VALUES ('2371', '青羊区', 'qingyangqu', 'qyq', '510100', '510105', null, null);
INSERT INTO `sys_area` VALUES ('2372', '金牛区', 'jinniuqu', 'jnq', '510100', '510106', null, null);
INSERT INTO `sys_area` VALUES ('2373', '武侯区', 'wuhouqu', 'whq', '510100', '510107', null, null);
INSERT INTO `sys_area` VALUES ('2374', '成华区', 'chenghuaqu', 'chq', '510100', '510108', null, null);
INSERT INTO `sys_area` VALUES ('2375', '龙泉驿区', 'longquanzuoqu', 'lq驿q', '510100', '510112', null, null);
INSERT INTO `sys_area` VALUES ('2376', '青白江区', 'qingbaijiangqu', 'qbjq', '510100', '510113', null, null);
INSERT INTO `sys_area` VALUES ('2377', '新都区', 'xinduqu', 'xdq', '510100', '510114', null, null);
INSERT INTO `sys_area` VALUES ('2378', '温江区', 'wenjiangqu', 'wjq', '510100', '510115', null, null);
INSERT INTO `sys_area` VALUES ('2379', '双流区', 'shuangliuqu', 'slq', '510100', '510116', null, null);
INSERT INTO `sys_area` VALUES ('2380', '郫都区', 'zuoduqu', '郫dq', '510100', '510117', null, null);
INSERT INTO `sys_area` VALUES ('2381', '金堂县', 'jintangxian', 'jtx', '510100', '510121', null, null);
INSERT INTO `sys_area` VALUES ('2382', '大邑县', 'dayixian', 'dyx', '510100', '510129', null, null);
INSERT INTO `sys_area` VALUES ('2383', '蒲江县', 'pujiangxian', 'pjx', '510100', '510131', null, null);
INSERT INTO `sys_area` VALUES ('2384', '新津县', 'xinjinxian', 'xjx', '510100', '510132', null, null);
INSERT INTO `sys_area` VALUES ('2385', '都江堰市', 'dujiangyanshi', 'djys', '510100', '510181', null, null);
INSERT INTO `sys_area` VALUES ('2386', '彭州市', 'pengzhoushi', 'pzs', '510100', '510182', null, null);
INSERT INTO `sys_area` VALUES ('2387', '邛崃市', 'zuozuoshi', '邛崃s', '510100', '510183', null, null);
INSERT INTO `sys_area` VALUES ('2388', '崇州市', 'chongzhoushi', 'czs', '510100', '510184', null, null);
INSERT INTO `sys_area` VALUES ('2389', '简阳市', 'jianyangshi', 'jys', '510100', '510185', null, null);
INSERT INTO `sys_area` VALUES ('2390', '自贡市', 'zigongshi', 'zgs', '510000', '510300', null, null);
INSERT INTO `sys_area` VALUES ('2391', '自流井区', 'ziliujingqu', 'zljq', '510300', '510302', null, null);
INSERT INTO `sys_area` VALUES ('2392', '贡井区', 'gongjingqu', 'gjq', '510300', '510303', null, null);
INSERT INTO `sys_area` VALUES ('2393', '大安区', 'daanqu', 'daq', '510300', '510304', null, null);
INSERT INTO `sys_area` VALUES ('2394', '沿滩区', 'yantanqu', 'ytq', '510300', '510311', null, null);
INSERT INTO `sys_area` VALUES ('2395', '荣县', 'rongxian', 'rx', '510300', '510321', null, null);
INSERT INTO `sys_area` VALUES ('2396', '富顺县', 'fushunxian', 'fsx', '510300', '510322', null, null);
INSERT INTO `sys_area` VALUES ('2397', '攀枝花市', 'panzhihuashi', 'pzhs', '510000', '510400', null, null);
INSERT INTO `sys_area` VALUES ('2398', '东区', 'dongqu', 'dq', '510400', '510402', null, null);
INSERT INTO `sys_area` VALUES ('2399', '西区', 'xiqu', 'xq', '510400', '510403', null, null);
INSERT INTO `sys_area` VALUES ('2400', '仁和区', 'renhequ', 'rhq', '510400', '510411', null, null);
INSERT INTO `sys_area` VALUES ('2401', '米易县', 'miyixian', 'myx', '510400', '510421', null, null);
INSERT INTO `sys_area` VALUES ('2402', '盐边县', 'yanbianxian', 'ybx', '510400', '510422', null, null);
INSERT INTO `sys_area` VALUES ('2403', '泸州市', 'zuozhoushi', '泸zs', '510000', '510500', null, null);
INSERT INTO `sys_area` VALUES ('2404', '江阳区', 'jiangyangqu', 'jyq', '510500', '510502', null, null);
INSERT INTO `sys_area` VALUES ('2405', '纳溪区', 'naxiqu', 'nxq', '510500', '510503', null, null);
INSERT INTO `sys_area` VALUES ('2406', '龙马潭区', 'longmatanqu', 'lmtq', '510500', '510504', null, null);
INSERT INTO `sys_area` VALUES ('2407', '泸县', 'zuoxian', '泸x', '510500', '510521', null, null);
INSERT INTO `sys_area` VALUES ('2408', '合江县', 'hejiangxian', 'hjx', '510500', '510522', null, null);
INSERT INTO `sys_area` VALUES ('2409', '叙永县', 'xuyongxian', 'xyx', '510500', '510524', null, null);
INSERT INTO `sys_area` VALUES ('2410', '古蔺县', 'guzuoxian', 'g蔺x', '510500', '510525', null, null);
INSERT INTO `sys_area` VALUES ('2411', '德阳市', 'deyangshi', 'dys', '510000', '510600', null, null);
INSERT INTO `sys_area` VALUES ('2412', '旌阳区', 'zuoyangqu', '旌yq', '510600', '510603', null, null);
INSERT INTO `sys_area` VALUES ('2413', '罗江区', 'luojiangqu', 'ljq', '510600', '510604', null, null);
INSERT INTO `sys_area` VALUES ('2414', '中江县', 'zhongjiangxian', 'zjx', '510600', '510623', null, null);
INSERT INTO `sys_area` VALUES ('2415', '广汉市', 'guanghanshi', 'ghs', '510600', '510681', null, null);
INSERT INTO `sys_area` VALUES ('2416', '什邡市', 'shizuoshi', 's邡s', '510600', '510682', null, null);
INSERT INTO `sys_area` VALUES ('2417', '绵竹市', 'mianzhushi', 'mzs', '510600', '510683', null, null);
INSERT INTO `sys_area` VALUES ('2418', '绵阳市', 'mianyangshi', 'mys', '510000', '510700', null, null);
INSERT INTO `sys_area` VALUES ('2419', '涪城区', 'fuchengqu', 'fcq', '510700', '510703', null, null);
INSERT INTO `sys_area` VALUES ('2420', '游仙区', 'youxianqu', 'yxq', '510700', '510704', null, null);
INSERT INTO `sys_area` VALUES ('2421', '安州区', 'anzhouqu', 'azq', '510700', '510705', null, null);
INSERT INTO `sys_area` VALUES ('2422', '三台县', 'santaixian', 'stx', '510700', '510722', null, null);
INSERT INTO `sys_area` VALUES ('2423', '盐亭县', 'yantingxian', 'ytx', '510700', '510723', null, null);
INSERT INTO `sys_area` VALUES ('2424', '梓潼县', 'zuozuoxian', '梓潼x', '510700', '510725', null, null);
INSERT INTO `sys_area` VALUES ('2425', '北川羌族自治县', 'beichuanqiangzuzizhixian', 'bcqzzzx', '510700', '510726', null, null);
INSERT INTO `sys_area` VALUES ('2426', '平武县', 'pingwuxian', 'pwx', '510700', '510727', null, null);
INSERT INTO `sys_area` VALUES ('2427', '江油市', 'jiangyoushi', 'jys', '510700', '510781', null, null);
INSERT INTO `sys_area` VALUES ('2428', '广元市', 'guangyuanshi', 'gys', '510000', '510800', null, null);
INSERT INTO `sys_area` VALUES ('2429', '利州区', 'lizhouqu', 'lzq', '510800', '510802', null, null);
INSERT INTO `sys_area` VALUES ('2430', '昭化区', 'zhaohuaqu', 'zhq', '510800', '510811', null, null);
INSERT INTO `sys_area` VALUES ('2431', '朝天区', 'chaotianqu', 'ctq', '510800', '510812', null, null);
INSERT INTO `sys_area` VALUES ('2432', '旺苍县', 'wangcangxian', 'wcx', '510800', '510821', null, null);
INSERT INTO `sys_area` VALUES ('2433', '青川县', 'qingchuanxian', 'qcx', '510800', '510822', null, null);
INSERT INTO `sys_area` VALUES ('2434', '剑阁县', 'jiangexian', 'jgx', '510800', '510823', null, null);
INSERT INTO `sys_area` VALUES ('2435', '苍溪县', 'cangxixian', 'cxx', '510800', '510824', null, null);
INSERT INTO `sys_area` VALUES ('2436', '遂宁市', 'suiningshi', 'sns', '510000', '510900', null, null);
INSERT INTO `sys_area` VALUES ('2437', '船山区', 'chuanshanqu', 'csq', '510900', '510903', null, null);
INSERT INTO `sys_area` VALUES ('2438', '安居区', 'anjuqu', 'ajq', '510900', '510904', null, null);
INSERT INTO `sys_area` VALUES ('2439', '蓬溪县', 'pengxixian', 'pxx', '510900', '510921', null, null);
INSERT INTO `sys_area` VALUES ('2440', '射洪县', 'shehongxian', 'shx', '510900', '510922', null, null);
INSERT INTO `sys_area` VALUES ('2441', '大英县', 'dayingxian', 'dyx', '510900', '510923', null, null);
INSERT INTO `sys_area` VALUES ('2442', '内江市', 'neijiangshi', 'njs', '510000', '511000', null, null);
INSERT INTO `sys_area` VALUES ('2443', '市中区', 'shizhongqu', 'szq', '511000', '511002', null, null);
INSERT INTO `sys_area` VALUES ('2444', '东兴区', 'dongxingqu', 'dxq', '511000', '511011', null, null);
INSERT INTO `sys_area` VALUES ('2445', '威远县', 'weiyuanxian', 'wyx', '511000', '511024', null, null);
INSERT INTO `sys_area` VALUES ('2446', '资中县', 'zizhongxian', 'zzx', '511000', '511025', null, null);
INSERT INTO `sys_area` VALUES ('2447', '隆昌市', 'longchangshi', 'lcs', '511000', '511083', null, null);
INSERT INTO `sys_area` VALUES ('2448', '乐山市', 'leshanshi', 'lss', '510000', '511100', null, null);
INSERT INTO `sys_area` VALUES ('2449', '市中区', 'shizhongqu', 'szq', '511100', '511102', null, null);
INSERT INTO `sys_area` VALUES ('2450', '沙湾区', 'shawanqu', 'swq', '511100', '511111', null, null);
INSERT INTO `sys_area` VALUES ('2451', '五通桥区', 'wutongqiaoqu', 'wtqq', '511100', '511112', null, null);
INSERT INTO `sys_area` VALUES ('2452', '金口河区', 'jinkouhequ', 'jkhq', '511100', '511113', null, null);
INSERT INTO `sys_area` VALUES ('2453', '犍为县', 'zuoweixian', '犍wx', '511100', '511123', null, null);
INSERT INTO `sys_area` VALUES ('2454', '井研县', 'jingyanxian', 'jyx', '511100', '511124', null, null);
INSERT INTO `sys_area` VALUES ('2455', '夹江县', 'jiajiangxian', 'jjx', '511100', '511126', null, null);
INSERT INTO `sys_area` VALUES ('2456', '沐川县', 'zuochuanxian', '沐cx', '511100', '511129', null, null);
INSERT INTO `sys_area` VALUES ('2457', '峨边彝族自治县', 'ebianyizuzizhixian', 'ebyzzzx', '511100', '511132', null, null);
INSERT INTO `sys_area` VALUES ('2458', '马边彝族自治县', 'mabianyizuzizhixian', 'mbyzzzx', '511100', '511133', null, null);
INSERT INTO `sys_area` VALUES ('2459', '峨眉山市', 'emeishanshi', 'emss', '511100', '511181', null, null);
INSERT INTO `sys_area` VALUES ('2460', '南充市', 'nanchongshi', 'ncs', '510000', '511300', null, null);
INSERT INTO `sys_area` VALUES ('2461', '顺庆区', 'shunqingqu', 'sqq', '511300', '511302', null, null);
INSERT INTO `sys_area` VALUES ('2462', '高坪区', 'gaopingqu', 'gpq', '511300', '511303', null, null);
INSERT INTO `sys_area` VALUES ('2463', '嘉陵区', 'jialingqu', 'jlq', '511300', '511304', null, null);
INSERT INTO `sys_area` VALUES ('2464', '南部县', 'nanbuxian', 'nbx', '511300', '511321', null, null);
INSERT INTO `sys_area` VALUES ('2465', '营山县', 'yingshanxian', 'ysx', '511300', '511322', null, null);
INSERT INTO `sys_area` VALUES ('2466', '蓬安县', 'penganxian', 'pax', '511300', '511323', null, null);
INSERT INTO `sys_area` VALUES ('2467', '仪陇县', 'yilongxian', 'ylx', '511300', '511324', null, null);
INSERT INTO `sys_area` VALUES ('2468', '西充县', 'xichongxian', 'xcx', '511300', '511325', null, null);
INSERT INTO `sys_area` VALUES ('2469', '阆中市', 'zuozhongshi', '阆zs', '511300', '511381', null, null);
INSERT INTO `sys_area` VALUES ('2470', '眉山市', 'meishanshi', 'mss', '510000', '511400', null, null);
INSERT INTO `sys_area` VALUES ('2471', '东坡区', 'dongpoqu', 'dpq', '511400', '511402', null, null);
INSERT INTO `sys_area` VALUES ('2472', '彭山区', 'pengshanqu', 'psq', '511400', '511403', null, null);
INSERT INTO `sys_area` VALUES ('2473', '仁寿县', 'renshouxian', 'rsx', '511400', '511421', null, null);
INSERT INTO `sys_area` VALUES ('2474', '洪雅县', 'hongyaxian', 'hyx', '511400', '511423', null, null);
INSERT INTO `sys_area` VALUES ('2475', '丹棱县', 'danlengxian', 'dlx', '511400', '511424', null, null);
INSERT INTO `sys_area` VALUES ('2476', '青神县', 'qingshenxian', 'qsx', '511400', '511425', null, null);
INSERT INTO `sys_area` VALUES ('2477', '宜宾市', 'yibinshi', 'ybs', '510000', '511500', null, null);
INSERT INTO `sys_area` VALUES ('2478', '翠屏区', 'cuipingqu', 'cpq', '511500', '511502', null, null);
INSERT INTO `sys_area` VALUES ('2479', '南溪区', 'nanxiqu', 'nxq', '511500', '511503', null, null);
INSERT INTO `sys_area` VALUES ('2480', '宜宾县', 'yibinxian', 'ybx', '511500', '511521', null, null);
INSERT INTO `sys_area` VALUES ('2481', '江安县', 'jianganxian', 'jax', '511500', '511523', null, null);
INSERT INTO `sys_area` VALUES ('2482', '长宁县', 'changningxian', 'cnx', '511500', '511524', null, null);
INSERT INTO `sys_area` VALUES ('2483', '高县', 'gaoxian', 'gx', '511500', '511525', null, null);
INSERT INTO `sys_area` VALUES ('2484', '珙县', 'zuoxian', '珙x', '511500', '511526', null, null);
INSERT INTO `sys_area` VALUES ('2485', '筠连县', 'zuolianxian', '筠lx', '511500', '511527', null, null);
INSERT INTO `sys_area` VALUES ('2486', '兴文县', 'xingwenxian', 'xwx', '511500', '511528', null, null);
INSERT INTO `sys_area` VALUES ('2487', '屏山县', 'pingshanxian', 'psx', '511500', '511529', null, null);
INSERT INTO `sys_area` VALUES ('2488', '广安市', 'guanganshi', 'gas', '510000', '511600', null, null);
INSERT INTO `sys_area` VALUES ('2489', '广安区', 'guanganqu', 'gaq', '511600', '511602', null, null);
INSERT INTO `sys_area` VALUES ('2490', '前锋区', 'qianfengqu', 'qfq', '511600', '511603', null, null);
INSERT INTO `sys_area` VALUES ('2491', '岳池县', 'yuechixian', 'ycx', '511600', '511621', null, null);
INSERT INTO `sys_area` VALUES ('2492', '武胜县', 'wushengxian', 'wsx', '511600', '511622', null, null);
INSERT INTO `sys_area` VALUES ('2493', '邻水县', 'linshuixian', 'lsx', '511600', '511623', null, null);
INSERT INTO `sys_area` VALUES ('2494', '华蓥市', 'huazuoshi', 'h蓥s', '511600', '511681', null, null);
INSERT INTO `sys_area` VALUES ('2495', '达州市', 'dazhoushi', 'dzs', '510000', '511700', null, null);
INSERT INTO `sys_area` VALUES ('2496', '通川区', 'tongchuanqu', 'tcq', '511700', '511702', null, null);
INSERT INTO `sys_area` VALUES ('2497', '达川区', 'dachuanqu', 'dcq', '511700', '511703', null, null);
INSERT INTO `sys_area` VALUES ('2498', '宣汉县', 'xuanhanxian', 'xhx', '511700', '511722', null, null);
INSERT INTO `sys_area` VALUES ('2499', '开江县', 'kaijiangxian', 'kjx', '511700', '511723', null, null);
INSERT INTO `sys_area` VALUES ('2500', '大竹县', 'dazhuxian', 'dzx', '511700', '511724', null, null);
INSERT INTO `sys_area` VALUES ('2501', '渠县', 'quxian', 'qx', '511700', '511725', null, null);
INSERT INTO `sys_area` VALUES ('2502', '万源市', 'wanyuanshi', 'wys', '511700', '511781', null, null);
INSERT INTO `sys_area` VALUES ('2503', '雅安市', 'yaanshi', 'yas', '510000', '511800', null, null);
INSERT INTO `sys_area` VALUES ('2504', '雨城区', 'yuchengqu', 'ycq', '511800', '511802', null, null);
INSERT INTO `sys_area` VALUES ('2505', '名山区', 'mingshanqu', 'msq', '511800', '511803', null, null);
INSERT INTO `sys_area` VALUES ('2506', '荥经县', 'zuojingxian', '荥jx', '511800', '511822', null, null);
INSERT INTO `sys_area` VALUES ('2507', '汉源县', 'hanyuanxian', 'hyx', '511800', '511823', null, null);
INSERT INTO `sys_area` VALUES ('2508', '石棉县', 'shimianxian', 'smx', '511800', '511824', null, null);
INSERT INTO `sys_area` VALUES ('2509', '天全县', 'tianquanxian', 'tqx', '511800', '511825', null, null);
INSERT INTO `sys_area` VALUES ('2510', '芦山县', 'lushanxian', 'lsx', '511800', '511826', null, null);
INSERT INTO `sys_area` VALUES ('2511', '宝兴县', 'baoxingxian', 'bxx', '511800', '511827', null, null);
INSERT INTO `sys_area` VALUES ('2512', '巴中市', 'bazhongshi', 'bzs', '510000', '511900', null, null);
INSERT INTO `sys_area` VALUES ('2513', '巴州区', 'bazhouqu', 'bzq', '511900', '511902', null, null);
INSERT INTO `sys_area` VALUES ('2514', '恩阳区', 'enyangqu', 'eyq', '511900', '511903', null, null);
INSERT INTO `sys_area` VALUES ('2515', '通江县', 'tongjiangxian', 'tjx', '511900', '511921', null, null);
INSERT INTO `sys_area` VALUES ('2516', '南江县', 'nanjiangxian', 'njx', '511900', '511922', null, null);
INSERT INTO `sys_area` VALUES ('2517', '平昌县', 'pingchangxian', 'pcx', '511900', '511923', null, null);
INSERT INTO `sys_area` VALUES ('2518', '资阳市', 'ziyangshi', 'zys', '510000', '512000', null, null);
INSERT INTO `sys_area` VALUES ('2519', '雁江区', 'yanjiangqu', 'yjq', '512000', '512002', null, null);
INSERT INTO `sys_area` VALUES ('2520', '安岳县', 'anyuexian', 'ayx', '512000', '512021', null, null);
INSERT INTO `sys_area` VALUES ('2521', '乐至县', 'lezhixian', 'lzx', '512000', '512022', null, null);
INSERT INTO `sys_area` VALUES ('2522', '阿坝藏族羌族自治州', 'abacangzuqiangzuzizhizhou', 'abczqzzzz', '510000', '513200', null, null);
INSERT INTO `sys_area` VALUES ('2523', '马尔康市', 'maerkangshi', 'meks', '513200', '513201', null, null);
INSERT INTO `sys_area` VALUES ('2524', '汶川县', 'zuochuanxian', '汶cx', '513200', '513221', null, null);
INSERT INTO `sys_area` VALUES ('2525', '理县', 'lixian', 'lx', '513200', '513222', null, null);
INSERT INTO `sys_area` VALUES ('2526', '茂县', 'maoxian', 'mx', '513200', '513223', null, null);
INSERT INTO `sys_area` VALUES ('2527', '松潘县', 'songpanxian', 'spx', '513200', '513224', null, null);
INSERT INTO `sys_area` VALUES ('2528', '九寨沟县', 'jiuzhaigouxian', 'jzgx', '513200', '513225', null, null);
INSERT INTO `sys_area` VALUES ('2529', '金川县', 'jinchuanxian', 'jcx', '513200', '513226', null, null);
INSERT INTO `sys_area` VALUES ('2530', '小金县', 'xiaojinxian', 'xjx', '513200', '513227', null, null);
INSERT INTO `sys_area` VALUES ('2531', '黑水县', 'heishuixian', 'hsx', '513200', '513228', null, null);
INSERT INTO `sys_area` VALUES ('2532', '壤塘县', 'rangtangxian', 'rtx', '513200', '513230', null, null);
INSERT INTO `sys_area` VALUES ('2533', '阿坝县', 'abaxian', 'abx', '513200', '513231', null, null);
INSERT INTO `sys_area` VALUES ('2534', '若尔盖县', 'ruoergaixian', 'regx', '513200', '513232', null, null);
INSERT INTO `sys_area` VALUES ('2535', '红原县', 'hongyuanxian', 'hyx', '513200', '513233', null, null);
INSERT INTO `sys_area` VALUES ('2536', '甘孜藏族自治州', 'ganzicangzuzizhizhou', 'gzczzzz', '510000', '513300', null, null);
INSERT INTO `sys_area` VALUES ('2537', '康定市', 'kangdingshi', 'kds', '513300', '513301', null, null);
INSERT INTO `sys_area` VALUES ('2538', '泸定县', 'zuodingxian', '泸dx', '513300', '513322', null, null);
INSERT INTO `sys_area` VALUES ('2539', '丹巴县', 'danbaxian', 'dbx', '513300', '513323', null, null);
INSERT INTO `sys_area` VALUES ('2540', '九龙县', 'jiulongxian', 'jlx', '513300', '513324', null, null);
INSERT INTO `sys_area` VALUES ('2541', '雅江县', 'yajiangxian', 'yjx', '513300', '513325', null, null);
INSERT INTO `sys_area` VALUES ('2542', '道孚县', 'daozuoxian', 'd孚x', '513300', '513326', null, null);
INSERT INTO `sys_area` VALUES ('2543', '炉霍县', 'luhuoxian', 'lhx', '513300', '513327', null, null);
INSERT INTO `sys_area` VALUES ('2544', '甘孜县', 'ganzixian', 'gzx', '513300', '513328', null, null);
INSERT INTO `sys_area` VALUES ('2545', '新龙县', 'xinlongxian', 'xlx', '513300', '513329', null, null);
INSERT INTO `sys_area` VALUES ('2546', '德格县', 'degexian', 'dgx', '513300', '513330', null, null);
INSERT INTO `sys_area` VALUES ('2547', '白玉县', 'baiyuxian', 'byx', '513300', '513331', null, null);
INSERT INTO `sys_area` VALUES ('2548', '石渠县', 'shiquxian', 'sqx', '513300', '513332', null, null);
INSERT INTO `sys_area` VALUES ('2549', '色达县', 'sedaxian', 'sdx', '513300', '513333', null, null);
INSERT INTO `sys_area` VALUES ('2550', '理塘县', 'litangxian', 'ltx', '513300', '513334', null, null);
INSERT INTO `sys_area` VALUES ('2551', '巴塘县', 'batangxian', 'btx', '513300', '513335', null, null);
INSERT INTO `sys_area` VALUES ('2552', '乡城县', 'xiangchengxian', 'xcx', '513300', '513336', null, null);
INSERT INTO `sys_area` VALUES ('2553', '稻城县', 'daochengxian', 'dcx', '513300', '513337', null, null);
INSERT INTO `sys_area` VALUES ('2554', '得荣县', 'derongxian', 'drx', '513300', '513338', null, null);
INSERT INTO `sys_area` VALUES ('2555', '凉山彝族自治州', 'liangshanyizuzizhizhou', 'lsyzzzz', '510000', '513400', null, null);
INSERT INTO `sys_area` VALUES ('2556', '西昌市', 'xichangshi', 'xcs', '513400', '513401', null, null);
INSERT INTO `sys_area` VALUES ('2557', '木里藏族自治县', 'mulicangzuzizhixian', 'mlczzzx', '513400', '513422', null, null);
INSERT INTO `sys_area` VALUES ('2558', '盐源县', 'yanyuanxian', 'yyx', '513400', '513423', null, null);
INSERT INTO `sys_area` VALUES ('2559', '德昌县', 'dechangxian', 'dcx', '513400', '513424', null, null);
INSERT INTO `sys_area` VALUES ('2560', '会理县', 'huilixian', 'hlx', '513400', '513425', null, null);
INSERT INTO `sys_area` VALUES ('2561', '会东县', 'huidongxian', 'hdx', '513400', '513426', null, null);
INSERT INTO `sys_area` VALUES ('2562', '宁南县', 'ningnanxian', 'nnx', '513400', '513427', null, null);
INSERT INTO `sys_area` VALUES ('2563', '普格县', 'pugexian', 'pgx', '513400', '513428', null, null);
INSERT INTO `sys_area` VALUES ('2564', '布拖县', 'butuoxian', 'btx', '513400', '513429', null, null);
INSERT INTO `sys_area` VALUES ('2565', '金阳县', 'jinyangxian', 'jyx', '513400', '513430', null, null);
INSERT INTO `sys_area` VALUES ('2566', '昭觉县', 'zhaojuexian', 'zjx', '513400', '513431', null, null);
INSERT INTO `sys_area` VALUES ('2567', '喜德县', 'xidexian', 'xdx', '513400', '513432', null, null);
INSERT INTO `sys_area` VALUES ('2568', '冕宁县', 'mianningxian', 'mnx', '513400', '513433', null, null);
INSERT INTO `sys_area` VALUES ('2569', '越西县', 'yuexixian', 'yxx', '513400', '513434', null, null);
INSERT INTO `sys_area` VALUES ('2570', '甘洛县', 'ganluoxian', 'glx', '513400', '513435', null, null);
INSERT INTO `sys_area` VALUES ('2571', '美姑县', 'meiguxian', 'mgx', '513400', '513436', null, null);
INSERT INTO `sys_area` VALUES ('2572', '雷波县', 'leiboxian', 'lbx', '513400', '513437', null, null);
INSERT INTO `sys_area` VALUES ('2573', '贵州省', 'guizhousheng', 'gzs', '0', '520000', null, null);
INSERT INTO `sys_area` VALUES ('2574', '贵阳市', 'guiyangshi', 'gys', '520000', '520100', null, null);
INSERT INTO `sys_area` VALUES ('2575', '南明区', 'nanmingqu', 'nmq', '520100', '520102', null, null);
INSERT INTO `sys_area` VALUES ('2576', '云岩区', 'yunyanqu', 'yyq', '520100', '520103', null, null);
INSERT INTO `sys_area` VALUES ('2577', '花溪区', 'huaxiqu', 'hxq', '520100', '520111', null, null);
INSERT INTO `sys_area` VALUES ('2578', '乌当区', 'wudangqu', 'wdq', '520100', '520112', null, null);
INSERT INTO `sys_area` VALUES ('2579', '白云区', 'baiyunqu', 'byq', '520100', '520113', null, null);
INSERT INTO `sys_area` VALUES ('2580', '观山湖区', 'guanshanhuqu', 'gshq', '520100', '520115', null, null);
INSERT INTO `sys_area` VALUES ('2581', '开阳县', 'kaiyangxian', 'kyx', '520100', '520121', null, null);
INSERT INTO `sys_area` VALUES ('2582', '息烽县', 'xifengxian', 'xfx', '520100', '520122', null, null);
INSERT INTO `sys_area` VALUES ('2583', '修文县', 'xiuwenxian', 'xwx', '520100', '520123', null, null);
INSERT INTO `sys_area` VALUES ('2584', '清镇市', 'qingzhenshi', 'qzs', '520100', '520181', null, null);
INSERT INTO `sys_area` VALUES ('2585', '六盘水市', 'liupanshuishi', 'lpss', '520000', '520200', null, null);
INSERT INTO `sys_area` VALUES ('2586', '钟山区', 'zhongshanqu', 'zsq', '520200', '520201', null, null);
INSERT INTO `sys_area` VALUES ('2587', '六枝特区', 'liuzhitequ', 'lztq', '520200', '520203', null, null);
INSERT INTO `sys_area` VALUES ('2588', '水城县', 'shuichengxian', 'scx', '520200', '520221', null, null);
INSERT INTO `sys_area` VALUES ('2589', '盘州市', 'panzhoushi', 'pzs', '520200', '520281', null, null);
INSERT INTO `sys_area` VALUES ('2590', '遵义市', 'zunyishi', 'zys', '520000', '520300', null, null);
INSERT INTO `sys_area` VALUES ('2591', '红花岗区', 'honghuagangqu', 'hhgq', '520300', '520302', null, null);
INSERT INTO `sys_area` VALUES ('2592', '汇川区', 'huichuanqu', 'hcq', '520300', '520303', null, null);
INSERT INTO `sys_area` VALUES ('2593', '播州区', 'bozhouqu', 'bzq', '520300', '520304', null, null);
INSERT INTO `sys_area` VALUES ('2594', '桐梓县', 'tongzuoxian', 't梓x', '520300', '520322', null, null);
INSERT INTO `sys_area` VALUES ('2595', '绥阳县', 'suiyangxian', 'syx', '520300', '520323', null, null);
INSERT INTO `sys_area` VALUES ('2596', '正安县', 'zhenganxian', 'zax', '520300', '520324', null, null);
INSERT INTO `sys_area` VALUES ('2597', '道真仡佬族苗族自治县', 'daozhenzuolaozumiaozuzizhixian', 'dz仡lzmzzzx', '520300', '520325', null, null);
INSERT INTO `sys_area` VALUES ('2598', '务川仡佬族苗族自治县', 'wuchuanzuolaozumiaozuzizhixian', 'wc仡lzmzzzx', '520300', '520326', null, null);
INSERT INTO `sys_area` VALUES ('2599', '凤冈县', 'fenggangxian', 'fgx', '520300', '520327', null, null);
INSERT INTO `sys_area` VALUES ('2600', '湄潭县', 'zuotanxian', '湄tx', '520300', '520328', null, null);
INSERT INTO `sys_area` VALUES ('2601', '余庆县', 'yuqingxian', 'yqx', '520300', '520329', null, null);
INSERT INTO `sys_area` VALUES ('2602', '习水县', 'xishuixian', 'xsx', '520300', '520330', null, null);
INSERT INTO `sys_area` VALUES ('2603', '赤水市', 'chishuishi', 'css', '520300', '520381', null, null);
INSERT INTO `sys_area` VALUES ('2604', '仁怀市', 'renhuaishi', 'rhs', '520300', '520382', null, null);
INSERT INTO `sys_area` VALUES ('2605', '安顺市', 'anshunshi', 'ass', '520000', '520400', null, null);
INSERT INTO `sys_area` VALUES ('2606', '西秀区', 'xixiuqu', 'xxq', '520400', '520402', null, null);
INSERT INTO `sys_area` VALUES ('2607', '平坝区', 'pingbaqu', 'pbq', '520400', '520403', null, null);
INSERT INTO `sys_area` VALUES ('2608', '普定县', 'pudingxian', 'pdx', '520400', '520422', null, null);
INSERT INTO `sys_area` VALUES ('2609', '镇宁布依族苗族自治县', 'zhenningbuyizumiaozuzizhixian', 'znbyzmzzzx', '520400', '520423', null, null);
INSERT INTO `sys_area` VALUES ('2610', '关岭布依族苗族自治县', 'guanlingbuyizumiaozuzizhixian', 'glbyzmzzzx', '520400', '520424', null, null);
INSERT INTO `sys_area` VALUES ('2611', '紫云苗族布依族自治县', 'ziyunmiaozubuyizuzizhixian', 'zymzbyzzzx', '520400', '520425', null, null);
INSERT INTO `sys_area` VALUES ('2612', '毕节市', 'bijieshi', 'bjs', '520000', '520500', null, null);
INSERT INTO `sys_area` VALUES ('2613', '七星关区', 'qixingguanqu', 'qxgq', '520500', '520502', null, null);
INSERT INTO `sys_area` VALUES ('2614', '大方县', 'dafangxian', 'dfx', '520500', '520521', null, null);
INSERT INTO `sys_area` VALUES ('2615', '黔西县', 'qianxixian', 'qxx', '520500', '520522', null, null);
INSERT INTO `sys_area` VALUES ('2616', '金沙县', 'jinshaxian', 'jsx', '520500', '520523', null, null);
INSERT INTO `sys_area` VALUES ('2617', '织金县', 'zhijinxian', 'zjx', '520500', '520524', null, null);
INSERT INTO `sys_area` VALUES ('2618', '纳雍县', 'nayongxian', 'nyx', '520500', '520525', null, null);
INSERT INTO `sys_area` VALUES ('2619', '威宁彝族回族苗族自治县', 'weiningyizuhuizumiaozuzizhixian', 'wnyzhzmzzzx', '520500', '520526', null, null);
INSERT INTO `sys_area` VALUES ('2620', '赫章县', 'hezhangxian', 'hzx', '520500', '520527', null, null);
INSERT INTO `sys_area` VALUES ('2621', '铜仁市', 'tongrenshi', 'trs', '520000', '520600', null, null);
INSERT INTO `sys_area` VALUES ('2622', '碧江区', 'bijiangqu', 'bjq', '520600', '520602', null, null);
INSERT INTO `sys_area` VALUES ('2623', '万山区', 'wanshanqu', 'wsq', '520600', '520603', null, null);
INSERT INTO `sys_area` VALUES ('2624', '江口县', 'jiangkouxian', 'jkx', '520600', '520621', null, null);
INSERT INTO `sys_area` VALUES ('2625', '玉屏侗族自治县', 'yupingdongzuzizhixian', 'ypdzzzx', '520600', '520622', null, null);
INSERT INTO `sys_area` VALUES ('2626', '石阡县', 'shizuoxian', 's阡x', '520600', '520623', null, null);
INSERT INTO `sys_area` VALUES ('2627', '思南县', 'sinanxian', 'snx', '520600', '520624', null, null);
INSERT INTO `sys_area` VALUES ('2628', '印江土家族苗族自治县', 'yinjiangtujiazumiaozuzizhixian', 'yjtjzmzzzx', '520600', '520625', null, null);
INSERT INTO `sys_area` VALUES ('2629', '德江县', 'dejiangxian', 'djx', '520600', '520626', null, null);
INSERT INTO `sys_area` VALUES ('2630', '沿河土家族自治县', 'yanhetujiazuzizhixian', 'yhtjzzzx', '520600', '520627', null, null);
INSERT INTO `sys_area` VALUES ('2631', '松桃苗族自治县', 'songtaomiaozuzizhixian', 'stmzzzx', '520600', '520628', null, null);
INSERT INTO `sys_area` VALUES ('2632', '黔西南布依族苗族自治州', 'qianxinanbuyizumiaozuzizhizhou', 'qxnbyzmzzzz', '520000', '522300', null, null);
INSERT INTO `sys_area` VALUES ('2633', '兴义市', 'xingyishi', 'xys', '522300', '522301', null, null);
INSERT INTO `sys_area` VALUES ('2634', '兴仁县', 'xingrenxian', 'xrx', '522300', '522322', null, null);
INSERT INTO `sys_area` VALUES ('2635', '普安县', 'puanxian', 'pax', '522300', '522323', null, null);
INSERT INTO `sys_area` VALUES ('2636', '晴隆县', 'qinglongxian', 'qlx', '522300', '522324', null, null);
INSERT INTO `sys_area` VALUES ('2637', '贞丰县', 'zhenfengxian', 'zfx', '522300', '522325', null, null);
INSERT INTO `sys_area` VALUES ('2638', '望谟县', 'wangzuoxian', 'w谟x', '522300', '522326', null, null);
INSERT INTO `sys_area` VALUES ('2639', '册亨县', 'cehengxian', 'chx', '522300', '522327', null, null);
INSERT INTO `sys_area` VALUES ('2640', '安龙县', 'anlongxian', 'alx', '522300', '522328', null, null);
INSERT INTO `sys_area` VALUES ('2641', '黔东南苗族侗族自治州', 'qiandongnanmiaozudongzuzizhizhou', 'qdnmzdzzzz', '520000', '522600', null, null);
INSERT INTO `sys_area` VALUES ('2642', '凯里市', 'kailishi', 'kls', '522600', '522601', null, null);
INSERT INTO `sys_area` VALUES ('2643', '黄平县', 'huangpingxian', 'hpx', '522600', '522622', null, null);
INSERT INTO `sys_area` VALUES ('2644', '施秉县', 'shibingxian', 'sbx', '522600', '522623', null, null);
INSERT INTO `sys_area` VALUES ('2645', '三穗县', 'sansuixian', 'ssx', '522600', '522624', null, null);
INSERT INTO `sys_area` VALUES ('2646', '镇远县', 'zhenyuanxian', 'zyx', '522600', '522625', null, null);
INSERT INTO `sys_area` VALUES ('2647', '岑巩县', 'zuogongxian', '岑gx', '522600', '522626', null, null);
INSERT INTO `sys_area` VALUES ('2648', '天柱县', 'tianzhuxian', 'tzx', '522600', '522627', null, null);
INSERT INTO `sys_area` VALUES ('2649', '锦屏县', 'jinpingxian', 'jpx', '522600', '522628', null, null);
INSERT INTO `sys_area` VALUES ('2650', '剑河县', 'jianhexian', 'jhx', '522600', '522629', null, null);
INSERT INTO `sys_area` VALUES ('2651', '台江县', 'taijiangxian', 'tjx', '522600', '522630', null, null);
INSERT INTO `sys_area` VALUES ('2652', '黎平县', 'lipingxian', 'lpx', '522600', '522631', null, null);
INSERT INTO `sys_area` VALUES ('2653', '榕江县', 'zuojiangxian', '榕jx', '522600', '522632', null, null);
INSERT INTO `sys_area` VALUES ('2654', '从江县', 'congjiangxian', 'cjx', '522600', '522633', null, null);
INSERT INTO `sys_area` VALUES ('2655', '雷山县', 'leishanxian', 'lsx', '522600', '522634', null, null);
INSERT INTO `sys_area` VALUES ('2656', '麻江县', 'majiangxian', 'mjx', '522600', '522635', null, null);
INSERT INTO `sys_area` VALUES ('2657', '丹寨县', 'danzhaixian', 'dzx', '522600', '522636', null, null);
INSERT INTO `sys_area` VALUES ('2658', '黔南布依族苗族自治州', 'qiannanbuyizumiaozuzizhizhou', 'qnbyzmzzzz', '520000', '522700', null, null);
INSERT INTO `sys_area` VALUES ('2659', '都匀市', 'duyunshi', 'dys', '522700', '522701', null, null);
INSERT INTO `sys_area` VALUES ('2660', '福泉市', 'fuquanshi', 'fqs', '522700', '522702', null, null);
INSERT INTO `sys_area` VALUES ('2661', '荔波县', 'liboxian', 'lbx', '522700', '522722', null, null);
INSERT INTO `sys_area` VALUES ('2662', '贵定县', 'guidingxian', 'gdx', '522700', '522723', null, null);
INSERT INTO `sys_area` VALUES ('2663', '瓮安县', 'wenganxian', 'wax', '522700', '522725', null, null);
INSERT INTO `sys_area` VALUES ('2664', '独山县', 'dushanxian', 'dsx', '522700', '522726', null, null);
INSERT INTO `sys_area` VALUES ('2665', '平塘县', 'pingtangxian', 'ptx', '522700', '522727', null, null);
INSERT INTO `sys_area` VALUES ('2666', '罗甸县', 'luodianxian', 'ldx', '522700', '522728', null, null);
INSERT INTO `sys_area` VALUES ('2667', '长顺县', 'changshunxian', 'csx', '522700', '522729', null, null);
INSERT INTO `sys_area` VALUES ('2668', '龙里县', 'longlixian', 'llx', '522700', '522730', null, null);
INSERT INTO `sys_area` VALUES ('2669', '惠水县', 'huishuixian', 'hsx', '522700', '522731', null, null);
INSERT INTO `sys_area` VALUES ('2670', '三都水族自治县', 'sandushuizuzizhixian', 'sdszzzx', '522700', '522732', null, null);
INSERT INTO `sys_area` VALUES ('2671', '云南省', 'yunnansheng', 'yns', '0', '530000', null, null);
INSERT INTO `sys_area` VALUES ('2672', '昆明市', 'kunmingshi', 'kms', '530000', '530100', null, null);
INSERT INTO `sys_area` VALUES ('2673', '五华区', 'wuhuaqu', 'whq', '530100', '530102', null, null);
INSERT INTO `sys_area` VALUES ('2674', '盘龙区', 'panlongqu', 'plq', '530100', '530103', null, null);
INSERT INTO `sys_area` VALUES ('2675', '官渡区', 'guanduqu', 'gdq', '530100', '530111', null, null);
INSERT INTO `sys_area` VALUES ('2676', '西山区', 'xishanqu', 'xsq', '530100', '530112', null, null);
INSERT INTO `sys_area` VALUES ('2677', '东川区', 'dongchuanqu', 'dcq', '530100', '530113', null, null);
INSERT INTO `sys_area` VALUES ('2678', '呈贡区', 'chenggongqu', 'cgq', '530100', '530114', null, null);
INSERT INTO `sys_area` VALUES ('2679', '晋宁区', 'jinningqu', 'jnq', '530100', '530115', null, null);
INSERT INTO `sys_area` VALUES ('2680', '富民县', 'fuminxian', 'fmx', '530100', '530124', null, null);
INSERT INTO `sys_area` VALUES ('2681', '宜良县', 'yiliangxian', 'ylx', '530100', '530125', null, null);
INSERT INTO `sys_area` VALUES ('2682', '石林彝族自治县', 'shilinyizuzizhixian', 'slyzzzx', '530100', '530126', null, null);
INSERT INTO `sys_area` VALUES ('2683', '嵩明县', 'zuomingxian', '嵩mx', '530100', '530127', null, null);
INSERT INTO `sys_area` VALUES ('2684', '禄劝彝族苗族自治县', 'luquanyizumiaozuzizhixian', 'lqyzmzzzx', '530100', '530128', null, null);
INSERT INTO `sys_area` VALUES ('2685', '寻甸回族彝族自治县', 'xundianhuizuyizuzizhixian', 'xdhzyzzzx', '530100', '530129', null, null);
INSERT INTO `sys_area` VALUES ('2686', '安宁市', 'anningshi', 'ans', '530100', '530181', null, null);
INSERT INTO `sys_area` VALUES ('2687', '曲靖市', 'qujingshi', 'qjs', '530000', '530300', null, null);
INSERT INTO `sys_area` VALUES ('2688', '麒麟区', 'zuozuoqu', '麒麟q', '530300', '530302', null, null);
INSERT INTO `sys_area` VALUES ('2689', '沾益区', 'zhanyiqu', 'zyq', '530300', '530303', null, null);
INSERT INTO `sys_area` VALUES ('2690', '马龙区', 'malongqu', 'mlq', '530300', '530304', null, null);
INSERT INTO `sys_area` VALUES ('2691', '陆良县', 'luliangxian', 'llx', '530300', '530322', null, null);
INSERT INTO `sys_area` VALUES ('2692', '师宗县', 'shizongxian', 'szx', '530300', '530323', null, null);
INSERT INTO `sys_area` VALUES ('2693', '罗平县', 'luopingxian', 'lpx', '530300', '530324', null, null);
INSERT INTO `sys_area` VALUES ('2694', '富源县', 'fuyuanxian', 'fyx', '530300', '530325', null, null);
INSERT INTO `sys_area` VALUES ('2695', '会泽县', 'huizexian', 'hzx', '530300', '530326', null, null);
INSERT INTO `sys_area` VALUES ('2696', '宣威市', 'xuanweishi', 'xws', '530300', '530381', null, null);
INSERT INTO `sys_area` VALUES ('2697', '玉溪市', 'yuxishi', 'yxs', '530000', '530400', null, null);
INSERT INTO `sys_area` VALUES ('2698', '红塔区', 'hongtaqu', 'htq', '530400', '530402', null, null);
INSERT INTO `sys_area` VALUES ('2699', '江川区', 'jiangchuanqu', 'jcq', '530400', '530403', null, null);
INSERT INTO `sys_area` VALUES ('2700', '澄江县', 'chengjiangxian', 'cjx', '530400', '530422', null, null);
INSERT INTO `sys_area` VALUES ('2701', '通海县', 'tonghaixian', 'thx', '530400', '530423', null, null);
INSERT INTO `sys_area` VALUES ('2702', '华宁县', 'huaningxian', 'hnx', '530400', '530424', null, null);
INSERT INTO `sys_area` VALUES ('2703', '易门县', 'yimenxian', 'ymx', '530400', '530425', null, null);
INSERT INTO `sys_area` VALUES ('2704', '峨山彝族自治县', 'eshanyizuzizhixian', 'esyzzzx', '530400', '530426', null, null);
INSERT INTO `sys_area` VALUES ('2705', '新平彝族傣族自治县', 'xinpingyizudaizuzizhixian', 'xpyzdzzzx', '530400', '530427', null, null);
INSERT INTO `sys_area` VALUES ('2706', '元江哈尼族彝族傣族自治县', 'yuanjianghanizuyizudaizuzizhixian', 'yjhnzyzdzzzx', '530400', '530428', null, null);
INSERT INTO `sys_area` VALUES ('2707', '保山市', 'baoshanshi', 'bss', '530000', '530500', null, null);
INSERT INTO `sys_area` VALUES ('2708', '隆阳区', 'longyangqu', 'lyq', '530500', '530502', null, null);
INSERT INTO `sys_area` VALUES ('2709', '施甸县', 'shidianxian', 'sdx', '530500', '530521', null, null);
INSERT INTO `sys_area` VALUES ('2710', '龙陵县', 'longlingxian', 'llx', '530500', '530523', null, null);
INSERT INTO `sys_area` VALUES ('2711', '昌宁县', 'changningxian', 'cnx', '530500', '530524', null, null);
INSERT INTO `sys_area` VALUES ('2712', '腾冲市', 'tengchongshi', 'tcs', '530500', '530581', null, null);
INSERT INTO `sys_area` VALUES ('2713', '昭通市', 'zhaotongshi', 'zts', '530000', '530600', null, null);
INSERT INTO `sys_area` VALUES ('2714', '昭阳区', 'zhaoyangqu', 'zyq', '530600', '530602', null, null);
INSERT INTO `sys_area` VALUES ('2715', '鲁甸县', 'ludianxian', 'ldx', '530600', '530621', null, null);
INSERT INTO `sys_area` VALUES ('2716', '巧家县', 'qiaojiaxian', 'qjx', '530600', '530622', null, null);
INSERT INTO `sys_area` VALUES ('2717', '盐津县', 'yanjinxian', 'yjx', '530600', '530623', null, null);
INSERT INTO `sys_area` VALUES ('2718', '大关县', 'daguanxian', 'dgx', '530600', '530624', null, null);
INSERT INTO `sys_area` VALUES ('2719', '永善县', 'yongshanxian', 'ysx', '530600', '530625', null, null);
INSERT INTO `sys_area` VALUES ('2720', '绥江县', 'suijiangxian', 'sjx', '530600', '530626', null, null);
INSERT INTO `sys_area` VALUES ('2721', '镇雄县', 'zhenxiongxian', 'zxx', '530600', '530627', null, null);
INSERT INTO `sys_area` VALUES ('2722', '彝良县', 'yiliangxian', 'ylx', '530600', '530628', null, null);
INSERT INTO `sys_area` VALUES ('2723', '威信县', 'weixinxian', 'wxx', '530600', '530629', null, null);
INSERT INTO `sys_area` VALUES ('2724', '水富县', 'shuifuxian', 'sfx', '530600', '530630', null, null);
INSERT INTO `sys_area` VALUES ('2725', '丽江市', 'lijiangshi', 'ljs', '530000', '530700', null, null);
INSERT INTO `sys_area` VALUES ('2726', '古城区', 'guchengqu', 'gcq', '530700', '530702', null, null);
INSERT INTO `sys_area` VALUES ('2727', '玉龙纳西族自治县', 'yulongnaxizuzizhixian', 'ylnxzzzx', '530700', '530721', null, null);
INSERT INTO `sys_area` VALUES ('2728', '永胜县', 'yongshengxian', 'ysx', '530700', '530722', null, null);
INSERT INTO `sys_area` VALUES ('2729', '华坪县', 'huapingxian', 'hpx', '530700', '530723', null, null);
INSERT INTO `sys_area` VALUES ('2730', '宁蒗彝族自治县', 'ningzuoyizuzizhixian', 'n蒗yzzzx', '530700', '530724', null, null);
INSERT INTO `sys_area` VALUES ('2731', '普洱市', 'puershi', 'pes', '530000', '530800', null, null);
INSERT INTO `sys_area` VALUES ('2732', '思茅区', 'simaoqu', 'smq', '530800', '530802', null, null);
INSERT INTO `sys_area` VALUES ('2733', '宁洱哈尼族彝族自治县', 'ningerhanizuyizuzizhixian', 'nehnzyzzzx', '530800', '530821', null, null);
INSERT INTO `sys_area` VALUES ('2734', '墨江哈尼族自治县', 'mojianghanizuzizhixian', 'mjhnzzzx', '530800', '530822', null, null);
INSERT INTO `sys_area` VALUES ('2735', '景东彝族自治县', 'jingdongyizuzizhixian', 'jdyzzzx', '530800', '530823', null, null);
INSERT INTO `sys_area` VALUES ('2736', '景谷傣族彝族自治县', 'jinggudaizuyizuzizhixian', 'jgdzyzzzx', '530800', '530824', null, null);
INSERT INTO `sys_area` VALUES ('2737', '镇沅彝族哈尼族拉祜族自治县', 'zhenzuoyizuhanizulazuozuzizhixian', 'z沅yzhnzl祜zzzx', '530800', '530825', null, null);
INSERT INTO `sys_area` VALUES ('2738', '江城哈尼族彝族自治县', 'jiangchenghanizuyizuzizhixian', 'jchnzyzzzx', '530800', '530826', null, null);
INSERT INTO `sys_area` VALUES ('2739', '孟连傣族拉祜族佤族自治县', 'mengliandaizulazuozuzuozuzizhixian', 'mldzl祜z佤zzzx', '530800', '530827', null, null);
INSERT INTO `sys_area` VALUES ('2740', '澜沧拉祜族自治县', 'lancanglazuozuzizhixian', 'lcl祜zzzx', '530800', '530828', null, null);
INSERT INTO `sys_area` VALUES ('2741', '西盟佤族自治县', 'ximengzuozuzizhixian', 'xm佤zzzx', '530800', '530829', null, null);
INSERT INTO `sys_area` VALUES ('2742', '临沧市', 'lincangshi', 'lcs', '530000', '530900', null, null);
INSERT INTO `sys_area` VALUES ('2743', '临翔区', 'linxiangqu', 'lxq', '530900', '530902', null, null);
INSERT INTO `sys_area` VALUES ('2744', '凤庆县', 'fengqingxian', 'fqx', '530900', '530921', null, null);
INSERT INTO `sys_area` VALUES ('2745', '云县', 'yunxian', 'yx', '530900', '530922', null, null);
INSERT INTO `sys_area` VALUES ('2746', '永德县', 'yongdexian', 'ydx', '530900', '530923', null, null);
INSERT INTO `sys_area` VALUES ('2747', '镇康县', 'zhenkangxian', 'zkx', '530900', '530924', null, null);
INSERT INTO `sys_area` VALUES ('2748', '双江拉祜族佤族布朗族傣族自治县', 'shuangjianglazuozuzuozubulangzudaizuzizhixian', 'sjl祜z佤zblzdzzzx', '530900', '530925', null, null);
INSERT INTO `sys_area` VALUES ('2749', '耿马傣族佤族自治县', 'gengmadaizuzuozuzizhixian', 'gmdz佤zzzx', '530900', '530926', null, null);
INSERT INTO `sys_area` VALUES ('2750', '沧源佤族自治县', 'cangyuanzuozuzizhixian', 'cy佤zzzx', '530900', '530927', null, null);
INSERT INTO `sys_area` VALUES ('2751', '楚雄彝族自治州', 'chuxiongyizuzizhizhou', 'cxyzzzz', '530000', '532300', null, null);
INSERT INTO `sys_area` VALUES ('2752', '楚雄市', 'chuxiongshi', 'cxs', '532300', '532301', null, null);
INSERT INTO `sys_area` VALUES ('2753', '双柏县', 'shuangbaixian', 'sbx', '532300', '532322', null, null);
INSERT INTO `sys_area` VALUES ('2754', '牟定县', 'moudingxian', 'mdx', '532300', '532323', null, null);
INSERT INTO `sys_area` VALUES ('2755', '南华县', 'nanhuaxian', 'nhx', '532300', '532324', null, null);
INSERT INTO `sys_area` VALUES ('2756', '姚安县', 'yaoanxian', 'yax', '532300', '532325', null, null);
INSERT INTO `sys_area` VALUES ('2757', '大姚县', 'dayaoxian', 'dyx', '532300', '532326', null, null);
INSERT INTO `sys_area` VALUES ('2758', '永仁县', 'yongrenxian', 'yrx', '532300', '532327', null, null);
INSERT INTO `sys_area` VALUES ('2759', '元谋县', 'yuanmouxian', 'ymx', '532300', '532328', null, null);
INSERT INTO `sys_area` VALUES ('2760', '武定县', 'wudingxian', 'wdx', '532300', '532329', null, null);
INSERT INTO `sys_area` VALUES ('2761', '禄丰县', 'lufengxian', 'lfx', '532300', '532331', null, null);
INSERT INTO `sys_area` VALUES ('2762', '红河哈尼族彝族自治州', 'honghehanizuyizuzizhizhou', 'hhhnzyzzzz', '530000', '532500', null, null);
INSERT INTO `sys_area` VALUES ('2763', '个旧市', 'gejiushi', 'gjs', '532500', '532501', null, null);
INSERT INTO `sys_area` VALUES ('2764', '开远市', 'kaiyuanshi', 'kys', '532500', '532502', null, null);
INSERT INTO `sys_area` VALUES ('2765', '蒙自市', 'mengzishi', 'mzs', '532500', '532503', null, null);
INSERT INTO `sys_area` VALUES ('2766', '弥勒市', 'mileshi', 'mls', '532500', '532504', null, null);
INSERT INTO `sys_area` VALUES ('2767', '屏边苗族自治县', 'pingbianmiaozuzizhixian', 'pbmzzzx', '532500', '532523', null, null);
INSERT INTO `sys_area` VALUES ('2768', '建水县', 'jianshuixian', 'jsx', '532500', '532524', null, null);
INSERT INTO `sys_area` VALUES ('2769', '石屏县', 'shipingxian', 'spx', '532500', '532525', null, null);
INSERT INTO `sys_area` VALUES ('2770', '泸西县', 'zuoxixian', '泸xx', '532500', '532527', null, null);
INSERT INTO `sys_area` VALUES ('2771', '元阳县', 'yuanyangxian', 'yyx', '532500', '532528', null, null);
INSERT INTO `sys_area` VALUES ('2772', '红河县', 'honghexian', 'hhx', '532500', '532529', null, null);
INSERT INTO `sys_area` VALUES ('2773', '金平苗族瑶族傣族自治县', 'jinpingmiaozuyaozudaizuzizhixian', 'jpmzyzdzzzx', '532500', '532530', null, null);
INSERT INTO `sys_area` VALUES ('2774', '绿春县', 'lvchunxian', 'lcx', '532500', '532531', null, null);
INSERT INTO `sys_area` VALUES ('2775', '河口瑶族自治县', 'hekouyaozuzizhixian', 'hkyzzzx', '532500', '532532', null, null);
INSERT INTO `sys_area` VALUES ('2776', '文山壮族苗族自治州', 'wenshanzhuangzumiaozuzizhizhou', 'wszzmzzzz', '530000', '532600', null, null);
INSERT INTO `sys_area` VALUES ('2777', '文山市', 'wenshanshi', 'wss', '532600', '532601', null, null);
INSERT INTO `sys_area` VALUES ('2778', '砚山县', 'yanshanxian', 'ysx', '532600', '532622', null, null);
INSERT INTO `sys_area` VALUES ('2779', '西畴县', 'xichouxian', 'xcx', '532600', '532623', null, null);
INSERT INTO `sys_area` VALUES ('2780', '麻栗坡县', 'malipoxian', 'mlpx', '532600', '532624', null, null);
INSERT INTO `sys_area` VALUES ('2781', '马关县', 'maguanxian', 'mgx', '532600', '532625', null, null);
INSERT INTO `sys_area` VALUES ('2782', '丘北县', 'qiubeixian', 'qbx', '532600', '532626', null, null);
INSERT INTO `sys_area` VALUES ('2783', '广南县', 'guangnanxian', 'gnx', '532600', '532627', null, null);
INSERT INTO `sys_area` VALUES ('2784', '富宁县', 'funingxian', 'fnx', '532600', '532628', null, null);
INSERT INTO `sys_area` VALUES ('2785', '西双版纳傣族自治州', 'xishuangbannadaizuzizhizhou', 'xsbndzzzz', '530000', '532800', null, null);
INSERT INTO `sys_area` VALUES ('2786', '景洪市', 'jinghongshi', 'jhs', '532800', '532801', null, null);
INSERT INTO `sys_area` VALUES ('2787', '勐海县', 'zuohaixian', '勐hx', '532800', '532822', null, null);
INSERT INTO `sys_area` VALUES ('2788', '勐腊县', 'zuolaxian', '勐lx', '532800', '532823', null, null);
INSERT INTO `sys_area` VALUES ('2789', '大理白族自治州', 'dalibaizuzizhizhou', 'dlbzzzz', '530000', '532900', null, null);
INSERT INTO `sys_area` VALUES ('2790', '大理市', 'dalishi', 'dls', '532900', '532901', null, null);
INSERT INTO `sys_area` VALUES ('2791', '漾濞彝族自治县', 'yangzuoyizuzizhixian', 'y濞yzzzx', '532900', '532922', null, null);
INSERT INTO `sys_area` VALUES ('2792', '祥云县', 'xiangyunxian', 'xyx', '532900', '532923', null, null);
INSERT INTO `sys_area` VALUES ('2793', '宾川县', 'binchuanxian', 'bcx', '532900', '532924', null, null);
INSERT INTO `sys_area` VALUES ('2794', '弥渡县', 'miduxian', 'mdx', '532900', '532925', null, null);
INSERT INTO `sys_area` VALUES ('2795', '南涧彝族自治县', 'nanjianyizuzizhixian', 'njyzzzx', '532900', '532926', null, null);
INSERT INTO `sys_area` VALUES ('2796', '巍山彝族回族自治县', 'weishanyizuhuizuzizhixian', 'wsyzhzzzx', '532900', '532927', null, null);
INSERT INTO `sys_area` VALUES ('2797', '永平县', 'yongpingxian', 'ypx', '532900', '532928', null, null);
INSERT INTO `sys_area` VALUES ('2798', '云龙县', 'yunlongxian', 'ylx', '532900', '532929', null, null);
INSERT INTO `sys_area` VALUES ('2799', '洱源县', 'eryuanxian', 'eyx', '532900', '532930', null, null);
INSERT INTO `sys_area` VALUES ('2800', '剑川县', 'jianchuanxian', 'jcx', '532900', '532931', null, null);
INSERT INTO `sys_area` VALUES ('2801', '鹤庆县', 'heqingxian', 'hqx', '532900', '532932', null, null);
INSERT INTO `sys_area` VALUES ('2802', '德宏傣族景颇族自治州', 'dehongdaizujingpozuzizhizhou', 'dhdzjpzzzz', '530000', '533100', null, null);
INSERT INTO `sys_area` VALUES ('2803', '瑞丽市', 'ruilishi', 'rls', '533100', '533102', null, null);
INSERT INTO `sys_area` VALUES ('2804', '芒市', 'mangshi', 'ms', '533100', '533103', null, null);
INSERT INTO `sys_area` VALUES ('2805', '梁河县', 'lianghexian', 'lhx', '533100', '533122', null, null);
INSERT INTO `sys_area` VALUES ('2806', '盈江县', 'yingjiangxian', 'yjx', '533100', '533123', null, null);
INSERT INTO `sys_area` VALUES ('2807', '陇川县', 'longchuanxian', 'lcx', '533100', '533124', null, null);
INSERT INTO `sys_area` VALUES ('2808', '怒江傈僳族自治州', 'nujianglisuzuzizhizhou', 'njlszzzz', '530000', '533300', null, null);
INSERT INTO `sys_area` VALUES ('2809', '泸水市', 'zuoshuishi', '泸ss', '533300', '533301', null, null);
INSERT INTO `sys_area` VALUES ('2810', '福贡县', 'fugongxian', 'fgx', '533300', '533323', null, null);
INSERT INTO `sys_area` VALUES ('2811', '贡山独龙族怒族自治县', 'gongshandulongzunuzuzizhixian', 'gsdlznzzzx', '533300', '533324', null, null);
INSERT INTO `sys_area` VALUES ('2812', '兰坪白族普米族自治县', 'lanpingbaizupumizuzizhixian', 'lpbzpmzzzx', '533300', '533325', null, null);
INSERT INTO `sys_area` VALUES ('2813', '迪庆藏族自治州', 'diqingcangzuzizhizhou', 'dqczzzz', '530000', '533400', null, null);
INSERT INTO `sys_area` VALUES ('2814', '香格里拉市', 'xianggelilashi', 'xglls', '533400', '533401', null, null);
INSERT INTO `sys_area` VALUES ('2815', '德钦县', 'deqinxian', 'dqx', '533400', '533422', null, null);
INSERT INTO `sys_area` VALUES ('2816', '维西傈僳族自治县', 'weixilisuzuzizhixian', 'wxlszzzx', '533400', '533423', null, null);
INSERT INTO `sys_area` VALUES ('2817', '西藏自治区', 'xicangzizhiqu', 'xczzq', '0', '540000', null, null);
INSERT INTO `sys_area` VALUES ('2818', '拉萨市', 'lasashi', 'lss', '540000', '540100', null, null);
INSERT INTO `sys_area` VALUES ('2819', '城关区', 'chengguanqu', 'cgq', '540100', '540102', null, null);
INSERT INTO `sys_area` VALUES ('2820', '堆龙德庆区', 'duilongdeqingqu', 'dldqq', '540100', '540103', null, null);
INSERT INTO `sys_area` VALUES ('2821', '达孜区', 'daziqu', 'dzq', '540100', '540104', null, null);
INSERT INTO `sys_area` VALUES ('2822', '林周县', 'linzhouxian', 'lzx', '540100', '540121', null, null);
INSERT INTO `sys_area` VALUES ('2823', '当雄县', 'dangxiongxian', 'dxx', '540100', '540122', null, null);
INSERT INTO `sys_area` VALUES ('2824', '尼木县', 'nimuxian', 'nmx', '540100', '540123', null, null);
INSERT INTO `sys_area` VALUES ('2825', '曲水县', 'qushuixian', 'qsx', '540100', '540124', null, null);
INSERT INTO `sys_area` VALUES ('2826', '墨竹工卡县', 'mozhugongkaxian', 'mzgkx', '540100', '540127', null, null);
INSERT INTO `sys_area` VALUES ('2827', '日喀则市', 'rikazeshi', 'rkzs', '540000', '540200', null, null);
INSERT INTO `sys_area` VALUES ('2828', '桑珠孜区', 'sangzhuziqu', 'szzq', '540200', '540202', null, null);
INSERT INTO `sys_area` VALUES ('2829', '南木林县', 'nanmulinxian', 'nmlx', '540200', '540221', null, null);
INSERT INTO `sys_area` VALUES ('2830', '江孜县', 'jiangzixian', 'jzx', '540200', '540222', null, null);
INSERT INTO `sys_area` VALUES ('2831', '定日县', 'dingrixian', 'drx', '540200', '540223', null, null);
INSERT INTO `sys_area` VALUES ('2832', '萨迦县', 'sazuoxian', 's迦x', '540200', '540224', null, null);
INSERT INTO `sys_area` VALUES ('2833', '拉孜县', 'lazixian', 'lzx', '540200', '540225', null, null);
INSERT INTO `sys_area` VALUES ('2834', '昂仁县', 'angrenxian', 'arx', '540200', '540226', null, null);
INSERT INTO `sys_area` VALUES ('2835', '谢通门县', 'xietongmenxian', 'xtmx', '540200', '540227', null, null);
INSERT INTO `sys_area` VALUES ('2836', '白朗县', 'bailangxian', 'blx', '540200', '540228', null, null);
INSERT INTO `sys_area` VALUES ('2837', '仁布县', 'renbuxian', 'rbx', '540200', '540229', null, null);
INSERT INTO `sys_area` VALUES ('2838', '康马县', 'kangmaxian', 'kmx', '540200', '540230', null, null);
INSERT INTO `sys_area` VALUES ('2839', '定结县', 'dingjiexian', 'djx', '540200', '540231', null, null);
INSERT INTO `sys_area` VALUES ('2840', '仲巴县', 'zhongbaxian', 'zbx', '540200', '540232', null, null);
INSERT INTO `sys_area` VALUES ('2841', '亚东县', 'yadongxian', 'ydx', '540200', '540233', null, null);
INSERT INTO `sys_area` VALUES ('2842', '吉隆县', 'jilongxian', 'jlx', '540200', '540234', null, null);
INSERT INTO `sys_area` VALUES ('2843', '聂拉木县', 'nielamuxian', 'nlmx', '540200', '540235', null, null);
INSERT INTO `sys_area` VALUES ('2844', '萨嘎县', 'sagaxian', 'sgx', '540200', '540236', null, null);
INSERT INTO `sys_area` VALUES ('2845', '岗巴县', 'gangbaxian', 'gbx', '540200', '540237', null, null);
INSERT INTO `sys_area` VALUES ('2846', '昌都市', 'changdushi', 'cds', '540000', '540300', null, null);
INSERT INTO `sys_area` VALUES ('2847', '卡若区', 'karuoqu', 'krq', '540300', '540302', null, null);
INSERT INTO `sys_area` VALUES ('2848', '江达县', 'jiangdaxian', 'jdx', '540300', '540321', null, null);
INSERT INTO `sys_area` VALUES ('2849', '贡觉县', 'gongjuexian', 'gjx', '540300', '540322', null, null);
INSERT INTO `sys_area` VALUES ('2850', '类乌齐县', 'leiwuqixian', 'lwqx', '540300', '540323', null, null);
INSERT INTO `sys_area` VALUES ('2851', '丁青县', 'dingqingxian', 'dqx', '540300', '540324', null, null);
INSERT INTO `sys_area` VALUES ('2852', '察雅县', 'chayaxian', 'cyx', '540300', '540325', null, null);
INSERT INTO `sys_area` VALUES ('2853', '八宿县', 'basuxian', 'bsx', '540300', '540326', null, null);
INSERT INTO `sys_area` VALUES ('2854', '左贡县', 'zuogongxian', 'zgx', '540300', '540327', null, null);
INSERT INTO `sys_area` VALUES ('2855', '芒康县', 'mangkangxian', 'mkx', '540300', '540328', null, null);
INSERT INTO `sys_area` VALUES ('2856', '洛隆县', 'luolongxian', 'llx', '540300', '540329', null, null);
INSERT INTO `sys_area` VALUES ('2857', '边坝县', 'bianbaxian', 'bbx', '540300', '540330', null, null);
INSERT INTO `sys_area` VALUES ('2858', '林芝市', 'linzhishi', 'lzs', '540000', '540400', null, null);
INSERT INTO `sys_area` VALUES ('2859', '巴宜区', 'bayiqu', 'byq', '540400', '540402', null, null);
INSERT INTO `sys_area` VALUES ('2860', '工布江达县', 'gongbujiangdaxian', 'gbjdx', '540400', '540421', null, null);
INSERT INTO `sys_area` VALUES ('2861', '米林县', 'milinxian', 'mlx', '540400', '540422', null, null);
INSERT INTO `sys_area` VALUES ('2862', '墨脱县', 'motuoxian', 'mtx', '540400', '540423', null, null);
INSERT INTO `sys_area` VALUES ('2863', '波密县', 'bomixian', 'bmx', '540400', '540424', null, null);
INSERT INTO `sys_area` VALUES ('2864', '察隅县', 'chayuxian', 'cyx', '540400', '540425', null, null);
INSERT INTO `sys_area` VALUES ('2865', '朗县', 'langxian', 'lx', '540400', '540426', null, null);
INSERT INTO `sys_area` VALUES ('2866', '山南市', 'shannanshi', 'sns', '540000', '540500', null, null);
INSERT INTO `sys_area` VALUES ('2867', '乃东区', 'naidongqu', 'ndq', '540500', '540502', null, null);
INSERT INTO `sys_area` VALUES ('2868', '扎囊县', 'zhanangxian', 'znx', '540500', '540521', null, null);
INSERT INTO `sys_area` VALUES ('2869', '贡嘎县', 'gonggaxian', 'ggx', '540500', '540522', null, null);
INSERT INTO `sys_area` VALUES ('2870', '桑日县', 'sangrixian', 'srx', '540500', '540523', null, null);
INSERT INTO `sys_area` VALUES ('2871', '琼结县', 'qiongjiexian', 'qjx', '540500', '540524', null, null);
INSERT INTO `sys_area` VALUES ('2872', '曲松县', 'qusongxian', 'qsx', '540500', '540525', null, null);
INSERT INTO `sys_area` VALUES ('2873', '措美县', 'cuomeixian', 'cmx', '540500', '540526', null, null);
INSERT INTO `sys_area` VALUES ('2874', '洛扎县', 'luozhaxian', 'lzx', '540500', '540527', null, null);
INSERT INTO `sys_area` VALUES ('2875', '加查县', 'jiachaxian', 'jcx', '540500', '540528', null, null);
INSERT INTO `sys_area` VALUES ('2876', '隆子县', 'longzixian', 'lzx', '540500', '540529', null, null);
INSERT INTO `sys_area` VALUES ('2877', '错那县', 'cuonaxian', 'cnx', '540500', '540530', null, null);
INSERT INTO `sys_area` VALUES ('2878', '浪卡子县', 'langkazixian', 'lkzx', '540500', '540531', null, null);
INSERT INTO `sys_area` VALUES ('2879', '那曲市', 'naqushi', 'nqs', '540000', '540600', null, null);
INSERT INTO `sys_area` VALUES ('2880', '色尼区', 'seniqu', 'snq', '540600', '540602', null, null);
INSERT INTO `sys_area` VALUES ('2881', '嘉黎县 ', 'jialixian?', 'jlx ', '540600', '540621', null, null);
INSERT INTO `sys_area` VALUES ('2882', '比如县 ', 'biruxian?', 'brx ', '540600', '540622', null, null);
INSERT INTO `sys_area` VALUES ('2883', '聂荣县 ', 'nierongxian?', 'nrx ', '540600', '540623', null, null);
INSERT INTO `sys_area` VALUES ('2884', '安多县 ', 'anduoxian?', 'adx ', '540600', '540624', null, null);
INSERT INTO `sys_area` VALUES ('2885', '申扎县 ', 'shenzhaxian?', 'szx ', '540600', '540625', null, null);
INSERT INTO `sys_area` VALUES ('2886', '索县   ', 'suoxian???', 'sx   ', '540600', '540626', null, null);
INSERT INTO `sys_area` VALUES ('2887', '班戈县 ', 'bangexian?', 'bgx ', '540600', '540627', null, null);
INSERT INTO `sys_area` VALUES ('2888', '巴青县 ', 'baqingxian?', 'bqx ', '540600', '540628', null, null);
INSERT INTO `sys_area` VALUES ('2889', '尼玛县 ', 'nimaxian?', 'nmx ', '540600', '540629', null, null);
INSERT INTO `sys_area` VALUES ('2890', '双湖县 ', 'shuanghuxian?', 'shx ', '540600', '540630', null, null);
INSERT INTO `sys_area` VALUES ('2891', '阿里地区', 'alidiqu', 'aldq', '540000', '542500', null, null);
INSERT INTO `sys_area` VALUES ('2892', '普兰县', 'pulanxian', 'plx', '542500', '542521', null, null);
INSERT INTO `sys_area` VALUES ('2893', '札达县', 'zhadaxian', 'zdx', '542500', '542522', null, null);
INSERT INTO `sys_area` VALUES ('2894', '噶尔县', 'gaerxian', 'gex', '542500', '542523', null, null);
INSERT INTO `sys_area` VALUES ('2895', '日土县', 'rituxian', 'rtx', '542500', '542524', null, null);
INSERT INTO `sys_area` VALUES ('2896', '革吉县', 'gejixian', 'gjx', '542500', '542525', null, null);
INSERT INTO `sys_area` VALUES ('2897', '改则县', 'gaizexian', 'gzx', '542500', '542526', null, null);
INSERT INTO `sys_area` VALUES ('2898', '措勤县', 'cuoqinxian', 'cqx', '542500', '542527', null, null);
INSERT INTO `sys_area` VALUES ('2899', '陕西省', 'shanxisheng', 'sxs', '0', '610000', null, null);
INSERT INTO `sys_area` VALUES ('2900', '西安市', 'xianshi', 'xas', '610000', '610100', null, null);
INSERT INTO `sys_area` VALUES ('2901', '新城区', 'xinchengqu', 'xcq', '610100', '610102', null, null);
INSERT INTO `sys_area` VALUES ('2902', '碑林区', 'beilinqu', 'blq', '610100', '610103', null, null);
INSERT INTO `sys_area` VALUES ('2903', '莲湖区', 'lianhuqu', 'lhq', '610100', '610104', null, null);
INSERT INTO `sys_area` VALUES ('2904', '灞桥区', 'zuoqiaoqu', '灞qq', '610100', '610111', null, null);
INSERT INTO `sys_area` VALUES ('2905', '未央区', 'weiyangqu', 'wyq', '610100', '610112', null, null);
INSERT INTO `sys_area` VALUES ('2906', '雁塔区', 'yantaqu', 'ytq', '610100', '610113', null, null);
INSERT INTO `sys_area` VALUES ('2907', '阎良区', 'yanliangqu', 'ylq', '610100', '610114', null, null);
INSERT INTO `sys_area` VALUES ('2908', '临潼区', 'linzuoqu', 'l潼q', '610100', '610115', null, null);
INSERT INTO `sys_area` VALUES ('2909', '长安区', 'changanqu', 'caq', '610100', '610116', null, null);
INSERT INTO `sys_area` VALUES ('2910', '高陵区', 'gaolingqu', 'glq', '610100', '610117', null, null);
INSERT INTO `sys_area` VALUES ('2911', '鄠邑区', 'zuoyiqu', '鄠yq', '610100', '610118', null, null);
INSERT INTO `sys_area` VALUES ('2912', '蓝田县', 'lantianxian', 'ltx', '610100', '610122', null, null);
INSERT INTO `sys_area` VALUES ('2913', '周至县', 'zhouzhixian', 'zzx', '610100', '610124', null, null);
INSERT INTO `sys_area` VALUES ('2914', '铜川市', 'tongchuanshi', 'tcs', '610000', '610200', null, null);
INSERT INTO `sys_area` VALUES ('2915', '王益区', 'wangyiqu', 'wyq', '610200', '610202', null, null);
INSERT INTO `sys_area` VALUES ('2916', '印台区', 'yintaiqu', 'ytq', '610200', '610203', null, null);
INSERT INTO `sys_area` VALUES ('2917', '耀州区', 'yaozhouqu', 'yzq', '610200', '610204', null, null);
INSERT INTO `sys_area` VALUES ('2918', '宜君县', 'yijunxian', 'yjx', '610200', '610222', null, null);
INSERT INTO `sys_area` VALUES ('2919', '宝鸡市', 'baojishi', 'bjs', '610000', '610300', null, null);
INSERT INTO `sys_area` VALUES ('2920', '渭滨区', 'weibinqu', 'wbq', '610300', '610302', null, null);
INSERT INTO `sys_area` VALUES ('2921', '金台区', 'jintaiqu', 'jtq', '610300', '610303', null, null);
INSERT INTO `sys_area` VALUES ('2922', '陈仓区', 'chencangqu', 'ccq', '610300', '610304', null, null);
INSERT INTO `sys_area` VALUES ('2923', '凤翔县', 'fengxiangxian', 'fxx', '610300', '610322', null, null);
INSERT INTO `sys_area` VALUES ('2924', '岐山县', 'zuoshanxian', '岐sx', '610300', '610323', null, null);
INSERT INTO `sys_area` VALUES ('2925', '扶风县', 'fufengxian', 'ffx', '610300', '610324', null, null);
INSERT INTO `sys_area` VALUES ('2926', '眉县', 'meixian', 'mx', '610300', '610326', null, null);
INSERT INTO `sys_area` VALUES ('2927', '陇县', 'longxian', 'lx', '610300', '610327', null, null);
INSERT INTO `sys_area` VALUES ('2928', '千阳县', 'qianyangxian', 'qyx', '610300', '610328', null, null);
INSERT INTO `sys_area` VALUES ('2929', '麟游县', 'zuoyouxian', '麟yx', '610300', '610329', null, null);
INSERT INTO `sys_area` VALUES ('2930', '凤县', 'fengxian', 'fx', '610300', '610330', null, null);
INSERT INTO `sys_area` VALUES ('2931', '太白县', 'taibaixian', 'tbx', '610300', '610331', null, null);
INSERT INTO `sys_area` VALUES ('2932', '咸阳市', 'xianyangshi', 'xys', '610000', '610400', null, null);
INSERT INTO `sys_area` VALUES ('2933', '秦都区', 'qinduqu', 'qdq', '610400', '610402', null, null);
INSERT INTO `sys_area` VALUES ('2934', '杨陵区', 'yanglingqu', 'ylq', '610400', '610403', null, null);
INSERT INTO `sys_area` VALUES ('2935', '渭城区', 'weichengqu', 'wcq', '610400', '610404', null, null);
INSERT INTO `sys_area` VALUES ('2936', '三原县', 'sanyuanxian', 'syx', '610400', '610422', null, null);
INSERT INTO `sys_area` VALUES ('2937', '泾阳县', 'zuoyangxian', '泾yx', '610400', '610423', null, null);
INSERT INTO `sys_area` VALUES ('2938', '乾县', 'qianxian', 'qx', '610400', '610424', null, null);
INSERT INTO `sys_area` VALUES ('2939', '礼泉县', 'liquanxian', 'lqx', '610400', '610425', null, null);
INSERT INTO `sys_area` VALUES ('2940', '永寿县', 'yongshouxian', 'ysx', '610400', '610426', null, null);
INSERT INTO `sys_area` VALUES ('2941', '长武县', 'changwuxian', 'cwx', '610400', '610428', null, null);
INSERT INTO `sys_area` VALUES ('2942', '旬邑县', 'xunyixian', 'xyx', '610400', '610429', null, null);
INSERT INTO `sys_area` VALUES ('2943', '淳化县', 'chunhuaxian', 'chx', '610400', '610430', null, null);
INSERT INTO `sys_area` VALUES ('2944', '武功县', 'wugongxian', 'wgx', '610400', '610431', null, null);
INSERT INTO `sys_area` VALUES ('2945', '兴平市', 'xingpingshi', 'xps', '610400', '610481', null, null);
INSERT INTO `sys_area` VALUES ('2946', '彬州市', 'binzhoushi', 'bzs', '610400', '610482', null, null);
INSERT INTO `sys_area` VALUES ('2947', '渭南市', 'weinanshi', 'wns', '610000', '610500', null, null);
INSERT INTO `sys_area` VALUES ('2948', '临渭区', 'linweiqu', 'lwq', '610500', '610502', null, null);
INSERT INTO `sys_area` VALUES ('2949', '华州区', 'huazhouqu', 'hzq', '610500', '610503', null, null);
INSERT INTO `sys_area` VALUES ('2950', '潼关县', 'zuoguanxian', '潼gx', '610500', '610522', null, null);
INSERT INTO `sys_area` VALUES ('2951', '大荔县', 'dalixian', 'dlx', '610500', '610523', null, null);
INSERT INTO `sys_area` VALUES ('2952', '合阳县', 'heyangxian', 'hyx', '610500', '610524', null, null);
INSERT INTO `sys_area` VALUES ('2953', '澄城县', 'chengchengxian', 'ccx', '610500', '610525', null, null);
INSERT INTO `sys_area` VALUES ('2954', '蒲城县', 'puchengxian', 'pcx', '610500', '610526', null, null);
INSERT INTO `sys_area` VALUES ('2955', '白水县', 'baishuixian', 'bsx', '610500', '610527', null, null);
INSERT INTO `sys_area` VALUES ('2956', '富平县', 'fupingxian', 'fpx', '610500', '610528', null, null);
INSERT INTO `sys_area` VALUES ('2957', '韩城市', 'hanchengshi', 'hcs', '610500', '610581', null, null);
INSERT INTO `sys_area` VALUES ('2958', '华阴市', 'huayinshi', 'hys', '610500', '610582', null, null);
INSERT INTO `sys_area` VALUES ('2959', '延安市', 'yananshi', 'yas', '610000', '610600', null, null);
INSERT INTO `sys_area` VALUES ('2960', '宝塔区', 'baotaqu', 'btq', '610600', '610602', null, null);
INSERT INTO `sys_area` VALUES ('2961', '安塞区', 'ansaiqu', 'asq', '610600', '610603', null, null);
INSERT INTO `sys_area` VALUES ('2962', '延长县', 'yanchangxian', 'ycx', '610600', '610621', null, null);
INSERT INTO `sys_area` VALUES ('2963', '延川县', 'yanchuanxian', 'ycx', '610600', '610622', null, null);
INSERT INTO `sys_area` VALUES ('2964', '子长县', 'zichangxian', 'zcx', '610600', '610623', null, null);
INSERT INTO `sys_area` VALUES ('2965', '志丹县', 'zhidanxian', 'zdx', '610600', '610625', null, null);
INSERT INTO `sys_area` VALUES ('2966', '吴起县', 'wuqixian', 'wqx', '610600', '610626', null, null);
INSERT INTO `sys_area` VALUES ('2967', '甘泉县', 'ganquanxian', 'gqx', '610600', '610627', null, null);
INSERT INTO `sys_area` VALUES ('2968', '富县', 'fuxian', 'fx', '610600', '610628', null, null);
INSERT INTO `sys_area` VALUES ('2969', '洛川县', 'luochuanxian', 'lcx', '610600', '610629', null, null);
INSERT INTO `sys_area` VALUES ('2970', '宜川县', 'yichuanxian', 'ycx', '610600', '610630', null, null);
INSERT INTO `sys_area` VALUES ('2971', '黄龙县', 'huanglongxian', 'hlx', '610600', '610631', null, null);
INSERT INTO `sys_area` VALUES ('2972', '黄陵县', 'huanglingxian', 'hlx', '610600', '610632', null, null);
INSERT INTO `sys_area` VALUES ('2973', '汉中市', 'hanzhongshi', 'hzs', '610000', '610700', null, null);
INSERT INTO `sys_area` VALUES ('2974', '汉台区', 'hantaiqu', 'htq', '610700', '610702', null, null);
INSERT INTO `sys_area` VALUES ('2975', '南郑区', 'nanzhengqu', 'nzq', '610700', '610703', null, null);
INSERT INTO `sys_area` VALUES ('2976', '城固县', 'chengguxian', 'cgx', '610700', '610722', null, null);
INSERT INTO `sys_area` VALUES ('2977', '洋县', 'yangxian', 'yx', '610700', '610723', null, null);
INSERT INTO `sys_area` VALUES ('2978', '西乡县', 'xixiangxian', 'xxx', '610700', '610724', null, null);
INSERT INTO `sys_area` VALUES ('2979', '勉县', 'mianxian', 'mx', '610700', '610725', null, null);
INSERT INTO `sys_area` VALUES ('2980', '宁强县', 'ningqiangxian', 'nqx', '610700', '610726', null, null);
INSERT INTO `sys_area` VALUES ('2981', '略阳县', 'lueyangxian', 'lyx', '610700', '610727', null, null);
INSERT INTO `sys_area` VALUES ('2982', '镇巴县', 'zhenbaxian', 'zbx', '610700', '610728', null, null);
INSERT INTO `sys_area` VALUES ('2983', '留坝县', 'liubaxian', 'lbx', '610700', '610729', null, null);
INSERT INTO `sys_area` VALUES ('2984', '佛坪县', 'fopingxian', 'fpx', '610700', '610730', null, null);
INSERT INTO `sys_area` VALUES ('2985', '榆林市', 'yulinshi', 'yls', '610000', '610800', null, null);
INSERT INTO `sys_area` VALUES ('2986', '榆阳区', 'yuyangqu', 'yyq', '610800', '610802', null, null);
INSERT INTO `sys_area` VALUES ('2987', '横山区', 'hengshanqu', 'hsq', '610800', '610803', null, null);
INSERT INTO `sys_area` VALUES ('2988', '府谷县', 'fuguxian', 'fgx', '610800', '610822', null, null);
INSERT INTO `sys_area` VALUES ('2989', '靖边县', 'jingbianxian', 'jbx', '610800', '610824', null, null);
INSERT INTO `sys_area` VALUES ('2990', '定边县', 'dingbianxian', 'dbx', '610800', '610825', null, null);
INSERT INTO `sys_area` VALUES ('2991', '绥德县', 'suidexian', 'sdx', '610800', '610826', null, null);
INSERT INTO `sys_area` VALUES ('2992', '米脂县', 'mizhixian', 'mzx', '610800', '610827', null, null);
INSERT INTO `sys_area` VALUES ('2993', '佳县', 'jiaxian', 'jx', '610800', '610828', null, null);
INSERT INTO `sys_area` VALUES ('2994', '吴堡县', 'wubaoxian', 'wbx', '610800', '610829', null, null);
INSERT INTO `sys_area` VALUES ('2995', '清涧县', 'qingjianxian', 'qjx', '610800', '610830', null, null);
INSERT INTO `sys_area` VALUES ('2996', '子洲县', 'zizhouxian', 'zzx', '610800', '610831', null, null);
INSERT INTO `sys_area` VALUES ('2997', '神木市', 'shenmushi', 'sms', '610800', '610881', null, null);
INSERT INTO `sys_area` VALUES ('2998', '安康市', 'ankangshi', 'aks', '610000', '610900', null, null);
INSERT INTO `sys_area` VALUES ('2999', '汉滨区', 'hanbinqu', 'hbq', '610900', '610902', null, null);
INSERT INTO `sys_area` VALUES ('3000', '汉阴县', 'hanyinxian', 'hyx', '610900', '610921', null, null);
INSERT INTO `sys_area` VALUES ('3001', '石泉县', 'shiquanxian', 'sqx', '610900', '610922', null, null);
INSERT INTO `sys_area` VALUES ('3002', '宁陕县', 'ningshanxian', 'nsx', '610900', '610923', null, null);
INSERT INTO `sys_area` VALUES ('3003', '紫阳县', 'ziyangxian', 'zyx', '610900', '610924', null, null);
INSERT INTO `sys_area` VALUES ('3004', '岚皋县', 'zuogaoxian', '岚gx', '610900', '610925', null, null);
INSERT INTO `sys_area` VALUES ('3005', '平利县', 'pinglixian', 'plx', '610900', '610926', null, null);
INSERT INTO `sys_area` VALUES ('3006', '镇坪县', 'zhenpingxian', 'zpx', '610900', '610927', null, null);
INSERT INTO `sys_area` VALUES ('3007', '旬阳县', 'xunyangxian', 'xyx', '610900', '610928', null, null);
INSERT INTO `sys_area` VALUES ('3008', '白河县', 'baihexian', 'bhx', '610900', '610929', null, null);
INSERT INTO `sys_area` VALUES ('3009', '商洛市', 'shangluoshi', 'sls', '610000', '611000', null, null);
INSERT INTO `sys_area` VALUES ('3010', '商州区', 'shangzhouqu', 'szq', '611000', '611002', null, null);
INSERT INTO `sys_area` VALUES ('3011', '洛南县', 'luonanxian', 'lnx', '611000', '611021', null, null);
INSERT INTO `sys_area` VALUES ('3012', '丹凤县', 'danfengxian', 'dfx', '611000', '611022', null, null);
INSERT INTO `sys_area` VALUES ('3013', '商南县', 'shangnanxian', 'snx', '611000', '611023', null, null);
INSERT INTO `sys_area` VALUES ('3014', '山阳县', 'shanyangxian', 'syx', '611000', '611024', null, null);
INSERT INTO `sys_area` VALUES ('3015', '镇安县', 'zhenanxian', 'zax', '611000', '611025', null, null);
INSERT INTO `sys_area` VALUES ('3016', '柞水县', 'zuoshuixian', 'zsx', '611000', '611026', null, null);
INSERT INTO `sys_area` VALUES ('3017', '甘肃省', 'gansusheng', 'gss', '0', '620000', null, null);
INSERT INTO `sys_area` VALUES ('3018', '兰州市', 'lanzhoushi', 'lzs', '620000', '620100', null, null);
INSERT INTO `sys_area` VALUES ('3019', '城关区', 'chengguanqu', 'cgq', '620100', '620102', null, null);
INSERT INTO `sys_area` VALUES ('3020', '七里河区', 'qilihequ', 'qlhq', '620100', '620103', null, null);
INSERT INTO `sys_area` VALUES ('3021', '西固区', 'xiguqu', 'xgq', '620100', '620104', null, null);
INSERT INTO `sys_area` VALUES ('3022', '安宁区', 'anningqu', 'anq', '620100', '620105', null, null);
INSERT INTO `sys_area` VALUES ('3023', '红古区', 'hongguqu', 'hgq', '620100', '620111', null, null);
INSERT INTO `sys_area` VALUES ('3024', '永登县', 'yongdengxian', 'ydx', '620100', '620121', null, null);
INSERT INTO `sys_area` VALUES ('3025', '皋兰县', 'gaolanxian', 'glx', '620100', '620122', null, null);
INSERT INTO `sys_area` VALUES ('3026', '榆中县', 'yuzhongxian', 'yzx', '620100', '620123', null, null);
INSERT INTO `sys_area` VALUES ('3027', '嘉峪关市', 'jiayuguanshi', 'jygs', '620000', '620200', null, null);
INSERT INTO `sys_area` VALUES ('3028', '金昌市', 'jinchangshi', 'jcs', '620000', '620300', null, null);
INSERT INTO `sys_area` VALUES ('3029', '金川区', 'jinchuanqu', 'jcq', '620300', '620302', null, null);
INSERT INTO `sys_area` VALUES ('3030', '永昌县', 'yongchangxian', 'ycx', '620300', '620321', null, null);
INSERT INTO `sys_area` VALUES ('3031', '白银市', 'baiyinshi', 'bys', '620000', '620400', null, null);
INSERT INTO `sys_area` VALUES ('3032', '白银区', 'baiyinqu', 'byq', '620400', '620402', null, null);
INSERT INTO `sys_area` VALUES ('3033', '平川区', 'pingchuanqu', 'pcq', '620400', '620403', null, null);
INSERT INTO `sys_area` VALUES ('3034', '靖远县', 'jingyuanxian', 'jyx', '620400', '620421', null, null);
INSERT INTO `sys_area` VALUES ('3035', '会宁县', 'huiningxian', 'hnx', '620400', '620422', null, null);
INSERT INTO `sys_area` VALUES ('3036', '景泰县', 'jingtaixian', 'jtx', '620400', '620423', null, null);
INSERT INTO `sys_area` VALUES ('3037', '天水市', 'tianshuishi', 'tss', '620000', '620500', null, null);
INSERT INTO `sys_area` VALUES ('3038', '秦州区', 'qinzhouqu', 'qzq', '620500', '620502', null, null);
INSERT INTO `sys_area` VALUES ('3039', '麦积区', 'maijiqu', 'mjq', '620500', '620503', null, null);
INSERT INTO `sys_area` VALUES ('3040', '清水县', 'qingshuixian', 'qsx', '620500', '620521', null, null);
INSERT INTO `sys_area` VALUES ('3041', '秦安县', 'qinanxian', 'qax', '620500', '620522', null, null);
INSERT INTO `sys_area` VALUES ('3042', '甘谷县', 'ganguxian', 'ggx', '620500', '620523', null, null);
INSERT INTO `sys_area` VALUES ('3043', '武山县', 'wushanxian', 'wsx', '620500', '620524', null, null);
INSERT INTO `sys_area` VALUES ('3044', '张家川回族自治县', 'zhangjiachuanhuizuzizhixian', 'zjchzzzx', '620500', '620525', null, null);
INSERT INTO `sys_area` VALUES ('3045', '武威市', 'wuweishi', 'wws', '620000', '620600', null, null);
INSERT INTO `sys_area` VALUES ('3046', '凉州区', 'liangzhouqu', 'lzq', '620600', '620602', null, null);
INSERT INTO `sys_area` VALUES ('3047', '民勤县', 'minqinxian', 'mqx', '620600', '620621', null, null);
INSERT INTO `sys_area` VALUES ('3048', '古浪县', 'gulangxian', 'glx', '620600', '620622', null, null);
INSERT INTO `sys_area` VALUES ('3049', '天祝藏族自治县', 'tianzhucangzuzizhixian', 'tzczzzx', '620600', '620623', null, null);
INSERT INTO `sys_area` VALUES ('3050', '张掖市', 'zhangyeshi', 'zys', '620000', '620700', null, null);
INSERT INTO `sys_area` VALUES ('3051', '甘州区', 'ganzhouqu', 'gzq', '620700', '620702', null, null);
INSERT INTO `sys_area` VALUES ('3052', '肃南裕固族自治县', 'sunanyuguzuzizhixian', 'snygzzzx', '620700', '620721', null, null);
INSERT INTO `sys_area` VALUES ('3053', '民乐县', 'minlexian', 'mlx', '620700', '620722', null, null);
INSERT INTO `sys_area` VALUES ('3054', '临泽县', 'linzexian', 'lzx', '620700', '620723', null, null);
INSERT INTO `sys_area` VALUES ('3055', '高台县', 'gaotaixian', 'gtx', '620700', '620724', null, null);
INSERT INTO `sys_area` VALUES ('3056', '山丹县', 'shandanxian', 'sdx', '620700', '620725', null, null);
INSERT INTO `sys_area` VALUES ('3057', '平凉市', 'pingliangshi', 'pls', '620000', '620800', null, null);
INSERT INTO `sys_area` VALUES ('3058', '崆峒区', 'zuozuoqu', '崆峒q', '620800', '620802', null, null);
INSERT INTO `sys_area` VALUES ('3059', '泾川县', 'zuochuanxian', '泾cx', '620800', '620821', null, null);
INSERT INTO `sys_area` VALUES ('3060', '灵台县', 'lingtaixian', 'ltx', '620800', '620822', null, null);
INSERT INTO `sys_area` VALUES ('3061', '崇信县', 'chongxinxian', 'cxx', '620800', '620823', null, null);
INSERT INTO `sys_area` VALUES ('3062', '庄浪县', 'zhuanglangxian', 'zlx', '620800', '620825', null, null);
INSERT INTO `sys_area` VALUES ('3063', '静宁县', 'jingningxian', 'jnx', '620800', '620826', null, null);
INSERT INTO `sys_area` VALUES ('3064', '华亭市', 'huatingshi', 'hts', '620800', '620881', null, null);
INSERT INTO `sys_area` VALUES ('3065', '酒泉市', 'jiuquanshi', 'jqs', '620000', '620900', null, null);
INSERT INTO `sys_area` VALUES ('3066', '肃州区', 'suzhouqu', 'szq', '620900', '620902', null, null);
INSERT INTO `sys_area` VALUES ('3067', '金塔县', 'jintaxian', 'jtx', '620900', '620921', null, null);
INSERT INTO `sys_area` VALUES ('3068', '瓜州县', 'guazhouxian', 'gzx', '620900', '620922', null, null);
INSERT INTO `sys_area` VALUES ('3069', '肃北蒙古族自治县', 'subeimengguzuzizhixian', 'sbmgzzzx', '620900', '620923', null, null);
INSERT INTO `sys_area` VALUES ('3070', '阿克塞哈萨克族自治县', 'akesaihasakezuzizhixian', 'akshskzzzx', '620900', '620924', null, null);
INSERT INTO `sys_area` VALUES ('3071', '玉门市', 'yumenshi', 'yms', '620900', '620981', null, null);
INSERT INTO `sys_area` VALUES ('3072', '敦煌市', 'dunhuangshi', 'dhs', '620900', '620982', null, null);
INSERT INTO `sys_area` VALUES ('3073', '庆阳市', 'qingyangshi', 'qys', '620000', '621000', null, null);
INSERT INTO `sys_area` VALUES ('3074', '西峰区', 'xifengqu', 'xfq', '621000', '621002', null, null);
INSERT INTO `sys_area` VALUES ('3075', '庆城县', 'qingchengxian', 'qcx', '621000', '621021', null, null);
INSERT INTO `sys_area` VALUES ('3076', '环县', 'huanxian', 'hx', '621000', '621022', null, null);
INSERT INTO `sys_area` VALUES ('3077', '华池县', 'huachixian', 'hcx', '621000', '621023', null, null);
INSERT INTO `sys_area` VALUES ('3078', '合水县', 'heshuixian', 'hsx', '621000', '621024', null, null);
INSERT INTO `sys_area` VALUES ('3079', '正宁县', 'zhengningxian', 'znx', '621000', '621025', null, null);
INSERT INTO `sys_area` VALUES ('3080', '宁县', 'ningxian', 'nx', '621000', '621026', null, null);
INSERT INTO `sys_area` VALUES ('3081', '镇原县', 'zhenyuanxian', 'zyx', '621000', '621027', null, null);
INSERT INTO `sys_area` VALUES ('3082', '定西市', 'dingxishi', 'dxs', '620000', '621100', null, null);
INSERT INTO `sys_area` VALUES ('3083', '安定区', 'andingqu', 'adq', '621100', '621102', null, null);
INSERT INTO `sys_area` VALUES ('3084', '通渭县', 'tongweixian', 'twx', '621100', '621121', null, null);
INSERT INTO `sys_area` VALUES ('3085', '陇西县', 'longxixian', 'lxx', '621100', '621122', null, null);
INSERT INTO `sys_area` VALUES ('3086', '渭源县', 'weiyuanxian', 'wyx', '621100', '621123', null, null);
INSERT INTO `sys_area` VALUES ('3087', '临洮县', 'linzuoxian', 'l洮x', '621100', '621124', null, null);
INSERT INTO `sys_area` VALUES ('3088', '漳县', 'zhangxian', 'zx', '621100', '621125', null, null);
INSERT INTO `sys_area` VALUES ('3089', '岷县', 'zuoxian', '岷x', '621100', '621126', null, null);
INSERT INTO `sys_area` VALUES ('3090', '陇南市', 'longnanshi', 'lns', '620000', '621200', null, null);
INSERT INTO `sys_area` VALUES ('3091', '武都区', 'wuduqu', 'wdq', '621200', '621202', null, null);
INSERT INTO `sys_area` VALUES ('3092', '成县', 'chengxian', 'cx', '621200', '621221', null, null);
INSERT INTO `sys_area` VALUES ('3093', '文县', 'wenxian', 'wx', '621200', '621222', null, null);
INSERT INTO `sys_area` VALUES ('3094', '宕昌县', 'zuochangxian', '宕cx', '621200', '621223', null, null);
INSERT INTO `sys_area` VALUES ('3095', '康县', 'kangxian', 'kx', '621200', '621224', null, null);
INSERT INTO `sys_area` VALUES ('3096', '西和县', 'xihexian', 'xhx', '621200', '621225', null, null);
INSERT INTO `sys_area` VALUES ('3097', '礼县', 'lixian', 'lx', '621200', '621226', null, null);
INSERT INTO `sys_area` VALUES ('3098', '徽县', 'huixian', 'hx', '621200', '621227', null, null);
INSERT INTO `sys_area` VALUES ('3099', '两当县', 'liangdangxian', 'ldx', '621200', '621228', null, null);
INSERT INTO `sys_area` VALUES ('3100', '临夏回族自治州', 'linxiahuizuzizhizhou', 'lxhzzzz', '620000', '622900', null, null);
INSERT INTO `sys_area` VALUES ('3101', '临夏市', 'linxiashi', 'lxs', '622900', '622901', null, null);
INSERT INTO `sys_area` VALUES ('3102', '临夏县', 'linxiaxian', 'lxx', '622900', '622921', null, null);
INSERT INTO `sys_area` VALUES ('3103', '康乐县', 'kanglexian', 'klx', '622900', '622922', null, null);
INSERT INTO `sys_area` VALUES ('3104', '永靖县', 'yongjingxian', 'yjx', '622900', '622923', null, null);
INSERT INTO `sys_area` VALUES ('3105', '广河县', 'guanghexian', 'ghx', '622900', '622924', null, null);
INSERT INTO `sys_area` VALUES ('3106', '和政县', 'hezhengxian', 'hzx', '622900', '622925', null, null);
INSERT INTO `sys_area` VALUES ('3107', '东乡族自治县', 'dongxiangzuzizhixian', 'dxzzzx', '622900', '622926', null, null);
INSERT INTO `sys_area` VALUES ('3108', '积石山保安族东乡族撒拉族自治县', 'jishishanbaoanzudongxiangzusalazuzizhixian', 'jssbazdxzslzzzx', '622900', '622927', null, null);
INSERT INTO `sys_area` VALUES ('3109', '甘南藏族自治州', 'gannancangzuzizhizhou', 'gnczzzz', '620000', '623000', null, null);
INSERT INTO `sys_area` VALUES ('3110', '合作市', 'hezuoshi', 'hzs', '623000', '623001', null, null);
INSERT INTO `sys_area` VALUES ('3111', '临潭县', 'lintanxian', 'ltx', '623000', '623021', null, null);
INSERT INTO `sys_area` VALUES ('3112', '卓尼县', 'zhuonixian', 'znx', '623000', '623022', null, null);
INSERT INTO `sys_area` VALUES ('3113', '舟曲县', 'zhouquxian', 'zqx', '623000', '623023', null, null);
INSERT INTO `sys_area` VALUES ('3114', '迭部县', 'diebuxian', 'dbx', '623000', '623024', null, null);
INSERT INTO `sys_area` VALUES ('3115', '玛曲县', 'maquxian', 'mqx', '623000', '623025', null, null);
INSERT INTO `sys_area` VALUES ('3116', '碌曲县', 'luquxian', 'lqx', '623000', '623026', null, null);
INSERT INTO `sys_area` VALUES ('3117', '夏河县', 'xiahexian', 'xhx', '623000', '623027', null, null);
INSERT INTO `sys_area` VALUES ('3118', '青海省', 'qinghaisheng', 'qhs', '0', '630000', null, null);
INSERT INTO `sys_area` VALUES ('3119', '西宁市', 'xiningshi', 'xns', '630000', '630100', null, null);
INSERT INTO `sys_area` VALUES ('3120', '城东区', 'chengdongqu', 'cdq', '630100', '630102', null, null);
INSERT INTO `sys_area` VALUES ('3121', '城中区', 'chengzhongqu', 'czq', '630100', '630103', null, null);
INSERT INTO `sys_area` VALUES ('3122', '城西区', 'chengxiqu', 'cxq', '630100', '630104', null, null);
INSERT INTO `sys_area` VALUES ('3123', '城北区', 'chengbeiqu', 'cbq', '630100', '630105', null, null);
INSERT INTO `sys_area` VALUES ('3124', '大通回族土族自治县', 'datonghuizutuzuzizhixian', 'dthztzzzx', '630100', '630121', null, null);
INSERT INTO `sys_area` VALUES ('3125', '湟中县', 'zuozhongxian', '湟zx', '630100', '630122', null, null);
INSERT INTO `sys_area` VALUES ('3126', '湟源县', 'zuoyuanxian', '湟yx', '630100', '630123', null, null);
INSERT INTO `sys_area` VALUES ('3127', '海东市', 'haidongshi', 'hds', '630000', '630200', null, null);
INSERT INTO `sys_area` VALUES ('3128', '乐都区', 'leduqu', 'ldq', '630200', '630202', null, null);
INSERT INTO `sys_area` VALUES ('3129', '平安区', 'pinganqu', 'paq', '630200', '630203', null, null);
INSERT INTO `sys_area` VALUES ('3130', '民和回族土族自治县', 'minhehuizutuzuzizhixian', 'mhhztzzzx', '630200', '630222', null, null);
INSERT INTO `sys_area` VALUES ('3131', '互助土族自治县', 'huzhutuzuzizhixian', 'hztzzzx', '630200', '630223', null, null);
INSERT INTO `sys_area` VALUES ('3132', '化隆回族自治县', 'hualonghuizuzizhixian', 'hlhzzzx', '630200', '630224', null, null);
INSERT INTO `sys_area` VALUES ('3133', '循化撒拉族自治县', 'xunhuasalazuzizhixian', 'xhslzzzx', '630200', '630225', null, null);
INSERT INTO `sys_area` VALUES ('3134', '海北藏族自治州', 'haibeicangzuzizhizhou', 'hbczzzz', '630000', '632200', null, null);
INSERT INTO `sys_area` VALUES ('3135', '门源回族自治县', 'menyuanhuizuzizhixian', 'myhzzzx', '632200', '632221', null, null);
INSERT INTO `sys_area` VALUES ('3136', '祁连县', 'qilianxian', 'qlx', '632200', '632222', null, null);
INSERT INTO `sys_area` VALUES ('3137', '海晏县', 'haizuoxian', 'h晏x', '632200', '632223', null, null);
INSERT INTO `sys_area` VALUES ('3138', '刚察县', 'gangchaxian', 'gcx', '632200', '632224', null, null);
INSERT INTO `sys_area` VALUES ('3139', '黄南藏族自治州', 'huangnancangzuzizhizhou', 'hnczzzz', '630000', '632300', null, null);
INSERT INTO `sys_area` VALUES ('3140', '同仁县', 'tongrenxian', 'trx', '632300', '632321', null, null);
INSERT INTO `sys_area` VALUES ('3141', '尖扎县', 'jianzhaxian', 'jzx', '632300', '632322', null, null);
INSERT INTO `sys_area` VALUES ('3142', '泽库县', 'zekuxian', 'zkx', '632300', '632323', null, null);
INSERT INTO `sys_area` VALUES ('3143', '河南蒙古族自治县', 'henanmengguzuzizhixian', 'hnmgzzzx', '632300', '632324', null, null);
INSERT INTO `sys_area` VALUES ('3144', '海南藏族自治州', 'hainancangzuzizhizhou', 'hnczzzz', '630000', '632500', null, null);
INSERT INTO `sys_area` VALUES ('3145', '共和县', 'gonghexian', 'ghx', '632500', '632521', null, null);
INSERT INTO `sys_area` VALUES ('3146', '同德县', 'tongdexian', 'tdx', '632500', '632522', null, null);
INSERT INTO `sys_area` VALUES ('3147', '贵德县', 'guidexian', 'gdx', '632500', '632523', null, null);
INSERT INTO `sys_area` VALUES ('3148', '兴海县', 'xinghaixian', 'xhx', '632500', '632524', null, null);
INSERT INTO `sys_area` VALUES ('3149', '贵南县', 'guinanxian', 'gnx', '632500', '632525', null, null);
INSERT INTO `sys_area` VALUES ('3150', '果洛藏族自治州', 'guoluocangzuzizhizhou', 'glczzzz', '630000', '632600', null, null);
INSERT INTO `sys_area` VALUES ('3151', '玛沁县', 'maqinxian', 'mqx', '632600', '632621', null, null);
INSERT INTO `sys_area` VALUES ('3152', '班玛县', 'banmaxian', 'bmx', '632600', '632622', null, null);
INSERT INTO `sys_area` VALUES ('3153', '甘德县', 'gandexian', 'gdx', '632600', '632623', null, null);
INSERT INTO `sys_area` VALUES ('3154', '达日县', 'darixian', 'drx', '632600', '632624', null, null);
INSERT INTO `sys_area` VALUES ('3155', '久治县', 'jiuzhixian', 'jzx', '632600', '632625', null, null);
INSERT INTO `sys_area` VALUES ('3156', '玛多县', 'maduoxian', 'mdx', '632600', '632626', null, null);
INSERT INTO `sys_area` VALUES ('3157', '玉树藏族自治州', 'yushucangzuzizhizhou', 'ysczzzz', '630000', '632700', null, null);
INSERT INTO `sys_area` VALUES ('3158', '玉树市', 'yushushi', 'yss', '632700', '632701', null, null);
INSERT INTO `sys_area` VALUES ('3159', '杂多县', 'zaduoxian', 'zdx', '632700', '632722', null, null);
INSERT INTO `sys_area` VALUES ('3160', '称多县', 'chengduoxian', 'cdx', '632700', '632723', null, null);
INSERT INTO `sys_area` VALUES ('3161', '治多县', 'zhiduoxian', 'zdx', '632700', '632724', null, null);
INSERT INTO `sys_area` VALUES ('3162', '囊谦县', 'nangqianxian', 'nqx', '632700', '632725', null, null);
INSERT INTO `sys_area` VALUES ('3163', '曲麻莱县', 'qumalaixian', 'qmlx', '632700', '632726', null, null);
INSERT INTO `sys_area` VALUES ('3164', '海西蒙古族藏族自治州', 'haiximengguzucangzuzizhizhou', 'hxmgzczzzz', '630000', '632800', null, null);
INSERT INTO `sys_area` VALUES ('3165', '格尔木市', 'geermushi', 'gems', '632800', '632801', null, null);
INSERT INTO `sys_area` VALUES ('3166', '德令哈市', 'delinghashi', 'dlhs', '632800', '632802', null, null);
INSERT INTO `sys_area` VALUES ('3167', '茫崖市', 'mangyashi', 'mys', '632800', '632803', null, null);
INSERT INTO `sys_area` VALUES ('3168', '乌兰县', 'wulanxian', 'wlx', '632800', '632821', null, null);
INSERT INTO `sys_area` VALUES ('3169', '都兰县', 'dulanxian', 'dlx', '632800', '632822', null, null);
INSERT INTO `sys_area` VALUES ('3170', '天峻县', 'tianjunxian', 'tjx', '632800', '632823', null, null);
INSERT INTO `sys_area` VALUES ('3171', '宁夏回族自治区', 'ningxiahuizuzizhiqu', 'nxhzzzq', '0', '640000', null, null);
INSERT INTO `sys_area` VALUES ('3172', '银川市', 'yinchuanshi', 'ycs', '640000', '640100', null, null);
INSERT INTO `sys_area` VALUES ('3173', '兴庆区', 'xingqingqu', 'xqq', '640100', '640104', null, null);
INSERT INTO `sys_area` VALUES ('3174', '西夏区', 'xixiaqu', 'xxq', '640100', '640105', null, null);
INSERT INTO `sys_area` VALUES ('3175', '金凤区', 'jinfengqu', 'jfq', '640100', '640106', null, null);
INSERT INTO `sys_area` VALUES ('3176', '永宁县', 'yongningxian', 'ynx', '640100', '640121', null, null);
INSERT INTO `sys_area` VALUES ('3177', '贺兰县', 'helanxian', 'hlx', '640100', '640122', null, null);
INSERT INTO `sys_area` VALUES ('3178', '灵武市', 'lingwushi', 'lws', '640100', '640181', null, null);
INSERT INTO `sys_area` VALUES ('3179', '石嘴山市', 'shizuishanshi', 'szss', '640000', '640200', null, null);
INSERT INTO `sys_area` VALUES ('3180', '大武口区', 'dawukouqu', 'dwkq', '640200', '640202', null, null);
INSERT INTO `sys_area` VALUES ('3181', '惠农区', 'huinongqu', 'hnq', '640200', '640205', null, null);
INSERT INTO `sys_area` VALUES ('3182', '平罗县', 'pingluoxian', 'plx', '640200', '640221', null, null);
INSERT INTO `sys_area` VALUES ('3183', '吴忠市', 'wuzhongshi', 'wzs', '640000', '640300', null, null);
INSERT INTO `sys_area` VALUES ('3184', '利通区', 'litongqu', 'ltq', '640300', '640302', null, null);
INSERT INTO `sys_area` VALUES ('3185', '红寺堡区', 'hongsibaoqu', 'hsbq', '640300', '640303', null, null);
INSERT INTO `sys_area` VALUES ('3186', '盐池县', 'yanchixian', 'ycx', '640300', '640323', null, null);
INSERT INTO `sys_area` VALUES ('3187', '同心县', 'tongxinxian', 'txx', '640300', '640324', null, null);
INSERT INTO `sys_area` VALUES ('3188', '青铜峡市', 'qingtongxiashi', 'qtxs', '640300', '640381', null, null);
INSERT INTO `sys_area` VALUES ('3189', '固原市', 'guyuanshi', 'gys', '640000', '640400', null, null);
INSERT INTO `sys_area` VALUES ('3190', '原州区', 'yuanzhouqu', 'yzq', '640400', '640402', null, null);
INSERT INTO `sys_area` VALUES ('3191', '西吉县', 'xijixian', 'xjx', '640400', '640422', null, null);
INSERT INTO `sys_area` VALUES ('3192', '隆德县', 'longdexian', 'ldx', '640400', '640423', null, null);
INSERT INTO `sys_area` VALUES ('3193', '泾源县', 'zuoyuanxian', '泾yx', '640400', '640424', null, null);
INSERT INTO `sys_area` VALUES ('3194', '彭阳县', 'pengyangxian', 'pyx', '640400', '640425', null, null);
INSERT INTO `sys_area` VALUES ('3195', '中卫市', 'zhongweishi', 'zws', '640000', '640500', null, null);
INSERT INTO `sys_area` VALUES ('3196', '沙坡头区', 'shapotouqu', 'sptq', '640500', '640502', null, null);
INSERT INTO `sys_area` VALUES ('3197', '中宁县', 'zhongningxian', 'znx', '640500', '640521', null, null);
INSERT INTO `sys_area` VALUES ('3198', '海原县', 'haiyuanxian', 'hyx', '640500', '640522', null, null);
INSERT INTO `sys_area` VALUES ('3199', '新疆维吾尔自治区', 'xinjiangweiwuerzizhiqu', 'xjwwezzq', '0', '650000', null, null);
INSERT INTO `sys_area` VALUES ('3200', '乌鲁木齐市', 'wulumuqishi', 'wlmqs', '650000', '650100', null, null);
INSERT INTO `sys_area` VALUES ('3201', '天山区', 'tianshanqu', 'tsq', '650100', '650102', null, null);
INSERT INTO `sys_area` VALUES ('3202', '沙依巴克区', 'shayibakequ', 'sybkq', '650100', '650103', null, null);
INSERT INTO `sys_area` VALUES ('3203', '新市区', 'xinshiqu', 'xsq', '650100', '650104', null, null);
INSERT INTO `sys_area` VALUES ('3204', '水磨沟区', 'shuimogouqu', 'smgq', '650100', '650105', null, null);
INSERT INTO `sys_area` VALUES ('3205', '头屯河区', 'toutunhequ', 'tthq', '650100', '650106', null, null);
INSERT INTO `sys_area` VALUES ('3206', '达坂城区', 'dazuochengqu', 'd坂cq', '650100', '650107', null, null);
INSERT INTO `sys_area` VALUES ('3207', '米东区', 'midongqu', 'mdq', '650100', '650109', null, null);
INSERT INTO `sys_area` VALUES ('3208', '乌鲁木齐县', 'wulumuqixian', 'wlmqx', '650100', '650121', null, null);
INSERT INTO `sys_area` VALUES ('3209', '克拉玛依市', 'kelamayishi', 'klmys', '650000', '650200', null, null);
INSERT INTO `sys_area` VALUES ('3210', '独山子区', 'dushanziqu', 'dszq', '650200', '650202', null, null);
INSERT INTO `sys_area` VALUES ('3211', '克拉玛依区', 'kelamayiqu', 'klmyq', '650200', '650203', null, null);
INSERT INTO `sys_area` VALUES ('3212', '白碱滩区', 'baijiantanqu', 'bjtq', '650200', '650204', null, null);
INSERT INTO `sys_area` VALUES ('3213', '乌尔禾区', 'wuerhequ', 'wehq', '650200', '650205', null, null);
INSERT INTO `sys_area` VALUES ('3214', '吐鲁番市', 'tulufanshi', 'tlfs', '650000', '650400', null, null);
INSERT INTO `sys_area` VALUES ('3215', '高昌区', 'gaochangqu', 'gcq', '650400', '650402', null, null);
INSERT INTO `sys_area` VALUES ('3216', '鄯善县', 'zuoshanxian', '鄯sx', '650400', '650421', null, null);
INSERT INTO `sys_area` VALUES ('3217', '托克逊县', 'tuokexunxian', 'tkxx', '650400', '650422', null, null);
INSERT INTO `sys_area` VALUES ('3218', '哈密市', 'hamishi', 'hms', '650000', '650500', null, null);
INSERT INTO `sys_area` VALUES ('3219', '伊州区', 'yizhouqu', 'yzq', '650500', '650502', null, null);
INSERT INTO `sys_area` VALUES ('3220', '巴里坤哈萨克自治县', 'balikunhasakezizhixian', 'blkhskzzx', '650500', '650521', null, null);
INSERT INTO `sys_area` VALUES ('3221', '伊吾县', 'yiwuxian', 'ywx', '650500', '650522', null, null);
INSERT INTO `sys_area` VALUES ('3222', '昌吉回族自治州', 'changjihuizuzizhizhou', 'cjhzzzz', '650000', '652300', null, null);
INSERT INTO `sys_area` VALUES ('3223', '昌吉市', 'changjishi', 'cjs', '652300', '652301', null, null);
INSERT INTO `sys_area` VALUES ('3224', '阜康市', 'fukangshi', 'fks', '652300', '652302', null, null);
INSERT INTO `sys_area` VALUES ('3225', '呼图壁县', 'hutubixian', 'htbx', '652300', '652323', null, null);
INSERT INTO `sys_area` VALUES ('3226', '玛纳斯县', 'manasixian', 'mnsx', '652300', '652324', null, null);
INSERT INTO `sys_area` VALUES ('3227', '奇台县', 'qitaixian', 'qtx', '652300', '652325', null, null);
INSERT INTO `sys_area` VALUES ('3228', '吉木萨尔县', 'jimusaerxian', 'jmsex', '652300', '652327', null, null);
INSERT INTO `sys_area` VALUES ('3229', '木垒哈萨克自治县', 'muleihasakezizhixian', 'mlhskzzx', '652300', '652328', null, null);
INSERT INTO `sys_area` VALUES ('3230', '博尔塔拉蒙古自治州', 'boertalamengguzizhizhou', 'betlmgzzz', '650000', '652700', null, null);
INSERT INTO `sys_area` VALUES ('3231', '博乐市', 'boleshi', 'bls', '652700', '652701', null, null);
INSERT INTO `sys_area` VALUES ('3232', '阿拉山口市', 'alashankoushi', 'alsks', '652700', '652702', null, null);
INSERT INTO `sys_area` VALUES ('3233', '精河县', 'jinghexian', 'jhx', '652700', '652722', null, null);
INSERT INTO `sys_area` VALUES ('3234', '温泉县', 'wenquanxian', 'wqx', '652700', '652723', null, null);
INSERT INTO `sys_area` VALUES ('3235', '巴音郭楞蒙古自治州', 'bayinguolengmengguzizhizhou', 'byglmgzzz', '650000', '652800', null, null);
INSERT INTO `sys_area` VALUES ('3236', '库尔勒市', 'kuerleshi', 'kels', '652800', '652801', null, null);
INSERT INTO `sys_area` VALUES ('3237', '轮台县', 'luntaixian', 'ltx', '652800', '652822', null, null);
INSERT INTO `sys_area` VALUES ('3238', '尉犁县', 'weilixian', 'wlx', '652800', '652823', null, null);
INSERT INTO `sys_area` VALUES ('3239', '若羌县', 'ruoqiangxian', 'rqx', '652800', '652824', null, null);
INSERT INTO `sys_area` VALUES ('3240', '且末县', 'qiemoxian', 'qmx', '652800', '652825', null, null);
INSERT INTO `sys_area` VALUES ('3241', '焉耆回族自治县', 'yanzuohuizuzizhixian', 'y耆hzzzx', '652800', '652826', null, null);
INSERT INTO `sys_area` VALUES ('3242', '和静县', 'hejingxian', 'hjx', '652800', '652827', null, null);
INSERT INTO `sys_area` VALUES ('3243', '和硕县', 'heshuoxian', 'hsx', '652800', '652828', null, null);
INSERT INTO `sys_area` VALUES ('3244', '博湖县', 'bohuxian', 'bhx', '652800', '652829', null, null);
INSERT INTO `sys_area` VALUES ('3245', '阿克苏地区', 'akesudiqu', 'aksdq', '650000', '652900', null, null);
INSERT INTO `sys_area` VALUES ('3246', '阿克苏市', 'akesushi', 'akss', '652900', '652901', null, null);
INSERT INTO `sys_area` VALUES ('3247', '温宿县', 'wensuxian', 'wsx', '652900', '652922', null, null);
INSERT INTO `sys_area` VALUES ('3248', '库车县', 'kuchexian', 'kcx', '652900', '652923', null, null);
INSERT INTO `sys_area` VALUES ('3249', '沙雅县', 'shayaxian', 'syx', '652900', '652924', null, null);
INSERT INTO `sys_area` VALUES ('3250', '新和县', 'xinhexian', 'xhx', '652900', '652925', null, null);
INSERT INTO `sys_area` VALUES ('3251', '拜城县', 'baichengxian', 'bcx', '652900', '652926', null, null);
INSERT INTO `sys_area` VALUES ('3252', '乌什县', 'wushixian', 'wsx', '652900', '652927', null, null);
INSERT INTO `sys_area` VALUES ('3253', '阿瓦提县', 'awatixian', 'awtx', '652900', '652928', null, null);
INSERT INTO `sys_area` VALUES ('3254', '柯坪县', 'kepingxian', 'kpx', '652900', '652929', null, null);
INSERT INTO `sys_area` VALUES ('3255', '克孜勒苏柯尔克孜自治州', 'kezilesukeerkezizizhizhou', 'kzlskekzzzz', '650000', '653000', null, null);
INSERT INTO `sys_area` VALUES ('3256', '阿图什市', 'atushishi', 'atss', '653000', '653001', null, null);
INSERT INTO `sys_area` VALUES ('3257', '阿克陶县', 'aketaoxian', 'aktx', '653000', '653022', null, null);
INSERT INTO `sys_area` VALUES ('3258', '阿合奇县', 'aheqixian', 'ahqx', '653000', '653023', null, null);
INSERT INTO `sys_area` VALUES ('3259', '乌恰县', 'wuqiaxian', 'wqx', '653000', '653024', null, null);
INSERT INTO `sys_area` VALUES ('3260', '喀什地区', 'kashidiqu', 'ksdq', '650000', '653100', null, null);
INSERT INTO `sys_area` VALUES ('3261', '喀什市', 'kashishi', 'kss', '653100', '653101', null, null);
INSERT INTO `sys_area` VALUES ('3262', '疏附县', 'shufuxian', 'sfx', '653100', '653121', null, null);
INSERT INTO `sys_area` VALUES ('3263', '疏勒县', 'shulexian', 'slx', '653100', '653122', null, null);
INSERT INTO `sys_area` VALUES ('3264', '英吉沙县', 'yingjishaxian', 'yjsx', '653100', '653123', null, null);
INSERT INTO `sys_area` VALUES ('3265', '泽普县', 'zepuxian', 'zpx', '653100', '653124', null, null);
INSERT INTO `sys_area` VALUES ('3266', '莎车县', 'shachexian', 'scx', '653100', '653125', null, null);
INSERT INTO `sys_area` VALUES ('3267', '叶城县', 'yechengxian', 'ycx', '653100', '653126', null, null);
INSERT INTO `sys_area` VALUES ('3268', '麦盖提县', 'maigaitixian', 'mgtx', '653100', '653127', null, null);
INSERT INTO `sys_area` VALUES ('3269', '岳普湖县', 'yuepuhuxian', 'yphx', '653100', '653128', null, null);
INSERT INTO `sys_area` VALUES ('3270', '伽师县', 'zuoshixian', '伽sx', '653100', '653129', null, null);
INSERT INTO `sys_area` VALUES ('3271', '巴楚县', 'bachuxian', 'bcx', '653100', '653130', null, null);
INSERT INTO `sys_area` VALUES ('3272', '塔什库尔干塔吉克自治县', 'tashikuergantajikezizhixian', 'tskegtjkzzx', '653100', '653131', null, null);
INSERT INTO `sys_area` VALUES ('3273', '和田地区', 'hetiandiqu', 'htdq', '650000', '653200', null, null);
INSERT INTO `sys_area` VALUES ('3274', '和田市', 'hetianshi', 'hts', '653200', '653201', null, null);
INSERT INTO `sys_area` VALUES ('3275', '和田县', 'hetianxian', 'htx', '653200', '653221', null, null);
INSERT INTO `sys_area` VALUES ('3276', '墨玉县', 'moyuxian', 'myx', '653200', '653222', null, null);
INSERT INTO `sys_area` VALUES ('3277', '皮山县', 'pishanxian', 'psx', '653200', '653223', null, null);
INSERT INTO `sys_area` VALUES ('3278', '洛浦县', 'luopuxian', 'lpx', '653200', '653224', null, null);
INSERT INTO `sys_area` VALUES ('3279', '策勒县', 'celexian', 'clx', '653200', '653225', null, null);
INSERT INTO `sys_area` VALUES ('3280', '于田县', 'yutianxian', 'ytx', '653200', '653226', null, null);
INSERT INTO `sys_area` VALUES ('3281', '民丰县', 'minfengxian', 'mfx', '653200', '653227', null, null);
INSERT INTO `sys_area` VALUES ('3282', '伊犁哈萨克自治州', 'yilihasakezizhizhou', 'ylhskzzz', '650000', '654000', null, null);
INSERT INTO `sys_area` VALUES ('3283', '伊宁市', 'yiningshi', 'yns', '654000', '654002', null, null);
INSERT INTO `sys_area` VALUES ('3284', '奎屯市', 'kuitunshi', 'kts', '654000', '654003', null, null);
INSERT INTO `sys_area` VALUES ('3285', '霍尔果斯市', 'huoerguosishi', 'hegss', '654000', '654004', null, null);
INSERT INTO `sys_area` VALUES ('3286', '伊宁县', 'yiningxian', 'ynx', '654000', '654021', null, null);
INSERT INTO `sys_area` VALUES ('3287', '察布查尔锡伯自治县', 'chabuchaerxibozizhixian', 'cbcexbzzx', '654000', '654022', null, null);
INSERT INTO `sys_area` VALUES ('3288', '霍城县', 'huochengxian', 'hcx', '654000', '654023', null, null);
INSERT INTO `sys_area` VALUES ('3289', '巩留县', 'gongliuxian', 'glx', '654000', '654024', null, null);
INSERT INTO `sys_area` VALUES ('3290', '新源县', 'xinyuanxian', 'xyx', '654000', '654025', null, null);
INSERT INTO `sys_area` VALUES ('3291', '昭苏县', 'zhaosuxian', 'zsx', '654000', '654026', null, null);
INSERT INTO `sys_area` VALUES ('3292', '特克斯县', 'tekesixian', 'tksx', '654000', '654027', null, null);
INSERT INTO `sys_area` VALUES ('3293', '尼勒克县', 'nilekexian', 'nlkx', '654000', '654028', null, null);
INSERT INTO `sys_area` VALUES ('3294', '塔城地区', 'tachengdiqu', 'tcdq', '650000', '654200', null, null);
INSERT INTO `sys_area` VALUES ('3295', '塔城市', 'tachengshi', 'tcs', '654200', '654201', null, null);
INSERT INTO `sys_area` VALUES ('3296', '乌苏市', 'wusushi', 'wss', '654200', '654202', null, null);
INSERT INTO `sys_area` VALUES ('3297', '额敏县', 'eminxian', 'emx', '654200', '654221', null, null);
INSERT INTO `sys_area` VALUES ('3298', '沙湾县', 'shawanxian', 'swx', '654200', '654223', null, null);
INSERT INTO `sys_area` VALUES ('3299', '托里县', 'tuolixian', 'tlx', '654200', '654224', null, null);
INSERT INTO `sys_area` VALUES ('3300', '裕民县', 'yuminxian', 'ymx', '654200', '654225', null, null);
INSERT INTO `sys_area` VALUES ('3301', '和布克赛尔蒙古自治县', 'hebukesaiermengguzizhixian', 'hbksemgzzx', '654200', '654226', null, null);
INSERT INTO `sys_area` VALUES ('3302', '阿勒泰地区', 'aletaidiqu', 'altdq', '650000', '654300', null, null);
INSERT INTO `sys_area` VALUES ('3303', '阿勒泰市', 'aletaishi', 'alts', '654300', '654301', null, null);
INSERT INTO `sys_area` VALUES ('3304', '布尔津县', 'buerjinxian', 'bejx', '654300', '654321', null, null);
INSERT INTO `sys_area` VALUES ('3305', '富蕴县', 'fuyunxian', 'fyx', '654300', '654322', null, null);
INSERT INTO `sys_area` VALUES ('3306', '福海县', 'fuhaixian', 'fhx', '654300', '654323', null, null);
INSERT INTO `sys_area` VALUES ('3307', '哈巴河县', 'habahexian', 'hbhx', '654300', '654324', null, null);
INSERT INTO `sys_area` VALUES ('3308', '青河县', 'qinghexian', 'qhx', '654300', '654325', null, null);
INSERT INTO `sys_area` VALUES ('3309', '吉木乃县', 'jimunaixian', 'jmnx', '654300', '654326', null, null);
INSERT INTO `sys_area` VALUES ('3310', '石河子市', 'shihezishi', 'shzs', '650000', '659001', null, null);
INSERT INTO `sys_area` VALUES ('3311', '阿拉尔市', 'alaershi', 'ales', '650000', '659002', null, null);
INSERT INTO `sys_area` VALUES ('3312', '图木舒克市', 'tumushukeshi', 'tmsks', '650000', '659003', null, null);
INSERT INTO `sys_area` VALUES ('3313', '五家渠市', 'wujiaqushi', 'wjqs', '650000', '659004', null, null);
INSERT INTO `sys_area` VALUES ('3314', '北屯市', 'beitunshi', 'bts', '650000', '659005', null, null);
INSERT INTO `sys_area` VALUES ('3315', '铁门关市', 'tiemenguanshi', 'tmgs', '650000', '659006', null, null);
INSERT INTO `sys_area` VALUES ('3316', '双河市', 'shuangheshi', 'shs', '650000', '659007', null, null);
INSERT INTO `sys_area` VALUES ('3317', '可克达拉市', 'kekedalashi', 'kkdls', '650000', '659008', null, null);
INSERT INTO `sys_area` VALUES ('3318', '昆玉市', 'kunyushi', 'kys', '650000', '659009', null, null);
INSERT INTO `sys_area` VALUES ('3319', '台湾省', 'taiwansheng', 'tws', '0', '710000', null, null);
INSERT INTO `sys_area` VALUES ('3320', '香港特别行政区', 'xianggangtebiexingzhengqu', 'xgtbxzq', '0', '810000', null, null);
INSERT INTO `sys_area` VALUES ('3321', '澳门特别行政区', 'aomentebiexingzhengqu', 'amtbxzq', '0', '820000', null, null);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `name` varchar(255) DEFAULT NULL COMMENT '配置名称',
  `value` varchar(255) DEFAULT NULL COMMENT '配置值',
  `remark` varchar(255) DEFAULT NULL COMMENT '配置描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典排序值     用来排序',
  `name` varchar(80) NOT NULL COMMENT '字典名',
  `value` int(8) NOT NULL COMMENT '字典值',
  `type` varchar(80) NOT NULL COMMENT '字典分类，同一个分类的type一样',
  `remarks` varchar(255) DEFAULT NULL COMMENT '字典描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `value_2` (`value`,`type`),
  KEY `value` (`value`) USING BTREE,
  KEY `type` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='字典表';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('1', '塔机', '1', 'equipmentType', '设备类型');
INSERT INTO `sys_dictionary` VALUES ('2', '升降机', '2', 'equipmentType', '设备类型');
INSERT INTO `sys_dictionary` VALUES ('3', '卸料平台', '3', 'equipmentType', '设备类型');
INSERT INTO `sys_dictionary` VALUES ('4', '雾炮喷淋', '4', 'equipmentType', '设备类型');
INSERT INTO `sys_dictionary` VALUES ('5', '扬尘噪音', '5', 'equipmentType', '设备类型');
INSERT INTO `sys_dictionary` VALUES ('6', '房屋建筑工程', '1', 'proType', '工地项目类型');
INSERT INTO `sys_dictionary` VALUES ('7', '市政基础设施工程', '2', 'proType', '工地项目类型');
INSERT INTO `sys_dictionary` VALUES ('8', '其他', '99', 'proType', '工地项目类型');
INSERT INTO `sys_dictionary` VALUES ('9', '输变电工程', '77', 'proType', '工地项目类型');
INSERT INTO `sys_dictionary` VALUES ('44', '居住建筑', '2', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('43', '基础建设工程', '1', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('12', '道路喷淋', '1', 'FogGunType', '喷淋类型');
INSERT INTO `sys_dictionary` VALUES ('13', '塔吊喷淋', '2', 'FogGunType', '喷淋类型');
INSERT INTO `sys_dictionary` VALUES ('14', '其他喷淋', '3', 'FogGunType', '喷淋类型');
INSERT INTO `sys_dictionary` VALUES ('15', '摄像头(视频)', '6', 'equipmentType', '设备类型');
INSERT INTO `sys_dictionary` VALUES ('16', '男', '1', 'userSex', '用户性别');
INSERT INTO `sys_dictionary` VALUES ('17', '女', '0', 'userSex', '用户性别');
INSERT INTO `sys_dictionary` VALUES ('18', '管理人员', '1', 'personnelType', '工地人员类型');
INSERT INTO `sys_dictionary` VALUES ('19', '劳务人员', '2', 'personnelType', '工地人员类型');
INSERT INTO `sys_dictionary` VALUES ('20', '新建', '1', 'proConstructionNature', '建设性质');
INSERT INTO `sys_dictionary` VALUES ('21', '改建', '2', 'proConstructionNature', '建设性质');
INSERT INTO `sys_dictionary` VALUES ('22', '扩建', '3', 'proConstructionNature', '建设性质');
INSERT INTO `sys_dictionary` VALUES ('23', '拆除', '4', 'proConstructionNature', '建设性质');
INSERT INTO `sys_dictionary` VALUES ('24', '其他', '99', 'proConstructionNature', '建设性质');
INSERT INTO `sys_dictionary` VALUES ('25', '钢筋混凝土', '1', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('26', '砖混结构', '2', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('27', '底框结构', '3', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('28', '框架结构', '4', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('29', '框架——剪力墙结构', '5', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('30', '剪力墙结构', '6', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('31', '版主—剪力墙结构', '7', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('32', '短肢墙剪力墙结构', '8', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('33', '部分框支剪力墙结构', '9', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('34', '框—简体结构', '10', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('35', '筒中筒结构', '11', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('36', '异型柱框架结构', '12', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('37', '负责高层结构', '13', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('38', '混合结构', '14', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('39', '钢结构', '15', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('40', '排架结构', '16', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('41', '混凝土框架结构', '17', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('42', '其他', '99', 'proMainStructureType', '主体结构类型');
INSERT INTO `sys_dictionary` VALUES ('45', '居住建筑配套工程', '3', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('46', '公共建筑', '4', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('47', '办公建筑', '5', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('48', '商业建筑', '6', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('49', '旅游建筑', '7', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('50', '科教文卫建筑', '8', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('51', '交通运输类', '9', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('52', '通信建筑', '10', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('53', '公共建筑配套工程', '11', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('54', '商住楼', '12', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('55', '农业建筑', '13', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('56', '农业建筑配套工程', '14', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('57', '工业建筑', '15', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('58', '水泥熟料生产线', '16', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('59', '工业建筑配套工程', '17', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('60', '给水', '18', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('61', '排水', '19', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('62', '供电设施', '20', 'proFunction', '项目功能');
INSERT INTO `sys_dictionary` VALUES ('63', '其他', '21', 'proFunction', '项目功能');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序id',
  `uuid` varchar(32) NOT NULL COMMENT '数据id',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父模块id',
  `name` varchar(50) NOT NULL COMMENT '模块名称',
  `url` varchar(100) DEFAULT NULL COMMENT '模块路径',
  `path` varchar(100) DEFAULT NULL COMMENT '模块路径',
  `icon` varchar(80) DEFAULT NULL COMMENT '图标',
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '1工地模块菜单',
  `sortNum` int(4) DEFAULT NULL COMMENT '排序值',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '0正常 1 删除',
  PRIMARY KEY (`id`),
  KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('100', 'd081ec66631346b982822fd8b6b43f65', null, '工地首页', '#!dataview', 'console/dataview', 'layui-icon-website', '1', '100', '0');
INSERT INTO `sys_menu` VALUES ('101', '77c9b8767ebc46658b1e5c8073067cc8', null, '项目大事记', '#!memorabilia', 'console/memorabilia', 'layui-icon-read', '1', '200', '0');
INSERT INTO `sys_menu` VALUES ('102', '462695ca60d4445fbb78391c3c7939b5', null, '视频监控', '#!videomonitor', 'console/videomonitor', 'layui-icon-video', '1', '300', '0');
INSERT INTO `sys_menu` VALUES ('103', '6623d45c65094a8691a08e3dc69f34d1', null, '人员管理', '#!personmanagement', 'console/personmanagement', 'layui-icon-user', '1', '400', '0');
INSERT INTO `sys_menu` VALUES ('104', '7c592fdcb5e541b7b89940ddcb4ffe31', null, '绿色施工', '#!greenconstruction', 'console/greenconstruction', 'layui-icon-tree', '1', '500', '0');
INSERT INTO `sys_menu` VALUES ('105', '799dffd9a09346f6893f270a423386f6', null, '智能硬件', '#!smarthardware', 'console/smarthardware', 'layui-icon-util', '1', '600', '0');
INSERT INTO `sys_menu` VALUES ('106', 'ca7ce1b8a6f84a0da1dbb2f2f799aaa5', null, '工地管理', '#!sitemanage', 'manage/index', 'layui-icon-console', '1', '700', '0');

-- ----------------------------
-- Table structure for unit_company
-- ----------------------------
DROP TABLE IF EXISTS `unit_company`;
CREATE TABLE `unit_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id数据id',
  `uuid` varchar(32) NOT NULL COMMENT '数据唯一标识',
  `superior` varchar(32) DEFAULT NULL COMMENT '上级单位UUID',
  `companyName` varchar(100) NOT NULL COMMENT '企业名称',
  `type` int(2) NOT NULL COMMENT '1 建设 2施工 3设计 4监理5 勘察 6劳务分包商 7公司集团 8设备提供商 9设备代理商',
  `typeName` varchar(5) DEFAULT NULL COMMENT '和type对应的单位类型',
  `logo` varchar(18) DEFAULT NULL COMMENT '企业logo图片文件名',
  `shortName` varchar(10) NOT NULL COMMENT '企业简称',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '0 正常  1删除',
  `officialWebsite` varchar(100) DEFAULT NULL COMMENT '官网地址',
  `description` varchar(150) DEFAULT NULL COMMENT '企业描述',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=464 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='企业表';

-- ----------------------------
-- Records of unit_company
-- ----------------------------
INSERT INTO `unit_company` VALUES ('457', 'e1b32d4cd832448bb9ab24e19b2560b5', null, '（北京）一通无限科技有限公司', '7', '集团公司', null, '一通无限', '0', 'https://www.yeetong.cn', null, '1541866868307', '1542468251265');
INSERT INTO `unit_company` VALUES ('458', '77d68ca5bc8b4b249f5fb3b5f192653f', null, '未命名代理商', '9', '设备代理商', '', '代理商', '0', null, null, '1542468329901', '1542468329901');
INSERT INTO `unit_company` VALUES ('459', '22a667e553e04d52b8627c1ed45e247b', null, '未命名设备供应商', '8', '设备供应商', '', '设备供应商', '0', null, null, '1542468358490', '1542468358490');
INSERT INTO `unit_company` VALUES ('460', 'cb9cd655213f495092e638d5c7b386dc', null, '施工单位测试', '2', '施工单位', '', '施工单位', '0', null, null, '1543056861825', '1543056861825');
INSERT INTO `unit_company` VALUES ('461', '51e07e47462f424aac4dabb854fb2bbf', null, '分包商1', '6', '劳务分包商', '', '分包商1', '0', null, null, '1543056877461', '1543056877461');
INSERT INTO `unit_company` VALUES ('462', '9dfc04947515420687d14cdc7546d83d', null, '分包商2', '6', '劳务分包商', '', '分包商2', '0', null, null, '1543056887577', '1543056887577');
INSERT INTO `unit_company` VALUES ('463', '08c7310399cf4e44aa5296099f9daf23', null, '分包商3', '6', '劳务分包商', '', '分包商3', '0', null, null, '1543056899167', '1543056899167');

-- ----------------------------
-- Table structure for unit_department
-- ----------------------------
DROP TABLE IF EXISTS `unit_department`;
CREATE TABLE `unit_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列，排序用',
  `uuid` varchar(32) NOT NULL COMMENT '数据唯一标识',
  `parentUUID` varchar(32) DEFAULT NULL COMMENT '父节点uuid',
  `deptName` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `deptUUID` varchar(32) NOT NULL COMMENT '所属工地，公司，政务单位唯一标识',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '0正常  1删除',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部门表';

-- ----------------------------
-- Records of unit_department
-- ----------------------------
INSERT INTO `unit_department` VALUES ('246', '8851e9a232b94e41bbfb3d22e2859ecb', null, '系统运维部', '86221de342e0414a98046bcbbc7df735', '0', '1541866778524', '1543226936080');
INSERT INTO `unit_department` VALUES ('247', '8851e9a232b94e41bbfb3d22e2859ec3', '8851e9a232b94e41bbfb3d22e2859ecb', '系统运维部', '86221de342e0414a98046bcbbc7df735', '0', '1541866778524', '1543157570811');
INSERT INTO `unit_department` VALUES ('248', '1a0bf7fc1d3a418496cfd9c0ee0917f9', '8851e9a232b94e41bbfb3d22e2859ecb', 'ff', '86221de342e0414a98046bcbbc7df735', '1', '1543140836693', '1543155268690');
INSERT INTO `unit_department` VALUES ('249', '127b0638a0a14f6c98fd5748cc509fec', null, 'sasa', '86221de342e0414a98046bcbbc7df735', '1', '1543140844659', '1543155088140');
INSERT INTO `unit_department` VALUES ('250', '510dea696ee24b9d8b308bb0f1bbe616', null, 'sdsd', '86221de342e0414a98046bcbbc7df735', '1', '1543141790554', '1543155091895');
INSERT INTO `unit_department` VALUES ('251', '9440e87bad0847b6941cba2cc3bbe4a2', '510dea696ee24b9d8b308bb0f1bbe616', 'AAAA', '86221de342e0414a98046bcbbc7df735', '1', '1543150558763', '1543154762314');
INSERT INTO `unit_department` VALUES ('252', '857cc8d9fc1243888a7bef7041428dbe', null, 'AA', '86221de342e0414a98046bcbbc7df735', '1', '1543150671686', '1543155271871');
INSERT INTO `unit_department` VALUES ('253', '85120301d612450d968c1bdca200332f', null, 'AAA', '86221de342e0414a98046bcbbc7df735', '1', '1543150868749', '1543155083786');
INSERT INTO `unit_department` VALUES ('254', '38d39a9008f04253bf958a5d2e7dbb20', '85120301d612450d968c1bdca200332f', 'CCCC', '86221de342e0414a98046bcbbc7df735', '1', '1543150927278', '1543154756656');
INSERT INTO `unit_department` VALUES ('255', 'a9b448b15bd64ed3a46d73d0525b7cb8', null, 'BBB', '86221de342e0414a98046bcbbc7df735', '1', '1543150978926', '1543155080394');
INSERT INTO `unit_department` VALUES ('256', '3de0ea490461478287893495bcbf0eb6', null, 'AAA', '86221de342e0414a98046bcbbc7df735', '1', '1543155097207', '1543156807529');
INSERT INTO `unit_department` VALUES ('257', '51454e9eeb57495f97e8592c78b96d54', '3de0ea490461478287893495bcbf0eb6', 'ddd', '86221de342e0414a98046bcbbc7df735', '1', '1543155289238', '1543156805784');
INSERT INTO `unit_department` VALUES ('258', '76693c4be2b1428aa6222afe296b9137', null, '系统运维部', '86221de342e0414a98046bcbbc7df735', '1', '1543156828358', '1543156836593');
INSERT INTO `unit_department` VALUES ('259', '532421dfc9684d379df58a3cb5f96909', null, '系统运维部', '86221de342e0414a98046bcbbc7df735', '1', '1543157338366', '1543157413971');

-- ----------------------------
-- Table structure for unit_group
-- ----------------------------
DROP TABLE IF EXISTS `unit_group`;
CREATE TABLE `unit_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) NOT NULL COMMENT '班组id数据id唯一主键',
  `name` varchar(80) NOT NULL COMMENT '班组名称',
  `proUuid` varchar(32) NOT NULL COMMENT '工地uuid',
  `companyUuid` varchar(32) DEFAULT NULL COMMENT '劳务分包商企业uuid',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新时间',
  `state` int(2) DEFAULT NULL COMMENT '0正常 1删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `proUuid` (`proUuid`),
  KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='劳务班组';

-- ----------------------------
-- Records of unit_group
-- ----------------------------
INSERT INTO `unit_group` VALUES ('100', '51e07e47462f424aac4dabb854fb2bbf', '啛啛喳喳', '86221de342e0414a98046bcbbc7df735', '51e07e47462f424aac4dabb854fb2bbf', '111', '1543064773396', '1');
INSERT INTO `unit_group` VALUES ('101', '112', '订单2121', '86221de342e0414a98046bcbbc7df735', '51e07e47462f424aac4dabb854fb2bbf', '11', '1543063987018', '0');
INSERT INTO `unit_group` VALUES ('102', '23323', '全文388', '86221de342e0414a98046bcbbc7df735', '51e07e47462f424aac4dabb854fb2bbf', '232', '1543064007159', '0');
INSERT INTO `unit_group` VALUES ('103', 'cb010c0417754865be4dca833cf3d43c', '333', '86221de342e0414a98046bcbbc7df735', '51e07e47462f424aac4dabb854fb2bbf', '1543064743829', '1543064743829', '0');
INSERT INTO `unit_group` VALUES ('104', '8ab137fbdec74982b25bd6ba491a21b0', '232', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543064755007', '1543064769576', '1');
INSERT INTO `unit_group` VALUES ('105', '5397b49745ce41898789029b5b5b86fb', '2322', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543064760899', '1543064799952', '0');
INSERT INTO `unit_group` VALUES ('106', 'db2a3461930847d8961940c17191ceb0', '232', '86221de342e0414a98046bcbbc7df735', '08c7310399cf4e44aa5296099f9daf23', '1543064763943', '1543064766870', '1');
INSERT INTO `unit_group` VALUES ('107', '1b010de4202c4ebd9c0ffab4359c6969', '23', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543064785991', '1543064785991', '0');
INSERT INTO `unit_group` VALUES ('108', '17daba90c3704bc68561cefb9b15a406', 'oo', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543064922084', '1543064959378', '1');
INSERT INTO `unit_group` VALUES ('109', '6f61b2bc4f364b99b2e67b536c9e8769', 'oo0', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543064928517', '1543064928517', '0');
INSERT INTO `unit_group` VALUES ('110', 'e4b3817190f749b59cbbe6d09feba0a8', '112212', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543064965791', '1543133738990', '0');
INSERT INTO `unit_group` VALUES ('111', 'd9d7b84a767640f6b4a8fd54afe41ef3', '333s', '86221de342e0414a98046bcbbc7df735', '08c7310399cf4e44aa5296099f9daf23', '1543065041368', '1543156720708', '0');
INSERT INTO `unit_group` VALUES ('112', 'da0f7337dc3d43e5a2f9d746c9fb7b4f', 'dd', '86221de342e0414a98046bcbbc7df735', '51e07e47462f424aac4dabb854fb2bbf', '1543065258714', '1543132897605', '1');
INSERT INTO `unit_group` VALUES ('113', '481f79bfc31846949199426170f4d8de', '12121', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543135023856', '1543135023856', '0');
INSERT INTO `unit_group` VALUES ('114', '348f29ff11fb49019b69a2460af12251', 'sds', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543135355831', '1543135355831', '0');

-- ----------------------------
-- Table structure for unit_laborsubcontractor
-- ----------------------------
DROP TABLE IF EXISTS `unit_laborsubcontractor`;
CREATE TABLE `unit_laborsubcontractor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `proUuid` varchar(32) NOT NULL COMMENT '工地uuid',
  `companyUuid` varchar(32) NOT NULL COMMENT '劳务分包商uuid',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间毫秒值',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '状态0 正常1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='劳务分包单位和工地关联表';

-- ----------------------------
-- Records of unit_laborsubcontractor
-- ----------------------------
INSERT INTO `unit_laborsubcontractor` VALUES ('100', '86221de342e0414a98046bcbbc7df735', '51e07e47462f424aac4dabb854fb2bbf', '1543056916147', '0');
INSERT INTO `unit_laborsubcontractor` VALUES ('101', '86221de342e0414a98046bcbbc7df735', '9dfc04947515420687d14cdc7546d83d', '1543056916168', '0');
INSERT INTO `unit_laborsubcontractor` VALUES ('102', '86221de342e0414a98046bcbbc7df735', '08c7310399cf4e44aa5296099f9daf23', '1543056916187', '0');

-- ----------------------------
-- Table structure for unit_post
-- ----------------------------
DROP TABLE IF EXISTS `unit_post`;
CREATE TABLE `unit_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uuid` varchar(32) NOT NULL COMMENT 'uuid',
  `name` varchar(150) NOT NULL COMMENT '岗位名称',
  `proUuid` varchar(32) NOT NULL COMMENT '工地uuid',
  `deptUuid` varchar(32) NOT NULL COMMENT '部门uuid',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='岗位表';

-- ----------------------------
-- Records of unit_post
-- ----------------------------
INSERT INTO `unit_post` VALUES ('100', '8851e9a232b94e41bbfb3d22e2859ec1', '存储', '86221de342e0414a98046bcbbc7df735', '8851e9a232b94e41bbfb3d22e2859ecb', '0');
INSERT INTO `unit_post` VALUES ('101', '8851e9a232b94e41bbfb3d22e2859ec2', '存储2', '86221de342e0414a98046bcbbc7df735', '8851e9a232b94e41bbfb3d22e2859ecb', '1');
INSERT INTO `unit_post` VALUES ('103', 'e1e29ac37ce14d40b2a784a1f356adb8', '是的', '86221de342e0414a98046bcbbc7df735', '857cc8d9fc1243888a7bef7041428dbe', '1');
INSERT INTO `unit_post` VALUES ('104', '7a08dd12019345b0be6a87ae6f1f3c7c', 'asdas', '86221de342e0414a98046bcbbc7df735', '857cc8d9fc1243888a7bef7041428dbe', '1');
INSERT INTO `unit_post` VALUES ('105', 'ec7c4626af5b43fca61c9687d1c9212e', '大多数', '86221de342e0414a98046bcbbc7df735', '857cc8d9fc1243888a7bef7041428dbe', '1');
INSERT INTO `unit_post` VALUES ('112', '8c5a3f4248724daaa48498cc604fc2b0', 'ddd', '86221de342e0414a98046bcbbc7df735', 'a9b448b15bd64ed3a46d73d0525b7cb8', '1');
INSERT INTO `unit_post` VALUES ('113', 'f7086b018821420885da665823f0423d', 'aaa', '86221de342e0414a98046bcbbc7df735', '510dea696ee24b9d8b308bb0f1bbe616', '1');
INSERT INTO `unit_post` VALUES ('118', '4f9c237e61874dd091f221abf13c31c6', 'ddddd', '86221de342e0414a98046bcbbc7df735', '3de0ea490461478287893495bcbf0eb6', '1');
INSERT INTO `unit_post` VALUES ('119', 'a9f06f5bf2df4749a12cd33d8d8a8067', 'ddd', '86221de342e0414a98046bcbbc7df735', '3de0ea490461478287893495bcbf0eb6', '1');
INSERT INTO `unit_post` VALUES ('120', 'ec3231006f6c483fb751303505e452db', 'dddd', '86221de342e0414a98046bcbbc7df735', '3de0ea490461478287893495bcbf0eb6', '1');
INSERT INTO `unit_post` VALUES ('121', '6a65bcc9ab8b453e98320d4f89738b80', '12', '86221de342e0414a98046bcbbc7df735', '8851e9a232b94e41bbfb3d22e2859ec3', '0');
INSERT INTO `unit_post` VALUES ('122', '5e6ed55a067f4970a1a9a9b37d56e42f', '系统运维部', '86221de342e0414a98046bcbbc7df735', '532421dfc9684d379df58a3cb5f96909', '1');
INSERT INTO `unit_post` VALUES ('123', '71d57224751f460f941be2ee68329b4a', '系统运维部系统运维部', '86221de342e0414a98046bcbbc7df735', '532421dfc9684d379df58a3cb5f96909', '1');
INSERT INTO `unit_post` VALUES ('124', '3f1f6fae3b1c4615a40f5990ee556c52', '系统运维部,系统运维部', '86221de342e0414a98046bcbbc7df735', '532421dfc9684d379df58a3cb5f96909', '1');
INSERT INTO `unit_post` VALUES ('125', '75ec4c31dc4f4e80a215ae844a3e70b5', '测试', '86221de342e0414a98046bcbbc7df735', '8851e9a232b94e41bbfb3d22e2859ec3', '0');

-- ----------------------------
-- Table structure for unit_project
-- ----------------------------
DROP TABLE IF EXISTS `unit_project`;
CREATE TABLE `unit_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工地自增id',
  `uuid` varchar(32) NOT NULL COMMENT '工地id唯一标识',
  `name` varchar(32) DEFAULT NULL COMMENT '项目简称',
  `proName` varchar(64) NOT NULL COMMENT '工地名称全称呼',
  `proCode` varchar(16) DEFAULT NULL COMMENT '平台序列号，项目编码',
  `longitude` varchar(15) DEFAULT NULL COMMENT '工地经度',
  `latitude` varchar(15) DEFAULT NULL COMMENT '工地纬度',
  `companyUuid` varchar(32) DEFAULT NULL COMMENT '工地所属单位',
  `building` varchar(32) DEFAULT NULL COMMENT '建设单位UUID',
  `supervision` varchar(32) DEFAULT NULL COMMENT '监理单位UUID',
  `survey` varchar(32) DEFAULT NULL COMMENT '勘察单位UUD',
  `construction` varchar(32) DEFAULT NULL COMMENT '施工单位UUID',
  `design` varchar(32) DEFAULT NULL COMMENT '设计单位UUID',
  `width` int(8) DEFAULT NULL COMMENT '工地宽度,区位图宽度',
  `length` int(8) DEFAULT NULL COMMENT '工地长度,区位图长度',
  `locationMap` varchar(32) DEFAULT NULL COMMENT '区位图文件名称',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新时间',
  `personName` varchar(15) DEFAULT NULL COMMENT '现场联系人负责人',
  `phone` varchar(15) DEFAULT NULL COMMENT '现场联系人电话',
  `salesName` varchar(15) DEFAULT NULL COMMENT '销售人员姓名',
  `salesPhone` varchar(15) DEFAULT NULL COMMENT '销售联系电话',
  `state` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '0正常1 删除',
  `code` varchar(32) DEFAULT NULL COMMENT '工地二维码地址',
  `logo` varchar(32) DEFAULT NULL COMMENT '工地logo文件名',
  `measure` double(64,0) DEFAULT NULL COMMENT '建筑面积   平方米',
  `investment` double(64,0) DEFAULT NULL COMMENT '投资额  万元',
  `numberOfLayers` varchar(128) DEFAULT NULL COMMENT '建筑层数',
  `type` varchar(4) DEFAULT NULL COMMENT '工程类型    字典表  proType',
  `constructionNature` varchar(4) DEFAULT NULL COMMENT '建设性质    字典表  proConstructionNature',
  `mainStructureType` varchar(128) DEFAULT NULL COMMENT '主要结构类型   字典表   proMainStructureType',
  `functions` varchar(512) DEFAULT NULL COMMENT '项目功能（字典表  proFunctrion）、工程用途，坑爹不是字典的了',
  `planStartTime` bigint(16) DEFAULT NULL COMMENT '计划开工时间',
  `planEndTime` bigint(16) DEFAULT NULL COMMENT '计划竣工时间',
  `province` varchar(8) DEFAULT NULL COMMENT '工地所在省',
  `city` varchar(8) DEFAULT NULL COMMENT '工地所在市',
  `county` varchar(8) DEFAULT NULL COMMENT '工地所在区/县',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uuid` (`uuid`),
  KEY `companyUuid` (`companyUuid`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='工地表';

-- ----------------------------
-- Records of unit_project
-- ----------------------------
INSERT INTO `unit_project` VALUES ('105', '86221de342e0414a98046bcbbc7df735', '中冶华天', 'szx', '2018112000007264', null, null, 'e1b32d4cd832448bb9ab24e19b2560b5', null, null, null, 'cb9cd655213f495092e638d5c7b386dc', null, null, null, null, '1542689428849', '1543056916118', null, null, null, null, '0', null, null, null, null, null, null, null, '', '1,2', null, null, '100000', null, null);

-- ----------------------------
-- Table structure for unit_project_config
-- ----------------------------
DROP TABLE IF EXISTS `unit_project_config`;
CREATE TABLE `unit_project_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `proUuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '工地id',
  `isPersonnelPositioning` int(4) NOT NULL DEFAULT '0' COMMENT '是否以人员定位数据作为，考勤数据定位 1 人员定位，其他不是',
  `isShowGov` int(2) NOT NULL DEFAULT '0' COMMENT '工地是否在政务版显示，1 不显示其他显示 ',
  `bim_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '项目bim地址',
  `bim_proportion` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '项目bim放大比例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='工地参数配置表';

-- ----------------------------
-- Records of unit_project_config
-- ----------------------------
INSERT INTO `unit_project_config` VALUES ('105', '86221de342e0414a98046bcbbc7df735', '0', '0', null, null);

-- ----------------------------
-- Table structure for unit_worktype
-- ----------------------------
DROP TABLE IF EXISTS `unit_worktype`;
CREATE TABLE `unit_worktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工种id',
  `name` varchar(80) NOT NULL COMMENT '工种名称',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '数据状态 0 正常1 删除',
  `proUuid` varchar(32) DEFAULT NULL COMMENT '工地的uuid',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '0系统配置只能用不能改   1 各工地自己添加的自己随便动',
  `addTime` bigint(16) DEFAULT NULL COMMENT '添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`),
  KEY `proUuid` (`proUuid`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='工种表';

-- ----------------------------
-- Records of unit_worktype
-- ----------------------------
INSERT INTO `unit_worktype` VALUES ('115', '工种12', '0', '86221de342e0414a98046bcbbc7df735', '1', '1543047225640', '1543135036373');
INSERT INTO `unit_worktype` VALUES ('116', '工种2', '1', '86221de342e0414a98046bcbbc7df735', '1', '1543047237116', '1543048610072');
INSERT INTO `unit_worktype` VALUES ('117', '233', '1', '86221de342e0414a98046bcbbc7df735', '1', '1543053880890', '1543053909304');
INSERT INTO `unit_worktype` VALUES ('118', '2323', '0', '86221de342e0414a98046bcbbc7df735', '1', '1543135033170', '1543135033170');
INSERT INTO `unit_worktype` VALUES ('119', 'sdsdsds', '0', '86221de342e0414a98046bcbbc7df735', '1', '1543135338039', '1543135347456');
INSERT INTO `unit_worktype` VALUES ('120', 'sdsd', '0', '86221de342e0414a98046bcbbc7df735', '1', '1543135340479', '1543135340479');
INSERT INTO `unit_worktype` VALUES ('121', '23232', '0', '86221de342e0414a98046bcbbc7df735', '1', '1543287612680', '1543287612680');

-- ----------------------------
-- Table structure for user_identity
-- ----------------------------
DROP TABLE IF EXISTS `user_identity`;
CREATE TABLE `user_identity` (
  `id` int(21) NOT NULL AUTO_INCREMENT COMMENT '自增列，不作为唯一主键',
  `uuid` varchar(32) NOT NULL COMMENT '数据id    唯一表示',
  `userUuid` varchar(32) NOT NULL COMMENT '用户UUID',
  `type` int(2) NOT NULL COMMENT '身份类型 1工地 2 企业 3政务4劳务人员',
  `isDefault` int(1) DEFAULT '0' COMMENT '是否默认身份   默认身份每个类型只能有一个  1 默认      其他不是',
  `deptUuid` varchar(32) NOT NULL DEFAULT '' COMMENT '所属   工地',
  `roleUuid` varchar(32) DEFAULT NULL COMMENT '用户角色id',
  `isLogIng` int(1) DEFAULT NULL COMMENT '当前帐号是否可以登陆工地版0可以1不可以  默认超管 不是系统管理员',
  `singleSignon` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否单点登陆   0 否  1 是',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新时间',
  `passTime` bigint(16) DEFAULT NULL COMMENT '审核通过时间',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '用户身份状态 0正常 1删除 2待审核 3审核失败 4停用、5解除绑定',
  `department` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `post` varchar(32) DEFAULT NULL COMMENT '所属岗位',
  `groupUuid` varchar(32) DEFAULT NULL COMMENT '所属班组',
  `worktype` varchar(32) DEFAULT NULL COMMENT '当前工种',
  `isSuper` int(2) DEFAULT '0' COMMENT '是否是系统创建时候的默认系统超管  1是  0不是',
  `companyUuid` varchar(32) DEFAULT NULL COMMENT '用户所属企业',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uuid索引` (`uuid`) USING BTREE,
  KEY `userUuid索引` (`userUuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=717 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户身份表';

-- ----------------------------
-- Records of user_identity
-- ----------------------------
INSERT INTO `user_identity` VALUES ('715', 'c6f7f95d26944730965a59995426b12a', '28667ba2c75445f083fbc531d2b39073', '1', '1', '86221de342e0414a98046bcbbc7df735', null, '0', '0', '1541866419838', '1541866419838', '1541866419838', '0', '8851e9a232b94e41bbfb3d22e2859ecb', null, null, null, '1', null);
INSERT INTO `user_identity` VALUES ('716', '85fd8a0865b6472ab5ea7e8b138d2405', '215009e35f84446492256890d68589f5', '1', '1', '86221de342e0414a98046bcbbc7df735', '', '0', '0', '1542689428883', '1542689428883', '1542689428883', '0', null, null, null, null, '1', 'e1b32d4cd832448bb9ab24e19b2560b5');

-- ----------------------------
-- Table structure for user_jurisdiction
-- ----------------------------
DROP TABLE IF EXISTS `user_jurisdiction`;
CREATE TABLE `user_jurisdiction` (
  `proUuid` varchar(32) NOT NULL COMMENT '工地id  unit_project',
  `moduleUuid` varchar(32) NOT NULL COMMENT 'sys_module 模块表数据id',
  `roleUuid` varchar(32) NOT NULL COMMENT '角色uuid   sys_role   如果当前模块为工地权限那么没有这个字段保存工地uuid',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '0正常 1 删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户、工地、其他组织权限表';

-- ----------------------------
-- Records of user_jurisdiction
-- ----------------------------

-- ----------------------------
-- Table structure for user_loginaccount
-- ----------------------------
DROP TABLE IF EXISTS `user_loginaccount`;
CREATE TABLE `user_loginaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id   不作为主键',
  `uuid` varchar(32) NOT NULL COMMENT '数据id唯一标识',
  `idNumber` varchar(32) DEFAULT NULL COMMENT '用户身份证号，   全表唯一不重复',
  `userName` varchar(128) DEFAULT NULL COMMENT '人员登陆用户名   全表唯一不重复',
  `phone` varchar(16) NOT NULL COMMENT '手机号               全表唯一不重复',
  `name` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `addTime` bigint(16) DEFAULT NULL COMMENT '用户添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '更新个人信息时间',
  `password` varchar(32) NOT NULL COMMENT '用户密码             md5加密后的密码，不能明码',
  `photo` varchar(32) DEFAULT NULL COMMENT '用户头像文件名',
  `sex` int(1) DEFAULT NULL COMMENT '用户性别  0 女 1 男',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '用户状态 0正常1删除2停用',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uuidIndex` (`uuid`) USING BTREE COMMENT '主键索引'
) ENGINE=InnoDB AUTO_INCREMENT=490 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户信息表';

-- ----------------------------
-- Records of user_loginaccount
-- ----------------------------
INSERT INTO `user_loginaccount` VALUES ('488', '28667ba2c75445f083fbc531d2b39073', null, 'szx', '18518436862', '司仲夏', '1541864026578', '1541864026578', 'e122ba054d1c7cc9b22517345bdb8adc', null, '1', '0');
INSERT INTO `user_loginaccount` VALUES ('489', '215009e35f84446492256890d68589f5', null, 'szx1', '', null, '1542689428873', '1542689428873', 'd6a0893166fa62c3aad72da5b4bc5b20', null, '1', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id  排序',
  `uuid` varchar(32) NOT NULL COMMENT '角色id',
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  `roleCode` varchar(50) NOT NULL COMMENT '角色名称',
  `addTime` bigint(16) NOT NULL COMMENT '添加时间',
  `updateTime` bigint(16) DEFAULT NULL COMMENT '修改时间',
  `deptId` varchar(32) DEFAULT NULL COMMENT '角色所属工地、企业、政务',
  `state` int(1) DEFAULT '0' COMMENT '0 正常  1 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('168', '3830715c7bc343509917d16f24fe2f88', '工地超管', 'admin', '1541866574442', '1541866574442', '8851e9a232b94e41bbfb3d22e2859ecb', '0');

-- ----------------------------
-- Table structure for user_token_flag
-- ----------------------------
DROP TABLE IF EXISTS `user_token_flag`;
CREATE TABLE `user_token_flag` (
  `userUuid` varchar(32) NOT NULL COMMENT '用户ID',
  `flag_key` varchar(32) NOT NULL COMMENT 'token标识',
  `token` varchar(500) NOT NULL COMMENT 'Token',
  `create_time` bigint(16) NOT NULL COMMENT '创建时间',
  `expire_time` bigint(16) NOT NULL COMMENT '到期时间',
  PRIMARY KEY (`userUuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token标识记录';

-- ----------------------------
-- Records of user_token_flag
-- ----------------------------
INSERT INTO `user_token_flag` VALUES ('56eec8b4219f4fdb8cfb7569e6fc508b', 'e2ced1c2b7114cfbb2fb3eb6b6965b6b', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJmbGFnX2tleSI6ImUyY2VkMWMyYjcxMTRjZmJiMmZiM2ViNmI2OTY1YjZiIiwidW5pcXVlX25hbWUiOiJhZG1pbl9zengiLCJ1c2VyaWQiOiI1NmVlYzhiNDIxOWY0ZmRiOGNmYjc1NjllNmZjNTA4YiIsImlzcyI6InllZXRvbmciLCJhdWQiOiJrZXBxbWY3em05cWxvZXY4azVtcHNxY2VheXl5YmJndyIsImV4cCI6MTU0MzM5MTQ4MiwibmJmIjoxNTQzMzg0MjgyfQ.Sme6Ag9_fZtAgNq2heycdTAEIT3F6ky_IdP2MgvJ9m0', '1543384282693', '1543391482693');
