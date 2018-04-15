<%@ page import="com.azarenko.model.Periodicals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <h1>Бибилиотека переодических изданий</h1>
    <p/>
    <p/>
    <a href="/cart">Корзина</a>
    <p/>
    <a href="pages/start.jsp">Войти под другим логином</a>
    <a href="">Профиль</a>
    <div>
        <p>Пиветствуем тебя User ${login} </p>
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
                    <th><a href="/subscribe?action=subscribe&periodicalid=<c:out value="${periodicals.id}"/>" methods="post">Оформить подписку</a> </th>
                </tr>
            </c:forEach>
            <th></th>

        </table>
    </div>
</head>
<body>

</body>
</html>
