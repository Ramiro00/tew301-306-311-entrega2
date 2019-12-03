package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cita;
import com.tew.persistence.CitaDao;


public class CitasConfirma {

	public void confirmacitas(Cita cita) throws EntityNotFoundException {
		CitaDao dao = Factories.persistence.createCitaDao();
		try {
			dao.confirmaVisita(cita);
		} catch (EntityNotFoundException ex) {
			throw new EntityNotFoundException("Piso no eliminado "  , ex);
		}
		
	}
}
