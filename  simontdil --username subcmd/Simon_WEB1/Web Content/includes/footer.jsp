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
	<div id="copyright">SEGIB Secretar&iacute;a General Iberoamericana<br>Paseo de Recoletos, 8 - Madrid 28001 Espa&ntilde;a<br>Tel&eacute;fono: +34 91 590 19 80 </div>
	<div id="logoCumbres"><img src="images/footer/logoCumbres.gif" alt="Cumbres Iberoamericanas" width="103" height="49"></div>
</div>
</body>
</html>