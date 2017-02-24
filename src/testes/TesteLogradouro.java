package testes;

import org.junit.Assert;
import org.junit.Test;

import TransactionScripts.CriarLogradouroScript;
import TransactionScripts.GetLogradouroScript;
import entidades.Logradouro;

public class TesteLogradouro extends TestBase{
	@Test
	public void CriarLogradouroComTodosOsCampos() throws Exception{
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 133;
		
		CriarLogradouroScript lts = new CriarLogradouroScript(_cep, _estado, _cidade, _distrito, _endereco, _numero);
		int id = lts.execute();
		
		GetLogradouroScript gl = new GetLogradouroScript(id);
		Logradouro l = gl.execute();
		
		Assert.assertEquals(l.get_cep(), _cep);
		Assert.assertEquals(l.get_cidade(), _cidade);
		Assert.assertEquals(l.get_estado(), _estado);
		Assert.assertEquals(l.get_distrito(), _distrito);
		Assert.assertEquals(l.get_endereco(), _endereco);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarLogradouroSemNumero() throws Exception{
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 0;

		CriarLogradouroScript lts = new CriarLogradouroScript(_cep, _cidade, _estado, _distrito, _endereco, _numero);
		lts.execute();
	}
}
