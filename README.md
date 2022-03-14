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

ENDPOINT for creating a new teacher : POST http://localhost:8080/student-management-system/api/v1/teachers/
ENDPOINT for getting all the teachers: GET http://localhost:8080/student-management-system/api/v1/teachers/
ENDPOINT for getting teacher by id: GET http://localhost:8080/student-management-system/api/v1/teachers/{id}
ENDPOINT for updating a current teacher: PUT http://localhost:8080/student-management-system/api/v1/teachers/{id}
ENDPOINT for deleting a current teacher: DELETE http://localhost:8080/student-management-system/api/v1/teachers/{id}
ENDPOINT for getting teacher by lastname: GET http://localhost:8080/student-management-system/api/v1/teachers/filter/lastname?

Description about JSON format:
{
"email": "",
"firstName": "",
"id": ,
"lastName": ""
}

ENDPOINT for creating a new subject: POST http://localhost:8080/student-management-system/api/v1/subjects/
ENDPOINT for getting all the subjects: GET http://localhost:8080/student-management-system/api/v1/subjects/
ENDPOINT for getting subject by id: GET http://localhost:8080/student-management-system/api/v1/subjects/{id}
ENDPOINT for updating a current subject: PUT http://localhost:8080/student-management-system/api/v1/subjects/{id}
ENDPOINT for deleting a current subject: DELETE http://localhost:8080/student-management-system/api/v1/subjects/{id}
ENDPOINT for getting subject by subject name : GET http://localhost:8080/student-management-system/api/v1/subjects/filter/subjectname?

Description about JSON format:
{
"credits": "",
"id": ,
"subjectName": ""
}

ENDPOINT for enrolling student to subject : PUT http://localhost:8080/student-management-system/api/v1/subjects/{id}/students/{id}
ENDPOINT for assigning a teacher to a subject: PUT http://localhost:8080/student-management-system/api/v1/subjects/{id}/teachers/{id}
ENDPOINT for complete information about a subject: GET http://localhost:8080/student-management-system/api/v1/subjects/



