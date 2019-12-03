package com.tew.presentation;

import java.io.Serializable;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.model.Cita;

@ManagedBean(name = "cita")
@SessionScoped
public class BeanCita extends Cita implements Serializable {
	private static final long serialVersionUID = 6611899L;

	public BeanCita() {
		iniciaCita(null);
	}

	/**
	 * Este método es necesario para copiar la cita a editar cuando se pincha el
	 * enlace Editar en la vista listadoCitas.xhtml. Podría sustituirse por un
	 * método editar en BeanCitas.
	 */
	public void setCita(Cita cita) {
		setIdPiso(cita.getIdPiso());
		setDireccion(cita.getDireccion());
		setIdCliente(cita.getIdCliente());
		setFechaHoraCita(cita.getFechaHoraCita());
		setEstado(cita.getEstado());
		setEstadoStr(cita.getEstadoStr());
	}

	/*
	 * Iniciamos los datos de la citao con los valores por defecto del archivo de
	 * config
	 */
	public void iniciaCita(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		
		setIdPiso(Integer.valueOf((String) bundle.getObject("valorDefectoCIdPiso")));
		setIdCliente(Integer.valueOf((String) bundle.getObject("valorDefectoCIdCliente")));
		setDireccion(bundle.getString("valorDefectoDireccion"));
		setEstado(Integer.valueOf((String) bundle.getObject("valorDefectCEstado")));
		setFechaHoraCita(Long.valueOf((String) bundle.getObject("valorDefectCCita")));
	}
}