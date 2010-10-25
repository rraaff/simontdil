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
<style type="text/css">
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
<% java.util.List<com.tdil.simon.data.valueobjects.SignatureVO> allSignatures = com.tdil.simon.utils.DelegateSiteCache.getAllSignatures(); %>
<% if (allSignatures != null && !allSignatures.isEmpty()) { %>
<script type="text/javascript">
	window.addEvent('domready',function() {
		/* settings */
		var list = $('news-feed').getFirst('ul');
		var items = list.getElements('li');
		var showDuration = 3000;
		var scrollDuration = 500;
		var index = 0;
		var height = items[0].getSize().y;
		/* action func */
		var move = function() {
			list.set('tween',{
				duration: scrollDuration,
				onComplete: function() {
					if(index == items.length - 1) {
						index = 0 - 1;
						list.scrollTo(0,0);
					}
				}
			}).tween('top',0 - (++index * height));
		};
		/* go! */
		window.addEvent('load',function() {
			
			move.periodical(showDuration);
		});
	});
</script>
<% } %>

</head>
<body>
<table>
<% java.util.List<com.tdil.simon.data.model.Paragraph> paragraphs = com.tdil.simon.utils.DelegateSiteCache.getFinalParagraphs(); %>
<% for (com.tdil.simon.data.model.Paragraph p : paragraphs) { %>
	<tr><td><%=p.getParagraphNumberForDisplay()%>. <%=p.getParagraphText()%></td></tr>
<% } %>
</table>

<div id="news-feed">
<ul>

<% for (com.tdil.simon.data.valueobjects.SignatureVO signature : allSignatures) { %>
<li>
<table>
<tr> 
	<td rowspan="2" width="200" align="center"><img width="200" height="110" src="./download.do?action=signature&signature=<%=signature.getSignatureFileName()%>"></td>
	<td width="10"><img src="images/null.gif" width="10" height="1"></td>
	<td width="30"><img src="./download.do?action=flag&fileId=<%=signature.getCountryId()%>" width="30" height="30"></td>
	<td width="10"><img src="images/null.gif" width="10" height="1"></td>
	<td><%=signature.getCountryDescription()%></td>
</tr>
<tr>
	<td width="10"><img src="images/null.gif" width="10" height="1"></td>
	<td colspan="3" height="60"><span class="remarcado"><%=signature.getDelegateName()%></span><br><%=signature.getJob()%></td>
</tr>
</table>
</li>
<% } %>
</ul>
</div>
</table>
</body>
</html>