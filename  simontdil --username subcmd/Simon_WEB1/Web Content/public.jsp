<%@ page info="public"%>
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
<title>Cumbres Iberoamericanas | Al servicio de la Comunidad Iberoamericana</title>
<meta name="keywords" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="description" content="Al servicio de la Comunidad Iberoamericana" />
<meta name="AUTHOR" content="That Day in London - Agencia Interactiva & Diseño" />
<script type="text/javascript" src="./scripts/mootools-1.2.4-core-nc.js" ></script>
<script type="text/javascript" src="./scripts/notimoo.js" ></script>
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="scripts/simon.js" type="text/javascript"></script>
<script src="scripts/menu.js" type="text/javascript"></script>
<!--[if lte IE 7]>
<style>
#menuwrapper, #p7menubar ul a {height: 1%;}
a:active {width: auto;}
</style>
<![endif]-->
<link rel="shortcut icon" href="http://segib.org/cumbres/wp-content/themes/segib/images/favicon.ico">
<link href="styles/tdil.css" rel="stylesheet" type="text/css">
<link href="styles/notimoo.css" rel="stylesheet" type="text/css">
<script>

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
Splash
</div>
<div id="negotiationLayer" <%= com.tdil.simon.data.model.Site.IN_NEGOTIATION.equals(status) ? "style='display: block;'" : "style='display: none;'"%>>
<table>
	<tr>
		<td id="originalParagraph">
			<% if ("".equals(statusMap.get("originalText"))) { %>
				-
			<% } else {%>
				<p class='article'><%=statusMap.get("paragraphNumberForDisplay")%>. <%=statusMap.get("originalText")%></p>
			<% } %>
			</td>
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
<div id="inSignLayer" <%= com.tdil.simon.data.model.Site.IN_SIGN.equals(status) ? "style='display: block;'" : "style='display: none;'"%>>
En firmas
</div>
<% if (com.tdil.simon.data.model.Site.SIGN_SHOW.equals(status)) { %>
	<jsp:forward page="./publicSignShow.jsp"/>
<% } %>
</body>
</html>