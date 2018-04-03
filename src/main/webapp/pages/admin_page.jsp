<%@ page import="com.azarenko.model.Publication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admin</title>
    <h1>Бибилиотека переодических изданий</h1>
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
            <c:forEach items="${requestScope.catalogs}" var="publication">
                <jsp:useBean id="publication" scope="page" type="com.azarenko.model.Publication"/>
                <tr>
                    <th>${publication.id}</th>
                    <th>${publication.title}</th>
                    <th>${publication.description}</th>
                    <th>${publication.price}</th>
                    <th><a href="/admin?action=edit&catalogId=<c:out value="${publication.id}"/>" methods="post">Редактировать</a></th>
                    <th><a href="/admin?action=delete&catalogId=<c:out value="${publication.id}"/>">Удалить</a></th>
                </tr>
            </c:forEach>
            <th></th>

        </table>
    </div>
</head>
<body>

</body>
</html>
