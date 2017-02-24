package testes;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import TransactionScripts.AdicionarUsuarioEmGrupoScript;
import TransactionScripts.CriarGrupoScript;
import TransactionScripts.CriarUsuarioScript;
import TransactionScripts.GetGrupoScript;
import entidades.Grupo;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;

public class TestesGrupo extends TestBase{
	@Test
	public void CriarGrupoComTodosOsCampos() throws Exception{
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";
		
		CriarUsuarioScript uts = new CriarUsuarioScript(unome,uemail,utelefone);
		uts.execute();
		
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 3;
		String emailCriador = "teste@fake.com";
		
		CriarGrupoScript gts = new CriarGrupoScript(emailCriador, nome, desc, regras, limite);
		int id = gts.execute();
		
		GetGrupoScript ggs = new GetGrupoScript(id);
		Grupo g = ggs.execute();
		
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
		
		CriarGrupoScript gts = new CriarGrupoScript(emailCriador, nome, desc, regras, limite);
		gts.execute();
	}
	
	@Test
	public void AdicionarMembroEmGrupo() throws Exception{
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";

		CriarUsuarioScript uts = new CriarUsuarioScript(unome,uemail,utelefone);
		uts.execute();
		
		String unome2 = "usuario teste 2";
		String uemail2 = "teste2@fake.com";
		String utelefone2 = "96412356";
		
		uts = new CriarUsuarioScript(unome2,uemail2,utelefone2);
		uts.execute();
		
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 3;
		String emailCriador = "teste@fake.com";
		
		CriarGrupoScript gts = new CriarGrupoScript(emailCriador, nome, desc, regras, limite);
		int id = gts.execute();
		
		AdicionarUsuarioEmGrupoScript augs = new AdicionarUsuarioEmGrupoScript(uemail2, id);
		augs.execute();
		
		GetGrupoScript ggs = new GetGrupoScript(id);
		Grupo grupo = ggs.execute();
		
		Assert.assertEquals(2, grupo.get_membros().size());
		Iterator<Usuario> it = grupo.get_membros().iterator();
		it.next();
		Assert.assertEquals(uemail2, it.next().get_email());
	}
	
	@Test(expected=EntidadeNaoEncontradaException.class)
	public void AdicionarMembroInexistenteEmGrupo() throws Exception{
		String unome = "usuario teste";
		String uemail = "teste@fake.com";
		String utelefone = "814213612";

		CriarUsuarioScript uts = new CriarUsuarioScript(unome,uemail,utelefone);
		uts.execute();
		
		String nome = "grupo teste";
		String desc = "descrição";
		String regras = "regras";
		int limite = 3;
		String emailCriador = "teste@fake.com";
		
		CriarGrupoScript gts = new CriarGrupoScript(emailCriador, nome, desc, regras, limite);
		int id = gts.execute();
		
		AdicionarUsuarioEmGrupoScript augs = new AdicionarUsuarioEmGrupoScript("emailinexistente@fake.com", id);
		augs.execute();
	}
}
