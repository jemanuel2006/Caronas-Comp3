package TransactionScripts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import excecoes.CaronaComUsuariosException;
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
import persistencia.ParadaFinder;
import persistencia.ParadaGateway;
import persistencia.VeiculoFinder;
import persistencia.VeiculoGateway;

public class EditarCaronaScript {
	private int caronaId;
	private int veiculoId;
	private int motoristaId;
	private Date dia;
	private Date hora_saida;
	private int logradouroOrigemId;
	private int logradouroDestinoId;
	
	public EditarCaronaScript(int caronaId, int motoristaId, int veiculoId, Date dia, Date hora_saida, int logradouroOrigemId, int logradouroDestinoId) {
		this.caronaId = caronaId;
		this.veiculoId = veiculoId;
		this.motoristaId = motoristaId;
		this.dia = dia;
		this.hora_saida = hora_saida;
		this.logradouroOrigemId = logradouroOrigemId;
		this.logradouroDestinoId = logradouroDestinoId;
	}
	
	public void execute() throws Exception{
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
		
		cGateway.set_estadoCarona(0);
		cGateway.set_logradouroDestinoId(logradouroDestinoId);
		cGateway.set_logradouroOrigemId(logradouroOrigemId);
		cGateway.set_motoristaId(motoristaId);
		cGateway.set_veiculoId(veiculoId);
		cGateway.setDia(dia);
		cGateway.setHora_saida(hora_saida);
		
		cGateway.Save();
	}
}
