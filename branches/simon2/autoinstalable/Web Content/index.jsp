<%@ page info="index"%>
<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title><%=ResourceBundleCache.get("ventana", "titulo")%></title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Dise�o" />
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">

<link href="styles/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
#centrador {
	padding-left:500px;
}
#content{
	width:auto;
	height:700px;
	text-align:center;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #808080;
	margin-top: 10px;
	margin-right: 10px;
	margin-left: 10px;
}
#imageHolder{
	height: 480px;
	width: 978px;
	margin-right: auto;
	margin-left: auto;
	position: relative;
	padding-top: 100px;
}

-->
</style>
</head>
<body>
<div id="content">
	<div id="imageHolder"><img src="images/demo/caratula.gif" width="978" height="480"></div>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td><a class="button" href="login.jsp"><span><%=ResourceBundleCache.get(getServletInfo(), "ingresar")%></span></a></td>
			<td>&nbsp;</td>
			<td><a class="button" href="changePassword.jsp"><span><%=ResourceBundleCache.get(getServletInfo(), "usuarioNuevo")%></span></a></td>
		</tr>
	</table>
</div>
<div id="footer">
	<div id="copyright">Member countries should make use of online registration to register their delegates to the meeting. Only the Focal Point of each country or persons authorized by him may access this section with personal password. Are strongly encouraged to register for this road, way to avoid delays when opening the meeting.SEJEC Secretar&iacute;a Ejecutiva XX Cumbre Iberoamericana<br><br><br>Esmeralda 1212, Ciudad Aut�noma de Buenos Aires.<br>C�digo Postal: C1007ABR. Rep�blica Argentina<br>Tel&eacute;fonos: +54 11 4819 7520 / +54 11 4819 7521</div>
	<div id="logoCumbres"><img src="./download.do?action=logo&fileId=footer.logoCumbres" alt="Cumbres Iberoamericanas" width="103" height="49"></div>
	<div style="width:150px;font-size:10px; color:#999999; float:left; margin-top: -60px; margin-left: 10px;"> - El servidor utiliza GMT - </div>
</div>
</body>
</html>