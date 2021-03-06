package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.EditarUsuarioScript;
import TransactionScripts.GetUsuarioScript;
import entidades.Usuario;
import helpers.QueryStringHelper;


@WebServlet("/EditarUsuario")
public class CrEditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrEditarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
			Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
			int id = Integer.parseInt(params.get("id"));
			
			GetUsuarioScript script = new GetUsuarioScript(id);
			Usuario u = script.execute();
			
			request.setAttribute("_id", id);
			request.setAttribute("_nome", u.get_nome());
			request.setAttribute("_email", u.get_email());
			request.setAttribute("_telefone", u.get_telefone());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		RequestDispatcher view = request.getRequestDispatcher("Usuarios/FrEditarUsuario.jsp");
        view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("_nome");
			String telefone = request.getParameter("_telefone");
			int id = Integer.parseInt(request.getParameter("_id"));
			
			EditarUsuarioScript script = new EditarUsuarioScript(id, nome, telefone);
			script.execute();
			
			response.sendRedirect("./EditarUsuario?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
