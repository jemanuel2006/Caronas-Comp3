package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.CaronaFinder;
import persistencia.CaronaGateway;

public class CancelarCaronaScript {
	private int caronaId;
	
	public CancelarCaronaScript(int caronaId) {
		this.caronaId = caronaId;
	}
	
	public void CancelarCarona() throws Exception{
		CaronaFinder cFinder = new CaronaFinder();
		CaronaGateway cGateway = cFinder.find(caronaId);
		
		if(cGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		//Cancelada
		cGateway.set_estadoCarona(2);
		cGateway.Save();
	}
}
