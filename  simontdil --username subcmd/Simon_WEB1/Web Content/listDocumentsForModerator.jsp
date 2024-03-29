<%@ page info="listDocumentsForModerator"%>
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
	height:470px;
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
<html:form method="POST" action="/documentABM">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<!-- inicio tabla template -->
			<span class="errorText"><html:errors property="general" /></span>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Listado de Documentos</div></td>
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
										<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<tr>
										<td height="20" align="left">Documento</td>
										<td>Versi�n</td>
										<td align="left">Nombre de Versi�n</td>
										<td align="left">Estado</td>
										<td>Tiene Obs.</td>
										<td># de obs.</td>
										<td># nuevos solicitados</td>
										<td width="60"> </td>
										<td width="80"> </td>
										<% if (isModerator) { %><td width="100">Ingreso de Obs.</td> <% } %>
										<td width="5"> </td>
									</tr>
									<logic:iterate name="ListDocument" property="list" id="version" indexId="iterIndex"> 
									<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
										<td <%= ((com.tdil.simon.data.model.PersistentObject)version).isDeleted() ? "class=\"notActive\"" : "" %> height="28" align="left"><bean:write name="version" property="documentTitle" /></td>
										<td <%= ((com.tdil.simon.data.model.PersistentObject)version).isDeleted() ? "class=\"notActive\"" : "" %>><bean:write name="version" property="versionWithSubversion" /></td>
										<td <%= ((com.tdil.simon.data.model.PersistentObject)version).isDeleted() ? "class=\"notActive\"" : "" %> align="left"><bean:write name="version" property="name" /></td>
										<td <%= ((com.tdil.simon.data.model.PersistentObject)version).isDeleted() ? "class=\"notActive\"" : "" %> align="left"><bean:write name="version" property="translatedStatus" /></td> 
										<td <%= ((com.tdil.simon.data.model.PersistentObject)version).isDeleted() ? "class=\"notActive\"" : "" %>><bean:write name="version" property="hasObservationText" /></td>
										<td <%= ((com.tdil.simon.data.model.PersistentObject)version).isDeleted() ? "class=\"notActive\"" : "" %>><bean:write name="version" property="observationCountText" /></td>
										<td <%= ((com.tdil.simon.data.model.PersistentObject)version).isDeleted() ? "class=\"notActive\"" : "" %>><bean:write name="version" property="newParagraphCountText" /></td>
										<% if (((com.tdil.simon.data.model.Version)version).canBeEdited()) { %>  
											<td><html:link  action="editVersion.st?" paramName="version" paramProperty="id" paramId="id"><img src="images/buttons/editar.png" width="50" height="24" border="0"></html:link>
										<% } else { %>  
											<td>-</td>
										<% } %> 
										<td><logic:equal name="version" property="deleted" value="false">
												<html:image property="deleteImages" indexed="true" value="id"  src="images/buttons/desactivar.png"></html:image>
											</logic:equal>
											<logic:equal name="version" property="deleted" value="true">
												<html:image property="reactivateImages" indexed="true" value="id"  src="images/buttons/activar.png"></html:image>
											</logic:equal></td>
										<% if (isModerator) { %><td width="80">
											<logic:equal name="version" property="commentsEnabled" value="true">
												<html:image property="disableCommentsImages" indexed="true" value="id"  src="images/buttons/desactivar.png"></html:image>
											</logic:equal>
											<logic:equal name="version" property="commentsEnabled" value="false">
												<html:image property="enableCommentsImages" indexed="true" value="id"  src="images/buttons/activar.png"></html:image>
											</logic:equal></td>
										<% } %> 
										<td width="5"> </td>
									</tr> 
									</logic:iterate>
									<tr>
										<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
								</table>	
							<!-- corte tabla template -->
							</div>
						</div>
					</td>
					<td width="30" align="right">
					<div id="scrollbar" style="width:20px; height:440px; float:right;">
						<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track" style="height:416px;">
							<div id="dragBar"></div>
						</div>
						<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
					</div></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomCenter.gif" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
</table>
</html:form>
</div>
<%@ include file="includes/footer.jsp" %>