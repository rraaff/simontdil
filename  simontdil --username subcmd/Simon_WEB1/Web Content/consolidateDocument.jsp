<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Cumbres Iberoamericanas | Al servicio de la Comunidad Iberoamericana</title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script type="text/javascript" src="./scripts/prototype.js" ></script>
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">

<link href="styles/tdil.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
#content #centrador{
	padding-left:130px;
}
-->
</style>
</head>
<body>
<html:form method="POST" action="/consolidateDocument">
<!-- Necesitaría un cancel, que vuelva al paso anterior -->
<div id="content">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#40708C">
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
	<tr>
		<td>
			<table border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
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
									<a class="button" href="moderatorHome.jsp"><span>Consolidar</span></a></td>
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
		</td>
	</tr>
	<tr>
		<td height="20"><img src="images/null.gif" width="1" height="20"></td>
	</tr>
</table>
</div>
</html:form>
</html:html>