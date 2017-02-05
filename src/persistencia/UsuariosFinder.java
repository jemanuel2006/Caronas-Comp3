package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Usuario;
import excecoes.EntidadeNaoEncontradaException;

public class UsuariosFinder {
	private final String _selectAllStatement = "SELECT * FROM usuario";
	private final String _selectStatement = "SELECT * FROM usuario WHERE id = ?";
	private final String _selectByEmailStatement = "SELECT * FROM usuario WHERE email = ?";
	
	public UsuarioGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet usuarioSet = selectStatement.executeQuery();
			 UsuarioGateway gateway = null;
			 
			 if (usuarioSet.next()) {
                Usuario u = new Usuario(usuarioSet.getString("nome"),usuarioSet.getString("email"), usuarioSet.getString("telefone"));
                u.set_id(id);
                gateway = new UsuarioGateway(u);
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public UsuarioGateway find(String email) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectByEmailStatement);
			 selectStatement.setString(1, email);
			 
			 ResultSet usuarioSet = selectStatement.executeQuery();
			 UsuarioGateway gateway = null;
			 
			 if (usuarioSet.next()) {
                Usuario u = new Usuario(usuarioSet.getString("nome"),usuarioSet.getString("email"), usuarioSet.getString("telefone"));
                u.set_id(usuarioSet.getInt("id"));
                gateway = new UsuarioGateway(u);
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<UsuarioGateway> getAll() throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectAllStatement);
			 
			 ResultSet usuarioSet = selectStatement.executeQuery();
			 Collection<UsuarioGateway> usuarios = new ArrayList<UsuarioGateway>();
			 
			 while (usuarioSet.next()) {
                Usuario u = new Usuario(usuarioSet.getString("nome"),usuarioSet.getString("email"), usuarioSet.getString("telefone"));
                u.set_id(usuarioSet.getInt("id"));
                UsuarioGateway gateway = new UsuarioGateway(u);
                usuarios.add(gateway);
			 }
			 
			 return usuarios;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
