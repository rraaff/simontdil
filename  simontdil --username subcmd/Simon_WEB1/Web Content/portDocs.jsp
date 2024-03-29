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
	# width:100%;
	* width:100%;
	width:inherit;
	height:470px;
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
<div id="content">
<table height="560" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="940">
			<!-- inicio tabla template -->
			<table width="940" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" width="10" height="19" background="images/interfaces/topLeftTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
					<td colspan="2" width="920" height="19" align="left" background="images/interfaces/topTitle.gif"><div id="blockTitle">Documentos em portugu�s</div></td>
					<td colspan="2" width="10" height="19" background="images/interfaces/topRightTitle.gif"><img src="images/null.gif" width="10" height="19"></td>
				</tr>
				<tr>
					<td width="1" bgcolor="#c6c6c6"><img src="images/null.gif" width="1" height="1"></td>
					<td width="9"><img src="images/null.gif" width="9" height="1"></td>
					<td width="900" height="460">
						<!-- corte tabla template -->
						<div id="main">
							<div id="lyr1" style="width:inherit;">
								<table width="900" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="2" height="11"><img src="images/null.gif" width="1" height="11"></td>
									</tr>
									<tr>
										<td height="20" align="left">T�tulo</td>
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
										<td height="28" align="left">PROJETO DECLARA��O DE MAR DEL PLATA - V3</td>
										<td><a href="other/Proy-Decl-XX-Cumbre-Ibero-P-Version-3.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="dl">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE A &ldquo;PROMO&Ccedil;&Atilde;O DE INVESTIMENTOS&rdquo; (Proposta Argentina)</td>
										<td><a href="other/Proy-Comunicado-Esp-Inversiones-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr> 
									<tr class="d0">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE A &quot;QUEST&Atilde;O DAS ILHAS MALVINAS&quot; (Proposta Argentina)</td>
										<td><a href="other/Proy-Comunicado-Esp-Malvinas-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="dl">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE A NECESSIDADE DE P&Ocirc;R FIM AO BLOQUEIO (Proposta Cuba)</td>
										<td><a href="other/Proy-Comunicado-Esp-Bloqueo-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE O &ldquo;QHAPAQ &Ntilde;AN&rdquo;  (Proposta Argentina em nome de Bol&iacute;via, Chile, Colombia, Ecuador e Per&uacute;)</td>
										<td><a href="other/Proy-Comunicado-Esp-Qhapaq-Nan-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    
                                    <tr class="dl">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE O "QHAPAQ �AN" - V2</td>
										<td><a href="other/Proy-Comunicado-Esp-Qhapaq-Nan--P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    
									<tr class="d0">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL DE APOIO &Agrave; LUTA CONTRA O  TERRORISMO (Proposta Cuba)</td>
										<td><a href="other/Proy-Comunicado-Esp-Terrorismo-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    
                                    <tr class="d1">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE A INICIATIVA YASUNI-ITT</td>
										<td><a href="other/Proy-Comunicado-Esp-Yasuni-ITT-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    
                                    <tr class="d0">
										<td height="28" align="left">PROJETO COMUNICADO ESPECIAL SOBRE DEFESA DA DEMOCRACIA </td>
										<td><a href="other/Proy-Comunicado-Esp-Clausula-Democratica-VERSION-ULTIMA-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    
                                    <!-- tr class="d1">
										<td height="28" align="left">RELAT�RIO DE ACTIVIDADES DA SECRETARIA-GERAL IBERO-AMERICANA</td>
										<td><a href="other/RELSEGIBXX-CNRC2-101125.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr-->
									
								<!--	<tr class="d1">
										<td height="28" align="left">Projeto Agenda Tem&aacute;tica &ndash; respons&aacute;veis de coopera&ccedil;&atilde;o</td>
										<td><a href="other/Agenda-tematica-RC-P-13sept2010.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr> -->
                                    
									<tr class="d1">
										<td height="28" align="left">Relat&oacute;rio de avalia&ccedil;&atilde;o dos Programas Ibero-Americanos</td>
										<td><a href="other/INFORME-DE-VALORACION-DE-LOS-PROGRAMAS-PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Iniciativa Ibero-Americana</td>
										<td><a href="other/INICIATIVA-CASCOS-BLANCOS-PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Programa IBERO-AMERICANO de INOVA&Ccedil;&Atilde;O</td>
										<td><a href="other/PROGRAMA-IBEROAMERICANO-DE-INNOVACION-OCTUBRE-2010-PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Programa IBER-ROTAS</td>
										<td><a href="other/PROGRAMA-IBERUTAS-oct2010-PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Documento de Formula&ccedil;&atilde;o do Projecto associado: Jovens  por uma Ibero-Am&eacute;rica sem pobreza.</td>
										<td><a href="other/PROYECTO-ADS-JOVENS-SEM-POBREZA-PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Informe SEGIB de Apresenta&ccedil;&atilde;o dos temas Or&ccedil;ament&aacute;rios e  Administrativos e Documentos Adjuntos</td>
										<td><a href="other/INFTEMADM-SEGIB-extraCNRCXX- P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Anexo I: quadro acompanhamento pagamento de quotas 2010</td>
										<td><a href="other/ANEXOI-INFCUOTA10-SEGIB-extraCNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Anexo II a: Quadro resumo de execu&ccedil;&atilde;o provis&oacute;ria do  or&ccedil;amento 2010</td>
										<td><a href="other/ANEXOIIa-CUAEJEPRE10-SEGIB-extraCNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Anexo II b: Informe de execu&ccedil;&atilde;o dos fundos volunt&aacute;rios</td>
										<td><a href="other/ANEXOIIb-INFEJEFV10-SEGIB-extraCNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Anexo III: Secretaria-Geral Ibero-Americana (SEGIB)  Estados de Contas e Notas Explicativas</td>
										<td><a href="other/ANEXOIII-INFAUD09-SEGIB-extraCNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Projecto de Or&ccedil;amento 2011</td>
										<td><a href="other/ANEXOIV DIVPRE11-SEGIB-extraCNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Calend&aacute;rio geral de reuni&otilde;es da Confer&ecirc;ncia Ibero-Americana 2010</td>
										<td><a href="other/CALCONF-SEGIB-extraCNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Relat&oacute;rio de Actividades da Secretaria-Geral Ibero-Americana - 1 de Janeiro a 15 de Setembro</td>
										<td><a href="other/Inf-Actividades PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Processo de ratifica&ccedil;&atilde;o do Acordo de Santa Cruz de la Sierra</td>
										<td><a href="other/LISRAT-SEGIB-extraCNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Manual Operativo da Coopera&ccedil;&atilde;o Ibero-Americana</td>
										<td><a href="other/GUIA-MANUAL-OPERATIVO-PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Manual Operativo</td>
										<td><a href="other/MANUAL-OPERATIVO-PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Reuni&otilde;es Ministeriais Sectoriais 2010</td>
										<td><a href="other/Reunioes-Ministeriais-Sectoriais-2010.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">O Sistema Inter-Governamental Ibero-Americano: a coordena&ccedil;&atilde;o dos Organismos Ibero-Americanos</td>
										<td><a href="other/EL-SISTEMA-INTERGUBERNAMENTAL-IBEROAMERICANO-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									<!--tr class="d0">
										<td height="28" align="left">Regra de funcionamento do Registro de Redes Ibero-Americanas</td>
										<td><a href="other/REGLA-DEFINITIVA-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr> -->
									<tr class="dl">
										<td height="28" align="left">Estados de Contas e Notas Explicativas 31/12/2009</td>
										<td><a href="other/INFAUD09-SEGIB-RMREXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Projecto De Or�amento 2011</td>
										<td><a href="other/DIVPRE11-SEGIB-2CNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="dl">
										<td height="28" align="left">Quadro Acompanhamento Pagamento De Quotas 2010</td>
										<td><a href="other/INFCUOTA10-SEGIB-IICNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Informes De Execu��o Do Or�amento E Dos Fundos Volunt�rios 2010</td>
										<td><a href="other/Informes-ejecucion-2010-SEGIB-2CNRCXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="dl">
										<td height="28" align="left">Informe E Aprova��o Da Prorroga Do Auditor Externo</td>
										<td><a href="other/INFRENAUD-SEGIB-RMREXX-P.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    <tr class="d0">
										<td height="28" align="left">Agenda Tem&aacute;tica &ndash; Coordenadores Nacionais</strong></p></td>
										<td><a href="other/PRAGDCN-SPT-CNRC2XX-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Agenda Tem&aacute;tica &ndash; Respons&aacute;veis De Coopera&ccedil;&atilde;o</strong></p></td>
										<td><a href="other/PRAGDRC-SPT-CNRC2XX-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Agenda Tem&aacute;tica &ndash; Ses&otilde;es Conjuntas</td>
										<td><a href="other/PRAGDCONJ-SPT-CNRC2XX-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    <tr class="dl">
										<td height="28" align="left">Programa Geral</td>
										<td><a href="other/PROGEN-SEGIB-IICNRCXX-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    <tr class="d0">
										<td height="28" align="left">O Sistema Inter-Governamental Ibero-Americano: a coordena��o dos Organismos Ibero-Americanos</td>
										<td><a href="other/EL SISTEMA INTERGUBERNAMENTAL IBEROAMERICANO PORT.pdf" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
                                    <tr class="dl">
										<td height="28" align="left">Regra de funcionamento do Registo de Redes Ibero-Americanas</td>
										<td><a href="other/REGLA%20DEFINITIVA%20PORT.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">AGENDA TEM�TICA PARA A C�PULA DE CHEFES DE ESTADO E DE GOVERNO</td>
										<td><a href="other/PRAGDCEG-SPT-CNRC2XX-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">AGENDA TEM�TICA PARA LA CUMBRE DE JEFES DE ESTADO Y DE GOBIERNO</td>
										<td><a href="other/PRAGDCEG-SPT-CNRC2XX-E.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Alinhamentos para o PROGRAMA DE TRABALHO 2011 da Secretaria-Geral Ibero-Americana</td>
										<td><a href="other/LINEAMIENTOS-2011-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Lineamientos para el PROGRAMA DE TRABAJO 2011 de la Secretaria General Iberoamericana</td>
										<td><a href="other/LINEAMIENTOS-2011-E.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Comunicado Especial Sobre a &quot;Quest&atilde;o Das  Ilhas Malvinas&quot; (Proposta Argentina)</td>
										<td><a href="other/VERSION-2-PROYECTO-Malvinas-al-23-de-nov-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d1">
										<td height="28" align="left">Comunicado Especial Sobre &quot;A Comunidade De Estados Da Am&eacute;rica Latina e Do Caribe (Celac)&quot; (Proposta Venezuela)</td>
										<td><a href="other/Proy-Comunicado-Esp-sobre-CELAC-Prop-Venezuela-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr class="d0">
										<td height="28" align="left">Comunicado Especial Sobre O Combate &agrave; Delinq&uuml;&ecirc;ncia Organizada Transnacional Em Todas Suas Formas E Manifesta&otilde;es (Proposta El Salvador)</td>
										<td><a href="other/Proy-Com-Esp-Delincuencia-Org-El-Salvador-Versi�n-2-P.doc" target="_blank"><img src="images/buttons/descargar.png" width="74" height="24" border="0"></a></td>
									</tr>
									<tr>
										<td colspan="2" height="25"><img src="images/null.gif" width="1" height="25"></td>
									</tr>
								</table>
							</div>
						</div>
					</td>
					<td width="30" align="right">
					<div id="scrollbar" style="width:20px; height:440px; float:right;">
						<div id="up"><a class="mouseover_up" href=""><img src="images/btn-up.gif" width="11" height="11" alt="" border="0" /></a></div>
						<div id="track" style="height:416px;">
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
