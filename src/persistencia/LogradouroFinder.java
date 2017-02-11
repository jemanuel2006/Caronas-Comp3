package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Grupo;
import excecoes.EntidadeNaoEncontradaException;

public class LogradouroFinder {
	private final String _selectStatement = "SELECT * FROM logradouro WHERE id = ?";
	
	public LogradouroGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet grupoSet = selectStatement.executeQuery();
			 LogradouroGateway gateway = null;
			 
			 if (grupoSet.next()) {
                gateway = new LogradouroGateway();
                
                gateway.set_cep(grupoSet.getString("cep"));
                gateway.set_cidade(grupoSet.getString("cidade"));
                gateway.set_distrito(grupoSet.getString("distrito"));
                gateway.set_endereco(grupoSet.getString("endereco"));
                gateway.set_estado(grupoSet.getString("estado"));
                gateway.set_numero(grupoSet.getInt("numero"));
                gateway.set_id(grupoSet.getInt("id"));
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
