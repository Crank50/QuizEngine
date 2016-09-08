<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QUIZ RESULTS</title>
    <link type="text/css" rel="stylesheet" href="/css/quizResults.css"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<h1>QUIZ RESULTS</h1>


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
<img src="https://harvardgazette.files.wordpress.com/2015/07/billmurray_image_605.jpg">


<%--<%- Tie into counter in the Results Entity&ndash;%&gt;--%>
<section>

    <c:if test="${not empty tracker.totalQ}">
    <div style="color:green;font-weight: bold;">
        Total Questions:    <c:out value="${tracker.totalQ}" />
    </div>
</c:if>

    <div style="color:green;font-weight: bold;">
        Total Questions True:    <c:out value="${currentQuizTotal}" />
    </div>

<c:if test="${not empty tracker.correct}">
    <div style="color:green;font-weight: bold;">
      Correct:  <td><c:out value="${tracker.correct}" /></td>
    </div>
</c:if>
<c:if test="${not empty tracker.incorrect}">
    <div style="color:red;font-weight: bold;">
  Incorrect:      <c:out value="${tracker.incorrect}" />
    </div>
</c:if>
<c:if test="${not empty tracker.name}">
    <div style="color:green;font-weight: bold;">
   Name:     <c:out value="${tracker.name}" />
    </div>
</c:if>
<c:if test="${not empty tracker.email}">
    <div style="color:green;font-weight: bold;">
    Email:    <c:out value="${tracker.email}" />
    </div>
</c:if>
    </section>

<br><br>

<br><br>
<aside>
    <a href="/">Quiz Me Again</a> | <a href="/admin/">admin</a>
</aside>
</body>
</html>