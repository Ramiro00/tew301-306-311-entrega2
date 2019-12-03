package impl.tew.business;

import com.tew.business.PisosService;
import com.tew.business.CitasService;
import com.tew.business.DatosService;
import com.tew.business.LoginService;
import com.tew.business.ServicesFactory;
import com.tew.business.SignupService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public PisosService createPisosService() {
		return new SimplePisosService();
	}

	@Override
	public LoginService createLoginService() {
		return new SimpleLoginService();
	}

	@Override
	public CitasService createCitasService() {
		return new SimpleCitasService();
	}

	@Override
	public SignupService CreateSignupService() {
		return new SimpleSignupService();
	}

	@Override
	public SignupService createSignupService() {
		return new SimpleSignupService();
	}

	@Override
	public DatosService CreateDatosService() {
		return new SimpleDatosService();
	}
}
