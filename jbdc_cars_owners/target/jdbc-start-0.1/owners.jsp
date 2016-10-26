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
            <td><c:out value="${currentUser}" /><td>
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
    <input type="submit" value="Add">
    <span class="error">${error}</span>
</form>
</body>
</html>