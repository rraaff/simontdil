<%@ taglib uri="/tags/struts-html" prefix="html" %>
<div id="extraLinks">
	<div id="extraLinkBlock"><a href="http://desa.focalae-acreditacion.mrec.ar/index.php?ac=f" target="_blank"><img src="<%=ResourceBundleCache.get("extraLinks", "OfficialSite")%>"></a></div> 
	<div id="extraLinkBlock"><html:link action="/genericNavigation2" ><img src="<%=ResourceBundleCache.get("extraLinks", "SecurityArrangements")%>"></html:link></div>
	<div id="extraLinkBlock" style="margin-right:0px;"><html:link action="/genericNavigation1" ><img src="<%=ResourceBundleCache.get("extraLinks", "FocalPoints")%>"></html:link></div>
</div>