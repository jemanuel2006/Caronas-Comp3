package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Motorista;
import excecoes.EntidadeNaoEncontradaException;

public class MotoristaFinder {
	private final String _selectAllStatement = "SELECT * FROM motorista";
	private final String _selectStatement = "SELECT * FROM motorista WHERE id = ?";
	
	public MotoristaGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet usuarioSet = selectStatement.executeQuery();
			 MotoristaGateway gateway = null;
			 
			 if (usuarioSet.next()) {
                gateway = new MotoristaGateway(id);
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
