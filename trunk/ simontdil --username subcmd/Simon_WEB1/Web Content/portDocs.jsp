<%@ page info="portDocs"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<%if (isModerator) { %>
	<%@ include file="includes/menu.jsp" %>
<% } %>
<style type="text/css">
/* hide from incapable browsers */
div#main {
	background-color:#FFFFFF;
	width:900px;
	height:460px;
	font-size:10px;
}
div#scrollbar {
	display:none;
}
</style>

<div id="content">
<table height="560" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="940">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="920" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Documentos em português</div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="900" height="460">
						<div id="main">
							<div id="lyr1">
								<table width="900" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="2" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<tr>
										<td height="20" align="left">Título</td>
										<td width="80"><img src="images/null.gif" width="1" height="1"></td>
									</tr> 
									<tr class="d0">
										<td height="28" align="left">PROJETO PROGRAMA DE A&Ccedil;&Atilde;O DE MAR DE PLATA</td>
										<td><a href="other/Programa-de-Accion-P-Version-1.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr> 
									<tr class="d1">
										<td height="28" align="left">PROJETO DECLARA&Ccedil;&Atilde;O DE MAR DEL PLATA</td>
										<td><a href="other/Proy-Dec-XX-Cumbre-Ibero-P-Version1.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr> 
									<tr class="d0">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE A &ldquo;PROMO&Ccedil;&Atilde;O DE INVESTIMENTOS&rdquo; (Proposta Argentina)</td>
										<td><a href="other/Proy-Comunicado-Esp-Inversiones-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr> 
									<tr class="d1">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE A &quot;QUEST&Atilde;O DAS ILHAS MALVINAS&quot; (Proposta Argentina)</td>
										<td><a href="other/Proy-Comunicado-Esp-Malvinas-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE A NECESSIDADE DE P&Ocirc;R FIM AO BLOQUEIO (Proposta Cuba)</td>
										<td><a href="other/Proy-Comunicado-Esp-Bloqueo-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE O &ldquo;QHAPAQ &Ntilde;AN&rdquo;  (Proposta Argentina em nome de Bol&iacute;via, Chile, Colombia, Ecuador e Per&uacute;)</td>
										<td><a href="other/Proy-Comunicado-Esp-Qhapaq-Nan-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL DE APOIO &Agrave; LUTA CONTRA O  TERRORISMO (Proposta Cuba)</td>
										<td><a href="other/Proy-Comunicado-Esp-Terrorismo-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr>
										<td colspan="2" height="25"><img src="images/null.gif" width="1" height="25"></td>
									</tr>
								</table>
							</div>
						</div>
					</td>
					<td width="30" align="right">
					<!--div id="scrollbar" align="right">
						<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track">
							<div id="dragBar"></div>
						</div>
						<div id="down"><a class="mouseover_down" href=""><img src="images/btn-dn.gif" width="11" height="11" alt="" border="0" /></a></div>
					</div--></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomLeft.gif"><img src="images/null.gif" width="10" height="10"></td>
					<td colspan="2" height="10" background="images/interfaces/bottomCenter.gif"><img src="images/null.gif" width="1" height="10"></td>
					<td colspan="2" width="10" height="10" background="images/interfaces/bottomRight.gif"><img src="images/null.gif" width="10" height="10"></td>
				</tr>
				<tr>
					<% if (isDelegate) { %>
						<td colspan="6" height="30" align="center" valign="bottom"><html:link action="/goToDelegateHome" ><img src="images/buttons/volver.png" width="50" height="24" border="0" /></html:link></td>
					<% } %>
					<% if (isModerator) { %>
						<td colspan="6" height="30" align="center" valign="bottom"><html:link action="/goToModeratorHome" ><img src="images/buttons/volver.png" width="50" height="24" border="0" /></html:link></td>
					<% } %>
				</tr>
			</table>
		<!-- fin tabla template -->
		</td>
	</tr>
</table>
</div>
<%@ include file="includes/footer.jsp" %>