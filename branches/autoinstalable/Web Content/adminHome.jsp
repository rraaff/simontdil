<%@ page info="adminHome"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<%@ include file="includes/leftContent.jsp" %>
<html:html>
		<td width="100%" align="center" valign="middle"><%=ResourceBundleCache.get(getServletInfo(), "seleccione")%></td>
</html:html>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>