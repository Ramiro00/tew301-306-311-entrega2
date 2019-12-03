package impl.tew.business.classes;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.PisoDao;

public class PisosDuplicar {

	public Piso duplicar(Piso piso) {
		PisoDao dao = Factories.persistence.createPisoDao();
		return dao.findById(piso.getId());
	}

}
