/*
SQLyog Community v13.1.8 (64 bit)
MySQL - 10.4.24-MariaDB : Database - atletskiklub
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`atletskiklub` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `atletskiklub`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `AdministratorID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Ime` varchar(30) NOT NULL,
  `Prezime` varchar(30) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `administrator` */

insert  into `administrator`(`AdministratorID`,`Ime`,`Prezime`,`Username`,`Password`) values 
(1,'Mihailo','Sljuka','mihailo','mihailo123'),
(2,'Petar','Lazic','petar','petar123');

/*Table structure for table `clan` */

DROP TABLE IF EXISTS `clan`;

CREATE TABLE `clan` (
  `ClanID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `ImeClana` varchar(30) NOT NULL,
  `PrezimeClana` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Telefon` varchar(50) NOT NULL,
  `KategorijaID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`ClanID`),
  KEY `fk_kategorija_id` (`KategorijaID`),
  CONSTRAINT `fk_kategorija_id` FOREIGN KEY (`KategorijaID`) REFERENCES `kategorija` (`KategorijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `clan` */

insert  into `clan`(`ClanID`,`ImeClana`,`PrezimeClana`,`Email`,`Telefon`,`KategorijaID`) values 
(1,'Filip','Drespic','filip@gmail.com','0631231234',1),
(2,'Matija','Dragutinovic','matija@gmail.com','0654645434',1),
(3,'Danilo','Djurovic','danilo@gmail.com','0641235153',2),
(4,'Zarko','Colic','zarko@gmail.com','0634534536',2),
(5,'Uros','Soljaga','uros@gmail.com','0641235135',3),
(6,'Ivana','Djuric','ivana@gmail.com','0623162371',3);

/*Table structure for table `kategorija` */

DROP TABLE IF EXISTS `kategorija`;

CREATE TABLE `kategorija` (
  `KategorijaID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `NazivKategorije` varchar(30) NOT NULL,
  `OpisKategorije` varchar(30) NOT NULL,
  PRIMARY KEY (`KategorijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kategorija` */

insert  into `kategorija`(`KategorijaID`,`NazivKategorije`,`OpisKategorije`) values 
(1,'Junior','Pocetnici i deca do 12 godina.'),
(2,'Medior','Deca uzrasta od 12 do 18 godin'),
(3,'Senior','Iskusni clanovi stariji od 18 ');

/*Table structure for table `sala` */

DROP TABLE IF EXISTS `sala`;

CREATE TABLE `sala` (
  `SalaID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `NazivSale` varchar(30) NOT NULL,
  PRIMARY KEY (`SalaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sala` */

insert  into `sala`(`SalaID`,`NazivSale`) values 
(1,'Dragutin Topic'),
(2,'Spanovic'),
(3,'Nikcevic');

/*Table structure for table `stavkatermina` */

DROP TABLE IF EXISTS `stavkatermina`;

CREATE TABLE `stavkatermina` (
  `TerminID` bigint(10) unsigned NOT NULL,
  `RbStavke` int(7) NOT NULL,
  `Napomena` varchar(70) NOT NULL,
  `ClanID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`TerminID`,`RbStavke`),
  KEY `fk_clan_id2` (`ClanID`),
  CONSTRAINT `fk_clan_id2` FOREIGN KEY (`ClanID`) REFERENCES `clan` (`ClanID`),
  CONSTRAINT `fk_termin_id` FOREIGN KEY (`TerminID`) REFERENCES `termin` (`TerminID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkatermina` */

insert  into `stavkatermina`(`TerminID`,`RbStavke`,`Napomena`,`ClanID`) values 
(1,1,'/',1),
(1,2,'/',2);

/*Table structure for table `termin` */

DROP TABLE IF EXISTS `termin`;

CREATE TABLE `termin` (
  `TerminID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `NazivTermina` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `DatumVreme` datetime NOT NULL,
  `OpisTermina` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `MaxClanova` int(7) NOT NULL,
  `SalaID` bigint(10) unsigned NOT NULL,
  `AdministratorID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`TerminID`),
  KEY `fk_sala_id` (`SalaID`),
  KEY `fk_admin_id` (`AdministratorID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`),
  CONSTRAINT `fk_sala_id` FOREIGN KEY (`SalaID`) REFERENCES `sala` (`SalaID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `termin` */

insert  into `termin`(`TerminID`,`NazivTermina`,`DatumVreme`,`OpisTermina`,`MaxClanova`,`SalaID`,`AdministratorID`) values 
(1,'Vecernji termin','2022-10-15 20:00:00','Priprema za takmicenje.',5,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
