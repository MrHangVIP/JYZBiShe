-- MySQL dump 10.13  Distrib 5.5.39, for Win64 (x86)
--
-- Host: localhost    Database: jyzbishe
-- ------------------------------------------------------
-- Server version	5.5.39

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
-- Table structure for table `t_answer`
--

DROP TABLE IF EXISTS `t_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_answer` (
  `answerId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `questionId` int(10) unsigned NOT NULL,
  `questionnaireId` int(10) unsigned NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  `answer` varchar(255) NOT NULL,
  `createTime` varchar(45) NOT NULL,
  `createTimeStmp` longtext NOT NULL,
  `selectionId` int(10) unsigned DEFAULT NULL COMMENT '選項的答案',
  PRIMARY KEY (`answerId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='答案表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_answer`
--

LOCK TABLES `t_answer` WRITE;
/*!40000 ALTER TABLE `t_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_answer_num`
--

DROP TABLE IF EXISTS `t_answer_num`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_answer_num` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `num` int(10) unsigned NOT NULL DEFAULT '0',
  `questionnaireid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='答题数量表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_answer_num`
--

LOCK TABLES `t_answer_num` WRITE;
/*!40000 ALTER TABLE `t_answer_num` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_answer_num` ENABLE KEYS */;
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
  `line` varchar(255) DEFAULT NULL,
  `least` varchar(255) DEFAULT NULL,
  `more` varchar(255) DEFAULT NULL,
  `isMust` varchar(255) NOT NULL,
  `orders` varchar(255) DEFAULT NULL COMMENT '順序',
  PRIMARY KEY (`questionId`,`questionnaireId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='问题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_question`
--

LOCK TABLES `t_question` WRITE;
/*!40000 ALTER TABLE `t_question` DISABLE KEYS */;
INSERT INTO `t_question` VALUES (13,26,'aa','2','','不限','不限','1',NULL),(14,26,'aa','3','1','','','1',NULL);
/*!40000 ALTER TABLE `t_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_questionnaire`
--

DROP TABLE IF EXISTS `t_questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_questionnaire` (
  `questionnaireid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `introduce` varchar(1000) NOT NULL,
  `thanks` varchar(255) NOT NULL,
  `createtime` varchar(255) NOT NULL,
  `updatetime` varchar(255) DEFAULT NULL,
  `finishtime` varchar(255) DEFAULT NULL,
  `createtimestmp` longtext NOT NULL,
  `finishtimestmp` longtext,
  `nickname` varchar(255) NOT NULL,
  PRIMARY KEY (`questionnaireid`,`userid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='问卷表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_questionnaire`
--

LOCK TABLES `t_questionnaire` WRITE;
/*!40000 ALTER TABLE `t_questionnaire` DISABLE KEYS */;
INSERT INTO `t_questionnaire` VALUES (26,6,'aa','aa','您的答案已提交,感谢您的参与!','2017-11-19 19:59:06',NULL,NULL,'1511092746753',NULL,'15651071230');
/*!40000 ALTER TABLE `t_questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_score`
--

DROP TABLE IF EXISTS `t_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='积分表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_score`
--

LOCK TABLES `t_score` WRITE;
/*!40000 ALTER TABLE `t_score` DISABLE KEYS */;
INSERT INTO `t_score` VALUES (1,5,55),(2,6,55);
/*!40000 ALTER TABLE `t_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_score_record`
--

DROP TABLE IF EXISTS `t_score_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_score_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `score_change` int(11) NOT NULL,
  `createtime` varchar(255) CHARACTER SET utf8 NOT NULL,
  `action` varchar(255) NOT NULL COMMENT '操作说明',
  `questionnaireId` int(10) unsigned NOT NULL COMMENT '回答或者创建的问卷',
  PRIMARY KEY (`id`,`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk COMMENT='积分变动记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_score_record`
--

LOCK TABLES `t_score_record` WRITE;
/*!40000 ALTER TABLE `t_score_record` DISABLE KEYS */;
INSERT INTO `t_score_record` VALUES (1,6,10,'2017-11-18 18:35:04','用户注册赠送10积分',0),(2,6,5,'2017-11-19 17:01:59','回答问卷增加5积分',20),(3,6,-10,'2017-11-19 17:04:36','发表问卷扣除10积分',21),(4,6,-10,'2017-11-19 17:24:06','发表问卷扣除10积分',22),(5,6,-10,'2017-11-19 17:28:45','发表问卷扣除10积分',23),(6,6,-10,'2017-11-19 17:30:26','发表问卷扣除10积分',24),(7,6,-10,'2017-11-19 18:39:58','发表问卷扣除10积分',25),(8,6,-10,'2017-11-19 19:59:07','发表问卷扣除10积分',26);
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
  `orders` varchar(255) NOT NULL,
  PRIMARY KEY (`selectionId`,`questionId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='选项的选项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_selection`
--

LOCK TABLES `t_selection` WRITE;
/*!40000 ALTER TABLE `t_selection` DISABLE KEYS */;
INSERT INTO `t_selection` VALUES (18,13,'aa','0','0'),(19,13,'aa','0','1');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_token`
--

LOCK TABLES `t_token` WRITE;
/*!40000 ALTER TABLE `t_token` DISABLE KEYS */;
INSERT INTO `t_token` VALUES (6,'15651071230','JY263ZM230JD9IM7','1511090732633');
/*!40000 ALTER TABLE `t_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userphone` varchar(255) NOT NULL,
  `userpass` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `createtime` varchar(45) NOT NULL,
  `lastupdatetime` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL DEFAULT '南京',
  `birthday` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `headurl` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`,`userphone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (6,'15651071230','12345678','15651071230','2017-11-18 18:35:04',NULL,'南京',NULL,NULL,NULL);
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

-- Dump completed on 2017-11-19 23:25:20
