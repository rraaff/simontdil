<%@ page info="public"%>
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
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="scripts/simon.js" type="text/javascript"></script>
<script src="scripts/menu.js" type="text/javascript"></script>

<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">
<link href="styles/tdil.css" rel="stylesheet" type="text/css">
<link href="styles/notimoo.css" rel="stylesheet" type="text/css">
<style type="text/css">
#content {
	height:700px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #808080;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #808080;
}
.article{
	font-size: 20px;
	line-height: 24px;	
}
</style>

<script type="text/javascript">

	var intro = "abcdefghijklmnopqrstuvwxyz";
	
	function getParagraphForDisplay(pNumber) {
		if (pNumber < 500) {
			if (pNumber < intro.length) {
				return intro.charAt(pNumber);
			} else {
				return "z" + (pNumber - intro.length);
			}
		} else {
			return pNumber - 500 + 1;
		}
	}

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
						if ('NORMAL' == status) {
							document.getElementById('splashLayer').style.display = 'block';
							document.getElementById('negotiationLayer').style.display = 'none';
							document.getElementById('inSignLayer').style.display = 'none';
						}
						if ('SIGN_SHOW' == status) {
							window.location='<html:rewrite page="/publicSignShow.jsp"/>';
						}
						if ('IN_NEGOTIATION' == status) {
							var paragraphNumber = json.paragraphNumber;
			        		var paragraphText = json.paragraphText;
			        		var paragraphVersion = json.paragraphVersion;
			        		if (paragraphNumber == "0") {
			        			lastNumber = paragraphNumber;
				        		lastText = paragraphText;
				        		document.getElementById('originalParagraph').innerHTML = '-';
				        		document.getElementById('negotiatedParagraph').innerHTML = '-';
			        		} else {
			        			// le saque || lastText != paragraphText 
				        		if (lastNumber != paragraphNumber || lastParagraphVersion != paragraphVersion) {
				        			document.getElementById('originalParagraph').innerHTML = "<p class='article'>" + getParagraphForDisplay(paragraphNumber - 1) + ". " + json.originalText + "</p>";
				        			document.getElementById('negotiatedParagraph').innerHTML = "<p class='article'>" + getParagraphForDisplay(paragraphNumber - 1) + ". " + json.paragraphText + "</p>";
				        			lastNumber = paragraphNumber;
				        			lastText = paragraphText;
				        			lastParagraphVersion = paragraphVersion;
				        		}
				        	}
							document.getElementById('splashLayer').style.display = 'none';
							document.getElementById('negotiationLayer').style.display = 'block';
							document.getElementById('inSignLayer').style.display = 'none';
						}
						if ('IN_SIGN' == status) {
							document.getElementById('splashLayer').style.display = 'none';
							document.getElementById('negotiationLayer').style.display = 'none';
							document.getElementById('inSignLayer').style.display = 'block';
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
</head>
<body>
<%
	java.util.HashMap<String, String> statusMap = com.tdil.simon.struts.actions.GetPublicSiteStatus.basicExecute("0", "0");
	String status = statusMap.get(com.tdil.simon.struts.actions.GetPublicSiteStatus.SITESTATUS);
%>
<div id="splashLayer" <%= com.tdil.simon.data.model.Site.NORMAL.equals(status) ? "style='display: block;'" : "style='display: none;'"%>>
	<div id="content">
		<table width="95%" height="700" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" align="center">
			<tr>
				<td align="center" valign="middle"><img src="./download.do?action=logo&fileId=others.splashSegib" width="500" height="500"></td>
			</tr>
		</table>
	</div>
</div>
<div id="negotiationLayer" <%= com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(status) ? "style='display: block;'" : "style='display: none;'"%>>
	<div id="content">
		<table width="95%" cellpadding="0" cellspacing="0" border="0" align="center">
			<tr>
				<td><img src="images/null.gif" width="1" height="17"></td>
			</tr>
			<tr>
				<td><div id="main1" style="background-color:#eeeeee; width:100%; height:2px; overflow:scroll;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td id="originalParagraph">
								<% if ("".equals(statusMap.get("originalText"))) { %>
									-
								<% } else {%>
									<p class='article'><%=statusMap.get("paragraphNumberForDisplay")%>. <%=statusMap.get("originalText")%></p>
								<% } %>
							</td>
						</tr>
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
							<td colspan="2" width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "parrafoEnNegociacion")%></div></td>
							<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
						</tr>
						<tr>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td width="100%" height="320" align="left" valign="top">
							<!-- corte tabla template -->
								<div id="main" style="background-color:#eeeeee; width:100%; height:640px; overflow:scroll;">
								<!--div id="negotiationArea" style="width:100%; height:350px;">
									<div id="lyr2" style="width:100%; height:326px;"-->
										<table width="100%" cellpadding="0" cellspacing="5" border="0" align="center">
											<tr>
												<td id="negotiatedParagraph">
													<% if ("".equals(statusMap.get("originalText"))) { %>
														-
													<% } else {%>
														<p class='article'><%=statusMap.get("paragraphNumberForDisplay")%>.<%=statusMap.get("paragraphText")%></p>
													<% } %>
												</td>
											</tr>
										</table>
									</div>
								</div>
							<!-- corte tabla template --></td>
							<td width="10"><img src="images/null.gif" width="10" height="1"></td>
							<!--td width="30" align="right">
								<div id="scrollbar2" style="width:20px; height:350px; float:right;">
									<div id="up2"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
									<div id="track2" style="height:326px;">
										<div id="dragBar2"></div>
									</div>
									<div id="down2"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
								</div></td-->
							<td width="9"><img src="images/null.gif" width="9" height="1"></td>
							<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
						</tr>
						<tr>
							<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
							<td colspan="2" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
							<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><img src="images/null.gif" width="1" height="20"></td>
			</tr>
		</table>
	</div>
</div>
<div id="inSignLayer" <%= com.tdil.simon.data.model.Site.IN_SIGN.equals(status) ? "style='display: block;'" : "style='display: none;'"%>>
	<div id="content">
		<table width="95%" height="700" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" align="center">
			<tr>
				<td align="center" valign="middle"><img src="./download.do?action=logo&fileId=others.splashSegib" width="500" height="500"><br><br><%=ResourceBundleCache.get(getServletInfo(), "documentoEnFirma")%>.</td>
			</tr>
		</table>
	</div>
</div>
<% if (com.tdil.simon.data.model.Site.SIGN_SHOW.equals(status)) { %>
	<jsp:forward page="./publicSignShow.jsp"/>
<% } %>
</body>
</html>