package impl.tew.business.classes;

import java.util.List;
import com.tew.infrastructure.Factories;
import com.tew.model.Cita;
import com.tew.model.Piso;
import com.tew.persistence.CitaDao;
import com.tew.persistence.PisoDao;

/**
 * Esta clase pertenece a la capa de persistencia y ejecuta un proceso de
 * negocio.
 * 
 * Si el problema a resolver fuese mas complejo habra otras muchas clases de
 * este estilo en esta capa.
 * 
 * Las clases que forman la capa de negocio pueden necesitar acceder a la capa
 * de persistencia para resolver su cometido. Esta oculta los detalles de la
 * tecnologia de persistencia ofreciendo metodos del estilo: crear, borrer,
 * actualizar y diversas consultas.
 * 
 */

public class CitasListado {

	public List<Cita> getCitas() throws Exception {
		CitaDao dao = Factories.persistence.createCitaDao();
		return dao.getCitas();
	}

	public List<Cita> getCitas(String login) {
		CitaDao dao = Factories.persistence.createCitaDao();
		return dao.getCitas(login);
	}

	public List<Piso> getPisos(String id) {
		PisoDao dao = Factories.persistence.createPisoDao();
		return dao.getPisos();
	}

}
