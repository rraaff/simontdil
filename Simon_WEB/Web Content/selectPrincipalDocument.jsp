<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>

<html:html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
    <title>
      Seleccionar documento principal
    </title>
  </head>
  <body>
    <html:form method="POST" action="/selectPrincipalDocument">


      <html:select property="selectedDocument">
        <html:optionsCollection name="SelectPrincipalDocument" property="options" value="id" label="title"/>
      </html:select>
      <br>
      <br>
      <html:submit property="operation">
        <bean:message key="selectPrincipalDocument.save"/>
      </html:submit>
    </html:form>
  </body>
</html:html>

