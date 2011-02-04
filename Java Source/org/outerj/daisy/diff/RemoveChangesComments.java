package org.outerj.daisy.diff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class RemoveChangesComments {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String content = IOUtils.toString(new FileInputStream("C:/icarus/workspace/simon/DaisyDiff/daisydiff.htm"));
		content = content.replaceAll("\\[cambio[^\\]]*\\]", "");
		content = content.replaceAll("\\[/cambio[^\\]]*\\]", "");
		System.out.println(content);
	}
	
	public static String removeHtml(String text) {
		String content = text;
		content = content.replaceAll("<html>", "");
		content = content.replaceAll("<body>", "");
		content = content.replaceAll("</body>", "");
		content = content.replaceAll("</html>", "");
		while(content.startsWith("\n") || content.startsWith("\r")) {
			content = content.substring(1, content.length());
		}
		while(content.endsWith("\n") || content.endsWith("\r")) {
			content = content.substring(0, content.length() - 1);
		}
		return content;
	}
	
	public static String clean(String text) {
		String content = text;
		content = content.replaceAll("\\[cambio[^\\]]*\\]", "");
		content = content.replaceAll("\\[/cambio[^\\]]*\\]", "");
		content = content.replaceAll("<html>", "");
		content = content.replaceAll("<body>", "");
		content = content.replaceAll("</body>", "");
		content = content.replaceAll("</html>", "");
		while(content.startsWith("\n") || content.startsWith("\r")) {
			content = content.substring(1, content.length());
		}
		while(content.endsWith("\n") || content.endsWith("\r")) {
			content = content.substring(0, content.length() - 1);
		}
		return content;
	}
}
