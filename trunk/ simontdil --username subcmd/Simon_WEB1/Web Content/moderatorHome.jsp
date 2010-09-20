<%@ page info="moderatorHome"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<div id="content">
	<div id="alcien" style="height:200px;">
		<div id="alcincuentaLeft" style="height:200px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td background="images/interfaces/topLeftTitleDoc.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><img src="images/titles/documentoPrincipal.gif" alt="Ingreso al Sitio" width="132" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="10" height="20" background="images/interfaces/middleLeftDoc.gif"><img src="images/null.gif" width="1" height="20"></td>
					<td></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td background="images/interfaces/middleLeftDoc.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
					<td height="100%">
						<div id="contentDocumentoPrincipal">
							<logic:equal name="ModeratorHome" property="hasTypeOne" value="true"> 
								<div class="titleDocInModule"><html:link  action="/goToViewVersion.st?" paramName="ModeratorHome" paramProperty="typeOne.id" paramId="versionID"><bean:write name="ModeratorHome" property="typeOne.documentTitle" /></html:link></div>
								Versi&oacute;n: <bean:write name="ModeratorHome" property="typeOne.number" /> - <bean:write name="ModeratorHome" property="typeOne.name" /><br>
								L&iacute;mite para observaciones: <bean:write name="ModeratorHome" property="typeOne.limitObservationsString" />
							</logic:equal>
						</div>
						<!-- corte tabla template --></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="10" height="20" background="images/interfaces/middleLeftDoc.gif"><img src="images/null.gif" width="1" height="20"></td>
					<td></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td background="images/interfaces/bottomLeftDoc.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
		<div id="alcincuentaRight" style="height:200px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td width="10" height="19" background="images/interfaces/topLeftTitleDocB.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><img src="images/titles/documentoPrincipal.gif" alt="Ingreso al Sitio" width="132" height="19"></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="10" height="20" background="images/interfaces/middleLeftDocB.gif"><img src="images/null.gif" width="1" height="20"></td>
					<td></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="10" height="1" background="images/interfaces/middleLeftDocB.gif"><img src="images/null.gif" width="10" height="1"></td>
					<td height="100%">
						<!-- corte tabla template -->
						<div id="contentDocumentoPrincipal">
						<logic:equal name="ModeratorHome" property="hasTypeTwo" value="true">
							<div class="titleDocInModule"><html:link  action="/goToViewVersion.st?" paramName="ModeratorHome" paramProperty="typeTwo.id" paramId="versionID"><bean:write name="ModeratorHome" property="typeTwo.documentTitle" /></html:link></div>
							Versi&oacute;n: <bean:write name="ModeratorHome" property="typeTwo.number" /> - <bean:write name="ModeratorHome" property="typeTwo.name" /><br>
							L&iacute;mite para observaciones: <bean:write name="ModeratorHome" property="typeTwo.limitObservationsString" />
						</logic:equal>
						</div>
						<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td width="10" height="20" background="images/interfaces/middleLeftDocB.gif"><img src="images/null.gif" width="1" height="20"></td>
					<td></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td background="images/interfaces/bottomLeftDocB.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="alcien">
		<div id="alcincuentaLeft">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><img src="images/titles/documentosSecundarios.gif" alt="Ingreso al Sitio" width="154" height="19"></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="100%" valign="top">
						<!-- corte tabla template -->
						<div id="contentDocumentoSecundarios">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr> 
									<td height="15"><img src="images/null.gif" width="1" height="15"></td>
								</tr>
								<logic:iterate name="ModeratorHome" property="otherDocumentsList" id="doc" indexId="referenceListIndex"> 
									<tr> 
										<td height="22" class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><html:link action="/goToViewLastVersionOfDocument.st?" paramName="doc" paramProperty="id" paramId="documentID"><bean:write name="doc" property="title" /></html:link></td>
									</tr> 
								</logic:iterate>
								<tr> 
									<td height="15"><img src="images/null.gif" width="1" height="15"></td>
								</tr>
							</table>
						</div>
						<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td><img src="images/null.gif" width="9" height="1"></td>
					<td height="20" valign="right"></td>
					<td><img src="images/null.gif" width="9" height="1"></td>
					<td bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
		<div id="alcincuentaRight">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><img src="images/titles/libreriaDocsReferencia.gif" alt="Ingreso al Sitio" width="215" height="19"></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="100%" valign="top">
						<!-- corte tabla template -->
						<div id="contentDocumentoSecundarios">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr> 
									<td height="15"><img src="images/null.gif" width="1" height="15"></td>
								</tr>
								<logic:iterate name="ModeratorHome" property="referenceList" id="ref" indexId="referenceListIndex"> 
									<tr> 
										<td height="22" class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><bean:write name="ref" property="title" /></td>
									</tr> 
								</logic:iterate>
								<tr> 
									<td height="11"><img src="images/null.gif" width="1" height="11"></td>
								</tr>
							</table>
						</div>
						<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td><img src="images/null.gif" width="9" height="1"></td>
					<td height="20" valign="right"><html:link  action="/goToReferenceDocLibrary.st">Ver libreria de referencia</html:link></td>
					<td><img src="images/null.gif" width="9" height="1"></td>
					<td bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<%@ include file="includes/footer.jsp" %>