<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../fragments/headTag.jsp"/>

<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="resources/css/font-awesome.css" type="text/css">
    <jsp:include page="../../fragments/bodyHeaderUser.jsp"/>
    <script type="text/javascript" src="resources/js/subscriptionUtils.js" defer></script>
    <script type="text/javascript" src="resources/js/subscriptionTable.js" defer></script>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <table class="table table-striped table-bordered" id="datatable" width="100%">

            <thead>
            <tr>
                <th>Начало подписки</th>
                <th>Окончиние подписки</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<jsp:include page="../../fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js" defer></script>
</html>

