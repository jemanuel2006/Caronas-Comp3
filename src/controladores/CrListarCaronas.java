package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.GetCaronasScript;
import entidades.Carona;

/**
 * Servlet implementation class CrListarCaronas
 */
@WebServlet("/ListarCaronas")
public class CrListarCaronas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrListarCaronas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Carona> caronas = new ArrayList<Carona>();
		
		try {
			GetCaronasScript script = new GetCaronasScript();
			caronas = script.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", caronas);
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrListarCaronas.jsp");
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
