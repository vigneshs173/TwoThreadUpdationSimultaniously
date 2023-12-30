<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Student</title>
</head>
<body>

<h2>Edit Student</h2>

<form action="<c:url value='/fetchStudent'/>" method="post">
            <p>${isStudentExistsWhileUpdating}</p>
            <p>${errorMessage}</p>

    <label>ID:</label>
    <input type="text" name="id" value="${student.id}"/>
    <input type="submit" value="Fetch"/>

    <label>Name:</label>
    <input type="text" name="name" value="${student.name}"/><br/>

    <label>Gmail:</label>
    <input type="text" name="gmail" value="${student.gmail}"/><br/>

    <label>Roll No:</label>
    <input type="text" name="rollno" value="${student.rollno}"/><br/>

    <label>Mark:</label>
        <input type="number" name="mark" value="${student.mark}"/><br/>


        <input type="submit" formaction="<c:url value='/update'/>" value="update"/>  <!-- with this it sits on the same page with entered values --!>
