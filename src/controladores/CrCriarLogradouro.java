package controladores;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TransactionScripts.CriarLogradouroScript;

/**
 * Servlet implementation class CrCriarLogradouro
 */
@WebServlet("/CriarLogradouro")
public class CrCriarLogradouro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrCriarLogradouro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("Carona/FrCriarLogradouro.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String distrito = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("uf");
		int numero = Integer.parseInt(request.getParameter("numero"));
		
		try{
			CriarLogradouroScript ts = new CriarLogradouroScript(cep, estado, cidade, distrito, rua, numero);
			ts.execute();
			response.sendRedirect("./ListarLogradouros");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
