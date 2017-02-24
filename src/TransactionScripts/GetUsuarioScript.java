package TransactionScripts;

import entidades.Avaliacao;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.AvaliacaoFinder;
import persistencia.AvaliacaoGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class GetUsuarioScript {
	private int id;
	
	public GetUsuarioScript(int id) {
		this.id = id;
	}
	
	public Usuario execute() throws Exception{
		UsuariosFinder finder = new UsuariosFinder();
		UsuarioGateway u = finder.find(id);
		
		if(u == null)
			throw new EntidadeNaoEncontradaException();
		
		Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
		usuario.set_id(id);
		
		AvaliacaoFinder aFinder = new AvaliacaoFinder();
		
		for(AvaliacaoGateway a: aFinder.getByUsuario(id)){
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.set_avaliacao(a.get_estrelas());
			avaliacao.set_id(a.get_id());
			avaliacao.set_usuario(usuario);
			
			usuario.AdicionarAvaliacao(avaliacao);
		}
		
		return usuario;
	}
}
