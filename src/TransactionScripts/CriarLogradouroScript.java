package TransactionScripts;

import persistencia.LogradouroGateway;

public class CriarLogradouroScript {
	private String cep;
	private String estado;
	private String cidade;
	private String distrito;
	private String endereco; 
	private int numero;
	
	public CriarLogradouroScript(String cep, String estado, String cidade, String distrito, String endereco, int numero) {
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
		this.distrito = distrito;
		this.endereco = endereco;
		this.numero = numero;
	}
	
	public int execute() throws Exception{
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
		
		if(endereco == null || endereco.isEmpty()){
			throw new IllegalArgumentException("endereco");
		}
		
		if(numero < 1) {
			throw new IllegalArgumentException("numero");
		}
		
		LogradouroGateway l = new LogradouroGateway();
		
		l.set_cep(cep);
		l.set_cidade(cidade);
		l.set_distrito(distrito);
		l.set_endereco(endereco);
		l.set_estado(estado);
		l.set_numero(numero);
		
		l.Insert();
		
		return l.get_id();
	}
}
