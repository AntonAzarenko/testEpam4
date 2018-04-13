<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<h1>Вход в систему "Переодические издания"</h1>
<body>
<form action="/authorize" method=post>
    <p><strong>Введите ваш EMAIL: </strong>
        <input type="text" name="login" size="25">
    <p>
    <p><strong>Введите ваш пароль: </strong>
        <input type="password" size="15" name="password">
    <p>
    <p>
        <input type="submit" value="Принять">
        <input type="reset" value="Сбросить">
</form>

<a href="/pages/registration.jsp">Зарегистрироваться</a>

</body>
</html>
