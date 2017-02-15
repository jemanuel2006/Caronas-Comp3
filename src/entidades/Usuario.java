package entidades;

import java.util.ArrayList;
import java.util.Collection;

public class Usuario {
	private String _nome;
	private String _email;
	private String _telefone;
	private int _id;
	private Collection<Avaliacao> _avaliacoes;
	
	public Usuario(){
		this._avaliacoes = new ArrayList<>();
	}
	
	public Usuario(String nome, String email, String telefone) {
		this.set_nome(nome);
		this.set_email(email);
		this.set_telefone(telefone);
		this._avaliacoes = new ArrayList<>();
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

	public Collection<Avaliacao> get_avaliacoes() {
		return _avaliacoes;
	}
	
	public void AdicionarAvaliacao(Avaliacao a){
		this._avaliacoes.add(a);
	}
	
	public int get_MediaAvaliacoes(){
		int size = this._avaliacoes.size();
		int val = 0;
		
		for(Avaliacao a : this._avaliacoes){
			val += a.get_avaliacao();
		}
		
		return size == 0 ? 0 : val/size;
	}
	
	public int get_quantidadeAvaliacoesRuins(){
		int numero = 0;
		
		for(Avaliacao a : this._avaliacoes){
			if(a.get_avaliacao() < 3)
				numero++;
		}
		
		return numero;
	}
}
