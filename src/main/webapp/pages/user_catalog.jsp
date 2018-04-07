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
    <a href="">Корзина</a>
    <p/>
    <a href="pages/start.jsp">Войти под другим логином</a>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Id Publications</th>
                <th>Title</th>
                <th>Discription</th>
                <th>price</th>
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
