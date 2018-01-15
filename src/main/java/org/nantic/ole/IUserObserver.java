package org.nantic.ole;

public interface IUserObserver {
	void addUserListener(IUserListener newListener);
	void removeUserListener(IUserListener oldListener);
	void notifyUserListener(UserEvent event);
}
