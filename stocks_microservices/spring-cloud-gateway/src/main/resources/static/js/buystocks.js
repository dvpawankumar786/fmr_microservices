$(document).ready(function () {
 
    $("#buystockid").click(function (event) {
    	 
    	event.preventDefault();
    	alert('entering save stock js');
    	 fire_ajax_submit_save();
    	 
    });
 
});
function fire_ajax_submit_save() {

    var request = {}
    request["symbol"] = $("#symbol").val();
    request["type"] = $("#type").val();
    request["lastDividend"] = $("#lastDividend").val();
    request["fixedDividend"] = $("#fixedDividend").val();
    request["parValue"] = $("#parValue").val();
    request["tickerPrice"] = $("#tickerPrice").val();

    var token=$("#token").val();
    
    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/verify/saveStockDetailsIn",
        headers: {
            'Authorization': 'Bearer '+token,
            },
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
	
	window.location.replace("http://localhost:8086/token/storestocks");
}