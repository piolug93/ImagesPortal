<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>ImagesPortal</title>
    <%@include file="fragments/resources.jspf"%>
</head>
<body>
<%@include file="fragments/header.jspf"%>
<div class="container">

    <c:if test="${not empty requestScope.post}">
        <div class="post row">
            <div class="col col-md-10 col-md-offset-1 border">
                <h3>${post.title}</h3>
                <h5>${post.content}</h5>
            </div>
            <img src="images/${post.imageName}" class="img-responsive">
            <div class="post-footer">
                <div class="col col-md-10">
                    <h6>Dodane prze: ${post.author.login} Dnia: <fmt:formatDate value="${post.date}" pattern="dd/MM/YYYY"/></h6>
                </div>
                <div class="col col-md-2 input-group">
                    <button class="btn btn-success">+</button>
                    <span class="score">${post.voteUp - post.voteDown}</span>
                    <button class="btn btn-danger vote-down">-</button>
                </div>
            </div>
        </div>
    </c:if>
</div>
<%@include file="fragments/footer.jspf"%>
</body>
</html>
