<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body style="padding-left: 200px">
<a href="/admin?action=catalog">К каталогу</a>
<p>
    <a href="/admin?action=payment">К платежам</a>
<p>

<div>
    <table border="1" cellpadding="10" cellspacing="2">
        <tr>
            <th>Id</th>
            <th>Id издания</th>
            <th>Имя издания</th>
            <th>Дата начала подписки</th>
            <th>Дата окончания подписки</th>
            <th>Id пользователя</th>
        </tr>
        <c:forEach items="${requestScope.subscriptionList}" var="list">
            <jsp:useBean id="list" scope="page" type="com.azarenko.model.Subscription"/>
            <tr>
                <th>${list.id}</th>
                <th>${list.periodicalId}</th>
                <th>${list.namePeriodical}</th>
                <th>${list.dateStartSubcription}</th>
                <th>${list.dateEndSubscription}</th>
                <th>${list.userId}</th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
