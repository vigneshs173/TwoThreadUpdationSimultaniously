<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.text.DecimalFormat" %>

<html>
<head>
    <title>Sorted Student List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2>Sorted Student List</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Roll No</th>
        <th>Mark</th>
    </tr>
    <c:forEach var="student" items="${sortedStudents}">
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
