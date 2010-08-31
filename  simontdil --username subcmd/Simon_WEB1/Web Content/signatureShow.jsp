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
			var signatureArray = new Array();
			var lastSignatureIndex = 0;
		</script>
	</head>
	<body>
	<input type="button" onclick="getSignatures()" value="get signature">
	Signature showwwww
		<table>
		<logic:iterate name="DelegateNegotiationForm" property="versionVO.paragraphs" id="paragraph"> 
		<tr> 
			<td><bean:write name="paragraph" property="paragraphNumber" />. <bean:write name="paragraph" property="paragraphText" /></td>
		</tr> 
		</logic:iterate>
		</table>
		Doc: <bean:write name="DelegateNegotiationForm" property="versionVO.document.title" /><br>
		Numero de Version:<bean:write name="DelegateNegotiationForm" property="versionVO.version.number" /><br>
		Nombre:<bean:write name="DelegateNegotiationForm" property="versionVO.version.name" /><br>
		<div id="signaturePreview">
		</div>
		<table id="signTable">
		<logic:iterate name="DelegateNegotiationForm" property="signatures" id="signature" indexId="signatureIndex"> 
		<tr> 
			<td><img width="200" height="200" src="./signatures/<bean:write name="signature" property="signatureFileName" />"></td>
			<script>
				signatureArray[<%=signatureIndex%>] = '<bean:write name="signature" property="signatureFileName" />';
				lastSignatureIndex = <%=signatureIndex%>;
			</script>
		</tr>
		<tr> 
			<td><bean:write name="signature" property="delegateName" /></td>
		</tr>
		</logic:iterate>
		</table>
		<script type="text/javascript">
			function alreadyDisplayed(signName) {
				var index = 0;
				while(index < lastSignatureIndex) {
					if (signatureArray[index] == signName) {
						return true;
					}
					index = index + 1;
				}
				return false;
			}
			
			function getSignatures() {
				new Ajax.Request('<html:rewrite page="/getDelegateSiteStatus.st"/>', {
			    onComplete: function(transport, json) {
			        var sitestatus = json.sitestatus;
			        if (sitestatus == 'NORMAL') {
			        	window.location='<html:rewrite page="/goToDelegateHome.st"/>';
			        } else {
			        	// TODO manejar variable de bloqueo
			        	var i = 0;
			        	while(i < json.delegates.length) {
			        		alert(json.fileNames[i]);
			        		if (!alreadyDisplayed(json.fileNames[i])) {
				        		var myTable = document.getElementById("signTable");
								var tBody = myTable.getElementsByTagName("TBODY")[0];
								var newTR = document.createElement('tr');
								var operatorTD = document.createElement('td');
								operatorTD.align="center";
								operatorTD.innerHTML = '<b>' + json.delegates[i] + '</b>';
								operatorTD.className = "BorderRigth";
								operatorTD.width = "150";
								newTR.appendChild (operatorTD);
								tBody.appendChild(newTR);
								
								newTR = document.createElement('tr');
								var dateTD = document.createElement('td');
								dateTD.innerHTML = '<img width="200" height="200" alt="xxx" src="./signatures/' + json.fileNames[i] + '">';
								dateTD.className = "BorderRigth";
								dateTD.width = "100";
								dateTD.align="center";
								newTR.appendChild (dateTD);
								tBody.appendChild(newTR);
								signatureArray[lastSignatureIndex] = json.fileNames[i];
								lastSignatureIndex = lastSignatureIndex + 1;
							}
							i = i + 1;
			        	}
			        	
			        }
			      }
			   });
			}
			//timer = setInterval("getSignatures()",10000);
		</script>
	</body>
</html:html>
<%@ include file="includes/footer.jsp" %>

	