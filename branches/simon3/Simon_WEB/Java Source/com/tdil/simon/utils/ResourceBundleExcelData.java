package com.tdil.simon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.tdil.simon.data.model.ResourceBundle;

public class ResourceBundleExcelData {

	private int contextIndex = 0;
	public int getContextIndex() {
		return contextIndex;
	}


	private int keyIndex = 0;
	private Map<String, Integer> languages = new HashMap<String, Integer>();

	private ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
	
	/** Este codigo solo debe usarse para testing */
	public ArrayList<ArrayList<String>> getRows() {
		return rows;
	}

	/** Este codigo solo debe usarse para testing */
	public Map<String, Integer> getLanguages() {
		return languages;
	}
	
	public int getKeyIndex() {
		return keyIndex;
	}
	
	public void add(String headerName, int headerPosition) {
		if (headerName.equals("context")) {
			contextIndex = headerPosition;
		} else {
			if (headerName.equals("key")) {
				keyIndex = headerPosition;
			} else {
				languages.put(headerName, headerPosition);
			}
		}
	}
	
	public List<ResourceBundle> getResourceBundles() {
		List<ResourceBundle> rbs = new ArrayList<ResourceBundle>();
		for (ArrayList<String> row : rows) {
			rbs.addAll(getResourceBundles(row));
		}
		return rbs;
	}

	private List<ResourceBundle> getResourceBundles(ArrayList<String> rbRow) {
		List<ResourceBundle> rbs = new ArrayList<ResourceBundle>();
		String context = rbRow.get(contextIndex);
		String key = rbRow.get(keyIndex);
		for (Map.Entry<String, Integer> langEntry : languages.entrySet()) {
			ResourceBundle rb = new ResourceBundle();
			rb.setRbContext(context);
			rb.setRbKey(key);
			rb.setRbLanguage(langEntry.getKey());
			rb.setRbValue(rbRow.get(langEntry.getValue()));
			rbs.add(rb);
		}
		return rbs;
	}
	
	
	public void readFrom(InputStream inputStream) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(inputStream);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// Only import firt sheet
		HSSFSheet sheet = wb.getSheetAt(0);
		
		sheet = wb.getSheetAt(0);
		HSSFRow row;
		int firstRow = sheet.getFirstRowNum();
		int lastRow = sheet.getLastRowNum();
		row = sheet.getRow(firstRow);
		int maxFirstRowIndex = row.getLastCellNum();
		for (int j = 0; j < maxFirstRowIndex; j++) {
			HSSFCell cell = row.getCell((short)j);
			String value = null;
			if (cell != null) {
				value = cell.getStringCellValue();
				this.add(value, j);
			} else {
				this.add(null, j);
			}
		}
		for (int i = firstRow + 1; i <= lastRow; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				maxFirstRowIndex = row.getLastCellNum();
				ArrayList<String> rowArrayList = new ArrayList<String>();
				for (int j = 0; j < maxFirstRowIndex; j++) {
					HSSFCell cell = row.getCell((short)j);
					String value = null;
					if (cell != null) {
						value = cell.getStringCellValue();
						rowArrayList.add(value);
					} else {
						rowArrayList.add(null);
					}
				}
				rows.add(rowArrayList);
			}
		}
	}
}
