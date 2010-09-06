<%@ page info="systemUserABM"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<html:form method="POST" action="/systemUserABM">

Nombre<html:text name="SystemUserABM" property="name" /><br>
Usuario<logic:equal name="SystemUserABM" property="id" value="0">
	<html:text name="SystemUserABM" property="username"/>
</logic:equal>
<logic:notEqual name="SystemUserABM" property="id" value="0">
	<html:text name="SystemUserABM" property="username" disabled="true"/>
</logic:notEqual><br>

Email<html:text name="SystemUserABM" property="email"/><br>
<html:checkbox name="SystemUserABM" property="administrator"/> Administrador<br>
<html:checkbox name="SystemUserABM" property="moderator"/> Moderador<br>
<html:checkbox name="SystemUserABM" property="designer"/> Diseñador<br>

<logic:equal name="SystemUserABM" property="id" value="0">
	<html:submit property="operation">
		<bean:message key="systemUserABM.create"/>
	</html:submit>
</logic:equal>
	
	<logic:notEqual name="SystemUserABM" property="id" value="0">
		<html:submit property="operation">
		<bean:message key="systemUserABM.modify"/>
		</html:submit>
	</logic:notEqual>
	
	<html:submit property="operation">
		<bean:message key="systemUserABM.cancel"/>
	</html:submit><br>
	
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
	</tr>
	<tr>
		<td height="20" align="left">Username</td>
		<td align="left">Nombre</td>
		<td>Permisos</td>
		<td>Habilitado</td>
		<td>Editar</td>
		<td>Borrar/Reactivar</td>
	</tr> 
	<logic:iterate name="SystemUserABM" property="allUsers" id="iterUser" indexId="iterIndex"> 
		<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
			<td height="28" align="left"><bean:write name="iterUser" property="username" /></td>
			<td height="28" align="left"><bean:write name="iterUser" property="name" /></td>
			<td><bean:write name="iterUser" property="systemPermissionsString" /></td>
			<td><logic:equal name="iterUser" property="deleted" value="true">No</logic:equal>
			<logic:equal name="iterUser" property="deleted" value="false">Si</logic:equal></td>
			<td><html:link  action="editSystemUser.st?" paramName="iterUser" paramProperty="id" paramId="id">
				<img src="images/buttons/editar.png" width="50" height="24" border="0">
			</html:link>
			<td><logic:equal name="iterUser" property="deleted" value="false">
					<html:image property="deleteImages" indexed="true" value="id"  src="images/buttons/minus.gif"></html:image>
				</logic:equal>
				<logic:equal name="iterUser" property="deleted" value="true">
					<html:image property="reactivateImages" indexed="true" value="id"  src="images/buttons/plus.gif"></html:image>
				</logic:equal></td>
		</tr> 
	</logic:iterate>
	<tr>
		<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
	</tr>
</table>	
	
</html:form>
<%@ include file="includes/footer.jsp" %>