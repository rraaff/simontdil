<%@ page info="createDocumentStepParagraph"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:html>
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
<html:form method="POST" action="/paragraphNavigation">
<input type="hidden" name="indexOperation" value=""/>
<input type="hidden" name="indexClicked"/>
	<div id="alcien" style="height:130px;">
		<table border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get("createDocument", "titulo")%></div></td>
				<td width="10" height="19" colspan="2" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
			</tr>
			<tr>
				<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				<td width="9"><img src="images/null.gif" width="9" height="1"></td>
				<td>
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="11"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
						<tr>
							<td width="53" align="right"><%=ResourceBundleCache.get("createDocument", "tituloDocumento")%>:</td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td width="210" align="left"><html:text name="CreateDocumentForm" property="title" disabled="true" styleClass="textfield_effect"/></td>
							<td width="20"><img src="images/null.gif" width="20" height="1"></td>
							<td width="205" align="right"><%=ResourceBundleCache.get("createDocument", "version")%>: <bean:write name="CreateDocumentForm" property="versionNumber"/></td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td colspan="3" align="right"><%=ResourceBundleCache.get("createDocument", "nombreVersion")%>:</td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td><html:text name="CreateDocumentForm" property="versionName" disabled="true" styleClass="textfield_effect"/></td>
						</tr>
						<tr>
							<td colspan="11" height="5"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
						<tr>
							<td align="right"><html:checkbox name="CreateDocumentForm" property="principal" disabled="true"/></td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td align="left"><%=ResourceBundleCache.get("createDocument", "documentoPrincipal")%></td>
							<td width="20"><img src="images/null.gif" width="20" height="1"></td>
							<td align="right"><%=ResourceBundleCache.get("createDocument", "limiteObservaciones")%>:</td>
							<td><img src="images/null.gif" width="7" height="1"></td>
							<td width="359" align="left">
								<html:text name="CreateDocumentForm" property="limitObservations" styleClass='date demo_vista' disabled="true"/>
								<script>
									window.addEvent('load', function() {
										new DatePicker('.demo_vista', { pickerClass: 'datepicker_vista' });
									});
								</script>
							</td>
							<td width="20"><img src="images/null.gif" width="20" height="1"></td>
							<td width="160" align="right"><%=ResourceBundleCache.get("createDocument", "tipoDocumento")%>:</td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td width="290" align="left">
								<html:select name="CreateDocumentForm" property="documentSubTypeId" styleClass="textfield_effect" disabled="true">
									<logic:iterate name="CreateDocumentForm" property="allDocumentSubType" id="iterSubCat"> 
											<% com.tdil.simon.data.valueobjects.DocumentTypeVO sub = (com.tdil.simon.data.valueobjects.DocumentTypeVO)iterSubCat; %>
											<% if (sub.getParentId() == 0) { %>
												<option <%=sub.getSelected() ? "selected" : "" %> value="<%=sub.getId()%>"><%=sub.getName()%></option>
											<% } else { %>
												<option <%=sub.getSelected() ? "selected" : "" %> value="<%=sub.getId()%>">&nbsp;&nbsp;&nbsp;<%=sub.getName()%></option>
											<% } %>
										</logic:iterate>
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="11" height="11"><img src="images/null.gif" width="1" height="11"></td>
						</tr>
						
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
		<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				<td height="19" align="left" background="images/interfaces/topTitle.gif">
					<div id="blockTitle">
					<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="true">
						<%=ResourceBundleCache.get("createDocument", "paso2")%>
					</logic:equal>
					<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="false">
						<%=ResourceBundleCache.get("createDocument", "paso3")%>
					</logic:equal>
					</div>
				</td>
				<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
			</tr>
			<tr>
				<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				<td width="9"><img src="images/null.gif" width="9" height="1"></td>
				<td valign="top" align="center"><!-- corte tabla template -->
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right"><%=ResourceBundleCache.get("createDocument", "parrafo")%>:</td>
							<td width="10"><img src="images/null.gif" width="7" height="1"></td>
							<td align="left" id="parDisplay"><bean:write name="CreateDocumentForm" property="paragraphForDisplay" /></td>
							<td align="right"><%=ResourceBundleCache.get("createDocument", "etiqueta")%>: </td>
							<td align="left" id="parDetail"><html:text name="CreateDocumentForm" property="currentDetail" styleClass="textfield_effect"/></td>
							<td align="right"><%=ResourceBundleCache.get("createDocument", "cambiarElParrafo")%>:</td>
							<td width="10"><img src="images/null.gif" width="7" height="1"></td>
							<td align="right">
								<html:select name="CreateDocumentForm" property="goToParagraph">
									<html:optionsCollection name="CreateDocumentForm" property="allParagraphNumbers" value="paragraphNumber" label="paragraphNumberForDisplay"/>
								</html:select></td>
							<td width="7"><img src="images/null.gif" width="7" height="1"></td>
							<td align="left">
								<%=com.tdil.simon.web.ButtonGenerator.getIndexedButtonByKey("CreateDocumentForm","botones","ir", 0)%>
							</td>
							<td align="left">
								<logic:equal name="CreateDocumentForm" property="versionNegotiated" value="true">
									<html:checkbox name="CreateDocumentForm" property="livePreview"><%=ResourceBundleCache.get("createDocument", "actualizacionEnVivo")%></html:checkbox>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td colspan="11" height="2"><img src="images/null.gif" width="1" height="2"></td>
						</tr>
						<tr>
							<td colspan="11" align="center"><html:textarea name="CreateDocumentForm" property="paragraphText" styleClass="textfield_effect_area"/><html:errors property="paragraphText" /></td>
						</tr>
						<tr>
							<td colspan="11" height="2"><img src="images/null.gif" width="1" height="2"></td>
						</tr>
						<tr>
							<td colspan="11" align="center">
							<!-- Boton prev -->
							<logic:notEqual name="CreateDocumentForm" property="backDisabled" value="true">
								<html:submit property="operation">
								<%=ResourceBundleCache.get(getServletInfo(), "anterior")%>
								</html:submit>
							</logic:notEqual>
							<logic:equal name="CreateDocumentForm" property="backDisabled" value="true">
								<span id="backButtonLayer">
								<html:submit property="operation" disabled="true">
								<%=ResourceBundleCache.get(getServletInfo(), "anterior")%>
								</html:submit>
								</span>
							</logic:equal>
							
							<!-- Boton Next -->
							<logic:notEqual name="CreateDocumentForm" property="last" value="true">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "siguiente")%>
								</html:submit>
							</logic:notEqual>
							<logic:equal name="CreateDocumentForm" property="last" value="true">
								<span id="nextButtonLayer">
								<html:submit property="operation" disabled="true">
									<%=ResourceBundleCache.get(getServletInfo(), "siguiente")%>
								</html:submit>
								</span>
							</logic:equal>
							
							<!-- Boton Add before -->
							<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="true">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "agregarAntes")%>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="false">
								<html:submit property="operation" disabled="true">
									<%=ResourceBundleCache.get(getServletInfo(), "agregarAntes")%>
								</html:submit>
							</logic:equal>
							
							<logic:equal name="CreateDocumentForm" property="last" value="true">
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="true">
									<html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "agregar")%>
									</html:submit>
								</logic:equal>
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="false">
									<html:submit property="operation" disabled="true">
										<%=ResourceBundleCache.get(getServletInfo(), "agregar")%>
									</html:submit>
								</logic:equal>
							</logic:equal>
							
							<logic:notEqual name="CreateDocumentForm" property="last" value="true">
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="true">
									<html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "agregarDespues")%>
									</html:submit>
								</logic:equal>
								<logic:equal name="CreateDocumentForm" property="canAddParagraph" value="false">
									<html:submit property="operation" disabled="true">
										<%=ResourceBundleCache.get(getServletInfo(), "agregarDespues")%>
									</html:submit>
								</logic:equal>
							</logic:notEqual>
							
							<!-- Boton delete -->
							<logic:equal name="CreateDocumentForm" property="canDeleteParagraph" value="true">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "borrar")%>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="canDeleteParagraph" value="false">
								<html:submit property="operation" disabled="true">
									<%=ResourceBundleCache.get(getServletInfo(), "borrar")%>
								</html:submit>
							</logic:equal>
							
							</td>
						</tr>
						<tr>
							<td colspan="11" height="5"><img src="images/null.gif" width="1" height="5"></td>
						</tr>
						<tr>
							<td colspan="11" height="25" align="center">
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="true">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "modificarDocumento")%>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="false">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "modificarIntroduccion")%>
								</html:submit>
							</logic:equal>
							<%if (isModerator) { %>
							<logic:equal name="CreateDocumentForm" property="versionNegotiated" value="true">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "actualizarContenido")%>
								</html:submit>
							</logic:equal> 
							<% } %>
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="true">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "agregarParrafos")%>
								</html:submit>
							</logic:equal>
							<logic:equal name="CreateDocumentForm" property="introductoryParagraph" value="false">
								<html:submit property="operation">
									<%=ResourceBundleCache.get(getServletInfo(), "previsualizar")%>
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
					height:"220", width:"890"
					
				});
				
		<logic:equal name="CreateDocumentForm" property="versionNegotiated" value="true">
			var lastPush = "";
			var parId = "<bean:write name="CreateDocumentForm" property="currentParagraphId"/>";
			var liveP = document.getElementsByName('livePreview')[0];
			function pushData() {
				var mustPush = liveP.checked;
				if (mustPush) {
					var current = editor.getData();
					if (lastPush != current) {
						var jsonRequest = new Request.JSON({url: '<html:rewrite page="/livePreviewParagraph.st"/>', onSuccess: function(json, responseText){
							// nothing to do
						}
						}).post({'pText':current, 'parId' : parId});
						lastPush = current;
					}
				}
			}
			var timer = setInterval("pushData()",<%=com.tdil.simon.web.SystemConfig.getClientParagrahRefreshTime()%>);
		</logic:equal>
</script>
</html:form>
</html:html>
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>