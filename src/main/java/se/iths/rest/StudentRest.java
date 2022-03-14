package se.iths.rest;

import se.iths.ErrorMessage;
import se.iths.rest.Message;
import se.iths.entity.Student;
import se.iths.exception.StudentDataInvalidException;
import se.iths.exception.StudentNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON) // Våran REST applikation kräver och tar emot i JSON format.
@Produces(MediaType.APPLICATION_JSON) // Våran REST applikation skickar ut svaret i JSON format.
public class StudentRest {

    @Inject // Injicerar så att man kan använda sig utav StudentService klassen i StudentRest
    StudentService studentService;

    @Path("")
    @POST // Denna annotationen är tillför CREATE i REST
    public Response createStudent(Student student){

        try {
            studentService.createStudent(student);
        } catch (Exception e) {
            throw new StudentDataInvalidException();
        }

        return Response.ok(student).build();

    }

    @Path("{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student){

            studentService.updateStudent(student,id);

            return Response.ok(student).build();

    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id){
        Student foundStudent = null;
        try {
            foundStudent = studentService.findStudentById(id);
        } catch (Exception e) {
           throw new StudentNotFoundException(new ErrorMessage("404", "Student with ID " + id + " was not found in database.", "/api/v1/student/" + id));
        }
        return Response.ok(foundStudent).build();
    }


    @Path("")
    @GET
    public List<Student> getAllStudents(){ //Fixa Response här istället

        return studentService.getAllStudents(); /* Behöver inte implementera någon exception, för att den
         returnerar bara en tom lista ifall man inte får ut något */
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        studentService.deleteStudent(id); /* Här behöver man inte lägga till någon exception eftersom att
        man ändå får fram rätt resultat oavsett vad. Målet är ju att studenten ska vara borttagen*/
        String responseString = "Studenten har tagits bort";

        return Response.ok(new Message(responseString)).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("filter/lastname")
    @GET
    public Response getAllStudentsByLastName(@QueryParam("lastName") String lastName){
        List<Student> foundStudents = studentService.getStudentsByLastName(lastName); /* Behöver inte implementera någon exception, för att den
         returnerar bara en tom lista ifall man inte får ut något */
        return Response.ok(foundStudents).build();

    }

}
