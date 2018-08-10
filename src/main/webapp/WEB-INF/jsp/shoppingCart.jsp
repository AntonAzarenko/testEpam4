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
    <div class="container">
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
        <div class="row">
            <div class="col-auto mr-auto">
                <button type="button" class="btn btn-primary" onclick="pay()">Оплатить</button>

            </div>
            <div class="col-auto">
                <h4>${price}</h4>

            </div>

        </div>
    </div>



</div>
<jsp:include page="../../fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js" defer></script>
</html>