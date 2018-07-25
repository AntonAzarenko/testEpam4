<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="../../css/font-awesome.css" type="text/css">
    <jsp:include page="../../fragments/bodyHeader.jsp"/>
    <div class="container h-75  bg-light shadow-lg">
        <br>
        <jsp:include page="../../fragments/discription.jsp"/>
        <div class="text-info">
            <h3>Maven, Spring Data, Spring Security, Hibernate, Ajax, JavaScript, Bootstrap </h3>
        </div>
        <br>
        <br>
        <p><h5><fmt:message key = "app.discription"/></h5></p>

    </div>

</head>

