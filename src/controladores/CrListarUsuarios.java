package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.GetAllUsuariosScript;
import entidades.Usuario;


@WebServlet("/ListarUsuarios")
public class CrListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrListarUsuarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			GetAllUsuariosScript script = new GetAllUsuariosScript();
			usuarios = script.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", usuarios);
		RequestDispatcher view = request.getRequestDispatcher("Usuarios/FrListarUsuarios.jsp");
        view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
