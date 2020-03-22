$(document).ready(function () {
 
    $("#submit2").click(function (event) {
    	 
    	event.preventDefault();
    	alert('entering js');
    	 fire_ajax_submit();
    	 
    });
 
});
function fire_ajax_submit() {

    var request = {}
    request["username"] = $("#user").val();
    request["password"] = $("#pass").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/token/generate-token",
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        success: function (data) {
        	callbackfn(data);
        },
        error: function (e) {callbackfn(e)}
    });

}
function callbackfn(data)
{
	
	window.location.replace("http://localhost:8086/token/welcome");
}
$(document).ready(function () {
	 
    $("#register").click(function (event) {
    	 
    	event.preventDefault();
    	alert('entering js register');
    	fire_ajax_submit_register();
    	 
    });
 
});
function fire_ajax_submit_register() {

    var request = {}
    request["username"] = $("#user1").val();
    request["password"] = $("#pass").val();
    request["role"] = $("#role").val();
    request["age"] = $("#age").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/token/register",
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        success: function (data) {
        	callbackrg(data);
        },
        error: function (e) {callbackrg(e);}
    });

}
function callbackrg(data)
{
	
	window.location.replace("http://localhost:8086/token/registerdone");
}