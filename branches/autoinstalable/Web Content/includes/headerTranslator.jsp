<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=ResourceBundleCache.get("ventana", "titulo")%></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script type="text/javascript" src="./scripts/mootools-1.2.4-core-nc.js" ></script>
<script type="text/javascript" src="./scripts/notimoo.js" ></script>
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="scripts/simon.js" type="text/javascript"></script>
<script src="scripts/menu.js" type="text/javascript"></script>
<script type="text/javascript" src="ckeditor.js"></script>
<script type="text/javascript" src="scripts/sexyalertbox.v1.2.moo.mini.js"></script>
<script type="text/javascript" src="scripts/tabs.js"></script>
<!--[if lte IE 7]>
<style>
#menuwrapper, #p7menubar ul a {height: 1%;}
a:active {width: auto;}
</style>
<![endif]-->
<link rel="shortcut icon" href="images/favicon.ico">
<!-- link href="styles/tdil.css" rel="stylesheet" type="text/css" -->
<link href="styles/focalae.css" rel="stylesheet" type="text/css">
<link href="styles/notimoo.css" rel="stylesheet" type="text/css">
<link href="styles/sexyalertbox.css" rel="stylesheet" type="text/css">
<link href="styles/tabs.css" rel="stylesheet" type="text/css">
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
		<td width="100%" align="center">
			<div id="header">
				<div id="logo"><html:link action="/goToTranslatorHome" ><img src="./download.do?action=logo&fileId=header.logo" border="0"></html:link></div>
				<div id="blockinHeaderDelegate">
					<table width="500" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="3" height="5"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
						<tr>
							<td width="460" align="right"><%=ResourceBundleCache.get("header", "usuario")%>: <span class="userLogged"><%= user.getName() %></span></td>
							<td width="10"><img src="images/null.gif" width="10" height="1"></td>
							<td width="30" height="30"><img src="./download.do?action=flag&fileId=<%=user.getCountryId()%>" width="30" height="30"></td>
						</tr>
						<tr>
							<td colspan="3" height="10" align="right"><html:link action="/logout" ><%=ResourceBundleCache.get("header", "salir")%></html:link></td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		<td width="20" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="1"></td>
	</tr>
</table>