<%@ page info="listPrivateObservationsForVersion"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<style type="text/css">
/* hide from incapable browsers */
div#main {
	background-color:#FFFFFF;
	width:910px;
	height:430px;
}
div#scrollbar {
	display:none;
}
</style>
<script src="scripts/dw_event.js" type="text/javascript"></script>
<script src="scripts/dw_scroll.js" type="text/javascript"></script>
<script src="scripts/dw_scrollbar.js" type="text/javascript"></script>
<script src="scripts/scroll_controls.js" type="text/javascript"></script>
<script type="text/javascript">
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
<html:html>
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
	<tr>
		<td width="960" height="300" align="center"><img src="images/null.gif" width="1" height="300"></td>
	</tr>
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
</table>
</div>
</html:html>
<div id="outerdiv">
	<div id="innerdiv"></div>
	<div id="contentTableComment">
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="middle">
				<table width="960" height="500" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
					<tr>
						<td width="960" align="center" valign="top">
							<!-- inicio tabla template -->
							<table width="920" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
									<td colspan="2" width="900" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Listado de mensajes privados</div></td>
									<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
								</tr>
								<tr>
									<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
									<td width="9"><img src="images/null.gif" width="9" height="1"></td>
									<td width="900" height="430" valign="top">
									<!-- corte tabla template -->
									<div id="main">
										<div id="lyr1">
											<table width="900" border="0" cellspacing="0" cellpadding="0" align="center">
												<logic:iterate name="ListPrivate" property="list" id="observation" indexId="iterIndex"> 
												<tr>
													<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
													<td width="280" height="19" background="images/interfaces/topTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
													<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
													<td width="20" background="images/interfaces/topListSeparator<%= (iterIndex % 2 == 0) ? "1" : "2" %>.gif"><img src="images/null.gif" width="20" height="19"></td>
													<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
													<td width="560" height="19" background="images/interfaces/topTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
													<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
												</tr>
												<tr>
													<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
													<td width="9"><img src="images/null.gif" width="9" height="1"></td>
													<td width="280" align="left" valign="top"><p><img src="./download.do?action=flag&fileId=<bean:write name="observation" property="countryId" />" width="30" height="30"></p>
													Delegación: <span class="dataDinamica"><bean:write name="observation" property="countryName" /></span><br>
													Párrafo: <span class="dataDinamica"><bean:write name="observation" property="paragraphNumberForDisplay" /></span><br>
													Fecha de Observación: <span class="dataDinamica"><bean:write name="observation" property="creationDateFormatted" /></span><br>
													<!-- Delegado: <span class="dataDinamica"><bean:write name="observation" property="name" /></span><br>-->
													
													<div style="height:25px; margin-top:10px;"><html:link  action="deletePrivateObservation.st?" paramName="observation" paramProperty="id" paramId="id"><img src="images/buttons/borrar.png" width="50" height="24" border="0"></html:link></div></td>
													<td width="9"><img src="images/null.gif" width="9" height="1"></td>
													<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
													<td width="20" bgcolor="#<%= (iterIndex % 2 == 0) ? "E5E5E5" : "F2F2F2" %>"><img src="images/null.gif" width="20" height="19"></td>
													<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
													<td width="9"><img src="images/null.gif" width="9" height="1"></td>
													<td width="560" align="left" valign="top"><p class="dataDinamica"><bean:write name="observation" filter="false" property="observationText" /></p></td>
													<td width="9"><img src="images/null.gif" width="9" height="1"></td>
													<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
												</tr>
												<tr>
													<td width="10" height="10" colspan="2" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
													<td width="280" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
													<td width="10" height="10" colspan="2" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
													<td width="20" bgcolor="#<%= (iterIndex % 2 == 0) ? "E5E5E5" : "F2F2F2" %>"><img src="images/null.gif" width="20" height="10"></td>
													<td width="10" height="10" colspan="2" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
													<td width="600" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
													<td width="10" height="10" colspan="2" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
												</tr>
												</logic:iterate>
											</table>	
										<!-- corte tabla template -->
										</div>
									</div>
									</td>
									<td width="30" align="right">
										<div id="scrollbar" style="width:20px; height:640px;">
											<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
											<div id="track" style="height:616px;">
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
									<td colspan="2" height="30" align="center" valign="middle">
									<logic:equal name="ListPrivate" property="param(paragraphNegotiated)" value="true">
										<html:form action="/paragraphNavigation">
											<html:submit property="operation">
												<bean:message key="listPrivateObservations.back"/>
											</html:submit>
										</html:form >
									</logic:equal>
									<logic:notEqual name="ListPrivate" property="param(paragraphNegotiated)" value="true">
										<html:form action="/createDocumentActionStep2">
											<html:submit property="operation">
												<bean:message key="listPrivateObservations.back"/>
											</html:submit>
										</html:form >
									</logic:notEqual>
									</td>
									<td width="9"><img src="images/null.gif" width="9" height="1"></td>
									<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
								</tr>
								<tr>
									<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
									<td colspan="2" background="images/interfaces/bottomCenter.gif" height="10"><img src="images/null.gif" width="1" height="10"></td>
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