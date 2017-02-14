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

import TransactionScripts.CaronaScript;
import TransactionScripts.LogradouroScript;
import TransactionScripts.MotoristaScript;
import entidades.Carona;
import entidades.Motorista;
import entidades.Parada;
import entidades.Usuario;
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
			
			CaronaScript cts = new CaronaScript();
			Carona carona = cts.GetCarona(id);
			
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
			
			CaronaScript cts = new CaronaScript();
			cts.RemoverUsuarioDeCarona(paradaId);
			response.sendRedirect("./VisualizarParticipantes?caronaId=" + caronaId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
