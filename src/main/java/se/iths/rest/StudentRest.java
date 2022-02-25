package se.iths.rest;


import se.iths.ErrorMessage;
import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON) // Våran REST applikation kräver och tar emot i JSON format.
@Produces(MediaType.APPLICATION_JSON) // Våran REST applikation skickar ut svaret i JSON format.
public class StudentRest {

    @Inject // Injicerar så att man kan använda sig utav StudentService klassen i StudentRest
    StudentService studentService;

    @Path("create")
    @POST // Denna annotationen är tillför CREATE i REST
    public Response createStudent(Student student){
        studentService.createStudent(student);
        return Response.ok(student).build();

    }

    @Path("update")
    @PUT
    public Response updateStudent(Student student){
        studentService.updateStudent(student);
        String responseString = "Studenten har uppdaterats ";
        return Response.ok(responseString).type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id){
        Optional<Student> foundStudent = studentService.findStudentById(id);

        var student = foundStudent.orElseThrow(
                () -> new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorMessage("404", "Item with ID " + id + " was not found in database.", "/api/v1/student/" + id))
                        .type(MediaType.APPLICATION_JSON_TYPE)
                        .build()));


        return Response.ok(student).build();
    }

    @Path("getall")
    @GET
    public List<Student> getAllStudents(){

        return studentService.getAllStudents();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        studentService.deleteStudent(id);
        String responseString = "Studenten som du uppgav har tagits bort ifrån tabellen ";
        return Response.ok(responseString).type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    @Path("/filter")
    @GET
    public Response getAllStudentsByLastName(@QueryParam("lastName") String lastName){
        List<Student> foundStudents = studentService.getStudentsByLastName(lastName);
        return Response.ok(foundStudents).build();

    }


}
