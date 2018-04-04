<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>

    <form method="post" action='admin' name="insert">
        <p>
            ID : <input type="text" readonly="readonly" name="catalogId"
        value="<c:out value="${periodicals.id}"/>"/>

        <p>
            Название : <input type="text"  name="title"/>
        <p>
            Описание : <input type="text" name="discription"/>
        <p>
            Цена : <input type="text" name="price"/>
        <p>
            <input  type="submit" value="Submit" name="action=add">
    </form>
</head>
<body>

</body>
</html>
