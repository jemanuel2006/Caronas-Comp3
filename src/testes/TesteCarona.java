package testes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TransactionScripts.CaronaScript;
import TransactionScripts.LogradouroScript;
import TransactionScripts.MotoristaScript;
import TransactionScripts.UsuariosScript;
import entidades.Carona;
import entidades.Usuario;
import excecoes.CaronaNoMesmoHorarioException;
import excecoes.EntidadeNaoEncontradaException;
import excecoes.LimiteVagasAtingidoException;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import persistencia.DatabaseConnector;

public class TesteCarona extends TestBase{
	@Test
	public void CriarCaronaComTodosOsCampos() throws Exception{
		CaronaScript cts = new CaronaScript();
		int veiculoId = 01;
		int motoristaId = 01;
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime());
		Date dia = new Date(sdf.parse("01/02/2017 00:00").getTime());
		int logradouroOrigemId = 26032730; 
		int logradouroDestinoId = 26032524;

		
		try{
			int id = cts.CriarCarona(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
			Carona c = cts.GetCarona(id);
			
			Assert.assertEquals(c.get_veiculo(), veiculoId);
			Assert.assertEquals(c.getMotoristaId(), motoristaId);
			Assert.assertEquals(c.getDia(), dia);
			Assert.assertEquals(c.getHora_saida(), hora_saida);
			Assert.assertEquals(c.get_origem(), logradouroOrigemId);
			Assert.assertEquals(c.get_destino(), logradouroDestinoId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test(expected=EntidadeNaoEncontradaException.class)
	public void CriarCaronaSemVeiculo() throws Exception{
		CaronaScript cts = new CaronaScript();
		int veiculoId= -1;
		int motoristaId = 01;
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime());
		Date dia = new Date(sdf.parse("01/02/2017 00:00").getTime());
		int logradouroOrigemId = 26032730; 
		int logradouroDestinoId = 26032524;
		
		cts.CriarCarona(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
	}
	
	@Test(expected=CaronaNoMesmoHorarioException.class)
	public void CriarCaronaNaMesmaDataHora() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		int id = uts.CriarUsuario(unome, uemail, utelefone);
		
		MotoristaScript vts = new MotoristaScript();
		String modelo = "modelo";
		String placa = "placa";
		String cor = "cor";
		int vId = vts.AdicionarVeiculo(id, modelo, placa, cor);
		
		LogradouroScript lts = new LogradouroScript();
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 44;
		
		int origemId = lts.CriarLogradouro(_cep, _cidade, _estado, _distrito, _endereco, _numero);
		
		String _cep2 = "2756453";
		String _cidade2 = "Sei lá";
		String _estado2 = "Estado qualquer";
		String _distrito2 = "Um distrito aí";
		String _endereco2 = "Uma rua inexistente";
		int _numero2 = 22;
		
		int destinoId = lts.CriarLogradouro(_cep2, _cidade2, _estado2, _distrito2, _endereco2, _numero2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime()); 
		Date dia = new Date(sdf.parse("01/02/2017 12:00").getTime());
		
		CaronaScript cts = new CaronaScript();
		int veiculoId= vId;
		int motoristaId = id;
		int logradouroOrigemId = origemId; 
		int logradouroDestinoId = destinoId;
		
		cts.CriarCarona(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		cts.CriarCarona(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
	}
	
	@Test
	public void AdicionarMembroEmCarona() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		int id = uts.CriarUsuario(unome, uemail, utelefone);
		
		String unome2 = "usuario 2 teste";
		String uemail2 = "teste2@fake.com";
		String utelefone2 = "836121234";
		int id2 = uts.CriarUsuario(unome2, uemail2, utelefone2);
		
		MotoristaScript vts = new MotoristaScript();
		String modelo = "modelo";
		String placa = "placa";
		String cor = "cor";
		int vId = vts.AdicionarVeiculo(id, modelo, placa, cor);
		
		LogradouroScript lts = new LogradouroScript();
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 44;
		
		int origemId = lts.CriarLogradouro(_cep, _cidade, _estado, _distrito, _endereco, _numero);
		
		String _cep2 = "2756453";
		String _cidade2 = "Sei lá";
		String _estado2 = "Estado qualquer";
		String _distrito2 = "Um distrito aí";
		String _endereco2 = "Uma rua inexistente";
		int _numero2 = 22;
		
		int destinoId = lts.CriarLogradouro(_cep2, _cidade2, _estado2, _distrito2, _endereco2, _numero2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime()); 
		Date dia = new Date(sdf.parse("01/02/2017 12:00").getTime());
		
		CaronaScript cts = new CaronaScript();
		int veiculoId= vId;
		int motoristaId = id;
		int logradouroOrigemId = origemId; 
		int logradouroDestinoId = destinoId;
		
		int cId = cts.CriarCarona(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		
		cts.AdicionarUsuarioEmCarona(cId, uemail2, destinoId);
		Carona carona = cts.GetCarona(cId);
		
		Assert.assertEquals(carona.GetParadas().size(), 1);
		Assert.assertEquals(carona.GetParadas().iterator().next().get_endereco().get_id(), destinoId);
	}
	
	@Test(expected=LimiteVagasAtingidoException.class)
	public void AdicionarNaCaronaMaisMebrosQueOPermitido() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		int id = uts.CriarUsuario(unome, uemail, utelefone);
		
		String unome2 = "usuario 2 teste";
		String uemail2 = "teste2@fake.com";
		String utelefone2 = "83611251321234";
		int id2 = uts.CriarUsuario(unome2, uemail2, utelefone2);
		
		String unome3 = "usuario 3 teste";
		String uemail3 = "teste3@fake.com";
		String utelefone3 = "34221123";
		int id3 = uts.CriarUsuario(unome3, uemail3, utelefone3);
		
		String unome4 = "usuario 4 teste";
		String uemail4 = "teste4@fake.com";
		String utelefone4 = "61424231";
		int id4 = uts.CriarUsuario(unome4, uemail4, utelefone4);
		
		String unome5 = "usuario 5 teste";
		String uemail5 = "teste5@fake.com";
		String utelefone5 = "125123215";
		int id5 = uts.CriarUsuario(unome5, uemail5, utelefone5);
		
		String unome6 = "usuario 6 teste";
		String uemail6 = "teste6@fake.com";
		String utelefone6 = "123112454124";
		int id6 = uts.CriarUsuario(unome6, uemail6, utelefone6);
		
		MotoristaScript vts = new MotoristaScript();
		String modelo = "modelo";
		String placa = "placa";
		String cor = "cor";
		int vId = vts.AdicionarVeiculo(id, modelo, placa, cor);
		
		LogradouroScript lts = new LogradouroScript();
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 44;
		
		int origemId = lts.CriarLogradouro(_cep, _cidade, _estado, _distrito, _endereco, _numero);
		
		String _cep2 = "2756453";
		String _cidade2 = "Sei lá";
		String _estado2 = "Estado qualquer";
		String _distrito2 = "Um distrito aí";
		String _endereco2 = "Uma rua inexistente";
		int _numero2 = 22;
		
		int destinoId = lts.CriarLogradouro(_cep2, _cidade2, _estado2, _distrito2, _endereco2, _numero2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime()); 
		Date dia = new Date(sdf.parse("01/02/2017 12:00").getTime());
		
		CaronaScript cts = new CaronaScript();
		int veiculoId= vId;
		int motoristaId = id;
		int logradouroOrigemId = origemId; 
		int logradouroDestinoId = destinoId;
		
		int cId = cts.CriarCarona(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		
		cts.AdicionarUsuarioEmCarona(cId, uemail2, destinoId);
		cts.AdicionarUsuarioEmCarona(cId, uemail3, destinoId);
		cts.AdicionarUsuarioEmCarona(cId, uemail4, destinoId);
		cts.AdicionarUsuarioEmCarona(cId, uemail5, destinoId);
		cts.AdicionarUsuarioEmCarona(cId, uemail6, destinoId);
	}
}
