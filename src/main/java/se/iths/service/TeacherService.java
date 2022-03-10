package se.iths.service;

import se.iths.ErrorMessage;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.StudentNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional /* Denna annotationen talar om att klassen under interagerar med databasen.
 För att slippa göra en egen transaktion med begin() och close() etc. */
public class TeacherService { /* Denna applikationen kontrollerar flödet av information som kommer in,
fungerar som en controller kan man säga*/
    @PersistenceContext
    EntityManager entityManager; // EntityManager sköter interaktionen med databasen som heter h2

    public void createTeacher(Teacher teacher){
        entityManager.persist(teacher);
    }


    public Teacher updateTeacher(Teacher teacher, Long id){
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        if(foundTeacher == null){

            throw new StudentNotFoundException(new ErrorMessage("404", "Teacher with ID " + id + " was not found in database.", "/api/v1/teachers/" + id));
        }

        return entityManager.merge(teacher);

    }

    public Teacher findTeacherById(Long id){
        return entityManager.find(Teacher.class,id);

    }


    public List<Teacher> getAllTeachers(){
        return entityManager.createQuery("SELECT t FROM Teacher t",Teacher.class).getResultList();
    }


    public Teacher deleteTeacher(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
        return foundTeacher;
    }

    public List<Teacher> getTeacherByLastname(String lastName) {
        TypedQuery<Teacher> query = entityManager.createQuery("SELECT t from Teacher t where t.lastName =:lastname", Teacher.class);
        query.setParameter("lastname", lastName);
        return query.getResultList();
    }

}
