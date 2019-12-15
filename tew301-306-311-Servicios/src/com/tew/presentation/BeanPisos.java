package com.tew.presentation;

import java.io.Serializable;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.model.User;

@ManagedBean(name = "control")
@SessionScoped
public class BeanPisos implements Serializable {
	private static final long serialVersionUID = 55555L;

	private Piso[] pisos = null;

	// Uso, inyecci�n de dependencia
	@ManagedProperty(value = "#{piso}")
	private BeanPiso piso;

	public BeanPiso getPiso() {
		return piso;
	}

	public void setPiso(BeanPiso piso) {
		this.piso = piso;
	}

	@ManagedProperty(value = "#{signup}")
	private BeanSignUp signup;

	public BeanSignUp getSignUp() {
		return signup;
	}

	/*
	 * public BeanAlumnos() { iniciaAlumno(null); }
	 */

	public Piso[] getPisos() {
		return (pisos);
	}

	public void setAlumnos(Piso[] pisos) {
		this.pisos = pisos;
	}

	public void iniciaPisos(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		piso.setId(Integer.valueOf((String) bundle.getObject("valorDefectoPisoId")));
		piso.setIdagente(Integer.valueOf((String) bundle.getObject("valorDefectoIdAgente")));
		piso.setPrecio(Integer.valueOf((String) bundle.getObject("valorDefectoPrecio")));
		piso.setDireccion(bundle.getString("valorDefectoDireccion"));
		piso.setCiudad(bundle.getString("valorDefectoCiudad"));
		piso.setAno(Integer.valueOf((String) bundle.getObject("valorDefectoAnyo")));
		piso.setEstado(Integer.valueOf((String) bundle.getObject("valorDefectoEstado")));
		piso.setVisita(false);
	}

	public String listadoagente() {

		PisosService service;
		try {
			service = Factories.services.createPisosService();
			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String listadocliente() {

		PisosService service;
		try {
			service = Factories.services.createPisosService();
			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String baja(Piso piso) {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			service = Factories.services.createPisosService();
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("LOGGEDIN_AGENTE");
			service.deletePiso(piso.getId(), user.getLogin());
			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String edit() {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			piso = (BeanPiso) service.findById(Long.valueOf(piso.getId()));
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String salva() {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("LOGGEDIN_AGENTE");
			service.savePiso(piso, user.getLogin());

			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	@SuppressWarnings("unchecked")
	public boolean filterByPrice(Object value, Object filter, Locale locale) {

		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable<Integer>) value).compareTo(Integer.valueOf(filterText)) > 0;
	}

	@SuppressWarnings("unchecked")
	public boolean filterByPrice2(Object value, Object filter, Locale locale) {

		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable<Integer>) value).compareTo(Integer.valueOf(filterText)) < 0;
	}

	public String guardarSeleccion() {

		PisosService service;
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		user.setLogin("user1@micorreo.com");
		try {
			service = Factories.services.createPisosService();
			pisos = (Piso[]) service.getPisos(user.getLogin()).toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("cellchanged"),
					"\n" + bundle.getString("oldcell") + ": " + oldValue + ", " + bundle.getString("newcell") + ": "
							+ newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	@PostConstruct
	public void init() {
		System.out.println("BeanPisos - PostConstruct");
		piso = (BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("piso"));

		if (piso == null) {
			System.out.println("BeanPisos - No existia");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("piso", piso);
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanPisos - PreDestroy");
	}

}
