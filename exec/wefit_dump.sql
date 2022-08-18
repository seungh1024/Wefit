-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 3.36.88.140    Database: ssafy
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `src_id` bigint NOT NULL,
  `dest_id` bigint NOT NULL,
  PRIMARY KEY (`src_id`,`dest_id`),
  KEY `FK7pm6a6gs2eh0o7yyu4c191hpy` (`dest_id`),
  CONSTRAINT `FK7pm6a6gs2eh0o7yyu4c191hpy` FOREIGN KEY (`dest_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKpbv6n81lumqf1rhpmwbcq438f` FOREIGN KEY (`src_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hate_mbti`
--

DROP TABLE IF EXISTS `hate_mbti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hate_mbti` (
  `hate_mbti_id` int NOT NULL AUTO_INCREMENT,
  `mbti_name` varchar(255) DEFAULT NULL,
  `user_detail_id` bigint DEFAULT NULL,
  PRIMARY KEY (`hate_mbti_id`),
  KEY `FKi11dm5slrug0goyv4wx1vj1py` (`mbti_name`),
  KEY `FKq1tjd1ec4c2v9us7snshu1pll` (`user_detail_id`),
  CONSTRAINT `FKi11dm5slrug0goyv4wx1vj1py` FOREIGN KEY (`mbti_name`) REFERENCES `mbti` (`mbti_name`),
  CONSTRAINT `FKq1tjd1ec4c2v9us7snshu1pll` FOREIGN KEY (`user_detail_id`) REFERENCES `user_detail` (`user_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hate_mbti`
--

LOCK TABLES `hate_mbti` WRITE;
/*!40000 ALTER TABLE `hate_mbti` DISABLE KEYS */;
INSERT INTO `hate_mbti` VALUES (3,'intp',24),(4,'infj',24),(5,'infp',24),(11,'esfj',25),(12,'infj',29),(13,'infp',29),(14,'isfp',29),(15,'infj',30),(16,'estj',30),(17,'esfp',30);
/*!40000 ALTER TABLE `hate_mbti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest` (
  `interest_name` varchar(50) NOT NULL,
  PRIMARY KEY (`interest_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
INSERT INTO `interest` VALUES ('IT'),('kpop'),('감성적인'),('건강'),('게임'),('골프'),('공기업'),('내향적'),('넷플릭스'),('다이어트'),('독서'),('드라이브'),('디자인'),('맛집'),('봉사활동'),('부동산'),('비건'),('서핑'),('술'),('스시'),('스타트업'),('언어교환'),('여행'),('영화감상'),('와인'),('외향적'),('웹툰'),('유머러스'),('인턴'),('전시회관람'),('주식'),('집콕'),('취준'),('커피'),('코인'),('테니스'),('필라테스'),('헬스'),('환경보호');
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbti`
--

DROP TABLE IF EXISTS `mbti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbti` (
  `mbti_name` varchar(255) NOT NULL,
  PRIMARY KEY (`mbti_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbti`
--

LOCK TABLES `mbti` WRITE;
/*!40000 ALTER TABLE `mbti` DISABLE KEYS */;
INSERT INTO `mbti` VALUES ('enfj'),('enfp'),('entj'),('entp'),('esfj'),('esfp'),('estj'),('estp'),('infj'),('infp'),('intj'),('intp'),('isfj'),('isfp'),('istj'),('istp');
/*!40000 ALTER TABLE `mbti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_j09k2v8lxofv2vecxu2hde9so` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test1','$2a$10$0FDqUYAflvTFuFurUqWr1eGRZJ3J9KE9JZOmD2Rhq4tI1tIvgAir6'),(2,'1234','$2a$10$VwRy/vHbAFiglqNBeaWPr.pEFJNsn3fm4yBJNxbKJHoet152oCkwu'),(3,'test','$2a$10$cAVu2UCcY3v70B5XC3PfjeXsP9OLla1OHaH1hwvs/H8ZPWfMhAjNq'),(4,'test142324','$2a$10$OT3Eik88oIUjBzC37VsdZ.euag6qjATNbxD94jmUSitA9B3g7soZS'),(5,'test14232cfv4','$2a$10$ae3/G1UPYBtu/MmJjWWpdub.YOum1u4uBcD6uGiqWtNrJhDRLsKiC'),(6,'test14232cfv43','$2a$10$6opcilOXy.vVdrwfUYJ.tuSfZlgVjDKgZrmL7E0P2hev/P1JB5sf.'),(7,'서준희','$2a$10$mOkS2l.CVFRjk3lWoSYyJO0AiSeh2hCooAD.gaUaZnsbfWNgpmtiS'),(8,'서준희2','$2a$10$Bq6FaS4T0hIMYA7CfuPiQOcGkV2R/OJqlv8kNFHM14QCeZE9WDDJG'),(9,'서준희3','$2a$10$JcO64tr7YqZVBHXHZry.F.0okmQyIP5r9KnppurqQi3VWlutT9WVW'),(10,'서준희4','$2a$10$7ajo25SbWJ2djWJDePIhtuwmh9EPBb6aQmHi8ZSS0h36InTXFGRRu'),(11,'서준희999','$2a$10$Ajc6p8lCBG7HNsx5ndSqienN1TXeAH5iQHoKqa5Sed3oM/vusH4cm'),(12,'서준희998','$2a$10$EpO3X8pwEIN0zHd/o10cAuchmqB6EUxG6ZiILWBmRQTm0g7/jmfwy'),(13,'서준희997','$2a$10$YngRSOK4ZEtAILCpznqLUuwPm2x7xVjVlZYetq3s/L69kv/wuJjj.'),(14,'서준희555','$2a$10$Tpck.Y1kJUXN0d40y.EQH.ffsrRQlARpSz0buAHED7m3yVQiNvGR.'),(15,'서준희556','$2a$10$LZawYPPpoWyUt/dS6VY3i.3Xk.GoFlbwEY/wDb/6YRWljs6JEuHJ.'),(16,'서준희557','$2a$10$OY58IUV8slznVP1RAtWrG.F/0RPRKGYftxbO1qmYL7B20WKb4/w/O'),(17,'서준희222','$2a$10$qPZuW4LviiGfqpbwu5pMl.DiifoXu75ZBHbKPvIZrnA09JGARt9ae'),(18,'서준희1111','$2a$10$Pu.Q5.ZLrOUhmCQsOSxNAO2afJL9yq0tnmjEKSDxpElGTtHoOsgyi'),(19,'서준희12345','$2a$10$aB67LfV1LRLXkqxhFv54/uFCXxD7sgY2gy0wvWQk5oGVaI3KRRY0C'),(20,'서준희55555','$2a$10$d5dC6.JxzDgBJeU8XTH/ium3U8vr7UMyFHFrPkdopOIwtdk5W6v3q'),(21,'서준희77777','$2a$10$igpuoOgockVwdxbqYd0cR.ezZLRhf/vW8zgh7m3EbnbbQ6vjzwbjm'),(22,'서준희88888','$2a$10$IMzdSkM5MbMhXhgJF1Z8AeAfqpx5o/pPpCjoGmRRy502XBP0cG..u'),(23,'서준희99999','$2a$10$RAi7QGazwqLVsZHsDRdF5urxbyJsRmABxDVJDk2NUS3dW/fX5a0fK'),(24,'서준희555556','$2a$10$.XxFow0bVUGrSNV6M/kJC.sjmJzMBD1tt91JUJosmOqZBgHsmXHFy'),(25,'testtest','$2a$10$sQIF9y8mR5vYZbCYmXPyi.96g1O9sv3xt7wF/ICHSdis.llmXRDxO'),(26,'마지막테스트','$2a$10$POLJjWzrhZQEtCx866gdweyANizJDWsuHSKoeHBN.zFckh/2WvKpO'),(27,'진짜마지막','$2a$10$HJgKy.nGiwSRo3qi9IvBgukruFV1T0RBb72RPFTsAOgf8e2vJIOw.'),(28,'대전2반화이팅','$2a$10$QxnAqEP.18QfcTLNU/MSveLg95C4ItNwj3OD2DkpvOMfYE.vN.rHq'),(29,'6팀화이팅','$2a$10$xaT19DG3i5FVdvx6hn7J7.fesge30rWpVVvqy07B21ZpyDlKt3DvK'),(30,'진짜마지막테스트다','$2a$10$ttzhdr6XWnYjRiIjpDKGUuE0hTWhNoqyttg6W4Mq/93fcvxc12GZu'),(31,'끝이냐?','$2a$10$ImlGRajU7W.Tp2a1uyRJ7ejk8MXDEHrVIqM.pjk0uRP6AN2tUmPMO'),(32,'아직도끝이아니라니','$2a$10$ztdtYACjV5Nq6h9k269tsOrk0Mi1v6D4nFRwFhwl1JUx1bQRgqPCC'),(33,'이젠제발끝내줘','$2a$10$VD.0yzmIEBxjKZ43MtM1ieopye6AveSUTdVApiUxHphPc9uNVKD/C'),(34,'라우터만테스트하고잠','$2a$10$4yjxnroJ/iuR3UFGL/2HkePM8SOJcSxkFDxjdU4tznCASJrGSXYuy'),(35,'서준희8888888','$2a$10$qHjrs20TFDJCHVWWnFqin.O.39RRdbZtX.jCLOlBrqdXSJ2zJOgPS'),(36,'lasttest','$2a$10$AF9Pshi4pOR9I1FXJmIfhePFBNpG4ZOY74OU6JS7Zz1X9fjIgqRPK'),(37,'temp5','$2a$10$Zwj8NLUO0AdEjfPnfUpMcOrHmX2KPkOsDDDMxuZ0PIA6S./DzF6zK'),(38,'asd@naver.com','$2a$10$xN3mAqxTGoti0F4zFOqWYexyf3IICPOq8vbfGWT71SfQq5uzvRXE.'),(39,'서준희b206','$2a$10$lezT2y8PeMjc0PVXvanZzunRpKFg2yZg2cK.yuK2dqfkreRFL1P5O'),(40,'watermelons3394@gmail.com','$2a$10$TMOky5Lj/k9SRBKhCOd0YeJKp2wo9hBBbVQ89Aq/1QH6Au0xf7WRK'),(41,'5시테스트','$2a$10$DevbIed/kmMrYAFt7ypjbeWfP3oWVLS0mpIZsUW6zCnp5yoEwKe.S'),(42,'asd1@naver.com','$2a$10$PWI2ryZMsxzDyy0VR3W5QeMK7K.M9OIxDxxgUdUWImyLQMedmK8Gq'),(43,'asd2@naver.com','$2a$10$XJZOHx24hxPNwP1xzlQFp.jshUl4PsCUYnssxMNt5wNwsvxT3DwFy'),(44,'test2','$2a$10$nG2cqF4P2PnF4xpxwCSD1OWx6LOyX9C8VZwwqumzyGi/gNwdBaoSG'),(46,'test14@naver.com','$2a$10$O2RFqUXJlB3rczYuPI1MgOaY.dKrcAYJNDJKMbm6NdO2ztbMCHIJO'),(47,'test15@naver.com','$2a$10$WApV97HWn8RV1BUwZkF28OsK3i1MTjnzywwXaGwd/La0xhe0vb/8m'),(48,'test16@naver.com','$2a$10$OdAWhIYDIj1OJ.q8oqpiFePcQz/O7z5QRCTJR9wtMtm3nLAKWkNv.'),(49,'서준희1','$2a$10$8L0WPNC4FRnpTYvCOF3zI.JiyG805EoXphfDYdgDLRe/gHhvsYft6'),(50,'대전2반서준희','$2a$10$khgIS9QQNGBcPU52ZgaAnOOznhIjP50dLvO590ciIeNVkTN8EQEZW'),(51,'test17@naver.com','$2a$10$VLWNN7phsCC6lN7/A.FJ/O1aRpr1qoUakU5ZdyItiikgIEXsXEIPq'),(52,'1111','$2a$10$cVbrIxDZZaZDvu3bo2n.yuoo1SO440B.pEugDkwifhEqL9rosxrGK'),(53,'2222','$2a$10$Bj9xJ1oUKERlmInvFmjkR.QGz5fZO.Bqv0Cnia/JBx9LeXh4HtVO2'),(55,'대전서준희','$2a$10$qBvK4A5g3aNjUebJogAuoeNqEF7qpI5geU6X.3.6og1V23SbGe4OC'),(56,'3333','$2a$10$AJHT/xb6RD4.qmsy2OFj4OrZi.x4Pu/tF/I.MaokTaNlzyFCgrjdm'),(57,'4444','$2a$10$0HEdUQL63WQqpOwhBAPeQu3gNJhgukNICd61.zm.bXUEjIaLY./5K'),(58,'seungh1024','$2a$10$Ra7HKhXij1oASGutn58ouuUb6CAy2/Q6h3L1/EInXAQoKTm0NBpBm'),(59,'ssafy07206@gmail.com','$2a$10$t7AnqUv7cjxBBeTZ1d1MmOYvWFdSTZQdvVQuRDreUabgy1zn5mHKu'),(60,'qqqq','$2a$10$e1UAhYAOxodKWiY5mtvM4uN0u1nMEy2FAdtims2veqo5JSSoIA5ay'),(61,'wwww','$2a$10$zN.p0ufnkqE.cmbtyEiOEOHAQsdgDPkoFWip13bgN1zuchE6CKbhm'),(62,'염탐하러왔습니다','$2a$10$yYnNocAuYrey7ypRAWOIMeGGJ0Ab7bsXvCCOB0hlpSNzw8/1/dKPa'),(63,'09870987','$2a$10$jxVnRMeTbo0SxyKEEY7nGOp0BKRnlTrUQ/jJRonoGp716fGhtH7Mu'),(64,'6666','$2a$10$1OqXCT8Kk.jC4BddNpIch.R1fPmi6o73beeRZ8lKvv0.n6jd6IKcC'),(65,'qwer','$2a$10$KS1nCCDsBnkL6pQS90D0n.Jf/VMd3e8ynij3Y75qByP79TTF4ZbTy'),(66,'eeeee','$2a$10$y4VtBcIBuHbzS7pYQpAMju44DQ0UiS2cqxhuBV9Km3mxHnz8IDoAy'),(67,'qqq123@naver.com','$2a$10$nbFP5k5NNJCC6L3yq4ndPu45k32PS1ZRaAJzUzKylLwTq16PKseuO'),(68,'qwer@gmail.com','$2a$10$WyvOE5IGkJtkg6YzoX/R2.zGVs4gc.6wRTy9Wye6O/iVAK3UfLWXW'),(69,'ssafy@ssafy7.com','$2a$10$OeeGycfVywFPhxGWXozMSeqOzg5Vna1B7t.FwSse6cgwlMXQ6OIbO'),(70,'abc123@naver.com','$2a$10$NYV2PCwNfdCv3bItHHVQOu77Vwakksvhufo8QBM7R9nL1JGXsNL0u'),(71,'test@test.com','$2a$10$G9r7.ys0TRGcFcNZ0KFayu3R8ON0oenz5R98/st//l5OpuOAek.yG'),(72,'coach13@ssafy.com','$2a$10$2NYtGpSlW0tLa5VcKu2PYO07vKsmFIiJHgSiBUAqNJanTXzxnQiWi'),(73,'rlarudehd32@gmail.com','$2a$10$qFX/0knKYgyq5RF5h32FWuKgvsZfHcXz4X.cludytEBwwrkYRusRe'),(74,'ttttt@gmai.com','$2a$10$3Y6MosgA9t6vOF3IL4dbZuniee.lnkZlwu0Jbq2ry.Ymad00PY51q'),(75,'qweqwewqweqw@123','$2a$10$o4eXKBShmhXMhDIK86moVefzADGcbmviM0/SkaJfwr7oguM0k7b.m'),(76,'test123@test123','$2a$10$pBn1fYZVQV5GqADFYsHd.exmekSy4M3PbqbVKiMji7sw5CqOlTNFu'),(77,'test486@test.com','$2a$10$STG3DFMbxww.XUawKxuG1OWL8bZlSCT1zH08/AHusNJ8AECjH3e4i'),(78,'kci8311@gmail.com','$2a$10$du2s170h33V6tUtHxk3DY.IBNM9.7DT9tycEsjQvmFSfxV0NqCIYa'),(79,'kci911@hanmail.net','$2a$10$64Wm7sUf3LJ/lDQFDflW0uHOKpAiHDv7xpeX9R69Pg9v2qhBe8B9i'),(80,'ss@gmail.com','$2a$10$HkS.5vY3IDdkPWnidz.z7edONT5J56cRo5IniAFX8TuSLyysNaU6K'),(81,'wmf3362@naver.com','$2a$10$cocZ0t3VKp8zmT.3cgVv6.dYo0Aq675K/xL.Alcl2lDf7BsVi7yuu'),(82,'서준희TEST','$2a$10$t.8CZyCUSn35hc/RfScrPezqkUkmcpkmPtXoPZYeQQX.HbYcNIElG'),(83,'rlawlstks@naver.com','$2a$10$GTDM7VqOql9KC58nB/Q9M.PkvXveQDlTI7YOlXlX3dTHsxdanMKNK'),(84,'dbdudwls@naver.com','$2a$10$xJeBpZCXFWAXpQg2Lgdofe2ocGfVzBXc6hmxsHdzU3mepG/9KZBcG'),(85,'alsl3600@gmail.com','$2a$10$ge5qk2VoeZ0jZzwCkySkReLAzNMVUctVNBcu/2E227qCaFegd9W96');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_authority` (
  `user_id` bigint NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `FK6ktglpl5mjosa283rvken2py5` (`authority_name`),
  CONSTRAINT `FK6ktglpl5mjosa283rvken2py5` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`authority_name`),
  CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (1,'ROLE_USER'),(2,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(6,'ROLE_USER'),(7,'ROLE_USER'),(8,'ROLE_USER'),(9,'ROLE_USER'),(10,'ROLE_USER'),(11,'ROLE_USER'),(12,'ROLE_USER'),(13,'ROLE_USER'),(14,'ROLE_USER'),(15,'ROLE_USER'),(16,'ROLE_USER'),(17,'ROLE_USER'),(18,'ROLE_USER'),(19,'ROLE_USER'),(20,'ROLE_USER'),(21,'ROLE_USER'),(22,'ROLE_USER'),(23,'ROLE_USER'),(24,'ROLE_USER'),(25,'ROLE_USER'),(26,'ROLE_USER'),(27,'ROLE_USER'),(28,'ROLE_USER'),(29,'ROLE_USER'),(30,'ROLE_USER'),(31,'ROLE_USER'),(32,'ROLE_USER'),(33,'ROLE_USER'),(34,'ROLE_USER'),(35,'ROLE_USER'),(36,'ROLE_USER'),(37,'ROLE_USER'),(38,'ROLE_USER'),(39,'ROLE_USER'),(40,'ROLE_USER'),(41,'ROLE_USER'),(42,'ROLE_USER'),(43,'ROLE_USER'),(44,'ROLE_USER'),(46,'ROLE_USER'),(47,'ROLE_USER'),(48,'ROLE_USER'),(49,'ROLE_USER'),(50,'ROLE_USER'),(51,'ROLE_USER'),(52,'ROLE_USER'),(53,'ROLE_USER'),(55,'ROLE_USER'),(56,'ROLE_USER'),(57,'ROLE_USER'),(58,'ROLE_USER'),(59,'ROLE_USER'),(60,'ROLE_USER'),(61,'ROLE_USER'),(62,'ROLE_USER'),(63,'ROLE_USER'),(64,'ROLE_USER'),(65,'ROLE_USER'),(66,'ROLE_USER'),(67,'ROLE_USER'),(68,'ROLE_USER'),(69,'ROLE_USER'),(70,'ROLE_USER'),(71,'ROLE_USER'),(72,'ROLE_USER'),(73,'ROLE_USER'),(74,'ROLE_USER'),(75,'ROLE_USER'),(76,'ROLE_USER'),(77,'ROLE_USER'),(78,'ROLE_USER'),(79,'ROLE_USER'),(80,'ROLE_USER'),(81,'ROLE_USER'),(82,'ROLE_USER'),(83,'ROLE_USER'),(84,'ROLE_USER'),(85,'ROLE_USER');
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_detail` (
  `user_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `user_connect` bit(1) DEFAULT NULL,
  `user_created` bit(1) DEFAULT NULL,
  `user_field` varchar(255) DEFAULT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_is_deleted` bit(1) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_singo_count` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `user_temp_pwd` varchar(255) DEFAULT NULL,
  `user_mbti` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_detail_id`),
  KEY `FKc2fr118twu8aratnm1qop1mn9` (`user_id`),
  CONSTRAINT `FKc2fr118twu8aratnm1qop1mn9` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_detail`
--

LOCK TABLES `user_detail` WRITE;
/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;
INSERT INTO `user_detail` VALUES (1,_binary '\0',_binary '\0','대전','남',_binary '\0','서준희','서준희테스트','01030092554',0,8,NULL,NULL),(2,_binary '\0',_binary '\0','경기도','',_binary '\0','',NULL,'',0,30,NULL,NULL),(3,_binary '\0',_binary '\0','대전','',_binary '\0','',NULL,'',0,32,NULL,NULL),(4,_binary '\0',_binary '\0','대전','',_binary '\0','',NULL,'',0,33,NULL,NULL),(5,_binary '\0',_binary '\0','대전','',_binary '\0','',NULL,'',0,34,NULL,NULL),(6,_binary '\0',_binary '\0','대전','',_binary '\0','',NULL,'',0,35,NULL,NULL),(7,_binary '\0',_binary '\0','대전','',_binary '\0','seojunhee',NULL,'',0,36,NULL,NULL),(8,_binary '\0',_binary '\0','대전','man',_binary '\0','seojunhee','1234','',0,37,NULL,'ENFP'),(9,_binary '\0',_binary '\0','','',_binary '\0','','','',0,38,NULL,NULL),(10,_binary '\0',_binary '\0','대전','man',_binary '\0','서준희','개발잘하고싶다','',0,39,NULL,'ENFP'),(11,_binary '\0',_binary '\0','대전','man',_binary '\0','서준희','테스트용','11111111111',0,41,NULL,'ENFP'),(12,_binary '\0',_binary '\0','대전','man',_binary '\0','서준희','너무신나','01030092554',0,40,NULL,'ESFP'),(13,_binary '\0',_binary '\0','대전','man',_binary '\0','서준희','서준희','01030092554',0,40,NULL,'ESTP'),(14,_binary '\0',_binary '\0','대전','man',_binary '\0','text1','text','01023125125',0,42,NULL,'INTP'),(15,_binary '\0',_binary '\0','서울시','',_binary '\0','마','압자아밪','숫자만 들어가게 바꾸',0,43,NULL,'ESTP'),(16,_binary '\0',_binary '\0','df','man',_binary '\0','','df','',0,44,NULL,'ISTP'),(19,_binary '\0',_binary '\0','ㄷㅈ','man',_binary '\0','테스트','ㅌㅅㅌ','11111111111',0,48,NULL,'ENTJ'),(20,_binary '\0',_binary '\0','대전','man',_binary '\0','서준희','서준희','01000000000',0,49,NULL,'ISTJ'),(21,_binary '\0',_binary '\0','대전','man',_binary '\0','서준희','서준희12354','777777',0,50,NULL,'ISTJ'),(23,_binary '\0',_binary '\0','ㅈㅂㅇㅈㅂ','man',_binary '\0','대전','ㅇㅈㅂㅇㅂㅍㅂ','12428492491',0,51,NULL,'ESTP'),(24,_binary '\0',_binary '\0','대전','man',_binary '\0','ㅇㅇㅇ','오타 수정 완료','01050923539',0,52,NULL,'ISTJ'),(25,_binary '\0',_binary '\0','대전','man',_binary '\0','qqqq','qqqq','01050923539',0,53,NULL,'ENTP'),(26,_binary '\0',_binary '\0','대전','',_binary '\0','서준희','1234','01000000000',0,40,NULL,'ENFP'),(28,_binary '\0',_binary '\0','대전','man',_binary '\0','관심사test','서준희','11111111111',0,55,NULL,'ESTP'),(29,_binary '\0',_binary '\0','대전','man',_binary '\0','바보','바보','01010101010',0,56,NULL,'ISTP'),(30,_binary '\0',_binary '\0','대전','man',_binary '\0','멍청이','멍청이','12312312312',0,57,NULL,'ENTP'),(33,_binary '\0',_binary '\0','ssafy','man',_binary '\0','','EhdajrdjEhd','ssafy',0,59,NULL,'ISTJ'),(34,_binary '\0',_binary '\0','qqqq','man',_binary '\0','','qqqq','qqqq',0,60,NULL,'ISTJ'),(35,_binary '\0',_binary '\0','wwww','man',_binary '\0','','wwww','wwwww',0,61,NULL,'ISTJ'),(36,_binary '\0',_binary '\0','','',_binary '\0','','','',0,62,NULL,''),(37,_binary '\0',_binary '\0','대전광역시','woman',_binary '\0','09870987','09879078','122334112',0,63,NULL,'ENFP'),(38,_binary '\0',_binary '\0','대전시','woman',_binary '\0','윤영훈','귤귤','00000000000',0,65,NULL,'ESFP'),(45,_binary '\0',_binary '\0','qweqwe','man',_binary '\0','qweqwe','qweqwe','qwe',0,66,NULL,'ESTJ'),(46,_binary '\0',_binary '\0','','man',_binary '\0','','','',0,67,NULL,''),(47,_binary '\0',_binary '\0','','man',_binary '\0','','','',0,68,NULL,''),(48,_binary '\0',_binary '\0','','',_binary '\0','','','',0,69,NULL,''),(49,_binary '\0',_binary '\0','제주도','man',_binary '\0','강프로','강프로','0101010101',0,70,NULL,'INFJ'),(50,_binary '\0',_binary '\0','대전','man',_binary '\0','','코치입니다','01012345678',0,72,NULL,'ENTJ'),(51,_binary '\0',_binary '\0','대전광역시','man',_binary '\0','','산업스파이','01099619879',0,73,NULL,'ENTP'),(52,_binary '\0',_binary '\0','대전','woman',_binary '\0','','비밀비밀','00001111',0,74,NULL,'ISTP'),(53,_binary '\0',_binary '\0','','',_binary '\0','','','',0,75,NULL,''),(54,_binary '\0',_binary '\0','','',_binary '\0','','','',0,76,NULL,''),(55,_binary '\0',_binary '\0','대전','man',_binary '\0','','은밀하게위대하게','000-1111-11',0,77,NULL,'ISFP'),(56,_binary '\0',_binary '\0','대전','man',_binary '\0','김찬일','kci8311','',0,78,NULL,'INFP'),(57,_binary '\0',_binary '\0','','',_binary '\0','','','',0,79,NULL,''),(58,_binary '\0',_binary '\0','','woman',_binary '\0','ggg','agagag','',0,80,NULL,'INFP'),(59,_binary '\0',_binary '\0','zzz','man',_binary '\0','','zz','zzz',0,81,NULL,'ESTP'),(60,_binary '\0',_binary '\0','대전','man',_binary '\0','서준희','서준희','00000000000',0,82,NULL,'ENTP'),(61,_binary '\0',_binary '\0','대전','man',_binary '\0','김진산','잠만보','01025514963',0,83,NULL,'ESTP'),(62,_binary '\0',_binary '\0','대전','woman',_binary '\0','유영진','고독한늑대','01011112222',0,84,NULL,'ESFP'),(63,_binary '\0',_binary '\0','비밀','woman',_binary '\0','','하이','1234',0,85,NULL,'INFP');
/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_interest`
--

DROP TABLE IF EXISTS `user_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_interest` (
  `user_interest_id` int NOT NULL AUTO_INCREMENT,
  `interest_name` varchar(50) DEFAULT NULL,
  `user_detail_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_interest_id`),
  KEY `FKe4c4xqp0njt5j9o4lp1a7kgak` (`interest_name`),
  KEY `FKa1iasifvvbww0f0kd9alm4uw0` (`user_detail_id`),
  CONSTRAINT `FKa1iasifvvbww0f0kd9alm4uw0` FOREIGN KEY (`user_detail_id`) REFERENCES `user_detail` (`user_detail_id`),
  CONSTRAINT `FKe4c4xqp0njt5j9o4lp1a7kgak` FOREIGN KEY (`interest_name`) REFERENCES `interest` (`interest_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_interest`
--

LOCK TABLES `user_interest` WRITE;
/*!40000 ALTER TABLE `user_interest` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_interest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-17 23:22:42
