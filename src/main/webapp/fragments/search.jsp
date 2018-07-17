<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <nav class="navbar navbar-light bg-light">
        <a href="#">
            <div class="navbar-brand"><fmt:message key="app.title"/></div>
        </a>
        <form class="form-inline my-2 my-lg-0" method="post" action="search">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="search">
            <input class="btn btn-outline-success my-2 my-sm-0 mr-sm-2" type="submit"
                   value="<fmt:message key="app.search"/>"/>
        </form>
    </nav>
</head>
</html>
