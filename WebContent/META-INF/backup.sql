-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: jyzbishe
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `t_ score`
--

DROP TABLE IF EXISTS `t_ score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  ` score` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ score`
--

LOCK TABLES `t_ score` WRITE;
/*!40000 ALTER TABLE `t_ score` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_ score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_question`
--

DROP TABLE IF EXISTS `t_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_question` (
  `questionId` int(11) NOT NULL AUTO_INCREMENT,
  `questionnaireId` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `lines` varchar(255) NOT NULL,
  `least` varchar(255) NOT NULL,
  `more` varchar(255) NOT NULL,
  `isMust` varchar(255) NOT NULL,
  PRIMARY KEY (`questionId`,`questionnaireId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question`
--

LOCK TABLES `t_question` WRITE;
/*!40000 ALTER TABLE `t_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_questionnaire`
--

DROP TABLE IF EXISTS `t_questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_questionnaire` (
  `questionnaireId` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `introduce` varchar(1000) NOT NULL,
  `thanks` varchar(255) NOT NULL,
  `createtime` varchar(255) NOT NULL,
  `updatetime` varchar(255) NOT NULL,
  `finishtime` varchar(255) NOT NULL,
  `createtimestmp` int(11) NOT NULL,
  `finishtimestmp` int(11) NOT NULL,
  PRIMARY KEY (`questionnaireId`,`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问卷表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_questionnaire`
--

LOCK TABLES `t_questionnaire` WRITE;
/*!40000 ALTER TABLE `t_questionnaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_score_record`
--

DROP TABLE IF EXISTS `t_score_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_score_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `score_change` int(11) NOT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`id`,`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='积分变动记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_score_record`
--

LOCK TABLES `t_score_record` WRITE;
/*!40000 ALTER TABLE `t_score_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_score_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_selection`
--

DROP TABLE IF EXISTS `t_selection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_selection` (
  `selectionId` int(11) NOT NULL AUTO_INCREMENT,
  `questionId` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `isSelect` varchar(255) NOT NULL,
  PRIMARY KEY (`selectionId`,`questionId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选项的选项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_selection`
--

LOCK TABLES `t_selection` WRITE;
/*!40000 ALTER TABLE `t_selection` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_selection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_token`
--

DROP TABLE IF EXISTS `t_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_token` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userphone` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `createtime` mediumtext NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_token`
--

LOCK TABLES `t_token` WRITE;
/*!40000 ALTER TABLE `t_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `stuid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userphone` varchar(255) NOT NULL,
  `userpass` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `createtime` varchar(45) NOT NULL,
  `lastupdatetime` varchar(45) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL DEFAULT '南京',
  `birthday` varchar(45) DEFAULT NULL,
  `sex` varchar(45) NOT NULL DEFAULT '女',
  `headurl` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stuid`,`userphone`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26 17:55:41
