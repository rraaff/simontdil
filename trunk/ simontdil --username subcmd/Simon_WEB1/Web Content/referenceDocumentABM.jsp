<%@ page info="referenceDocumentABM"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:form method="POST" action="/referenceDocumentABM" enctype="multipart/form-data">
 		
 		Nombre <html:text name="ReferenceDocumentABMForm" property="title"/><br/>
 		Select File: <html:file property="document"/> <br/>
 		<html:select name="ReferenceDocumentABMForm" property="categoryId" styleClass="textfield_effect_month">
			<html:optionsCollection name="ReferenceDocumentABMForm" property="allCategories" value="id" label="name"/>
		</html:select><br/>
 		
		<logic:equal name="ReferenceDocumentABMForm" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="referenceDocumentABM.create"/>
			</html:submit>
		</logic:equal><br/>
			
		<logic:notEqual name="ReferenceDocumentABMForm" property="id" value="0">
			<html:submit property="operation">
			<bean:message key="referenceDocumentABM.modify"/>
			</html:submit>
		</logic:notEqual><br/>
		
		<html:submit property="operation">
			<bean:message key="referenceDocumentABM.cancel"/>
		</html:submit><br/>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" height="11"><img src="images/null.gif" width=<"1" height="11"></td>
	</tr>
	<tr>
		<td height="20" align="left">Titulo</td>
		<td align="left">Archivo</td>
		<td align="left">Categoria</td>
		<td>Editar</td>
		<td>Borrar</td>
	</tr> 
	<logic:iterate name="ReferenceDocumentABMForm" property="allReferenceDocuments" id="iterRefDoc" indexId="iterIndex"> 
		<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
			<td><bean:write name="iterRefDoc" property="title" /></td>
			<td><bean:write name="iterRefDoc" property="fileName" /></td>
			<td><bean:write name="iterRefDoc" property="categoryName" /></td>
			<td><html:link  action="editReferenceDocument.st?" paramName="iterRefDoc" paramProperty="id" paramId="id">
				<img src="images/buttons/editar.png" width="50" height="24" border="0">
			</html:link>
			<td> - </td>
		</tr> 
	</logic:iterate>
</table>
</html:form>
<%@ include file="includes/footer.jsp" %>