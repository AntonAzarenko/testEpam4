<%@ page import="com.azarenko.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Приветствие</title>
  <style>
      .prof{
          padding-left: 500px;

      }
  </style>
</head>
<body>
<h1>Бибилиотека периодических изданий</h1>
<p/>
<p/>
<a href="/cart">Корзина</a>
<p/>
<a href="pages/start.jsp">Войти под другим логином</a>
<a class="prof" href="/user?action=profile">Профиль</a>
<div>
    <h2>Приветствуем тебя User ${login} </h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Id Издания</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена</th>
        </tr>
        <c:forEach items="${requestScope.catalogs}" var="periodicals">
            <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodicals"/>
            <tr>
                <th>${periodicals.id}</th>
                <th>${periodicals.title}</th>
                <th>${periodicals.description}</th>
                <th>${periodicals.price}</th>
                <th><a href="/subscribe?action=subscribe&periodicalid=<c:out value="${periodicals.id}"/>" methods="post" >Оформить подписку</a> </th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
