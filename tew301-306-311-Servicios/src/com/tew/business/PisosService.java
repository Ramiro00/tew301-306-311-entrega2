package com.tew.business;

import java.util.List;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

/**
 * Este es el interfaz que ofrecer�� cualquier implementaci��n de la
 * clase fachada.
 * 
 * Al separar la implementaci��n de la fachada de su interfaz se permite
 * cambiar las implementaciones reales de la fachada. Esto es muy ��til
 * cuando se necesita a��adir funcionalidad extra como acceso remoto, web
 * services, control de acceso, etc. Al hacerlo de esta forma esos cambios solo
 * afectan a las factorias y no al contenido de las capas. Las factor��as,
 * en un desarrollo profesional, se configuran declarativamente (properties,
 * xml, etc)
 * 
 * @author alb
 *
 */
public interface PisosService {

	List<Piso> getPisos() throws Exception;

	//Piso findById(int id) throws EntityNotFoundException;
	
	Piso findById(Long id) throws EntityNotFoundException;

	void updatePiso(Piso p) throws EntityNotFoundException;

	void deletePiso(int id, String login) throws EntityNotFoundException;

	List<Piso> getPisos(String login);

	void savePiso(Piso p, String login);

	void duplicarpiso(Piso piso) throws EntityNotFoundException;

}