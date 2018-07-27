<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../fragments/headTag.jsp"/>

<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">

    <link rel="stylesheet" href="resources/css/font-awesome.css" type="text/css">

    <jsp:include page="../../fragments/bodyHeader.jsp"/>
    <%-- <jsp:include page="../../fragments/search.jsp"/>--%>
    <script type="text/javascript" src="resources/js/periodicalUtil.js" defer></script>
    <script type="text/javascript" src="resources/js/tablePeriodical.js" defer></script>
</head>
<body>
<div class="jumbotron">
    <div class="view-box">
        <table class="table tsble-sm  table-light table-striped display table-bordered" id="datatable">

            <thead>
            <button type="button" class="btn btn-info" onclick="add()"><i class="fa fa-plus"></i>
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            </button>
            <tr>
                <th>Index Издания</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Издатель</th>
                <th>Периодичность выхода</th>
                <th>Ограничение по возрасту</th>
                <th>Цена</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="periodicals">
                <jsp:useBean id="periodicals" scope="page" type="com.azarenko.model.Periodical"/>
                <c:if test="${periodicals.archive == false}">
                    <tr>
                        <th>${periodicals.index}</th>
                        <th>${periodicals.title}</th>
                        <th>${periodicals.discription}</th>
                        <th>${periodicals.publisher}</th>
                        <th>${periodicals.outputFrequency}</th>
                        <th>${periodicals.ageLimit}</th>
                        <th>${periodicals.price}</th>
                        <th><a href="edit?id=<c:out value="${periodicals.id}"/>">
                            <button class="btn btn-xs btn-light edit"><i class="fa fa-edit"></i></button>
                        </a>
                        </th>
                        <th><a class="btn btn-light" type="button" aria-hidden="true"
                               onclick='deleteRow(<c:out value="${periodicals.id}"/>)'>
                            <i class="fa fa-file-archive-o"></i><span class="glyphicon glyphicon-plus"
                                                                      aria-hidden="true"></span></a>
                        </th>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Добавить Издание</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm" method="post">
                    <input type="text" hidden="hidden" class="form-control" id="id" name="id">
                    <div class="form-group">
                        <label for="title" class="col-form-label">Название:</label>
                        <input type="text" class="form-control" id="title" name="title">
                    </div>
                    <div class="form-group">
                        <label for="discription" class="col-form-label">Описание:</label>
                        <input type="text" class="form-control" id="discription" name="discription">
                    </div>
                    <div class="form-group">
                        <label for="publisher" class="col-form-label">Издатель:</label>
                        <input type="text" class="form-control" id="publisher" name="publisher">
                    </div>
                    <div class="form-group">
                        <label for="index" class="col-form-label">Индекс:</label>
                        <input type="text" class="form-control" id="index" name="index">
                    </div>
                    <div class="form-group">
                        <label for="outputFrequency" class="col-form-label">Пероидичность выхода:</label>
                        <input type="text" class="form-control" id="outputFrequency" name="outputFrequency">
                    </div>
                    <div class="form-group">
                        <label for="ageLimit" class="col-form-label">Ограничение по возрасту:</label>
                        <input type="text" class="form-control" id="ageLimit" name="ageLimit">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label">Цена:</label>
                        <input type="text" class="form-control" id="price" name="price">
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
<jsp:include page="../../fragments/footer.jsp"/>
</body>

<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js" defer></script>


</html>
