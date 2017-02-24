package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.CaronaFinder;
import persistencia.CaronaGateway;

public class FinalizarCaronaScript {
	private int caronaId;
	
	public FinalizarCaronaScript(int caronaId) {
		this.caronaId = caronaId;
	}
	
	public void execute() throws Exception{
		CaronaFinder cFinder = new CaronaFinder();
		CaronaGateway cGateway = cFinder.find(caronaId);
		
		if(cGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		//Concluída
		cGateway.set_estadoCarona(1);
		cGateway.Save();
	}
}
