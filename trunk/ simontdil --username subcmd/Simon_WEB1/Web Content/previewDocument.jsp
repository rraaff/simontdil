<%@ page info="previewDocument"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
	<tr>
		<td>
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="920" height="19" align="left"><div id="blockTitle">Previsualización del documento</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="920" height="340"><!-- corte tabla template -->
						<table width="900" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" align="left"><bean:write name="CreateDocumentForm" property="title"/>
									<bean:write name="CreateDocumentForm" property="versionNumber"/>
									<bean:write name="CreateDocumentForm" property="versionName"/>
									<bean:write name="CreateDocumentForm" property="limitObservationsDay"/>
									<bean:write name="CreateDocumentForm" property="limitObservationsMonth"/></td>
							</tr>
							<tr>
								<td height="290" align="left" valign="top"><div id="previewDocumento">
								<logic:iterate name="CreateDocumentForm" property="previewParagraphs" id="paragraph"> 
								<!--  pre --> 
									<p><%=paragraph%></p>
								<!--  pre -->
								</logic:iterate></div>
								</td>
							</tr>
							<tr>
								<td><html:errors property="general" />
									<html:form method="POST" action="/previewDocument">
										
									<html:submit property="operation">
										<bean:message key="createDocument.preview.editParagraphs"/>
									</html:submit>
									
									<!-- Si no esta en negociacion -->
									<logic:equal name="CreateDocumentForm" property="isInNegotiation" value="false">
										<html:submit property="operation">
											<bean:message key="createDocument.preview.save"/>
										</html:submit>
										<html:submit property="operation">
											<bean:message key="createDocument.preview.consolidate"/>
										</html:submit>
									</logic:equal>
									
									<!-- Si esta en negociacion -->
									<logic:equal name="CreateDocumentForm" property="isInNegotiation" value="true">
										<html:submit property="operation">
											<bean:message key="createDocument.preview.saveAndContinue"/>
										</html:submit>
										<html:submit property="operation">
											<bean:message key="createDocument.preview.saveAndSign"/>
										</html:submit>
									</logic:equal>
									</html:form></td>
							</tr>
							<tr>
								<td height="5"><img src="images/null.gif" width="1" height="5"></td>
							</tr>
						</table>
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
	<tr>
		<td height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
</table>
</div>
<div id="footer">
<%@ include file="includes/footer.jsp" %>