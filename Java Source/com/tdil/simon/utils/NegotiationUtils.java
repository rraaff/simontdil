package com.tdil.simon.utils;

import java.sql.SQLException;

import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.struts.forms.CreateDocumentForm;

public class NegotiationUtils {

	public static void updateDelegateSiteParagraph(int paragraphId) throws SQLException {
		Site site = Site.getDELEGATE_SITE();
		site.setDataId(paragraphId);
		SiteDAO.updateSite(site);
	}

	public static boolean isInNegotiation(CreateDocumentForm createDocumentForm) throws SQLException {
		if (Site.IN_NEGOTIATION.equals(Site.getDELEGATE_SITE().getStatus())) {
			return Version.IN_NEGOTIATION.equals(createDocumentForm.getVersionStatus());
		} else {
			return false;
		}
	}
}
