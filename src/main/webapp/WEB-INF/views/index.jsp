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
<c:choose>
    <c:when test="${template == 'registrationForm'}">
        <jsp:include page="includes/registrationForm.jsp" >
            <jsp:param name="locationList" value="${locationList}"></jsp:param>
        </jsp:include>
    </c:when>
    <c:otherwise>
        <jsp:include page="includes/${template}.jsp" />
    </c:otherwise>
</c:choose>
</body>
</html>