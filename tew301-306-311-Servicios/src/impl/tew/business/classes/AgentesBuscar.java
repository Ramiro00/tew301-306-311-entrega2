package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.persistence.AgenteDao;
public class AgentesBuscar {

	public Agente find(String login) throws EntityNotFoundException {
		AgenteDao dao = Factories.persistence.createAgenteDao();
		Agente a = dao.findByLogin(login);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el agente");
		}
		
		return a;
	}
}
