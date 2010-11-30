<%@ page info="viewVersion"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<!-- style type="text/css">
/* hide from incapable browsers */
div#sizer { display:none; }  
/* breathing room between images in sizer */
div#sizer img { margin-right:3px; }

div#scrollbar {
	display:none;
}
lyr1{
	
}
#content #portaVersiones a {
	color: #FFFFFF;
}
#versionStrong {
	font-size: 14px;
	color: #FFFFFF;
	background-color: #EF8A1B;
	height: 20px;
	width: 20px;
	text-align: center;
}
</style -->
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
#content #alcincuentaRight{
	width:72%;
	height:530px;
	padding-left:10px;
	padding-right:10px;
	margin-top:12px;
/*	#width: 400px; Para Internet Explorer */
/*	_width: 400px; Para Internet Explorer 6 */
}
div#main{
/*	#width: 400px; Para Internet Explorer */
/*	_width: 400px; Para Internet Explorer 6 */
	width:95%;
	height:470px;
	float:left;
	margin: 0px;
	position: relative;
}
#mainDocContainer {
	border: 1px solid #c6c6c6;
	position: relative;
	height: 200px;
}
lyr1{
	
}
#content #portaVersiones a {
	color: #FFFFFF;
}
#versionStrong {
	font-size: 14px;
	color: #FFFFFF;
	background-color: #EF8A1B;
	height: 20px;
	width: 20px;
	text-align: center;
}
#blockTitle1 {
	background-color: #FFFFFF;
	width: auto;
	position: relative;
	height: 15px;
	float: left;
	padding-top: 4px;
	padding-right: 4px;
	padding-bottom: 0px;
	padding-left: 4px;
	left: 10px;
	top: -15px;
}
#blockTitle2 {
	background-color: #FFFFFF;
	width: auto;
	height: 15px;
	padding-right:4px;
	padding-left:4px;
	padding-top:4px;
	float: left;
	position: relative;
	top: -15px;
	left: 10px;
}
#documentContainerNew {
	border: 1px solid #c6c6c6;
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
	<div id="alcien" style="height:560px; padding-top:20px;">
		<div id="alcincuentaLeft" style="width:24%; height:540px;">
			<div id="mainDocContainer" style="height:180px; margin-top:13px;">
				<div id="blockTitle1">Documento Principal</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td width="10"><img src="images/null.gif" width="10" height="1"></td>
						<td colspan="3" class="titleDocInModule"><bean:write name="ViewVersion" property="version.document.title" /></td>
					</tr>
					<!-- tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td width="60" align="left">Versi&oacute;n:</td>
						<td width="30"><div id="versionStrong"><bean:write name="ViewVersion" property="version.version.number" /></div></td>
						<td align="left"><bean:write name="ViewVersion" property="version.version.name" /></td>
					</tr -->
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td colspan="3">L&iacute;mite para obs.: <bean:write name="ViewVersion" property="version.version.limitObservationsString" /></td>
					</tr>
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td colspan="3" align="center"><html:submit property="operation">
															<bean:message key="viewVersion.downloadPdf"/>
														</html:submit></td>
					</tr>
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td colspan="3" align="center"><html:submit property="operation">
											<bean:message key="viewVersion.downloadRtf"/>
										</html:submit></td>
					</tr>
				</table>
			</div>
			<div id="mainDocContainer" style="margin-top:20px;">
				<div id="blockTitle2">Acciones disponibles</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<!-- tr>
						<td align="center">
							<table width="274" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
								</tr>
								<tr>
									<td align="right" width="50" height="30"><span style="font-size: 10px;">Anteriores:</span></td>
									<td width="7"><img src="images/null.gif" width="7" height="1"></td>
									<td width="217" align="left" valign="middle">
										<div id="portaVersiones">
										<logic:iterate name="ViewVersion" property="version.reducedVersions" id="otherVersion">
											<logic:equal name="otherVersion" property="current" value="true">
												<b><bean:write name="otherVersion" property="number" /></b>
											</logic:equal>
											<logic:notEqual name="otherVersion" property="current" value="true">
												<html:link  action="/goToViewVersion.st?" paramName="otherVersion" paramProperty="id" paramId="versionID"><bean:write name="otherVersion" property="number" /></html:link>
											</logic:notEqual>
										</logic:iterate>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="3"><img src="images/null.gif" width="1" height="16"></td>
								</tr>
								<tr>
									<td colspan="3" align="center"><html:link action="/goToDelegateHome" >Volver al inicio</html:link></td>
								</tr>
								<tr>
									<td colspan="3" height="80"><img src="images/null.gif" width="1" height="80"></td>
								</tr>
							</table-->					
						<!-- corte tabla template>
						</td>
					</tr -->
					<tr>
						<td><img src="images/null.gif" width="1" height="10"></td>
					</tr>
					
					<tr>
						<td align="center">
							<% if (isDelegate) { %>
								<logic:equal name="ViewVersion" property="hasTranslation" value="true">
									<html:link page="/viewFinalVersion.jsp"><img src="images/buttons/volver.png" border="0"/></html:link>
								</logic:equal>
								<logic:equal name="ViewVersion" property="hasTranslation" value="false">
									<html:link action="/goToDelegateHome" ><img src="images/buttons/volver.png" border="0"/></html:link>
								</logic:equal >
							<% } %>
							<% if (isModerator) { %>
							<logic:equal name="ViewVersion" property="hasTranslation" value="true">
									<html:link page="/viewFinalVersion.jsp"><img src="images/buttons/volver.png" border="0"/></html:link>
								</logic:equal>
								<logic:equal name="ViewVersion" property="hasTranslation" value="false">
									<html:link action="/goToModeratorHome" ><img src="images/buttons/volver.png" border="0"/></html:link>
								</logic:equal >
							<% } %>
						</td>
					</tr>
					<tr>
						<td><img src="images/null.gif" width="1" height="10"></td>
					</tr>
				</table>
			</div>
		</div>
		<%if (isModerator) { %>
		<div id="alcincuentaRight" style="height:518px; border:1px solid #c6c6c6;">
			<div id="blockTitle1">Documento</div>
			<div>
				<div id="main" style="height:460px; background-color:#FFFFFF;">
		<% } else { %>
		<div id="alcincuentaRight" style="border:1px solid #c6c6c6;">
			<div id="blockTitle1">Documento</div>
			<div>
				<div id="main" style="background-color:#FFFFFF;">
		<% } %>
					<div id="lyr1">
						<p class="article"><bean:write name="ViewVersion" property="version.document.introduction" /></p>
						<!-- div id="documentoCompleto" -->
							<logic:iterate name="ViewVersion" property="selectedLanguageParagraphs" id="paragraph"> 
								<p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" />.<bean:write filter="false" name="paragraph" property="paragraphText" /></p>
							</logic:iterate>
							<table width="100%" id="signTable" border="0" cellspacing="0" cellpadding="0">
								<logic:iterate name="ViewVersion" property="signaturesRows" id="signatureRow" indexId="signatureIndex">
								<tr> 
									<td rowspan="2" width="200" align="center"><img width="200" height="110" src="././download.do?action=signature&signature=<bean:write name="signatureRow" property="left.signatureFileName" />"></td>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td width="30"><img src="./download.do?action=flag&fileId=<bean:write name="signatureRow" property="left.countryId" />" width="30" height="30"></td>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td><bean:write name="signatureRow" property="left.countryDescription" /></td>
									<logic:equal name="signatureRow" property="hasRight" value="false">
										<td rowspan="2" width="200" align="center">&nbsp;</td>
										<td width="10">&nbsp;</td>
										<td width="30">&nbsp;</td>
										<td width="10">&nbsp;</td>
									</logic:equal>
									<logic:equal name="signatureRow" property="hasRight" value="true">
										<td rowspan="2" width="200" align="center"><img width="200" height="110" src="././download.do?action=signature&signature=<bean:write name="signatureRow" property="right.signatureFileName" />"></td>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td width="30"><img src="./download.do?action=flag&fileId=<bean:write name="signatureRow" property="right.countryId" />" width="30" height="30"></td>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td><bean:write name="signatureRow" property="right.countryDescription" /></td>
									</logic:equal>
								</tr>
								
								<tr>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td colspan="3" height="60"><span class="remarcado"><bean:write name="signatureRow" property="left.delegateName" /></span><br>Cargo</td>
									<logic:equal name="signatureRow" property="hasRight" value="false">
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td colspan="3" height="60">&nbsp;</td>
									</logic:equal>
									<logic:equal name="signatureRow" property="hasRight" value="true">
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td colspan="3" height="60"><span class="remarcado"><bean:write name="signatureRow" property="right.delegateName" /></span><br>Cargo</td>
									</logic:equal>
								</tr>
								
								</logic:iterate>
							</table>
					</div>
				</div>
				<%if (isModerator) { %>
				<div id="scrollbar" style="width:20px; height:460px; float:right;">
					<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
					<div id="track" style="height:436px;">
				<% } else { %>
				<div id="scrollbar" style="width:20px; height:470px; float:right;">
					<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
					<div id="track" style="height:446px;">
				<% } %>
						<div id="dragBar"></div>
					</div>
					<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
				</div>
			</div>
			<div id="sizer"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div>
		</div>
	</div>
</html:form>
</div>
<%@ include file="includes/footer.jsp" %>