<%@ page import="com.azarenko.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${periodical.title}</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/user_catalog_current.css">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <img src="/image/3.png">
        <h1>Профиль</h1>
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/user?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a>
            </li>
            <li class="dws-li"><a href="/user?action=shoppingcart"><i class="fa fa-shopping-cart"
                                                                      aria-hidden="true"></i>Корзина</a></li>
            <li class="dws-li"><a href="/user?action=catalog"><i class="fa fa-folder-open" aria-hidden="true"></i>Каталог</a>
            </li>
            <li class="dws-li"><a href="/user?action=profile"><i class="fa fa-server" aria-hidden="true"></i>Профиль</a>
            </li>
            <li class="dws-li"><a href="/user?action=news"><i class="fa fa-newspaper-o"
                                                              aria-hidden="true"></i>Новости</a></li>
        </ui>
    </div>
</header>
<div class="container">
    <div>
        <form action="/user?action=showcurrentsubscription" method="post">
            <select>
                <c:forEach items="${requestScope.subList}" var="list">
                    <option value="periodicalId=<c:out value="list.id"/>">${list.namePeriodical}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Показать">
        </form>
    </div>
    <a href="/user?action=myperiodicals">Мои подписки</a>
    <br>
    <a href="/user?action=myprofile">Личная информация</a>
</div>
</body>
</html>
