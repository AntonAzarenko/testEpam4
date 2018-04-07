<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--

<jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodicals"/>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>

<h1>Редактировать\Добавить</h1>
<form method="post" action='admin' name="insert">
    <p>
        ID : <input type="text" readonly="readonly" name="catalogId"
                    value="<c:out value="${periodicals.id}"/>"/>

    <p>
        Название : <input type="text" name="title"
                          value="<c:out value="${periodicals.title}"/>"/>
    <p>
        Описание : <input type="text" name="discription"
                          value="<c:out value="${periodicals.description}"/>"/>
    <p>
        Цена : <input type="text" name="price"
                      value="<c:out value="${periodicals.price}"/>"/>
    <p>
        <input type="submit" value="Принять" name="action=add">
</form>
</body>
</html>
