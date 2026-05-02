<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title><c:choose><c:when test="${formAction == 'update'}">Edit Student</c:when><c:otherwise>Add Student</c:otherwise></c:choose></title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <header>
        <h1><c:choose><c:when test="${formAction == 'update'}">Edit Student</c:when><c:otherwise>Add Student</c:otherwise></c:choose></h1>
    </header>

    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <form action="students" method="post" class="form-card">
        <input type="hidden" name="action" value="${formAction}" />
        <c:if test="${formAction == 'update'}">
            <input type="hidden" name="id" value="${student.id}" />
        </c:if>

        <label>Name</label>
        <input type="text" name="name" value="${student.name}" required />

        <label>Email</label>
        <input type="email" name="email" value="${student.email}" required />

        <label>Course</label>
        <input type="text" name="course" value="${student.course}" required />

        <div class="button-row">
            <button type="submit">Save</button>
            <a class="button secondary" href="students">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
