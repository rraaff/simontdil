<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title></title>
	</head>
	<body>
		<a href="moderatorHome.jsp">Home</a><br>
			title<html:text name="CreateDocumentForm" property="title" disabled="true"/><br>
			Version: <bean:write name="CreateDocumentForm" property="versionNumber"/>
			Nombre version: <html:text name="CreateDocumentForm" property="versionName" disabled="true"/><br>
			dia<html:text name="CreateDocumentForm" property="limitObservationsDay" disabled="true"/><br>
			mes<html:text name="CreateDocumentForm" property="limitObservationsMonth" disabled="true"/><br>
			prop decl<html:radio name="CreateDocumentForm" property="documentType" value="typeOne" disabled="true"/>
			plan de accion<html:radio name="CreateDocumentForm" property="documentType" value="typeTwo" disabled="true"/><br>
	
		<html:errors property="general" />
		<html:form method="POST" action="/createDocumentActionParagraph">
			<html:textarea name="CreateDocumentForm" property="introduction"/>
		<html:submit property="operation">
			<bean:message key="createDocument.back"/>
		</html:submit>
		<html:submit property="operation">
			<bean:message key="createDocument.addParagraphs"/>
		</html:submit>
		</html:form>
	</body>
</html:html>

