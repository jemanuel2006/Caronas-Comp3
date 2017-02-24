package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.ParadaFinder;
import persistencia.ParadaGateway;

public class RemoverUsuarioDeCaronaScript {
	private int paradaId;
	
	public RemoverUsuarioDeCaronaScript(int paradaId) {
		this.paradaId = paradaId;
	}
	
	public void execute() throws Exception{
		ParadaFinder pFinder = new ParadaFinder();
		ParadaGateway p = pFinder.find(paradaId);
		
		if(p == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		p.Delete();
	}
}
