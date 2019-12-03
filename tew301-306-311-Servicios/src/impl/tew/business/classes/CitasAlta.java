package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cita;
import com.tew.persistence.CitaDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class CitasAlta {

	public void save(Cita cita) throws EntityAlreadyExistsException {
		CitaDao dao = Factories.persistence.createCitaDao();
		try {
			dao.save(cita);
		} catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Cita ya existe " + cita, ex);
		}
	}
}