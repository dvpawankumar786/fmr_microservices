$(document).ready(function () {
 
    $("#form").submit(function (event) {
    	alert('hiiii');
        //stop submit the form event. Do this manually using ajax post function
 
        var loginForm = {}
        loginForm["username"] = $("#user").val();
        loginForm["password"] = $("#pass").val();
 
        
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8086/token/generate-token",
            data: JSON.stringify(loginForm),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {success:function(data) {
                jq("#response").html(data);
            }},
            error: function (e) {}
        });
        
    });
 
});