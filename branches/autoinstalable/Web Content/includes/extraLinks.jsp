<%@ taglib uri="/tags/struts-html" prefix="html" %>
<div id="extraLinks">
	<div id="extraLinkBlock"><%=com.tdil.simon.web.SystemConfig.getExternalLink1()%><img src="<%=ResourceBundleCache.get("extraLinks", "OfficialSite")%>" border="0"></a></div>
	<div id="extraLinkBlock"><html:link action="/genericNavigation1" ><img src="<%=ResourceBundleCache.get("extraLinks", "FocalPoints")%>" border="0"></html:link></div>
	<div id="extraLinkBlock" style="margin-right:0px;"><html:link action="/genericNavigation3" ><img src="<%=ResourceBundleCache.get("extraLinks", "LogisticalInformation")%>" border="0"></html:link></div>
</div>