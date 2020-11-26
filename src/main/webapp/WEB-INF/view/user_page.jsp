<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<h1>Hello user!</h1>
<div>
    <c:if test="${sessionScope.admin eq true}">
        <a href="${request.contextPath}admin_page.html">For Admins</a>
    </c:if>
</div>
</body>
</html>
