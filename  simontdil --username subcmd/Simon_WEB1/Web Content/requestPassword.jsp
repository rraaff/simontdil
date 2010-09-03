<%@ page info="requestPassword"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerLogoff.jsp" %>
<style type="text/css">
#centrador {
	padding-left:80px;
}  
</style>
<html:html>
<html:form method="POST" action="/requestPassword">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
	<tr>
		<td width="960">
			<table width="340" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="320" height="19" align="center"><img src="images/titles/blanqueoDeContrasena.gif" alt="Ingreso al Sitio" width="178" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="320" height="144"><!-- corte tabla template -->
						<table width="320" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" colspan="3"><p align="left">En caso de no recordar su contrase&ntilde;a, le sugerimos pedir un blanqu&eacute;o de la misma, completando su usuario, la direcci&oacute;n de E-Mail utilizada para su registraci&oacute;n y haciendo clic  en el bot&oacute;n &ldquo;solicitar contrase&ntilde;a&rdquo;.</p><p align="left">Si no recuerda su Usuario o la direcci&oacute;n de E-Mail utilizada para su registraci&oacute;n, comun&iacute;quese con ......</p></td>
							</tr>
							<tr>
								<td align="right" width="82">Usuario:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="231" align="left"><html:text name="RequestPaswwordForm" property="username" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">E-mail:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:text name="RequestPaswwordForm" property="email" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3">
									<html:submit>
										<bean:message key="requestPassword.request"/>
									</html:submit>
									<!--  a class="button" href="pedirnuevaclave_error.html"><span>Solicitar contase&ntilde;a</span></a -->
								</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3"><div id="centrador"><a class="button" href="login.jsp"><span>Volver al inicio</span></a></div></td>
							</tr>
						</table>					
						<!-- corte tabla template -->
						<span class="errorText"><html:errors property="general" /></span>
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
		<!-- fin tabla template -->
		</td>
	</tr>
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
</table>
</div>
</html:form>
</html:html>
<%@ include file="includes/footer.jsp" %>