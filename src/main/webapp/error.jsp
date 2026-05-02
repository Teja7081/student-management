<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Error</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <header>
        <h1>Application Error</h1>
    </header>
    <div class="error">
        <p>An unexpected error occurred.</p>
        <p>${requestScope['javax.servlet.error.message']}</p>
    </div>
    <a class="button" href="students">Return to list</a>
</div>
</body>
</html>
