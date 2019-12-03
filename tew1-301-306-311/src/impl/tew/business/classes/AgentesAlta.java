package impl.tew.business.classes;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class AgentesAlta {

	public void save(Agente agente) throws EntityAlreadyExistsException {
		AgenteDao dao = Factories.persistence.createAgenteDao();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(agente.getPassword().getBytes(StandardCharsets.UTF_8));

	        agente.setPassword(new String(Base64.getEncoder().encode(hash)));
			dao.save(agente);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Agente ya existe " + agente, ex);
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new EntityAlreadyExistsException("Correo ya en uso" + agente, e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
