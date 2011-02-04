<%@ page import="com.tdil.simon.struts.ApplicationResources"%>
<table border="0" cellspacing="0" cellpadding="0" onclick="doOperationSubmit('<%= request.getParameter("_form")%>','<%=ApplicationResources.getMessage(request.getParameter("_operation"))%>')">
	<tr>
		<td width="13" height="24"><img src="images/buttons/buttonLeft.gif" width="13" height="24" border="0"></td>
		<td background="images/buttons/buttonCenter.gif" align="center" valign="middle"><a href="#" class="newButton"><%=ApplicationResources.getMessage(request.getParameter("_operation"))%></a></td>
		<td width="13" height="24"><img src="images/buttons/buttonRight.gif" width="13" height="24" border="0"></td>
	</tr>
</table>