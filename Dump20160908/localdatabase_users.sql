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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `TITLE` enum('Mr','Mrs','Ms') DEFAULT NULL,
  `PHONE_NUMBER` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  `ROLES_ID` int(11) DEFAULT NULL,
  `AGENCIES_ID` int(11) DEFAULT NULL,
  `POSITIONS_ID` int(11) DEFAULT NULL,
  `LAST_MODIFIED_AT` date DEFAULT NULL,
  `LAST_MODIFIED_BY` varchar(45) DEFAULT NULL,
  `TEAMS_ID` int(11) DEFAULT NULL,
  `UID` longtext,
  `FORGOT_UID` longtext,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UX_EMAIL` (`EMAIL`),
  KEY `FK_USERS__ROLES` (`ROLES_ID`),
  KEY `FK_USERS__AGENCIES` (`AGENCIES_ID`),
  KEY `FK_USERS__DEPARTMENTS` (`POSITIONS_ID`),
  KEY `FK_USERS__TEAMS` (`TEAMS_ID`),
  CONSTRAINT `FK_USERS__AGENCIES` FOREIGN KEY (`AGENCIES_ID`) REFERENCES `agencies` (`ID`),
  CONSTRAINT `FK_USERS__DEPARTMENTS` FOREIGN KEY (`POSITIONS_ID`) REFERENCES `positions` (`ID`),
  CONSTRAINT `FK_USERS__ROLES` FOREIGN KEY (`ROLES_ID`) REFERENCES `roles` (`ID`),
  CONSTRAINT `FK_USERS__TEAMS` FOREIGN KEY (`TEAMS_ID`) REFERENCES `teams` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'Cusmuliuc','Ciprian-Gabriel','Mr','0756213506','cipriancus@gmail.com','cipriancus','$2a$11$FSxWgnoUBp2o5G5/5gAwd.mg2VNeitNn0PP0h8uU/kXk8gXQ08Mj2',1,1,1,1,'2016-09-07','Cusmuliuc',1,'7d0fd738-7b91-466f-a769-0ac73f9ac85e','2f6ac6ac-b97d-4759-9056-18eab33d2628'),(3,'Andrei','Herder','Mr','0741025591','andreiherdes@yahoo.com','andreih','$2a$11$UocHAoG0UWaxMQMj/C/38.XG2pj6F3Ckvz8RKio/JMHQ3tNpCyLre',1,1,1,1,'2016-09-07','Cusmuliuc',5,NULL,'af01f104-3f45-4225-bb1d-f387152e45b0'),(4,'Bianca','Miron','Ms','0741025591','bianca.miron@nttdata.ro','biancam','$2a$11$OtuWtRd9TG6nRzaCPh3gXu0jWrXNAibv92/wsWWaPNzsdyqfPPQKC',1,1,1,1,'2016-09-07','Cusmuliuc',5,NULL,NULL),(5,'Dan','Dulceanu','Mr','0741025591','dan.dulceanu@nttdata.ro','dan.dulceanu','$2a$11$mVur9hgD7xTr8HOaowHTxuyxZzHqxkJf5oG6sgP9iPLtg1noDwTuS',1,2,1,1,'2016-09-07','Cusmuliuc',1,NULL,NULL),(6,'Bernard','Ciurariu','Mr','0741025591','bernard.ciurariu@nttdata.ro','bernard.ciurariu','$2a$11$kHPb8AoKLrtBv/qhcbfPTu3tOqT4vTEVAJwxrGGBq5wy1wNSnWhCi',1,2,1,1,'2016-09-07','Cusmuliuc',1,NULL,'55e6a672-67a7-41c7-9f66-6d2be419ee3b'),(7,'Teofil','Ursan','Mr','0741025591','teofil.ursan@nttdata.ro','teofil.ursan','$2a$11$w3ykt0bN8lSMD6Coo108o.HAJb65OuoPdoY/YrE4bhsGoiUlH/gD2',1,2,1,1,'2016-09-07','Cusmuliuc',6,NULL,NULL),(8,'Sorin','Bahmata','Mr','0741025591','sorin.bahmata@gmail.com','sorin.bahmata','$2a$11$WIiKc/xEiTN5bp0SWgyaROYX1ToCG2U5/an8fL.E9meSCju/3CMVy',1,2,1,1,'2016-09-07','Cusmuliuc',3,NULL,'74347283-2a1c-4d05-a781-5a2d95c3e75f'),(10,'Dan','Cazacu','Mr','0741025591','dan.cazacu@nttdata.ro','dan.cazacu','$2a$11$IVVwhCRxzYhpPcLloWpV6.nrM6BOhmMGKZ5z6w3v7UnAbNqtUzfzy',1,2,1,1,'2016-09-07','Cusmuliuc',6,'32280b02-58c0-436c-8140-e66b7ec07148',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
