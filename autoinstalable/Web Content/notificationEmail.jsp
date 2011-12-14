<%@ page info="notificationEmail"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<style type="text/css">
/* hide from incapable browsers */
div#main {
	background-color:#FFFFFF;
	# width:100%;
	* width:100%;
	width:inherit;
	height:480px;
	font-size: 10px;
}
div#scrollbar {
	display:none;
}
</style>
<script src="scripts/dw_event.js" type="text/javascript"></script>
<script src="scripts/dw_scroll.js" type="text/javascript"></script>
<script src="scripts/dw_scrollbar.js" type="text/javascript"></script>
<script src="scripts/scroll_controls.js" type="text/javascript"></script>
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
<html:form method="POST" action="/notificationEmailSaveAction">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
			<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
			<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
		</tr>
		<tr>
			<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
			<td width="9"><img src="images/null.gif" width="9" height="1"></td>
			<td height="500" align="center" valign="top">
				<!-- corte tabla template -->
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="3" height="75"><img src="images/null.gif" width="1" height="75"></td>
					</tr>
					<tr>
						<td width="100"><%=ResourceBundleCache.get(getServletInfo(), "from")%>:</td>
						<td width="7"><img src="images/null.gif" width="7" height="1"></td>
						<td width="300" align="left"><html:text name="NotificationEmail" property="emailFrom" styleClass="textfield_effect_300" /><html:errors property="email.from" /></td>
					</tr>
					<logic:equal name="NotificationEmail" property="emailKey" value="passworreset">
					<tr>
						<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
					</tr>
					<tr>
						<td><%=ResourceBundleCache.get(getServletInfo(), "to")%>:</td>
						<td><img src="images/null.gif" width="7" height="1"></td>
						<td><html:text name="NotificationEmail" property="emailTo" styleClass="textfield_effect_300" /><html:errors property="email.to" /></td>
					</tr>
					</logic:equal>
					<tr>
						<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
					</tr>
					<tr>
						<td><%=ResourceBundleCache.get(getServletInfo(), "subject")%>:</td>
						<td><img src="images/null.gif" width="7" height="1"></td>
						<td><html:text name="NotificationEmail" property="emailSubject" styleClass="textfield_effect_300" /><html:errors property="email.subject" /></td>
					</tr>
					<tr>
						<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
					</tr>
					<tr>
						<td valign="top"><%=ResourceBundleCache.get(getServletInfo(), "body")%>:</td><!-- cols="100" rows="10" -->
						<td><img src="images/null.gif" width="7" height="1"></td>
						<td><html:textarea name="NotificationEmail" property="emailText" styleClass="textfield_effect_area" /><html:errors property="email.text" /></td>
					</tr>
					<tr>
						<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
					</tr>
					<tr>
						<td><img src="images/null.gif" width="1" height="1"></td>
						<td><img src="images/null.gif" width="1" height="1"></td>
						<td align="center"><html:submit property="operation">
												<%=ResourceBundleCache.get(getServletInfo(), "modificar")%>
											</html:submit></td>
					</tr>
					<tr>
						<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
					</tr>
				</table>					
				<!-- corte tabla template -->
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
</html:form>
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>