import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
	
	String url;
	Connection conn;
	PreparedStatement preparedStatement;
	
	public DataBase(String url, String username, String password) throws SQLException {
		//initialize connection
		this.url =url;
		//log in to database
		conn = DriverManager.getConnection(this.url,username , password);
	}
	
	ResultSet executeQuery(String query) {
		try {
			preparedStatement = conn.prepareStatement(query);
			ResultSet executedQuery = preparedStatement.executeQuery();
			return executedQuery;
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		return null;
	}
	void closeConnection() throws SQLException{
		conn.close();
	}
	void closePreparedStatement() throws SQLException {
		preparedStatement.close();
	}
}
