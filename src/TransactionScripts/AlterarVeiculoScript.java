package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class AlterarVeiculoScript {
	private int id;
	private String cor;
	
	public AlterarVeiculoScript(int id, String cor) {
		this.id = id;
		this.cor = cor;
	}
	
	public void execute() throws Exception{
		VeiculoFinder vFinder = new VeiculoFinder();
		VeiculoGateway gateway = vFinder.find(id);
		
		if(gateway == null){
			throw new EntidadeNaoEncontradaException();
		}

		gateway.set_cor(cor);
		gateway.Save();
	}
	
}
