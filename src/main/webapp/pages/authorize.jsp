<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="css/start.css" media="all">
<style>
    <%@include file="/css/start.css"%>
</style>
<header>
    <h1>Вход</h1>
    <div class="container">
        <img src="/image/3.png">
        <form class="center" method=post action="authorize?action=authorize">
            <div class="dws-input">
                <input type="text" placeholder="Введите логин" name="login">
            </div>
            <div class="dws-input">
                <input type="password" placeholder="Введите пароль" name="password"/>
            </div>
            <input class="butt" type="submit" value="Войти">
            <br/>

            <a href="">Восстановить пароль</a>
            <br/>
        </form>
        <form method="post" class="dws-input" action="authorize?action=register">
            <input class="butt" type="submit" value="Зарегистрироваться">
        </form>
        <div class="error">
            <p>${error}</p>
        </div>
    </div>
</header>
</body>
</html>
