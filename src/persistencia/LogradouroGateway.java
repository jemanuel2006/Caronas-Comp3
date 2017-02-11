package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogradouroGateway {
	private int _id;
	private String _cep;
	private String _cidade;
	private String _estado;
	private String _distrito;
	private String _endereco;
	private int _numero;
	
	private final String _insertStatement = "INSERT INTO logradouro(cep, cidade, estado, distrito, endereco, numero) VALUES (?,?,?,?,?,?)";
	
	public String get_cep() {
		return _cep;
	}
	public void set_cep(String _cep) {
		this._cep = _cep;
	}
	public String get_cidade() {
		return _cidade;
	}
	public void set_cidade(String _cidade) {
		this._cidade = _cidade;
	}
	public String get_estado() {
		return _estado;
	}
	public void set_estado(String _estado) {
		this._estado = _estado;
	}
	public String get_distrito() {
		return _distrito;
	}
	public void set_distrito(String _distrito) {
		this._distrito = _distrito;
	}
	public String get_endereco() {
		return _endereco;
	}
	public void set_endereco(String _endereco) {
		this._endereco = _endereco;
	}
	public int get_numero() {
		return _numero;
	}
	public void set_numero(int _numero) {
		this._numero = _numero;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public int Insert() throws Exception {
		PreparedStatement insertStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 insertStatement = s.prepareStatement(_insertStatement, Statement.RETURN_GENERATED_KEYS);
			 insertStatement.setString(1, _cep);
			 insertStatement.setString(2, _cidade);
			 insertStatement.setString(3, _estado);
			 insertStatement.setString(4, _distrito);
			 insertStatement.setString(5, _endereco);
			 insertStatement.setInt(6, _numero);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação do Logradouro.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id do novo logradouro.");
			    }
			 }
			 
			 return get_id();
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
