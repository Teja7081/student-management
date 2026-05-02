# Student Management System

A simple Java web application using Servlets, JSP, and JDBC for managing student data.

## Features
- Add student
- View student list
- Edit student details
- Delete student
- Input validation and basic error handling
- Uses MySQL and prepared statements for security

## Project Structure
- `pom.xml` - Maven build file
- `src/main/java` - Java source files
- `src/main/webapp` - JSP views and web resources
- `src/main/webapp/WEB-INF/web.xml` - Servlet mapping
- `sql/create_student_db.sql` - Database creation script

## Database Setup
1. Start MySQL server.
2. Create database and table using `sql/create_student_db.sql`.
3. Update MySQL credentials in `src/main/java/com/example/student/util/DatabaseUtil.java` if needed.

SQL script:
```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL
);
```

## Build and Deploy
1. Open a terminal at the project root.
2. Build the WAR file:
   ```powershell
   mvn clean package
   ```
3. Copy `target/student-management.war` to the Apache Tomcat `webapps` folder.
4. Start Tomcat and wait for deployment.
5. Access the app at:
   ```
   http://localhost:8080/student-management/
   ```

## Notes
- Ensure Tomcat is running on port 8080.
- If using a different port, update the URL accordingly.
- The app uses `studentdb` database by default.
