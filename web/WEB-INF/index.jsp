<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ImagesPortal</title>
    <%@include file="fragments/resources.jspf"%>
  </head>
  <body>
    <%@include file="fragments/header.jspf"%>
    <c:if test="${not empty requestScope.posts}">
        <c:forEach var="tmpPost" items="${requestScope.posts}">
            <div class="container-post">
                <c:set var="post" scope="request" value="${tmpPost}"/>
                <%@include file="fragments/post.jspf"%>
            </div>
        </c:forEach>
    </c:if>
  <%@include file="fragments/footer.jspf"%>
  </body>
</html>
