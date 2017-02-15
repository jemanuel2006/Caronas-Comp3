package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Usuario;
import entidades.Veiculo;
import excecoes.EntidadeNaoEncontradaException;

public class VeiculoFinder {
	private final String _selectStatement = "SELECT * FROM veiculo WHERE id = ?";
	private final String _selectByUsuario = "SELECT * FROM veiculo WHERE usuario_id = ?";
	
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
                gateway.set_motoristaId(veiculoSet.getInt("usuario_id"));
                gateway.set_id(id);
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<VeiculoGateway> getVeiculosByUsuario(int usuarioId) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectByUsuario);
			 selectStatement.setInt(1, usuarioId);
			 
			 ResultSet veiculoSet = selectStatement.executeQuery();
			 Collection<VeiculoGateway> veiculos = new ArrayList<VeiculoGateway>();
			 
			 while (veiculoSet.next()) {
                VeiculoGateway v = new VeiculoGateway(veiculoSet.getString("modelo"), veiculoSet.getString("placa"), veiculoSet.getString("cor"));
                v.set_motoristaId(veiculoSet.getInt("usuario_id"));
                v.set_id(veiculoSet.getInt("id"));
                veiculos.add(v);
			 }
			 
			 return veiculos;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
