<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:html>
		<html:errors property="general" />
		<html:form method="POST" action="/createDocumentActionStep2">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
	<tr>
		<td width="960">
			<table border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="500" height="19" align="left"><div id="blockTitle">Edición de documentos (Paso 1 de 4)</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="500" height="144"><!-- corte tabla template -->
						<table width="500" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" colspan="3"><p align="left">&nbsp;</p></td>
							</tr>
							<tr>
								<td align="right" width="192">Titulo:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="301" align="left"><html:text name="CreateDocumentForm" property="title" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><html:errors property="title" /><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right" width="192">Versi&oacute;n:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="301" align="left"><bean:write name="CreateDocumentForm" property="versionNumber" /></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">Nombre de la versi&oacute;n:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:text name="CreateDocumentForm" property="versionName" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><html:errors property="versionName" /><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="192" align="right"><html:checkbox name="CreateDocumentForm" property="principal"/></td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="301" align="left">Marcar como documento principal a negociar</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">L&iacute;mite para observaciones:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:text name="CreateDocumentForm" property="limitObservationsDay" styleClass="textfield_effect_day"/> de <html:text name="CreateDocumentForm" property="limitObservationsMonth" styleClass="textfield_effect_month"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11">
								<select name="day" class="textfield_effect_day">
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
								</select><img src="images/null.gif" width="1" height="11">
								<select name="day" class="textfield_effect_month">
									<option value="01">Enero</option>
									<option value="02">Febrero</option>
									<option value="03">Marzo</option>
									<option value="04">Abril</option>
									<option value="05">Mayo</option>
									<option value="06">Junio</option>
									<option value="07">Julio</option>
									<option value="08">Agosto</option>
									<option value="09">Septiembre</option>
									<option value="10">Octubre</option>
									<option value="11">Noviembre</option>
									<option value="12">Diciembre</option>
								</select></td>
							</tr>
							<tr>
								<td align="right">Tipo de documento:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:radio name="CreateDocumentForm" property="documentType" value="typeOne"/> Propuesta de declaraci&oacute;n&nbsp;&nbsp;&nbsp;&nbsp;<html:radio name="CreateDocumentForm" property="documentType" value="typeTwo"/> Plan de Acción</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3">
									<html:submit property="operation">
										<bean:message key="createDocument.next"/>
									</html:submit>
									<!--  div id="centrador" style="padding-left:120px;"><a class="button" href="pedirnuevaclave_error.html"><span>Siguiente (Introducci&oacute;n)</span></a>
									<a class="button" href="moderatorHome.jsp"><span>Cancelar</span></a></div -->
								</td>
							</tr>
						</table>					
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
		</td>
	</tr>
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
</table>
</div>
		</html:form>
</html:html>
<%@ include file="includes/footer.jsp" %>