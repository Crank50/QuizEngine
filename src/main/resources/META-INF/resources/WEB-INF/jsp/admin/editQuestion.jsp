<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/editQuestion.css"/>

    <title>Edit Question</title>
    <script type="text/javascript">
        function changeTypeDisplay () {
            var e = document.getElementById("questionType");
            var optionValue = e.options[e.selectedIndex].value;
            if(optionValue == 'MULTIPLE_CHOICE') {
                document.getElementById("type1").style.display = 'block';
                document.getElementById("type2").style.display = 'none';
                document.getElementById("type3").style.display = 'none';
            } else if(optionValue == 'TRUE_FALSE') {
                document.getElementById("type1").style.display = 'none';
                document.getElementById("type2").style.display = 'block';
                document.getElementById("type3").style.display = 'none';
            } else if(optionValue == 'CODE') {
                document.getElementById("type1").style.display = 'none';
                document.getElementById("type2").style.display = 'none';
                document.getElementById("type3").style.display = 'block';
            }
        }
    </script>
</head>
<body>
<h1>Edit Question</h1>
<form:form id="editQuestionForm" commandName="quizQuestion" method="POST" action="/admin/saveEditedQuestion">
    <form:hidden path="id" id="id" />
    <form:label path="category">Question Category:</form:label>
    <form:select path="category" id="category">
        <form:options items="${Category}" />
    </form:select>
    <br>
    <form:label path="questionType">Question Type:</form:label>
    <form:select path="questionType" id="questionType" onchange="changeTypeDisplay();">
        <form:options items="${QuestionType}" />
    </form:select>
    <br>
    <form:label path="difficulty">Question Difficulty:</form:label>
    <form:select path="difficulty" id="difficulty">
        <form:options items="${Difficulty}" />
    </form:select>
    <br>
    <form:label path="question">Question:</form:label>
    <form:input path="question" id="question" maxlength="255" size="100" />
    <br>
    <div id="type1">
        <form:label path="correctMultipleChoiceAnswer">Correct Multiple Choice Answer:</form:label>
        <form:input path="correctMultipleChoiceAnswer" id="correctMultipleChoiceAnswer" maxlength="255" size="100" />
        <br>
        <form:label path="wrongMultipleChoiceAnswer1">First Wrong Multiple Choice Answer:</form:label>
        <form:input path="wrongMultipleChoiceAnswer1" id="wrongMultipleChoiceAnswer1" maxlength="255" size="100" />
        <br>
        <form:label path="wrongMultipleChoiceAnswer2">Second Wrong Multiple Choice Answer:</form:label>
        <form:input path="wrongMultipleChoiceAnswer2" id="wrongMultipleChoiceAnswer2" maxlength="255" size="100" />
        <br>
        <form:label path="wrongMultipleChoiceAnswer3">Third Wrong Multiple Choice Answer:</form:label>
        <form:input path="wrongMultipleChoiceAnswer3" id="wrongMultipleChoiceAnswer3" maxlength="255" size="100" />
    </div>
    <div id="type2" style="display:none">
        <form:label path="trueOrFalse">True or False:</form:label>
        <form:radiobutton path="trueOrFalse" value="true"/>True
        <form:radiobutton path="trueOrFalse" value="false"/>False
    </div>
    <div id="type3" style="display:none">
        <form:label path="codeLines">codeLines:</form:label>
        <br>
        Line 01: <form:input path="codeLines[0]" maxlength="255" size="100" />
        <br>
        Line 02: <form:input path="codeLines[1]" maxlength="255" size="100" />
        <br>
        Line 03: <form:input path="codeLines[2]" maxlength="255" size="100" />
        <br>
        Line 04: <form:input path="codeLines[3]" maxlength="255" size="100" />
        <br>
        Line 05: <form:input path="codeLines[4]" maxlength="255" size="100" />
        <br>
        Line 06: <form:input path="codeLines[5]" maxlength="255" size="100" />
        <br>
        Line 07: <form:input path="codeLines[6]" maxlength="255" size="100" />
        <br>
        Line 08: <form:input path="codeLines[7]" maxlength="255" size="100" />
        <br>
        Line 09: <form:input path="codeLines[8]" maxlength="255" size="100" />
        <br>
        Line 10: <form:input path="codeLines[9]" maxlength="255" size="100" />
        <br>
        Line 11: <form:input path="codeLines[10]" maxlength="255" size="100" />
        <br>
        Line 12: <form:input path="codeLines[11]" maxlength="255" size="100" />
        <br>
        Line 13: <form:input path="codeLines[12]" maxlength="255" size="100" />
        <br>
        Line 14: <form:input path="codeLines[13]" maxlength="255" size="100" />
        <br>
        Line 15: <form:input path="codeLines[14]" maxlength="255" size="100" />
        <br>
        Line 16: <form:input path="codeLines[15]" maxlength="255" size="100" />
        <br>
        Line 17: <form:input path="codeLines[16]" maxlength="255" size="100" />
        <br>
        Line 18: <form:input path="codeLines[17]" maxlength="255" size="100" />
        <br>
        Line 19: <form:input path="codeLines[18]" maxlength="255" size="100" />
        <br>
        Line 20: <form:input path="codeLines[19]" maxlength="255" size="100" />
    </div>
    <br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Save Changes"><input type="button" value="Cancel" onclick="location.href='/admin/';">
</form:form>
</body>
<script type="text/javascript">
    changeTypeDisplay();
</script>
</html>