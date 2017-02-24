package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.GetCaronaScript;
import TransactionScripts.RemoverUsuarioDeCaronaScript;
import entidades.Carona;
import helpers.QueryStringHelper;

/**
 * Servlet implementation class CrVisualizarParticipantes
 */
@WebServlet("/VisualizarParticipantes")
public class CrVisualizarParticipantes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrVisualizarParticipantes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
			int id = Integer.parseInt(params.get("caronaId"));
			
			GetCaronaScript cts = new GetCaronaScript(id);
			Carona carona = cts.execute();
			
			request.setAttribute("paradas", carona.GetParadas());
    		request.setAttribute("caronaId", id);
    		request.setAttribute("nome", carona.toString());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrVisualizarParticipantes.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
			int paradaId = Integer.parseInt(params.get("paradaId"));
			int caronaId = Integer.parseInt(params.get("caronaId"));
			
			RemoverUsuarioDeCaronaScript cts = new RemoverUsuarioDeCaronaScript(paradaId);
			cts.execute();
			response.sendRedirect("./VisualizarParticipantes?caronaId=" + caronaId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
