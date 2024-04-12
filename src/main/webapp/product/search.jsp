<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/6/2024
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>SEARCH</title>
    <link rel="stylesheet" href="product/home.css">
</head>
<body>
<a href="http://localhost:8080/staff?action=home">HOME</a>
<form method="post" action="http://localhost:8080/products?action=search" style="float: right;">
    <input type="text" name="keyword" placeholder="Search">
    <button type="submit" class="btn">Search</button>
</form>
<h3>SEARCHING RESULT: ${keyword}</h3>
<table>
    <tr style="font-weight: bold">
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Image</td>
        <td>Action</td>
    </tr>
    <c:forEach var="item" items="${products}">
    <tr>
        <td>${item.id}</td>
        <td >${item.name}</td>
        <td>${item.price}</td>
        <td>${item.image}</td>
        <td><a href="http://localhost:8080/products?action=choose&idChoose=${item.id}">Select</a></td>
    </tr>
    </c:forEach>
</table>
</form>
<form method="post" action="http://localhost:8080/products?action=total" style="float: right;">
    <button type="submit" class="btn">Total</button>
</form>
</body>
</html>
