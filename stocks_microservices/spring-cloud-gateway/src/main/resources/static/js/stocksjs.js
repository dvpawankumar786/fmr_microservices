$(document).ready(function () {
 
    $("#stock").click(function (event) {
    	
    	event.preventDefault();
    	alert('insied js');
    	 fire_ajax_submit_stock();
    	 
    });
 
});
function fire_ajax_submit_stock() {

	
	var request = {}
    request["portfolioId"] = $("#pid").val();
	
	var token=$("#token").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/verify/getStockDetails",
        headers: {
            'Authorization': 'Bearer '+token,
            },
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        success: function (data) {
        	callbackfn(data);
        },
        error: function (e) {callbackfn(e);}
    });

}
function callbackfn(data)
{
	
	window.location.replace("http://localhost:8086/token/disstocks");
}
$(document).ready(function () {
	 
    $("#logoutid").click(function (event) {
    	 
    	event.preventDefault();
    	alert('entering logout js');
    	fire_ajax_logout();
    	 
    });
 
});
function fire_ajax_logout() {
	
	 var request = {}
	 request["portfolioId"] = $("#portifd").val();
	var token=$("#token").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/verify/logoutser",
        headers: {
            'Authorization': 'Bearer '+token,
            },
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        success: function (data) {
        	callbackfnlg(data);
        },
        error: function (e) {
        	
        	callbackfnlg(e);}
    });
}
function callbackfnlg(data)
{
	
	window.location.replace("http://localhost:8086/token/logout");
}
$(document).ready(function () {
	 
    $("#portid").click(function (event) {
    	
    	event.preventDefault();
    	alert('insied port js');
    	 fire_ajax_submit_portfolio();
    	 
    });
 
});
function fire_ajax_submit_portfolio() {

	
	var request = {}
    request["custid"] = $("#custid").val();
	
	var token=$("#token").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/verify/getAllPortfolioDetails",
        headers: {
            'Authorization': 'Bearer '+token,
            },
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        success: function (data) {
        	callbackpf(data);
        },
        error: function (e) {callbackpf(e);}
    });

}
function callbackpf(data)
{
	
	window.location.replace("http://localhost:8086/token/disports");
}
$(document).ready(function () {
	 
    $("#home").click(function (event) {
    	
    	event.preventDefault();
    	alert('insied home js');
    	window.location.replace("http://localhost:8086/token/login");
    	 
    });
 
});
$(document).ready(function () {
	 
    $("#buystock").click(function (event) {
    	
    	event.preventDefault();
    	alert('insied stock buy js');
    	fire_ajax_call_masterstocks();
    });
 
});
function fire_ajax_call_masterstocks(){
	
	var request = {}
    request["portfolioId"] = $("#pid").val();
	
	var token=$("#token").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/verify/getStockMasterDetails",
        headers: {
            'Authorization': 'Bearer '+token,
            },
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        success: function (data) {
        	callbackfnmaster(data);
        },
        error: function (e) {callbackfnmaster(e);}
    });
}
function callbackfnmaster(data)
{
	
	window.location.replace("http://localhost:8086/token/dismasterstocks");
}