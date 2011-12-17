CREATE  TABLE `COUNTRY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `flag` MEDIUMBLOB NULL ,
  `host` INT NULL ,
  `language` VARCHAR(100) NULL,
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
  `name` VARCHAR(255) NULL ,
  `email` VARCHAR(100) NULL ,
  `job` VARCHAR(100) NULL ,
  `countryDesc` VARCHAR(150) NULL ,
  `countryId` INT NULL ,
  `administrator` INT NULL ,
  `moderator` INT NULL ,
  `delegate` INT NULL ,
  `canSign` INT NULL ,
  `passwordResetRequest` INT NULL ,
  `temporaryPassword` INT NULL ,
  `canProposeParagraph` INT NULL ,
  `assistant` INT NULL ,
  `canComment` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `UNAME` (`username` ASC) );
/  
CREATE  TABLE `DOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(255) NOT NULL ,
  `introduction` MEDIUMTEXT NULL ,
  `principal` INT NULL ,
  `documentSubTypeId` INT NULL ,
  `relevance` INT NULL ,
  `documentDate` MEDIUMTEXT NULL ,
  `topic` MEDIUMTEXT NULL ,
  `tag1` MEDIUMTEXT NULL ,
  `tag2` MEDIUMTEXT NULL ,
  `orderNumber` INT NULL ,
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
  `subCategoryId` INT NULL ,
  `title` VARCHAR(255) NULL ,
  `fileName` VARCHAR(100) NULL ,
  `extension` VARCHAR(10) NULL ,
  `contentType` VARCHAR(100) NULL ,
  `document` MEDIUMBLOB NULL ,
  `orderNumber` INT NULL ,
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
  `name` VARCHAR(255) NULL ,
  `parentId` INT NULL,
  `orderNumber` INT NULL ,
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
  `rbLanguage` VARCHAR(100) NULL ,
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
CREATE  TABLE `DOCUMENTTYPE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NULL ,
  `parentId` INT NULL,
  `orderNumber` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/ 
CREATE  TABLE `USERGROUP` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/ 
CREATE  TABLE `SUBCATEGORY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `categoryId` INT NULL ,
  `name` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/ 
CREATE  TABLE `GROUPPERMISSION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `groupId` INT NULL ,
  `objectId` INT NULL ,
  `objectType` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/ 
CREATE  TABLE `GROUPMEMBER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `groupId` INT NULL ,
  `systemUserId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  /
INSERT INTO SYSPROPERTIES (propKey, propValue, deleted) VALUES ('simon.mayorVersion', '2', 0);
/
INSERT INTO SYSPROPERTIES (propKey, propValue, deleted) VALUES ('simon.minorVersion', '0', 0);
/
CREATE  TABLE `DOCUMENTSUBTYPE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `documentTypeId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
/
CREATE  TABLE `ATTACHMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `ownerId` INT NOT NULL ,
  `ownerType` VARCHAR(255) NOT NULL ,
  `title` VARCHAR(255) NULL ,
  `fileName` VARCHAR(100) NULL ,
  `extension` VARCHAR(10) NULL ,
  `contentType` VARCHAR(100) NULL ,
  `data` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`), 
  INDEX `ATT_IX_00` (`ownerId` ASC, `ownerType` ASC));