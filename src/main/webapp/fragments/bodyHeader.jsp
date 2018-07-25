<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <nav class="navbar navbar-dark bg-dark">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#registration" data-whatever="@mdo">Open modal for @mdo
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#registration" data-whatever="@mdo">
            <fmt:message key="app.register"></fmt:message>
        </button>


        <form class="form-inline my-2 my-lg-0">
            <a class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" role="button" href="list"><fmt:message key="user.title"/></a>
            <a class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" role="button" href="start">Войти</a>
        </form>
    </nav>
</head>

