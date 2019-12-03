package impl.tew.business;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.tew.business.LoginService;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;
import com.tew.model.Cliente;
import com.tew.model.User;

import impl.tew.business.classes.AgentesBuscar;
import impl.tew.business.classes.ClientesBuscar;

public class SimpleLoginService implements LoginService{

	public User verify(String login, String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		return validLogin(login, new String(Base64.getEncoder().encode(hash)));
	}

	private User validLogin(String login, String password) {
		Cliente c = null;
		Agente g = null;

		try {
			c = new ClientesBuscar().find(login);

			if(c.getLogin().equals(login) && c.getPassword().equals(password))
				return new User(login, false);

		} catch (EntityNotFoundException e) {
		}
		try {
			g = new AgentesBuscar().find(login);
			if(g.getLogin().equals(login) && g.getPassword().equals(password))	
				return new User(login, true);
		} catch (EntityNotFoundException e) {
		}
		return null;
	}
}
