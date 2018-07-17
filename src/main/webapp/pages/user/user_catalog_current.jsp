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
        <img src="/resources/image/3.png">
        <h1>Бибилиотека периодических изданий</h1>
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/user?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a></li>
            <li class="dws-li"><a href="/user?action=shoppingcart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Корзина</a></li>
            <li class="dws-li"><a href="/user?action=catalog"><i class="fa fa-folder-open" aria-hidden="true"></i>Каталог</a></li>
            <li class="dws-li"><a href="/user?action=profile"><i class="fa fa-server" aria-hidden="true"></i>Профиль</a></li>
            <li class="dws-li"><a href="/user?action=news"><i class="fa fa-newspaper-o" aria-hidden="true"></i>Новости</a></li>  </ui>
    </div>
</header>
<div class="container">
    <div class="dws-menu-two">
        <h3>Название</h3>
        <ul class="dws-ui-two">
            <c:forEach items="${requestScope.catalogs}" var="periodical">
            <li class="dws-li-two">
                <a href="/user?action=show&periodicalId=<c:out value="${periodical.id}"/>">${periodical.title}</a></li>
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
            <a  href='<c:out value="${next}" />' class="pn next">
                <button class="btn btn-info">Вперед</button>
            </a>
        </c:if>

    </div>
</div>
<div class="container_two">

    <p>Название :</p>
    <h2>${periodical.title}</h2>

    <p> Описание : </p>
    <h2>${periodical.description}</h2>
    <p>Количество выпусков в месяц</p>
    <h2>${periodical.outputFrequency}</h2>
    <p>Цена за один экзкмпляр</p>
    <div class="end">
        <h2 cass="end">${periodical.price}</h2>
    </div>
    <a href="/user?action=redirectsubscribe&periodicalId=<c:out value="${periodical.id}"/>">
        <button class="butt">Подписаться</button>
    </a>
    <%--<form method="get" class="dws-input" action="/userPage?action=redirectsubscribe&periodicalId=<c:out value="${logic.id}"/>">
        <input class="butt" type="submit" value="Подписаться" />
    </form>--%>

</div>

</body>
</html>
