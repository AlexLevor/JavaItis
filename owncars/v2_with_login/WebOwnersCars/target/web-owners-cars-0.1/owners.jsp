<%--
  Created by IntelliJ IDEA.
  User: AlexLevor
  Date: 26.10.2016
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <title>Список пользователей</title>
</head>
<body>
<h1>
    Список автовладельцев:
</h1>
<p>
    <c:forEach items="${requestScope.myOwners}" var="currentUser">
        <tr>
            <td><c:out value="${currentUser.id}" /><td>
            <td><c:out value="${currentUser.city}" /><td>
            <td><c:out value="${currentUser.age}" /><td>
            <td><c:out value="${currentUser.name}" /><td>
            <td><c:out value="${currentUser.login}" /><td>
            <td><c:out value="${currentUser.password}" /><td>
        <br>
        </tr>
    </c:forEach>
</p>
<h2>
    Форма для добавления нового владельца
</h2>

<form action="owners" method="post">
    City: <input type="text" name="city">
    Age: <input type="text" name="age">
    Name: <input type="text" name="name">
    Login: <input type="text" name="login">
    Password: <input type="text" name="password">
    <input type="submit" value="Add">
    <span class="error">${error}</span>
</form>
</body>
</html>
