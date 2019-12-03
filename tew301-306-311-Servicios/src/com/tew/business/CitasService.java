package com.tew.business;

import java.util.List;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cita;
import com.tew.model.Piso;

/**
 * Este es el interfaz que ofrecera cualquier implementacion de la clase
 * fachada.
 * 
 * Al separar la implementacion de la fachada de su interfaz se permite cambiar
 * las implementaciones reales de la fachada. Esto es muy Util cuando se
 * necesita añadir funcionalidad extra como acceso remoto, web services, control
 * de acceso, etc. Al hacerlo de esta forma esos cambios solo afectan a las
 * factorias y no al contenido de las capas. Las factor��as, en un desarrollo
 * profesional, se configuran declarativamente (properties, xml, etc)
 * 
 * @author JaVi
 *
 */

public interface CitasService {

	List<Cita> getCitas() throws Exception;

	List<Cita> getCitas(String login) throws Exception;
	
	void confirmaVisita(Cita c) throws EntityNotFoundException;

	List<Piso> getPisos() throws Exception;

}

