package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.AvaliacaoScript;
import TransactionScripts.CaronaScript;
import entidades.Carona;
import entidades.Parada;
import entidades.Usuario;
import helpers.QueryStringHelper;

/**
 * Servlet implementation class CrFinalizarCarona
 */
@WebServlet("/FinalizarCarona")
public class CrFinalizarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrFinalizarCarona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
		int id = Integer.parseInt(params.get("caronaId"));
		Collection<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			CaronaScript cts = new CaronaScript();
			Carona carona = cts.GetCarona(id);
			
			for(Parada p : carona.GetParadas()){
				usuarios.add(p.get_usuario());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("_id", id);
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrFinalizarCarona.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> parameterNames = request.getParameterNames();
		try{
			int id = Integer.parseInt(request.getParameter("_id"));
			CaronaScript cts = new CaronaScript();
			cts.FinalizarCarona(id);
			while(parameterNames.hasMoreElements()){
				String paramName = parameterNames.nextElement();
				
				if(paramName.contains("avaliacao_")){
					String[] ids = paramName.split("_");
					int usuarioId = Integer.parseInt(ids[1]);
					int avaliacao = Integer.parseInt(request.getParameter(paramName));
					
					AvaliacaoScript ats = new AvaliacaoScript();
					ats.AvaliarUsuario(usuarioId, avaliacao);
				}
			}
			
			response.sendRedirect("./ListarCaronas");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
