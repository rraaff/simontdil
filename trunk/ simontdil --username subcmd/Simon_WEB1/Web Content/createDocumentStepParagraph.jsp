<%@ page info="createDocumentStepParagraph"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:html>
<div id="content">
<html:form method="POST" action="/paragraphNavigation">
	<div id="alcien" style="height:130px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Datos del documento</div></td>
				<td width="10" height="19" colspan="2" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
			</tr>
			<tr>
				<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				<td width="9"><img src="images/null.gif" width="9" height="1"></td>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="5"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
						<tr>
							<td width="53" align="right">Titulo:</td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td width="210" align="left"><html:text name="CreateDocumentForm" property="title" disabled="true" styleClass="textfield_effect"/></td>
							<td width="20"><img src="images/null.gif" width="20" height="1"></td>
							<td width="205" align="right">Versi&oacute;n: <bean:write name="CreateDocumentForm" property="versionNumber"/></td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td colspan="3" align="right">Nombre de la versi&oacute;n:</td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td><html:text name="CreateDocumentForm" property="versionName" disabled="true" styleClass="textfield_effect"/></td>
						</tr>
						<tr>
							<td colspan="11" height="5"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
						<tr>
							<td align="right"><html:checkbox name="CreateDocumentForm" property="principal" disabled="true"/></td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td align="left">Documento principal a negociar</td>
							<td width="20"><img src="images/null.gif" width="20" height="1"></td>
							<td align="right">L&iacute;mite para obs.:</td>
							<td><img src="images/null.gif" width="7" height="1"></td>
							<td width="359" align="left"><html:select property="limitObservationsDay" styleClass="textfield_effect_day" disabled="true">
									<html:optionsCollection name="CreateDocumentForm" property="days" value="dayNumber" label="dayNumber"/>
								</html:select>
								de
								<html:select property="limitObservationsMonth" styleClass="textfield_effect_month" disabled="true">
									<html:optionsCollection name="CreateDocumentForm" property="months" value="monthNumber" label="monthText"/>
								</html:select></td>
							<td width="20"><img src="images/null.gif" width="20" height="1"></td>
							<td width="160" align="right">Tipo de doc.:</td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td width="290" align="left"><html:radio name="CreateDocumentForm" property="documentType" value="typeOne" disabled="true"/>Propuesta de dec.&nbsp;&nbsp;<html:radio name="CreateDocumentForm" property="documentType" value="typeTwo" disabled="true"/>Plan de Acción</td>
						</tr>
						<tr>
							<td colspan="11" height="11"><img src="images/null.gif" width="1" height="11"></td>
						</tr>
						<!-- tr>
							<td colspan="11" align="center"><logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="true">
									<html:submit property="operation">
										<bean:message key="createDocument.paragraphs.modifyDocument"/>
									</html:submit>
								</logic:equal>
									<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="false">
										<html:submit property="operation">
											<bean:message key="createDocument.paragraphs.modifyIntroduction"/>
										</html:submit>
									</logic:equal></td>
						</tr -->
						<tr>
							<td colspan="11" height="5"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
					</table>
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
	</div>
	<div id="alcien" style="height:410px;">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif">
					<div id="blockTitle">
					<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="true">
						Pre&aacute;mbulo (Paso 2 de 4)
					</logic:equal>
					<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="false">
						P&aacute;rrafos (Paso 3 de 4)
					</logic:equal>
					</div>
				</td>
				<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
			</tr>
			<tr>
				<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				<td width="9"><img src="images/null.gif" width="9" height="1"></td>
				<td valign="top"><!-- corte tabla template -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right">Párrafo:</td>
							<td width="10"><img src="images/null.gif" width="7" height="1"></td>
							<td align="left" id="parDisplay"><bean:write name="CreateDocumentForm" property="paragraphForDisplay" /></td>
							<td align="left">Detalle:</td>
							<td align="left" id="parDetail"><html:text name="CreateDocumentForm" property="currentDetail" styleClass="textfield_effect"/></td>
							<td align="right">Cambiar al párrafo:</td>
							<td width="10"><img src="images/null.gif" width="7" height="1"></td>
							<td align="right">
								<html:select name="CreateDocumentForm" property="goToParagraph">
									<html:optionsCollection name="CreateDocumentForm" property="allParagraphNumbers" value="paragraphNumber" label="paragraphNumberForDisplay"/>
								</html:select></td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td align="left"><html:image property="jumpTo" value="jumpTo"  src="images/buttons/ir.png"></html:image></td>
						</tr>
						<tr>
							<td colspan="9" height="2"><img src="images/null.gif" width="1" height="2"></td>
						</tr>
						<tr>
							<td colspan="9" align="center"><html:textarea name="CreateDocumentForm" property="paragraphText" styleClass="textfield_effect_area"/><html:errors property="paragraphText" /></td>
						</tr>
						<tr>
							<td colspan="9" height="2"><img src="images/null.gif" width="1" height="2"></td>
						</tr>
						<tr>
							<td colspan="9" align="center">
							<!-- Boton prev -->
							<logic:notEqual name="CreateDocumentForm" property="backDisabled" value="true">
								<html:submit property="operation">
								<bean:message key="createDocument.paragraphs.back" />
								</html:submit>
							</logic:notEqual>
							<logic:equal name="CreateDocumentForm" property="backDisabled" value="true">
								<span id="backButtonLayer">
								<html:submit property="operation" disabled="true">
								<bean:message key="createDocument.paragraphs.back" />
								</html:submit>
								</span>
							</logic:equal>
							
							<!-- Boton Next -->
							<logic:notEqual name="CreateDocumentForm" property="last" value="true">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.next"/>
								</html:submit>
							</logic:notEqual>
							<logic:equal name="CreateDocumentForm" property="last" value="true">
								<span id="nextButtonLayer">
								<html:submit property="operation" disabled="true">
									<bean:message key="createDocument.paragraphs.next"/>
								</html:submit>
								</span>
							</logic:equal>
							
							<!-- Boton Add before -->
							<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="true">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.addBefore"/>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="false">
								<html:submit property="operation" disabled="true">
									<bean:message key="createDocument.paragraphs.addBefore"/>
								</html:submit>
							</logic:equal>
							
							<logic:equal name="CreateDocumentForm" property="last" value="true">
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="true">
									<html:submit property="operation">
										<bean:message key="createDocument.paragraphs.add"/>
									</html:submit>
								</logic:equal>
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="false">
									<html:submit property="operation" disabled="true">
										<bean:message key="createDocument.paragraphs.add"/>
									</html:submit>
								</logic:equal>
							</logic:equal>
							
							<logic:notEqual name="CreateDocumentForm" property="last" value="true">
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="true">
									<html:submit property="operation">
										<bean:message key="createDocument.paragraphs.addAfter"/>
									</html:submit>
								</logic:equal>
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="false">
									<html:submit property="operation" disabled="true">
										<bean:message key="createDocument.paragraphs.addAfter"/>
									</html:submit>
								</logic:equal>
							</logic:notEqual>
							
							<!-- Boton delete -->
							<logic:equal name="CreateDocumentForm" property="canDeleteParagraph" value="true">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.delete"/>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="canDeleteParagraph" value="false">
								<html:submit property="operation" disabled="true">
									<bean:message key="createDocument.paragraphs.delete"/>
								</html:submit>
							</logic:equal>
							
							</td>
						</tr>
						<tr>
							<td colspan="9" height="5"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
						<tr>
							<td colspan="9" height="25" align="center">
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="true">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.modifyDocument"/>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="false">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.modifyIntroduction"/>
								</html:submit>
							</logic:equal>
							<%if (isModerator) { %>
							<logic:equal name="CreateDocumentForm" property="versionNegotiated" value="true">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.pushData"/>
								</html:submit>
							</logic:equal> 
							<% } %>
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="true">
								<html:submit property="operation">
									<bean:message key="createDocument.addParagraphs"/>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="false">
								<html:submit property="operation">
									<bean:message key="createDocument.paragraphs.preview"/>
								</html:submit>
							</logic:equal>
							</td>
						</tr>
					</table>
					<!-- corte tabla template --></td>
				<td width="9"><img src="images/null.gif" width="9" height="1"></td>
				<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
			</tr>
			<tr>
				<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
				<td height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
				<td colspan="2" width="10" height="10" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
			</tr>
		</table>
	</div>
<script type="text/javascript">
		//<![CDATA[
			var moveToParagraphUrl = '<html:rewrite page="/moveToParagraph.st"/>';
			// Replace the <textarea id="editor1"> with an CKEditor instance.
			var editor = CKEDITOR.replace( 'paragraphText',
				{
					// Defines a simpler toolbar to be used in this sample.
					// Note that we have added out "MyButton" button here.
					toolbar : [ ['Bold', 'Italic', 'Underline', 'Strike','-'] ,['TextColor','BGColor'],['simonflags','moveto']],
					height:"220", width:"960"
					
				});
</script>
</html:form>
</div>
</html:html>
<%@ include file="includes/footer.jsp" %>