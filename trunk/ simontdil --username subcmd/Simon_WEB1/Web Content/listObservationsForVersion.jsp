<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerDelegate.jsp" %>

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
					<td background="images/interfaces/topTitle.gif" width="920" height="19" align="left"><div id="blockTitle">Listado de Observaciones</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="920" height="300" valign="top">
					<!-- corte tabla template -->
					<div id="portaTabla">
						<table width="920" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="50" height="20">Parrafo</td>
								<td width="200">Fecha</td>
								<td width="200">Delegado</td>
								<td width="150">Delegación</td>
								<td width="250">Observación</td>
								<td width="70">Ver</td>
							</tr>
							<logic:iterate name="ViewVersion" property="observations" id="observation"> 
							<tr class="d0"><!-- En el iterador cambiar el style por d1 -->
								<td height="28"><bean:write name="observation" property="paragraphNumber" /></td>
								<td><bean:write name="observation" property="creationDate" /></td>
								<td><bean:write name="observation" property="name" /></td> 
								<td><bean:write name="observation" property="countryName" /></td>
								<td><bean:write name="observation" property="observationText" /></td>
								<td>Ver</td>
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