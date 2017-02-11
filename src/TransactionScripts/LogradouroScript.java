package TransactionScripts;

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
}
