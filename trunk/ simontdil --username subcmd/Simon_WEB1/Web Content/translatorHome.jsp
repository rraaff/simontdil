<%@ page info="assistantHome"%>
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
	dw_Event.add( window, 'resize', init_dw_Scroll);
}

</script>
<script type="text/javascript">
	
	function getDelegateSiteStatus() {
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getDelegateSiteStatus.st"/>', onSuccess: function(json, responseText){
			var errorResult = json.error;
			if ('notLogged' == errorResult) {
				window.location='<html:rewrite page="/login.jsp"/>';
				return;
			}
		    var sitestatus = json.sitestatus;
			if (sitestatus != 'IN_NEGOTIATION') {
				window.location='<html:rewrite page="/login.jsp"/>';
				return;
			}
		}}).get();
	}
	timer = setInterval("getDelegateSiteStatus()",5000);

</script>
<%if (isAdministrator || isDesigner) { %>
<%@ include file="includes/menu.jsp" %>
<% } %>
<div id="content">
<form name="refreshForm" action="goToTranslatorHome.st">
</form>
	<div id="alcien" style="height:560px; padding-top:20px;">
		<div id="alcincuentaLeft" style="width:24%; height:540px;">
			<div id="mainDocContainer" style="height:260px; margin-top:13px;">
				<div id="blockTitle1">Negocia&ccedil;&atilde;o de documento</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td width="10"><img src="images/null.gif" width="10" height="1"></td>
						<td colspan="3" class="titleDocInModule"><bean:write name="TranslatorHome" property="version.document.title" /></td>
					</tr>
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td width="60" align="left">Vers&atilde;o:</td>
						<td width="30"><div id="versionStrong"><bean:write name="TranslatorHome" property="version.version.number" /></div></td>
						<td align="left"><bean:write name="TranslatorHome" property="version.version.name" /></td>
					</tr>
				</table>
			</div>
			<div id="mainDocContainer" style="margin-top:20px;">
				<div id="blockTitle2">Ac&ccedil;&otilde;es Dispon&iacute;veis</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><img src="images/null.gif" width="1" height="10"></td>
					</tr>
					<tr>
						<td align="center">
									<script type="text/javascript">
										var notimooAddManager = new Notimoo();
										var alreadyReplaced = false;
										var editor;
										
										function refreshEditorContents(editor, selectObj) {
											var paragraphNumber = document.getElementById('pNumber').value;
											var paragraphtext = document.getElementById('p_' + selectObj.value).innerHTML;
											editor.setData("Consultado...");
											var pVersion = '<bean:write name="TranslatorHome" property="version.version.id" />';
											var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getTranslatorObservationAction.st"/>', onSuccess: function(json, responseText){
												var errorResult = json.error;
												if ('notLogged' == errorResult) {
													window.location='<html:rewrite page="/login.jsp"/>';
													return;
												}
												var result = json.result;
											   if ('OK' == result) {
											   	if (json.exists == 'true') {
											   		editor.setData(json.translation);
											   	} else {
											   		editor.setData("");
											   	}
											   } else {
												editor.setData("");
											}
											}}).post({'pNumber':paragraphNumber, 'pVersion':pVersion});
										}
										
										function extractParagraphContent(content) {
											return content.substring(content.indexOf('.') + 1, content.length);
										}
										
										function modifyTranslation() {
											if ( editor )
												return;
											editor = CKEDITOR.appendTo( 'editor', {
												// Defines a simpler toolbar to be used in this sample.
												// Note that we have added out "MyButton" button here.
												toolbar : [ ['Bold', 'Italic', 'Underline', 'Strike','-'] ,['TextColor','BGColor']],
												height:"400", width:"800",
												baseFloatZIndex: 100002
											});
											document.getElementById('outerdiv').style.display = '';
											refreshEditorContents(editor, document.getElementById('pNumber'));
										}
										
										function addObservationFor(pObj, pNumber) {
											if ( editor )
												return;
											editor = CKEDITOR.appendTo( 'editor', {
												// Defines a simpler toolbar to be used in this sample.
												// Note that we have added out "MyButton" button here.
												toolbar : [ ['Bold', 'Italic', 'Underline', 'Strike','-'] ,['TextColor','BGColor']],
												height:"400", width:"800",
												baseFloatZIndex: 100002
											}, "");
											var pNumberObj = document.getElementById('pNumber');
											var opts = pNumberObj.options;
											var index = 0;
											while(opts[index].value != pNumber) {
												index = index + 1;
											}
											opts[index].selected = true;
											document.getElementById('outerdiv').style.display = '';
											refreshEditorContents(editor, document.getElementById('pNumber'));
										}
										
										function cancelAdd() {
											document.getElementById('outerdiv').style.display = 'none';
											editor.destroy();
											editor = null;
										}
										
										function doAdd() {
											var pText = editor.getData();
											if (pText.length == 0) {
												Sexy.error('Você deve digitar a tradução do parágrafo.');
												return;
											}
											Sexy.confirm('Traduzido parágrafo podem ser editados posteriormente. Sem embarlo, uma vez que adicionou a tradução pode ser visto pelos delegados. Verifique se estão corretas. Se você quiser adicionar ou editar clique em "OK", porém se você quiser editar ou revisá-lo antes de enviá-lo, pressione "Cancelar".', { onComplete: 
										        function(returnvalue) {
										          if(returnvalue) {
										            basicDoAdd();
										          } 
										        }
										      });
										}
									
										function basicDoAdd() {
											var paragraphNumber = document.getElementById('pNumber').value;
											var pText = editor.getData();
											var pVersion = '<bean:write name="TranslatorHome" property="version.version.id" />';
											var jsonRequest = new Request.JSON({url: '<html:rewrite page="/addTranslatorObservationAction.st"/>', onSuccess: function(json, responseText){
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
												document.getElementById('outerdiv').style.display = 'none';
												showOKMessage();
											   } else {
												var error = json.error;
												showErrorMessage();
											}
											}}).post({'pNumber':paragraphNumber, 'pText':pText, 'pVersion':pVersion});
						
										}
										var notimooObservationManager = new Notimoo();
										
										function showOKMessage() {
											Sexy.info('Sua tradução foi adicionada com sucesso.');
										}
										
										function showErrorMessage() {
											Sexy.error('Sua tradução não foi adicionado.');
										}
										
										function refreshPage() {
											document.forms['refreshForm'].submit();
										}
									</script>
									<input type="button" value="Editar Tradução" onclick="modifyTranslation();">
							</td>
					</tr>
					<tr>
						<td><img src="images/null.gif" width="1" height="10"></td>
					</tr>
					<tr>
						<td  align="center"><input type="button" value="Atualizar" onclick="refreshPage();"></td>
					</tr>
					<tr>
						<td><img src="images/null.gif" width="1" height="10"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="alcincuentaRight" style="border:1px solid #c6c6c6;">
			<div id="blockTitle1">Documento</div>
			<div>
				<div id="main">
					<div id="lyr1">
					<!-- div id="documentoCompleto" -->
						<p class="article"><bean:write name="TranslatorHome" property="version.document.introduction" /></p>
						<logic:iterate name="TranslatorHome" property="version.paragraphs" id="paragraph"> 
							<p id="p_<bean:write name="paragraph" property="paragraphNumber" />" class="article" onclick="addObservationFor(this, '<bean:write name="paragraph" property="paragraphNumber" />')"><bean:write name="paragraph" property="paragraphNumberForDisplay" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
						</logic:iterate>
					</div>
				</div>
				<div id="scrollbar" style="width:20px; height:470px; float:right;">
					<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
					<div id="track" style="height:446px;">
						<div id="dragBar"></div>
					</div>
					<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
				</div>
			</div>
			<div id="sizer"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div>
		</div>
	</div>
</div>
<div id="outerdiv" style="display: none;">
	<div id="innerdiv"></div>
	<div id="contentTableComment">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td align="center" valign="middle">
					<table width="980" height="600" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="960" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
										<td width="940" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Adicionar Tradu&ccedil;&atilde;o</div></td>
										<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td>
										<!-- corte tabla template -->
											<table width="940" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="7" id="error"></td>
												<tr>
												<tr>
													<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td width="73" height="30" align="right">Par&aacute;grafo:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="60" align="left">
													<select id="pNumber" onchange="refreshEditorContents(editor, this);">
														<logic:iterate name="TranslatorHome" property="version.paragraphs" id="paragraph"> 
															<option value="<bean:write name="paragraph" property="paragraphNumber" />"><bean:write name="paragraph" property="paragraphNumberForDisplay" /></option>
														</logic:iterate>
													</select></td>
													<td width="10"><img src="images/null.gif" width="10" height="1"></td>
													<td width="20" align="right"></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="348" valign="middle"></td>
												</tr>
												<tr>
													<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top">Texto:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="5" align="left"><div id="editor"></div></td>
												<tr>
												<tr>
													<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="7" align="center"><input type="button" onclick="doAdd()" value="   Ok   "> <input type="button" onclick="cancelAdd();" value="Cancelar"></td>
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