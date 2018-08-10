<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:include page="../../fragments/headTag.jsp"/>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="../../resources/css/font-awesome.css" type="text/css">
    <jsp:include page="../../fragments/bodyHeaderLogin.jsp"/>
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
    <script type="text/javascript" src="resources/js/registration.js" defer></script>
</head>
<body>



<div class="modal fade" id="register">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><fmt:message key="app.register"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="registration" method="post">
                    <div class="form-group">
                        <label for="name" class="col-form-label">
                            <fmt:message key="app.login.name"/>
                        </label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-form-label">
                            <fmt:message key="app.login.email"/>
                        </label>
                        <input type="text" class="form-control" id="email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-form-label">
                            <fmt:message key="app.login.password"/>
                        </label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" onclick="addUser()" class="btn btn-primary"><fmt:message key="app.register"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="enter">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><fmt:message key="app.welcome"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="enterForm" action="spring_security_check" method="post">

                    <div class="form-group">
                        <label  class="col-form-label">
                            <fmt:message key="app.login.email"/>
                        </label>
                        <input type="text" class="form-control"  name='username'>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">
                            <fmt:message key="app.login.password"/>
                        </label>
                        <input type="password"  class="form-control"  name='password'>
                    </div>
                    <div class="modal-footer">
                        <button type="submit"  class="btn btn-success"><fmt:message key="app.enter"/></button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<jsp:include page="../../fragments/footer.jsp"/>

</body>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js" defer></script>


