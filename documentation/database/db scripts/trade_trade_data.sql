-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: trade
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `trade_data`
--

DROP TABLE IF EXISTS `trade_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_data` (
  `id` int DEFAULT NULL,
  `instrument_id` text,
  `buyer` text,
  `confirmation_timestamp` text,
  `counter_party` text,
  `counter_party_fullname` text,
  `counter_party_institution` text,
  `creation_timestamp` text,
  `currency` text,
  `effective_date` text,
  `maturity_date` text,
  `notional_amount` int DEFAULT NULL,
  `party` text,
  `party_fullname` text,
  `party_institution` text,
  `seller` text,
  `status` text,
  `trade_date` text,
  `trade_id` int DEFAULT NULL,
  `trade_ref_num` text,
  `version` int DEFAULT NULL,
  `version_timestamp` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_data`
--

LOCK TABLES `trade_data` WRITE;
/*!40000 ALTER TABLE `trade_data` DISABLE KEYS */;
INSERT INTO `trade_data` VALUES (1,'Securities','SbiMum','05-09-2022 20:37','SbiMum','Sbi Mumbai','Sbi','05-09-2022 20:37','USD','05-10-2022','05-09-2025',130000,'HdfcDel','Hdfc Delhi','Hdfc','HdfcDel','Confirmed','05-09-2022',281,'HdfcDel00281',4,'05-09-2022 20:37'),(2,'Securities','SbiMum','05-09-2022 20:37','HdfcDel','Hdfc Delhi','Hdfc','05-09-2022 20:37','USD','05-10-2022','05-09-2025',1330000,'SbiMum','Sbi Mumbai','Sbi','HdfcDel','Confirmed','05-09-2022',1,'SbiMum001',3,'05-09-2022 20:37'),(7,'Securities','SbiMum','05-09-2022 20:37','SbiMum','Sbi Mumbai','Sbi','05-09-2022 20:37','USD','05-10-2022','05-09-2025',1330000,'HdfcDel','Hdfc Delhi','Hdfc','HdfcDel','Confirmed','05-09-2022',9,'HdfcDel009',2,'05-09-2022 20:37'),(8,'Securities','SbiMum','05-09-2022 20:37','HdfcDel','Hdfc Delhi','Hdfc','05-09-2022 20:37','INR','05-10-2022','05-09-2025',30000,'SbiMum','Sbi Mumbai','Sbi','HdfcDel','Unconfirmed','05-09-2022',989109,'SbiMum989109',1,'05-09-2022 20:37'),(11,'Securities','SbiMum','05-09-2022 20:37','HdfcDel','Hdfc Delhi','Hdfc','05-09-2022 20:37','INR','05-10-2022','05-09-2025',30000,'SbiMum','Sbi Mumbai','Sbi','HdfcDel','Unconfirmed','05-09-2022',23133,'SbiMum23133',1,'05-09-2022 20:37'),(13,'Securities','SbiMum','05-09-2022 20:37','SbiMum','Sbi Mumbai','Sbi','05-09-2022 20:37','USD','05-10-2022','05-09-2025',130000,'HdfcDel','Hdfc Delhi','Hdfc','HdfcDel','Unconfirmed','05-09-2022',2811,'HdfcDel002811',2,'05-09-2022 20:37'),(16,'Securities','SbiMum','08-09-2022 04:57','HdfcDel','Hdfc Delhi','Hdfc','05-09-2022 20:37','USD','05-10-2022','05-09-2025',130000,'SbiMum','Sbi Mumbai','Sbi','HdfcDel','Confirmed','05-09-2022',211,'SbiMum211',3,'08-09-2022 04:57'),(18,'Securities','SbiMum','08-09-2022 04:57','SbiMum','Sbi Mumbai','Sbi','05-09-2022 20:37','USD','05-10-2022','05-09-2025',130000,'HdfcDel','Hdfc Delhi','Hdfc','HdfcDel','Confirmed','05-09-2022',7,'HdfcDel007',3,'08-09-2022 04:57'),(19,'Securities','UbsOnt','08-09-2022 04:57','JpmorganNew','Jpmorgan Newyork','Jpmorgan','05-09-2022 20:37','USD','05-10-2022','05-09-2025',89000,'UbsOnt','Ubs Ontario','Ubs','JpmorganNew','Unconfirmed','05-09-2022',3,'UbsOnt003',1,'08-09-2022 04:57'),(20,'Securities','JpmorganNew','08-09-2022 04:57','UbsOnt','Ubs Ontario','Ubs','05-09-2022 20:37','USD','05-10-2022','05-09-2025',89000,'JpmorganNew','Jpmorgan Newyork','Jpmorgan','UbsOnt','Unconfirmed','05-09-2022',4,'JpmorganNew004',1,'08-09-2022 04:57'),(21,'Securities','JpmorganLon','08-09-2022 04:57','UbsLon','Ubs London','Ubs','05-09-2022 20:37','USD','05-10-2022','05-09-2025',89000,'JpmorganLon','Jpmorgan London','Jpmorgan','UbsLon','Unconfirmed','05-09-2022',5,'JpmorganLon005',1,'08-09-2022 04:57'),(22,'Securities','JpmorganInd','08-09-2022 04:57','UbsCan','Ubs Canada','Ubs','05-09-2022 20:37','USD','05-10-2022','05-09-2025',89000,'JpmorganInd','Jpmorgan India','Jpmorgan','UbsCan','Unconfirmed','05-09-2022',6,'JpmorganInd006',1,'08-09-2022 04:57'),(23,'Securities','SbiGur','05-09-2022 20:37','HdfcGur','Hdfc Gurgaon','Hdfc','05-09-2022 20:37','INR','05-10-2022','05-09-2025',30000,'SbiGur','Sbi Gurgaon','Sbi','HdfcGur','Unconfirmed','05-09-2022',989110,'SbiGur989110',1,'05-09-2022 20:37'),(24,'Securities','SbiGur','05-09-2022 20:37','HdfcGur','Hdfc Gurgaon','Hdfc','05-09-2022 20:37','USD','05-10-2022','05-09-2025',31000,'SbiGur','Sbi Gurgaon','Sbi','HdfcGur','Cancel','05-09-2022',989111,'SbiGur989111',2,'05-09-2022 20:37'),(25,'Securities','SbiGur','05-09-2022 20:37','HdfcGur','Hdfc Gurgaon','Hdfc','05-09-2022 20:37','USD','05-11-2022','05-10-2022',31000,'SbiGur','Sbi Gurgaon','Sbi','HdfcGur','Exit','05-09-2022',989112,'SbiGur989112',2,'05-09-2022 20:37'),(26,'Securities','BarclaysLon','05-09-2022 20:37','HdfcGur','Hdfc Gurgaon','Hdfc','05-09-2022 20:37','USD','05-11-2022','05-10-2023',31000,'BarclaysLon','Barclays London','Barclays','HdfcGur','Unconfirmed','05-09-2022',21,'BarclaysLon21',1,'05-09-2022 20:37'),(27,'Securities','BarclaysNew','05-09-2022 20:37','HdfcGur','Hdfc Gurgaon','Hdfc','05-09-2022 20:37','USD','05-11-2022','05-10-2023',31000,'BarclaysNew','Barclays Newyork','Barclays','HdfcGur','Unconfirmed','05-09-2022',22,'BarclaysLon22',1,'05-09-2022 20:37'),(28,'Securities','BarclaysCan','05-09-2022 20:37','HdfcGur','Hdfc Gurgaon','Hdfc','05-09-2022 20:37','USD','05-11-2022','05-10-2022',31000,'BarclaysCan','Barclays Canada','Barclays','HdfcGur','Exit','05-09-2022',23,'BarclaysLon23',1,'05-09-2022 20:37');
/*!40000 ALTER TABLE `trade_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-12 14:57:01
