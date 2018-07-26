<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImagesPortal</title>
    <%@include file="fragments/resources.jspf"%>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
    <%@include file="fragments/header.jspf"%>

    <div class="container">
        <div class="row">
            <form class="form-horizontal">
                <fieldset>
                    <legend>Dodaj zdjęcie</legend>
                    <div class="col-md-7">
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="textinput">Tytył</label>
                            <div class="col-md-9">
                                <input id="textinput" name="textinput" placeholder="" class="form-control input-md" required="" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="textinput">Źródło</label>
                            <div class="col-md-9">
                                <input id="textinput" name="textinput" placeholder="" class="form-control input-md" required="" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="prependedcheckbox">Hasło</label>
                            <div class="col-md-7">
                                <div class="input-group">
                               <span class="input-group-addon">
                                  <input type="checkbox">
                               </span>
                                    <input id="prependedcheckbox" name="prependedcheckbox" class="form-control" type="password">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="textarea">Opis</label>
                            <div class="col-md-7">
                                <textarea class="form-control" id="textarea" name="textarea"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="singlebutton"></label>
                            <div class="col-md-8">
                                <button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5 left-line">
                        <div class="wrapper">
                            <div class="box">
                                <div class="js--image-preview" id="image">
                                    <img id="output"/>
                                </div>
                                <div class="upload-options">
                                    <label>
                                        <input type="file" class="image-upload" accept="image/*" onchange="loadFile(event)"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </form>

        </div>
    </div>

    <%@include file="fragments/footer.jspf"%>
</body>
</html>
