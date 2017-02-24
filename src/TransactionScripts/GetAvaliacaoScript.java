package TransactionScripts;

import entidades.Avaliacao;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.AvaliacaoFinder;
import persistencia.AvaliacaoGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class GetAvaliacaoScript {
	private int avaliacaoId;
	
	public GetAvaliacaoScript(int avaliacaoId) {
		this.avaliacaoId = avaliacaoId;
	}
	
	public Avaliacao execute() throws Exception{
		AvaliacaoFinder aFinder = new AvaliacaoFinder();
		AvaliacaoGateway aGateway = aFinder.find(avaliacaoId);
		
		if(aGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		UsuariosFinder finder = new UsuariosFinder();
		UsuarioGateway u = finder.find(aGateway.get_usuarioId());
		
		if(u == null)
			throw new EntidadeNaoEncontradaException();
		
		Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
		usuario.set_id(aGateway.get_usuarioId());
		
		for(AvaliacaoGateway a: aFinder.getByUsuario(aGateway.get_usuarioId())){
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.set_avaliacao(a.get_estrelas());
			avaliacao.set_id(a.get_id());
			avaliacao.set_usuario(usuario);
			
			usuario.AdicionarAvaliacao(avaliacao);
		}
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.set_avaliacao(aGateway.get_estrelas());
		avaliacao.set_usuario(usuario);
		avaliacao.set_id(avaliacaoId);
		
		return avaliacao;
	}
}
