<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Me</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/my_profile.css">
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <img src="/image/3.png">

    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/user?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a>
            </li>
            <li class="dws-li"><a href="/user?action=shoppingcart"><i class="fa fa-shopping-cart"
                                                                      aria-hidden="true"></i>Корзина</a></li>
            <li class="dws-li"><a href="/user?action=catalog"><i class="fa fa-folder-open" aria-hidden="true"></i>Каталог</a>
            </li>
            <li class="dws-li"><a href="/user?action=myprofile"><i class="fa fa-server" aria-hidden="true"></i>Мой профиль</a>
            </li>
            <li class="dws-li"><a href="/user?action=profile"><i class="fa fa-newspaper-o"
                                                                 aria-hidden="true"></i>Подписки</a></li>
            <li class="dws-li"><a href="/user?action=exit"><i class="fa fa-newspaper-o"
                                                              aria-hidden="true"></i>Выход</a></li></ui>
    </div>
</header>
<div class="container">
    <h2>${user.name}</h2>
    <h2>${user.email}</h2>
    <h2>${user.role}</h2>
    <h2>${user.registered}</h2>
</div>
</body>
</html>

