package impl.tew.business.classes;

import com.tew.infrastructure.Factories;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.CitaDao;

public class AgentesBaja {
	public void deleteAll(){
		AgenteDao dao = Factories.persistence.createAgenteDao();
		dao.deleteAll();
	}
}
