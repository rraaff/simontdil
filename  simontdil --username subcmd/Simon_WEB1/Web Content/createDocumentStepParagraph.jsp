<%@ page info="createDocumentStepParagraph"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:html>
<html:form method="POST" action="/paragraphNavigation">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
	<tr>
		<td width="470">
			<!-- inicio tabla template -->
			<table width="450" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="450" height="19" align="left"><div id="blockTitle">Datos del documento</div></td>
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
								<td align="left"><html:radio name="CreateDocumentForm" property="documentType" value="typeOne" disabled="true"/> Propuesta de dec.&nbsp;&nbsp;&nbsp;&nbsp;<html:radio name="CreateDocumentForm" property="documentType" value="typeTwo" disabled="true"/> Plan de Acción</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right">Introducción:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left"><html:textarea name="CreateDocumentForm" property="limitedIntro" disabled="true" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="20"><img src="images/null.gif" width="1" height="20"></td>
							</tr>
							<tr>
								<td colspan="3">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.modifyIntroduction"/>
								</html:submit>
								<!-- a class="button" href="#"><span>Anterior (Editar Introducción)</span></a --></td>
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
					<td background="images/interfaces/topTitle.gif" width="420" height="19" align="left"><div id="blockTitle">P&aacute;rrafos (Paso 3 de 4)</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="420" height="144"><!-- corte tabla template -->
						<table width="420" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="7" height="11"><html:errors property="general" /><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="70" align="right" valign="top">Párrafo:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="50" align="left"><bean:write name="CreateDocumentForm" property="paragraphForDisplay" /></td>
								<td width="20"><img src="images/null.gif" width="7" height="1"></td>
								<td width="70" align="right">Oculto:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="196" align="left"><bean:write name="CreateDocumentForm" property="paragraphHidden" /></td>
							</tr>
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="70" align="right" valign="top">Párrafo:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td colspan="5" width="343" align="left"><html:textarea name="CreateDocumentForm" property="paragraphText" styleClass="textfield_effect_area"/><html:errors property="paragraphText" /></td>
							</tr>
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="70"><img src="images/null.gif" width="70" height="1"></td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td colspan="5" align="left">
								<!-- Boton prev -->
								<logic:notEqual name="CreateDocumentForm" property="backDisabled" value="true">
									<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.back" />
									</html:submit>
								</logic:notEqual>
								<logic:equal name="CreateDocumentForm" property="backDisabled" value="true">
									<html:submit property="operation" disabled="true">
									<bean:message key="createDocument.paragraphs.back" />
									</html:submit>
								</logic:equal>
								
								<!-- Boton Next -->
								<logic:notEqual name="CreateDocumentForm" property="nextDisabled" value="true">
									<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.next"/>
								</html:submit>
								</logic:notEqual>
								<logic:equal name="CreateDocumentForm" property="nextDisabled" value="true">
									<html:submit property="operation" disabled="true">
									<bean:message key="createDocument.paragraphs.next"/>
								</html:submit>
								</logic:equal>
								
								<logic:equal name="CreateDocumentForm" property="last" value="true">
									<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.add"/>
									</html:submit>
								</logic:equal>
								<logic:notEqual name="CreateDocumentForm" property="last" value="true">
									<html:submit property="operation" disabled="true">
									<bean:message key="createDocument.paragraphs.add"/>
									</html:submit>
								</logic:notEqual>
								
								<html:submit property="operation">
									<bean:write name="CreateDocumentForm" property="hideOrUnhide" />
								</html:submit>
								</td>
							</tr>
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="7" height="25">
								<%if (isModerator) { %>
								<logic:equal name="CreateDocumentForm" property="versionNegotiated" value="true">
									<html:submit property="operation">
										<bean:message key="createDocument.paragraphs.pushData"/>
									</html:submit>
								</logic:equal>
								<% } %></td>
							</tr>
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="7" height="25">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.preview"/>
								</html:submit>								
								</td>
							</tr>
							<tr>
								<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
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
		<td colspan="3" height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
</table>
</div>
<script type="text/javascript">
		//<![CDATA[
			// Replace the <textarea id="editor1"> with an CKEditor instance.
			var editor = CKEDITOR.replace( 'paragraphText',
				{
					// Defines a simpler toolbar to be used in this sample.
					// Note that we have added out "MyButton" button here.
					toolbar : [ ['Bold', 'Italic', 'Underline', 'Strike','-'] ,['TextColor','BGColor']],
					height:"140", width:"380"
					
				});
</script>
</html:form>
</html:html>
<%@ include file="includes/footer.jsp" %>