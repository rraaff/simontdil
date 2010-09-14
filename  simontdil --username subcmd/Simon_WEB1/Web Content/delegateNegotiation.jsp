<%@ page info="delegateNegotiation"%>
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

div#main { 
	background-color:#eee;
	width:480px;
}

#content #negotiationArea #lastParagraphText {
	font-size: 11px;
	color: #000000;
	background-color: #F3F3F3;
	width: 342px;
	display: block;
	text-align: left;
	height: 500px;
}

div#scrollbar {
	display:none;
}

#content #signArea {
	display: none;
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
	
	var wndo = new dw_scrollObj('negotiationArea', 'lastParagraphText');
	wndo.setUpScrollbar("dragBar2", "track2", "v", 1, 1);
	wndo.setUpScrollControls('scrollbar2');
}

// if code supported, link in the style sheet and call the init function onload
if ( dw_scrollObj.isSupported() ) {
    dw_Util.writeStyleSheet('styles/scrollbar_demo.css')
    dw_Event.add( window, 'load', init_dw_Scroll);
}
</script>
<html:html>
<script type="text/javascript">
	function doAdd() {
		var pText = document.getElementById('pText').value;
		if (pText.length == 0) {
			notimooErrorManager.show({
				title: 'Error',
				message: 'Debe ingresar el mensaje',
				 customClass:'alert_error',
				 sticky: true
			});
			return;
		}
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/addPrivateMessage.st"/>', onSuccess: function(json, responseText){
			var errorResult = json.error;
			if ('notLogged' == errorResult) {
				window.location='<html:rewrite page="/login.jsp"/>';
				return;
			}
	    	var result = json.result;
		   if ('OK' == result) {
		   document.getElementById('pText').value = "";
		   	document.getElementById('addCommentLayer').style.display = 'none';
		   	showOKMessage();
		   } else {
		   	var error = json.error;
		   	showErrorMessage();
		   }
		}}).post({'message': pText});
	}
	
	function showOKMessage() {
		notimooNormalManager.show({
			title: 'Mensaje',
			message: 'Su mensaje ha sido enviado exitosamente.'
		});
	}
	function showErrorMessage() {
		notimooErrorManager.show({
			title: 'Error',
			message: 'Su mensaje no ha podido ser agregado.',
			customClass:'alert_error',
			 sticky: true
		});
	}
</script>
<script type="text/javascript">
	var signAreaShowed = false;
	function showSignArea() {
		if (!signAreaShowed) {
			signAreaShowed = true;
			document.getElementById('outerdiv').style.display = 'block';
		}
	}
</script>
<script type="text/javascript">
	var lastNumber = "0";
	var lastText = "";
	var lastParagraphVersion = "0";
	function getDelegateSiteStatus() {
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
		        if (sitestatus == 'SIGN_SHOW') {
		        	clearTimer();
		        	showSignArea();
		        } else {
		        	if (sitestatus == 'IN_NEGOTIATION') {
		        		var paragraphNumber = json.paragraphNumber;
		        		var paragraphText = json.paragraphText;
		        		var paragraphVersion = json.paragraphVersion;
		        		if (paragraphNumber == "0") {
		        			lastNumber = paragraphNumber;
			        		lastText = paragraphText;
			        		var divObj = document.getElementById("lastParagraphText");
		        			divObj.innerHTML = "-";
		        		} else {
		        			document.getElementById("addPrivateComment").disabled = false;
		        			// le saque || lastText != paragraphText 
			        		if (lastNumber != paragraphNumber || lastParagraphVersion != paragraphVersion) {
			        			var divObj = document.getElementById("lastParagraphText");
			        			divObj.innerHTML = paragraphNumber + ". " + paragraphText;
			        			lastNumber = paragraphNumber;
			        			lastText = paragraphText;
			        			lastParagraphVersion = paragraphVersion;
			        		}
			        	}
		        	} else {
		        		var divObj = document.getElementById("negotiationArea");
		        		divObj.style.display = 'none';
		        		showSignArea();
		        	}
		        }
	        }
	      }
	   }).get({'paragraphVersion': lastParagraphVersion, 'paragraphNumber': lastNumber});
	}
	var timer = setInterval("getDelegateSiteStatus()",<%=com.tdil.simon.web.SystemConfig.getClientParagrahRefreshTime()%>);
	
	function clearTimer() {
		timer = clearInterval(timer);
	}
</script>
<div id="content">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="528" valign="top">
			<!-- inicio tabla template -->
			<table width="528" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" height="19" background="images/interfaces/topTitle.gif" align="left"><div id="blockTitle">Texto de la &uacute;ltima versi&oacute;n consolidada</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="478" height="284" align="left" valign="top">
					<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1">
								<p class="article"><bean:write name="DelegateNegotiationForm" property="versionVO.document.introduction" /></p>
								<logic:iterate name="DelegateNegotiationForm" property="paragraphs" id="paragraph"> 
									<p class="article"><bean:write name="paragraph" property="paragraphNumber" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
								</logic:iterate>
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
					<td colspan="2" width="508" height="30"><div id="sizer" align="right"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
		<td width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="392" valign="top">
			<!-- inicio tabla template -->
			<table width="392" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td width="372" height="19" background="images/interfaces/topTitle.gif" align="left"><div id="blockTitle">Documento: <bean:write name="DelegateNegotiationForm" property="versionVO.document.title" /> - V <bean:write name="DelegateNegotiationForm" property="versionVO.version.number" /> - <bean:write name="DelegateNegotiationForm" property="versionVO.version.name" /></div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td>
						<!-- corte tabla template -->
						<table width="372" border="0" cellspacing="0" cellpadding="0" id="workTable">
							<tr>
								<td colspan="2" height="8"><img src="images/null.gif" width="1" height="8"></td>
							</tr>
							<tr>
								<td width="342" height="157" valign="top">
								<div id="negotiationArea" style="width:342px; height:281px; text-align:left;">
									<div id="lastParagraphText">  -  </div>
								</div>
								</td>
								<td width="30" align="right">
								<div id="scrollbar2" align="right">
									<div id="up2"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
									<div id="track2">
										<div id="dragBar2"></div>
									</div>
									<div id="down2"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
								</div></td>
							</tr>
							<tr>
								<td colspan="2" height="25" align="center"><input type="button" value="Mensaje al delegado" id="addPrivateComment" disabled="true" onClick="document.getElementById('addCommentLayer').style.display = '';"></td>
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
	</tr>
</table>
</div>
</html:html>
<%@ include file="includes/footer.jsp" %>
	<% 	if(isDelegate && isSign && user.isCanSign()) { %>
	<script type="text/javascript">
		signAreaShowed = true;
	</script>
	<% } %>
	<div id="outerdiv" style="display: <%=(isDelegate && isSign && user.isCanSign()) ? "block" : "none"%>;">
		<div id="innerdiv"></div>
		<div id="contentTableComment">
			<table width="980" height="582" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="middle">
						<table width="640" height="370" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
							<tr>
								<td width="640" align="center" valign="top">
									<!-- inicio tabla template -->
									<table width="620" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
										<tr>
											<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
											<td width="600" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Firma en el documento</div></td>
											<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
										</tr>
										<tr>
											<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
											<td width="9"><img src="images/null.gif" width="9" height="1"></td>
											<td width="600">
											<!-- corte tabla template -->
												<%if (isDelegate && user.isCanSign()) { %>
												<embed src="swf/SimonSignaturator.swf" quality="high" width="600" height="330"
												   flashvars="saveUrl=http://<%=com.tdil.simon.web.SystemConfig.getServerUrl()%>/Simon/signVersion.st&goToDocUrl=goToSignShow.st" scale="noscale" salign="l" name="testClass" align="middle"
												   play="true" loop="false" quality="best" allowScriptAccess="always" type="application/x-shockwave-flash"
												   pluginspage="http://www.adobe.com/go/getflashplayer">
												</embed>
												<% } %>
											<!-- corte tabla template -->
											</td>
											<td width="9"><img src="images/null.gif" width="9" height="1"></td>
											<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										</tr>
										<tr>
											<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
											<td background="images/interfaces/bottomCenter.gif" height="10"><img src="images/null.gif" width="1" height="10"></td>
											<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
										</tr>
									</table>
									<!-- fin tabla template -->
								</td>
							</tr>
						</table>
					</td>
				<tr>
			</table>
		</div>
	</div>
<!-- div id="outerdiv" style="display: none;" -->
<div id="addCommentLayer" style="display: none;">
	<!-- div id="innerdiv" -->
		<div id="innerdiv"></div>
		<div id="contentTableComment">
		<table width="980" height="582" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="middle">
					<table width="440" height="225" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td width="440" align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="420" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
										<td background="images/interfaces/topTitle.gif" width="400" height="19" align="left"><div id="blockTitle">Propuesta de Párrafo</div></td>
										<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="400">
										<!-- corte tabla template -->
											<table width="400" border="0" cellspacing="0" cellpadding="0" align="center">
												<tr>
													<td height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td id="error"></td>
												<tr>
												<tr>
													<td><textarea id="pText" class="textfield_effect_area_big"></textarea></td>
												<tr>
												<tr>
													<td height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="center"><input type="button" onClick="doAdd()" value="Enviar" > <input type="button" onClick="document.getElementById('addCommentLayer').style.display = 'none';" value="Cancelar"></td>
												<tr>
											</table>
											<!-- corte tabla template -->
										</td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
										<td background="images/interfaces/bottomCenter.gif" height="10"><img src="images/null.gif" width="1" height="10"></td>
										<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
									</tr>
								</table>
								<!-- fin tabla template -->
							</td>
						</tr>
					</table>
				</td>
			<tr>
		</table>
	</div>
</div>