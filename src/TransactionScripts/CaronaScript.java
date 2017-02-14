package TransactionScripts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Veiculo;
import excecoes.CaronaComUsuariosException;
import excecoes.CaronaNoMesmoHorarioException;
import excecoes.EntidadeNaoEncontradaException;
import excecoes.LimiteVagasAtingidoException;
import persistencia.CaronaFinder;
import persistencia.CaronaGateway;
import persistencia.DestinoFinder;
import persistencia.DestinoGateway;
import persistencia.LogradouroFinder;
import persistencia.LogradouroGateway;
import persistencia.MotoristaFinder;
import persistencia.MotoristaGateway;
import persistencia.OrigemFinder;
import persistencia.OrigemGateway;
import persistencia.ParadaFinder;
import persistencia.ParadaGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class CaronaScript {
	public int CriarCarona(int veiculoId, int motoristaId, Date dia, Date hora_saida, int logradouroOrigemId, int logradouroDestinoId) throws Exception{
		Collection<CaronaGateway> caronas = new ArrayList<CaronaGateway>();
		CaronaFinder cFinder = new CaronaFinder();
		caronas = cFinder.getAll();
		
		for(CaronaGateway carona: caronas){
			//Verificar se compara data/hora certo
			if(carona.get_motoristaId() == motoristaId && carona.getDia() == dia && carona.getHora_saida() == hora_saida){
				throw new CaronaNoMesmoHorarioException();
			}
		}
		
		VeiculoFinder vFinder = new VeiculoFinder();
		VeiculoGateway vGateway = vFinder.find(veiculoId);
		
		if(vGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		MotoristaFinder mFinder = new MotoristaFinder();
		MotoristaGateway mGateway = mFinder.find(motoristaId);
		
		if(mGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		OrigemFinder oFinder = new OrigemFinder();
		OrigemGateway oGateway = oFinder.find(logradouroOrigemId);
		
		LogradouroFinder lFinder = new LogradouroFinder();
		
		if(oGateway == null){
			
			LogradouroGateway origemGateway = lFinder.find(logradouroOrigemId);
			
			if(origemGateway == null){
				throw new EntidadeNaoEncontradaException();
			}
			
			oGateway = new OrigemGateway(logradouroOrigemId);
			oGateway.Insert();
		}
		
		DestinoFinder dFinder = new DestinoFinder();
		DestinoGateway dGateway = dFinder.find(logradouroDestinoId);
		
		if(dGateway == null){
			LogradouroGateway destinoGateway = lFinder.find(logradouroDestinoId);
			
			if(destinoGateway == null){
				throw new EntidadeNaoEncontradaException();
			}
			
			dGateway = new DestinoGateway(logradouroDestinoId);
			dGateway.Insert();
		}
		
		CaronaGateway cGateway = new CaronaGateway();
		
		cGateway.set_estadoCarona(0);
		cGateway.set_logradouroDestinoId(logradouroDestinoId);
		cGateway.set_logradouroOrigemId(logradouroOrigemId);
		cGateway.set_motoristaId(motoristaId);
		cGateway.set_veiculoId(veiculoId);
		cGateway.setDia(dia);
		cGateway.setHora_saida(hora_saida);
		
		cGateway.Insert();
		
		return cGateway.get_id();
	}
	
	public void EditarCarona(int caronaId, int motoristaId, int veiculoId, Date dia, Date hora_saida, int logradouroOrigemId, int logradouroDestinoId) throws Exception{
		CaronaFinder cFinder = new CaronaFinder();
		CaronaGateway cGateway = new CaronaGateway();
		
		cGateway = cFinder.find(caronaId);
		
		if(cGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		if(cGateway.get_logradouroDestinoId() != logradouroDestinoId || cGateway.get_logradouroOrigemId() != logradouroOrigemId){
			Collection<ParadaGateway> paradas = new ArrayList<ParadaGateway>();
			ParadaFinder pFinder = new ParadaFinder();
			
			paradas = pFinder.getParadasByCarona(caronaId);
			
			if(paradas.isEmpty() == false){
				throw new CaronaComUsuariosException();
			}
		}
		
		Collection<CaronaGateway> caronas = new ArrayList<CaronaGateway>();
		caronas = cFinder.getAll();
		
		for(CaronaGateway carona: caronas){
			//Verificar se compara data/hora certo
			if(carona.get_motoristaId() == motoristaId && carona.getDia() == dia && carona.getHora_saida() == hora_saida){
				throw new CaronaNoMesmoHorarioException();
			}
		}
		
		VeiculoFinder vFinder = new VeiculoFinder();
		VeiculoGateway vGateway = vFinder.find(veiculoId);
		
		if(vGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		MotoristaFinder mFinder = new MotoristaFinder();
		MotoristaGateway mGateway = mFinder.find(motoristaId);
		
		if(mGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		LogradouroFinder lFinder = new LogradouroFinder();
		LogradouroGateway origemGateway = lFinder.find(logradouroOrigemId);
		
		if(origemGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		LogradouroGateway destinoGateway = lFinder.find(logradouroDestinoId);
		
		if(destinoGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		cGateway.set_estadoCarona(0);
		cGateway.set_logradouroDestinoId(logradouroDestinoId);
		cGateway.set_logradouroOrigemId(logradouroOrigemId);
		cGateway.set_motoristaId(motoristaId);
		cGateway.set_veiculoId(veiculoId);
		cGateway.setDia(dia);
		cGateway.setHora_saida(hora_saida);
	}
	
	public void AdicionarUsuarioEmCarona(int caronaId, String emailUsuario, int paradaId) throws Exception{
		ParadaFinder pFinder = new ParadaFinder();
		ParadaGateway p = pFinder.find(paradaId);
		
		if(p == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		UsuariosFinder uFinder = new UsuariosFinder();
		UsuarioGateway u = uFinder.find(emailUsuario);
		
		if(u == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		CaronaFinder cFinder = new CaronaFinder();
		CaronaGateway c = cFinder.find(caronaId);
		
		if(c == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		if(pFinder.getParadasByCarona(caronaId).size() >= Veiculo.maxVagas){
			throw new LimiteVagasAtingidoException();
		}
		
		p.set_caronaId(caronaId);
	}
}
