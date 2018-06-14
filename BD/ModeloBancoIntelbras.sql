CREATE DATABASE  IF NOT EXISTS `intelbras` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `intelbras`;
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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(80) DEFAULT NULL,
  `cpfCliente` varchar(15) DEFAULT NULL,
  `rgCliente` varchar(14) DEFAULT NULL,
  `sexoCliente` varchar(10) DEFAULT NULL,
  `dataNascCliente` varchar(20) DEFAULT NULL,
  `bairroCliente` varchar(45) DEFAULT NULL,
  `cepCliente` varchar(10) DEFAULT NULL,
  `telefoneCliente` varchar(16) DEFAULT NULL,
  `enderecoCliente` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Wesley','122.653.746-44','mg-19.357.164','Masculino','22/01/1997','inatel','37540-000','(35)99905-6951','Av. Joao de Camargo'),(5,'Lucas S','12265374644','19357164','Masculino','12/05/1995','Barra','37730000','99887766','-'),(6,'Lucas Souza Rau','122.653.746-44','19.357.164','Masculino','12/05/1995','Barra','37730-000','(99) 88776-6   ','-');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `idFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` varchar(80) DEFAULT NULL,
  `cpfFuncionario` varchar(15) DEFAULT NULL,
  `rgFuncionario` varchar(14) DEFAULT NULL,
  `enderecoFuncionario` varchar(80) DEFAULT NULL,
  `cepFuncionario` varchar(10) DEFAULT NULL,
  `bairroFuncionario` varchar(45) DEFAULT NULL,
  `telefoneFuncionario` varchar(16) DEFAULT NULL,
  `sexoFuncionario` varchar(10) DEFAULT NULL,
  `salarioFuncionario` double DEFAULT NULL,
  `setorFuncionario` varchar(45) DEFAULT NULL,
  `comissaoFuncionario` float DEFAULT NULL,
  `nivelAcessoFuncionario` int(11) DEFAULT NULL,
  `rfidFuncionario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE KEY `rfidFuncionario_UNIQUE` (`rfidFuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'Wesley Reis da Silva','122.653.746-44','19.357.164','Av. João de Camargo','37540-000','Inatel','999056951','Masculino',550,'Pesquisa IoT',0,2,' B5 17 4C BE'),(3,'Gerente','123.456.789-  ','12.345.678','Rua','37234-2  ','','(35) 99887-766 ','Masculino',200,'Agricultura',20,1,'3'),(4,'Vendedor','123.123.123-12','12.123.123','Av Paulista','23234-234','','(23) 23423-4234','Masculino',1200,'PeD',0,0,'');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `idLogin` int(11) NOT NULL AUTO_INCREMENT,
  `nomeLogin` varchar(45) NOT NULL,
  `emailLogin` varchar(45) DEFAULT NULL,
  `senhaLogin` varchar(45) NOT NULL,
  `Funcionario_idFuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idLogin`),
  KEY `fk_Login_Funcionario1_idx` (`Funcionario_idFuncionario`),
  CONSTRAINT `fk_Login_Funcionario1` FOREIGN KEY (`Funcionario_idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'wesleyreis','wesleyreis@gec.inatel.br','1234',1),(2,'gerente','','1234',3),(3,'vendedor','','1234',4);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ponto`
--

DROP TABLE IF EXISTS `ponto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ponto` (
  `idPontos` int(11) NOT NULL AUTO_INCREMENT,
  `dataPonto` varchar(45) NOT NULL,
  `setorPonto` varchar(45) DEFAULT NULL,
  `Funcionario_idFuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idPontos`),
  KEY `fk_Ponto_Funcionario1_idx` (`Funcionario_idFuncionario`),
  CONSTRAINT `fk_Ponto_Funcionario1` FOREIGN KEY (`Funcionario_idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ponto`
--

LOCK TABLES `ponto` WRITE;
/*!40000 ALTER TABLE `ponto` DISABLE KEYS */;
INSERT INTO `ponto` VALUES (1,'13/03/2018','IOT',1),(2,'14/04/2018','IOT',1),(6,'23/4/2018 10:52:36','Pesquisa',1),(7,'23/4/2018 11:45:29','Pesquisa',1);
/*!40000 ALTER TABLE `ponto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `descricaoProduto` mediumtext,
  `marcaProduto` varchar(45) DEFAULT NULL,
  `valorProduto` float unsigned DEFAULT NULL,
  `modeloProduto` varchar(45) DEFAULT NULL,
  `obsProduto` mediumtext,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Amido de Milho','Santa Amalia',4.75,'Alimenticio','Bom para fazer mingau'),(2,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(3,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(4,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(5,'Amido de Milho','Maizena',3.75,'Alimenticio','Bom para fazer mingau'),(8,'Palha de Aço','Bom Brill',2.5,'-','1001 utilidades');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas` (
  `idVendas` int(11) NOT NULL AUTO_INCREMENT,
  `dataVenda` varchar(20) DEFAULT NULL,
  `valorTotalVenda` float DEFAULT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `Funcionario_idFuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idVendas`),
  KEY `fk_Vendas_Cliente1_idx` (`Cliente_idCliente`),
  KEY `fk_Vendas_Funcionario1_idx` (`Funcionario_idFuncionario`),
  CONSTRAINT `fk_Vendas_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Funcionario1` FOREIGN KEY (`Funcionario_idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (7,'12-06-2018',3.75,6,1);
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas_has_produto`
--

DROP TABLE IF EXISTS `vendas_has_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas_has_produto` (
  `Vendas_idVendas` int(11) NOT NULL,
  `Produto_idProduto` int(11) NOT NULL,
  PRIMARY KEY (`Vendas_idVendas`,`Produto_idProduto`),
  KEY `fk_Vendas_has_Produto_Produto1_idx` (`Produto_idProduto`),
  KEY `fk_Vendas_has_Produto_Vendas_idx` (`Vendas_idVendas`),
  CONSTRAINT `fk_Vendas_has_Produto_Produto1` FOREIGN KEY (`Produto_idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Vendas_has_Produto_Vendas` FOREIGN KEY (`Vendas_idVendas`) REFERENCES `vendas` (`idVendas`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas_has_produto`
--

LOCK TABLES `vendas_has_produto` WRITE;
/*!40000 ALTER TABLE `vendas_has_produto` DISABLE KEYS */;
INSERT INTO `vendas_has_produto` VALUES (7,4);
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

-- Dump completed on 2018-06-12 21:14:16
