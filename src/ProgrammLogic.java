import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgrammLogic {
		
	static DataBase db;
	static ResultSet students;
	
	public static void main(String[] args) throws SQLException {
		db = new DataBase("jdbc:postgresql://localhost:2704/geekyCamp", "postgres", "12345");
		showAllStudents();
		insertStudent("Kamen","Vakavchiev");
		showAllStudents();
		deleteStudent(2);
		showAllStudents();
	}
	//works
	static void showAllStudents() throws SQLException {
		students = db.executeQuery("SELECT * FROM students");
		while(students.next()) {
			System.out.println(students.getInt(1)+" | " +students.getString(2)+" | "+students.getString(3));
		}
	}
	static void showAllCourses(ResultSet courses) throws SQLException {
		while(courses.next()) {
			System.out.println(courses.getInt(1)+" | " +courses.getString(2)+" | "+courses.getString(3)+" | "+courses.getString(4));
		}
	}
	//works
	static void deleteStudent(int id) {
		db.executeQuery("delete from students where studid = "+id);
	}
	static void insertStudent(String firstName, String lastName) {
		db.executeQuery("insert into students (first_name,last_name) values('"+firstName+"','"+lastName+"')");
	}
}
