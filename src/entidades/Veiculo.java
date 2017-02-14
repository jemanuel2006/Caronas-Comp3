package entidades;

public class Veiculo {
	private int _id;
	private String _modelo;
	private String _placa;
	private String _cor;
	private Motorista _motorista;
	public static int maxVagas = 4;
	
	public String get_modelo() {
		return _modelo;
	}
	public void set_modelo(String _modelo) {
		this._modelo = _modelo;
	}
	public String get_placa() {
		return _placa;
	}
	public void set_placa(String _placa) {
		this._placa = _placa;
	}
	public String get_cor() {
		return _cor;
	}
	public void set_cor(String _cor) {
		this._cor = _cor;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public Motorista get_motorista() {
		return _motorista;
	}
	public void set_motorista(Motorista _motorista) {
		this._motorista = _motorista;
	}
	
}
