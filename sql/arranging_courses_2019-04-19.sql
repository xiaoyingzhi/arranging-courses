# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.25)
# Database: arranging_courses
# Generation Time: 2019-04-19 15:47:23 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table arrange
# ------------------------------------------------------------

DROP TABLE IF EXISTS `arrange`;

CREATE TABLE `arrange` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `course_id` int(10) NOT NULL,
  `class_id` int(10) NOT NULL,
  `room_id` int(10) NOT NULL,
  `techer_id` int(20) NOT NULL,
  `seme_id` int(20) NOT NULL,
  `course_name` varchar(20) DEFAULT '',
  `class_name` varchar(20) DEFAULT NULL,
  `room_name` varchar(20) DEFAULT NULL,
  `techer_name` varchar(20) DEFAULT NULL,
  `star_week` int(11) DEFAULT NULL,
  `end_week` int(11) DEFAULT NULL,
  `srd` int(10) DEFAULT '0',
  `statu` int(2) DEFAULT '0' COMMENT '标志',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table classes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `classes`;

CREATE TABLE `classes` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(20) NOT NULL DEFAULT '',
  `class_number` varchar(20) NOT NULL DEFAULT '',
  `day_limit` int(10) DEFAULT '8',
  `statu` int(10) DEFAULT '0',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table classroom
# ------------------------------------------------------------

DROP TABLE IF EXISTS `classroom`;

CREATE TABLE `classroom` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(20) NOT NULL DEFAULT '',
  `room_space` int(4) NOT NULL,
  `room_layer` int(10) NOT NULL,
  `room_sign` int(11) DEFAULT '0',
  `statu` int(10) DEFAULT '0',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table college
# ------------------------------------------------------------

DROP TABLE IF EXISTS `college`;

CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(20) NOT NULL DEFAULT '',
  `college_describe` int(200) NOT NULL,
  `statu` int(10) DEFAULT '0',
  `mark` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) NOT NULL DEFAULT '',
  `course_time` int(2) DEFAULT NULL COMMENT '课时',
  `statu` int(10) DEFAULT '0',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table semester
# ------------------------------------------------------------

DROP TABLE IF EXISTS `semester`;

CREATE TABLE `semester` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(20) NOT NULL DEFAULT '',
  `semester` varchar(20) NOT NULL DEFAULT '',
  `week_count` int(10) NOT NULL,
  `statu` int(10) DEFAULT '0',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table speciality
# ------------------------------------------------------------

DROP TABLE IF EXISTS `speciality`;

CREATE TABLE `speciality` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spe_name` varchar(20) NOT NULL DEFAULT '',
  `spe_describe` varchar(200) NOT NULL DEFAULT '',
  `statu` int(10) DEFAULT '0',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table teacher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(20) NOT NULL DEFAULT '',
  `teacher_phone` varchar(15) DEFAULT NULL,
  `count_limit` int(10) DEFAULT '20',
  `statu` int(10) DEFAULT '0',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table teaching
# ------------------------------------------------------------

DROP TABLE IF EXISTS `teaching`;

CREATE TABLE `teaching` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `class_id` int(10) DEFAULT NULL,
  `teacher_id` int(10) DEFAULT NULL,
  `sem_id` int(10) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `techer_name` varchar(20) DEFAULT NULL,
  `sign` varchar(20) DEFAULT NULL,
  `statu` int(10) DEFAULT NULL,
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(10) NOT NULL DEFAULT '普通用户',
  `statu` int(11) DEFAULT '0',
  `mark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `username`, `password`, `role`, `statu`, `mark`)
VALUES
	(1,'kwk','123','超级管理员',0,'无');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
