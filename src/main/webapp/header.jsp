<%-- 
    Document   : header
    Created on : Jun 10, 2020, 10:43:59 AM
    Author     : HP
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="index.html">Zenith</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto" style="display:flex;justify-content: center;align-items: center">
                <li class="nav-item active">
                    <a class="nav-link" href="index.html">Home
                        <span class="sr-only">(current)</span>
                        <c:set var="check" scope="session"  value='${sessionScope["LoggedIn"]}'/>
                    </a>
                </li>
                <c:choose>
                    <c:when test='${check==false || check == null}'>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Login</a>
                        </li>
                    </c:when>
                    <c:otherwise>

                        <li class="nav-item">
                            <a class="nav-link" href="Logout">Logout</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="download.html?appId=-1">My downloads</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
                <li class="nav-item">
                    <form action="searchResults.html" method="POST">
                    
                        <input type="text" name="searchTerm" id="searchTerm" style="margin-left: 10px;margin-right: 0px"/> <button id="myButton" style="margin-left: 0px"><i class="fa fa-search"></i></button>
                        <script type="text/javascript">
                        document.getElementById("myButton").onclick = function () {
                            location.href = "searchResults.html";
                        };
                        </script>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
