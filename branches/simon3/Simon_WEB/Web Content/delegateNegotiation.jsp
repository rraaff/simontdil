<%@ page info="delegateNegotiation"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
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

/*function init_dw_Scroll() {
    var wndo = new dw_scrollObj('main', 'lyr1');
    wndo.setUpScrollbar("dragBar", "track", "v", 1, 1);
    wndo.setUpScrollControls('scrollbar');
}

// if code supported, link in the style sheet and call the init function onload
if ( dw_scrollObj.isSupported() ) {
    dw_Util.writeStyleSheet('styles/scrollbar_demo.css')
    dw_Event.add( window, 'load', init_dw_Scroll);
	//dw_Event.add( window, 'resize', init_dw_Scroll);
}*/
</script>
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
	background-color:#eee;
	width:inherit;
	height:500px;
}

#content #signArea {
	display: none;
}
</style>
<script src="scripts/popup.js" type="text/javascript"></script>
<script type="text/javascript">
function openDocs(){
	openPopupWindow('forcedDocs.jsp', 1024, 690, 0, 0, false, true, '<%=ResourceBundleCache.get(getServletInfo(), "tituloPopup")%>', false);
}
</script>
<html:html>
<script type="text/javascript">
	function doAdd() {
		var pText = document.getElementById('pText').value;
		if (pText.length == 0) {
			notimooErrorManager.show({
				title: '<%=ResourceBundleCache.get("ventanaError", "titulo")%>',
				message: '<%=ResourceBundleCache.get(getServletInfo(), "mensajeVacio")%>',
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
			title: '<%=ResourceBundleCache.get(getServletInfo(), "mensaje")%>',
			message: '<%=ResourceBundleCache.get(getServletInfo(), "mensajeEnviado")%>'
		});
	}
	function showErrorMessage() {
		notimooErrorManager.show({
			title: '<%=ResourceBundleCache.get("ventanaError", "titulo")%>',
			message: '<%=ResourceBundleCache.get(getServletInfo(), "mensajeNoEnviado")%>',
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
	var inProgress = false;
	var retries = 0;
	
	var intro = "abcdefghijklmnopqrstuvwxyz";
	
	function getParagraphForDisplay(pNumber) {
		if (pNumber < 500) {
			if (pNumber < intro.length) {
				return intro.charAt(pNumber);
			} else {
				return "z" + (pNumber - intro.length);
			}
		} else {
			return pNumber - 500 + 1;
		}
	}
	
	function getDelegateSiteStatus() {
		if (!inProgress || retries > 60) {
			retries = 0;
			inProgress = true;
			var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getDelegateSiteStatus.st"/>', onSuccess: function(json, responseText){
				inProgress = false;
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
			        	<%if (!user.isCanSign()) { %>
			        		window.location='<html:rewrite page="/goToSignShow.st"/>';
			        	<% } %>
			        } else {
			        	if (sitestatus == 'IN_NEGOTIATION') {
			        		var paragraphNumber = json.paragraphNumber;
			        		var paragraphComment = json.paragraphComment;
			        		var paragraphText = json.paragraphText;
			        		var paragraphVersion = json.paragraphVersion;
			        		if (paragraphNumber == "0") {
			        			lastNumber = paragraphNumber;
				        		lastText = paragraphText;
				        		var divObj = document.getElementById("lastParagraphText");
			        			divObj.innerHTML = "<p class='article'>-</p>";
			        		} else {
			        			if (document.getElementById("addPrivateComment")) {
			        				document.getElementById("addPrivateComment").disabled = false;
			        			}
			        			// le saque || lastText != paragraphText 
				        		if (lastNumber != paragraphNumber || lastParagraphVersion != paragraphVersion) {
				        			var divObj = document.getElementById("lastParagraphText");
				        			var newText = "";
				        			if (paragraphComment.length == 0) { 
				        				newText = "<p class='article'>" + getParagraphForDisplay(paragraphNumber - 1) + ". " + paragraphText + "</p>";
				        			} else {
				        				newText = "<p class='article'>" + getParagraphForDisplay(paragraphNumber - 1) + " " + paragraphComment + ". " + paragraphText + "</p>";
				        			}
				        			divObj.innerHTML = newText;
				        			if (lastNumber == paragraphNumber) {
				        				var pObj = document.getElementById("current_version_" + paragraphNumber);
				        				pObj.innerHTML = newText;
				        			} else {
				        				refreshCompleteCurrentArea();
				        			}
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
		} else {
			retries = retries + 1;
		}
	}
	var timer = setInterval("getDelegateSiteStatus()",<%=com.tdil.simon.web.SystemConfig.getClientParagrahRefreshTime()%>);
	
	function refreshCompleteCurrentArea() {
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getCompleteDocument.st"/>', 
			onSuccess: function(json, responseText){
				var i = 0;
				var newDoc = "";
				while(i < json.paragraph.length) {
					if (json.paragraphComments[i].length == 0) {
						newDoc = newDoc + "<p class='article' id='current_version_" + json.paragraphNumber[i] + "'>" + getParagraphForDisplay(json.paragraphNumber[i] - 1) + ". " + json.paragraph[i] + "</p>"
					} else {
						newDoc = newDoc + "<p class='article' id='current_version_" + json.paragraphNumber[i] + "'>" + getParagraphForDisplay(json.paragraphNumber[i] - 1) + " " + json.paragraphComments[i] + ". " + json.paragraph[i] + "</p>"
					}
					i = i + 1;
				}	
				document.getElementById("main_current").innerHTML = newDoc;
			}
		}).get();
	}
	
	function clearTimer() {
		timer = clearInterval(timer);
	}
	
	/* usage */
	window.addEvent('load',function() {
		var tabset = new TabSet($$('#tabs1 li a'),$$('#contents1 li'));
	});
</script>
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
	<div id="alcien" style="height:560px; padding-top:10px;">
		<div id="alcincuentaLeft" style="width:47%; height:560px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "ultimaConsolidada")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%" height="500" align="left" valign="top" bordercolor="#00CC00">
					<!-- corte tabla template -->
						<!--div id="main">
							<div id="lyr1" -->
							<div class="clear"><img src="images/null.gif" width="1" height="10"></div>
							<div class="tab-container">
								<ul id="tabs1" class="tabs">
									<li><a href=""><%=ResourceBundleCache.get(getServletInfo(), "versionConsolidada")%></a></li>
									<li><a href=""><%=ResourceBundleCache.get(getServletInfo(), "previsualizar")%></a></li>
								</ul>
							</div>
							<div class="clear"><img src="images/null.gif" width="1" height="10"></div>
							<ul id="contents1" class="tabs-content">
								<li>
									<div id="main" style="background-color:#eeeeee; width:inherit; height:440px; overflow:scroll;">
										<p class="article"><bean:write name="DelegateNegotiationForm" property="versionVO.document.introduction" /></p>
										<logic:iterate name="DelegateNegotiationForm" property="paragraphs" id="paragraph"> 
											<p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" /><bean:write name="paragraph" property="paragraphDetailForDisplay" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
										</logic:iterate>
									</div>
								</li>
								<li>
									<div id="main_current" style="background-color:#eeeeee; width:inherit; height:440px; overflow:scroll;">
										<logic:iterate name="DelegateNegotiationForm" property="versionVO.paragraphs" id="paragraph1"> 
											<p class="article" id="current_version_<bean:write name="paragraph1" property="paragraphNumber" />"><bean:write name="paragraph1" property="paragraphNumberForDisplay" /><bean:write name="paragraph1" property="paragraphDetailForDisplay" />. <bean:write filter="false" name="paragraph1" property="paragraphText" /></p>
										</logic:iterate>
									</div>
								</li>
							</ul>
						<!--/div-->
					<!-- corte tabla template --></td>
					<!-- td width="10"><img src="images/null.gif" width="10" height="1"></td>
					<td width="5" align="right" -->
					<!--div id="scrollbar" style="width:20px; height:480px;">
						<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track" style="height:456px;">
							<div id="dragBar"></div>
						</div>
						<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
					</div></td-->
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="30"><div id="sizer" align="right"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
		<div id="alcincuentaRight" style="width:47%; height:500px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "documento")%>: <bean:write name="DelegateNegotiationForm" property="versionVO.document.title" /> - V <bean:write name="DelegateNegotiationForm" property="versionVO.version.number" /> - <bean:write name="DelegateNegotiationForm" property="versionVO.version.name" /></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%">
						<!-- corte tabla template -->
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="workTable">
							<tr>
								<td height="8"><img src="images/null.gif" width="1" height="8"></td>
							</tr>
							<tr>
								<td width="100%" valign="top">
								<div id="negotiationArea" style="background-color:#eeeeee; width:inherit; height:490px; overflow:scroll;">
									<div id="lastParagraphText" style="width:inherit; height:500px;">  -  </div>
								</div>
								</td>
							</tr>
							<% if (user.isCanProposeParagraph()) { %>
							<!--tr>
								<td colspan="2" height="32" align="center" valign="bottom"><input type="button" value="<%=ResourceBundleCache.get(getServletInfo(), "proponerParrafo")%>" id="addPrivateComment" disabled="true" onClick="document.getElementById('addCommentLayer').style.display = '';"></td>
							</tr -->
							<% } %>
							<tr>
								<td height="30" align="center" valign="bottom"><input type="button" value="<%=ResourceBundleCache.get(getServletInfo(), "abrirDocumentos")%>" id="openDocs" onClick="openDocs();"></td>
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
		</div>
	</div>
</td>
<%@ include file="includes/rightContent.jsp" %>
</html:html>
<% 	if(isDelegate && isSign && user.isCanSign()) { %>
<script type="text/javascript">
	signAreaShowed = true;
</script>
<% } %>
<div id="outerdiv" style="display: <%=(isDelegate && isSign && user.isCanSign()) ? "block" : "none"%>;">
	<div id="innerdiv"></div>
	<div id="contentTableComment">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td align="center" valign="middle"><br/><br/><br/><br/><br/><br/>
					<table width="940" height="640" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td width="940" align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="920" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
										<td width="600" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "firma")%></div></td>
										<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="900">
										<!-- corte tabla template -->
											<%if (isDelegate && user.isCanSign()) { %>
											<embed src="swf/SimonSignaturator.swf" quality="high" width="900" height="600"
											   flashvars="saveUrl=<%=com.tdil.simon.web.SystemConfig.getServerUrl()%>/signVersion.st&goToDocUrl=goToSignShow.st" scale="noscale" salign="l" name="testClass" align="middle"
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
<div id="addCommentLayer" style="display: none;">
	<div id="innerdiv"></div>
	<div id="contentTableComment">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td align="center" valign="middle">
					<table width="440" height="230" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="420" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
										<td background="images/interfaces/topTitle.gif" width="400" height="19" align="left"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "propuestaDeParrafo")%></div></td>
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
													<td align="center"><input type="button" onClick="doAdd()" value="<%=ResourceBundleCache.get(getServletInfo(), "enviar")%>" > <input type="button" onClick="document.getElementById('addCommentLayer').style.display = 'none';" value="<%=ResourceBundleCache.get(getServletInfo(), "cancelar")%>"></td>
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
<%@ include file="includes/footer.jsp" %>