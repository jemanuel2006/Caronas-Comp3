package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import TransactionScripts.CaronaScript;


/**
 * Servlet implementation class CrCriarCarona
 */
@WebServlet("/CriarCarona")
public class CrCriarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrCriarCarona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int _veiculoId;
		int _motoristaId;
		Date _dia, _hora_saida;
		int _logradouroOrigemId;
		int _logradouroDestinoId;
		
		_veiculoId = Integer.parseInt(request.getParameter("_veiculoId"));
		_motoristaId = Integer.parseInt(request.getParameter("_motoristaId"));
		_dia = Date.valueOf("_dia");
		_hora_saida = Date.valueOf("_hora_saida");
		_logradouroOrigemId = Integer.parseInt(request.getParameter("_logradouroOrigemId"));
		_logradouroDestinoId = Integer.parseInt(request.getParameter("_logradouroDestinoId"));
		
		CaronaScript ts = new CaronaScript();
		
		try {
			int id = ts.CriarCarona(_veiculoId, _motoristaId, _dia, _hora_saida, _logradouroOrigemId, _logradouroDestinoId);
			response.sendRedirect("./EditarCarona?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrCriarCarona.jsp");
        view.forward(request, response);
	}

}
