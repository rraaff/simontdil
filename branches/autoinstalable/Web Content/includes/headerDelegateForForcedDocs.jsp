<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=ResourceBundleCache.get("ventana", "titulo")%></title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="scripts/simon.js" type="text/javascript"></script>
<script src="scripts/menu.js" type="text/javascript"></script>
<%	com.tdil.simon.data.model.SystemUser user = (com.tdil.simon.data.model.SystemUser)session.getAttribute("user");
	if (user == null) { %>
	<jsp:forward page="./login.jsp">
		<jsp:param name="error" value="notlogged"/>
	</jsp:forward>
	<% } %>
<!--[if lte IE 7]>
<style>
#menuwrapper, #p7menubar ul a {height: 1%;}
a:active {width: auto;}
</style>
<![endif]-->
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">
<link href="styles/tdil.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="header">
	<div id="logo"><img src="./download.do?action=logo&fileId=header.logo" width="143" height="80" border="0"></div>
	<div id="blockinHeaderDelegate">
		<table width="500" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3" height="5"><img src="images/null.gif" width="1" height="5"></td>
			</tr>
			<tr>
				<td width="460" align="right"></td>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
				<td width="30" height="30"></td>
			</tr>
			<tr>
				<td colspan="3" height="10" align="right"><a href="javascript:window.close();"><%=ResourceBundleCache.get("header", "cerrarVentana")%></a></td>
			</tr>
		</table>
	</div>
	<div id="siteSeccion"><img src="images/header/zonaRestringida.gif" alt="Zona Restringida" width="168" height="60"></div>
	<div id="rayitaHeader"><img src="images/null.gif" width="936" height="5"></div>
</div>