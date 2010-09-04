<%@ page info="resetPassword"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:form method="POST" action="/resetPasswordAction">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
	</tr>
	<tr>
		<td height="20">&nbsp;</td>
		<td>Usuario</td>
		<td>Nombre</td>
		<td>email</td>
		<td>Delegación</td>
		<td>Solicitó blanqueo</td>
		<td>Clave Provisoria</td>
	</tr>
	<logic:iterate name="ResetPassword" property="list" id="userForReset" indexId="iterIndex"> 
		<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
			<td height="28">
			<html:multibox name="ResetPassword" property="selectedIds">
				<bean:write name="userForReset" property="id"/>
			</html:multibox>
			</td>
			<td><bean:write name="userForReset" property="username" /></td>
			<td><bean:write name="userForReset" property="name" /></td> 
			<td><bean:write name="userForReset" property="email" /></td>
			<td><bean:write name="userForReset" property="countryName" /></td>
			<td><logic:equal name="userForReset" property="passwordResetRequest" value="true">SI</logic:equal>
			<logic:notEqual name="userForReset" property="passwordResetRequest" value="true">NO</logic:notEqual>
			</td>
			<td><bean:write name="userForReset" property="password" /></td>
		</tr> 
	</logic:iterate>
</table>
	<script type="text/javascript">
		function selectAll() {
			var checksObj = document.ResetPassword.selectedIds;
			if (checksObj.length > 0) {
				var i = 0;
				for (i = 0; i < checksObj.length; i++) {
					checksObj[i].checked = true;
				}
			} else {
				checksObj.checked = true;
			}
		}
	</script>
	<input type="button" value="<bean:message key="resetPassword.selectAll"/>" onclick="selectAll()">				
	</input>
	<html:submit property="operation">
		<bean:message key="resetPassword.resetPassword"/>
	</html:submit>
</html:form>

