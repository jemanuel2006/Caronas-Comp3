package testes;

import org.junit.Assert;
import org.junit.Test;

import TransactionScripts.CriarUsuarioScript;
import TransactionScripts.EditarUsuarioScript;
import TransactionScripts.GetUsuarioScript;
import entidades.Usuario;

public class TestesUsuario extends TestBase{
	@Test
	public void CriarUsuarioComTodosOsCampos(){
		String nome = "usuario teste";
		String email = "email@email.com";
		String telefone = "814213612";
		
		try{

			CriarUsuarioScript uts = new CriarUsuarioScript(nome,email,telefone);
			int id = uts.execute();
			
			GetUsuarioScript gus = new GetUsuarioScript(id);
			Usuario u = gus.execute();
			
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
		String nome = "usuario teste";
		String email = "";
		String telefone = "814213612";

		CriarUsuarioScript uts = new CriarUsuarioScript(nome,email,telefone);
		uts.execute();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarUsuarioSemTelefone() throws Exception{
		String nome = "usuario teste";
		String email = "a@a.com";
		String telefone = "";

		CriarUsuarioScript uts = new CriarUsuarioScript(nome,email,telefone);
		uts.execute();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarUsuarioSemNome() throws Exception{
		String nome = "";
		String email = "a@a.com";
		String telefone = "814213612";
		
		CriarUsuarioScript uts = new CriarUsuarioScript(nome,email,telefone);
		uts.execute();
	}
	
	@Test
	public void EditarUsuarioComTodosOsCampos() throws Exception{
		String nome = "usuario teste";
		String email = "email@email.com";
		String telefone = "814213612";
		CriarUsuarioScript uts = new CriarUsuarioScript(nome,email,telefone);
		int id = uts.execute();
		
		nome = "usuario teste alterado";
		telefone = "95637231";
		
		EditarUsuarioScript eus = new EditarUsuarioScript(id, nome, telefone);
		eus.execute();
		
		GetUsuarioScript gus = new GetUsuarioScript(id);
		Usuario u = gus.execute();
		
		Assert.assertEquals(u.get_nome(), nome);
		Assert.assertEquals(u.get_email(), email);
		Assert.assertEquals(u.get_telefone(), telefone);
	}
}
