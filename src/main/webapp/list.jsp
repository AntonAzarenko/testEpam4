<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 12.06.2018
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>
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
    <c:forEach items="${list}" var="periodicals">
        <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodical"/>
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
</body>
</html>
