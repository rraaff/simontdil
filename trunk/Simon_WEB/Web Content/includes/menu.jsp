<%@ taglib uri="/tags/struts-html" prefix="html" %>

<!--  Rsto no iria en la final  -->
<html:link action="/goToSelectPrincipalDocument" >Seleccionar documento principal</html:link><br><br>
<!--  Esto es de moderador  -->
<html:link action="/createDocument" >Crear documento</html:link><br><br>
<html:link action="/goToListDocument" >Ir al listado de documentos</html:link><br>

<!-- Esto es de admin -->
<html:link action="setModSiteNormal" >Mod site normal</html:link><br>
<html:link action="setModSiteEvent" >Mod site event</html:link><br>