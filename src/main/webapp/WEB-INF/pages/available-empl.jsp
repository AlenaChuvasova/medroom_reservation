<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tamma+2:wght@500;600;700&display=swap" rel="stylesheet">
    <style>
        <%@include file="/styles/style.css"%>
    </style>
</head>
<body>
<h2>All available personnel in hospital</h2>
<p>
    <a href='<c:url value="/"/>'><- main page</a>
</p>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>POSITION</th>
        <th>IS_FREE</th>
    </tr>
    </thead>
    <c:forEach items="${employees}" var="employee">
        <tbody>
        <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.name}</td>
            <td>${employee.surname}</td>
            <td>${employee.position}</td>
            <td>${employee.isFree}</td>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>
