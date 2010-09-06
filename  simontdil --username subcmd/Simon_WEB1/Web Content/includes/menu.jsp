<%@ taglib uri="/tags/struts-html" prefix="html" %>
<div id="amMenu">
	<div id="menuwrapper">
		<ul id="p7menubar">
			<% if (isAdministrator) { %>
			<li><a class="trigger" href="#">Usuario t&eacute;cnico</a>
				<ul>
					<li><a href="adminHome.jsp">Home de Admin</a></li>
					<li><html:link action="/goToCountryABM" >ABM de Delegaciones</html:link></li>
					<li><html:link action="/goToDelegateABM" >ABM de Delegados</html:link></li>
					<li><html:link action="/goToSystemUserABM" >ABM de Usuarios del sistema</html:link></li>
					<li><html:link action="/goToResetPassword" >Blanqueo de contraseñas</html:link></li>
				</ul>
			</li>
			<% } %>
			<li><a class="trigger" href="#">Administrador Moderador</a>
				<ul>
					<li><html:link action="/goToModeratorHome" >Home de Moderador</html:link></li>
					<li><html:link action="/createDocument" >Crear documento</html:link></li>
					<li><html:link action="/goToListDocument" >Lista de documentos</html:link></li>
					<li><html:link action="/goToReferenceDocumentABM" >ABM de Librer&iacute;a de Referencia</html:link></li>
					<li><html:link action="/goToCategoryABM" >ABM de Categor&iacute;as de Librer&iacute;a</html:link></li>
					<li><html:link action="/goToDelegateStats" >Panel de Control</html:link></li>
				</ul>
			</li>
			<li><a class="trigger" href="#">Evento</a>
				<ul>
					<li><html:link action="setModSiteNormal" >Desactivar Modo Negociación</html:link></li>
					<li><html:link action="setModSiteEvent" >Activar Modo Negociación</html:link></li>
				</ul>
			</li>
			<!--li><a class="trigger" href="#">Asistente de Dise&ntilde;o</a>
				<ul>
					<li><a href="am_m3_docList.html">Lista de Documentos Consolidados</a></li>
				</ul>
			</li-->
		</ul>
		<br class="clearit">
	</div>
</div>