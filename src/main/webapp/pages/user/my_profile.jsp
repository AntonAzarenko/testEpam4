<%@ page import="com.azarenko.model.Periodicals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Личная информация</title>
</head>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>id Ползователя</th>
        <th>Имя</th>
        <th>Почта/логин</th>
        <th>Дата регистрации</th>
        <th>Роль</th>
    </tr>

        <tr>
            <th>${user.id}</th>
            <th>${user.name}</th>
            <th>${user.email}</th>
            <th>${user.registered}</th>
            <th>${user.role}</th>
        </tr>
</table>
<p/>
<a href="/user?action=catalog">К каталогу</a>
</body>
</html>
