package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.NotPersistedException;

public class PisosBaja {

	public void delete(int id) throws EntityNotFoundException {
		PisoDao dao = Factories.persistence.createPisoDao();
		try {
			dao.deletevisitas(id);
			dao.delete(id);
		} catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Piso no eliminado " + id, ex);
		}
	}

	public void deleteAll(){
		PisoDao dao = Factories.persistence.createPisoDao();
		dao.deleteAll();
	}
}
