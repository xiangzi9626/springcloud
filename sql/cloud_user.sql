-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cloud_user
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `r_id` varchar(500) DEFAULT NULL COMMENT '角色ID',
  `money` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '余额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `level` smallint(6) DEFAULT '3',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','12345678900','22',0.00,'2021-02-16 18:52:30',1),(5,'user02','e10adc3949ba59abbe56e057f20f883e','12345212130','0',0.00,'2021-05-26 14:50:49',3),(6,'user03','e10adc3949ba59abbe56e057f20f883e','12102124533',NULL,0.00,'2021-05-26 14:51:48',3),(13,'admin01','e10adc3949ba59abbe56e057f20f883e','12345678907','24',0.00,'2021-08-04 15:10:18',2),(14,'admin02','e10adc3949ba59abbe56e057f20f883e','12345678902','24',0.00,'2022-12-06 14:02:58',1),(15,'admin03','e10adc3949ba59abbe56e057f20f883e','12345678903','22',0.00,'2022-12-06 14:04:07',1),(16,'admin04','e10adc3949ba59abbe56e057f20f883e','12345678904',NULL,0.00,'2022-12-06 14:07:59',1),(17,'admin05','e10adc3949ba59abbe56e057f20f883e','12345678905',NULL,0.00,'2022-12-06 14:11:47',1),(18,'admin06','e10adc3949ba59abbe56e057f20f883e','10212121077',NULL,0.00,'2023-03-12 09:44:40',1),(19,'admin07','e10adc3949ba59abbe56e057f20f883e','12545678900','24',0.00,'2023-03-17 14:42:39',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-06 10:01:05
