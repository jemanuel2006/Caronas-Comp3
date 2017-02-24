package TransactionScripts;

import excecoes.EntidadeNaoEncontradaException;
import persistencia.GrupoFinder;
import persistencia.GrupoGateway;

public class EditarGrupoScript {
	int id; 
	String nome; 
	String descricao;
	int limite;
	
	public EditarGrupoScript(int id, String nome, String descricao, int limite) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.limite = limite;
	}
	
	public void execute() throws Exception{
		if(nome == null || nome.isEmpty()){
			throw new IllegalArgumentException("nome");
		}
		
		if(descricao == null || descricao.isEmpty()){
			throw new IllegalArgumentException("descricao");
		}
		
		if(limite < 1 || limite > 5) {
			throw new IllegalArgumentException("limite");
		}
		
		GrupoFinder grupoFinder = new GrupoFinder();
		GrupoGateway grupoGateway = grupoFinder.find(id);
		
		if(grupoGateway == null)
			throw new EntidadeNaoEncontradaException();
		
		grupoGateway.set_nome(nome);
		grupoGateway.set_descricao(descricao);
		grupoGateway.set_limite(limite);
		
		grupoGateway.Save();
	}
}
