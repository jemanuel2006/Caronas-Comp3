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

public class AvaliacaoScript {
	public void AvaliarUsuario(int usuarioId, int avaliacao) throws Exception{
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
	
	public Avaliacao GetAvaliacao(int avaliacaoId) throws Exception{
		AvaliacaoFinder aFinder = new AvaliacaoFinder();
		AvaliacaoGateway aGateway = aFinder.find(avaliacaoId);
		
		if(aGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		UsuariosScript uts = new UsuariosScript();
		Usuario usuario = uts.GetUsuario(aGateway.get_usuarioId());
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.set_avaliacao(aGateway.get_estrelas());
		avaliacao.set_usuario(usuario);
		avaliacao.set_id(avaliacaoId);
		
		return avaliacao;
	}
	
	public Collection<Avaliacao> GetAvaliacaoByUsuario(int usuarioId) throws Exception{
		AvaliacaoFinder aFinder = new AvaliacaoFinder();
		Collection<AvaliacaoGateway> gateways = aFinder.getByUsuario(usuarioId);
		Collection<Avaliacao> avaliacoes = new ArrayList<>();
		
		for(AvaliacaoGateway aGateway : gateways){
			UsuariosScript uts = new UsuariosScript();
			Usuario usuario = uts.GetUsuario(aGateway.get_usuarioId());
			
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.set_avaliacao(aGateway.get_estrelas());
			avaliacao.set_usuario(usuario);
			avaliacao.set_id(aGateway.get_id());
			
			avaliacoes.add(avaliacao);
		}
		
		return avaliacoes;
	}
}
