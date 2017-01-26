package persistencia;

import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import conexao.Conexao;
import entidades.Usuario;
public class UsuarioDAO {
	
private Connection connection;
	
	public UsuarioDAO() throws ClassNotFoundException {
		this.connection = (Connection) new Conexao().getConnection();
	}
	
	//Adiciona usuario no banco
	public void adiciona(Usuario usuario) {
		//if(!valida(itemVenda)) return;
		String sql = "insert into usuario " +
				"(_nome, _email, _telefone)" +
				" values (?,?,?)";
		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,usuario.get_nome());
			stmt.setString(2,usuario.get_email());
			stmt.setString(3,usuario.get_telefone());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}