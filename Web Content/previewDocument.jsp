<%@ page info="previewDocument"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<style type="text/css">
div#scrollbar {
	display:none;
}
div#main{
	width:auto;
	height:420px;
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
	dw_Event.add( window, 'resize', init_dw_Scroll);
}
</script>
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
	<!-- div id="alcien" style="height:130px; padding-top:10px;" -->
		<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
							<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
							<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
						</tr>
						<tr>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td width="98%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="25" align="left"><%=ResourceBundleCache.get(getServletInfo(), "documento")%>: <bean:write name="CreateDocumentForm" property="title"/> - 
												V <bean:write name="CreateDocumentForm" property="versionNumber"/> - 
											(<bean:write name="CreateDocumentForm" property="versionName"/>) - 
											<%=ResourceBundleCache.get(getServletInfo(), "limiteObservaciones")%>: <bean:write name="CreateDocumentForm" property="limitObservationsString" /></td>
									</tr>
									<tr>
										<td height="230" align="left" valign="top">
										<div id="main">
											<div id="lyr1">
												<logic:iterate name="CreateDocumentForm" property="previewIntroduction" id="intro"> 
													<p class="article"><%=intro%></p>
												</logic:iterate>
												<logic:iterate name="CreateDocumentForm" property="previewParagraphs" id="paragraph"> 
													<p class="article"><%=paragraph%></p>
												</logic:iterate>
											</div>
										</div>
										</td>
									</tr>
								</table>
							</td>
							<td width="30" align="right">
							<div id="scrollbar" style="height:450px;">
								<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
								<div id="track" style="height:426px;">
									<div id="dragBar"></div>
								</div>
								<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
							</div></td>
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
						</tr>
						<tr>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td colspan="2" width="98%" height="10"><img src="images/null.gif" width="1" height="10"></td>
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
						</tr>
						<tr>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td colspan="2" width="98%" height="20" align="center" valign="bottom">
								<html:errors property="general" />
								<html:form method="POST" action="/previewDocument">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "editarParrafos")%>
								</html:submit>
								<!-- Si no esta en negociacion -->
								<logic:equal name="CreateDocumentForm" property="isInNegotiation" value="false">
									<!-- Si no esta en disenio -->
									<logic:equal name="CreateDocumentForm" property="designer" value="false">
										<!-- Si no es portugues -->
										<logic:equal name="CreateDocumentForm" property="portugues" value="false">
											<html:submit property="operation">
												<%=ResourceBundleCache.get(getServletInfo(), "grabarBorrador")%>
											</html:submit>
											<html:submit property="operation">
												<%=ResourceBundleCache.get(getServletInfo(), "consolidar")%>
											</html:submit>
										</logic:equal>
										<!-- Si es portugues -->
										<logic:equal name="CreateDocumentForm" property="portugues" value="true">
											<html:submit property="operation">
												<%=ResourceBundleCache.get(getServletInfo(), "grabarBorrador")%>
											</html:submit>
											<html:submit property="operation">
												<%=ResourceBundleCache.get(getServletInfo(), "grabarIdiomaAlternativo")%>
											</html:submit>
										</logic:equal>
									</logic:equal>
									<!-- Si esta en disenio -->
									<logic:equal name="CreateDocumentForm" property="designer" value="true">
										<html:submit property="operation">
											<%=ResourceBundleCache.get(getServletInfo(), "grabarDisenio")%>
										</html:submit>
									</logic:equal>
								</logic:equal>
								<!-- Si esta en negociacion -->
								<logic:equal name="CreateDocumentForm" property="isInNegotiation" value="true">
									<html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "grabarYContinuar")%>
									</html:submit>
									<html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "ponerEnFirma")%>
									</html:submit>
									
									<html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "finalizar")%>
									</html:submit>
								</logic:equal>
								</html:form></td>
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
						</tr>
						<tr>
							<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
							<td colspan="2" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
							<td colspan="2" width="10" height="10" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	<!-- /div -->
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>