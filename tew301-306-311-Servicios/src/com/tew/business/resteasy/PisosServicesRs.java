package com.tew.business.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

@Path("/PisosServicesRs")
public interface PisosServicesRs {

	// responde a peticiones GET
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Piso> getPisos() throws Exception;

	@GET
	// paramentro indicado en la URL, utilizado el metodo con @PathParam
	@Path("{id}")
	// formato en el que los datos se retornan en el metodo
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Piso findById(@PathParam("id") Long id) throws EntityNotFoundException;

	// responde a peticiones DELETE
	@DELETE
	// paramentro indicado en la URL
	@Path("{id}")
	void deletePiso(@PathParam("id") Long id) throws EntityNotFoundException;

	// responde a peticiones PUT
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	void savePiso(Piso piso) throws EntityAlreadyExistsException;

	// responde a peticiones POST
	@POST
	// formato en que los datos se reciben en el m�todo
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	void updatePiso(Piso piso) throws EntityNotFoundException;
}
