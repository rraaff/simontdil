<%@ page info="viewVersion"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<style type="text/css">
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

<html:form action="/viewVersionAction">
<%if (isModerator) { %>
	<%@ include file="includes/menu.jsp" %>
<% } %>
<div id="content">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="294" valign="top">
			<!-- inicio tabla template -->
			<table width="294" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td background="images/interfaces/topLeftTitleDoc.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><img src="images/titles/documentoPrincipalVistaDoc.gif" alt="Ingreso al Sitio" width="132" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td background="images/interfaces/middleLeftDoc.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
					<td height="11"><img src="images/null.gif" width="1" height="11"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td background="images/interfaces/middleLeftDoc.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
					<td width="274" height="80" align="center" valign="middle">
						<table width="254" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3" height="30"><div class="titleDocInModule"><bean:write name="ViewVersion" property="version.document.title" /></div></td>
							</tr>
							<tr>
								<td colspan="3">
									<table width="254" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="50" height="30" align="left">Versi&oacute;n:</td>
											<td width="8"><img src="images/null.gif" width="7" height="1"></td>
											<td width="20"><div id="versionStrong"><bean:write name="ViewVersion" property="version.version.number" /></div></td>
											<td width="8"><img src="images/null.gif" width="7" height="1"></td>
											<td width="156" align="left"><bean:write name="ViewVersion" property="version.version.name" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="163" height="30" align="left">L&iacute;mite para observaciones:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="84" align="left"><bean:write name="ViewVersion" property="version.version.limitObservationsString" /></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
								<html:submit property="operation">
									<bean:message key="viewVersion.downloadPdf"/>
								</html:submit>
								</td>
							</tr>
						</table>
						<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td background="images/interfaces/bottomLeftDoc.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		<!-- separador -->
		<div id="separador1Home"><img src="images/null.gif" width="1" height="16"><div>
		<!-- inicio tabla template -->
			<table width="294" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><img src="images/titles/accionesDisponiblesVistaDoc.gif" alt="Ingreso al Sitio" width="132" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="274"><!-- corte tabla template -->
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
		<td width="608">
		<!-- inicio tabla template -->
			<table width="608" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="274" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Documento</div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="578" height="284" align="left" valign="top">
					<!-- corte tabla template -->
					<div id="main" style="background-color:#FFFFFF;">
						<div id="lyr1">
						<p class="article"><bean:write name="ViewVersion" property="version.document.introduction" /></p>
						<!-- div id="documentoCompleto" -->
							<logic:iterate name="ViewVersion" property="version.paragraphs" id="paragraph"> 
								<p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" />.<bean:write filter="false" name="paragraph" property="paragraphText" /></p>
							</logic:iterate>
							<table width="100%" id="signTable" border="0" cellspacing="0" cellpadding="0">
								<logic:iterate name="ViewVersion" property="signatures" id="signature" indexId="signatureIndex">
								<tr> 
									<td rowspan="2" width="200" align="center"><img width="200" height="110" src="././download.do?action=signature&signature=<bean:write name="signature" property="signatureFileName" />"></td>
									<script>
										signatureArray[<%=signatureIndex%>] = '<bean:write name="signature" property="signatureFileName" />';
										lastSignatureIndex = <%=signatureIndex%>;
									</script>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td width="30"><img src="./download.do?action=flag&fileId=<bean:write name="signature" property="countryId" />" width="30" height="30"></td>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td>Nombre de la delegación en MAYUSCULAS</td>
								</tr>
								<tr>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td colspan="3" height="60"><span class="remarcado"><bean:write name="signature" property="delegateName" /></span><br>Cargo</td>
								</tr>
								</logic:iterate>
							</table>
						</div>
					</div>
					<!-- corte tabla template --></td>
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
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td colspan="2" width="608" height="30"><div id="sizer" align="right"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" width="320" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
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