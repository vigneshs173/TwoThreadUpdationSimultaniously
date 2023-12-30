<!-- showStudents.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Student List</title>
</head>
<body>

<h2>Student List</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Gmail</th>
        <th>Roll No</th>
        <th>Mark</th>
    </tr>
    <c:forEach var="student" items="${students}" >
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.gmail}</td>
            <td>${student.rollno}</td>
            <td>${student.mark}</td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
