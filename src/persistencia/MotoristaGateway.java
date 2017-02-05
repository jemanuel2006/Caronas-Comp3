package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotoristaGateway {
	private int _id;
	
	public MotoristaGateway(int id){
		this._id = id;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}
	
	private final String _insertStatement = "INSERT INTO motorista(id) VALUES (?)";
	private final String _deleteStatement = "DELETE FROM motorista WHERE id = ?";
	
	public int Insert() throws Exception {
		PreparedStatement insertStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 insertStatement = s.prepareStatement(_insertStatement, Statement.RETURN_GENERATED_KEYS);
			 insertStatement.setInt(1, _id);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação do motorista.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id do novo motorista.");
			    }
			 }
			 
			 return get_id();
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public void Delete() throws Exception {
		PreparedStatement deleteStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 deleteStatement = s.prepareStatement(_deleteStatement);
			 deleteStatement.setInt(1, _id);
			 
			 int affectedRows = deleteStatement.executeUpdate();
			 
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a exclusão do motorista.");
			 }
			 
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
