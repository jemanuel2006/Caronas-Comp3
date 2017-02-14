package entidades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class Carona {
	private int _id;
	private Date dia;
	private Date hora_saida;
	private Logradouro _origem;
	private Logradouro _destino;
	private Veiculo _veiculo;
	private Collection<Parada> _paradas;
	private int _estadoCarona;
	
	public Carona(){
		this._paradas = new ArrayList<Parada>();
	}
	
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public Date getHora_saida() {
		return hora_saida;
	}
	public void setHora_saida(Date hora_saida) {
		this.hora_saida = hora_saida;
	}

	public Logradouro get_origem() {
		return _origem;
	}

	public void set_origem(Logradouro _origem) {
		this._origem = _origem;
	}

	public Logradouro get_destino() {
		return _destino;
	}

	public void set_destino(Logradouro _destino) {
		this._destino = _destino;
	}
	
	public Collection<Parada> GetParadas(){
		return this._paradas;
	}
	
	public void AdicionarParada(Parada p){
		this._paradas.add(p);
	}
	
	public void RemoverParada(int id){
		this._paradas.removeIf(x -> x.get_id() == id);
	}

	public Veiculo get_veiculo() {
		return _veiculo;
	}

	public void set_veiculo(Veiculo _veiculo) {
		this._veiculo = _veiculo;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_estadoCarona() {
		return _estadoCarona;
	}

	public void set_estadoCarona(int _estadoCarona) {
		this._estadoCarona = _estadoCarona;
	}
	
	@Override
	public String toString(){
		return "Origem: " + this._origem.get_endereco() + ", Destino: " + this._destino.get_endereco();
	}
}
