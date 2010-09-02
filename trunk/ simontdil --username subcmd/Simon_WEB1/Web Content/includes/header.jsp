<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Cumbres Iberoamericanas | Al servicio de la Comunidad Iberoamericana</title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Dise�o" />
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
				<td width="220" valign="middle">
					<div id="userLogued">
						<table width="320" height="30" border="0" cellspacing="0" cellpadding="0">
							<!-- Meter un if user logged ac� con un else -->
							<tr>
								<td width="280" align="right">Bienvenido: <span class="userLogged"><%= user.getName() %></span></td>
								<td width="10"><img src="images/null.gif" width="10" height="1"></td>
								<td width="30"><img src="images/others/banderaArgentina.png" width="30" height="30"></td>
							</tr>
						</table>
					</div>
				</td>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
				<td width="250" align="right" class="remarcado">Mensajes Privados: <span class="remarcado" id="privateMessagesCount"><html:link  action="goToListPrivateObservations.st" >Ver</html:link></span></td>
				<td width="10"><img src="images/null.gif" width="10" height="1"></td>
			</tr>
			<tr>
				<td colspan="5" height="10"><img src="images/null.gif" width="1" height="10"></td>
			</tr>
			<tr>
				<td colspan="5" height="10">
					<div id="dataSites">
						<table width="500" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>Moderador: <%=com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus()%></td>
								<td width="10"><img src="images/null.gif" width="10" height="1"></td>
								<td>Delegados: <%=com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus()%></td>
								<td width="10"><img src="images/null.gif" width="10" height="1"></td>
								<td>Pantalla P�blica: <%=com.tdil.simon.data.model.Site.getPUBLIC_SITE().getStatus()%></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="siteSeccion">
	<!-- ac� me gustar�a cargar una imagen dependiendo del estado del site LO_TENES -->
	<% 	if(com.tdil.simon.data.model.Site.EVENT.equals(com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus())) { %>
		<% 	if(com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
			<img src="images/header/modoNegociacionDelegados.gif" width="187" height="60">Modo evento y negociando
		<% } else { %>
			<% 	if(com.tdil.simon.data.model.Site.IN_SIGN.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
				<img src="images/header/modoNegociacionDelegados.gif" width="187" height="60">Modo evento firmando
			<% } else { %>
				<img src="images/header/modoNegociacionDelegados.gif" width="187" height="60">Modo evento aun no negociando
			<% } %>
		<% } %>
	<% } else { %>
		<img src="images/header/administradorModerador.gif" width="187" height="60">
	<% } %>
	</div>
	<% 	if(com.tdil.simon.data.model.Site.EVENT.equals(com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus())) { %>
		<% 	if(com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
			<% if (isModerator) {
					com.tdil.simon.data.valueobjects.ObservationSummaryVO summary = com.tdil.simon.utils.ObservationUtils.countPrivateObservationsForNegotiatedVersion(); 
			%>
			<script type="text/javascript">
			document.getElementById('privateMessagesCount').innerHTML = <%=summary.getCount()%>;
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
				timer = setInterval("refreshPrivateMessages()",10000);
			</script>
			<% } %>
		<% } %>
	<% } %>
	<div id="rayitaHeader"><img src="images/null.gif" width="936" height="5"></div>
</div>