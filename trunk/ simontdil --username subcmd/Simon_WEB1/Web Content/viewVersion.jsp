<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>View Document</title>
	</head>
	<body>
	
	<a href="./goToModeratorHome.st">Home</a><br>
	<a href="./goToDelegateHome.st">Home</a><br>
	<html:form action="/viewVersionAction">
	
	<%if (isModerator) { %>
		<logic:equal name="ViewVersion" property="versionCanBeNegotiated" value="true">
			<html:submit property="operation">
				<bean:message key="viewVersion.initNegotiation"/>
			</html:submit>
		</logic:equal>
	<% } %>

	<bean:write name="ViewVersion" property="version.document.title" /><br>
	Version: <bean:write name="ViewVersion" property="version.version.number" /> - <bean:write name="ViewVersion" property="version.version.name" /><br>
	Fecha limite: <bean:write name="ViewVersion" property="version.version.limitObservationsString" /><br>

	<html:submit property="operation">
		<bean:message key="viewVersion.downloadPdf"/>
	</html:submit>
	Versiones disponibles<br>
	<table border="1"><tr>
	<logic:iterate name="ViewVersion" property="version.allVersions" id="otherVersion"> 
		<td><html:link  action="/goToViewVersion.st?" paramName="otherVersion" paramProperty="id" paramId="versionID"><bean:write name="otherVersion" property="number" /></html:link></td>
	</logic:iterate>
	</tr></table>
	
	<% if (isDelegate) { %>
		<logic:equal name="ViewVersion" property="versionCanBeCommented" value="true">
			<script type="text/javascript">
				function doAdd() {
					var paragraphNumber = document.getElementById('pNumber').value;
					var newPar = document.getElementById('pNewParagraph').checked ? "true" : "false";
					var pText = document.getElementById('pText').value;
					var pVersion = '<bean:write name="ViewVersion" property="version.version.id" />';
					new Ajax.Request('<html:rewrite page="/addObservation.st"/>', {
					    parameters: { pNumber:paragraphNumber, newPar:newPar, pText:pText, pVersion:pVersion},
					    onComplete: 
							function(transport, json) {
							   var result = json.result;
							   if ('OK' == result) {
							   document.getElementById('pNumber').value = "";
							   document.getElementById('pNewParagraph').checked = false;
							   document.getElementById('pText').value = "";
							   	document.getElementById('addCommentLayer').style.display = 'none';
							   } else {
							   	var error = json.error;
							   	document.getElementById('error').innertHTML= error;
							   }
							 }
					});
				}
			</script>
			<div id="addCommentLayer" style="display: none;">
				<table>
					<tr><td id="error"></td><tr>
					<tr><td>parrafo<input type="text" id="pNumber"></td><tr>
					<tr><td>new par<input type="checkbox" id="pNewParagraph"></td><tr>
					<tr><td>observation<textarea id="pText"></textarea></td><tr>
					<tr><td><input type="button" onclick="doAdd()" value="Agregar observacion"><input type="button" onclick="document.getElementById('addCommentLayer').style.display = 'none';" value="Cancelar"></td><tr>
				</table>
			</div>
			<input type="button" value="Añadir observacion" onclick="document.getElementById('addCommentLayer').style.display = '';">
		</logic:equal>
		<logic:notEqual name="ViewVersion" property="versionCanBeCommented" value="true">
			<html:submit property="operation" disabled="true">
				<bean:message key="viewVersion.addObservation"/>
			</html:submit>
		</logic:notEqual>
	<% } else { %>
		<html:submit property="operation" disabled="true">
			<bean:message key="viewVersion.addObservation"/>
		</html:submit>
	<% } %>
	<html:submit property="operation" disabled="true">
		<bean:message key="viewVersion.searchObservations"/>
	</html:submit>
	<html:submit property="operation">
		<bean:message key="viewVersion.listObservations"/>
	</html:submit>
	
	<logic:iterate name="ViewVersion" property="version.paragraphs" id="paragraph"> 
	<p><bean:write name="paragraph" property="paragraphNumber" />.<bean:write name="paragraph" property="paragraphText" /></p> 
	</logic:iterate>
	
	</html:form >
	</body>
</html:html>

