package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;

public class ClientesBuscar {
	
	public Cliente find(String login) throws EntityNotFoundException {
		ClienteDao dao = Factories.persistence.createClienteDao();
		Cliente c = dao.findByLogin(login);
		if ( c == null) {
			throw new EntityNotFoundException("No se ha encontrado el cliente");
		}
		
		return c;
	}
}
