<%@ page info="genericPage1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<style type="text/css">
/* hide from incapable browsers */
div#sizer {
	display:none;
	position: relative;
	float: right;
	padding-top: 10px;
	padding-right: 10px;
}  
/* breathing room between images in sizer */
div#sizer img { margin-right:3px; }

div#scrollbar {
	display:none;
}
#content #alcincuentaRight{
	width:72%;
	height:530px;
	padding-left:10px;
	padding-right:10px;
	margin-top:12px;
/*	#width: 400px; Para Internet Explorer */
/*	_width: 400px; Para Internet Explorer 6 */
}
div#main{
/*	#width: 400px; Para Internet Explorer */
/*	_width: 400px; Para Internet Explorer 6 */
	width:95%;
	height:450px;
	float:left;
	margin: 0px;
	position: relative;
}
#mainDocContainer {
	border: 1px solid #c6c6c6;
	position: relative;
	width:100%;
	height:230px;
	text-align:center;
}

#mainDocContainer #leftObject {
	/*background-color:#00FF00;*/
	width:100%;
	height:30px;
	text-align:center;
	padding-top:10px;
	float:left;
}
lyr1{
	
}
#content #portaVersiones a {
	color: #FFFFFF;
}
#content #portaVersiones {
	width:130px;
}
#versionStrong {
	font-size: 14px;
	color: #FFFFFF;
	background-color: #EF8A1B;
	height: 20px;
	width: 20px;
	text-align: center;
}
#blockTitle1 {
	background-color: #FFFFFF;
	width: auto;
	position: relative;
	height: 15px;
	float: left;
	padding-top: 4px;
	padding-right: 4px;
	padding-bottom: 0px;
	padding-left: 4px;
	left: 10px;
	top: -15px;
}
#blockTitle2 {
	background-color: #FFFFFF;
	width: auto;
	height: 15px;
	padding-right:4px;
	padding-left:4px;
	padding-top:4px;
	float: left;
	position: relative;
	top: -15px;
	left: 10px;
}
#documentContainerNew {
	border: 1px solid #c6c6c6;
}
</style>
<script src="scripts/dw_event.js" type="text/javascript"></script>
<script src="scripts/dw_cookies.js" type="text/javascript"></script>
<script src="scripts/dw_sizerdx.js" type="text/javascript"></script>
<script type="text/javascript">

dw_fontSizerDX.setDefaults("px", 13, 9, 26, ['div#main p.article'] );


dw_Event.add( window, 'load', dw_fontSizerDX.init );

</script>
<% if (isDelegate) { %>
<script type="text/javascript">
	
	function getDelegateSiteStatus() {
		var jsonRequest = new Request.JSON({url: '<html:rewrite page="/getDelegateSiteStatus.st"/>', onSuccess: function(json, responseText){
			var errorResult = json.error;
			if ('notLogged' == errorResult) {
				window.location='<html:rewrite page="/login.jsp"/>';
				return;
			}
		    var sitestatus = json.sitestatus;
			if (sitestatus != 'NORMAL') {
				window.location='<html:rewrite page="/goToDelegateNegotiation.st"/>';
				return;
			}
		}}).get();
	}
	timer = setInterval("getDelegateSiteStatus()",1000);

</script>
<% } %>
<%if (isModerator) { %>
<%@ include file="includes/menu.jsp" %>
<% } %>
Aca esta la pagina 1
<%@ include file="includes/footer.jsp" %>