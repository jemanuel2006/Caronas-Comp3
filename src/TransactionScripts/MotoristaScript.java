package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.MotoristaFinder;
import persistencia.MotoristaGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class MotoristaScript {
	public void AdicionarVeiculo(int usuarioId, String modelo, String placa, String cor) throws Exception{
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
		
		vGateway.Save();
	}
	
	public void AlterarVeiculo(int id, String cor) throws Exception{
		VeiculoFinder vFinder = new VeiculoFinder();
		VeiculoGateway gateway = vFinder.find(id);
		
		if(gateway == null){
			throw new EntidadeNaoEncontradaException();
		}

		gateway.set_cor(cor);
		gateway.Save();
	}
}
