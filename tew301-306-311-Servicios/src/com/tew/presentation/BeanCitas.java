package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.CitasService;
import com.tew.infrastructure.Factories;
import com.tew.model.Cita;
import com.tew.model.Piso;

/**
 * Se añade este atributo de entidad para recibir la cita en concreto
 * selecionada de la tabla o de un formulario. Es necesario inicializarlo para
 * que al entrar desde el formulario de altaCitaForm.xml se puedan dejar los
 * avalores en un objeto existente.
 * 
 */
@ManagedBean(name = "control3")
@SessionScoped
public class BeanCitas implements Serializable {
	private static final long serialVersionUID = 7944042841591604009L;

	private Cita[] citas = null;

	// uso de inteccción de dependencia
	@ManagedProperty(value = "#{cita}")
	private BeanCita cita;

	public BeanCita getCita() {
		return cita;
	}

	public void setCita(BeanCita cita) {
		this.cita = cita;
	}

	@ManagedProperty(value = "#{signup}")
	private BeanSignUp signup;

	public BeanSignUp getSignUp() {
		return signup;
	}

	/*
	 * Se quita el constructor de inicia
	 */

	public Cita[] getCitas() {
		return (citas);
	}

	public void iniciaCitas(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		/*
		 * Obtenemos el archivo de propiedades correspondiente al idioma que tengamos
		 * seleccionado y que viene envuelto en facesContext
		 */
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		cita.setIdPiso(Integer.valueOf((String) bundle.getObject("valorDefectoCIdPiso")));
		cita.setDireccion(bundle.getString("valorDefectoDireccion"));
		cita.setIdCliente(Integer.valueOf((String) bundle.getObject("valorDefectoCIdCliente")));
		cita.setFechaHoraCita(Long.valueOf((String) bundle.getObject("valorDefectCCita")));
		cita.setEstado(Integer.valueOf((String) bundle.getObject("valorDefectCEstado")));
	}

	public String listadocitas() {
		CitasService service;
		try {
			// Acceso a la implementacion de la capa de negocio a traves de la factoria
			service = Factories.services.createCitasService();
			citas = (Cita[]) service.getCitas().toArray(new Cita[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String confirmacitas(Cita cita) {
		CitasService service;
		try {
			service = Factories.services.createCitasService();
			service.confirmaVisita(cita);
			citas = (Cita[]) service.getCitas().toArray(new Cita[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/*
	 * Se inicia correctamente el MBean inyectado si JSF lo hubiera crea y en caso
	 * contrario se crea. (hay que tener en cuenta que es un Bean de sesión) Se
	 * usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed
	 * Bean ya estaba construido y en @PostConstruct SI.
	 */

	@PostConstruct
	public void init() {
		System.out.println("BeanCitas - PostConstruct");
		// Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
		cita = (BeanCita) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("cita"));
		// si no existe lo creamos e inicializamos
		if (cita == null) {
			System.out.println("BeanCitas - No existia");
			cita = new BeanCita();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cita", cita);
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanCitas - PreDestroy");
	}

}