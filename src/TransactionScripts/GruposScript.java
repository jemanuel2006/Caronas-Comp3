package TransactionScripts;

import entidades.Grupo;
import excecoes.UsuarioJaInseridoNoGrupoException;
import persistencia.GrupoGateway;
import persistencia.UsuarioGateway;
import persistencia.Usuario_GrupoFinder;
import persistencia.Usuario_GrupoGateway;
import persistencia.UsuariosFinder;
import persistencia.GrupoFinder;

public class GruposScript {
	public int CriarGrupo(int _id, String nome, String descricao, String regras, int limite) throws Exception{
		if(nome == null || nome.isEmpty()){
			throw new IllegalArgumentException("nome");
		}
		
		if(regras == null || regras.isEmpty()){
			throw new IllegalArgumentException("regras");
		}
		
		if(descricao == null || descricao.isEmpty()){
			throw new IllegalArgumentException("descricao");
		}
		
		if(limite < 1 || limite > 5) {
			throw new IllegalArgumentException("limite");
		}
		
		Grupo grupo = new Grupo(nome, descricao, regras, limite);
		GrupoGateway gruposGateway = new GrupoGateway(grupo);
		int id = gruposGateway.Insert();
		grupo.set_id(id);
		
		return id;
	}
	
	public void EditarGrupo(int id, String nome, String descricao, int limite) throws Exception{
		if(nome == null || nome.isEmpty()){
			throw new IllegalArgumentException("nome");
		}
		
		if(descricao == null || descricao.isEmpty()){
			throw new IllegalArgumentException("descricao");
		}
		
		if(limite < 1 || limite > 5) {
			throw new IllegalArgumentException("limite");
		}
		
		GrupoFinder grupoFinder = new GrupoFinder();
		GrupoGateway grupoGateway = grupoFinder.find(id);
		
		grupoGateway.set_nome(nome);
		grupoGateway.set_descricao(descricao);
		grupoGateway.set_limite(limite);
		
		grupoGateway.Save();
	}
	
	public Grupo GetGrupo(int id) throws Exception{
		GrupoFinder finder = new GrupoFinder();
		GrupoGateway g = finder.find(id);
		return new Grupo(g.get_nome(), g.get_descricao(), g.get_regras(), g.get_limite());
	}
	
	public void AdicionarUsuarioEmGrupo(String emailUsuario, int idGrupo) throws Exception{
		if(emailUsuario == null || emailUsuario.isEmpty()){
			throw new IllegalArgumentException("emailUsuario");
		}
		
		GrupoFinder finder = new GrupoFinder();
		GrupoGateway g = finder.find(idGrupo);
		
		UsuariosFinder usuariosFinder = new UsuariosFinder();
		UsuarioGateway u = usuariosFinder.find(emailUsuario);
		
		Usuario_GrupoFinder associacaoFinder = new Usuario_GrupoFinder();
		Usuario_GrupoGateway associacaoGateway = associacaoFinder.find(g.get_id(),u.get_id());
		
		if(associacaoGateway != null)
			throw new UsuarioJaInseridoNoGrupoException();
		
		associacaoGateway = new Usuario_GrupoGateway(u.get_id(), g.get_id());
		associacaoGateway.Insert();
	}

	public void DeletarGrupo(int id) throws Exception {
		GrupoFinder finder = new GrupoFinder();
		GrupoGateway g = finder.find(id);
		g.Delete();
		
	}
}
