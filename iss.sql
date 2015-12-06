--
-- Host: localhost    Database: iss
-- ------------------------------------------------------

--
-- Table structure for table `Content`
--

DROP TABLE IF EXISTS `Content`;
CREATE TABLE `Content` (
  `contentId` int(11) NOT NULL AUTO_INCREMENT,
  `contentAddress` varchar(255) DEFAULT NULL,
  `contentType` int(11) DEFAULT NULL,
  `contentTitle` varchar(255) DEFAULT NULL,
  `contentValidFrom` datetime DEFAULT NULL,
  `contentValidTo` datetime DEFAULT NULL,
  `contentUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`contentId`)
) AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `Screen`
--

DROP TABLE IF EXISTS `Screen`;
CREATE TABLE `Screen` (
  `screenId` int(11) NOT NULL AUTO_INCREMENT,
  `screenDescription` varchar(255) DEFAULT NULL,
  `screenFormat` varchar(255) DEFAULT NULL,
  `screenName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`screenId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Table structure for table `Settings`
--

DROP TABLE IF EXISTS `Settings`;
CREATE TABLE `Settings` (
  `settingKey` varchar(255) NOT NULL,
  `settingType` int(11) DEFAULT NULL,
  `settingValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`settingKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
CREATE TABLE `Tag` (
  `tagId` int(11) NOT NULL AUTO_INCREMENT,
  `tagDescription` varchar(255) DEFAULT NULL,
  `tagName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `Type`
--

DROP TABLE IF EXISTS `Type`;
CREATE TABLE `Type` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userLogin` varchar(255) DEFAULT NULL,
  `userPassword` varchar(255) DEFAULT NULL,
  `userRoleId` int(11) DEFAULT NULL,
  `userTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


--
-- Table structure for table `contenttag`
--

DROP TABLE IF EXISTS `contenttag`;
CREATE TABLE `contenttag` (
  `contentId` int(11) NOT NULL,
  `tagId` int(11) NOT NULL,
  PRIMARY KEY (`contentId`,`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `screentag`
--

DROP TABLE IF EXISTS `screentag`;
CREATE TABLE `screentag` (
  `screenId` int(11) NOT NULL,
  `tagId` int(11) NOT NULL,
  PRIMARY KEY (`screenId`,`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
