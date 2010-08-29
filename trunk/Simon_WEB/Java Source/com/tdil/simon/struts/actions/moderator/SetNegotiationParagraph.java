package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;

public class SetNegotiationParagraph extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String paragraphNumber = request.getParameter("paragraphNumber");
		final int pNumber = Integer.valueOf(paragraphNumber);
		
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				Version version = VersionDAO.getVersionUnderNegotiation();
				Paragraph paragraph = ParagraphDAO.getParagraph(version.getId(), pNumber);
				Site delegateSite = Site.getDELEGATE_SITE();
				delegateSite.setDataId(paragraph.getId());
				SiteDAO.updateSite(delegateSite);
			}
		});
		
		HashMap hm = new HashMap();
		hm.put("negotiatingParagraph", paragraphNumber);
		JSONObject json = JSONObject.fromObject(hm);
		response.setHeader("X-JSON", json.toString());
		return mapping.findForward("ajaxReturn");
	}
	
}
