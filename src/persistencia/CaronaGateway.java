package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CaronaGateway {
	private Date dia;
	private Date hora_saida;
	private int _id;
	private int _motoristaId;
	private int _veiculoId;
	private int _origemId;
	private int _destinoId;
	//0 = ativo, 1 = concluida, 2 = cancelada
	private int _estadoCarona;
	
	private final String _insertStatement = "INSERT INTO grupo(dia, hora_saida,motoristaId,veiculoId,origemId,destinoId,estadoCarona) VALUES (?,?,?,?,?,?,?)";
	private final String _updateStatement = "UPDATE grupo SET dia = ?, hora_saida = ?,motoristaId = ?,veiculoId = ?, origemid = ?, destinoid = ?,estadoCarona = ? WHERE id = ?";
	
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public Date getHora_saida() {
		return hora_saida;
	}
	public void setHora_saida(Date hora_saida) {
		this.hora_saida = hora_saida;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_motoristaId() {
		return _motoristaId;
	}
	public void set_motoristaId(int _motoristaId) {
		this._motoristaId = _motoristaId;
	}
	public int get_veiculoId() {
		return _veiculoId;
	}
	public void set_veiculoId(int _veiculoId) {
		this._veiculoId = _veiculoId;
	}
	public int get_logradouroOrigemId() {
		return _origemId;
	}
	public void set_logradouroOrigemId(int _logradouroOrigemId) {
		this._origemId = _logradouroOrigemId;
	}
	public int get_logradouroDestinoId() {
		return _destinoId;
	}
	public void set_logradouroDestinoId(int _logradouroDestinoId) {
		this._destinoId = _logradouroDestinoId;
	}
	public int get_estadoCarona() {
		return _estadoCarona;
	}
	public void set_estadoCarona(int _estadoCarona) {
		this._estadoCarona = _estadoCarona;
	}
	
	public int Insert() throws Exception {
		PreparedStatement insertStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 insertStatement = s.prepareStatement(_insertStatement, Statement.RETURN_GENERATED_KEYS);
			 insertStatement.setDate(1, dia);
			 insertStatement.setDate(2, hora_saida);
			 insertStatement.setInt(3, _motoristaId);
			 insertStatement.setInt(4, _veiculoId);
			 insertStatement.setInt(5, _origemId);
			 insertStatement.setInt(6, _destinoId);
			 insertStatement.setInt(7, _estadoCarona);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação da carona.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id da nova carona.");
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
			 updateStatement.setDate(1, dia);
			 updateStatement.setDate(2, hora_saida);
			 updateStatement.setInt(3, _motoristaId);
			 updateStatement.setInt(4, _veiculoId);
			 updateStatement.setInt(5, _origemId);
			 updateStatement.setInt(6, _destinoId);
			 updateStatement.setInt(7, _estadoCarona);
			 updateStatement.setInt(8, _id);
			 
			 int affectedRows = updateStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a atualização da carona.");
			 }
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
