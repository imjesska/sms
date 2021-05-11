package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Course")
public class Course {
    @Id
    @Column(name="id")
    int cId;
    @Column(name="name", length=50, nullable=false)
    String cName;
    @Column(name="Instructor", length=50, nullable=false)
    String cInstructorName;

    //Requirement: The first constructor takes no parameters and it initializes every member to an initial value
    public Course() {
        this.cId = Integer.parseInt(null);
        this.cName = "";
        this.cInstructorName = "";
    }

    public Course(int cId, String cName, String cInstructorName) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }

    //getters and setters
    public int getcId() {
        return cId;
    }
    public void setcId(int cId) {
        this.cId = cId;
    }
    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    public String getcInstructorName() {
        return cInstructorName;
    }
    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }


}