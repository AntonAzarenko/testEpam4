<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>
<header>
    <div class="logo">
        <img src="/image/3.png">
        <h1>Каталог периодических изданий</h1>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/admin_edit_add.css">
        <link rel="stylesheet" href="../css/style.css" type="text/css">
        <link rel="stylesheet" href="../css/font-awesome.css" type="text/css">
    </div>
    <div class="dws-menu">
        <ui class="dws-ul">
            <li class="dws-li"><a href="/admin?action=insert"><i class="fa fa-home" aria-hidden="true"></i>Добавить</a>
            </li>
            <li class="dws-li"><a href="/admin?action=subscription"><i class="fa fa-shopping-cart"
                                                                       aria-hidden="true"></i>Подписки</a></li>
            <li class="dws-li"><a href="/admin?action=payment"><i class="fa fa-folder-open" aria-hidden="true"></i>Платежи</a>
            </li>
            <li class="dws-li"><a href="/admin?action=users"><i class="fa fa-server" aria-hidden="true"></i>Пользователи</a>
            </li>
            <li class="dws-li"><a href="/admin?action=exit"><i class="fa fa-newspaper-o"
                                                               aria-hidden="true"></i>Выйти</a></li>
        </ui>
    </div>
</header>
<div class="container">
    <h1>Редактировать\Добавить</h1>


    <form method="post" action="/admin?action=add">

        <div class="dws-input">
            ID : <input type="text" readonly="readonly" name="catalogId"
                        value="<c:out value="${periodicals.id}"/>"/>
        </div>
        <div class="dws-input">

            Название : <input type="text" name="title"
                              value="<c:out value="${periodicals.title}"/>"/>
        </div>
        <p style="color: red">${error1}</p>
        <div class="dws-input">
            Периодичность выхода : <input type="text" name="outfrequency"
                                          value="<c:out value="${periodicals.outputFrequency}"/>"/>
        </div>
        <p style="color: red">${error2}</p>
        <p style="color: red">${error3}</p>

        <div class="dws-input">
            Описание : <input type="text" name="discription"
                              value="<c:out value="${periodicals.description}"/>"/>
        </div>
        <p style="color: red">${error4}</p>
        <div class="dws-input">
            Цена : <input type="text" name="price"
                          value="<c:out value="${periodicals.price}"/>"/>
        </div>
            <p style="color: red">${error5}</p>
            <p style="color: red">${error6}</p>
            <div class="dws-input">
                <input class="butt" type="submit" value="Принять">
            </div>
    </form>
</div>
</body>
</html>
