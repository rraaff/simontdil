<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>List DOcuments</title>
	</head>
	<body>
	

		<table border="1">
			<tr>
				<td>Parrafo</td>
				<td>date</td>
				<td>Delegate</td>
				<td>COuntry</td>
				<td>text</td>
			</tr>
			<logic:iterate name="ViewVersion" property="observations" id="observation"> 
			<tr> 
				<td><bean:write name="observation" property="paragraphNumber" /></td>
				<td><bean:write name="observation" property="creationDate" /></td>
				<td><bean:write name="observation" property="name" /></td> 
				<td><bean:write name="observation" property="countryName" /></td>
				<td><bean:write name="observation" property="observationText" /></td>
			</tr> 
			</logic:iterate>
		</table>
	</body>
</html:html>

