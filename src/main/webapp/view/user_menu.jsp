<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 24.11.2020
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Menu</title>
</head>
<body>
Hello user!
<div>
    <a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
</div>
<div>
    <%
        if ((Boolean) session.getAttribute("admin")) {
            out.println("<a href=\"/view/admin_menu.jsp\"");
        }
    %>
</div>
</body>
</html>
