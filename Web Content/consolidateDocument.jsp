<%@ page info="consolidateDocument"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<html:html>
<div id="content"><img src="images/null.gif" width="1" height="340"></div>
<div id="outerdiv">
<html:form method="POST" action="/consolidateDocument">
	<div id="innerdiv"></div>
	<div id="contentTableComment">
		<table height="100%" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="middle">
					<table width="490" height="265" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td width="490" align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="470" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
										<td background="images/interfaces/topTitle.gif" width="450" height="19" align="left"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
										<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="450" height="144"><!-- corte tabla template -->
											<table width="450" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
												</tr>
												<tr>
													<td align="right" width="82" height="140" valign="top"><%=ResourceBundleCache.get(getServletInfo(), "descripcion")%>:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="303" align="left"><html:textarea name="CreateDocumentForm" property="consolidateText" styleClass="textfield_effect_area"/></td>
												</tr>
												<tr>
													<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="3" align="center"><html:submit property="operation">
																		<%=ResourceBundleCache.get(getServletInfo(), "consolidar")%>
																	</html:submit>
																	<html:submit property="operation">
																		<%=ResourceBundleCache.get(getServletInfo(), "cancelar")%>
																	</html:submit></td>
												</tr>
												<tr>
													<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
												</tr>
											</table>
											<!-- corte tabla template --></td>
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
					</table>
				</td>
			<tr>
		</table>
	</div>
</html:form>
</div>
</html:html>
<%@ include file="includes/footer.jsp" %>