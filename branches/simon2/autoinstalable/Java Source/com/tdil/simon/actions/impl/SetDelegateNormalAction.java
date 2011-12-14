package com.tdil.simon.actions.impl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.database.TransactionProvider;
/**
 * @deprecated
 * @author mgodoy
 *
 */
public class SetDelegateNormalAction extends AbstractAction implements TransactionalAction {

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.MODERATOR;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		// TODO ver si hay que validar algo
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		Site.setDELEGATE_SITE(SiteDAO.getSite(Site.DELEGATE));
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Site site = SiteDAO.getSite(Site.DELEGATE);
		site.setStatus(Site.NORMAL);
		site.setDataId(0);
		SiteDAO.updateSite(site);
	}
}
