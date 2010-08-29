<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>request Password</title>
	</head>
	<body>
		<html:errors property="general" />
	
		<html:form method="POST" action="/requestPassword">
		<html:text name="RequestPaswwordForm" property="username"/>
		<html:text name="RequestPaswwordForm" property="email"/>
		<br>
		<html:submit>
			<bean:message key="requestPassword.request"/>
		</html:submit>
		
		<br>
		<a href="login.jsp">Volver al inicio</a><br>
		</html:form>
	</body>
</html:html>

