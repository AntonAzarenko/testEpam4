<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог</title>
</head>
<body style="padding-left: 150px">

<a href="/admin?action=payment">Платежи</a>
    <p/>
    <a href="/admin?action=subscription">Подписки</a>
    <p/>
    <a href="pages/admin/start.jsp">Воити под другим логином</a>
    <p/>
    <a href="/admin?action=insert">Добавить издание</a>
    <p/>
<h1 style="padding-left: 200px">Каталог периодических изданий</h1>
    <div>
        <table border="1" cellpadding="10" cellspacing="2">
            <tr>
                <th>Id Издания</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Цена</th>
                <th>Редактировать</th>
                <th>Удалить</th>
            </tr>
            <c:forEach items="${requestScope.catalogs}" var="periodicals">
                <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodicals"/>
                <tr>
                    <th>${periodicals.id}</th>
                    <th>${periodicals.title}</th>
                    <th>${periodicals.description}</th>
                    <th>${periodicals.price}</th>
                    <th><a href="/admin?action=edit&catalogId=<c:out value="${periodicals.id}"/>" methods="post">Редактировать</a>
                    </th>
                    <th><a href="/admin?action=delete&catalogId=<c:out value="${periodicals.id}"/>">Удалить</a></th>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
