package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.AdicionarUsuarioEmCaronaScript;
import TransactionScripts.GetCaronasDisponiveisScript;
import TransactionScripts.GetLogradourosScript;

/**
 * Servlet implementation class CrCriarParticipacaoCarona
 */
@WebServlet("/CriarParticipacaoCarona")
public class CrCriarParticipacaoCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrCriarParticipacaoCarona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			GetCaronasDisponiveisScript cts = new GetCaronasDisponiveisScript();
			GetLogradourosScript lts = new GetLogradourosScript();
			request.setAttribute("caronas", cts.execute());
			request.setAttribute("logradouros", lts.execute());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrCriarParticipacaoCarona.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("_usuarioEmail");
		int caronaId = Integer.parseInt(request.getParameter("_caronaId"));
		int logradouroId = Integer.parseInt(request.getParameter("_logradouroId"));
		
		try {
			AdicionarUsuarioEmCaronaScript cts = new AdicionarUsuarioEmCaronaScript(caronaId, email, logradouroId);
			cts.execute();
			response.sendRedirect("./VisualizarParticipantes?caronaId=" + caronaId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
