package TransactionScripts;

import java.sql.Date;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.CaronaGateway;
import persistencia.LogradouroFinder;
import persistencia.LogradouroGateway;
import persistencia.MotoristaFinder;
import persistencia.MotoristaGateway;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class CaronaScript {
	public int CriarCarona(int veiculoId, int motoristaId, Date dia, Date hora_saida, int logradouroOrigemId, int logradouroDestinoId) throws Exception{
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
}
