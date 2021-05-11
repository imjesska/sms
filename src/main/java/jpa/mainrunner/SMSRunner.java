package jpa.mainrunner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 *Given SMSRunner for SBA
 *
 * @author Harry
 */
public class SMSRunner {

    private Scanner sin;
    private StringBuilder sb;

    private CourseService courseService;
    private StudentService studentService;
    private Student currentStudent;

    public SMSRunner() {
        sin = new Scanner(System.in);
        sb = new StringBuilder();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        SMSRunner sms = new SMSRunner();
        sms.run();
    }

    private void run() {
        // Login or quit
        switch (menu1()) {
            case 1:
                if (studentLogin()) {
                    registerMenu();
                }
                break;
            case 2:
                out.println("Goodbye!");
                break;
            case 3:
                System.out.println("Displaying all Courses");
                List<Course> curs = courseService.getAllCourses();
                curs.forEach(System.out::println);
                break;

            default:
                out.println("Try again");
                break;

        }
    }

    //Menu Workflow
    private int menu1() {
        sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());
        return sin.nextInt();
    }

    private boolean studentLogin() {
        boolean retValue = false;
        out.print("Enter your email address: ");
        String email = sin.next();
        out.print("Enter your password: ");
        String password = sin.next();
        currentStudent = studentService.getStudentByEmail(email);

        //If student has successfully logged in then display the courses that the student has registered.
        if (currentStudent != null && currentStudent.getsPass().equals(password)) {
            List<Course> courses = studentService.getStudentCourses(email);
            out.println("My Classes");
            out.printf("%5s%35S%25s\n", "ID", "Course", "Instructor");

            //If there are no courses then it will display nothing
            for (Course course : courses) {
                out.printf("%5d%35S%25s\n", course.getcId(), course.getcName(), course.getcInstructorName());
            }


            retValue = true;
        } else {
            out.println("User Validation failed. GoodBye!");
        }
        return retValue;



    }


    private void registerMenu() {
        sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());

        switch (sin.nextInt()) {
            case 1:
                //display a list of available courses
                List<Course> allCourses = courseService.getAllCourses();
                List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
                allCourses.removeAll(studentCourses);
                out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    out.println(course);
                }
                out.println();

                //select a course from the list
                out.print("Enter Course Number: ");
                int number = sin.nextInt();

                //retrieve the course based on course id
                Course newCourse = courseService.getCourseById(number);

                //check to see if course exists
                if (newCourse != null) {

                    //register the student to the course if course exists
                    studentService.registerStudentToCourse(currentStudent.getsEmail(), newCourse.getcId());
                    Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());

                    //displays all the courses the student is registered in
                    List<Course> sCourses = studentService.getStudentCourses(temp.getsEmail());
                    out.println("MyClasses");
                    out.printf("%5s%35S%25s\n", "ID", "Course", "Instructor");
                    for (Course course : sCourses) {
                        out.printf("%5d%35S%25s\n", course.getcId(), course.getcName(), course.getcInstructorName());
                    }
                } else {
                    //If the course number selected by user does not exist then display message
                    out.println("Please choose valid course from the list!");
                }
                break;
            case 2:
                out.println("Goodbye!");
                break;
            default:
                out.println("Please select valid option from menu, Goodbye!");
                break;
        }
    }
}

