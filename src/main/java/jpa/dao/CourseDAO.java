package jpa.dao;

import jpa.entitymodels.Course;
import java.util.List;

public interface CourseDAO {
    //given method declaration
     List<Course> getAllCourses();
}
