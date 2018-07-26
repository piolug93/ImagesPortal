<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ImagesPortal</title>
    <%@include file="fragments/resources.jspf"%>
  </head>
  <body>
    <%@include file="fragments/header.jspf"%>
    <div class="container">
        <div class="post row">
            <div class="col col-md-10 col-md-offset-1 border">
                <div class="border post-top">
                    <h3><a href="#">Title</a></h3>
                    <h5>Description</h5>
                </div>
                <img src="../resources/images/placeholder.svg" class="img-responsive">
                <div class="post-footer">
                    <div class="col col-md-10">
                        <h6>Dodane przez: piolug93 Dnia: 12/07/2018</h6>
                    </div>
                    <div class="col col-md-2 input-group">
                        <button class="btn btn-success">+</button>
                        <span class="score">0</span>
                        <button class="btn btn-danger vote-down">-</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>
