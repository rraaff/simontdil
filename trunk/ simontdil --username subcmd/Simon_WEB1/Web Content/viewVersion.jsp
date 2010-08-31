<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<style type="text/css">
/* hide from incapable browsers */
div#sizer { display:none; }  
/* breathing room between images in sizer */
div#sizer img { margin-right:3px; }

div#main { background-color:#eee; }  
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

<html:form action="/viewVersionAction">
<!-- 	<a href="./goToModeratorHome.st">Home</a><br>
	<a href="./goToDelegateHome.st">Home</a><br>

 -->
	<%if (isModerator) { %>
		<logic:equal name="ViewVersion" property="versionCanBeNegotiated" value="true">
			<html:submit property="operation">
				<bean:message key="viewVersion.initNegotiation"/>
			</html:submit>
		</logic:equal>
	<% } %>
<div id="content">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="5" height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
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
					<td width="274" height="165"><!-- corte tabla template -->
						<div id="contentDocumentoPrincipal">
							<div class="titleDocInModule"><bean:write name="ViewVersion" property="version.document.title" /></div><br>
							<p style="line-height: 12px;">Versi&oacute;n: <bean:write name="ViewVersion" property="version.version.number" /> - <bean:write name="ViewVersion" property="version.version.name" /><br>
							L&iacute;mite para observaciones: <bean:write name="ViewVersion" property="version.version.limitObservationsString" /></p><br>
						</div>
						<html:submit property="operation">
							<bean:message key="viewVersion.downloadPdf"/>
						</html:submit>
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
								<td align="right" width="50" height="30"><span style="font-size: 10px;">Anteriores:</span></td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="217" align="left" valign="middle"><div id="portaVersiones"><!--
									<table border="0">
										<tr>
											--><logic:iterate name="ViewVersion" property="version.allVersions" id="otherVersion"><!--
												<td --><html:link  action="/goToViewVersion.st?" paramName="otherVersion" paramProperty="id" paramId="versionID"><bean:write name="otherVersion" property="number" /></html:link>&nbsp;<!-- </td>
											--></logic:iterate><!--
										</tr>
									</table>
								--></div></td>
							</tr>
							<tr>
								<td colspan="3" height="3"><img src="images/null.gif" width="1" height="3"></td>
							</tr>
							<tr>
								<td colspan="3">
	<% if (isDelegate) { %>
		<logic:equal name="ViewVersion" property="versionCanBeCommented" value="true">
			<script type="text/javascript">
				function doAdd() {
					var paragraphNumber = document.getElementById('pNumber').value;
					var newPar = document.getElementById('pNewParagraph').checked ? "true" : "false";
					var pText = document.getElementById('pText').value;
					var pVersion = '<bean:write name="ViewVersion" property="version.version.id" />';
					new Ajax.Request('<html:rewrite page="/addObservation.st"/>', {
					    parameters: { pNumber:paragraphNumber, newPar:newPar, pText:pText, pVersion:pVersion},
					    onComplete: 
							function(transport, json) {
							   var result = json.result;
							   if ('OK' == result) {
							   document.getElementById('pNumber').value = "";
							   document.getElementById('pNewParagraph').checked = false;
							   document.getElementById('pText').value = "";
							   	document.getElementById('addCommentLayer').style.display = 'none';
							   } else {
							   	var error = json.error;
							   	document.getElementById('error').innertHTML= error;
							   }
							 }
					});
				}
			</script>
			<div id="addCommentLayer" style="display: none;">
				<table>
					<tr><td id="error"></td><tr>
					<tr><td>parrafo<input type="text" id="pNumber"></td><tr>
					<tr><td>new par<input type="checkbox" id="pNewParagraph"></td><tr>
					<tr><td>observation<textarea id="pText"></textarea></td><tr>
					<tr><td><input type="button" onclick="doAdd()" value="Agregar observacion"><input type="button" onclick="document.getElementById('addCommentLayer').style.display = 'none';" value="Cancelar"></td><tr>
				</table>
			</div>
			<input type="button" value="A�adir observacion" onclick="document.getElementById('addCommentLayer').style.display = '';">
		</logic:equal>
		<logic:notEqual name="ViewVersion" property="versionCanBeCommented" value="true">
			<html:submit property="operation" disabled="true">
				<bean:message key="viewVersion.addObservation"/>
			</html:submit>
		</logic:notEqual>
	<% } else { %>
		<html:submit property="operation" disabled="true">
			<bean:message key="viewVersion.addObservation"/>
		</html:submit>
	<% } %>
								</td>
							</tr>
							<tr>
								<td colspan="3" height="8"><img src="images/null.gif" width="1" height="8"></td>
							</tr>
							<tr>
								<td colspan="3">
	<html:submit property="operation" disabled="true">
		<bean:message key="viewVersion.searchObservations"/>
	</html:submit>
								</td>
							</tr>
							<tr>
								<td colspan="3" height="8"><img src="images/null.gif" width="1" height="8"></td>
							</tr>
							<tr>
								<td colspan="3">
	<html:submit property="operation">
		<bean:message key="viewVersion.listObservations"/>
	</html:submit>
</td>
							</tr>
						</table>					
						<!-- corte tabla template --></td>
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
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><img src="images/titles/documentosSecundarios.gif" alt="Ingreso al Sitio" width="154" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="608" height="284" align="left" valign="top">
					<!-- corte tabla template -->
					<div id="main">
						<div id="documentoCompleto">
							<logic:iterate name="ViewVersion" property="version.paragraphs" id="paragraph"> 
							<p class="article"><bean:write name="paragraph" property="paragraphNumber" />.<bean:write name="paragraph" property="paragraphText" /></p> 
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
	</tr>
	<tr>
		<td colspan="5" height="20" align="right"><img src="images/null.gif" width="10" height="20"></td>
	</tr>
</table>
</div>
</html:form >
</html:html>
<%@ include file="includes/footer.jsp" %>