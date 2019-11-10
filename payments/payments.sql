CREATE DATABASE  IF NOT EXISTS `payments` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `payments`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: payments
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
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2019-11-10 04:02:31',1,'EXECUTED','8:9bf75aca02e1c4157cc1268a96e34b65','createTable tableName=jhi_persistent_audit_event; createTable tableName=jhi_persistent_audit_evt_data; addPrimaryKey tableName=jhi_persistent_audit_evt_data; createIndex indexName=idx_persistent_audit_event, tableName=jhi_persistent_audit_event; c...','',NULL,'3.6.3',NULL,NULL,'3358551050'),('20191110040621-1','jhipster','config/liquibase/changelog/20191110040621_added_entity_PaymentRecord.xml','2019-11-10 04:08:15',2,'EXECUTED','8:4baf51fa05e134f9712dc43e50f6a86c','createTable tableName=payment_record; dropDefaultValue columnName=issued_time, tableName=payment_record','',NULL,'3.6.3',NULL,NULL,'3358894807'),('20191110040621-1-relations','jhipster','config/liquibase/changelog/20191110040621_added_entity_PaymentRecord.xml','2019-11-10 04:08:15',3,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.6.3',NULL,NULL,'3358894807'),('20191110040621-1-data','jhipster','config/liquibase/changelog/20191110040621_added_entity_PaymentRecord.xml','2019-11-10 04:08:15',4,'EXECUTED','8:ec606420971916eeb9f57f1d288b64fc','loadData tableName=payment_record','',NULL,'3.6.3','faker',NULL,'3358894807');
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
-- Dumping data for table `payment_record`
--

LOCK TABLES `payment_record` WRITE;
/*!40000 ALTER TABLE `payment_record` DISABLE KEYS */;
INSERT INTO `payment_record` VALUES (1,'ISSUED',77,59865,'2019-11-09 05:47:01',15601,44583,'Small'),(2,'PAID',70,65088,'2019-11-09 19:36:50',5641,96990,'parsing red Chips'),(3,'PAID',61165,52475,'2019-11-09 19:56:18',43194,2306,'Namibia'),(4,'PAID',36636,23277,'2019-11-09 09:09:44',9060,78517,'grey Cambridgeshire'),(5,'PAID',68030,17052,'2019-11-09 09:17:45',6505,1326,'Usability South Dakota envisioneer'),(6,'PAID',68423,24102,'2019-11-09 09:32:39',68326,12203,'Buckinghamshire Games'),(7,'PAID',42515,52006,'2019-11-09 15:07:02',54671,56770,'Industrial'),(8,'CANCELLED',59789,72727,'2019-11-08 21:58:59',32654,8849,'sky blue Tasty Plastic Pizza'),(9,'PAID',58675,35675,'2019-11-08 20:56:31',28093,60703,'bifurcated scalable'),(10,'PAID',98688,42340,'2019-11-09 01:34:27',39488,19871,'Books');
/*!40000 ALTER TABLE `payment_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-10 14:42:37
