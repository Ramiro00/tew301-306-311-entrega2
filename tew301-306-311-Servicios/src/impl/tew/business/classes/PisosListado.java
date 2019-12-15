package impl.tew.business.classes;

import java.util.List;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.NotPersistedException;

/**
 * Esta clase pertenece a la capa de persistencia y ejecuta un proceso de
 * negocio.
 * 
 * Si el problema a resolver fuese m??????s complejo habr?????? otras muchas
 * clases de este estilo en esta capa.
 * 
 * Las clases que forman la capa de negocio pueden necesitar acceder a la capa
 * de persistencia para resolver su cometido. Esta oculta los detalles de la
 * tecnolog??????a de persistencia ofreciendo m??????todos del estilo: crear,
 * borrer, actualizar y diversas consultas.
 * 
 */
public class PisosListado {

	public List<Piso> getPisos() throws EntityNotFoundException {
		PisoDao dao = Factories.persistence.createPisoDao();
		return dao.getPisos();

	}
	
	public List<Piso> getPisos(String login) throws EntityNotFoundException {
		PisoDao dao = Factories.persistence.createPisoDao();
		return dao.getPisos();
	}

}
