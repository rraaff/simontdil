<%@ page info="forcedDocs"%>
<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/headerDelegateForForcedDocs.jsp" %>
<style type="text/css">
div#main {
	* width:100%;
	# width:100%;
	width:auto;
}
div#main2 {
	* width:100%;
	# width:100%;
	width:auto;
}
#content .titleDocInModule {
	font-size: 12px;
	line-height: 14px;
	color:#333333;
}
</style>
<jsp:useBean id="delegatePopupBean" scope="session" class="com.tdil.simon.struts.forms.DelegatePopupBean"/>
<% 
	delegatePopupBean.setLoggedUser(user);
	delegatePopupBean.init(); 
%>

<div id="content" style="height:350px;">
	<div id="alcien" style="height:60px; padding-top:5px;">
	<% if (delegatePopupBean.getTypeOne() != null) { %>
		<div id="alcincuentaLeft" style="width:47%">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td background="images/interfaces/topLeftTitleDoc.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
					<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td background="images/interfaces/middleLeftDoc.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
					<td height="100%"><div id="contentDocumentoPrincipal"><div class="titleDocInModule"><a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=delegatePopupBean.getTypeOne().getDocumentId()%>"><%=delegatePopupBean.getTypeOne().getDocumentTitle()%></a></div></div>
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td background="images/interfaces/bottomLeftDoc.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
	<% } %>
	<% if (delegatePopupBean.getTypeTwo() != null) { %>
		<div id="alcincuentaRight" style="width:47%; height:inherit;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td width="10" height="19" background="images/interfaces/topLeftTitleDocB.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="10" height="1" background="images/interfaces/middleLeftDocB.gif"><img src="images/null.gif" width="10" height="1"></td>
					<td height="100%"><div id="contentDocumentoPrincipal"><div class="titleDocInModule"><a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=delegatePopupBean.getTypeTwo().getDocumentId()%>"><%=delegatePopupBean.getTypeTwo().getDocumentTitle()%></a></div></div>
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td background="images/interfaces/bottomLeftDocB.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
	<% } %>
	</div>
	<div id="alcien" style="height:200px; padding-bottom:10px;">
		<div id="alcincuentaLeft" style="width:47%; height:200px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "documentosEspecificos")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%">
						<!-- corte tabla template -->
						<!--div id="main" style="width:inherit; height:250px;">
							<div id="lyr1" style="width:inherit; height:240px;"-->
							<div id="main" style="background-color:#eeeeee; width:98%; height:240px; overflow:scroll;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<%  int referenceListIndex = 0;
										for (com.tdil.simon.data.model.Document doc : delegatePopupBean.getOtherDocumentsList()) { %> 
										<tr> 
											<td height="22" class="row<%=referenceListIndex % 2 == 0 ? "ODD" : "EVEN"%>"><a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=doc.getId()%>"><%=doc.getTitle()%></a></td>
										</tr>
									<% referenceListIndex = referenceListIndex + 1;
										} %>
								</table>
							</div>
						<!-- /div -->
					</td>
					<td width="30">
						<!--div id="scrollbar" style="width:20px; height:240px; float:right;">
							<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
							<div id="track" style="height:216px;">
								<div id="dragBar"></div>
							</div>
							<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
						</div-->
					<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomCenter.gif" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
		<div id="alcincuentaRight" style="width:47%; height:inherit;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "documentosInformativos")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%">
						<!-- corte tabla template -->
						<!--div id="main2" style="width:inherit; height:250px;">
							<div id="lyr2" style="width:inherit; height:240px;"-->
							<div id="main2" style="background-color:#eeeeee; width:98%; height:240px; overflow:scroll; position:relative;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<%  int iterIndex = 0;
										for (com.tdil.simon.data.model.ReferenceDocument ref : delegatePopupBean.getReferenceList()) { %>
										<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
											<td height="28" align="left"><%=ref.getTitle()%></td>
											<td align="left"><%=ref.getFileName()%></td>
											<td width="80"><a href="./download.do?action=refdoc&fileId=<%=ref.getId()%>"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
										</tr> 
									<% iterIndex = iterIndex + 1;
										} %>
								</table>
							</div>
						<!-- /div -->
					<!-- corte tabla template -->
					</td>
					<td width="30">
						<!--div id="scrollbar2" style="width:20px; height:240px; float:right;">
							<div id="up2"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
							<div id="track2" style="height:216px;">
								<div id="dragBar2"></div>
							</div>
							<div id="down2"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
						</div-->
					<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomCenter.gif" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<div id="footer">
	<div id="copyright">SEJEC Secretar&iacute;a Ejecutiva XX Cumbre Iberoamericana<br>Esmeralda 1212, Ciudad Autónoma de Buenos Aires.<br>Código Postal: C1007ABR. República Argentina<br>Tel&eacute;fonos: +54 11 4819 7520 / +54 11 4819 7521</div>
	<div id="logoCumbres"><img src="images/footer/logoCumbres.gif" alt="Cumbres Iberoamericanas" width="103" height="49"></div>
	<div style="width:150px;font-size:10px; color:#999999; float:left; margin-top: -60px; margin-left: 10px;"> - El servidor utiliza GMT - </div>
</div>
</body>
</html>