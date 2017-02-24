package TransactionScripts;

import entidades.Grupo;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.GrupoGateway;
import persistencia.UsuarioGateway;
import persistencia.Usuario_GrupoGateway;
import persistencia.UsuariosFinder;

public class CriarGrupoScript {
	String emailCriador; 
	String nome; 
	String descricao; 
	String regras; 
	int limite;
	
	public CriarGrupoScript(String emailCriador, String nome, String descricao, String regras, int limite) {
		this.emailCriador = emailCriador;
		this.nome = nome;
		this.descricao = descricao;
		this.regras = regras;
		this.limite = limite;
	}
	
	public int execute() throws Exception{
		if(nome == null || nome.isEmpty()){
			throw new IllegalArgumentException("nome");
		}
		
		if(regras == null || regras.isEmpty()){
			throw new IllegalArgumentException("regras");
		}
		
		if(descricao == null || descricao.isEmpty()){
			throw new IllegalArgumentException("descricao");
		}
		
		if(limite < 1 || limite > 5) {
			throw new IllegalArgumentException("limite");
		}
		
		Grupo grupo = new Grupo(nome, descricao, regras, limite);
		GrupoGateway gruposGateway = new GrupoGateway(grupo);
		int id = gruposGateway.Insert();
		
		UsuariosFinder uFinder = new UsuariosFinder();
		UsuarioGateway uGateway = uFinder.find(emailCriador);
		
		if(uGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		Usuario_GrupoGateway ug = new Usuario_GrupoGateway(uGateway.get_id(), id);
		ug.Insert();
		
		return id;
	}
}
