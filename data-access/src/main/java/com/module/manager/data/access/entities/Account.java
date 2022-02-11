package com.module.manager.data.access.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@NamedQueries({
	@NamedQuery(name = "loadUserByUserName", query = "select A from Account A where A.username = :username")
})
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ACCOUNT_KEY")
	private int key;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "ENABLED")
	private boolean enabled;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
