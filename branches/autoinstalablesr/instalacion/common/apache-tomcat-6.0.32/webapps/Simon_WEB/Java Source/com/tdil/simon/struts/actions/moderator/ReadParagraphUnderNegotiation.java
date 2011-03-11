package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.NegotiateParagraphForm;

/**
 * @deprecated
 * @author mgodoy
 * 
 */
public class ReadParagraphUnderNegotiation extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Site site = Site.getDELEGATE_SITE();
		Paragraph underNegotiation = ParagraphDAO.getParagraph(site.getDataId());
		NegotiateParagraphForm negotiateForm = (NegotiateParagraphForm) form;
		negotiateForm.setParagraphId(underNegotiation.getId());
		negotiateForm.setParagraphNumber(String.valueOf(underNegotiation.getParagraphNumber()));
		negotiateForm.setParagraphText(underNegotiation.getParagraphText());
		return mapping.findForward("success");
	}
}
