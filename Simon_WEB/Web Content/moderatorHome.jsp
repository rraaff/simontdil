<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>Moderator Home</title>
		<script type="text/javascript">
			function refreshPrivateMessages() {
				new Ajax.Request('<html:rewrite page="/countPrivateMessagesForVersion.st"/>', {
			    onComplete: function(transport, json) {
			        var value = json.count;
			         document.getElementById('privCount').innerHTML = value;
			      }
			   });
			}
			timer = setInterval("refreshPrivateMessages()",1000);
		</script>
	</head>
	<body>
		MOD:<%=com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus()%><br><br>
		DEL:<%=com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus()%><br><br>
		PUB:<%=com.tdil.simon.data.model.Site.getPUBLIC_SITE().getStatus()%><br><br>
	
		<% 	if(com.tdil.simon.data.model.Site.EVENT.equals(com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus())) { %>
			<% 	if(com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
				<html:link action="/initSign" >Iniciar firmas</html:link><br><br>
				<span id="privCount"><%=com.tdil.simon.utils.ObservationUtils.countPrivateObservationsForNegotiatedVersion()%></span>
			<% } %>
			<% 	if(com.tdil.simon.data.model.Site.IN_SIGN.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
				En firmas, muestro progreso???
				Hay alguna accion para cerrar esto?
			<% } %>
			<% 	if(com.tdil.simon.data.model.Site.NORMAL.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
				<html:link action="/initNegotiation" >Iniciar negociacion</html:link><br><br>
			<% } %>
		<% } %>
		<br>
				
		<!-- New code -->
		
	
	
	</body>
</html:html>
<%@ include file="includes/header.jsp" %>

