CREATE  TABLE `SIMON`.`SYSPROPERTIES` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NULL ,
  `propValue` VARCHAR(255) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  
  
INSERT INTO `SIMON`.`SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.properties.location', 'C:/icarus/workspace/simon/Simon_Resources/props/simon.properties', 0);
INSERT INTO `SIMON`.`SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.server.name', 'localhost', 0);

COMMIT;