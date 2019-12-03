package com.tew.business;

public interface ServicesFactory {

	LoginService createLoginService();

	CitasService createCitasService();

	SignupService createSignupService();

	PisosService createPisosService();

	SignupService CreateSignupService();
	
	DatosService CreateDatosService();

}
