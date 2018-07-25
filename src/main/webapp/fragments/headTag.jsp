<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <title><fmt:message key="app.register"/></title>
    <%--<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url,0 ,fn:length(url)- fn:length(pageContext.request.requestURL) )}">--%>
    <link rel="stylesheet" href="resources/css/style.css"/>
    <link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.16/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="webjars/datatables/1.10.16/css/dataTables.bootstrap.min.css"/>
    <link rel="shortcut icon" href="resources/image/3.png"/>

    <script type="text/javascript" src="webjars/jquery/3.0.0/jquery.min.js" defer></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.0/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.16/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.16/js/dataTables.bootstrap.min.js" defer></script>
</head>