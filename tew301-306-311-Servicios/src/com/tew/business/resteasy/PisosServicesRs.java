package com.tew.business.resteasy;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.tew.business.PisosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

@Path("/AlumnosServicesRs")
public interface PisosServicesRs extends PisosService {
	
	@GET 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Piso> getAlumnos();
	
	@GET
	@Path("{id}") 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Piso findById(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@DELETE
	@Path("{id}")
	void deletePiso(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void saveAlumno(Piso alumno) throws EntityAlreadyExistsException;
	
	@POST 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void updateAlumno(Piso alumno) throws EntityNotFoundException;
}