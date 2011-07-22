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
<script src="scripts/menu.js" type="text/javascript"></script>
<script src="scripts/simon.js" type="text/javascript"></script>
<script type="text/javascript" src="./scripts/mootools-1.2.4-core-nc.js" ></script>
<script type="text/javascript" src="./scripts/mootools-1.2.4.4-more-yc.js" ></script>
<script type="text/javascript" src="./scripts/notimoo.js" ></script>
<script type="text/javascript" src="./scripts/slimpicker.js" ></script>
<script type="text/javascript" src="ckeditor.js"></script>
<script type="text/javascript" src="scripts/sexyalertbox.v1.2.moo.mini.js"></script>
<!--[if lte IE 7]>
<style>
#menuwrapper, #p7menubar ul a {height: 1%;}
a:active {width: auto;}
input.date {
	width: 150px;
	color: #000;
}
</style>
<![endif]-->
<link rel="shortcut icon" href="images/favicon.ico">
<!--link href="styles/tdil.css" rel="stylesheet" type="text/css"-->
<link href="styles/focalae.css" rel="stylesheet" type="text/css">
<link href="styles/menu.css" rel="stylesheet" type="text/css">
<link href="styles/notimoo.css" rel="stylesheet" type="text/css">
<link href="styles/slimpicker.css" rel="stylesheet" type="text/css">
<link href="styles/sexyalertbox.css" rel="stylesheet" type="text/css">
</head>
<body onLoad="P7_ExpMenu();" id="jsp-<%=getServletInfo()%>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="20" height="20"><img src="images/focalae_set/top_left.gif" width="20" height="20"></td>
		<td width="100%" background="images/focalae_set/top_center.gif"><img src="images/null.gif" width="20" height="20"></td>
		<td width="30" height="20"><img src="images/focalae_set/top_right.gif" width="20" height="20"></td>
	</tr>
	<tr>
		<td width="20" background="images/focalae_set/middle_left.gif"><img src="images/null.gif" width="20" height="1"></td>
		<td width="100%" align="center" bgcolor="#FFFFFF">
			<!-- div id="header">
				<div id="blockinHeader"-->
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td rowspan="2">
								<% if (!isAdministrator && !isModerator && isDesigner) { %>
									<html:link action="/goToListDocumentForDesign" ><img src="./download.do?action=logo&fileId=header.logo" border="0"></html:link>
								<% } else { %>
									<img src="./download.do?action=logo&fileId=header.logo" border="0">
								<% } %>
							</td>
							<td width="200" rowspan="2" align="left"><%=ResourceBundleCache.get("header", "sitioModerador")%>: <b><%=com.tdil.simon.data.model.Site.getMODERATOR_SITE().getTranslatedStatus()%></b><br><%=ResourceBundleCache.get("header", "sitioDelegados")%>: <b><%=com.tdil.simon.data.model.Site.getDELEGATE_SITE().getTranslatedStatus()%></b><br><%=ResourceBundleCache.get("header", "sitioPublico")%>: <b><%=com.tdil.simon.data.model.Site.getPUBLIC_SITE().getTranslatedStatus()%></b></td>
							<td width="200" align="right"><%=ResourceBundleCache.get("header", "usuario")%>: <span class="userLogged"><%= user.getName() %></span></td>
							<td width="10"><img src="images/null.gif" width="10" height="1"></td>
							<td width="30" height="30"><img src="./download.do?action=flag&fileId=<%=user.getCountryId()%>" width="30" height="30"></td>
							<td rowspan="2" width="130" align="right">
								<% 	if(eventMode) { %>
									<% 	if(inNegotiation) { %>
										<img src="images/header/modoNegociacionDelegados.png"><!-- Modo Modo negociación y negociando -->
									<% } else { %>
										<% 	if(isSign) { %>
											<img src="images/header/modoNegociacionDelegados.png"><!-- Modo Modo negociación firmando -->
										<% } else { %>
											<img src="images/header/modoNegociacionDelegados.png"><!-- Modo Modo negociación aun no negociando -->
										<% } %>
									<% } %>
								<% } else { %>
									<img src="images/header/administradorModerador.png">
								<% } %>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="right">
								<div id="logoutLink"><html:link action="/logout" ><%=ResourceBundleCache.get("header", "salir")%></html:link> - 
								<% 	if(eventMode && inNegotiation && isModerator) { 
										if (com.tdil.simon.utils.PrivateMessageUtils.mustBeShownIn(this.getServletInfo())) {
										%>
										<html:link  action="/goToListPrivateObservations.st"><%=ResourceBundleCache.get("header", "mensajesPrivados")%>: <span class="remarcado" id="privateMessagesCount"></span></html:link></span>
										<% } else { %>
										<%=ResourceBundleCache.get("header", "negociacionEnCurso")%>
										<% } %>
								<% } else { %>
									
								<% } %>
								</div>
							</td>
						</tr>
					</table>
				<!-- /div -->
				<!-- div id="siteSeccion"> - </div -->
				<% 	if(eventMode && inNegotiation && isModerator ) { 
						if (com.tdil.simon.utils.PrivateMessageUtils.mustBeShownIn(this.getServletInfo())) {
						
							com.tdil.simon.data.valueobjects.ObservationSummaryVO summary;
							if ("createDocumentStepParagraph".equals(this.getServletInfo())) {
								summary = com.tdil.simon.utils.ObservationUtils.countPrivateObservationsForNegotiatedParagraph(); 
							} else {
								summary = com.tdil.simon.utils.ObservationUtils.countPrivateObservationsForNegotiatedVersion(); 
							}
							%>
							<script type="text/javascript">
							document.getElementById('privateMessagesCount').innerHTML = <%=summary.getCount()%>;
							var notimooManager = new Notimoo();
								var maxId = <%= summary.getMaxId()%>;
								function refreshPrivateMessages() {		
									var jsonRequest = new Request.JSON({url: '<html:rewrite page="/countPrivateMessagesForVersion.st"/>', onSuccess: function(privMessages, responseText){
										var errorResult = privMessages.error;
										if ('notLogged' == errorResult) {
											window.location='<html:rewrite page="/login.jsp"/>';
											return;
										}
										document.getElementById('privateMessagesCount').innerHTML = privMessages.count;
										if("0" != privMessages.maxId && privMessages.maxId != maxId) {
											maxId = privMessages.maxId;
											notimooManager.show({
												title: '<%=ResourceBundleCache.get("header", "mensajesPrivados")%>',
												message: '<%=ResourceBundleCache.get("header", "nuevoMensajesPrivado")%>'
											});
										}
									}}).get({'full': '<%=getServletInfo().equals("createDocumentStepParagraph") ? "false" : "true"%>'});
								}
								timer = setInterval("refreshPrivateMessages()",1000);
							</script>
						<% } %>
				<% } %>
			<!-- /div -->
		</td>
		<td width="30" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="1"></td>
	</tr>
</table>