package org.outerj.daisy.diff;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.outerj.daisy.diff.html.HTMLDiffer;
import org.outerj.daisy.diff.html.HtmlSaxDiffOutput;
import org.outerj.daisy.diff.html.TextNodeComparator;
import org.outerj.daisy.diff.html.dom.DomTreeBuilder;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ParagraphComparator {

	public static void main(String[] args) throws TransformerConfigurationException, IOException, SAXException {
		String firstResult = RemoveChangesComments.removeHtml(compare("Habia u&ntilde;a vez una vaca", "Habia u&ntilde;a vez un avestruz"));
		//result = unify(result);
//		System.out.println((firstResult));
//		firstResult = addSpaces(firstResult);
		System.out.println((firstResult));
		System.out.println("****");		
		String result = RemoveChangesComments.removeHtml(compare(firstResult, "Habia u&ntilde;a vez un avestruz de mierda"));
//		System.out.println(result);
		System.out.println("****");
		String secondResult = RemoveChangesComments.removeHtml(compare(firstResult, result));
		secondResult = addSpaces(secondResult);
		System.out.println(secondResult);
		
		String third = RemoveChangesComments.removeHtml(compare(firstResult, secondResult));
		System.out.println(third);
////		System.out.println(unify(result));
//		System.out.println(result);
	}
	
//	private static String unify(String changes) {
//		String result = changes;
//		result = StringUtils.replace(result, "[cambio:[cambio:removido]removido]", "[cambio:removido]");
//		result = StringUtils.replace(result, "[/cambio:removido][/cambio:removido]", "[/cambio:removido]");
//		result = StringUtils.replace(result, "[cambio:agregado][cambio:removido]", "[cambio:agregado][cambio:removido]");
//		
//		return result;
//	}

	private static String addSpaces(String firstResult) {
		String temp = StringUtils.replace(firstResult, "[change:", " [change:");
		temp = StringUtils.replace(temp, "][", "] [");
		temp = StringUtils.replace(temp, "[/cambio", " [/cambio");
		temp = StringUtils.replace(temp, "[cambio:estilo]", "[cambio:estilo] ");
		temp = StringUtils.replace(temp, "[cambio:agregado]", "[cambio:agregado] ");
		temp = StringUtils.replace(temp, "[cambio:removido]", "[cambio:removido] ");
		return temp;
	}

	public static String compare(String first, String second) throws TransformerConfigurationException, IOException, SAXException {
		SAXTransformerFactory tf = (SAXTransformerFactory) TransformerFactory.newInstance();

		TransformerHandler result = tf.newTransformerHandler();
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		result.setResult(new StreamResult(byteOut));

		InputStream oldStream = new ByteArrayInputStream(first.getBytes());
		InputStream newStream = new ByteArrayInputStream(second.getBytes());
		XslFilter filter = new XslFilter();

		ContentHandler postProcess = filter.xsl(result, "org/outerj/daisy/diff/htmlheader.xsl");

		Locale locale = Locale.getDefault();
		String prefix = "diff";

		HtmlCleaner cleaner = new HtmlCleaner();

		InputSource oldSource = new InputSource(oldStream);
		InputSource newSource = new InputSource(newStream);

		DomTreeBuilder oldHandler = new DomTreeBuilder();
		cleaner.cleanAndParse(oldSource, oldHandler);
		System.out.print(".");
		TextNodeComparator leftComparator = new TextNodeComparator(oldHandler, locale);

		DomTreeBuilder newHandler = new DomTreeBuilder();
		cleaner.cleanAndParse(newSource, newHandler);
		System.out.print(".");
		TextNodeComparator rightComparator = new TextNodeComparator(newHandler, locale);

		postProcess.startDocument();
		postProcess.startElement("", "diffreport", "diffreport", new AttributesImpl());
		postProcess.startElement("", "diff", "diff", new AttributesImpl());
		HtmlSaxDiffOutput output = new HtmlSaxDiffOutput(postProcess, prefix);

		HTMLDiffer differ = new HTMLDiffer(output);
		differ.diff(leftComparator, rightComparator);
		System.out.print(".");
		postProcess.endElement("", "diff", "diff");
		postProcess.endElement("", "diffreport", "diffreport");
		postProcess.endDocument();
		
		String textResult = byteOut.toString();
//		return unify(textResult);
		return textResult;
	}
}
