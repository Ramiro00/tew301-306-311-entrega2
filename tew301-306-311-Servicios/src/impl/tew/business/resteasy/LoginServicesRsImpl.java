package impl.tew.business.resteasy;

import java.util.List;

import com.tew.business.resteasy.LoginServicesRs;
import com.tew.model.Agente;
import impl.tew.business.classes.AgentesListado;

public class LoginServicesRsImpl implements LoginServicesRs{

	@Override
	public List<Agente> getAgentes() throws Exception {
		return new AgentesListado().getAgentes();
	}

}
