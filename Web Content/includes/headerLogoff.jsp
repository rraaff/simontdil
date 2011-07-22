<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title><%=ResourceBundleCache.get("ventana", "titulo")%></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="scripts/simon.js" type="text/javascript"></script>
<link rel="shortcut icon" href="images/favicon.ico">
<!-- link href="styles/tdil.css" rel="stylesheet" type="text/css" -->
<link href="styles/focalae.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
#content #centrador{
	padding-left:130px;
}
#header #blockinHeader {
	background-color: #FFFFFF;
	height: 20px;
	width: 500px;
	float: right;
	position: relative;
}
-->
</style>
</head>
<body id="jsp-<%=getServletInfo()%>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="20" height="20"><img src="images/focalae_set/top_left.gif" width="20" height="20"></td>
		<td width="100%" background="images/focalae_set/top_center.gif"><img src="images/null.gif" width="20" height="20"></td>
		<td width="20" height="20"><img src="images/focalae_set/top_right.gif" width="20" height="20"></td>
	</tr>
	<tr>
		<td width="20" background="images/focalae_set/middle_left.gif"><img src="images/null.gif" width="20" height="1"></td>
		<td width="100%" align="center" bgcolor="#FFFFFF">
			<div id="header">
				<div id="logo"><img src="./download.do?action=logo&fileId=header.logo" border="0"></div>
				<div id="blockinHeader"></div>
				<div id="siteSeccion"><img src="images/header/zonaRestringida.png"></div>
				<div id="rayitaHeader"><img src="images/null.gif" width="1" height="1"></div>
			</div>
		</td>
		<td width="20" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="1"></td>
	</tr>
</table>