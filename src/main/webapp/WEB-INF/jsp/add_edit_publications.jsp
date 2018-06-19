<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>
<header>

    <br>
    <h2>Edit/Add </h2>
    <hr>
    <div class="container">

        <form method="post" >

                        <input type="hidden" name="id"
                        value="<c:out value="${periodical.id}"/>" >
            Название : <input type="text" name="title"
                              value="<c:out value="${periodical.title}"/>"/>
            <br>
            <p/>
            Описание : <input type="text" name="discription"
                              value="<c:out value="${periodical.discription}"/>"/>
            <br>
            <p/>
            Издатель : <input type="text" name="pub"
                              value="<c:out value="${periodical.publisher}"/>"/>
            <br>
            <p/>
            Index : <input type="text" name="index"
                                          value="<c:out value="${periodical.index}"/>"/>
            <br>
            <p/>
            Периодичность выхода : <input type="text" name="of"
                                          value="<c:out value="${periodical.outputFrequency}"/>"/>

            <br>
            <p/>
            Огрничение по возрасту : <input type="text" name="al"
                              value="<c:out value="${periodical.ageLimit}"/>"/>
            <br>
            <p/>
            Цена : <input type="text" name="price"
                          value="<c:out value="${periodical.price}"/>"/>

            <div class="dws-input">
                <input class="butt" type="submit" value="Принять">
            </div>
        </form>
    </div>
</body>
</html>
