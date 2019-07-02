<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/template-styles/post.css">
</head>
<body>
<div class="content-wrapper">
    <div class="container">
        <c:forEach items="${posts}" var="post">
            <div class="row mb-2">
                <div class="col-md-12">
                    <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary">By: ${post.owner.name}</strong>
                            <sec:authorize access="isAuthenticated()">
                            <div class="edit-delete-block">
                                <span class="item-edit"><a href="/edit-post?id=${post.id}">Edit</a></span> |
                                <span class="item-delete"><a href="/delete-post?id=${post.id}">Delete</a></span>
                            </div>
                            </sec:authorize>
                            <div class="location-block">Locations:
                                <c:forEach var="location" items="${post.visibilityLocation}">
                                    <span class="location-item">${location.location}</span>
                                </c:forEach>
                            </div>
                            <h5 class="mb-0">${post.title}</h5>
                            <div class="mb-1 text-muted">${post.createdOn} | ${post.visibility}</div>
                            <p class="card-text mb-auto">${post.status}</p>
                            <%--<a href="#" class="stretched-link">Continue reading</a>--%>
                        </div>
                        <div class="col-auto d-none d-lg-block">
                            <svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg"
                                 preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
                                 aria-label="Placeholder: Thumbnail"><title>Placeholder</title>
                                <rect width="100%" height="100%" fill="#55595c"></rect>
                                <text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
                            </svg>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>