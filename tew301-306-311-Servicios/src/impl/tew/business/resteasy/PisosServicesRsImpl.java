package impl.tew.business.resteasy;
import java.util.List;
import com.tew.business.resteasy.PisosServicesRs;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;
import impl.tew.business.classes.*;

public class PisosServicesRsImpl implements PisosServicesRs {

	@Override
	public Piso findById(int id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatePiso(Piso piso) throws EntityNotFoundException {

		new PisosUpdate().update(piso);
		
	}

	@Override
	public void deletePiso(int id) throws EntityNotFoundException {
		new PisosBaja().delete(id);
		
	}

	@Override
	public void savePiso(Piso p, String login) {

		new PisosAlta().save(p, login);
		
	}

	@Override
	public List<Piso> getPisos() throws Exception {
		
		return new PisosListado().getPisos();
	}


	@Override
	public void savePiso(Piso piso) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub
		
	}
	


}