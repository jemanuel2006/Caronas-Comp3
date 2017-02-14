package TransactionScripts;

import java.util.Collection;

import entidades.Motorista;
import entidades.Veiculo;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.MotoristaFinder;
import persistencia.MotoristaGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class MotoristaScript {
	public int AdicionarVeiculo(int usuarioId, String modelo, String placa, String cor) throws Exception{
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
	
	public void AlterarVeiculo(int id, String cor) throws Exception{
		VeiculoFinder vFinder = new VeiculoFinder();
		VeiculoGateway gateway = vFinder.find(id);
		
		if(gateway == null){
			throw new EntidadeNaoEncontradaException();
		}

		gateway.set_cor(cor);
		gateway.Save();
	}
	
	public Motorista GetMotorista(int usuarioId) throws Exception{
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
			motorista.AdicionarVeiculo(veiculo);
		}
		
		return motorista;
	}
	
	public Veiculo GetVeiculo(int veiculoId) throws Exception {
		VeiculoFinder vFinder = new VeiculoFinder();
		VeiculoGateway vGateway = vFinder.find(veiculoId);
		
		if(vGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		Veiculo v = new Veiculo();
		v.set_cor(vGateway.get_cor());
		v.set_id(veiculoId);
		v.set_modelo(vGateway.get_modelo());
		v.set_placa(vGateway.get_placa());
		
		return v;
	}
}
