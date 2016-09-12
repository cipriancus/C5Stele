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
-- Table structure for table `about`
--

DROP TABLE IF EXISTS `about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `about` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `TEXT` varchar(1500) DEFAULT NULL,
  `LAST_MODIFIED_AT` date DEFAULT NULL,
  `LAST_MODIFIED_BY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `about`
--

LOCK TABLES `about` WRITE;
/*!40000 ALTER TABLE `about` DISABLE KEYS */;
INSERT INTO `about` VALUES (1,'NTT DATA IN ROMANIA','EBS, one of the leading providers of software solutions and services in Romania, has officially changed its name to NTT DATA Romania, following the acquisition of the company in 2013. This change gives a new name and identity to the company, however the team format and all management attributes, which led to the companys success over the years, are maintained. EBS was founded in 2000, and has continued to grow its reputation as an experienced software provider on the international market. The company has a knowledgeable team across six locations in Romania: Bucharest, Sibiu, Brasov, Timisoara, Iasi and the headquarters in Cluj-Napoca. NTT DATA Romania provides development assistance and expert advice for customers across various industries, IT service providers, system integrators and software companies. The services offered cover the entire software development and management lifecycle. Currently the company serves over 250 clients in multiple countries, including Romania, Germany, Austria, Switzerland, Netherlands, Norway, Finland and USA. The added value provided by NTT DATA Romania lies within the specific know-how of various business lines. This includes a thorough understanding of the main challenges felt by clients and the current economic environment. These aspects have significantly contributed to the growth of the company over the past 16 years, along with consistently enhancing customer experience.','2016-08-04','cipriancus'),(2,'Mission','NTT DATA uses information technology to create new paradigms and values, which help contribute to a more affluent and harmonious society. Clients First, first, and above all else, we place the needs of our clients. We work continuously to understand your business and we strive to resolve every concern to your satisfaction. We feel responsibility to ensure your success and we let this obligation set the direction of our work and guide our actions.Foresight,we never settle for the status quo. Instead, with speed and foresight, we anticipate challenges that lay ahead. We consider the future of IT as well as the future of your business, work to enhance our ability to picture the future, and with our ecosystems, adapt to the changing business environment. In this way, we help you to meet your goals and create a brighter future for society.Teamwork, we put great importance on enabling our employees to achieve their best through their work with each other. We believe that when a diverse group of individuals brings their unique world views together, shares their wisdom, and works toward a common goal, the results are extraordinary and far beyond what can be achieved by any one person.','2016-08-04','cipriancus'),(3,'Authors','Java interns <b>Cusmuliuc Ciprian-Gabriel</b>, <b>Andrei Herdes</b> and <b>Bianca Miron</b>. <br>Mentors: <b>Bernard Ciurariu</b> , <b>Sorin Bahmata</b>  , <b>Dan Dulceanu</b>','2016-08-04','cipriancus');
/*!40000 ALTER TABLE `about` ENABLE KEYS */;
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
