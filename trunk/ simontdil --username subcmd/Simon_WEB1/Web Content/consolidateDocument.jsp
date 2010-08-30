<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>Consolidate</title>
	</head>
	<body>
		
		<html:errors property="general" />
		<html:form method="POST" action="/consolidateDocument">
		
		<br>
		<html:textarea name="CreateDocumentForm" property="consolidateText"/>
		<br>
		<html:submit property="operation">
			<bean:message key="createDocument.consolidate.save"/>
		</html:submit>
		</html:form>
	</body>
</html:html>

