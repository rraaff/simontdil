package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ListForm;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class DocumentABMAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final ListForm versionListForm = (ListForm) form;

		if (isIndexedOperation(request, "botones", "desactivar")) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						versionListForm.deleteVersion(getIndexClicked(request));
					}
				});
			} catch (ValidationException e) {
				ActionErrors errors = new ActionErrors();
				errors.add(e.asMessages());
				addErrors(request, errors);
			}
		}
		if (isIndexedOperation(request, "botones", "activar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					versionListForm.reactivateVersion(getIndexClicked(request));
				}
			});
		}
		
		if (isIndexedOperationByKey(request, "botones", "activarComentarios")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					versionListForm.enableCommentsForVersion(getIndexClicked(request));
				}
			});
		}
		if (isIndexedOperationByKey(request, "botones", "desactivarComentarios")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					versionListForm.disableCommentsForVersion(getIndexClicked(request));
				}
			});
		}
		
		if (StringUtils.equalsUnescaped(versionListForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "marcarComoRelevantes"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					versionListForm.selectAsRelevant();
				}
			});
		}
		if (StringUtils.equalsUnescaped(versionListForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "marcarComoNoRelevantes"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					versionListForm.deselectAsRelevant();
				}
			});
		}
		
		return mapping.findForward("continue");
	}

	private String getServletInfo() {
		return "listDocumentsForModerator";
	}

}
