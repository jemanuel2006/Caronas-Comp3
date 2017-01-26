package entidades;

public class Grupo {
	private String _nome;
	private String _descricao;
	private String _regras;
	private int _limite;
	
	public String get_nome() {
		return _nome;
	}
	public void set_nome(String _nome) {
		this._nome = _nome;
	}
	public String get_regras() {
		return _regras;
	}
	public void set_regras(String _regras) {
		this._regras = _regras;
	}
	public String get_descricao() {
		return _descricao;
	}
	public void set_descricao(String _descricao) {
		this._descricao = _descricao;
	}
	public int get_limite() {
		return _limite;
	}
	public void set_limite(int _limite) {
		this._limite = _limite;
	}
}
