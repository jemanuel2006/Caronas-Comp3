package entidades;

public class Usuario {
	private String _nome;
	private String _email;
	private String _telefone;
	private int _id;
	
	public Usuario(){
		
	}
	
	public Usuario(String nome, String email, String telefone) {
		this.set_nome(nome);
		this.set_email(email);
		this.set_telefone(telefone);
		
	}
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
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
}
