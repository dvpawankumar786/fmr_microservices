package com.security.jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user_fmr", uniqueConstraints = @UniqueConstraint(columnNames = {"userName"}, name="USER_UNIQUE_USERNAME"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "bigint unsigned")
	private Integer id;

	@Column(name="username",length=50)
	private String userName;

	@Column(name="password")
	private String password;

	@Column(name="accountexpired")
	private Boolean accountExpired;

	@Column(name="accountlocked")
	private Boolean accountLocked;

	@Column(name="credentialsexpired")
	private Boolean credentialsExpired;

	@Column(name="enabled")
	private Boolean enabled;
	
	@Column(name="userauthorities")
	private String userAuthorities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public Boolean getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(String userAuthorities) {
		this.userAuthorities = userAuthorities;
	}
	
	
}