<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../fragments/headTag.jsp"/>
<html>
<head>
    <%-- <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">--%>
    <link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
    <jsp:include page="../../fragments/bodyHeaderAdmin.jsp"/>
</head>

<body>
<div class="container">
    <form method="post" action="save">

        <input type="hidden" name="id" value="
                        <c:out value="${periodical.id}"/>">
        Название : <input type="text" name="title"
                          value="<c:out value="${periodical.title}"/>"/>
        <br>
        <p/>
        Описание : <input type="text" name="discription"
                          value="<c:out value="${periodical.discription}"/>"/>
        <br>
        <p/>
        Издатель : <input type="text" name="publisher"
                          value="<c:out value="${periodical.publisher}"/>"/>
        <br>
        <p/>
        Index : <input type="text" name="index"
                       value="<c:out value="${periodical.index}"/>"/>
        <br>
        <p/>
        Периодичность выхода : <input type="text" name="outputFrequency"
                                      value="<c:out value="${periodical.outputFrequency}"/>"/>

        <br>
        <p/>
        Огрничение по возрасту : <input type="text" name="ageLimit"
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
<jsp:include page="../../fragments/footer.jsp"/>

</body>
</html>
