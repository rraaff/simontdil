CREATE TABLE IF NOT EXISTS `DEPLOYED_SCRIPTS` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `scriptId` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) );
/
INSERT INTO SYSPROPERTIES (propKey, propValue, deleted) VALUES ('simon.mailserver', 'localhost', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('menu', 'servidorDeEmail', 'Servidor de Email', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'desactivar', 'Desactivar', 0);
/
INSERT INTO SYSPROPERTIES (propKey, propValue, deleted) VALUES ('simon.mayorVersion', '2', 0);
/
INSERT INTO SYSPROPERTIES (propKey, propValue, deleted) VALUES ('simon.minorVersion', '0', 0);
/