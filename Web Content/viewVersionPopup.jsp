<%@ page info="viewVersionPopup"%>
<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=ResourceBundleCache.get("ventana", "titulo")%></title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<link href="styles/tdil.css" rel="stylesheet" type="text/css">
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
	height:370px;
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

<script type="text/javascript">
// setDefaults arguments: size unit, default size, minimum, maximum
// optional array of elements or selectors to apply these defaults to
dw_fontSizerDX.setDefaults("px", 13, 9, 26, ['div#main p.article'] );

// set arguments: default size, minimum, maximum
// array of elements or selectors to apply these settings to
dw_fontSizerDX.set(18, 12, 36, ['div#main h2'] );

dw_Event.add( window, 'load', dw_fontSizerDX.init );

</script>
</head>
<body>
<div id="content" style="height:530px; border-top-width: 1px; border-bottom-width: 1px; border-top-style: solid; border-bottom-style: solid; border-top-color: #808080; border-top-color: #808080;">
<html:form action="/viewVersionActionPopup">
	<div id="alcien" style="height:510px; padding-top:20px;">
		<div id="alcincuentaLeft" style="width:24%; height:510px;">
			<div id="mainDocContainer" style="height:470px; margin-top:13px;">
				<div id="blockTitle1"><%=ResourceBundleCache.get("viewVersion", "documentoPrincipal")%></div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td colspan="3" class="titleDocInModule"><bean:write name="ViewVersionPopup" property="version.document.title" /></td>
					</tr>
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td width="60" align="left"><%=ResourceBundleCache.get("viewVersion", "version")%>:</td>
						<td width="30"><div id="versionStrong"><bean:write name="ViewVersionPopup" property="version.version.number" /></div></td>
						<td align="left"><bean:write name="ViewVersionPopup" property="version.version.name" /></td>
					</tr>
					<!-- INICIO: Recupero versión 197 :: Modificada -->
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td height="30"><%=ResourceBundleCache.get("viewVersion", "versiones")%>: </td>
						<td colspan="2" align="left" valign="middle">
						<div id="portaVersiones" style="width:130px;">
						<logic:iterate name="ViewVersionPopup" property="version.reducedVersions" id="otherVersion">
							<logic:equal name="otherVersion" property="current" value="true">
								<b><bean:write name="otherVersion" property="number" /></b>
							</logic:equal>
							<logic:notEqual name="otherVersion" property="current" value="true">
								<html:link  action="/goToViewVersionPopup.st?" paramName="otherVersion" paramProperty="id" paramId="versionID"><bean:write name="otherVersion" property="number" /></html:link>
							</logic:notEqual>
						</logic:iterate>
						</div>
						</td>
					</tr>
					<tr>
						<td width="10" height="30"><img src="images/null.gif" width="10" height="1"></td>
						<td colspan="3" align="center"><a href="./forcedDocs.jsp" ><img src="images/buttons/volver.png" border="0"/></a></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="alcincuentaRight" style="width:70%; border:1px solid #c6c6c6;">
			<div id="blockTitle1"><%=ResourceBundleCache.get("viewVersion", "documento")%></div>
			<div>
				<!-- div id="main" style="height:430px;">
					<div id="lyr1" style="height:430px;"-->
					<!-- div id="documentoCompleto" -->
					<div id="main" style="background-color:#eeeeee; width:660px; height:422px; overflow:scroll;">
						<p class="article"><bean:write name="ViewVersionPopup" property="version.document.introduction" /></p>
						<logic:iterate name="ViewVersionPopup" property="version.paragraphs" id="paragraph"> 
								<p class="article"><bean:write name="paragraph" property="paragraphNumberForDisplay" /><bean:write name="paragraph" property="paragraphDetailForDisplay" />. <bean:write filter="false" name="paragraph" property="paragraphText" /></p>
						</logic:iterate>
					</div>
					<!--/div>
				</div-->
				<!--div id="scrollbar" style="width:20px; height:440px; float:right;">
					<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
					<div id="track" style="height:416px;">
						<div id="dragBar"></div>
					</div>
					<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
				</div -->
			</div>
			<div id="sizer"><a class="increase" href="#" title="Aumentar tamaño del texto"><img src="images/buttons/plus.gif" alt="" border="0" /></a><a class="decrease" href="#" title="Reducir tamaño del texto"><img src="images/buttons/minus.gif" alt="" border="0" /></a><a class="reset" href="#" title="Tamaño normal"><img src="images/buttons/reset.gif" alt="" border="0" /></a></div>
		</div>
	</div>
	</html:form>
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
										<td width="940" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get("viewVersion", "agregarObservacion")%></div></td>
										<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td>
										<!-- corte tabla template -->
											<table width="940" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="10" id="error"></td>
												<tr>
												<tr>
													<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td width="73" height="30" align="right"><%=ResourceBundleCache.get("viewVersion", "parrafo")%>:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="60" align="left">
													<select id="pNumber" onchange="refreshEditorContents(this);">
														<logic:iterate name="ViewVersionPopup" property="version.paragraphs" id="paragraph"> 
															<option value="<bean:write name="paragraph" property="paragraphNumber" />"><bean:write name="paragraph" property="paragraphNumberForDisplay" /></option>
														</logic:iterate>
													</select></td>
													<td width="10"><img src="images/null.gif" width="10" height="1"></td>
													<td width="20" align="right"><input type="checkbox" id="pNewParagraph" onclick="clickNewPar(this);"></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="200" align="left"><%=ResourceBundleCache.get("viewVersion", "solicitarNuevoParrafo")%></td>
													<td width="10"><img src="images/null.gif" width="10" height="1"></td>
													<td width="105"><div id="newParTextTDLabel" style="display: none;"><%=ResourceBundleCache.get("viewVersion", "indicarUbicacion")%>:</div></td>
													<td width="348" valign="middle"><div id="newParTextTD" style="display: none;"><input type="text" id="newParText" name="newPartext" class="textfield_effect_300"></div></td>
												</tr>
												<tr>
													<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top"><%=ResourceBundleCache.get("viewVersion", "observacion")%>: </td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="8" align="left"><div id="editor"></div></td>
												<tr>
												<tr>
													<td colspan="10" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="10" align="center"><input type="button" onclick="doAdd()" value="<%=ResourceBundleCache.get("viewVersion", "agregarObservacion")%>"> <input type="button" onclick="cancelAdd();" value="<%=ResourceBundleCache.get("viewVersion", "cancelar")%>"></td>
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
