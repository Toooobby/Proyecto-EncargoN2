PROFESOR

  GET http://localhost:8092/api/teacher
•	→ Lista todos los profesores.

  GET http://localhost:8092/api/teacher/{id}
•	→ Obtiene un profesor por su ID.

  GET http://localhost:8092/api/teacher/with-course/{id}
•	→ Obtiene un profesor junto a su curso asociado.

  POST http://localhost:8092/api/teacher
•	→ Crea un nuevo profesor.

  DELETE http://localhost:8092/api/teacher/{id}
•	→ Elimina un profesor por ID.

  GET http://localhost:8092/api/teacher/buscar/nombre/{nombre}
•	→ Busca profesores por nombre.

  GET http://localhost:8092/api/teacher/buscar/course/{courseId}
•	→ Busca profesores por ID de curso.

  GET http://localhost:8092/api/teacher/buscar?nombre={nombre}&courseId={courseId}
•	→ Busca profesores por nombre y ID de curso.


ESTUDIANTE

  GET http://localhost:8090/api/student
•	→ Lista todos los estudiantes.

  GET http://localhost:8090/api/student/{id}
•	→ Obtiene un estudiante por su ID.

  GET http://localhost:8090/api/student/with-course/{id}
•	→ Obtiene un estudiante junto a su curso asociado.

  POST http://localhost:8090/api/student
•	→ Crea un nuevo estudiante.

  DELETE http://localhost:8090/api/student/{id}
•	→ Elimina un estudiante por ID.

  GET http://localhost:8090/api/student/buscar/nombre/{nombre}
•	→ Busca estudiantes por nombre.

  GET http://localhost:8090/api/student/buscar/course/{courseId}
•	→ Busca estudiantes por ID de curso.

  GET http://localhost:8090/api/student/buscar?nombre={nombre}&courseId={courseId}
•	→ Busca estudiantes por nombre y ID de curso.


CLASSROOM

  GET http://localhost:8093/api/classroom
•	→ Lista todas las salas de clases.

  GET http://localhost:8093/api/classroom/{id}
•	→ Obtiene una sala por su ID.

  GET http://localhost:8093/api/classroom/with-course/{id}
•	→ Obtiene una sala junto con el curso asociado.

  POST http://localhost:8093/api/classroom
•	→ Crea una nueva sala.

  DELETE http://localhost:8093/api/classroom/{id}
•	→ Elimina una sala por ID.

  GET http://localhost:8093/api/classroom/buscar/numero/{numero}
•	→ Busca salas por número.

  GET http://localhost:8093/api/classroom/buscar/course/{courseId}
•	→ Busca salas por ID de curso.

  GET http://localhost:8093/api/classroom/buscar?numero={numero}&courseId={courseId}
•	→ Busca salas por número y ID de curso.






SEDE

  GET http://localhost:8094/api/sede
•	Listar todas las sedes

  GET http://localhost:8094/api/sede/{id}
•	Obtener detalle de una sede por su ID

  GET http://localhost:8094/api/sede/with-classroom/{id}
•	Obtener una sede junto con la información del aula (classroom) asociada, por ID de sede

  POST http://localhost:8094/api/sede
•	Crear una nueva sede con los datos enviados en el cuerpo de la solicitud

  DELETE http://localhost:8094/api/sede/{id}
•	Eliminar una sede por su ID

  GET http://localhost:8094/api/sede/buscar/nombre/{nombre}
•	Buscar sedes por nombre
