package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Subject {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long subjectId;
    @NotEmpty
    private String subjectName;
    @NotEmpty
    private String credits;
    @ManyToMany
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")


    )
    private List<Student> enrolledStudents = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teacher teacher;


    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }


    public Subject(){

    }

    public Subject(Long subjectId, String subjectName, String credits, List<Student> enrolledStudents ) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
        this.enrolledStudents = enrolledStudents;

    }

    public Long getId() {
        return subjectId;
    }

    public void setId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);

    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;

    }
}
