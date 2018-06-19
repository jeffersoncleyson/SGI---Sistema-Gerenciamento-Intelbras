-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: intelbras
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Wesley','122.653.746-44','mg-19.357.164','Masculino','22/01/1997','inatel','37540-000','(35)99905-6951','Av. Joao de Camargo'),(5,'Lucas S','12265374644','19357164','Masculino','12/05/1995','Barra','37730000','99887766','-'),(6,'Lucas Souza R','12265374644','19357164','Masculino','12/05/1995','Barra','37730000','99887766','-');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'Wesley Reis da Silva','122.653.746-44','19.357.164','Av. João de Camargo','37540-000','Inatel','999056951','Masculino',550,'Pesquisa IoT',0,2,' B5 17 4C BE'),(2,'Jefferson','123123123','19453','Inatel','37540000',NULL,'99887766','Masculino',200,'Geral',100,0,'2'),(3,'Lucas','123456789','12345678','Rua','372342',NULL,'35 99887766','Masculino',200,'Agricultura',20,1,'3');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'wesleyreis','wesleyreis@gec.inatel.br','1234',1),(2,'emerson','emerson@gec.inatel.br','senha',1),(3,'emerson','emerson2@gec.inatel.br','senha123',3),(4,'emerson','emerson@gec.inatel.br','senha',1),(5,'emerson','emerson@gec.inatel.br','senha',1),(6,'emerson','emerson@gec.inatel.br','senha',1);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ponto`
--

LOCK TABLES `ponto` WRITE;
/*!40000 ALTER TABLE `ponto` DISABLE KEYS */;
INSERT INTO `ponto` VALUES (1,'13/03/2018','IOT',1),(2,'14/04/2018','IOT',1),(6,'23/4/2018 10:52:36','Pesquisa',1),(7,'23/4/2018 11:45:29','Pesquisa',1);
/*!40000 ALTER TABLE `ponto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Amido de Milho','Santa Amalia',4.75,'Alimenticio','Bom para fazer mingau'),(2,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(3,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(4,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(5,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(6,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(7,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vendas_has_produto`
--

LOCK TABLES `vendas_has_produto` WRITE;
/*!40000 ALTER TABLE `vendas_has_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas_has_produto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-16 15:24:08
