<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Cumbres Iberoamericanas | Al servicio de la Comunidad Iberoamericana</title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
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
<link href="styles/menu.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./scripts/mootools-1.js" ></script>
<script type="text/javascript" src="./scripts/notimoo-v1.js" ></script>
<style type="text/css">
<!--
#content #centrador{
	padding-left:130px;
}
-->
</style>
<link href="styles/notimoo-v1.css" rel="stylesheet" type="text/css"/>

</head>
<body onLoad="P7_ExpMenu();">
<div id="header">
	<div id="logo"><img src="images/header/logo.gif" alt="Cumbres Iberoamericanas | Argentina 2010" width="243" height="136"></div>
	<div id="blockinHeader">
		<table width="500" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="5" height="10"><img src="images/null.gif" width="1" height="10"></td>
			</tr>
			<tr>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
				<td width="320">
					<div id="userLogued">
						<table width="320" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="280" align="left"><%= user.getName() %></td>
								<td width="10"><img src="images/null.gif" width="10" height="1"></td>
								<td>[Insertar bandera de la delegación]<!-- img src="images/others/banderaArgentina.png" width="30" height="30"> --></td>
							</tr>
						</table>
					</div>
				</td>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
				<td width="150" align="right">Estado de la negociaci&oacute;n<br>
				<span class="remarcado">Mensajes Privados: </span><span class="remarcado" id="privateMessagesCount">-</span></td>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
			</tr>
			<tr>
				<td colspan="5" height="9"><img src="images/null.gif" width="1" height="9"></td>
			</tr>
			<tr>
				<td colspan="2"><div id="centrador" style="padding-left:14px;"><a class="button" href="#"><span>Activar pantalla p&uacute;blica</span></a></div></td>
				<td colspan="3"><a class="button" href="am_home.html"><span>Iniciar Negociaci&oacute;n</span></a></td>
			</tr>
		</table>
	</div>
	
	<div id="siteSeccion"><img src="images/null.gif" alt="Zona Restringida" width="187" height="60"></div>
	<div id="rayitaHeader"><img src="images/null.gif" width="936" height="5"></div>
</div>
<% 	if(com.tdil.simon.data.model.Site.EVENT.equals(com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus())) { %>
	<% 	if(com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
		<% if (isModerator) {
				com.tdil.simon.data.valueobjects.ObservationSummaryVO summary = com.tdil.simon.utils.ObservationUtils.countPrivateObservationsForNegotiatedVersion(); 
		%>
		<script type="text/javascript">
		var notimooManager = new Notimoo();
			var maxId = <%= summary.getMaxId()%>;
			function refreshPrivateMessages() {		
				var jsonRequest = new Request.JSON({url: '<html:rewrite page="/countPrivateMessagesForVersion.st"/>', onSuccess: function(privMessages, responseText){
				    document.getElementById('privateMessagesCount').innerHTML = privMessages.count;
				    if(privMessages.maxId != maxId) {
				    	maxId = privMessages.maxId;
				    	notimooManager.show({
							title: 'Mensajes Privados',
							message: 'Usted ha recibido nuevo/s mensajes privados.'
						});
				    }
				}}).get();
			}
			timer = setInterval("refreshPrivateMessages()",1000);
		</script>
		<% } %>
	<% } %>
<% } %>