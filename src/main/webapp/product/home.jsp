<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/5/2024
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="product/home.css">
</head>
<body>
<div class="header">
    <a class="btn" href="http://localhost:8080/staff?action=add">Add</a>
    <form method="post" action="http://localhost:8080/staff?action=search" style="float: right;">
        <input type="text" name="keyword" placeholder="Search">
        <button type="submit" class="btn">Search</button>
    </form>
</div>
</br>
    <table>
        <tr style="font-weight: bold">
            <td>#</td>
            <td>Name</td>
            <td>Price</td>
            <td>Quantity</td>
            <td>Image</td>
            <td>Category</td>
            <td colspan="2">Action</td>
        </tr>
        <c:forEach var="item" items="${productList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td>${item.image}</td>
            <td>${item.category.name}</td>
            <td><a class="edit" href="http://localhost:8080/staff?action=edit&idEdit=${item.id}">Edit</a></td>
            <td><a class="delete" href="http://localhost:8080/staff?action=delete&idDelete=${item.id}">Delete</a></td>
        </tr>
        </c:forEach>
</table>
</body>
</html>
