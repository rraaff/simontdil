<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>

<style type="text/css">
<!--
#content #centrador{
	padding-left:130px;
}
-->
</style>
<html:html>
<div id="content">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="980" height="300">
			<!-- inicio tabla template -->
			<img src="images/null.gif" width="1" height="340">
			<!-- fin tabla template -->
		</td>
	</tr>
</table>
</div>
<%@ include file="includes/footer.jsp" %>
<html:form method="POST" action="/consolidateDocument">
<div id="outerdiv">
	<div id="innerdiv"></div>
	<div id="contetTableComment">
		<table width="980" height="582" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="middle">
					<table width="540" height="285" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
						<tr>
							<td width="540" align="center" valign="top">
								<!-- inicio tabla template -->
								<table width="520" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" background="images/interfaces/topLeftTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
										<td background="images/interfaces/topTitle.gif" width="500" height="19" align="left"><div id="blockTitle">Agregar comentario de versión</div></td>
										<td colspan="2" background="images/interfaces/topRightTitle.gif" width="10" height="19"><img src="images/null.gif" width="10" height="19"></td>
									</tr>
									<tr>
										<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
										<td width="9"><img src="images/null.gif" width="9" height="1"></td>
										<td width="500" height="144"><!-- corte tabla template -->
											<table width="500" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
												</tr>
												<tr>
													<td align="right" width="139" height="140" valign="top">Comentario:</td>
													<td width="7"><img src="images/null.gif" width="7" height="1"></td>
													<td width="354" align="left"><html:textarea name="CreateDocumentForm" property="consolidateText" styleClass="textfield_effect_area"/></td>
												</tr>
												<tr>
													<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
												</tr>
												<tr>
													<td colspan="3" align="center"><html:submit property="operation">
																		<bean:message key="createDocument.consolidate.save"/>
																	</html:submit>
																	<html:submit property="operation">
																		<bean:message key="createDocument.consolidate.cancel"/>
																	</html:submit></td>
												</tr>
												<tr>
													<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
												</tr>
											</table>
											<span class="errorText"><html:errors property="general" /></span>
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
</div>
</html:form>
</html:html>