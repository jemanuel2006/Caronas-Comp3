package entidades;

public class Logradouro {
	private String _cep;
	private String _cidade;
	private String _estado;
	private String _distrito;
	private String _endereco;
	private int _numero;
	private int _id;
	
	public String get_cep() {
		return _cep;
	}
	public void set_cep(String _cep) {
		this._cep = _cep;
	}
	public String get_cidade() {
		return _cidade;
	}
	public void set_cidade(String _cidade) {
		this._cidade = _cidade;
	}
	public String get_estado() {
		return _estado;
	}
	public void set_estado(String _estado) {
		this._estado = _estado;
	}
	public String get_distrito() {
		return _distrito;
	}
	public void set_distrito(String _distrito) {
		this._distrito = _distrito;
	}
	public String get_endereco() {
		return _endereco;
	}
	public void set_endereco(String _endereco) {
		this._endereco = _endereco;
	}
	public int get_numero() {
		return _numero;
	}
	public void set_numero(int _numero) {
		this._numero = _numero;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
}
