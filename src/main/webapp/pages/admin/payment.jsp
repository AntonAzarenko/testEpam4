<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Платежи</title>
</head>
<body style="padding-left: 200px">
<a href="/admin?action=catalog">К каталогу</a>
<p>
    <a href="/admin?action=subscription">К подпискам</a>
<p>

<div>
    <table border="1" cellpadding="10" cellspacing="2">
        <tr>
            <th>Id</th>
            <th>Дата платежа</th>
            <th>Id пользователя</th>
            <th>Сумма</th>
        </tr>
        <c:forEach items="${requestScope.paymentList}" var="list">
            <jsp:useBean id="list" scope="page" type="com.azarenko.model.Payment"/>
            <tr>
                <th>${list.id}</th>
                <th>${list.date}</th>
                <th>${list.userId}</th>
                <th>${list.price}</th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
