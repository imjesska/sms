package jpa.service;

import jpa.dao.Connection;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import java.util.List;

public class StudentService extends Connection implements StudentDAO {

    public StudentService() {

    }

    @Override
    public List<Student> getAllStudents() {
        this.connect();
        List<Student> students = em.createQuery("select s from Student s").getResultList();
        this.disconnect();
        return students;
    }

    //retrieves student from db by email
    @Override
    public Student getStudentByEmail(String email) {
        this.connect();
        try {
            Student s = em.find(Student.class, email);
            this.disconnect();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            this.disconnect();
            return null;
        }
    }

    //verifies and matches email and password
    @Override
    public boolean validateStudent(String email, String sPassword) {
        Student s = this.getStudentByEmail(email);
        if (s.getsPass().equals(sPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        this.connect();
        Student s = em.find(Student.class, sEmail);
        em.getTransaction().begin();
        Course c = em.find(Course.class, cId);

        //display course to add
        System.out.println("Course you want to add");
        System.out.println(c);
        List<Course> courses = s.getsCourses();

        //if student is already in the course display message and return
        if (courses.contains(c)){
            System.out.println("You're already registered in this course");
            return;
        }
        courses.add(c);
        System.out.println(courses.size());
        s.setsCourses(courses);
    	em.persist(courses);
        em.getTransaction().commit();
        this.disconnect();
    }
    //get a list of students courses by email
    @Override
    public List<Course> getStudentCourses(String sEmail) {
        Student s = this.getStudentByEmail(sEmail);
        List<Course> courses = s.getsCourses();
        return courses;
    }

}
