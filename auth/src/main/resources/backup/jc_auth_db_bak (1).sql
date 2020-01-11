-- MySQL dump 10.13  Distrib 5.7.20, for macos10.12 (x86_64)
--
-- Host: 175.6.122.21    Database: jc_auth_db_bak
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_department`
--

DROP TABLE IF EXISTS `auth_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `department_name` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '部门名称',
  `extra_info` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '额外信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='部门信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `auth_department`
--

LOCK TABLES `auth_department` WRITE;
/*!40000 ALTER TABLE `auth_department` DISABLE KEYS */;
INSERT INTO `auth_department` VALUES (1,'综合部','行政中心'),(31,'技术一部','技术中心'),(44,'财务部','财务部'),(96,'分析室','质量中心'),(97,'研发部','研发部'),(106,'质量部','质量中心'),(107,'制造三部','制造中心制造三部'),(108,'制造二部','制造中心制造二部'),(116,'总经理','公司总经理'),(117,'副总经理','公司副总经理');
/*!40000 ALTER TABLE `auth_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_menu`
--

DROP TABLE IF EXISTS `auth_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '菜单名',
  `menu_type` tinyint(4) NOT NULL COMMENT '菜单类型(1:一级菜单，2:二级餐单，3:三级菜单)',
  `parent` int(11) DEFAULT NULL COMMENT '所属父类菜单，如果没有父类菜单，此处为-1',
  `prefix` varchar(24) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单简称',
  `path` varchar(24) CHARACTER SET utf8 DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COMMENT='菜单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `auth_menu`
--

LOCK TABLES `auth_menu` WRITE;
/*!40000 ALTER TABLE `auth_menu` DISABLE KEYS */;
INSERT INTO `auth_menu` VALUES (1,'操作管理',2,4,'AUTH_AUTH','/operationManagement'),(2,'菜单管理',2,4,'AUTH_MENU','/menu'),(3,'角色管理',2,4,'AUTH_ROLE','/role'),(4,'用户和权限',1,-1,'AUTH',NULL),(5,'用户管理',2,4,'AUTH_USER','/user'),(6,'部门管理',2,4,'AUTH_DEPARTMENT','/departManagement'),(7,'质量与流程',1,-1,'PROCESS',NULL),(8,'数据录入',2,7,'PROCESS_DATA_IN','/dataEntry'),(9,'流程管理',2,7,'PROCESS_PROCESS','/management'),(10,'待办事项',2,7,'PROCESS_TASK','/todoList'),(11,'基础数据',2,7,'PROCESS_BASE_DATA','/baseInfo'),(73,'智能仓库',1,-1,'STORE',NULL),(74,'入库管理',2,73,'STORE_IN','/enterStorage'),(75,'库存管理',2,73,'STORE_STOCK','/inventorManage'),(76,'出库管理',2,73,'STORE_OUT','/stockOut'),(77,'盘库管理',2,73,'STORE_CHECK','/libraryManage'),(78,'红单管理',2,73,'STORE_RED_LISTS','/redListManage'),(93,'技术中心',1,-1,'default',NULL),(94,'原材料标准',2,93,'default','/rawStandard'),(102,'成本核算',1,-1,'default',NULL),(103,'单耗统计',2,102,'default',NULL),(107,'批次管理',1,-1,'default',NULL),(108,'大数据',1,-1,'default',NULL),(109,'成品编号',2,107,'default',NULL),(111,'生产制造',1,-1,'default',NULL),(112,'溶液配置',2,111,'default',NULL),(113,'合成反应',2,111,'default',NULL),(114,'洗涤压滤',2,111,'default',NULL),(115,'烘干工序',2,111,'default',NULL),(116,'包装工序',2,111,'default',NULL),(117,'成品标准',2,93,'default','/productStandard'),(118,'制程编号',2,107,'default',NULL),(119,'生产日报',2,102,'default',NULL),(120,'车间盘存',2,102,'default',NULL),(121,'成本分析',2,102,'default',NULL),(122,'产品标准',2,93,'default','/productStandard'),(137,'设备管理',1,-1,'default',NULL),(138,'设备档案',2,137,'default','/equipmentArchive'),(139,'设备指导',2,137,'default','/equipmentGuidance'),(140,'设备维修',2,137,'default','/equipmentRepair'),(141,'测试菜单1',1,-1,'default','string'),(142,'制程检测',3,8,'default','string');
/*!40000 ALTER TABLE `auth_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_operation`
--

DROP TABLE IF EXISTS `auth_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(12) CHARACTER SET utf8 NOT NULL COMMENT '请求路径',
  `operation_code` varchar(12) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='权限列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `auth_operation`
--

LOCK TABLES `auth_operation` WRITE;
/*!40000 ALTER TABLE `auth_operation` DISABLE KEYS */;
INSERT INTO `auth_operation` VALUES (1,'删除','DELETE'),(2,'修改','UPDATE'),(3,'新增','SAVE'),(4,'查询','QUERY'),(5,'审核','AUDIT'),(6,'打印','PRINT'),(7,'上传','UPLOAD'),(8,'下载','DOWNLOAD');
/*!40000 ALTER TABLE `auth_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_role`
--

DROP TABLE IF EXISTS `auth_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `role_name` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `auth_role`
--

LOCK TABLES `auth_role` WRITE;
/*!40000 ALTER TABLE `auth_role` DISABLE KEYS */;
INSERT INTO `auth_role` VALUES (1,'ROLE_ADMIN','超级管理员'),(86,'ROLE_TESTER','检测人员'),(87,'ROLE_SAMPLER','采样人员'),(88,'ROLE_IQC','进料质量控制人员');
/*!40000 ALTER TABLE `auth_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_role_menu_operation`
--

DROP TABLE IF EXISTS `auth_role_menu_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_role_menu_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `operation_id` int(11) NOT NULL COMMENT '后端接口id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户在三级菜单下的操作权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `auth_role_menu_operation`
--

LOCK TABLES `auth_role_menu_operation` WRITE;
/*!40000 ALTER TABLE `auth_role_menu_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_role_menu_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) DEFAULT NULL,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '登录密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机',
  `last_password_modified` datetime DEFAULT NULL COMMENT '最后一次修改密码的时间',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户的真实姓名',
  `id_card_number` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,1,'admin','$2a$10$4hkuL17Aq.oii8gV4ZvYjO0Yb4f98/sXGOnmsZYCfcDwquwfXMYIC','18888888888','2018-08-08 00:00:00','管理员',NULL),(2,1,'quanyuanquan','$2a$10$kksjiybWcsCSxtWscrx6p.IVrPugg/I9aksYl0tPWyFu0/RA3exDW','15355446655',NULL,'匡远泉',NULL),(3,31,'liming','$2a$10$T2fBS9338xuOCdFJ965lnedQDY7PWm4bS8FFM.6u2wQ47ic2Ysd02','15566667777',NULL,'李敏',NULL),(4,107,'longzhihui','$2a$10$NeBaf54Dnz/KFOch00dYEevNwFRCMvI7wts3XjAENYIfzap/Jr3hK','17677888877',NULL,'龙志辉',NULL),(5,31,'liupeng','$2a$10$mUsLId1vMpIQPpM4rT6SZe/0utLCzEPo0EiNl28dQ5oC3xEnRysXO','15566667777',NULL,'刘鹏',NULL),(6,1,'daisongqiang','$2a$10$csIeZ4HNT3Hru1XNhORTfODeycwsSizvRdWqUBD0iUJSjGoRgWFaS','14666666666',NULL,'戴松强',NULL),(7,97,'lanyage','$2a$10$4hkuL17Aq.oii8gV4ZvYjO0Yb4f98/sXGOnmsZYCfcDwquwfXMYIC','13898989898',NULL,'兰亚戈',NULL),(8,97,'wuyanzu','$2a$10$alHsSvoiMdvZXuoROEMUqeu/C091LuzykHdEzNaYGokeg4GH9QOH.','15435435487',NULL,'胡旭东',NULL),(9,97,'yangmei','$2a$10$Cgx6a8AADS.w6lUskZAzieTGvSfeqTKnoLS1Btdmi61Hfg6jp9wrG','13333333335',NULL,'杨梅',NULL),(10,97,'fangle','$2a$10$lweyHyvpAAncWdnRcksfNuJbc/yafFSNoxgW0JwvoCREEvxF2pM66','15555555555',NULL,'方乐',NULL),(11,97,'chenken','$2a$10$F1Np9Fw7xWAc.3CMZ7YBN.74tcp/oJ2TUnmGyk6yViguD660vNshy','13222222222',NULL,'陈肯',NULL),(12,116,'huliuquan','$2a$10$jWTdAhLVkZkXaaDQuzMmvOKKGA8enFvCDsyBKKvTIbjB7oHytTYQ2','18670062066',NULL,'胡柳泉',NULL),(13,117,'zhangjingjing','$2a$10$Zl70Ulxy4Uvj9xd1c25aZu8rwC1gwgvQn89VWQoP0njJj1Ta0tSK6','18670062066',NULL,'张谨谨',NULL),(14,117,'zhangzheng','$2a$10$RvI9cDYwhJ/J9PEhKFaSauPOWw0oA7PBuT0X236bgXZeMksBzSX2m','18670062066',NULL,'张臻',NULL),(15,117,'huzexing','$2a$10$84G0FZCpm/x8/meFyTs8G.y1TDT3OX3A5.slD92Xg9xguCx7lzHCO','18670062066',NULL,'胡泽星',NULL),(16,117,'hemin','$2a$10$hOtW6rBi8lwUraXsDezvau3vYSaqFmCnxz7KvoriWJWSLCityBar.','13456789009',NULL,'何敏',NULL);
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_role`
--

DROP TABLE IF EXISTS `auth_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户具有的角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `auth_user_role`
--

LOCK TABLES `auth_user_role` WRITE;
/*!40000 ALTER TABLE `auth_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-02 14:47:09
