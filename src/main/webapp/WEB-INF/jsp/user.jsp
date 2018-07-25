<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../fragments/headTag.jsp"/>


<html>

<head>
    <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="../../css/font-awesome.css" type="text/css">
    <jsp:include page="../../fragments/bodyHeader.jsp"/>
    <jsp:include page="../../fragments/search.jsp"/>
</head>
<body>

<div class="container-fluid " style="background: #ebebeb" >

        <div class="container bg-light flex-lg-row ">
            <table class="table table-striped ">
                <tr>
                    <thead>
                    <th>ISBN</th>
                    <th>Название</th>
                    </thead>
                </tr>
                <c:forEach items="${list}" var="periodicals">
                    <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodical"/>
                    <tr>
                        <th>${periodicals.index}</th>
                        <th class="p-3"><a class="bg-light text-dark  p-1 " style="text-decoration: none"
                               href="get?id=<c:out value='${periodicals.id}'/>">${periodicals.title}<a/></th>
                        <th><a href="t&id=<c:out value="${periodicals.id}"/>">
                            <button class="btn btn-light">Подписаться</button>
                        </a>
                        </th>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </div>
       <%-- <div class="container ml-2 w-50 shadow-lg col " style="background-color: rgba(227,225,222,0.62)">
            <c:if test="${periodical.id != null}">
            <div class="text-center">
                <hr>
                <h7>Название - ${periodical.title}</h7>
            </div>
            <div class="text-center">
                <hr>
                <h7>Описание - ${periodical.discription}</h7>
            </div>
                <div class="text-center">
                    <hr>
                    <h7>Издатель - ${periodical.publisher}</h7>
                </div>
                <div class="text-center">
                    <hr>
                    <h7>Ограничение по возрасту - от ${periodical.ageLimit} лет</h7>
                </div>
                <div class="text-center">
                    <hr>
                    <h7>Подписной индекс - ${periodical.index}</h7>
                </div>
                <div class="text-center">
                    <hr>
                    <h7>Цена за экземпляр - ${periodical.price}</h7>
                </div>
            </c:if>
        </div>
    </div>--%>
</div>
<jsp:include page="../../fragments/footer.jsp"/>
</body>
</html>
