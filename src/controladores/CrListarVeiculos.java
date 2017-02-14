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

import TransactionScripts.GruposScript;
import TransactionScripts.MotoristaScript;
import entidades.Grupo;
import entidades.Motorista;
import entidades.Usuario;
import entidades.Veiculo;
import helpers.QueryStringHelper;

/**
 * Servlet implementation class CrListarVeiculos
 */
@WebServlet("/ListarVeiculos")
public class CrListarVeiculos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrListarVeiculos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Veiculo> veiculos = new ArrayList<Veiculo>();
		Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
		int id = Integer.parseInt(params.get("usuarioId"));
		
		try {
			MotoristaScript ts = new MotoristaScript();
			Motorista motorista = ts.GetMotorista(id);
			veiculos = motorista.get_veiculos();
			request.setAttribute("_nomeMotorista", motorista.get_nome());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("list", veiculos);
		request.setAttribute("_id", id);
		RequestDispatcher view = request.getRequestDispatcher("Motorista/FrListarVeiculos.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
