package entidades;

public class Avaliacao {
	private int _id;
	private int _avaliacao;
	private Usuario _usuario;

	public int get_avaliacao() {
		return _avaliacao;
	}

	public void set_avaliacao(int _avaliacao) {
		this._avaliacao = _avaliacao;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public Usuario get_usuario() {
		return _usuario;
	}

	public void set_usuario(Usuario _usuario) {
		this._usuario = _usuario;
	}
}
