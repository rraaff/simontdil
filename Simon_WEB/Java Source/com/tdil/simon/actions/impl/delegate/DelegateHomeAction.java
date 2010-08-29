package com.tdil.simon.actions.impl.delegate;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.CategoryVO;
import com.tdil.simon.data.valueobjects.DelegateHomeVO;

public class DelegateHomeAction extends AbstractAction {

	private SystemUser user;
	
	@Override
	protected ActionResponse basicExecute(HttpServletRequest req)
			throws ValidationException, SQLException {
		DelegateHomeVO delegateHomeVO = new DelegateHomeVO();
		// TODO listar por tipo de acceso
		List documents = DocumentDAO.selectNotDeletedDocument();
		for (Object obj : documents) {
			Document doc = (Document)obj;
			if (doc.canAccess(this.user)) {
				if (doc.isPrincipal()) {
					delegateHomeVO.setPrincipal(doc);
				} else {
					delegateHomeVO.getSecundary().add(doc);
				}
			}
		}
		List<CategoryVO> categories = CategoryDAO.selectAllCategoriesNotDeletedVO();
		for (CategoryVO cat : categories) {
			cat.setDocuments(ReferenceDocumentDAO.selectAllReferenceDocumentForCategory(cat));
		}
		delegateHomeVO.setCategories(categories);
		return ActionResponse.newOKResponse(delegateHomeVO);
	}
	

	@Override
	protected void basicValidate(HttpServletRequest req,
			ValidationError validation) {
	}

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.DELEGATE;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.user = (SystemUser)req.getSession().getAttribute("user");
	}

}
