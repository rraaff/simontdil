<%@ page info="resourceBundle"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<style type="text/css">
/* hide from incapable browsers */
div#main {
	background-color:#FFFFFF;
	# width:100%;
	* width:100%;
	width:inherit;
	height:480px;
	font-size: 10px;
}
div#scrollbar {
	display:none;
}
.editable:hover	{ background:#eee; }
.textarea textarea{ height:200px; padding:3px; }
.editable-empty	{ background:#fffea1; padding:20px; border:1px dashed #fc0; }
.box		{ border:1px solid #ccc; padding:5px; display:block; width:95%; }
</style>
<script src="scripts/dw_event.js" type="text/javascript"></script>
<script src="scripts/dw_scroll.js" type="text/javascript"></script>
<script src="scripts/dw_scrollbar.js" type="text/javascript"></script>
<script src="scripts/scroll_controls.js" type="text/javascript"></script>
<script type="text/javascript">
//once the dom is ready
window.addEvent('domready', function() {
	//find the editable areas
	$$('.editable').each(function(el) {
		//add double-click and blur events
		el.addEvent('dblclick',function() {
			//store "before" message
			var before = el.get('text').trim();
			//erase current
			el.set('text','');
			//replace current text/content with input or textarea element
			if(el.hasClass('textarea'))
			{
				var input = new Element('textarea', { 'class':'box', 'text':before });
			}
			else
			{
				var input = new Element('input', { 'class':'box', 'value':before });
				//blur input when they press "Enter"
				input.addEvent('keydown', function(e) { if(e.key == 'enter') { this.fireEvent('blur'); } });
			}
			input.inject(el).select();
			//add blur event to input
			input.addEvent('blur', function() {
				//get value, place it in original element
				val = input.get('value').trim();
				//save respective record
				el.set('text',val).addClass(val != '' ? '' : 'editable-empty');
				var jsonRequest = new Request.JSON({url: '<html:rewrite page="/saveResourceBundleAjax.st"/>', onSuccess: function(json, responseText){
					var errorResult = json.error;
					if ('notLogged' == errorResult) {
						window.location='<html:rewrite page="/login.jsp"/>';
						return;
					}
			    	var result = json.result;
				   if ('OK' != result) {
				   	el.set('text',json.rbValue);
				   	var error = json.error;
				   	showErrorMessage();
				   }
				}}).post({'rbLanguage': el.get('rbLanguage'),'rbContext': el.get('rbContext'), 'rbKey': el.get('rbKey'), 'rbValue':el.get('text')});
			});
		});
	});
});

function showErrorMessage() {
		notimooErrorManager.show({
			title: '<%=ResourceBundleCache.get("ventanaError", "titulo")%>',
			message: '<%=ResourceBundleCache.get(getServletInfo(), "errorGrabar")%>',
			customClass:'alert_error',
			 sticky: true
		});
	}
</script>
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
<html:form method="POST" action="/saveResourceBundle">
			<!-- inicio tabla template -->
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="500" align="center" valign="top">
						<!-- corte tabla template -->
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="12" height="75"><img src="images/null.gif" width="1" height="75"></td>
							</tr>
							<tr>
								<td width="150"><%=ResourceBundleCache.get(getServletInfo(), "lenguage")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td><html:select property="rbLanguage" styleClass="textfield_effect_300">
							        <html:optionsCollection name="ResourceBundleForm" property="allLanguage" value="language" label="language"/>
									</html:select>
								</td>
								<td width="150"><%=ResourceBundleCache.get(getServletInfo(), "contexto")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td><html:select property="rbContext" styleClass="textfield_effect_300">
							        <html:optionsCollection name="ResourceBundleForm" property="allContext" value="context" label="context"/>
									</html:select>
								</td>
								<td width="20"><img src="images/null.gif" width="20" height="1"></td>
								<td width="150"><%=ResourceBundleCache.get(getServletInfo(), "traduccion")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td><html:text name="ResourceBundleForm" property="rbSearchValue" styleClass="textfield_effect"/></td>
								<td width="20"><img src="images/null.gif" width="20" height="1"></td>
								<td><html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "buscar")%>
									</html:submit></td>
							</tr>
							<tr>
								<td colspan="12" height="10"><img src="images/null.gif" width="1" height="10"></td>
							</tr>
							<tr>
								<td colspan="12" align="center"><i><%=ResourceBundleCache.get(getServletInfo(), "ayuda")%></i></td>
							</tr>
							<tr>
								<td colspan="12" height="10"><img src="images/null.gif" width="1" height="10"></td>
							</tr>
							<tr>
								<td colspan="12" height="25" align="center">
									<table width="100%" border="0">
										<tr class="d1">
											<td><%=ResourceBundleCache.get(getServletInfo(), "lenguage")%></td>
											<td><%=ResourceBundleCache.get(getServletInfo(), "contexto")%></td>
											<td><%=ResourceBundleCache.get(getServletInfo(), "clave")%></td>
											<td><%=ResourceBundleCache.get(getServletInfo(), "traduccion")%></td>
										</tr> 
										<logic:iterate name="ResourceBundleForm" property="searchResult" id="iterResourceBundle" indexId="iterIndex"> 
											<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
												<td><bean:write name="iterResourceBundle" property="rbLanguage" /></td>
												<td><bean:write name="iterResourceBundle" property="rbContext" /></td>
												<td><bean:write name="iterResourceBundle" property="rbKey" /></td>
												<td><p class="editable textarea" rbLanguage="<bean:write name="iterResourceBundle" property="rbLanguage" />" rbContext="<bean:write name="iterResourceBundle" property="rbContext" />" rbKey="<bean:write name="iterResourceBundle" property="rbKey" />"><bean:write name="iterResourceBundle" property="rbValue" /></p></td>
											</tr> 
										</logic:iterate>
									</table>
								</td>
							</tr>
						</table>					
						<!-- corte tabla template -->
					</td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" background="images/interfaces/bottomLeft.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
					<td background="images/interfaces/bottomCenter.gif" width="320" height="10"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" background="images/interfaces/bottomRight.gif" width="10" height="10"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
</html:form>
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>