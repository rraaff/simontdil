<%@ page info="systemUserABM"%>
<%@ page contentType="text/html; charset=Cp1252" %>
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
	width:500px;
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
<html:form method="POST" action="/systemUserABM">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
	<tr>
		<td width="370">
			<!-- inicio tabla template -->
			<table width="350" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="350" height="19" align="left"><div id="blockTitle">Editar usuarios del sistema</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="350" height="144"><!-- corte tabla template -->
						<table width="350" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3" height="40"><img src="images/null.gif" width="1" height="40"></td>
							</tr>
							<tr>
								<td width="143" align="right">Nombre:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="200" align="left"><html:text name="SystemUserABM" property="name" /></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td height="24" align="right">Nombre de Usuario:</td>
								<td><img src="images/null.gif" width="7" height="1"></td>
								<td align="left">
									<logic:equal name="SystemUserABM" property="id" value="0">
										<html:text name="SystemUserABM" property="username"/>
									</logic:equal>
									<logic:notEqual name="SystemUserABM" property="id" value="0">
										<html:text name="SystemUserABM" property="username" disabled="true"/>
									</logic:notEqual></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td height="24" align="right">E-Mail:</td>
								<td><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:text name="SystemUserABM" property="email"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td height="24" align="right"><html:checkbox name="SystemUserABM" property="administrator"/></td>
								<td><img src="images/null.gif" width="7" height="1"></td>
								<td align="left">Administrador</td>
							</tr>
							<tr>
								<td height="24" align="right"><html:checkbox name="SystemUserABM" property="moderator"/></td>
								<td><img src="images/null.gif" width="7" height="1"></td>
								<td align="left">Moderador</td>
							</tr>
							<tr>
								<td height="24" align="right"><html:checkbox name="SystemUserABM" property="designer"/></td>
								<td><img src="images/null.gif" width="7" height="1"></td>
								<td align="left">Dise�ador</td>
							</tr>
							<tr>
								<td colspan="3" height="23"><img src="images/null.gif" width="1" height="23"></td>
							</tr>
							<tr>
								<td colspan="3" height="24" align="center">
									<logic:equal name="SystemUserABM" property="id" value="0">
										<html:submit property="operation">
											<bean:message key="systemUserABM.create"/>
										</html:submit>
									</logic:equal>
									<logic:notEqual name="SystemUserABM" property="id" value="0">
										<html:submit property="operation">
										<bean:message key="systemUserABM.modify"/>
										</html:submit>
									</logic:notEqual>
									<html:submit property="operation">
										<bean:message key="systemUserABM.cancel"/>
									</html:submit></td>
							</tr>
							<tr>
								<td colspan="3" height="30"><img src="images/null.gif" width="1" height="30"></td>
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
		<td width="550">
			<!-- inicio tabla template -->
			<table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" background="images/interfaces/topTitle.gif" width="530" height="19" align="left"><div id="blockTitle">Listado de usuarios del sistema</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="530" height="144">
					<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1">
								<table width="505" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<tr>
										<td height="20" align="left">Username</td>
										<td align="left">Nombre</td>
										<td>Permisos</td>
										<td>Habilitado</td>
										<td> </td>
										<td> </td>
									</tr> 
									<logic:iterate name="SystemUserABM" property="allUsers" id="iterUser" indexId="iterIndex"> 
										<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
											<td height="28" align="left"><bean:write name="iterUser" property="username" /></td>
											<td height="28" align="left"><bean:write name="iterUser" property="name" /></td>
											<td><bean:write name="iterUser" property="systemPermissionsString" /></td>
											<td><logic:equal name="iterUser" property="deleted" value="true">No</logic:equal>
											<logic:equal name="iterUser" property="deleted" value="false">Si</logic:equal></td>
											<td><html:link  action="editSystemUser.st?" paramName="iterUser" paramProperty="id" paramId="id">
												<img src="images/buttons/editar.png" width="50" height="24" border="0">
											</html:link>
											<td><logic:equal name="iterUser" property="deleted" value="false">
													<html:image property="deleteImages" indexed="true" value="id" src="images/buttons/desactivar.png" border="0"></html:image>
												</logic:equal>
												<logic:equal name="iterUser" property="deleted" value="true">
													<html:image property="reactivateImages" indexed="true" value="id" src="images/buttons/activar.png" border="0"></html:image>
												</logic:equal></td>
										</tr> 
									</logic:iterate>
									<tr>
										<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
								</table>	
							<!-- corte tabla template -->
							</div>
						</div>
					</td>
					<td width="30" align="right">
					<div id="scrollbar" align="right">
						<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track">
							<div id="dragBar"></div>
						</div>
						<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
					</div></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
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