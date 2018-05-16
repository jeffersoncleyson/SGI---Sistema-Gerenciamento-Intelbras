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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `fk_Login_Funcionario1` FOREIGN KEY (`Funcionario_idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `obsVenda` mediumtext,
  `Cliente_idCliente` int(11) NOT NULL,
  `Funcionario_idFuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idVendas`),
  KEY `fk_Vendas_Cliente1_idx` (`Cliente_idCliente`),
  KEY `fk_Vendas_Funcionario1_idx` (`Funcionario_idFuncionario`),
  CONSTRAINT `fk_Vendas_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Funcionario1` FOREIGN KEY (`Funcionario_idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `fk_Vendas_has_Produto_Produto1` FOREIGN KEY (`Produto_idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_has_Produto_Vendas` FOREIGN KEY (`Vendas_idVendas`) REFERENCES `vendas` (`idVendas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-16 15:19:03
