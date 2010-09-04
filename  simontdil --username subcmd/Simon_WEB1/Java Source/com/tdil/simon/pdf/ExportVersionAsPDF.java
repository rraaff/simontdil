package com.tdil.simon.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.FontSize;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Signature;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.data.valueobjects.VersionVO;

public class ExportVersionAsPDF {

	public static void exportDocument(SystemUser user, VersionVO version, OutputStream output) throws SQLException, IOException, ParserConfigurationException, DocumentException, SAXException {
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
		buf.append("	font-size: 10px;");
		buf.append("	line-height: normal;");
		buf.append("	letter-spacing: normal;");
		buf.append("	word-spacing: normal;");
		buf.append("	white-space: normal;");
		buf.append("}");
		buf.append("-->");
		buf.append("</style>");
		buf.append("<title>").append(version.getDocument().getTitle()).append("</title>");
		buf.append("</head>");

		// generate the body
		buf.append("<body>");
		buf.append("<H1>").append(version.getDocument().getTitle()).append("</H1>");
		buf.append("<H2>Introducci�n</H2>");
		buf.append("<p>").append(version.getDocument().getIntroduction()).append("</p>");
		buf.append("<H2>P�rrafos</H2>");
		for (Paragraph p : version.getParagraphs()) {
			buf.append("<p>").append(p.getParagraphNumber()).append(". ").append(p.getParagraphText()).append("</p>");
		}
		if (Version.IN_SIGN.equals(version.getVersion().getStatus()) || 
				Version.FINAL.equals(version.getVersion().getStatus())) {
			List signatures = SignatureDAO.selectSignaturesFor(version.getVersion().getId());
			buf.append("<table border=\"0\">");
			for (Object signObj : signatures) {
				SignatureVO signatureVO = (SignatureVO)signObj;
				buf.append("<TR><TD><TABLE><TR>");
				buf.append("<TD>Delegado: ").append(signatureVO.getDelegateName()).append("</TD>");
				buf.append("</TR><TR>");
				buf.append("<TD>Delegaci�n: ").append(signatureVO.getCountryDescription()).append("</TD>");
				buf.append("</TR><TR>");
				buf.append("<TD>Posici�n: ").append(signatureVO.getJob()).append("</TD>");
				buf.append("</TR></TABLE></TD>");
				System.out.println(new File(".").getAbsolutePath()); 
				buf.append("<TD valign=\"top\">").append("<img widht=\"100\" height=\"100\" src=\"./").append(signatureVO.getSignatureFileName()).append("\"></TD>");
			}
			buf.append("</table>");
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			buf.append("<H2>Observaciones a la fecha: ").append(simpleDateFormat.format(new Date())).append("</H2>");
			List<Observation> observations = ObservationDAO.selectNotDeletedObservationsForVersion(version.getVersion().getId());
			buf.append("<table border=\"0\">");
			for (Observation o : observations) {
				DelegateAuditDAO.registerDownloadObservation(user, o);
				ObservationVO vo = (ObservationVO)o;
				buf.append("<TR><TD><TABLE><TR>");
				buf.append("<TD>P�rrafo: ").append(vo.getParagraphNumber()).append("</TD>");
				buf.append("</TR><TR>");
				buf.append("<TD>Fecha de observaci�n: ").append(simpleDateFormat.format(vo.getCreationDate())).append("</TD>");
				buf.append("</TR><TR>");
				buf.append("<TD>Delegado: ").append(vo.getName()).append("</TD>");
				buf.append("</TR><TR>");
				buf.append("<TD>Delegaci�n: ").append(vo.getCountryName()).append("</TD>");
				buf.append("</TR></TABLE></TD>");
				buf.append("<TD width=\"70%\" valign=\"top\" bgcolor=\"#EEEEEE\">").append(vo.getObservationText()).append("</TD>");
			}
			buf.append("</table>");
		}
		buf.append("</body>");
		buf.append("</html>");
		System.out.println(buf.toString());
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
}