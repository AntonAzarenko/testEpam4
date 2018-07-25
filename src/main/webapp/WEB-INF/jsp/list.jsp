<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../fragments/headTag.jsp"/>

<html>
<head>
    <title>List</title>

    <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">

    <link rel="shortcut icon" href="../../css/font-awesome.css" type="text/css">

    <jsp:include page="../../fragments/bodyHeader.jsp"/>
    <jsp:include page="../../fragments/search.jsp"/>
    <script type="text/javascript" src="resources/js/periodicalUtil.js" defer></script>
</head>
<body>

<div class="view-box">


    <div class="p-3 mb-2 bg-light text-dark">
        <br>
        <table class="table table-striped display" id="datatable">
            <tr>
                <thead>
                <th><a style="text-decoration: none #1d21ff" href="sortIndex">Index Издания</a></th>
                <th><a style="text-decoration: none #1d21ff" href="sortTitle">Название</a></th>
                <th>Описание</th>
                <th>Издатель</th>
                <th>Периодичность выхода</th>
                <th>Ограничение по возрасту</th>
                <th>Цена</th>
                <th>Редактировать</th>
                <th>Удалить</th>
                </thead>
            </tr>
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
                            <button class="btn btn-light">Редактировать</button>
                        </a>
                        </th>
                        <th><a class=" btn btn-primary " onclick='deleteRow(<c:out value="${periodicals.id}"/>)'> В архив

                        </a>
                        </th>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

        <br>
        <button type="button" class="btn btn-info" onclick="add()">
        ">
        Добавить
        </button>
        <%-- <div class="align-content-center">
             <a class="btn btn-primary btn-light">Добавить</a>
         </div>--%>
    </div>
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
                    <div class="form-group">
                        <label for="title" class="col-form-label">Название:</label>
                        <input type="text" class="form-control" id="title">
                    </div>
                    <div class="form-group">
                        <label for="discription" class="col-form-label">Описание:</label>
                        <input type="text" class="form-control" id="discription">
                    </div>
                    <div class="form-group">
                        <label for="publisher" class="col-form-label">Издатель:</label>
                        <input type="text" class="form-control" id="publisher">
                    </div>
                    <div class="form-group">
                        <label for="index" class="col-form-label">Индекс:</label>
                        <input type="text" class="form-control" id="index">
                    </div>
                    <div class="form-group">
                        <label for="output_frequency" class="col-form-label">Пероидичность выхода:</label>
                        <input type="text" class="form-control" id="output_frequency">
                    </div>
                    <div class="form-group">
                        <label for="age_limit" class="col-form-label">Ограничение по возрасту:</label>
                        <input type="text" class="form-control" id="age_limit">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                <button onclick="save()" class="btn btn-primary">Добавить</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../fragments/footer.jsp"/>
</body>

<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js" defer></script>

<script type="text/javascript">

    var ajaxUrl = 'ajax/periodical/';
    var datatableApi;



    $(function () {
        datatableApi = $('#datatable').DataTable({
            "paging": false,
            "info": false,
            "colunms": [
                {
                    "data" : "index"
                },
                {
                    "data": "title"
                },
                {
                    "data": "discription"
                },
                {
                    "data": "publisher"
                },
                {
                    "data": "outputFrequency"
                },
                {
                    "data": "ageLimit"
                },
                {
                    "data": "price"
                },
                {
                    sDefaultContent: "",
                    bSortable: false
                },
                {
                    sDefaultContent: "",
                    bSortable: false
                }
            ],
            "order": [
                [0,
                    "asc"
                ]
            ]
        });

        makeEditable();

    });

</script>


</html>
