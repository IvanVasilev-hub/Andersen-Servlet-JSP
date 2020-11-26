<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New User</title>
</head>
<body>
<h2>Enter user credentials:</h2>
<form method="post">
    <p>
        <input type="text" name="email"/>
    </p>
    <p>
        <input type="password" name="password"/>
    </p>
    <input type="submit" value="create"/>
</form>
<span style="color:red">
                <%=(request.getAttribute("error") == null)
                        ? "" : request.getAttribute("error")%>
</span>
</body>
</html>
