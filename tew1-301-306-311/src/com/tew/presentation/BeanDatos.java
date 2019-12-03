package com.tew.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tew.business.DatosService;
import com.tew.infrastructure.Factories;

@ManagedBean (name = "datos")
public class BeanDatos implements Serializable{

	private static final long serialVersionUID = -1641004297727143977L;
	
	public String reiniciarbbdd() {
		FacesContext.getCurrentInstance();
		
		 DatosService datosservice = Factories.services.CreateDatosService();
		 
		 datosservice.reiniciaBaseDatos();
		 
		 return "";
		
	}
	
}
