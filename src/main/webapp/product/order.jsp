<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/10/2024
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ORDER</title>
</head>
<body>
<table border="1">
    <tr style="font-weight: bold">
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Quantity</td>
    </tr>
    <c:forEach var="item" items="${listed}">
        <tr>
            <td>${item.id}</td>
            <td >${item.name}</td>
            <td>${item.price}</td>
            <td><input name="quantities" value="1"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
