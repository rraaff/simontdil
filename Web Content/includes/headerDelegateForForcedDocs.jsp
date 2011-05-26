<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=ResourceBundleCache.get("ventana", "titulo")%></title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Dise�o" />
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
<!-- link href="styles/tdil.css" rel="stylesheet" type="text/css" -->
<link href="styles/focalae.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="20" height="20"><img src="images/focalae_set/top_left.gif" width="20" height="20"></td>
		<td width="100%" background="images/focalae_set/top_center.gif"><img src="images/null.gif" width="20" height="20"></td>
		<td width="20" height="20"><img src="images/focalae_set/top_right.gif" width="20" height="20"></td>
	</tr>
	<tr>
		<td width="20" background="images/focalae_set/middle_left.gif"><img src="images/null.gif" width="20" height="1"></td>
		<td width="100%" align="center" bgcolor="#FFFFFF"><div id="closeWindow"><a href="javascript:window.close();"><%=ResourceBundleCache.get("header", "cerrarVentana")%></a></div></td>
		<td width="20" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="1"></td>
	</tr>
</table>