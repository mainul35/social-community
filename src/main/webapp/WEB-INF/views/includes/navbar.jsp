<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/navbar/navbar.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
    <div class="container">
        <a class="navbar-brand" href="#"><fmt:message key="social.community"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <c:if test="${session.getAttribute('username') == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/login"><fmt:message key="login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register"><fmt:message key="register"/></a>
                </li>
                </c:if>
                <c:if test="${session.getAttribute('username') != null}">
                    <li class="nav-item"></li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout"><fmt:message key="logout"/></a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>