<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<table border="0" cellspacing="0" cellpadding="0" onclick="doOperationSubmit('<%= request.getParameter("_form")%>','<%=request.getParameter("_context") +"-" + request.getParameter("_key")%>')">
	<tr>
		<td width="13" height="24"><a href="javascript:doOperationSubmit('<%= request.getParameter("_form")%>','<%=request.getParameter("_context") +"-" + request.getParameter("_key")%>')"><img src="images/buttons/buttonLeft.gif" border="0" width="13" height="24" border="0"></a></td>
		<td background="images/buttons/buttonCenter.gif" align="center" valign="middle"><a href="javascript:doOperationSubmit('<%= request.getParameter("_form")%>','<%=request.getParameter("_context") +"-" + request.getParameter("_key")%>')" class="newButton"><%=ResourceBundleCache.get(request.getParameter("_context"), request.getParameter("_key"))%></a></td>
		<td width="13" height="24"><a href="javascript:doOperationSubmit('<%= request.getParameter("_form")%>','<%=request.getParameter("_context") +"-" + request.getParameter("_key")%>')"><img src="images/buttons/buttonRight.gif" width="13" height="24" border="0"></a></td>
	</tr>
</table>