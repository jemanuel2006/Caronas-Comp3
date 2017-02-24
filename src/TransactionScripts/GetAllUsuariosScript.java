package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Usuario;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class GetAllUsuariosScript {
	
	public GetAllUsuariosScript() {
	}
	
	public Collection<Usuario> execute() throws Exception{
		UsuariosFinder finder = new UsuariosFinder();
		Collection<UsuarioGateway> usuariosGateway = finder.getAll();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		for(UsuarioGateway u : usuariosGateway){
			Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
			usuario.set_id(u.get_id());
			usuarios.add(usuario);
		}
		
		return usuarios;
	}
}
