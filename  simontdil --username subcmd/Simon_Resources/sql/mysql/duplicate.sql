CREATE  TABLE `COUNTRY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `flag` MEDIUMBLOB NULL ,
  `host` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `SIGNATURE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NOT NULL ,
  `userId` INT NOT NULL ,
  `image` MEDIUMBLOB NULL ,
  `video` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

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
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `UNAME` (`username` ASC) );
  
CREATE  TABLE `DOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `introduction` MEDIUMTEXT NULL ,
  `principal` INT NULL ,
  `typeOne` INT NULL ,
  `typeTwo` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

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

CREATE  TABLE `PARAGRAPH` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NULL ,
  `paragraphNumber` INT NULL ,
  `paragraphText` MEDIUMTEXT NULL ,
  `versionNumber` INT NULL,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );


CREATE  TABLE `OBSERVATION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paragraphId` INT NULL ,
  `userId` INT NULL ,
  `addNewParagraph` INT NULL ,
  `privateObservation` INT NULL ,
  `creationDate` DATETIME NULL ,
  `observationText` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  
CREATE  TABLE `SITE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NULL ,
  `status` VARCHAR(50) NULL ,
  `dataId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `REFERENCEDOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `categoryId` INT NULL ,
  `title` VARCHAR(100) NULL ,
  `fileName` VARCHAR(100) NULL ,
  `extension` VARCHAR(10) NULL ,
  `contentType` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

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

CREATE  TABLE `CATEGORY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  
CREATE  TABLE `SYSPROPERTIES` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NULL ,
  `propValue` VARCHAR(255) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );


INSERT INTO COUNTRY(name, host, deleted) VALUES ('Argentina', 1, 0);
INSERT INTO SYSTEMUSER (username, password, name, email, countryId, administrator,moderator,delegate,
typeOne, typeTwo, canSign, designer, passwordResetRequest, temporaryPassword, canProposeParagraph, deleted)
SELECT 'Admin', '4E7AFEBCFBAE000B22C7C85E5560F89A2A0280B4', 'Admin', 'admin@tdil.com', c.id, 1,1,0,
0,0,0,1,0,0,0,0
FROM COUNTRY C
WHERE C.HOST = 1;

COMMIT;
  
insert into SITE(name, status, deleted) values('Delegate', 'NORMAL',0);
insert into SITE(name, status, deleted) values('Moderator', 'NORMAL',0);
insert into SITE(name, status, deleted) values('Public', 'NORMAL',0);

update SITE set dataId = 0;

commit;
  
INSERT INTO `SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.properties.location', 'C:/icarus/workspace/simon/Simon_Resources/simon2/simon.properties', 0);
INSERT INTO `SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.server.name', 'doble', 0);

COMMIT;