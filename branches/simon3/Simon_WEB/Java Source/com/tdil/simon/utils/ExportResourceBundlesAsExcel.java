package com.tdil.simon.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tdil.simon.web.ResourceBundleCache;

public class ExportResourceBundlesAsExcel {

	public static void exportResourceBundlesAsExcel(OutputStream outputStream) throws IOException, SQLException {
		HSSFWorkbook wb = new HSSFWorkbook();
		List<String> allLanguages = ResourceBundleCache.getAllLanguages();
		HSSFSheet sheet = wb.createSheet("Translations");
		createHeader(sheet, allLanguages);
		Map<String, List<String>> toExport = ResourceBundleCache.getResourceBundleForExport();
		int i = 1;
		for (Map.Entry<String, List<String>> entry : toExport.entrySet()) {
			String context = entry.getKey();
			List<String> keys = entry.getValue();
			Collections.sort(keys);
			for (String key : keys) {
				createRow(sheet, i, context, key, allLanguages);
				i = i + 1;
			}
		}
		wb.write(outputStream);
	}
	
	private static void createRow(HSSFSheet sheet, int position, String context, String key, List<String> allLanguages) {
		HSSFRow excelRow = sheet.createRow(position);
		HSSFCell cell = excelRow.createCell((short) 0);
		cell.setCellValue(context);
		cell = excelRow.createCell((short) 1);
		cell.setCellValue(key);
		int i = 2;
		for (String lang : allLanguages) {
			cell = excelRow.createCell((short) i);
			cell.setCellValue(ResourceBundleCache.get(context, key, lang));
			i = i + 1;
		}
	}

	private static void createHeader(HSSFSheet sheet, List<String> allLanguages) {
		HSSFRow excelRow = sheet.createRow(0);
		HSSFCell cell = excelRow.createCell((short) 0);
		cell.setCellValue("context");
		cell = excelRow.createCell((short) 1);
		cell.setCellValue("key");
		int i = 2;
		for (String lang : allLanguages) {
			cell = excelRow.createCell((short) i);
			cell.setCellValue(lang);
			i = i + 1;
		}
	}
}
