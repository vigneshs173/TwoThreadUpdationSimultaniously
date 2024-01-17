<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Fetch Student</title>
</head>
<body>
    <h2>Fetch Student</h2>

    <form action="fetching" method="post">


        <label>ID:</label>
        <input type="text" name="id" value="${student.id}"/>
        <input type="submit" value="Fetch"/>

        <label>Name:</label>
        <input type="text" name="name" value="${student.name}"/><br/>



        <label>Roll No:</label>
        <input type="text" name="rollno" value="${student.rollno}"/><br/>





</body>
</html>
