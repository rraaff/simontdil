<%@ page info="genericPage1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>

<% if (isDelegate) { %>
<script type="text/javascript">
	
	function getDelegateSiteStatus() {
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getDelegateSiteStatus.st"/>', onSuccess: function(json, responseText){
			var errorResult = json.error;
			if ('notLogged' == errorResult) {
				window.location='<html:rewrite page="/login.jsp"/>';
				return;
			}
		    var sitestatus = json.sitestatus;
			if (sitestatus != 'NORMAL') {
				window.location='<html:rewrite page="/goToDelegateNegotiation.st"/>';
				return;
			}
		}}).get();
	}
	timer = setInterval("getDelegateSiteStatus()",1000);

</script>
<% } %>
<%if (isModerator) { %>
<%@ include file="includes/menu.jsp" %>
<% } %>
<%@ include file="includes/leftContentModerator.jsp" %>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td colspan="3"><%@ include file="includes/extraLinks.jsp" %></td>
		</tr>
		<tr>
			<td colspan="3"><img src="images/null.gif" width="1" height="10"></td>
		</tr>
		<tr>
			<td colspan="3">
				<div style="width:100%; height:450px; overflow:auto;">
					<table width="100%" border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td><%=ResourceBundleCache.get("genericPage1", "Documento")%></td>
							<td width="150"><%=ResourceBundleCache.get("genericPage1", "Actions")%></td>
						</tr>
						<tr class="rowODD">
							<td><!--Security_arrangements.doc--></td>
							<td><!-- a href="other/Security_arrangements.doc" target="_blank"><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonMiddle("botones","descargar")%></a--></td>
						</tr>
						<tr class="rowEVEN">
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="40" align="center" valign="middle"><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonStart()%><html:link action="/goToDelegateHome" ><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonMiddle("botones","volver")%></html:link><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonEnd()%></td>
		</tr>
	</table>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>