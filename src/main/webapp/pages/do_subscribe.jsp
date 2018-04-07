<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оформление подписки</title>
</head>
<body>
<form method="post" action="sub">
    <p/>
    ID : <input type="text" readonly="readonly" name="id"
                value="<c:out value="${periodicals.id}"/>"/>
    <p/>
    Описание : <input type="text" readonly="readonly" name="discription"
                      value="<c:out value="${periodicals.description}"/>"/>

    Название : <input type="text" readonly="readonly" name="title"
                      value="<c:out value="${periodicals.title}"/>"/>
    <p>
        Цена в месяц : <input type="text" readonly="readonly" name="price"
                              value="<c:out value="${periodicals.price}"/>"/>
    <p/>
    Количество месяцев : <input type="text"/>
    <p/>

    <input type="submit" value="Отправить в корзину" name="action=add">

</form>
</body>
</html>
