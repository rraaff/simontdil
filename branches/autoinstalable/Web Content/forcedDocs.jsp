<%@ page info="forcedDocs"%>
<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerDelegateForForcedDocs.jsp" %>
<style type="text/css">
/* hide from incapable browsers */
div#main {
	background-color:#FFFFFF;
	# width:100%;
	* width:100%;
	width:inherit;
	height:380px;
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
<jsp:useBean id="delegatePopupBean" scope="session" class="com.tdil.simon.struts.forms.DelegatePopupBean"/>
<% 
	delegatePopupBean.setLoggedUser(user);
	delegatePopupBean.init(); 
%>

<div id="content" style="height:420px;">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td background="images/focalae_set/middle_left.gif" width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="100%">
			<!-- inicio tabla template -->
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%" height="300" valign="top">
					<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1" style="width:inherit;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<% for (com.tdil.simon.data.valueobjects.VersionForListVO principal : delegatePopupBean.getPrincipalVersions()) { %>
										<tr>
											<td colspan="3" height="22" class="rowODD"><a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=principal.getDocumentId()%>"><%=principal.getDocumentTitle()%></a></td>
										</tr>
									<% } %>
									<tr>
										<td colspan="3" height="1" bgcolor="#333333"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td colspan="3" height="20"><img src="images/null.gif" width="1" height="20"></td>
									</tr>
									<tr>
										<td colspan="3" class="titles"><%=ResourceBundleCache.get(getServletInfo(), "documentosEspecificos")%></td>
									</tr>
									<%  int referenceListIndex = 0;
										for (com.tdil.simon.data.model.Document doc : delegatePopupBean.getOtherDocumentsList()) { %> 
										<tr> 
											<td colspan="3" height="22" class="row<%=referenceListIndex % 2 == 0 ? "ODD" : "EVEN"%>"><a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=doc.getId()%>"><%=doc.getTitle()%></a></td>
										</tr>
									<% referenceListIndex = referenceListIndex + 1;
										} %>
									<tr>
										<td colspan="3" height="1" bgcolor="#333333"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td colspan="3" height="20"><img src="images/null.gif" width="1" height="20"></td>
									</tr>
									<tr>
										<td colspan="3" class="titles"><%=ResourceBundleCache.get(getServletInfo(), "documentosInformativos")%></td>
									</tr>
									<%  int iterIndex = 0;
										for (com.tdil.simon.data.model.ReferenceDocument ref : delegatePopupBean.getReferenceList()) { %>
										<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
											<td height="28" align="left"><%=ref.getTitle()%></td>
											<td align="left"><%=ref.getFileName()%></td>
											<td width="80"><a href="./download.do?action=refdoc&fileId=<%=ref.getId()%>">
												<%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","descargar")%>
												</a></td>
										</tr> 
									<% iterIndex = iterIndex + 1;
										} %>
									<tr>
										<td colspan="3" height="1" bgcolor="#333333"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
								</table>	
							<!-- corte tabla template -->
							</div>
						</div>
					</td>
					<td width="30" align="right">
					<div id="scrollbar" style="width:20px; height:380px; float:right;">
						<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track" style="height:356px;">
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
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footerClean.jsp" %>