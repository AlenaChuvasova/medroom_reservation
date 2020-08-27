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
        <th colspan="2">    CANCEL   </th>
    </tr>
    </thead>
    <c:forEach items="${reservations}" var="reservations">
        <tbody>
        <tr>
            <td>${reservations.id}</td>
            <td>${reservations.fullName}</td>
            <td>${reservations.manipulationName}</td>
            <td>${reservations.description}</td>
            <td>${reservations.startTime}</td>
            <td>${reservations.endTime}</td>
            <td>${reservations.isActive}</td>
            <td>${reservations.roomNumber}</td>
            <td><form action="${pageContext.request.contextPath}/cancel" method="post">
            <td>
                <button onclick="location.href='/cancel'">cancel</button>
            <input type="hidden" name="id" value="${reservations.id}">
            </td>
            </form></td>
                <%-- --%>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>

<%--<td><button onclick="location.href='/cancel/${reservs.id}'">cancel</button></td>--%>
