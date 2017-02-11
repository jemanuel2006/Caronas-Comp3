package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import excecoes.EntidadeNaoEncontradaException;

public class ParadaFinder {
	private final String _selectAllStatement = "SELECT * FROM parada";
	private final String _selectStatement = "SELECT * FROM parada WHERE id = ?";
	private final String _selectStatementByCarona = "SELECT * FROM parada WHERE caronaId = ?";
	
	public ParadaGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet paradaSet = selectStatement.executeQuery();
			 ParadaGateway gateway = null;
			 
			 if (paradaSet.next()) {
				 gateway = new ParadaGateway();
				 gateway.set_caronaId(paradaSet.getInt("caronaId"));
				 gateway.set_id(paradaSet.getInt("id"));
				 gateway.set_logradouroId(paradaSet.getInt("logradouroId"));
				 gateway.set_usuarioId(paradaSet.getInt("usuarioId"));
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<ParadaGateway> getAll() throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectAllStatement);
			 
			 ResultSet paradaSet = selectStatement.executeQuery();
			 Collection<ParadaGateway> paradas = new ArrayList<ParadaGateway>();
			 
			 while (paradaSet.next()) {
				ParadaGateway gateway = new ParadaGateway();
				gateway.set_caronaId(paradaSet.getInt("caronaId"));
				gateway.set_id(paradaSet.getInt("id"));
				gateway.set_logradouroId(paradaSet.getInt("logradouroId"));
				gateway.set_usuarioId(paradaSet.getInt("usuarioId"));
				paradas.add(gateway);
			 }
			 
			 return paradas;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<ParadaGateway> getParadasByCarona(int caronaId) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatementByCarona);
			 selectStatement.setInt(1, caronaId);
			 
			 ResultSet paradaSet = selectStatement.executeQuery();
			 Collection<ParadaGateway> paradas = new ArrayList<ParadaGateway>();
			 
			 while (paradaSet.next()) {
				ParadaGateway gateway = new ParadaGateway();
				gateway.set_caronaId(paradaSet.getInt("caronaId"));
				gateway.set_id(paradaSet.getInt("id"));
				gateway.set_logradouroId(paradaSet.getInt("logradouroId"));
				gateway.set_usuarioId(paradaSet.getInt("usuarioId"));
				paradas.add(gateway);
			 }
			 
			 return paradas;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
