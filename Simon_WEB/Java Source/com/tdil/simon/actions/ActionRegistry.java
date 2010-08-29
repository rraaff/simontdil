package com.tdil.simon.actions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.impl.InitDelegateNegotiationAction;
import com.tdil.simon.actions.impl.InitDelegateSignAction;
import com.tdil.simon.actions.impl.LoginAction;
import com.tdil.simon.actions.impl.LoginWithTemporaryAction;
import com.tdil.simon.actions.impl.LogoutAction;
import com.tdil.simon.actions.impl.RequestPasswordResetAction;
import com.tdil.simon.actions.impl.SetDelegateNormalAction;
import com.tdil.simon.actions.impl.admin.AddCountryAction;
import com.tdil.simon.actions.impl.admin.AddDelegateAction;
import com.tdil.simon.actions.impl.admin.AddSystemUserAction;
import com.tdil.simon.actions.impl.admin.DeleteCountryAction;
import com.tdil.simon.actions.impl.admin.DeleteDelegateAction;
import com.tdil.simon.actions.impl.admin.DeleteSystemUserAction;
import com.tdil.simon.actions.impl.admin.ListCountryAction;
import com.tdil.simon.actions.impl.admin.ListDelegateAction;
import com.tdil.simon.actions.impl.admin.ListDelegateForCountryAction;
import com.tdil.simon.actions.impl.admin.ListSystemUserAction;
import com.tdil.simon.actions.impl.admin.ModifyCountryAction;
import com.tdil.simon.actions.impl.admin.ModifyDelegateAction;
import com.tdil.simon.actions.impl.admin.ModifySystemUserAction;
import com.tdil.simon.actions.impl.admin.ModifySystemUserPermissionsAction;
import com.tdil.simon.actions.impl.admin.ResetPasswordsAction;
import com.tdil.simon.actions.impl.delegate.AddObservationAction;
import com.tdil.simon.actions.impl.delegate.DelegateHomeAction;
import com.tdil.simon.actions.impl.delegate.ListObservationsForVersionAction;
import com.tdil.simon.actions.impl.delegate.SearchObservationsAction;
import com.tdil.simon.actions.impl.delegate.ViewLastVersionForDocumentAction;
import com.tdil.simon.actions.impl.delegate.ViewVersionAction;
import com.tdil.simon.actions.impl.moderator.AddCategoryAction;
import com.tdil.simon.actions.impl.moderator.AddDocumentAction;
import com.tdil.simon.actions.impl.moderator.AddReferenceDocumentAction;
import com.tdil.simon.actions.impl.moderator.DeleteCategoryAction;
import com.tdil.simon.actions.impl.moderator.DeleteReferenceDocumentAction;
import com.tdil.simon.actions.impl.moderator.DeleteVersionAction;
import com.tdil.simon.actions.impl.moderator.ListCategoryAction;
import com.tdil.simon.actions.impl.moderator.ListNotDeletedCategoryAction;
import com.tdil.simon.actions.impl.moderator.ListReferenceDocumentAction;
import com.tdil.simon.actions.impl.moderator.ListVersionAction;
import com.tdil.simon.actions.impl.moderator.ListVersionForDocumentAction;
import com.tdil.simon.actions.impl.moderator.ModifyCategoryAction;
import com.tdil.simon.actions.impl.moderator.ModifyDocumentAction;
import com.tdil.simon.actions.impl.moderator.ModifyReferenceDocumentAction;
import com.tdil.simon.utils.LoggerProvider;

public class ActionRegistry {

	private static final Logger Log = LoggerProvider.getLogger(ActionRegistry.class);
	private static Map<String, AbstractAction> actions;
	
	static {
		actions = new ConcurrentHashMap<String, AbstractAction>();
		
		// Administrator //
		// Country ABM
		actions.put("listCountry", new ListCountryAction());
		actions.put("addCountry", new AddCountryAction());
		actions.put("modifyCountry", new ModifyCountryAction());
		actions.put("deleteCountry", new DeleteCountryAction());
		
//		actions.put("addUser", new AddUserAction());
//		actions.put("listUser", new ListUserAction());
		
		// Delegate ABM
		actions.put("listDelegate", new ListDelegateAction());
		actions.put("listDelegateForCountry", new ListDelegateForCountryAction());
		actions.put("addDelegate", new AddDelegateAction());
		actions.put("modifyDelegate", new ModifyDelegateAction());
		actions.put("deleteDelegate", new DeleteDelegateAction());
		
		// SystemUser ABM
		actions.put("listSystemUser", new ListSystemUserAction());
		actions.put("addSystemUser", new AddSystemUserAction());
		actions.put("modifySystemUser", new ModifySystemUserAction());
		actions.put("deleteSystemUser", new DeleteSystemUserAction());
		actions.put("modifySystemUserPermission", new ModifySystemUserPermissionsAction());
		
		// Password
		actions.put("resetPassword", new ResetPasswordsAction());
		
		// Moderator
		actions.put("listCategory", new ListCategoryAction());
		actions.put("listNotDeletedCategory", new ListNotDeletedCategoryAction());
		actions.put("addCategory", new AddCategoryAction());
		actions.put("modifyCategory", new ModifyCategoryAction());
		actions.put("deleteCategory", new DeleteCategoryAction());
		
		actions.put("addReference", new AddReferenceDocumentAction());
		actions.put("listReference", new ListReferenceDocumentAction());
		actions.put("deleteReference", new DeleteReferenceDocumentAction());
		actions.put("modifyReference", new ModifyReferenceDocumentAction());
		
		actions.put("addDocument", new AddDocumentAction());
		actions.put("deleteVersion", new DeleteVersionAction());
		actions.put("modifyDocument", new ModifyDocumentAction());
		
		actions.put("listVersion", new ListVersionAction());
		actions.put("listVersionForDocument", new ListVersionForDocumentAction());
		
		
		// Delegate
		actions.put("delegateHome", new DelegateHomeAction());
		actions.put("viewLastVersion", new ViewLastVersionForDocumentAction());
		actions.put("viewVersion", new ViewVersionAction());
		
		actions.put("listObservationsForVersion", new ListObservationsForVersionAction());
		actions.put("addObservation", new AddObservationAction());
		actions.put("searchObservation", new SearchObservationsAction());
		
//		actions.put("initDelegateNegotiation", new InitDelegateNegotiationAction());
//		actions.put("initDelegateSign", new InitDelegateSignAction());
//		actions.put("setDelegateNormal", new SetDelegateNormalAction());
		// Designer
	
		// Public
		actions.put("login", new LoginAction());
		actions.put("loginAndChange", new LoginWithTemporaryAction());
		actions.put("logout", new LogoutAction());
		actions.put("requesPasswordReset", new RequestPasswordResetAction());
	}
	
	public static AbstractAction getActionFor(String key) {
		AbstractAction result = actions.get(key);
		if (result != null) {
			try {
				return (AbstractAction)result.clone();
			} catch (CloneNotSupportedException e) {
				Log.error(e.getMessage(), e);
				return null;
			}
		}
		return null;
	}
}
