package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrigemFinder {
	private final String _selectStatement = "SELECT * FROM origem WHERE id = ?";
	
	public OrigemGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet logradouroSet = selectStatement.executeQuery();
			 OrigemGateway gateway = null;
			 
			 if (logradouroSet.next()) {
                gateway = new OrigemGateway(logradouroSet.getInt("id"));
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
