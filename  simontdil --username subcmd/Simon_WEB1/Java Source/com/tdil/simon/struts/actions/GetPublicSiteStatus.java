package com.tdil.simon.struts.actions;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.utils.DelegateSiteCache;

public class GetPublicSiteStatus extends AjaxSimonAction {

	public static final String SITESTATUS = "sitestatus";

	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> result = (HashMap<String, String>) basicExecute(request.getParameter("paragraphVersion"), request.getParameter("paragraphNumber"));
		System.out.println(result);
		this.writeJsonResponse(result, response);
		return null;
	}
	
	public static HashMap<String, String> basicExecute(String paramVersion, String paramNumber) throws SQLException, ValidationException {
		HashMap<String, String> result = new HashMap<String, String>();

		String publicSiteStatus = Site.getPUBLIC_SITE().getStatus();
		if (Site.NORMAL.equals(publicSiteStatus)) {
			result.put(SITESTATUS, Site.NORMAL);
			return result;
		}
		if (Site.SIGN_SHOW.equals(publicSiteStatus)) {
			result.put(SITESTATUS, Site.SIGN_SHOW);
			return result;
		}
		String delegateSiteStatus = DelegateSiteCache.getDelegateSiteStatus();
		if (Site.IN_NEGOTIATION.equals(delegateSiteStatus)) {
			result.put(SITESTATUS, Site.IN_NEGOTIATION);
			Paragraph paragraph = DelegateSiteCache.getNegotiatedParagraph();
			if (paragraph != null) {
				int pVersion = Integer.valueOf(paramVersion);
				int pNumber = Integer.valueOf(paramNumber);
				if (paragraph.getParagraphNumber() == pNumber && paragraph.getVersionNumber() == pVersion) {
					result.put("paragraphNumber", String.valueOf(paragraph.getParagraphNumber()));
					result.put("paragraphText", "");
					result.put("originalText", "");
					result.put("paragraphVersion", paramVersion);
				} else {
					Paragraph original = DelegateSiteCache.getOriginalParagraph();
					result.put("paragraphNumber", String.valueOf(paragraph.getParagraphNumber()));
					result.put("paragraphText", String.valueOf(paragraph.getParagraphText()));
					result.put("originalText", String.valueOf(original.getParagraphText()));
					result.put("paragraphVersion", String.valueOf(paragraph.getVersionNumber()));
					result.put("paragraphNumberForDisplay", paragraph.getParagraphNumberForDisplay());
				}
			} else {
				result.put("paragraphText", "-");
				result.put("originalText", "-");
				result.put("paragraphNumber", "0");
			}
			return result;
		}
		if (Site.IN_SIGN.equals(delegateSiteStatus)) {
			result.put(SITESTATUS, Site.IN_SIGN);
			return result;
		}
		result.put(SITESTATUS, Site.NORMAL);
		return result;
	}
	@Override
	protected ActionForward basicExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}
	
	@Override
	protected UserTypeValidation[] getPermissions() {
		return null;
	}

}
