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

<div id="content" style="height:420px;">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td background="images/focalae_set/middle_left.gif" width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="100%">
		<% for (com.tdil.simon.data.valueobjects.VersionForListVO principal : delegatePopupBean.getPrincipalVersions()) { %>
			<div id="alcincuentaLeft" style="width:47%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td background="images/interfaces/topLeftTitleDoc.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
						<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
						<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
					</tr>
					<tr>
						<td background="images/interfaces/middleLeftDoc.gif" width="10" height="1"><img src="images/null.gif" width="10" height="1"></td>
						<td height="100%"><div id="contentDocumentoPrincipal"><div class="titleDocInModule"><a href="./goToViewLastVersionOfDocumentPopup.st?documentID=<%=principal.getDocumentId()%>"><%=principal.getDocumentTitle()%></a></div></div>
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
											<td width="80"><a href="./download.do?action=refdoc&fileId=<%=ref.getId()%>">
												<%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","descargar")%>
												</a></td>
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
		</td>
		<td background="images/focalae_set/middle_right.gif" width="20"><img src="images/null.gif" width="20" height="1"></td>
	</tr>
</table>
</div>
<div id="footer">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20" height="2" background="images/focalae_set/middle_left.gif"><img src="images/null.gif" width="20" height="2"></td>
			<td colspan="3" height="2" background="images/focalae_set/line_sutil.gif"><img src="images/null.gif" width="1" height="2"></td>
			<td width="20" height="2" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="2"></td>
		</tr>
		<tr>
			<td width="20" background="images/focalae_set/middle_left.gif"><img src="images/null.gif" width="20" height="1"></td>
			<td width="220" align="right"><img src="images/focalae_set/logo_focalae.gif" width="200" height="71"></td>
			<td width="100%" align="center"><div id="copyright"><strong>Foro de Cooperaci&oacute;n Am&eacute;rica Latina - Asia del Este</strong><br/>Esmeralda 1212, C1007ABR, Buenos Aires, Argentina - Tel. +54 (11) 4310-8397<br/><br/><%=ResourceBundleCache.get("footer", "GMT")%></div></td>
			<td width="220" align="left"><img src="images/focalae_set/logo_mrcic.gif" width="200" height="71"></td>
			<td width="20" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="1"></td>
		</tr>
		<tr>
			<td width="20" height="20"><img src="images/focalae_set/bottom_left.gif" width="20" height="20"></td>
			<td colspan="3" background="images/focalae_set/bottom_center.gif"><img src="images/null.gif" width="20" height="20"></td>
			<td width="20" height="20"><img src="images/focalae_set/bottom_right.gif" width="20" height="20"></td>
		</tr>
	</table>
</div>
</body>
</html>