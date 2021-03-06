package controladores;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.EditarCaronaScript;
import TransactionScripts.GetCaronaScript;
import TransactionScripts.GetLogradourosScript;
import TransactionScripts.GetMotoristaByVeiculo;
import TransactionScripts.PodeAlterarCaronaScript;
import entidades.Carona;
import entidades.Motorista;
import helpers.QueryStringHelper;

/**
 * Servlet implementation class CrEditarCarona
 */
@WebServlet("/EditarCarona")
public class CrEditarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrEditarCarona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
			int id = Integer.parseInt(params.get("id"));
			
			PodeAlterarCaronaScript cts = new PodeAlterarCaronaScript(id);
			GetCaronaScript caronaScript = new GetCaronaScript(id);
			
			Carona carona = caronaScript.execute();
			boolean podeAlterarCarona = cts.execute();
			
			GetMotoristaByVeiculo mts = new GetMotoristaByVeiculo(carona.get_veiculo().get_id());
			Motorista motorista = mts.execute();
			
			GetLogradourosScript lts = new GetLogradourosScript();
			
			request.setAttribute("usuarioId", motorista.get_id());
			request.setAttribute("veiculoId", carona.get_veiculo().get_id());
			request.setAttribute("origemId", carona.get_origem().get_id());
			request.setAttribute("destinoId", carona.get_destino().get_id());
			request.setAttribute("hora_saida", carona.getHora_saida());
			request.setAttribute("dia", carona.getDia());
			request.setAttribute("logradouros", lts.execute());
    		request.setAttribute("veiculos", motorista.get_veiculos());
    		request.setAttribute("podeAlterarCarona", podeAlterarCarona);
    		request.setAttribute("_id", id);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrEditarCarona.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("_id"));
			int _veiculoId = Integer.parseInt(request.getParameter("_veiculoId"));
			int _motoristaId = Integer.parseInt(request.getParameter("usuarioId"));
			Date _dia = Date.valueOf(request.getParameter("_dia"));
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Date _hora_saida = new Date(sdf.parse(request.getParameter("horario_saida")).getTime());
			int _logradouroOrigemId = Integer.parseInt(request.getParameter("_origemId"));
			int _logradouroDestinoId = Integer.parseInt(request.getParameter("_destinoId"));
			
			EditarCaronaScript ts = new EditarCaronaScript(id, _motoristaId, _veiculoId, _dia, _hora_saida, _logradouroOrigemId, _logradouroDestinoId);
			
			ts.execute();
			response.sendRedirect("./EditarCarona?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
