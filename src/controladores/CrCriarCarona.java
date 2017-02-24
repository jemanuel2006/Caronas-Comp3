package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import TransactionScripts.CriarCaronaScript;
import TransactionScripts.GetLogradourosScript;
import TransactionScripts.GetMotoristaScript;
import entidades.Motorista;
import helpers.QueryStringHelper;


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
		try {
			int _veiculoId = Integer.parseInt(request.getParameter("_veiculoId"));
			int _motoristaId = Integer.parseInt(request.getParameter("usuarioId"));
			Date _dia = Date.valueOf(request.getParameter("_dia"));
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Date _hora_saida = new Date(sdf.parse(request.getParameter("horario_saida")).getTime());
			int _logradouroOrigemId = Integer.parseInt(request.getParameter("_origemId"));
			int _logradouroDestinoId = Integer.parseInt(request.getParameter("_destinoId"));
			
			CriarCaronaScript ts = new CriarCaronaScript(_veiculoId, _motoristaId, _dia, _hora_saida, _logradouroOrigemId, _logradouroDestinoId);
			
			int id = ts.execute();
			response.sendRedirect("./EditarCarona?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
		int usuarioId = Integer.parseInt(params.get("usuarioId"));
		
    	try{
    		GetMotoristaScript ts = new GetMotoristaScript(usuarioId);
    		Motorista m = ts.execute();
    		
    		GetLogradourosScript lts = new GetLogradourosScript();
    		
    		request.setAttribute("logradouros", lts.execute());
    		request.setAttribute("veiculos", m.get_veiculos());
    		request.setAttribute("usuarioId", usuarioId);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrCriarCarona.jsp");
        view.forward(request, response);
	}

}
