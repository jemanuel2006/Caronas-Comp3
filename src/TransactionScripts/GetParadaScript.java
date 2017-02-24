package TransactionScripts;

import entidades.Avaliacao;
import entidades.Logradouro;
import entidades.Parada;
import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;
import persistencia.AvaliacaoFinder;
import persistencia.AvaliacaoGateway;
import persistencia.LogradouroFinder;
import persistencia.LogradouroGateway;
import persistencia.ParadaFinder;
import persistencia.ParadaGateway;
import persistencia.UsuarioGateway;
import persistencia.UsuariosFinder;

public class GetParadaScript {
	private int paradaId;
	
	public GetParadaScript(int paradaId) {
		this.paradaId = paradaId;
	}
	
	public Parada execute() throws Exception{
		ParadaFinder pFinder = new ParadaFinder();
		ParadaGateway pGateway = pFinder.find(paradaId);
		
		if(pGateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		LogradouroFinder lFinder = new LogradouroFinder();
		LogradouroGateway gateway = lFinder.find(pGateway.get_logradouroId());
		
		if(gateway == null){
			throw new EntidadeNaoEncontradaException();
		}
		
		Logradouro l = new Logradouro();
		
		l.set_cep(gateway.get_cep());
		l.set_cidade(gateway.get_cidade());
		l.set_distrito(gateway.get_distrito());
		l.set_endereco(gateway.get_endereco());
		l.set_estado(gateway.get_estado());
		l.set_numero(gateway.get_numero());
		l.set_id(gateway.get_id());
		
		UsuariosFinder finder = new UsuariosFinder();
		UsuarioGateway u = finder.find(pGateway.get_usuarioId());
		
		if(u == null)
			throw new EntidadeNaoEncontradaException();
		
		Usuario usuario = new Usuario(u.get_nome(), u.get_email(), u.get_telefone());
		usuario.set_id(pGateway.get_usuarioId());
		
		AvaliacaoFinder aFinder = new AvaliacaoFinder();
		
		for(AvaliacaoGateway a: aFinder.getByUsuario(pGateway.get_usuarioId())){
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.set_avaliacao(a.get_estrelas());
			avaliacao.set_id(a.get_id());
			avaliacao.set_usuario(usuario);
			
			usuario.AdicionarAvaliacao(avaliacao);
		}
		
		Parada parada = new Parada();
		parada.set_endereco(l);
		parada.set_id(paradaId);
		parada.set_usuario(usuario);
		
		return parada;
	}
}
