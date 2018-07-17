<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Оформление подписки</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/subscribe.css">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/subscribe.css" type="text/css"/>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
</head>
<body>
<div class="logo">
    <img src="/resources/image/3.png">
    <h1>Бибилиотека периодических изданий</h1>
</div>
<div class="dws-menu">
    <ui class="dws-ul">
        <li class="dws-li"><a href="/user?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a></li>
        <li class="dws-li"><a href="/user?action=shoppingcart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Корзина</a></li>
        <li class="dws-li"><a href="/user?action=catalog"><i class="fa fa-folder-open" aria-hidden="true"></i>Каталог</a></li>
        <li class="dws-li"><a href="/user?action=profile"><i class="fa fa-server" aria-hidden="true"></i>Профиль</a></li>
        <li class="dws-li"><a href="/user?action=news"><i class="fa fa-newspaper-o" aria-hidden="true"></i>Новости</a></li> </ui>
</div>
<div class="container">
    <div class="dws-menu-two">
        <h3>Название</h3>
        <ul class="dws-ui-two">
            <c:forEach items="${catalogs}" var="periodical">
                <li class="dws-li-two"><a
                        href="/user?action=show&periodicalId=<c:out value="${periodical.id}"/>">${periodical.title}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="pagination" class="pagin">
        <c:url value="${currentsort}" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">
                <button class="btn btn-info">Назад</button>
            </a>
        </c:if>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="${currentsort}" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>
                        <button class="btn btn-info">${i.index}</button>
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="${currentsort}" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>

        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">
                <button class="btn btn-info">Вперед</button>
            </a>
        </c:if>

    </div>
</div>

<div class="container_two">
    <form method="post" action="/user?action=addtocart&periodicalId=<c:out value="${periodical.id}"/>">

        <div class="dws-input">
            <p>Описание :</p> <input type="text" readonly="readonly" name="discription"
                                     value="<c:out value="${periodical.description}"/>"/>
        </div>
        <div class="dws-input">
            <p>Название :</p> <input type="text" readonly="readonly" name="title"
                                     value="<c:out value="${periodical.title}"/>"/>
        </div>
        <div class="dws-input">
            <p>Цена за экземпляр :</p> <input type="text" readonly="readonly" name="price"
                                              value="<c:out value="${periodical.price}"/>"/>
        </div>
        <div class="dws-input">
            <p>Количество экземпляров :</p> <input type="text" placeholder="Введите количество выпусков" name="count" value="1"/>
        </div>

        <input class="butt" type="submit" value="Отправить в корзину">

    </form>

</div>

</body>
</html>
