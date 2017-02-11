package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Grupo;
import excecoes.EntidadeNaoEncontradaException;

public class Usuario_GrupoFinder {
	private final String _selectStatement = "SELECT * FROM usuario_grupo WHERE grupo_id = ? AND usuario_id = ?";
	private final String _selectStatementGrupo = "SELECT * FROM usuario_grupo WHERE grupo_id = ?";
	private final String _selectStatementUsuario = "SELECT * FROM usuario_grupo WHERE usuario_id = ?";
	
	public Usuario_GrupoGateway find(int grupoId, int usuarioId) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, grupoId);
			 selectStatement.setInt(2, usuarioId);
			 
			 ResultSet associacaoSet = selectStatement.executeQuery();
			 Usuario_GrupoGateway gateway = null;
			 
			 if (associacaoSet.next()) {
				 gateway = new Usuario_GrupoGateway(grupoId, usuarioId);
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<Usuario_GrupoGateway> findByGrupo(int grupoId) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		Collection<Usuario_GrupoGateway> gateways = new ArrayList<Usuario_GrupoGateway>();
		
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatementGrupo);
			 selectStatement.setInt(1, grupoId);
			 
			 ResultSet associacaoSet = selectStatement.executeQuery();
			 
			 while (associacaoSet.next()) {
				 Usuario_GrupoGateway gateway = new Usuario_GrupoGateway();
				 gateway.set_grupoId(grupoId);
				 gateway.set_usuarioId(associacaoSet.getInt("usuario_id"));
				 gateways.add(gateway);
			 }
			 
			 return gateways;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
