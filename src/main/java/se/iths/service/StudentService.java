package se.iths.service;

import se.iths.ErrorMessage;
import se.iths.entity.Student;
import se.iths.exception.StudentNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional /* Denna annotationen talar om att klassen under interagerar med databasen.
 För att slippa göra en egen transaktion med begin() och close() etc. */
public class StudentService { /* Denna applikationen kontrollerar flödet av information som kommer in,
fungerar som en controller kan man säga*/
    @PersistenceContext
    EntityManager entityManager; // EntityManager sköter interaktionen med databasen som heter h2

    public void createStudent(Student student){
        entityManager.persist(student);
    }

    public Student updateStudent(Student student, Long id){
        Student foundStudent = entityManager.find(Student.class, id);
        if(foundStudent == null){

            throw new StudentNotFoundException(new ErrorMessage("404", "Item with ID " + id + " was not found in database.", "/api/v1/student/" + id));
        }

        return entityManager.merge(student);

    }

    public Student findStudentById(Long id){
    return entityManager.find(Student.class,id);

    }


    public List<Student> getAllStudents(){
        return entityManager.createQuery("SELECT s FROM Student s",Student.class).getResultList();
    }


    public Student deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
        return foundStudent;
    }

    public List<Student> getStudentsByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s from Student s where s.lastName =:lastname", Student.class);
        query.setParameter("lastname", lastName);
        return query.getResultList();
    }

}
