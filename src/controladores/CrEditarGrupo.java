package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.GruposScript;
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
			
			GruposScript script = new GruposScript();
			Grupo u = script.GetGrupo(id);
			
			request.setAttribute("_id", id);
			request.setAttribute("_nome", u.get_nome());
			request.setAttribute("_descricao", u.get_descricao());
			request.setAttribute("_regras", u.get_regras());
			request.setAttribute("_limite", u.get_limite());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		RequestDispatcher view = request.getRequestDispatcher("Usuarios/FrEditarGrupo.jsp");
        view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("_nome");
			String descricao = request.getParameter("_descricao");
			int limite = Integer.parseInt(request.getParameter("_limite"));
			int id = Integer.parseInt(request.getParameter("_id"));
			
			GruposScript script = new GruposScript();
			script.EditarGrupo(id, nome, descricao, limite);
			
			response.sendRedirect("./EditarGrupo?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}