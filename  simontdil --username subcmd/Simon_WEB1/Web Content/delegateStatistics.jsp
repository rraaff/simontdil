<%@ page info="delegateStatistics"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<style>
<!--
#portaTabla{
	width:900px;
}
-->
</style>
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
	<tr>
		<td width="940">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="920" height="19" align="left"><div id="blockTitle">Estadísticas</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="900" height="144">
					<!-- corte tabla template -->
						<div id="portaTabla">
							<table border="0" cellspacing="0" cellpadding="0" align="left">
								<tr>
									<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
								</tr>
								<tr>
									<td width="60" height="20" align="left">Fecha</td>
									<td width="250" align="left">Acciones</td>
									<td align="left" colspan="<bean:write name="StatisticsForm" property="countryCount"/>">Delegaciones</td>
								</tr>
								<tr>
									<td height="20" bgcolor="#E6E6E6">&nbsp;</td>
									<td bgcolor="#E6E6E6">&nbsp;</td>
									<logic:iterate name="StatisticsForm" property="statisticsVO.allCountries" id="countryIter"> 
										<td align="left" bgcolor="#E6E6E6"><bean:write name="countryIter" property="name" /></td>
									</logic:iterate>
								</tr>
								<tr>
									<td height="20" bgcolor="#CCCCCC">&nbsp;</td>
									<td align="left" bgcolor="#CCCCCC">Último ingreso:</td>
									<logic:iterate name="StatisticsForm" property="lastLogins" id="lastLogin"> 
										<td align="left" bgcolor="#CCCCCC"><bean:write name="lastLogin"/></td>
									</logic:iterate>
								</tr>
								<logic:iterate name="StatisticsForm" property="statisticsVO.versionStats" id="versionStat"> 
									<tr>
									<td height="20" align="left"><bean:write name="versionStat" property="formattedDate"/></td>
									<td height="20" align="left"><bean:write name="versionStat" property="docName"/></td>
									<logic:iterate name="versionStat" property="stats" id="rdStat"> 
										<td width="150" valign="bottom"><logic:equal name="rdStat" property="read" value="true"><img src="images/icons/leido.gif" width="16" height="16" border="0"></logic:equal> / <logic:equal name="rdStat" property="download" value="true"><img src="images/icons/descargado.gif" width="16" height="16" border="0"></logic:equal></td>
									</logic:iterate>
									</tr>
									<logic:iterate name="versionStat" property="observationsStats" id="rdObservationStat" indexId="rdObservationStatIndex">
										<tr>
											<td height="20" align="left"><bean:write name="rdObservationStat" property="formattedDate"/></td>
											<td height="20" align="left">Observación #<bean:write name="rdObservationStat" property="observationNumber"/></td>
											<logic:iterate name="rdObservationStat" property="stats" id="rdObsStat"> 
												<td valign="bottom"><logic:equal name="rdObsStat" property="read" value="true"><img src="images/icons/leido.gif" width="16" height="16" border="0"></logic:equal> / <logic:equal name="rdObsStat" property="download" value="true"><img src="images/icons/descargado.gif" width="16" height="16" border="0"></logic:equal></td>
											</logic:iterate>
										</tr>
									 </logic:iterate>
								</logic:iterate>
								<tr>
									<td colspan="7" height="25"><img src="images/null.gif" width="1" height="25"></td>
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
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
	<tr>
		<td colspan="3" height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
</table>
</div>
<%@ include file="includes/footer.jsp" %>