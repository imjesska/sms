package jpa.service;

import jpa.dao.Connection;
import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

import java.util.List;

public class CourseService extends Connection implements CourseDAO {

    public CourseService() {

    }
    //retrieves courses from db
    @Override
    public List<Course> getAllCourses() {
        this.connect();
        List<Course> courses = em.createQuery("select c from Course c").getResultList();
        this.disconnect();
        return courses;
    }

    //retrieves course from db by num
   public Course getCourseById(int cId){
       this.connect();
       try{
           Course c = em.find(Course.class, cId);
           this.disconnect();
           return c;
       }catch (Exception e){
           e.printStackTrace();
           this.disconnect();
           return null;
       }
   }

    }

