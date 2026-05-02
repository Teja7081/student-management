<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Student Management</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <header>
        <h1>Student Management</h1>
        <p>Manage student records securely.</p>
    </header>

    <div class="toolbar">
        <a class="button" href="students?action=new">Add New Student</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentList}">
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.email}"/></td>
                <td><c:out value="${student.course}"/></td>
                <td>
                    <a class="action" href="students?action=edit&id=${student.id}">Edit</a>
                    <a class="action delete" href="students?action=delete&id=${student.id}" onclick="return confirm('Delete this student?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
