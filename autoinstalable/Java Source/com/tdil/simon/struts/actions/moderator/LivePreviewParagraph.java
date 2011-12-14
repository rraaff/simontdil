package com.tdil.simon.struts.actions.moderator;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.utils.DelegateSiteCache;

public class LivePreviewParagraph extends AjaxSimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pText = request.getParameter("pText");
		String parId = request.getParameter("parId");
		
		DelegateSiteCache.livePreview(parId, pText);
		
		this.writeJsonResponse(new HashMap<String, String>(), response);
		return null;
	}


}
