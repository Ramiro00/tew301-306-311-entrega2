package impl.tew.business;

import java.util.List;

import com.tew.business.CitasService;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cita;
import com.tew.model.Piso;

import impl.tew.business.classes.CitasConfirma;
import impl.tew.business.classes.CitasListado;
import impl.tew.business.classes.PisosListado;

public class SimpleCitasService implements CitasService {

	@Override
	public List<Cita> getCitas() throws Exception {
		return new CitasListado().getCitas();
	}

	@Override
	public List<Cita> getCitas(String login) {
		return new CitasListado().getCitas(login);
	}
	
	@Override
	public List<Piso> getPisos() throws Exception {
		return new PisosListado().getPisos();
	}
	
	@Override
	public void confirmaVisita(Cita cita) throws EntityNotFoundException {
		new CitasConfirma().confirmacitas(cita);
		
	}

/*
 * 	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {
		new PisosUpdate().update(piso);
 */

}

