<%@ page info="moderatorHome"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
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
<%@ include file="includes/menu.jsp" %>
<%@ include file="includes/leftContentModerator.jsp" %>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<!-- inicio tabla template -->
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "Documentos")%></div></td>
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
										<td class="titles"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></td>
										<td class="titles"><%=ResourceBundleCache.get(getServletInfo(), "version")%></td>
										<td class="titles"><%=ResourceBundleCache.get(getServletInfo(), "nombreDeVersion")%></td>
										<td class="titles"><%=ResourceBundleCache.get(getServletInfo(), "limiteObservaciones")%></td>
										<td class="titles"><%=ResourceBundleCache.get(getServletInfo(), "tipoDeDocumento")%></td>
										<td class="titles"><%=ResourceBundleCache.get(getServletInfo(), "subTipoDeDocumento")%></td>
										<td colspan="4" class="titles"></td>
									</tr>
									<logic:iterate name="ModeratorHome" property="principalVersions" id="doc" indexId="principalIndex">
										<tr class="row<%= (principalIndex % 2 == 0) ? "ODD" : "EVEN" %>">
											<td height="22" align="left"><html:link  action="/goToViewVersion.st?" paramName="doc" paramProperty="id" paramId="versionID"><bean:write name="doc" property="documentTitle" /></html:link></td>
											<td><bean:write name="doc" property="number" /></td>
											<td><bean:write name="doc" property="name" /></td>
											<td><bean:write name="doc" property="limitObservationsString" /></td>
											<td><bean:write name="doc" property="documentTypeName" /></td>
											<td><bean:write name="doc" property="documentSubTypeName" /></td>
											<td colspan="4"></td>
										</tr>
									</logic:iterate>
									<tr>
										<td colspan="10" height="1" bgcolor="#333333"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td colspan="10" height="20"><img src="images/null.gif" width="1" height="20"></td>
									</tr>
									<tr>
										<td colspan="10" class="titles"><%=ResourceBundleCache.get(getServletInfo(), "documentosEspecificos")%></td>
									</tr>
									<logic:iterate name="ModeratorHome" property="otherDocumentsList" id="doc" indexId="referenceListIndex"> 
										<tr> 
											<td colspan="10" height="22" class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><html:link action="/goToViewLastVersionOfDocument.st?" paramName="doc" paramProperty="id" paramId="documentID"><bean:write name="doc" property="title" /></html:link></td>
										</tr>
									</logic:iterate>
									<tr>
										<td colspan="10" height="1" bgcolor="#333333"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td colspan="10" height="20"><img src="images/null.gif" width="1" height="20"></td>
									</tr>
									<tr>
										<td colspan="10" class="titles"><%=ResourceBundleCache.get(getServletInfo(), "documentosInformativos")%></td>
									</tr>
									<logic:iterate name="ModeratorHome" property="referenceList" id="ref" indexId="referenceListIndex"> 
										<tr> 
											<td colspan="10" height="22" class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><bean:write name="ref" property="title" /></td>
										</tr>
									</logic:iterate>
									<tr>
										<td colspan="10" height="40" align="center"><html:link  action="/goToReferenceDocLibrary.st">
											<%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","irallistado")%>
										</html:link></td>
									</tr>
									<tr>
										<td colspan="10" height="1" bgcolor="#333333"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
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
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>