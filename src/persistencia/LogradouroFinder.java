package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Grupo;
import excecoes.EntidadeNaoEncontradaException;

public class LogradouroFinder {
	private final String _selectStatement = "SELECT * FROM logradouro WHERE id = ?";
	private final String _selectAllStatement = "SELECT * FROM logradouro";
	
	public LogradouroGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet logradouroSet = selectStatement.executeQuery();
			 LogradouroGateway gateway = null;
			 
			 if (logradouroSet.next()) {
                gateway = new LogradouroGateway();
                
                gateway.set_cep(logradouroSet.getString("cep"));
                gateway.set_cidade(logradouroSet.getString("cidade"));
                gateway.set_distrito(logradouroSet.getString("distrito"));
                gateway.set_endereco(logradouroSet.getString("endereco"));
                gateway.set_estado(logradouroSet.getString("estado"));
                gateway.set_numero(logradouroSet.getInt("numero"));
                gateway.set_id(logradouroSet.getInt("id"));
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<LogradouroGateway> getAll() throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		Collection<LogradouroGateway> logradouros = new ArrayList<>();
		
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectAllStatement);
			 
			 ResultSet logradouroSet = selectStatement.executeQuery();
			 
			 while (logradouroSet.next()) {
				LogradouroGateway gateway = new LogradouroGateway();
                
                gateway.set_cep(logradouroSet.getString("cep"));
                gateway.set_cidade(logradouroSet.getString("cidade"));
                gateway.set_distrito(logradouroSet.getString("distrito"));
                gateway.set_endereco(logradouroSet.getString("endereco"));
                gateway.set_estado(logradouroSet.getString("estado"));
                gateway.set_numero(logradouroSet.getInt("numero"));
                gateway.set_id(logradouroSet.getInt("id"));
                
                logradouros.add(gateway);
			 }
			 
			 return logradouros;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
