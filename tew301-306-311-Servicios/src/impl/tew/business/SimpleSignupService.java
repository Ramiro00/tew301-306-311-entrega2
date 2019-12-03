package impl.tew.business;

import com.tew.business.SignupService;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.model.Cliente;
import com.tew.model.User;

import impl.tew.business.classes.ClientesAlta;

public class SimpleSignupService implements SignupService{

	@Override
	public User registrarse(Cliente cliente) {
		try {
			new ClientesAlta().save(cliente);
			return new User(cliente.getEmail(), false);
		} catch (EntityAlreadyExistsException e) {
			return null;
		}
		
		/*@Override
	public Cliente findById(Long id) throws EntityNotFoundException {
		return new AlumnosBuscar().find(id);
	}*/

	}
}
