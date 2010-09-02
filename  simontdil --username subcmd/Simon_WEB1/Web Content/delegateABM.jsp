<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title></title>
	</head>
	<body>
		<html:form method="POST" action="/delegateABM">
		nombere<html:text name="DelegateABM" property="name" /><br>
		usuario
			<logic:equal name="DelegateABM" property="id" value="0">
				<html:text name="DelegateABM" property="username"/>
			</logic:equal>
			<logic:notEqual name="DelegateABM" property="id" value="0">
				<html:text name="DelegateABM" property="username" disabled="true"/>
			</logic:notEqual>
		<br>
		pais<html:select name="DelegateABM" property="countryId" styleClass="textfield_effect_month">
	        <html:optionsCollection name="DelegateABM" property="allCountries" value="id" label="name"/>
	      </html:select><br>
		email<html:text name="DelegateABM" property="email"/><br>
		A<html:checkbox name="DelegateABM" property="typeOne"/><br>
		B<html:checkbox name="DelegateABM" property="typeTwo"/><br>
		Firma<html:checkbox name="DelegateABM" property="canSign"/><br>
		Cargo<html:text name="DelegateABM" property="job"/><br>
		
		<logic:equal name="DelegateABM" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="delegateABM.create"/>
			</html:submit>
		</logic:equal>
		
		<logic:notEqual name="DelegateABM" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="delegateABM.modify"/>
			</html:submit>
		</logic:notEqual>
		
		<html:submit property="operation">
			<bean:message key="delegateABM.cancel"/>
		</html:submit><br>
		<br>
		<table border="1">
		<tr>
			<td height="28">Nombre</td>
			<td>Pais</td>
			<td>Permisos</td>
			<td>Firmante</td>
			<td>Habilitado</td>
			<td>Editar</td>
		</tr> 
		<logic:iterate name="DelegateABM" property="allUsers" id="user" indexId="iterIndex"> 
			<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
				<td height="28"><bean:write name="user" property="name" /></td>
				<td><bean:write name="user" property="countryName" /></td>
				<td><bean:write name="user" property="permissionsString" /></td>
				<td><logic:equal name="user" property="canSign" value="true">Si</logic:equal>
				<logic:equal name="user" property="canSign" value="false">No</logic:equal></td>
				<td><logic:equal name="user" property="deleted" value="true">No</logic:equal>
				<logic:equal name="user" property="deleted" value="false">Si</logic:equal></td>
				<td><html:link  action="editDelegate.st?" paramName="user" paramProperty="id" paramId="id">
					<img src="images/buttons/editar.png" width="50" height="24" border="0">
				</html:link>
			</tr> 
		</logic:iterate>
		</table>
		</html:form>
	</body>
</html:html>

