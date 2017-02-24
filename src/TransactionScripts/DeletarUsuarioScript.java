package TransactionScripts;

import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class DeletarUsuarioScript {
	private int id;
	
	public DeletarUsuarioScript(int id) {
		this.id = id;
	}
	
	public void execute() throws Exception {
		UsuariosFinder finder = new UsuariosFinder();
		UsuarioGateway u = finder.find(id);
		u.Delete();
	}
}
