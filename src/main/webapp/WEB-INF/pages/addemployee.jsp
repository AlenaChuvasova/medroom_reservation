<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.chuvasova.medroom.model.Position" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new employee</title>
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tamma+2:wght@500;600;700&display=swap" rel="stylesheet">
    <style>
        <%@include file="/styles/style.css"%>
    </style>
</head>
<body>
<div class="add_empl">
    Add a new employee:
    <form id="addemployee" action="${pageContext.request.contextPath}/addemployee" method="post">
        <label for="name">Name: </label><br>
        <input type="text" name="name" placeholder="Input name" id="name"><br>

        <label for="surname">Surname: </label><br>
        <input type="text" name="surname" placeholder="Input surname" id="surname"><br>

        <label for="position">Choose appropriate position:</label>
        <select name="position" id="position">
            <c:forEach items="<%=Position.values()%>" var="entry">
                <option>${entry.name}</option>
            </c:forEach>
        </select>
        <br/>
        <button type="submit">CREATE</button>
        <p>
            <a href='<c:url value="/"/>'><- main page</a>
        </p>
    </form>
</div>
</body>
</html>
