<%@ page info="searchObservations"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>


<div id="content">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="300">
			<!-- inicio tabla template -->
			<img src="images/null.gif" width="1" height="340">
			<!-- fin tabla template -->
		</td>
	</tr>
</table>
</div>
<%@ include file="includes/footer.jsp" %>
<html:form method="POST" action="/searchObservations">
<div id="outerdiv">
	<div id="innerdiv"></div>
	<div id="contetTableComment">
		<table height="582" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="middle">
					<table width="330" height="410" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td width="310" align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="310" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
										<td background="images/interfaces/topTitle.gif" width="290" height="19" align="left"><div id="blockTitle">Agregar comentario de versión</div></td>
										<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="290"><!-- corte tabla template -->
											<table width="290" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="5" height="25"><img src="images/null.gif" width="1" height="25"></td>
												</tr>
												<tr>
													<td width="60" align="right" valign="top">Delegaci&oacute;n:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="3" width="223" align="left">
													<html:select name="SearchObservationsForm" property="countryId" styleClass="textfield_effect">
														<html:optionsCollection name="SearchObservationsForm" property="allCountries" value="id" label="name"/>
													</html:select></td>
												</tr>
												<tr>
													<td colspan="5" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top"><html:radio name="SearchObservationsForm" property="exactDate" value="exact"/></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="3" align="left">Buscar por fecha exacta</td>
												</tr>
												<tr>
													<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top"></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td align="right" valign="top">
													<html:select name="SearchObservationsForm" property="exactDay" styleClass="textfield_effect_day">
														<html:optionsCollection name="SearchObservationsForm" property="days" value="dayNumber" label="dayNumber"/>
													</html:select></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td align="left"> de 
													<html:select name="SearchObservationsForm" property="exactMonth" styleClass="textfield_effect_month">
														<html:optionsCollection name="SearchObservationsForm" property="months" value="monthNumber" label="monthText"/>
													</html:select>
													</td>
												</tr>
												<tr>
													<td colspan="4" height="30"><img src="images/null.gif" width="1" height="30"></td>
												</tr>
												<tr>
													<td align="right" valign="top"><html:radio name="SearchObservationsForm" property="exactDate" value="range"/></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="3" align="left">Buscar por rango de fechas</td>
												</tr>
												<tr>
													<td colspan="5" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top">Desde:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td align="right" valign="top">
													<html:select name="SearchObservationsForm" property="lowerDay" styleClass="textfield_effect_day">
														<html:optionsCollection name="SearchObservationsForm" property="days" value="dayNumber" label="dayNumber"/>
												      </html:select></td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td align="left"> de 
													<html:select name="SearchObservationsForm" property="lowerMonth" styleClass="textfield_effect_month">
														<html:optionsCollection name="SearchObservationsForm" property="months" value="monthNumber" label="monthText"/>
													</html:select>
													</td>
												</tr>
												<tr>
													<td colspan="5" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top">Hasta:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td align="right" valign="top">
													<html:select name="SearchObservationsForm" property="upperDay" styleClass="textfield_effect_day">
														<html:optionsCollection name="SearchObservationsForm" property="days" value="dayNumber" label="dayNumber"/>
													</html:select>
													</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td align="left"> de 
													<html:select name="SearchObservationsForm" property="upperMonth" styleClass="textfield_effect_month">
														<html:optionsCollection name="SearchObservationsForm" property="months" value="monthNumber" label="monthText"/>
													</html:select>
													</td>
												</tr>
												<tr>
													<td colspan="5" height="30"><img src="images/null.gif" width="1" height="30"></td>
												</tr>
												<tr>
													<td align="right" valign="top">Parrafo:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="3" align="left">
													<html:select name="SearchObservationsForm" property="paragraphNumber" styleClass="textfield_effect_day">
														<html:options name="SearchObservationsForm" property="allParagraphs"/>
													</html:select></td>
												</tr>
												<tr>
													<td colspan="5" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td align="right" valign="top">Palabras:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td colspan="3" align="left"><html:text name="SearchObservationsForm" property="words" styleClass="textfield_effect"/></td>
												</tr>
												<tr>
													<td colspan="5" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="5" align="center">
													<html:submit property="operation">
														<bean:message key="searchObservations.search"/>
													</html:submit>
													<html:submit property="operation">
														<bean:message key="searchObservations.back"/>
													</html:submit></td>
												</tr>
												<tr>
													<td colspan="5" height="25"><img src="images/null.gif" width="1" height="25"></td>
												</tr>
											</table>
											<span class="errorText"><html:errors property="general" /></span>
											<!-- corte tabla template --></td>
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
				</td>
			<tr>
		</table>
	</div>
</div>
</html:form>