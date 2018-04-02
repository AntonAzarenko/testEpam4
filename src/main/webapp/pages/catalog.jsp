<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h1>Lybriary</h1>
    <div>
        <table>
            <tr>
                <th>Id Publications</th>
                <th>Title</th>
                <th>Discription</th>
                <th>price</th>
            </tr>
            <tr>
                <c:forEach items="${catalogs}" var="catalog">
                    <!--<jsp:useBean id="catalog" scope="page" type="com.azarenko.model.Catalog"/>-->
                    <th>${catalog.id}</th>
                    <th>${catalog.title}</th>
                    <th>${catalog.description}</th>
                    <th>${catalog.price}</th>
                </c:forEach>
                <th></th>
            </tr>
        </table>
    </div>
</head>
<body>

</body>
</html>
