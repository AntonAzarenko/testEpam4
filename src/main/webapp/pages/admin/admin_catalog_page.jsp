<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог</title>
</head>
<header>
    <div class="logo">
        <img src="/image/3.png">
        <h1>Каталог периодических изданий</h1>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/admin_catalog.css">
        <link rel="stylesheet" href="../css/style.css" type="text/css">
        <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/admin?action=insert"><i class="fa fa-home" aria-hidden="true"></i>Добавить</a>
            </li>
            <li class="dws-li"><a href="/admin?action=subscription"><i class="fa fa-shopping-cart"
                                                                       aria-hidden="true"></i>Подписки</a></li>
            <li class="dws-li"><a href="/admin?action=payment"><i class="fa fa-folder-open" aria-hidden="true"></i>Платежи</a>
            </li>
            <li class="dws-li"><a href="/admin?action=users"><i class="fa fa-server" aria-hidden="true"></i>Пользователи</a>
            </li>
            <li class="dws-li"><a href="/admin?action=exit"><i class="fa fa-newspaper-o"
                                                               aria-hidden="true"></i>Выйти</a></li>
        </ui>
    </div>
</header>
<body>
<div class="container">
    <table border="1" cellpadding="7" cellspacing="2">
        <tr class="tr">
            <th>Id Издания</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Периодичность выхода</th>
            <th>Цена</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        <c:forEach items="${catalogs}" var="periodicals">
            <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodicals"/>
            <tr>
                <th>${periodicals.id}</th>
                <th>${periodicals.title}</th>
                <th>${periodicals.description}</th>
                <th>${periodicals.outputFrequency}</th>
                <th>${periodicals.price}</th>
                <th><a href="/admin?action=edit&catalogId=<c:out value="${periodicals.id}"/>">
                    <button class="butt">Редактировать</button>
                </a>
                </th>
                <th><a href='/admin?action=delete&catalogId=<c:out value="${periodicals.id}"/>'>
                    <button class="butt">Удалить</button>
                </a>
                </th>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
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
</body>
</html>
