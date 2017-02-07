package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.GruposScript;

/**
 * Servlet implementation class CrCriarGrupo
 */
@WebServlet("/CrCriarGrupo")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int _id;
		String _nome;
		String _descricao;
		String _regras;
		int _limite;
		
		_id = Integer.parseInt(request.getParameter("_id"));
		_nome = request.getParameter("_nome");
		_descricao = request.getParameter("_descricao");
		_regras = request.getParameter("_regras");
		_limite = Integer.parseInt(request.getParameter("_limite"));
		
		GruposScript ts = new GruposScript();
		
		try {
			int id = ts.CriarGrupo(_id, _nome, _descricao, _regras, _limite);
			response.sendRedirect("./EditarGrupo?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
