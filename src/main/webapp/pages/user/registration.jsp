<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="../css/registration.css">
</head>

<body>
<h1 >Регистрация</h1>
<div class="container">
    <<img src="../image/3.png" alt="">
    <form class="header" method="post"  action="/authorize?action=reg" >
        <div class="dws-input">
        <input type="text" placeholder="Введите имя *" name="name"/>
        </div>
        <div class ="error">
            <p>${error1}</p>
        </div>
        <div class="dws-input">
        <input type="text" placeholder="Введите Email *" name="email"/>
        </div>
        <div class ="error">
            <p>${error1}</p>
        </div>
        <div class="dws-input">
        <input type="password" placeholder="Введите пароль *" name="password"/>
        </div>
        <div class ="error">
            <p>${error1}</p>
        </div>
        <div class="dws-input">
        <input type="password" placeholder="Повторите пароль *" name="password_two">
        </div>
        <div class ="error">
            <p>${error1}</p>
        </div>
        <input class="butt" type="submit" value="Зарегистрироваться">
    </form>
</div>
</body>
</html>
