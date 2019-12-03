package com.tew.model;


import java.io.Serializable;

public class Agente implements Serializable {

	private static final long serialVersionUID = 3900827839536798702L;
	
	private long id;
	private String password;
	private String login;


	public long getId() {
		return id;
	}
	public void setID(long iD) {
		this.id = iD;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
}
