CREATE DATABASE  IF NOT EXISTS `hospitalsystem` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `hospitalsystem`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitalsystem
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
-- Table structure for table `drugperforms`
--

DROP TABLE IF EXISTS `drugperforms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `drugperforms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_drugperforms_drugs_id` (`drug_id`),
  KEY `FK_drugperforms_staff_id` (`staff_id`),
  CONSTRAINT `FK_drugperforms_drugs_id` FOREIGN KEY (`drug_id`) REFERENCES `drugs` (`id`),
  CONSTRAINT `FK_drugperforms_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugperforms`
--

LOCK TABLES `drugperforms` WRITE;
/*!40000 ALTER TABLE `drugperforms` DISABLE KEYS */;
/*!40000 ALTER TABLE `drugperforms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugs`
--

DROP TABLE IF EXISTS `drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `drugs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `drug` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_drugs_patients_id` (`patient_id`),
  KEY `FK_drugs_staff_id` (`doctor_id`),
  CONSTRAINT `FK_drugs_patients_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`),
  CONSTRAINT `FK_drugs_staff_id` FOREIGN KEY (`doctor_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugs`
--

LOCK TABLES `drugs` WRITE;
/*!40000 ALTER TABLE `drugs` DISABLE KEYS */;
INSERT INTO `drugs` VALUES (1,'аспирин',1,1),(2,'проповерин',2,2);
/*!40000 ALTER TABLE `drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operationperformers`
--

DROP TABLE IF EXISTS `operationperformers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operationperformers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_operationperformers_operations_id` (`operation_id`),
  KEY `FK_operationperformers_staff_id` (`doctor_id`),
  CONSTRAINT `FK_operationperformers_operations_id` FOREIGN KEY (`operation_id`) REFERENCES `operations` (`id`),
  CONSTRAINT `FK_operationperformers_staff_id` FOREIGN KEY (`doctor_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operationperformers`
--

LOCK TABLES `operationperformers` WRITE;
/*!40000 ALTER TABLE `operationperformers` DISABLE KEYS */;
/*!40000 ALTER TABLE `operationperformers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operations`
--

DROP TABLE IF EXISTS `operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_operations_patients_id` (`patient_id`),
  KEY `FK_operations_staff_id` (`doctor_id`),
  CONSTRAINT `FK_operations_patients_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`),
  CONSTRAINT `FK_operations_staff_id` FOREIGN KEY (`doctor_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operations`
--

LOCK TABLES `operations` WRITE;
/*!40000 ALTER TABLE `operations` DISABLE KEYS */;
/*!40000 ALTER TABLE `operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci,
  `surname` varchar(45) COLLATE utf8_unicode_ci,
  `birthday` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8_unicode_ci,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startdiagnosis` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enddiagnosis` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `come` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `out` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Всеволод','Держинский','1987-12-12','+375295855588','derzh87','воспаление легких','пневмония','2018-10-09','2018-10-10'),(2,'UpdatedName','Пысин','1967','+375255681547','нет','ЗЧМТ','рак мозга','2018-10-09','2018-10-15'),(3,'name','surname','birthday','phone','email','startdiagnosis','enddiagnosis','come','out'),(6,'хер','dfdf','дата рождения','телефон','e-mail','первичный диагноз','конечный диагноз','прибыл','убыл'),(7,'имя','фамилия','дата рождения','телефон','e-mail','первичный диагноз','конечный диагноз','прибыл','убыл'),(8,'имя','фамилия','дата рождения','телефон','e-mail','первичный диагноз','конечный диагноз','прибыл','убыл'),(9,'имя','фамилия','дата рождения','телефон','e-mail','первичный диагноз','конечный диагноз','прибыл','убыл'),(10,'имя','фамилия','дата рождения','телефон','e-mail','первичный диагноз','конечный диагноз','прибыл','убыл'),(12,'имя','фамилия','дата рождения','телефон','e-mail','первичный диагноз','конечный диагноз','прибыл','убыл'),(19,'Генадий','Пысин','1967','+375255681547','нет','ЗЧМТ','рак мозга','2018-10-09','2018-10-15'),(20,'UpdatedName','Пысин','1967','+375255681547','нет','ЗЧМТ','рак мозга','2018-10-09','2018-10-15');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescriptions`
--

DROP TABLE IF EXISTS `prescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prescriptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_id` int(11) DEFAULT NULL,
  `procedure_id` int(11) DEFAULT NULL,
  `operation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_prescriptions_operations_id` (`operation_id`),
  KEY `FK_prescriptions_procedures_id` (`procedure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescriptions`
--

LOCK TABLES `prescriptions` WRITE;
/*!40000 ALTER TABLE `prescriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedureperformers`
--

DROP TABLE IF EXISTS `procedureperformers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `procedureperformers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `procedure_id` int(11) NOT NULL,
  `stuff_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_procedureperformers_procedures_id` (`procedure_id`),
  KEY `FK_procedureperformers_staff_id` (`stuff_id`),
  CONSTRAINT `FK_procedureperformers_procedures_id` FOREIGN KEY (`procedure_id`) REFERENCES `procedures` (`id`),
  CONSTRAINT `FK_procedureperformers_staff_id` FOREIGN KEY (`stuff_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedureperformers`
--

LOCK TABLES `procedureperformers` WRITE;
/*!40000 ALTER TABLE `procedureperformers` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedureperformers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures`
--

DROP TABLE IF EXISTS `procedures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `procedures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `procedure` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_procedures_patients_id` (`patient_id`),
  KEY `FK_procedures_staff_id` (`doctor_id`),
  CONSTRAINT `FK_procedures_patients_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`),
  CONSTRAINT `FK_procedures_staff_id` FOREIGN KEY (`doctor_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures`
--

LOCK TABLES `procedures` WRITE;
/*!40000 ALTER TABLE `procedures` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quals`
--

DROP TABLE IF EXISTS `quals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `quals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quals`
--

LOCK TABLES `quals` WRITE;
/*!40000 ALTER TABLE `quals` DISABLE KEYS */;
INSERT INTO `quals` VALUES (1,'Врач'),(2,'Медсестра');
/*!40000 ALTER TABLE `quals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qual_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_staff_quals_id` (`qual_id`),
  CONSTRAINT `FK_staff_quals_id` FOREIGN KEY (`qual_id`) REFERENCES `quals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Генадий','Чебурашкин','+375298957489','gen.cheb@gmail.com',1),(2,'Анастасия','Чернова','+375255874789','black_cat@gmail.com',1);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-18  3:22:22
