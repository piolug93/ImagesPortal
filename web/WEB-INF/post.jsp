<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImagesPortal</title>
    <%@include file="fragments/resources.jspf"%>
</head>
<body>
<%@include file="fragments/header.jspf"%>
<div class="container">
    <c:if test="${not empty requestScope.post}">
        <c:choose>
            <c:when test="${(post.secured && allow) || !post.secured}">
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
            </c:when>
            <c:otherwise>
                <div class="post row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="panel panel-login">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <span class="centered"><h3>Wpisz hasło dostępu.</h3></span>
                                    </div>
                                </div>
                                <hr>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <form id="login-form" action="post?id=${post.id}" method="post" role="form"
                                              style="
                                              <c:choose>
                                              <c:when test="${requestScope['javax.servlet.forward.request_uri'] == '/register'}">
                                                      display:none;
                                              </c:when>
                                              <c:otherwise>
                                                      display: block;
                                              </c:otherwise>
                                                      </c:choose>">
                                            <c:choose>
                                                <c:when test="${requestScope.error == 'bad_password'}">
                                                    <div class="form-group notification alert-danger">Błędne hasło.
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" tabindex="2"
                                                       class="form-control" placeholder="Hasło">
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                               class="form-control btn btn-login" value="Weryfikuj">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>
<%@include file="fragments/footer.jspf"%>
</body>
</html>
