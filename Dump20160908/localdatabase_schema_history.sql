-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: localdatabase
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `schema_history`
--

DROP TABLE IF EXISTS `schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schema_history` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `SCHEMA_HISTORY_vr_idx` (`version_rank`),
  KEY `SCHEMA_HISTORY_ir_idx` (`installed_rank`),
  KEY `SCHEMA_HISTORY_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schema_history`
--

LOCK TABLES `schema_history` WRITE;
/*!40000 ALTER TABLE `schema_history` DISABLE KEYS */;
INSERT INTO `schema_history` VALUES (1,1,'1.0','DDL create database','SQL','V1_0__DDL_create_database.sql',883819698,'root','2016-09-07 10:02:59',826,1),(14,14,'10.0','DDL change votes table','SQL','V10_0__DDL_change_votes_table.sql',-1551894586,'root','2016-09-07 10:03:06',1294,1),(15,15,'11.0','DDL modify picture','SQL','V11_0__DDL_modify_picture.sql',164165600,'root','2016-09-07 10:03:07',824,1),(16,16,'11.1','DML insert country agency','SQL','V11_1__DML_insert_country_agency.sql',469608032,'root','2016-09-07 10:03:07',30,1),(17,17,'11.2','DML insert one image','SQL','V11_2__DML_insert_one_image.sql',-52459800,'root','2016-09-07 10:03:07',10,1),(18,18,'12.0','DDL insert UID user','SQL','V12_0__DDL_insert_UID_user.sql',1436966640,'root','2016-09-07 10:03:07',447,1),(19,19,'13.0','DDL remove picsid','SQL','V13_0__DDL_remove_picsid.sql',260478823,'root','2016-09-07 10:03:08',256,1),(20,20,'13.1','DML insert samples','SQL','V13_1__DML_insert_samples.sql',26866227,'root','2016-09-07 10:03:08',80,1),(21,21,'14.0','DDL alter user forgot','SQL','V14_0__DDL_alter_user_forgot.sql',1135390463,'root','2016-09-07 10:03:08',219,1),(22,22,'15.0','DDL resize password colum','SQL','V15_0__DDL_resize_password_colum.sql',1478993513,'root','2016-09-07 10:03:08',151,1),(2,2,'2.0','DDL update database schema','SQL','V2_0__DDL_update_database_schema.sql',-513664653,'root','2016-09-07 10:03:00',629,1),(3,3,'3.0','DDL update database schema','SQL','V3_0__DDL_update_database_schema.sql',1033227181,'root','2016-09-07 10:03:00',29,1),(4,4,'4.0','DDL update database schema','SQL','V4_0__DDL_update_database_schema.sql',-1692664300,'root','2016-09-07 10:03:00',46,1),(5,5,'4.1','DML insert faqs','SQL','V4_1__DML_insert_faqs.sql',-1081186020,'root','2016-09-07 10:03:00',51,1),(6,6,'5.0','DDL add columns addtable news','SQL','V5_0__DDL_add_columns_addtable_news.sql',-1171119879,'root','2016-09-07 10:03:01',1262,1),(7,7,'5.1','DML insert news','SQL','V5_1__DML_insert_news.sql',-682146814,'root','2016-09-07 10:03:02',8,1),(8,8,'6.0','DDL add about table','SQL','V6_0__DDL_add_about_table.sql',269764199,'root','2016-09-07 10:03:02',52,1),(9,9,'6.1','DML insert about','SQL','V6_1__DML_insert_about.sql',-7717680,'root','2016-09-07 10:03:02',8,1),(10,10,'7.0','DDL add projname autoincrement','SQL','V7_0__DDL_add_projname_autoincrement.sql',612189655,'root','2016-09-07 10:03:04',1952,1),(11,11,'8.0','DDL create contact table','SQL','V8_0__DDL_create_contact_table.sql',-1143806306,'root','2016-09-07 10:03:04',88,1),(12,12,'8.1','DML insert contact','SQL','V8_1__DML_insert_contact.sql',1644774907,'root','2016-09-07 10:03:04',10,1),(13,13,'9.0','DDL insert periodId votes','SQL','V9_0__DDL_insert_periodId_votes.sql',981200910,'root','2016-09-07 10:03:05',837,1);
/*!40000 ALTER TABLE `schema_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-08  8:55:50
