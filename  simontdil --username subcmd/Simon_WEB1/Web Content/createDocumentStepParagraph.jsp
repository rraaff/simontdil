<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
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
			
			<html:textarea name="CreateDocumentForm" property="introduction" disabled="true"/>
	
		<html:errors property="general" />
		<html:form method="POST" action="/paragraphNavigation">
		Numero:<bean:write name="CreateDocumentForm" property="paragraphForDisplay" />
		Oculto:<bean:write name="CreateDocumentForm" property="paragraphHidden" />
		<br>
		<html:textarea name="CreateDocumentForm" property="paragraphText"/>
		<br>
		<!-- Boton prev -->
		<logic:notEqual name="CreateDocumentForm" property="backDisabled" value="true">
			<html:submit property="operation">
			<bean:message key="createDocument.paragraphs.back" />
			</html:submit>
		</logic:notEqual>
		<logic:equal name="CreateDocumentForm" property="backDisabled" value="true">
			<html:submit property="operation" disabled="true">
			<bean:message key="createDocument.paragraphs.back" />
			</html:submit>
		</logic:equal>
		
		<!-- Boton Next -->
		<logic:notEqual name="CreateDocumentForm" property="nextDisabled" value="true">
			<html:submit property="operation">
			<bean:message key="createDocument.paragraphs.next"/>
		</html:submit>
		</logic:notEqual>
		<logic:equal name="CreateDocumentForm" property="nextDisabled" value="true">
			<html:submit property="operation" disabled="true">
			<bean:message key="createDocument.paragraphs.next"/>
		</html:submit>
		</logic:equal>
		
		<logic:equal name="CreateDocumentForm" property="last" value="true">
			<html:submit property="operation">
			<bean:message key="createDocument.paragraphs.add"/>
			</html:submit>
		</logic:equal>
		<logic:notEqual name="CreateDocumentForm" property="last" value="true">
			<html:submit property="operation" disabled="true">
			<bean:message key="createDocument.paragraphs.add"/>
			</html:submit>
		</logic:notEqual>
		
		<html:submit property="operation">
			<bean:write name="CreateDocumentForm" property="hideOrUnhide" />
		</html:submit>
		
		<%if (isModerator) { %>
		<logic:equal name="CreateDocumentForm" property="versionNegotiated" value="true">
			<html:submit property="operation">
				<bean:message key="createDocument.paragraphs.pushData"/>
			</html:submit>
		</logic:equal>
		<% } %>
	
		<br>
		<html:submit property="operation">
			<bean:message key="createDocument.paragraphs.modifyIntroduction"/>
		</html:submit>
		<html:submit property="operation">
			<bean:message key="createDocument.paragraphs.preview"/>
		</html:submit>
		</html:form>
		
		
	</body>
</html:html>

