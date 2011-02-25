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
							title: 'Error',
							message: '<html:messages id="msg" message="true"><% if (!"*".equals(msg)) {%><%=msg%><br><% } %></html:messages>',
							 customClass:'alert_error',
							 sticky: true
						});
	<% } %>
</script>
<div id="footer">
	<div id="copyright"><!-- INSERTAR VARIABLE --></div>
	<div id="logoCumbres"><img src="images/null.gif" width="103" height="49"></div>
	<div style="width:150px;font-size:10px; color:#999999; float:left; margin-top: -60px; margin-left: 10px;"> - El servidor utiliza GMT - </div>
</div>
</body>
</html>
<% } catch (Throwable e) {
		com.tdil.simon.web.JspErrorLog.logJspError(e);%>
		<%@ include file="./exception.jsp"%>
<% } %>