package testes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TransactionScripts.CaronaScript;
import TransactionScripts.UsuariosScript;
import entidades.Carona;
import entidades.Usuario;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import persistencia.DatabaseConnector;

public class TesteCarona {
	
	@Before
	public void Setup(){
		try{
			DatabaseConnector db = new DatabaseConnector();
			String criarBanco = "create database bancocaronascomp3_tests";
			PreparedStatement ps = db.getConnection().prepareStatement(criarBanco);
			ps.executeUpdate();
			
			String criarTabelaCaronas = "create table bancocaronascomp3_tests.usuario(id int not null auto_increment, dia date not null, hora_saida date not null, motoristaId int not null, veiculoId int not null, origemId int not null, destinoId int not null, estadoCarona int not null);";
			ps = db.getConnection().prepareStatement(criarTabelaCaronas);
			ps.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@After
	public void Destroy(){
			try{
				DatabaseConnector db = new DatabaseConnector();
				String removerBanco = "drop database bancocaronascomp3_tests;";
				PreparedStatement ps = db.getConnection().prepareStatement(removerBanco);
				ps.executeUpdate();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		
	}
	
	@Test
	public void CriarCaronaComTodosOsCampos(){
		CaronaScript cts = new CaronaScript();
		int veiculoId = 01;
		int motoristaId = 01;
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse(request.getParameter("12:00")).getTime());
		Date dia = new Date(sdf.parse(request.getParameter("01/02/2017")).getTime());
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
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarCaronaSemVeiculo() throws Exception{
		CaronaScript cts = new CaronaScript();
		int veiculoId= (Integer) null;
		int motoristaId = 01;
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY HH:mm");
		Date hora_saida = new Date(sdf.parse(request.getParameter("12:00")).getTime());
		Date dia = new Date(sdf.parse(request.getParameter("01/02/2017")).getTime());
		int logradouroOrigemId = 26032730; 
		int logradouroDestinoId = 26032524;
		
		cts.CriarCarona(veiculoId, motoristaId, dia, hora_saida, logradouroOrigemId, logradouroDestinoId);
	}
	

}
