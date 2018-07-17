
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admin</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/admin_start.css">
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <img src="/resources/image/3.png">
        <h1>Бибилиотека периодических изданий</h1>
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/admin?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a></li>
            <li class="dws-li"><a href="/admin?action=catalog"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Каталог</a></li>
            <li class="dws-li"><a href="/admin?action=subscription"><i class="fa fa-folder-open" aria-hidden="true"></i>Подписки</a></li>
            <li class="dws-li"><a href="/admin?action=payment"><i class="fa fa-server" aria-hidden="true"></i>Платежи</a></li>
            <li class="dws-li"><a href="/admin?action=users"><i class="fa fa-newspaper-o" aria-hidden="true"></i>Пользователи</a></li>
        </ui>
    </div>
    <h1>Приветствуем тебя ${login}</h1>
</header>
</body>
</html>
