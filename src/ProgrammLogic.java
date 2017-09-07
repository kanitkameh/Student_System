import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProgrammLogic {
	
	static DataBase db;
	static ResultSet queryResult;
	static Scanner input;
	
	public static void main(String[] args) throws SQLException {
		db = new DataBase("jdbc:postgresql://localhost:2704/geekyCamp", "postgres", "12345");
		input = new Scanner(System.in);
		StudentController.setDb(db);
		StudentController.setQueryResult(queryResult);
		
		CoursesController.setDb(db);
		CoursesController.setQueryResult(queryResult);
		
		FacultyController.setDb(db);
		FacultyController.setQueryResult(queryResult);
		int choice=0;
		while(true) {
			System.out.println("Type 1 for viewing a table, 2 for inserting a new element, 3 for deleting an element and 4 for exit:");
			choice=Integer.parseInt(input.nextLine());
			switch(choice){
			case 1:view();
				break;
			case 2:insert();
				break;
			case 3:delete();
				break;
			case 4:input.close(); 
				return;
			}
		}
	}
	static void view() throws SQLException {
		System.out.println("Choose which records do you want to see:");
		printTables();
		switch(Integer.parseInt(input.nextLine())) {
		case 1:StudentController.showAllStudents();
			break;
		case 2:CoursesController.showAllCourses();
			break;
		case 3:CoursesController.showStudentCourses();
			break;
		case 4:FacultyController.showAllFaculties();
			break;
		case 5:FacultyController.showStudentFaculties();
			break;
		}
	}
	static void insert() throws SQLException {
		System.out.println("Choose where do you want to insert new records:");
		printTables();
		switch(Integer.parseInt(input.nextLine())) {
		case 1:
			System.out.println("Enter first name of the student");
			String first_name=input.nextLine();
			System.out.println("Enter last name of the student");
			String last_name=input.nextLine();
			StudentController.insertStudent(first_name,last_name);
			break;
		case 2:
			System.out.println("Enter course name:");
			String name = input.nextLine();
			System.out.println("Enter description about the course:");
			String descr = input.nextLine();
			System.out.println("Enter the amout of credits:");
			int credits = Integer.parseInt(input.nextLine());
			CoursesController.insertCourse(name, descr, credits);
			break;
		case 3:
			System.out.println("Enter the id of the student you want to record:");
			int student_id = Integer.parseInt(input.nextLine());
			System.out.println("Enther the desired course id for this student");
			int course_id = Integer.parseInt(input.nextLine());
			CoursesController.insertStudentIntoCourse(student_id, course_id);
			break;
		case 4:
			System.out.println("Enter the name for the new faculty:");
			FacultyController.insertNewFaculty(input.nextLine());
			break;
		case 5:
			System.out.println("Enter the id of the student you want to record:");
			int student_id1 = Integer.parseInt(input.nextLine());
			System.out.println("Enther the desired faculty id for this student");
			int faculty_id = Integer.parseInt(input.nextLine());
			FacultyController.insertStudentIntoFaculty(student_id1, faculty_id);
			break;
		}
	}
	static void delete() {
		System.out.println("Choose where do you want to delete records:");
		printTables();
		switch(Integer.parseInt(input.nextLine())) {
		case 1:
			System.out.println("Enter the id of the student you want to delete");
			StudentController.deleteStudent(Integer.parseInt(input.nextLine()));
			break;
		case 2:
			System.out.println("Enter the name of the course you want to delete");
			CoursesController.deleteCourse(Integer.parseInt(input.nextLine()));
			break;
		case 3:
			System.out.println("Enter the id of the student you want to remove from a course:");
			int student_id=Integer.parseInt(input.nextLine());
			System.out.println("Enter the id of the course:");
			int course_id=Integer.parseInt(input.nextLine());
			CoursesController.removeStudentFromCourse(student_id, course_id);
			break;
		case 4:
			System.out.println("Enter the name of the faculty you want to delete:");
			FacultyController.deleteFaculty(input.nextLine());
			break;
		case 5:
			System.out.println("Enter the id of the student you want to unsign from his faculty:");
			FacultyController.deleteStudentFromFaculty(Integer.parseInt(input.nextLine()));
			break;
		}
		
	}
	static void printTables() {
		System.out.println("1.All students");
		System.out.println("2.All courses");
		System.out.println("3.Students listed by their choosen courses");
		System.out.println("4.All faculties");
		System.out.println("5.Students listed by their choosen faculties");
	}
}
