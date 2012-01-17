package com.tdil.simon.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.test.utils.RandomUtils;
import com.tdil.simon.utils.ImportExportResourceBundles;
import com.tdil.simon.utils.ResourceBundleExcelData;

public class TestResourceBundles extends SimonTest {

	public void testExportImport() throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				try {
					File exported = new File(System.getProperty("java.io.tmpdir") + "/" + "rb.xls");
					if (exported.exists()) {
						FileUtils.forceDelete(exported);
					}
					exported = new File(System.getProperty("java.io.tmpdir") + "/" + "rbimportadd.xls");
					if (exported.exists()) {
						FileUtils.forceDelete(exported);
					}
					List<String> english = new ArrayList<String>();
					english.add("English");
					ImportExportResourceBundles.exportResourceBundlesAsExcel(new FileOutputStream(System.getProperty("java.io.tmpdir") + "/" + "en_rb.xls"), english);					
					// TODO asserts o algo asi
					 
					String newLang = RandomUtils.randomString("EqKey-");
					
					ResourceBundleExcelData rbData = new ResourceBundleExcelData();
					rbData.readFrom(new FileInputStream(System.getProperty("java.io.tmpdir") + "/" + "en_rb.xls"));
					
					FileOutputStream fout = new FileOutputStream(System.getProperty("java.io.tmpdir") + "/" + "rbimportadd.xls");
					writeExcelDataForImportAdd(newLang, rbData, fout);
					fout.close();
					ImportExportResourceBundles.importResourceBundlesFromExcel(new FileInputStream(System.getProperty("java.io.tmpdir") + "/" + "rbimportadd.xls"));
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	protected void writeExcelDataForImportAdd(String newLang, ResourceBundleExcelData rbData, FileOutputStream fout) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		List<String> allLanguages = new ArrayList<String>();
		allLanguages.add(newLang);
		HSSFSheet sheet = wb.createSheet("Translations");
		ImportExportResourceBundles.createHeader(sheet, allLanguages);
		int i = 1;
		for (ArrayList<String> row : rbData.getRows()) {
			String context = row.get(rbData.getContextIndex());
			String key = row.get(rbData.getKeyIndex());
			createRow(sheet, i, context, key, key);
			i = i + 1;
		}
		wb.write(fout);
	}

	public static void createRow(HSSFSheet sheet, int position, String context, String key, String value) {
		HSSFRow excelRow = sheet.createRow(position);
		HSSFCell cell = excelRow.createCell((short) 0);
		cell.setCellValue(context);
		cell = excelRow.createCell((short) 1);
		cell.setCellValue(key);
		cell = excelRow.createCell((short) 2);
		cell.setCellValue(value);
	}
}
