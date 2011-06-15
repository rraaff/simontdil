<%@ page info="genericPage1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>

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
<%@ include file="includes/leftContentModerator.jsp" %>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td colspan="3"><%@ include file="includes/extraLinks.jsp" %></td>
		</tr>
		<tr>
			<td colspan="3"><img src="images/null.gif" width="1" height="10"></td>
		</tr>
		<tr>
			<td colspan="3">
				<div style="width:100%; height:450px; overflow:auto;">
					<table width="100%" border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td width="13%"><%=ResourceBundleCache.get("genericPage1", "fullName")%></td>
							<td width="33%"><%=ResourceBundleCache.get("genericPage1", "position")%></td>
							<td width="10%"><%=ResourceBundleCache.get("genericPage1", "delegacy")%></td>
							<td width="17%"><%=ResourceBundleCache.get("genericPage1", "email")%></td>
							<td width="20%"><%=ResourceBundleCache.get("genericPage1", "contactPhone")%></td>
						</tr>
						<tr class="rowODD">
							<td>Coalar Chris Munn</td>
							<td>Director de Am&eacute;rica del Sur y Punto Focal en Canberra</td>
							<td>Australia</td>
							<td><a href="mailto:chris.munn@dfat.gov.au">chris.munn@dfat.gov.au</a></td>
							<td>612 62613904</td>
						</tr>
						<tr class="rowEVEN">
							<td>Fernando Calder&oacute;n</td>
							<td>Jefe de la Unidad de Cooperaci&oacute;n Econ&oacute;mica y Punto Focal en La Paz</td>
							<td>Bolivia</td>
							<td><a href="mailto:calderong.fernando@gmail.com">calderong.fernando@gmail.com</a></td>
							<td>(591-2) 240 8900 int 3431</td>
						</tr>
						<tr class="rowODD">
							<td>Consejero Maria Dolores Penna de Almeida Cunha</td>
							<td>Directora de la DSC y Punto Focal en Brasilia</td>
							<td>Brasil</td>
							<td><a href="mailto:dsc@itamaraty.gov.br">dsc@itamaraty.gov.br</a> / <a href="mailto:maria.dolores@itamaraty.gov.br">maria.dolores@itamaraty.gov.br</a></td>
							<td>55-61-3411-9556 / 6604</td>
						</tr>
						<tr class="rowEVEN">
							<td>Noralizan Abdul Momin</td>
							<td>Punto Focal en Bandar Seri Begawan</td>
							<td>Brunei-Darussalam</td>
							<td><a href="mailto:noralizan.momin@mfa.gov.bn">noralizan.momin@mfa.gov.bn</a></td>
							<td>(673) 226 1117 ext 225</td>
						</tr>
						<tr class="rowODD">
							<td>Mr. Nong Savanra</td>
							<td>ASEAN Sub-Director y Punto Focal en Phnom Penh</td>
							<td>Camboya</td>
							<td><a href="mailto:mfaic.asean@mfa.gov.kh">mfaic.asean@mfa.gov.kh</a></td>
							<td>855-68-991-954 / 855-23-213-247</td>
						</tr>
						<tr class="rowEVEN">
							<td>Consejero Rafael Puelma</td>
							<td>Punto Focal en Santiago</td>
							<td>Chile</td>
							<td><a href="mailto:rpuelma@minrel.gov.cl">rpuelma@minrel.gov.cl</a></td>
							<td>562-8274340</td>
						</tr>
						<tr class="rowODD">
							<td>Lin Ji</td>
							<td>Punto Focal en Beijing</td>
							<td>China</td>
							<td><a href="mailto:lin_ji@mfa.gov.cn">lin_ji@mfa.gov.cn</a></td>
							<td>00 86-10-6596-2761/65</td>
						</tr>
						<tr class="rowEVEN">
							<td>Juan Guillermo Castro Benetti</td>
							<td>Co-Presidente GT Pol&iacute;tica, Cultura, Educaci&oacute;n y Deporte y Punto Focal en Bogot&aacute;</td>
							<td>Colombia</td>
							<td><a href="mailto:juan.castro@cancilleria.gov.co">juan.castro@cancilleria.gov.co</a></td>
							<td>57-1381-4000 ext 1783</td>
						</tr>
						<tr class="rowODD">
							<td>Ms Jong Cha</td>
							<td>Punto Focal en Se&uacute;l</td>
							<td>Corea</td>
							<td><a href="mailto:fealac@mofat.go.kr">fealac@mofat.go.kr</a></td>
							<td>82-2-720-7093</td>
						</tr>
						<tr class="rowEVEN">
							<td>Paola Patricia Porras Pastran</td>
							<td>Punto Focal en San Jos&eacute;</td>
							<td>Costa Rica</td>
							<td><a href="mailto:paola.porras.pastran@gmail.com">paola.porras.pastran@gmail.com</a></td>
							<td>(506) 2223 7555 ext 256</td>
						</tr>
						<tr class="rowODD">
							<td>Consejero Herminio L&oacute;pez D&iacute;az</td>
							<td>Punto Focal en La Habana</td>
							<td>Cuba</td>
							<td><a href="mailto:herminio@minrex.gov.cu">herminio@minrex.gov.cu</a></td>
							<td>(53) 8364 360</td>
						</tr>
						<tr class="rowEVEN">
							<td>Embajador Paulina Garcia Donoso</td>
							<td>Punto Focal en Quito</td>
							<td>Ecuador</td>
							<td><a href="mailto:ecupec@mmrree.gob.ec">ecupec@mmrree.gob.ec</a></td>
							<td>Cel: 005938-5676688</td>
						</tr>
						<tr class="rowODD">
							<td>Lic. Milton A. Maga&ntilde;a Herrera</td>
							<td>Director de Asia, Africa y Ocean&iacute;a y Punto Focal de San Salvador</td>
							<td>El Salvador</td>
							<td><a href="mailto:mmagana@rree.gob.sv">mmagana@rree.gob.sv</a></td>
							<td>(503) 2231-1081</td>
						</tr>
						<tr class="rowEVEN">
							<td>Director Gines Gallaga</td>
							<td>Punto Focal en Manila</td>
							<td>Filipinas</td>
							<td><a href="mailto:gines.gallaga@yahoo.com">gines.gallaga@yahoo.com</a></td>
							<td>(632) 834-3345; (632) 834-3726 / Cel: (0063) 9272773715</td>
						</tr>
						<tr class="rowODD">
							<td>Segundo Secretario Byron Morales</td>
							<td>Punto Focal en Ciudad de Guatemala</td>
							<td>Guatemala</td>
							<td><a href="mailto:bmorales@minex.gob.gt">bmorales@minex.gob.gt</a></td>
							<td>502-2410-0110</td>
						</tr>
						<tr class="rowEVEN">
							<td>Sr. Johan Mulyadi</td>
							<td>Punto Focal en Jakarta</td>
							<td>Indonesia</td>
							<td><a href="mailto:johan_mulyadi@yahoo.com">johan_mulyadi@yahoo.com</a></td>
							<td>0062 21 3812 778</td>
						</tr>
						<tr class="rowODD">
							<td>Kenji Shimada</td>
							<td>Punto Focal en Tokio</td>
							<td>Jap&oacute;n</td>
							<td><a href="mailto:kenji.shimada@mofa.go.jp">kenji.shimada@mofa.go.jp</a></td>
							<td>81-3-5501-8288</td>
						</tr>
						<tr class="rowEVEN">
							<td>Khamfong Sayalath</td>
							<td>Punto Focal en Vient&aacute;n</td>
							<td>Laos</td>
							<td><a href="mailto:kfsayalath@yahoo.com">kfsayalath@yahoo.com</a></td>
							<td>856-21-454282</td>
						</tr>
						<tr class="rowODD">
							<td>Segundo Secretario Alia Carmallia</td>
							<td>Punto Focal en Kuala Lumpur</td>
							<td>Malasia</td>
							<td><a href="mailto:carmallia@kln.gov.my">carmallia@kln.gov.my</a></td>
							<td>(60) (3) 8887 4583</td>
						</tr>
						<tr class="rowEVEN">
							<td>Ministro Armando Ivarez Reina</td>
							<td>Alto Funcionario de M&eacute;xico para FOCALAE y Director Genreal para Asia-Pac&iacute;fico y Punto Focal en Ciudad de M&eacute;xico</td>
							<td>M&eacute;xico</td>
							<td><a href="mailto:aalvarez@sre.gob.mx">aalvarez@sre.gob.mx</a></td>
							<td>(52-55) 3686-5946</td>
						</tr>
						<tr class="rowODD">
							<td>Director Tugsbilgun Tumurkhuleg</td>
							<td>Punto Focal en Ul&aacute;n Bator</td>
							<td>Mongolia</td>
							<td><a href="mailto:tugsbilgun@mfat.gov.mn">tugsbilgun@mfat.gov.mn</a></td>
							<td>976-26-2968</td>
						</tr>
						<tr class="rowEVEN">
							<td>Directora Ms. Kyi Kyi Sein</td>
							<td>Punto Focal en Naipyid&oacute;</td>
							<td>Myanmar</td>
							<td><a href="mailto:kkyisein@gmail.com">kkyisein@gmail.com</a></td>
							<td>95-67-412361</td>
						</tr>
						<tr class="rowODD">
							<td>Bertha Antonia Mendoza Irigoyen</td>
							<td>Responsable de la Direcci&oacute;n de Asia y Ocean&iacute;a y Punto Focal en Managua</td>
							<td>Nicaragua</td>
							<td><a href="mailto:bmendoza@cancilleria.gob.ni">bmendoza@cancilleria.gob.ni</a></td>
							<td>(505) 2244-8042</td>
						</tr>
						<tr class="rowEVEN">
							<td>Policy Officer Luke Leonard</td>
							<td>Punto Focal en Wellington</td>
							<td>Nueva Zelandia</td>
							<td><a href="mailto:luke.leonard@mfat.govt.nz">luke.leonard@mfat.govt.nz</a></td>
							<td>+64-4-439-8119</td>
						</tr>
						<tr class="rowODD">
							<td>Lic. Tom&aacute;s Guardia</td>
							<td>Director de Organismos y Conferencias Internacionales del Ministerio de Relaciones Exteriores y Punto Focal en Panam&aacute;</td>
							<td>Panam&aacute;</td>
							<td><a href="mailto:tguardia@mire.gob.pa">tguardia@mire.gob.pa</a> / <a href="mailto:tomguar@gmail.com">tomguar@gmail.com</a></td>
							<td>00507 511 4275</td>
						</tr>
						<tr class="rowEVEN">
							<td>Ministro Mart&iacute;n Llano-Heyn</td>
							<td>Punto Focal en Asunci&oacute;n</td>
							<td>Paraguay</td>
							<td><a href="mailto:mllano@mre.gov.py">mllano@mre.gov.py</a></td>
							<td>Tel:(595 21) 444-919</td>
						</tr>
						<tr class="rowODD">
							<td>Tercer Secretario Sergio Zapata</td>
							<td>Punto Focal en Lima</td>
							<td>Per&uacute;</td>
							<td><a href="mailto:szapata@rree.gob.pe">szapata@rree.gob.pe</a></td>
							<td>(511) 204 3017</td>
						</tr>
						<tr class="rowEVEN">
							<td>Consejero para Asuntos Asi&aacute;ticos Yesina Antigua</td>
							<td>Punto Focal en Santo Domingo</td>
							<td>Rep&uacute;blica Dominicana</td>
							<td><a href="mailto:yesiantigua@hotmail.com">yesiantigua@hotmail.com</a> / <a href="mailto:yantigua@mirex.gov.do">yantigua@mirex.gov.do</a></td>
							<td>(001809) 987-7001 ext 7200</td>
						</tr>
						<tr class="rowODD">
							<td>Srita Kassandra Tan Yen Pin</td>
							<td>Punto Focal en Singapur</td>
							<td>Singapur</td>
							<td><a href="mailto:kassandra_tan@mfa.gov.sg">kassandra_tan@mfa.gov.sg</a></td>
							<td>0065 6379-8232</td>
						</tr>
						<tr class="rowEVEN">
							<td>Tercer Secretaria Ploy Khumthukthit</td>
							<td>Punto Focal en Bangkok</td>
							<td>Tailandia</td>
							<td><a href="mailto:ployk@mfa.go.th">ployk@mfa.go.th</a></td>
							<td>662-643-5000</td>
						</tr>
						<tr class="rowODD">
							<td>Embajadora Marta Pizzanelli</td>
							<td>Directora Regional Asia y &Aacute;frica y Ocean&iacute;a del Ministerio de Relaciones Exteriores y Punto Focal en Montevideo</td>
							<td>Uruguay</td>
							<td><a href="mailto:martapizzanelli2003@yahoo.com">martapizzanelli2003@yahoo.com</a></td>
							<td width="7%">(00598) 2902 7975</td>
						</tr>
						<tr class="rowEVEN">
							<td>Yelitza Ventura Polanco</td>
							<td>Coordinadora de Asuntos Multilaterales y Regionales del Despacho del Viceminstro para Asia Medio Oriente y Ocean&iacute;a y Punto Focal en Caracas</td>
							<td>Venezuela</td>
							<td><a href="mailto:yelitza.ventura@gmail.com">yelitza.ventura@gmail.com</a></td>
							<td>58 412 9355385 / 58 212 8064369</td>
						</tr>
						<tr class="rowODD">
							<td>Asistente del Director Le Cong Tien</td>
							<td>Punto Focal en Hanoi</td>
							<td>Vietnam</td>
							<td><a href="mailto:cmy.mfa@mofa.gov.vn">cmy.mfa@mofa.gov.vn</a></td>
							<td>(844) 379 92206 / Cel: (84) 936609996</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="40" align="center" valign="middle"><html:link action="/goToDelegateHome" ><%=com.tdil.simon.web.ButtonGenerator.getNoOPButton("botones","volver")%></html:link></td>
		</tr>
	</table>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>