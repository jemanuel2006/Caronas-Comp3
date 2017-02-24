package TransactionScripts;

import entidades.Logradouro;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.LogradouroFinder;
import persistencia.LogradouroGateway;

public class GetLogradouroScript {
	private int logradouroId;
	
	public GetLogradouroScript(int logradouroId) {
		this.logradouroId = logradouroId;
	}
	
	public Logradouro execute() throws Exception{
		LogradouroFinder lFinder = new LogradouroFinder();
		LogradouroGateway gateway = lFinder.find(logradouroId);
		
		if(gateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		Logradouro l = new Logradouro();
		
		l.set_cep(gateway.get_cep());
		l.set_cidade(gateway.get_cidade());
		l.set_distrito(gateway.get_distrito());
		l.set_endereco(gateway.get_endereco());
		l.set_estado(gateway.get_estado());
		l.set_numero(gateway.get_numero());
		l.set_id(gateway.get_id());
		
		return l;
	}
}
