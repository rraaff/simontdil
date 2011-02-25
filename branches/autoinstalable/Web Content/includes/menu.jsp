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
			<li><a class="trigger" href="#">Administraci&oacute;n de Usuarios</a>
				<ul>
					<li><a href="adminHome.jsp">Inicio</a></li>
					<li><html:link action="/goToCountryABM" >Administrar Delegaciones</html:link></li>
					<li><html:link action="/goToDelegateABM" >Administrar Delegados</html:link></li>
					<li><html:link action="/goToSystemUserABM" >Administrar Usuarios del sistema</html:link></li>
					<li><html:link action="/goToResetPassword" >Blanqueo de contraseñas</html:link></li>
				</ul>
			</li>
			<% } %>
			<li><a class="trigger" href="#">Administraci&oacute;n de Documentos</a>
				<ul>
					<li><html:link action="/goToModeratorHome" >Inicio/Documentos</html:link></li>
					<li><html:link action="/createDocument" >Crear documento</html:link></li>
					<li><html:link action="/goToListDocument" >Lista de documentos</html:link></li>
					<li><html:link action="/goToReferenceDocumentABM" >Documentos de consulta</html:link></li>
					<li><html:link action="/goToCategoryABM" >Categor&iacute;as de Documentos de consulta</html:link></li>
					<li><html:link action="/goToDelegateStats" >Panel de Control</html:link></li>
				</ul>
			</li>
			<li><a class="trigger" href="#">Administraci&oacute;n de Systema</a>
				<ul>
					<li><html:link action="/editEmail?id=newpassword" >Email Nueva Password</html:link></li>
					<li><html:link action="/editEmail?id=passworreset" >Email Blanqueo de password</html:link></li>
					<li><html:link action="/editEmail?id=newversion" >Email Nueva Versi&oacute;n</html:link></li>
					<li><html:link action="/editEmail?id=newobservation" >Email Nueva Observaci&oacute;n</html:link></li>
					<li><html:link action="/editProperty?id=simon.server.name" >Nombre del server</html:link></li>
					<li><html:link action="/editProperty?id=simon.server.url" >URL del server</html:link></li>
					<li><html:link action="/editProperty?id=simon.tmp.subpath" >Subpath temporal</html:link></li>
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
			<li><a class="trigger" href="#">Modo negociación</a>
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
			<!--li><a class="trigger" href="#">Asistente de Dise&ntilde;o</a>
				<ul>
					<li><a href="am_m3_docList.html">Lista de Documentos Consolidados</a></li>
				</ul>
			</li-->
		</ul>
		<br class="clearit">
	</div>
</div>
<% } %>