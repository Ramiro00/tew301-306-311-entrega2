package impl.tew.business.classes;

import com.tew.infrastructure.Factories;
import com.tew.persistence.CitaDao;
import com.tew.persistence.PisoDao;

public class CitasBaja {

	public void deleteAll(){
		CitaDao dao = Factories.persistence.createCitaDao();
		dao.deleteAll();
	}
}
