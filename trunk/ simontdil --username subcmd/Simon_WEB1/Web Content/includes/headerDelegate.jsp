<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cumbres Iberoamericanas | Al servicio de la Comunidad Iberoamericana</title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script type="text/javascript" src="./scripts/mootools-1.2.4-core-nc.js" ></script>
<script type="text/javascript" src="./scripts/notimoo.js" ></script>
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="scripts/menu.js" type="text/javascript"></script>
<script type="text/javascript" src="ckeditor.js"></script>
<script type="text/javascript" src="scripts/sexyalertbox.v1.2.moo.mini.js"></script>
<!--[if lte IE 7]>
<style>
#menuwrapper, #p7menubar ul a {height: 1%;}
a:active {width: auto;}
</style>
<![endif]-->
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">
<link href="styles/tdil.css" rel="stylesheet" type="text/css">
<link href="styles/notimoo.css" rel="stylesheet" type="text/css">
<link href="styles/sexyalertbox.css" rel="stylesheet" type="text/css">
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
	<div id="logo"><html:link action="/goToDelegateHome" ><img src="images/header/logo.gif" alt="Cumbres Iberoamericanas | Argentina 2010" width="243" height="136" border="0"></html:link></div>
	<div id="blockinHeaderDelegate">
		<table width="500" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" height="10"><img src="images/null.gif" width="1" height="10"></td>
			</tr>
			<tr>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
				<td width="320" align="right">
					<div id="userLogued">
						<table width="420" border="0" cellspacing="0" cellpadding="0">
							<!-- Meter un if user logged acá con un else -->
							<tr>
								<td width="380" align="right">Usuario: <span class="userLogged"><%= user.getName() %></span></td>
								<td width="10"><img src="images/null.gif" width="10" height="1"></td>
								<td width="30"><img src="./download.do?action=flag&fileId=<%=user.getCountryId()%>" width="30" height="30"></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" width="30" align="right"><html:link action="/logout" >Salir</html:link></td>
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