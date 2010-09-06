<%@ page info="searchObservations"%>
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title></title>
	</head>
	<body>
		<html:form method="POST" action="/searchObservations">
			Pais:<html:select name="SearchObservationsForm" property="countryId" styleClass="textfield_effect_month">
				<html:optionsCollection name="SearchObservationsForm" property="allCountries" value="id" label="name"/>
			</html:select>

		<html:radio name="SearchObservationsForm" property="exactDate" value="exact"/><br>
		
		<html:select name="SearchObservationsForm" property="exactDay" styleClass="textfield_effect_day">
	        <html:optionsCollection name="SearchObservationsForm" property="days" value="dayNumber" label="dayNumber"/>
	      </html:select> de <html:select name="SearchObservationsForm" property="exactMonth" styleClass="textfield_effect_month">
	        <html:optionsCollection name="SearchObservationsForm" property="months" value="monthNumber" label="monthText"/>
	      </html:select><br>
		
		<html:radio name="SearchObservationsForm" property="exactDate" value="range"/><br>
		Desde <html:select name="SearchObservationsForm" property="lowerDay" styleClass="textfield_effect_day">
	        <html:optionsCollection name="SearchObservationsForm" property="days" value="dayNumber" label="dayNumber"/>
	      </html:select> de <html:select name="SearchObservationsForm" property="lowerMonth" styleClass="textfield_effect_month">
	        <html:optionsCollection name="SearchObservationsForm" property="months" value="monthNumber" label="monthText"/>
	      </html:select><br>
		Hasta <html:select name="SearchObservationsForm" property="upperDay" styleClass="textfield_effect_day">
	        <html:optionsCollection name="SearchObservationsForm" property="days" value="dayNumber" label="dayNumber"/>
	      </html:select> de <html:select name="SearchObservationsForm" property="upperMonth" styleClass="textfield_effect_month">
	        <html:optionsCollection name="SearchObservationsForm" property="months" value="monthNumber" label="monthText"/>
	      </html:select><br>
	      
	      Parrafo: <html:select name="SearchObservationsForm" property="paragraphNumber" styleClass="textfield_effect_day">
	      	<html:options name="SearchObservationsForm" property="allParagraphs"/>
	      </html:select><br>
	      Palabras<html:text name="SearchObservationsForm" property="words" styleClass="textfield_effect"/><br>
		
			<html:submit property="operation">
				<bean:message key="searchObservations.search"/>
			</html:submit><br>
			
			<html:submit property="operation">
				<bean:message key="searchObservations.back"/>
			</html:submit><br>
		</html:form><br>
	</body>

<%@ include file="includes/footer.jsp" %>