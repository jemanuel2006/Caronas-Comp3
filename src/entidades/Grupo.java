package entidades;

public class Grupo {
	private int _id;
	private String _nome;
	private String _descricao;
	private String _regras;
	private int _limite;
	
	public Grupo(){
		
	}
	
	public Grupo(String nome, String descricao, String regras, int limite) {
		this.set_nome(nome);
		this.set_descricao(descricao);
		this.set_regras(regras);
		this.set_limite(limite);
	}
	
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

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}
}
