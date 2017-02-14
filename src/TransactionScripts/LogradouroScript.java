package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Logradouro;
import persistencia.LogradouroFinder;
import persistencia.LogradouroGateway;

public class LogradouroScript {
	public int CriarLogradouro(String cep, String estado, String cidade, String distrito, String endereço, int numero) throws Exception{
		if(cep == null || cep.isEmpty()){
			throw new IllegalArgumentException("cep");
		}
		
		if(estado == null || estado.isEmpty()){
			throw new IllegalArgumentException("estado");
		}
		
		if(cidade == null || cidade.isEmpty()){
			throw new IllegalArgumentException("cidade");
		}
		
		if(distrito == null || distrito.isEmpty()){
			throw new IllegalArgumentException("distrito");
		}
		
		if(endereço == null || endereço.isEmpty()){
			throw new IllegalArgumentException("endereço");
		}
		
		if(numero < 1) {
			throw new IllegalArgumentException("numero");
		}
		
		LogradouroGateway l = new LogradouroGateway();
		
		l.set_cep(cep);
		l.set_cidade(cidade);
		l.set_distrito(distrito);
		l.set_endereco(endereço);
		l.set_estado(estado);
		l.set_numero(numero);
		
		l.Insert();
		
		return l.get_id();
	}
	
	public Collection<Logradouro> GetLogradouros() throws Exception{
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
