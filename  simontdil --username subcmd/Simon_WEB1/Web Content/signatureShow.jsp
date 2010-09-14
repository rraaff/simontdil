<%@ page info="signatureShow"%>
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

div#main { 
	background-color:#eee;
	width:900px;
	text-align:left;
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
<div id="content">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="5" height="20"><img src="images/null.gif" width="1" height="20" id="imgReferencia" name="imgReferencia"></td>
	</tr>
	<tr>
		<td valign="top">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="920" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Documento: <bean:write name="DelegateNegotiationForm" property="versionVO.document.title" /> - N�mero de Versi�n:<bean:write name="DelegateNegotiationForm" property="versionVO.version.number" /> - Nombre:<bean:write name="DelegateNegotiationForm" property="versionVO.version.name" /></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="920" height="165">
					<!-- corte tabla template -->
					<script type="text/javascript">
						var signatureArray = new Array();
						var lastSignatureIndex = 0;
					</script>
					<br>
					<div id="main">
						<div id="lyr1" style="background-color:#FFFFFF">
							<!-- PABLO: intro -->
							<p class="article"><bean:write name="DelegateNegotiationForm" property="versionVO.document.introduction" /></p>
							<logic:iterate name="DelegateNegotiationForm" property="versionVO.paragraphs" id="paragraph"> 
								<p class="article"><bean:write name="paragraph" property="paragraphNumber" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
							</logic:iterate>
							<div id="signaturePreview">
							</div>
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
									<td>Nombre de la delegaci�n en MAYUSCULAS</td>
								</tr>
								<tr>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td colspan="3" height="60"><span class="remarcado"><bean:write name="signature" property="delegateName" /></span><br>Cargo</td>
								</tr>
								</logic:iterate>
							</table>
							<% if (isSign) { %>
							<script type="text/javascript">
								function alreadyDisplayed(signName) {
									var index = 0;
									while(index <= lastSignatureIndex) {
										if (signatureArray[index] == signName) {
											return true;
										}
										index = index + 1;
									}
									return false;
								}
								
								function getSignatures() {
									var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getDelegateSiteStatus.st"/>', onSuccess: function(json, responseText){
										var errorResult = json.error;
										if ('notLogged' == errorResult) {
											window.location='<html:rewrite page="/login.jsp"/>';
											return;
										}
										var sitestatus = json.sitestatus;
										if (sitestatus == 'NORMAL') {
											window.location='<html:rewrite page="/goToDelegateHome.st"/>';
										} else {
											// TODO manejar variable de bloqueo
											var i = 0;
											while(i < json.delegates.length) {
												if (!alreadyDisplayed(json.fileNames[i])) {
													var myTable = document.getElementById("signTable");
													var tBody = myTable.getElementsByTagName("TBODY")[0];
													var newTR = document.createElement('tr');
													var flagTD = document.createElement('td');
													flagTD.innerHTML = '<img width="200" height="110" src="./download.do?action=flag&fileId=' + json.flags[i] + '">';
													flagTD.align="center";
													newTR.appendChild (flagTD);
													tBody.appendChild(newTR);
													
													newTR = document.createElement('tr');
													var signTD = document.createElement('td');
													signTD.innerHTML = '<img width="200" height="110" src="./download.do?action=signature&signature=' + json.fileNames[i] + '">';
													signTD.className = "BorderRigth";
													signTD.width = "100";
													signTD.align="center";
													newTR.appendChild (signTD);
													tBody.appendChild(newTR);
													
													newTR = document.createElement('tr');
													var operatorTD = document.createElement('td');
													operatorTD.align="center";
													operatorTD.innerHTML = '<b>' + json.delegates[i] + '</b>';
													operatorTD.className = "BorderRigth";
													operatorTD.width = "150";
													newTR.appendChild (operatorTD);
													tBody.appendChild(newTR);
													
													lastSignatureIndex = lastSignatureIndex + 1;
													signatureArray[lastSignatureIndex] = json.fileNames[i];
												}
												i = i + 1;
											}
										}
									  }
								   }).get();
								}
								timer = setInterval("getSignatures()",<%=com.tdil.simon.web.SystemConfig.getClientSignaturesRefreshTime()%>);
							</script>
							<% } %>
						</div>
					</div>
					<!-- corte tabla template --></td>
					<td width="30" align="right" valign="bottom">
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
					<td colspan="2" height="30"><div id="sizer" align="right"><a class="increase" href="#" title="Aumentar tama�o del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tama�o del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tama�o normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div></td>
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
	<tr>
		<td colspan="5" height="20" align="right"><img src="images/null.gif" width="10" height="20"></td>
	</tr>
</table>
</div>
<%@ include file="includes/footer.jsp" %>