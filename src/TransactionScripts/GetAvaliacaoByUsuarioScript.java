package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Avaliacao;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.AvaliacaoFinder;
import persistencia.AvaliacaoGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class GetAvaliacaoByUsuarioScript {
	private int usuarioId;
	
	public GetAvaliacaoByUsuarioScript(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Collection<Avaliacao> execute() throws Exception{
		AvaliacaoFinder aFinder = new AvaliacaoFinder();
		Collection<AvaliacaoGateway> gateways = aFinder.getByUsuario(usuarioId);
		Collection<Avaliacao> avaliacoes = new ArrayList<>();
		UsuariosFinder finder = new UsuariosFinder();
		
		UsuarioGateway u = finder.find(usuarioId);
		
		if(u == null)
			throw new EntidadeNaoEncontradaException();
		
		Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
		usuario.set_id(usuarioId);
		
		for(AvaliacaoGateway a: aFinder.getByUsuario(usuarioId)){
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.set_avaliacao(a.get_estrelas());
			avaliacao.set_id(a.get_id());
			avaliacao.set_usuario(usuario);
			
			usuario.AdicionarAvaliacao(avaliacao);
		}
		
		for(AvaliacaoGateway aGateway : gateways){
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.set_avaliacao(aGateway.get_estrelas());
			avaliacao.set_usuario(usuario);
			avaliacao.set_id(aGateway.get_id());
			
			avaliacoes.add(avaliacao);
		}
		
		return avaliacoes;
	}
}
