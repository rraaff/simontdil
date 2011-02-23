<%@ page info="createDocumentStep1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<html:html>
<div id="content">
<html:errors property="general" />
<html:form method="POST" action="/createDocumentActionStep2">
<table width="100%" height="590" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="100%" valign="middle">
			<table border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="600" height="19" align="left"><div id="blockTitle">Edición de documentos (Paso 1 de 4)</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="600" height="144"><!-- corte tabla template -->
						<table width="600" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" colspan="3"><p align="left">&nbsp;</p></td>
							</tr>
							<tr>
								<td align="right" width="192">T&iacute;tulo:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="401" align="left">
									<logic:equal name="CreateDocumentForm" property="portugues" value="false">
										<html:text name="CreateDocumentForm" property="title" styleClass="textfield_effect"/><html:errors property="title" />
									</logic:equal>
									<logic:equal name="CreateDocumentForm" property="portugues" value="true">
										<html:text name="CreateDocumentForm" property="title" styleClass="textfield_effect" disabled="true"/>
									</logic:equal>
								</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
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
								<td align="left"><html:text name="CreateDocumentForm" property="versionName" styleClass="textfield_effect"/><html:errors property="versionName" /></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="192" align="right">
									<logic:equal name="CreateDocumentForm" property="principalReadOnly" value="false">
										<html:checkbox name="CreateDocumentForm" property="principal"/><html:errors property="principal" />
									</logic:equal>
									<logic:equal name="CreateDocumentForm" property="principalReadOnly" value="true">
										<html:checkbox name="CreateDocumentForm" property="principal" disabled="true"/>
									</logic:equal>
									</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="301" align="left">Marcar como documento principal a negociar</td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td>asdasdasdasdasdsdssd<br>
								<label>Datepicker with Vista skin:</label>
								<input name='date_B' type='text' value='' class='date demo_vista' />
								</td>
								<script>
								window.addEvent('load', function() {
	new DatePicker('.demo_vista', { pickerClass: 'datepicker_vista' });
});
								</script>
							</tr>
							<tr>
								<td align="right">Fecha l&iacute;mite para observaciones:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left">
								<logic:equal name="CreateDocumentForm" property="dateLimitReadOnly" value="false">
								  <html:select property="limitObservationsDay" styleClass="textfield_effect_day">
							        <html:optionsCollection name="CreateDocumentForm" property="days" value="dayNumber" label="dayNumber"/>
							      </html:select> de <html:select property="limitObservationsMonth" styleClass="textfield_effect_month">
							        <html:optionsCollection name="CreateDocumentForm" property="months" value="monthNumber" label="monthText"/>
							      </html:select> de 2010
							    </logic:equal>
							    <logic:equal name="CreateDocumentForm" property="dateLimitReadOnly" value="true">
								  <html:select property="limitObservationsDay" styleClass="textfield_effect_day" disabled="true">
							        <html:optionsCollection name="CreateDocumentForm" property="days" value="dayNumber" label="dayNumber"/>
							      </html:select> de <html:select property="limitObservationsMonth" styleClass="textfield_effect_month" disabled="true">
							        <html:optionsCollection name="CreateDocumentForm" property="months" value="monthNumber" label="monthText"/>
							      </html:select> de 2010
							    </logic:equal>
							    </td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td align="right" valign="top">Tipo de documento:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td align="left">
								<logic:equal name="CreateDocumentForm" property="typeReadOnly" value="false">
									<html:radio name="CreateDocumentForm" property="documentType" value="typeOne"/><html:errors property="title" /> Documentos para Coordinadores Nacionales&nbsp;&nbsp;&nbsp;&nbsp;<br/><html:radio name="CreateDocumentForm" property="documentType" value="typeTwo"/> Documentos para Responsables de Cooperación
								</logic:equal>
								<logic:equal name="CreateDocumentForm" property="typeReadOnly" value="true">
									<html:radio name="CreateDocumentForm" property="documentType" value="typeOne" disabled="true"/> Documentos para Coordinadores Nacionales&nbsp;&nbsp;&nbsp;&nbsp;<br/><html:radio name="CreateDocumentForm" property="documentType" value="typeTwo" disabled="true"/> Documentos para Responsables de Cooperación
								</logic:equal>
								</td>
							</tr>
							<logic:equal name="CreateDocumentForm" property="portuguesOrDesigner" value="true">
								<tr>
									<td colspan="4">
										<html:textarea name="CreateDocumentForm" property="designerText" styleClass="textfield_effect_area"/><html:errors property="designerText" />
									</td>
								</tr>
							</logic:equal>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<html:submit property="operation">
										<bean:message key="createDocument.next"/>
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
		</td>
	</tr>
</table>
<logic:equal name="CreateDocumentForm" property="portuguesOrDesigner" value="true">
<script type="text/javascript">
		//<![CDATA[
			// Replace the <textarea id="editor1"> with an CKEditor instance.
			var editor = CKEDITOR.replace( 'designerText',
				{
					// Defines a simpler toolbar to be used in this sample.
					// Note that we have added out "MyButton" button here.
					toolbar : [ ['Bold', 'Italic', 'Underline', 'Strike','-'] ,['TextColor','BGColor']],
					height:"120", width:"960"
					
				});
</script>
</logic:equal>
</html:form>
</div>
</html:html>
<%@ include file="includes/footer.jsp" %>