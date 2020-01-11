-- MySQL dump 10.13  Distrib 5.7.20, for macos10.12 (x86_64)
--
-- Host: 175.6.122.21    Database: jc_integration_db_bak
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
-- Table structure for table `cost_bucket`
--

DROP TABLE IF EXISTS `cost_bucket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost_bucket` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(24) DEFAULT NULL COMMENT '名称',
  `code` varchar(24) NOT NULL COMMENT '编号',
  `extra` varchar(24) DEFAULT NULL COMMENT '备用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='反应罐';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `cost_bucket`
--

LOCK TABLES `cost_bucket` WRITE;
/*!40000 ALTER TABLE `cost_bucket` DISABLE KEYS */;
/*!40000 ALTER TABLE `cost_bucket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cost_pipeline`
--

DROP TABLE IF EXISTS `cost_pipeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost_pipeline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `index` varchar(24) NOT NULL COMMENT '进料管道编号',
  `name` varchar(12) DEFAULT NULL COMMENT '名称',
  `bucket_id` int(11) DEFAULT NULL COMMENT '反应罐id',
  `color` varchar(12) DEFAULT NULL COMMENT '进料管道颜色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进料管道';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `cost_pipeline`
--

LOCK TABLES `cost_pipeline` WRITE;
/*!40000 ALTER TABLE `cost_pipeline` DISABLE KEYS */;
/*!40000 ALTER TABLE `cost_pipeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cost_pipeline_value`
--

DROP TABLE IF EXISTS `cost_pipeline_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost_pipeline_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `day_shift_value` varchar(24) DEFAULT NULL COMMENT '白班值',
  `night_shift_value` varchar(24) DEFAULT NULL COMMENT '晚班值',
  `pipeline_id` int(11) DEFAULT NULL COMMENT '管道id',
  `create_time` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `cost_pipeline_value`
--

LOCK TABLES `cost_pipeline_value` WRITE;
/*!40000 ALTER TABLE `cost_pipeline_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `cost_pipeline_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_archive_record`
--

DROP TABLE IF EXISTS `equipment_archive_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_archive_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(12) NOT NULL COMMENT '档案名称',
  `instrument_id` int(11) NOT NULL COMMENT '设备ID',
  `install_time` datetime NOT NULL COMMENT '安装时间',
  `warranty_period` varchar(6) NOT NULL COMMENT '保修期限',
  `supply_manufacturer_id` int(11) NOT NULL COMMENT '供货厂商ID',
  `repair_manufacturer_id` int(11) NOT NULL COMMENT '维修厂商ID',
  `manual_name` varchar(255) DEFAULT NULL COMMENT '手册名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `equipment_archive_record`
--

LOCK TABLES `equipment_archive_record` WRITE;
/*!40000 ALTER TABLE `equipment_archive_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_archive_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_base_instrument`
--

DROP TABLE IF EXISTS `equipment_base_instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_base_instrument` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(45) NOT NULL COMMENT '设备名称',
  `code` varchar(24) NOT NULL COMMENT '设备编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备仪器基础表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `equipment_base_instrument`
--

LOCK TABLES `equipment_base_instrument` WRITE;
/*!40000 ALTER TABLE `equipment_base_instrument` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_base_instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_base_manufacturer`
--

DROP TABLE IF EXISTS `equipment_base_manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_base_manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(36) NOT NULL COMMENT '厂商名称',
  `code` varchar(32) DEFAULT NULL COMMENT '厂商编号',
  `contact` varchar(15) NOT NULL COMMENT '厂商联系方式',
  `type` tinyint(4) NOT NULL COMMENT '厂商类型 \n\n维修厂商、供货厂商',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备维修、生产厂商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `equipment_base_manufacturer`
--

LOCK TABLES `equipment_base_manufacturer` WRITE;
/*!40000 ALTER TABLE `equipment_base_manufacturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_base_manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_check_point_record`
--

DROP TABLE IF EXISTS `equipment_check_point_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_check_point_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_content` varchar(24) NOT NULL COMMENT '每日检点内容',
  `check_standard` varchar(24) NOT NULL COMMENT '检查标准',
  `check_frequency` varchar(12) DEFAULT NULL COMMENT '检查频率',
  `check_point_pic_name` varchar(24) DEFAULT NULL COMMENT '检测点拍照',
  `instructor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `equipment_check_point_record`
--

LOCK TABLES `equipment_check_point_record` WRITE;
/*!40000 ALTER TABLE `equipment_check_point_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_check_point_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_instructor_record`
--

DROP TABLE IF EXISTS `equipment_instructor_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_instructor_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(45) NOT NULL COMMENT '指导书名称',
  `batch_number` int(11) NOT NULL COMMENT '批号 用于审核',
  `effective_date` datetime NOT NULL COMMENT '生效日期',
  `obsolete` tinyint(4) NOT NULL COMMENT '1 未过时，0 已过时',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备指导书';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `equipment_instructor_record`
--

LOCK TABLES `equipment_instructor_record` WRITE;
/*!40000 ALTER TABLE `equipment_instructor_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_instructor_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_repair_record`
--

DROP TABLE IF EXISTS `equipment_repair_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_repair_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` tinyint(4) NOT NULL COMMENT '工单状态',
  `instument_id` int(11) NOT NULL COMMENT '设备ID',
  `department_id` int(11) NOT NULL COMMENT '部门ID',
  `product_line_id` int(11) NOT NULL COMMENT '产品线ID',
  `repair_applier_id` int(11) NOT NULL COMMENT '维修申请人ID',
  `repair_apply_time` datetime DEFAULT NULL COMMENT '维修申请日期',
  `failure_description` varchar(128) DEFAULT NULL COMMENT '故障描述',
  `batch_number_id` int(11) NOT NULL COMMENT '批号ID\n用于提交维修申请流程',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备维修申请记录\n在APP端完成\n后端需要所有想到的接口';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `equipment_repair_record`
--

LOCK TABLES `equipment_repair_record` WRITE;
/*!40000 ALTER TABLE `equipment_repair_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_repair_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_base_delivery_factory`
--

DROP TABLE IF EXISTS `quality_base_delivery_factory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_base_delivery_factory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL COMMENT '送样工厂',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='送检工厂表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_base_delivery_factory`
--

LOCK TABLES `quality_base_delivery_factory` WRITE;
/*!40000 ALTER TABLE `quality_base_delivery_factory` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_base_delivery_factory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_base_product_line`
--

DROP TABLE IF EXISTS `quality_base_product_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_base_product_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL COMMENT '产品线名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品线表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_base_product_line`
--

LOCK TABLES `quality_base_product_line` WRITE;
/*!40000 ALTER TABLE `quality_base_product_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_base_product_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_base_production_process`
--

DROP TABLE IF EXISTS `quality_base_production_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_base_production_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工序名称',
  `name` varchar(12) NOT NULL COMMENT '工序名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工序表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_base_production_process`
--

LOCK TABLES `quality_base_production_process` WRITE;
/*!40000 ALTER TABLE `quality_base_production_process` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_base_production_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_base_test_item`
--

DROP TABLE IF EXISTS `quality_base_test_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_base_test_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '检测项目名',
  `unit` varchar(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检测项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_base_test_item`
--

LOCK TABLES `quality_base_test_item` WRITE;
/*!40000 ALTER TABLE `quality_base_test_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_base_test_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_common_batch_number`
--

DROP TABLE IF EXISTS `quality_common_batch_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_common_batch_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_number` varchar(32) NOT NULL,
  `description` varchar(12) DEFAULT NULL COMMENT '批号的描述',
  `create_person_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `data_type` tinyint(4) NOT NULL COMMENT '数据类型，后端用枚举类来维护。',
  `status` tinyint(4) DEFAULT NULL COMMENT '-1  已保存未提交\r\n\n0   已提交未未审核\r\n\n1   审核中\r\n\n2  审核通过\r\n\n3  审核未通过\n\r\n4  合格\r\n\n5  不合格\r\n 6 已发布',
  `is_urgent` tinyint(1) DEFAULT NULL COMMENT '是否紧急\n紧急 1\n非紧急 0',
  `memo` varchar(128) DEFAULT NULL COMMENT '如果对应的数据类型是制程检测\n那么会有这么一个comment',
  `is_published` tinyint(1) DEFAULT NULL COMMENT '是否已发布 0未发布1已发布',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='批号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_common_batch_number`
--

LOCK TABLES `quality_common_batch_number` WRITE;
/*!40000 ALTER TABLE `quality_common_batch_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_common_batch_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_data_task_record`
--

DROP TABLE IF EXISTS `quality_data_task_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_data_task_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_batch_number_id` int(11) NOT NULL COMMENT '数据对应批号id',
  `task_batch_number_id` int(11) NOT NULL COMMENT '流程对应的批号id',
  `create_time` datetime DEFAULT NULL COMMENT '提交流程时间\n',
  `create_person` int(11) NOT NULL COMMENT '提交流程人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_data_task_record`
--

LOCK TABLES `quality_data_task_record` WRITE;
/*!40000 ALTER TABLE `quality_data_task_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_data_task_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_procedure_test_item_record`
--

DROP TABLE IF EXISTS `quality_procedure_test_item_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_procedure_test_item_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_item_id` int(11) NOT NULL COMMENT '检测项目ID',
  `procedure_test_record_id` int(11) NOT NULL COMMENT '制程检测纪录id',
  `extra` varchar(2) DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_procedure_test_item_record`
--

LOCK TABLES `quality_procedure_test_item_record` WRITE;
/*!40000 ALTER TABLE `quality_procedure_test_item_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_procedure_test_item_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_procedure_test_record`
--

DROP TABLE IF EXISTS `quality_procedure_test_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_procedure_test_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_number_id` int(11) NOT NULL COMMENT '批号',
  `delivery_factory_id` int(11) NOT NULL COMMENT '送样工厂ID',
  `procedure_id` int(11) NOT NULL,
  `serial_number_id` int(11) NOT NULL COMMENT '序号ID',
  `sampler` int(11) NOT NULL COMMENT '采样人角色Id',
  `tester` int(11) NOT NULL COMMENT '测试人角色id',
  `sample_point_name` varchar(24) NOT NULL COMMENT '样品检测点',
  `test_frequency` varchar(12) NOT NULL COMMENT '测试频率',
  `comment` varchar(64) DEFAULT NULL COMMENT '备注',
  `isIteration` int(11) DEFAULT NULL COMMENT '是否为迭代数据即历史数据1是0否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='制程检测记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_procedure_test_record`
--

LOCK TABLES `quality_procedure_test_record` WRITE;
/*!40000 ALTER TABLE `quality_procedure_test_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_procedure_test_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_purchase_test_report_record`
--

DROP TABLE IF EXISTS `quality_purchase_test_report_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_purchase_test_report_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `batch_number_id` int(11) NOT NULL COMMENT '批号',
  `test_material_id` int(11) NOT NULL COMMENT '受检物料id',
  `manufacturer_id` int(11) NOT NULL COMMENT '生产厂家id',
  `quantity` varchar(45) DEFAULT NULL COMMENT '数量',
  `weight` varchar(45) DEFAULT NULL COMMENT '重量',
  `norm` varchar(45) DEFAULT NULL COMMENT '规格',
  `judgement` tinyint(4) DEFAULT NULL COMMENT '总判定',
  `judger` int(11) DEFAULT NULL COMMENT '判定人',
  `judge_date` datetime DEFAULT NULL COMMENT '判定日期',
  `dev_judgement` varchar(45) DEFAULT NULL COMMENT '研发部意见',
  `dev_judger` int(11) DEFAULT NULL COMMENT '研发部意见人',
  `dev_judge_date` datetime DEFAULT NULL COMMENT '研发部评议日期',
  `receive_date` datetime DEFAULT NULL COMMENT '到货日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='原料进货单记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_purchase_test_report_record`
--

LOCK TABLES `quality_purchase_test_report_record` WRITE;
/*!40000 ALTER TABLE `quality_purchase_test_report_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_purchase_test_report_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_sample_delivering_record`
--

DROP TABLE IF EXISTS `quality_sample_delivering_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_sample_delivering_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `sample_delivering_date` datetime NOT NULL COMMENT '样品送检时间',
  `deliverer_id` int(11) NOT NULL COMMENT '送检人',
  `delivery_factory_id` int(11) NOT NULL COMMENT '送样工厂ID',
  `serial_number_id` int(11) NOT NULL COMMENT '编号ID',
  `test_items` varchar(128) NOT NULL COMMENT '检测项目',
  `exception_comment` varchar(64) DEFAULT NULL COMMENT '异常备注',
  `handle_comment` varchar(64) DEFAULT NULL COMMENT '处理的操作，也就是不接受的原因。',
  `accept_status` tinyint(4) NOT NULL COMMENT '1 样品未接\n2 接受\n3 不接受',
  `type` tinyint(4) NOT NULL COMMENT '样品类型\n1 原材料\n2 中间件\n3 成品',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='样品送检记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_sample_delivering_record`
--

LOCK TABLES `quality_sample_delivering_record` WRITE;
/*!40000 ALTER TABLE `quality_sample_delivering_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_sample_delivering_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_task`
--

DROP TABLE IF EXISTS `quality_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_number_id` int(11) NOT NULL COMMENT '批号，1个批号对应多条任务。',
  `operator` int(11) NOT NULL COMMENT '操作人',
  `operation_description` varchar(64) NOT NULL COMMENT '操作明细，对应操作者的responsibility',
  `type` tinyint(4) DEFAULT NULL COMMENT '1. 标示为检测任务\n2. 标示为审核任务\n3. 标示为发布任务',
  `previous` int(11) DEFAULT NULL COMMENT '前一个任务节点，即前一个审核人的审核任务。',
  `next` int(11) DEFAULT NULL COMMENT '后一个审核人的审核任务。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_task`
--

LOCK TABLES `quality_task` WRITE;
/*!40000 ALTER TABLE `quality_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_task_handling_record`
--

DROP TABLE IF EXISTS `quality_task_handling_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_task_handling_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `handler` int(11) NOT NULL,
  `handle_time` datetime DEFAULT NULL,
  `handle_reply` varchar(128) DEFAULT NULL,
  `data_task_record_id` int(11) NOT NULL,
  `visible` tinyint(4) NOT NULL COMMENT '是否对当前操作人可见。0不可见，1可见。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_task_handling_record`
--

LOCK TABLES `quality_task_handling_record` WRITE;
/*!40000 ALTER TABLE `quality_task_handling_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_task_handling_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_test_item_result_record`
--

DROP TABLE IF EXISTS `quality_test_item_result_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_test_item_result_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_item_id` int(11) NOT NULL COMMENT '检测项目',
  `test_report_record_id` int(11) NOT NULL COMMENT '对应的 物料检测纪录id\n',
  `test_result` varchar(12) DEFAULT NULL COMMENT '检测结果',
  `is_valid` tinyint(4) DEFAULT NULL COMMENT '是否符合标准\n0 不符合标准\n1 符合标准',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_test_item_result_record`
--

LOCK TABLES `quality_test_item_result_record` WRITE;
/*!40000 ALTER TABLE `quality_test_item_result_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_test_item_result_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_test_report_record`
--

DROP TABLE IF EXISTS `quality_test_report_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_test_report_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `sample_delivering_record_id` int(11) NOT NULL COMMENT '样品送检纪录\n\n样品送检纪录中的编号可以确定受检样品，从而确定受检样品的标准。',
  `batch_number_id` int(11) DEFAULT NULL COMMENT '批号 用于审核',
  `purchase_report_record_id` int(11) DEFAULT NULL COMMENT '用来看看是否已经用来生成进货检验报告单。',
  `judger` int(11) DEFAULT NULL COMMENT '检测人',
  `judge_date` datetime DEFAULT NULL,
  `is_qualified` tinyint(4) DEFAULT NULL COMMENT '0 不合格\n1 合格',
  `decision` int(11) DEFAULT NULL COMMENT '进货检验报告单中单条纪录和标准对比后的判定结果。\n其他时候为NULL',
  `quality_level` int(11) DEFAULT NULL COMMENT '择优等级\n优等品/普通品/不合格品',
  `rate_person_id` int(11) DEFAULT NULL COMMENT '择优人id',
  `rate_date` datetime DEFAULT NULL COMMENT '择优日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='原材料、中间品检测记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_test_report_record`
--

LOCK TABLES `quality_test_report_record` WRITE;
/*!40000 ALTER TABLE `quality_test_report_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_test_report_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_unqualified_test_item_result_record`
--

DROP TABLE IF EXISTS `quality_unqualified_test_item_result_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_unqualified_test_item_result_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_item_id` int(11) NOT NULL COMMENT '检测项目',
  `unqualified_test_report_record_id` int(11) NOT NULL COMMENT '不合格纪录ID',
  `test_result` varchar(12) NOT NULL COMMENT '检测结果',
  `is_valid` int(11) NOT NULL COMMENT '是否符合标准',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_unqualified_test_item_result_record`
--

LOCK TABLES `quality_unqualified_test_item_result_record` WRITE;
/*!40000 ALTER TABLE `quality_unqualified_test_item_result_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_unqualified_test_item_result_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_unqualified_test_report_record`
--

DROP TABLE IF EXISTS `quality_unqualified_test_report_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_unqualified_test_report_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `sample_delivering_record_id` int(11) DEFAULT NULL COMMENT '样品送检纪录\n\n样品送检纪录中的编号可以确定受检样品，从而确定受检样品的标准。',
  `batch_number_id` int(11) NOT NULL,
  `purchase_report_record_id` int(11) DEFAULT NULL COMMENT '用来看看是否已经用来生成进货检验报告单。',
  `judger` int(11) DEFAULT NULL COMMENT '检测人',
  `judge_date` datetime DEFAULT NULL,
  `is_qualified` tinyint(4) DEFAULT NULL COMMENT '0 不合格\n1 合格',
  `status` tinyint(4) DEFAULT NULL COMMENT '1 未提交\n2 待审核\n3 审核通过\n4 审核未通过\n注： 提交审核流程',
  `quality_level` int(11) DEFAULT NULL COMMENT '择优等级\n优等品/普通品/不合格品',
  `decision` int(11) DEFAULT NULL COMMENT '进货检验报告单中单条纪录和标准对比后的判定结果。\n其他时候为NULL',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='不合格审评表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_unqualified_test_report_record`
--

LOCK TABLES `quality_unqualified_test_report_record` WRITE;
/*!40000 ALTER TABLE `quality_unqualified_test_report_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_unqualified_test_report_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quality_unqualified_tracing_record`
--

DROP TABLE IF EXISTS `quality_unqualified_tracing_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality_unqualified_tracing_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_number_id` int(11) NOT NULL COMMENT '批号',
  `create_time` datetime DEFAULT NULL COMMENT '发生时间',
  `production_process_id` int(11) DEFAULT NULL COMMENT '发生工序',
  `handler` int(11) DEFAULT NULL COMMENT '处理人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='不合格品跟踪表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quality_unqualified_tracing_record`
--

LOCK TABLES `quality_unqualified_tracing_record` WRITE;
/*!40000 ALTER TABLE `quality_unqualified_tracing_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quality_unqualified_tracing_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quanlity_unqualified_tracing_test_record`
--

DROP TABLE IF EXISTS `quanlity_unqualified_tracing_test_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quanlity_unqualified_tracing_test_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unqualified_test_report_record_id` int(11) DEFAULT NULL COMMENT '不合格评审表id',
  `unqualified_tracing_record_id` int(11) DEFAULT NULL COMMENT '不合格追踪表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `quanlity_unqualified_tracing_test_record`
--

LOCK TABLES `quanlity_unqualified_tracing_test_record` WRITE;
/*!40000 ALTER TABLE `quanlity_unqualified_tracing_test_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `quanlity_unqualified_tracing_test_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repo_base_serial_number`
--

DROP TABLE IF EXISTS `repo_base_serial_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repo_base_serial_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(45) NOT NULL COMMENT '编号	',
  `material_name` varchar(24) NOT NULL COMMENT '物料名称',
  `material_class` int(11) NOT NULL COMMENT '物料类型\n1  原材料\n2  中间品\n3  成品',
  `manufacturer_name` varchar(24) NOT NULL COMMENT '生产厂家',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序号，deprecated';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `repo_base_serial_number`
--

LOCK TABLES `repo_base_serial_number` WRITE;
/*!40000 ALTER TABLE `repo_base_serial_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `repo_base_serial_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repo_diff_record`
--

DROP TABLE IF EXISTS `repo_diff_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repo_diff_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number_id` int(11) NOT NULL COMMENT '批号',
  `real_quantity` int(11) DEFAULT NULL COMMENT '实际数量',
  `supposed_quantity` int(11) DEFAULT NULL COMMENT '理论数量',
  `creator` int(11) NOT NULL COMMENT '制表人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `real_weight` int(11) NOT NULL,
  `supposed_weight` int(11) NOT NULL,
  `quantity_diff` int(11) DEFAULT NULL,
  `weight_diff` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `repo_diff_record`
--

LOCK TABLES `repo_diff_record` WRITE;
/*!40000 ALTER TABLE `repo_diff_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `repo_diff_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repo_in_record`
--

DROP TABLE IF EXISTS `repo_in_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repo_in_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number_id` int(11) NOT NULL COMMENT '批号',
  `weight` int(11) NOT NULL COMMENT '重量',
  `create_time` datetime NOT NULL COMMENT '记录插入时间',
  `create_person` varchar(12) NOT NULL COMMENT '入库申请人',
  `in_time` datetime NOT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库纪录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `repo_in_record`
--

LOCK TABLES `repo_in_record` WRITE;
/*!40000 ALTER TABLE `repo_in_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `repo_in_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repo_out_apply`
--

DROP TABLE IF EXISTS `repo_out_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repo_out_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_number_id` int(11) NOT NULL COMMENT '批号。\n用于提交审核。',
  `serial_number_id` int(11) NOT NULL COMMENT '批号',
  `quantity` int(11) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `out_time` datetime DEFAULT NULL COMMENT '出库时间',
  `out_already` tinyint(4) DEFAULT NULL COMMENT '0 未出库\n1 已出库',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `repo_out_apply`
--

LOCK TABLES `repo_out_apply` WRITE;
/*!40000 ALTER TABLE `repo_out_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `repo_out_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repo_red_table`
--

DROP TABLE IF EXISTS `repo_red_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repo_red_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_number_id` int(11) NOT NULL COMMENT '批号，用于审核',
  `serial_number_id` int(11) NOT NULL,
  `quantity_loss` int(11) DEFAULT NULL COMMENT '意外损失',
  `note` varchar(45) DEFAULT NULL COMMENT '意外损失文字说明',
  `weight_loss` int(11) NOT NULL COMMENT '重量损失',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红单填写';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `repo_red_table`
--

LOCK TABLES `repo_red_table` WRITE;
/*!40000 ALTER TABLE `repo_red_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `repo_red_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repo_stock`
--

DROP TABLE IF EXISTS `repo_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repo_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_type` int(11) NOT NULL COMMENT '物料类类型 deprecated\n',
  `serial_number_id` int(11) NOT NULL COMMENT '批号',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `weight` int(11) NOT NULL COMMENT '重量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `repo_stock`
--

LOCK TABLES `repo_stock` WRITE;
/*!40000 ALTER TABLE `repo_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `repo_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_base_product_class`
--

DROP TABLE IF EXISTS `technique_base_product_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_base_product_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL COMMENT '型号名称',
  `parent` int(11) DEFAULT NULL COMMENT '父型号ID',
  `is_leaf` int(11) NOT NULL COMMENT '是否叶子结点，如果是，就可以用该型号的标准',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品型号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_base_product_class`
--

LOCK TABLES `technique_base_product_class` WRITE;
/*!40000 ALTER TABLE `technique_base_product_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_base_product_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_base_raw_manufacturer`
--

DROP TABLE IF EXISTS `technique_base_raw_manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_base_raw_manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL COMMENT '原材老生产厂商',
  `code` varchar(255) DEFAULT NULL COMMENT '缩写',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_base_raw_manufacturer`
--

LOCK TABLES `technique_base_raw_manufacturer` WRITE;
/*!40000 ALTER TABLE `technique_base_raw_manufacturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_base_raw_manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_base_raw_material`
--

DROP TABLE IF EXISTS `technique_base_raw_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_base_raw_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL COMMENT '物料名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='原材料';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_base_raw_material`
--

LOCK TABLES `technique_base_raw_material` WRITE;
/*!40000 ALTER TABLE `technique_base_raw_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_base_raw_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_product_standard_record`
--

DROP TABLE IF EXISTS `technique_product_standard_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_product_standard_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_class_id` int(11) NOT NULL COMMENT '最小型号的ID',
  `batch_number_id` int(11) DEFAULT NULL,
  `effective_time` datetime DEFAULT NULL COMMENT '生效时间',
  `serial_number_id` int(11) DEFAULT NULL COMMENT '对应基本编号中的成品',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_product_standard_record`
--

LOCK TABLES `technique_product_standard_record` WRITE;
/*!40000 ALTER TABLE `technique_product_standard_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_product_standard_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_product_test_item_standard`
--

DROP TABLE IF EXISTS `technique_product_test_item_standard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_product_test_item_standard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_item_id` int(11) NOT NULL COMMENT '检测项目ID',
  `value` varchar(24) NOT NULL COMMENT '标准值',
  `product_standard_id` int(11) NOT NULL COMMENT '产品型号ID,最小型号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_product_test_item_standard`
--

LOCK TABLES `technique_product_test_item_standard` WRITE;
/*!40000 ALTER TABLE `technique_product_test_item_standard` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_product_test_item_standard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_raw_item_record`
--

DROP TABLE IF EXISTS `technique_raw_item_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_raw_item_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raw_material_id` int(11) NOT NULL COMMENT '原材料id',
  `test_item_id` int(11) NOT NULL COMMENT '检测项目id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_raw_item_record`
--

LOCK TABLES `technique_raw_item_record` WRITE;
/*!40000 ALTER TABLE `technique_raw_item_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_raw_item_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_raw_standard_record`
--

DROP TABLE IF EXISTS `technique_raw_standard_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_raw_standard_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raw_material_id` int(11) NOT NULL COMMENT '原材料ID',
  `raw_manufacturer_id` int(11) NOT NULL COMMENT '生产厂家ID',
  `batch_number_id` int(11) NOT NULL COMMENT '批号，用户审核。',
  `effective_time` datetime DEFAULT NULL COMMENT '生效时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='原材料技术标准';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_raw_standard_record`
--

LOCK TABLES `technique_raw_standard_record` WRITE;
/*!40000 ALTER TABLE `technique_raw_standard_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_raw_standard_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technique_raw_test_item_standard`
--

DROP TABLE IF EXISTS `technique_raw_test_item_standard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technique_raw_test_item_standard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_item_id` int(11) NOT NULL COMMENT '检测项目ID',
  `value` varchar(24) NOT NULL COMMENT '标准值',
  `raw_standard_record_id` int(11) NOT NULL COMMENT '原材料标准ID\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping list for table `technique_raw_test_item_standard`
--

LOCK TABLES `technique_raw_test_item_standard` WRITE;
/*!40000 ALTER TABLE `technique_raw_test_item_standard` DISABLE KEYS */;
/*!40000 ALTER TABLE `technique_raw_test_item_standard` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-02 14:04:47
