package TransactionScripts;

import java.util.Collection;

import entidades.Avaliacao;
import entidades.Grupo;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.AvaliacaoFinder;
import persistencia.AvaliacaoGateway;
import persistencia.GrupoFinder;
import persistencia.GrupoGateway;
import persistencia.UsuarioGateway;
import persistencia.Usuario_GrupoFinder;
import persistencia.Usuario_GrupoGateway;
import persistencia.UsuariosFinder;

public class GetGrupoScript {
	private int id;
	
	public GetGrupoScript(int id) {
		this.id = id;
	}
	
	public Grupo execute() throws Exception{
		GrupoFinder finder = new GrupoFinder();
		GrupoGateway g = finder.find(id);
		
		if(g == null)
			throw new EntidadeNaoEncontradaException();
		
		Grupo grupo = new Grupo(g.get_nome(), g.get_descricao(), g.get_regras(), g.get_limite());
		grupo.set_id(id);
		
		Usuario_GrupoFinder associacaoFinder = new Usuario_GrupoFinder();
		Collection<Usuario_GrupoGateway> associacoesGateway = associacaoFinder.findByGrupo(grupo.get_id());
		
		for(Usuario_GrupoGateway a : associacoesGateway){
			UsuariosFinder ufinder = new UsuariosFinder();
			UsuarioGateway u = ufinder.find(a.get_usuarioId());
			Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
			usuario.set_id(a.get_usuarioId());
			
			AvaliacaoFinder aFinder = new AvaliacaoFinder();
			
			for(AvaliacaoGateway aGateway: aFinder.getByUsuario(a.get_usuarioId())){
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.set_avaliacao(aGateway.get_estrelas());
				avaliacao.set_id(aGateway.get_id());
				avaliacao.set_usuario(usuario);
				
				usuario.AdicionarAvaliacao(avaliacao);
			}
			
			grupo.AdicionarMembro(usuario);
		}
		
		return grupo;
	}
}
