package se.iths.rest;

import se.iths.ErrorMessage;
import se.iths.entity.Subject;
import se.iths.rest.Message;
import se.iths.entity.Teacher;
import se.iths.exception.StudentDataInvalidException;
import se.iths.exception.StudentNotFoundException;
import se.iths.service.SubjectService;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON) // Våran REST applikation kräver och tar emot i JSON format.
@Produces(MediaType.APPLICATION_JSON) // Våran REST applikation skickar ut svaret i JSON format.
public class TeacherRest {

    @Inject // Injicerar så att man kan använda sig utav TeacherService klassen i TeacherRest
    TeacherService teacherService;

    @Path("")
    @POST // Denna annotationen är tillför CREATE i REST
    public Response createTeacher(Teacher teacher){

        try {
            teacherService.createTeacher(teacher);
        } catch (Exception e) {
            throw new StudentDataInvalidException();
        }

        return Response.ok(teacher).build();

    }

    @Path("{id}")
    @PUT
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher){

        teacherService.updateTeacher(teacher,id);

        return Response.ok(teacher).build();

    }

    @Path("{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id){
        Teacher foundTeacher = null;
        try {
            foundTeacher = teacherService.findTeacherById(id);
        } catch (Exception e) {
            throw new StudentNotFoundException(new ErrorMessage("404", "Teacher with ID " + id + " was not found in database.", "/api/v1/teachers/" + id));
        }
        return Response.ok(foundTeacher).build();
    }


    @Path("")
    @GET
    public List<Teacher> getAllTeachers(){ //Fixa Response här istället

        return teacherService.getAllTeachers(); /* Behöver inte implementera någon exception, för att den
         returnerar bara en tom lista ifall man inte får ut något */
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id){
        teacherService.deleteTeacher(id); /* Här behöver man inte lägga till någon exception eftersom att
        man ändå får fram rätt resultat oavsett vad. Målet är ju att studenten ska vara borttagen*/
        String responseString = "Läraren har tagits bort";

        return Response.ok(new Message(responseString)).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("filter/lastname")
    @GET
    public Response getAllTeachersByLastName(@QueryParam("lastName") String lastName){
        List<Teacher> foundTeachers = teacherService.getTeacherByLastname(lastName); /* Behöver inte implementera någon exception, för att den
         returnerar bara en tom lista ifall man inte får ut något */
        return Response.ok(foundTeachers).build();

    }


}
