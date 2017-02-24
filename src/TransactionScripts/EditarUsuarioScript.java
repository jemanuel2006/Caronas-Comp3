package TransactionScripts;

import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class EditarUsuarioScript {
	private int id; 
	private String nome; 
	private String telefone;
	
	public EditarUsuarioScript(int id, String nome, String telefone) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public void execute() throws Exception{
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
}
