package com.tdil.simon.actions.impl.moderator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ReferenceDocumentValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.ReferenceDocumentUtils;
import com.tdil.simon.web.Controller;

public class AddReferenceDocumentAction extends AbstractAction implements TransactionalAction {

	private static final Logger Log = LoggerProvider.getLogger(AddReferenceDocumentAction.class);
	
	protected String categoryId;
	protected int categoryIdInt;
	private String title;
	private FileItem fileItem;
	private String docNameAndType[];
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		// Nothing todo
	}
	
	@Override
	public void takeValuesFrom(List<FileItem> fileItems) {
		this.categoryId = Controller.getParameter(fileItems, "categoryId");
		this.title = Controller.getParameter(fileItems, "title");
		this.fileItem = Controller.getFileItem(fileItems, "document");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		this.title = ReferenceDocumentValidation.validateTitle(this.title, "title", validation);
		docNameAndType = ReferenceDocumentValidation.validateDocument(this.fileItem, "document", true, validation);
		this.categoryIdInt = IdValidation.validate(this.categoryId, "categoryId", validation);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		ReferenceDocument exist = ReferenceDocumentDAO.getReferenceDocument(this.title);
		if (exist != null) {
			throw new ValidationException(new ValidationError(ValidationErrors.REFERENCE_DOCUMENT_ALREADY_EXISTS));
		}
		Category category = CategoryDAO.getCategory(this.categoryIdInt);
		if (category == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.CATEGORY_DOES_NOT_EXISTS));
		}
		ReferenceDocument ref = new ReferenceDocument();
		ref.setCategoryId(category.getId());
		ref.setTitle(this.title);
		ref.setFileName(this.docNameAndType[0]);
		ref.setExtension(this.docNameAndType[1]);
		ref.setDeleted(false);
		int id = ReferenceDocumentDAO.insertReferenceDocument(ref);
		try {
			ReferenceDocumentUtils.writeDocumentToDisk(id, this.fileItem);
		} catch (IOException e) {
			throw new ValidationException(new ValidationError(ValidationErrors.REFERENCE_DOCUMENT_CANNOT_BE_WRITTEN));
		}
		
	}
	
}
