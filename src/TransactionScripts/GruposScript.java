package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Grupo;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;
import excecoes.UsuarioJaInseridoNoGrupoException;
import persistencia.GrupoGateway;
import persistencia.UsuarioGateway;
import persistencia.Usuario_GrupoFinder;
import persistencia.Usuario_GrupoGateway;
import persistencia.UsuariosFinder;
import persistencia.GrupoFinder;

public class GruposScript {
	public int CriarGrupo(String emailCriador, String nome, String descricao, String regras, int limite) throws Exception{
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
		
		UsuariosFinder uFinder = new UsuariosFinder();
		UsuarioGateway uGateway = uFinder.find(emailCriador);
		
		if(uGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		Usuario_GrupoGateway ug = new Usuario_GrupoGateway(uGateway.get_id(), id);
		ug.Insert();
		
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
		
		if(g == null)
			throw new EntidadeNaoEncontradaException();
		
		Grupo grupo = new Grupo(g.get_nome(), g.get_descricao(), g.get_regras(), g.get_limite());
		grupo.set_id(id);
		
		Usuario_GrupoFinder associacaoFinder = new Usuario_GrupoFinder();
		Collection<Usuario_GrupoGateway> associacoesGateway = associacaoFinder.findByGrupo(grupo.get_id());
		
		for(Usuario_GrupoGateway a : associacoesGateway){
			UsuariosFinder uFinder = new UsuariosFinder();
			UsuarioGateway usuarioGateway = uFinder.find(a.get_usuarioId());
			
			Usuario usuario = new Usuario(usuarioGateway.get_nome(), usuarioGateway.get_email(), usuarioGateway.get_telefone());
			usuario.set_id(usuarioGateway.get_id());
			grupo.AdicionarMembro(usuario);
		}
		
		return grupo;
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
	
	public Collection<Grupo> GetAllGrupos() throws Exception{
		GrupoFinder finder = new GrupoFinder();
		Collection<GrupoGateway> grupoGateways = finder.getAll();
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		
		for(GrupoGateway g : grupoGateways){
			Grupo grupo = new Grupo(g.get_nome(), g.get_descricao(), g.get_regras(), g.get_limite());
			grupo.set_id(g.get_id());
			
			Usuario_GrupoFinder associacaoFinder = new Usuario_GrupoFinder();
			Collection<Usuario_GrupoGateway> associacoesGateway = associacaoFinder.findByGrupo(grupo.get_id());
			
			for(Usuario_GrupoGateway a : associacoesGateway){
				UsuariosFinder uFinder = new UsuariosFinder();
				UsuarioGateway usuarioGateway = uFinder.find(a.get_usuarioId());
				
				Usuario usuario = new Usuario(usuarioGateway.get_nome(), usuarioGateway.get_email(), usuarioGateway.get_telefone());
				grupo.AdicionarMembro(usuario);
			}
			
			grupos.add(grupo);
		}
		
		return grupos;
	}
	
	public void DeletarGrupo(int id) throws Exception {
		GrupoFinder finder = new GrupoFinder();
		GrupoGateway g = finder.find(id);
		g.Delete();
		
	}
}
