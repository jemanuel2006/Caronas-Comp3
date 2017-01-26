package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuario;
import persistencia.UsuarioDAO;

/**
 * Servlet implementation class CrCriarUsuario
 */
@WebServlet("/CrCriarUsuario")
public class CrCriarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrCriarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _nome;
		String _email;
		String _telefone;
		
		_nome = (request.getParameter("_nome"));
		_email = (request.getParameter("_email"));
		_telefone = (request.getParameter("_telefone"));
		
		
		Usuario usuario = new Usuario(_nome, _email, _telefone);
		
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.adiciona(usuario);
		
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		doGet(request, response);
	}

}