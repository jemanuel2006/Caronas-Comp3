package testes;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TransactionScripts.UsuariosScript;
import entidades.Usuario;
import persistencia.DatabaseConnector;

public class TestesUsuario extends TestBase{
	@Test
	public void CriarUsuarioComTodosOsCampos(){
		UsuariosScript uts = new UsuariosScript();
		String nome = "usuario teste";
		String email = "email@email.com";
		String telefone = "814213612";
		
		try{
			int id = uts.CriarUsuario(nome,email,telefone);
			Usuario u = uts.GetUsuario(id);
			
			Assert.assertEquals(u.get_nome(), nome);
			Assert.assertEquals(u.get_email(), email);
			Assert.assertEquals(u.get_telefone(), telefone);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarUsuarioSemEmail() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String nome = "usuario teste";
		String email = "";
		String telefone = "814213612";
		
		uts.CriarUsuario(nome,email,telefone);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarUsuarioSemTelefone() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String nome = "usuario teste";
		String email = "a@a.com";
		String telefone = "";
		
		uts.CriarUsuario(nome,email,telefone);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarUsuarioSemNome() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String nome = "";
		String email = "a@a.com";
		String telefone = "814213612";
		
		uts.CriarUsuario(nome,email,telefone);
	}
	
	@Test
	public void EditarUsuarioComTodosOsCampos() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String nome = "usuario teste";
		String email = "email@email.com";
		String telefone = "814213612";
		
		int id = uts.CriarUsuario(nome,email,telefone);
		
		nome = "usuario teste alterado";
		telefone = "95637231";
		
		uts.EditarUsuario(id, nome, telefone);
		Usuario u = uts.GetUsuario(id);
		
		Assert.assertEquals(u.get_nome(), nome);
		Assert.assertEquals(u.get_email(), email);
		Assert.assertEquals(u.get_telefone(), telefone);
	}
}
