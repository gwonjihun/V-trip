CREATE DATABASE  IF NOT EXISTS `tripdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tripdb`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tripdb
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attraction_description`
--

DROP TABLE IF EXISTS `attraction_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_description` (
  `content_id` int NOT NULL,
  `homepage` varchar(100) DEFAULT NULL,
  `overview` varchar(10000) DEFAULT NULL,
  `telname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attraction_detail`
--

DROP TABLE IF EXISTS `attraction_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_detail` (
  `content_id` int NOT NULL,
  `cat1` varchar(3) DEFAULT NULL,
  `cat2` varchar(5) DEFAULT NULL,
  `cat3` varchar(9) DEFAULT NULL,
  `created_time` varchar(14) DEFAULT NULL,
  `modified_time` varchar(14) DEFAULT NULL,
  `booktour` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attraction_info`
--

DROP TABLE IF EXISTS `attraction_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_info` (
  `content_id` int NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `addr1` varchar(100) DEFAULT NULL,
  `addr2` varchar(50) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `first_image` varchar(200) DEFAULT NULL,
  `first_image2` varchar(200) DEFAULT NULL,
  `readcount` int DEFAULT NULL,
  `sido_code` int DEFAULT NULL,
  `gugun_code` int DEFAULT NULL,
  `latitude` decimal(20,17) DEFAULT NULL,
  `longitude` decimal(20,17) DEFAULT NULL,
  `mlevel` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  KEY `attraction_to_content_type_id_fk_idx` (`content_type_id`),
  KEY `attraction_to_sido_code_fk_idx` (`sido_code`),
  KEY `attraction_to_gugun_code_fk_idx` (`gugun_code`),
  CONSTRAINT `attraction_to_content_type_id_fk` FOREIGN KEY (`content_type_id`) REFERENCES `content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk` FOREIGN KEY (`gugun_code`) REFERENCES `gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk` FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `writername` varchar(40) NOT NULL,
  `content_id` int NOT NULL,
  `link` varchar(40) DEFAULT '0',
  `createat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateat` datetime DEFAULT NULL,
  `deleteat` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `id_idx` (`writername`),
  KEY `content_idx` (`content_id`),
  CONSTRAINT `content` FOREIGN KEY (`content_id`) REFERENCES `content` (`content_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`writername`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content` (
  `content_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` text NOT NULL,
  `writername` varchar(40) NOT NULL,
  `reads` int DEFAULT '0',
  `createat` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateat` datetime DEFAULT NULL,
  `deleteat` datetime DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  KEY `id_idx` (`writername`),
  CONSTRAINT `id` FOREIGN KEY (`writername`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `content_like`
--

DROP TABLE IF EXISTS `content_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content_like` (
  `content_id` int NOT NULL,
  `users_id` varchar(40) NOT NULL,
  PRIMARY KEY (`content_id`,`users_id`),
  KEY `fk_content_like_users1_idx` (`users_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_content_like_content` FOREIGN KEY (`content_id`) REFERENCES `content` (`content_id`),
  CONSTRAINT `fk_content_like_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `coupon_id` int NOT NULL AUTO_INCREMENT,
  `couponcol` varchar(45) DEFAULT NULL,
  `ids` varchar(40) NOT NULL,
  `kind_id` int DEFAULT NULL,
  `startdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enddate` datetime DEFAULT NULL,
  `usedate` datetime DEFAULT NULL,
  PRIMARY KEY (`coupon_id`),
  KEY `kind_id_idx` (`kind_id`),
  KEY `ids` (`ids`),
  CONSTRAINT `ids` FOREIGN KEY (`ids`) REFERENCES `users` (`id`),
  CONSTRAINT `kind` FOREIGN KEY (`kind_id`) REFERENCES `coupondetail` (`kind_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coupondetail`
--

DROP TABLE IF EXISTS `coupondetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupondetail` (
  `kind_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `sales` int DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`kind_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gugun`
--

DROP TABLE IF EXISTS `gugun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gugun` (
  `gugun_code` int NOT NULL,
  `gugun_name` varchar(30) DEFAULT NULL,
  `sido_code` int NOT NULL,
  PRIMARY KEY (`gugun_code`,`sido_code`),
  KEY `gugun_to_sido_sido_code_fk_idx` (`sido_code`),
  CONSTRAINT `gugun_to_sido_sido_code_fk` FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `writerid` varchar(40) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `reads` int DEFAULT '0',
  `share` tinyint DEFAULT '0',
  `sidocode` int DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `createat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleteat` datetime DEFAULT NULL,
  `updateat` datetime DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `id_idx` (`writerid`),
  CONSTRAINT `writername` FOREIGN KEY (`writerid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan_like`
--

DROP TABLE IF EXISTS `plan_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_like` (
  `plan_id` int NOT NULL,
  `users_id` varchar(40) NOT NULL,
  PRIMARY KEY (`plan_id`,`users_id`),
  KEY `fk_plan_like_users1_idx` (`users_id`),
  CONSTRAINT `fk_plan_like_plan` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`),
  CONSTRAINT `fk_plan_like_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plandetail`
--

DROP TABLE IF EXISTS `plandetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plandetail` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `plan_id` int NOT NULL,
  `content_id` int NOT NULL,
  `days` int NOT NULL,
  `index` int NOT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `_idx` (`plan_id`),
  KEY `attraction_info_idx` (`content_id`),
  CONSTRAINT `attraction_info` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`),
  CONSTRAINT `plan_id` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salt`
--

DROP TABLE IF EXISTS `salt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salt` (
  `id` varchar(40) NOT NULL,
  `salt` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shareplan`
--

DROP TABLE IF EXISTS `shareplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shareplan` (
  `plan_id` int NOT NULL,
  `users_id` varchar(40) NOT NULL,
  PRIMARY KEY (`plan_id`,`users_id`),
  KEY `fk_plan_like_users1_idx` (`users_id`),
  CONSTRAINT `fk_plan_plan` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`),
  CONSTRAINT `fk_plan_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sido`
--

DROP TABLE IF EXISTS `sido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sido` (
  `sido_code` int NOT NULL,
  `sido_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(40) NOT NULL,
  `user_type` varchar(45) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `point` int DEFAULT '0',
  `deleteat` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-24 16:26:34
