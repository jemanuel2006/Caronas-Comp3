package conexao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao {
	public Connection getConnection() throws ClassNotFoundException {
	     try {
	    	 Class.forName("com.mysql.jdbc.Driver");
	         return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bancoCaronas-Comp3","root", "");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
}
