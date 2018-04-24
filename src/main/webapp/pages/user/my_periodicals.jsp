<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Подписки</title>
</head>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>id Подписки</th>
        <th>Название</th>
        <th>Начало подписки</th>
        <th>Окончание</th>
    </tr>
    <c:forEach items="${requestScope.subscriptions}" var="list">
        <jsp:useBean id="list" scope="page" type="com.azarenko.model.Subscription"/>
        <tr>
            <th>${list.periodicalId}</th>
            <th>${list.namePeriodical}</th>
            <th>${list.dateStartSubcription}</th>
            <th>${list.dateEndSubscription}</th>
        </tr>
    </c:forEach>
    <a href="/user?action=catalog">К каталогу</a>
</table>

</body>
</html>
