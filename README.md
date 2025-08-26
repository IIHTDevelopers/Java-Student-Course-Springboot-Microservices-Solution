# Student–Course–Eureka Microservices (Spring Boot 3 / Java 17)

Services:
- **eureka-service** (port 8761)
- **student-service** (port 8081)
- **course-service** (port 8082)

## Run order
1. eureka-service
2. student-service
3. course-service

## Build & run
mvn clean package -DskipTests
java -jar target/*.jar

## Smoke test
1) Create a student (8081):
POST /api/students
{ "name":"Aditi","email":"aditi@example.com","program":"CSE" }

2) Create a course linked to that student (8082):
POST /api/courses
{ "name":"Intro to CS","code":"CS101","studentId":1 }

3) Fetch course with student details (8082):
GET /api/courses/1
