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
	
	<a href="moderatorHome.jsp">Home</a><br>
	
		<table border="1">
			<tr>
				<td>Documento</td>
				<td>Version</td>
				<td>Estado</td>
				<td>NOmbre de version</td>
				<td>Observaciones</td>
				<td>Cantidad de observaciones</td>
				<td>Edit</td>
			</tr>
			<logic:iterate name="ListDocument" property="list" id="version"> 
			<tr> 
				<td><bean:write name="version" property="documentTitle" /></td>
				<td><bean:write name="version" property="versionWithSubversion" /></td>
				<td><bean:write name="version" property="status" /></td> 
				<td><bean:write name="version" property="name" /></td>
				<td><bean:write name="version" property="hasObservationText" /></td>
				<td><bean:write name="version" property="observationCountText" /></td>
				<% if (!((com.tdil.simon.data.model.Version)version).isHasDraft()) { %>  
					<td><html:link  action="editVersion.st?" paramName="version" paramProperty="id" paramId="id">Edit</html:link>
				<% } else { %>  
					<td>-</td>
				<% } %> 
			</tr> 
			</logic:iterate>
		</table>
	</body>
</html:html>

