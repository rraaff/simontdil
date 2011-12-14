ALTER TABLE VERSION ADD spanishVersion INT NULL;
UPDATE VERSION SET spanishVersion = 1;
COMMIT;

CREATE  TABLE `TRACKCHANGE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paragraphId` INT NULL ,
  `changeText` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  
INSERT INTO TRACKCHANGE (paragraphId, changeText, deleted)
SELECT id, paragraphText, 0 FROM PARAGRAPH;
COMMIT;

ALTER TABLE VERSION ADD designerText MEDIUMTEXT NULL;
COMMIT;