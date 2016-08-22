<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QUIZ ENGINE</title>
    <link type="text/css" rel="stylesheet" href="/css/index.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

</head>
<body>

    <h1>QUIZ ENGINE</h1>
    <%--Link Awesome Fonts--%>
    <nav>
    <a target="_blank" href="http://stackoverflow.com/" style=" position: relative; left: 150px;">
        <i class="icon-camera-retro"></i> Stack OverFlow
    </a>

    <a  target="_blank"href="http://lmgtfy.com/" style=" position: relative; left: 450px;">
        <i  class="fa fa-pied-piper" aria-hidden="true"></i>Use Google
    </a>

    <a target="_blank" href="http://tv.giphy.com/?username=alastairgray" style=" position: relative; left: 750px;">
        <i class="fa fa-gitlab" aria-hidden="true"  ></i> Distract Yourself
    </a>
        </nav>

    <%----------------------------------------------------------------%>
    <section>
    <form name="quizStartForm" method="POST" action="/quiz/startQuiz">
        Name: <input type="text" name="name" /><br>
        Email: <input type="text" name="email" /><br><br>
        Quiz Category: <select name="category">
            <c:forEach var="category" items="${categories}">
                <option value="<c:out value="${category}"/>"><c:out value="${category}"/></option>
            </c:forEach>
        </select><br><br>
        Quiz Type: <select name="quizType">
            <c:forEach var="QuizType" items="${QuizTypes}">
                <option value="<c:out value="${QuizType}"/>"><c:out value="${QuizType}"/></option>
            </c:forEach>
        </select><br><br>
        Question Type: <select name="questionType">
            <c:forEach var="questionType" items="${questionTypes}">
                <option value="<c:out value="${questionType}"/>"><c:out value="${questionType}"/></option>
            </c:forEach>
        </select><br><br>
        Question Difficulty: <select name="difficulty">
            <c:forEach var="difficulty" items="${difficulties}">
                <option value="<c:out value="${difficulty}"/>"><c:out value="${difficulty}"/></option>
            </c:forEach>
        </select><br><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="Let the Quiz Begin">
    </form>
        </section>
    <br><br><br><br>

    <aside>
    <a href="/admin/">admin </a> |
    <a href="/stats/"> stats </a>
    </aside>

</body>
</html>

