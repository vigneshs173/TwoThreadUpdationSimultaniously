<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Student</title>
</head>
<body>

    <h2>Delete Student</h2>

    <form action="delete" method="post">
                <p>${deleteForm}</p>
        <label for="studentId">Student ID:</label>
        <input type="number" id="studentId" name="id" >
        <br>
        <input type="submit" value="Delete">
    </form>

</body>
</html>
