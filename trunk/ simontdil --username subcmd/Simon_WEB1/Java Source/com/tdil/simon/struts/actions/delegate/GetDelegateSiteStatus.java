package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.LoggedUserForm;

public class GetDelegateSiteStatus extends SimonAction implements TransactionalActionWithValue {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoggedUserForm loggedUserForm = new LoggedUserForm();
		loggedUserForm.setUser(this.getLoggedUser(request));
		HashMap<String, String> result = (HashMap<String, String>) TransactionProvider.executeInTransaction(this,
				loggedUserForm);
		JSONObject json = JSONObject.fromObject(result);
//		response.setHeader("X-JSON", json.toString());
		response.getOutputStream().write(json.toString().getBytes());
		return null;
//		return mapping.findForward("ajaxReturn");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		LoggedUserForm loggedUserForm = (LoggedUserForm) form;
		HashMap<String, Object> result = new HashMap<String, Object>();
		Site delegateSite = Site.getDELEGATE_SITE();

		if (Site.NORMAL.equals(delegateSite.getStatus())) {
			result.put("sitestatus", Site.NORMAL);
			return result;
		}
		Document doc = DocumentDAO.getDocumentUnderWork();
		if (doc.isTypeOne() && !loggedUserForm.getUser().isTypeOne()) {
			result.put("sitestatus", Site.NORMAL);
			return result;
		}
		if (doc.isTypeTwo() && !loggedUserForm.getUser().isTypeTwo()) {
			result.put("sitestatus", Site.NORMAL);
			return result;
		}
		if (Site.IN_NEGOTIATION.endsWith(delegateSite.getStatus())) {
			result.put("sitestatus", Site.IN_NEGOTIATION);
			Paragraph paragraph = ParagraphDAO.getParagraph(delegateSite.getDataId());
			if (paragraph != null) {
				result.put("paragraphNumber", String.valueOf(paragraph.getParagraphNumber()));
				result.put("paragraphText", String.valueOf(paragraph.getParagraphText()));
			} else {
				result.put("paragraphNumber", "0");
			}
			return result;
		}
		if (Site.IN_SIGN.endsWith(delegateSite.getStatus())) {
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
		result.put("sitestatus", delegateSite.getStatus());
		return result;
	}

	private void fillSignatures(HashMap<String, Object> result, Version version) throws SQLException {
		List<SignatureVO> all = SignatureDAO.selectSignaturesFor(version.getId());
		List<String> delegates = new ArrayList<String>(all.size());
		List<String> fileNames = new ArrayList<String>(all.size());
		for (SignatureVO signatureVO : all) {
			delegates.add(signatureVO.getDelegateName());
			fileNames.add(signatureVO.getSignatureFileName());
		}
		result.put("delegates", delegates.toArray(new String[0]));
		result.put("fileNames", fileNames.toArray(new String[0]));
	}

}
