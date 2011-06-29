<%@ page info="login"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerLogoff.jsp" %>

<%
String cookieValue = request.getParameter("language");
if (cookieValue == null || org.apache.commons.lang.StringUtils.isEmpty(cookieValue)) {
	String cookieName = com.tdil.simon.web.SetLanguageFilter.USER_LANGUAGE;
	Cookie cookies [] = request.getCookies ();
	Cookie myCookie = null;
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies [i].getName().equals (cookieName)) {
				myCookie = cookies[i];
				break;
			}
		}
	}
	if (myCookie != null) { 
		cookieValue = new String(new sun.misc.BASE64Decoder().decodeBuffer(myCookie.getValue()));
	}
}
%>

<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
<html:form method="POST" action="/login">
<html:hidden name="LoginForm" property="operation" value=""/>
<table height="590" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td valign="middle">
			<table width="500" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="3"><p>Member countries should make use of online registration  to register their delegates to the meetings. The online registration system  will be available from july 22nd. Only the Focal Point of each country or  persons authorized by him may access this section with personal password.  Delegates are strongly encouraged to register in this maner in order to avoid  delays.</p><p>Los pa&iacute;ses miembros deber&iacute;an hacer uso del registro en  l&iacute;nea, el cual estar&aacute; disponible a partir del 22 de julio, a fin de registrar a  sus delegados a las Reuniones. Solamente el Punto Focal de cada pa&iacute;s o personas  autorizadas por &eacute;l podr&aacute;n acceder a esta secci&oacute;n con su clave personal. Se  recomienda que los delegados se registren de esta manera para evitar demoras.</p></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="480" height="19" align="center"><!--  % =ResourceBundleCache.get(getServletInfo(), "titulo") % --></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="480"><!-- corte tabla template -->
						<table width="480" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right" width="123"><%=ResourceBundleCache.get(getServletInfo(), "usuario")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="250"><html:text name="LoginForm" property="username" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right"><%=ResourceBundleCache.get(getServletInfo(), "contrasenia")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td><html:password name="LoginForm" property="password" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right"><%=ResourceBundleCache.get(getServletInfo(), "lenguage")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td><html:select property="language" styleClass="textfield_effect">
							        <html:optionsCollection name="LoginForm" property="allLanguage" value="language" label="language"/>
									</html:select>
									<% if (cookieValue != null) { %>
									<script>
										var langSelObject = document.forms['LoginForm'].elements['language'];
										for( var i = langSelObject.options.length-1;i>=0; i-- ){
											if(langSelObject.options[i].value=='<%=cookieValue%>') {
												langSelObject.selectedIndex=i;
											}
										}
									</script>
									<% } else { %>
										<script>
										var langSelObject = document.forms['LoginForm'].elements['language'];
										for( var i = langSelObject.options.length-1;i>=0; i-- ){
											if(langSelObject.options[i].value=='<%=com.tdil.simon.web.SystemConfig.getSystemLanguage()%>') {
												langSelObject.selectedIndex=i;
											}
										}
										</script>
									<% } %>
									</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<logic:equal name="LoginForm" property="canForce" value="false">
										<jsp:include page="./includes/button.jsp">
											<jsp:param name="_form" value="LoginForm" />
											<jsp:param name="_context" value="login" />
											<jsp:param name="_key" value="ingresar" />
										</jsp:include> 
									</logic:equal>
									<logic:equal name="LoginForm" property="canForce" value="true">
										<jsp:include page="./includes/button.jsp">
											<jsp:param name="_form" value="LoginForm" />
											<jsp:param name="_context" value="login" />
											<jsp:param name="_key" value="desloguearEIngresar" />
										</jsp:include> 
									</logic:equal>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="center"><span class="errorText"><%=com.tdil.simon.web.ErrorFormatter.getErrorFrom(request, "general")%></span></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<!--tr>
								<td colspan="3" align="center"><%=ResourceBundleCache.get(getServletInfo(), "pedirNuevaContrasenia")%></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" align="center" valign="middle">
									<jsp:include page="./includes/button.jsp">
											<jsp:param name="_form" value="LoginForm" />
											<jsp:param name="_context" value="login" />
											<jsp:param name="_key" value="pedirNuevaContrasenia" />
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
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footerClean.jsp" %>