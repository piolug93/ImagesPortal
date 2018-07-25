<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImagesPortal</title>
    <%@include file="fragments/resources.jspf" %>
</head>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <c:choose>
                            <c:when test="${requestScope['javax.servlet.forward.request_uri'] == '/register'}">
                                <div class="col-xs-6">
                                    <a href="#" id="login-form-link">Login</a>
                                </div>
                                <div class="col-xs-6">
                                    <a href="#" class="active" id="register-form-link">Register</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-xs-6">
                                    <a href="#" class="active" id="login-form-link">Login</a>
                                </div>
                                <div class="col-xs-6">
                                    <a href="#" id="register-form-link">Register</a>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                                    <form id="login-form" action="login" method="post" role="form"
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
                                            <c:when test="${requestScope.error == 'bad_login'}">
                                                <div class="form-group notification alert-danger">Błędny login i/lub hasło.</div>
                                            </c:when>
                                        </c:choose>
                                        <div class="form-group">
                                            <input type="text" name="login" id="username" tabindex="1" class="form-control"
                                                   placeholder="Login" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" tabindex="2"
                                                   class="form-control" placeholder="Hasło">
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                           class="form-control btn btn-login" value="Zaloguj się">
                                                </div>
                                            </div>
                                        </div>
                                    </form>

                                    <form id="register-form" action="register" method="post"
                                          role="form" style="
                                    <c:choose>
                                    <c:when test="${requestScope['javax.servlet.forward.request_uri'] == '/register'}">
                                            display: block;
                                    </c:when>
                                    <c:otherwise>
                                            display: none;
                                    </c:otherwise>
                                    </c:choose>">
                                        <c:choose>
                                            <c:when test="${requestScope.error == 'false'}">
                                                <div class="form-group notification alert-success">Rejestracja przebiegła pomyślnie</div>
                                            </c:when>
                                            <c:when test="${requestScope.error == 'repassword_incorrect'}">
                                                <div class="form-group notification alert-danger">Hasła niezgodne</div>
                                            </c:when>
                                            <c:when test="${requestScope.error == 'email_exist'}">
                                                <div class="form-group notification alert-danger">Użytkownik z takim e-mail istnieje już w bazie</div>
                                            </c:when>
                                            <c:when test="${requestScope.error == 'email_incorrect'}">
                                                <div class="form-group notification alert-danger">E-mail niepoprawny</div>
                                            </c:when>
                                            <c:when test="${requestScope.error == 'login_exist'}">
                                                <div class="form-group notification alert-danger">Użytkownik o takim loginie istnieje już w bazie</div>
                                            </c:when>
                                        </c:choose>
                                        <div class="form-group">
                                            <input type="text" name="login" id="username" tabindex="1" class="form-control"
                                                   placeholder="Login" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="email" name="email" id="email" tabindex="1" class="form-control"
                                                   placeholder="E-mail" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" tabindex="2"
                                                   class="form-control" placeholder="Hasło">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="confirm-password" id="confirm-password" tabindex="2"
                                                   class="form-control" placeholder="Powtórz hasło">
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="register-submit" id="register-submit"
                                                           tabindex="4" class="form-control btn btn-register"
                                                           value="Zarejetruj Się">
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
</div>
<%@include file="fragments/footer.jspf"%>
</body>
</html>
