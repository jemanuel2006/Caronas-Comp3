package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.GruposScript;

/**
 * Servlet implementation class CrCriarGrupo
 */
@WebServlet("/CriarGrupo")
public class CrCriarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrCriarGrupo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _nome;
		String _descricao;
		String _regras;
		String _emailCriador;
		int _limite;
		
		_nome = request.getParameter("_nome");
		_descricao = request.getParameter("_descricao");
		_regras = request.getParameter("_regras");
		_limite = Integer.parseInt(request.getParameter("_limite"));
		_emailCriador = request.getParameter("_emailCriador");
		
		GruposScript ts = new GruposScript();
		
		try {
			int id = ts.CriarGrupo(_emailCriador, _nome, _descricao, _regras, _limite);
			response.sendRedirect("./EditarGrupo?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("Grupo/FrCriarGrupo.jsp");
        view.forward(request, response);
	}
}
