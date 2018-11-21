-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: dbsisgs
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agenda` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `hora` time NOT NULL,
  `servico` int(3) DEFAULT NULL,
  `cliente` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cliente` (`cliente`),
  KEY `fk_servico` (`servico`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`cliente`) REFERENCES `pessoa` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_servico` FOREIGN KEY (`servico`) REFERENCES `produto` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda`
--

LOCK TABLES `agenda` WRITE;
/*!40000 ALTER TABLE `agenda` DISABLE KEYS */;
INSERT INTO `agenda` VALUES (9,'2018-11-22','21:00:00',32,'30790641860'),(14,'2018-11-06','19:00:00',33,'28274141806'),(17,'2018-11-01','18:00:00',32,'30790641860'),(19,'2018-11-22','12:00:00',32,'30790641860'),(20,'2018-11-15','18:00:00',32,'30790641860');
/*!40000 ALTER TABLE `agenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'root','root');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pessoa` (
  `cpf` varchar(14) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `nome` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sexo` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `datanasc` date NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `celular` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES ('28274141806','KARINA MACHADO RAMOS','F','1979-04-22','KARINASC.MACHADO@GMAIL.COM','16997823270'),('30790641860','LEANDRO CANALI RAMOS','M','1982-06-07','LEANDROSC.RAMOS@GMAIL.COM','16997037799'),('54125263858','NICOLE MACHADO RAMOS','F','2012-10-14','NICOLESC.RAMOS@GMAI.COM','16987989878');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `descricao` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `estoque` int(3) DEFAULT '0',
  `categoria` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (32,'CORTE MASCULINO',35.00,'CORTE MASCULINO',0,'SERVIÇO'),(33,'CORTE FEMININO',50.00,'CORTE FEMININO',0,'SERVIÇO'),(34,'XAMPOO FEMININO',65.00,'XAMPOO FEMININO',15,'PRODUTO'),(35,'XAMPOO MASCULINO',30.00,'XAMPOO MASCULINO',20,'PRODUTO'),(37,'ESCOVA',25.00,'ESCOVA',0,'SERVIÇO');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendas` (
  `codigo` int(5) NOT NULL AUTO_INCREMENT,
  `cliente` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `observacao` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `pagamento` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `valorTotal` double NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_cliente_venda` (`cliente`),
  CONSTRAINT `fk_cliente_venda` FOREIGN KEY (`cliente`) REFERENCES `pessoa` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (2,'28274141806','ASDFASDF','DINHEIRO',333),(3,'30790641860','TESTE','CARTÃO DE CRÉDITO',200),(4,'28274141806','ADFSADSF','DINHEIRO',1234),(5,'28274141806','ASDF','CARTÃO DE CRÉDITO',333),(6,'28274141806','SDF','DINHEIRO',3333),(7,'28274141806','ADF','DINHEIRO',343),(8,'28274141806','ASDFASD','DINHEIRO',33),(9,'28274141806','2','DINHEIRO',22),(10,'28274141806','QWASDFASDF','DINHEIRO',77),(11,'28274141806','ASDFASDF','DINHEIRO',33),(12,'28274141806','ASDF','DINHEIRO',3),(13,'28274141806','ASDF','DINHEIRO',33),(14,'28274141806','ASDF','DINHEIRO',3),(15,'28274141806','ASDF','DINHEIRO',33),(16,'28274141806','ASDFASDF','DINHEIRO',33),(17,'28274141806','ASDF','DINHEIRO',33),(18,'54125263858','VENDA PARA NICOLE','DINHEIRO',34),(19,'30790641860','ASDASDF','CARTÃO DE CRÉDITO',56),(20,'28274141806','ASDFASDF','DINHEIRO',130),(21,'28274141806','','DINHEIRO',130),(22,'28274141806','ASDFADSF','DINHEIRO',75);
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendasprodutos`
--

DROP TABLE IF EXISTS `vendasprodutos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendasprodutos` (
  `codigoVenda` int(5) NOT NULL,
  `codigoProduto` int(11) NOT NULL,
  `qtde` int(5) NOT NULL,
  PRIMARY KEY (`codigoVenda`,`codigoProduto`),
  KEY `fk_codproduto_idx` (`codigoProduto`),
  CONSTRAINT `fk_codproduto` FOREIGN KEY (`codigoProduto`) REFERENCES `produto` (`codigo`),
  CONSTRAINT `fk_codvenda` FOREIGN KEY (`codigoVenda`) REFERENCES `vendas` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendasprodutos`
--

LOCK TABLES `vendasprodutos` WRITE;
/*!40000 ALTER TABLE `vendasprodutos` DISABLE KEYS */;
INSERT INTO `vendasprodutos` VALUES (18,33,1),(18,34,2),(19,34,2),(19,35,3),(20,32,2),(20,34,2),(21,34,2),(22,32,1),(22,34,2),(22,37,3);
/*!40000 ALTER TABLE `vendasprodutos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-21 17:36:09
