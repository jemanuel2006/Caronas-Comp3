package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.CriarUsuarioScript;


@WebServlet("/CriarUsuario")
public class CrCriarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrCriarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("Usuarios/FrCriarUsuario.jsp");
        view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _nome;
		String _email;
		String _telefone;
		
		_nome = request.getParameter("_nome");
		_email = request.getParameter("_email");
		_telefone = request.getParameter("_telefone");
		
		CriarUsuarioScript ts = new CriarUsuarioScript(_nome, _email, _telefone);
		
		try {
			int id = ts.execute();
			response.sendRedirect("./EditarUsuario?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}