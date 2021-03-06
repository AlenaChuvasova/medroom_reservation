<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.chuvasova.medroom.model.ManipulationName" %>
<%@ page import="by.chuvasova.medroom.service.ReservationService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new reservation</title>
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tamma+2:wght@500;600;700&display=swap" rel="stylesheet">
    <style>
        <%@include file="/styles/style.css"%>
    </style>
</head>
<body>
<div class="add_reserv">
    Create new reservation:
    <form id="addemployee" action="${pageContext.request.contextPath}/addreservation" method="post">
        <label>Available employee's full name: </label><br>
        <select name="employeeId">
            <c:forEach var="employee" items="${employees}">
                <option value="${employee.id}">${employee.name} ${employee.surname}</option>
            </c:forEach>
        </select><br/>
        <label for="manipulation">Choose appropriate manipulation: </label>
        <select name="manipulation" id="manipulation">
            <c:forEach items="<%=ManipulationName.values()%>" var="entry">
                <option>${entry.name}</option>
            </c:forEach>
        </select><br/>
        <label for="description">Description: </label><br>
        <input type="text" name="description" placeholder="Input description" id="description"><br/>

        <label for="starttime">Select start time: </label><br>
        <input type="datetime-local" name="startTime" id="starttime"><br/>

        <label for="endtime">Select end time: </label><br>
        <input type="datetime-local" name="endTime" id="endtime"><br/>

        <label>Check status: </label><br>
        <input type="radio" name="status" value="isActive" checked="checked"> Active</input>
        <input type="radio" name="status" value="inActive"> inActive</input>
        <br/>
        <label>Available room number: </label><br>
        <select name="roomNumber">
            <c:forEach var="number" items="${freeRoom}">
                <option value="${freeRoom}">
                        ${freeRoom}
                </option>
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
