<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<fmt:setBundle basename="messages.app"/>--%>
<jsp:include page="../../fragments/headTag.jsp"/>

<html>
<head>
    <title>List</title>

    <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="../../css/font-awesome.css" type="text/css">
    <jsp:include page="../../fragments/bodyHeader.jsp"/>
    <jsp:include page="../../fragments/search.jsp"/>

</head>
<body>

<div class="view-box">

</div>

<div class="p-3 mb-2 bg-light text-dark">
    <br>
   <%-- <div class="container">
        <div class="fa-search">
            <form action="search" method="post">
                Поиск: <input type="text" name="search" placeholder="название, индекс или цену"/>
                <input type="submit" value="найти"/>
                <h4><%=request.getParameter("message")%>
                </h4>
            </form>
        </div>--%>
        <table class="table table-striped">
            <tr>
                <thead>
                <th><a style="text-decoration: none #1d21ff" href="sortIndex">Index Издания</a></th>
                <th><a style="text-decoration: none #1d21ff" href="sortTitle">Название</a></th>
                <th>Описание</th>
                <th>Издатель</th>
                <th>Периодичность выхода</th>
                <th>Ограничение по возрасту</th>
                <th>Цена</th>
                <th>Редактировать</th>
                <th>Удалить</th>
                </thead>
            </tr>
            <c:forEach items="${list}" var="periodicals">
                <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodical"/>
                <c:if test="${periodicals.archive == false}">
                    <tr>
                        <th>${periodicals.index}</th>
                        <th>${periodicals.title}</th>
                        <th>${periodicals.discription}</th>
                        <th>${periodicals.publisher}</th>
                        <th>${periodicals.outputFrequency}</th>
                        <th>${periodicals.ageLimit}</th>
                        <th>${periodicals.price}</th>
                        <th><a href="edit?id=<c:out value="${periodicals.id}"/>">
                            <button class="btn btn-light">Редактировать</button>
                        </a>
                        </th>
                        <th><a href='archive?id=<c:out value="${periodicals.id}"/>'>
                            <button class="btn btn-link">В архив</button>
                        </a>
                        </th>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

        <br>
        <div class="align-content-center">
            <a href="add">
                <button class="btn btn-sm btn-light ">Добавить</button>
            </a>
        </div>
    </div>
</div>
<jsp:include page="../../fragments/footer.jsp"/>
</body>
</html>
