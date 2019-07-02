<%--
  Created by IntelliJ IDEA.
  User: Syed Mainul Hasan
  Date: 5/12/2019
  Time: 1:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:include page="css.jsp"/>
    <jsp:include page="js.jsp"/>
</head>
<body>
<jsp:include page="includes/navbar.jsp" />
<jsp:include page="includes/posts.jsp">
    <jsp:param name="posts" value="${posts}"></jsp:param>
</jsp:include>

