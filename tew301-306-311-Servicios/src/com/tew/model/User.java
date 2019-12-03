package com.tew.model;

import java.io.Serializable;

public class User implements Serializable{

	
	public User(String login) {
		super();
		this.login = login;
	}

	public User(String login, boolean agente) {
		super();
		this.login = login;
		Agente = agente;
	}



	private static final long serialVersionUID = 1L;

	private String login;
	private boolean Agente;
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAgente() {
		return Agente;
	}

	public void setAgente(boolean agente) {
		Agente = agente;
	}
	
}
