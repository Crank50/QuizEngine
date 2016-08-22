<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QUIZ QUESTION</title>
    <link type="text/css" rel="stylesheet" href="/css/question.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<h1>QUIZ QUESTION</h1>
<%--Link Awesome Fonts--%>
<nav>
<a target="_blank" href="http://stackoverflow.com/">
    <i class="icon-camera-retro" ></i> Stack OverFlow
</a>
<br>
<a target="_blank" href="http://lmgtfy.com/">
    <i class="fa fa-pied-piper" aria-hidden="true"></i>Use Google
</a>
<br>
<a target="_blank" href="http://tv.giphy.com/?username=alastairgray">
    <i class="fa fa-gitlab" aria-hidden="true"></i> Distract Yourself
</a>
</nav>
<br><br>

<%----------------------------------------------------------------%>
<section>
QUESTION: <c:out value="${quizQuestion.question}" />
<br><br><br>
<form name="questionForm" method="POST" action="/quiz/questionAnswer">
<c:if test="${quizQuestion.questionType == 'MULTIPLE_CHOICE'}">
    <input type="radio" name="multiAnswer" value="yes"> :<c:out value="${quizQuestion.correctMultipleChoiceAnswer}" /><br>
    <input type="radio" name="multiAnswer" value="no"> :<c:out value="${quizQuestion.wrongMultipleChoiceAnswer1}" /><br>
    <input type="radio" name="multiAnswer" value="no"> :<c:out value="${quizQuestion.wrongMultipleChoiceAnswer2}" /><br>
    <input type="radio" name="multiAnswer" value="no"> :<c:out value="${quizQuestion.wrongMultipleChoiceAnswer3}" /><br>
</c:if>
<c:if test="${quizQuestion.questionType == 'TRUE_FALSE'}">
    True: <input type="radio" name="trueFalseAnswer" value="true"><br>
    False: <input type="radio" name="trueFalseAnswer" value="false">
</c:if>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<br><br><input type="submit" name="Show Me">
    </section>
</form>
</body>
</html>