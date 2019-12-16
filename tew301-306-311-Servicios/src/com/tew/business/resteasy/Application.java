package com.tew.business.resteasy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import impl.tew.business.resteasy.PisosServicesRsImpl;
import impl.tew.business.resteasy.DatosServicesRsImpl;
import impl.tew.business.resteasy.LoginServicesRsImpl;

@SuppressWarnings("unchecked")
public class Application extends javax.ws.rs.core.Application {

	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public Application() {
		classes.add(PisosServicesRsImpl.class);
		classes.add(DatosServicesRsImpl.class);
		classes.add(LoginServicesRsImpl.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return Collections.EMPTY_SET;
	}
}
