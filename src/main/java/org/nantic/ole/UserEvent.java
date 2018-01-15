package org.nantic.ole;

import org.nantic.entites.User;

public class UserEvent {
	
	
	public UserEvent(User users, UserEventStatus eventStatus) {
		this.eventStatus = eventStatus;
		this.users = users;
	}
	
	
	public User getUsers() {
		return users;
	}
	public UserEventStatus getEventStatus() {
		return eventStatus;
	}
	private User users;
	private UserEventStatus eventStatus;
}
