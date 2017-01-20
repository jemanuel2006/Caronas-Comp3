package Entities;

public class Usuario {
	private String _nome;
	private String _email;
	private String _telefone;
	
	public String get_email() {
		return _email;
	}
	public void set_email(String _email) {
		this._email = _email;
	}
	public String get_nome() {
		return _nome;
	}
	public void set_nome(String _nome) {
		this._nome = _nome;
	}
	public String get_telefone() {
		return _telefone;
	}
	public void set_telefone(String _telefone) {
		this._telefone = _telefone;
	}
}
