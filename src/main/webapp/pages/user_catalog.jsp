<%@ page import="com.azarenko.model.Catalog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <h1>Бибилиотека переодических изданий</h1>
    <a href="">Корзина</a>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Id Publications</th>
                <th>Title</th>
                <th>Discription</th>
                <th>price</th>
            </tr>
            <c:forEach items="${requestScope.catalogs}" var="catalog">
                <jsp:useBean id="catalog" scope="page" type="com.azarenko.model.Catalog"/>
                <tr>
                    <th>${catalog.id}</th>
                    <th>${catalog.title}</th>
                    <th>${catalog.description}</th>
                    <th>${catalog.price}</th>
                    <th><a href="/addcasket" methods="post">Добавить в корзину</a> </th>
                </tr>
            </c:forEach>
            <th></th>

        </table>
    </div>
</head>
<body>

</body>
</html>
