package testes;

import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TransactionScripts.GruposScript;
import entidades.Grupo;
import persistencia.DatabaseConnector;

public class TestesGrupo {
	@Before
	public void setup(){
		try{
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
			
			String inserirUsuarioFake = "insert into bancocaronascomp3_tests.usuario(nome,email,telefone) values ('usuario','a@a.com','846723623')";
			ps = db.getConnection().prepareStatement(inserirUsuarioFake);
			ps.executeUpdate();
			
			String criarTabelaUsuario_Grupo = "create table usuario_grupo(id int not null auto_increment,grupo_id int not null,usuario_id int not null,primary key (id),foreign key (grupo_id) REFERENCES grupo(id),foreign key (usuario_id) REFERENCES usuario(id));";
			ps = db.getConnection().prepareStatement(criarTabelaUsuario_Grupo);
			ps.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@After
	public void destroy(){
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
	public void CriarGrupoComTodosOsCampos() throws Exception{
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 3;
		String emailCriador = "a@a.com";
		
		GruposScript gts = new GruposScript();
		int id = gts.CriarGrupo(emailCriador, nome, desc, regras, limite);
		Grupo g = gts.GetGrupo(id);
		
		Assert.assertEquals(g.get_nome(), nome);
		Assert.assertEquals(g.get_descricao(), desc);
		Assert.assertEquals(g.get_regras(), regras);
		Assert.assertEquals(g.get_limite(), limite);
		Assert.assertEquals(g.get_membros().iterator().next().get_email(), emailCriador);
	}
}
