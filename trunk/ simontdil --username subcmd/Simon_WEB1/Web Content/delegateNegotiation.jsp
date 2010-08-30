<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<html:html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>Delegate Home</title>
		<style>
		 .rowODD { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; text-transform: none; color: #FF0000; text-decoration: none }
		 .rowEVEN { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-style: normal; line-height: normal; font-weight: bold; font-variant: normal; text-transform: none; color: #FF00FF; text-decoration: none }
		</style>
		<script type="text/javascript">
			function doAdd() {
				var pText = document.getElementById('pText').value;
				new Ajax.Request('<html:rewrite page="/addPrivateMessage.st"/>', {
				    parameters: { pText:pText},
				    onComplete: 
						function(transport, json) {
						   var result = json.result;
						   if ('OK' == result) {
						   document.getElementById('pText').value = "";
						   	document.getElementById('addCommentLayer').style.display = 'none';
						   } else {
						   	var error = json.error;
						   	document.getElementById('error').innertHTML= error;
						   }
						 }
				});
			}
		</script>
		<script type="text/javascript">
			var lastNumber = "";
			var lastText = "";
			function getDelegateSiteStatus() {
				new Ajax.Request('<html:rewrite page="/getDelegateSiteStatus.st"/>', {
			    onComplete: function(transport, json) {
			        var sitestatus = json.sitestatus;
			        if (sitestatus == 'NORMAL') {
			        	window.location='<html:rewrite page="/goToDelegateHome.st"/>';
			        } else {
			        	if (sitestatus == 'IN_NEGOTIATION') {
			        		var paragraphNumber = json.paragraphNumber;
			        		var paragraphText = json.paragraphText;
			        		if (paragraphNumber == "0") {
			        			lastNumber = paragraphNumber;
				        		lastText = paragraphText;
				        		var divObj = document.getElementById("lastParagraphText");
			        			divObj.innerHTML = "-";
			        		} else {
				        		if (lastNumber != paragraphNumber || lastText != paragraphText) {
				        			var divObj = document.getElementById("lastParagraphText");
				        			divObj.innerHTML = paragraphNumber + ". " + paragraphText;
				        			lastNumber = paragraphNumber;
				        			lastText = paragraphText;
				        		}
				        	}
			        	} else {
			        		var divObj = document.getElementById("negotiationArea");
			        		divObj.style.display = 'none';
			        		var divObj = document.getElementById("signArea");
			        		divObj.style.display = '';
			        	}
			        }
			      }
			   });
			}
			timer = setInterval("getDelegateSiteStatus()",1000);
		</script>
	</head>
	<body>
		<table>
		<logic:iterate name="DelegateNegotiationForm" property="versionVO.paragraphs" id="paragraph"> 
		<tr> 
			<td><bean:write name="paragraph" property="paragraphNumber" />. <bean:write name="paragraph" property="paragraphText" /></td>
		</tr> 
		</logic:iterate>
		Doc: <bean:write name="DelegateNegotiationForm" property="versionVO.document.title" /><br>
		Numero de Version:<bean:write name="DelegateNegotiationForm" property="versionVO.version.number" /><br>
		Nombre:<bean:write name="DelegateNegotiationForm" property="versionVO.version.name" /><br>
		</table>
		<div id="negotiationArea">
			Parrafo en negociacion:
			<div id="lastParagraphText" style="display: block; width=100px; height: 100px;">-</div>
			<div id="addCommentLayer" style="display: none;">
				<table>
					<tr><td id="error"></td><tr>
					<tr><td>observation<textarea id="pText"></textarea></td><tr>
					<tr><td><input type="button" onclick="doAdd()" value="Agregar observacion"><input type="button" onclick="document.getElementById('addCommentLayer').style.display = 'none';" value="Cancelar"></td><tr>
				</table>
			</div>
			<input type="button" value="Mensaje al delegado" onclick="document.getElementById('addCommentLayer').style.display = '';">
		</div>
		<div id="signArea" style="display: none; width:100px; height: 100px;">Sign here</div>
	</body>
</html:html>
<%@ include file="includes/footer.jsp" %>

	