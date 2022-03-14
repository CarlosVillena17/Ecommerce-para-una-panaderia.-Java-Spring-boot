CREATE DATABASE  IF NOT EXISTS `pruebal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pruebal`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: pruebal
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'panaderia'),(2,'pasteleria'),(3,'confiteria'),(4,'abarrotes'),(5,'bocaditos'),(6,'frutas'),(7,'limpieza'),(8,'verduras');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `idcompras` int NOT NULL AUTO_INCREMENT,
  `idcliente` int DEFAULT NULL,
  `idpago` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `monto` double DEFAULT NULL,
  PRIMARY KEY (`idcompras`),
  KEY `fk_compras_cliente_idx` (`idcliente`),
  KEY `fk_pago_compras_idx` (`idpago`),
  CONSTRAINT `fk_compras_cliente` FOREIGN KEY (`idcliente`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pago_compras` FOREIGN KEY (`idpago`) REFERENCES `pago` (`idpago`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (3,NULL,NULL,NULL,12.5),(10,NULL,1,'2022-01-20',396),(11,NULL,2,'2022-01-20',162.18),(12,NULL,4,'2022-01-21',150),(13,NULL,5,'2022-01-21',209.6),(14,NULL,6,'2022-01-21',2.8),(15,NULL,7,'2022-01-21',103.76),(16,NULL,10,'2022-01-23',2.5),(17,NULL,11,'2022-01-23',10),(18,NULL,12,'2022-01-23',11.2),(19,NULL,17,'2022-01-23',12.5),(20,NULL,18,'2022-01-23',2.8),(21,1,19,'2022-01-23',11.2),(22,1,20,'2022-01-23',390.4),(23,1,21,'2022-01-23',70),(24,2,22,'2022-01-27',70),(25,2,23,'2022-01-27',70),(26,1,24,'2022-01-27',110),(27,1,25,'2022-01-28',45),(28,13,26,'2022-01-29',239.76);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compras`
--

DROP TABLE IF EXISTS `detalle_compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_compras` (
  `iddetalle` int NOT NULL AUTO_INCREMENT,
  `idproducto` int DEFAULT NULL,
  `idcompras` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  PRIMARY KEY (`iddetalle`),
  KEY `fk_detalle_compras_idx` (`idcompras`),
  KEY `fk_producto_detalle_idx` (`idproducto`),
  CONSTRAINT `fk_detalle_compras` FOREIGN KEY (`idcompras`) REFERENCES `compras` (`idcompras`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_producto_detalle` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`idproductos`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compras`
--

LOCK TABLES `detalle_compras` WRITE;
/*!40000 ALTER TABLE `detalle_compras` DISABLE KEYS */;
INSERT INTO `detalle_compras` VALUES (1,NULL,NULL,5,2.5),(16,20,NULL,5,35.2),(17,22,NULL,4,50),(18,3,NULL,4,2.2),(19,1,NULL,4,2.8),(20,21,11,4,39.92),(21,6,11,1,2.5),(22,22,12,3,50),(23,2,13,4,2.5),(24,21,13,5,39.92),(25,1,14,1,2.8),(26,10,15,5,2.8),(27,21,15,3,39.92),(28,2,16,1,2.5),(29,2,17,4,2.5),(30,1,18,4,2.8),(31,2,19,5,2.5),(32,1,20,1,2.8),(33,1,21,4,2.8),(34,24,22,4,25),(35,25,22,4,30),(36,27,22,4,42.6),(37,49,23,5,20),(38,24,24,4,25),(39,24,25,4,25),(40,24,26,4,25),(41,49,26,2,20),(42,24,27,3,25),(43,22,28,3,50),(44,21,28,3,39.92);
/*!40000 ALTER TABLE `detalle_compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `idpago` int NOT NULL AUTO_INCREMENT,
  `monto` double DEFAULT NULL,
  PRIMARY KEY (`idpago`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES (1,396),(2,162.18),(3,0),(4,150),(5,209.6),(6,2.8),(7,103.76),(8,0),(9,2.5),(10,2.5),(11,10),(12,11.2),(13,12.5),(14,12.5),(15,10),(16,10),(17,12.5),(18,2.8),(19,11.2),(20,390.4),(21,70),(22,70),(23,70),(24,110),(25,45),(26,239.76);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idproductos` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `idcategoria` int DEFAULT NULL,
  `foto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idproductos`),
  KEY `fk_categoria_idx` (`idcategoria`),
  CONSTRAINT `fk_categoria` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Pan Francés',4.9,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, manteca, sal, azúcar, mejorador y levadura.',1,'panes.jpg'),(2,'Pan Ciabatta',2.5,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, easy ciabatta y levadura.',1,'pan-ciabatta.jpg'),(3,'Pan Integral',2.2,'Harina integral, azúcar, sal, mejorador, manteca, levadura y agua.',1,'pan-integral.jpg'),(4,'Mini Cachitos',1.95,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, margarina, agua, huevo, azúcar, leche en polvo, levadura,',1,'mini-cachitos.jpg'),(5,'Pan Árabe',3.2,'Harina integral, mejorador, sal, levadura y agua.',1,'pan-arabe.jpg'),(6,'Pan de Yema',2.5,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, azúcar, margarina, huevo, mejorador, levadura, anís y ajonjolí.',1,'pan-yema.jpg'),(7,'Pan Carioca',2.5,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, manteca, sal, azúcar, mejorador y levadura.',1,'pan-carioca.jpg'),(8,'Pan Karamanduka',4.35,'Harina integral, mejorador, sal, levadura y agua',1,'pan-karamandunga.jpg'),(9,'Mini Croissant ',2.6,'Harina integral, mejorador, sal, levadura y agua',1,'pan-croissant.jpg'),(10,'Pan Hamburguesa',2.8,'Harina integral, mejorador, sal, levadura y agua',1,'pan-hamburguesa.jpg'),(11,'Pan Pizza',4.35,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, jamón pizza, queso edam, azúcar, manteca, huevo, sal,',1,'pan-pizza.jpg'),(12,'Pan Brioche',2.7,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, margarina, huevo, azúcar, leche en polvo, levadura, mejorador y sal.',1,'pan-brioche.jpg'),(13,'Pan de Centeno',5.2,'Harina integral centeno, agua y levadura.',1,'pan-centeno.jpg'),(14,'Croissant Clásico',2.9,'Harina integral centeno, agua y levadura.',1,'pan-croissant.jpg'),(15,'Pan Rústico',7.8,'Harina integral centeno, agua y levadura.',1,'pan-rustico.jpg'),(16,'Pan de la Casa',1.3,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, azúcar, manteca, leche en polvo, levadura, mejorador y sal.',1,'pan-casa.jpg'),(17,'Pan Hot Dog',2.5,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, azúcar, manteca, levadura, sal y mejorador.',1,'pan-hot-dog.jpg'),(18,'Pan Coliza',2.4,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, azúcar, manteca, levadura, mejorador y sal.',1,'pan-coliza.jpg'),(19,'Pan Baguette',3.6,'HARINA DE TRIGO FORTIFICADA PARA PERÚ SEGÚN DS012-2006-SA, agua, sal, azúcar, mejorador y levadura.',1,'pan-baguette.jpg'),(20,'Torta Tres Leches Maracuyá',35.2,NULL,2,'tres-leches.jpg'),(21,'Crema Volteada',39.92,NULL,2,'crema-volteada.jpg'),(22,'Torta Helada de Fresa Corazón',50,NULL,2,'torta-helada-corazon.jpg'),(23,'Torta Helada de Durazno',35,NULL,2,'torta-helada-durazno.jpg'),(24,'Torta Helada Arco Iris',40,'',2,'minikake.jpg'),(25,'Torta Candy Cake Petit',30,NULL,2,'candy-cake-petit.jpg'),(26,'Torta de Chantilly ',40,NULL,2,'torta-chantilly.jpg'),(27,'Torta Delicia de Fresas',42.6,'Bizcochuelo de vainilla embebido en Tres leches, compota de fresas y manjar, decorado con chantilly y comprota de fresa.',2,'torta-delicia.jpg'),(29,'Croissant Mixto de Jamón',10.5,'Contiene 1 unid, con jamón y queso y exquisita mezcla de sabores',3,'jamon.jpg'),(30,'Sandwich Panini de Jamón de Pavita',12.5,'Contiene 1 unid, con jamón y queso y exquisita mezcla de sabores',3,'sandwich-panini.jpg'),(31,'Empanada de Pollo De La Casa',5.5,'Rellena de pollo',3,'empanada.jpg'),(32,'Chocoteja rellena de mani',2.5,'Rellena de mani y manjarblanco',3,'chocoteja.jpg'),(33,'Arroz Extra Costeño Bolsa 5 Kg',25.3,'Arroz de 5Kg',4,'arrozcs.jpg'),(34,'Aceite Vegetal Primor Premium Botella 1 L',12.19,'Botella 1 L',4,'aceite-primor.jpg'),(35,'Lentejas Bebé Bolsa 500 g',6,'Bolsa de 500g',4,'lentejas-bebe.jpg'),(36,'Sal de Mesa Marina Emsal Bolsa 1 kg',1.5,'Bolsa 1 kg',4,'sal-mesa.jpg'),(37,'Spaguetti Don Vittorio Paquete 1 Kg',4.2,'Paquete 1 Kg',4,'fideo-donvictorio.jpg'),(38,'Arroz Extra Costeño Bolsa 750 g',4.1,'Bolsa 750 g',4,'arrozc.jpg'),(39,'Trozos de Atún Campomar en Aceite Vegetal',5.5,'Contenido de 170 gramos- Filete de atún- Conservado en aceite vegetal- Contiene Omega 3- Producto peruano- ver más',4,'atun-campomar.jpg'),(40,'Maíz Pop Corn Costeño Bolsa 500 g',4.5,'Bolsa 500 g',4,'maiz-pop.jpg'),(41,'Durazno En Mitades Arica Contenido 820 g',9.5,'Contenido 820 g',4,'durazno-arica.jpg'),(42,'Leche Evaporada Entera Gloria',3.5,' Lata 400 gr',4,'leche-gloria.jpg'),(43,' Pack de 6 Leche Gloria',25.5,'Lata 400 gr',4,'lata-leche-gloria.jpg'),(44,'Yogurt Parcialmente Descremado Piña Gloria',6.5,'Botella 1 kg',4,'yogurt.jpg'),(45,'Laive Vainilla Botella 1 Kg',6.8,'Botella 1 Kg',4,'yogurt-laive.jpg'),(46,'Bombones Ferrero Rocher',60,'Contenido 12 Unidades',5,'bonbones-ferrero.jpg'),(47,'Bombones Mixtura',30,'La Ibérica Caja 220 g',5,'mixtura.jpg'),(48,'Bombones de Chocolate Italian',100,'Caja 160 g',5,'bonbones-chocolate.jpg'),(49,'Bombones con Relleno de Crema',20,'I Love You Bon o Bon Lata 180 gr',5,'bon-bon.jpg'),(50,'Bombones de Chocolate con Crema de Maní ',35,'Princesa Caja 16 unid',5,'princesa.jpg'),(51,'Plátano de Seda ',4.99,'Tipo Exportación x kg',6,'platano.jpg'),(52,'Papaya x kg',7,'Papaya x kg',6,'papaya.jpg'),(53,'Naranja para Jugo',10,'Naranja para Jugo',6,'naranjas.jpg'),(54,'Mandarina Sin Pepa ',10.99,'Mandarina Sin Pepa La Pecosita x 2 kg',6,'mandarina.jpg'),(55,'Manzana',3.17,'Manzana Delicia Extra x kg',6,'manzana.jpg'),(56,'Melocotón Añawi x kg',12.3,'Melocotón Añawi x kg',6,'melocoton.jpg'),(57,'Pera',7.8,'Pera Packhams x kg',6,'pera.jpg'),(58,'Mango Kent',5,'Mango Kent xKg',6,'mango.jpg'),(59,'Granadilla',1,'Granadilla Extra x unid',6,'granadilla.jpg'),(60,'Uva Sultana Nacional x kg',12,'Uva Sultana Nacional x kg',6,'uva.jpg'),(61,'Mango Edward',8,'Mango Edwardx kg',6,'mando-edward.jpg'),(62,'Fideos Tornillo de Colores',2,'Fideos Tornillo de Colores Don Vittorio Bolsa 250 gr',4,'tornillocolores.jpg'),(63,'Filete de Atún',6.5,'Trozos de Atún Primor Lata 170 g',4,'atun.jpg'),(64,'Sillao Aji-No-Sillao ',4.5,'Sillao Aji-No-Sillao Botella 500 ml',4,'sillao.jpg'),(65,'Orégano Entero ',3.5,'Orégano Entero Max & Mix Sobre 10 g',4,'oregano.jpg'),(66,'Zanahoria x kg',4.51,'Zanahoria x kg',8,'zanahoria.jpg'),(67,'Tomate Italiano x kg',4.5,'1 Kg = 6 a 7 Unid aprox',8,'tomate.jpg'),(68,'Limón x kg',4.5,'Limón x kg',8,'limon.jpg'),(69,'Palta Fuerte Verde x kg',9.5,'Este producto no necesariamente se encuentra maduro.',8,'palta.jpg'),(70,'Choclo Serrano x unid',2.2,'- Producto en temporada baja',8,'choclo.jpg'),(71,'Papa Amarilla Tumbay Procesada x kg',5.5,'Enmallada',8,'papa.jpg'),(75,'Zapallo ',4.5,'Zapallo amarillo fresco',8,'zapallo.jpg'),(83,'MariLuz',1e16,'mi bebe',2,'f817ced839fb62c837862e681acf5dac.jpg');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'carlos','villena','carlos@gmail.com','123456789'),(2,'ricardo','cabrejos','ricardo@gmail.com','123456789'),(3,'maria','ccolcca','maria@gmail.com','123456789'),(4,'jose','martinez','jose@gmail.com','123454678'),(5,'jesus','trujillo','jesus@gmai..com','123456789'),(6,'zoe','cabrejos','zoe@gmail.com','123456789'),(7,'angel','peralta','peralta@gmai.coml','123456789'),(8,'kelly','martinez','kelly@gmail.com','123456789'),(9,'manuel ','rivadeneria','manuel@gmail.com','123456789'),(10,'yampier','cabrejos','yampier@gmail.com','123456789'),(11,'daniel','santos','daniel@gmail.com','123456789'),(12,'armando','espinoza','armando@gmail.com','123456789'),(13,'mariluz ','ccolcca','mariluz@gmail.com','123456789'),(14,'cachique','FALCON','cachique@gmail.com','933323597');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-14 15:38:28
