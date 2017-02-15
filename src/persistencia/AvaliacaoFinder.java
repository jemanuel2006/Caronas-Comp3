package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Usuario;

public class AvaliacaoFinder {
	private final String _selectStatement = "SELECT * FROM avaliacao WHERE id = ?";
	private final String _selectByUsuarioStatement = "SELECT * FROM avaliacao WHERE usuarioId = ?";
	private final String _selectAllStatement = "SELECT * FROM avaliacao";
	
	public AvaliacaoGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet avaliacaoSet = selectStatement.executeQuery();
			 AvaliacaoGateway gateway = null;
			 
			 if (avaliacaoSet.next()) {
                gateway = new AvaliacaoGateway();
                gateway.set_estrelas(avaliacaoSet.getInt("estrelas"));
                gateway.set_usuarioId(avaliacaoSet.getInt("usuarioId"));
                gateway.set_id(avaliacaoSet.getInt("id"));
			 }
			 
			 return gateway;
		 } finally {
			if(selectStatement != null)
				selectStatement.close();
			
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<AvaliacaoGateway> getAll() throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectAllStatement);
			 
			 ResultSet avaliacaoSet = selectStatement.executeQuery();
			 Collection<AvaliacaoGateway> avaliacoes = new ArrayList<AvaliacaoGateway>();
			 
			 while (avaliacaoSet.next()) {
				 AvaliacaoGateway gateway = new AvaliacaoGateway();
				 gateway.set_estrelas(avaliacaoSet.getInt("estrelas"));
				 gateway.set_usuarioId(avaliacaoSet.getInt("usuarioId"));
				 gateway.set_id(avaliacaoSet.getInt("id"));
				 avaliacoes.add(gateway);
			 }
			 
			 return avaliacoes;
		 } finally {
			 if(selectStatement != null)
					selectStatement.close();
			 
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<AvaliacaoGateway> getByUsuario(int usuarioId) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectByUsuarioStatement);
			 selectStatement.setInt(1, usuarioId);
			 
			 ResultSet avaliacaoSet = selectStatement.executeQuery();
			 Collection<AvaliacaoGateway> avaliacoes = new ArrayList<AvaliacaoGateway>();
			 
			 while (avaliacaoSet.next()) {
				 AvaliacaoGateway gateway = new AvaliacaoGateway();
				 gateway.set_estrelas(avaliacaoSet.getInt("estrelas"));
				 gateway.set_usuarioId(avaliacaoSet.getInt("usuarioId"));
				 gateway.set_id(avaliacaoSet.getInt("id"));
				 avaliacoes.add(gateway);
			 }
			 
			 return avaliacoes;
		 } finally {
			 if(selectStatement != null)
					selectStatement.close();
			dbConn.CloseConnection();
		 }
	}
}
