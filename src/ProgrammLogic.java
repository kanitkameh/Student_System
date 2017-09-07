import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgrammLogic {
	
	static DataBase db;
	static ResultSet queryResult;
	
	public static void main(String[] args) throws SQLException {
		db = new DataBase("jdbc:postgresql://localhost:2704/geekyCamp", "postgres", "12345");
		
		StudentController.setDb(db);
		StudentController.setQueryResult(queryResult);
		
		CoursesController.setDb(db);
		CoursesController.setQueryResult(queryResult);
		
		FacultyController.setDb(db);
		FacultyController.setQueryResult(queryResult);
		
		System.out.println("\nList of all students:");
		StudentController.showAllStudents();
		System.out.println("\nList of all courses:");
		CoursesController.showAllCourses();
		System.out.println("\nList of all courses students have subscribed to:");
		CoursesController.showStudentCourses();
		System.out.println("\nList of all faculties:");
		FacultyController.showAllFaculties();
		System.out.println("\nStudents listed by faculties:");
		FacultyController.showStudentFaculties();
	}
}
