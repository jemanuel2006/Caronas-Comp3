package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Logradouro;
import persistencia.LogradouroFinder;
import persistencia.LogradouroGateway;

public class GetLogradourosScript {
	public GetLogradourosScript() {
	}
	
	public Collection<Logradouro> execute() throws Exception{
		Collection<Logradouro> logradouros = new ArrayList<>();
		LogradouroFinder lFinder = new LogradouroFinder();
		Collection<LogradouroGateway> gateways = lFinder.getAll();
		
		for(LogradouroGateway log : gateways){
			Logradouro l = new Logradouro();
			
			l.set_cep(log.get_cep());
			l.set_cidade(log.get_cidade());
			l.set_distrito(log.get_distrito());
			l.set_endereco(log.get_endereco());
			l.set_estado(log.get_estado());
			l.set_numero(log.get_numero());
			l.set_id(log.get_id());
			
			logradouros.add(l);
		}
		
		return logradouros;
	}
}
