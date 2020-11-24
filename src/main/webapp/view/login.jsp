<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login" method="post">

    <table align="center">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td><span style="color:red">
                <%=(request.getAttribute("errorMessage") == null)
                        ? "" : request.getAttribute("errorMessage")%>
            </span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Login"> <input type="reset" value="Reset"></td>
        </tr>
    </table>
</form>
</body>
</html>
