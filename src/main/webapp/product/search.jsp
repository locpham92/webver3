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
<h3>SEARCHING RESULT: ${keyword}</h3>
<table>
    <tr style="font-weight: bold">
        <td>#</td>
        <td>Name</td>
        <td>Email</td>
        <td>Adress</td>
        <td>PhoneNumber</td>
        <td>Salary</td>
        <td>Department</td>
    </tr>
    <c:forEach var="item" items="${foundStaffList}">
    <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.email}</td>
        <td>${item.address}</td>
        <td>${item.phoneNumber}</td>
        <td>${item.salary}</td>
        <td>${item.department.name}</td>
    </tr>
    </c:forEach>
</body>
</html>
