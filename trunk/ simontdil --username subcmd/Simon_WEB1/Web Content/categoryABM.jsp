<%@ page info="categoryABM"%>
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
<html:form method="POST" action="/categoryABM">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="370">
			<!-- inicio tabla template -->
			<table width="350" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="350" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Editar categorías de librería</div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="350" height="290">
						<!-- corte tabla template -->
						<table width="350" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" colspan="3"><img src="images/null.gif" width="1" height="25"></td>
							</tr>
							<tr>
								<td width="93" align="right">Nombre:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="250" align="left"><html:text name="CategoryABMForm" property="name" styleClass="textfield_effect"/><html:errors property="category.name" /></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" height="25" align="center">
									<logic:equal name="CategoryABMForm" property="id" value="0">
										<html:submit property="operation">
											<bean:message key="categoryABM.create"/>
										</html:submit>
									</logic:equal>
									<logic:notEqual name="CategoryABMForm" property="id" value="0">
										<html:submit property="operation">
										<bean:message key="categoryABM.modify"/>
										</html:submit>
									</logic:notEqual>
									<html:submit property="operation">
										<bean:message key="categoryABM.cancel"/>
									</html:submit></td>
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
		<td width="550">
			<!-- inicio tabla template -->
			<table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" background="images/interfaces/topTitle.gif" width="530" height="19" align="left"><div id="blockTitle">Listado de categor&iacute;as</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="530" height="144">
					<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1">
								<table width="500" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<tr>
										<td width="70%" height="20" align="left">Nombre</td>
										<td width="60">Editar</td>
										<td width="60">Borrar/Reactivar</td>
									</tr> 
									<logic:iterate name="CategoryABMForm" property="allCategories" id="iterCategory" indexId="iterIndex"> 
										<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
											<td <%= ((com.tdil.simon.data.model.PersistentObject)iterCategory).isDeleted() ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterCategory" property="name" /></td>
											<td><html:link  action="editCategory.st?" paramName="iterCategory" paramProperty="id" paramId="id">
												<img src="images/buttons/editar.png" width="50" height="24" border="0">
											</html:link>
											<td><logic:equal name="iterCategory" property="deleted" value="false">
													<html:image property="deleteImages" indexed="true" value="id"  src="images/buttons/desactivar.png"></html:image>
												</logic:equal>
												<logic:equal name="iterCategory" property="deleted" value="true">
													<html:image property="reactivateImages" indexed="true" value="id"  src="images/buttons/activar.png"></html:image>
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
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
</table>
</div>
</html:form>
<%@ include file="includes/footer.jsp" %>