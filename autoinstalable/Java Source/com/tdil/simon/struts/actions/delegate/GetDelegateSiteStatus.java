package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.LoggedUserForm;
import com.tdil.simon.utils.DelegateSiteCache;

public class GetDelegateSiteStatus extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.DELEGATE, UserTypeValidation.ASSISTANT, 
		UserTypeValidation.TRANSLATOR};
	
	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoggedUserForm loggedUserForm = new LoggedUserForm();
		loggedUserForm.setUser(this.getLoggedUser(request));
		loggedUserForm.setParagraphVersion(request.getParameter("paragraphVersion"));
		loggedUserForm.setParagraphNumber(request.getParameter("paragraphNumber"));
		HashMap<String, String> result = (HashMap<String, String>) executeInTransaction(loggedUserForm);
//		System.out.println(result);
		this.writeJsonResponse(result, response);
		return null;
	}

	// TODO no es en transaccion
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		LoggedUserForm loggedUserForm = (LoggedUserForm) form;
		HashMap<String, Object> result = new HashMap<String, Object>();

		String delegateSiteStatus = DelegateSiteCache.getDelegateSiteStatus();
		if (Site.NORMAL.equals(delegateSiteStatus)) {
			result.put("sitestatus", Site.NORMAL);
			return result;
		}
		Document doc = DelegateSiteCache.getDocumentUnderWork();
		if (loggedUserForm.getUser().isDelegate() && !loggedUserForm.getUser().hasPermissionFor(doc)) {
			result.put("sitestatus", Site.NORMAL);
			return result;
		}
		if (Site.IN_NEGOTIATION.endsWith(delegateSiteStatus)) {
			result.put("sitestatus", Site.IN_NEGOTIATION);
			if (loggedUserForm.getUser().isAssistant() || loggedUserForm.getUser().isTranslator()) {
				return result;
			}
			Paragraph paragraph = DelegateSiteCache.getNegotiatedParagraph();
			if (paragraph != null) {
				int pVersion = Integer.valueOf(loggedUserForm.getParagraphVersion());
				int pNumber = Integer.valueOf(loggedUserForm.getParagraphNumber());
				if (paragraph.getParagraphNumber() == pNumber && paragraph.getVersionNumber() == pVersion) {
					result.put("paragraphNumber", paragraph.getParagraphNumber());
					result.put("paragraphComment", "");
					result.put("paragraphText", "");
					result.put("paragraphVersion", loggedUserForm.getParagraphVersion());
					result.put("hasTranslation", DelegateSiteCache.getPortuguesParagrah() != null ? "true" : "false");
				} else {
					result.put("paragraphNumber", String.valueOf(paragraph.getParagraphNumber()));
					result.put("paragraphComment", paragraph.getNumberDetail());
					result.put("paragraphText", String.valueOf(paragraph.getParagraphText()));
					result.put("paragraphVersion", String.valueOf(paragraph.getVersionNumber()));
					result.put("hasTranslation", DelegateSiteCache.getPortuguesParagrah() != null ? "true" : "false");
				}
			} else {
				result.put("paragraphNumber", "0");
				result.put("paragraphComment", "");
				result.put("hasTranslation", "false");
			}
			return result;
		}
		if (Site.IN_SIGN.endsWith(delegateSiteStatus)) {
			Version version = VersionDAO.getVersionUnderWork();
			if (!loggedUserForm.getUser().isCanSign()) {
				result.put("sitestatus", Site.SIGN_SHOW);
				fillSignatures(result, version);
				return result;
			}
			if (SignatureDAO.exist(version.getId(), loggedUserForm.getUser().getId())) {
				result.put("sitestatus", Site.SIGN_SHOW);
				fillSignatures(result, version);
				return result;
			} else {
				result.put("sitestatus", Site.IN_SIGN);
				return result;
			}
		}
		result.put("sitestatus", delegateSiteStatus);
		return result;
	}

	private void fillSignatures(HashMap<String, Object> result, Version version) throws SQLException {
		List<SignatureVO> all = DelegateSiteCache.getAllSignatures();
		List<String> delegates = new ArrayList<String>(all.size());
		List<String> countries = new ArrayList<String>(all.size());
		List<String> jobs = new ArrayList<String>(all.size());
		List<String> fileNames = new ArrayList<String>(all.size());
		List<String> flags = new ArrayList<String>(all.size());
		for (SignatureVO signatureVO : all) {
			delegates.add(signatureVO.getDelegateName());
			countries.add(signatureVO.getCountryDescription().toUpperCase());
			jobs.add(signatureVO.getJob());
			fileNames.add(signatureVO.getSignatureFileName());
			flags.add(String.valueOf(signatureVO.getCountryId()));
		}
		result.put("delegates", delegates.toArray(new String[0]));
		result.put("countries", countries.toArray(new String[0]));
		result.put("jobs", jobs.toArray(new String[0]));
		result.put("fileNames", fileNames.toArray(new String[0]));
		result.put("flags", flags.toArray(new String[0]));
	}

}
