<%@ page info="forcedDocs"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerDelegateForForcedDocs.jsp" %>
<jsp:useBean id="delegatePopupBean" scope="session" class="com.tdil.simon.struts.forms.DelegatePopupBean"/>
<% 
	delegatePopupBean.setLoggedUser(user);
	delegatePopupBean.init(); 
%>

<div id="content" style="height:350px;">
	<% if (delegatePopupBean.getTypeOne() != null) { %>
		<a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=delegatePopupBean.getTypeOne().getDocumentId()%>"><%=delegatePopupBean.getTypeOne().getDocumentTitle()%></a>
	<% } %>
	
	<% if (delegatePopupBean.getTypeTwo() != null) { %>
		<a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=delegatePopupBean.getTypeTwo().getDocumentId()%>"><%=delegatePopupBean.getTypeTwo().getDocumentTitle()%></a>
	<% } %>
	
	Documentos Principales
		<div id="alcincuentaLeft">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Documentos Específicos</div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="100%" valign="top">
						<!-- corte tabla template -->
						<div id="contentDocumentoSecundarios">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr> 
									<td height="15"><img src="images/null.gif" width="1" height="15"></td>
								</tr>
								<%  int referenceListIndex = 0;
									for (com.tdil.simon.data.model.Document doc : delegatePopupBean.getOtherDocumentsList()) { %> 
									<tr> 
										<td height="22" class="row<%=referenceListIndex % 2 == 0 ? "ODD" : "EVEN"%>"><a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=doc.getId()%>"><%=doc.getTitle()%></a></td>
									</tr>
								<% referenceListIndex = referenceListIndex + 1;
									} %>
								<tr>
									<td height="15"><img src="images/null.gif" width="1" height="15"></td>
								</tr>
							</table>
						</div>
					<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td><img src="images/null.gif" width="9" height="1"></td>
					<td height="20" valign="right"></td>
					<td><img src="images/null.gif" width="9" height="1"></td>
					<td bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
		<table>
	<%  int iterIndex = 0;
		for (com.tdil.simon.data.model.ReferenceDocument ref : delegatePopupBean.getReferenceList()) { %>
		<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
			<td height="28" align="left"><%=ref.getTitle()%></td>
			<td align="left"><%=ref.getFileName()%></td>
			<td><a href="./download.do?action=refdoc&fileId=<%=ref.getId()%>"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
		</tr> 
	<% iterIndex = iterIndex + 1;
		} %>
		</table>
	
	
</div>
<div id="footer">
	<div id="copyright">SEJEC Secretar&iacute;a Ejecutiva XX Cumbre Iberoamericana<br>Esmeralda 1212, Ciudad Autónoma de Buenos Aires.<br>Código Postal: C1007ABR. República Argentina<br>Tel&eacute;fonos: +54 11 4819 7520 / +54 11 4819 7521</div>
	<div id="logoCumbres"><img src="images/footer/logoCumbres.gif" alt="Cumbres Iberoamericanas" width="103" height="49"></div>
	<div style="width:150px;font-size:10px; color:#999999; float:left; margin-top: -60px; margin-left: 10px;"> - El servidor utiliza GMT - </div>
</div>
</body>
</html>