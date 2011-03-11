package com.tdil.simon.actions.impl.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.CategoryValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.LoggerProvider;

public class AddCategoryAction extends AbstractAction implements TransactionalAction {


	private String name;

	private static Logger getLog() {
		return LoggerProvider.getLogger(AddCategoryAction.class);
	}
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.MODERATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.name = req.getParameter("name");
	}

	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		this.name = CategoryValidation.validateName(this.name, "name", validation);
	}

	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}

	public void executeInTransaction() throws SQLException, ValidationException {
		Category exists = CategoryDAO.getCategory(this.name);
		if (exists != null) {
			throw new ValidationException(new ValidationError(ValidationErrors.CATEGORY_ALREADY_EXISTS));
		}
		Category category = new Category();
		category.setName(this.name);
		category.setDeleted(false);
		CategoryDAO.insertCategory(category);
	}

}
