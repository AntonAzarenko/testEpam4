<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <nav class="navbar navbar-dark bg-dark">
        <button type="button" class="btn btn-primary" onclick="register()" >
            <fmt:message key="app.register"/>
        </button>

        <form class="form-inline my-2 my-lg-0">
            <a class="btn  my-2 my-sm-0 mr-sm-2 btn-primary" role="button" href="list"><fmt:message key="user.title"/></a>
            <button class="btn  my-2 my-sm-0 mr-sm-2 btn-primary" type="button" onclick="enter()"><fmt:message key="app.enter"/></button>
        </form>
    </nav>
</head>

