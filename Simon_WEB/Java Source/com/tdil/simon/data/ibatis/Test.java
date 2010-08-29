package com.tdil.simon.data.ibatis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.model.Signature;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;

public class Test {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, ParseException {
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml");
		IBatisManager.sqlMapper.startTransaction();
//		Country argentina = new Country();
//		argentina.setName("venezuela");
//		argentina.setDeleted(false);
//		CountryDAO.insertCountry(argentina);
//		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml");
//		IBatisManager.sqlMapper.startTransaction();
//		Country c = CountryDAO.getCountry(1);
//		CountryDAO.logicallyDeleteCountry(c);
//		List not = CountryDAO.selectNotDeletedCountries();
//		System.out.println(XMLUtils.asAXML(not));
//		List all = CountryDAO.selectAllCountries();
//		System.out.println(all);
//		Country c = CountryDAO.getCountry("argentina");
//		System.out.println(c);
//		com.tdil.simon.data.model.Signature signature = new com.tdil.simon.data.model.Signature();
//		signature.setVersionId(1);
//		signature.setUserId(2);
//		signature.setImage(IOUtils.toByteArray(new FileInputStream("C:/simon/tmp/image.jpg")));
//		signature.setVideo(IOUtils.toByteArray(new FileInputStream("C:/simon/tmp/video.wmv")));
//		SignatureDAO.insertSignature(signature);
//		com.tdil.simon.data.model.Signature signature = new com.tdil.simon.data.model.Signature();
//		signature.setVersionId(1);
//		signature.setUserId(2);
//		Signature complete = SignatureDAO.getSignatureBlobBy(signature);
//		IOUtils.write(complete.getImage(), new FileOutputStream("C:/simon/tmp/image1.jpg"));
//		IOUtils.write(complete.getVideo(), new FileOutputStream("C:/simon/tmp/video1.wmv"));
		
//		SystemUser sysUSer = new SystemUser();
//		sysUSer.setFirstName("Marcos");
//		sysUSer.setLastName("Godoy");
//		sysUSer.setAdministrator(true);
//		sysUSer.setUsername("mgodoy");
//		sysUSer.setPassword("mgodoy");
//		SystemUserDAO.insertUser(sysUSer);
		
//		SystemUser sysUser = SystemUserDAO.getUser("mgodoy");
//		System.out.println(sysUser);
		
//		Document doc = new Document();
//		doc.setTitle("xxx");
//		doc.setDescription("internalll");
//		DocumentDAO.insertDocument(doc);
//		System.out.println(DocumentDAO.selectAllDocument());
//		Version ver = new Version();
//		ver.setNumber(1);
//		ver.setDescription("pepepepepe");
//		ver.setDocumentId(1);
//		VersionDAO.insertVersion(ver);

//		Site site = new Site();
//		site.setName("Delegate");
//		site.setStatus("Locked");
//		SiteDAO.insertSite(site);
//		System.out.println(SiteDAO.getSite("Delegate"));
		
//		Observation o = new Observation();
//		o.setCreationDate(new Date());
//		o.setObservationText("Segundo");
//		o.setParagraphId(4);
//		o.setUserId(2);
//		ObservationDAO.insertObservation(o);
//		System.out.println(DelegateAuditDAO.selectAllAudit());
		
//		ReferenceDocument ref = new ReferenceDocument();
//		ref.setCategoryId(1);
//		ref.setExtension("PDF");
//		ref.setFileName("pepe.pdf");
//		ref.setTitle("EEE DDDD");
//		ref.setDeleted(false);
//		ReferenceDocumentDAO.insertReferenceDocument(ref);
		HashMap params = new HashMap();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		params.put("countryId", 1);
		params.put("dateMax", simpleDateFormat.parseObject("24/08/2010"));
		System.out.println(ObservationDAO.searchObservations(params));
		IBatisManager.sqlMapper.commitTransaction();
	}
}
