<%@ page info="referenceDocumentABM"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<style>
<!--
#portaTabla{
	height:260px;
}
-->
</style>

<html:form method="POST" action="/referenceDocumentABM" enctype="multipart/form-data">
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="370">
			<!-- inicio tabla template -->
			<table width="350" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="350" height="19" align="left"><div id="blockTitle">Editar categorías de librería</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="350" height="260"><!-- corte tabla template -->
						<table width="350" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" colspan="3"><img src="images/null.gif" width="1" height="25"></td>
							</tr>
							<tr>
								<td width="103" align="right">Nombre:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="240" align="left"><html:text name="ReferenceDocumentABMForm" property="title" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="103" align="right">Elegir archivo:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="240" align="left"><html:file property="document" styleClass="textfield_effect"/></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="103" align="right">Categoría:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="240" align="left">
									<html:select name="ReferenceDocumentABMForm" property="categoryId" styleClass="textfield_effect">
										<html:optionsCollection name="ReferenceDocumentABMForm" property="allCategories" value="id" label="name"/>
									</html:select></td>
							</tr>
							<tr>
								<td colspan="3" height="30"><img src="images/null.gif" width="1" height="30"></td>
							</tr>
							<tr>
								<td colspan="3" height="25" align="center">
									<logic:equal name="ReferenceDocumentABMForm" property="id" value="0">
										<html:submit property="operation">
											<bean:message key="referenceDocumentABM.create"/>
										</html:submit>
									</logic:equal>
										
									<logic:notEqual name="ReferenceDocumentABMForm" property="id" value="0">
										<html:submit property="operation">
										<bean:message key="referenceDocumentABM.modify"/>
										</html:submit>
									</logic:notEqual>
									
									<html:submit property="operation">
										<bean:message key="referenceDocumentABM.cancel"/>
									</html:submit>
								</td>
							</tr>
							<tr>
								<td colspan="3" height="16"><img src="images/null.gif" width="1" height="16"></td>
							</tr>
						</table>					
						<!-- corte tabla template -->
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
		<td width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="550">
			<!-- inicio tabla template -->
			<table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="530" height="19" align="left"><div id="blockTitle">Listado archivos de librer&iacute;a de referencia</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="530">
					<!-- corte tabla template -->
						<div id="portaTabla">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
								</tr>
								<tr>
									<td height="20" align="left">T&iacute;tulo</td>
									<td align="left">Archivo</td>
									<td align="left">Categor&iacute;a</td>
									<td width="60">Editar</td>
									<td width="60">Borrar</td>
								</tr> 
								<logic:iterate name="ReferenceDocumentABMForm" property="allReferenceDocuments" id="iterRefDoc" indexId="iterIndex"> 
									<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
										<td height="28" align="left"><bean:write name="iterRefDoc" property="title" /></td>
										<td align="left"><bean:write name="iterRefDoc" property="fileName" /></td>
										<td align="left"><bean:write name="iterRefDoc" property="categoryName" /></td>
										<td><html:link  action="editReferenceDocument.st?" paramName="iterRefDoc" paramProperty="id" paramId="id">
											<img src="images/buttons/editar.png" width="50" height="24" border="0">
										</html:link>
										<td><logic:equal name="iterRefDoc" property="deleted" value="false">
											<html:image property="deleteImages" indexed="true" value="id"  src="images/buttons/desactivar.png"></html:image>
										</logic:equal>
										<logic:equal name="iterRefDoc" property="deleted" value="true">
											<html:image property="reactivateImages" indexed="true" value="id"  src="images/buttons/activar.png"></html:image>
										</logic:equal></td>
									</tr> 
								</logic:iterate>
								<tr>
									<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
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
</html:form>
<%@ include file="includes/footer.jsp" %>