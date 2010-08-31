<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<html:html>
<style type="text/css">
<!--
#content #centrador{
	padding-left:130px;
}
-->
</style>
<div id="content">
Moderador: <%=com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus()%> 
Delegados: <%=com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus()%> 
Pantalla Pública: <%=com.tdil.simon.data.model.Site.getPUBLIC_SITE().getStatus()%>

<% 	if(com.tdil.simon.data.model.Site.EVENT.equals(com.tdil.simon.data.model.Site.getMODERATOR_SITE().getStatus())) { %>
	<% 	if(com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
		<html:link action="/initSign" >Iniciar firmas</html:link>
		<span id="privCount"><%=com.tdil.simon.utils.ObservationUtils.countPrivateObservationsForNegotiatedVersion()%></span>
	<% } %>
	<% 	if(com.tdil.simon.data.model.Site.IN_SIGN.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
		En firmas, Debería haber un botón.
	<% } %>
	<% 	if(com.tdil.simon.data.model.Site.NORMAL.equals(com.tdil.simon.data.model.Site.getDELEGATE_SITE().getStatus())) { %>
		<html:link action="/initNegotiation" >Iniciar negociacion</html:link>
	<% } %>
<% } %>
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="5" height="5"><img src="images/null.gif" width="1" height="5"></td>
	</tr>
	<tr>
		<td width="294">
			<!-- inicio tabla template -->
			<table width="294" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td background="images/interfaces/topLeftTitleDoc.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><img src="images/titles/documentoPrincipal.gif" alt="Ingreso al Sitio" width="132" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td background="images/interfaces/middleLeftDoc.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
					<td width="274" height="82"><!-- corte tabla template -->
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
					<td background="images/interfaces/bottomLeftDoc.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		<!-- separador -->
		<div id="separador1Home"><img src="images/null.gif" width="1" height="87"><div>
		<!-- inicio tabla template -->
			<table width="294" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td background="images/interfaces/topLeftTitleDocB.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><img src="images/titles/documentoPrincipal.gif" alt="Ingreso al Sitio" width="132" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td background="images/interfaces/middleLeftDocB.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
					<td width="274" height="82">
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
					<td background="images/interfaces/bottomLeftDocB.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
		<td width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="294">
		<!-- inicio tabla template -->
			<table width="294" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><img src="images/titles/documentosSecundarios.gif" alt="Ingreso al Sitio" width="154" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="274" height="280" valign="middle"><!-- corte tabla template -->
						<div id="contentDocumentoSecundarios">
							<table width="254" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr> 
									<td height="15"><img src="images/null.gif" width="1" height="15"></td>
								</tr>
								<logic:iterate name="ModeratorHome" property="otherDocumentsList" id="doc" indexId="referenceListIndex"> 
									<tr> 
										<td height="22" class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><bean:write name="doc" property="title" /></td>
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
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
		<td width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="294">
		<!-- inicio tabla template -->
			<table width="294" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="274" height="19" align="left"><img src="images/titles/libreriaDocsReferencia.gif" alt="Ingreso al Sitio" width="215" height="19"></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="274" height="280" valign="middle"><!-- corte tabla template -->
						<div id="contentDocumentoSecundarios">
							<table width="254" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr> 
									<td height="15"><img src="images/null.gif" width="1" height="15"></td>
								</tr>
								<logic:iterate name="ModeratorHome" property="referenceList" id="ref" indexId="referenceListIndex"> 
									<tr> 
										<td height="22" class="row<%=referenceListIndex%2 == 0 ? "ODD" : "EVEN"%>"><bean:write name="ref" property="title" /></td>
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
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
	<tr>
		<td colspan="5" height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
</table>
</div>
</html:html>
<%@ include file="includes/footer.jsp" %>

