package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario_GrupoGateway {
	private int _usuarioId;
	private int _grupoId;
	
	private final String _insertStatement = "INSERT INTO usuario_grupo(grupo_id, usuario_id) VALUES (?,?)";
	private final String _deleteStatement = "DELETE FROM usuario_grupo WHERE grupo_id = ? AND usuario_id = ?";
	
	public Usuario_GrupoGateway() {
		
	}
	
	public Usuario_GrupoGateway(int usuarioId, int grupoId) {
		this.set_usuarioId(usuarioId);
		this.set_grupoId(grupoId);
	}

	public int get_usuarioId() {
		return _usuarioId;
	}

	public void set_usuarioId(int _usuarioId) {
		this._usuarioId = _usuarioId;
	}

	public int get_grupoId() {
		return _grupoId;
	}

	public void set_grupoId(int _grupoId) {
		this._grupoId = _grupoId;
	}
	
	public void Insert() throws Exception {
		PreparedStatement insertStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 insertStatement = s.prepareStatement(_insertStatement);
			 insertStatement.setInt(1, this._grupoId);
			 insertStatement.setInt(2, this._usuarioId);
			 
			 int affectedRows = insertStatement.executeUpdate();
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a criação da associação usuario-grupo.");
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
			 deleteStatement.setInt(1, _grupoId);
			 deleteStatement.setInt(2, _usuarioId);
			 
			 int affectedRows = deleteStatement.executeUpdate();
			 
			 if (affectedRows == 0) {
			    throw new SQLException("Ocorreu um erro ao executar a exclusão da associação de grupo e usuário.");
			 }
			 
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
