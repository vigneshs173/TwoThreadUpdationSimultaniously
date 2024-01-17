<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<h2>Insert Student</h2>
<p>${errors}</p>
<form:form modelAttribute="student" action="GetDetails" method="post">

    <label>ID:</label>
    <form:input type="text" path="id" name="id" />
    <p>
        <form:errors path="id"/>
    </p>

    <label>Name:</label>
    <form:input type="text" path="name" />
    <p>
        <form:errors path="name"/>
    </p>

    <label>RollNo:</label>
    <form:input type="text" path="rollno" />
    <p>
        <form:errors path="rollno"/>
    </p>

    <input type="submit" value="Insert"/>

</form:form>
</body>
</html>
