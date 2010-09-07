<%@ page info="referenceDocumentLibrary"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%if (isModerator) { %>
	<%@ include file="includes/menu.jsp" %>
<% } %>
<style>
<!--
#portaTabla{
	width:900px;
}
-->
</style>
<div id="content">
<table height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
	<tr>
		<td width="940">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td background="images/interfaces/topTitle.gif" width="920" height="19" align="left"><div id="blockTitle">Usuarios</div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="900" height="144">
					<!-- corte tabla template -->
						<div id="portaTabla">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td colspan="7" height="11"><img src="images/null.gif" width="1" height="11"></td>
								</tr>
								<tr>
									<td height="20" align="left">Título</td>
									<td align="left">Categoría</td>
									<td align="left">Archivo</td>
									<td width="80"><img src="images/null.gif" width="1" height="1"></td>
								</tr> 
								<logic:iterate name="ReferenceDocumentList" property="list" id="iterRefDoc" indexId="iterIndex"> 
									<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
										<td height="28" align="left"><bean:write name="iterRefDoc" property="title" /></td>
										<td align="left"><bean:write name="iterRefDoc" property="categoryName" /></td>
										<td align="left"><bean:write name="iterRefDoc" property="fileName" /></td>
										<td><a href="./download.do?action=refdoc&fileId=<bean:write name="iterRefDoc" property="id" />"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr> 
								</logic:iterate>
								<tr>
									<td colspan="7" height="25"><img src="images/null.gif" width="1" height="25"></td>
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
	<tr>
		<td colspan="3" height="10"><img src="images/null.gif" width="1" height="10"></td>
	</tr>
</table>
</div>
<%@ include file="includes/footer.jsp" %>