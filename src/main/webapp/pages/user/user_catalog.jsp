<%@ page import="com.azarenko.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Приветствие</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/user_catalog.css">
    <meta charset="UTF-8">

    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <img src="/image/3.png">
        <h1>Бибилиотека периодических изданий</h1>
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/user?action=start"><i class="fa fa-home" aria-hidden="true"></i>Главная</a>
            </li>
            <li class="dws-li"><a href="/user"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Корзина</a></li>
            <li class="dws-li"><a href="/user?action=catalog"><i class="fa fa-folder-open" aria-hidden="true"></i>Каталог</a>
            </li>
            <li class="dws-li"><a href="/user"><i class="fa fa-server" aria-hidden="true"></i>Профиль</a></li>
            <li class="dws-li"><a href="/user"><i class="fa fa-newspaper-o" aria-hidden="true"></i>Новости</a></li>
        </ui>
    </div>
</header>
<div class="container">
    <div class="dws-menu-two">
        <h3>Название</h3>
        <ul class="dws-ui-two">
            <c:forEach items="${requestScope.catalogs}" var="periodicals">
            <li class="dws-li-two"><a
                    href="/user?action=show&periodicalId=<c:out value="${periodicals.id}"/>">${periodicals.title}</a>
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
            <a  href='<c:out value="${next}" />' class="pn next">
                <button class="btn btn-info">Вперед</button>
            </a>
        </c:if>

    </div>
</div>


<%-- <h2>Приветствуем тебя User ${login} </h2>
 <table border="1" cellpadding="8" cellspacing="0">
     <tr>
         <th>Id Издания</th>
         <th>Название</th>
         <th>Описание</th>
         <th>Цена</th>
     </tr>
     <c:forEach items="${requestScope.catalogs}" var="periodicals">
         <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodicals"/>
         <tr>
             <th>${periodicals.id}</th>
             <th>${periodicals.title}</th>
             <th>${periodicals.description}</th>
             <th>${periodicals.price}</th>
             <th><a href="/subscribe?action=subscribe&periodicalid=<c:out value="${periodicals.id}"/>"
                    methods="post">Оформить подписку</a></th>
         </tr>
     </c:forEach>
 </table>
</div>
<a href="/user?action=exit">Выйти из системы</a>
<a class="prof" href="/user?action=profile">Профиль</a>
</body>--%>
</body>
</html>
