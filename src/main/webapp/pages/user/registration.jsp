<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Регистрация</title>
</head>

<body>

<h1 >Регистрация</h1>
<div>
    <form method="post"  action="/registration?action=reg" >
        Имя* : <input type="text" name="name"/>
        <br/>
        <p/>
        Email* : <input type="text" name="email"/>
        <br/>
        <p/>
        Пароль* : <input type="password" name="password"/>
        <p/>
        Подтвердить пароль : <input type="password" name="password_two">
        <p/>
        <br/>
        <input type="submit" value="Зарегистрироваться">
    </form>
</div>
</body>
</html>
