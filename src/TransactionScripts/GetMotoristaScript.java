package TransactionScripts;

import java.util.Collection;

import entidades.Motorista;
import entidades.Veiculo;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class GetMotoristaScript {
	private int usuarioId;
	
	public GetMotoristaScript(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Motorista execute() throws Exception{
		UsuariosFinder uFinder = new UsuariosFinder();
		UsuarioGateway uGateway = uFinder.find(usuarioId);
		
		if(uGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		Motorista motorista = new Motorista(uGateway.get_nome(), uGateway.get_email(), uGateway.get_telefone());
		motorista.set_id(usuarioId);
		
		VeiculoFinder vFinder = new VeiculoFinder();
		Collection<VeiculoGateway> veiculos = vFinder.getVeiculosByUsuario(usuarioId);
		
		for(VeiculoGateway v : veiculos){
			Veiculo veiculo = new Veiculo();
			veiculo.set_cor(v.get_cor());
			veiculo.set_modelo(v.get_modelo());
			veiculo.set_placa(v.get_placa());
			veiculo.set_id(v.get_id());
			veiculo.set_motorista(motorista);
			motorista.AdicionarVeiculo(veiculo);
		}
		
		return motorista;
	}
}
