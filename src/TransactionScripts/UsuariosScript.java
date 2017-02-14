package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Usuario;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class UsuariosScript {
	public int CriarUsuario(String nome, String email, String telefone) throws Exception{
		if(nome == null || nome.isEmpty()){
			throw new IllegalArgumentException("nome");
		}
		
		if(email == null || email.isEmpty()){
			throw new IllegalArgumentException("email");
		}
		
		if(telefone == null || telefone.isEmpty()){
			throw new IllegalArgumentException("telefone");
		}
		
		Usuario usuario = new Usuario(nome, email, telefone);
		UsuarioGateway usuarioGateway = new UsuarioGateway(usuario);
		int id = usuarioGateway.Insert();
		usuario.set_id(id);
		
		return id;
	}
	
	public void EditarUsuario(int id, String nome, String telefone) throws Exception{
		if(nome == null || nome.isEmpty()){
			throw new IllegalArgumentException("nome");
		}
		
		if(telefone == null || telefone.isEmpty()){
			throw new IllegalArgumentException("telefone");
		}
		
		UsuariosFinder finder = new UsuariosFinder();
		UsuarioGateway usuarioGateway = finder.find(id);
		
		usuarioGateway.set_nome(nome);
		usuarioGateway.set_telefone(telefone);
		
		usuarioGateway.Save();
	}
	
	public Usuario GetUsuario(int id) throws Exception{
		UsuariosFinder finder = new UsuariosFinder();
		UsuarioGateway u = finder.find(id);
		Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
		usuario.set_id(id);
		
		return usuario;
	}
	
	public Collection<Usuario> GetAllUsuarios() throws Exception{
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
	
	public void DeletarUsuario(int id) throws Exception {
		UsuariosFinder finder = new UsuariosFinder();
		UsuarioGateway u = finder.find(id);
		u.Delete();
	}
}
