<%@ page info="viewFinalVersion"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>

<style type="text/css">
/* hide from incapable browsers */
div#sizer {
	display:none;
	position: relative;
	float: right;
	padding-top: 10px;
	padding-right: 10px;
}  
/* breathing room between images in sizer */
div#sizer img { margin-right:3px; }

div#scrollbar {
	display:none;
}
div#main{
	# width:100%;
	* width:100%;
	width:inherit;
	height:470px;
	float:left;
	margin: 0px;
	position: relative;
}
lyr1{
	
}
#buttonContainer{
	margin-right: auto;
	margin-left: auto;
	height: 24px;
	width: 50px;
}
</style>

<script src="scripts/dw_event.js" type="text/javascript"></script>
<script src="scripts/dw_cookies.js" type="text/javascript"></script>
<script src="scripts/dw_sizerdx.js" type="text/javascript"></script>

<script src="scripts/dw_scroll.js" type="text/javascript"></script>
<script src="scripts/dw_scrollbar.js" type="text/javascript"></script>
<script src="scripts/scroll_controls.js" type="text/javascript"></script>
<script type="text/javascript">
// setDefaults arguments: size unit, default size, minimum, maximum
// optional array of elements or selectors to apply these defaults to
dw_fontSizerDX.setDefaults("px", 13, 9, 26, ['div#main p.article'] );

// set arguments: default size, minimum, maximum
// array of elements or selectors to apply these settings to
dw_fontSizerDX.set(18, 12, 36, ['div#main h2'] );

dw_Event.add( window, 'load', dw_fontSizerDX.init );

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
<% if (isDelegate) { %>
<script type="text/javascript">
	
	function getDelegateSiteStatus() {
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getDelegateSiteStatus.st"/>', onSuccess: function(json, responseText){
			var errorResult = json.error;
			if ('notLogged' == errorResult) {
				window.location='<html:rewrite page="/login.jsp"/>';
				return;
			}
		    var sitestatus = json.sitestatus;
			if (sitestatus != 'NORMAL') {
				window.location='<html:rewrite page="/goToDelegateNegotiation.st"/>';
				return;
			}
		}}).get();
	}
	timer = setInterval("getDelegateSiteStatus()",1000);

</script>
<% } %>
<%if (isModerator) { %>
	<%@ include file="includes/menu.jsp" %>
<% } %>
<div id="content">
<html:form action="/viewVersionAction">
<table width="95%" height="500" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="100%">
			<!-- inicio tabla template -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "documento")%>: <b><bean:write name="ViewVersion" property="version.document.title" /></b></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%" height="480" valign="middle">
						<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1">
							<p class="article"><bean:write name="ViewVersion" property="version.document.introduction" /></p>
							<!-- div id="documentoCompleto"-->
								<table width="100%" border="0" cellspacing="0" cellpadding="5">
									<tr>
										<td width="50%"><%=ResourceBundleCache.get(getServletInfo(), "idiomaDefault")%></td>
										<td width="50%"><%=ResourceBundleCache.get(getServletInfo(), "idiomaAlternativo")%></td>
									</tr>
								<logic:iterate name="ViewVersion" property="paragraphs" id="paragraph"> 
									<tr>
										<td width="50%" valign="top"><p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" />.<bean:write filter="false" name="paragraph" property="spanishVersion" /></p></td>
										<td width="50%" valign="top"><p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" />.<bean:write filter="false" name="paragraph" property="portuguesVersion" /></p></td>
									</tr>
								</logic:iterate>
									<tr>
										<td align="center" bgcolor="#FFFFFF"><html:submit property="operation">
												<%=ResourceBundleCache.get(getServletInfo(), "verIdiomaDefault")%>
											</html:submit></td>
										<td align="center" bgcolor="#FFFFFF"><html:submit property="operation">
												<%=ResourceBundleCache.get(getServletInfo(), "verIdiomaAlternativo")%>
											</html:submit></td>
									</tr>
								</table>
								<table width="100%" id="signTable" border="0" cellspacing="0" cellpadding="0">
									<logic:iterate name="ViewVersion" property="signatures" id="signature" indexId="signatureIndex">
									<tr> 
										<td rowspan="2" width="200" align="center"><img width="200" height="110" src="././download.do?action=signature&signature=<bean:write name="signature" property="signatureFileName" />"></td>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td width="30"><img src="./download.do?action=flag&fileId=<bean:write name="signature" property="countryId" />" width="30" height="30"></td>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td><bean:write name="signature" property="countryDescription" /></td>
									</tr>
									<tr>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td colspan="3" height="60"><span class="remarcado"><bean:write name="signature" property="delegateName" /></span><br><%=ResourceBundleCache.get(getServletInfo(), "cargo")%></td>
									</tr>
									</logic:iterate>
								</table>
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
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td colspan="2" align="right"><div id="sizer"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div>
					<div id="buttonContainer">
						<% if (isDelegate) { %>
							<html:link action="/goToDelegateHome" ><%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","volver")%></html:link>
						<% } %>
						<% if (isModerator) { %>
							<html:link action="/goToModeratorHome" ><%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","volver")%></html:link>
						<% } %>
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