package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional /* Denna annotationen talar om att klassen under interagerar med databasen.
 För att slippa göra en egen transaktion med begin() och close() etc. */
public class StudentService { /* Denna applikationen kontrollerar flödet av information som kommer in,
fungerar som en controller kan man säga*/
    @PersistenceContext
    EntityManager entityManager; // EntityManager sköter interaktionen med databasen som heter h2

    public void createStudent(Student student){
        entityManager.persist(student);
    }

    public void updateStudent(Student student){

        entityManager.merge(student);
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



}
