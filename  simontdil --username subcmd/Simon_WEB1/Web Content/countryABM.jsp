<%@ page info="countryABM"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:form method="POST" action="/countryABM" enctype="multipart/form-data">
 		
 		Nombre <html:text name="CountryABMForm" property="name"/><br/>
 		Select File: <html:file property="flag"/> <br/>
 		
		<logic:equal name="CountryABMForm" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="countryABM.create"/>
			</html:submit>
		</logic:equal><br/>
			
		<logic:notEqual name="CountryABMForm" property="id" value="0">
			<html:submit property="operation">
			<bean:message key="countryABM.modify"/>
			</html:submit>
		</logic:notEqual><br/>
		
		<html:submit property="operation">
			<bean:message key="countryABM.cancel"/>
		</html:submit><br/>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
	</tr>
	<tr>
		<td height="20" align="left">Nombre</td>
		<td align="left">Usuarios</td>
		<td>Editar</td>
		<td>Borrar</td>
	</tr> 
	<logic:iterate name="CountryABMForm" property="allCountries" id="iterCountry" indexId="iterIndex"> 
		<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
			<td><bean:write name="iterCountry" property="name" /></td>
			<td><bean:write name="iterCountry" property="userCount" /></td>
			<td><html:link  action="editCountry.st?" paramName="iterCountry" paramProperty="id" paramId="id">
				<img src="images/buttons/editar.png" width="50" height="24" border="0">
			</html:link>
			<td> - </td>
		</tr> 
	</logic:iterate>
</table>
</html:form>
<%@ include file="includes/footer.jsp" %>