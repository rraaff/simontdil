<%@ page info="moderatorHome"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<style type="text/css">
/* ### New Homes ### */
#newHomeBlock{
	border:1px solid #c6c6c6;
	width:1053px;
	height:200px;
	font-family:"Trebuchet MS", Tahoma, Verdana, Arial;
	overflow:auto;
}
#newHomeBlock a, #newHomeBlock a:hover, #newHomeBlock visited{
	font-size:12px;
	color:#888888;
}
#newHomeBlock .newHomeTitle{
	font-size:16px;
	color:#666;
}
#newHomeBlock .mainDocsCategory{
	font-size:14px;
	color:#888888;
}
#newHomeBlock .mainDocsSubCategory{
	font-size:17px;
	color:#ff004e;
}
#newHomeBlock .mainDocsName{
	font-size:12px;
	color:#888888;
}
#newHomeBlock .mainDocsData{
	font-size:11px;
	color:#888888;
}
#newHomeBlockHalf{
	border:1px solid #c6c6c6;
	width:100%;
<logic:equal name="ModeratorHome" property="hasPrincipalVersions" value="true">
	height:300px;
</logic:equal>
<logic:notEqual name="ModeratorHome" property="hasPrincipalVersions" value="true">
	height:500px;
</logic:notEqual>
	font-family:"Trebuchet MS", Tahoma, Verdana, Arial;
	float: left;
}
#newHomeBlockHalf .title{
	font-size:16px;
	color:#888888;
}
.categoryTD{
	height:44px;
	color:#666;
	font-family:"Trebuchet MS", Tahoma, Verdana, Arial;
	font-size:12px;
}
#documentTD{
	height:29px;
	color:#454545;
	font-family:"Trebuchet MS", Tahoma, Verdana, Arial;
	font-size:12px;
	padding-top: 15px;
}
#documentTD a, #documentTD a:hover, #documentTD visited {
	color:#454545;
}
</style>
<script type="text/javascript">
	function toggleChild(parent) {
		var child = document.getElementById("child-" + parent);
		if (child.style.display == 'none') {
			document.getElementById("f-" + parent).src="images/icons/folderopen.gif";
			document.getElementById("p-" + parent).src="images/icons/minus.gif";
			child.style.display = 'block';
		} else {
			document.getElementById("f-" + parent).src="images/icons/folder.gif";
			document.getElementById("p-" + parent).src="images/icons/plus.gif";
			child.style.display = 'none';
		}
	}
</script>
<%@ include file="includes/menu.jsp" %>
<%@ include file="includes/leftContentModerator.jsp" %>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
		<logic:equal name="ModeratorHome" property="hasPrincipalVersions" value="true">
			<tr>
				<td colspan="3"><img src="images/null.gif" width="1" height="20"></td>
			</tr>
			<tr>
				<td colspan="3">
					<div id="newHomeBlock">
						<table width="95%" cellspacing="0" cellpadding="15" align="center">
							<tr>
								<td><span class="newHomeTitle"><%=ResourceBundleCache.get(getServletInfo(), "Documentos")%></span></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
										<tr>
											<logic:iterate name="ModeratorHome" property="principalVersions" id="doc" indexId="principalIndex">
												<td>
													<table width="370" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td width="74"><img src="images/icons/documento.gif" width="64" height="76"></td>
															<td><span class="mainDocsCategory"><bean:write name="doc" property="documentTypeName" /></span><br/>
																<span class="mainDocsSubCategory"><bean:write name="doc" property="documentSubTypeName" /></span><br/>
																<span class="mainDocsName"><html:link  action="/goToViewVersion.st?" paramName="doc" paramProperty="id" paramId="versionID"><bean:write name="doc" property="documentTitle" /></html:link></span><br/>
																<span class="mainDocsData"><%=ResourceBundleCache.get(getServletInfo(), "version")%>: <bean:write name="doc" property="number" /> - <bean:write name="doc" property="name" /></span></td>
														</tr>
													</table>
												</td>
											</logic:iterate>
										</tr>
									</table>
								</td>
							</tr>
							
						</table>
					</div>
				</td>
			</tr>
		</logic:equal>
		<tr>
			<td colspan="3"><img src="images/null.gif" width="1" height="20"></td>
		</tr>
		<tr>
			<td width="50%">
				<div id="newHomeBlockHalf">
					<table width="98%" border="0" cellspacing="0" cellpadding="10" align="center">
						<tr>
							<td><span class="title"><%=ResourceBundleCache.get(getServletInfo(), "documentosEspecificos")%></span></td>
						</tr>
						<tr>
							<td valign="top">
								<logic:equal name="ModeratorHome" property="hasPrincipalVersions" value="true">
									<div style="width:100%; height:220px; overflow:auto;">
								</logic:equal>
								<logic:notEqual name="ModeratorHome" property="hasPrincipalVersions" value="true">
									<div style="width:100%; height:440px; overflow:auto;">
								</logic:notEqual>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<logic:iterate name="ModeratorHome" property="otherDocumentsTree" id="docTree">
										<tr>
											<td background="images/icons/line.gif" onclick="toggleChild('<bean:write name="docTree" property="id"/>')"><img id="p-<bean:write name="docTree" property="id"/>" src="images/icons/minus.gif"></td>
											<td onclick="toggleChild('<bean:write name="docTree" property="id"/>')" width="44"><img id="f-<bean:write name="docTree" property="id"/>" src="images/icons/folder.gif"></td>
											<td width="100%" onclick="toggleChild('<bean:write name="docTree" property="id"/>')" class="categoryTD"><bean:write name="docTree" property="name"/></td>
										</tr>
										<tr>
											<td background="images/icons/line.gif"></td>
											<td></td>
											<td colspan="1">
												<div id="child-<bean:write name="docTree" property="id"/>" style="display: block;">
													<table border="0" cellpadding="0" cellspacing="0">
														<logic:iterate name="docTree" property="documents" id="docLeafTop">
														<tr>
															<td width="50" align="center"><img src="images/icons/leaf.gif"></td>
															<td><div id="documentTD"><html:link action="/goToViewLastVersionOfDocument.st?" paramName="docLeafTop" paramProperty="id" paramId="documentID">
																<bean:write name="docLeafTop" property="title" /> - <%=ResourceBundleCache.get(getServletInfo(), "version")%> <bean:write name="docLeafTop" property="lastVersionNumber" /> - <bean:write name="docLeafTop" property="lastVersionName" />
																</html:link></div></td>
														</tr>
														</logic:iterate>
													</table>
													<table border="0" cellpadding="0" cellspacing="0">
														<logic:iterate name="docTree" property="documentSubTypes" id="docSubTypeTree">
															<tr>
																<td background="images/icons/line.gif" onclick="toggleChild('<bean:write name="docSubTypeTree" property="id"/>')"><img id="p-<bean:write name="docSubTypeTree" property="id"/>" src="images/icons/minus.gif"></td>
																<td onclick="toggleChild('<bean:write name="docSubTypeTree" property="id"/>')" width="44"><img id="f-<bean:write name="docSubTypeTree" property="id"/>" src="images/icons/folder.gif"></td>
																<td onclick="toggleChild('<bean:write name="docSubTypeTree" property="id"/>')" class="categoryTD"><bean:write name="docSubTypeTree" property="name"/></td>
															</tr>
															<tr>
																<td background="images/icons/line.gif"></td>
																<td></td>
																<td colspan="1">
																	<div id="child-<bean:write name="docSubTypeTree" property="id"/>" style="display: block;">
																		<table border="0" cellpadding="0" cellspacing="0">
																			<logic:iterate name="docSubTypeTree" property="documents" id="docLeaf">
																			<tr>
																				<td width="50" align="center"><img src="images/icons/leaf.gif"></td>
																				<td><div id="documentTD"><html:link action="/goToViewLastVersionOfDocument.st?" paramName="docLeaf" paramProperty="id" paramId="documentID">
																					<bean:write name="docLeaf" property="title" /> - <%=ResourceBundleCache.get(getServletInfo(), "version")%> <bean:write name="docLeaf" property="lastVersionNumber" /> - <bean:write name="docLeaf" property="lastVersionName" />
																					</html:link></div></td>
																			</tr>
																			</logic:iterate>
																		</table>
																	</div>
																</td>
															</tr>
														</logic:iterate>
													</table>
												</div>
											</td>
										</tr>
									</logic:iterate>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
			<td><img src="images/null.gif" width="20" height="1"></td>
			<td width="50%">
				<div id="newHomeBlockHalf">
					<table width="98%" border="0" cellspacing="0" cellpadding="10" align="center">
						<tr>
							<td colspan="3"><span class="title"><%=ResourceBundleCache.get(getServletInfo(), "documentosInformativos")%></span></td>
						</tr>
						<tr>
							<td valign="top">
								<logic:equal name="ModeratorHome" property="hasPrincipalVersions" value="true">
									<div style="width:100%; height:220px; overflow:auto;">
								</logic:equal>
								<logic:notEqual name="ModeratorHome" property="hasPrincipalVersions" value="true">
									<div style="width:100%; height:440px; overflow:auto;">
								</logic:notEqual>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
										<logic:iterate name="ModeratorHome" property="referenceDocumentTree" id="catTree">
											<tr>
												<td background="images/icons/line.gif" onclick="toggleChild('r<bean:write name="catTree" property="id"/>')"><img id="p-r<bean:write name="catTree" property="id"/>" src="images/icons/minus.gif"></td>
												<td onclick="toggleChild('r<bean:write name="catTree" property="id"/>')" width="44"><img id="f-r<bean:write name="catTree" property="id"/>" src="images/icons/folder.gif"></td>
												<td width="100%" onclick="toggleChild('r<bean:write name="catTree" property="id"/>')" class="categoryTD"><bean:write name="catTree" property="name"/></td>
											</tr>
											<tr>
												<td background="images/icons/line.gif"></td>
												<td></td>
												<td colspan="1">
													<div id="child-r<bean:write name="catTree" property="id"/>" style="display: block;">
														<table border="0" cellpadding="0" cellspacing="0">
															<logic:iterate name="catTree" property="documents" id="catLeafTop">
															<tr>
																<td width="50" align="center"><img src="images/icons/leaf.gif"></td>
																<td><div id="documentTD"><a href="./download.do?action=refdoc&fileId=<bean:write name="catLeafTop" property="id" />"><bean:write name="catLeafTop" property="title" /></a></div></td>
															</tr>
															</logic:iterate>
														</table>
														<table border="0" cellpadding="0" cellspacing="0">
															<logic:iterate name="catTree" property="documentSubTypes" id="subCatTree">
																<tr>
																	<td background="images/icons/line.gif" onclick="toggleChild('r<bean:write name="subCatTree" property="id"/>')"><img id="p-r<bean:write name="subCatTree" property="id"/>" src="images/icons/minus.gif"></td>
																	<td onclick="toggleChild('r<bean:write name="subCatTree" property="id"/>')" width="44"><img id="f-r<bean:write name="subCatTree" property="id"/>" src="images/icons/folder.gif"></td>
																	<td onclick="toggleChild('r<bean:write name="subCatTree" property="id"/>')" class="categoryTD"><bean:write name="subCatTree" property="name"/></td>
																</tr>
																<tr>
																	<td background="images/icons/line.gif"></td>
																	<td></td>
																	<td colspan="1">
																		<div id="child-r<bean:write name="subCatTree" property="id"/>" style="display: block;">
																			<table border="0" cellpadding="0" cellspacing="0">
																				<logic:iterate name="subCatTree" property="documents" id="refLeaf">
																				<tr>
																					<td width="50" align="center"><img src="images/icons/leaf.gif"></td>
																					<td><div id="documentTD"><a href="./download.do?action=refdoc&fileId=<bean:write name="refLeaf" property="id" />"><bean:write name="refLeaf" property="title" /></a></div></td>
																				</tr>
																				</logic:iterate>
																			</table>
																		</div>
																	</td>
																</tr>
															</logic:iterate>
														</table>
													</div>
												</td>
											</tr>
										</logic:iterate>
									</table>	
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>