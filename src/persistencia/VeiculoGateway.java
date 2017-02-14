package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeiculoGateway {
	private int _id;
	private String _modelo;
	private String _placa;
	private String _cor;
	private int _motoristaId;
	
	public VeiculoGateway(String modelo, String placa, String cor){
		this.set_modelo(modelo);
		this.set_placa(placa);
		this.set_cor(cor);
	}
	
	private final String _insertStatement = "INSERT INTO veiculo(modelo,placa,cor,usuario_id) VALUES (?,?,?,?)";
	private final String _updateStatement = "UPDATE usuario SET modelo = ?,placa = ?, cor = ?, usuario_id ? WHERE id = ?";
	
	public String get_modelo() {
		return _modelo;
	}
	public void set_modelo(String _modelo) {
		this._modelo = _modelo;
	}
	public String get_placa() {
		return _placa;
	}
	public void set_placa(String _placa) {
		this._placa = _placa;
	}
	public String get_cor() {
		return _cor;
	}
	public void set_cor(String _cor) {
		this._cor = _cor;
	}

	public int get_motoristaId() {
		return _motoristaId;
	}

	public void set_motoristaId(int _motoristaId) {
		this._motoristaId = _motoristaId;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int id) {
		this._id = id;
	}
	
	public int Insert() throws Exception {
		PreparedStatement insertStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 insertStatement = s.prepareStatement(_insertStatement, Statement.RETURN_GENERATED_KEYS);
			 insertStatement.setString(1, _modelo);
			 insertStatement.setString(2, _placa);
			 insertStatement.setString(3, _cor);
			 insertStatement.setInt(4, _motoristaId);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação do veiculo.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id do novo veiculo.");
			    }
			 }
			 
			 return get_id();
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public void Save() throws Exception {
		PreparedStatement updateStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 updateStatement = s.prepareStatement(_updateStatement);
			 updateStatement.setString(1, _modelo);
			 updateStatement.setString(2, _placa);
			 updateStatement.setString(3, _cor);
			 updateStatement.setInt(4, _motoristaId);
			 updateStatement.setInt(5, _id);
			 
			 int affectedRows = updateStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a atualização do veiculo.");
			 }
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
