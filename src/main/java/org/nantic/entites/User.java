package org.nantic.entites;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;

	private String password;

	private String username;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}