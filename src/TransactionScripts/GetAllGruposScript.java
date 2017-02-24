package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Grupo;
import entidades.Usuario;
import persistencia.GrupoFinder;
import persistencia.GrupoGateway;
import persistencia.UsuarioGateway;
import persistencia.Usuario_GrupoFinder;
import persistencia.Usuario_GrupoGateway;
import persistencia.UsuariosFinder;

public class GetAllGruposScript {
	public GetAllGruposScript() {
		
	}
	
	public Collection<Grupo> execute() throws Exception{
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
}
