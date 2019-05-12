-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.36-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for social_community
CREATE DATABASE IF NOT EXISTS `social_community` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `social_community`;

-- Dumping structure for table social_community.attachment
CREATE TABLE IF NOT EXISTS `attachment` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255),
  `createdOn` datetime ,
  `displayName` varchar(255) ,
  `path` varchar(255) ,
  `type` varchar(255) ,
  `updatedBy` varchar(255) ,
  `updatedOn` datetime ,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.attachment: 0 rows
DELETE FROM `attachment`;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;

-- Dumping structure for table social_community.attachments
CREATE TABLE IF NOT EXISTS `attachments` (
  `Status_id` bigint(20) NOT NULL,
  `attachments_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_b7xrrh8qp1uuf0k0b1oerdy0` (`attachments_id`),
  KEY `FKlrlnb2o55upycih5jhjb5jn94` (`Status_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.attachments: 0 rows
DELETE FROM `attachments`;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;

-- Dumping structure for table social_community.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.hibernate_sequence: 2 rows
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(3),
	(3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table social_community.location
CREATE TABLE IF NOT EXISTS `location` (
  `id` bigint(20) NOT NULL,
  `locationName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.location: 12 rows
DELETE FROM `location`;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` (`id`, `locationName`) VALUES
	(1557605328554, 'Dhaka'),
	(1557605329009, 'Khulna'),
	(1557605329021, 'Chattogram'),
	(1557605329024, 'Jessore'),
# /*!40000 ALTER TABLE `location` ENABLE KEYS */;

-- Dumping structure for table social_community.tbl_role

CREATE TABLE IF NOT EXISTS `tbl_role` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) ,
  `user_id` bigint(20) ,
  PRIMARY KEY (`id`),
  KEY `FKicbe7uo6ce2eerth61k6i0tg7` (`user_id`)
);

-- Dumping data for table social_community.tbl_role: 1 rows
DELETE FROM `tbl_role`;
/*!40000 ALTER TABLE `tbl_role` DISABLE KEYS */;
INSERT INTO `tbl_role` (`id`, `role`, `user_id`) VALUES
	(1, 'ROLE_USER', NULL);
/*!40000 ALTER TABLE `tbl_role` ENABLE KEYS */;

-- Dumping structure for table social_community.tbl_status
CREATE TABLE IF NOT EXISTS `tbl_status` (
  `id` bigint(20) NOT NULL,
  `createdOn` datetime ,
  `status` text,
  `updatedOn` datetime ,
  `visibility` varchar(255) NOT NULL,
  `status_writer` bigint(20) ,
  `title` varchar(255) ,
  PRIMARY KEY (`id`),
  KEY `FK5qqf5yssd5ds2b6r7harpkffd` (`status_writer`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.tbl_status: 2 rows
DELETE FROM `tbl_status`;
/*!40000 ALTER TABLE `tbl_status` DISABLE KEYS */;
INSERT INTO `tbl_status` (`id`, `createdOn`, `status`, `updatedOn`, `visibility`, `status_writer`, `title`) VALUES
	(1557612216013, '2019-05-12 04:05:08', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', NULL, 'Public', 2, 'Post - 2'),
	(1557612633403, '2019-05-12 04:11:03', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '2019-05-12 06:58:40', 'Public', 2, 'Post - 3');
/*!40000 ALTER TABLE `tbl_status` ENABLE KEYS */;

-- Dumping structure for table social_community.tbl_user
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `id` bigint(20) NOT NULL,
  `createdOn` datetime ,
  `email` varchar(60) NOT NULL,
  `enabled` bit(1) ,
  `location_name` varchar(255) NOT NULL,
  `name` varchar(70) NOT NULL,
  `password` varchar(70) NOT NULL,
  `updatedOn` datetime ,
  `username` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.tbl_user: 1 rows
DELETE FROM `tbl_user`;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`id`, `createdOn`, `email`, `enabled`, `location_name`, `name`, `password`, `updatedOn`, `username`) VALUES
	(2, '2019-05-12 02:10:00', 'mainuls18@gmail.com', b'1', 'Dhaka', 'SYED MAINUL HASAN', '$2a$11$jn3DeJuh9yafzYBJRsuJpe1CzznyfZbDwBvmUbvRtZjgtmVkG70Ty', '2019-05-12 02:10:00', 'mainul35');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

-- Dumping structure for table social_community.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `User_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_amwlmdeik2qdnksxgd566knop` (`roles_id`),
  KEY `FK7qee55ltn0pyb14bfu01i8sc8` (`User_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.user_roles: 1 rows
DELETE FROM `user_roles`;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`User_id`, `roles_id`) VALUES
	(2, 1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;

-- Dumping structure for table social_community.visible_to_locations
CREATE TABLE IF NOT EXISTS `visible_to_locations` (
  `status_id` bigint(20) NOT NULL,
  `location_name` varchar(255) ,
  KEY `FK91ycnm08921dxpt89qf3dxf0v` (`status_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table social_community.visible_to_locations: 5 rows
DELETE FROM `visible_to_locations`;
/*!40000 ALTER TABLE `visible_to_locations` DISABLE KEYS */;
INSERT INTO `visible_to_locations` (`status_id`, `location_name`) VALUES
	(1557605715709, 'Dhaka'),
	(1557612216013, 'Dhaka'),
	(1557612216013, 'Khulna'),
	(1557612633403, 'Dhaka'),
	(1557612633403, 'Khulna');
/*!40000 ALTER TABLE `visible_to_locations` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
