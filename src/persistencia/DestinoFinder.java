package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DestinoFinder {
private final String _selectStatement = "SELECT * FROM destino WHERE id = ?";
	
	public DestinoGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet logradouroSet = selectStatement.executeQuery();
			 DestinoGateway gateway = null;
			 
			 if (logradouroSet.next()) {
                gateway = new DestinoGateway(logradouroSet.getInt("id"));
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
