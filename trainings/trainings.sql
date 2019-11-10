CREATE DATABASE  IF NOT EXISTS `trainings` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trainings`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: trainings
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2019-11-10 04:41:19',1,'EXECUTED','8:9bf75aca02e1c4157cc1268a96e34b65','createTable tableName=jhi_persistent_audit_event; createTable tableName=jhi_persistent_audit_evt_data; addPrimaryKey tableName=jhi_persistent_audit_evt_data; createIndex indexName=idx_persistent_audit_event, tableName=jhi_persistent_audit_event; c...','',NULL,'3.6.3',NULL,NULL,'3360878934'),('20191110050030-1','jhipster','config/liquibase/changelog/20191110050030_added_entity_Mentor.xml','2019-11-10 05:04:09',2,'EXECUTED','8:905ba30440c192e2fa0a75e90db42c9a','createTable tableName=mentor; dropDefaultValue columnName=reg_datetime, tableName=mentor','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050030-1-relations','jhipster','config/liquibase/changelog/20191110050030_added_entity_Mentor.xml','2019-11-10 05:04:09',3,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050030-1-data','jhipster','config/liquibase/changelog/20191110050030_added_entity_Mentor.xml','2019-11-10 05:04:09',4,'EXECUTED','8:edd5bc8b7088cb6407a94e149ce12f54','loadData tableName=mentor','',NULL,'3.6.3','faker',NULL,'3362248847'),('20191110050031-1','jhipster','config/liquibase/changelog/20191110050031_added_entity_MentorSkill.xml','2019-11-10 05:04:09',5,'EXECUTED','8:55d2a45e2ae6c37f232d84c18b57aa71','createTable tableName=mentor_skill','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050031-1-relations','jhipster','config/liquibase/changelog/20191110050031_added_entity_MentorSkill.xml','2019-11-10 05:04:09',6,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050031-1-data','jhipster','config/liquibase/changelog/20191110050031_added_entity_MentorSkill.xml','2019-11-10 05:04:09',7,'EXECUTED','8:5d37ffa4200b97299c29d3bcb4a63c62','loadData tableName=mentor_skill','',NULL,'3.6.3','faker',NULL,'3362248847'),('20191110050032-1','jhipster','config/liquibase/changelog/20191110050032_added_entity_MyCalendar.xml','2019-11-10 05:04:09',8,'EXECUTED','8:d1719bb2233f45a75bdadd908b423b29','createTable tableName=my_calendar; dropDefaultValue columnName=cal_date, tableName=my_calendar','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050032-1-relations','jhipster','config/liquibase/changelog/20191110050032_added_entity_MyCalendar.xml','2019-11-10 05:04:10',9,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050032-1-data','jhipster','config/liquibase/changelog/20191110050032_added_entity_MyCalendar.xml','2019-11-10 05:04:10',10,'EXECUTED','8:5933401740bd593c90bd8993fb96498a','loadData tableName=my_calendar','',NULL,'3.6.3','faker',NULL,'3362248847'),('20191110050033-1','jhipster','config/liquibase/changelog/20191110050033_added_entity_Training.xml','2019-11-10 05:04:10',11,'EXECUTED','8:85c98b4940e9146865a93f35ff6aee74','createTable tableName=training; dropDefaultValue columnName=start_date, tableName=training; dropDefaultValue columnName=end_date, tableName=training','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050033-1-relations','jhipster','config/liquibase/changelog/20191110050033_added_entity_Training.xml','2019-11-10 05:04:10',12,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050033-1-data','jhipster','config/liquibase/changelog/20191110050033_added_entity_Training.xml','2019-11-10 05:04:10',13,'EXECUTED','8:06906495cd61b5e34228416aa18ec9c8','loadData tableName=training','',NULL,'3.6.3','faker',NULL,'3362248847'),('20191110050034-1','jhipster','config/liquibase/changelog/20191110050034_added_entity_TrainingRecord.xml','2019-11-10 05:04:10',14,'EXECUTED','8:6f155917bc30715db93fa57f88b2620f','createTable tableName=training_record','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050034-1-relations','jhipster','config/liquibase/changelog/20191110050034_added_entity_TrainingRecord.xml','2019-11-10 05:04:10',15,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050034-1-data','jhipster','config/liquibase/changelog/20191110050034_added_entity_TrainingRecord.xml','2019-11-10 05:04:10',16,'EXECUTED','8:d343ac3c279369e386ee44c11424706b','loadData tableName=training_record','',NULL,'3.6.3','faker',NULL,'3362248847'),('20191110050031-2','jhipster','config/liquibase/changelog/20191110050031_added_entity_constraints_MentorSkill.xml','2019-11-10 05:04:11',17,'EXECUTED','8:61ebb52f5a0868d77de27522505c3810','addForeignKeyConstraint baseTableName=mentor_skill, constraintName=fk_mentor_skill_mentor_id, referencedTableName=mentor','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050032-2','jhipster','config/liquibase/changelog/20191110050032_added_entity_constraints_MyCalendar.xml','2019-11-10 05:04:11',18,'EXECUTED','8:ac5e7289ff0549fdf11191e2bf71e46a','addForeignKeyConstraint baseTableName=my_calendar, constraintName=fk_my_calendar_training_id, referencedTableName=training','',NULL,'3.6.3',NULL,NULL,'3362248847'),('20191110050034-2','jhipster','config/liquibase/changelog/20191110050034_added_entity_constraints_TrainingRecord.xml','2019-11-10 05:04:11',19,'EXECUTED','8:5518a3be8e4d073d56f422a46234e010','addForeignKeyConstraint baseTableName=training_record, constraintName=fk_training_record_training_id, referencedTableName=training','',NULL,'3.6.3',NULL,NULL,'3362248847');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mentor`
--

LOCK TABLES `mentor` WRITE;
/*!40000 ALTER TABLE `mentor` DISABLE KEYS */;
INSERT INTO `mentor` VALUES (1,'mobile Fish','Home Loan Account Table Computer','2019-11-09 19:36:59','FTP Savings Account',70807,'Inactive'),(2,'Implementation Electronics Officer','methodology Albania','2019-11-09 10:38:37','intangible',61185,'Inactive'),(3,'Multi-channelled Licensed','grey Cambridgeshire','2019-11-09 06:47:33','Refined Rubber Chicken Table',6505,'Active'),(4,'South Dakota envisioneer Music','Nakfa','2019-11-09 10:26:48','Rubber Buckinghamshire Games',90932,'Inactive'),(5,'convergence','Mayotte','2019-11-09 16:34:26','Facilitator',77513,'Inactive'),(6,'capacitor archive','sky blue Tasty Plastic Pizza','2019-11-09 11:40:56','interactive generating compelling',28093,'Inactive'),(7,'Car Industrial deliverables','orange Books','2019-11-09 01:26:22','monitor',72900,'Inactive'),(8,'generating Highway Ergonomic','moratorium moratorium','2019-11-09 03:30:00','withdrawal COM',47221,'Inactive'),(9,'hard drive Robust Networked','Poland','2019-11-09 10:44:58','Grocery',10789,'Inactive'),(10,'Harbor Montana','Corporate Phased relationships','2019-11-09 02:52:38','User-centric',7697,'Inactive');
/*!40000 ALTER TABLE `mentor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mentor_skill`
--

LOCK TABLES `mentor_skill` WRITE;
/*!40000 ALTER TABLE `mentor_skill` DISABLE KEYS */;
INSERT INTO `mentor_skill` VALUES (1,'Auto Loan Account modular Refined',45653,NULL),(2,'mint green e-business EXE',32434,NULL),(3,'Fresh granular Direct',27213,NULL),(4,'withdrawal',52,NULL),(5,'cutting-edge ability Hat',53408,NULL),(6,'Bedfordshire Bahamian Dollar',26941,NULL),(7,'User-centric Gloves',21876,NULL),(8,'Oklahoma facilitate',6489,NULL),(9,'Versatile Exclusive Moroccan Dirham',71227,NULL),(10,'Plastic',99774,NULL);
/*!40000 ALTER TABLE `mentor_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `my_calendar`
--

LOCK TABLES `my_calendar` WRITE;
/*!40000 ALTER TABLE `my_calendar` DISABLE KEYS */;
INSERT INTO `my_calendar` VALUES (1,'2019-11-09 20:07:32','Afternoon1600','paradigms Home',NULL),(2,'2019-11-09 12:43:45','Morning0800','Designer Persistent',NULL),(3,'2019-11-09 04:52:47','Morning1000','Re-contextualized',NULL),(4,'2019-11-09 01:44:08','Afternoon1400','Solomon Islands Licensed',NULL),(5,'2019-11-09 00:57:49','Morning1000','Argentine Peso',NULL),(6,'2019-11-09 00:24:37','Afternoon1600','Buckinghamshire Future-proofed deposit',NULL),(7,'2019-11-09 16:49:34','Afternoon1400','Forint',NULL),(8,'2019-11-09 03:21:57','Afternoon1600','SMS Enhanced',NULL),(9,'2019-11-09 05:10:17','Morning1000','Borders content-based',NULL),(10,'2019-11-08 21:06:03','Afternoon1400','Human deposit',NULL);
/*!40000 ALTER TABLE `my_calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `training`
--

LOCK TABLES `training` WRITE;
/*!40000 ALTER TABLE `training` DISABLE KEYS */;
INSERT INTO `training` VALUES (1,'Active','Internal AI optical',64203,85092,'2019-11-09 18:59:29','2019-11-08 22:33:21','solid state'),(2,'Inactive','Supervisor clear-thinking',30536,54873,'2019-11-09 09:21:22','2019-11-09 04:24:20','invoice Devolved'),(3,'Active','payment calculating',65761,30806,'2019-11-09 07:22:17','2019-11-09 07:59:24','Berkshire'),(4,'Active','Coordinator Cambridgeshire Investor',43439,79481,'2019-11-09 12:36:32','2019-11-09 08:56:51','North Dakota Cambridgeshire'),(5,'Active','local Shoes',64547,4360,'2019-11-09 16:45:37','2019-11-08 21:08:31','Villages uniform PCI'),(6,'Active','Wisconsin Facilitator Incredible',30569,38509,'2019-11-09 16:25:44','2019-11-09 00:35:01','infrastructure'),(7,'Active','Configuration UIC-Franc',57006,7041,'2019-11-09 18:40:43','2019-11-09 15:59:48','Zambian Kwacha intuitive'),(8,'Active','portal calculating Metal',70891,35949,'2019-11-09 07:44:36','2019-11-09 13:57:53','redundant'),(9,'Inactive','Borders',79829,19427,'2019-11-09 05:24:42','2019-11-09 19:18:30','extend Unbranded Fresh Pants Tools'),(10,'Inactive','implementation',15504,54264,'2019-11-08 21:26:49','2019-11-09 14:08:02','Utah Computers Small Fresh Gloves');
/*!40000 ALTER TABLE `training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `training_record`
--

LOCK TABLES `training_record` WRITE;
/*!40000 ALTER TABLE `training_record` DISABLE KEYS */;
INSERT INTO `training_record` VALUES (1,'Completed','Two',8534,84202,5168,'Iran',54063,'deposit panel Strategist',NULL),(2,'Progress','Four',2535,18433,96264,'navigating',69597,'paradigms Salad Table',NULL),(3,'Propose','One',66019,93461,27993,'Operations alarm',55435,'Credit Card Account paradigms',NULL),(4,'Progress','Two',1439,34187,11607,'Ergonomic Steel Gloves',85546,'Colombian Peso Unidad de Valor Real Bridge Handmade',NULL),(5,'Progress','Three',61585,94914,63509,'Gorgeous Cotton Chair Personal Loan Account Borders',85648,'payment Soap',NULL),(6,'Progress','Two',57547,67716,38816,'deposit Station',54561,'Web revolutionize Branding',NULL),(7,'Completed','Four',68300,33655,7118,'functionalities quantifying',2327,'open-source hybrid',NULL),(8,'Completed','Three',73507,41794,80348,'intermediate Table quantify',80683,'Producer infrastructures',NULL),(9,'Progress','One',75087,73990,75454,'Fantastic',90255,'Berkshire navigating',NULL),(10,'Progress','Two',1083,95854,90538,'Fantastic time-frame Quality',95060,'Missouri deposit',NULL);
/*!40000 ALTER TABLE `training_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-10 14:43:15
