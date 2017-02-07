package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.GruposScript;
import entidades.Grupo;
import helpers.QueryStringHelper;

@WebServlet("/DeletarGrupo")
public class CrDeletarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrDeletarGrupo() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
			int id = Integer.parseInt(params.get("id"));
			
			GruposScript script = new GruposScript();
			script.DeletarGrupo(id);
			
			response.sendRedirect("./ListarGrupo");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
