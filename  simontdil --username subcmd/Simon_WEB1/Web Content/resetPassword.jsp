<%@ page info="resetPassword"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<style>
<!--
#portaTabla{
	width:900px;
}
-->
</style>

<html:form method="POST" action="/resetPasswordAction">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
	<tr>
		<td width="940">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="920" height="19" align="left"><div id="blockTitle">Usuarios</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="900" height="144">
					<!-- corte tabla template -->
						<div id="portaTabla">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
								</tr>
								<tr>
									<td height="20">&nbsp;</td>
									<td align="left">Usuario</td>
									<td align="left">Nombre</td>
									<td align="left">E-Mail</td>
									<td align="left">Delegación</td>
									<td width="100">Solicitó blanqueo</td>
									<td width="100">Clave Provisoria</td>
								</tr>
								<logic:iterate name="ResetPassword" property="list" id="userForReset" indexId="iterIndex"> 
									<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
										<td height="28">
										<html:multibox name="ResetPassword" property="selectedIds">
											<bean:write name="userForReset" property="id"/>
										</html:multibox>
										</td>
										<td align="left"><bean:write name="userForReset" property="username" /></td>
										<td align="left"><bean:write name="userForReset" property="name" /></td> 
										<td align="left"><bean:write name="userForReset" property="email" /></td>
										<td align="left"><bean:write name="userForReset" property="countryName" /></td>
										<td><logic:equal name="userForReset" property="passwordResetRequest" value="true">SI</logic:equal>
										<logic:notEqual name="userForReset" property="passwordResetRequest" value="true">NO</logic:notEqual>
										</td>
										<td><bean:write name="userForReset" property="password" /></td>
									</tr> 
								</logic:iterate>
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
								<tr>
									<td colspan="7" height="25"><img src="images/null.gif" width="1" height="25"></td>
								</tr>
							</table>	
						<!-- corte tabla template -->
						</div>
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="530" height="25">
					<input type="button" value="<bean:message key="resetPassword.selectAll"/>" onclick="selectAll()"></input>
					<html:submit property="operation">
						<bean:message key="resetPassword.resetPassword"/>
					</html:submit></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
	<tr>
		<td colspan="3" height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
</table>
</div>
</html:form>
<%@ include file="includes/footer.jsp" %>