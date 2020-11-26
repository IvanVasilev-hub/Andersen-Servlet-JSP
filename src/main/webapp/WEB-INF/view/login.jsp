<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form method="post" action="/login">
    <p>
        <input type="text" name="email" placeholder="email">
    </p>
    <p>
        <input type="password" name="password" placeholder="password">
    </p>
    <button type="submit">Login</button>
</form>
<form action="/create">
    <button type="submit">Create</button>
</form>
<span style="color:red">
                <%=(request.getAttribute("error") == null)
                        ? "" : request.getAttribute("error")%>
</span>
<span style="color:darkgreen">
                <%=(request.getAttribute("success") == null)
                        ? "" : request.getAttribute("success")%>
</span>
</body>
</html>
