package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParadaGateway {
	private int _id;
	private int _caronaId;
	private int _logradouroId;
	private int _usuarioId;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_caronaId() {
		return _caronaId;
	}
	public void set_caronaId(int _caronaId) {
		this._caronaId = _caronaId;
	}
	public int get_logradouroId() {
		return _logradouroId;
	}
	public void set_logradouroId(int _logradouroId) {
		this._logradouroId = _logradouroId;
	}
	public int get_usuarioId() {
		return _usuarioId;
	}
	public void set_usuarioId(int _usuarioId) {
		this._usuarioId = _usuarioId;
	}
	
	private final String _insertStatement = "INSERT INTO parada(caronaId, logradouroId, usuarioId) VALUES (?,?,?)";
	
	public int Insert() throws Exception {
		PreparedStatement insertStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 insertStatement = s.prepareStatement(_insertStatement, Statement.RETURN_GENERATED_KEYS);
			 insertStatement.setInt(1, _caronaId);
			 insertStatement.setInt(2, _logradouroId);
			 insertStatement.setInt(3, _usuarioId);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação da parada.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id da nova parada.");
			    }
			 }
			 
			 return get_id();
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
