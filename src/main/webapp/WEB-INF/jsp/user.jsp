<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../fragments/headTag.jsp"/>

<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">

    <link rel="stylesheet" href="resources/css/font-awesome.css" type="text/css">

    <jsp:include page="../../fragments/bodyHeaderUser.jsp"/>
    <%-- <jsp:include page="../../fragments/search.jsp"/>--%>
    <script type="text/javascript" src="resources/js/userPeriodicalUtil.js" defer></script>
    <script type="text/javascript" src="resources/js/periodicalUserTable.js" defer></script>
</head>
<body>
<div class="jumbotron">
    <button type="button" class="btn btn-primary   fa fa-shopping-cart" style="margin-left: 96%; margin-bottom: 10px" onclick="checkCart()"></button>

    <div class="">
        <table class="table table-striped table-bordered" id="datatable" width="100%">
            <thead>
            <tr>
                <th>Index Издания</th>
                <th>Название</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<div class="modal fade" id="addSub">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Добавить Издание</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="sub" id="subscriptionForm" method="post">
                    <input type="text" hidden="hidden" readonly="readonly" id="periodicalId" name="periodicalId"/>
                    <div class="form-group">
                        <label for="name" class="col-form-label">Имя:</label>
                        <input placeholder="hold" readonly="readonly" type="text" class="form-control" id="name" name="name"/>
                    </div>

                    <div class="form-group">
                        <label for="firstHalfYear" class="col-form-label">Первое полугодие:</label>
                        <input type="checkbox"  id="firstHalfYear" name="firstHalfYear"/>
                    </div>
                    <div class="form-group">
                        <label for="secondHalfYear" class="col-form-label">Второе полугодие:</label>
                        <input type="checkbox"  id="secondHalfYear" name="secondHalfYear">
                    </div>
                    <div class="form-group">
                        <label for="year" class="col-form-label">Год:</label>
                        <input type="checkbox"  id="year" name="year">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                <button type="submit" onclick="save()" class="btn btn-primary">Добавить</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="info">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Информация</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm" method="post">
                    <input type="text" readonly="readonly" hidden="hidden"  id="id" name="id">
                    <div class="form-group">
                        <label for="title" class="col-form-label">Название:</label>
                        <input type="text" readonly="readonly" class="form-control" id="title" name="title"/>
                    </div>
                    <div class="form-group">
                        <label for="discription" class="col-form-label">Описание:</label>
                        <input type="text" readonly="readonly"  class="form-control" id="discription" name="discription">
                    </div>
                    <div class="form-group">
                        <label for="publisher" class="col-form-label">Издатель:</label>
                        <input type="text" readonly="readonly" class="form-control" id="publisher" name="publisher">
                    </div>
                    <div class="form-group">
                        <label for="index" class="col-form-label">Индекс:</label>
                        <input type="text" readonly="readonly" class="form-control" id="index" name="index">
                    </div>
                    <div class="form-group">
                        <label for="outputFrequency" class="col-form-label">Пероидичность выхода:</label>
                        <input type="text" readonly="readonly" class="form-control" id="outputFrequency" name="outputFrequency">
                    </div>
                    <div class="form-group">
                        <label for="ageLimit" class="col-form-label">Ограничение по возрасту:</label>
                        <input type="text" readonly="readonly" class="form-control" id="ageLimit" name="ageLimit">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label">Цена:</label>
                        <input type="text" readonly="readonly" class="form-control" id="price" name="price">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js" defer></script>
</html>
