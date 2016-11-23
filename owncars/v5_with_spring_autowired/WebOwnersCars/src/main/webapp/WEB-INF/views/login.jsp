<%--
  Created by IntelliJ IDEA.
  User: KFU-user
  Date: 11.11.2016
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="login" method="post">
    Login: <input type="text" name="login">
    Password: <input type="text" name="password">
    <input type="submit" value="Add">
    <span class="error">${error}</span>
</form>
</body>
</html>
