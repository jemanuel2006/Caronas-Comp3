package TransactionScripts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Carona;
import entidades.Parada;
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
	
	public void AdicionarUsuarioEmCarona(int caronaId, String emailUsuario, int paradaId) throws Exception{
		LogradouroFinder lFinder = new LogradouroFinder();
		LogradouroGateway lGateway = lFinder.find(paradaId);
		
		if(lGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		ParadaGateway p = new ParadaGateway();
		
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
		
		Carona carona = this.GetCarona(caronaId);
		if(carona.GetParadas().size() > Veiculo.maxVagas){
			throw new LimiteVagasAtingidoException();
		}
		
		p.set_caronaId(caronaId);
		p.set_usuarioId(u.get_id());
		p.set_logradouroId(paradaId);
		p.Insert();
	}
	
	public void RemoverUsuarioDeCarona(int paradaId) throws Exception{
		ParadaFinder pFinder = new ParadaFinder();
		ParadaGateway p = pFinder.find(paradaId);
		
		if(p == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		p.Delete();
	}
	
	public Carona GetCarona(int caronaId) throws Exception{
		CaronaFinder cFinder = new CaronaFinder();
		CaronaGateway cGateway = cFinder.find(caronaId);
		
		if(cGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		LogradouroScript lts = new LogradouroScript();
		MotoristaScript mts = new MotoristaScript();
		
		Carona carona = new Carona();
		carona.set_origem(lts.GetLogradouro(cGateway.get_logradouroOrigemId()));
		carona.set_destino(lts.GetLogradouro(cGateway.get_logradouroDestinoId()));
		carona.set_veiculo(mts.GetVeiculo(cGateway.get_veiculoId()));
		carona.setDia(cGateway.getDia());
		carona.setHora_saida(cGateway.getHora_saida());
		carona.set_estadoCarona(cGateway.get_estadoCarona());
		carona.set_id(caronaId);
		
		for(Parada p : GetParadasByCaronaId(caronaId)){
			carona.AdicionarParada(p);
		}
		
		return carona;
	}
	
	public Collection<Carona> GetCaronas() throws Exception{
		Collection<Carona> caronas = new ArrayList<>();
		CaronaFinder cFinder = new CaronaFinder();
		Collection<CaronaGateway> gateways = cFinder.getAll();
		
		LogradouroScript lts = new LogradouroScript();
		MotoristaScript mts = new MotoristaScript();
		
		for(CaronaGateway c : gateways){
			Carona carona = new Carona();
			carona.set_origem(lts.GetLogradouro(c.get_logradouroOrigemId()));
			carona.set_destino(lts.GetLogradouro(c.get_logradouroDestinoId()));
			carona.set_veiculo(mts.GetVeiculo(c.get_veiculoId()));
			carona.setDia(c.getDia());
			carona.setHora_saida(c.getHora_saida());
			carona.set_estadoCarona(c.get_estadoCarona());
			carona.set_id(c.get_id());
			
			for(Parada p : GetParadasByCaronaId(c.get_id())){
				carona.AdicionarParada(p);
			}
			
			caronas.add(carona);
		}
		
		return caronas;
	}
	
	public Collection<Parada> GetParadasByCaronaId(int caronaId) throws Exception{
		ParadaFinder pFinder = new ParadaFinder();
		Collection<ParadaGateway> gateways = pFinder.getParadasByCarona(caronaId);
		Collection<Parada> paradas = new ArrayList<>();
		
		LogradouroScript lts = new LogradouroScript();
		UsuariosScript uts = new UsuariosScript();
		
		for(ParadaGateway c : gateways){
			Parada parada = new Parada();
			parada.set_endereco(lts.GetLogradouro(c.get_logradouroId()));
			parada.set_usuario(uts.GetUsuario(c.get_usuarioId()));
			parada.set_id(c.get_id());
			paradas.add(parada);
		}
		
		return paradas;
	}
	
	public Collection<Carona> GetCaronasDisponiveis() throws Exception{
		Collection<Carona> caronas = this.GetCaronas();
		Collection<Carona> caronasDisponiveis = new ArrayList<>();
		
		for(Carona carona: caronas){
			if(carona.get_estadoCarona() == 0 && carona.GetParadas().size() < Veiculo.maxVagas)
				caronasDisponiveis.add(carona);
		}
		
		return caronasDisponiveis;
	}
	
	public Parada GetParada(int paradaId) throws Exception{
		ParadaFinder pFinder = new ParadaFinder();
		ParadaGateway pGateway = pFinder.find(paradaId);
		
		if(pGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		LogradouroScript lts = new LogradouroScript();
		UsuariosScript us = new UsuariosScript();
		
		Parada parada = new Parada();
		parada.set_endereco(lts.GetLogradouro(pGateway.get_logradouroId()));
		parada.set_id(paradaId);
		parada.set_usuario(us.GetUsuario(pGateway.get_usuarioId()));
		
		return parada;
	}
	
	public void FinalizarCarona(int caronaId) throws Exception{
		CaronaFinder cFinder = new CaronaFinder();
		CaronaGateway cGateway = cFinder.find(caronaId);
		
		if(cGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		//Concluída
		cGateway.set_estadoCarona(1);
		cGateway.Save();
	}
	
	public void CancelarCarona(int caronaId) throws Exception{
		CaronaFinder cFinder = new CaronaFinder();
		CaronaGateway cGateway = cFinder.find(caronaId);
		
		if(cGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		//Cancelada
		cGateway.set_estadoCarona(2);
		cGateway.Save();
	}
	
	public boolean PodeAlterarCarona(int caronaId) throws Exception{
		Carona c = this.GetCarona(caronaId);
		
		return c.GetParadas().isEmpty();
	}
}
