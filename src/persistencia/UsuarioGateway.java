package persistencia;

import java.sql.*;

import entidades.Usuario;

public class UsuarioGateway {
	private String _nome;
	private String _email;
	private String _telefone;
	private int _id;
	
	private final String _insertStatement = "INSERT INTO usuario(nome,email,telefone) VALUES (?,?,?)";
	private final String _updateStatement = "UPDATE usuario SET nome = ?,email = ?, telefone = ? WHERE id = ?";
	private final String _deleteStatement = "DELETE FROM usuario WHERE id = ?";
	
	public UsuarioGateway(Usuario u){
		set_email(u.get_email());
		set_nome(u.get_nome());
		set_telefone(u.get_telefone());
		set_id(u.get_id());
	}
	
	public String get_email() {
		return _email;
	}
	public void set_email(String _email) {
		this._email = _email;
	}
	public String get_nome() {
		return _nome;
	}
	public void set_nome(String _nome) {
		this._nome = _nome;
	}
	public String get_telefone() {
		return _telefone;
	}
	public void set_telefone(String _telefone) {
		this._telefone = _telefone;
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
			 insertStatement.setString(1, _nome);
			 insertStatement.setString(2, _email);
			 insertStatement.setString(3, _telefone);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação do usuario.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id do novo usuario.");
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
			 updateStatement.setString(1, _nome);
			 updateStatement.setString(2, _email);
			 updateStatement.setString(3, _telefone);
			 updateStatement.setInt(4, _id);
			 
			 int affectedRows = updateStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a atualização do usuario.");
			 }
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
			    throw new SQLException("Ocorreu um erro ao executar a exclusão do usuario.");
			 }
			 
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
}
