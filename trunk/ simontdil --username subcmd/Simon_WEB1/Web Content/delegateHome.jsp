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
		<title>Delegate Home</title>
		<style>
		 .rowODD { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; text-transform: none; color: #FF0000; text-decoration: none }
		 .rowEVEN { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; text-transform: none; color: #FF00FF; text-decoration: none }
		</style>
		<script type="text/javascript">
			function getDelegateSiteStatus() {
				new Ajax.Request('<html:rewrite page="/getDelegateSiteStatus.st"/>', {
			    onComplete: function(transport, json) {
			        var sitestatus = json.sitestatus;
			        if (sitestatus != 'NORMAL') {
			        	window.location='<html:rewrite page="/goToDelegateNegotiation.st"/>';
			        }
			      }
			   });
			}
			timer = setInterval("getDelegateSiteStatus()",1000);
		</script>
	</head>
	<body>
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

