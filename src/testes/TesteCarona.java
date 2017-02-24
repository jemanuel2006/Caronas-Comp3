package testes;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import TransactionScripts.AdicionarUsuarioEmCaronaScript;
import TransactionScripts.AdicionarVeiculoScript;
import TransactionScripts.CriarCaronaScript;
import TransactionScripts.CriarLogradouroScript;
import TransactionScripts.CriarUsuarioScript;
import TransactionScripts.GetCaronaScript;
import entidades.Carona;
import excecoes.CaronaNoMesmoHorarioException;
import excecoes.EntidadeNaoEncontradaException;
import excecoes.LimiteVagasAtingidoException;

public class TesteCarona extends TestBase{
	@Test
	public void CriarCaronaComTodosOsCampos() throws Exception{
		int veiculoId = 01;
		int motoristaId = 01;
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime());
		Date dia = new Date(sdf.parse("01/02/2017 00:00").getTime());
		int logradouroOrigemId = 26032730; 
		int logradouroDestinoId = 26032524;

		
		try{
			CriarCaronaScript crc = new CriarCaronaScript(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
			int id = crc.execute();
			
			GetCaronaScript cts = new GetCaronaScript(id);
			Carona c = cts.execute();
			
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
		int veiculoId= -1;
		int motoristaId = 01;
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime());
		Date dia = new Date(sdf.parse("01/02/2017 00:00").getTime());
		int logradouroOrigemId = 26032730; 
		int logradouroDestinoId = 26032524;
		
		CriarCaronaScript cts = new CriarCaronaScript(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		cts.execute();
	}
	
	@Test(expected=CaronaNoMesmoHorarioException.class)
	public void CriarCaronaNaMesmaDataHora() throws Exception{
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		CriarUsuarioScript uts = new CriarUsuarioScript(unome, uemail, utelefone);
		int id = uts.execute();
		
		String modelo = "modelo";
		String placa = "placa";
		String cor = "cor";
		AdicionarVeiculoScript vts = new AdicionarVeiculoScript(id, modelo, placa, cor);
		int vId = vts.execute();
		
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 44;
		
		CriarLogradouroScript lts = new CriarLogradouroScript(_cep, _cidade, _estado, _distrito, _endereco, _numero);
		int origemId = lts.execute();
		
		String _cep2 = "2756453";
		String _cidade2 = "Sei lá";
		String _estado2 = "Estado qualquer";
		String _distrito2 = "Um distrito aí";
		String _endereco2 = "Uma rua inexistente";
		int _numero2 = 22;
		
		lts = new CriarLogradouroScript(_cep2, _cidade2, _estado2, _distrito2, _endereco2, _numero2);
		int destinoId = lts.execute();
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime()); 
		Date dia = new Date(sdf.parse("01/02/2017 12:00").getTime());
		
		int veiculoId= vId;
		int motoristaId = id;
		int logradouroOrigemId = origemId; 
		int logradouroDestinoId = destinoId;
		
		CriarCaronaScript cts = new CriarCaronaScript(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		cts.execute();
		
		cts = new CriarCaronaScript(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		cts.execute();
	}
	
	@Test
	public void AdicionarMembroEmCarona() throws Exception{
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		CriarUsuarioScript uts = new CriarUsuarioScript(unome, uemail, utelefone);
		int id = uts.execute();
		
		String unome2 = "usuario 2 teste";
		String uemail2 = "teste2@fake.com";
		String utelefone2 = "836121234";
		uts = new CriarUsuarioScript(unome2, uemail2, utelefone2);
		int id2 = uts.execute();
		
		String modelo = "modelo";
		String placa = "placa";
		String cor = "cor";
		AdicionarVeiculoScript vts = new AdicionarVeiculoScript(id, modelo, placa, cor);
		int vId = vts.execute();
		
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 44;
		
		CriarLogradouroScript lts = new CriarLogradouroScript(_cep, _cidade, _estado, _distrito, _endereco, _numero);
		int origemId = lts.execute();
		
		String _cep2 = "2756453";
		String _cidade2 = "Sei lá";
		String _estado2 = "Estado qualquer";
		String _distrito2 = "Um distrito aí";
		String _endereco2 = "Uma rua inexistente";
		int _numero2 = 22;
		
		lts = new CriarLogradouroScript(_cep2, _cidade2, _estado2, _distrito2, _endereco2, _numero2);
		int destinoId = lts.execute();
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime()); 
		Date dia = new Date(sdf.parse("01/02/2017 12:00").getTime());
		
		int veiculoId= vId;
		int motoristaId = id;
		int logradouroOrigemId = origemId; 
		int logradouroDestinoId = destinoId;
		
		CriarCaronaScript cts = new CriarCaronaScript(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		int cId = cts.execute();
		
		AdicionarUsuarioEmCaronaScript auc = new AdicionarUsuarioEmCaronaScript(cId, uemail2, destinoId);
		auc.execute();
		
		GetCaronaScript gcs = new GetCaronaScript(cId);
		Carona carona = gcs.execute();
		
		Assert.assertEquals(carona.GetParadas().size(), 1);
		Assert.assertEquals(carona.GetParadas().iterator().next().get_endereco().get_id(), destinoId);
	}
	
	@Test(expected=LimiteVagasAtingidoException.class)
	public void AdicionarNaCaronaMaisMebrosQueOPermitido() throws Exception{
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		CriarUsuarioScript uts = new CriarUsuarioScript(unome, uemail, utelefone);
		int id = uts.execute();
		
		String unome2 = "usuario 2 teste";
		String uemail2 = "teste2@fake.com";
		String utelefone2 = "83611251321234";
		uts = new CriarUsuarioScript(unome2, uemail2, utelefone2);
		int id2 = uts.execute();
		
		String unome3 = "usuario 3 teste";
		String uemail3 = "teste3@fake.com";
		String utelefone3 = "34221123";
		uts = new CriarUsuarioScript(unome3, uemail3, utelefone3);
		int id3 = uts.execute();
		
		String unome4 = "usuario 4 teste";
		String uemail4 = "teste4@fake.com";
		String utelefone4 = "61424231";
		uts = new CriarUsuarioScript(unome4, uemail4, utelefone4);
		int id4 = uts.execute();
		
		String unome5 = "usuario 5 teste";
		String uemail5 = "teste5@fake.com";
		String utelefone5 = "125123215";
		uts = new CriarUsuarioScript(unome5, uemail5, utelefone5);
		int id5 = uts.execute();
		
		String unome6 = "usuario 6 teste";
		String uemail6 = "teste6@fake.com";
		String utelefone6 = "123112454124";
		uts = new CriarUsuarioScript(unome6, uemail6, utelefone6);
		int id6 = uts.execute();
		
		String modelo = "modelo";
		String placa = "placa";
		String cor = "cor";
		AdicionarVeiculoScript vts = new AdicionarVeiculoScript(id, modelo, placa, cor);
		int vId = vts.execute();
		
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 44;
		
		CriarLogradouroScript lts = new CriarLogradouroScript(_cep, _cidade, _estado, _distrito, _endereco, _numero);
		int origemId = lts.execute();
		
		String _cep2 = "2756453";
		String _cidade2 = "Sei lá";
		String _estado2 = "Estado qualquer";
		String _distrito2 = "Um distrito aí";
		String _endereco2 = "Uma rua inexistente";
		int _numero2 = 22;
		
		lts = new CriarLogradouroScript(_cep2, _cidade2, _estado2, _distrito2, _endereco2, _numero2);
		int destinoId = lts.execute();
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse("01/02/2017 12:00").getTime()); 
		Date dia = new Date(sdf.parse("01/02/2017 12:00").getTime());
		
		int veiculoId= vId;
		int motoristaId = id;
		int logradouroOrigemId = origemId; 
		int logradouroDestinoId = destinoId;
		
		CriarCaronaScript cts = new CriarCaronaScript(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
		int cId = cts.execute();
		
		AdicionarUsuarioEmCaronaScript aucs = new AdicionarUsuarioEmCaronaScript(cId, uemail2, destinoId);
		aucs.execute();
		
		aucs = new AdicionarUsuarioEmCaronaScript(cId, uemail3, destinoId);
		aucs.execute();
		
		aucs = new AdicionarUsuarioEmCaronaScript(cId, uemail4, destinoId);
		aucs.execute();
		
		aucs = new AdicionarUsuarioEmCaronaScript(cId, uemail5, destinoId);
		aucs.execute();
		
		aucs = new AdicionarUsuarioEmCaronaScript(cId, uemail6, destinoId);
		aucs.execute();
	}
}
