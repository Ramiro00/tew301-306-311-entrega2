package com.tew.presentation;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;

@ManagedBean(name="login")
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 3523454636417208464L;
	private String name = "";
	private String password = "";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String verify() {
		// necesario para accede a msgs y a los mensajes en español e ingles de los ficheros
		// de propiedades
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		LoginService login = Factories.services.createLoginService();
		User user = login.verify(name, password);
		if (user != null) {
			if(user.isAgente()) {
				putAgenteInSession(user);
				return "agente";
			}
			else {
				putClienteInSession(user);
				return "cliente";
			}
		}
		// si el usuario no se encuentra
		// se prepara el mensaje que saldra en la vista del cliente
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				bundle.getString("login_form_result_error"), null);
		// se añade al element con id=”msg”
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "login";
	}
	private void putClienteInSession(User user) {
		Map<String, Object> session =
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_CLIENTE", user);
	}
	private void putAgenteInSession(User user) {
		Map<String, Object> session =
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("LOGGEDIN_AGENTE", user);
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "logout";
	}
}