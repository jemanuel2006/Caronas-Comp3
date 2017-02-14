package entidades;

import java.util.ArrayList;
import java.util.Collection;

public class Motorista extends Usuario {
	private Collection<Veiculo> _veiculos;

	public Motorista(String _nome, String _email, String _telefone) {
		super(_nome, _email, _telefone);
		this._veiculos = new ArrayList<Veiculo>();
	}
	
	public void AdicionarVeiculo(Veiculo v){
		_veiculos.add(v);
	}
	
	public Collection<Veiculo> get_veiculos(){
		return this._veiculos;
	}
}
