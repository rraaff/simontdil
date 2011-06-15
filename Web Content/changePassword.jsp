<%@ page info="changePassword"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerLogoff.jsp" %>
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
<html:html>
<html:form method="POST" action="/changePassword">
<table width="100%" height="590" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td valign="middle">
			<!-- inicio tabla template -->
			<table width="440" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="420" height="19" align="center"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="420">
						<!-- corte tabla template -->
						<table width="420" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td height="25" colspan="3"><p align="left"><%=ResourceBundleCache.get(getServletInfo(), "ingreseClaveProvisoria")%></p></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right" width="162"><%=ResourceBundleCache.get(getServletInfo(), "usuario")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="251" align="left"><html:text name="ChangePassword" property="username" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right"><%=ResourceBundleCache.get(getServletInfo(), "email")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:text name="ChangePassword" property="email" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right"><%=ResourceBundleCache.get(getServletInfo(), "claveProvisoria")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:password name="ChangePassword" property="password" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right"><%=ResourceBundleCache.get(getServletInfo(), "nuevaContrasenia")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:password name="ChangePassword" property="newPassword" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right"><%=ResourceBundleCache.get(getServletInfo(), "repetirNuevaContrasenia")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:password name="ChangePassword" property="retypeNewPassword" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<html:submit>
									<%=ResourceBundleCache.get(getServletInfo(), "crear")%>
								</html:submit></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
						</table>					
						<!-- corte tabla template --></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template --></td>
	</tr>
</table>
</html:form>
</html:html>
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footerClean.jsp" %>