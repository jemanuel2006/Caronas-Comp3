package testes;

import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import TransactionScripts.GruposScript;
import TransactionScripts.LogradouroScript;
import TransactionScripts.UsuariosScript;
import entidades.Grupo;
import entidades.Logradouro;
import persistencia.DatabaseConnector;

public class TesteLogradouro extends TestBase{
	@Test
	public void CriarLogradouroComTodosOsCampos() throws Exception{
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 133;
		
		LogradouroScript lts = new LogradouroScript();
		int id = lts.CriarLogradouro(_cep, _estado, _cidade, _distrito, _endereco, _numero);
		Logradouro l = lts.GetLogradouro(id);
		
		Assert.assertEquals(l.get_cep(), _cep);
		Assert.assertEquals(l.get_cidade(), _cidade);
		Assert.assertEquals(l.get_estado(), _estado);
		Assert.assertEquals(l.get_distrito(), _distrito);
		Assert.assertEquals(l.get_endereco(), _endereco);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CriarLogradouroSemNumero() throws Exception{
		LogradouroScript lts = new LogradouroScript();
		String _cep = "26032730";
		String _cidade = "Nova Iguaçu";
		String _estado = "rj";
		String _distrito = "Ponto Chic";
		String _endereco = "Estrada Velha de São José";
		int _numero = 0;
		
		lts.CriarLogradouro(_cep, _cidade, _estado, _distrito, _endereco, _numero);
	}
}
