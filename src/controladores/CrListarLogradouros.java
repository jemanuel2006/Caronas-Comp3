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

import TransactionScripts.GetLogradourosScript;
import entidades.Logradouro;

/**
 * Servlet implementation class CrListarLogradouros
 */
@WebServlet("/ListarLogradouros")
public class CrListarLogradouros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrListarLogradouros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Logradouro> logradouros = new ArrayList<Logradouro>();
		
		try {
			GetLogradourosScript ts = new GetLogradourosScript();
			logradouros = ts.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", logradouros);
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrListarLogradouros.jsp");
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
