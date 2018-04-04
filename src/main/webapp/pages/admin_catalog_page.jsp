<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/admin?action=insert">Добавить издание</a>
<div>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Id Издания</th>
            <th>Название</th>
            <th>Описание</th>
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
                <th>${periodicals.price}</th>
                <th><a href="/admin?action=edit&catalogId=<c:out value="${periodicals.id}"/>" methods="post">Редактировать</a></th>
                <th><a href="/admin?action=delete&catalogId=<c:out value="${periodicals.id}"/>">Удалить</a></th>
            </tr>
        </c:forEach>
        <th></th>

    </table>
</div>
</body>
</html>
