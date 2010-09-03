<%@ page info="delegateNegotiation"%>
<%@ page contentType="text/html; charset=Cp1252" %>
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

div#main { background-color:#eee; }  
#content #negotiationArea #lastParagraphText {
	font-size: 11px;
	color: #333366;
	background-color: #F3F3F3;
	width: 214px;
	height: 146px;
	display: block;
	overflow: scroll;
}
#content #signArea {
	display: none;
}
</style>
<script src="scripts/dw_event.js" type="text/javascript"></script>
<script src="scripts/dw_cookies.js" type="text/javascript"></script>
<script src="scripts/dw_sizerdx.js" type="text/javascript"></script>
<script type="text/javascript">
// setDefaults arguments: size unit, default size, minimum, maximum
// optional array of elements or selectors to apply these defaults to
dw_fontSizerDX.setDefaults("px", 13, 9, 26, ['div#main p.article'] );

// set arguments: default size, minimum, maximum
// array of elements or selectors to apply these settings to
dw_fontSizerDX.set(18, 12, 36, ['div#main h2'] );

dw_Event.add( window, 'load', dw_fontSizerDX.init );
</script>
<html:html>
<script type="text/javascript">
	function doAdd() {
		var pText = document.getElementById('pText').value;
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/addPrivateMessage.st"/>', onSuccess: function(json, responseText){
	    var result = json.result;
		   if ('OK' == result) {
		   document.getElementById('pText').value = "";
		   	document.getElementById('addCommentLayer').style.display = 'none';
		   } else {
		   	var error = json.error;
		   	document.getElementById('error').innertHTML= error;
		   }
		}}).get({'message': pText});
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
	var lastNumber = "";
	var lastText = "";
	function getDelegateSiteStatus() {
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getDelegateSiteStatus.st"/>', onSuccess: 
			function(json, responseText){
	        var sitestatus = json.sitestatus;
	        if (sitestatus == 'NORMAL') {
	        	window.location='<html:rewrite page="/goToDelegateHome.st"/>';
	        } else {
		        if (sitestatus == 'SIGN_SHOW') {
		        	clearTimer();
		        	showSignArea();
		        	// PABLO: Aca tendria que ir codigo javascript que muestre el boton de ir a ver el doc final
		        } else {
		        	if (sitestatus == 'IN_NEGOTIATION') {
		        		var paragraphNumber = json.paragraphNumber;
		        		var paragraphText = json.paragraphText;
		        		if (paragraphNumber == "0") {
		        			lastNumber = paragraphNumber;
			        		lastText = paragraphText;
			        		var divObj = document.getElementById("lastParagraphText");
		        			divObj.innerHTML = "-";
		        		} else {
		        			document.getElementById("addPrivateComment").disabled = false;
			        		if (lastNumber != paragraphNumber || lastText != paragraphText) {
			        			var divObj = document.getElementById("lastParagraphText");
			        			divObj.innerHTML = paragraphNumber + ". " + paragraphText;
			        			lastNumber = paragraphNumber;
			        			lastText = paragraphText;
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
	   }).get();
	}
	var timer = setInterval("getDelegateSiteStatus()",1000);
	
	function clearTimer() {
		timer = clearInterval(timer);
	}
</script>
<div id="content">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" height="20"><img src="images/null.gif" width="1" height="20" id="imgReferencia" name="imgReferencia"></td>
	</tr>
	<tr>
		<td width="608" valign="top">
			<!-- inicio tabla template -->
			<table width="608" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><div id="blockTitle">Texto de la &uacute;ltima versi&oacute;n consolidada</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="608" height="284" align="left" valign="top">
					<!-- corte tabla template -->
					<div id="main">
						<div id="documentoCompleto">
						<logic:iterate name="DelegateNegotiationForm" property="versionVO.paragraphs" id="paragraph"> 
							<p class="article"><bean:write name="paragraph" property="paragraphNumber" />. <bean:write name="paragraph" property="paragraphText" /></p>
						</logic:iterate>
						</div>
					</div>
					<!-- corte tabla template --></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="608" height="30"><div id="sizer" align="right"><a class="increase" href="#" title="Increase text size"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Decrease text size"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Restore default font-sizes"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div></td>
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
		<td width="294" valign="top">
			<!-- inicio tabla template -->
			<table width="294" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td background="images/interfaces/topLeftTitleDoc.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><div id="blockTitle">Documento en Negociación</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td background="images/interfaces/middleLeftDoc.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
					<td width="274" height="50" valign="middle">
					<!-- corte tabla template -->
						<div id="contentDocumentoPrincipal">
							<div class="titleDocInModule"><br><bean:write name="DelegateNegotiationForm" property="versionVO.document.title" /></div>
							<p style="line-height: 12px;">Versi&oacute;n: <bean:write name="DelegateNegotiationForm" property="versionVO.version.number" /> - <bean:write name="DelegateNegotiationForm" property="versionVO.version.name" /></p>
						</div>
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
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><div id="blockTitle">Draft del párrafo</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="274">
						<!-- corte tabla template -->
						<table width="274" border="0" cellspacing="0" cellpadding="0" id="workTable">
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="50" align="right" valign="top">Texto: </td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="217" height="157" align="left" valign="top">
								<div id="negotiationArea">
									<div id="lastParagraphText">  -  </div>
									<!--div id="addCommentLayer" style="display: none;">
										<table>
											<tr>
												<td id="error"></td>
											<tr>
											<tr>
												<td>Mensaje del párrafo: <textarea id="pText"></textarea></td>
											<tr>
											<tr>
												<td><input type="button" onClick="doAdd()" value="Agregar observacion" ><input type="button" onClick="document.getElementById('addCommentLayer').style.display = 'none';" value="Cancelar"></td>
											<tr>
										</table>
									</div-->
								</div></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" height="25" align="center"><input type="button" value="Mensaje al delegado" id="addPrivateComment" disabled="true" onClick="document.getElementById('addCommentLayer').style.display = '';"></td>
							</tr>
							<tr>
								<td colspan="3" height="3"><img src="images/null.gif" width="1" height="3"></td>
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
	<tr>
		<td colspan="5" height="20" align="right"><img src="images/null.gif" width="10" height="20"></td>
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
		<div id="contetTableComment">
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
											<td background="images/interfaces/topTitle.gif" width="400" height="19" align="left"><div id="blockTitle">Enviar mensaje privado al Moderador</div></td>
											<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
										</tr>
										<tr>
											<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
											<td width="9"><img src="images/null.gif" width="9" height="1"></td>
											<td width="400">
											<!-- corte tabla template -->
												<%if (isDelegate && user.isCanSign()) { %>
												<embed src="swf/SimonSignaturator.swf" quality="high" width="400" height="220"
												   flashvars="saveUrl=http://localhost:8180/Simon/signVersion.st" scale="noscale" salign="l" name="testClass" align="middle"
												   play="true" loop="false" quality="best" allowScriptAccess="always" type="application/x-shockwave-flash"
												   pluginspage="http://www.adobe.com/go/getflashplayer">
												</embed>
												<% } %>
												<html:form action="/goToSignShow">
													<html:submit property="operation">
														<bean:message key="delegateNegotiation.signShow"/>
													</html:submit>
												</html:form>
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
		<div id="contetTableComment">
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
										<td background="images/interfaces/topTitle.gif" width="400" height="19" align="left"><div id="blockTitle">Enviar mensaje privado al Moderador</div></td>
										<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="400">
										<!-- corte tabla template -->
											<table width="400" border="0" cellspacing="0" cellpadding="0" align="center">
												<tr>
													<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="3" id="error"></td>
												<tr>
												<tr>
													<td width="150" valign="top">Mensaje del párrafo:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="243"><textarea id="pText" class="textfield_effect_area"></textarea></td>
												<tr>
												<tr>
													<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="3" align="center"><input type="button" onClick="doAdd()" value="Agregar observacion" ><input type="button" onClick="document.getElementById('addCommentLayer').style.display = 'none';" value="Cancelar"></td>
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
