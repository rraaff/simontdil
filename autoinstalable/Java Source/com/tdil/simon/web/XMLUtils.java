package com.tdil.simon.web;

import java.io.ByteArrayOutputStream;

import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.CountryVO;
import com.tdil.simon.data.valueobjects.LastLoginVO;
import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;
import com.tdil.simon.data.valueobjects.UserVO;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.data.valueobjects.VersionForListVO;
import com.tdil.simon.data.valueobjects.VersionNumberVO;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.thoughtworks.xstream.XStream;

public class XMLUtils {

	public static final String asAXML(Object o) {
		XStream xstream = new XStream();
		addAliasForAllClasses(xstream);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		xstream.toXML(o, baos);
		byte[] result = baos.toByteArray();
		return new String(result);
	}

	private static void addAliasForAllClasses(XStream xstream) {
		xstream.alias("ActionResponse", ActionResponse.class);
		xstream.alias("Error", com.tdil.simon.actions.response.Error.class);
		xstream.alias("Warning", com.tdil.simon.actions.response.Warning.class);
		xstream.alias("ValidationError", ValidationError.class);
		// persistent objects
		xstream.alias("Country", Country.class);
		xstream.alias("SystemUser", SystemUser.class);
		xstream.alias("Document", Document.class);
		xstream.alias("Version", Version.class);
		xstream.alias("Paragraph", Paragraph.class);
		xstream.alias("Observation", Observation.class);
		xstream.alias("Site", Site.class);
		xstream.alias("ReferenceDocument", ReferenceDocument.class);
		xstream.alias("ReferenceDocument", ReferenceDocumentVO.class);
		xstream.alias("Version", VersionVO.class);
		xstream.alias("Observation", ObservationVO.class);
		xstream.alias("Country", CountryVO.class);
		xstream.alias("User", UserVO.class);
		xstream.alias("Category", Category.class);
		xstream.alias("Version", VersionForListVO.class);
		xstream.alias("VersionNumber", VersionNumberVO.class);
		xstream.alias("LastLogin", LastLoginVO.class);
	}
}
