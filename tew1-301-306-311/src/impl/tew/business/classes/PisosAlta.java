package impl.tew.business.classes;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class PisosAlta {

	public void save(Piso p, String login) {
		PisoDao dao = Factories.persistence.createPisoDao();
		try {
			dao.save(p, login);
		} catch (AlreadyPersistedException ex) {
			try {
				throw new EntityAlreadyExistsException("Piso ya existe " + p, ex);
			} catch (EntityAlreadyExistsException e) {
				e.printStackTrace();
			}
		}

	}

}
