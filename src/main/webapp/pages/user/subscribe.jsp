<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Оформление подписки</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/user_catalog_current.css">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
</head>
<body>
<div class="logo">
    <img src="/image/3.png">
    <h1>Бибилиотека периодических изданий</h1>
</div>
<div class="dws-menu">
    <ui class="dws-ul">
        <li class="dws-li"><a href="/user?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a>
        </li>
        <li class="dws-li"><a href="/user"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Корзина</a></li>
        <li class="dws-li"><a href="/user?action=catalog"><i class="fa fa-folder-open"
                                                             aria-hidden="true"></i>Каталог</a>
        </li>
        <li class="dws-li"><a href="/user"><i class="fa fa-server" aria-hidden="true"></i>Профиль</a></li>
        <li class="dws-li"><a href="/user"><i class="fa fa-newspaper-o" aria-hidden="true"></i>Новости</a></li>
    </ui>
</div>
<div class="container">
    <div class="dws-menu-two">
        <h3>Название</h3>
        <ul class="dws-ui-two">
            <c:forEach items="${requestScope.catalogs}" var="periodicals">
            <li class="dws-li-two"><a
                    href="/user?action=show&periodicalId=<c:out value="${periodicals.id}"/>">${periodicals.title}</a>
            </li>
        </ul>
        </c:forEach>
        </ul>
    </div>
</div>

<div class="container_two">
    <form method="post" action="/user?action=subscribe" name="add">
        <p/>
        ID : <input type="text" readonly="readonly" name="id"
                    value="<c:out value="${periodical.id}"/>"/>
        <p/>
        Описание : <input type="text" readonly="readonly" name="discription"
                          value="<c:out value="${periodical.description}"/>"/>
        <p>
            Название : <input type="text" readonly="readonly" name="title"
                              value="<c:out value="${periodical.title}"/>"/>
        <p>
            Цена за экземпляр : <input type="text" readonly="readonly" name="price"
                                  value="<c:out value="${periodical.price}"/>"/>
        <p/>
        Начало подписки : <input type="date" name="dateStart"/>
        <p/>
        Окончание подписки : <input type="date" name="dateEnd">
        <p/>
        <input type="submit" value="Отправить в корзину">

    </form>

</div>

</body>
</html>
