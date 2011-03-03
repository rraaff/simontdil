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
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "administracionDeSistema")%></a>
				<ul>
					<li><html:link action="/editEmail?id=newpassword" ><%=ResourceBundleCache.get("menu", "emailNuevaContraseña")%></html:link></li>
					<li><html:link action="/editEmail?id=passworreset" ><%=ResourceBundleCache.get("menu", "emailBlanqueoDeContraseña")%></html:link></li>
					<li><html:link action="/editEmail?id=newversion" ><%=ResourceBundleCache.get("menu", "emailNuevaVersion")%></html:link></li>
					<li><html:link action="/editEmail?id=newobservation" ><%=ResourceBundleCache.get("menu", "emailNuevaObservacion")%></html:link></li>
					<li><html:link action="/editProperty?id=simon.server.name" ><%=ResourceBundleCache.get("menu", "nombreServidor")%></html:link></li>
					<li><html:link action="/editProperty?id=simon.server.url" ><%=ResourceBundleCache.get("menu", "urlServidor")%></html:link></li>
					<li><html:link action="/editProperty?id=simon.tmp.subpath" ><%=ResourceBundleCache.get("menu", "subpathTemporal")%></html:link></li>
					<li><html:link action="/goToResourceBundle" ><%=ResourceBundleCache.get("menu", "textosDelSitio")%></html:link></li>
				</ul>
			</li>
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "administracionDeUsuarios")%></a>
				<ul>
					<li><a href="adminHome.jsp"><%=ResourceBundleCache.get("menu", "inicio")%></a></li>
					<li><html:link action="/goToCountryABM" ><%=ResourceBundleCache.get("menu", "administrarDelegaciones")%></html:link></li>
					<li><html:link action="/goToDelegateABM" ><%=ResourceBundleCache.get("menu", "administrarDelegados")%></html:link></li>
					<li><html:link action="/goToSystemUserABM" ><%=ResourceBundleCache.get("menu", "administrarUsuarios")%></html:link></li>
					<li><html:link action="/goToResetPassword" ><%=ResourceBundleCache.get("menu", "blanqueoDeContraseñas")%></html:link></li>
				</ul>
			</li>
			<% } %>
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "administracionDeDocumentos")%></a>
				<ul>
					<li><html:link action="/goToModeratorHome" ><%=ResourceBundleCache.get("menu", "inicioDocumentos")%></html:link></li>
					<li><html:link action="/createDocument" ><%=ResourceBundleCache.get("menu", "crearDocumento")%></html:link></li>
					<li><html:link action="/goToListDocument" ><%=ResourceBundleCache.get("menu", "listaDocumentos")%></html:link></li>
					<li><html:link action="/goToReferenceDocumentABM" ><%=ResourceBundleCache.get("menu", "documentosDeConsulta")%></html:link></li>
					<li><html:link action="/goToCategoryABM" ><%=ResourceBundleCache.get("menu", "categorias")%></html:link></li>
					<li><html:link action="/goToDelegateStats" ><%=ResourceBundleCache.get("menu", "panelDeControl")%></html:link></li>
				</ul>
			</li>
			<% if (isDesigner) { %>
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "diseñador")%></a>
				<ul>
					<li><html:link action="/goToListDocumentForDesign" ><%=ResourceBundleCache.get("menu", "homeDiseñador")%></html:link></li>
				</ul>
			</li>
			<% } %>
			<% if (isTranslator) { %>
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "traductor")%></a>
				<ul>
					<li><html:link action="/goToTranslatorHome" ><%=ResourceBundleCache.get("menu", "homeTraductor")%></html:link></li>
				</ul>
			</li>
			<% } %>
			<% if (isAsisstant) { %>
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "asistente")%></a>
				<ul>
					<li><html:link action="/goToAssistantHome" ><%=ResourceBundleCache.get("menu", "homeAsistente")%></html:link></li>
				</ul>
			</li>
			<% } %>
			<% if (isAdministrator) { %>
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "modoNegociacion")%></a>
				<ul>
				<% 	if(eventMode) { %>
					<li><html:link action="setModSiteNormal" ><%=ResourceBundleCache.get("menu", "desactivarModoNegociacion")%></html:link></li>
				<% } else { %>
					<li><html:link action="setModSiteEvent" ><%=ResourceBundleCache.get("menu", "activarModoNegociacion")%></html:link></li>
				<% } %>
				</ul>
			</li>
			<li><a class="trigger" href="#"><%=ResourceBundleCache.get("menu", "pantallaPublica")%></a>
				<ul>
					<li><html:link action="setPublicSiteNormal" ><%=ResourceBundleCache.get("menu", "desactivarPantallaPublica")%></html:link></li>
					<li><html:link action="setPublicSiteEvent" ><%=ResourceBundleCache.get("menu", "activarPantallaPublica")%></html:link></li>
				</ul>
			</li>
			<% } %>
		</ul>
		<br class="clearit">
	</div>
</div>
<% } %>