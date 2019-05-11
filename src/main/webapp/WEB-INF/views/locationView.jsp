<jsp:include page="includes/navbar.jsp" />
<jsp:include page="css.jsp"/>
<jsp:include page="js.jsp"/>
<jsp:include page="includes/locationForm.jsp"/>
<jsp:include page="includes/locationTable.jsp">
    <jsp:param name="locationList" value="${locationList}" />
</jsp:include>