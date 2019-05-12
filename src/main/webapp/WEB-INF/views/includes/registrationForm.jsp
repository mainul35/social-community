<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:message key="register"/></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/template-styles/registrationForm.css">
</head>
<body>
<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="register"/></div>
                    <c:if test="${errorMessage != null}">
                        <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>
                    <c:if test="${successMessage != null}">
                        <div class="alert alert-success" role="alert">
                                ${successMessage}
                        </div>
                    </c:if>
                    <div class="card-body">
                        <form:form action="${pageContext.request.contextPath}/register" method="POST" class="registrationForm" onsubmit="false" modelAttribute="user">
                            <form:input path="id" type="hidden" value="${user.id}"></form:input>
                            <div class="form-group row">
                                <label for="name" class="col-md-4 col-form-label text-md-right"><fmt:message key="name"/></label>
                                <div class="col-md-6">
                                    <form:input type="text" id="name" class="form-control required" length="2..70" path="name"
                                                required="true" autofocus="true"></form:input>
                                    <form:errors path="name" class="name-invalid-message"></form:errors>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right"><fmt:message key="email.address"/></label>
                                <div class="col-md-6">
                                    <form:input type="email" id="email_address" class="form-control required" length="5..60" path="email"
                                                required="true"></form:input>
                                    <form:errors path="email" class="email-invalid-message"></form:errors>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="username" class="col-md-4 col-form-label text-md-right"><fmt:message key="username"/></label>
                                <div class="col-md-6">
                                    <form:input type="text" id="username" class="form-control required" length="5..60" path="username"
                                                required="true"></form:input>
                                    <form:errors path="username" class="username-invalid-message"></form:errors>
                                </div>
                            </div>
                            <div  class="form-group row">
                                <label for="location" class="col-md-4 col-form-label text-md-right"><fmt:message key="location"/></label>
                                <div class="col-md-6">
                                    <form:select path="myLocation" id="location" cssStyle="width: 100%">
                                        <form:option selected="true"
                                                     value="${empty status.locations?'Please Select a Location':status.locations}"/>
                                        <form:options items="${locationList}" />
                                    </form:select>
                                    <form:errors path="myLocation" class="myLocation-invalid-message"></form:errors>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><fmt:message key="password"/></label>
                                <div class="col-md-6">
                                    <form:input type="password" id="password"  length="6..70" class="form-control required" path="password" required="true"></form:input>
                                    <form:errors path="password" class="password-invalid-message"></form:errors>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="register"/>
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
        src="${pageContext.request.contextPath}/resources/js/template-scripts/registrationForm.js"></script>
</html>