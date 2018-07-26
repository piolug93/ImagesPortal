$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});

var loadFile = function(event) {
    document.getElementById('image').style.height = "100%";
    $(document.getElementById('image')).addClass('special');
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
};