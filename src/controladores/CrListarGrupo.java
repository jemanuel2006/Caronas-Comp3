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

import TransactionScripts.GetAllGruposScript;
import entidades.Grupo;
import entidades.Usuario;

/**
 * Servlet implementation class CrListarGrupo
 */
@WebServlet("/ListarGrupos")
public class CrListarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrListarGrupo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Grupo> grupos = new ArrayList<Grupo>();
		
		try {
			GetAllGruposScript script = new GetAllGruposScript();
			grupos = script.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", grupos);
		RequestDispatcher view = request.getRequestDispatcher("Grupo/FrListarGrupos.jsp");
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
