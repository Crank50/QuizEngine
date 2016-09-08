<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stats Board</title>
    <link type="text/css" rel="stylesheet" href="/css/dashboard.css"/>
</head>
<body>
<h1>Stats Board</h1>
    <div style="color:green;font-weight: bold;">
        Total Questions:    <c:out value="${count}"/>
    </div>


<br><br>

<br><br>
<a href="/">Quiz Me Again</a> | <a href="/admin/">admin</a>
</body>
</html>