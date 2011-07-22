<%@ page info="viewVersion"%>
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
	height:450px;
	float:left;
	margin: 0px;
	position: relative;
}
#mainDocContainer {
	border: 1px solid #c6c6c6;
	position: relative;
	width:100%;
	height:230px;
	text-align:center;
}

#mainDocContainer #leftObject {
	/*background-color:#00FF00;*/
	width:100%;
	height:30px;
	text-align:center;
	padding-top:10px;
	float:left;
}
lyr1{
	
}
#content #portaVersiones a {
	color: #FFFFFF;
}
#content #portaVersiones {
	width:130px;
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
<script type="text/javascript">

dw_fontSizerDX.setDefaults("px", 13, 9, 26, ['div#main p.article'] );


dw_Event.add( window, 'load', dw_fontSizerDX.init );

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
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
<html:form action="/viewVersionAction">
	<div id="alcien" style="height:560px; padding-top:20px;">
		<div id="alcincuentaLeft" style="width:24%; height:540px;">
			<div id="mainDocContainer" style="height:250px; margin-top:13px; padding-left:10px;">
				<div id="blockTitle1"><%=ResourceBundleCache.get(getServletInfo(), "documentoPrincipal")%></div>
				<div id="leftObject" style=" text-align:left;"><span class="titleDocInModule"><bean:write name="ViewVersion" property="version.document.title" /></span>
				<div id="leftObject" style="text-align:left;"><%=ResourceBundleCache.get(getServletInfo(), "version")%>: <span class="titleDocInModule"><bean:write name="ViewVersion" property="version.version.number" /> <bean:write name="ViewVersion" property="version.version.name" /></span></div>
				<div id="leftObject" style="text-align:left;">
					<table>
						<tr>
							<td><%=ResourceBundleCache.get(getServletInfo(), "versiones")%>: </td>
							<td><div id="portaVersiones">
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
					</table>
				</div>
				<div id="leftObject" style="text-align:left;"><%=ResourceBundleCache.get(getServletInfo(), "limiteObservaciones")%>: <bean:write name="ViewVersion" property="version.version.limitObservationsString" /></div>
				<div id="leftObject">
					<html:submit property="operation">
						<%=ResourceBundleCache.get("viewFinalVersionSingle", "bajarPdf")%>
					</html:submit>
				</div>
				<div id="leftObject">
					<html:submit property="operation">
						<%=ResourceBundleCache.get("viewFinalVersionSingle", "bajarRtf")%>
					</html:submit></div>
				</div>
			</div>
			<div id="mainDocContainer" style="margin-top:20px; padding-left:10px;">
				<div id="blockTitle2" style="float:left;"><%=ResourceBundleCache.get(getServletInfo(), "acciones")%></div>
				<% if (isDelegate) { %>
					<div id="leftObject">
						<table align="center">
							<tr>
								<td><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonStart()%><html:link action="/goToDelegateHome" ><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonMiddle("botones","volver")%></html:link><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonEnd()%></td>
							</tr>
						</table>
					</div>
				<% } %>
				<% if (isModerator) { %>
					<div id="leftObject">
						<table align="center">
							<tr>
								<td><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonStart()%><html:link action="/goToModeratorHome" ><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonMiddle("botones","volver")%></html:link><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonEnd()%></td>
							</tr>
						</table>
					</div>
					<logic:equal name="ViewVersion" property="versionCanBeNegotiated" value="true">
						<div id="leftObject"><html:submit property="operation">
							<%=ResourceBundleCache.get(getServletInfo(), "negociar")%>
						</html:submit></div>
					</logic:equal>
					<logic:equal name="ViewVersion" property="versionIsInSign" value="true">
						<div id="leftObject"><html:submit property="operation">
							<%=ResourceBundleCache.get(getServletInfo(), "finalizarFirmas")%>
						</html:submit></div>
					</logic:equal>
					<logic:equal name="ViewVersion" property="versionCanBeEdited" value="true">
						<div id="leftObject">
							<table align="center">
								<tr>
									<td><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonStart()%>
										<html:link  action="editVersion.st?" paramName="ViewVersion" paramProperty="version.version.id" paramId="id">
											<%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonMiddle("botones","editar")%>
										</html:link>
									</td>
								</tr>
							</table>
						
						</div>
					</logic:equal>
				<% } %>
				<% if (isDelegate) { %>
					<logic:equal name="ViewVersion" property="versionCanBeCommented" value="true">
					<script type="text/javascript">
						var notimooAddManager = new Notimoo();
						var alreadyReplaced = false;
						var editor;
						
						function refreshEditorContents(selectObj) {
							var paragraphtext = document.getElementById('p_' + selectObj.value).innerHTML;
							editor.setData(extractParagraphContent(paragraphtext));
						}
						
						function extractParagraphContent(content) {
							return content.substring(content.indexOf('.') + 1, content.length);
						}
						
						function clickNewPar(chkObj) {
							if (chkObj.checked) {
								document.getElementById('newParTextTD').style.display = 'block';
								document.getElementById('newParTextTDLabel').style.display = 'block';
							} else {
								document.getElementById('newParTextTD').style.display = 'none';
								document.getElementById('newParTextTDLabel').style.display = 'none';
							}
						}
						
						function addObservation() {
							document.getElementById('pNewParagraph').checked = false;
							document.getElementById('newParTextTD').style.display = 'none';
							document.getElementById('newParText').value = '';
							if ( editor )
								return;
							editor = CKEDITOR.appendTo( 'editor', {
								// Defines a simpler toolbar to be used in this sample.
								// Note that we have added out "MyButton" button here.
								toolbar : [ ['Bold', 'Italic', 'Underline', 'Strike','-'] ,['TextColor','BGColor']],
								height:"300", width:"700",
								baseFloatZIndex: 100002
							});
							document.getElementById('outerdiv').style.display = '';
							refreshEditorContents(document.getElementById('pNumber'));
						}
						
						function addObservationFor(pObj, pNumber) {
							document.getElementById('pNewParagraph').checked = false;
							document.getElementById('newParTextTD').style.display = 'none';
							document.getElementById('newParText').value = '';
							if ( editor )
								return;
							editor = CKEDITOR.appendTo( 'editor', {
								// Defines a simpler toolbar to be used in this sample.
								// Note that we have added out "MyButton" button here.
								toolbar : [ ['Bold', 'Italic', 'Underline', 'Strike','-'] ,['TextColor','BGColor']],
								height:"300", width:"700",
								baseFloatZIndex: 100002
							}, extractParagraphContent(pObj.innerHTML));
							var pNumberObj = document.getElementById('pNumber');
							var opts = pNumberObj.options;
							var index = 0;
							while(opts[index].value != pNumber) {
								index = index + 1;
							}
							opts[index].selected = true;
							document.getElementById('outerdiv').style.display = '';
						}
						
						function cancelAdd() {
							document.getElementById('outerdiv').style.display = 'none';
							editor.destroy();
							editor = null;
						}
						
						function doAdd() {
							var pText = editor.getData();
							if (pText.length == 0) {
								Sexy.error('<%=ResourceBundleCache.get(getServletInfo(), "observacionVacia")%>');
								return;
							}
							if (pText.indexOf("<img ") != -1) {
								pText = pText.replace(/<img/g, '&lt;img');
							}
							Sexy.confirm('<%=ResourceBundleCache.get(getServletInfo(), "confirmeObservacion")%>', { onComplete: 
								function(returnvalue) {
								  if(returnvalue) {
									basicDoAdd();
								  } 
								}
							  });
						}
					
						function basicDoAdd() {
							var paragraphNumber = document.getElementById('pNumber').value;
							var newPar = document.getElementById('pNewParagraph').checked ? "true" : "false";
							var pText = editor.getData();
							if (document.getElementById('pNewParagraph').checked) {
								pText = document.getElementById('newParText').value + " - " + pText;
							}
							var pVersion = '<bean:write name="ViewVersion" property="version.version.id" />';
							var jsonRequest = new Request.JSON({url: '<html:rewrite page="/addObservation.st"/>', onSuccess: function(json, responseText){
								var errorResult = json.error;
								if ('notLogged' == errorResult) {
									window.location='<html:rewrite page="/login.jsp"/>';
									return;
								}
								var result = json.result;
							   if ('OK' == result) {
							   editor.destroy();
							   editor = null;
							   document.getElementById('pNumber').selectedIndex = 0;
							   document.getElementById('pNewParagraph').checked = false;
								document.getElementById('outerdiv').style.display = 'none';
								showOKMessage();
							   } else {
								var error = json.error;
								showErrorMessage();
							}
							}}).post({'pNumber':paragraphNumber, 'newPar':newPar, 'pText':pText, 'pVersion':pVersion});
		
						}
						var notimooObservationManager = new Notimoo();
						
						function showOKMessage() {
							Sexy.info('<%=ResourceBundleCache.get(getServletInfo(), "observacionAgregada")%>');
						}
						
						function showErrorMessage() {
							Sexy.error('<%=ResourceBundleCache.get(getServletInfo(), "observacionNoAgregada")%>');
						}
					</script>
					<div id="leftObject"><input type="button" value="<%=ResourceBundleCache.get(getServletInfo(), "agregarObservacion")%>" onclick="addObservation();"></div>
					</logic:equal>
					<div id="leftObject">
						<logic:notEqual name="ViewVersion" property="versionCanBeCommented" value="true">
							<html:submit property="operation" disabled="true">
								<%=ResourceBundleCache.get(getServletInfo(), "agregarObservacion")%>
							</html:submit>
						</logic:notEqual>
					</div>
				<% } %>
				<div id="leftObject">
					<html:submit property="operation">
						<%=ResourceBundleCache.get(getServletInfo(), "buscarObservaciones")%>
					</html:submit>
				</div>
				<div id="leftObject">
					<html:submit property="operation">
						<%=ResourceBundleCache.get(getServletInfo(), "listarObservaciones")%>
					</html:submit>
				</div>
			</div>
		</div>
		<div id="alcincuentaRight" style="width:70%; height:503px; float:left; border:1px solid #c6c6c6;">
			<div id="blockTitle1" style="float:left;"><%=ResourceBundleCache.get(getServletInfo(), "documento")%></div>
			<div id="main" style="width:100%; height:430px; float:left; overflow:auto;">
				<p class="article"><bean:write name="ViewVersion" property="version.document.introduction" /></p>
				<logic:iterate name="ViewVersion" property="version.paragraphs" id="paragraph"> 
					<logic:equal name="ViewVersion" property="versionCanBeCommented" value="true">
						<% if (isDelegate) { %>
							<p id="p_<bean:write name="paragraph" property="paragraphNumber" />" class="article" onclick="addObservationFor(this, '<bean:write name="paragraph" property="paragraphNumber" />')"><bean:write name="paragraph" property="paragraphNumberForDisplay" /><bean:write name="paragraph" property="paragraphDetailForDisplay" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
						<% } else { %>
							<p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" /><bean:write name="paragraph" property="paragraphDetailForDisplay" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
						<% } %>
					</logic:equal>
					<logic:notEqual name="ViewVersion" property="versionCanBeCommented" value="true">
						<p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" /><bean:write name="paragraph" property="paragraphDetailForDisplay" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
					</logic:notEqual>
				</logic:iterate>
			</div>
			<div id="sizer"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div>
		</div>
	</div>
	</html:form>
</div>
</td>
<%@ include file="includes/rightContent.jsp" %>
<div id="outerdiv" style="display:none;">
	<div id="innerdiv"></div>
	<div id="contentTableComment">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td align="center" valign="middle">
					<table width="800" height="500" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="780" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
										<td width="760" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">A&ntilde;adir observaci&oacute;n</div></td>
										<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td>
										<!-- corte tabla template -->
											<table width="720" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="10" id="error"></td>
												<tr>
												<tr>
													<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td width="73" height="30" align="right"><%=ResourceBundleCache.get(getServletInfo(), "parrafo")%>:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="60" align="left">
													<select id="pNumber" onchange="refreshEditorContents(this);">
														<logic:iterate name="ViewVersion" property="version.paragraphs" id="paragraph"> 
															<option value="<bean:write name="paragraph" property="paragraphNumber" />"><bean:write name="paragraph" property="paragraphNumberForDisplay" /></option>
														</logic:iterate>
													</select></td>
													<td width="10"><img src="images/null.gif" width="10" height="1"></td>
													<td width="20" align="right"><input type="checkbox" id="pNewParagraph" onclick="clickNewPar(this);"></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="150" align="left"><%=ResourceBundleCache.get(getServletInfo(), "solicitarNuevoParrafo")%></td>
													<td width="10"><img src="images/null.gif" width="10" height="1"></td>
													<td width="105"><div id="newParTextTDLabel" style="display: none;"><%=ResourceBundleCache.get(getServletInfo(), "indicarUbicacion")%>:</div></td>
													<td width="278" valign="middle"><div id="newParTextTD" style="display: none;"><input type="text" id="newParText" name="newPartext" class="textfield_effect_300"></div></td>
												</tr>
												<tr>
													<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top"><%=ResourceBundleCache.get(getServletInfo(), "observacion")%>: </td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="8" align="left"><div id="editor"></div></td>
												<tr>
												<tr>
													<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="10" align="center"><input type="button" onclick="doAdd()" value="<%=ResourceBundleCache.get(getServletInfo(), "agregarObservacion")%>"> <input type="button" onclick="cancelAdd();" value="<%=ResourceBundleCache.get(getServletInfo(), "cancelar")%>"></td>
												<tr>
											</table>
										<!-- corte tabla template -->
										</td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
										<td height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
										<td colspan="2" width="10" height="10" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>