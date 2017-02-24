package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.AdicionarVeiculoScript;
import helpers.QueryStringHelper;

/**
 * Servlet implementation class CrCriarVeiculo
 */
@WebServlet("/CriarVeiculo")
public class CrCriarVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrCriarVeiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
		int id = Integer.parseInt(params.get("usuarioId"));
		request.setAttribute("_id", id);
		RequestDispatcher view = request.getRequestDispatcher("Motorista/FrCriarVeiculo.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cor = request.getParameter("_cor");
		String modelo = request.getParameter("_modelo");
		String placa = request.getParameter("_placa");
		int usuarioId = Integer.parseInt(request.getParameter("_id"));
		
		try {
			AdicionarVeiculoScript ts = new AdicionarVeiculoScript(usuarioId, modelo, placa, cor);
			int id = ts.execute();
			response.sendRedirect("./EditarVeiculo?id=" + id + "&usuarioId=" + usuarioId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
