-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cloud_menu
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
-- Table structure for table `system_menu`
--

DROP TABLE IF EXISTS `system_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `system_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `path` varchar(100) NOT NULL DEFAULT '' COMMENT '链接',
  `sort` int(11) DEFAULT '0' COMMENT '菜单排序',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0:禁用,1:启用)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `component` varchar(500) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `title` (`title`),
  KEY `href` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_menu`
--

LOCK TABLES `system_menu` WRITE;
/*!40000 ALTER TABLE `system_menu` DISABLE KEYS */;
INSERT INTO `system_menu` VALUES (31,0,'首页','fa fa-home','home',0,1,'2022-11-12 09:41:25','home/Home','/admin/home'),(32,0,'菜单管理','fa fa-th','menu',1,1,'2022-11-12 10:20:18','',''),(33,32,'菜单列表','fa fa-list','list',0,1,'2022-11-12 10:22:34','menu/MenuList','/admin/menu/list'),(34,0,'商品管理','fa fa-shopping-bag','goods',2,1,'2022-11-12 10:28:50','',''),(35,0,'用户管理','fa fa-users','user',3,1,'2022-11-12 10:34:01','',''),(37,32,'添加菜单','fa fa-plus-circle','add',0,0,'2022-11-12 11:07:48','menu/AddMenu','/admin/menu/add'),(38,34,'商品列表','fa fa-table','list',0,1,'2022-11-12 11:50:23','goods/GoodsList','/admin/goods/list'),(39,34,'商品分类','fa fa-bookmark','category/list',0,1,'2022-11-12 11:57:47','goods/GoodsCategory','/admin/goods/category/list'),(42,35,'管理员列表','fa fa-user','admin/list',0,1,'2022-11-12 12:24:08','user/AdminList','/admin/user/admin/list'),(43,35,'用户列表','fa fa-user','user/list',0,1,'2022-11-12 12:27:31','user/UserList','/admin/user/user/list'),(44,34,'添加商品','fa fa-plus','add',0,0,'2022-11-15 09:43:30','goods/AddGoods','/admin/goods/add'),(45,34,'编辑商品','fa fa-edit','edit',0,0,'2022-11-26 16:19:29','goods/EditGoods','/admin/goods/edit'),(46,32,'编辑菜单','fa fa-edit','edit',0,0,'2022-11-27 09:57:36','menu/EditMenu','/admin/menu/edit'),(47,0,'文件管理','fa fa-folder','file',5,1,'2022-12-01 15:57:03','',''),(48,47,'图片','fa fa-picture-o','img/list',0,1,'2022-12-01 16:04:22','file/ImgList','/admin/file/img/list'),(49,0,'权限角色','fa fa-user-secret','role',4,1,'2022-12-01 16:37:54','',''),(50,49,'角色列表','fa fa-user-circle','list',0,1,'2022-12-01 16:45:50','role/RoleList','/admin/role/list');
/*!40000 ALTER TABLE `system_menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-06  9:59:13
