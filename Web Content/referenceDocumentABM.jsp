<%@ page info="referenceDocumentABM"%>
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
	height:440px;
	font-size: 10px;
}
div#scrollbar {
	display:none;
}
</style>
<script src="scripts/dw_event.js" type="text/javascript"></script>
<script src="scripts/dw_scroll.js" type="text/javascript"></script>
<script src="scripts/dw_scrollbar.js" type="text/javascript"></script>
<script src="scripts/scroll_controls.js" type="text/javascript"></script>
<script type="text/javascript">
function init_dw_Scroll() {
    var wndo = new dw_scrollObj('main', 'lyr1');
    wndo.setUpScrollbar("dragBar", "track", "v", 1, 1);
    wndo.setUpScrollControls('scrollbar');
}

// if code supported, link in the style sheet and call the init function onload
if ( dw_scrollObj.isSupported() ) {
    dw_Util.writeStyleSheet('styles/scrollbar_demo.css')
    dw_Event.add( window, 'load', init_dw_Scroll);
}
</script>
<%@ include file="includes/leftContent.jsp" %>
<td width="100%">
<html:form method="POST" action="/referenceDocumentABM" enctype="multipart/form-data">
<input type="hidden" name="indexOperation" value=""/>
<input type="hidden" name="indexClicked"/>
<table width="95%" height="560" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="35%">
			<!-- inicio tabla template -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" background="images/interfaces/topTitle.gif" align="left"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "titulo")%></div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td height="460" valign="top">
					<!-- corte tabla template -->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3" height="25"><img src="images/null.gif" width="1" height="25"></td>
							</tr>
							<tr>
								<td width="103" align="right"><%=ResourceBundleCache.get(getServletInfo(), "tituloDocumento")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="240" align="left"><html:text name="ReferenceDocumentABMForm" property="title" styleClass="textfield_effect"/><html:errors property="refDoc.title" /></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="103" align="right"><%=ResourceBundleCache.get(getServletInfo(), "elegirArchivo")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="240" align="left"><html:file property="document" styleClass="textfield_effect"/><html:errors property="refDoc.document" /></td>
							</tr>
							<tr>
								<td colspan="3" height="11"><img src="images/null.gif" width="1" height="11"></td>
							</tr>
							<tr>
								<td width="103" align="right"><%=ResourceBundleCache.get(getServletInfo(), "subCategoria")%>:</td>
								<td width="7"><img src="images/null.gif" width="7" height="1"></td>
								<td width="240" align="left">
									<html:select name="ReferenceDocumentABMForm" property="subCategoryId" styleClass="textfield_effect">
										<% String lastCategory = "";%>
										<logic:iterate name="ReferenceDocumentABMForm" property="allSubCategories" id="iterSubCat"> 
											<% com.tdil.simon.data.valueobjects.SubCategoryVO sub = (com.tdil.simon.data.valueobjects.SubCategoryVO)iterSubCat; %>
											<% if (!sub.getCategoryName().equals(lastCategory)) { %>
												<optgroup label="<%=sub.getCategoryName()%>"></optgroup>
											<% lastCategory = sub.getCategoryName();
											} %>
											<option value="<%=sub.getId()%>"><%=sub.getName()%></option>
										</logic:iterate>
									</html:select><html:errors property="refDoc.category" /></td>
							</tr>
							<tr>
								<td colspan="3" height="30"><img src="images/null.gif" width="1" height="30"></td>
							</tr>
							<tr>
								<td colspan="3" height="25" align="center">
									<logic:equal name="ReferenceDocumentABMForm" property="id" value="0">
										<html:submit property="operation">
											<%=ResourceBundleCache.get(getServletInfo(), "crear")%>
										</html:submit>
									</logic:equal>
										
									<logic:notEqual name="ReferenceDocumentABMForm" property="id" value="0">
										<html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "modificar")%>
										</html:submit>
									</logic:notEqual>
									
									<html:submit property="operation">
										<%=ResourceBundleCache.get(getServletInfo(), "cancelar")%>
									</html:submit>
								</td>
							</tr>
							<tr>
								<td colspan="3" height="16"><img src="images/null.gif" width="1" height="16"></td>
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
		</td>
		<td width="20"><img src="images/null.gif" width="20" height="1"></td>
		<td width="65%">
			<!-- inicio tabla template -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td width="10" height="19" colspan="2" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td width="100%" height="19" align="left" colspan="2" background="images/interfaces/topTitle.gif"><div id="blockTitle"><%=ResourceBundleCache.get(getServletInfo(), "tituloListado")%></div></td>
					<td width="10" height="19" colspan="2" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="100%" height="460" valign="top">
					<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1" style="width:inherit;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="5" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<tr>
										<td height="20" align="left"><%=ResourceBundleCache.get(getServletInfo(), "tituloDocumento")%></td>
										<td align="left"><%=ResourceBundleCache.get(getServletInfo(), "archivo")%></td>
										<td align="left"><%=ResourceBundleCache.get(getServletInfo(), "categoria")%></td>
										<td align="left"><%=ResourceBundleCache.get(getServletInfo(), "subCategoria")%></td>
										<td width="60"> </td>
										<td width="60"> </td>
									</tr> 
									<logic:iterate name="ReferenceDocumentABMForm" property="allReferenceDocuments" id="iterRefDoc" indexId="iterIndex"> 
										<tr class="<%= (iterIndex % 2 == 0) ? "d0" : "d1" %>">
											<td <%= ((com.tdil.simon.data.model.PersistentObject)iterRefDoc).isDeleted() ? "class=\"notActive\"" : "" %> height="28" align="left"><bean:write name="iterRefDoc" property="title" /></td>
											<td <%= ((com.tdil.simon.data.model.PersistentObject)iterRefDoc).isDeleted() ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterRefDoc" property="fileName" /></td>
											<td <%= ((com.tdil.simon.data.model.PersistentObject)iterRefDoc).isDeleted() ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterRefDoc" property="categoryName" /></td>
											<td <%= ((com.tdil.simon.data.model.PersistentObject)iterRefDoc).isDeleted() ? "class=\"notActive\"" : "" %> align="left"><bean:write name="iterRefDoc" property="subCategoryName" /></td>
											<td><html:link  action="editReferenceDocument.st?" paramName="iterRefDoc" paramProperty="id" paramId="id">
												<%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","editar")%>
											</html:link>
											<td><logic:equal name="iterRefDoc" property="deleted" value="false">
												<%=com.tdil.simon.web.ButtonGenerator.getIndexedButton("ReferenceDocumentABMForm","botones","desactivar", iterIndex)%>
											</logic:equal>
											<logic:equal name="iterRefDoc" property="deleted" value="true">
												<%=com.tdil.simon.web.ButtonGenerator.getIndexedButton("ReferenceDocumentABMForm","botones","activar", iterIndex)%>
											</logic:equal></td>
										</tr> 
									</logic:iterate>
									<tr>
										<td colspan="5" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
								</table>
							<!-- corte tabla template -->
							</div>
						</div>
					</td>
					<td width="30" align="right">
					<div id="scrollbar" style="width:20px; height:430px; float:right;">
					<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track" style="height:406px;">
							<div id="dragBar"></div>
						</div>
						<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
					</div></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
</table>
</html:form>
</td>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>