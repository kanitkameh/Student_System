import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentController {

	static DataBase db;
	static ResultSet queryResult;
	
	public static DataBase getDb() {
		return db;
	}
	public static void setDb(DataBase db) {
		StudentController.db = db;
	}
	public static ResultSet getQueryResult() {
		return queryResult;
	}
	public static void setQueryResult(ResultSet queryResult) {
		StudentController.queryResult = queryResult;
	}
	//student functions
	static void showAllStudents() throws SQLException {
		queryResult = db.executeQuery("SELECT * FROM students");
		while(queryResult.next()) {
			System.out.println(queryResult.getInt(1)+" | " +queryResult.getString(2)+" | "+queryResult.getString(3));
		}
	}
	static void deleteStudent(int id) {
		db.executeQuery("delete from students where studid = "+id);
	}
	static void insertStudent(String firstName, String lastName) {
		db.executeQuery("insert into students (first_name,last_name) values('"+firstName+"','"+lastName+"')");
	}

}
