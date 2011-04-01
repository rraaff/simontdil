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
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'activar', 'Activar', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'editar', 'Editar', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'borrarFirma', 'Borrar Firma', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'desactivarComentarios', 'Desactivar', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'activarComentarios', 'Activar', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'borrar', 'Borrar', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'descargar', 'Descargar', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'ir', 'Ir', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'irallistado', 'Ir al listado', 0);
/
INSERT INTO RESOURCEBUNDLE (rbContext, rbKey, rbValue, deleted) VALUES ('botones', 'volver', 'Volver', 0);
/