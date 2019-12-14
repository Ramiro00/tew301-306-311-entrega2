package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.PisosServicesRs;
import com.tew.model.Piso;

import impl.tew.business.classes.PisosAlta;
import impl.tew.business.classes.PisosBaja;
import impl.tew.business.classes.PisosBuscar;
import impl.tew.business.classes.PisosDuplicar;
import impl.tew.business.classes.PisosListado;

public class PisosServicesRsImpl implements PisosServicesRs{
	
	@Override
	public List<Piso> getPisos(){
		try {
			return new PisosListado().getPisos();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		return new PisosBuscar().find(id);
	}

	@Override
	public void deletePiso(int id, String login) throws EntityNotFoundException {
		new PisosBaja().delete(id, login);
		
	}

	@Override
	public List<Piso> getPisos(String login) {
		return new PisosListado().getPisos(login);
	}

	@Override
	public void savePiso(Piso p, String login) {
		new PisosAlta().save(p, login);	
	}

	@Override
	public void duplicarpiso(Piso piso) throws EntityNotFoundException {
		new PisosDuplicar().duplicar(piso);
	}

	@Override
	public void deletePiso(Long id) throws EntityNotFoundException {

	}

	@Override
	public void savePiso(Piso piso) throws EntityAlreadyExistsException {
		
	}

	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		
	}
}
