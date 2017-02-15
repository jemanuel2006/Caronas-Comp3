package testes;

import java.sql.PreparedStatement;
import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TransactionScripts.GruposScript;
import TransactionScripts.UsuariosScript;
import entidades.Grupo;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.DatabaseConnector;

public class TestesGrupo extends TestBase{
	@Test
	public void CriarGrupoComTodosOsCampos() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		
		uts.CriarUsuario(unome,uemail,utelefone);
		
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 3;
		String emailCriador = "teste@fake.com";
		
		GruposScript gts = new GruposScript();
		int id = gts.CriarGrupo(emailCriador, nome, desc, regras, limite);
		Grupo g = gts.GetGrupo(id);
		
		Assert.assertEquals(g.get_nome(), nome);
		Assert.assertEquals(g.get_descricao(), desc);
		Assert.assertEquals(g.get_regras(), regras);
		Assert.assertEquals(g.get_limite(), limite);
		Assert.assertEquals(g.get_membros().iterator().next().get_email(), emailCriador);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarGrupoComLimiteAcima() throws Exception{
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 576437634;
		String emailCriador = "a@a.com";
		
		GruposScript gts = new GruposScript();
		gts.CriarGrupo(emailCriador, nome, desc, regras, limite);
	}
	
	@Test
	public void AdicionarMembroEmGrupo() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		
		uts.CriarUsuario(unome,uemail,utelefone);
		
		String unome2 = "usuario teste 2";
		String uemail2 = "teste2@fake.com";
		String utelefone2 = "96412356";
		
		uts.CriarUsuario(unome2,uemail2,utelefone2);
		
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 3;
		String emailCriador = "teste@fake.com";
		
		GruposScript gts = new GruposScript();
		int id = gts.CriarGrupo(emailCriador, nome, desc, regras, limite);
		
		gts.AdicionarUsuarioEmGrupo(uemail2, id);
		
		Grupo grupo = gts.GetGrupo(id);
		
		Assert.assertEquals(2, grupo.get_membros().size());
		Iterator<Usuario> it = grupo.get_membros().iterator();
		it.next();
		Assert.assertEquals(uemail2, it.next().get_email());
	}
	
	@Test(expected=EntidadeNaoEncontradaException.class)
	public void AdicionarMembroInexistenteEmGrupo() throws Exception{
		UsuariosScript uts = new UsuariosScript();
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		
		uts.CriarUsuario(unome,uemail,utelefone);
		
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 3;
		String emailCriador = "teste@fake.com";
		
		GruposScript gts = new GruposScript();
		int id = gts.CriarGrupo(emailCriador, nome, desc, regras, limite);
		
		gts.AdicionarUsuarioEmGrupo("emailinexistente@fake.com", id);
	}
}
