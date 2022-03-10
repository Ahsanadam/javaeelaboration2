package se.iths.rest;

import se.iths.ErrorMessage;
import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.rest.Message;
import se.iths.entity.Subject;
import se.iths.exception.StudentDataInvalidException;
import se.iths.exception.StudentNotFoundException;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON) // Våran REST applikation kräver och tar emot i JSON format.
@Produces(MediaType.APPLICATION_JSON) // Våran REST applikation skickar ut svaret i JSON format.
public class SubjectRest {

    @Inject // Injicerar så att man kan använda sig utav StudentService klassen i StudentRest
    SubjectService subjectService;

    @Inject
    StudentService studentService;

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST // Denna annotationen är tillför CREATE i REST
    public Response createSubject(Subject subject){

        try {
            subjectService.createSubject(subject);
        } catch (Exception e) {
            throw new StudentDataInvalidException();
        }

        return Response.ok(subject).build();

    }


    @Path("{id}")
    @PUT
    public Response updateSubject(@PathParam("id") Long id, Subject subject){

        subjectService.updateSubject(subject,id);

        return Response.ok(subject).build();

    }

    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id){
        Subject foundSubject = null;
        try {
            foundSubject = subjectService.findSubjectById(id);
        } catch (Exception e) {
            throw new StudentNotFoundException(new ErrorMessage("404", "Subject with ID " + id + " was not found in database.", "/api/v1/student/" + id));
        }
        return Response.ok(foundSubject).build();
    }


    @Path("")
    @GET
    public List<Subject> getAllSubjects(){ //Fixa Response här istället

        return subjectService.getAllSubjects(); /* Behöver inte implementera någon exception, för att den
         returnerar bara en tom lista ifall man inte får ut något */
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id){
        subjectService.deleteSubject(id); /* Här behöver man inte lägga till någon exception eftersom att
        man ändå får fram rätt resultat oavsett vad. Målet är ju att studenten ska vara borttagen*/
        String responseString = "Ämnet har tagits bort";

        return Response.ok(new Message(responseString)).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("filter/subjectname")
    @GET
    public Response getAllSubjectsBySubjectName(@QueryParam("subjectName") String subjectName){
        List<Subject> foundSubjects = subjectService.getSubjectBySubjectName(subjectName); /* Behöver inte implementera någon exception, för att den
         returnerar bara en tom lista ifall man inte får ut något */
        return Response.ok(foundSubjects).build();

    }

    @Path("/{subjectId}/students/{studentId}")
    @PUT
    public Response enrollStudentToSubject(@PathParam("subjectId") Long subjectId, @PathParam("studentId") Long studentId){

        Subject subject = subjectService.findSubjectById(subjectId);
        Student student = studentService.findStudentById(studentId);
        subject.enrollStudent(student);
        subjectService.updateSubject(subject,subjectId);

        return Response.ok().build();
    }

    @Path("/{subjectId}/teachers/{teacherId}")
    @PUT
    public Response assignTeacherToSubject(@PathParam("subjectId") Long subjectId, @PathParam("teacherId") Long teacherId){

        Subject subject = subjectService.findSubjectById(subjectId);
        Teacher teacher = teacherService.findTeacherById(teacherId);
        subject.assignTeacher(teacher);
        subjectService.updateSubject(subject,subjectId);
        return Response.ok().build();
    }

}
