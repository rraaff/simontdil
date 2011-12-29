package com.tdil.simon.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.ExportResourceBundlesAsExcel;

public class TestResourceBundles extends SimonTest {

	public void testExport() throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				try {
					ExportResourceBundlesAsExcel.exportResourceBundlesAsExcel(new FileOutputStream("/home/mgodoy/temp/rb.xls"));
					// TODO asserts o algo asi
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
}
