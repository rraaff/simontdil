<%@ page info="publicSignShow"%>
<%@ page import="com.tdil.simon.web.ResourceBundleCache"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=ResourceBundleCache.get("ventana", "titulo")%></title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script type="text/javascript" src="./scripts/mootools-1.2.4-core-nc.js" ></script>
<script type="text/javascript" src="./scripts/notimoo.js" ></script>
<script src="scripts/simon.js" type="text/javascript"></script>
<script src="scripts/menu.js" type="text/javascript"></script>
<!--[if lte IE 7]>
<style>
#menuwrapper, #p7menubar ul a {height: 1%;}
a:active {width: auto;}
</style>
<![endif]-->
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">
<link href="styles/tdil.css" rel="stylesheet" type="text/css">
<link href="styles/notimoo.css" rel="stylesheet" type="text/css">
<style type="text/css">
#content {
	height:760px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #808080;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #808080;
}
.article{
	font-size: 15px;
	line-height: 18px;	
}
#news-feed	 { height:200px; width:400px; overflow:hidden; position:relative; border:1px solid #ccc; background:#eee; }
#news-feed ul	{ position:absolute; top:0; left:0; list-style-type:none; padding:0; margin:0; }
#news-feed ul li { height:180px; font-size:12px; margin:0; padding:10px; overflow:hidden; }
</style>

<script>
	var inProgress = false;
	var retries = 0;
	var lastNumber = "0";
	var lastParagraphVersion = "0";
	
	function getPublicSiteStatus() {
		if (!inProgress || retries > 60) {
			retries = 0;
			inProgress = true;
			var jsonRequest = new Request.JSON(
				{
				url: '<html:rewrite page="/getPublicSiteStatus.st"/>', 
				onSuccess: 
					function(json, responseText){
						retries = 0;
						inProgress = false;
						var status = json.sitestatus;
						if ('SIGN_SHOW' != status) {
							window.location='<html:rewrite page="/public.jsp"/>';
						}
					}
				}
			).get({'paragraphVersion': lastParagraphVersion, 'paragraphNumber': lastNumber});
		} else {
			retries = retries + 1;
		}
	}
	var timer = setInterval("getPublicSiteStatus()",<%=com.tdil.simon.web.SystemConfig.getClientParagrahRefreshTime()%>);
</script>
<% java.util.List<com.tdil.simon.struts.forms.SignatureRow> allSignatures = com.tdil.simon.utils.DelegateSiteCache.getSignaturesRows(); %>
<script>
	var velocidad = 0;
	function scrollme() {
		e=document.getElementById("content");
		if (e.scrollTop>(e.scrollHeight-1200)) {
			velocidad+=1;
		} else {
			velocidad+=5;
		}		
		e.scrollTop=0+velocidad;
		if(e.scrollTop>=(e.scrollHeight-e.clientHeight)) {
			clearInterval(timer2);
		}		
	}
	
	var timer2 = setInterval("scrollme()",20);

	function startScroll() {
		e=document.getElementById("content");
		e.scrollTop=0;
		timer2 = setInterval("scrollme()",20);
	}
	function stopScroll() {
		clearInterval(timer2);
	}
</script>

</head>
<body>
<div id="content" style="overflow:scroll;">
	<table width="95%" cellpadding="0" cellspacing="0" border="0" align="center">
		<tr>
			<td><img src="images/null.gif" width="1" height="17"></td>
		</tr>
		<tr>
			<td>
				<table width="95%" height="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" align="center">
					<% java.util.List<com.tdil.simon.data.model.Paragraph> paragraphs = com.tdil.simon.utils.DelegateSiteCache.getFinalParagraphs(); %>
					<% for (com.tdil.simon.data.model.Paragraph p : paragraphs) { %>
						<tr><td><%=p.getParagraphNumberForDisplay()%>. <%=p.getParagraphText()%></td></tr>
					<% } %>
				</table>
			</td>
		</tr>
		<tr>
			<td><img src="images/null.gif" width="1" height="50"></td>
		</tr>
		<tr>
			<td align="center">
				<table width="500" id="signTable" border="0" cellspacing="0" cellpadding="0" align="center">
					<% for (com.tdil.simon.struts.forms.SignatureRow signatureRow : allSignatures) { %>
					<tr> 
						<td width="200" align="center"><img width="200" height="110" src="./download.do?action=signature&signature=<%=signatureRow.getLeft().getSignatureFileName()%>"></td>
						<td width="100"><img src="images/null.gif" width="10" height="1"></td>
						<!-- td width="30"><img src="./download.do?action=flag&fileId=<%=signatureRow.getLeft().getCountryId()%>" width="30" height="30"></td>
						<td width="10"><img src="images/null.gif" width="10" height="1"></td -->
						<% if (!signatureRow.getHasRight()) { %>
							<td width="200"><img src="images/null.gif" width="10" height="1"></td>
						<% } else {%>
							<td width="200" align="center"><img width="200" height="110" src="./download.do?action=signature&signature=<%=signatureRow.getRight().getSignatureFileName()%>"></td>
						<% } %>
					</tr>
					<tr>
						<td align="center" height="18">Por <%=signatureRow.getLeft().getCountryDescription()%></td>
						<td width="100"><img src="images/null.gif" width="10" height="1"></td>
						<% if (!signatureRow.getHasRight()) { %>
							<td width="200"><img src="images/null.gif" width="10" height="1"></td>
							<!-- td rowspan="2" width="200" align="center">&nbsp;</td>
							<td width="10">&nbsp;</td>
							<td width="30">&nbsp;</td>
							<td width="10">&nbsp;</td -->
						<% } else {%>
							<!-- td rowspan="2" width="200" align="center"><img width="200" height="110" src="./download.do?action=signature&signature=<%=signatureRow.getRight().getSignatureFileName()%>"></td>
							<td width="10"><img src="images/null.gif" width="10" height="1"></td-->
							<!-- td width="30"><img src="./download.do?action=flag&fileId=<%=signatureRow.getRight().getCountryId()%>" width="30" height="30"></td>
							<td width="10"><img src="images/null.gif" width="10" height="1"></td -->
							<td align="center"><%=ResourceBundleCache.get(getServletInfo(), "por")%> <%=signatureRow.getRight().getCountryDescription()%></td>
						<% } %>
					</tr>
					<tr>
						<!-- td width="10"><img src="images/null.gif" width="10" height="1"></td -->
						<td align="center" height="18"><span class="remarcado"><!-- % =signatureRow.getLeft().getDelegateName()%></span><br --><!--  %=signatureRow.getLeft().getJob()% --></span></td>
						<td width="100"><img src="images/null.gif" width="10" height="1"></td>
						<% if (!signatureRow.getHasRight()) { %>
							<td width="200"><img src="images/null.gif" width="10" height="1"></td>
							<!-- td width="10"><img src="images/null.gif" width="10" height="1"></td>
							<td colspan="3" height="60">&nbsp;</td -->
						<% } else {%>
							<!-- td width="10"><img src="images/null.gif" width="10" height="1"></td>
							<td colspan="3" height="60"><span class="remarcado"><%=signatureRow.getRight().getDelegateName()%></span><br><!-- %=signatureRow.getRight().getJob()% --></td-->
							<td align="center"><span class="remarcado"><!--  %=signatureRow.getRight().getJob()% --></span></td>
						<% } %>
					</tr>
					<tr>
						<td colspan="3"><img src="images/null.gif" width="1" height="20"></td>
					</tr>
					<% } %>
				</table>
			</td>
		</tr>
		<tr>
			<td><img src="images/null.gif" width="1" height="17"></td>
		</tr>
	</table>
</div>
<a href="javascript:stopScroll()" style="color:#CCCCCC">Q</a>
</body>
</html>