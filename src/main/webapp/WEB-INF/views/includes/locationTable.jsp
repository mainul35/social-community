<%--
  Created by IntelliJ IDEA.
  User: Syed Mainul Hasan
  Date: 5/11/2019
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.mainul35.entity.Location" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
</head>
<body>
<div class="cotainer">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="location.name"/></th>
                    <th scope="col"><fmt:message key="update"/></th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="locationTableRow.jsp">
                    <jsp:param name="locationList" value="${locationList}" />
                </jsp:include>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
