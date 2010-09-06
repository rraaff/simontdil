<%@ page info="referenceDocumentLibrary"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" height="11"><img src="images/null.gif" width=<"1" height="11"></td>
	</tr>
	<tr>
		<td height="20" align="left">Categoria</td>
		<td align="left">Titulo</td>
		<td align="left">Archivo</td>
		<td width="30">Bajar</td>
	</tr> 
	<logic:iterate name="ReferenceDocumentList" property="list" id="iterRefDoc" indexId="iterIndex"> 
		<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
			<td><bean:write name="iterRefDoc" property="categoryName" /></td>
			<td><bean:write name="iterRefDoc" property="title" /></td>
			<td><bean:write name="iterRefDoc" property="fileName" /></td>
			<td><a href="./download.do?action=refdoc&fileId=<bean:write name="iterRefDoc" property="id" />">Bajar</a></td>
		</tr> 
	</logic:iterate>
</table>
<%@ include file="includes/footer.jsp" %>