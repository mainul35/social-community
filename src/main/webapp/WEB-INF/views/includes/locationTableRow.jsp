<%--
  Created by IntelliJ IDEA.
  User: Syed Mainul Hasan
  Date: 5/11/2019
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:forEach items="${locationList}" var="location" varStatus="locationCounter">
    <tr>
        <th scope="row">${locationCounter.index + 1}</th>
        <td>${location.locationName}</td>
        <td><a href="${pageContext.request.contextPath}/update-location?id=${location.id}"><fmt:message key="update"/></a></td>
    </tr>
</c:forEach>
