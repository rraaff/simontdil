<%@ taglib uri="/tags/struts-html" prefix="html" %>
<div id="amMenu">
	<div id="menuwrapper">
		<ul id="p7menubar">
			<li><a class="trigger" href="#">Usuario t&eacute;cnico</a>
				<ul>
					<li><a href="am_abm_country.html">ABM de Delegaciones</a></li>
					<li><a href="am_abm_deputy.html">ABM de Delegados</a></li>
					<li><a href="am_abm_sistemUsers.html">ABM de Usuarios del sistema</a></li>
					<li><a href="am_amb_passwords.html">Banqu&eacute;o de Contrase&ntilde;as</a></li>
				</ul>
			</li>
			<li><a class="trigger" href="#">Administrador Moderador</a>
				<ul>
				<!--  Rsto no iria en la final  -->
					<li><html:link action="/goToSelectPrincipalDocument" >Seleccionar documento principal</html:link></li>
					<li><html:link action="/goToModeratorHome" >Home de Moderador</html:link></li>
					<li><html:link action="/createDocument" >Crear documento</html:link></li>
					<li><html:link action="/goToListDocument" >Lista de documentos</html:link></li>
					<li><a href="am_abmLibRef.html">ABM de Librer&eacute;a de Referencia</a></li>
					<li><a href="am_abmCatLibRef.html">ABM de Categor&iacute;as de Librer&iacute;a</a></li>
					<li><a href="am_panel.html">Panel de Control</a></li>
					<li><html:link action="setModSiteNormal" >Desactivar Modo Negociación</html:link></li>
					<li><html:link action="setModSiteEvent" >Activar Modo Negociación</html:link></li>
				</ul>
			</li>
			<li><a class="trigger" href="#">Asistente de Dise&ntilde;o</a>
				<ul>
					<li><a href="am_m3_docList.html">Lista de Documentos Consolidados</a></li>
				</ul>
			</li>
		</ul>
		<br class="clearit">
	</div>
</div>