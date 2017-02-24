package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import excecoes.UsuarioJaInseridoNoGrupoException;
import persistencia.GrupoFinder;
import persistencia.GrupoGateway;
import persistencia.UsuarioGateway;
import persistencia.Usuario_GrupoFinder;
import persistencia.Usuario_GrupoGateway;
import persistencia.UsuariosFinder;

public class AdicionarUsuarioEmGrupoScript {
	private String emailUsuario;
	private int idGrupo;
	
	public AdicionarUsuarioEmGrupoScript(String emailUsuario, int idGrupo) {
		this.emailUsuario = emailUsuario;
		this.idGrupo = idGrupo;
	}
	
	public void execute() throws Exception{
		if(emailUsuario == null || emailUsuario.isEmpty()){
			throw new IllegalArgumentException("emailUsuario");
		}
		
		GrupoFinder finder = new GrupoFinder();
		GrupoGateway g = finder.find(idGrupo);
		
		UsuariosFinder usuariosFinder = new UsuariosFinder();
		UsuarioGateway u = usuariosFinder.find(emailUsuario);
		
		if(u == null)
			throw new EntidadeNaoEncontradaException();
		
		Usuario_GrupoFinder associacaoFinder = new Usuario_GrupoFinder();
		Usuario_GrupoGateway associacaoGateway = associacaoFinder.find(g.get_id(),u.get_id());
		
		if(associacaoGateway != null)
			throw new UsuarioJaInseridoNoGrupoException();
		
		associacaoGateway = new Usuario_GrupoGateway(u.get_id(), g.get_id());
		associacaoGateway.Insert();
	}
}
