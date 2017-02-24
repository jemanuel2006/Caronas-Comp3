package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.AvaliacaoGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class AvaliarUsuarioScript {
	private int usuarioId;
	private int avaliacao;
	
	public AvaliarUsuarioScript(int usuarioId, int avaliacao) {
		this.usuarioId = usuarioId;
		this.avaliacao = avaliacao;
	}
	
	public void execute() throws Exception{
		if(avaliacao < 1 || avaliacao > 5)
			throw new IllegalArgumentException("avaliacao");
		
		UsuariosFinder uFinder = new UsuariosFinder();
		UsuarioGateway uGateway = uFinder.find(usuarioId);
		
		if(uGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		AvaliacaoGateway aGateway = new AvaliacaoGateway();
		aGateway.set_estrelas(avaliacao);
		aGateway.set_usuarioId(usuarioId);
		
		aGateway.Insert();
	}
}
