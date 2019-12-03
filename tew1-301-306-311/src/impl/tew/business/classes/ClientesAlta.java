package impl.tew.business.classes;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.infrastructure.Factories;
import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class ClientesAlta {
	
	public void save(Cliente cliente) throws EntityAlreadyExistsException {
		ClienteDao dao = Factories.persistence.createClienteDao();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(cliente.getPassword().getBytes(StandardCharsets.UTF_8));

	        cliente.setPassword(new String(Base64.getEncoder().encode(hash)));
			dao.save(cliente);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Cliente ya existe " + cliente, ex);
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new EntityAlreadyExistsException("Correo ya en uso" + cliente, e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
