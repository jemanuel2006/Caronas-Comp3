package controladores;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.AlterarVeiculoScript;
import TransactionScripts.GetVeiculoScript;
import entidades.Veiculo;
import helpers.QueryStringHelper;

/**
 * Servlet implementation class CrEditarVeiculo
 */
@WebServlet("/EditarVeiculo")
public class CrEditarVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrEditarVeiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
		int id = Integer.parseInt(params.get("id"));
		int usuarioId = Integer.parseInt(params.get("usuarioId"));
		
		try{
			GetVeiculoScript ts = new GetVeiculoScript(id);
			Veiculo v = ts.execute();
			
			request.setAttribute("_id", id);
			request.setAttribute("_cor", v.get_cor());
			request.setAttribute("_modelo", v.get_modelo());
			request.setAttribute("_placa", v.get_placa());
			request.setAttribute("_usuarioId", usuarioId);
			
			RequestDispatcher view = request.getRequestDispatcher("Motorista/FrEditarVeiculo.jsp");
	        view.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cor = request.getParameter("_cor");
			int id = Integer.parseInt(request.getParameter("_id"));
			int usuarioId = Integer.parseInt(request.getParameter("_usuarioId"));
			
			AlterarVeiculoScript ts = new AlterarVeiculoScript(id, cor);
			ts.execute();
			
			response.sendRedirect("./EditarVeiculo?id=" + id + "usuarioId=" + usuarioId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
