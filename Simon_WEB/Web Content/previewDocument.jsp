<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>Preview previw</title>
	</head>
	<body>
	
	<a href="moderatorHome.jsp">Home</a><br>
	
	<bean:write name="CreateDocumentForm" property="title"/><br>
	<bean:write name="CreateDocumentForm" property="versionNumber"/><br>
	<bean:write name="CreateDocumentForm" property="versionName"/><br>
	<bean:write name="CreateDocumentForm" property="limitObservationsDay"/><br>
	<bean:write name="CreateDocumentForm" property="limitObservationsMonth"/><br>
	
	<logic:iterate name="CreateDocumentForm" property="previewParagraphs" id="paragraph"> 
	<pre> 
		<%=paragraph%>
	</pre> 
	</logic:iterate>
	
		<html:errors property="general" />
		<html:form method="POST" action="/previewDocument">
			
		<html:submit property="operation">
			<bean:message key="createDocument.preview.editParagraphs"/>
		</html:submit>
		<html:submit property="operation">
			<bean:message key="createDocument.preview.save"/>
		</html:submit>
		
		<html:submit property="operation">
			<bean:message key="createDocument.preview.consolidate"/>
		</html:submit>
		</html:form>
	</body>
</html:html>

