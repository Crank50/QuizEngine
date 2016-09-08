<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QUIZ ANSWER</title>
    <link type="text/css" rel="stylesheet" href="/css/answer.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<h1>QUIZ ANSWER</h1>

<%--Link Awesome Fonts--%>
<nav>
<a target="_blank" href="http://stackoverflow.com/">
    <i class="icon-camera-retro"></i> Stack OverFlow
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

<%----------------------------------------------------------------%>
<section>
<c:if test="${not empty tracker.correct}">
    <div style="color:green;font-weight: bold;">
        <c:out value ="${Right}"/>
        <br>
    Answered Correctly:    <c:out value="${tracker.correct}" />
    </div>
</c:if>
    <br>
    <br>
<c:if test="${not empty tracker.incorrect}">
    <div style="color:red;font-weight: bold;">
        <c:out value ="${Wrong}"/>
        <br>
        Answered Incorrectly:    <c:out value="${tracker.incorrect}" />
    </div>
</c:if>
<br><br>

QUESTION: <c:out value="${quizQuestion.question}" /><br>
ANSWER:
    <c:if test="${quizQuestion.questionType == 'MULTIPLE_CHOICE'}">
        <c:out value="${quizQuestion.correctMultipleChoiceAnswer}" />
    </c:if>
    <c:if test="${quizQuestion.questionType == 'TRUE_FALSE'}">
        <c:out value="${quizQuestion.trueOrFalse}" />
    </c:if>
    <c:if test="${quizQuestion.questionType == 'CODE'}">
        <br>
        <c:choose>
            <c:when test="${quizQuestion.category == 'SQL'}">
                <pre><code class="sql">
            </c:when>
            <c:when test="${quizQuestion.category == 'HTML'}">
                <pre><code class="html">
            </c:when>
            <c:when test="${quizQuestion.category == 'CSS'}">
                <pre><se class="css">
            </c:when>
            <c:otherwise>
                <pre><code class="java">
            </c:otherwise>
        </c:choose>
        <c:forEach var="codeLine" items="${quizQuestion.codeLines}">
            <c:if test="${not empty codeLine && fn:length(codeLine)>0}">
                <c:out value="${codeLine}"/>
            </c:if>
            <c:if test="${not empty code}"/>
        </c:forEach>
        <c:forEach var="code" items="${code}">
           <c:if test="${not empty code}">
               <c:out value="${code}"/><br>
           </c:if>
       </c:forEach>
        </code></pre>
    </c:if>
<br><br>
<form action="/quiz/nextQuestion" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Next Question" />
</form>
</section>
</body>
</html>