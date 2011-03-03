CREATE  TABLE `SIMON`.`COUNTRY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `flag` MEDIUMBLOB NULL ,
  `host` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `SIMON`.`SIGNATURE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NOT NULL ,
  `userId` INT NOT NULL ,
  `image` MEDIUMBLOB NULL ,
  `video` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `SIMON`.`SYSTEMUSER` (
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
  
CREATE  TABLE `SIMON`.`DOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `introduction` MEDIUMTEXT NULL ,
  `principal` INT NULL ,
  `typeOne` INT NULL ,
  `typeTwo` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `SIMON`.`VERSION` (
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

CREATE  TABLE `SIMON`.`PARAGRAPH` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `versionId` INT NULL ,
  `paragraphNumber` INT NULL ,
  `paragraphText` MEDIUMTEXT NULL ,
  `versionNumber` INT NULL,
  `numberDetail` VARCHAR(100) NULL,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );


CREATE  TABLE `SIMON`.`OBSERVATION` (
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
  
CREATE  TABLE `SIMON`.`SITE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NULL ,
  `status` VARCHAR(50) NULL ,
  `dataId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `SIMON`.`REFERENCEDOCUMENT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `categoryId` INT NULL ,
  `title` VARCHAR(100) NULL ,
  `fileName` VARCHAR(100) NULL ,
  `extension` VARCHAR(10) NULL ,
  `contentType` VARCHAR(100) NULL ,
  `document` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `SIMON`.`DELEGATEAUDIT` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `delegateId` INT NULL ,
  `countryId` INT NULL ,
  `objectId` INT NULL ,
  `creationDate` DATETIME NULL ,
  `actionId` INT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `AU_IX_00` (`countryId` ASC, `objectId` ASC, `actionId` ASC) );

CREATE  TABLE `SIMON`.`CATEGORY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  
CREATE  TABLE `SIMON`.`SYSPROPERTIES` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NULL ,
  `propValue` VARCHAR(255) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `SIMON`.`TRACKCHANGE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paragraphId` INT NULL ,
  `changeText` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

INSERT INTO SIMON.COUNTRY(name, host, deleted) VALUES ('Argentina', 1, 0);
INSERT INTO SIMON.SYSTEMUSER (username, password, name, email, countryId, administrator,moderator,delegate,
typeOne, typeTwo, canSign, designer, passwordResetRequest, temporaryPassword, canProposeParagraph, deleted, assistant, translator)
SELECT 'Admin', '4E7AFEBCFBAE000B22C7C85E5560F89A2A0280B4', 'Admin', 'admin@tdil.com', C.id, 1,1,0,
0,0,0,1,0,0,0,0,0,0
FROM SIMON.COUNTRY C
WHERE C.HOST = 1;

COMMIT;

insert into SIMON.SITE(name, status, deleted) values('Delegate', 'NORMAL',0);
insert into SIMON.SITE(name, status, deleted) values('Moderator', 'NORMAL',0);
insert into SIMON.SITE(name, status, deleted) values('Public', 'NORMAL',0);

update SIMON.SITE set dataId = 0;

INSERT INTO `SIMON`.`SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.properties.location', '/home/mgodoy/icarus/workspace/simon/Simon_Resources/props/simon.properties', 0);
INSERT INTO `SIMON`.`SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.server.name', 'localhost', 0);
INSERT INTO `SIMON`.`SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.tmp.subpath', 'simon', 0);
INSERT INTO `SIMON`.`SYSPROPERTIES`(propKey, propValue, deleted) VALUES('simon.server.url', 'http://localhost:8180/Simon', 0);
commit;

CREATE  TABLE `SIMON`.`NOTIFICATIONEMAIL` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `emailKey` VARCHAR(100) NULL ,
  `emailText` MEDIUMTEXT NULL ,
  `emailFrom` VARCHAR(100) NULL ,
  `emailTo` VARCHAR(100) NULL ,
  `emailSubject` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );

INSERT INTO `SIMON`.`NOTIFICATIONEMAIL`(emailKey, emailText, emailFrom, emailTo, emailSubject, deleted) 
VALUES('newpassword', '<style type="text/css">
<!--
.fontMail{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color:#666666;
	text-decoration: none;
}
.fontMail a, .fontMail a:hover{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight:bold;
	color:#666666;
	text-decoration: none;
}
-->
</style>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#E6E6E6">
	<tr>
		<td align="center">
			<table width="650" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
				<tr>
					<td width="650" colspan="5"><img src="{SERVER}/images/mails/header.gif" width="650" height="167"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="604" align="left" valign="top" class="fontMail"><p>Estimado <B>{FULLNAME}</B>,<br>Usted ha sido dado de alta como usuario de la zona restringida del sitio web XX CUMBRE IBEROAMERICANA, URL <a href="{SERVER}/changePassword.jsp">{SERVER}/</a>.</p><p>Su nombre de usuario es: <b>{USERNAME}</b>, y su contraseña temporal es: <b>{PASSWORD}</b>. Usted deberá modificar la contraseña la primera vez que ingrese al sitio.<br/>Para ingresar al sitio y cambiar su contraseña utilice el siguiente enlace: </p>
					<p align="center"><a href="{SERVER}/changePassword.jsp" target="_blank"><img src="{SERVER}/images/mails/botonIngresarAlSitio.gif" width="106" height="24" border="0"></a></p>
					<p>Ante cualquier consulta o dificultad, comuníquese con la Mesa de Ayuda de la Cancillería Argentina Correo electrónico: <a href="ayuda@xxcumbreiberoamericana.mrecic.gov.ar">ayuda@xxcumbreiberoamericana.mrecic.gov.ar</a>.<br />
					Telef&oacute;nos (+54 11) 4819 7658</p></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td colspan="3" height="20"><img src="{SERVER}/images/null.gif" width="10" height="20"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="5"><img src="{SERVER}/images/mails/footer.gif" width="650" height="84"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>', 'sejec@cancilleria.gob.ar', 'to', 'Nueva clave de acceso', 0);
commit;

INSERT INTO `SIMON`.`NOTIFICATIONEMAIL`(emailKey, emailText, emailFrom, emailTo, emailSubject, deleted) 
VALUES('passworreset', '<style type="text/css">
<!--
.fontMail{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color:#666666;
	text-decoration: none;
}
.fontMail a, .fontMail a:hover{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight:bold;
	color:#666666;
	text-decoration: none;
}
-->
</style>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#E6E6E6">
	<tr>
		<td align="center">
			<table width="650" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
				<tr>
					<td width="650" colspan="5"><img src="{SERVER}/images/mails/header.gif" width="650" height="167"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="604" align="left" valign="top" class="fontMail"><p>Estimado Administrador,<br>El usuario: <B>{FULLNAME}</B>Ha solicitado una contrase&ntilde;a nueva para su usuario: <B>{USERNAME}</B>.</p></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td colspan="3" height="20"><img src="{SERVER}/images/null.gif" width="10" height="20"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td colspan="3" align="center"><a href="{SERVER}/" target="_blank"><img src="{SERVER}/images/mails/botonIngresarAlSitio.gif" width="106" height="24" border="0"></a></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td colspan="3" height="20"><img src="{SERVER}/images/null.gif" width="10" height="20"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="5"><img src="{SERVER}/images/mails/footer.gif" width="650" height="84"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>', 'sejec@cancilleria.gob.ar', 'sejec@cancilleria.gob.ar', 'Usuario solicito blanqueo de clave', 0);
commit;


INSERT INTO `SIMON`.`NOTIFICATIONEMAIL`(emailKey, emailText, emailFrom, emailTo, emailSubject, deleted) 
VALUES('newversion', '<style type="text/css">
<!--
.fontMail{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color:#666666;
	text-decoration: none;
}
.fontMail a, .fontMail a:hover{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight:bold;
	color:#666666;
	text-decoration: none;
}
-->
</style>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#E6E6E6">
	<tr>
		<td align="center">
			<table width="650" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
				<tr>
					<td width="650" colspan="5"><img src="{SERVER}/images/mails/header.gif" width="650" height="167"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="604" align="left" valign="top" class="fontMail"><p>Estimado <B>{FULLNAME}</B>,</p>
					<p>Se ha creado un nuevo documento en la zona restringida del sitio web XX CUMBRE IBEROAMERICANA, URL, <a href="{SERVER}/index.jsp">{SERVER}/</a>.</p>
					<p>El nombre del nuevo documento es: <B>{DOCUMENT_TITLE}</B><br/>Para ingresar al sitio utilice el siguiente enlace:</p>
					<p align="center"><a href="{SERVER}/index.jsp" target="_blank"><img src="{SERVER}/images/mails/botonIngresarAlSitio.gif" width="106" height="24" border="0"></a></p>
					<p>Ante cualquier consulta o dificultad, comuníquese con la Mesa de Ayuda de la Cancillería Argentina Correo electrónico: <a href="ayuda@xxcumbreiberoamericana.mrecic.gov.ar">ayuda@xxcumbreiberoamericana.mrecic.gov.ar</a>.<br />
					Telef&oacute;nos (+54 11) 4819 7658</p></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td colspan="3" height="20"><img src="{SERVER}/images/null.gif" width="10" height="20"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="5"><img src="{SERVER}/images/mails/footer.gif" width="650" height="84"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>', 'sejec@cancilleria.gob.ar', 'sejec@cancilleria.gob.ar', 'Nueva version consolidada', 0);
commit;


INSERT INTO `SIMON`.`NOTIFICATIONEMAIL`(emailKey, emailText, emailFrom, emailTo, emailSubject, deleted) 
VALUES('newobservation', '<style type="text/css">
<!--
.fontMail{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color:#666666;
	text-decoration: none;
}
.fontMail a, .fontMail a:hover{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight:bold;
	color:#666666;
	text-decoration: none;
}
-->
</style>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#E6E6E6">
	<tr>
		<td align="center">
			<table width="650" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
				<tr>
					<td width="650" colspan="5"><img src="{SERVER}/images/mails/header.gif" width="650" height="167"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="604" align="left" valign="top" class="fontMail"><p>Estimado <B>{FULLNAME}</B>,</p>
					<p>La delegación de <B>{DELEGATION}</B>, ha creado una nueva observación al párrafo número <B>{PARAGRAPH}</B>, del documento: <B>{DOCUMENT_TITLE}</B>.</p>
					<p>Para ingresar al sitio utilice el siguiente enlace:</p>
					<p align="center"><a href="{SERVER}/index.jsp" target="_blank"><img src="{SERVER}/images/mails/botonIngresarAlSitio.gif" width="106" height="24" border="0"></a></p>
					<p>Ante cualquier consulta o dificultad, comuníquese con la Mesa de Ayuda de la Cancillería Argentina Correo electrónico: <a href="ayuda@xxcumbreiberoamericana.mrecic.gov.ar">ayuda@xxcumbreiberoamericana.mrecic.gov.ar</a>.<br />
					Telef&oacute;nos (+54 11) 4819 7658</p></td>
					<td width="22"><img src="{SERVER}/images/null.gif" width="22" height="1"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
					<td colspan="3" height="20"><img src="{SERVER}/images/null.gif" width="10" height="20"></td>
					<td width="1" bgcolor="#666666"><img src="{SERVER}/images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="5"><img src="{SERVER}/images/mails/footer.gif" width="650" height="84"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>', 'sejec@cancilleria.gob.ar', 'sejec@cancilleria.gob.ar', 'Nueva observacion', 0);
commit;


CREATE  TABLE `SIMON`.`RESOURCEBUNDLE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `rbContext` VARCHAR(100) NULL ,
  `rbKey` VARCHAR(100) NULL ,
  `rbValue` MEDIUMTEXT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );
  
CREATE  TABLE `SIMON`.`LOGO` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `logoKey` VARCHAR(100) NULL ,
  `logoData` MEDIUMBLOB NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) );