
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
</head>
<body>
    <h2>Add Student</h2>

    <form:form modelAttribute="student" action="insert" method="post">

        <p>${isStudentExistsWhileInserting}</p>

        <label for="name">Name:</label>
        <form:input type="text" path="name" id="name" name="name" placeholder="Enter Name" />
        <br />
        <p>
            <form:errors path="name" />
        </p>

        <label for="gmail">Student Gmail:</label>
        <form:input type="text" path="gmail" id="gmail" name="gmail" />
        <br>
        <p>
                    <form:errors path="gmail" />
                </p>

        <label for="rollno">RollNo:</label>
        <form:input type="number" path="rollno" id="rollno" name="rollno" />
        <br>

         <label for="mark">Mark:</label>
                <form:input type="number" path="mark" id="mark" name="mark" />
                <br />
                <p>
                    <form:errors path="mark" />
                </p>


        <input type="submit" value="insert">
    </form:form>
</body>
</html>