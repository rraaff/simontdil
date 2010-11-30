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
	width:980px;
	height:480px;
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
<table width="100%" height="560" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td valign="middle">
			<!-- inicio tabla template -->
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Documento: <bean:write name="DelegateNegotiationForm" property="versionVO.document.title" /> - Número de Versión:<bean:write name="DelegateNegotiationForm" property="versionVO.version.number" /> - Nombre:<bean:write name="DelegateNegotiationForm" property="versionVO.version.name" /></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="300">
					<!-- corte tabla template -->
					<script type="text/javascript">
						var signatureArray = new Array();
						var lastSignatureIndex = 0;
						var completed;
					</script>
					<br>
					<div id="main">
						<div id="lyr1" style="background-color:#FFFFFF">
							<!-- PABLO: intro -->
							<p class="article"><bean:write name="DelegateNegotiationForm" property="versionVO.document.introduction" /></p>
							<logic:iterate name="DelegateNegotiationForm" property="versionVO.paragraphs" id="paragraph"> 
								<p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
							</logic:iterate>
							<div id="signaturePreview">
							</div>
							<table width="100%" id="signTable" border="0" cellspacing="0" cellpadding="0">
								<% int index = 0;%>
								<logic:iterate name="DelegateNegotiationForm" property="signaturesRows" id="signatureRow">
								<tr>
									<td id="signature_td_<%=index%>" rowspan="2" width="200" align="center"><img width="200" height="110" src="./download.do?action=signature&signature=<bean:write name="signatureRow" property="left.signatureFileName" />"></td>
									<script>
										signatureArray[<%=index%>] = '<bean:write name="signatureRow" property="left.signatureFileName" />';
										lastSignatureIndex = <%=index%>;
										completed = false;
									</script>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td id="flag_td_<%=index%>" width="30"><img src="./download.do?action=flag&fileId=<bean:write name="signatureRow" property="left.countryId" />" width="30" height="30"></td>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td id="country_desc_td_<%=index%>"><bean:write name="signatureRow" property="left.countryDescription" /></td>
									
									<% index = index + 1;%>
									<td width="200"><img src="images/null.gif" width="200" height="1"></td>
									<logic:equal name="signatureRow" property="hasRight" value="true">
										<td id="signature_td_<%=index%>" rowspan="2" width="200" align="center"><img width="200" height="110" src="./download.do?action=signature&signature=<bean:write name="signatureRow" property="right.signatureFileName" />"></td>
										<script>
											signatureArray[<%=index%>] = '<bean:write name="signatureRow" property="right.signatureFileName" />';
											lastSignatureIndex = <%=index%>;
											completed = true;
										</script>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td id="flag_td_<%=index%>" width="30"><img src="./download.do?action=flag&fileId=<bean:write name="signatureRow" property="right.countryId" />" width="30" height="30"></td>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td id="country_desc_td_<%=index%>"><bean:write name="signatureRow" property="right.countryDescription" /></td>
									</logic:equal>
									<logic:equal name="signatureRow" property="hasRight" value="false">
										<td id="signature_td_<%=index%>" rowspan="2" width="200" align="center">&nbsp;</td>
										<script>
											lastSignatureIndex = <%=index%>;
											completed = false;
										</script>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td id="flag_td_<%=index%>" width="30">&nbsp;</td>
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td id="country_desc_td_<%=index%>">&nbsp;</td>
									</logic:equal>
								</tr>
								
								<tr>
									<td width="10"><img src="images/null.gif" width="10" height="1"></td>
									<td id="name_td_<%=index - 1%>" colspan="3" height="60"><span class="remarcado"><bean:write name="signatureRow" property="left.delegateName" /></span><br><bean:write name="signatureRow" property="left.job" /></td>
									
									<td width="200"><img src="images/null.gif" width="200" height="1"></td>
									<logic:equal name="signatureRow" property="hasRight" value="true">
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td id="name_td_<%=index%>" colspan="3" height="60"><span class="remarcado"><bean:write name="signatureRow" property="right.delegateName" /></span><br><bean:write name="signatureRow" property="right.job" /></td>
									</logic:equal>
									<logic:equal name="signatureRow" property="hasRight" value="false">
										<td width="10"><img src="images/null.gif" width="10" height="1"></td>
										<td id="name_td_<%=index%>" colspan="3" height="60">&nbsp;</td>
									</logic:equal>
								</tr>
								<% index = index + 1;%>
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
													if (completed) {
														lastSignatureIndex = lastSignatureIndex + 1;
														signatureArray[lastSignatureIndex] = json.fileNames[i];
														
														var myTable = document.getElementById("signTable");
														var tBody = myTable.getElementsByTagName("TBODY")[0];
														// First row
														var newTR = document.createElement('tr');
														
														// First cell
														var signatureTD = document.createElement('td');
														signatureTD.id = 'signature_td_' + lastSignatureIndex;
														signatureTD.rowSpan = 2;
														signatureTD.align="center";
														signatureTD.innerHTML = '<img width="200" height="110" src="./download.do?action=signature&signature=' + json.fileNames[i] + '">';
														newTR.appendChild (signatureTD);
														
														var spacerTD = document.createElement('td');
														spacerTD.innerHTML = '<img src="images/null.gif" width="10" height="1">';
														newTR.appendChild (spacerTD);
														
														var flagTD = document.createElement('td');
														flagTD.id = 'flag_td_' + lastSignatureIndex;
														flagTD.innerHTML = '<img width="30" height="30" src="./download.do?action=flag&fileId=' + json.flags[i] + '">';
														newTR.appendChild (flagTD);
														
														var spacerTD1 = document.createElement('td');
														spacerTD1.innerHTML = '<img src="images/null.gif" width="10" height="1">';
														newTR.appendChild (spacerTD1);
														
														var countryTD = document.createElement('td');
														countryTD.id = 'country_desc_td_' + lastSignatureIndex;
														countryTD.innerHTML = json.countries[i];
														newTR.appendChild (countryTD);
														
														// Separator
														var spacerTD2 = document.createElement('td');
														spacerTD2.innerHTML = '<img src="images/null.gif" width="200" height="1">';
														newTR.appendChild (spacerTD2);
														
														lastSignatureIndex = lastSignatureIndex + 1;
														// Second cell
														var signatureTDC1 = document.createElement('td');
														signatureTDC1.id = 'signature_td_' + lastSignatureIndex;
														signatureTDC1.rowSpan = 2;
														signatureTDC1.align="center";
														signatureTDC1.innerHTML = '&nbsp;';
														newTR.appendChild (signatureTDC1);
														
														var spacerTDC1 = document.createElement('td');
														spacerTDC1.innerHTML = '<img src="images/null.gif" width="10" height="1">';
														newTR.appendChild (spacerTDC1);
														
														var flagTDC1 = document.createElement('td');
														flagTDC1.id = 'flag_td_' + lastSignatureIndex;
														flagTDC1.innerHTML = '&nbsp;';
														newTR.appendChild (flagTDC1);
														
														var spacerTD1C1 = document.createElement('td');
														spacerTD1C1.innerHTML = '<img src="images/null.gif" width="10" height="1">';
														newTR.appendChild (spacerTD1C1);
														
														var countryTDC1 = document.createElement('td');
														countryTDC1.id = 'country_desc_td_' + lastSignatureIndex;
														countryTDC1.innerHTML = '&nbsp;';
														newTR.appendChild (countryTDC1);
														
														tBody.appendChild(newTR);
														
														newTR = document.createElement('tr');
														
														var spacerTD2 = document.createElement('td');
														spacerTD2.innerHTML = '<img src="images/null.gif" width="10" height="1">';
														newTR.appendChild (spacerTD2);
														
														var delegateNameTD = document.createElement('td');
														delegateNameTD.id = 'name_td_' + (lastSignatureIndex - 1);
														delegateNameTD.colSpan = 3;
														delegateNameTD.innerHTML = '<span class="remarcado">' + json.delegates[i] + '</span><br>' + json.jobs[i];
														newTR.appendChild (delegateNameTD);
														
														// Separator
														var spacerTD22 = document.createElement('td');
														spacerTD22.innerHTML = '<img src="images/null.gif" width="10" height="1">';
														newTR.appendChild (spacerTD22);
														
														var spacerTD2C1 = document.createElement('td');
														spacerTD2C1.innerHTML = '<img src="images/null.gif" width="200" height="1">';
														newTR.appendChild (spacerTD2C1);
														
														var delegateNameTDC1 = document.createElement('td');
														delegateNameTDC1.id = 'name_td_' + (lastSignatureIndex);
														delegateNameTDC1.colSpan = 3;
														delegateNameTDC1.innerHTML = '&nbsp;';
														newTR.appendChild (delegateNameTDC1);
														
														tBody.appendChild(newTR);
	
														init_dw_Scroll();
														completed = false;
													} else {
														signatureArray[lastSignatureIndex] = json.fileNames[i];
														var signatureTD = document.getElementById('signature_td_' + lastSignatureIndex);
														signatureTD.innerHTML = '<img width="200" height="110" src="./download.do?action=signature&signature=' + json.fileNames[i] + '">';
														var flagTD = document.getElementById('flag_td_' + lastSignatureIndex);
														flagTD.innerHTML = '<img width="30" height="30" src="./download.do?action=flag&fileId=' + json.flags[i] + '">';
														var countryTD = document.getElementById('country_desc_td_' + lastSignatureIndex);
														countryTD.innerHTML = json.countries[i];
														var delegateNameTD = document.getElementById('name_td_' + lastSignatureIndex);
														delegateNameTD.innerHTML = '<span class="remarcado">' + json.delegates[i] + '</span><br>' + json.jobs[i];
														completed = true;
													}
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
					<div id="scrollbar" style="width:20px; height:470px; float:right;">
						<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track" style="height:446px;">
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
					<td colspan="2" height="30"><div id="sizer" align="right"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div></td>
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
<%@ include file="includes/footer.jsp" %>