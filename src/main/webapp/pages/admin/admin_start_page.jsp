<%@ page import="com.azarenko.model.Periodicals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<div style="padding-left: 100px">

    <a  href="/admin?action=catalog">Каталог</a>
    <p>
        <a href="/admin?action=payment">Платежи</a>
    <p>
        <a href="/admin?action=subscription">Подписки</a>
</div>
</body>
</html>
