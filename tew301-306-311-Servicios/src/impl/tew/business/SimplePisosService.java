package impl.tew.business;

import impl.tew.business.classes.*;

import java.util.List;
import com.tew.business.PisosService;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

public class SimplePisosService implements PisosService {

	@Override
	public List<Piso> getPisos() throws Exception {
		return new PisosListado().getPisos();
	}

	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		new PisosUpdate().update(piso);
	}

	@Override
	public void deletePiso(int id, String login) throws EntityNotFoundException {
		new PisosBaja().delete(id, login);
	}

	@Override
	public Piso findById(Long id) throws EntityNotFoundException {
		return new PisosBuscar().find(id);
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

}
