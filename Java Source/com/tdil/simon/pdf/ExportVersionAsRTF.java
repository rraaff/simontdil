package com.tdil.simon.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jfor.jfor.converter.Converter;
import org.w3c.tidy.Tidy;
import org.xml.sax.InputSource;

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

public class ExportVersionAsRTF {

	public static void exportDocument(SystemUser user, VersionVO version, OutputStream output) throws Exception {
		Logo logo = LogoDAO.getLogo("header.logoHeaderPDFsRTFs");
		UploadUtils.createFile(logo.getLogoData(), SystemConfig.getTempPath() + "/logoHeaderPDFsRTFs.png");
		
		logo = LogoDAO.getLogo("header.logoSegundoPDFsRTFs");
		UploadUtils.createFile(logo.getLogoData(), SystemConfig.getTempPath() + "/logoSegundoPDFsRTFs.png");
		
		DelegateAuditDAO.registerDownloadVersion(user, version.getVersion());
		StringBuffer buf = new StringBuffer();
		buf.append("<html>");
		// put in some style
		buf.append("<head>");
		buf.append("<title>").append(version.getDocument().getTitle()).append("</title>");
		buf.append("</head>");
		// generate the body
		buf.append("<body>");
		buf.append("<h3>").append(version.getDocument().getTitle()).append("</h3>");
		String designerText = version.getVersion().getDesignerText();
		if (designerText == null) {
			designerText = "";
		}
		buf.append("<h4>").append(designerText).append("</h4>");
		List<Paragraph> introduction = filterIntroduction(version.getParagraphs());
		buf.append("<h4>Preámbulo</h4>");
		for (Paragraph p : introduction) {
			buf.append("<p>").append(p.getParagraphNumberForDisplay()).append(". ").append(convertForRtf(p.getParagraphText())).append("</p>");
		}
		List<Paragraph> paragraphs = version.getParagraphs().subList(introduction.size(), version.getParagraphs().size());
		buf.append("<br/><h4>Párrafos</h4>");
		for (Paragraph p : paragraphs) {
			buf.append("<p>").append(p.getParagraphNumberForDisplay()).append(". ").append(convertForRtf(p.getParagraphText())).append("</p>");
		}
		if (Version.IN_SIGN.equals(version.getVersion().getStatus()) || 
				Version.FINAL.equals(version.getVersion().getStatus())) {
			List signatures ;
			if (version.getVersion().isSpanishVersion()) {
				signatures = SignatureDAO.selectSignaturesFor(version.getVersion().getId());
			} else {
				signatures = SignatureDAO.selectSignaturesForPortugues(version.getDocument().getId(), version.getVersion().getNumber());
			}
			buf.append("<table cols=\"150 300pt\" border=\"0\">");
			for (Object signObj : signatures) {
				SignatureVO signatureVO = (SignatureVO)signObj;
				buf.append("<TR><TD>");
				buf.append("Delegado: ").append(signatureVO.getDelegateName()).append("<br/>");
				buf.append("");
				buf.append("Delegación: ").append(signatureVO.getCountryDescription()).append("<br/>");
				buf.append("Cargo: ").append(signatureVO.getJob()).append("<br/>");
				buf.append("</TD>");
				buf.append("<TD valign=\"top\">").append("<img width=\"1806px\" height=\"891px\" src=\"").append(SystemConfig.getSignatureStore() + "/" +signatureVO.getSignatureFileName()).append("\"></TD>");
				buf.append("</TR>");
			}
			buf.append("</table>");
		} else {
			int lastParagraphId = -1;
			List<Observation> observations = ObservationDAO.selectNotDeletedObservationsForVersion(version.getVersion().getId());
			if (!observations.isEmpty()) {
				DateFormat simpleDateFormat = SystemConfig.getDateFormatWithMinutes();
				buf.append("<h4>Observaciones a la fecha: ").append(simpleDateFormat.format(new Date())).append("</h4>");
				buf.append("<table cols=\"200 200pt\" border=\"0\">");
				for (Observation o : observations) {
					if (o.getParagraphId() != lastParagraphId) {
						Paragraph p = getParagraph(version.getParagraphs(), o.getParagraphId());
						buf.append("<tr>");
						buf.append("<td colspan=\"2\"><br/>");
						buf.append("</td>");
						buf.append("</tr>");
						buf.append("<tr>");
						buf.append("<td colspan=\"2\">Párrafo original: <br/>");
						buf.append(p.getParagraphNumberForDisplay()).append(". ");
						buf.append(convertForRtf(p.getParagraphText()));
						buf.append("</td>");
						buf.append("</tr>");
						lastParagraphId = p.getId();
					}
					DelegateAuditDAO.registerDownloadObservation(user, o);
					ObservationVO vo = (ObservationVO)o;
					buf.append("<tr>");
					buf.append("<td>");
					buf.append("Párrafo: ").append(vo.getParagraphNumberForDisplay()).append("<br/>");
					buf.append("Fecha de observación: ").append(simpleDateFormat.format(vo.getCreationDate())).append("<br/>");
					buf.append("Delegado: ").append(vo.getName()).append("<br/>");
					buf.append("Delegación: ").append(vo.getCountryName()).append("<br/>");
					buf.append("</td>");
					buf.append("<td>").append(convertForRtf(vo.getObservationText())).append("</td>");
					buf.append("</tr>");
				}
				buf.append("</table>");
			}
		}
		buf.append("</body>");
		buf.append("</html>");
		//System.out.println(buf.toString());
		Tidy tidy = new Tidy();
		tidy.setXHTML(true); 
		tidy.setDocType("omit");
		ByteArrayInputStream byteIn = new ByteArrayInputStream(buf.toString().getBytes());
		ByteArrayOutputStream tidyOut = new ByteArrayOutputStream();
		tidy.parse(byteIn, tidyOut);
		String text = new String(tidyOut.toByteArray());
		//System.out.println(text);
		text = text.replace("xmlns=\"http://www.w3.org/1999/xhtml\"", "");
		javax.xml.transform.TransformerFactory tFactory = javax.xml.transform.TransformerFactory.newInstance();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(SystemConfig.getXHTML2FO()); // TODO Cache
			javax.xml.transform.Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(inputStream));
			ByteArrayOutputStream fo = new ByteArrayOutputStream();
			transformer.transform(new StreamSource(new ByteArrayInputStream(text.getBytes())), new StreamResult(fo));
			
			System.out.println(fo.toString());
			final InputSource input = new InputSource(new ByteArrayInputStream(fo.toByteArray()));
			OutputStreamWriter out = new OutputStreamWriter(output);
			new Converter(input,out,Converter.createConverterOption ());
			out.flush();
			out.close();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	private static Paragraph getParagraph(List<Paragraph> paragraphs, int paragraphId) {
		for (Paragraph p : paragraphs) {
			if (p.getId() == paragraphId) {
				return p;
			}
		}
		return null;
	}

	private static String convertForRtf(String observationText) {
		StringBuffer firstPass = new StringBuffer();
		{
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<span style=\"color: rgb\\(([0-9]+), ([0-9]+), ([0-9]+)\\);\">");
			Matcher matcher = pattern.matcher(observationText);
			int previous = 0;
			while (matcher.find()) {
				firstPass.append(observationText.substring(previous, matcher.start()));
				Integer r = Integer.valueOf(matcher.group(1));
				String rhex = Integer.toHexString(r);  
				if (rhex.length() == 1) {
					rhex = "0" + rhex;
				}
				Integer g = Integer.valueOf(matcher.group(2));
				String ghex = Integer.toHexString(g);  
				if (ghex.length() == 1) {
					ghex = "0" + ghex;
				}
				Integer b = Integer.valueOf(matcher.group(3));
				String bhex = Integer.toHexString(b);  
				if (bhex.length() == 1) {
					bhex = "0" + bhex;
				}
				firstPass.append("<font color=\"#").append(rhex).append(ghex).append(bhex).append("\">");
				previous = matcher.end();
			}
			firstPass.append(observationText.substring(previous, observationText.length()));
		}
		StringBuffer secondPass = new StringBuffer();
		String firstPassResult = firstPass.toString();
		{
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<span style=\"background-color: rgb\\(([0-9]+), ([0-9]+), ([0-9]+)\\);\">");
			Matcher matcher = pattern.matcher(firstPassResult);
			int previous = 0;
			while (matcher.find()) {
				secondPass.append(firstPassResult.substring(previous, matcher.start()));
				Integer r = Integer.valueOf(matcher.group(1));
				String rhex = Integer.toHexString(r);  
				if (rhex.length() == 1) {
					rhex = "0" + rhex;
				}
				Integer g = Integer.valueOf(matcher.group(2));
				String ghex = Integer.toHexString(g);  
				if (ghex.length() == 1) {
					ghex = "0" + ghex;
				}
				Integer b = Integer.valueOf(matcher.group(3));
				String bhex = Integer.toHexString(b);  
				if (bhex.length() == 1) {
					bhex = "0" + bhex;
				}
				secondPass.append("<font background-color=\"#").append(rhex).append(ghex).append(bhex).append("\">");
				previous = matcher.end();
			}
			secondPass.append(firstPassResult.substring(previous, firstPassResult.length()));
		}
		
		String resultString = secondPass.toString();
		//resultString = resultString.replaceAll("<span style=\"background-color: rgb\\(([0-9]+), ([0-9]+), ([0-9]+)\\);\">", "<font>"); // TODO manejar background color
		resultString = resultString.replaceAll("</span>", "</font>");
		return resultString;
	}
	
	public static void main(String[] args) {
		System.out.println(convertForRtf("<span style=\"color: rgb(255, 0, 0);\">pepe <span style=\"background-color: rgb(255, 255, 0);\">background</span></span><br/>" + 
				"<span style=\"color: rgb(255, 0, 0);\">bold rojo</span><br />"+ 
				"<span style=\"color: rgb(255, 0, 0);\">italica rojo<br />"));// + 
//				"<u>underline rojo</u><br />" + 
//				"<strike>strike rojo</strike></span><br />" + 
//				"<strong><span style=\"background-color: rgb(255, 255, 0);\">bold amarillo</span></strong><br />" + 
//				"<em><span style=\"background-color: rgb(255, 255, 0);\">italica amarillo</span></em><br />" + 
//				"<u><span style=\"background-color: rgb(255, 255, 0);\">underline amarillo</span></u><br />" + 
//				"<strike><span style=\"background-color: rgb(255, 255, 0);\">strike amarillo</span></strike>"));
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
