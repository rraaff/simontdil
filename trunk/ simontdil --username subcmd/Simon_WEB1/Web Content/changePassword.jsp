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
		<html:form method="POST" action="/changePassword">
		nombere<html:text name="ChangePassword" property="username" /><br>
		email<html:text name="ChangePassword" property="email"/><br>
		password<html:text name="ChangePassword" property="password"/><br>
		new pass<html:text name="ChangePassword" property="newPassword"/><br>
		retype new pass<html:text name="ChangePassword" property="retypeNewPassword"/><br>
		
		
		<html:submit>
			<bean:message key="changePassword.create"/>
		</html:submit>

		</html:form>
	</body>
</html:html>

