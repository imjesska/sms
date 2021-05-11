package jpa.entitymodels;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "email", length = 50, nullable = false)
    String sEmail;
    @Column(name = "name", length = 50, nullable = false)
    String sName;
    @Column(name = "password", length = 50, nullable = false)
    String sPass;
    @OneToMany(targetEntity=Course.class)
    List<Course> sCourses;

    //Requirement: The first constructor takes no parameters and it initializes every member to an initial value
    public Student() {
        this.sEmail = "";
        this.sName = "";
        this.sPass = "";
        this.sCourses = null;
    }

    public Student(String email, String sName, String sPass, List<Course> sCourses) {
        this.sEmail = email;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    public Student(String email, String name, String password) {
        this.sEmail = email;
        this.sName = name;
        this.sPass = password;
    }

    //getters and setters
    public String getsEmail() {
        return sEmail;
    }
    public void setsEmail(String email) {
        this.sEmail = email;
    }
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    public String getsPass() {
        return sPass;
    }
    public void setsPass(String sPass) {
        this.sPass = sPass;
    }
    public List<Course> getsCourses() {
        return sCourses;
    }
    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }

}
