<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:html>
<html:form method="POST" action="/createDocumentActionParagraph">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
	<tr>
		<td width="470">
			<!-- inicio tabla template -->
			<table width="450" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="450" height="19" align="left"><div id="blockTitle">Datos del documento documentos</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="450" height="144"><!-- corte tabla template -->
						<table width="450" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" colspan="3"><img src="images/null.gif" width="1" height="25"></td>
							</tr>
							<tr>
								<td align="right" width="129">Titulo:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="264" align="left"><html:text name="CreateDocumentForm" property="title" disabled="true" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right" width="129" height="24">Versi&oacute;n:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="264" align="left"><bean:write name="CreateDocumentForm" property="versionNumber"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">Nombre de la versi&oacute;n:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:text name="CreateDocumentForm" property="versionName" disabled="true" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><html:errors property="versionName" /><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="192" align="right"><html:checkbox name="CreateDocumentForm" property="principal" disabled="true"/></td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="301" align="left">Marcar como documento principal a negociar</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">L&iacute;mite para observaciones:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:select property="limitObservationsDay" styleClass="textfield_effect_day" disabled="true">
							        <html:optionsCollection name="CreateDocumentForm" property="days" value="dayNumber" label="dayNumber"/>
							      </html:select> de <html:select property="limitObservationsMonth" styleClass="textfield_effect_month" disabled="true">
							        <html:optionsCollection name="CreateDocumentForm" property="months" value="monthNumber" label="monthText"/>
							      </html:select></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">Tipo de documento:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:radio name="CreateDocumentForm" property="documentType" value="typeOne" disabled="true"/> Propuesta de declaraci&oacute;n&nbsp;&nbsp;&nbsp;&nbsp;<html:radio name="CreateDocumentForm" property="documentType" value="typeTwo" disabled="true"/> Plan de Acci�n</td>
							</tr>
							<tr>
								<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
							</tr>
							<tr>
								<td colspan="3"><!--<a class="button" href="pedirnuevaclave_error.html"><span>Anterior (Datos del Documento)</span></a> -->
									<html:submit property="operation">
										<bean:message key="createDocument.back"/>
									</html:submit></td>
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
		<!-- fin tabla template -->
		</td>
		<td width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="440">
			<!-- inicio tabla template -->
			<table width="440" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="420" height="19" align="left"><div id="blockTitle">Introducci&oacute;n (Paso 2 de 4)</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="420" height="144"><!-- corte tabla template -->
						<table width="420" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3" height="27"><html:errors property="general" /><img src="images/null.gif" width="1" height="27"></td>
							</tr>
							<tr>
								<td width="74" align="right" valign="top">Texto:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="339" align="left">
								<!--  textarea cols="" rows="" class="textfield_effect_area"></textarea -->
									<html:textarea name="CreateDocumentForm" property="introduction" styleClass="textfield_effect_area"/></td>
							</tr>
							<tr>
								<td colspan="3" height="40"><img src="images/null.gif" width="1" height="40"></td>
							</tr>
							<tr>
								<td colspan="3">
									<html:submit property="operation">
										<bean:message key="createDocument.addParagraphs"/>
									</html:submit>
								<!--  div id="centrador" style="padding-left:110px;"><a class="button" href="pedirnuevaclave_error.html"><span>Siguiente (Agregar p&aacute;rrafos)</span></a></div --></td>
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
		<!-- fin tabla template -->
		</td>
	</tr>
	<tr>
		<td colspan="3" height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
</table>
</div>
		</html:form>
</html:html>
<%@ include file="includes/footer.jsp" %>