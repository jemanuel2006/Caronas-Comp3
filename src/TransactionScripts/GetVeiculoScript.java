package TransactionScripts;

import java.util.Collection;

import entidades.Motorista;
import entidades.Veiculo;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class GetVeiculoScript {
	private int veiculoId;
	
	public GetVeiculoScript(int veiculoId) {
		this.veiculoId = veiculoId;
	}
	
	public Veiculo execute() throws Exception {
		VeiculoFinder vFinder = new VeiculoFinder();
		VeiculoGateway vGateway = vFinder.find(veiculoId);
		
		if(vGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		UsuariosFinder uFinder = new UsuariosFinder();
		UsuarioGateway uGateway = uFinder.find(vGateway.get_motoristaId());
		
		if(uGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		Motorista motorista = new Motorista(uGateway.get_nome(), uGateway.get_email(), uGateway.get_telefone());
		motorista.set_id(vGateway.get_motoristaId());
		
		Collection<VeiculoGateway> veiculos = vFinder.getVeiculosByUsuario(vGateway.get_motoristaId());
		
		for(VeiculoGateway v : veiculos){
			Veiculo veiculo = new Veiculo();
			veiculo.set_cor(v.get_cor());
			veiculo.set_modelo(v.get_modelo());
			veiculo.set_placa(v.get_placa());
			veiculo.set_id(v.get_id());
			veiculo.set_motorista(motorista);
			motorista.AdicionarVeiculo(veiculo);
		}
		
		Veiculo v = new Veiculo();
		v.set_cor(vGateway.get_cor());
		v.set_id(veiculoId);
		v.set_modelo(vGateway.get_modelo());
		v.set_placa(vGateway.get_placa());
		v.set_motorista(motorista);
		
		return v;
	}
}
