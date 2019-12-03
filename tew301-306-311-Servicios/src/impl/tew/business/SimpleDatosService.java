package impl.tew.business;

import java.util.List;

import com.tew.business.DatosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.model.Agente;
import com.tew.model.Cita;
import com.tew.model.Cliente;
import com.tew.model.Piso;

import impl.tew.business.classes.AgentesAlta;
import impl.tew.business.classes.AgentesBaja;
import impl.tew.business.classes.CitasAlta;
import impl.tew.business.classes.CitasBaja;
import impl.tew.business.classes.ClientesAlta;
import impl.tew.business.classes.ClientesBaja;
import impl.tew.business.classes.ClientesBuscar;
import impl.tew.business.classes.PisosAlta;
import impl.tew.business.classes.PisosBaja;
import impl.tew.business.classes.PisosBuscar;
import impl.tew.business.classes.PisosListado;

public class SimpleDatosService implements DatosService{

	public void reiniciaBaseDatos(){
		new CitasBaja().deleteAll();
		new PisosBaja().deleteAll();
		new AgentesBaja().deleteAll();
		new ClientesBaja().deleteAll();
		
		AgentesAlta agentesalta = new AgentesAlta();

		Agente a1 = new Agente();
		a1.setLogin("agente1@micorreo.com");
		a1.setPassword("clave1");

		Agente a2 = new Agente();
		a2.setLogin("agente2@micorreo.com");
		a2.setPassword("clave2");

		try {
			agentesalta.save(a2);
			agentesalta.save(a1);
		} catch (EntityAlreadyExistsException e) {
			e.printStackTrace();
		}

		
		ClientesAlta clientesalta = new ClientesAlta();

		Cliente c1 = new Cliente();
		c1.setNombre("Cliente1");
		c1.setApellidos("Gonz�lez");
		c1.setLogin("user1@micorreo.com");
		c1.setEmail("user1@micorreo.com");
		c1.setPassword("clave1");

		Cliente c2 = new Cliente();
		c2.setNombre("Cliente2");
		c2.setApellidos("P�rez");
		c2.setLogin("user2@micorreo.com");
		c2.setEmail("user2@micorreo.com");
		c2.setPassword("clave2");

		
		try {
			clientesalta.save(c1);
			clientesalta.save(c2);
		} catch (EntityAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		
		
		PisosAlta pisosalta = new PisosAlta();

		Piso p1 = new Piso();

		p1.setAno(1998);
		p1.setCiudad("Oviedo");
		p1.setDireccion("Calle Uria 34");
		p1.setEstado(3);
		p1.setPrecio(150000);	

		Piso p2 = new Piso();

		p2.setAno(2013);
		p2.setCiudad("Gij�n");
		p2.setDireccion("Avenida Constituci�n 17");
		p2.setEstado(1);
		p2.setPrecio(300000);

		Piso p3 = new Piso();
		
		p3.setAno(2004);
		p3.setCiudad("Gij�n");
		p3.setDireccion("Calle Corrida 4");
		p3.setEstado(2);
		p3.setPrecio(350000);
		
	
		pisosalta.save(p1, a1.getLogin());
		
		pisosalta.save(p2, a2.getLogin());
		pisosalta.save(p3, a1.getLogin());
		

		
		ClientesBuscar clientesbuscar = new ClientesBuscar();
		List<Piso> pisos = null;
		try {
			c1 = clientesbuscar.find(c1.getLogin());
			c2 = clientesbuscar.find(c2.getLogin());
			pisos = new PisosListado().getPisos();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Cita cita1 = new Cita();
		
		cita1.setIdCliente(c1.getID());
		cita1.setIdPiso(pisos.get(0).getId());
		cita1.setEstado(2);
		cita1.setFechaHoraCita(1571799652);
		
		Cita cita2 = new Cita();
		
		cita2.setIdCliente(c1.getID());
		cita2.setIdPiso(pisos.get(1).getId());
		cita2.setEstado(3);
		cita2.setFechaHoraCita(1571759394);
		
	Cita cita3 = new Cita();
		
		cita3.setIdCliente(c2.getID());
		cita3.setIdPiso(pisos.get(0).getId());
		cita3.setEstado(1);
		cita3.setFechaHoraCita(1573459884);
		
		
		
		
		CitasAlta citasalta = new CitasAlta();
		try {
			citasalta.save(cita1);
			citasalta.save(cita2);
			citasalta.save(cita3);
		} catch (EntityAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
}
