<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImagesPortal</title>
    <%@include file="../fragments/resources.jspf"%>
</head>
<body>
    <%@include file="../fragments/header.jspf"%>
    <div class="container">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <form class="form-sign" action="login" method="post">
                <h2 class="form-signin-heading">Zaloguj się</h2>
                <input name="login" type="text" class="form-control" placeholder="Nazwa użytkownika" required autofocus>
                <input name="password" type="password" class="form-control" placeholder="Hasło" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
                <a href="registration">Zarejestruj</a>
            </form>
        </div>
    </div>
</body>
</html>
