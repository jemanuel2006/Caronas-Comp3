package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import excecoes.EntidadeNaoEncontradaException;

public class CaronaFinder {
	private final String _selectAllStatement = "SELECT * FROM carona";
	private final String _selectStatement = "SELECT * FROM carona WHERE id = ?";
	
	public CaronaGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet caronaSet = selectStatement.executeQuery();
			 CaronaGateway gateway = null;
			 
			 if (caronaSet.next()) {
                gateway = new CaronaGateway();
                gateway.set_id(caronaSet.getInt("id"));
                gateway.set_logradouroDestinoId(caronaSet.getInt("logradouroDestinoId"));
                gateway.set_logradouroOrigemId(caronaSet.getInt("logradouroOrigemId"));
                gateway.set_motoristaId(caronaSet.getInt("motoristaId"));
                gateway.set_veiculoId(caronaSet.getInt("veiculoId"));
                gateway.setDia(caronaSet.getDate("dia"));
                gateway.setHora_saida(caronaSet.getDate("hora_saida"));
                gateway.set_estadoCarona(caronaSet.getInt("estadoCarona"));
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<CaronaGateway> getAll() throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectAllStatement);
			 
			 ResultSet caronaSet = selectStatement.executeQuery();
			 Collection<CaronaGateway> caronas = new ArrayList<CaronaGateway>();
			 
			 while (caronaSet.next()) {
                CaronaGateway gateway = new CaronaGateway();
                gateway.set_id(caronaSet.getInt("id"));
                gateway.set_logradouroDestinoId(caronaSet.getInt("logradouroDestinoId"));
                gateway.set_logradouroOrigemId(caronaSet.getInt("logradouroOrigemId"));
                gateway.set_motoristaId(caronaSet.getInt("motoristaId"));
                gateway.set_veiculoId(caronaSet.getInt("veiculoId"));
                gateway.setDia(caronaSet.getDate("dia"));
                gateway.setHora_saida(caronaSet.getDate("hora_saida"));
                gateway.set_estadoCarona(caronaSet.getInt("estadoCarona"));
                caronas.add(gateway);
			 }
			 
			 return caronas;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
