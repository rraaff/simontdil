<%@ page info="listObservationsForVersion"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<style type="text/css">
/* hide from incapable browsers */
#portaTabla {
	height:430px;
}  
</style>
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
<%@ include file="includes/footer.jsp" %>
<div id="outerdiv">
	<div id="innerdiv"></div>
	<div id="contetTableComment">
	<table width="980" height="582" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="middle">
				<table width="960" height="500" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
					<tr>
						<td width="960" align="center" valign="top">
							<!-- inicio tabla template -->
							<table width="920" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
									<td background="images/interfaces/topTitle.gif" width="900" height="19" align="left"><div id="blockTitle">Listado de Observaciones</div></td>
									<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
								</tr>
								<tr>
									<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
									<td width="9"><img src="images/null.gif" width="9" height="1"></td>
									<td width="900" height="430" valign="top">
									<!-- corte tabla template -->
									<div id="portaTabla">
										<table width="900" border="0" cellspacing="0" cellpadding="0" align="center">
											<logic:iterate name="ViewVersion" property="observations" id="observation" indexId="iterIndex"> 
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
													<td width="280" valign="top">Párrafo: <span class="dataDinamica"><bean:write name="observation" property="paragraphNumber" /></span><br>
													Fecha de Observación: <span class="dataDinamica"><bean:write name="observation" property="creationDateFormatted" /></span><br>
													Delegado: <span class="dataDinamica"><bean:write name="observation" property="name" /></span><br>
													Delegación: <span class="dataDinamica"><bean:write name="observation" property="countryName" /></span></td>
													<td width="9"><img src="images/null.gif" width="9" height="1"></td>
													<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
													<td width="20" bgcolor="#<%= (iterIndex % 2 == 0) ? "E5E5E5" : "F2F2F2" %>"><img src="images/null.gif" width="20" height="19"></td>
													<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
													<td width="9"><img src="images/null.gif" width="9" height="1"></td>
													<td width="560" valign="top"><p class="dataDinamica"><bean:write name="observation" property="observationText" /></p></td>
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
										</td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td height="30" align="center" valign="middle">
										<html:form action="/viewVersionAction">
											<html:submit property="operation">
												<bean:message key="listObservations.back"/>
											</html:submit>
										</html:form >
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