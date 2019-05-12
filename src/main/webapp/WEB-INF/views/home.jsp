<jsp:include page="includes/navbar.jsp" />
<jsp:include page="css.jsp"/>
<jsp:include page="css.jsp"/>
<jsp:include page="js.jsp"/>
<jsp:include page="includes/postEditor.jsp">
    <jsp:param name="locationList" value="${locationList}"></jsp:param>
    <jsp:param name="visibilities" value="${visibilities}"></jsp:param>
</jsp:include>
<jsp:include page="includes/posts.jsp">
    <jsp:param name="posts" value="${posts}"></jsp:param>
</jsp:include>