package com.tdil.simon.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.data.valueobjects.VersionVO;

public class ExportVersionAsPDF {

	public static void exportDocument(VersionVO version, OutputStream output) throws SQLException, IOException, ParserConfigurationException, DocumentException, SAXException {
		StringBuffer buf = new StringBuffer();
		buf.append("<html>");

		// put in some style
		buf.append("<head>");
		buf.append("<title>").append(version.getDocument().getTitle()).append("</title>");
		buf.append("</head>");

		// generate the body
		buf.append("<body>");
		buf.append("<H1>").append(version.getDocument().getTitle()).append("</H1>");
		buf.append("<H2>Introducción</H2>");
		buf.append("<p>").append(version.getDocument().getIntroduction()).append("</p>");
		buf.append("<H2>Párrafos</H2>");
		for (Paragraph p : version.getParagraphs()) {
			buf.append("<p>").append(p.getParagraphNumber()).append(". ").append(p.getParagraphText()).append("</p>");
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		buf.append("<H2>Observaciones a la fecha: ").append(simpleDateFormat.format(new Date())).append("</H2>");
		List<Observation> observations = ObservationDAO.selectNotDeletedObservationsForVersion(version.getVersion().getId());
		buf.append("<table border=\"1\">");
		for (Observation o : observations) {
			ObservationVO vo = (ObservationVO)o;
			buf.append("<TR><TD><TABLE><TR>");
			buf.append("<TD>Párrafo: ").append(vo.getParagraphNumber()).append("</TD>");
			buf.append("</TR><TR>");
			buf.append("<TD>Fecha de observación: ").append(simpleDateFormat.format(vo.getCreationDate())).append("</TD>");
			buf.append("</TR><TR>");
			buf.append("<TD>Delegado: ").append(vo.getName()).append("</TD>");
			buf.append("</TR><TR>");
			buf.append("<TD>Delegación: ").append(vo.getCountryName()).append("</TD>");
			buf.append("</TR></TABLE></TD>");
			buf.append("<TD valign=\"top\">").append(vo.getObservationText()).append("</TD>");
		}
		buf.append("</table>");
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
}
