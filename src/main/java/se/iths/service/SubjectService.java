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
public class SubjectService { /* Denna applikationen kontrollerar flödet av information som kommer in,
fungerar som en controller kan man säga*/
    @PersistenceContext
    EntityManager entityManager;// EntityManager sköter interaktionen med databasen som heter h2
    Teacher teacher;

    public void createSubject(Subject subject){

        entityManager.persist(subject);
    }


    public Subject updateSubject(Subject subject, Long id){

        Subject foundSubject = entityManager.merge(subject);
        if(foundSubject == null){

            throw new StudentNotFoundException(new ErrorMessage("404", "Subject with ID " + id + " was not found in database.", "/api/v1/subjects/" + id));
        }

        return foundSubject;

    }

    public Subject findSubjectById(Long id){
        return entityManager.find(Subject.class,id);

    }


    public List<Subject> getAllSubjects(){
        return entityManager.createQuery("SELECT s FROM Subject s",Subject.class).getResultList();
    }


    public Subject deleteSubject(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
        return foundSubject;
    }

    public List<Subject> getSubjectBySubjectName(String subjectName) {
        TypedQuery<Subject> query = entityManager.createQuery("SELECT s from Subject s where s.subjectName =:subjectname", Subject.class);
        query.setParameter("subjectname", subjectName);
        return query.getResultList();
    }

}
