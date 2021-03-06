CREATE DATABASE  IF NOT EXISTS `registerdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `registerdb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: mydb.c2abvobvruo6.ap-southeast-2.rds.amazonaws.com    Database: registerdb
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `RegEnrol`
--

DROP TABLE IF EXISTS `RegEnrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RegEnrol` (
  `RegEnrolID` int(11) NOT NULL,
  `RegID` int(11) NOT NULL,
  `EnrolID` int(11) NOT NULL,
  `Attened` char(1) DEFAULT NULL,
  PRIMARY KEY (`RegEnrolID`),
  KEY `StudentID_idx` (`EnrolID`),
  KEY `RegID_idx` (`RegID`),
  CONSTRAINT `EnrolID` FOREIGN KEY (`EnrolID`) REFERENCES `Enrol` (`enrolId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RegID` FOREIGN KEY (`RegID`) REFERENCES `Register` (`registerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RegEnrol`
--

LOCK TABLES `RegEnrol` WRITE;
/*!40000 ALTER TABLE `RegEnrol` DISABLE KEYS */;
INSERT INTO `RegEnrol` VALUES (1,1,1,'t'),(2,1,2,'t'),(4,1,3,'f'),(8,2,3,'t'),(15,3,6,'f'),(30,3,12,'t'),(60,3,24,'f');
/*!40000 ALTER TABLE `RegEnrol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-19 14:32:42
