ENDPOINT for creating a new student: POST http://localhost:8080/student-management-system/api/v1/students/
ENDPOINT for getting all the students: GET http://localhost:8080/student-management-system/api/v1/students/
ENDPOINT for getting student by id: GET http://localhost:8080/student-management-system/api/v1/students/{id}
ENDPOINT for updating a current student: PUT http://localhost:8080/student-management-system/api/v1/students/{id}
ENDPOINT for deleting a current student: DELETE http://localhost:8080/student-management-system/api/v1/students/{id}
ENDPOINT for getting student by lastname: GET http://localhost:8080/student-management-system/api/v1/students/filter/lastname?

Description about JSON format:
{
"email": "",
"firstName": "",
"id": ,
"lastName": "",
"phoneNumber": ""
}