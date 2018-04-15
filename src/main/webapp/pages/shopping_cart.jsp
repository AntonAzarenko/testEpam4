<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Id Издания</th>
        <th>Дата начала подписки</th>
        <th>Дата окончания</th>
        <th>Цена</th>
    </tr>
    <c:forEach items="${requestScope.cartList}" var="shoppingCart">
        <jsp:useBean id="shoppingCart" scope="page" type="com.azarenko.model.ShoppingCart"/>
    <tr>
        <th>${shoppingCart.periodicalId}</th>
        <th>${shoppingCart.start}</th>
        <th>${shoppingCart.end}</th>
    </tr>
    </c:forEach>
    <th></th>
</body>
</html>
