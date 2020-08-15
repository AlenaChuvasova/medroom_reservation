<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.chuvasova.medroom.model.RoomTypes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Add new room</title>
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tamma+2:wght@500;600;700&display=swap" rel="stylesheet">
    <style>
        <%@include file="/styles/style.css"%>
    </style>
</head>
<body>
<%--<h1>ADD ROOM</h1>--%>
<div class="add_room">
    Add a new room:
    <%--<c:choose>--%>
    <%--<c:when test="${roomNumber == null}">--%>
    <%--<p>No Client with input ID!!!</p>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
    <form id="addroom" action="${pageContext.request.contextPath}/addroom" method="post">
        <label for="number">Room number:</label>
        <input id="number" type="text" placeholder="Enter number" name="roomNumber"/>
        <br />
        <label for="roomType">Choose appropriate position:</label>
        <select name="roomType" id="roomType">
            <c:forEach items="<%=RoomTypes.values()%>" var="entry">
                <option>${entry.name}</option>
            </c:forEach>
        </select>
        <br />
        <button type="submit">CREATE</button>
        <p>
            <a href='<c:url value="/"/>'><- main page</a>
        </p>
    </form>
    <%--</c:otherwise>--%>
    <%--</c:choose>--%>
</div>
</body>
</html>
