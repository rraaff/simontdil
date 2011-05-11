<%@ page info="manageUsersOfGroupABM"%>
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
	height:290px;
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
<script type="text/javascript">
function init_dw_Scroll() {
    var wndo = new dw_scrollObj('main', 'lyr1');
    wndo.setUpScrollbar("dragBar", "track", "v", 1, 1);
    wndo.setUpScrollControls('scrollbar');
}

// if code supported, link in the style sheet and call the init function onload
if ( dw_scrollObj.isSupported() ) {
    dw_Util.writeStyleSheet('styles/scrollbar_demo.css')
    dw_Event.add( window, 'load', init_dw_Scroll);
}
</script>
<div id="content">
<html:form method="POST" action="/manageUsersOfGroupABM">
<input type="hidden" name="indexOperation" value=""/>
<input type="hidden" name="indexClicked"/>
<table width="95%" height="560" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="35%">
			<!-- inicio tabla template -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="500" valign="top">
						<!-- corte tabla template -->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" colspan="3"><img src="images/null.gif" width="1" height="25"></td>
							</tr>
							<tr>
								<td width="93" align="right"><%=ResourceBundleCache.get(getServletInfo(), "grupo")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="250" align="left"><bean:write name="ManageUsersOfGroupABMForm" property="userGroupName" /></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
														<tr>
								<td width="103" align="right"><%=ResourceBundleCache.get(getServletInfo(), "usuario")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="240" align="left">
									<html:select name="ManageUsersOfGroupABMForm" property="systemUserId" styleClass="textfield_effect">
										<html:optionsCollection name="ManageUsersOfGroupABMForm" property="allUsersFiltered" value="id" label="nameAndCountry"/>
									</html:select></td>
							</tr>

							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" height="25" align="center">
										<html:submit property="operation">
											<%=ResourceBundleCache.get(getServletInfo(), "agregar")%>
										</html:submit>
								</td>
							</tr>
							<tr>
								<td colspan="3" height="16"><img src="images/null.gif" width="1" height="16"></td>
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
		</td>
		<td width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="65%">
			<!-- inicio tabla template -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" background="images/interfaces/topTitle.gif" align="left"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "tituloListado")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%" height="500" valign="top">
					<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1" style="width:inherit;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<tr>
										<td width="70%" height="20" align="left"><%=ResourceBundleCache.get(getServletInfo(), "usuario")%></td>
										<td width="70%" height="20" align="left"><%=ResourceBundleCache.get(getServletInfo(), "delegacion")%></td>
										<td width="60"><%=ResourceBundleCache.get(getServletInfo(), "quitar")%></td>
									</tr> 
									<logic:iterate name="ManageUsersOfGroupABMForm" property="allGroupMembers" id="iterGroupMember" indexId="iterIndex"> 
										<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
											<td align="left"><bean:write name="iterGroupMember" property="systemUserName" /></td>
											<td align="left"><bean:write name="iterGroupMember" property="countryName" /></td>
											<td><logic:equal name="iterGroupMember" property="deleted" value="false">
													<%=com.tdil.simon.web.ButtonGenerator.getIndexedButton("ManageUsersOfGroupABMForm","botones","quitar", iterIndex)%>
												</logic:equal>
											</td>
										</tr> 
									</logic:iterate>
									<tr>
										<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
								</table>
							<!-- corte tabla template -->
							</div>
						</div>
					</td>
					<td width="30" align="right">
					<div id="scrollbar" style="width:20px; height:460px; float:right;">
					<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track" style="height:436px;">
							<div id="dragBar"></div>
						</div>
						<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
					</div></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center"><html:link  action="editUserGroup.st?" paramName="ManageUsersOfGroupABMForm" paramProperty="userGroupId" paramId="id">
			<%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","volver")%>
		</html:link></td>
	</tr>
</table>

</html:form>
</div>
<%@ include file="includes/footer.jsp" %>