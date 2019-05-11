<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:bundle basename="login"/></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/template-styles/locationForm.css">
</head>
<body>
<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="add.locaiton"/></div>
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>
                    <div class="card-body">
                        <form:form action="${pageContext.request.contextPath}/add-location" method="POST" class="locationForm" onsubmit="false" modelAttribute="location">
                            <form:input path="id" type="hidden" value="${location.id}"></form:input>
                            <div class="form-group row">
                                <label for="locationName" class="col-md-4 col-form-label text-md-right"><fmt:message key="location.name"/></label>
                                <div class="col-md-6">
                                    <form:input type="text" id="locationName" class="form-control required" path="locationName"
                                                required="true" autofocus="true"></form:input>
                                    <form:errors path="locationName" class="locationName-invalid-message"></form:errors>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="add.locaiton"/>
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/js/template-scripts/locationForm.js"></script>
</html>