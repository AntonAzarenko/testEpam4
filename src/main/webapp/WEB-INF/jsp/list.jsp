<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>

    <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="../../css/font-awesome.css" type="text/css">
</head>
<body>

<div class="view-box">
<br>
    <hr>
</div>
<div class="container">
<table class="table table-striped">
    <tr>
        <thead>
        <th>Id Издания</th>
        <th>Название</th>
        <th>Описание</th>
        <th>Периодичность выхода</th>
        <th>Цена</th>
        <th>Редактировать</th>
        <th>Удалить</th>
        </thead>
    </tr>
    <c:forEach items="${list}" var="periodicals">
        <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodical"/>
        <tr>
            <th>${periodicals.index}</th>
            <th>${periodicals.title}</th>
            <th>${periodicals.discription}</th>
            <th>${periodicals.outputFrequency}</th>
            <th>${periodicals.price}</th>
            <th><a href="start?action=edit&id=<c:out value="${periodicals.id}"/>">
                <button class="btn btn-primary">Редактировать</button>
            </a>
            </th>
            <th><a href='start?action=delete&id=<c:out value="${periodicals.id}"/>'>
                <button class="btn btn-danger">В архив</button>
            </a>
            </th>
        </tr>
    </c:forEach>
</table>

<br>
<div class="align-content-center">
<a href="start?action=add"><button class="btn btn-sm btn-info ">Добавить</button></a>
</div>
</div>
</body>
</html>
