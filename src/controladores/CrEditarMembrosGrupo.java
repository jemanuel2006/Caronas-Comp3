package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.AdicionarUsuarioEmGrupoScript;
import TransactionScripts.GetGrupoScript;
import entidades.Grupo;
import entidades.Usuario;
import helpers.QueryStringHelper;

/**
 * Servlet implementation class CrEditarMembrosGrupo
 */
@WebServlet("/EditarMembrosGrupo")
public class CrEditarMembrosGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrEditarMembrosGrupo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Usuario> usuarios = new ArrayList<Usuario>();
		Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
		int id = Integer.parseInt(params.get("id"));
		
		try {
			GetGrupoScript script = new GetGrupoScript(id);
			Grupo grupo = script.execute();
			usuarios = grupo.get_membros();
			
			request.setAttribute("max", grupo.get_limite());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", usuarios);
		request.setAttribute("_id", id);
		RequestDispatcher view = request.getRequestDispatcher("Grupo/FrEditarMembrosGrupo.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("_email");
			int idGrupo = Integer.parseInt(request.getParameter("_id"));
			
			AdicionarUsuarioEmGrupoScript ts = new AdicionarUsuarioEmGrupoScript(email, idGrupo);
			ts.execute();
			
			response.sendRedirect("./EditarMembrosGrupo?id=" + idGrupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
