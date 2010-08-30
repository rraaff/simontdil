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
	
		<html:errors property="general" />
		<html:form method="POST" action="/createDocumentActionStep2">
			title<html:text name="CreateDocumentForm" property="title"/><br>
			<html:errors property="title" /><br>
			Version: <bean:write name="CreateDocumentForm" property="versionNumber" />
			Nombre version: <html:text name="CreateDocumentForm" property="versionName"/><br>
			<html:errors property="versionName" /><br>
			Is principal<html:checkbox name="CreateDocumentForm" property="principal"/><br>
			dia<html:text name="CreateDocumentForm" property="limitObservationsDay"/><br>
			mes<html:text name="CreateDocumentForm" property="limitObservationsMonth"/><br>
			prop decl<html:radio name="CreateDocumentForm" property="documentType" value="typeOne"/>
			plan de accion<html:radio name="CreateDocumentForm" property="documentType" value="typeTwo"/><br>
			
			<html:submit property="operation">
				<bean:message key="createDocument.next"/>
			</html:submit>
		</html:form>
	</body>
</html:html>

