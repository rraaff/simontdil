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
	height:200px;
	font-size: 10px;
}
div#scrollbar {
	display:none;
}
/* ### New Homes ### */
#newHomeBlock{
	border:1px solid #c6c6c6;
	width:100%;
	height:150px;
	font-family:"Trebuchet MS", Tahoma, Verdana, Arial;
}
#newHomeBlock a, #newHomeBlock a:hover, #newHomeBlock visited{
	font-size:12px;
	color:#888888;
}
#newHomeBlock .newHomeTitle{
	font-size:16px;
	color:#666;
}
#newHomeBlock .mainDocsCategory{
	font-size:14px;
	color:#888888;
}
#newHomeBlock .mainDocsSubCategory{
	font-size:17px;
	color:#ff004e;
}
#newHomeBlock .mainDocsName{
	font-size:12px;
	color:#888888;
}
#newHomeBlock .mainDocsData{
	font-size:11px;
	color:#888888;
}

#newHomeBlockHalf{
	border:1px solid #c6c6c6;
	width:100%;
	height:150px;
	font-family:"Trebuchet MS", Tahoma, Verdana, Arial;
	float: left;
}
#newHomeBlockHalf .title{
	font-size:16px;
	color:#888888;
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
	<table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td colspan="2"><img src="images/null.gif" width="1" height="20"></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="newHomeBlock">
					<table width="95%" cellspacing="0" cellpadding="15" align="center">
						<tr>
							<td><span class="newHomeTitle"><%=ResourceBundleCache.get(getServletInfo(), "Documentos")%></span></td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<logic:iterate name="ModeratorHome" property="principalVersions" id="doc" indexId="principalIndex">
											<td>
												<table width="370" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td width="74"><img src="images/icons/documento.gif" width="64" height="76"></td>
														<td><span class="mainDocsCategory"><bean:write name="doc" property="documentTypeName" /></span><br/>
															<span class="mainDocsSubCategory"><bean:write name="doc" property="documentSubTypeName" /></span><br/>
															<span class="mainDocsName"><html:link  action="/goToViewVersion.st?" paramName="doc" paramProperty="id" paramId="versionID"><bean:write name="doc" property="documentTitle" /></html:link></span><br/>
															<span class="mainDocsData"><%=ResourceBundleCache.get(getServletInfo(), "version")%>: <bean:write name="doc" property="number" /> - <bean:write name="doc" property="name" /></span></td>
													</tr>
												</table>
											</td>
										</logic:iterate>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2"><img src="images/null.gif" width="1" height="20"></td>
		</tr>
		<tr>
			<td width="50%">
				<div id="newHomeBlockHalf">
					<table width="98%" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="3"><span class="title"><%=ResourceBundleCache.get(getServletInfo(), "documentosEspecificos")%></span></td>
						</tr>
						<tr>
							<td>
								<div id="main">
									<div id="lyr1" style="width:inherit;">
										<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
											<logic:iterate name="ModeratorHome" property="otherDocumentsList" id="doc" indexId="referenceListIndex"> 
												<tr> 
													<td colspan="10" height="22" class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><html:link action="/goToViewLastVersionOfDocument.st?" paramName="doc" paramProperty="id" paramId="documentID"><bean:write name="doc" property="title" /></html:link></td>
												</tr>
											</logic:iterate>
										</table>	
									<!-- corte tabla template -->
									</div>
								</div>
							</td>
							<td width="10"><img src="images/null.gif" width="10" height="1"></td>
							<td width="30">
								<div id="scrollbar" style="width:20px; height:240px; float:right;">
									<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
									<div id="track" style="height:216px;">
										<div id="dragBar"></div>
									</div>
									<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
			<td width="50%">
				<div id="newHomeBlockHalf">
					<table width="98%" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="3"><span class="title"><%=ResourceBundleCache.get(getServletInfo(), "documentosInformativos")%></span></td>
						</tr>
						<tr>
							<td>
								<div id="main2">
									<div id="lyr2" style="width:inherit;">
										<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
											<!-- logic:iterate name="ModeratorHome" property="referenceList" id="ref" indexId="referenceListIndex"> 
												<tr> 
													<td colspan="9" height="30" class="row< %=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><bean:write name="ref" property="title" /></td>
													<td class="row< %=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><a href="./download.do?action=refdoc&fileId=<bean:write name="ref" property="id" />">< %=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","descargar")%></a></td>
												</tr>
											</logic:iterate  -->
										</table>	
									<!-- corte tabla template -->
									</div>
								</div>
							</td>
							<td width="10"><img src="images/null.gif" width="10" height="1"></td>
							<td>
								<div id="scrollbar2" style="width:20px; height:440px; float:right;">
									<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
									<div id="track" style="height:416px;">
										<div id="dragBar"></div>
									</div>
									<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>