package TransactionScripts;

import java.util.ArrayList;
import java.util.Collection;

import entidades.Avaliacao;
import entidades.Carona;
import entidades.Logradouro;
import entidades.Motorista;
import entidades.Parada;
import entidades.Usuario;
import entidades.Veiculo;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.AvaliacaoFinder;
import persistencia.AvaliacaoGateway;
import persistencia.CaronaFinder;
import persistencia.CaronaGateway;
import persistencia.LogradouroFinder;
import persistencia.LogradouroGateway;
import persistencia.ParadaFinder;
import persistencia.ParadaGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class GetCaronasDisponiveisScript {
	public GetCaronasDisponiveisScript() {
	}
	
	public Collection<Carona> execute() throws Exception{
		Collection<Carona> caronas = new ArrayList<>();
		CaronaFinder cFinder = new CaronaFinder();
		Collection<CaronaGateway> gateways = cFinder.getAll();
		
		for(CaronaGateway c : gateways){
			LogradouroFinder lFinder = new LogradouroFinder();
			LogradouroGateway logOrigemGateway = lFinder.find(c.get_logradouroOrigemId());
			
			if(logOrigemGateway == null){
				throw new EntidadeNaoEncontradaException();
			}
			
			Logradouro logOrigem = new Logradouro();
			
			logOrigem.set_cep(logOrigemGateway.get_cep());
			logOrigem.set_cidade(logOrigemGateway.get_cidade());
			logOrigem.set_distrito(logOrigemGateway.get_distrito());
			logOrigem.set_endereco(logOrigemGateway.get_endereco());
			logOrigem.set_estado(logOrigemGateway.get_estado());
			logOrigem.set_numero(logOrigemGateway.get_numero());
			logOrigem.set_id(logOrigemGateway.get_id());
			
			LogradouroGateway logDestinoGateway = lFinder.find(c.get_logradouroOrigemId());
			
			if(logDestinoGateway == null){
				throw new EntidadeNaoEncontradaException();
			}
			
			Logradouro logDestino = new Logradouro();
			
			logDestino.set_cep(logDestinoGateway.get_cep());
			logDestino.set_cidade(logDestinoGateway.get_cidade());
			logDestino.set_distrito(logDestinoGateway.get_distrito());
			logDestino.set_endereco(logDestinoGateway.get_endereco());
			logDestino.set_estado(logDestinoGateway.get_estado());
			logDestino.set_numero(logDestinoGateway.get_numero());
			logDestino.set_id(logDestinoGateway.get_id());
			
			VeiculoFinder vFinder = new VeiculoFinder();
			VeiculoGateway vGateway = vFinder.find(c.get_veiculoId());
			
			if(vGateway == null)
				throw new EntidadeNaoEncontradaException();
			
			UsuariosFinder uFinder = new UsuariosFinder();
			UsuarioGateway uGateway = uFinder.find(vGateway.get_motoristaId());
			
			if(uGateway == null)
				throw new EntidadeNaoEncontradaException();
			
			Motorista motorista = new Motorista(uGateway.get_nome(), uGateway.get_email(), uGateway.get_telefone());
			motorista.set_id(vGateway.get_motoristaId());
			
			Collection<VeiculoGateway> veiculos = vFinder.getVeiculosByUsuario(vGateway.get_motoristaId());
			
			for(VeiculoGateway vG : veiculos){
				Veiculo veiculo = new Veiculo();
				veiculo.set_cor(vG.get_cor());
				veiculo.set_modelo(vG.get_modelo());
				veiculo.set_placa(vG.get_placa());
				veiculo.set_id(vG.get_id());
				veiculo.set_motorista(motorista);
				motorista.AdicionarVeiculo(veiculo);
			}
			
			Veiculo v = new Veiculo();
			v.set_cor(vGateway.get_cor());
			v.set_id(c.get_veiculoId());
			v.set_modelo(vGateway.get_modelo());
			v.set_placa(vGateway.get_placa());
			v.set_motorista(motorista);
			
			Carona carona = new Carona();
			carona.set_origem(logOrigem);
			carona.set_destino(logDestino);
			carona.set_veiculo(v);
			carona.setDia(c.getDia());
			carona.setHora_saida(c.getHora_saida());
			carona.set_estadoCarona(c.get_estadoCarona());
			carona.set_id(c.get_id());
			
			for(Parada p : GetParadasByCaronaId(c.get_id())){
				carona.AdicionarParada(p);
			}
			
			caronas.add(carona);
		}
		
		Collection<Carona> caronasDisponiveis = new ArrayList<>();
		
		for(Carona carona: caronas){
			if(carona.get_estadoCarona() == 0 && carona.GetParadas().size() < Veiculo.maxVagas)
				caronasDisponiveis.add(carona);
		}
		
		return caronasDisponiveis;
	}
	
	private Collection<Parada> GetParadasByCaronaId(int caronaId) throws Exception{
		ParadaFinder pFinder = new ParadaFinder();
		Collection<ParadaGateway> gateways = pFinder.getParadasByCarona(caronaId);
		Collection<Parada> paradas = new ArrayList<>();
		
		for(ParadaGateway c : gateways){
			LogradouroFinder lFinder = new LogradouroFinder();
			LogradouroGateway gateway = lFinder.find(c.get_logradouroId());
			
			if(gateway == null){
				throw new EntidadeNaoEncontradaException();
			}
			
			Logradouro l = new Logradouro();
			
			l.set_cep(gateway.get_cep());
			l.set_cidade(gateway.get_cidade());
			l.set_distrito(gateway.get_distrito());
			l.set_endereco(gateway.get_endereco());
			l.set_estado(gateway.get_estado());
			l.set_numero(gateway.get_numero());
			l.set_id(gateway.get_id());
			
			UsuariosFinder finder = new UsuariosFinder();
			UsuarioGateway u = finder.find(c.get_usuarioId());
			
			if(u == null)
				throw new EntidadeNaoEncontradaException();
			
			Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
			usuario.set_id(c.get_usuarioId());
			
			AvaliacaoFinder aFinder = new AvaliacaoFinder();
			
			for(AvaliacaoGateway a: aFinder.getByUsuario(c.get_usuarioId())){
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.set_avaliacao(a.get_estrelas());
				avaliacao.set_id(a.get_id());
				avaliacao.set_usuario(usuario);
				
				usuario.AdicionarAvaliacao(avaliacao);
			}
			
			Parada parada = new Parada();
			parada.set_endereco(l);
			parada.set_usuario(usuario);
			parada.set_id(c.get_id());
			paradas.add(parada);
		}
		
		return paradas;
	}
}
