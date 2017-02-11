package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AvaliacaoGateway {
	private int _id;
	private int _usuarioId;
	private int _estrelas;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_usuarioId() {
		return _usuarioId;
	}
	public void set_usuarioId(int _usuarioId) {
		this._usuarioId = _usuarioId;
	}
	public int get_estrelas() {
		return _estrelas;
	}
	public void set_estrelas(int _estrelas) {
		this._estrelas = _estrelas;
	}
	
	private final String _insertStatement = "INSERT INTO avalicao(usuarioId, estrelas) VALUES (?,?)";
	
	public int Insert() throws Exception {
		PreparedStatement insertStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 insertStatement = s.prepareStatement(_insertStatement, Statement.RETURN_GENERATED_KEYS);
			 insertStatement.setInt(1, _usuarioId);
			 insertStatement.setInt(2, _estrelas);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação da avaliação.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id da nova avaliação.");
			    }
			 }
			 
			 return get_id();
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
