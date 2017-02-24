package TransactionScripts;

import entidades.Usuario;
import persistencia.UsuarioGateway;

public class CriarUsuarioScript {
	private String _nome;
	private String _email;
	private String _telefone;
	
	public CriarUsuarioScript(String nome, String email, String telefone) {
		_nome = nome;
		_email = email;
		_telefone = telefone;
	}
	
	public int execute() throws Exception{
		if(_nome == null || _nome.isEmpty()){
			throw new IllegalArgumentException("nome");
		}
		
		if(_email == null || _email.isEmpty()){
			throw new IllegalArgumentException("email");
		}
		
		if(_telefone == null || _telefone.isEmpty()){
			throw new IllegalArgumentException("telefone");
		}
		
		Usuario usuario = new Usuario(_nome, _email, _telefone);
		UsuarioGateway usuarioGateway = new UsuarioGateway(usuario);
		int id = usuarioGateway.Insert();
		usuario.set_id(id);
		
		return id;
	}
}
