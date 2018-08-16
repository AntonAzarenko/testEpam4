<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <nav class="navbar navbar-dark bg-dark text-light">
        <button type="button" class="btn btn-primary"  >
            <fmt:message key="app.title"/>
        </button>

        <form class="form-inline my-2 my-lg-0">
            <div class="dropdown dropleft">
                <button class="btn  my-2 my-sm-0 mr-sm-2 btn-primary dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-bars" aria-hidden="true"></i> </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="list">Каталог</a>
                    <a class="dropdown-item" onclick="showSubscription()">Подписки</a>
                    <a class="dropdown-item" href="#">Платежи</a>
                </div>
            </div>
            <button class="btn  my-2 my-sm-0 mr-sm-2 btn-primary" type="button" ><fmt:message key="app.exit"/></button>
        </form>
    </nav>
</head>