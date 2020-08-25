<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All reservation</title>
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tamma+2:wght@500;600;700&display=swap" rel="stylesheet">
    <style>
        <%@include file="/styles/style.css"%>
    </style>
</head>
<body>
<p>
    <a href='<c:url value="/"/>'><- main page</a>
</p>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>FULL NAME</th>
        <th>MANIPULATION</th>
        <th>DESCRIPTION</th>
        <th>START TIME</th>
        <th>END TIME</th>
        <th>IS ACTIVE</th>
        <th>ROOM NUMBER</th>
    </tr>
    </thead>
    <c:forEach items="${reservs}" var="reservs">
        <tbody>
        <tr>
            <td>${reservs.id}</td>
            <td>${reservs.fullName}</td>
            <td>${reservs.manipulationName}</td>
            <td>${reservs.description}</td>
            <td>${reservs.startTime}</td>
            <td>${reservs.endTime}</td>
            <td>${reservs.isActive}</td>
            <td>${reservs.roomNumber}</td>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>
