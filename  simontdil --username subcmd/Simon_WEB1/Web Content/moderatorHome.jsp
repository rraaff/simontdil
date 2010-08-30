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
		<style>
		 .rowODD { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; text-transform: none; color: #FF0000; text-decoration: none }
		 .rowEVEN { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; text-transform: none; color: #FF00FF; text-decoration: none }
		</style>
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
		<logic:equal name="ModeratorHome" property="hasTypeOne" value="true"> 
			<html:link  action="/goToViewVersion.st?" paramName="ModeratorHome" paramProperty="typeOne.id" paramId="versionID"><bean:write name="ModeratorHome" property="typeOne.documentTitle" /></html:link><br>
			Version: <bean:write name="ModeratorHome" property="typeOne.number" /> - <bean:write name="ModeratorHome" property="typeOne.name" /><br>
			Fecha limite: <bean:write name="ModeratorHome" property="typeOne.limitObservationsString" />
		</logic:equal>
		
		<br><br>
		<logic:equal name="ModeratorHome" property="hasTypeTwo" value="true"> 
			<html:link  action="/goToViewVersion.st?" paramName="ModeratorHome" paramProperty="typeTwo.id" paramId="versionID"><bean:write name="ModeratorHome" property="typeTwo.documentTitle" /></html:link><br>
			Version: <bean:write name="ModeratorHome" property="typeTwo.number" /> - <bean:write name="ModeratorHome" property="typeTwo.name" /><br>
			Fecha limite: <bean:write name="ModeratorHome" property="typeTwo.limitObservationsString" />
		</logic:equal>
	
		<br><br>
		Documentos Secundarios<br>
		<logic:iterate name="ModeratorHome" property="otherDocumentsList" id="doc" indexId="referenceListIndex"> 
		<table>
		<tr> 
			<td class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><bean:write name="doc" property="title" /></td>
		</tr> 
		</table>
		</logic:iterate>
		
		<br><br>
		Documentos de Referencia<br>
		<logic:iterate name="ModeratorHome" property="referenceList" id="ref" indexId="referenceListIndex"> 
		<table>
		<tr> 
			<td class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><bean:write name="ref" property="title" /></td>
		</tr> 
		</table>
		</logic:iterate>
	</body>
</html:html>
<%@ include file="includes/footer.jsp" %>

