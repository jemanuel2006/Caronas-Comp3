package entidades;

public class Parada {
	private int _id;
	private Logradouro _endereco;
	private Usuario _usuario;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public Logradouro get_endereco() {
		return _endereco;
	}
	public void set_endereco(Logradouro _endereco) {
		this._endereco = _endereco;
	}
	public Usuario get_usuario() {
		return _usuario;
	}
	public void set_usuario(Usuario _usuario) {
		this._usuario = _usuario;
	}
	
	@Override
	public String toString(){
		return this._endereco.get_endereco() + ", " + this._endereco.get_numero() + " - CEP: " + this._endereco.get_cep();
	}
}
