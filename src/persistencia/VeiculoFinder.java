package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Veiculo;
import excecoes.EntidadeNaoEncontradaException;

public class VeiculoFinder {
	private final String _selectStatement = "SELECT * FROM veiculo WHERE id = ?";
	
	public VeiculoGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet veiculoSet = selectStatement.executeQuery();
			 VeiculoGateway gateway = null;
			 
			 if (veiculoSet.next()) {
                gateway = new VeiculoGateway(veiculoSet.getString("modelo"), veiculoSet.getString("placa"), veiculoSet.getString("cor"));
                gateway.set_motoristaId(veiculoSet.getInt("motorista_id"));
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
