package impl.tew.business.classes;

import com.tew.infrastructure.Factories;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.PisoDao;

public class ClientesBaja {
	public void deleteAll(){
		ClienteDao dao = Factories.persistence.createClienteDao();
		dao.deleteAll();
	}
}
