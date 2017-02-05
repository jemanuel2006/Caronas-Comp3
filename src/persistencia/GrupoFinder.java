package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Grupo;
import excecoes.EntidadeNaoEncontradaException;

public class GrupoFinder {
	private final String _selectAllStatement = "SELECT * FROM grupo";
	private final String _selectStatement = "SELECT * FROM grupo WHERE id = ?";
	
	public GrupoGateway find(int id) throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectStatement);
			 selectStatement.setInt(1, id);
			 
			 ResultSet grupoSet = selectStatement.executeQuery();
			 GrupoGateway gateway = null;
			 
			 if (grupoSet.next()) {
                Grupo g = new Grupo(grupoSet.getString("nome"),grupoSet.getString("descricao"), grupoSet.getString("regras"), grupoSet.getInt("limite"));
                g.set_id(id);
                gateway = new GrupoGateway(g);
			 } else {
				 throw new EntidadeNaoEncontradaException();
			 }
			 
			 return gateway;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
	
	public Collection<GrupoGateway> getAll() throws Exception{
		PreparedStatement selectStatement = null;
		DatabaseConnector dbConn = new DatabaseConnector();
		 try {
			 Connection s = dbConn.getConnection();
			 selectStatement = s.prepareStatement(_selectAllStatement);
			 
			 ResultSet grupoSet = selectStatement.executeQuery();
			 Collection<GrupoGateway> grupos = new ArrayList<GrupoGateway>();
			 
			 while (grupoSet.next()) {
                Grupo g = new Grupo(grupoSet.getString("nome"),grupoSet.getString("descricao"), grupoSet.getString("regras"), grupoSet.getInt("limite"));
                g.set_id(grupoSet.getInt("id"));
                GrupoGateway gateway = new GrupoGateway(g);
                grupos.add(gateway);
			 }
			 
			 return grupos;
		 } finally {
			dbConn.CloseConnection();
		 }
	}
}
