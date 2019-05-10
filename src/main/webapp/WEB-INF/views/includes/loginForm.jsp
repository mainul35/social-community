<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:bundle basename="login"/></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/loginForm/loginForm.css">
</head>
<body>
<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="login"/></div>
                    <div class="card-body">
                        <form action="/login-processing" method="post" class="loginForm">
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right"><fmt:message key="email.address"/></label>
                                <div class="col-md-6">
                                    <input type="email" id="email_address" class="form-control required" length="5..60" name="email"
                                           required>
                                    <span class="email-invalid-message"></span>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><fmt:message key="password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password"  length="6..70" class="form-control required" name="password" required>
                                    <span class="password-invalid-message"></span>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="login"/>
                                </button>
                                <a href="#" class="btn btn-link">
                                    <fmt:message key="forgot.password"/>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>