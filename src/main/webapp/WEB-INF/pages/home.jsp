<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MedRoom Reservation System</title>
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Tamma+2:wght@500;600;700&display=swap" rel="stylesheet">
    <style>
        <%@include file="/styles/style.css"%>
    </style>
</head>
<body>
<h1>MedRoom Reservation System</h1>
<ul id="homepage">
    <li>
        <button onclick="location.href='/available-empl'">Get all available personnel</button>
    </li>
    <li>
        <button onclick="location.href='/availableroom'">Get all available room</button>
    </li>
    <li>
        <button onclick="location.href='/addroom'">Add new room</button>
    </li>
    <li>
        <button onclick="location.href='/addemployee'">Add new employee</button>
    </li>
    <li>
        <button onclick="location.href='/addreservation'">Make a reservation</button>
    </li>
</ul>
</body>
</html>
