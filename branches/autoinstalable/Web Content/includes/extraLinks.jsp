<%@ taglib uri="/tags/struts-html" prefix="html" %>
<div id="extraLinks">
	<div id="extraLinkBlock"><!-- ESTE ES UN LINK A >  <a href="http://desa.focalae-acreditacion.mrec.ar/index.php?ac=f">< % =ResourceBundleCache.get("extraLinks", "OfficialSite") % ></a>--><html:link action="/genericNavigation3" ><%=ResourceBundleCache.get("extraLinks", "OfficialSite")%></html:link></div> 
	<div id="extraLinkBlock"><html:link action="/genericNavigation2" ><%=ResourceBundleCache.get("extraLinks", "SecurityArrangements")%></html:link></div>
	<div id="extraLinkBlock" style="margin-right:0px;"><html:link action="/genericNavigation1" ><%=ResourceBundleCache.get("extraLinks", "FocalPoints")%></html:link></div>
</div>





