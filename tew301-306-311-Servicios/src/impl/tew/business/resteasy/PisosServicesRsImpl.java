package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.PisosServicesRs;
import com.tew.model.Piso;
import impl.tew.business.classes.PisosAlta;
import impl.tew.business.classes.PisosBaja;
import impl.tew.business.classes.PisosBuscar;
import impl.tew.business.classes.PisosListado;
import impl.tew.business.classes.PisosUpdate;

public class PisosServicesRsImpl implements PisosServicesRs {

	@Override
	public List<Piso> getPisos() throws Exception {
		try {
			return new PisosListado().getPisos();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		return new PisosBuscar().find(id);
	}

	@Override
	public void savePiso(Piso piso) {
		new PisosAlta().save(piso);
	}

	@Override
	public void deletePiso(Long id) throws EntityNotFoundException {
		new PisosBaja().delete(id);
	}

	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		new PisosUpdate().update(piso);
	}

	@Override
	public void deletePiso(int id, String login) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Piso> getPisos(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePiso(Piso p, String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicarpiso(Piso piso) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		
	}
}