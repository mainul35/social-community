<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:message key="login"/></title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/template-styles/postEditor.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/ckeditor.css">
</head>
<body>
<div class="content-wrapper post-editor-content-wrapper">
    <div class="container">
        <div class="row mb-2">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header"><fmt:message key="share.what.you.are.thinking"/></div>
                    <div class="card-body">
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
                        <form:form action="${pageContext.request.contextPath}/post-editor" method="post"
                                   class="statusForm" modelAttribute="status">
                            <input type="hidden" name="id" value="${status.id}"/>
                            <div class="form-group row">
                                <label for="title" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="title"/></label>
                                <div class="col-md-6">
                                    <form:input path="title" id="title" value="${status.title}" class="form-control"></form:input>
                                    <form:errors path="title" class="title-invalid-message"></form:errors>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="status" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="write.post"/></label>
                                <div class="col-md-6">
                                    <form:textarea path="status" id="status" value="${status.status}" class="form-control"></form:textarea>
                                    <form:errors path="status" class="status-invalid-message"></form:errors>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="location" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="location"/></label>
                                <div class="col-md-6">
                                    <form:select path="locations" id="location" cssStyle="width: 100%">
                                        <form:option selected="true"
                                                     value="${empty status.locations?'Please Select a Location':status.locations}"/>
                                        <form:options items="${locationList}" />
                                    </form:select>
                                    <form:errors path="locations" class="locations-invalid-message"></form:errors>
                                </div>
                            </div>

                            <%--<div class="form-group row">--%>
                                <%--<label for="file" class="col-md-4 col-form-label text-md-right"><fmt:message--%>
                                        <%--key="files"/></label>--%>
                                <%--<div class="col-md-6">--%>
                                    <%--<input type="file" accept=".image/jpeg, .image/png" id="file" class="form-control" name="file" multiple="multiple">--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="form-group row">
                                <label for="status" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="visibility"/></label>
                                <div class="col-md-6">
                                    <ul style="list-style: none">
                                        <c:forEach var="item" items="${visibilities}">
                                            <li><form:radiobutton path="visibility" value="${item}" />${item} </li>
                                        </c:forEach>
                                    </ul>
                                    <%--<form:radiobuttons path="visibility" element="li" cssStyle="list-style: none;" items="${visibilities}"/>--%>
                                    <form:errors path="visibility" class="visibility-invalid-message"></form:errors>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="post"/>
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/js/template-scripts/postEditor.js"></script>
</html>