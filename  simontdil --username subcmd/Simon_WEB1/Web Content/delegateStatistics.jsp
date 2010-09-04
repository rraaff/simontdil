<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
	</tr>
	<tr>
		<td height="20">Fecha</td>
		<td>Acciones</td>
		<td colspan="<bean:write name="StatisticsForm" property="countryCount"/>">Delegaciones</td>
	</tr>
	<tr>
		<td height="20">&nbsp;</td>
		<td>&nbsp;</td>
		<logic:iterate name="StatisticsForm" property="statisticsVO.allCountries" id="countryIter"> 
			<td><bean:write name="countryIter" property="name" /></td>
		</logic:iterate>
	</tr>
	<tr>
		<td height="20">&nbsp;</td>
		<td>Ultimo Login</td>
		<logic:iterate name="StatisticsForm" property="lastLogins" id="lastLogin"> 
			<td><bean:write name="lastLogin"/></td>
		</logic:iterate>
	</tr>
	<logic:iterate name="StatisticsForm" property="statisticsVO.versionStats" id="versionStat"> 
		<tr>
		<td height="20"><bean:write name="versionStat" property="formattedDate"/></td>
		<td height="20"><bean:write name="versionStat" property="docName"/></td>
		<logic:iterate name="versionStat" property="stats" id="rdStat"> 
			<td><logic:equal name="rdStat" property="read" value="true">L</logic:equal>/
					<logic:equal name="rdStat" property="download" value="true">D</logic:equal></td>
		</logic:iterate>
		</tr>
		<logic:iterate name="versionStat" property="observationsStats" id="rdObservationStat" indexId="rdObservationStatIndex">
			<tr>
			<td height="20"><bean:write name="rdObservationStat" property="formattedDate"/></td>
			<td height="20">Observacion #<bean:write name="rdObservationStat" property="observationNumber"/></td>
			<logic:iterate name="rdObservationStat" property="stats" id="rdObsStat"> 
				<td><logic:equal name="rdObsStat" property="read" value="true">L</logic:equal>/
					<logic:equal name="rdObsStat" property="download" value="true">D</logic:equal></td>
			</logic:iterate>
			</tr>
		 </logic:iterate>
	</logic:iterate>
	
</table>


