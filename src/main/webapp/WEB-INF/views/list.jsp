<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>index</title>
</head>
<body>
   <h1>hey</h1>
   <table>
       <table border="1">
   <thead>
       <tr>
       <th> Id</th>
       <th> Name</th>
       <th> Rollno</th>
   </tr>
   </thead>
   <tbody>
   <c:forEach var="student" items="${students}">
   <tr>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.rollno}</td>
    </tr>
    </c:forEach>
    </tbody>
    </table>
</body>
</html>
