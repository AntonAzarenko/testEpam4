<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../fragments/headTag.jsp"/>

<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="resources/css/font-awesome.css" type="text/css">
    <jsp:include page="../../fragments/bodyHeaderAdmin.jsp"/>
    <%-- <jsp:include page="../../fragments/search.jsp"/>--%>
    <script type="text/javascript" src="resources/js/shoppingCartUtil.js" defer></script>
    <script type="text/javascript" src="resources/js/shoppingCartTable.js" defer></script>
</head>
<body>
<div class="jumbotron">

    <div class="">
        <table class="table table-striped table-bordered" id="datatable" width="100%">

            <thead>
            <tr>
                <th>Index Издания</th>
                <th>Название</th>
                <th>Начало подписки</th>
                <th>Окончиние подписки</th>
                <th>Кол-во экземпляров</th>
                <th>Цена за подпску</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>