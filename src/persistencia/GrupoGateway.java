package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.Grupo;

public class GrupoGateway {
	private int _id;
	private String _nome;
	private String _descricao;
	private String _regras;
	private int _limite;
	
	private final String _insertStatement = "INSERT INTO grupo(nome, descricao,regras,limite) VALUES (?,?,?,?)";
	private final String _updateStatement = "UPDATE grupo SET nome = ?,descricao = ?, regras = ?, limite = ? WHERE id = ?";
	private final String _deleteStatement = "DELETE FROM grupo WHERE id = ?";
	
	public GrupoGateway(Grupo g){
		set_regras(g.get_regras());
		set_nome(g.get_nome());
		set_descricao(g.get_descricao());
		set_limite(g.get_limite());
	}
	
	public String get_nome() {
		return _nome;
	}
	public void set_nome(String _nome) {
		this._nome = _nome;
	}
	public String get_regras() {
		return _regras;
	}
	public void set_regras(String _regras) {
		this._regras = _regras;
	}
	public String get_descricao() {
		return _descricao;
	}
	public void set_descricao(String _descricao) {
		this._descricao = _descricao;
	}
	public int get_limite() {
		return _limite;
	}
	public void set_limite(int _limite) {
		this._limite = _limite;
	}
	public int get_id() {
		return this._id;
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
			 insertStatement.setString(1, _nome);
			 insertStatement.setString(2, _descricao);
			 insertStatement.setString(3, _regras);
			 insertStatement.setInt(4, _limite);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação do grupo.");
			 }
			
			 try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
			    if (generatedKeys.next()) {
			        this.set_id(generatedKeys.getInt(1));
			    }
			    else {
			        throw new SQLException("Ocorreu um erro ao adquirir o id do novo grupo.");
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
			 updateStatement.setString(2, _descricao);
			 updateStatement.setString(3, _regras);
			 updateStatement.setInt(4, _limite);
			 updateStatement.setInt(5, _id);
			 
			 int affectedRows = updateStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a atualização do grupo.");
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
			    throw new SQLException("Ocorreu um erro ao executar a exclusão do grupo.");
			 }
			 
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
