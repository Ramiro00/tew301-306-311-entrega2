package impl.tew.business.resteasy;

import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.resteasy.DatosServicesRs;
import com.tew.model.Agente;

import impl.tew.business.classes.AgentesAlta;
import impl.tew.business.classes.AgentesBaja;
import impl.tew.business.classes.PisosBaja;

public class DatosServicesRsImpl implements DatosServicesRs {

	@Override
	public void resetDatos() {
		new PisosBaja().deleteAll();
		new AgentesBaja().deleteAll();

		AgentesAlta agentesalta = new AgentesAlta();

		Agente a0 = new Agente();
		a0.setID(0);
		a0.setLogin("agente0@micorreo.com");
		a0.setPassword("clave0");

		Agente a1 = new Agente();
		a1.setID(1);
		a1.setLogin("agente1@micorreo.com");
		a1.setPassword("clave1");
		
		Agente a2 = new Agente();
		a2.setID(2);
		a2.setLogin("agente2@micorreo.com");
		a2.setPassword("clave2");

		try {
			agentesalta.save(a0);
			agentesalta.save(a1);
			agentesalta.save(a2);
		} catch (EntityAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
}
