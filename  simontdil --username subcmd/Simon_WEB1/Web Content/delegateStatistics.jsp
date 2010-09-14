<%@ page info="delegateStatistics"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
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
		<td width="940">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="920" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Estadísticas</div></td>
					<td width="10" height="19" colspan="2" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
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
									<td width="80" height="20" align="left">Fecha</td>
									<td width="200" align="left">Acciones</td>
									<td align="left" colspan="<bean:write name="StatisticsForm" property="countryCount"/>">Delegaciones</td>
								</tr>
								<tr>
									<td height="20" bgcolor="#E6E6E6"><img src="images/null.gif" width="60" height="1"></td>
									<td bgcolor="#E6E6E6"><img src="images/null.gif" width="200" height="1"></td>
									<logic:iterate name="StatisticsForm" property="statisticsVO.allCountries" id="countryIter"> 
										<td width="80" align="left" bgcolor="#E6E6E6"><bean:write name="countryIter" property="name" /></td>
									</logic:iterate>
								</tr>
								<tr>
									<td height="20" bgcolor="#CCCCCC">&nbsp;</td>
									<td align="left" bgcolor="#CCCCCC">Último ingreso:</td>
									<logic:iterate name="StatisticsForm" property="lastLogins" id="lastLogin"> 
										<td align="left" bgcolor="#CCCCCC"><bean:write name="lastLogin"/></td>
									</logic:iterate>
								</tr>
								<% int bgColor = 0; %>
								<logic:iterate name="StatisticsForm" property="statisticsVO.versionStats" id="versionStat"> 
									<tr>
									<td bgcolor="<%= (bgColor % 2 == 0) ? "#E6E6E6" : "#CCCCCC" %>" height="20" align="left"><bean:write name="versionStat" property="formattedDate"/></td>
									<td bgcolor="<%= (bgColor % 2 == 0) ? "#E6E6E6" : "#CCCCCC" %>" height="20" align="left"><bean:write name="versionStat" property="docName"/></td>
									<logic:iterate name="versionStat" property="stats" id="rdStat"> 
										<td bgcolor="<%= (bgColor % 2 == 0) ? "#E6E6E6" : "#CCCCCC" %>" width="150" valign="bottom"><%--
											--%><logic:equal name="rdStat" property="read" value="true"><img src="images/icons/leido.png" width="16" height="16" border="0"></logic:equal><%--
											--%><logic:equal name="rdStat" property="read" value="false"><img src="images/null.gif" width="16" height="16" border="0"></logic:equal><%--
											--%> / <%--
											--%><logic:equal name="rdStat" property="download" value="true"><img src="images/icons/descargado.png" width="16" height="16" border="0"></logic:equal><%--
											--%><logic:equal name="rdStat" property="download" value="false"><img src="images/null.gif" width="16" height="16" border="0"></logic:equal><%--
											--%></td>
									</logic:iterate>
									<% bgColor = bgColor + 1; %>
									</tr>
									<logic:iterate name="versionStat" property="observationsStats" id="rdObservationStat" indexId="rdObservationStatIndex">
										<tr>
											<td bgcolor="<%= (bgColor % 2 == 0) ? "#E6E6E6" : "#CCCCCC" %>" height="20" align="left"><bean:write name="rdObservationStat" property="formattedDate"/></td>
											<td bgcolor="<%= (bgColor % 2 == 0) ? "#E6E6E6" : "#CCCCCC" %>" height="20" align="left">Observación #<bean:write name="rdObservationStat" property="observationNumber"/></td>
											<logic:iterate name="rdObservationStat" property="stats" id="rdObsStat"> 
												<td bgcolor="<%= (bgColor % 2 == 0) ? "#E6E6E6" : "#CCCCCC" %>" valign="bottom"><%--
													--%><logic:equal name="rdObsStat" property="read" value="true"><img src="images/icons/leido.png" width="16" height="16" border="0"></logic:equal><%--
													--%><logic:equal name="rdObsStat" property="read" value="false"><img src="images/null.gif" width="16" height="16" border="0"></logic:equal><%--
												--%> / <%--
												--%><logic:equal name="rdObsStat" property="download" value="true"><img src="images/icons/descargado.png" width="16" height="16" border="0"></logic:equal><%--
												--%><logic:equal name="rdObsStat" property="download" value="false"><img src="images/null.gif" width="16" height="16" border="0"></logic:equal><%--
												--%></td>
											</logic:iterate>
											<% bgColor = bgColor + 1; %>
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
</table>
</div>
<%@ include file="includes/footer.jsp" %>