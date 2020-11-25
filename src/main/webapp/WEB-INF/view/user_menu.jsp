<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 24.11.2020
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Menu</title>
</head>
<body>
Hello user!
<div>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
<div>
    <c:if test="${sessionScope.admin}">
        <a href="admin_menu.jsp">For Admins</a>
    </c:if>
</div>
</body>
</html>
