package com.tdil.simon.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.LogoDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.model.Logo;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.tdil.simon.utils.UploadUtils;
import com.tdil.simon.web.SystemConfig;

public class ExportVersionAsPDF {

	public static void exportDocument(SystemUser user, VersionVO version, OutputStream output) throws SQLException, IOException, ParserConfigurationException, DocumentException, SAXException {
		// TODO PABLO ejemplos
		// Ejemplo con if para ver si esta vacio o no
		if (!org.apache.commons.lang.StringUtils.isEmpty(version.getDocument().getDocumentDate())) {
			System.out.println(version.getDocument().getDocumentDate());
		}
		System.out.println(version.getDocument().getTopic());
		System.out.println(version.getDocument().getTag1());
		System.out.println(version.getDocument().getTag2());
		
		Logo logo = LogoDAO.getLogo("header.logoHeaderPDFsRTFs");
		UploadUtils.createFile(logo.getLogoData(), SystemConfig.getTempPath() + "/logoHeaderPDFsRTFs.png");
		
		logo = LogoDAO.getLogo("header.logoSegundoPDFsRTFs");
		UploadUtils.createFile(logo.getLogoData(), SystemConfig.getTempPath() + "/logoSegundoPDFsRTFs.png");
		
		DelegateAuditDAO.registerDownloadVersion(user, version.getVersion());
		StringBuffer buf = new StringBuffer();
		buf.append("<html>");

		// put in some style
		buf.append("<head>");
		buf.append("<style type=\"text/css\">");
		buf.append("<!--");
		buf.append("body {");
		buf.append("	color:#000000;");
		buf.append("	font-family: Verdana, Arial, Helvetica, sans-serif;");
		buf.append("	font-size: 13px;");
		buf.append("	line-height: normal;");
		buf.append("	letter-spacing: normal;");
		buf.append("	word-spacing: normal;");
		buf.append("	white-space: normal;");
		buf.append("}");
		buf.append("h1 {");
		buf.append("	color:#000000;");
		buf.append("	font-size: 16px;");
		buf.append("}");
		buf.append("h2 {");
		buf.append("	color:#000000;");
		buf.append("	font-size: 14px;");
		buf.append("}");
		buf.append("nameDelegate {");
		buf.append("	color:#333333;");
		buf.append("	font-size: 12px;");
		buf.append("}");
		buf.append("-->");
		buf.append("</style>");
		buf.append("<title>").append(version.getDocument().getTitle()).append("</title>");
		buf.append("</head>");

		// generate the body
		buf.append("<body>");
		buf.append("<table width=\"100%\" height=\"154\" border=\"0\">");
		buf.append("<TR><TD width=\"155\"><img width=\"155\" height=\"77\" src=\""+SystemConfig.getTempPath() + "/logoHeaderPDFsRTFs.png"+"\"></TD>");
		String designerText = version.getVersion().getDesignerText();
		if (designerText == null) {
			designerText = "";
		}
		buf.append("<TD align=\"center\">").append(designerText).append("</TD>");
		buf.append("<TD width=\"150\"><img width=\"150\" height=\"77\" src=\""+SystemConfig.getTempPath() + "/logoSegundoPDFsRTFs.png"+"\"></TD></TR></TABLE>");
		buf.append("<H1>").append(version.getDocument().getTitle()).append("</H1>");
		List<Paragraph> introduction = filterIntroduction(version.getParagraphs());
		buf.append("<H2>Preámbulo</H2>");
		for (Paragraph p : introduction) {
			buf.append("<p>").append(p.getParagraphNumberForDisplay()).append(". ").append(p.getParagraphText()).append("</p>");
		}
		List<Paragraph> paragraphs = version.getParagraphs().subList(introduction.size(), version.getParagraphs().size());
		buf.append("<H2>Párrafos</H2>");
		for (Paragraph p : paragraphs) {
			buf.append("<p>").append(p.getParagraphNumberForDisplay()).append(". ").append(p.getParagraphText()).append("</p>");
		}
		if (Version.IN_SIGN.equals(version.getVersion().getStatus()) || 
				Version.FINAL.equals(version.getVersion().getStatus())) {
			List signatures ;
			if (version.getVersion().isSpanishVersion()) {
				signatures = SignatureDAO.selectSignaturesFor(version.getVersion().getId());
			} else {
				signatures = SignatureDAO.selectSignaturesForPortugues(version.getDocument().getId(), version.getVersion().getNumber());
			}
			buf.append("<table border=\"0\">");
			for (Object signObj : signatures) {
				SignatureVO signatureVO = (SignatureVO)signObj;
				buf.append("<TR>");
				//buf.append("<TD valign=\"top\">").append("<img width=\"200\" height=\"126\" src=\"./").append(signatureVO.getSignatureFileName()).append("\"></TD>");
				buf.append("<TD valign=\"top\">").append("<img width=\"200\" height=\"126\" src=\"").append(SystemConfig.getSignatureStore() + "/" +signatureVO.getSignatureFileName()).append("\"></TD>");
				buf.append("<TD><TABLE><TR>");
				buf.append("<TD class=\"nameDelegate\">").append(signatureVO.getDelegateName()).append("</TD>");
				buf.append("</TR><TR>");
				buf.append("<TD>").append(signatureVO.getCountryDescription()).append("</TD>");
				buf.append("</TR><TR>");
				buf.append("<TD>").append(signatureVO.getJob()).append("</TD>");
				buf.append("</TR></TABLE></TD>");
			}
			buf.append("</table>");
		} else {
			int lastParagraphId = -1;
			List<Observation> observations = ObservationDAO.selectNotDeletedObservationsForVersion(version.getVersion().getId());
			if (!observations.isEmpty()) {
				DateFormat simpleDateFormat = SystemConfig.getDateFormatWithMinutes();
				buf.append("<H2>Observaciones a la fecha: ").append(simpleDateFormat.format(new Date())).append("</H2>");
				buf.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"4\" border=\"0\">");
				for (Observation o : observations) {
					if (o.getParagraphId() != lastParagraphId) {
						Paragraph p = getParagraph(version.getParagraphs(), o.getParagraphId());
						buf.append("<TR><TD colspan=\"2\" width=\"100%\"><b>Párrafo original:</b><br/>");
						buf.append(p.getParagraphNumberForDisplay()).append(". ");
						buf.append(p.getParagraphText());
						buf.append("<br/></TD>");
						buf.append("</TR>");
						lastParagraphId = p.getId();
					}
					DelegateAuditDAO.registerDownloadObservation(user, o);
					ObservationVO vo = (ObservationVO)o;
					buf.append("<TR><TD><TABLE width=\"100%\"><TR>");
					buf.append("<TD>Párrafo: <b>").append(vo.getParagraphNumberForDisplay()).append("</b></TD>");
					buf.append("</TR><TR>");
					buf.append("<TD>Fecha de observación: <b>").append(simpleDateFormat.format(vo.getCreationDate())).append("</b></TD>");
					buf.append("</TR><TR>");
					buf.append("<TD>Delegado: <b>").append(vo.getName()).append("</b></TD>");
					buf.append("</TR><TR>");
					buf.append("<TD>Delegación: <b>").append(vo.getCountryName()).append("</b></TD>");
					buf.append("</TR></TABLE></TD>");
					buf.append("<TD width=\"70%\" valign=\"top\" bgcolor=\"#EEEEEE\">").append(vo.getObservationText()).append("</TD>");
				}
				buf.append("</table>");
			}
		}
		buf.append("</body>");
		buf.append("</html>");
		ByteArrayOutputStream tidyOut = new ByteArrayOutputStream();
		Tidy tidy = new Tidy(); 
		tidy.setXHTML(true); 
		tidy.setDocType("omit");
		ByteArrayInputStream byteIn = new ByteArrayInputStream(buf.toString().getBytes());
		tidy.parse(byteIn, tidyOut);
		String text = new String(tidyOut.toByteArray());
		InputStream tidyIn = new ByteArrayInputStream(text.getBytes());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(tidyIn);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument( doc,  null);
		renderer.layout();
		renderer.createPDF(output);
	}
	
	private static Paragraph getParagraph(List<Paragraph> paragraphs, int paragraphId) {
		for (Paragraph p : paragraphs) {
			if (p.getId() == paragraphId) {
				return p;
			}
		}
		return null;
	}


	private static List<Paragraph> filterIntroduction(List<Paragraph> paragraphs) {
		List<Paragraph> result = new ArrayList<Paragraph>();
		for (Paragraph p : paragraphs) {
			if (p.belongsToIntroduction()) {
				result.add(p);
			} else {
				return result;
			}
		}
		return result;
	}
}
