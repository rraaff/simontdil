CREATE  TABLE `COUNTRY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `flag` MEDIUMBLOB NULL ,
  `host` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `SIGNATURE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NOT NULL ,
  `userId` INT NOT NULL ,
  `image` MEDIUMBLOB NULL ,
  `video` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `SYSTEMUSER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(4000) NULL ,
  `name` VARCHAR(150) NULL ,
  `email` VARCHAR(100) NULL ,
  `job` VARCHAR(100) NULL ,
  `countryDesc` VARCHAR(150) NULL ,
  `countryId` INT NULL ,
  `administrator` INT NULL ,
  `moderator` INT NULL ,
  `delegate` INT NULL ,
  `typeOne` INT NULL ,
  `typeTwo` INT NULL ,
  `canSign` INT NULL ,
  `designer` INT NULL ,
  `passwordResetRequest` INT NULL ,
  `temporaryPassword` INT NULL ,
  `canProposeParagraph` INT NULL ,
  `assistant` INT NULL ,
  `translator` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `UNAME` (`username` ASC) );
/  
CREATE  TABLE `DOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `introduction` MEDIUMTEXT NULL ,
  `principal` INT NULL ,
  `typeOne` INT NULL ,
  `typeTwo` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `VERSION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `documentId` INT NULL ,
  `number` INT NULL ,
  `name` VARCHAR(100) NULL ,
  `description` MEDIUMTEXT NULL ,
  `creationDate` DATETIME NULL ,
  `upToCommentDate` DATETIME NULL ,
  `status` VARCHAR(50) NULL ,
  `hasDraft` INT NULL ,
  `deleted` INT NULL ,
  `commentsEnabled` INT NULL ,
  `spanishVersion` INT NULL ,
  `designerText` MEDIUMTEXT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `document` (`documentId` ASC) );
/
CREATE  TABLE `PARAGRAPH` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NULL ,
  `paragraphNumber` INT NULL ,
  `paragraphText` MEDIUMTEXT NULL ,
  `versionNumber` INT NULL,
  `numberDetail` VARCHAR(100) NULL,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `OBSERVATION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paragraphId` INT NULL ,
  `userId` INT NULL ,
  `addNewParagraph` INT NULL ,
  `privateObservation` INT NULL ,
  `creationDate` DATETIME NULL ,
  `observationText` MEDIUMTEXT NULL ,
  `portuguesTranslation` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/  
CREATE  TABLE `SITE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NULL ,
  `status` VARCHAR(50) NULL ,
  `dataId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `REFERENCEDOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `categoryId` INT NULL ,
  `title` VARCHAR(100) NULL ,
  `fileName` VARCHAR(100) NULL ,
  `extension` VARCHAR(10) NULL ,
  `contentType` VARCHAR(100) NULL ,
  `document` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `DELEGATEAUDIT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `delegateId` INT NULL ,
  `countryId` INT NULL ,
  `objectId` INT NULL ,
  `creationDate` DATETIME NULL ,
  `actionId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `AU_IX_00` (`countryId` ASC, `objectId` ASC, `actionId` ASC) );
/
CREATE  TABLE `CATEGORY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/ 
CREATE  TABLE `SYSPROPERTIES` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NULL ,
  `propValue` VARCHAR(255) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `TRACKCHANGE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paragraphId` INT NULL ,
  `changeText` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `NOTIFICATIONEMAIL` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `emailKey` VARCHAR(100) NULL ,
  `emailText` MEDIUMTEXT NULL ,
  `emailFrom` VARCHAR(100) NULL ,
  `emailTo` VARCHAR(100) NULL ,
  `emailSubject` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `RESOURCEBUNDLE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `rbContext` VARCHAR(100) NULL ,
  `rbKey` VARCHAR(100) NULL ,
  `rbValue` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/ 
CREATE  TABLE `LOGO` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `logoKey` VARCHAR(100) NULL ,
  `logoData` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
 /
 CREATE TABLE `DEPLOYED_SCRIPTS` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `scriptId` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) );
/