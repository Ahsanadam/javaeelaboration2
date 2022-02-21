package se.iths.rest;


import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON) // Våran REST applikation kräver och tar emot i JSON format.
@Produces(MediaType.APPLICATION_JSON) // Våran REST applikation skickar ut svaret i JSON format.
public class StudentRest {

    @Inject // Injicerar så att man kan använda sig utav StudentService klassen i StudentRest
    StudentService studentService;

    @Path("new")
    @POST // Denna annotationen är tillför CREATE i REST
    public Response createStudent(Student student){
        studentService.createStudent(student);
        return Response.ok(student).build();

    }

    @Path("update")
    @PUT
    public Response updateStudent(Student student){
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Student getStudent(@PathParam("id") Long id){

        return studentService.findStudentById(id);
    }

    @Path("getall")
    @GET
    public List<Student> getAllStudents(){

        return studentService.getAllStudents();
    }

    @Path("delete/{id}")
    @DELETE
    public Student deleteStudent(@PathParam("id") Long id){

        return studentService.deleteStudent(id);
    }



}
