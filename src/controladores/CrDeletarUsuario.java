package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.UsuariosScript;
import entidades.Usuario;
import helpers.QueryStringHelper;

@WebServlet("/DeletarUsuario")
public class CrDeletarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrDeletarUsuario() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
			int id = Integer.parseInt(params.get("id"));
			
			UsuariosScript script = new UsuariosScript();
			script.DeletarUsuario(id);
			
			response.sendRedirect("./ListarUsuarios");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
