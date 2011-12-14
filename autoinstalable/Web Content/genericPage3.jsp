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
						
				    <tr class="rowODD">
							<td><a href="other/Circular-4-V-FMM-Esp.pdf" target="_blank">FMM/2011/INF/4<br />
						    SOM/2011/INF4<br />
						    POL/2011/INF/4<br />
						    ECON/2011/INF/4<br />
						    ST/2011/INF/4<br />
						    SWGT/2011/INF/4 Circular Informativa 4</a></td>
						</tr>
						<tr class="rowEVEN">
							<td><a href="other/Circular-4-V-FMM-ingles.pdf" target="_blank">FMM/2011/INF/4<br />
						    SOM/2011/INF/4<br />
						    POL/2011/INF/4<br />
						    ECON/2011/INF/4<br />
						    ST/2011/INF/4<br />
						    SWGT/2011/INF/4 Informative Circular 4</a></td>
						</tr>
                        
                        <tr class="rowODD">
							<td><a href="other/Circular-5-ingles-programa.pdf" target="_blank">FMM/2011/INF/4<br />
						    SOM/2011/INF/5<br />
						    POL/2011/INF/5<br />
						    ECON/2011/INF/5<br />
						    ST/2011/INF/5<br />
						    SWGT/2011/INF/5 Informative Circular 5</a></td>
						</tr>
                        
                        <tr class="rowEVEN">
							<td><a href="other/Circular-5-esp-programa.pdf" target="_blank">FMM/2011/INF/4<br />
						    SOM/2011/INF/5<br />
						    POL/2011/INF/5<br />
						    ECON/2011/INF/5<br />
						    ST/2011/INF/5<br />
						    SWGT/2011/INF/5 Circular Informativa 5</a></td>
						</tr>
                        
						<tr class="rowODD">
							<td><a href="other/Circular-6-V-FMM-esp-(seguridad).pdf" target="_blank">FMM/2011/INF/6<br />
  SOM/2011/INF/6<br />
  POL/2011/INF/6<br />
  ECON/2011/INF/6<br />
  ST/2011/INF/6<br />
  SWGT/2011/INF/6  Circular Informativa 6</a></td>
						</tr>
						<tr class="rowEVEN">
							<td><a href="other/Circular-6-V-FMM-ingles-(seguridad).pdf" target="_blank">FMM/2011/INF/6<br />
SOM/2011/INF/6<br />
POL/2011/INF/6<br />
ECON/2011/INF/6<br />
ST/2011/INF/6<br />
SWGT/2011/INF/6 Informative Circular 6</a></td>
						</tr>
						<tr class="rowODD">
							<td><a href="other/circular-7-V-FMM-esp-(protocolo).pdf" target="_blank">FMM/2011/INF/7<br />
  SOM/2011/INF/7<br />
  POL/2011/INF/7<br />
  ECON/2011/INF/7<br />
  ST/2011/INF/7<br />
  SWGT/2011/INF/7 Circular Informativa 7</a></td>
						</tr>
						<tr class="rowEVEN">
							<td><a href="other/circular-7-V-FMM-ingles.pdf" target="_blank">FMM/2011/INF/7<br />
SOM/2011/INF/7<br />
POL/2011/INF/7<br />
ECON/2011/INF/7<br />
ST/2011/INF/7<br />
SWGT/2011/INF/7 Informative Circular 7</a></td>
						</tr>
						<tr class="rowODD">
							<td><a href="other/circular-8-V-FMM-esp-(nota cargos rotativos).pdf" target="_blank">FMM/2011/INF/8<br />
  SOM/2011/INF/8<br />
  POL/2011/INF/8<br />
  ECON/2011/INF/8<br />
  ST/2011/INF/8<br />
  SWGT/2011/INF/8 Circular Informativa 8</a></td>
						</tr>
						<tr class="rowEVEN">
							<td><a href="other/circular-8-V-FMM-ingles-(nota cargos rotativos).pdf" target="_blank">FMM/2011/INF/8<br />
SOM/2011/INF/8<br />
POL/2011/INF/8<br />
ECON/2011/INF/8<br />
ST/2011/INF/8<br />
SWGT/2011/INF/8 Informative Circular 8</a></td>
						</tr>
						<tr class="rowODD">
							<td><a href="other/circular-9-V-FMM-esp-(especialistas).pdf" target="_blank">FMM/2011/INF/9<br />
  SOM/2011/INF/9<br />
  POL/2011/INF/9<br />
  ECON/2011/INF/9<br />
  ST/2011/INF/9<br />
  SWGT/2011/INF/9 Circular Informativa 9</a></td>
						</tr>
						<tr class="rowEVEN">
							<td><a href="other/circular-9-V-FMM-ingles-(especialistas).pdf" target="_blank">FMM/2011/INF/9<br />
SOM/2011/INF/9<br />
POL/2011/INF/9<br />
ECON/2011/INF/9<br />
ST/2011/INF/9<br />
SWGT/2011/INF/9 Informative Circular 9</a></td>
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