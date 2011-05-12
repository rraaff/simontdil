<script type="text/javascript">
	var absolute_center = Array(window.getSize().x / 2,window.getSize().y / 2);
	var notimooNormalManager = new Notimoo();
	var notimooErrorManager = new Notimoo({
		locationVType: 'top',
		locationHType: 'right',
		locationHBase: absolute_center[0] - 150,
		locationVBase: absolute_center[1] - 50
	});
	<% if ("true".equals((String)request.getAttribute("hasError"))) { %>
	notimooErrorManager.show({
							title: '<%=ResourceBundleCache.get("ventanaError", "titulo")%>',
							message: '<%=com.tdil.simon.web.ErrorFormatter.getErrorsFrom(request)%>',
							 customClass:'alert_error',
							 sticky: true
						});
	<% } %>
</script>
<div id="footer">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20" height="2" background="images/focalae_set/middle_left.gif"><img src="images/null.gif" width="20" height="2"></td>
			<td colspan="3" height="2" background="images/focalae_set/line_sutil.gif"><img src="images/null.gif" width="1" height="2"></td>
			<td width="20" height="2" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="2"></td>
		</tr>
		<tr>
			<td width="20" background="images/focalae_set/middle_left.gif"><img src="images/null.gif" width="20" height="1"></td>
			<td width="220" align="right"><img src="images/focalae_set/logo_focalae.gif" width="200" height="71"></td>
			<td width="100%" align="center"><div id="copyright"><strong>Foro de Cooperaci&oacute;n Am&eacute;rica Latina - Asia del Este</strong><br/>Esmeralda 1212, C1007ABR, Buenos Aires, Argentina - Tel. +54 (11) 4310-8397<br/><br/><%=ResourceBundleCache.get("footer", "GMT")%></div></td>
			<td width="220" align="left"><img src="images/focalae_set/logo_mrcic.gif" width="200" height="71"></td>
			<td width="20" background="images/focalae_set/middle_right.gif"><img src="images/null.gif" width="20" height="1"></td>
		</tr>
		<tr>
			<td width="20" height="20"><img src="images/focalae_set/bottom_left.gif" width="20" height="20"></td>
			<td colspan="3" background="images/focalae_set/bottom_center.gif"><img src="images/null.gif" width="20" height="20"></td>
			<td width="20" height="20"><img src="images/focalae_set/bottom_right.gif" width="20" height="20"></td>
		</tr>
	</table>
</div>
</body>
</html>
<% } catch (Throwable e) {
		com.tdil.simon.web.JspErrorLog.logJspError(e);%>
		<%@ include file="./exception.jsp"%>
<% } %>