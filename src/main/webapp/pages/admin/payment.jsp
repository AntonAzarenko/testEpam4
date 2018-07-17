<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Платежи</title>
</head>
<body>
<header>
    <div class="logo">
        <img src="/resources/image/3.png">
        <h1>Каталог периодических изданий</h1>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/admin_catalog.css">
        <link rel="stylesheet" href="../css/style.css" type="text/css">
        <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/admin?action=catalog"><i class="fa fa-home" aria-hidden="true"></i>Каталог</a>
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

<div class="container">
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
