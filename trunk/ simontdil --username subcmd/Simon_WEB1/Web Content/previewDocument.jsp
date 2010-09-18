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
	width:900px;
	height:260px;
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
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="920" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Previsualización del documento</div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="920">
						<!-- corte tabla template -->
						<table width="900" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" align="left">Documento: <bean:write name="CreateDocumentForm" property="title"/> - 
										V <bean:write name="CreateDocumentForm" property="versionNumber"/> - 
									(<bean:write name="CreateDocumentForm" property="versionName"/>) - 
									Límite para realizar observaciones: <!-- bean:write name="ViewVersion" property="version.version.limitObservationsString" /--></td>
							</tr>
							<tr>
								<td height="270" align="left" valign="top">
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
							<tr>
								<td align="center"><html:errors property="general" />
									<html:form method="POST" action="/previewDocument">
										
									<html:submit property="operation">
										<bean:message key="createDocument.preview.editParagraphs"/>
									</html:submit>
									
									<!-- Si no esta en negociacion -->
									<logic:equal name="CreateDocumentForm" property="isInNegotiation" value="false">
										<html:submit property="operation">
											<bean:message key="createDocument.preview.save"/>
										</html:submit>
										<html:submit property="operation">
											<bean:message key="createDocument.preview.consolidate"/>
										</html:submit>
									</logic:equal>
									
									<!-- Si esta en negociacion -->
									<logic:equal name="CreateDocumentForm" property="isInNegotiation" value="true">
										<html:submit property="operation">
											<bean:message key="createDocument.preview.saveAndContinue"/>
										</html:submit>

									</logic:equal>
									</html:form></td>
							</tr>
							<tr>
								<td height="5"><img src="images/null.gif" width="1" height="5"></td>
							</tr>
						</table>
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
<div id="footer">
<%@ include file="includes/footer.jsp" %>