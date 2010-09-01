<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Cumbres Iberoamericanas | Al servicio de la Comunidad Iberoamericana</title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script type="text/javascript" src="./scripts/mootools-1.js" ></script>
<script type="text/javascript" src="./scripts/notimoo-v1.js" ></script>
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="scripts/menu.js" type="text/javascript"></script>
<!--[if lte IE 7]>
<style>
#menuwrapper, #p7menubar ul a {height: 1%;}
a:active {width: auto;}
</style>
<![endif]-->
<% 
	com.tdil.simon.data.model.SystemUser user = (com.tdil.simon.data.model.SystemUser)session.getAttribute("user");
	boolean isAdministrator = user.isAdministrator();
	boolean isModerator = user.isModerator();
	boolean isDelegate = user.isDelegate();
%>
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">

<link href="styles/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
#content #centrador{
	padding-left:130px;
}
-->
</style>
</head>
<body>
<div id="header">
	<div id="logo"><img src="images/header/logo.gif" alt="Cumbres Iberoamericanas | Argentina 2010" width="243" height="136"></div>
	<div id="blockinHeaderDelegate">
		<table width="500" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" height="10"><img src="images/null.gif" width="1" height="10"></td>
			</tr>
			<tr>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
				<td width="320">
					<div id="userLogued">
						<table width="320" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="280" align="left">[insertar nombre de usuario]</td>
								<td width="10"><img src="images/null.gif" width="10" height="1"></td>
								<td>[Insertar bandera de la delegación]<!-- img src="images/others/banderaArgentina.png" width="30" height="30"> --></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" height="9"><img src="images/null.gif" width="1" height="9"></td>
			</tr>
		</table>
	</div>
	<div id="siteSeccion"><img src="images/header/zonaRestringida.gif" alt="Zona Restringida" width="168" height="60"></div>
	<div id="rayitaHeader"><img src="images/null.gif" width="936" height="5"></div>
</div>