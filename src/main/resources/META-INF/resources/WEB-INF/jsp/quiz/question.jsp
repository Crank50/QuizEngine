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
    <c:if test="${quizQuestion.questionType == 'CODE'}">
    Line 01: <input path="codeLines[0]" maxlength="255" size="100" />
    <br>
    Line 02: <input path="codeLines[1]" maxlength="255" size="100" />
    <br>
    Line 03: <input path="codeLines[2]" maxlength="255" size="100" />
    <br>
    Line 04: <input path="codeLines[3]" maxlength="255" size="100" />
    <br>
    Line 05: <input path="codeLines[4]" maxlength="255" size="100" />
    <br>
    Line 06: <input path="codeLines[5]" maxlength="255" size="100" />
    <br>
    Line 07: <input path="codeLines[6]" maxlength="255" size="100" />
    <br>
    Line 08: <input path="codeLines[7]" maxlength="255" size="100" />
    <br>
    Line 09: <input path="codeLines[8]" maxlength="255" size="100" />
    <br>
    Line 10: <input path="codeLines[9]" maxlength="255" size="100" />
    <br>
    Line 11: <input path="codeLines[10]" maxlength="255" size="100" />
    <br>
    Line 12: <input path="codeLines[11]" maxlength="255" size="100" />
    <br>
    Line 13: <input path="codeLines[12]" maxlength="255" size="100" />
    <br>
    Line 14: <input path="codeLines[13]" maxlength="255" size="100" />
    <br>
    Line 15: <input path="codeLines[14]" maxlength="255" size="100" />
    <br>
    Line 16: <input path="codeLines[15]" maxlength="255" size="100" />
    <br>
    Line 17: <input path="codeLines[16]" maxlength="255" size="100" />
    <br>
    Line 18: <input path="codeLines[17]" maxlength="255" size="100" />
    <br>
    Line 19: <input path="codeLines[18]" maxlength="255" size="100" />
    <br>
    Line 20: <input path="codeLines[19]" maxlength="255" size="100" />
    <br>
            </c:if>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<br><br><input type="submit" name="Show Me">
</section>
</form>

<br>
<a href="/">Restart Quiz</a>

</body>
</html>