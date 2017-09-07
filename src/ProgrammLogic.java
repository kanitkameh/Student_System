import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgrammLogic {
	
	static DataBase db;
	static ResultSet queryResult;
	
	public static void main(String[] args) throws SQLException {
		db = new DataBase("jdbc:postgresql://localhost:2704/geekyCamp", "postgres", "12345");
		System.out.println("List of all students:");
		showAllStudents();
		System.out.println("List of all courses:");
		showAllCourses();
		System.out.println("List of all courses students have subscribed to:");
		showStudentCourses();
	}
	static void removeStudentFromCourse(int stud_id, int course_id) {
		db.executeQuery("delete from studentcourses where student_id="+stud_id+" AND course_id="+course_id);
	}
	static void showStudentCourses() throws SQLException {
		queryResult = db.executeQuery("Select distinct students.firstname, students.lastname, courses.coursename\r\n" + 
				"from studentcourses left join students on students.id=studentcourses.student_id left join\r\n" + 
				"courses on courses.id=studentcourses.course_id;");
		while(queryResult.next()) {
			System.out.println(queryResult.getString(1)+" | "+queryResult.getString(2)+" | "+queryResult.getString(3));
		}
	}
	static void insertStudentIntoCourse(int student_id, int course_id) {
		db.executeQuery("insert into studentcourses (course_id,student_id)values("+course_id+","+student_id+");");
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
