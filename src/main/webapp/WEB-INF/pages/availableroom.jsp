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
<h2>All available rooms in hospital</h2>
<p>
    <a href='<c:url value="/"/>'><- main page</a>
</p>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>NUMBER</th>
        <th>TYPE</th>
        <th>IS_AVAILABLE</th>

    </tr>
    </thead>
    <c:forEach items="${rooms}" var="room">
        <tbody>
        <tr>
            <td>${room.roomId}</td>
            <td>${room.roomNumber}</td>
            <td>${room.roomType}</td>
            <td>${room.isAvailable}</td>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>
