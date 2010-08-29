package com.tdil.simon.struts.actions.delegate;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.data.model.Site;

public class GetDelegateSiteStatus extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Site delegateSite = Site.getDELEGATE_SITE();
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("status", delegateSite.getStatus());
		JSONObject json = JSONObject.fromObject(result);
		response.setHeader("X-JSON", json.toString());
		return mapping.findForward("ajaxReturn");

	}

}
