package TransactionScripts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import excecoes.CaronaNoMesmoHorarioException;
import excecoes.EntidadeNaoEncontradaException;
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
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class CriarCaronaScript {
	private int veiculoId;
	private int motoristaId;
	private Date dia;
	private Date hora_saida;
	private int logradouroOrigemId;
	private int logradouroDestinoId;
	
	public CriarCaronaScript(int veiculoId, int motoristaId, Date dia, Date hora_saida, int logradouroOrigemId, int logradouroDestinoId) {
		this.veiculoId = veiculoId;
		this.motoristaId = motoristaId;
		this.dia = dia;
		this.hora_saida = hora_saida;
		this.logradouroOrigemId = logradouroOrigemId;
		this.logradouroDestinoId = logradouroDestinoId;
	}
	
	public int execute() throws Exception{
		Collection<CaronaGateway> caronas = new ArrayList<CaronaGateway>();
		CaronaFinder cFinder = new CaronaFinder();
		caronas = cFinder.getAll();
		
		for(CaronaGateway carona: caronas){
			//Verificar se compara data/hora certo
			if(carona.get_motoristaId() == motoristaId && DataIgual(carona.getDia(),dia) && DataIgual(carona.getHora_saida(), hora_saida)){
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
	
	@SuppressWarnings("deprecation")
	private boolean DataIgual(Date date1, Date date2){
		return date1.getYear() == date2.getYear() && date1.getDay() == date2.getDay() && date1.getMonth() == date2.getMonth();
	}
}
