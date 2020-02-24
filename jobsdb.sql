-- MySQL dump 10.13  Distrib 8.0.19, for osx10.15 (x86_64)
--
-- Host: localhost    Database: jobsdb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `jobsdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `jobsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `jobsdb`;

--
-- Table structure for table `employers`
--

DROP TABLE IF EXISTS `employers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employers` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `LOCATION` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employers`
--

LOCK TABLES `employers` WRITE;
/*!40000 ALTER TABLE `employers` DISABLE KEYS */;
INSERT INTO `employers` VALUES (14,'Barone Sanitation','We are a very normal family-owned waste management company with several consultants.','Jersey City, NJ'),(15,'Satriale\'s Pork Store','We sell a variety of Italian meats and deli-style sandwiches. We have the best GABAGOOL, PROSCUIT, and MOOZADEL in Jersey.','Kearney, NJ'),(16,'Bada Bing Club','We are a club in beautiful Lodi, NJ. We feature a full bar and live entertainment.','Lodi, NJ'),(17,'Soprano Family','We are a very normal family in the North Caldwell area.','North Caldwell, NJ'),(28,'Jennifer Melfi, MD','I am a psychiatrist serving the Caldwell area','Caldwell, NJ'),(34,'Nuovo Vesuvio','Family-owned restaurant with brand new facilities.','Caldwell, NJ'),(35,'Jennifer Melfi M.D.','I am a psychiatrist serving the greater Caldwell area.','Caldwell, NJ');
/*!40000 ALTER TABLE `employers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employer_id` int NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `job_description` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `jobs_FK` (`employer_id`),
  CONSTRAINT `jobs_FK` FOREIGN KEY (`employer_id`) REFERENCES `employers` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (21,15,'Butcher','Lorem ipsum dolor amet everyday carry VHS lumbersexual, pitchfork shabby chic jean shorts neutra godard edison bulb bicycle rights wayfarers meggings leggings asymmetrical seitan. Salvia raclette poutine slow-carb, food truck asymmetrical vaporware banh mi yuccie waistcoat tumeric vegan. Hammock flannel YOLO 3 wolf moon, synth readymade actually beard fashion axe cornhole literally raw denim. Put a bird on it bespoke blue bottle, la croix brunch bicycle rights readymade typewriter sartorial woke XOXO copper mug pork belly. Swag butcher sriracha, hexagon meh before they sold out fashion axe cold-pressed kickstarter normcore. Migas DIY seitan intelligentsia pok pok kogi bushwick VHS roof party.','2020-02-16 18:43:55'),(22,17,'Landscaper','Lorem ipsum dolor amet everyday carry VHS lumbersexual, pitchfork shabby chic jean shorts neutra godard edison bulb bicycle rights wayfarers meggings leggings asymmetrical seitan. Salvia raclette poutine slow-carb, food truck asymmetrical vaporware banh mi yuccie waistcoat tumeric vegan. Hammock flannel YOLO 3 wolf moon, synth readymade actually beard fashion axe cornhole literally raw denim. Put a bird on it bespoke blue bottle, la croix brunch bicycle rights readymade typewriter sartorial woke XOXO copper mug pork belly. Swag butcher sriracha, hexagon meh before they sold out fashion axe cold-pressed kickstarter normcore. Migas DIY seitan intelligentsia pok pok kogi bushwick VHS roof party.','2020-02-16 18:46:08'),(23,17,'Housekeeper','Lorem ipsum dolor amet everyday carry VHS lumbersexual, pitchfork shabby chic jean shorts neutra godard edison bulb bicycle rights wayfarers meggings leggings asymmetrical seitan. Salvia raclette poutine slow-carb, food truck asymmetrical vaporware banh mi yuccie waistcoat tumeric vegan. Hammock flannel YOLO 3 wolf moon, synth readymade actually beard fashion axe cornhole literally raw denim. Put a bird on it bespoke blue bottle, la croix brunch bicycle rights readymade typewriter sartorial woke XOXO copper mug pork belly. Swag butcher sriracha, hexagon meh before they sold out fashion axe cold-pressed kickstarter normcore. Migas DIY seitan intelligentsia pok pok kogi bushwick VHS roof party.','2020-02-16 18:46:17'),(24,16,'Security','Lorem ipsum dolor amet everyday carry VHS lumbersexual, pitchfork shabby chic jean shorts neutra godard edison bulb bicycle rights wayfarers meggings leggings asymmetrical seitan. Salvia raclette poutine slow-carb, food truck asymmetrical vaporware banh mi yuccie waistcoat tumeric vegan. Hammock flannel YOLO 3 wolf moon, synth readymade actually beard fashion axe cornhole literally raw denim. Put a bird on it bespoke blue bottle, la croix brunch bicycle rights readymade typewriter sartorial woke XOXO copper mug pork belly. Swag butcher sriracha, hexagon meh before they sold out fashion axe cold-pressed kickstarter normcore. Migas DIY seitan intelligentsia pok pok kogi bushwick VHS roof party.\r\n','2020-02-16 18:46:36'),(25,16,'Bartender','Lorem ipsum dolor amet everyday carry VHS lumbersexual, pitchfork shabby chic jean shorts neutra godard edison bulb bicycle rights wayfarers meggings leggings asymmetrical seitan. Salvia raclette poutine slow-carb, food truck asymmetrical vaporware banh mi yuccie waistcoat tumeric vegan. Hammock flannel YOLO 3 wolf moon, synth readymade actually beard fashion axe cornhole literally raw denim. Put a bird on it bespoke blue bottle, la croix brunch bicycle rights readymade typewriter sartorial woke XOXO copper mug pork belly. Swag butcher sriracha, hexagon meh before they sold out fashion axe cold-pressed kickstarter normcore. Migas DIY seitan intelligentsia pok pok kogi bushwick VHS roof party.','2020-02-16 18:46:42'),(41,14,'Consultant','gabbagool proscuit braciole gabbagool proscuit braciole gabbagool proscuit braciole gabbagool proscuit braciole gabbagool proscuit braciole gabbagool proscuit braciole ','2020-02-18 21:33:49'),(50,34,'Server','','2020-02-24 08:50:11'),(51,35,'Receptionist','','2020-02-24 08:50:57');
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-24  9:05:34
