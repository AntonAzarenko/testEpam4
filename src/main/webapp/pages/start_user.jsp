
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приветствие</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/user_catalog.css">
    <link rel="stylesheet" href="..css/style.css" type="text/css">
    <link rel="stylesheet" href="..css/font-awesome.css" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <img src="/image/3.png">
        <h1>Бибилиотека периодических изданий</h1>
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="user?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a></li>
            <li class="dws-li"><a href="user?action=shoppingcart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Корзина</a></li>
            <li class="dws-li"><a href="user?action=catalog"><i class="fa fa-folder-open" aria-hidden="true"></i>Каталог</a></li>
            <li class="dws-li"><a href="user?action=profile"><i class="fa fa-server" aria-hidden="true"></i>Профиль</a></li>
            <li class="dws-li"><a href="user?action=news"><i class="fa fa-newspaper-o" aria-hidden="true"></i>Новости</a></li>
        </ui>
    </div>
    <h1>Приветствуем тебя ${login}</h1>
</header>
</body>
</html>
