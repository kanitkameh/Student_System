import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgrammLogic {
	
	static DataBase db;
	static ResultSet queryResult;
	
	public static void main(String[] args) throws SQLException {
		db = new DataBase("jdbc:postgresql://localhost:2704/geekyCamp", "postgres", "12345");
		insertStudent("Kamen","Vakavchiev");
	}
	//course functions
	static void showAllCourses() throws SQLException {
		queryResult = db.executeQuery("SELECT * FROM courses");
		while(queryResult.next()) {
			System.out.println(queryResult.getInt(1)+" | " +queryResult.getString(2)+" | "+queryResult.getString(3)+" | "+queryResult.getString(4));
		}
	}
	static void insertCourse(String name, String descr,int credits) {
		db.executeQuery("Insert into courses(coursename,coursedescr,credits)values('"+name+"','"+descr+"',"+credits+")");
	}
	static void deleteCourse(int id) {
		db.executeQuery("DELETE FROM courses where id="+id);
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
