<%@ page info="listDocumentsForModerator"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:html>
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
	<tr>
		<td width="960" align="center">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="920" height="19" align="left"><div id="blockTitle">Listado de Documentos</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="920" height="300" valign="top">
					<!-- corte tabla template -->
					<div id="portaTabla">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td height="20">Documento</td>
								<td>Versión</td>
								<td>Estado</td>
								<td>Nombre de Versión</td>
								<td>Observaciones</td>
								<td>Cantidad de observaciones</td>
								<td>Editar</td>
								<td>Borrar</td>
							</tr>
							<logic:iterate name="ListDocument" property="list" id="version" indexId="iterIndex"> 
							<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
								<td height="28"><bean:write name="version" property="documentTitle" /></td>
								<td><bean:write name="version" property="versionWithSubversion" /></td>
								<td><bean:write name="version" property="status" /></td> 
								<td><bean:write name="version" property="name" /></td>
								<td><bean:write name="version" property="hasObservationText" /></td>
								<td><bean:write name="version" property="observationCountText" /></td>
								<% if (!((com.tdil.simon.data.model.Version)version).isHasDraft()) { %>  
									<td><html:link  action="editVersion.st?" paramName="version" paramProperty="id" paramId="id"><img src="images/buttons/editar.png" width="50" height="24" border="0"></html:link>
								<% } else { %>  
									<td>-</td>
								<% } %> 
								<td></td>
							</tr> 
							</logic:iterate>
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
						</table>	
					<!-- corte tabla template -->
					</div>
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
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
</table>
</div>
</html:html>
<%@ include file="includes/footer.jsp" %>