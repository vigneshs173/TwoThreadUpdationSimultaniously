<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<h2>update Student</h2>
<form action="updateNow" method="post">

<label>ID:</label>
    <input type="text" name="id" value="${student.id}"/>


<label>Name:</label>
    <input type="text" name="name" value="${student.name}"/><br/>


    <label>RollNo:</label>
        <input type="text" name="rollno" value="${student.rollno}"/><br/>

  <input type="submit" value="update"/>


</body>
</html>