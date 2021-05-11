package jpa.entitymodels;

import jpa.service.StudentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class loginTest {
    StudentService sService = new StudentService();

    @Test
    public void test() {
        Student s = sService.getStudentByEmail("hluckham0@google.ru");
        assertEquals("X1uZcoIh0dj", s.getsPass());
    }

}