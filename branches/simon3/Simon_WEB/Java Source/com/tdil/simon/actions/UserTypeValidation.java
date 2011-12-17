package com.tdil.simon.actions;

import com.tdil.simon.data.model.SystemUser;

public enum UserTypeValidation {

	ADMINISTRATOR {
		@Override
		public boolean isValid(SystemUser user) {
			return user.isAdministrator();
		}
	},
	MODERATOR {
		@Override
		public boolean isValid(SystemUser user) {
			return user.isModerator();
		}
	},
	ASSISTANT {
		@Override
		public boolean isValid(SystemUser user) {
			return user.isAssistant();
		}
	},
	DELEGATE {
		@Override
		public boolean isValid(SystemUser user) {
			return user.isDelegate();
		}
	},
	DELEGATE_AND_SIGN {
		@Override
		public boolean isValid(SystemUser user) {
			return user.isDelegate() && user.isCanSign();
		}
	},
	ANONYMOUS {
		@Override
		public boolean isValid(SystemUser user) {
			return true;
		}
	};
	
	public abstract boolean isValid(SystemUser user);
	
	public static final boolean isValid(SystemUser user, UserTypeValidation roles[]) {
		for (UserTypeValidation userTypeValidation : roles) {
			if (userTypeValidation.isValid(user)) {
				return true;
			}
		}
		return false;
	}

}
