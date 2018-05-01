<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог</title>
</head>
<body style="padding-left: 150px">

<a href="/admin?action=payment">Платежи</a>
    <p/>
    <a href="/admin?action=subscription">Подписки</a>
    <p/>
    <a href="pages/authorize.jsp">Воити под другим логином</a>
    <p/>
    <a href="/admin?action=insert">Добавить издание</a>
    <p/>
<h1 style="padding-left: 200px">Каталог периодических изданий</h1>
    <div>
        <table border="1" cellpadding="10" cellspacing="2">
            <tr>
                <th>Id Издания</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Периодичность выхода</th>
                <th>Цена</th>
                <th>Редактировать</th>
                <th>Удалить</th>
            </tr>
            <c:forEach items="${requestScope.catalogs}" var="periodicals">
                <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodicals"/>
                <tr>
                    <th>${periodicals.id}</th>
                    <th>${periodicals.title}</th>
                    <th>${periodicals.description}</th>
                    <th>${periodicals.outputFrequency}</th>
                    <th>${periodicals.price}</th>
                    <th><a href="/admin?action=edit&catalogId=<c:out value="${periodicals.id}"/>" methods="post">Редактировать</a>
                    </th>
                    <th><a href="/admin?action=delete&catalogId=<c:out value="${periodicals.id}"/>">Удалить</a></th>
                </tr>
            </c:forEach>
        </table>
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
</body>
</html>
