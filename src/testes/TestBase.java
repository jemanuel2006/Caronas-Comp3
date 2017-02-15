package testes;

import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;

import persistencia.DatabaseConnector;

public abstract class TestBase {
	@Before
	public void Setup(){
		try{
			DatabaseConnector.connString = "jdbc:mysql://127.0.0.1:3306/bancocaronascomp3";
			DatabaseConnector db = new DatabaseConnector();
			String criarBanco = "create database bancocaronascomp3_tests";
			PreparedStatement ps = db.getConnection().prepareStatement(criarBanco);
			ps.executeUpdate();
			
			DatabaseConnector.connString = "jdbc:mysql://127.0.0.1:3306/bancocaronascomp3_tests";
			db = new DatabaseConnector();
			
			String usarBanco = "use bancocaronascomp3_tests";
			ps = db.getConnection().prepareStatement(usarBanco);
			ps.executeUpdate(); 
			
			String criarTabelaUsuarios = "create table bancocaronascomp3_tests.usuario(id int not null auto_increment,nome varchar(500) not null,email varchar(500) not null,telefone varchar(500) not null,primary key (id));";
			ps = db.getConnection().prepareStatement(criarTabelaUsuarios);
			ps.executeUpdate();
			
			String criarTabelaGrupos = "create table bancocaronascomp3_tests.grupo(id int not null auto_increment,nome varchar(500) not null,descricao varchar(500) not null,regras varchar(5000) not null,limite int not null,primary key (id))";
			ps = db.getConnection().prepareStatement(criarTabelaGrupos);
			ps.executeUpdate();
			
			String criarTabelaUsuario_Grupo = "create table usuario_grupo(id int not null auto_increment,grupo_id int not null,usuario_id int not null,primary key (id),foreign key (grupo_id) REFERENCES grupo(id),foreign key (usuario_id) REFERENCES usuario(id));";
			ps = db.getConnection().prepareStatement(criarTabelaUsuario_Grupo);
			ps.executeUpdate();
			
			String criarTabelaMotorista = "create table motorista(id int not null,primary key (id));";
			ps = db.getConnection().prepareStatement(criarTabelaMotorista);
			ps.executeUpdate();
			
			String criarTabelaVeiculo = "create table veiculo(id int not null auto_increment,modelo varchar(500) not null,placa varchar(500) not null,cor varchar(500) not null,usuario_id int not null,primary key (id),foreign key (usuario_id) REFERENCES motorista(id));";
			ps = db.getConnection().prepareStatement(criarTabelaVeiculo);
			ps.executeUpdate();
			
			String criarTabelaLogradouro = "create table logradouro(id int not null auto_increment,cep varchar(500) not null,cidade varchar(500) not null,estado varchar(500) not null,distrito varchar(500) not null,endereco varchar(1000) not null,numero int not null,primary key (id));";
			ps = db.getConnection().prepareStatement(criarTabelaLogradouro);
			ps.executeUpdate();
			
			String criarTabelaOrigem = "create table origem(id int not null,primary key (id));";
			ps = db.getConnection().prepareStatement(criarTabelaOrigem);
			ps.executeUpdate();
			
			String criarTabelaDestino = "create table destino(id int not null,primary key (id));";
			ps = db.getConnection().prepareStatement(criarTabelaDestino);
			ps.executeUpdate();
			
			String criarTabelaCaronas = "create table carona(id int not null auto_increment,dia date not null,hora_saida date not null,motoristaId int not null,"+
					"veiculoId int not null,origemId int not null,destinoId int not null,estadoCarona int not null,primary key (id),foreign key (motoristaId) REFERENCES motorista(id)," +
					"foreign key (veiculoId) REFERENCES veiculo(id),foreign key (origemId) REFERENCES origem(id),foreign key (destinoId) REFERENCES destino(id));";
			ps = db.getConnection().prepareStatement(criarTabelaCaronas);
			ps.executeUpdate();
			
			String criarTabelaParada = "create table parada(id int not null auto_increment,caronaId int not null,logradouroId int not null,usuarioId int not null," +
					"primary key (id),foreign key (caronaId) REFERENCES carona(id),foreign key (logradouroId) REFERENCES logradouro(id),foreign key (usuarioId) REFERENCES usuario(id));";
			ps = db.getConnection().prepareStatement(criarTabelaParada);
			ps.executeUpdate();
			
			String criarTabelaAvaliacao = "create table avaliacao(id int not null auto_increment,usuarioId int not null,estrelas int not null,primary key (id),foreign key (usuarioId) REFERENCES usuario(id));";
			ps = db.getConnection().prepareStatement(criarTabelaAvaliacao);
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
}
