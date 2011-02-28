<% if (isAdministrator || isModerator) { %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<style type="text/css">
#content {
	height: 560px;
}
</style>
<div id="amMenu">
	<div id="menuwrapper">
		<ul id="p7menubar">
			<% if (isAdministrator) { %>
			<li><a class="trigger" href="#">Administraci&oacute;n de Sistema</a>
				<ul>
					<li><html:link action="/editEmail?id=newpassword" >E-mail Nueva Contraseña</html:link></li>
					<li><html:link action="/editEmail?id=passworreset" >E-mail Blanqueo de password</html:link></li>
					<li><html:link action="/editEmail?id=newversion" >E-mail Nueva Versi&oacute;n</html:link></li>
					<li><html:link action="/editEmail?id=newobservation" >E-mail Nueva Observaci&oacute;n</html:link></li>
					<li><html:link action="/editProperty?id=simon.server.name" >Nombre del Servidor</html:link></li>
					<li><html:link action="/editProperty?id=simon.server.url" >URL del Servidor</html:link></li>
					<li><html:link action="/editProperty?id=simon.tmp.subpath" >Subpath Temporal</html:link></li>
					<li><html:link action="/goToResourceBundle" >Textos del Sitio</html:link></li>
				</ul>
			</li>
			<li><a class="trigger" href="#">Administraci&oacute;n de Usuarios</a>
				<ul>
					<li><a href="adminHome.jsp">Inicio</a></li>
					<li><html:link action="/goToCountryABM" >Administrar Delegaciones</html:link></li>
					<li><html:link action="/goToDelegateABM" >Administrar Delegados</html:link></li>
					<li><html:link action="/goToSystemUserABM" >Administrar Usuarios del Sistema</html:link></li>
					<li><html:link action="/goToResetPassword" >Blanqueo de Contraseñas</html:link></li>
				</ul>
			</li>
			<% } %>
			<li><a class="trigger" href="#">Administraci&oacute;n de Documentos</a>
				<ul>
					<li><html:link action="/goToModeratorHome" >Inicio/Documentos</html:link></li>
					<li><html:link action="/createDocument" >Crear Documento</html:link></li>
					<li><html:link action="/goToListDocument" >Lista de Documentos</html:link></li>
					<li><html:link action="/goToReferenceDocumentABM" >Documentos de Consulta</html:link></li>
					<li><html:link action="/goToCategoryABM" >Categor&iacute;as de Documentos de Consulta</html:link></li>
					<li><html:link action="/goToDelegateStats" >Panel de Control</html:link></li>
				</ul>
			</li>
			<% if (isDesigner) { %>
			<li><a class="trigger" href="#">Dise&ntilde;ador</a>
				<ul>
					<li><html:link action="/goToListDocumentForDesign" >Home de Dise&ntilde;ador</html:link></li>
				</ul>
			</li>
			<% } %>
			<% if (isTranslator) { %>
			<li><a class="trigger" href="#">Traductor</a>
				<ul>
					<li><html:link action="/goToTranslatorHome" >Home de Traductor</html:link></li>
				</ul>
			</li>
			<% } %>
			<% if (isAsisstant) { %>
			<li><a class="trigger" href="#">Asistente</a>
				<ul>
					<li><html:link action="/goToAssistantHome" >Home de Asistentes</html:link></li>
				</ul>
			</li>
			<% } %>
			<% if (isAdministrator) { %>
			<li><a class="trigger" href="#">Modo Negociación</a>
				<ul>
				<% 	if(eventMode) { %>
					<li><html:link action="setModSiteNormal" >Desactivar Modo Negociación</html:link></li>
				<% } else { %>
					<li><html:link action="setModSiteEvent" >Activar Modo Negociación</html:link></li>
				<% } %>
				</ul>
			</li>
			<li><a class="trigger" href="#">Pantalla pública</a>
				<ul>
					<li><html:link action="setPublicSiteNormal" >Desactivar Modo Negociación</html:link></li>
					<li><html:link action="setPublicSiteEvent" >Activar Modo Negociación</html:link></li>
				</ul>
			</li>
			<% } %>
		</ul>
		<br class="clearit">
	</div>
</div>
<% } %>