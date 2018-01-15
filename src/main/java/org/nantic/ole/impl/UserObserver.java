package org.nantic.ole.impl;

import org.nantic.ole.IUserListener;
import org.nantic.ole.IUserObserver;
import org.nantic.ole.UserEvent;

import java.util.ArrayList;
import java.util.List;

public class UserObserver implements IUserObserver {

	public UserObserver() {
		this.listeners = new ArrayList<IUserListener>();
	}
	
	@Override
	public void addUserListener(IUserListener newListener) {
		if(newListener == null)
			return;
		if(this.listeners ==null)
			this.listeners = new ArrayList<IUserListener>();
		if(this.listeners.contains(newListener))
			return;
		this.listeners.add(newListener);
	}

	@Override
	public void removeUserListener(IUserListener oldListener) {
		if(oldListener == null || this.listeners == null || !this.listeners.contains(oldListener))
			return;
		this.listeners.remove(oldListener);
	}

	@Override
	public void notifyUserListener(UserEvent event) {
		if(event == null || this.listeners == null || this.listeners.isEmpty())
			return;
		List<IUserListener> tmpListeners;
		synchronized (this.listeners) {
			tmpListeners = new ArrayList<IUserListener>(this.listeners);
		}
		for(IUserListener listener : tmpListeners){
			listener.performedUserEvent(event);
		}
	}
	
	private List<IUserListener> listeners;
	
}
