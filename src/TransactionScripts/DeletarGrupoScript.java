package TransactionScripts;

import persistencia.GrupoFinder;
import persistencia.GrupoGateway;

public class DeletarGrupoScript {
	private int id;
	
	public DeletarGrupoScript(int id) {
		this.id = id;
	}
	
	public void execute() throws Exception {
		GrupoFinder finder = new GrupoFinder();
		GrupoGateway g = finder.find(id);
		g.Delete();
	}
}
