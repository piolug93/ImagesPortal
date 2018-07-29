<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ImagesPortal</title>
    <%@include file="fragments/resources.jspf"%>
  </head>
  <body>
    <%@include file="fragments/header.jspf"%>
    <c:forEach var="tmpPost" items="${requestScope.posts}">
        <c:choose>
            <c:when test="${tmpPost.mainPage && !waiting && !tmpPost.secured}">
                <div class="container-post">
                    <c:set var="post" scope="request" value="${tmpPost}"/>
                    <%@include file="fragments/post.jspf"%>
                </div>
            </c:when>
            <c:when test="${!tmpPost.mainPage && waiting && !tmpPost.secured}">
                <div class="container-post">
                    <c:set var="post" scope="request" value="${tmpPost}"/>
                    <%@include file="fragments/post.jspf"%>
                </div>
            </c:when>
        </c:choose>
    </c:forEach>
  <%@include file="fragments/footer.jspf"%>
  </body>
</html>
