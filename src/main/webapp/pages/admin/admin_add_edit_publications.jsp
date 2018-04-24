<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>

<h1>Редактировать\Добавить</h1>

<p/>
<a href="/admin?action=catalog">Назад</a>
<form method="get" action="/admin?action=add">
    <p>

        ID : <input type="text" readonly="readonly" name="catalogId"
                    value="<c:out value="${periodicals.id}"/>"/>

    <p>
        Название : <input type="text" name="title"
                          value="<c:out value="${periodicals.title}"/>"/>
    <p style="color: red">${error1}</p>
    <p>
        Периодичность выхода : <input type="text" name="outfrequency"
                                      value="<c:out value="${periodicals.outputFrequency}"/>"/>
    <p style="color: red">${error2}</p>
    <p style="color: red">${error3}</p>
    <p>

        Описание : <input type="text" name="discription"
                          value="<c:out value="${periodicals.description}"/>"/>
    <p style="color: red">${error4}</p>
    <p>
        Цена : <input type="text" name="price"
                      value="<c:out value="${periodicals.price}"/>"/>
    <p>
    <p style="color: red">${error5}</p>
    <p style="color: red">${error6}</p>
        <input type="submit"  value="add" name="action">
</form>
</body>
</html>
