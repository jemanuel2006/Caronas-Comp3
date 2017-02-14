package persistencia;
import java.sql.*;
import com.mysql.jdbc.Driver;;

public class DatabaseConnector {
    private Connection conn;
    public static String connString = "jdbc:mysql://127.0.0.1:3306/bancocaronascomp3";
    public static String user = "root";
    public static String pass = "abc123";

    public DatabaseConnector() throws Exception{
        conn = getConnection();
    }

    public Connection getConnection() throws Exception{
        Connection conn = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConnector.connString, DatabaseConnector.user, DatabaseConnector.pass);
            return conn;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return conn;
    }

    public void CloseConnection() throws SQLException{
        conn.close();
    }
}