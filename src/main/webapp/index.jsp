<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="../../resources/css/style.css" type="text/css">
<link rel="stylesheet" href="webjars/bootstrap/4.1.0/css/bootstrap.min.css">
<jsp:include page="WEB-INF/jsp/bodyHeader.jsp"/>

<body>

<br>
<h3>Проект EPAM "<a href="https://github.com/AntonAzarenko/testEpam4/tree/spring" target="_blank">Периодические издания</a>" </h3>

<jsp:include page="WEB-INF/jsp/bodyHeader.jsp"/>
<hr>
<form class="tool-bar" method="Get" action="start">
    Logged us: <select name="userId">
    <option value="1" selected>User</option>
    <option value="2">Admin</option>
                </select><br><p/>
    <input type="submit" value="Выбрать"/>
</form>
</body>
</html>
