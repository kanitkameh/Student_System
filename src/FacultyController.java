import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyController {

	static DataBase db;
	static ResultSet queryResult;
	
	public static DataBase getDb() {
		return db;
	}
	public static void setDb(DataBase db) {
		FacultyController.db = db;
	}
	public static ResultSet getQueryResult() {
		return queryResult;
	}
	public static void setQueryResult(ResultSet queryResult) {
		FacultyController.queryResult = queryResult;
	}
	
	static void insertNewFaculty(String name) {
		db.executeQuery("insert into faculties(faculty_name) values ('"+name+"');");
	}
	
	static void deleteFaculty(String name) {
		db.executeQuery("DELETE FROM faculties where faculty_name = "+name);
		//another statement for removal of all studs in the following faculty
		
	}
	static void deleteFaculty(int id) throws SQLException {
		//selects all students from this faculty and deletes them
		queryResult = db.executeQuery("Select student_id from studentfaculties where faculty_id="+id);
		while(queryResult.next()) {
			db.executeQuery("DELETE FROM students where id="+queryResult.getInt(1));
		}
		//deletes the faculty
		db.executeQuery("DELETE FROM faculties where id = "+id);
	}
	static void showAllFaculties() throws SQLException {
		queryResult = db.executeQuery("SELECT * FROM faculties");
		while(queryResult.next()) {
			System.out.println(queryResult.getInt(1)+" | " +queryResult.getString(2));
		}
	}
	static void insertStudentIntoFaculty(int student_id,int faculty_id) {
		db.executeQuery("insert into studentfaculties(student_id,faculty_id)values("+student_id+","+faculty_id+");");
	}
	static void showStudentFaculties() throws SQLException {
		queryResult = db.executeQuery("select students.first_name, students.last_name, faculties.faculty_name\r\n" + 
				"from studentfaculties LEFT JOIN students on students.id=studentfaculties.student_id\r\n" + 
				"Left JOIN faculties on faculties.id=studentfaculties.faculty_id;");
		while(queryResult.next()) {
			System.out.println(queryResult.getString(1)+" | "+queryResult.getString(2)+" | "+queryResult.getString(3));
		}
	}
	static void deleteStudentFromFaculty(int student_id) {
		db.executeQuery("DELETE FROM studentfaculties where student_id = "+student_id);
	}
}
