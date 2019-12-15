package com.tew.business.resteasy;

import javax.ws.rs.GET;

import javax.ws.rs.Path;

@Path("/DatosServicesRs")
public interface DatosServicesRs {

	// responde a peticiones GET
	@GET
	void resetDatos();

}