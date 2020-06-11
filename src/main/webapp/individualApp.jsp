<%-- 
    Document   : index
    Created on : Jun 10, 2020, 10:47:02 AM
    Author     : HP
--%>

<%@page import="java.util.List" %>
<%@page import="com.AppStore.domain.Application" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    <c:set var="app" value='${requestScope["app"]}'/>
    <title><c:out value="${app.getName()}"/></title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="container">

    <div class="row">
        <jsp:include page="/sidebar.jsp"/>

        <div class="col-lg-9">

            <div class="card mt-4" style="display:flex;">
                <img class="card-img-top img-fluid" src='${app.getLogo()}' alt="${app.getName()}"
                     style="width:fit-content;height:auto">
                <span class='text-right'><a href="#"><button class='btn btn-primary'>Download</button></a></span>
                <div class="card-body">
                    <h3 class="card-title"><c:out value="${app.getName()}"/></h3>
                    <p class="card-text"><c:out value="${app.getDescription()}"/></p>
                    <span class="card-text">Downloads : <c:out value="${app.getNumDownloads()}"/>+</span>
                    <span class="text-warning">Rating : <c:out value="${app.getRating()}"/></span>
                </div>
            </div>
            <!-- /.card -->
            <h3 class="card-title" style='margin-top:50px'>Customers also downloaded:</h3>
            <div class="row">

                <c:set var="id1" value="${app.getId()}"/>
                <c:set var="item" value='${requestScope["appList"]}'/>
                <c:forEach var="appli" items="${item}">
                    <c:set var="id2" value="${appli.getId()}"/>
                    <c:if test="${id1!=id2}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card h-100">
                                <a href="individualPage.html?id=${appli.getId()}"><img class="card-img-top"
                                                                                       src="${appli.getLogo()}"
                                                                                       alt="${app.getName()}"></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="individualPage.html?id=${appli.getId()}"><c:out
                                                value="${appli.getName()}"/></a>
                                    </h4>
                                    <h5>Version = <c:out value="${appli.getVersion()}"/></h5>
                                    <p class="card-text"><c:out value="${appli.getDescription()}"/></p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">Rating: <c:out value="${appli.getRating()}"/></small>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/footer.jsp"/>
</body>
</html>
