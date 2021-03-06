package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.EditarGrupoScript;
import TransactionScripts.GetGrupoScript;
import entidades.Grupo;
import helpers.QueryStringHelper;


@WebServlet("/EditarGrupo")
public class CrEditarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrEditarGrupo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
			Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
			int id = Integer.parseInt(params.get("id"));
			
			GetGrupoScript script = new GetGrupoScript(id);
			Grupo g = script.execute();
			
			request.setAttribute("_id", id);
			request.setAttribute("_nome", g.get_nome());
			request.setAttribute("_descricao", g.get_descricao());
			request.setAttribute("_regras", g.get_regras());
			request.setAttribute("_limite", g.get_limite());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		RequestDispatcher view = request.getRequestDispatcher("Grupo/FrEditarGrupo.jsp");
        view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("_nome");
			String descricao = request.getParameter("_descricao");
			int limite = Integer.parseInt(request.getParameter("_limite"));
			int id = Integer.parseInt(request.getParameter("_id"));
			
			EditarGrupoScript script = new EditarGrupoScript(id, nome, descricao, limite);
			script.execute();
			
			response.sendRedirect("./EditarGrupo?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}