<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View All Questions</title>
    <link type="text/css" rel="stylesheet" href="/css/viewAllQuestions.css"/>
</head>
<body>
<h1>View All Questions</h1>
<a href="/">HOME</a>
<a href="/admin/addQuestion">Add New Question</a>
<br><br>
<table>
    <tr>
        <th> Question Id </th>
        <th> | </th>
        <th> Question Category </th>
        <th> | </th>
        <th> Question Type </th>
        <th> | </th>
        <th> Question Difficulty </th>
        <th> | </th>
        <th> Question </th>
        <th> | </th>
        <th> Answer </th>
    </tr>
    <c:forEach var="quizQuestion" items="${quizQuestions}">
        <tr>
            <td><a href="/admin/viewQuestion?id=${quizQuestion.id}"><c:out value="${quizQuestion.id}" /></a></td>
            <th> | </th>
            <td><c:out value="${quizQuestion.category}" /></td>
            <th> | </th>
            <td><c:out value="${quizQuestion.questionType}" /></td>
            <th> | </th>
            <td><c:out value="${quizQuestion.difficulty}" /></td>
            <th> | </th>
            <td><c:out value="${quizQuestion.question}" /></td>
            <th> | </th>
            <td>
                <c:if test="${quizQuestion.questionType == 'MULTIPLE_CHOICE'}">
                    <c:out value="${quizQuestion.correctMultipleChoiceAnswer}" />
                </c:if>
                <c:if test="${quizQuestion.questionType == 'TRUE_FALSE'}">
                    <c:out value="${quizQuestion.trueOrFalse}" />
                </c:if>
                <c:if test="${quizQuestion.questionType == 'CODE'}">
                    <c:forEach var="codeLine" items="${quizQuestion.codeLines}">
                        <c:if test="${not empty codeLine}">
                            <c:out value="${codeLine}"/><br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </td>
            <td><a href="/admin/deleteQuestion?id=${quizQuestion.id}">Delete:<c:out value="${quizQuestion.id}" /></a></td>

        </tr>
    </c:forEach>

</table>
<br><br>
<a href="/">HOME</a>
||
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/admin/addQuestion">Add New Question</a>
||
<a href="/restData/getAllQuestions">Bulk Download Questions</a>
||
<a href="/admin/uploadQuestions">Bulk Upload Questions</a>
||
<td><a href="/admin/deleteAllQuestion?id=${quizQuestions}" onclick="return confirm('Really?')">Delete All</a></td>


</body>
</html>