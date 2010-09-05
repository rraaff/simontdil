CREATE  TABLE `simon`.`COUNTRY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `flag` MEDIUMBLOB NULL ,
  `host` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `simon`.`SIGNATURE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NOT NULL ,
  `userId` INT NOT NULL ,
  `image` MEDIUMBLOB NULL ,
  `video` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `simon`.`SYSTEMUSER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(20) NULL ,
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
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `UNAME` (`username` ASC) );
  
CREATE  TABLE `simon`.`DOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `introduction` VARCHAR(250) NOT NULL ,
  `principal` INT NULL ,
  `typeOne` INT NULL ,
  `typeTwo` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `simon`.`VERSION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `documentId` INT NULL ,
  `number` INT NULL ,
  `name` VARCHAR(100) NULL ,
  `description` VARCHAR(500) NULL ,
  `creationDate` DATETIME NULL ,
  `upToCommentDate` DATETIME NULL ,
  `status` VARCHAR(50) NULL ,
  `hasDraft` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `document` (`documentId` ASC) );

CREATE  TABLE `simon`.`PARAGRAPH` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NULL ,
  `paragraphNumber` INT NULL ,
  `paragraphText` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );


CREATE  TABLE `simon`.`OBSERVATION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paragraphId` INT NULL ,
  `userId` INT NULL ,
  `addNewParagraph` INT NULL ,
  `privateObservation` INT NULL ,
  `creationDate` DATETIME NULL ,
  `observationText` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  
CREATE  TABLE `simon`.`SITE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NULL ,
  `status` VARCHAR(50) NULL ,
  `dataId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `simon`.`REFERENCEDOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `categoryId` INT NULL ,
  `title` VARCHAR(100) NULL ,
  `fileName` VARCHAR(100) NULL ,
  `extension` VARCHAR(10) NULL ,
  `contentType` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `simon`.`DELEGATEAUDIT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `delegateId` INT NULL ,
  `countryId` INT NULL ,
  `objectId` INT NULL ,
  `creationDate` DATETIME NULL ,
  `actionId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `AU_IX_00` (`countryId` ASC, `objectId` ASC, `actionId` ASC) );

CREATE  TABLE `simon`.`CATEGORY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );


INSERT INTO SIMON.COUNTRY(name, host, deleted) VALUES ('Argentina', 1, 0);
INSERT INTO SIMON.SYSTEMUSER (username, password, name, email, countryId, administrator,moderator,delegate,
typeOne, typeTwo, canSign, designer, passwordResetRequest, temporaryPassword, deleted)
SELECT 'Admin', 'Admin', 'Admin', 'admin@tdil.com', c.id, 1,1,0,
0,0,0,1,0,0,0
FROM SIMON.COUNTRY C
WHERE C.HOST = 1;

COMMIT;
  