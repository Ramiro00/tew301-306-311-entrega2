package com.tew.presentation;

import java.io.Serializable;

import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.model.Piso;

@ManagedBean(name = "piso")
@SessionScoped
public class BeanPiso extends Piso implements Serializable {
	private static final long serialVersionUID = 55556L;

	public BeanPiso() {
		iniciaPiso(null);
	}

	public void setPiso(Piso piso) {
		setId(piso.getId());
		setIdagente(piso.getIdagente());
		setPrecio(piso.getPrecio());
		setDireccion(piso.getDireccion());
		setCiudad(piso.getCiudad());
		setAno(piso.getAno());
		setEstado(piso.getEstado());
		setSestado(piso.getSestado());
		setVisita(piso.isVisita());
	}

	// Iniciamos los datos del piso con los valores por defecto del archivo de
	// config
	public void iniciaPiso(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");

		setId(Integer.valueOf((String) bundle.getObject("valorDefectoPisoId")));
		setIdagente(Integer.valueOf((String) bundle.getObject("valorDefectoIdAgente")));
		setPrecio(Integer.valueOf((String) bundle.getObject("valorDefectoPrecio")));
		setDireccion(bundle.getString("valorDefectoDireccion"));
		setCiudad(bundle.getString("valorDefectoCiudad"));
		setAno(Integer.valueOf((String) bundle.getObject("valorDefectoAnyo")));
		setEstado(Integer.valueOf((String) bundle.getObject("valorDefectoEstado")));
		setVisita(false);
	}
}
