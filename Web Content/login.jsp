<%@ page info="login"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerLogoff.jsp" %>

<div id="content">
<html:form method="POST" action="/login">
<html:hidden name="LoginForm" property="operation" value=""/>
<table height="590" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td valign="middle">
			<table width="400" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="320" height="19" align="center"><img src="images/titles/ingresoAlSitio.gif" alt="Ingreso al Sitio" width="109" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="380"><!-- corte tabla template -->
						<table width="380" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right" width="123">Usuario:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="250"><html:text name="LoginForm" property="username" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">Contrase&ntilde;a:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td><html:password name="LoginForm" property="password" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<logic:equal name="LoginForm" property="canForce" value="false">
										<jsp:include page="./includes/button.jsp">
											<jsp:param name="_form" value="LoginForm" />
											<jsp:param name="_operation" value="login.enter" />
										</jsp:include> 
									</logic:equal>
									<logic:equal name="LoginForm" property="canForce" value="true">
										<jsp:include page="./includes/button.jsp">
											<jsp:param name="_form" value="LoginForm" />
											<jsp:param name="_operation" value="login.logoutAndEnter" />
										</jsp:include> 
									</logic:equal>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="center"><span class="errorText"><html:errors property="general" /></span></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<!--tr>
								<td colspan="3" align="center">Si ha olvidado su contrase&ntilde;a puede solicitar una nueva.</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" align="center" valign="middle">
									<jsp:include page="./includes/button.jsp">
											<jsp:param name="_form" value="LoginForm" />
											<jsp:param name="_operation" value="login.requestPassword" />
									</jsp:include>
								</td>
							</tr-->
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
						</table>
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</div>
<%@ include file="includes/footerClean.jsp" %>