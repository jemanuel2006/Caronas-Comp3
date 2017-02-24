package TransactionScripts;

import persistencia.MotoristaFinder;
import persistencia.MotoristaGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;
import persistencia.VeiculoGateway;

public class AdicionarVeiculoScript {
	private int usuarioId;
	private String modelo;
	private String placa;
	private String cor;
	
	public AdicionarVeiculoScript(int usuarioId, String modelo, String placa, String cor) {
		this.usuarioId = usuarioId;
		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;
	}
	
	public int execute() throws Exception{
		MotoristaFinder mFinder = new MotoristaFinder();
		MotoristaGateway gateway = mFinder.find(usuarioId);
		if(gateway == null){
			UsuariosFinder uFinder = new UsuariosFinder();
			UsuarioGateway uGateway = uFinder.find(usuarioId);
			
			gateway = new MotoristaGateway(uGateway.get_id());
			gateway.Insert();
		}
		
		VeiculoGateway vGateway = new VeiculoGateway(modelo, placa, cor);
		vGateway.set_motoristaId(gateway.get_id());
		
		vGateway.Insert();
		
		return vGateway.get_id();
	}
}
