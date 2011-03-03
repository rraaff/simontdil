<%@ page info="adminHome"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<div id="content">
<html:html>
<table width="980" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center" valign="middle"><%=ResourceBundleCache.get(getServletInfo(), "seleccione")%></td>
	</tr>
</table>
</html:html>
</div>
<%@ include file="includes/footer.jsp" %>