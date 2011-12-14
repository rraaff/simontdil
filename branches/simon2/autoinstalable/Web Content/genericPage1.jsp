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
							<td width="10%"><%=ResourceBundleCache.get("genericPage1", "delegacy")%></td>
							<td width="13%"><%=ResourceBundleCache.get("genericPage1", "fullName")%></td>
							<td width="33%"><%=ResourceBundleCache.get("genericPage1", "position")%></td>
							<td width="17%"><%=ResourceBundleCache.get("genericPage1", "email")%></td>
							<td width="20%"><%=ResourceBundleCache.get("genericPage1", "contactPhone")%></td>
						</tr>
						<tr class="rowODD">
							<td>ARGENTINA</td>
							<td>Luis Eduardo Susmann</td>
							<td>Consejero - Direcci&oacute;n de Asia y Ocen&iacute;a - Ministerio de Relaciones Exteriores, Comercio Internacional y Culto - Punto Focal / Focal Point</td>
							<td><a href="mailto:les@mrecic.gov.ar">les@mrecic.gov.ar</a> / <a href="mailto:diayo@mrecic.gov.ar">diayo@mrecic.gov.ar</a></td>
							<td>Tel: (54) 11 4819-7000 # 7717</td>
						</tr>
						<tr class="rowEVEN">
							<td>AUSTRALIA</td>
							<td>Chris Munn</td>
							<td>Director de Am&eacute;rica del Sur y Coalar - Punto Focal / Focal Point</td>
							<td><a href="mailto:chris.munn@dfat.gov.au">chris.munn@dfat.gov.au</a></td>
							<td>Tel: (61) (2) 62613904</td>
						</tr>
						<tr class="rowODD">
							<td>BOLIVIA</td>
							<td>Fernando Calder&oacute;n</td>
							<td>Jefe de la Unidad de Cooperaci&oacute;n Econ&oacute;mica - Viceministerio de Comercio Exterior e Integraci&oacute;n - Ministerio de Relaciones Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:calderong.fernando@gmail.com">calderong.fernando@gmail.com</a></td>
							<td>Tel: (591) (2) 240 8900 int 3431</td>
						</tr>
						<tr class="rowEVEN">
							<td>BRASIL/BRAZIL</td>
							<td>Maria Dolores Penna de Almeida Cunha</td>
							<td>Consejero - Directora de la Division de Seguimiento de Cumbres (DSC) - Punto Focal / Focal Point</td>
							<td><a href="mailto:dsc@itamaraty.gov.br">dsc@itamaraty.gov.br</a> / <a href="mailto:maria.dolores@itamaraty.gov.br">maria.dolores@itamaraty.gov.br</a></td>
							<td>Tel: (55) 61-3411-9556 / 6604; fax: (55) 61-3411-9767</td>
						</tr>
						<tr class="rowODD">
							<td>BRUNEI DARUSSALAM</td>
							<td>Noralizan Abdul Momin</td>
							<td>Department of Politics II - Punto Focal / Focal Point</td>
							<td><a href="mailto:noralizan.momin@mfa.gov.bn">noralizan.momin@mfa.gov.bn</a></td>
							<td>Tel: (673) 226-1177 # 225; fax: (673) 226-2477</td>
						</tr>
						<tr class="rowEVEN">
							<td>CAMBOYA/CAMBODIA</td>
							<td>Mr. Nong Savanra</td>
							<td>ASEAN Sub-Director - Punto Focal / Focal Point</td>
							<td><a href="mailto:mfaic.asean@mfa.gov.kh">mfaic.asean@mfa.gov.kh</a></td>
							<td>Tel: (855) 23-213-247; fax: (855) 23-215-541</td>
						</tr>
						<tr class="rowODD">
							<td>CHILE</td>
							<td>Rafael Puelma</td>
							<td>Consejero de la Direcci&oacute;n Asia Pac&iacute;fica - Ministerio de Relaciones Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:rpuelma@minrel.gov.cl">rpuelma@minrel.gov.cl</a> / <a href="mailto:rpuelma7@hotmail.com">rpuelma7@hotmail.com</a></td>
							<td>Tel: (56) (2) 827-4340/ 827-4728/ 827-4190</td>
						</tr>
						<tr class="rowEVEN">
							<td>CHINA</td>
							<td>Lin Ji</td>
							<td>Departamento de Am&eacute;rica Latina y el Caribe - Ministerio de Relaciones Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:lin_ji@mfa.gov.cn">lin_ji@mfa.gov.cn</a></td>
							<td>Tel: (86) (10) 6596-2765</td>
						</tr>
						<tr class="rowODD">
							<td>COLOMBIA</td>
							<td>Dr. Juan Guillermo Castro Benetti</td>
							<td>Director de Asia, Africa y Ocean&iacute;a - Ministerio de Relaciones Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:juan.castro@cancilleria.gov.co">juan.castro@cancilleria.gov.co</a></td>
							<td>Tel: (57) (1) 381-4000 # 1509</td>
						</tr>
						<tr class="rowEVEN">
							<td>COREA / KOREA</td>
							<td>Mr. Hak-Jae Kim</td>
							<td>Punto Focal / Focal Point</td>
							<td><a href="mailto:hjkim94@mofat.go.kr">hjkim94@mofat.go.kr</a></td>
							<td>Tel: (82) (2) 720-7093</td>
						</tr>
						<tr class="rowODD">
							<td>COSTA RICA</td>
							<td>Paola Patricia Porras Pastran</td>
							<td>Encargada de Asia y Ocean&iacute;a - Direcci&oacute;n General de Pol&iacute;tica Exterior - Ministerio de Relaciones Exteriores y Culto - Punto Focal / Focal Point</td>
							<td><a href="mailto:paola.porras.pastran@gmail.com">paola.porras.pastran@gmail.com</a></td>
							<td>Tel: (506) 2223-7555 # 256</td>
						</tr>
						<tr class="rowEVEN">
							<td>CUBA</td>
							<td>Herminio L&oacute;pez D&iacute;az</td>
							<td>Consejero Especialista Principal - Direcci&oacute;n de Asia y Ocean&iacute;a - Ministerio de Relaciones Exteriores de Cuba - Punto Focal / Focal Point</td>
							<td><a href="mailto:herminio@minrex.gov.cu">herminio@minrex.gov.cu</a></td>
							<td>Tel: (53) 836-4360</td>
						</tr>
						<tr class="rowODD">
							<td>ECUADOR</td>
							<td>Emb. Paulina Garcia Donoso</td>
							<td>Secretar&iacute;a por el Comit&eacute; Ecuatoriano de la cuenca del Pac&iacute;fico - Punto Focal / Focal Point</td>
							<td><a href="mailto:ecupec@mmrree.gob.ec">ecupec@mmrree.gob.ec</a></td>
							<td>Cel: (593) (8) 5676688</td>
						</tr>
						<tr class="rowEVEN">
							<td>EL SALVADOR</td>
							<td>Lic. Milton A. Maga&ntilde;a Herrera - Director de Asia, Africa y Ocean&iacute;a - Punto Focal / Focal Point</td>
							<td><a href="mailto:mmagana@rree.gob.sv">mmagana@rree.gob.sv</a></td>
							<td>Tel: (503) 2231-1081</td>
						</tr>
						<tr class="rowODD">
							<td>FILIPINAS / PHILIPPINES</td>
							<td>Mr.Gines D. Gallaga</td>
							<td>Director South America Division - Office of Foreign Affairs - Punto Focal / Focal Point</td>
							<td><a href="mailto:gines.gallaga@yahoo.com">gines.gallaga@yahoo.com</a></td>
							<td>Tel: (63) (2) 834-3345; (63) (2) 834-3726 / Cel: (63) 9272773715</td>
						</tr>
						<tr class="rowEVEN">
							<td>GUATEMALA</td>
							<td>Lic. Shirley Castillo Rivera</td>
							<td>Subdirectora de Pol&iacute;tica Econ&oacute;mica - Direcci&oacute;n General de Relaciones Internacionales Multilaterales y Econ&oacute;micas - Punto Focal / Focal Point</td>
							<td><a href="mailto:scastillo@minex.gob.gt">scastillo@minex.gob.gt</a></td>
							<td>Tel: (502)-2410-0110</td>
						</tr>
						<tr class="rowODD">
							<td>INDONESIA</td>
							<td>Sr. Johan Mulyadi</td>
							<td>Deputy Director - Punto Focal / Focal Point</td>
							<td><a href="mailto:johan_mulyadi@yahoo.com">johan_mulyadi@yahoo.com</a></td>
							<td>Tel: (62) 21-3812-778</td>
						</tr>
						<tr class="rowEVEN">
							<td>JAP&Oacute;N / JAPAN</td>
							<td>Kenji Shimada</td>
							<td>Secretary - Punto Focal / Focal Point</td>
							<td><a href="mailto:kenji.shimada@mofa.go.jp">kenji.shimada@mofa.go.jp</a></td>
							<td>Tel: (81) (3) 5501-8288</td>
						</tr>
						<tr class="rowODD">
							<td>LAOS</td>
							<td>Khamfong Sayalath</td>
							<td>Officer of Economic Affairs Department - Ministry of Foreign Affairs - Punto Focal / Focal Point</td>
							<td><a href="mailto:kfsayalath@yahoo.com">kfsayalath@yahoo.com</a></td>
							<td>Tel: (856) 21-454282; fax: (856) 21-415932</td>
						</tr>
						<tr class="rowEVEN">
							<td>MALASIA / MALAYSIA</td>
							<td>Segundo Secretario Ms. Alia Carmallia</td>
							<td>Assistance Secretary - Regional Cooperation, Social and Cultural Division - Ministry of Foreign Affairs - Punto Focal / Focal Point</td>
							<td><a href="mailto:carmallia@kln.gov.my">carmallia@kln.gov.my</a></td>
							<td>Tel: (60) (3) 8887-4583; fax: (60) (3) 8889-2806</td>
						</tr>
						<tr class="rowODD">
							<td>M&Eacute;XICO</td>
							<td>Ministro Armando &Aacute;lvarez Reina</td>
							<td>Alto Funcionario de M&eacute;xico para FOCALAE - Director General para Asia-Pac&iacute;fico - Punto Focal / Focal Point</td>
							<td><a href="mailto:aalvarez@sre.gob.mx">aalvarez@sre.gob.mx</a></td>
							<td>Tel: (52) (55) 3686-5946</td>
						</tr>
						<tr class="rowEVEN">
							<td>MONGOLIA</td>
							<td>Tugsbilgun Tumurkhuleg</td>
							<td>Deputy Director - Punto Focal / Focal Point</td>
							<td><a href="mailto:tugsbilgun@mfat.gov.mn">tugsbilgun@mfat.gov.mn</a></td>
							<td>Tel: (976) 26-2968</td>
						</tr>
						<tr class="rowODD">
							<td>MYANMAR</td>
							<td>Ms. Kyi Kyi Sein</td>
							<td>Departamento de Pol&iacute;tica - Director de la Divisi&oacute;n Am&eacute;rica - Ministerio de Asuntos Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:kkyisein@gmail.com">kkyisein@gmail.com</a></td>
							<td>Tel: (95) 67-412361; fax: (95) 67-412396</td>
						</tr>
						<tr class="rowEVEN">
							<td>NICARAGUA</td>
							<td>Bertha Antonia Mendoza Irigoyen</td>
							<td>Responsable de la Direcci&oacute;n de Asia y Ocean&iacute;a - Punto Focal / Focal Point</td>
							<td><a href="mailto:bmendoza@cancilleria.gob.ni">bmendoza@cancilleria.gob.ni</a></td>
							<td>Tel: (505) 2244-8042</td>
						</tr>
						<tr class="rowODD">
							<td>NUEVA ZELANDIA / NEW ZEALAND</td>
							<td>Luke Leonard</td>
							<td>Policy Officer - Americas Division - Punto Focal / Focal Point</td>
							<td><a href="mailto:luke.leonard@mfat.govt.nz">luke.leonard@mfat.govt.nz</a></td>
							<td>Tel: (64) (4) 439-8119; fax: (64) (4) 439-8516</td>
						</tr>
						<tr class="rowEVEN">
							<td>PANAM&Aacute;</td>
							<td>Lic. Mar&iacute;a Celia Dopaso Lopez</td>
							<td>Directora General de Proyectos Especiales y Cooperaci&oacute;n - Ministerio de Relaciones Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:mdopeso@mire.gob.pa">mdopeso@mire.gob.pa</a></td>
							<td>Tel: (507) 511 4268/4278</td>
						</tr>
						<tr class="rowODD">
							<td>PARAGUAY</td>
							<td>Ministro Mart&iacute;n Llano-Heyn</td>
							<td>Director de Foros Regionales - Ministerio de Relaciones Exteriores del Paraguay - Punto Focal / Focal Point</td>
							<td><a href="mailto:mllano@mre.gov.py">mllano@mre.gov.py</a></td>
							<td>Tel:(595) 21 444-919</td>
						</tr>
						<tr class="rowEVEN">
							<td>PER&Uacute;</td>
							<td>Sergio Zapata</td>
							<td>Tercer Secretario - Punto Focal / Focal Point</td>
							<td><a href="mailto:szapata@rree.gob.pe">szapata@rree.gob.pe</a></td>
							<td>Tel: (51) (1) 204-3017</td>
						</tr>
						<tr class="rowODD">
							<td>REP&Uacute;BLICA DOMINICANA / DOMINICAN REPUBLIC</td>
							<td>Yesina Antigua</td>
							<td>Ministro Consejero para Asuntos Asiaticos - Punto Focal / Focal Point</td>
							<td><a href="yesiantigua@hotmail.com">yesiantigua@hotmail.com</a> / <a href="yantigua@mirex.gov.do">yantigua@mirex.gov.do</a></td>
							<td>Tel: (1-809) 987-7001 # 7200</td>
						</tr>
						<tr class="rowEVEN">
							<td>SINGAPUR / SINGAPORE</td>
							<td>Srita Kassandra Tan Yen Pin</td>
							<td>Country Officer - Punto Focal / Focal Point</td>
							<td><a href="mailto:kassandra_tan@mfa.gov.sg">kassandra_tan@mfa.gov.sg</a></td>
							<td>Tel: (65) 6379-8232</td>
						</tr>
						<tr class="rowODD">
							<td>TAILANDIA / THAILAND</td>
							<td>Ms. Ploy Khumthukthit</td>
							<td>Tercer Secretaria - Departamento de Asuntos de Am&eacute;rica y Pac&iacute;fico Sur - Divisi&oacute;n Am&eacute;rica - Ministerio de Asuntos Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:ployk@mfa.go.th">ployk@mfa.go.th</a></td>
							<td>Tel: (66) (2) 643-5000; fax: (66) (2) 643-5127</td>
						</tr>
						<tr class="rowEVEN">
							<td>URUGUAY</td>
							<td>Emb. Marta Pizzanelli</td>
							<td>Directora Regional Asia, &Aacute;frica y Ocean&iacute;a - Ministerio de Relaciones Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:martapizzanelli2003@yahoo.com">martapizzanelli2003@yahoo.com</a></td>
							<td>Tel: (598) 2902 7975</td>
						</tr>
						<tr class="rowODD">
							<td>VENEZUELA</td>
							<td>Lic. Yelitza Ventura Polanco</td>
							<td>Coordinadora de Asuntos Multilaterales y Regionales del Despacho del - Viceministro para Asia, Medio Oriente y Ocean&iacute;a - Punto Focal / Focal Point</td>
							<td><a href="mailto:yelitza.ventura@gmail.com">yelitza.ventura@gmail.com</a></td>
							<td>Tel: (58) 412 9355385 / (58) 212 8064369</td>
						</tr>
						<tr class="rowEVEN">
							<td>VIETNAM</td>
							<td>Sr. Le Cong Tien</td>
							<td>Asistente del Director del Departamento de Am&eacute;ricas - Ministerio de Relaciones Exteriores - Punto Focal / Focal Point</td>
							<td><a href="mailto:cmy.mfa@mofa.gov.vn">cmy.mfa@mofa.gov.vn</a></td>
							<td>Tel: (84) (4) 379 92206 / Cel: (84) 936609996</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="40" align="center" valign="middle"><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonStart()%><html:link action="/goToDelegateHome" ><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonMiddle("botones","volver")%></html:link><%=com.tdil.simon.web.ButtonGenerator.getNoOPButtonEnd()%></td>
		</tr>
	</table>
<%@ include file="includes/rightContent.jsp" %>
<%@ include file="includes/footer.jsp" %>